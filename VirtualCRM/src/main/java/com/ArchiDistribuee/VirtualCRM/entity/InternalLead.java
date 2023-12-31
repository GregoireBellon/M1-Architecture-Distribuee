package com.ArchiDistribuee.VirtualCRM.entity;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class InternalLead {
    private String id;
    private String name;
    private double annualRevenue;
    private String phone;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private ZonedDateTime creationDate;
    private String company;
    private String state;

    public String getLastName() {
        String[] words = this.name.split(", ");
        if (words.length < 2) {
            log.error("Invalid name: " + this.name + "for Internal lead (id=" + this.id +
                    ")");
            return "";
        }
        return words[0];
    }

    public String getFirstName() {
        String[] words = this.name.split(", ");
        if (words.length < 2) {
            log.error("Invalid name: " + this.name + "for Internal lead (id=" + this.id +
                    ")");
            return "";
        }
        return words[1];
    }
}
