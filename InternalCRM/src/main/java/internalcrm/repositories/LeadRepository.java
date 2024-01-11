package internalcrm.repositories;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import internalcrm.models.Lead;

public class LeadRepository {

    private static LeadRepository instance = null;

    private static final Faker faker = new Faker(Locale.FRENCH);
    private static final Set<Lead> leadDTOs = new HashSet<>();

    static {
        for (int i = 0; i < 50; i++) {
            leadDTOs.add(
                    new Lead(UUID.randomUUID().toString(),
                            faker.name().lastName() + ", " + faker.name().firstName(),
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

    public Set<Lead> findLeadsBySalary(double minSalaire, double maxSalaire) {
        return LeadRepository.leadDTOs.stream()
                .filter(lead -> lead.getAnnualRevenue() >= minSalaire)
                .filter(lead -> lead.getAnnualRevenue() <= maxSalaire)
                .collect(Collectors.toSet());
    }

    public Set<Lead> findLeadsByCreationDate(ZonedDateTime minDate, ZonedDateTime maxDate) {
        return LeadRepository.leadDTOs.stream()
                .filter(lead -> lead.getCreationDate() == null ? false : lead.getCreationDate().isBefore(maxDate))
                .filter(lead -> lead.getCreationDate() == null ? false : lead.getCreationDate().isAfter(minDate))
                .collect(Collectors.toSet());
    }

    public void addLead(Lead internalLead) {
        LeadRepository.leadDTOs.add(internalLead);
    }

    public boolean deleteLead(String leadId) {
        return LeadRepository.leadDTOs.removeIf(lead -> lead.getId().equals(leadId));
    }

    public Set<Lead> findAll() {
        return LeadRepository.leadDTOs;
    }

}
