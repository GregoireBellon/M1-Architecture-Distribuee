package com.ArchiDistribuee.VirtualCRM.mapper;

import java.util.Set;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ArchiDistribuee.VirtualCRM.entity.InternalLead;

import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;

@Mapper
public interface InternalLeadMapper {

    InternalLead fromThriftInternalLeadDTO(ThriftInternalLeadDTO thriftInternalLeadDTO);

    Set<InternalLead> fromThriftInternalLeadDTO(Set<ThriftInternalLeadDTO> thriftInternalLeadDTOs);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ThriftInternalLeadDTO toThriftInternalLeadDTO(InternalLead internalLead);

    Set<ThriftInternalLeadDTO> toThriftInternalLeadDTO(Set<InternalLead> internalLeads);

}
