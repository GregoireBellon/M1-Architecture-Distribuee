package com.ArchiDistribuee.VirtualCRM.entity;

import java.util.Calendar;

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
    private Calendar creationDate;
    private GeographicPoint geographicPoint;
    private String company;
    private String state;

    public static VirtualLead fromInternalLead(InternalLead internalLead) {
        return new VirtualLead(internalLead.getFirstName(),
                internalLead.getLastName(),
                internalLead.getAnnualRevenue(),
                internalLead.getPhone(),
                internalLead.getStreet(),
                internalLead.getPostalCode(),
                internalLead.getCity(),
                internalLead.getCountry(),
                internalLead.getCreationDate(),
                null,
                internalLead.getCompany(),
                internalLead.getState());
    }

    // TODO
    public static VirtualLead fromSalesForceLead(SalesForceLead salesForceLead) {
        return new VirtualLead(null, null, 0, null, null, null, null, null, null, null, null, null);
    }

}