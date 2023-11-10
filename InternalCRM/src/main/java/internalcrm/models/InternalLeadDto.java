package internalcrm.models;

public record InternalLeadDto(
        String id,
        String name,
        double annualRevenue,
        String phone,
        String street,
        String postalCode,
        String city,
        String country,
        String creationDate,
        String company,
        String state) {
}
