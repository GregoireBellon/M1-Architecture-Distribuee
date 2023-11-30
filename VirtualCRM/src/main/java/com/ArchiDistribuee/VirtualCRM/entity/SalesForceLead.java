package com.ArchiDistribuee.VirtualCRM.entity;

import java.time.ZonedDateTime;
import java.util.Calendar;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
        private  ZonedDateTime createdDate;
        private String company;
        private String state;

}
