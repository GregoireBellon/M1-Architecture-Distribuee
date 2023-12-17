import { ThriftInternalLeadDTO } from '../gen-nodejs/LeadService_types';
import { Lead } from '../lead';

export function toThriftInternalLeadDto(lead: Lead): ThriftInternalLeadDTO {
  return {
    id: lead.id,
    name: `${lead.firstName} ${lead.lastName}`,
    company: lead.company,
    annualRevenue: lead.annualRevenue,
    country: lead.country,
    state: lead.state,
    postalCode: lead.postalCode,
    city: lead.city,
    street: lead.street,
    phone: lead.phone,
    creationDate: lead.creationDate.toISOString(),
  };
}
