package internalcrm.services;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.thrift.TException;
import org.mapstruct.factory.Mappers;

import internalcrm.mappers.LeadMapper;
import internalcrm.models.InternalLead;
import internalcrm.repositories.LeadRepository;
import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;
import internalcrm.services.thrift.impl.LeadService;

public class LeadServiceImpl implements LeadService.Iface {

    private final static LeadRepository leadRepository = LeadRepository.getInstance();
    private final static LeadMapper leadMapper = Mappers.getMapper(LeadMapper.class);

    private static LeadServiceImpl instance = null;

    private LeadServiceImpl() {
    }

    public static LeadServiceImpl getInstance() {

        if (instance != null)
            return instance;

        LeadServiceImpl.instance = new LeadServiceImpl();

        return instance;
    }

    @Override
    public Set<ThriftInternalLeadDTO> getLeads(double borneInfSalaire, double borneSupSalaire, String state)
            throws TException {
        return leadMapper.toInternalLeadDto(
                leadRepository.findLeadsBySalary(borneInfSalaire, borneSupSalaire)
                        .stream().filter(lead -> lead.getState().equalsIgnoreCase(state))
                        .collect(Collectors.toSet()));

    }

    @Override
    public Set<ThriftInternalLeadDTO> getLeadsByDate(String borneInfSalaire, String borneSupSalaire) throws TException {
        return leadMapper.toInternalLeadDto(
                leadRepository.findLeadsByCreationDate(ZonedDateTime.parse(borneInfSalaire),
                        ZonedDateTime.parse(borneSupSalaire)));
    }

    @Override
    public void addLead(ThriftInternalLeadDTO lead) throws TException {

        InternalLead internal = leadMapper.toInternalLead(lead);

        internal.setId(UUID.randomUUID().toString());

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
