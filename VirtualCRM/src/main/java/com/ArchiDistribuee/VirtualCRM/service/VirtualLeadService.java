package com.ArchiDistribuee.VirtualCRM.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.ArchiDistribuee.VirtualCRM.dto.VirtualLeadDto;
import com.ArchiDistribuee.VirtualCRM.entity.GenericLead;
import com.ArchiDistribuee.VirtualCRM.entity.VirtualLead;
import com.ArchiDistribuee.VirtualCRM.exception.InvalidAnnualRevenuesException;
import com.ArchiDistribuee.VirtualCRM.exception.InvalidDatesException;
import com.ArchiDistribuee.VirtualCRM.mapper.VirtualLeadMapper;
import com.ArchiDistribuee.VirtualCRM.repository.GenericCRMRepository;
import com.ArchiDistribuee.VirtualCRM.repository.InternalCRMRepository;
import com.ArchiDistribuee.VirtualCRM.repository.OpenStreetMapRepository;
import com.ArchiDistribuee.VirtualCRM.repository.SalesForceCRMRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VirtualLeadService {

    private final OpenStreetMapRepository openStreetMapRepository;
    private final Set<GenericCRMRepository> crmRepositories = new HashSet<>();

    public VirtualLeadService(InternalCRMRepository internalCRMRepository,
            OpenStreetMapRepository openStreetMapRepository, SalesForceCRMRepository salesForceCRMRepository) {
        this.openStreetMapRepository = openStreetMapRepository;

        this.crmRepositories.add(internalCRMRepository);
        this.crmRepositories.add(salesForceCRMRepository);

    }

    public Set<VirtualLeadDto> getVirtualLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {

        // Verification of the input

        if (highAnnualRevenue < lowAnnualRevenue) {
            throw new InvalidAnnualRevenuesException(lowAnnualRevenue, highAnnualRevenue);
        }

        // Collection of data
        final Set<VirtualLead> virtualLeads = new HashSet<>();

        for (GenericCRMRepository crmRepository : this.crmRepositories) {

            try {
                virtualLeads.addAll(crmRepository.getLeads(lowAnnualRevenue, highAnnualRevenue, state).stream()
                        .map(GenericLead::toVirtualLead).toList());
            } catch (Exception e) {
                log.error("Cannot fetch from " + crmRepository.getClass(), e);
            }

        }

        appendGeographicPoint(virtualLeads);

        return virtualLeads
                .stream()
                .map(VirtualLeadMapper.INSTANCE::toVirtualLeadDto)
                .collect(Collectors.toSet());
    }

    private void appendGeographicPoint(Set<VirtualLead> leads) {

        leads.parallelStream().forEach(v -> {
            try {
                v.setGeographicPoint(this.openStreetMapRepository
                        .getGeographicPoint(
                                v.getStreet(),
                                v.getCity(),
                                v.getCountry(),
                                v.getState(),
                                v.getPostalCode())
                        .orElse(null));
            } catch (WebClientRequestException e) {
                log.error("Erreur lors de la récupération du geographic point de " + v.getFirstName() + " "
                        + v.getLastName());
            }
        });

    }

    public Set<VirtualLeadDto> getVirtualLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate) {

        if (endDate.isBefore(startDate)) {
            throw new InvalidDatesException(startDate, endDate);
        }

        // Collection of data
        final Set<VirtualLead> virtualLeads = new HashSet<>();

        for (GenericCRMRepository crmRepository : this.crmRepositories) {

            try {
                virtualLeads.addAll(crmRepository.getLeadsByDate(startDate, endDate).stream()
                        .map(GenericLead::toVirtualLead).toList());
            } catch (Exception e) {
                log.error("Cannot fetch from " + crmRepository.getClass(), e);
            }

        }

        appendGeographicPoint(virtualLeads);

        return virtualLeads
                .stream()
                .map(VirtualLeadMapper.INSTANCE::toVirtualLeadDto)
                .collect(Collectors.toSet());
    }

}
