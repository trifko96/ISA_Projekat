import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Let } from '../model/Let';
import { PretragaLet } from '../model/PretragaLet';
import { FilterLet } from '../model/FilterLet';

@Injectable({
    providedIn : 'root',
})
export class letServis{

    constructor(private http : HttpClient) {}

    vratiLetove(){
        return this.http.get<Let[]>("api/Let/vratiLetove");
    }

    dodajNoviLet(l : Let){
        return this.http.post<Let[]>("api/Let/dodajLet", l);
    }

    vratiSveLetove(){
        return this.http.get<Let[]>("api/Let/vratiSveLetove");
    }

    pretraziLet(l : PretragaLet){
        return this.http.post<Let[]>("api/Let/pretraziLet", l);
    }

    filtrirajLet(l : FilterLet){
        return this.http.post<Let[]>("api/Let/filtrirajLet", l);
    }
}