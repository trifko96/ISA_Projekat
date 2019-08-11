import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AvioKompanija } from '../model/AvioKompanija';

@Injectable({
    providedIn : 'root',
})
export class avioServis {

    constructor(private http : HttpClient) {}

    vratiKompaniju(){
        return this.http.get<AvioKompanija>("api/AvioKompanija/vratiKompaniju");
    }

    izmeniKompaniju(a : AvioKompanija){
        return this.http.post<AvioKompanija>("api/AvioKompanija/izmeniKompaniju", a);
    }
}