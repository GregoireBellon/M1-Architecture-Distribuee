import { expect, test } from '@jest/globals';
import { DateTime } from 'luxon';

import { InternalCrmClient } from './internal-crm-client';
import { Lead } from './lead';

test('Should get data from InternalCRM', async () => {
  const internalCrmClient = new InternalCrmClient();
  const leads = await internalCrmClient.getAllLeads();
  expect(leads.length).not.toBe(0);
});

test('Should merge leads to InternalCRM Successfully', async () => {
  const internalCrmClient = new InternalCrmClient();

  const leadsToAdd: Lead[] = [];

  for (let i = 0; i < 10; i++) {
    leadsToAdd.push({
      // cet identifiant sert à rendree chaque objet du set différent du précédent, ils seront override par le back de toutes façons
      id: `${i}`,
      firstName: 'Jean',
      lastName: 'Pierre',
      annualRevenue: 23,
      city: 'Nantes',
      company: 'Jsp Inc.',
      country: 'France',
      creationDate: DateTime.now().toJSDate(),
      phone: '0000000000',
      postalCode: '44000',
      street: 'boulevard des 50 otages',
      state: 'Alsace',
      geographicalPoint: {
        latitude: 0,
        longitude: 0,
      },
    });
  }
  const initialDbSize = (await internalCrmClient.getAllLeads()).length;

  await internalCrmClient.mergeLeads(leadsToAdd);

  const updatedLeads = await internalCrmClient.getAllLeads();

  expect(updatedLeads.length).toEqual(initialDbSize + leadsToAdd.length);
});
