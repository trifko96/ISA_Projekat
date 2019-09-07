import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Avion } from '../model/Avion';
import { Sediste } from '../model/Sediste';
import { Let } from '../model/Let';
import { AvionskaKarta } from '../model/AvionskaKarta';
import { Rezervacija } from '../model/Rezervacija';

@Injectable({
    providedIn : 'root',
})
export class avionServis {

    constructor(private http : HttpClient) {}

    dodajAvion(a : Avion, id : number){
        return this.http.post<Avion[]>("api/Avion/dodajAvion/"+id, a);
    }

    vratiAvione(id : number){
        return this.http.get<Avion[]>("api/Avion/vratiAvione/"+id);
    }

    vratiAvioneZaLet(id : number){
        return this.http.get<Avion[]>("api/Avion/vratiAvioneZaLet/"+id);
    }

    izmeniAvion(a : Avion){
        return this.http.post<Avion[]>("api/Avion/izmeniAvion", a);
    }

    izmeniSediste(s : Sediste){
        return this.http.post<Avion>("api/Sediste/izmeniSediste", s);
    }

    dodajSediste(s : Sediste){
        return this.http.post<Avion>("api/Sediste/dodajSediste", s);
    }

    vratiKarte(l : Let){
        return this.http.post<AvionskaKarta[]>("api/Sediste/vratiBrzeKarte", l);
    }

    vratiSveBrzeKarte(){
        return this.http.get<AvionskaKarta[]>("api/Sediste/vratiSveBrzeKarte");
    }

    brzoRezervisi(k : AvionskaKarta, id : number){
        return this.http.post<AvionskaKarta[]>("api/Sediste/brzoRezervisi/"+id,k);
    }

    vratiAvion(id : number){
        return this.http.get<Avion>("api/Avion/vratiAvion/"+id);
    }

    rezervisi(r : Rezervacija, id : number){
        return this.http.post<Rezervacija>("api/Sediste/rezervisi/"+id,r);
    }
}