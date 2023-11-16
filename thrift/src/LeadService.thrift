namespace java internalcrm.services.thrift.impl

typedef string Timestamp

struct ThriftInternalLeadDTO {
        1:string id,
        2:string name,
        3:double annualRevenue, 
        4:string phone, 
        5:string street, 
        6:string postalCode, 
        7:string city, 
        8:string country, 
        9:Timestamp creationDate, 
        10:string company
        11:string state
}

service LeadService
{
        set<ThriftInternalLeadDTO> getLeads(1:double borneInfSalaire, 2:double borneSupSalaire),
        set<ThriftInternalLeadDTO> getLeadsByDate(1:string borneInfDate, 2:string borneSupDate),
        set<ThriftInternalLeadDTO> getAllLeads(),
        void addLead(1:ThriftInternalLeadDTO lead),
        void deleteLead(1:ThriftInternalLeadDTO lead),
}