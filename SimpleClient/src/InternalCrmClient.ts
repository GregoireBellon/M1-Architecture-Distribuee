import { TJSONProtocol, TXHRTransport } from 'thrift';

import { LeadServiceClient } from './gen-js/LeadService';

export class InternalCrmClient {

    private transport: TXHRTransport;
    private protocol: TJSONProtocol;
    private service: LeadServiceClient;

    constructor() {
        this.transport = new TXHRTransport('/hello');
        this.protocol = new TJSONProtocol(this.transport);
        this.service = new LeadServiceClient(this.protocol);
    }
}