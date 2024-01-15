package internalcrm.services;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.thrift.TException;
import org.mapstruct.factory.Mappers;

import internalcrm.entity.Lead;
import internalcrm.mappers.LeadMapper;
import internalcrm.repositories.LeadRepository;
import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;
import internalcrm.services.thrift.impl.LeadService;

public class LeadServiceImpl implements LeadService.Iface {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper = Mappers.getMapper(LeadMapper.class);

    private static LeadServiceImpl instance = null;

    private LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public static LeadServiceImpl getInstance(LeadRepository leadRepository) {

        if (instance != null)
            return instance;

        LeadServiceImpl.instance = new LeadServiceImpl(leadRepository);

        return instance;
    }

    @Override
    public Set<ThriftInternalLeadDTO> getLeads(double borneInfSalaire, double borneSupSalaire, String state)
            throws TException {
        var leadsRet = leadMapper.toInternalLeadDto(
                leadRepository.findLeadsBySalary(borneInfSalaire, borneSupSalaire)
                        .stream().filter(lead -> lead.getState().equalsIgnoreCase(state))
                        .collect(Collectors.toSet()));

        System.out.println("Returned " + leadsRet.size() + " leads by salary");

        return leadsRet;

    }

    @Override
    public Set<ThriftInternalLeadDTO> getLeadsByDate(String borneInfDate, String borneSupDate) throws TException {
        var leadsRet = leadMapper.toInternalLeadDto(
                leadRepository.findLeadsByCreationDate(ZonedDateTime.parse(borneInfDate),
                        ZonedDateTime.parse(borneSupDate)));

        System.out.println("Returned " + leadsRet.size() + " leads by date");

        return leadsRet;
    }

    @Override
    public void addLead(ThriftInternalLeadDTO lead) throws TException {

        Lead internal = leadMapper.toInternalLead(lead);

        internal.setId(UUID.randomUUID().toString());

        System.out.println("Ajout du lead : ");
        System.out.println(internal);

        leadRepository.addLead(internal);
    }

    @Override
    public void deleteLead(ThriftInternalLeadDTO lead) throws TException {
        leadRepository.deleteLead(lead.getId());
    }

    @Override
    public Set<ThriftInternalLeadDTO> getAllLeads() throws TException {
        return leadMapper.toInternalLeadDto(leadRepository.findAll());
    }

    @Override
    public void addLeads(Set<ThriftInternalLeadDTO> leads) throws TException {

        for (ThriftInternalLeadDTO thriftInternalLeadDTO : leads) {
            this.addLead(thriftInternalLeadDTO);
        }

    }

    @Override
    public double countLeads() throws TException {
        return leadRepository.findAll().size();
    }

}
