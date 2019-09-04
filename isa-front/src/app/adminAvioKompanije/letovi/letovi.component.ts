import { Component, OnInit } from '@angular/core';
import { avionServis } from 'src/app/service/avionServis';
import { aerodromServis } from 'src/app/service/aerodromServis';
import { Aerodrom } from 'src/app/model/Aerodrom';
import { Avion } from 'src/app/model/Avion';
import { Let } from 'src/app/model/Let';
import * as $ from 'jquery';
import { LokacijePresedanja } from 'src/app/model/LokacijePresedanja';
import { letServis } from 'src/app/service/letServis';
import { AvionskaKarta } from 'src/app/model/AvionskaKarta';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-letovi',
  templateUrl: './letovi.component.html',
  styleUrls: ['./letovi.component.css']
})
export class LetoviComponent implements OnInit {

  mojiAerodromi : Aerodrom[] = [];
  lokacijeAerodromi : Aerodrom[] = [];
  avioni : Avion[] = [];
  letovi : Let[] = [];
  opcije = [
    {name: "ONE_WAY", value: "ONE_WAY"},
    {name: "ROUND_TRIP", value: "ROUND_TRIP"}
  ]
  prikazFormeZaDodavanje : boolean = false;
  prikazDalje : boolean = false;
  prikazLokacija : boolean = false;
  noviLet : Let = new Let();
  selektovanaOpcija : string = "";
  selektovaniAvion : string = "";
  selektovanoMestoPoletanja : string = "";
  selektovanoMestoSletanja : string = "";
  izabraneLokacije : LokacijePresedanja[] = [];
  lokacijeZaPrikaz : LokacijePresedanja[] = [];
  poruka : string = "";
  porukaAero : string = "";
  porukaLokacije : string = "";
  avioKarte : AvionskaKarta[] = [];
  prikazBrzihKarata : boolean = false;

  constructor(private avionServis : avionServis, private aeroServis : aerodromServis, private letServis : letServis, private korisnikServis : korisnikServis, private router : Router) {
    this.korisnikServis.vratiTrenutnogKorisnika().subscribe(
      data => {
        if(data.provera == "ADMINISTRATOR_HOTELA"){
          this.router.navigate([""]);
        } else if(data.provera == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["glavnaRentACar/infoStranica"]);
        } else if(data.provera == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["glavnaAdminSistema/adminSistema"]);
        } else if(data.provera == "OBICAN_KORISNIK"){
          this.router.navigate(["glavnaRegistrovani/profil"]);
        }
      }
    )

    this.avionServis.vratiAvioneZaLet().subscribe(
      data => {
        this.avioni = data;
      }
    )

    this.aeroServis.vratiAerodrome().subscribe(
      data => {
        this.mojiAerodromi = data;
        this.lokacijeAerodromi = data;
      }
    )

    this.letServis.vratiLetove().subscribe(
      data => {
        this.letovi = data;
      }
    )

  }

  ngOnInit() {
  }

  dodaj(){
    this.prikazFormeZaDodavanje = true;
  }

  dalje(){
    let provera : boolean = false;
    this.noviLet.tip = this.selektovanaOpcija;
    if(this.selektovanaOpcija == ""){
      provera = true;
      $("#tipLeta").addClass('border-danger');
    } else {
      $("#tipLeta").removeClass('border-danger');
    }
    if(provera == false){
      this.prikazDalje = true;
    }
  }

  prikaziLokacije(l : Let){
    this.lokacijeZaPrikaz = l.lokacije;
    this.prikazLokacija = true;
  }

  zatvori(){
    this.prikazLokacija = false;
  }

  dodavanjeLeta(){
    let provera = false;
    if(this.selektovaniAvion == ""){
      $("#avionLet").addClass('border-danger');
      provera = true;
    } else {
      $("#avionLet").removeClass('border-danger');
    }
    if(this.selektovanoMestoPoletanja == ""){
      $("#mestoPol").addClass('border-danger');
      provera = true;
    } else {
      $("#mestoPol").removeClass('border-danger');
    }
    if(this.selektovanoMestoSletanja == ""){
      $("#mestoSle").addClass('border-danger');
      provera = true;
    } else {
      $("#mestoSle").removeClass('border-danger');
    }
    if(this.noviLet.cenaPrveKlase <= 1){
      $("#cenaPrve").addClass('border-danger');
      provera = true;
    } else {
      $("#cenaPrve").removeClass('border-danger');
    }
    if(this.noviLet.cenaKarteBiznisKlase <= 1){
      $("#cenaBiznis").addClass('border-danger');
      provera = true;
    } else {
      $("#cenaBiznis").removeClass('border-danger');
    }
    if(this.noviLet.cenaKarteEkonomskeKlase <= 1){
      $("#cenaEkonomske").addClass('border-danger');
      provera = true;
    } else {
      $("#cenaEkonomske").removeClass('border-danger');
    }
    if(this.noviLet.popust <= 1){
      $("#popust").addClass('border-danger');
      provera = true;
    } else {
      $("#popust").removeClass('border-danger');
    }
    if(this.noviLet.datumPoletanja >= this.noviLet.datumSletanja){
      provera = true;
      this.poruka = "Pogresno odabrani datumi!"
    } else {
      this.poruka = "";
    }
    if(this.noviLet.tip == "ROUND_TRIP"){
      if(this.noviLet.datumPolaskaNazad <= this.noviLet.datumSletanja){
        provera = true;
        this.poruka = "Pogresno odabrani datumi!";
      } else {
        this.poruka = "";
      }
      if(this.noviLet.datumDolaskaNazad <= this.noviLet.datumPolaskaNazad){
        provera = true;
        this.poruka = "Pogresno odabrani datumi!";
      } else {
        this.poruka = "";
      }
    }
    if(this.selektovanoMestoPoletanja == this.selektovanoMestoSletanja){
      provera = true;
      this.porukaAero = "Aerodromi moraju biti razliciti!";
    } else {
      this.porukaAero = "";
    }
    for(let l of this.lokacijeAerodromi){
      if(l.isChecked){
        if(l.ime == this.selektovanoMestoPoletanja){
          provera = true;
          this.porukaLokacije = "Odaberite drugu lokaciju!";
        } else if(l.ime == this.selektovanoMestoSletanja){
          provera = true;
          this.porukaLokacije = "Odaberite drugu lokaciju!";
        } else {
          this.porukaLokacije = "";
        }
      }
    }

    if(!provera){
      for(let a of this.avioni){
        if(a.ime == this.selektovaniAvion){
          this.noviLet.avion = a;
        }
      }
      for(let a of this.mojiAerodromi){
        if(a.ime == this.selektovanoMestoPoletanja){
          this.noviLet.polaznaDestinacija = a;
        }
      }
      for(let a of this.mojiAerodromi){
        if(a.ime == this.selektovanoMestoSletanja){
          this.noviLet.odredisnaDestinacija = a;
        }
      }
      for(let l of this.lokacijeAerodromi){
        if(l.isChecked){
          let lok = new LokacijePresedanja();
          lok.aerodrom = l;
          this.izabraneLokacije.push(lok);
        }
      }
      this.noviLet.lokacije = this.izabraneLokacije;
      this.izabraneLokacije = [];
      this.letServis.dodajNoviLet(this.noviLet).subscribe(
        data => {
          this.letovi = data;
          this.prikazFormeZaDodavanje = false;
          this.prikazDalje = false;
          this.noviLet = new Let();
          this.selektovanaOpcija = "";
          for(let l of this.lokacijeAerodromi){
            l.isChecked = false;
          }
          this.selektovaniAvion = "";
          this.selektovanoMestoPoletanja = "";
          this.selektovanoMestoSletanja = "";
          this.avionServis.vratiAvioneZaLet().subscribe(
            data => {
              this.avioni = data;
            }
          )
        }
      )

    }
  }

  ok(){
    this.prikazBrzihKarata = false;
  }

  prikazKarata(l : Let){
    this.avionServis.vratiKarte(l).subscribe(
      data => {
        this.avioKarte = data;
        this.prikazBrzihKarata = true;
      }
    )
  }
}
