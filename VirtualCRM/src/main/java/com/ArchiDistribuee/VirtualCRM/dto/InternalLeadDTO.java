package com.ArchiDistribuee.VirtualCRM.dto;

import java.time.ZonedDateTime;

public record InternalLeadDTO(
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
