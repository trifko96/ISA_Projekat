import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Aerodrom } from '../model/Aerodrom';

@Injectable({
    providedIn : 'root',
})
export class aerodromServis {

    constructor(private http : HttpClient) {}

    vratiAerodrome(id : number){
        return this.http.get<Aerodrom[]>("api/Aerodrom/vratiAerodrome/"+id);
    }

    dodajAerodrom(a : Aerodrom, id : number){
        return this.http.post<Aerodrom[]>("api/Aerodrom/dodajNovi/"+id,a);
    }

    vratiSlobodne(id : number){
        return this.http.get<Aerodrom[]>("api/Aerodrom/vratiSlobodneAerodrome/"+id);
    }

    dodajPostojeci(a : Aerodrom[], id : number){
        return this.http.post<Aerodrom[]>("api/Aerodrom/dodajPostojeci/"+id,a);
    }

    obrisi(a : Aerodrom, id : number){
        return this.http.post<Aerodrom[]>("api/Aerodrom/obrisiAerodrom/"+id,a);
    }

    vratiSveAerodrome(){
        return this.http.get<Aerodrom[]>("api/Aerodrom/vratiSveAerodrome");
    }
}