package com.ArchiDistribuee.VirtualCRM.entity;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SalesForceLead {
        private String firstName;
        private String lastName;
        private Double annualRevenue;
        private String phone;
        private String street;
        private String city;
        private String postalCode;
        private String country;
        private Calendar creationDate;
        private String company;
        private String state;

}
