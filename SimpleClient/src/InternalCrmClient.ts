import Thrift from 'thrift';

import { Client } from './gen-nodejs/LeadService';

export class InternalCrmClient {
  public readonly client: Client;

  constructor() {
    const conn = Thrift.createConnection('localhost', 9090, {
      transport: Thrift.TBufferedTransport,
      protocol: Thrift.TBinaryProtocol,
    });

    this.client = Thrift.createClient(Client, conn);
  }
}
