import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {RentCar} from '../model/RentCar';
import { DatumskiOpseg } from '../model/DatumskiOpseg';
import { Prihod } from '../model/Prihod';
import { Statistika } from '../model/Statistika';
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

    vratiSveServise(){
      return this.http.get<RentCar[]>("api/RentACar/vratiSveServise");
    }

    vratiFilijaleServisa(r : RentCar, id : number){
      return this.http.post<Lokacija[]>("api/RentACar/vratiFilijale/"+id, r);
    }

    pretraziServis(p : PretragaServis){
      return this.http.post<RentCar[]>("api/RentACar/pretraziServise",p);
    }

    oceniServis(r : RentCar, id : number, opcija : number){
      return this.http.get<RentCar[]>("api/RentACar/oceniServis/"+id/+r/+opcija);
  }
}