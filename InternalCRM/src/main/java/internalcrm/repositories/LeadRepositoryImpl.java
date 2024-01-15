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

import internalcrm.entity.Lead;

public class LeadRepositoryImpl implements LeadRepository {

    private static LeadRepositoryImpl instance = null;

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
        leadDTOs.add(new Lead(UUID.randomUUID().toString(), "Monsieur InternalCRM", 30000, "0666666666",
                "12 Rue de Sébastopol", "67000", "Strasbourg", "France",
                ZonedDateTime.of(2024, 1, 15, 12, 0, 0, 0, ZoneId.systemDefault()), "Facebook", "Alsace"));
    }

    private LeadRepositoryImpl() {
    }

    public static LeadRepositoryImpl getInstance() {

        if (instance != null)
            return instance;

        instance = new LeadRepositoryImpl();

        return instance;
    }

    @Override
    public Set<Lead> findLeadsBySalary(double minSalaire, double maxSalaire) {
        return LeadRepositoryImpl.leadDTOs.stream()
                .filter(lead -> lead.getAnnualRevenue() >= minSalaire)
                .filter(lead -> lead.getAnnualRevenue() <= maxSalaire)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Lead> findLeadsByCreationDate(ZonedDateTime minDate, ZonedDateTime maxDate) {
        return LeadRepositoryImpl.leadDTOs.stream()
                .filter(lead -> lead.getCreationDate() == null ? false : lead.getCreationDate().isBefore(maxDate))
                .filter(lead -> lead.getCreationDate() == null ? false : lead.getCreationDate().isAfter(minDate))
                .collect(Collectors.toSet());
    }

    @Override
    public void addLead(Lead internalLead) {
        LeadRepositoryImpl.leadDTOs.add(internalLead);
    }

    @Override
    public boolean deleteLead(String leadId) {
        return LeadRepositoryImpl.leadDTOs.removeIf(lead -> lead.getId().equals(leadId));
    }

    @Override
    public Set<Lead> findAll() {
        return LeadRepositoryImpl.leadDTOs;
    }

}
