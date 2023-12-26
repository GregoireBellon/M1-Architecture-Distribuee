package com.ArchiDistribuee.VirtualCRM.repository;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;

@SpringBootTest
public class SalesForceCRMRepositoryIntegrationTest {

    @Autowired
    private SalesForceCRMRepository salesForceCRMRepository;

    @Test
    void whenGetSalesForceLeadShouldReturnValid() {

        double lowAnnualRevenue = 0;
        double highAnnualRevenue = 1000000000;
        String state = "FL";

        Set<SalesForceLead> result = salesForceCRMRepository.getLeads(lowAnnualRevenue, highAnnualRevenue, state);
        
        assertFalse(result.isEmpty());

        SalesForceLead firstLead = result.iterator().next();

        System.out.println("Lead : " + firstLead);

        assertNotNull(firstLead.getFirstName());
        assertNotNull(firstLead.getLastName());
        assertNotNull(firstLead.getAnnualRevenue());

    }

    @Test
    void whenGetSalesForceLeadByDateShouldReturnValid() {

    ZoneId _zoneId = ZoneId.of("UTC");
    ZonedDateTime start = ZonedDateTime.now(_zoneId).minusYears(10);
    ZonedDateTime end = ZonedDateTime.now(_zoneId);

    Set<SalesForceLead> result = salesForceCRMRepository.getLeadsByDate(start, end);
    
    assertFalse(result.isEmpty());

        SalesForceLead firstLead = result.iterator().next();

        System.out.println("Lead : " + firstLead);

        assertNotNull(firstLead.getFirstName());
        assertNotNull(firstLead.getLastName());
        assertNotNull(firstLead.getCreatedDate());


    }

}