import { Component, OnInit } from '@angular/core';
import { Vozilo } from 'src/app/model/Vozilo';
import { voziloServis } from 'src/app/service/voziloServis';
import * as $ from 'jquery';
import { Lokacija } from 'src/app/model/Lokacija';
import { lokacijeServis } from 'src/app/service/lokacijeServis';

@Component({
  selector: 'app-vozila',
  templateUrl: './vozila.component.html',
  styleUrls: ['./vozila.component.css']
})
export class VozilaComponent implements OnInit {

  vozila : Vozilo[] = [];
  novoVozilo : Vozilo = new Vozilo();
  prikazFormeZaDodavanjeNovog : boolean = false; 
  poruka : string = "";
  porukaBrisanje = "";
  opcije = [
    {name: "DA", value: "DA"},
    {name: "NE", value: "NE"}
  ]
  selektovanaOpcija : string = "";
  selektovanaLokacija : string = "";
  lokacije : Lokacija[] = [];

  constructor(private voziloServis : voziloServis, private lokacijeServis : lokacijeServis) { 

    this.voziloServis.vratiVozilo().subscribe(
      data => {
        this.vozila = data;
      }
    )

    this.lokacijeServis.vratiSveLokacije().subscribe(
      data => {
        this.lokacije = data;
      }
    )

  }
 
  ngOnInit() {
  }

  dodajNovi(){
    this.prikazFormeZaDodavanjeNovog = true;
  }

  Dodaj(){
    let provera : boolean = false;

    
    this.novoVozilo.naPopustu = this.selektovanaOpcija;
    this.novoVozilo.adresaLokacije = this.selektovanaLokacija;

    if(this.novoVozilo.naziv == ""){
      provera = true;
      $("#nazivVoz").addClass('border-danger');
    } else {
      $("#nazivVoz").removeClass('border-danger');
    }

    if(this.novoVozilo.marka == ""){
      provera = true;
      $("#markaVoz").addClass('border-danger');
    } else {
      $("#markaVoz").removeClass('border-danger');
    }

    if(this.novoVozilo.model == ""){
      provera = true;
      $("#modelVoz").addClass('border-danger');
    } else {
      $("#modelVoz").removeClass('border-danger');
    }

    if(this.novoVozilo.godinaProizvodnje == ""){
      provera = true;
      $("#proizVoz").addClass('border-danger');
    } else {
      $("#proizVoz").removeClass('border-danger');
    }

    if(this.novoVozilo.brSedista == 0){
      provera = true;
      $("#brSedVoz").addClass('border-danger');
    } else {
      $("#brSedVoz").removeClass('border-danger');
    }

    if(this.novoVozilo.naPopustu == ""){
      provera = true;
      $("#popustVozilo").addClass('border-danger');
    } else {
      $("#popustVozilo").removeClass('border-danger');
    }

    if(this.novoVozilo.adresaLokacije == ""){
      provera = true;
      $("#adresaLokacije").addClass('border-danger');
    } else {
      $("#adresaLokacije").removeClass('border-danger');
    }

    if(!provera){
      this.voziloServis.dodajVozilo(this.novoVozilo).subscribe(
        data => {
          this.voziloServis.vratiVozilo().subscribe(
            data => {
              this.vozila = data;
              this.prikazFormeZaDodavanjeNovog = false;
              this.novoVozilo = new Vozilo();
            }
          )
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
        }
        
      )
    }
  }

  obrisi(v : Vozilo){
    this.voziloServis.obrisi(v).subscribe(
      data => {
        this.vozila = data;
        this.porukaBrisanje = "";  
      },
      error => {
        this.porukaBrisanje = "Nije moguce brisanje vozila!";
      }
    )
  }

}
