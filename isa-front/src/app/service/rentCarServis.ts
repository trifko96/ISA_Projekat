import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {RentCar} from '../model/RentCar';

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
}