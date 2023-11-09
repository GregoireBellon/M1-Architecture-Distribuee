import { Lead } from "./lead";

const figlet = require("figlet");
const { Command } = require('commander');


function parseDate(date_in: string): Date {

    if (date_in.split.length != 3) {
        throw new Error("Date mal formatée (rappel : dd/mm/yyyy)");
    }

    const [day_str, month_str, year_str] = date_in.split('/');

    const day = parseInt(day_str), month = parseInt(month_str), year = parseInt(year_str);

    if (month > 12 || month < 1) {
        throw new Error("Date mal formatée (rappel : dd/mm/yyyy)");
    }

    return new Date(year, month - 1, day);
}

function inputLeadsByDate(borne_inf: Date, borne_sup: Date): Array<Lead> {
    console.log(`Appel des leads par date entre ${borne_inf} et ${borne_sup}`);
    return [];
}

function inputLeadsBySalary(borne_inf: number, borne_sup: number): Array<Lead> {
    console.log(`Appel des leads par salaire, entre ${borne_inf} et ${borne_sup}`);
    return [];
}

function display(leads: Array<any>) {
    console.log(`${leads.length} leads :`);
    console.table(leads);
}


console.log(figlet.textSync("CRM Commander"));

const program = new Command();

program.command('date')
    .description('Récupérer les leads en fonction de leur date d\'ajout au format "dd/mm/yyyy"')
    .argument('<borne_inf/?>', 'la borne inférieure (? si indéfinie)')
    .argument('<borne_sup/?>', 'la borne supérieure (? si indéfinie)')
    .action((borne_inf: string, borne_sup: string) => {

        const date_inf = parseDate(borne_inf), date_sup = parseDate(borne_sup);

        if (date_inf > date_sup) {
            throw new Error("La borne inférieure est plus élevée que la borne supérieure");
        }

        const leads = inputLeadsByDate(date_inf, date_sup);

        display(leads);
    });

program.command('salary')
    .description('Récupérer les leads en fonction de salaire')
    .argument('<borne_inf/?>', 'la borne inférieure (? si indéfinie)')
    .argument('<borne_sup/?>', 'la borne supérieure (? si indéfinie)')
    .action((borne_inf: string, borne_sup: string) => {

        const salaire_inf = parseFloat(borne_inf), salaire_sup = parseFloat(borne_sup)

        if (salaire_inf > salaire_sup) {
            throw new Error("La borne supérieur est plus élevée que la borne inférieure");
        }

        const leads = inputLeadsBySalary(salaire_inf, salaire_sup);

        display(leads);
    });

program
    .description('Un CLI pour appeler l\'api VirtualCRM')

program.parse();

// Get the leads between a date and another
// Get the leads between a salary and another
// Free request


