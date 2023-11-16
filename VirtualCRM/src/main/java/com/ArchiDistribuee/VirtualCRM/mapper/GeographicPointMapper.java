package com.ArchiDistribuee.VirtualCRM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ArchiDistribuee.VirtualCRM.dto.GeographicPointDto;
import com.ArchiDistribuee.VirtualCRM.entity.GeographicPoint;

@Mapper
public interface GeographicPointMapper {
    GeographicPointMapper INSTANCE = Mappers.getMapper(GeographicPointMapper.class);

    // TODO (Après avoir update GeographicPoint)
    GeographicPointDto toGeographicPointDto(GeographicPoint geographicPoint);

}
