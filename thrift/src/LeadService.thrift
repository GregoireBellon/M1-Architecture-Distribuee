namespace java internalcrm.services.thrift.impl

typedef string Timestamp

struct ThriftInternalLeadDTO {
        1:string id,
        2:string firstName, 
        3:string lastName, 
        4:double annualRevenue, 
        5:string phone, 
        6:string street, 
        7:string postalCode, 
        8:string city, 
        9:string country, 
        10:Timestamp creationDate, 
        11:string company
        12:string state
}

service LeadService
{
        set<ThriftInternalLeadDTO> getLeads(1:double borneInfSalaire, 2:double borneSupSalaire),
        set<ThriftInternalLeadDTO> getLeadsByDate(1:string borneInfDate, 2:string borneSupDate),
        set<ThriftInternalLeadDTO> getAllLeads(),
        void addLead(1:ThriftInternalLeadDTO lead),
        void deleteLead(1:ThriftInternalLeadDTO lead),
}