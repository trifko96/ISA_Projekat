import { Component, OnInit } from '@angular/core';
import { RentCar } from 'src/app/model/RentCar';
import { Vozilo } from 'src/app/model/Vozilo';
import { rentCarServis } from 'src/app/service/rentCarServis';
import { voziloServis } from 'src/app/service/voziloServis';
import { rezervacijaServis } from 'src/app/service/rezervacijaServis';
import { Rezervacija } from 'src/app/model/Rezervacija';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/model/Korisnik';
import { Lokacija } from 'src/app/model/Lokacija';
import { PretragaServis } from 'src/app/model/PretragaServis';
import { lokacijeServis } from 'src/app/service/lokacijeServis';
import * as $ from 'jquery';
import { PretragaVozilo } from 'src/app/model/PretragaVozilo';

@Component({
  selector: 'app-rezervisanje-vozila',
  templateUrl: './rezervisanje-vozila.component.html',
  styleUrls: ['./rezervisanje-vozila.component.css']
})
export class RezervisanjeVozilaComponent implements OnInit {

  servisi : RentCar[] = [];
  brzaVozila : Vozilo[] = [];
  vozila : Vozilo[] = [];
  prikazBrzihVozila : boolean = false;
  prikazFilijala : boolean = false;
  prikazPretrageVozila : boolean = false;
  prikazTabeleVozila : boolean = false;
  filijaleZaPrikaz : Lokacija[] = [];
  rezervacija : Rezervacija = new Rezervacija();
  trenutniServis : RentCar = new RentCar();
  idKorisnika : number;
  korisnik : Korisnik = new Korisnik();
  greskaPriBrzojRezervaciji : string = "";
  datumOd : Date = new Date();
  datumDo : Date = new Date();
  pretragaVozilo : PretragaVozilo = new PretragaVozilo();
  sveLokacije : Lokacija[] = [];
  mojeLokacije : Lokacija[] = [];
  selektovanaLokacija : string = "";
  selektovanaLokacijaPre : string = "";
  selektovanaLokacijaVra : string = "";
  selektovanaOpcijaTip : string = "";
  porukaZaRezervaciju : string = "";
  pretragaServis : PretragaServis = new PretragaServis();
  prikazPretrageServisa : boolean = true;
  opcije1 = [
    {name: "MINI", value: "MINI"},
    {name: "LIMUZINA", value: "LIMUZINA"},
    {name: "KARAVAN", value: "KARAVAN"},
    {name: "KABRIOLET", value: "KABRIOLET"}
  
  ]

  constructor(private rentCarServis : rentCarServis, private voziloServis : voziloServis, private rezervacijaServis : rezervacijaServis, private korisnikServis : korisnikServis, private router : Router, private lokacijaServis : lokacijeServis) {
    this.korisnikServis.vratiTrenutnogKorisnika().subscribe(
      data => {
        if(data.provera == "ADMINISTRATOR_HOTELA"){
          this.router.navigate([""]);
        } else if(data.provera == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["glavnaRentACar/infoStranica"]);
        } else if(data.provera == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["glavnaAdminSistema/adminSistema"]);
        } else if(data.provera == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate(["glavna/avioKompanija"]);
        }
        this.idKorisnika = data.id;
        this.korisnik = data;
      }
    )
    this.rentCarServis.vratiSveServise().subscribe(
      data => {
        this.servisi = data;
      }
    )

    this.lokacijaServis.vratiSveLokacije().subscribe(
      data => {
        this.sveLokacije = data;
      }
    )

    for(let s of this.servisi){
      if(s.brojOcena > 0){
        s.prosecnaOcena = s.ocena / s.brojOcena;
      }
    }

    this.rezervacija = this.rezervacijaServis.rezervacija;
    if(this.rezervacija.karte.length > 0){
      this.datumOd = this.rezervacija.karte[0].datumOdLeta;
      this.datumDo = this.rezervacija.karte[0].datumDoLeta;
    }
  }

  ngOnInit() {
  }

  prikaziBrzaVozila(){
    this.voziloServis.vratiBrzaVozila().subscribe(
      data => {
        this.brzaVozila = data;
        this.prikazBrzihVozila = true;
        this.prikazFilijala = false;
      }
    )
  }

  zatvori(){
    this.prikazBrzihVozila = false;
    this.greskaPriBrzojRezervaciji = "";
  }

  prikaziFilijale(r : RentCar){
    this.rentCarServis.vratiFilijaleServisa(r, r.id).subscribe(
      data => {
        this.filijaleZaPrikaz = data;
        this.prikazBrzihVozila = false;
        this.prikazFilijala = true;
      }
    )
  }

  zatvoriFilijale(){
    this.prikazFilijala = false;
  }

  brzoRezervisi(v : Vozilo){
    if(this.rezervacija.karte.length != 0){
      v.emailKorisnika = this.korisnik.email;
      v.datumOd = this.datumOd;
      v.datumDo = this.datumDo;
      this.rezervacija.vozilo = v;
      this.voziloServis.brzoRezervisiVozilo(this.rezervacija, this.idKorisnika).subscribe(
        data => {
          this.brzaVozila = data;
          this.rezervacijaServis.rezervacija = new Rezervacija();
        },

        error => {
          this.greskaPriBrzojRezervaciji = "Vozilo ili neko od sedista je u medjuvremenu rezervisano!";
        }
      )
    } else {
      this.greskaPriBrzojRezervaciji = "Morate rezervisati let!";
    }
  }
  
  pretrazi(){
    let provera = false;
    if(this.selektovanaLokacija == ""){
      $("#lokacija").addClass('border-danger');
      provera = true;
    } else {
      $("#lokacija").removeClass('border-danger');
    }
    if(!provera){
      this.pretragaServis.lokacija = this.selektovanaLokacija;
      this.rentCarServis.pretraziServis(this.pretragaServis).subscribe(
        data => {
          this.servisi = data;
          this.pretragaServis = new PretragaServis();
          this.selektovanaLokacija = "";
          this.prikazPretrageServisa = false;
        }
      )
    }
  }

  prikazRezervisanja(s : RentCar){
    this.trenutniServis = s;
    this.prikazPretrageVozila = true;
    this.lokacijaServis.vratiLokacijePoRent(s.id).subscribe(
      data => {
        this.mojeLokacije = data;
      }
    )
  }

  pretraziVozila(){
    let provera = false;
    if(this.selektovanaLokacijaPre == ""){
      $("#lokacijaPre").addClass('border-danger');
      provera = true;
    } else {
      $("#lokacijaPre").removeClass('border-danger');
    }

    if(this.selektovanaLokacijaVra == ""){
      $("#lokacijaVra").addClass('border-danger');
      provera = true;
    } else {
      $("#lokacijaVra").removeClass('border-danger');
    }

    if(this.selektovanaOpcijaTip == ""){
      $("#tipVoz").addClass('border-danger');
      provera = true;
    } else {
      $("#tipVoz").removeClass('border-danger');
    }

    if(this.pretragaVozilo.brojPutnika == 0){
      $("#brojPutnika").addClass('border-danger');
      provera = true;
    } else {
      $("#brojPutnika").removeClass('border-danger');
    }

    if(!provera){
      this.pretragaVozilo.mestoPreuzimanja = this.selektovanaLokacijaPre;
      this.pretragaVozilo.mestoVracanja = this.selektovanaLokacijaVra;
      this.pretragaVozilo.tipVozila = this.selektovanaOpcijaTip;
      this.voziloServis.pretraziVozilo(this.pretragaVozilo, this.trenutniServis.id).subscribe(
        data => {
          this.vozila = data;
          for(let v of this.vozila){
            if(v.brOcena > 0){
              v.prosecnaOcena = v.brOcena/v.ocene;
            }
          }
          this.prikazPretrageVozila = false;
          this.prikazTabeleVozila = true;
        }
      )
    }
    
  }

  rezervisi(v : Vozilo){
    if(this.rezervacija.karte.length != 0){
      v.emailKorisnika = this.korisnik.email;
      v.datumOd = this.pretragaVozilo.datumPreuzimanja;
      v.datumDo = this.pretragaVozilo.datumVracanja;
      this.rezervacija.vozilo = v;
      this.voziloServis.brzoRezervisiVozilo(this.rezervacija, this.idKorisnika).subscribe(
        data => {
          this.prikazTabeleVozila = false;
          this.rezervacijaServis.rezervacija = new Rezervacija();
          this.rentCarServis.vratiSveServise().subscribe(
            data => {
              this.servisi = data;
            }
          )
        },

        error => {
          this.porukaZaRezervaciju = "Vozilo ili neko od sedista je u medjuvremenu rezervisano!";
        }
      )
    } else {
      this.porukaZaRezervaciju = "Morate prvo rezervisati let!";
    }
  }

}
