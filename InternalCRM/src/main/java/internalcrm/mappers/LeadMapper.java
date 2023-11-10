package internalcrm.mappers;

import java.util.Set;

import org.mapstruct.Mapper;

import internalcrm.models.InternalLead;
import internalcrm.services.thrift.impl.InternalLeadDTO;;

@Mapper
public interface LeadMapper {

    InternalLeadDTO toInternalLeadDto(InternalLead lead);

    Set<InternalLeadDTO> toInternalLeadDto(Set<InternalLead> lead);

    InternalLead toInternalLead(InternalLeadDTO lead);

}
