import { DateTime } from 'luxon';
import { ThriftInternalLeadDTO } from '../gen-nodejs/LeadService_types';
import { Lead } from '../model/lead';
import { SalesforceLead } from '../model/salesforce-lead';

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

export function fromThriftInternalLeadDto(thriftLead: ThriftInternalLeadDTO): Lead {
  return {
    id: thriftLead.id,
    firstName: thriftLead.name.split(" ")[0],
    lastName: thriftLead.name.split(" ").slice(1).join(" "),
    company: thriftLead.company,
    annualRevenue: thriftLead.annualRevenue,
    country: thriftLead.country,
    state: thriftLead.state,
    postalCode: thriftLead.postalCode,
    city: thriftLead.city,
    street: thriftLead.state,
    phone: thriftLead.phone,
    creationDate: new Date(thriftLead.creationDate),
    geographicalPoint: null,
  };
}


export function fromSalesforceLead(salesforceLead: SalesforceLead): Lead {
  return {
    id: '',
    firstName: salesforceLead.FirstName,
    lastName: salesforceLead.LastName,
    company: salesforceLead.Company,
    annualRevenue: salesforceLead.AnnualRevenue,
    country: salesforceLead.Country,
    state: salesforceLead.State,
    postalCode: salesforceLead.PostalCode,
    city: salesforceLead.City,
    street: salesforceLead.Street,
    phone: salesforceLead.Phone,
    creationDate: new Date(salesforceLead.CreatedDate),
    geographicalPoint: null,
  };
}


