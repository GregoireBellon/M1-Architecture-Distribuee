package internalcrm.services;

import java.time.ZonedDateTime;
import java.util.Set;

import org.apache.thrift.TException;
import org.mapstruct.factory.Mappers;

import internalcrm.mappers.LeadMapper;
import internalcrm.repositories.LeadRepository;
import internalcrm.services.thrift.impl.InternalLeadDTO;
import internalcrm.services.thrift.impl.LeadService;

public class LeadServiceImpl implements LeadService.Iface {

    private final static LeadRepository leadRepository = LeadRepository.getInstance();
    private final static LeadMapper leadMapper = Mappers.getMapper(LeadMapper.class);

    @Override
    public Set<InternalLeadDTO> getLeads(double borneInfSalaire, double borneSupSalaire) throws TException {
        return leadMapper.toInternalLeadDto(leadRepository.findLeadsBySalary(borneInfSalaire, borneSupSalaire));

    }

    @Override
    public Set<InternalLeadDTO> getLeadsByDate(String borneInfSalaire, String borneSupSalaire) throws TException {
        return leadMapper.toInternalLeadDto(
                leadRepository.findLeadsByCreationDate(ZonedDateTime.parse(borneInfSalaire),
                        ZonedDateTime.parse(borneSupSalaire)));
    }

    @Override
    public void addLead(InternalLeadDTO lead) throws TException {
        leadRepository.addLead(leadMapper.toInternalLead(lead));
    }

    @Override
    public void deleteLead(InternalLeadDTO lead) throws TException {
        leadRepository.deleteLead(lead.getId());
    }

    @Override
    public Set<InternalLeadDTO> getAllLeads() throws TException {
        return leadMapper.toInternalLeadDto(leadRepository.findAll());
    }

}
