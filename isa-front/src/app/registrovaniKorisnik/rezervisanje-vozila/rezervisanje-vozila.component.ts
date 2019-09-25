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
import { DatumiPopust } from 'src/app/model/DatumiPopust';
import { RezervacijaVozilo } from 'src/app/model/RezervacijaVozilo';

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
  datumPopust : DatumiPopust = new DatumiPopust();
  rezervacijaVozilo : RezervacijaVozilo = new RezervacijaVozilo;

  
  selektovanaOpcija : number = 0;
  porukaOcenjivanje : string = "";

  prikazFormeZaOcenjivanjeServisa : boolean = false;

  servisZaOcenjivanje : RentCar = new RentCar();

  opcije1 = [
    {name: "MINI", value: "MINI"},
    {name: "LIMUZINA", value: "LIMUZINA"},
    {name: "KARAVAN", value: "KARAVAN"},
    {name: "KABRIOLET", value: "KABRIOLET"}
  
  ]

  opcije = [
    {name: "5", value: 5},
    {name: "4", value: 4},
    {name: "3", value: 3},
    {name: "2", value: 2},
    {name: "1", value: 1}
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
      },

      error => {
        this.router.navigate(["glavnaNeregistrovani/prijava"]);
      }
    )
    this.rentCarServis.vratiSveServise().subscribe(
      data => {
        this.servisi = data;
        for(let s of this.servisi){
          if(s.brojOcena > 0){
            s.prosecnaOcena = s.ocena / s.brojOcena;
          }
        }
      }
    )

    this.lokacijaServis.vratiSveLokacije().subscribe(
      data => {
        this.sveLokacije = data;
      }
    )

    

    this.rezervacija = this.rezervacijaServis.rezervacija;
    if(this.rezervacija.karte.length > 0){
      this.datumOd = this.rezervacija.karte[0].datumOdLeta;
      this.datumDo = this.rezervacija.karte[0].datumDoLeta;
    }
  }

  ngOnInit() {
  } 

  prikaziBrzaVozila(){
    this.datumPopust.pocetni = this.datumOd;
    if(this.datumDo == null){
      this.datumPopust.krajnji = new Date("2019-10-10");
    } else {
      this.datumPopust.krajnji = this.datumDo;
    }
    this.voziloServis.vratiBrzaVozila(this.datumPopust).subscribe(
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
      this.rezervacijaVozilo.emailKorisnika = this.korisnik.email;
      this.rezervacijaVozilo.vozilo = v;
      this.rezervacijaVozilo.datumRezervacijaOd = this.datumOd;
      if(this.datumDo == null){
        this.rezervacijaVozilo.datumRezervacijaDo = new Date("2019-10-10");
      } else {
        this.rezervacijaVozilo.datumRezervacijaDo = this.datumDo;
      }
      this.rezervacija.rezervacijaVozilo = this.rezervacijaVozilo;
      this.voziloServis.brzoRezervisiVozilo(this.rezervacija, this.idKorisnika).subscribe(
        data => {
          let indeks = this.brzaVozila.indexOf(v);
          this.brzaVozila.splice(indeks, 1);
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
          for(let s of this.servisi){
            if(s.brojOcena != 0){
              s.prosecnaOcena = s.ocena / s.brojOcena;
            }
          }
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
      this.pretragaVozilo.pocetni = this.datumOd;
    if(this.datumDo == null){
      this.pretragaVozilo.krajnji = new Date("2019-10-10");
    } else {
      this.pretragaVozilo.krajnji = this.datumDo;
    }
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
      this.rezervacijaVozilo.emailKorisnika = this.korisnik.email;
      this.rezervacijaVozilo.datumRezervacijaOd = this.pretragaVozilo.datumPreuzimanja;
      this.rezervacijaVozilo.datumRezervacijaDo = this.pretragaVozilo.datumVracanja;
      this.rezervacijaVozilo.mestoPreuzimanja = this.pretragaVozilo.mestoPreuzimanja;
      this.rezervacijaVozilo.mestoVracanja = this.pretragaVozilo.mestoVracanja;
      this.rezervacijaVozilo.vozilo = v;
      this.rezervacija.rezervacijaVozilo = this.rezervacijaVozilo;
      this.voziloServis.brzoRezervisiVozilo(this.rezervacija, this.idKorisnika).subscribe(
        data => {
          this.prikazTabeleVozila = false;
          this.rezervacijaServis.rezervacija = new Rezervacija();
          this.rentCarServis.vratiSveServise().subscribe(
            data => {
              this.servisi = data;
              for(let s of this.servisi){
                if(s.brojOcena != 0){
                  s.prosecnaOcena = s.ocena / s.brojOcena;
                }
              }
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

  oceni(s : RentCar){
    this.prikazFormeZaOcenjivanjeServisa = true;
    this.servisZaOcenjivanje = s;
  }

  Oceni(){
    //this.servisZaOcenjivanje.ocena = this.selektovanaOpcija;
    if(this.porukaOcenjivanje == ""){
      this.rentCarServis.oceniServis(this.servisZaOcenjivanje.id, this.idKorisnika, this.selektovanaOpcija).subscribe(
        data => {
          this.rentCarServis.vratiSveServise().subscribe(
            data => {
              this.prikazFormeZaOcenjivanjeServisa = false;
              this.servisi = data;
              for(let s of this.servisi){
                if(s.brojOcena != 0){
                  s.prosecnaOcena = s.ocena / s.brojOcena;
                }
              }
              $("#ocenaRent").val("");
              this.servisZaOcenjivanje = new RentCar();
              this.porukaOcenjivanje = "";
            }
          )
        },
        error => {
          this.porukaOcenjivanje = "Nije moguce oceniti rent a car servis!";
        }
      ) 
    } else {
      this.prikazFormeZaOcenjivanjeServisa = false;
      this.porukaOcenjivanje = "";
    }

 }

}
