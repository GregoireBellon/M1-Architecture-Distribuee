package com.ArchiDistribuee.VirtualCRM.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ArchiDistribuee.VirtualCRM.entity.TypedInternalLeadDTO;

@SpringBootTest(classes = InternalCRMRepository.class)
public class InternalCRMRepositoryIntegrationTest {

    @Autowired
    private InternalCRMRepository repository;

    public InternalCRMRepositoryIntegrationTest() {
    }

    @Test
    void whenGetAllShouldReturnValidResult() {

        Set<TypedInternalLeadDTO> leads = this.repository.getAllLeads();

        System.out.println("LEADS RECUPÉRÉS : " + leads.toString());

        assertEquals(1000, leads.size());
    }

}
