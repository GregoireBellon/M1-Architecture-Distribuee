package com.ArchiDistribuee.VirtualCRM.repository;

import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;

import com.ArchiDistribuee.VirtualCRM.entity.InternalLead;

@Repository
public class InternalCRMRepository {
    // TODO
    public Set<InternalLead> getLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        return new HashSet<InternalLead>();
    }

    // TODO
    public Set<InternalLead> getLeadsByDate(Calendar startDate, Calendar endDate) {
        return new HashSet<InternalLead>();
    }
}
