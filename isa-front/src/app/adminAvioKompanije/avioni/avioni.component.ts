import { Component, OnInit } from '@angular/core';
import { Avion } from 'src/app/model/Avion';
import { avionServis } from 'src/app/service/avionServis';
import { Segment } from 'src/app/model/Segment';
import * as $ from 'jquery';
import { Sediste } from 'src/app/model/Sediste';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-avioni',
  templateUrl: './avioni.component.html',
  styleUrls: ['./avioni.component.css']
})
export class AvioniComponent implements OnInit {

  avioni : Avion[] = [];
  idKorisnika : number;
  avion : Avion = new Avion();
  avionIzmena : Avion = new Avion();
  prikazFormeZaDodavanjeAviona : boolean = false;
  brRedPrva : number = 0;
  brKolPrva : number = 0;
  brRedBiznis : number = 0;
  brKolBiznis : number = 0;
  brRedEkonomska : number = 0;
  brKolEkonomska : number = 0;
  prvaKlasa : Segment = new Segment();
  biznisKlasa : Segment = new Segment();
  ekonomskaKlasa : Segment = new Segment();
  poruka : string = "";
  trenutnoIme : string = "";
  prikazFormeZaIzmenuAviona : boolean = false;
  prikazIzmeneSedista = false;
  prikazIzmeneKlase = false;
  sedistaZaIzmenu : Sediste[] = [];
  trenutnaKlasa : Segment = new Segment();
  trenutniAvion : Avion = new Avion();
  trenutnoSediste : Sediste = new Sediste();
  novoSediste : Sediste = new Sediste();
  poslednjeSediste : Sediste = new Sediste();
  imeKlase : string = "";
  porukaZaIzmenu : string = "";
  selektovanaOpcija : string = "";
  opcije = [
    {name: "OBRISANO", value: "OBRISANO"},
    {name: "SLOBODNO", value: "SLOBODNO"},
    {name: "BRZA_REZERVACIJA", value: "BRZA_REZERVACIJA"}
  ]

  constructor(private avionServis : avionServis, private korisnikServis : korisnikServis, private router : Router) {
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
        this.idKorisnika = data.id;

        this.avionServis.vratiAvione(this.idKorisnika).subscribe(
          data => {
            this.avioni = data;
          }
        )
      }
    )
      
  }

  ngOnInit() {
  }

  dodajAvion(){
    this.prikazFormeZaDodavanjeAviona = true;
  }

  dodaj(){
    let provera : boolean = false;

    if(this.avion.ime == ""){
      $("#imeAvio").addClass('border-danger');
      provera = true;
    } else {
      $("#imeAvio").removeClass('border-danger');
    }

    if(this.brKolBiznis <= 0){
      $("#brKolBiznis").addClass('border-danger');
      provera = true;
      this.poruka = "Morate uneti broj veci od 0!";
    } else {
      $("#brKolBiznis").removeClass('border-danger');
    }

    if(this.brKolEkonomska <= 0){
      $("#brKolEkonomska").addClass('border-danger');
      provera = true;
      this.poruka = "Morate uneti broj veci od 0!";
    } else {
      $("#brKolEkonomska").removeClass('border-danger');
    }

    if(this.brKolPrva <= 0){
      $("#brKolPrva").addClass('border-danger');
      provera = true;
      this.poruka = "Morate uneti broj veci od 0!";
    } else {
      $("#brKolPrva").removeClass('border-danger');
    }

    if(this.brRedBiznis <= 0){
      $("#brRedBiznis").addClass('border-danger');
      provera = true;
      this.poruka = "Morate uneti broj veci od 0!";
    } else {
      $("#brRedBiznis").removeClass('border-danger');
    }

    if(this.brRedEkonomska <= 0){
      $("#brRedEkonomska").addClass('border-danger');
      provera = true;
      this.poruka = "Morate uneti broj veci od 0!";
    } else {
      $("#brRedEkonomska").removeClass('border-danger');
    }

    if(this.brRedPrva <= 0){
      $("#brRedPrva").addClass('border-danger');
      provera = true;
      this.poruka = "Morate uneti broj veci od 0!";
    } else {
      $("#brRedPrva").removeClass('border-danger');
    }
    if(!provera){
      
      this.prvaKlasa.tip = "PRVA";
      this.prvaKlasa.brojRedova = this.brRedPrva;
      this.prvaKlasa.brojKolona = this.brKolPrva;
      this.biznisKlasa.tip = "BIZNIS";
      this.biznisKlasa.brojRedova = this.brRedBiznis;
      this.biznisKlasa.brojKolona = this.brKolBiznis;
      this.ekonomskaKlasa.tip = "EKONOMSKA";
      this.ekonomskaKlasa.brojRedova = this.brRedEkonomska;
      this.ekonomskaKlasa.brojKolona = this.brKolEkonomska;
      this.avion.klase.push(this.prvaKlasa);
      this.avion.klase.push(this.biznisKlasa);
      this.avion.klase.push(this.ekonomskaKlasa);
      this.avionServis.dodajAvion(this.avion, this.idKorisnika).subscribe(
        data => {
          this.avioni = data;
          this.avion.ime = "";
          this.brRedPrva = 0;
          this.brKolPrva = 0;
          this.brRedBiznis = 0;
          this.brKolBiznis = 0;
          this.brRedEkonomska = 0;
          this.brKolEkonomska = 0;
          this.prikazFormeZaDodavanjeAviona = false;
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
        }
      )
    }
  }

  izmeniAvion(a : Avion) {
    this.avionIzmena.id = a.id;
    this.trenutnoIme = a.ime;
    this.prikazFormeZaIzmenuAviona = true;
  }

  promena(){
    let provera : boolean = false;
    if(this.trenutnoIme == ""){
      provera = true;
      $("#trenutnoIme").addClass('border-danger');
    } else {
      $("#trenutnoIme").removeClass('border-danger');
    }
    if(!provera){
      this.avionIzmena.ime = this.trenutnoIme;
      
      this.avionServis.izmeniAvion(this.avionIzmena).subscribe(
        data => {
          this.prikazFormeZaIzmenuAviona = false;
          this.avioni = data;
          this.porukaZaIzmenu = "";
        }, 
        error => {
          this.porukaZaIzmenu = "Uneto ime vec postoji!";
        }
      )
    }
  }

  izmeniPrvuKlasu(a : Avion){
    this.prikazIzmeneKlase = true;
    this.trenutniAvion = a;
    this.imeKlase = "PRVA";
    for(let k of a.klase){
      if(k.tip == "PRVA"){
        this.trenutnaKlasa = k;
        this.sedistaZaIzmenu = k.listaSedista;
      }
    }
  }

  izmeniBiznisKlasu(a : Avion){
    this.prikazIzmeneKlase = true;
    this.trenutniAvion = a;
    this.imeKlase = "BIZNIS";
    for(let k of a.klase){
      if(k.tip == "BIZNIS"){
        this.trenutnaKlasa = k;
        this.sedistaZaIzmenu = k.listaSedista;
      }
    }
  }

  izmeniEkonomskuKlasu(a : Avion){
    this.prikazIzmeneKlase = true;
    this.trenutniAvion = a;
    this.imeKlase = "EKONOMSKA";
    for(let k of a.klase){
      if(k.tip == "EKONOMSKA"){
        this.trenutnaKlasa = k;
        this.sedistaZaIzmenu = k.listaSedista;
      }
    }
  }

  zatvori(){
    this.prikazIzmeneKlase = false;
  }

  izmeniSediste(s : Sediste){
    this.prikazIzmeneSedista = true;
    this.selektovanaOpcija = s.status;
    this.trenutnoSediste = s;
  }

  potvrdi(){
    this.trenutnoSediste.status = this.selektovanaOpcija;
    this.avionServis.izmeniSediste(this.trenutnoSediste).subscribe(
      data => {
        this.trenutniAvion = data;
        for(let k of this.trenutniAvion.klase){
          if(k.tip == this.imeKlase){
            this.trenutnaKlasa = k;
            this.sedistaZaIzmenu = k.listaSedista;
          }
        }
        this.prikazIzmeneSedista = false;
        this.avionServis.vratiAvione(this.idKorisnika).subscribe(
          data => {
            this.avioni = data;
          }
        )
      }
    )
  }

  dodajSediste(){
    let tmp : number = 0;
    this.poslednjeSediste = this.sedistaZaIzmenu[this.sedistaZaIzmenu.length - 1];
    tmp = this.poslednjeSediste.brKolone + 1;
    if(this.poslednjeSediste.brKolone == this.trenutnaKlasa.brojKolona){
      this.novoSediste.brKolone = 1;
      this.novoSediste.brReda = this.poslednjeSediste.brReda + 1;
      this.novoSediste.status = "SLOBODNO";
      this.novoSediste.natpis = this.poslednjeSediste.natpis + 1;
      this.novoSediste.pom = this.trenutnaKlasa.id;
      this.novoSediste.granica = false;
    } else {
      this.novoSediste.brKolone = this.poslednjeSediste.brKolone + 1;
      this.novoSediste.brReda = this.poslednjeSediste.brReda;
      this.novoSediste.status = "SLOBODNO";
      this.novoSediste.natpis = this.poslednjeSediste.natpis + 1;
      this.novoSediste.pom = this.trenutnaKlasa.id;
      if(tmp == this.trenutnaKlasa.brojKolona){
        this.novoSediste.granica = true;
      } else {
        this.novoSediste.granica = false;
      }
    }
    this.avionServis.dodajSediste(this.novoSediste).subscribe(
      data => {
        this.trenutniAvion = data;
        for(let k of this.trenutniAvion.klase){
          if(k.tip == this.imeKlase){
            this.trenutnaKlasa = k;
            this.sedistaZaIzmenu = k.listaSedista;
          }
        }
        this.avionServis.vratiAvione(this.idKorisnika).subscribe(
          data => {
            this.avioni = data;
          }
        )
      }
    )
  }

}
