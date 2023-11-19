package com.ArchiDistribuee.VirtualCRM.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Optional;

import com.ArchiDistribuee.VirtualCRM.CustomDeserializer.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


 public record SalesForceLead(
     @JsonProperty("FirstName")
     String firstName,
     @JsonProperty("LastName")
     String lastName,
     @JsonProperty("AnnualRevenue") 
     Double annualRevenue,
     @JsonProperty("Phone")
     String phone,
     @JsonProperty("Street") 
     String street,
     @JsonProperty("City")
     String city,
     @JsonProperty("PostalCode") 
     String postalCode,
     @JsonProperty("Country") 
     String country,
     @JsonProperty("CreatedDate") 
     Calendar creationDate,
     @JsonProperty("Company")
     String company,
     @JsonProperty("State")
     String state) {

    }


// @NoArgsConstructor
// @AllArgsConstructor
// @Setter
// @Getter
// @ToString
// @JsonIgnoreProperties(ignoreUnknown = true) 
// public class SalesForceLead {
    
//     @JsonProperty("FirstName")
//     private String firstName;
//     @JsonProperty("LastName") 
//     private String lastName;
//     @JsonProperty("AnnualRevenue") 
//     private Double annualRevenue;
//     @JsonProperty("Phone")
//     private String phone;
//     @JsonProperty("Street") 
//     private String street;
//     @JsonProperty("City") 
//     private String city;
//     @JsonProperty("PostalCode") 
//     private String postalCode;
//     @JsonProperty("Country") 
//     private String country;
//     @JsonProperty("CreatedDate") 
//     @JsonDeserialize(using = DateDeserializer.class)
//     private Calendar creationDate;
//     @JsonProperty("Company") 
//     private String company;
//     @JsonProperty("State") 
//     private String state;

//     public Double getAnnualRevenue() {
//         return annualRevenue != null ? annualRevenue : 0.0;
//     }
// }
