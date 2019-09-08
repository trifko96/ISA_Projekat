import { Component, OnInit } from '@angular/core';
import { rentCarServis } from 'src/app/service/rentCarServis';
import { HttpClient } from '@angular/common/http';
import { RentCar } from 'src/app/model/RentCar';
declare var ol: any;
import * as $ from 'jquery';

@Component({
  selector: 'app-info-stranica',
  templateUrl: './info-stranica.component.html',
  styleUrls: ['./info-stranica.component.css']
})
 
export class InfoStranicaComponent implements OnInit {

  rentACar : RentCar = new RentCar();
  rentACarIzmena : RentCar = new RentCar();
  prosecnaOcena : number = 0;
  izmena : boolean = false;
  poruka : string = "";

  constructor(private rentCarServis : rentCarServis, private http : HttpClient) {

    this.rentCarServis.vratiRentCar().subscribe(
      data => {
        this.rentACar = data;
        if(data.brojOcena != 0){
          this.prosecnaOcena = data.ocena/data.brojOcena;
        }
      }
    )
  }

  Izmena(){
    if(this.izmena == true){
      this.izmena = false;
      this.rentACar.naziv = $("#nazivKom").val();
      this.rentACar.adresa = $("#adresaKom").val();
      this.rentACar.opis = $("#opisKom").val();
      this.rentACar.id = this.rentACar.id;
      this.rentACar.brojOcena = this.rentACar.brojOcena;
      this.rentACar.ocena = this.rentACar.ocena;
      this.rentACar.prihod = this.rentACar.prihod;
      this.rentCarServis.izmeniRentACar(this.rentACar).subscribe(
        data => {
          this.rentCarServis.vratiRentCar().subscribe(
            data => {
              this.rentACar = data;
              $("#nazivKom").val("");
              $("#adresaKom").val("");
              $("#opisKom").val("");
              this.poruka = "";
            }
          )
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
        }
      )
    } else {
      this.poruka = "Odaberite kompaniju za izmenu!";
    }
  }


  onClick(){
    $("#nazivKom").val(this.rentACar.naziv);
    $("#adresaKom").val(this.rentACar.adresa);
    $("#opisKom").val(this.rentACar.opis);
    this.izmena = true;
    this.poruka = ""; 
  }
  
  ngOnInit() {
  }

}
