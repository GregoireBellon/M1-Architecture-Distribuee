package com.ArchiDistribuee.VirtualCRM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ArchiDistribuee.VirtualCRM.dto.VirtualLeadDto;
import com.ArchiDistribuee.VirtualCRM.entity.VirtualLead;

@Mapper(uses = GeographicPointMapper.class)
public interface VirtualLeadMapper {
    VirtualLeadMapper INSTANCE = Mappers.getMapper(VirtualLeadMapper.class);

    @Mapping(source = "geographicPoint", target = "geographicPointDto")
    VirtualLeadDto virtualLeadToVirtualLeadDto(VirtualLead virtualLead);

}
