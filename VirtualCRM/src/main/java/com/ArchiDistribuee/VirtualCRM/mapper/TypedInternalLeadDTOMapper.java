package com.ArchiDistribuee.VirtualCRM.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.ArchiDistribuee.VirtualCRM.entity.InternalLeadDTO;

import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;

@Mapper
public interface TypedInternalLeadDTOMapper {

    InternalLeadDTO fromThriftInternalLeadDTO(ThriftInternalLeadDTO lead);

    Set<InternalLeadDTO> fromThriftInternalLeadDTO(Set<ThriftInternalLeadDTO> leads);

    ThriftInternalLeadDTO toThriftInternalLeadDTO(InternalLeadDTO lead);

    Set<ThriftInternalLeadDTO> toThriftInternalLeadDTO(Set<InternalLeadDTO> leads);

}
