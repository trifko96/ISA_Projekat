import { Component, OnInit } from '@angular/core';
import { AvioKompanija } from 'src/app/model/AvioKompanija';
import { avioServis } from 'src/app/service/avioServis';
import { AvionskaKarta } from 'src/app/model/AvionskaKarta';
import { avionServis } from 'src/app/service/avionServis';
import { PretragaLet } from 'src/app/model/PretragaLet';
import { FilterLet } from 'src/app/model/FilterLet';
import { LokacijePresedanja } from 'src/app/model/LokacijePresedanja';
import { letServis } from 'src/app/service/letServis';
import { Let } from 'src/app/model/Let';
import * as $ from 'jquery';
import { aerodromServis } from 'src/app/service/aerodromServis';
import { Aerodrom } from 'src/app/model/Aerodrom';

@Component({
  selector: 'app-rezervisanje',
  templateUrl: './rezervisanje.component.html',
  styleUrls: ['./rezervisanje.component.css']
})
export class RezervisanjeComponent implements OnInit {

  avioKompanije : AvioKompanija[] = [];
  kompanije : AvioKompanija[] = [];
  brzeKarte : AvionskaKarta[] = [];
  prikazBrzihKarata : boolean = false;
  rezervisanje : boolean = false;
  filtriranje : boolean = false;
  prikazLokacija : boolean = false;
  pretragaLet : PretragaLet = new PretragaLet();
  mojiAerodromi : Aerodrom[] = [];
  selektovanoMestoPoletanja : string = "";
  selektovanoMestoSletanja : string = "";
  selektovanaKompanija : string = "";
  selektovanaOpcija : string = "";
  selektovanaKlasa : string = "";
  lokacijeZaPrikaz : LokacijePresedanja[] = [];
  letovi : Let[] = [];
  cenaFilter : number = 0;
  filterLet : FilterLet = new FilterLet();
  opcije = [
    {name: "ONE_WAY", value: "ONE_WAY"},
    {name: "ROUND_TRIP", value: "ROUND_TRIP"}
  ]
  klase = [
    {name: "PRVA", value: "PRVA"},
    {name: "BIZNIS", value: "BIZNIS"},
    {name: "EKONOMSKA", value: "EKONOMSKA"}
  ]

  constructor(private avioServis : avioServis, private avionServis : avionServis, private letServis : letServis, private aeroServis : aerodromServis) { 
    this.avioServis.vratiSveKompanije().subscribe(
      data => {
        this.avioKompanije = data;
      }
    )
    for(let a of this.avioKompanije){
      a.prosecnaOcena = a.ocena / a.brojOcena;
    }

    this.letServis.vratiSveLetove().subscribe(
      data => {
        this.letovi = data;
      }
    )

    this.aeroServis.vratiSveAerodrome().subscribe(
      data => {
        this.mojiAerodromi = data;
      }
    )

  }

  ngOnInit() {
  }

  prikaziBrzeKarte(){
    this.avionServis.vratiSveBrzeKarte().subscribe(
      data => {
        this.brzeKarte = data;
        this.prikazBrzihKarata = true;
      }
    )
  }

  zatvori(){
    this.prikazBrzihKarata = false;
  }

  brzoRezervisi(k : AvionskaKarta){
    this.avionServis.brzoRezervisi(k).subscribe(
      data => {
        this.brzeKarte = data;
      }
    )
  }

  prikaziLokacije(l : Let){
    this.prikazLokacija = true;
    this.lokacijeZaPrikaz = l.lokacije;
  }

  pretrazi(){
    let provera = false;
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

    if(this.selektovanaOpcija == ""){
      $("#tipLeta").addClass('border-danger');
      provera = true;
    } else {
      $("#tipLeta").removeClass('border-danger');
    }

    if(this.selektovanaKlasa == ""){
      $("#klasaLeta").addClass('border-danger');
      provera = true;
    } else {
      $("#klasaLeta").removeClass('border-danger');
    }

    if(this.pretragaLet.brOsoba == 0){
      $("#brOsoba").addClass('border-danger');
      provera = true;
    } else {
      $("#brOsoba").removeClass('border-danger');
    }

    if(!provera) {
      this.pretragaLet.mestoPoletanja = this.selektovanoMestoPoletanja;
      this.pretragaLet.mestoSletanja = this.selektovanoMestoSletanja;
      this.pretragaLet.klasa = this.selektovanaKlasa;
      this.pretragaLet.tip = this.selektovanaOpcija;

      this.letServis.pretraziLet(this.pretragaLet).subscribe(
        data => {
          this.letovi = data;
          this.filtriranje = true;
          this.pretragaLet = new PretragaLet();
          this.selektovanaKlasa = "";
          this.selektovanaOpcija = "";
          this.selektovanoMestoPoletanja = "";
          this.selektovanoMestoSletanja = "";
        }
      )
    }
  }

  filtriraj(){
    let provera = false;
    if(this.selektovanaKompanija == ""){
      $("#kompanijaFilter").addClass('border-danger');
      provera = true;
    } else {
      $("#kompanijaFilter").removeClass('border-danger');
    }

    if(this.cenaFilter == 0){
      $("#cena").addClass('border-danger');
      provera = true;
    } else {
      $("#cena").removeClass('border-danger');
    }

    if(!provera){
      this.filterLet.avioKompanija = this.selektovanaKompanija;
      this.filterLet.cena = this.cenaFilter;

      this.letServis.filtrirajLet(this.filterLet).subscribe(
        data => {
          let niz : Let[] = [];
          for(let l of data){
            let znak = 0;
            for(let l1 of this.letovi){
              if(l.id == l1.id){
                znak++;
              }
            }
            if(znak != 0){
              niz.push(l);
            }
          }
          this.letovi = niz;
          this.filtriranje = false;
          this.filterLet = new FilterLet();
          this.cenaFilter = 0;
          this.selektovanaKompanija = "";
        }
      )
    }
  }

  zatvaranje(){
    this.prikazLokacija = false;
  }

}
