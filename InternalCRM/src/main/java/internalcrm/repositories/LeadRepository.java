package internalcrm.repositories;

import java.time.ZonedDateTime;
import java.util.Set;

import internalcrm.entity.Lead;

public interface LeadRepository {

    public Set<Lead> findLeadsBySalary(double minSalaire, double maxSalaire);
    
    public Set<Lead> findLeadsByCreationDate(ZonedDateTime minDate, ZonedDateTime maxDate);

    public void addLead(Lead internalLead);

    public boolean deleteLead(String leadId);

    public Set<Lead> findAll();

}   
