package com.ArchiDistribuee.VirtualCRM.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.ArchiDistribuee.VirtualCRM.entity.TypedInternalLeadDTO;

import internalcrm.services.thrift.impl.InternalLeadDTO;

@Mapper
public interface TypedInternalLeadDTOMapper {

    TypedInternalLeadDTO fromInternalLeadDTO(InternalLeadDTO lead);

    Set<TypedInternalLeadDTO> fromInternalLeadDTO(Set<InternalLeadDTO> leads);

    InternalLeadDTO toInternalLeadDTO(TypedInternalLeadDTO lead);

    Set<InternalLeadDTO> toInternalLeadDTO(Set<TypedInternalLeadDTO> leads);

}
