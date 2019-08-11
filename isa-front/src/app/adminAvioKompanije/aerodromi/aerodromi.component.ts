import { Component, OnInit } from '@angular/core';
import { Aerodrom } from 'src/app/model/Aerodrom';
import { aerodromServis } from 'src/app/service/aerodromServis';
import * as $ from 'jquery';

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

  constructor(private aerodromServis : aerodromServis) {

    this.aerodromServis.vratiAerodrome().subscribe(
      data => {
        this.aerodromi = data;
      }
    )

    this.aerodromServis.vratiSlobodne().subscribe(
      data => {
        this.postojeciAerodromi = data;
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
      this.aerodromServis.dodajAerodrom(this.noviAerodrom).subscribe(
        data => {
          this.aerodromServis.vratiAerodrome().subscribe(
            data => {
              this.aerodromi = data;
              this.prikazFormeZaDodavanjeNovog = false;
              this.noviAerodrom = new Aerodrom();
            }
          )
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
        }
        
      )
    }
  }

  dodajNovi(){
    this.prikazFormeZaDodavanjeNovog = true;
  }

  dodajPostojeci(){
    this.prikazFormeZaDodavanjePostojeceg = true;
    if(this.postojeciAerodromi.length == 0){
      this.imaPostojecih = false;
    }
  }

  Zatvori(){
    this.prikazFormeZaDodavanjePostojeceg = false;
  }

  DodajPostojeci(){
    for(let p of this.postojeciAerodromi){
      if(p.isChecked){
        this.aerodromiZaSlanje.push(p);
      }
    }
    this.aerodromServis.dodajPostojeci(this.aerodromiZaSlanje).subscribe(
      data => {
        this.aerodromServis.vratiAerodrome().subscribe(
          data => {
            this.aerodromi = data;
            this.prikazFormeZaDodavanjePostojeceg = false;
            this.aerodromiZaSlanje = [];
            this.aerodromServis.vratiSlobodne().subscribe(
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
    this.aerodromServis.obrisi(a).subscribe(
      data => {
        this.aerodromi = data;
        this.porukaBrisanje = "";
        this.aerodromServis.vratiSlobodne().subscribe(
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
      }
    )
  }

}
