package com.ArchiDistribuee.VirtualCRM.repository;

import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;

import java.util.Set;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Calendar;

@Repository
public class SalesForceCRMRepository {

    // TODO
    public Set<SalesForceLead> getLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        return new HashSet<SalesForceLead>();
    }

    // TODO
    public Set<SalesForceLead> getLeadsByDate(Calendar startDate, Calendar endDate) {
        return new HashSet<SalesForceLead>();
    }
}
