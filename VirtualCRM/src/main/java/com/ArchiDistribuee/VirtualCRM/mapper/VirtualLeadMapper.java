package com.ArchiDistribuee.VirtualCRM.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.ArchiDistribuee.VirtualCRM.dto.VirtualLeadDto;
import com.ArchiDistribuee.VirtualCRM.entity.InternalLead;
import com.ArchiDistribuee.VirtualCRM.entity.SalesForceLead;
import com.ArchiDistribuee.VirtualCRM.entity.VirtualLead;

@Mapper(uses = GeographicPointMapper.class)
public interface VirtualLeadMapper {
    VirtualLeadMapper INSTANCE = Mappers.getMapper(VirtualLeadMapper.class);

    @Mapping(source = "geographicPoint", target = "geographicPointDto")
    VirtualLeadDto toVirtualLeadDto(VirtualLead virtualLead);

    @Mapping(target = "firstName", source = "name", qualifiedByName = "extractFirstName")
    @Mapping(target = "lastName", source = "name", qualifiedByName = "extractLastName")
    @Mapping(target = "geographicPoint", ignore = true)
    VirtualLead fromInternalLead(InternalLead internalLead);
    
    @Mapping(target = "geographicPoint", ignore = true)
    @Mapping(source = "createdDate", target = "creationDate")
    VirtualLead fromSalesForceLead(SalesForceLead lead);

    Set<VirtualLead> fromSalesForceLead(Set<SalesForceLead> lead);

    @Named("extractFirstName")
    default String extractFirstName(String leadName){
        if (leadName == null) {
            return "";
        }

        return leadName.split(" ")[0];
    }

    @Named("extractLastName")
    default String extractLastName(String leadName){
        if (leadName == null) {
            return "";
        }

        var splitted = leadName.split(" ");

        if(splitted.length < 2){
            return null;
        }

        return splitted[1];
    }

}
