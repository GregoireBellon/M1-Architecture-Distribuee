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
    public static VirtualLead fromSalesForceLead(SalesForceLead salesForceLead) {
        Double annualRevenue = salesForceLead.getAnnualRevenue() != null ? salesForceLead.getAnnualRevenue() : 0.0;;

        return new VirtualLead(salesForceLead.getFirstName(),
                salesForceLead.getLastName(),
                annualRevenue,
                salesForceLead.getPhone(),
                salesForceLead.getStreet(),
                salesForceLead.getPostalCode(),
                salesForceLead.getCity(),
                salesForceLead.getCountry(),
                salesForceLead.getCreationDate(),
                null,
                salesForceLead.getCompany(),
                salesForceLead.getState()
                );    
            }
    // TODO
    // public static VirtualLead fromSalesForceLead(SalesForceLead salesForceLead) {
    //     return new VirtualLead(salesForceLead.getFirstName(),
    //             salesForceLead.getLastName(),
    //             salesForceLead.getAnnualRevenue(),
    //             salesForceLead.getPhone(),
    //             salesForceLead.getStreet(),
    //             salesForceLead.getPostalCode(),
    //             salesForceLead.getCity(),
    //             salesForceLead.getCountry(),
    //             salesForceLead.getCreationDate(),
    //             null,
    //             salesForceLead.getCompany(),
    //             salesForceLead.getState()
    //             );    
    //         }

}