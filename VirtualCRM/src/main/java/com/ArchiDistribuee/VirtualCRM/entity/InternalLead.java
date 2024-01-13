package com.ArchiDistribuee.VirtualCRM.entity;

import java.time.ZonedDateTime;

import com.ArchiDistribuee.VirtualCRM.mapper.VirtualLeadMapper;

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
public class InternalLead implements GenericLead{
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InternalLead l) {
            return l.getId() == this.getId();
        }
        return false;
    }

    @Override
    public VirtualLead toVirtualLead() {
        return VirtualLeadMapper.INSTANCE.fromInternalLead(this);
    }
}
