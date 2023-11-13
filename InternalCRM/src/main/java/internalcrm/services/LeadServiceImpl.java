package internalcrm.services;

import java.time.ZonedDateTime;
import java.util.Set;

import org.apache.thrift.TException;
import org.mapstruct.factory.Mappers;

import internalcrm.mappers.LeadMapper;
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
    public Set<ThriftInternalLeadDTO> getLeads(double borneInfSalaire, double borneSupSalaire) throws TException {
        return leadMapper.toInternalLeadDto(leadRepository.findLeadsBySalary(borneInfSalaire, borneSupSalaire));

    }

    @Override
    public Set<ThriftInternalLeadDTO> getLeadsByDate(String borneInfSalaire, String borneSupSalaire) throws TException {
        return leadMapper.toInternalLeadDto(
                leadRepository.findLeadsByCreationDate(ZonedDateTime.parse(borneInfSalaire),
                        ZonedDateTime.parse(borneSupSalaire)));
    }

    @Override
    public void addLead(ThriftInternalLeadDTO lead) throws TException {
        leadRepository.addLead(leadMapper.toInternalLead(lead));
    }

    @Override
    public void deleteLead(ThriftInternalLeadDTO lead) throws TException {
        leadRepository.deleteLead(lead.getId());
    }

    @Override
    public Set<ThriftInternalLeadDTO> getAllLeads() throws TException {
        return leadMapper.toInternalLeadDto(leadRepository.findAll());
    }

}
