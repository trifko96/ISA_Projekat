import { Component, OnInit } from '@angular/core';
import { rentCarServis } from 'src/app/service/rentCarServis';
import { HttpClient } from '@angular/common/http';
import { RentCar } from 'src/app/model/RentCar';
declare var ol: any;
import * as $ from 'jquery';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

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
  idKorisnika : number;

  constructor(private rentCarServis : rentCarServis, private http : HttpClient, private korisnikServis : korisnikServis, private router : Router) {
    this.korisnikServis.vratiTrenutnogKorisnika().subscribe(
      data => {
        if(data.provera == "ADMINISTRATOR_HOTELA"){
          this.router.navigate([""]);
        } else if(data.provera == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate(["glavna/avioKompanija"]);
        } else if(data.provera == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["glavnaAdminSistema/adminSistema"]);
        } else if(data.provera == "OBICAN_KORISNIK"){
          this.router.navigate(["glavnaRegistrovani/profil"]);
        }
      this.idKorisnika = data.id;
      this.rentCarServis.vratiRentCar().subscribe(
       data => {
         this.rentACar = data;
         if(data.brojOcena != 0){
           this.prosecnaOcena = data.ocena/data.brojOcena;
         }
       }
     )

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
      this.poruka = "Odaberite servis za izmenu!";
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
