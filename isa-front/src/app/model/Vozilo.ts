
export class Vozilo{
    id : number;
    cena : string = "";
    naziv : string = "";
    marka : string = "";
    model : string = "";
    godinaProizvodnje : string = "";
    brSedista : number = 0;
    tip : string = "";
    prosecnaOcena : number = 0;
    brOcena : number;
    ocene : number;
    rezervisano : boolean;
    naPopustu : string = "";
    datumOd : Date;
    datumDo : Date;
    emailKorisnika : string = "";
    mestoPreuzimanja : string = "";
    mestoVracanja : string = "";
    adresaLokacije : string = "";
    popust : number = 0;
    datumPopustOd : Date;
    datumPopustDo : Date;
    datumPopustOdString : string = "";
    datumPopustDoString : string = "";
} 