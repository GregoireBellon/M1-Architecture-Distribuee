package com.ArchiDistribuee.VirtualCRM.repository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;
import com.ArchiDistribuee.VirtualCRM.entity.SalesforceResponse;

@Repository
public class SalesForceCRMRepository {

    @Autowired
    private WebClient salesforceWebClient;
    
    public Set<SalesForceLead> getLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        int _lowAnnualRevenue = (int) lowAnnualRevenue;
        int _highAnnualRevenue = (int) highAnnualRevenue;
        String query = "SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company ,CreatedDate , PostalCode , Country  FROM Lead"
                +
                " WHERE AnnualRevenue > " + _lowAnnualRevenue +
                " AND AnnualRevenue < " + _highAnnualRevenue;

        SalesforceResponse<SalesForceLead> response = salesforceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/query")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<SalesforceResponse<SalesForceLead>>() {})
                .block()
                .getBody();

        return response.records();

    }
 
    public Set<SalesForceLead> getLeadsByDate(ZonedDateTime startDate, ZonedDateTime endDate) {
    String start = startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    String end = endDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    String query = "SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company, CreatedDate, PostalCode , Country " +
                   "FROM Lead " +
                   "WHERE CreatedDate >= " + start + " AND CreatedDate <= " + end;
            
       SalesforceResponse<SalesForceLead> response = salesforceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/query")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<SalesforceResponse<SalesForceLead>>() {})
                .block()
                .getBody();

        return response.records();
    }
}
