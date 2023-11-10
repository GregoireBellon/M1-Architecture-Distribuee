package com.ArchiDistribuee.VirtualCRM.repository;

import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.time.ZonedDateTime;
import java.util.Calendar;

@Repository
public class SalesForceCRMRepository {
    @Value("${salesforcepassword}")
    private String password;

    @Value("${salesforcelogin}")
    private String login;

    // TODO
    public Set<SalesForceLead> getLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        return new HashSet<SalesForceLead>();
    }

    // TODO
    public Set<SalesForceLead> getLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate) {
        return new HashSet<SalesForceLead>();
    }
}
