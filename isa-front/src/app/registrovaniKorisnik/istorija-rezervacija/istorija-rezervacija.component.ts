import { Component, OnInit } from '@angular/core';
import { Let } from 'src/app/model/Let';
import { Router } from '@angular/router';
import { letServis } from 'src/app/service/letServis';
import { Vozilo } from 'src/app/model/Vozilo';
import { voziloServis } from 'src/app/service/voziloServis';

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
  //idLeta : number;

  constructor(private router : Router, private letServis : letServis, private voziloServis : voziloServis) {
    
    this.letServis.vratiRezezrvisaneLetove().subscribe(
      data => {
        this.letovi = data;
      }
    )

    this.voziloServis.vratiRezezrvisanaVozila().subscribe(
      data => {
        this.vozila = data;
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
        this.porukaBrisanje1 = "";  
      },
      error => {
        this.porukaBrisanje1 = "Nije moguce otkazivanje rezervaciju vozila!";
      }
    )
  }
 
} 
