import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vozilo } from '../model/Vozilo';
import { Rezervacija } from '../model/Rezervacija';
import { PretragaVozilo } from '../model/PretragaVozilo';
import { DatumiPopust } from '../model/DatumiPopust';
import { RezervacijaVozilo } from '../model/RezervacijaVozilo';

@Injectable({
    providedIn : 'root',
})
export class voziloServis {

    constructor(private http : HttpClient) {}
 
    vratiVozilo(){
        return this.http.get<Vozilo[]>("api/Vozilo/vratiVozilo");
    }

    dodajVozilo(v : Vozilo){
        return this.http.post<Vozilo[]>("api/Vozilo/dodajNovo",v);
    }

   /* vratiSlobodne(){
        return this.http.get<Lokacija[]>("api/Lokacija/vratiSlobodneLokacije");
    }*/ 

    /*dodajPostojecu(l : Lokacija[]){
        return this.http.post<Lokacija[]>("api/Lokacija/dodajPostojecu",l);
    }*/

    obrisi(v : Vozilo){
        return this.http.post<Vozilo[]>("api/Vozilo/obrisiVozilo",v);
    }

    izmeniVozilo(v : Vozilo){
        return this.http.post<Vozilo>("api/Vozilo/izmeniVozilo", v);
    }

   /* vratiSveLokacije(){
        return this.http.get<Lokacija[]>("api/Lokacija/vratiSveLokacije");
    }*/

    vratiBrzaVozila(d : DatumiPopust){
        return this.http.post<Vozilo[]>("api/Vozilo/vratiBrzaVozila", d);
    }

    brzoRezervisiVozilo(r : Rezervacija, id : number){
        return this.http.post<Vozilo[]>("api/Vozilo/brzoRezervisiVozilo/"+id, r);
    }

    pretraziVozilo(p : PretragaVozilo, id : number){
        return this.http.post<Vozilo[]>("api/Vozilo/pretraziVozilo/"+id,p);
    }

    vratiRezezrvisanaVozila(){
        return this.http.get<RezervacijaVozilo[]>("api/Vozilo/rezervisanaVozila")
    }

    otkaziRezervacijuVozila(r : RezervacijaVozilo, id : number){
        return this.http.post<RezervacijaVozilo[]>("api/Vozilo/otkaziRezervacijuVozila/"+id, r);
    }

    oceniVozilo(idr : number, id : number, opcija : number){
        return this.http.get<RezervacijaVozilo[]>("api/Vozilo/oceniVozilo/"+id+"/"+idr+"/"+opcija);
    }
    
}