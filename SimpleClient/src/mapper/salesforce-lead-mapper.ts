import { Lead } from '../lead';
import { SalesforceLead } from '../salesforce-lead';

export function salesforceLeadtoLead(salesforceLead: SalesforceLead): Lead {
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
