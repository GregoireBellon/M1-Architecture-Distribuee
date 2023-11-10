/*
 * Copyright 2023 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

namespace java internalcrm.services.thrift.impl

typedef string Timestamp

struct InternalLeadDTO {
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
        set<InternalLeadDTO> getLeads(1:double borneInfSalaire, 2:double borneSupSalaire),
        set<InternalLeadDTO> getLeadsByDate(1:string borneInfDate, 2:string borneSupDate),
        set<InternalLeadDTO> getAllLeads(),
        void addLead(1:InternalLeadDTO lead),
        void deleteLead(1:InternalLeadDTO lead),
}