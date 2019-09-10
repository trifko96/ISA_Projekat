export class RezervacijaKarataDTO{
    idSedista : number;
    brSedista : number;
    ime : string = "";
    prezime : string = "";
    brTelefona : string = "";
    email : string = "";
    brPasosa : string = "";
    idLeta : number;
    datumOdLeta : Date = new Date();
    datumDoLeta : Date = new Date();
}