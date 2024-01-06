import axios from 'axios';

import { SalesforceLead, SalesforceLeadResponse } from '../model/salesforce-lead';
import salesforceVariables from '../salesforce-var.json';

export class SalesforceClient {

  private bearerToken: string = '';
  private tenant: string = salesforceVariables.SF_TENANT;

  private constructor() {}

  public static async Build(): Promise<SalesforceClient> {
    if (
      salesforceVariables.SF_CLIENT_ID === undefined ||
      salesforceVariables.SF_CLIENT_SECRET === undefined ||
      salesforceVariables.SF_USERNAME === undefined ||
      salesforceVariables.SF_PASSWORD === undefined ||
      salesforceVariables.SF_TENANT === undefined
    ) {
      throw new Error('ENV ERROR: SALESFORCE VARIABLES MISSING');
    }
    const salesForceClient = new SalesforceClient();
    salesForceClient.tenant = salesforceVariables.SF_TENANT;

    const { data } = await axios.post<{ access_token: string }>(
      `https://${salesForceClient.tenant}.my.salesforce.com/services/oauth2/token`,
      {},
      {
        params: {
          grant_type: 'password',
          client_id: salesforceVariables.SF_CLIENT_ID,
          client_secret: salesforceVariables.SF_CLIENT_SECRET,
          username: salesforceVariables.SF_USERNAME,
          password: salesforceVariables.SF_PASSWORD,
        },
      },
    );

    salesForceClient.bearerToken = data.access_token;

    return salesForceClient;
  }

  public async getSalesforceLeads() : Promise<SalesforceLead[]> {
    const { data } = await axios.get<SalesforceLeadResponse>(
      `https://${this.tenant}.my.salesforce.com/services/data/v45.0/query/`,
      {
        params: {
          q: 'SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company, CreatedDate, PostalCode , Country FROM Lead ',
        },
        headers: {
          Authorization: `Bearer ${this.bearerToken}`,
        },
      },
    );
    return data.records;
  }
}
