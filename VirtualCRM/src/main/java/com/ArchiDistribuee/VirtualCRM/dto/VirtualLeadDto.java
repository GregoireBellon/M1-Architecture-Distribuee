package com.ArchiDistribuee.VirtualCRM.dto;

import java.util.Calendar;

public record VirtualLeadDto(
        String firstName,
        String lastName,
        double annualRevenue,
        String phone,
        String street,
        String postalCode,
        String city,
        String country,
        Calendar creationDate,
        GeographicPointDto geographicPointDto,
        String company,
        String state) {
}