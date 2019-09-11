import { Component, OnInit } from '@angular/core';
import { Let } from 'src/app/model/Let';
import { Router } from '@angular/router';
import { letServis } from 'src/app/service/letServis';
import { Vozilo } from 'src/app/model/Vozilo';
import * as $ from 'jquery';
import { voziloServis } from 'src/app/service/voziloServis';
import { korisnikServis } from 'src/app/service/korisnikServis';

@Component({
  selector: 'app-istorija-rezervacija',
  templateUrl: './istorija-rezervacija.component.html',
  styleUrls: ['./istorija-rezervacija.component.css']
})
export class IstorijaRezervacijaComponent implements OnInit {

  letovi : Let[] = [];
  porukaBrisanje : string = "";
  porukaBrisanje1 : string = "";
  vozila : Vozilo[] = [];
  opcije = [
    {name: "5", value: 5},
    {name: "4", value: 4},
    {name: "3", value: 3},
    {name: "2", value: 2},
    {name: "1", value: 1}
  ]
  opcije1 = [
    {name: "5", value: 5},
    {name: "4", value: 4},
    {name: "3", value: 3},
    {name: "2", value: 2},
    {name: "1", value: 1}
  
  ]
  selektovanaOpcija : number = 0;
  selektovanaOpcija1 : number = 0;
  porukaOcenjivanje : string = "";
  porukaOcenjivanje1 : string = "";

  prikazFormeZaOcenjivanjeLeta : boolean = false;
  prikazFormeZaOcenjivanjeVozila : boolean = false;

  letZaOcenjivanje : Let = new Let();
  voziloZaOcenjivanje : Vozilo = new Vozilo();

  idKorisnika : number;

  constructor(private router : Router, private letServis : letServis, private voziloServis : voziloServis, private korisnikServis : korisnikServis) {
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

    this.letServis.vratiRezezrvisaneLetove().subscribe(
      data => {
        this.letovi = data;
        for(let l of this.letovi){
          if(l.brojOcena != 0){
            l.prosecnaOcena = l.ocena / l.brojOcena;
          }
        }
      }
    )

    this.voziloServis.vratiRezezrvisanaVozila().subscribe(
      data => {
        this.vozila = data;
        for(let v of this.vozila){
          if(v.brOcena != 0){
            v.prosecnaOcena = v.ocene / v.brOcena;
          }
        }
      }
    )

   },

   error => {
    this.router.navigate(["glavnaNeregistrovani/prijava"]);
  }
  )
}

  ngOnInit() {
  }

  obrisi(l : Let)
  {
    let idLeta = l.id;
    this.letServis.otkaziRezervacijuLeta(l, idLeta).subscribe(
      data => {
        this.letovi = data;
        for(let l of this.letovi){
          if(l.brojOcena != 0){
            l.prosecnaOcena = l.ocena / l.brojOcena;
          }
        }
        this.porukaBrisanje = "";  
      },
      error => {
        this.porukaBrisanje = "Nije moguce otkazivanje rezervaciju leta!";
      }
    )
  }

  obrisiVozilo(v : Vozilo)
  {
    let idVozila = v.id;
    this.voziloServis.otkaziRezervacijuVozila(v, idVozila).subscribe(
      data => {
        this.vozila = data;
        for(let v of this.vozila){
          if(v.brOcena != 0){
            v.prosecnaOcena = v.ocene / v.brOcena;
          }
        }
        this.porukaBrisanje1 = "";  
      },
      error => {
        this.porukaBrisanje1 = "Nije moguce otkazivanje rezervaciju vozila!";
      }
    )
  }

  oceni(l : Let){
    this.prikazFormeZaOcenjivanjeLeta = true;
    this.letZaOcenjivanje = l;
  }

  oceni1(v : Vozilo){
    this.prikazFormeZaOcenjivanjeVozila = true;
    this.voziloZaOcenjivanje = v;
  }
 
  Oceni(){
    //this.letZaOcenjivanje.ocena = this.selektovanaOpcija;
    if(this.porukaOcenjivanje == ""){
      this.letServis.oceniLet(this.letZaOcenjivanje.id, this.idKorisnika, this.selektovanaOpcija).subscribe(
        data => {
              this.prikazFormeZaOcenjivanjeLeta = false;
              this.letovi = data;
              for(let l of this.letovi){
                if(l.brojOcena != 0){
                  l.prosecnaOcena = l.ocena / l.brojOcena;
                }
              }
              $("#ocenaLet").val("");
              this.letZaOcenjivanje = new Let();
              this.porukaOcenjivanje = "";
            },
            error => {
              this.porukaOcenjivanje = "Nije moguce oceniti let!";
            }
          )
        } else {
            this.prikazFormeZaOcenjivanjeLeta = false;
            this.porukaOcenjivanje = "";
          }

 }

  Oceni1(){
    //this.voziloZaOcenjivanje.ocene = this.selektovanaOpcija1;
    if(this.porukaOcenjivanje1 == ""){
      this.voziloServis.oceniVozilo(this.voziloZaOcenjivanje.id, this.idKorisnika, this.selektovanaOpcija1).subscribe(
        data => {
              this.prikazFormeZaOcenjivanjeVozila = false;
              this.vozila = data;
              for(let v of this.vozila){
                if(v.brOcena != 0){
                  v.prosecnaOcena = v.ocene / v.brOcena;
                }
              }
              $("#ocenaVozilo").val("");
              this.voziloZaOcenjivanje = new Vozilo();
              this.porukaOcenjivanje1 = "";
            },
            error => {
              this.porukaOcenjivanje1 = "Nije moguce oceniti vozilo!";
            }
          )
        } else {
            this.prikazFormeZaOcenjivanjeVozila = false;
            this.porukaOcenjivanje1 = "";
          }

  }

} 
