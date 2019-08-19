import { Aerodrom } from './Aerodrom';

export class LokacijePresedanja{
    id : number;
    datumPoletanja : Date;
    datumSletanja : Date;
    aerodrom : Aerodrom = new Aerodrom();
}