import { Component, OnInit } from '@angular/core';
import { Aerodrom } from 'src/app/model/Aerodrom';
import { aerodromServis } from 'src/app/service/aerodromServis';
import * as $ from 'jquery';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { TemplateRef } from '@angular/core';

@Component({
  selector: 'app-aerodromi',
  templateUrl: './aerodromi.component.html',
  styleUrls: ['./aerodromi.component.css']
})
export class AerodromiComponent implements OnInit {

  aerodromi : Aerodrom[] = [];
  noviAerodrom : Aerodrom = new Aerodrom();
  prikazFormeZaDodavanjeNovog : boolean = false;
  poruka : string = "";
  postojeciAerodromi : Aerodrom[] = [];
  prikazFormeZaDodavanjePostojeceg : boolean = false;
  aerodromiZaSlanje : Aerodrom[] = [];
  imaPostojecih : boolean = true;
  porukaBrisanje = "";
  idKorisnika : number;
  modalRef: BsModalRef;


  constructor(private modalService: BsModalService, private aerodromServis : aerodromServis, private korisnikServis : korisnikServis, private router : Router) {
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
        this.aerodromServis.vratiAerodrome(this.idKorisnika).subscribe(
          data => {
            this.aerodromi = data;
          }
        )
    
        this.aerodromServis.vratiSlobodne(this.idKorisnika).subscribe(
          data => {
            this.postojeciAerodromi = data;
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

    if(this.noviAerodrom.ime == ""){
      provera = true;
      $("#imeAero").addClass('border-danger');
    } else {
      $("#imeAero").removeClass('border-danger');
    }

    if(this.noviAerodrom.grad == ""){
      provera = true;
      $("#gradAero").addClass('border-danger');
    } else {
      $("#gradAero").removeClass('border-danger');
    }

    if(!provera){
      this.aerodromServis.dodajAerodrom(this.noviAerodrom, this.idKorisnika).subscribe(
        data => {
          this.aerodromServis.vratiAerodrome(this.idKorisnika).subscribe(
            data => {
              this.aerodromi = data;
              //this.prikazFormeZaDodavanjeNovog = false;
              this.modalRef.hide();
              this.noviAerodrom = new Aerodrom();
            }
          )
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
          setTimeout(() => {
            this.poruka = "";
          }, 2000);
        }
        
      )
    }
  }

  dodajNovi(template: TemplateRef<any>){
    //this.prikazFormeZaDodavanjeNovog = true;
    this.modalRef = this.modalService.show(template);
  }

  zatvaranjeModala(){
    this.modalRef.hide();
    this.noviAerodrom = new Aerodrom();
    this.poruka = "";
  }

  dodajPostojeci(template: TemplateRef<any>){
    //this.prikazFormeZaDodavanjePostojeceg = true;
    this.modalRef = this.modalService.show(template);
    if(this.postojeciAerodromi.length == 0){
      this.imaPostojecih = false;
    }
  }

  Zatvori(){
    //this.prikazFormeZaDodavanjePostojeceg = false;
    this.modalRef.hide();
  }

  DodajPostojeci(){
    for(let p of this.postojeciAerodromi){
      if(p.isChecked){
        this.aerodromiZaSlanje.push(p);
      }
    }
    this.aerodromServis.dodajPostojeci(this.aerodromiZaSlanje, this.idKorisnika).subscribe(
      data => {
        this.aerodromServis.vratiAerodrome(this.idKorisnika).subscribe(
          data => {
            this.aerodromi = data;
            //this.prikazFormeZaDodavanjePostojeceg = false;
            this.modalRef.hide();
            this.aerodromiZaSlanje = [];
            this.aerodromServis.vratiSlobodne(this.idKorisnika).subscribe(
              data => {
                this.postojeciAerodromi = data;
                if(this.postojeciAerodromi.length != 0){
                  this.imaPostojecih = true;
                }
              }
            )
          }
        )
      }
    )
  }

  obrisi(a : Aerodrom){
    this.aerodromServis.obrisi(a, this.idKorisnika).subscribe(
      data => {
        this.aerodromi = data;
        this.porukaBrisanje = "";
        this.aerodromServis.vratiSlobodne(this.idKorisnika).subscribe(
          data => {
            this.postojeciAerodromi = data;
            if(this.postojeciAerodromi.length != 0){
              this.imaPostojecih = true;
            }
          }
        )
      },
      error => {
        this.porukaBrisanje = "Nije moguce brisanje aerodroma!";
        setTimeout(() => {
          this.porukaBrisanje = "";
        }, 2000);
      }
    )
  }

}
