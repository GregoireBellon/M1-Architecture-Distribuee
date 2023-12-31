import { TBinaryProtocol, TBufferedTransport, createClient, createConnection } from 'thrift';

import { Client } from './gen-nodejs/LeadService';
import { ThriftInternalLeadDTO } from './gen-nodejs/LeadService_types';
import { Lead } from './lead';
import { toThriftInternalLeadDto } from './mapper/lead-mapper';

export class InternalCrmClient {
  private host: string = process.env.THRIFT_HOST ?? 'localhost';
  private port: number = parseInt(process.env.THRIFT_PORT ?? '9090');

  private async connectionWrapper<T>(operation: (client: Readonly<Client>) => T): Promise<T> {
    const conn = createConnection(this.host, this.port, {
      transport: TBufferedTransport,
      protocol: TBinaryProtocol,
    });

    const ret = await operation(createClient(Client, conn));

    conn.end();

    return ret;
  }

  getAllLeads(): Promise<ThriftInternalLeadDTO[]> {
    return this.connectionWrapper(client => client.getAllLeads()).then(promisedLeads => promisedLeads);
  }

  mergeLeads(leads: Lead[]): Promise<void> {
    const mappedLeads = leads.map(toThriftInternalLeadDto);

    return this.connectionWrapper(client => client.addLeads(mappedLeads));
  }
}
