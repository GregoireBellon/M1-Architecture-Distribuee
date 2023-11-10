package internalcrm.repositories;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import internalcrm.models.InternalLeadDto;

public class LeadRepository {

    private static LeadRepository instance = null;

    private static final Faker faker = new Faker();
    private static final Set<InternalLeadDto> leadDTOs = new HashSet<>();

    static {
        for (int i = 0; i < 1000; i++) {
            leadDTOs.add(
                    new InternalLeadDto("INTERN_" + i,
                            faker.name().fullName(),
                            faker.number().randomDouble(2, 25, 125),
                            faker.phoneNumber().cellPhone(),
                            faker.address().streetAddress(),
                            faker.address().zipCode(),
                            faker.address().cityName(),
                            faker.address().country(),
                            faker.date().past(30 * 365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
                                    .toLocalDateTime(),
                            faker.company().name(),
                            faker.address().state()));
        }
    }

    private LeadRepository() {
    }

    public static LeadRepository getInstance() {
        if (instance != null)
            return instance;

        return new LeadRepository();
    }

    public Set<InternalLeadDto> findLeadsBySalary(double minSalaire, double maxSalaire) {
        return LeadRepository.leadDTOs.stream()
                .filter(Lead -> Lead.annualRevenue() >= minSalaire)
                .filter(Lead -> Lead.annualRevenue() <= maxSalaire)
                .collect(Collectors.toSet());
    }

    public Set<InternalLeadDto> findLeadsByCreationDate(LocalDateTime minDate, LocalDateTime maxDate) {
        return LeadRepository.leadDTOs.stream()
                .filter(Lead -> Lead.creationDate().isBefore(maxDate))
                .filter(Lead -> Lead.creationDate().isAfter(minDate))
                .collect(Collectors.toSet());
    }

    public void addLead(InternalLeadDto internalLead) {
        LeadRepository.leadDTOs.add(internalLead);
    }

    public boolean deleteLead(String leadId) {
        return LeadRepository.leadDTOs.removeIf(lead -> lead.id().equals(leadId));
    }

    public Set<InternalLeadDto> findAll() {
        return LeadRepository.leadDTOs;
    }

}
