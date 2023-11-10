package internalcrm.services;

import java.time.LocalDate;
import java.util.Set;

import internalcrm.models.InternalLeadDto;

public interface LeadService {

    public Set<InternalLeadDto> getLeads(double borneInfSalaire, double borneSupSalaire);

    public Set<InternalLeadDto> getLeadsByDate(LocalDate borneInfSalaire, LocalDate borneSupSalaire);

    public Set<InternalLeadDto> getAllLeads();

    public void addLead(InternalLeadDto lead);

    public void deleteLead(InternalLeadDto lead);
}
