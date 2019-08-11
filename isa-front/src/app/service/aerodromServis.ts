import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Aerodrom } from '../model/Aerodrom';

@Injectable({
    providedIn : 'root',
})
export class aerodromServis {

    constructor(private http : HttpClient) {}

    vratiAerodrome(){
        return this.http.get<Aerodrom[]>("api/Aerodrom/vratiAerodrome");
    }

    dodajAerodrom(a : Aerodrom){
        return this.http.post<Aerodrom[]>("api/Aerodrom/dodajNovi",a);
    }

    vratiSlobodne(){
        return this.http.get<Aerodrom[]>("api/Aerodrom/vratiSlobodneAerodrome");
    }

    dodajPostojeci(a : Aerodrom[]){
        return this.http.post<Aerodrom[]>("api/Aerodrom/dodajPostojeci",a);
    }

    obrisi(a : Aerodrom){
        return this.http.post<Aerodrom[]>("api/Aerodrom/obrisiAerodrom",a);
    }
}