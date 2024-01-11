package internalcrm.mappers;

import java.util.Set;

import org.mapstruct.Mapper;

import internalcrm.entity.Lead;
import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;;

@Mapper
public interface LeadMapper {

    ThriftInternalLeadDTO toInternalLeadDto(Lead lead);

    Set<ThriftInternalLeadDTO> toInternalLeadDto(Set<Lead> lead);

    Lead toInternalLead(ThriftInternalLeadDTO lead);

    Set<Lead> toInternalLead(Set<ThriftInternalLeadDTO> lead);

}
