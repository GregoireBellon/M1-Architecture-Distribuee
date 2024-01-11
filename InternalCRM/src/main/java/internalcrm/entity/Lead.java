package internalcrm.entity;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
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
        if(obj == this){
            return true;
        }
        if(obj instanceof Lead i){
            return i.getId() == this.getId();
        }
        return false;
    }
}
