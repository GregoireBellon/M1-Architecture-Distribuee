export interface SalesforceLeadResponse {
  totalSize: number;
  done: boolean;
  records: SalesforceLead[];
}

export interface SalesforceLead {
  FirstName: string;
  LastName: string;
  AnnualRevenue: number;
  Phone: string;
  Street: string;
  City: string;
  State: string;
  Company: string;
  CreatedDate: string;
  PostalCode: string;
  Country: string;
}
