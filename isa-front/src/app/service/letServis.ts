import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Let } from '../model/Let';

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
}