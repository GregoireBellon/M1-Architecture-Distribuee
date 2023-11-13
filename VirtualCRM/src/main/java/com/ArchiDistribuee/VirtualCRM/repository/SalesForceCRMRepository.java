package com.ArchiDistribuee.VirtualCRM.repository;

import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;

import lombok.Setter;

import java.util.Set;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.time.ZonedDateTime;

@Setter
@Repository
public class SalesForceCRMRepository {
    @Value("${spring.datasource.salesforce.password}")
    private String password;

    @Value("${spring.datasource.salesforce.login}")
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
