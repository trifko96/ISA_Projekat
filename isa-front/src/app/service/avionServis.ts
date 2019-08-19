import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Avion } from '../model/Avion';
import { Sediste } from '../model/Sediste';
import { Let } from '../model/Let';
import { AvionskaKarta } from '../model/AvionskaKarta';

@Injectable({
    providedIn : 'root',
})
export class avionServis {

    constructor(private http : HttpClient) {}

    dodajAvion(a : Avion){
        return this.http.post<Avion[]>("api/Avion/dodajAvion", a);
    }

    vratiAvione(){
        return this.http.get<Avion[]>("api/Avion/vratiAvione");
    }

    vratiAvioneZaLet(){
        return this.http.get<Avion[]>("api/Avion/vratiAvioneZaLet");
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
}