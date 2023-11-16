package com.ArchiDistribuee.VirtualCRM.entity;

import java.time.ZonedDateTime;

import com.ArchiDistribuee.VirtualCRM.dto.InternalLeadDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VirtualLead {
    private String firstName;
    private String lastName;
    private double annualRevenue;
    private String phone;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private ZonedDateTime creationDate;
    private GeographicPoint geographicPoint;
    private String company;
    private String state;

    public static VirtualLead fromTypedInternalLeadDTO(InternalLeadDTO internalLead) {
        return new VirtualLead(
                internalLead.firstName(),
                internalLead.lastName(),
                internalLead.annualRevenue(),
                internalLead.phone(),
                internalLead.street(),
                internalLead.postalCode(),
                internalLead.city(),
                internalLead.country(),
                internalLead.creationDate(),
                null,
                internalLead.company(),
                internalLead.state());
    }

    // TODO
    public static VirtualLead fromSalesForceLead(SalesForceLead salesForceLead) {
        return new VirtualLead(null, null, 0, null, null, null, null, null, null, null, null, null);
    }

}