import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {RentCar} from '../model/RentCar';
import { Lokacija } from '../model/Lokacija';
import { PretragaServis } from '../model/PretragaServis';

@Injectable({
    providedIn : 'root',
})

export class rentCarServis{
  
    constructor(private http : HttpClient) {}
    
    vratiRentCar() {
      return this.http.get<RentCar>("api/RentACar/vratiRentACar");
    }

    izmeniRentACar(r : RentCar){
      return this.http.post<RentCar>("api/RentACar/izmeniRentACar", r);
    }

    vratiSveServise(){
      return this.http.get<RentCar[]>("api/RentACar/vratiSveServise");
    }

    vratiFilijaleServisa(r : RentCar, id : number){
      return this.http.post<Lokacija[]>("api/RentACar/vratiFilijale/"+id, r);
    }

    pretraziServis(p : PretragaServis){
      return this.http.post<RentCar[]>("api/RentACar/pretraziServise",p);
    }
}