package com.ArchiDistribuee.VirtualCRM.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ArchiDistribuee.VirtualCRM.entity.InternalLead;

@SpringBootTest(classes = InternalCRMRepository.class)
public class InternalCRMRepositoryIntegrationTest {

    @Autowired
    private InternalCRMRepository repository;

    public InternalCRMRepositoryIntegrationTest() {
    }

    @Test
    void whenGetAllShouldReturnValidResult() {

        Set<InternalLead> leads = this.repository.getAllLeads();

        assertEquals(1000, leads.size());
    }

    @Test
    void whenGetLeadsShouldReturnValidResult() {

        Set<InternalLead> leads = this.repository.getLeads(15D, 55D, "Alsace");

        assertNotEquals(0, leads.size());
    }

    @Test
    void whenGetLeadsByDateShouldReturnValidResult() {

        Set<InternalLead> leads = this.repository.getLeadsByDate(ZonedDateTime.now().minusYears(5),
                ZonedDateTime.now());

        assertNotEquals(0, leads.size());
    }

}
