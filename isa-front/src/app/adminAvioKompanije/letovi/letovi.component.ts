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
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { TemplateRef } from '@angular/core';

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
  idKorisnika : number;
  modalRef: BsModalRef;
  letDetalji : Let = new Let();
  prikazFormeZaLet : boolean = true;

  constructor(private modalService: BsModalService, private avionServis : avionServis, private aeroServis : aerodromServis, private letServis : letServis, private korisnikServis : korisnikServis, private router : Router) {
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

        this.avionServis.vratiAvioneZaLet(this.idKorisnika).subscribe(
          data => {
            this.avioni = data;
          }
        )

        this.letServis.vratiLetove(this.idKorisnika).subscribe(
          data => {
            this.letovi = data;
            for(let l of this.letovi){
              if(l.brojOcena != 0){
                l.prosecnaOcena = l.ocena / l.brojOcena;
              }
            }
          }
        )

        this.aeroServis.vratiAerodrome(this.idKorisnika).subscribe(
          data => {
            this.mojiAerodromi = data;
            this.lokacijeAerodromi = data;
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

  dodaj(template: TemplateRef<any>){
    //this.prikazFormeZaDodavanje = true;
    this.modalRef = this.modalService.show(template);
  }

  zatvaranjeModala(){
    this.modalRef.hide();
    this.prikazDalje = false;
    this.noviLet = new Let();
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

  prikaziLokacije(l : Let, template: TemplateRef<any>){
    this.lokacijeZaPrikaz = l.lokacije;
    //this.prikazLokacija = true;
    this.modalRef = this.modalService.show(template);
  }

  zatvori(){
    //this.prikazLokacija = false;
    this.modalRef.hide();
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
          if(provera == false){
            this.porukaLokacije = "";
          }
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
      this.prikazFormeZaLet = false;
    }
  }

    dodavanjeLetaKraj(){
      this.letServis.dodajNoviLet(this.noviLet, this.idKorisnika).subscribe(
        data => {
          this.letovi = data;
          for(let l of this.letovi){
            if(l.brojOcena != 0){
              l.prosecnaOcena = l.ocena / l.brojOcena;
            }
          }
          this.prikazFormeZaDodavanje = false;
          this.modalRef.hide();
          this.prikazDalje = false;
          this.noviLet = new Let();
          this.selektovanaOpcija = "";
          this.prikazFormeZaLet = true;
          for(let l of this.lokacijeAerodromi){
            l.isChecked = false;
          }
          this.selektovaniAvion = "";
          this.selektovanoMestoPoletanja = "";
          this.selektovanoMestoSletanja = "";
          this.avionServis.vratiAvioneZaLet(this.idKorisnika).subscribe(
            data => {
              this.avioni = data;
            }
          )
        }
      )
    }
  

  ok(){
    //this.prikazBrzihKarata = false;
    this.modalRef.hide();
  }

  prikazKarata(l : Let, template: TemplateRef<any>){
    this.avionServis.vratiKarte(l).subscribe(
      data => {
        this.avioKarte = data;
        //this.prikazBrzihKarata = true;
        this.modalRef = this.modalService.show(template);
      }
    )
  }

  detalji(l : Let, template: TemplateRef<any>){
    this.letDetalji = l;
    this.modalRef = this.modalService.show(template);
  }

  zatvaranjeDetalja(){
    this.modalRef.hide();
  }
}
