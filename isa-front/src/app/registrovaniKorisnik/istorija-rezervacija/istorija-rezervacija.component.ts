import { Component, OnInit } from '@angular/core';
import { Let } from 'src/app/model/Let';
import { Router } from '@angular/router';
import { letServis } from 'src/app/service/letServis';

@Component({
  selector: 'app-istorija-rezervacija',
  templateUrl: './istorija-rezervacija.component.html',
  styleUrls: ['./istorija-rezervacija.component.css']
})
export class IstorijaRezervacijaComponent implements OnInit {

  letovi : Let[] = [];
  porukaBrisanje : string = "";
  //idLeta : number;

  constructor(private router : Router, private letServis : letServis) {
    
    this.letServis.vratiRezezrvisaneLetove().subscribe(
      data => {
        this.letovi = data;
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
        this.porukaBrisanje = "Nije moguce otkazivanje rezervacije leta!";
      }
    )
  }
 
}
