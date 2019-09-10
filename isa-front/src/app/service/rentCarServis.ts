import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {RentCar} from '../model/RentCar';
import { DatumskiOpseg } from '../model/DatumskiOpseg';
import { Prihod } from '../model/Prihod';
import { Statistika } from '../model/Statistika';

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

    vratiPrihod(d : DatumskiOpseg, id : number){
      return this.http.post<Prihod>("api/RentACar/vratiPrihod/"+id, d);
    } 

    vratiStatistikuPoDanu(){
      return this.http.get<Statistika>("api/RentACar/vratiStatistikuPoDanu");
    }

    vratiStatistikuPoNedelji(){
      return this.http.get<Statistika>("api/RentACar/vratiStatistikuPoNedelji");
    }

    vratiStatistikuPoGodini(){
      return this.http.get<Statistika>("api/RentACar/vratiStatistikuPoGodini");
    }

}