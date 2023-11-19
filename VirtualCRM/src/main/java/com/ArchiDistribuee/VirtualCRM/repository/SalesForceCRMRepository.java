package com.ArchiDistribuee.VirtualCRM.repository;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.openmbean.CompositeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;
import com.ArchiDistribuee.VirtualCRM.entity.SalesforceResponse;
import com.ArchiDistribuee.VirtualCRM.service.VirtualLeadService;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

<<<<<<< HEAD
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;
import org.slf4j.Logger;
import java.util.HashSet;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

@Setter
=======
>>>>>>> a79b2ab (fix : debug de SalesForceCRMRepositoryTest)
@Repository
public class SalesForceCRMRepository {

    @Autowired
    private WebClient salesforceWebClient;
    private static final Logger logger = LoggerFactory.getLogger(VirtualLeadService.class);

    public Set<SalesForceLead> getLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        int _lowAnnualRevenue = (int) lowAnnualRevenue;
        int _highAnnualRevenue = (int) highAnnualRevenue;
        String query = "SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company ,CreatedDate , PostalCode  FROM Lead"
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

<<<<<<< HEAD
        // TODO
        public Set<SalesForceLead> getLeadsByDate(Calendar startDate, Calendar endDate) {
                            String query = "SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company ,CreatedDate , PostalCode  FROM Lead "+
                             "WHERE CreatedDate  > " +  "2023-12-31T00:00:00Z" + 
                " AND CreatedDate < " + "2022-12-31T00:00:00Z";  
 
                Mono<String> responseMono = salesforceWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/query")
                    .queryParam("q", query)
                    .build())
                    .retrieve()
                    .bodyToMono(String.class);
                    responseMono.subscribe(response -> {
                        System.out.println("Response: " + response);
                    });
                String response = responseMono.block(); 
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    SalesforceResponse salesforceResponse = objectMapper.readValue(response, SalesforceResponse.class);
                    return salesforceResponse.getLeads(); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
                return new HashSet<>();
            }
    }
=======
        return response.records();

    }
    // public Set<SalesForceLead> getLeads(double lowAnnualRevenue, double
    // highAnnualRevenue, String state) {
    // int _lowAnnualRevenue = (int)lowAnnualRevenue ;
    // int _highAnnualRevenue = (int) highAnnualRevenue ;
    // String query = "SELECT FirstName, LastName, AnnualRevenue, Phone, Street,
    // City, State, Company ,CreatedDate , PostalCode FROM Lead" +
    // " WHERE AnnualRevenue > " + _lowAnnualRevenue +
    // " AND AnnualRevenue < " + _highAnnualRevenue;

    // Mono<String> responseMono = salesforceWebClient.get()
    // .uri(uriBuilder -> uriBuilder.path("/query")
    // .queryParam("q", query)
    // .build())
    // .retrieve()
    // .bodyToMono(String.class);

    // String response = responseMono.block();
    // ObjectMapper objectMapper = new ObjectMapper();
    // try {
    // SalesforceResponse salesforceResponse = objectMapper.readValue(response,
    // SalesforceResponse.class);
    // return salesforceResponse.getLeads();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // return new HashSet<>();
    // }

    // TODO
    public Set<SalesForceLead> getLeadsByDate(Calendar startDate, Calendar endDate) {
        String query = "SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company ,CreatedDate , PostalCode  FROM Lead "
                +
                "WHERE CreatedDate  > " + "2023-12-31T00:00:00Z" +
                " AND CreatedDate < " + "2022-12-31T00:00:00Z";

        Mono<String> responseMono = salesforceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/query")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        responseMono.subscribe(response -> {
            System.out.println("Response: " + response);
        });
        String response = responseMono.block();
        ObjectMapper objectMapper = new ObjectMapper();
        // try {
        //     SalesforceResponse salesforceResponse = objectMapper.readValue(response, SalesforceResponse.class);
        //     return salesforceResponse.leads();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        return new HashSet<>();
    }
}
>>>>>>> a79b2ab (fix : debug de SalesForceCRMRepositoryTest)
