import axios from 'axios';
import { Command } from 'commander';
import figlet from 'figlet';
import { DateTime } from 'luxon';

import { Lead } from './lead';

const httpClient = axios.create({
  baseURL: process.env.API_BASE_URL ?? 'http://localhost:8080',
});

const logger = {
  debug: (msg: string): void => {
    if (process.env.DEBUG === '1') {
      console.log(`[debug] ${msg}`);
    }
  },
};

function parseDate(inputDate: string): DateTime {
  const date = DateTime.fromFormat(inputDate, 'dd/MM/yyyy');
  if (!date.isValid) {
    throw new Error('La date doit être au format dd/MM/yyyy');
  }
  return date;
}

function parseSalary(salary: string): number {
  try {
    const result = parseFloat(salary);
    if (Number.isNaN(result)) {
      throw new Error();
    }
    return result;
  } catch (e) {
    throw new Error(`Salaire ${salary} invalide`);
  }
}

async function inputLeadsByDate(startDate: DateTime, endDate: DateTime): Promise<Set<Lead>> {
  try {
    const { data } = await httpClient.get<Lead[]>('/leads/byDate', {
      params: { startDate: startDate.toISO(), endDate: endDate.toISO() },
    });
    return new Set(data);
  } catch (e) {
    logger.debug((e as Error).message);
    throw new Error('Impossible de récupérer les données');
  }
}

async function inputLeadsBySalary(minSalary: number, maxSalary: number, state: string): Promise<Set<Lead>> {
  try {
    const { data } = await httpClient.get<Lead[]>('/leads', {
      params: { lowAnnualRevenue: minSalary, highAnnualRevenue: maxSalary, state },
    });
    return new Set(data);
  } catch (e) {
    logger.debug((e as Error).message);
    throw new Error('Impossible de récupérer les données');
  }
}

function display(leads: Set<Lead>) {
  console.log(`${leads.size} leads :`);
  console.table([...leads]);
}

const program = new Command();

program
  .command('date')
  .description('Récupérer les leads en fonction de leur date d\'ajout au format "dd/mm/yyyy"')
  .argument('<borne_inf/?>', 'la borne inférieure (? si indéfinie)')
  .argument('<borne_sup/?>', 'la borne supérieure (? si indéfinie)')
  .action(async (borne_inf: string, borne_sup: string) => {
    const startDate = parseDate(borne_inf);
    const endDate = parseDate(borne_sup);

    if (endDate < startDate) {
      throw new Error('La borne inférieure est plus élevée que la borne supérieure');
    }

    console.log(`Appel des leads par date entre ${borne_inf} et ${borne_sup}`);

    const leads = await inputLeadsByDate(startDate, endDate);

    display(leads);
  });

program
  .command('salary')
  .description('Récupérer les leads en fonction de salaire')
  .argument('<borne_inf/?>', 'la borne inférieure (? si indéfinie)')
  .argument('<borne_sup/?>', 'la borne supérieure (? si indéfinie)')
  .argument('<etat/?>')
  .action(async (borne_inf: string, borne_sup: string, etat: string) => {
    const minSalary = parseSalary(borne_inf);
    const maxSalary = parseSalary(borne_sup);
    const state = etat;

    if (minSalary > maxSalary) {
      throw new Error('La borne supérieur est plus élevée que la borne inférieure');
    }

    console.log(`Appel des leads par salaire, entre ${borne_inf} et ${borne_sup}`);

    const leads = await inputLeadsBySalary(minSalary, maxSalary, state);

    display(leads);
  });

program.description("Un CLI pour appeler l'api VirtualCRM");

async function main() {
  try {
    console.log(figlet.textSync('CRM Commander'));
    await program.parseAsync();
  } catch (error) {
    console.error('[ERREUR :', (error as Error).message, ']');
  }
}

void main();
