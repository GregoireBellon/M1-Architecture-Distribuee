import { TBinaryProtocol, TBufferedTransport, createClient, createConnection } from 'thrift';

import { Client } from './gen-nodejs/LeadService';
import { ThriftInternalLeadDTO } from './gen-nodejs/LeadService_types';
import { Lead } from './lead';
import { toThriftInternalLeadDto } from './mapper/lead-mapper';

export class InternalCrmClient {
  private async connectionWrapper<T>(operation: (client: Readonly<Client>) => T): Promise<T> {
    const conn = createConnection('localhost', 9090, {
      transport: TBufferedTransport,
      protocol: TBinaryProtocol,
    });

    const ret = await operation(createClient(Client, conn));

    conn.end();

    return ret;
  }

  getAllLeads(): Promise<Set<ThriftInternalLeadDTO>> {
    return this.connectionWrapper(client => client.getAllLeads())
      .then(promisedLeads => promisedLeads)
      .then(leads => new Set(leads));
  }

  mergeLeads(leads: Set<Lead>): Promise<void> {
    const mappedLeads = [...leads].map(toThriftInternalLeadDto);

    return this.connectionWrapper(client => client.addAllLeads(mappedLeads));
  }
}
