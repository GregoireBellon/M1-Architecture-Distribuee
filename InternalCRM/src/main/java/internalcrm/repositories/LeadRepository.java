package internalcrm.repositories;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import internalcrm.models.InternalLead;

public class LeadRepository {

    private static LeadRepository instance = null;

    private static final Faker faker = new Faker(Locale.FRENCH);
    private static final Set<InternalLead> leadDTOs = new HashSet<>();

    static {
        for (int i = 0; i < 1000; i++) {
            leadDTOs.add(
                    new InternalLead("INTERN_" + i,
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.number().randomDouble(2, 25, 125),
                            faker.phoneNumber().cellPhone(),
                            faker.address().streetAddress(),
                            faker.address().zipCode(),
                            faker.address().cityName(),
                            faker.address().country(),
                            faker.date().past(30 * 365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()),
                            faker.company().name(),
                            faker.address().state()));
        }
    }

    private LeadRepository() {
    }

    public static LeadRepository getInstance() {

        if (instance != null)
            return instance;

        instance = new LeadRepository();

        return instance;
    }

    public Set<InternalLead> findLeadsBySalary(double minSalaire, double maxSalaire) {
        return LeadRepository.leadDTOs.stream()
                .filter(lead -> lead.getAnnualRevenue() >= minSalaire)
                .filter(lead -> lead.getAnnualRevenue() <= maxSalaire)
                .collect(Collectors.toSet());
    }

    public Set<InternalLead> findLeadsByCreationDate(ZonedDateTime minDate, ZonedDateTime maxDate) {
        return LeadRepository.leadDTOs.stream()
                .filter(lead -> lead.getCreationDate() == null ? false : lead.getCreationDate().isBefore(maxDate))
                .filter(lead -> lead.getCreationDate() == null ? false : lead.getCreationDate().isAfter(minDate))
                .collect(Collectors.toSet());
    }

    public void addLead(InternalLead internalLead) {
        LeadRepository.leadDTOs.add(internalLead);
    }

    public boolean deleteLead(String leadId) {
        return LeadRepository.leadDTOs.removeIf(lead -> lead.getId().equals(leadId));
    }

    public Set<InternalLead> findAll() {
        return LeadRepository.leadDTOs;
    }

}
