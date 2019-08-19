import { Aerodrom } from './Aerodrom';
import { Avion } from './Avion';
import { AvionskaKarta } from './AvionskaKarta';
import { LokacijePresedanja } from './LokacijePresedanja';

export class Let{
    id : number;
    datumPoletanja : Date = new Date();
    datumSletanja : Date = new Date();
    datumPolaskaNazad : Date = new Date();
    datumDolaskaNazad : Date = new Date();
    polaznaDestinacija : Aerodrom = new Aerodrom();
    odredisnaDestinacija : Aerodrom = new Aerodrom();
    ocena : number;
    cenaKarteBiznisKlase : number = 0;
    cenaKarteEkonomskeKlase : number = 0;
    cenaPrveKlase : number = 0;
    avion : Avion = new Avion();
    karte : AvionskaKarta[] = [];
    lokacije : LokacijePresedanja[] = [];
    tip : string = "";
    popust : number = 0;
}