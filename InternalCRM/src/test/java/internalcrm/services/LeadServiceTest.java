package internalcrm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.ZonedDateTime;

import org.apache.thrift.TException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;
import internalcrm.services.thrift.impl.LeadService;

public class LeadServiceTest {

    private LeadService.Iface leadService;

    @BeforeEach
    private void initLeadService() {
        this.leadService = LeadServiceImpl.getInstance();
    }

    @Test
    public void whenGetLeadsResultShoudBeValid() throws TException {
        final var leads = leadService.getLeads(20D, 200D);

        leads.forEach(lead -> {
            if (lead.getAnnualRevenue() < 20D || lead.getAnnualRevenue() > 200D)
                fail(lead.getId() + " revenu annuel : " + lead.getAnnualRevenue()
                        + " n'est pas dans l'intervalle [20,200]");
        });
    }

    @Test
    public void whenGetLeadsByDateResultShouldBeValid() throws TException {

        final var borneInf = ZonedDateTime.now().minusYears(5);
        final var borneSup = ZonedDateTime.now();

        final var leads = leadService.getLeadsByDate(borneInf.toString(), borneSup.toString());

        leads.forEach(lead -> {

            final ZonedDateTime dateLead = ZonedDateTime.parse(lead.getCreationDate());

            if (dateLead.isBefore(borneInf)
                    || dateLead.isAfter(borneSup))
                fail(lead.getId() + " date de création : " + lead.getCreationDate() + " n'est pas dans l'intervalle [ "
                        + borneInf + "," + borneSup + "]");
        });
    }

    @Test
    public void whenDeleteLeadsShouldDelete() throws TException {
        final var allLeads = leadService.getAllLeads();
        final int initialSize = allLeads.size();

        leadService.deleteLead(allLeads.iterator().next());

        assertEquals(initialSize - 1, leadService.getAllLeads().size());
    }

    @Test
    public void whenAddLeadShouldAdd() throws TException {
        final var allLeads = leadService.getAllLeads();
        final int initialSize = allLeads.size();

        leadService.addLead(
                new ThriftInternalLeadDTO("null", null, null, 0, null, null, null, null, null,
                        ZonedDateTime.now().toString(),
                        null,
                        null));

        assertEquals(initialSize + 1, leadService.getAllLeads().size());

    }

}
