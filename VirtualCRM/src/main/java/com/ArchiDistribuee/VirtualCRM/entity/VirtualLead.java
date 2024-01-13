package com.ArchiDistribuee.VirtualCRM.entity;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VirtualLead implements GenericLead {
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

    @Override
    public VirtualLead toVirtualLead() {
        return this;
    }

}