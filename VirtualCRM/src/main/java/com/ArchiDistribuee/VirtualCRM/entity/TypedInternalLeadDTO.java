package com.ArchiDistribuee.VirtualCRM.entity;

import java.time.ZonedDateTime;

public record TypedInternalLeadDTO(
        String id,
        String firstName,
        String lastName,
        double annualRevenue,
        String phone,
        String street,
        String postalCode,
        String city,
        String country,
        ZonedDateTime creationDate,
        String company,
        String state) {
}
