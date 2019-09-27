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
import { Avion } from 'src/app/model/Avion';
import { Sediste } from 'src/app/model/Sediste';
import { Rezervacija } from 'src/app/model/Rezervacija';
import { RezervacijaKarataDTO } from 'src/app/model/RezervacijaKarataDTO';
import { Korisnik } from 'src/app/model/Korisnik';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { zahteviServis } from 'src/app/service/zahteviServis';
import { rezervacijaServis } from 'src/app/service/rezervacijaServis';
import { Router } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { TemplateRef } from '@angular/core';

@Component({
  selector: 'app-rezervisanje',
  templateUrl: './rezervisanje.component.html',
  styleUrls: ['./rezervisanje.component.css']
})
export class RezervisanjeComponent implements OnInit {

  modalRef: BsModalRef;


  avioKompanije : AvioKompanija[] = [];
  idKorisnika : number;
  idTrenutnogLeta : number;
  datumOdTrenutnogLeta : Date = new Date();
  datumDoTrenutnogLeta : Date = new Date();
  kompanije : AvioKompanija[] = [];
  brzeKarte : AvionskaKarta[] = [];
  prikazBrzihKarata : boolean = false;
  rezervisanje : boolean = false;
  filtriranje : boolean = false;
  prikazLokacija : boolean = false;
  prikazSedista : boolean = false;
  prikazPutnika : boolean = false;
  prikazPoruke : boolean = false;
  prikazPoruke1 : boolean = false;
  pretragaLet : PretragaLet = new PretragaLet();
  mojiAerodromi : Aerodrom[] = [];
  selektovanoMestoPoletanja : string = "";
  selektovanoMestoSletanja : string = "";
  selektovanaKompanija : string = "";
  selektovanaOpcija : string = "ONE_WAY";
  selektovanaKlasa : string = "";
  poruka : string = "";
  poruka1 : string = "";
  poruka2 : string = "";
  greskaPriBrzojRezervaciji = "";
  greskaPriRezervaciji = "";
  lokacijeZaPrikaz : LokacijePresedanja[] = [];
  rezervacija : Rezervacija = new Rezervacija();
  korisnici : Korisnik[] = [];
  letovi : Let[] = [];
  sedistaZaIzmenu : Sediste[] = [];
  cenaFilter : number = 0;
  filterLet : FilterLet = new FilterLet();
  gornji : boolean = true;
  donji : boolean = false;
  letDetalji : Let = new Let();

  selektovanaOpcija1;
  porukaOcenjivanje1 : string = "";

  prikazFormeZaOcenjivanjeKompanije : boolean = false;

  kompanijaZaOcenjivanje : AvioKompanija = new AvioKompanija();


  
  opcije1 = [
    {name: "", value: 0},
    {name: "5", value: 5},
    {name: "4", value: 4},
    {name: "3", value: 3},
    {name: "2", value: 2},
    {name: "1", value: 1}
  
  ]

  avion : Avion = new Avion();
  opcije = [
    {name: "ONE_WAY", value: "ONE_WAY"},
    {name: "ROUND_TRIP", value: "ROUND_TRIP"}
  ]
  klase = [
    {name: "PRVA", value: "PRVA"},
    {name: "BIZNIS", value: "BIZNIS"},
    {name: "EKONOMSKA", value: "EKONOMSKA"}
  ]

  constructor(private modalService: BsModalService, private avioServis : avioServis, private avionServis : avionServis, private letServis : letServis, private aeroServis : aerodromServis, private zahteviServis : zahteviServis, private rezervacijaServis : rezervacijaServis, private router : Router, private korisnikServis : korisnikServis, public fb: FormBuilder) { 
    
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
      },

      error => {
        this.router.navigate(["glavnaNeregistrovani/prijava"]);
      }
    )
    
    this.avioServis.vratiSveKompanije().subscribe(
      data => {
        this.avioKompanije = data;
        for(let a of this.avioKompanije){
          if(a.brojOcena != 0){
            a.prosecnaOcena = a.ocena / a.brojOcena;
          }
        }
      }
    )
    

    this.letServis.vratiSveLetove().subscribe(
      data => {
        this.letovi = data;
        for(let l of this.letovi){
          if(l.brojOcena != 0){
            l.prosecnaOcena = l.ocena / l.brojOcena;
          }
        }
      }
    )

    this.aeroServis.vratiSveAerodrome().subscribe(
      data => {
        this.mojiAerodromi = data;
      }
    )

    this.zahteviServis.vratiPrijateljeZaLet().subscribe(
      data => {
        this.korisnici = data;
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
    this.avionServis.brzoRezervisi(k, this.idKorisnika).subscribe(
      data => {
        this.brzeKarte = data;
        this.greskaPriBrzojRezervaciji = "";
      },

      error => {
        this.greskaPriBrzojRezervaciji = "Karta je u medjuvremenu rezervisana!";
      }
    )
  }

  prikaziLokacije(l : Let, template: TemplateRef<any>){
    //this.prikazLokacija = true;
    this.lokacijeZaPrikaz = l.lokacije;
    this.modalRef = this.modalService.show(template);
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
      this.modalRef.hide();
    }
  }

  detalji(l : Let, template: TemplateRef<any>){
    this.letDetalji = l;
    this.modalRef = this.modalService.show(template);
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
          this.modalRef.hide();
        }
      )
    }
  }

  zatvaranje(){
    this.modalRef.hide();
    //this.prikazLokacija = false;
  }

  zatvaranjeDetalja(){
    this.modalRef.hide();
  }

  nazadNaLetove(){
    this.rezervisanje = false;
  }

  rezervisi(l : Let){
    this.rezervisanje = true;
    this.filtriranje = false;
    this.idTrenutnogLeta = l.id;
    this.datumOdTrenutnogLeta = l.datumSletanja;
    this.datumDoTrenutnogLeta = l.datumPolaskaNazad;
    this.avionServis.vratiAvion(l.id).subscribe(
      data => {
        this.avion = data;
      }
    )
    this.letServis.vratiSveLetove().subscribe(
      data => {
        this.letovi = data;
        for(let l of this.letovi){
          if(l.brojOcena != 0){
            l.prosecnaOcena = l.ocena / l.brojOcena;
          }
        }
      }
    )
  }

  prikaziPrvuKlasu(){
    for(let k of this.avion.klase){
      if(k.tip == "PRVA"){
        this.sedistaZaIzmenu = k.listaSedista;
      }
    }
    this.prikazSedista = true;
  }

  prikaziBiznisKlasu(){
    for(let k of this.avion.klase){
      if(k.tip == "BIZNIS"){
        this.sedistaZaIzmenu = k.listaSedista;
      }
    }
    this.prikazSedista = true;
  }

  prikaziEkonomskuKlasu(){
    for(let k of this.avion.klase){
      if(k.tip == "EKONOMSKA"){
        this.sedistaZaIzmenu = k.listaSedista;
      }
    }
    this.prikazSedista = true;
  }

  nazadNaKlase(){
    this.rezervisanje = true;
    this.prikazSedista = false;
  }

  nazadNaSedista(){
    this.prikazPutnika = false;
    this.prikazSedista = true;
    this.rezervacija.karte = [];
  }

  dalje(){
    
    for(let s of this.sedistaZaIzmenu){
      if(s.status == "SELEKTOVANO"){
        let karta : RezervacijaKarataDTO = new RezervacijaKarataDTO();
        karta.idSedista = s.id;
        karta.brSedista = s.natpis;
        this.rezervacija.karte.push(karta);
      }
    }
    if(this.rezervacija.karte.length != 0){
      this.prikazSedista = false;
      this.prikazPutnika = true;
      this.poruka2 = "";
    } else {
      this.poruka2 = "Morate odabrati sedista!";
      setTimeout(() => {
        this.poruka2 = "";
      }, 2000);
    }
  }

  selektuj(s : Sediste){
    s.status = "SELEKTOVANO";
  }

  odselektuj(s : Sediste){
    s.status = "SLOBODNO";
  }


  pozovi(k : Korisnik, karta : RezervacijaKarataDTO){
    karta.ime = k.ime;
    karta.prezime = k.prezime;
    karta.email = k.email;
    karta.brTelefona = k.brTelefona;
    karta.idLeta = this.idTrenutnogLeta;
    karta.datumOdLeta = this.datumOdTrenutnogLeta;
    karta.datumDoLeta = this.datumDoTrenutnogLeta;
  }

  dalje1(){
    let provera : boolean = false;
    for(let r of this.rezervacija.karte){
      if(r.brPasosa == "" || r.brPasosa == "0"){
        provera = true;
        $("#pasosPutnika").addClass('border-danger');
      } else {
        $("#pasosPutnika").removeClass('border-danger');
      }
      if(r.brTelefona == "" || isNaN(+r.brTelefona)){
        provera = true;
        $("#telPutnika").addClass('border-danger');
      } else {
        $("#telPutnika").removeClass('border-danger');
      }
      if(r.email == ""){
        provera = true;
        $("#emailPutnika").addClass('border-danger');
      } else {
        $("#emailPutnika").removeClass('border-danger');
      }
      if(r.ime == ""){
        provera = true;
        $("#imePutnika").addClass('border-danger');
      } else {
        $("#imePutnika").addClass('border-danger');
      }
      if(r.prezime == ""){
        provera = true;
        $("#prezimePutnika").addClass('border-danger');
      } else {
        $("#prezimePutnika").addClass('border-danger');
      }
    }
    if(!provera){
      $("#telPutnika").removeClass('border-danger');
      $("#pasosPutnika").removeClass('border-danger');
      $("#prezimePutnika").removeClass('border-danger');
      $("#imePutnika").removeClass('border-danger');
      $("#emailPutnika").removeClass('border-danger');
      this.prikazPoruke = true;
      this.prikazPutnika = false;
      this.poruka1 = "";
      for(let k of this.rezervacija.karte){
        k.datumOdLeta = this.datumOdTrenutnogLeta;
        k.datumDoLeta = this.datumDoTrenutnogLeta;
      }
    } else {
      this.poruka1 = "Popunite sva polja!";
      setTimeout(() => {
        this.poruka1 = "";
      }, 2000);
    }
  }

  ne(){
    this.poruka = "Uspesno ste rezervisali let!";
    setTimeout(() => {
      this.poruka = "";
    }, 2000);
    this.avionServis.rezervisi(this.rezervacija, this.idKorisnika).subscribe(
      data => {
        this.poruka = "";
        this.rezervisanje = false;
        this.prikazPoruke = false;
        this.greskaPriRezervaciji = "";
      },

      error => {
        this.greskaPriRezervaciji = "Neko od sedista je u medjuvremenu rezervisano!";
      }
    )
  }

  da(){
    this.prikazPoruke1 = true;
    this.prikazPoruke = false;
  }

  smestaj(){
    this.rezervacijaServis.rezervacija = this.rezervacija;
    this.rezervisanje = false;
    this.router.navigate(["glavnaRegistrovani/rezervisanjeHotela"]);
  }

  vozilo(){
    this.rezervacijaServis.rezervacija = this.rezervacija;
    this.rezervisanje = false;
    this.router.navigate(["glavnaRegistrovani/rezervisanjeVozila"]);
  }

  pretraziLetove(){
    this.gornji = false;
    this.donji = true;
    this.filtriranje = false;
  }

  pretragaModal(template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template);
  }

  filtriranjeModal(template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template);
  }

  povratak(){
    this.gornji = true;
    this.donji = false;
    this.letServis.vratiSveLetove().subscribe(
      data => {
        this.letovi = data;
        for(let l of this.letovi){
          if(l.brojOcena != 0){
            l.prosecnaOcena = l.ocena / l.brojOcena;
          }
        }
      }
    )
  }

  oceni(a : AvioKompanija, template: TemplateRef<any> ){
    this.modalRef = this.modalService.show(template);
    this.kompanijaZaOcenjivanje = a;
  }

  Oceni(template: TemplateRef<any> ){
    //this.kompanijaZaOcenjivanje.ocena = this.selektovanaOpcija1;
    if(this.selektovanaOpcija1 == null){
      this.porukaOcenjivanje1 = "Izaberi ocenu!";
      setTimeout(() => {
        this.porukaOcenjivanje1 = "";
      }, 2000);
      return;
    }
    if(this.porukaOcenjivanje1 == ""){
      this.avioServis.oceniKompaniju(this.kompanijaZaOcenjivanje.id, this.idKorisnika, this.selektovanaOpcija1.value).subscribe(
        data => {
          this.avioServis.vratiSveKompanije().subscribe(
            data => {
              this.prikazFormeZaOcenjivanjeKompanije = false;
              this.avioKompanije = data;
              for(let a of this.avioKompanije){
                if(a.brojOcena != 0){
                  a.prosecnaOcena = a.ocena / a.brojOcena;
                }
              }
              $("#ocenaAvio").val("");
              this.kompanijaZaOcenjivanje = new AvioKompanija();
              this.porukaOcenjivanje1 = "";
            }
          )
          this.modalRef.hide();
        },
        error => {
          this.porukaOcenjivanje1 = "Vec ste ocenili ovu avio kompaniju!";
          setTimeout(() => {
            this.porukaOcenjivanje1 = "";
          }, 2000);
        }
      ) 
    } else {
      this.prikazFormeZaOcenjivanjeKompanije = false;
      this.porukaOcenjivanje1 = "";
    }

 }

}
