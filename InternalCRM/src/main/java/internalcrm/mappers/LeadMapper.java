package internalcrm.mappers;

import java.util.Set;

import org.mapstruct.Mapper;

import internalcrm.entity.InternalLead;
import internalcrm.services.thrift.impl.ThriftInternalLeadDTO;;

@Mapper
public interface LeadMapper {

    ThriftInternalLeadDTO toInternalLeadDto(InternalLead lead);

    Set<ThriftInternalLeadDTO> toInternalLeadDto(Set<InternalLead> lead);

    InternalLead toInternalLead(ThriftInternalLeadDTO lead);

    Set<InternalLead> toInternalLead(Set<ThriftInternalLeadDTO> lead);

}
