package com.ArchiDistribuee.VirtualCRM.service;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ArchiDistribuee.VirtualCRM.dto.VirtualLeadDto;
import com.ArchiDistribuee.VirtualCRM.entity.GeographicPoint;
import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;
import com.ArchiDistribuee.VirtualCRM.entity.InternalLeadDTO;
import com.ArchiDistribuee.VirtualCRM.entity.VirtualLead;
import com.ArchiDistribuee.VirtualCRM.exception.InvalidAnnualRevenuesException;
import com.ArchiDistribuee.VirtualCRM.exception.InvalidDatesException;
import com.ArchiDistribuee.VirtualCRM.mapper.VirtualLeadMapper;
import com.ArchiDistribuee.VirtualCRM.repository.InternalCRMRepository;
import com.ArchiDistribuee.VirtualCRM.repository.OpenStreetMapRepository;
import com.ArchiDistribuee.VirtualCRM.repository.SalesForceCRMRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VirtualLeadService {

    private final InternalCRMRepository internalCRMRepository;
    private final OpenStreetMapRepository openStreetMapRepository;
    private final SalesForceCRMRepository salesForceCRMRepository;

    public Set<VirtualLeadDto> getVirtualLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        if (highAnnualRevenue < lowAnnualRevenue) {
            throw new InvalidAnnualRevenuesException(lowAnnualRevenue, highAnnualRevenue);
        }

        Set<InternalLeadDTO> internalLeads = new HashSet<InternalLeadDTO>();
        try {
            internalLeads = internalCRMRepository.getLeads(lowAnnualRevenue, highAnnualRevenue);
        } catch (Exception e) {
            log.error("Cannot fetch from internal CRM", e);
        }

        Set<SalesForceLead> salesForceLeads = new HashSet<SalesForceLead>();
        try {
            salesForceLeads = salesForceCRMRepository.getLeads(lowAnnualRevenue, highAnnualRevenue, state);
        } catch (Exception e) {
            log.error("Cannot fetch from SalesForce", e);
        }

        ArrayList<VirtualLead> virtualLeads = new ArrayList<VirtualLead>();
        virtualLeads
                .addAll(internalLeads.stream().map(VirtualLead::fromTypedInternalLeadDTO).collect(Collectors.toList()));
        virtualLeads.addAll(salesForceLeads.stream().map(VirtualLead::fromSalesForceLead).collect(Collectors.toList()));

        for (VirtualLead virtualLead : virtualLeads) {
            GeographicPoint geographicPoint = openStreetMapRepository
                    .getGeographicPoint(
                            virtualLead.getStreet(),
                            virtualLead.getCity(),
                            virtualLead.getCountry(),
                            virtualLead.getState(),
                            virtualLead.getPostalCode())
                    .orElse(null);
            virtualLead.setGeographicPoint(geographicPoint);
        }

        return virtualLeads
                .stream()
                .map(VirtualLeadMapper.INSTANCE::virtualLeadToVirtualLeadDto)
                .collect(Collectors.toSet());
    }

    // TODO
    public Set<VirtualLeadDto> getVirtualLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate) {

      if (endDate.isBefore(startDate)) {
            throw new InvalidDatesException(startDate, endDate);
        }

        Set<InternalLeadDTO> internalLeads = new HashSet<InternalLeadDTO>();
        try {
            internalLeads = internalCRMRepository.getLeadsByDate(startDate, endDate);
        } catch (Exception e) {
            log.error("Cannot fetch from internal CRM", e);
        }

        Set<SalesForceLead> salesForceLeads = new HashSet<SalesForceLead>();
        try {
            salesForceLeads = salesForceCRMRepository.getLeadsByDate(startDate, endDate);
        } catch (Exception e) {
            log.error("Cannot fetch from SalesForce", e);
        }

        ArrayList<VirtualLead> virtualLeads = new ArrayList<VirtualLead>();
        virtualLeads
                .addAll(internalLeads.stream().map(VirtualLead::fromTypedInternalLeadDTO).collect(Collectors.toList()));
        virtualLeads.addAll(salesForceLeads.stream().map(VirtualLead::fromSalesForceLead).collect(Collectors.toList()));

        for (VirtualLead virtualLead : virtualLeads) {
            GeographicPoint geographicPoint = openStreetMapRepository
                    .getGeographicPoint(
                            virtualLead.getStreet(),
                            virtualLead.getCity(),
                            virtualLead.getCountry(),
                            virtualLead.getState(),
                            virtualLead.getPostalCode())
                    .orElse(null);
            virtualLead.setGeographicPoint(geographicPoint);
        }

        return virtualLeads
                .stream()
                .map(VirtualLeadMapper.INSTANCE::virtualLeadToVirtualLeadDto)
                .collect(Collectors.toSet());
    }

}
