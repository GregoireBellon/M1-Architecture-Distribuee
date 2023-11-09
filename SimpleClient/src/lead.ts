export interface Lead {
    id: string,
    firstName: string,
    lastName: string,
    annualRevenue: number,
    phone: string,
    street: string,
    postalCode: string,
    city: string,
    country: string,
    creationDate: Date,
    // geographicalPoint : ;
    company: string,
    state: string
};