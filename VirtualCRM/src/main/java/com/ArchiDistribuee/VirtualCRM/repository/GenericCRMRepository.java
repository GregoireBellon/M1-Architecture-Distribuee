package com.ArchiDistribuee.VirtualCRM.repository;

import java.time.ZonedDateTime;
import java.util.Set;

import com.ArchiDistribuee.VirtualCRM.entity.GenericLead;

public interface GenericCRMRepository {
    public Set<? extends GenericLead> getLeads(double lowAnnualRevenue, double highAnnualRevenue, String state);
    public Set<? extends GenericLead> getLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate);
}
