import { Vozilo } from './Vozilo';

export class RezervacijaVozilo{
    idRezVozilo : number;
    datumRezervacijaOd : Date = new Date();
    datumRezervacijaDo : Date = new Date();
    trenutniDatumRezervacija : Date = new Date();
    emailKorisnika : string = "";
    mestoPreuzimanja : string = "";
    mestoVracanja : string = "";
    prosecnaOcena : number;
    brOcena : number;
    ocene : number;
    vozilo : Vozilo =  new Vozilo();
}