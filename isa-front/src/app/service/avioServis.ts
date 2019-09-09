import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AvioKompanija } from '../model/AvioKompanija';
import { DatumskiOpseg } from '../model/DatumskiOpseg';
import { Prihod } from '../model/Prihod';
import { Statistika } from '../model/Statistika';

@Injectable({
    providedIn : 'root',
})
export class avioServis {

    constructor(private http : HttpClient) {}

    vratiKompaniju(id : number){
        return this.http.get<AvioKompanija>("api/AvioKompanija/vratiKompaniju/"+id);
    }

    izmeniKompaniju(a : AvioKompanija){
        return this.http.post<AvioKompanija>("api/AvioKompanija/izmeniKompaniju", a);
    }

    vratiPrihod(d : DatumskiOpseg, id : number){
        return this.http.post<Prihod>("api/AvioKompanija/vratiPrihod/"+id, d);
    }

    vratiStatistikuPoDanu(){
        return this.http.get<Statistika>("api/AvioKompanija/vratiStatistikuPoDanu");
    }

    vratiStatistikuPoNedelji(){
        return this.http.get<Statistika>("api/AvioKompanija/vratiStatistikuPoNedelji");
    }

    vratiStatistikuPoGodini(){
        return this.http.get<Statistika>("api/AvioKompanija/vratiStatistikuPoGodini");
    }

    vratiSveKompanije(){
        return this.http.get<AvioKompanija[]>("api/AvioKompanija/vratiSveKompanije");
    }
}