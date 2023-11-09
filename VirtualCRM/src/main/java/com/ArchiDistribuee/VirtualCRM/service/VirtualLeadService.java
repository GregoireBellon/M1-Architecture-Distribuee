package com.ArchiDistribuee.VirtualCRM.service;

import org.springframework.stereotype.Service;

import com.ArchiDistribuee.VirtualCRM.repository.InternalCRMRepository;
import com.ArchiDistribuee.VirtualCRM.repository.OpenStreetMapRepository;
import com.ArchiDistribuee.VirtualCRM.repository.SalesForceCRMRepository;

@Service
public class VirtualLeadService {
    private final InternalCRMRepository internalCRMRepository;
    private final OpenStreetMapRepository openStreetMapRepository;
    private final SalesForceCRMRepository salesForceCRMRepository;

    public VirtualLeadService(InternalCRMRepository internalCRMRepository,
            OpenStreetMapRepository openStreetMapRepository, SalesForceCRMRepository salesForceCRMRepository) {
        this.internalCRMRepository = internalCRMRepository;
        this.openStreetMapRepository = openStreetMapRepository;
        this.salesForceCRMRepository = salesForceCRMRepository;
    }

}
