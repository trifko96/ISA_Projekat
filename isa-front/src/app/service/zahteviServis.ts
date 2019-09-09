import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Korisnik } from '../model/Korisnik';

@Injectable({
    providedIn : 'root',
})
export class zahteviServis{

    constructor(private http : HttpClient) {}

    vratiMojePrijatelje(){
        return this.http.get<Korisnik[]>("api/Zahtevi/vratiMojePrijatelje");
    }

    vratiMojeZahteve(){
        return this.http.get<Korisnik[]>("api/Zahtevi/vratiMojeZahteve");
    }

    vratiOstaleKorisnike(ime : string){
        return this.http.get<Korisnik[]>("api/Zahtevi/vratiOstaleKorisnike/"+ime);
    }

    obrisiPrijatelja(id : number){
        return this.http.get<Korisnik[]>("api/Zahtevi/obrisiPrijatelja/"+id);
    }

    dodajPrijatelja(k : Korisnik){
        return this.http.post<Korisnik[]>("api/Zahtevi/dodajPrijatelja",k);
    }

    prihvatiZahtev(id : number){
        return this.http.get<Korisnik[]>("api/Zahtevi/prihvatiZahtev/"+id);
    }

    izbrisiZahtev(id : number){
        return this.http.get<Korisnik[]>("api/Zahtevi/izbrisiZahtev/"+id);
    }
    
    vratiPrijateljeZaLet(){
        return this.http.get<Korisnik[]>("api/Zahtevi/vratiPrijateljeZaLet");
    }
}