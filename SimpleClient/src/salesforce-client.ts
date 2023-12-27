import axios from 'axios';

import { SalesforceLead } from './salesforce-lead';

export class SalesforceClient {
  private bearerToken: string = '';

  private constructor() {}

  public static async Build(): Promise<SalesforceClient> {
    if (
      process.env.SF_CLIENT_ID === undefined ||
      process.env.SF_CLIENT_SECRET === undefined ||
      process.env.SF_USERNAME === undefined ||
      process.env.SF_PASSWORD === undefined
    ) {
      throw new Error('ENV ERROR: SALESFORCE VARIABLES MISSING');
    }
    const salesForceClient = new SalesforceClient();

    const { data } = await axios.post<{ access_token: string }>(
      'https://login.salesforce.com/services/oauth2/token',
      {},
      {
        params: {
          grant_type: 'password',
          client_id: process.env.SF_CLIENT_ID,
          client_secret: process.env.SF_CLIENT_SECRET,
          username: process.env.SF_USERNAME,
          password: process.env.SF_PASSWORD,
        },
      },
    );
    salesForceClient.bearerToken = data.access_token;

    return salesForceClient;
  }

  public async getSalesforceLeads() {
    const { data } = await axios.get<SalesforceLead[]>(
      'https://universit3-dev-ed.develop.my.my.salesforce.com/services/data/v45.0/query/',
      {
        params: {
          q: 'SELECT FirstName, LastName, AnnualRevenue, Phone, Street, City, State, Company, CreatedDate, PostalCode , Country FROM Lead ',
        },
        headers: {
          Authorization: `Bearer ${this.bearerToken}`,
        },
      },
    );
    return data;
  }
}
