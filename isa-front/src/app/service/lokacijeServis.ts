import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Lokacija } from '../model/Lokacija';

@Injectable({
    providedIn : 'root',
})
export class lokacijeServis {

    constructor(private http : HttpClient) {}
 
    vratiLokacije(){
        return this.http.get<Lokacija[]>("api/Lokacija/vratiLokacije");
    }

    dodajLokaciju(l : Lokacija){
        return this.http.post<Lokacija[]>("api/Lokacija/dodajNovu",l);
    }

    vratiSlobodne(){
        return this.http.get<Lokacija[]>("api/Lokacija/vratiSlobodneLokacije");
    }

    dodajPostojecu(l : Lokacija[]){
        return this.http.post<Lokacija[]>("api/Lokacija/dodajPostojecu",l);
    }

    obrisi(l : Lokacija){
        return this.http.post<Lokacija[]>("api/Lokacija/obrisiLokaciju",l);
    }

    vratiSveLokacije(){
        return this.http.get<Lokacija[]>("api/Lokacija/vratiSveLokacije");
    }
}