package internalcrm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internalcrm.models.InternalLeadDto;

public class LeadServiceTest {

    private LeadService leadService;

    @BeforeEach
    private void initLeadService() {
        this.leadService = new LeadServiceImpl();
    }

    @Test
    public void whenGetLeadsResultShoudBeValid() {
        final var leads = leadService.getLeads(20D, 200D);

        leads.forEach(lead -> {
            if (lead.annualRevenue() < 20D || lead.annualRevenue() > 200D)
                fail(lead.id() + " revenu annuel : " + lead.annualRevenue() + " n'est pas dans l'intervalle [20,200]");
        });
    }

    @Test
    public void whenGetLeadsByDateResultShouldBeValid() {

        final var borneInf = LocalDate.now().minusYears(5);
        final var borneSup = LocalDate.now();

        final var leads = leadService.getLeadsByDate(borneInf, borneSup);

        leads.forEach(lead -> {
            if (lead.creationDate().isBefore(borneInf.atStartOfDay())
                    || lead.creationDate().isAfter(borneSup.atStartOfDay()))
                fail(lead.id() + " date de création : " + lead.creationDate() + " n'est pas dans l'intervalle [ "
                        + borneInf + "," + borneSup + "]");
        });
    }

    @Test
    public void whenDeleteLeadsShouldDelete() {
        final var allLeads = leadService.getAllLeads();
        final int initialSize = allLeads.size();

        leadService.deleteLead(allLeads.iterator().next());

        assertEquals(initialSize - 1, leadService.getAllLeads().size());
    }

    @Test
    public void whenAddLeadShouldAdd() {
        final var allLeads = leadService.getAllLeads();
        final int initialSize = allLeads.size();

        leadService.addLead(
                new InternalLeadDto("null", null, 0, null, null, null, null, null, LocalDateTime.now(), null, null));

        assertEquals(initialSize + 1, leadService.getAllLeads().size());

    }

}
