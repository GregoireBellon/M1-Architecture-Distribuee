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

        assertNotEquals(0, leads.size());
    }

    @Test
    void whenGetLeadsShouldReturnValidResult() {

        this.repository.addLead(new InternalLead("ID_TEST_1", "test", 40000, "", "", "", "", "", ZonedDateTime.now(), "", "Alsace"));

        Set<InternalLead> leads = this.repository.getLeads(15000D, 55000D, "Alsace");

        assertNotEquals(0, leads.size());
    }

    @Test
    void whenGetLeadsByDateShouldReturnValidResult() {
                this.repository.addLead(new InternalLead("ID_TEST_2", "test", 40000, "", "", "", "", "", ZonedDateTime.now().minusYears(2), "", "Alsace"));

        Set<InternalLead> leads = this.repository.getLeadsByDate(ZonedDateTime.now().minusYears(5),
                ZonedDateTime.now());

        assertNotEquals(0, leads.size());
    }

}
