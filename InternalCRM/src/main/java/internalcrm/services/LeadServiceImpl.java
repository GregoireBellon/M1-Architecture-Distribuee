package internalcrm.services;

import java.time.LocalDate;
import java.util.Set;
import internalcrm.models.InternalLeadDto;
import internalcrm.repositories.LeadRepository;

public class LeadServiceImpl implements LeadService {

    private final static LeadRepository leadRepository = LeadRepository.getInstance();

    @Override
    public Set<InternalLeadDto> getLeads(double borneInfSalaire, double borneSupSalaire) {
        return leadRepository.findLeadsBySalary(borneInfSalaire, borneSupSalaire);
    }

    @Override
    public Set<InternalLeadDto> getLeadsByDate(LocalDate borneInfSalaire, LocalDate borneSupSalaire) {
        return leadRepository.findLeadsByCreationDate(borneInfSalaire.atStartOfDay(), borneSupSalaire.atStartOfDay());
    }

    @Override
    public void addLead(InternalLeadDto lead) {
        leadRepository.addLead(lead);
    }

    @Override
    public void deleteLead(InternalLeadDto lead) {
        leadRepository.deleteLead(lead.id());
    }

    @Override
    public Set<InternalLeadDto> getAllLeads() {
        return leadRepository.findAll();
    }

}
