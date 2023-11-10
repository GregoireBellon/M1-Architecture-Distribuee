package internalcrm.models;

import java.time.LocalDateTime;

public record InternalLeadDto(
                String id,
                String name,
                double annualRevenue,
                String phone,
                String street,
                String postalCode,
                String city,
                String country,
                LocalDateTime creationDate,
                String company,
                String state) {
}
