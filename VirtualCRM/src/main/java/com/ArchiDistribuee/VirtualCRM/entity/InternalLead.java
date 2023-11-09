package com.ArchiDistribuee.VirtualCRM.entity;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InternalLead {
    private String firstName;
    private String lastName;
    private double annualRevenue;
    private String phone;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private Calendar creationDate;
    private String company;
    private String state;
}
