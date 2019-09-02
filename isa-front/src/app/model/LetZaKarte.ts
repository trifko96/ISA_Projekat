import { Aerodrom } from './Aerodrom';

export class LetZaKarte{
    id : number;
    datumPoletanja : Date;
    datumSletanja : Date;
    polaznaDestinacija : Aerodrom;
    odredisnaDestinacija : Aerodrom;
    popust : number;
    imeAviona : string;
}