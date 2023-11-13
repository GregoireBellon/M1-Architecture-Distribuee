package com.ArchiDistribuee.VirtualCRM.repository;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ArchiDistribuee.VirtualCRM.entity.InternalLeadDTO;
import com.ArchiDistribuee.VirtualCRM.mapper.TypedInternalLeadDTOMapper;

import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;
import internalcrm.services.thrift.impl.LeadService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class InternalCRMRepository implements InitializingBean {

    private boolean initializationSucceed = false;

    private final String initialisationTolerancy;
    private final String url;
    private final int port;

    private TTransport transport;
    private TProtocol protocol;
    private LeadService.Client client;

    public InternalCRMRepository(
            @Value("${spring.datasource.internalCRM.initialisation}") String initialisationTolerancy,
            @Value("${spring.datasource.internalCRM.url}") String url,
            @Value("${spring.datasource.internalCRM.port}") int port) {
        this.port = port;
        this.url = url;
        this.initialisationTolerancy = initialisationTolerancy;
    }

    private final TypedInternalLeadDTOMapper typedInternalLeadDTOMapper = Mappers
            .getMapper(TypedInternalLeadDTOMapper.class);

    public <T> Optional<T> transportWrapper(Callable<T> clientCall) {

        if (!this.initializationSucceed)
            return Optional.empty();

        try {
            this.transport.open();
            final T result = clientCall.call();
            this.transport.close();

            return Optional.of(result);

        } catch (Exception e) {
            log.error("Erreur lors de la communication Thrift : " + e.getMessage());
        }
        return Optional.empty();
    }

    public Set<InternalLeadDTO> getLeads(double lowAnnualRevenue, double highAnnualRevenue) {

        final Optional<Set<ThriftInternalLeadDTO>> leads = this.transportWrapper(
                () -> this.client.getLeads(lowAnnualRevenue, highAnnualRevenue));

        return typedInternalLeadDTOMapper.fromThriftInternalLeadDTO(leads.orElse(Collections.emptySet()));
    }

    public Set<InternalLeadDTO> getLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate) {

        final Optional<Set<ThriftInternalLeadDTO>> leads = this.transportWrapper(
                () -> this.client.getLeadsByDate(startDate.toString(), endDate.toString()));

        return typedInternalLeadDTOMapper.fromThriftInternalLeadDTO(leads.orElse(Collections.emptySet()));
    }

    public Set<InternalLeadDTO> getAllLeads() {

        final Optional<Set<ThriftInternalLeadDTO>> leads = this.transportWrapper(
                () -> this.client.getAllLeads());

        return typedInternalLeadDTOMapper.fromThriftInternalLeadDTO(leads.orElse(Collections.emptySet()));
    }

    public void addLead(InternalLeadDTO lead) {

        final var mappedLead = typedInternalLeadDTOMapper.toThriftInternalLeadDTO(lead);

        this.transportWrapper(
                () -> {
                    this.client.addLead(mappedLead);
                    return null;
                });

    }

    public void deleteLead(InternalLeadDTO lead) {

        final var mappedLead = typedInternalLeadDTOMapper.toThriftInternalLeadDTO(lead);

        this.transportWrapper(
                () -> {
                    this.client.deleteLead(mappedLead);
                    return null;
                });
    }

    // On throw l'exception si spring.datasource.internalCRM.initialisation = strict
    @Override
    public void afterPropertiesSet() throws TTransportException {
        log.info("Initialisation avec : ");
        log.info("url = " + this.url);
        log.info("port = " + this.port);
        log.info("spring.datasource.internalCRM.initialisation = " + this.initialisationTolerancy);

        TSocket transportInit;

        try {
            transportInit = new TSocket(this.url, this.port);

        } catch (TTransportException e) {
            if (initialisationTolerancy.equals("strict"))
                throw e;
            log.error("La création du Socket Thrift a échoué : " + e.getStackTrace());
            return;
        }

        this.transport = transportInit;
        this.protocol = new TBinaryProtocol(transport);
        this.client = new LeadService.Client(protocol);
        this.initializationSucceed = true;
    }

}
