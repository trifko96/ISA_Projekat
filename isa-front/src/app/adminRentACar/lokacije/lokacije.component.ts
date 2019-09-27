import { Component, OnInit} from '@angular/core';
import { Lokacija } from 'src/app/model/Lokacija';
import { lokacijeServis } from 'src/app/service/lokacijeServis';
import * as $ from 'jquery';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';
import { TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-lokacije',
  templateUrl: './lokacije.component.html',
  styleUrls: ['./lokacije.component.css']
})
export class LokacijeComponent implements OnInit {

  modalRef: BsModalRef;

  lokacije : Lokacija[] = [];
  novaLokacija : Lokacija = new Lokacija();
  prikazFormeZaDodavanjeNovog : boolean = false;
  prikazFormeZaIzmenuPostojeceg : boolean = false;
  izmena : boolean = false;
  poruka : string = "";
  postojeceLokacije : Lokacija[] = [];
  prikazFormeZaDodavanjePostojeceg : boolean = false;
  lokacijeZaSlanje : Lokacija[] = [];
  imaPostojecih : boolean = true;
  porukaBrisanje = "";
  idKorisnika : number;

  constructor(private modalService: BsModalService, private lokacijeServis : lokacijeServis, private korisnikServis : korisnikServis, private router : Router) {
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
    this.lokacijeServis.vratiLokacije().subscribe(
      data => {
        this.lokacije = data;
      }
    )

    this.lokacijeServis.vratiSlobodne().subscribe(
      data => {
        this.postojeceLokacije = data;
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

  Dodaj(){
    let provera : boolean = false;

    if(this.novaLokacija.adresa == ""){
      provera = true;
      $("#adresaLok").addClass('border-danger');
    } else {
      $("#adresaLok").removeClass('border-danger');
    }

    if(!provera){
      this.lokacijeServis.dodajLokaciju(this.novaLokacija).subscribe(
        data => {
          this.lokacijeServis.vratiLokacije().subscribe(
            data => {
              this.lokacije = data;
              this.prikazFormeZaDodavanjeNovog = false;
              this.modalRef.hide();
              this.novaLokacija = new Lokacija();
            }
          )
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
        }
        
      )
    }
  }

  dodajNovi(template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template);
  }

  izmeni(l : Lokacija, template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template);
    this.novaLokacija.id = l.id;
    this.novaLokacija.adresa = l.adresa;
    this.izmena = true;
    this.poruka = ""; 

  }

  dodajPostojeci(){
    this.prikazFormeZaDodavanjePostojeceg = true;
    if(this.postojeceLokacije.length == 0){
      this.imaPostojecih = false;
    }
  }

  Zatvori(){
    this.prikazFormeZaDodavanjePostojeceg = false;
  }

  DodajPostojeci(){
    for(let p of this.postojeceLokacije){
      if(p.isChecked){
        this.lokacijeZaSlanje.push(p);
      }
    }
    this.lokacijeServis.dodajPostojecu(this.lokacijeZaSlanje).subscribe(
      data => {
        this.lokacijeServis.vratiLokacije().subscribe(
          data => {
            this.lokacije = data;
            this.prikazFormeZaDodavanjePostojeceg = false;
            this.lokacijeZaSlanje = [];
            this.lokacijeServis.vratiSlobodne().subscribe(
              data => {
                this.postojeceLokacije = data;
                if(this.postojeceLokacije.length != 0){
                  this.imaPostojecih = true;
                }
              }
            )
          }
        )
      }
    )
  }

  obrisi(l : Lokacija){
    this.lokacijeServis.obrisi(l).subscribe(
      data => {
        this.lokacije = data;
        this.porukaBrisanje = "";
        this.lokacijeServis.vratiSlobodne().subscribe(
          data => {
            this.postojeceLokacije = data;
            if(this.postojeceLokacije.length != 0){
              this.imaPostojecih = true;
            }
          }
        ) 
      },
      error => {
        this.porukaBrisanje = "Nije moguce brisanje lokacije!";
      }
    )
  }

  Izmena(){
    if(this.izmena == true){ 
      let provera = false;
      if(this.novaLokacija.adresa == ""){
        provera = true;
        $("#adresaLokacije").addClass('border-danger');
      } else {
        $("#adresaLokacije").removeClass('border-danger');
      }
      if(!provera){
        this.izmena = false;
        this.modalRef.hide();
        this.lokacijeServis.izmeniLokaciju(this.novaLokacija).subscribe(
          data => {
            this.lokacijeServis.vratiLokacije().subscribe(
              data => {
                this.lokacije = data;
                $("#adresaLokacije").val("");
                this.novaLokacija = new Lokacija();
                this.poruka = "";
              }
            )
          },
          error => {
            this.poruka = "Nije moguce izmeniti lokaciju, jer ima vozila na njoj!";
          }
        )
      }
    } else {
      this.poruka = "Odaberite lokaciju za izmenu!";
    }
  }

}
