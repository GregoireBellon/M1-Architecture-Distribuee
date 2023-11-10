package com.ArchiDistribuee.VirtualCRM.repository;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.Optional;
import java.time.ZonedDateTime;
import java.util.Collections;

import com.ArchiDistribuee.VirtualCRM.entity.TypedInternalLeadDTO;
import com.ArchiDistribuee.VirtualCRM.mapper.TypedInternalLeadDTOMapper;

import internalcrm.services.thrift.impl.InternalLeadDTO;
import internalcrm.services.thrift.impl.LeadService;

@Repository
public class InternalCRMRepository {

    private boolean initializationSucceed = false;

    @Value("${spring.datasource.internalCRM.initialisation}")
    private String initialisationTolerancy = "strict";

    @Value("${spring.datasource.internalCRM.url}")
    private String url = "localhost";

    @Value("${spring.datasource.internalCRM.port}")
    private int port = 9090;

    private final TTransport transport;
    private final TProtocol protocol;
    private final LeadService.Client client;

    private final TypedInternalLeadDTOMapper typedInternalLeadDTOMapper = Mappers
            .getMapper(TypedInternalLeadDTOMapper.class);

    // On throw l'exception si spring.datasource.internalCRM.initialisation = strict
    public InternalCRMRepository() throws TTransportException {

        System.out.println("Initialisation avec : ");
        System.out.println("url = " + this.url);
        System.out.println("port = " + this.port);
        System.out.println("spring.datasource.internalCRM.initialisation = " + this.initialisationTolerancy);

        TSocket transportInit;

        try {
            transportInit = new TSocket(this.url, this.port);

        } catch (TTransportException e) {
            if (initialisationTolerancy.equals("strict"))
                throw e;
            this.transport = null;
            this.protocol = null;
            this.client = null;

            System.err.println("La création du Socket Thrift a échoué : " + e.getStackTrace());

            return;
        }

        this.transport = transportInit;
        this.protocol = new TBinaryProtocol(transport);
        this.client = new LeadService.Client(protocol);
        this.initializationSucceed = true;
    }

    public <T> Optional<T> transportWrapper(Callable<T> clientCall) {

        if (!this.initializationSucceed)
            return Optional.empty();

        try {
            this.transport.open();
            final T result = clientCall.call();
            this.transport.close();

            return Optional.of(result);

        } catch (Exception e) {
            System.err.println("Erreur lors de la communication Thrift : " + e.getMessage());
        }
        return Optional.empty();
    }

    public Set<TypedInternalLeadDTO> getLeads(double lowAnnualRevenue, double highAnnualRevenue) {

        final Optional<Set<InternalLeadDTO>> leads = this.transportWrapper(
                () -> this.client.getLeads(lowAnnualRevenue, highAnnualRevenue));

        return typedInternalLeadDTOMapper.fromInternalLeadDTO(leads.orElse(Collections.emptySet()));
    }

    public Set<TypedInternalLeadDTO> getLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate) {

        final Optional<Set<InternalLeadDTO>> leads = this.transportWrapper(
                () -> this.client.getLeadsByDate(startDate.toString(), endDate.toString()));

        return typedInternalLeadDTOMapper.fromInternalLeadDTO(leads.orElse(Collections.emptySet()));
    }

    public Set<TypedInternalLeadDTO> getAllLeads() {

        final Optional<Set<InternalLeadDTO>> leads = this.transportWrapper(
                () -> this.client.getAllLeads());

        return typedInternalLeadDTOMapper.fromInternalLeadDTO(leads.orElse(Collections.emptySet()));
    }

    public void addLead(TypedInternalLeadDTO lead) {

        final var mappedLead = typedInternalLeadDTOMapper.toInternalLeadDTO(lead);

        this.transportWrapper(
                () -> {
                    this.client.addLead(mappedLead);
                    return null;
                });

    }

    public void deleteLead(TypedInternalLeadDTO lead) {

        final var mappedLead = typedInternalLeadDTOMapper.toInternalLeadDTO(lead);

        this.transportWrapper(
                () -> {
                    this.client.deleteLead(mappedLead);
                    return null;
                });
    }

}
