import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rezervacija } from '../model/Rezervacija';

@Injectable({
    providedIn : 'root',
})
export class rezervacijaServis{

    rezervacija : Rezervacija = new Rezervacija();

    constructor(private http : HttpClient) {}
}