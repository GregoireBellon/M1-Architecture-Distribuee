package com.ArchiDistribuee.VirtualCRM.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

import com.ArchiDistribuee.VirtualCRM.dto.VirtualLeadDto;
import com.ArchiDistribuee.VirtualCRM.entity.GeographicPoint;
import com.ArchiDistribuee.VirtualCRM.entity.InternalLead;
import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;
import com.ArchiDistribuee.VirtualCRM.entity.VirtualLead;
import com.ArchiDistribuee.VirtualCRM.exception.InvalidAnnualRevenuesException;
import com.ArchiDistribuee.VirtualCRM.exception.InvalidDatesException;
import com.ArchiDistribuee.VirtualCRM.mapper.VirtualLeadMapper;
import com.ArchiDistribuee.VirtualCRM.repository.InternalCRMRepository;
import com.ArchiDistribuee.VirtualCRM.repository.OpenStreetMapRepository;
import com.ArchiDistribuee.VirtualCRM.repository.SalesForceCRMRepository;

@Service
public class VirtualLeadService {
    private final InternalCRMRepository internalCRMRepository;
    private final OpenStreetMapRepository openStreetMapRepository;
    private final SalesForceCRMRepository salesForceCRMRepository;

    Logger logger = LoggerFactory.getLogger(VirtualLeadService.class);

    public VirtualLeadService(InternalCRMRepository internalCRMRepository,
            OpenStreetMapRepository openStreetMapRepository, SalesForceCRMRepository salesForceCRMRepository) {
        this.internalCRMRepository = internalCRMRepository;
        this.openStreetMapRepository = openStreetMapRepository;
        this.salesForceCRMRepository = salesForceCRMRepository;
    }

    public Set<VirtualLeadDto> getVirtualLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        if (highAnnualRevenue < lowAnnualRevenue) {
            throw new InvalidAnnualRevenuesException(lowAnnualRevenue, highAnnualRevenue);
        }

        Set<InternalLead> internalLeads = new HashSet<InternalLead>();
        try {
            internalLeads = internalCRMRepository.getLeads(lowAnnualRevenue, highAnnualRevenue, state);
        } catch (Exception e) {
            logger.error("Cannot fetch from internal CRM", e);
        }

        Set<SalesForceLead> salesForceLeads = new HashSet<SalesForceLead>();
        try {
            salesForceLeads = salesForceCRMRepository.getLeads(lowAnnualRevenue, highAnnualRevenue, state);
        } catch (Exception e) {
            logger.error("Cannot fetch from SalesForce", e);
        }

        ArrayList<VirtualLead> virtualLeads = new ArrayList<VirtualLead>();
        virtualLeads.addAll(internalLeads.stream().map(VirtualLead::fromInternalLead).collect(Collectors.toList()));
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

    public Set<VirtualLeadDto> getVirtualLeadsByDate(Calendar startDate, Calendar endDate) {
        if (endDate.before(startDate)) {
            throw new InvalidDatesException(startDate, endDate);
        }

        Set<InternalLead> internalLeads = new HashSet<InternalLead>();
        try {
            internalLeads = internalCRMRepository.getLeadsByDate(startDate, endDate);
        } catch (Exception e) {
            logger.error("Cannot fetch from internal CRM", e);
        }

        Set<SalesForceLead> salesForceLeads = new HashSet<SalesForceLead>();
        try {
            salesForceLeads = salesForceCRMRepository.getLeadsByDate(startDate, endDate);
        } catch (Exception e) {
            logger.error("Cannot fetch from SalesForce", e);
        }

        ArrayList<VirtualLead> virtualLeads = new ArrayList<VirtualLead>();
        virtualLeads.addAll(internalLeads.stream().map(VirtualLead::fromInternalLead).collect(Collectors.toList()));
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
