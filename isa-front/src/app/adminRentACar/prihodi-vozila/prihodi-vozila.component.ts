import { Component, OnInit } from '@angular/core';
import { rentCarServis } from 'src/app/service/rentCarServis';
import { Prihod } from 'src/app/model/Prihod';
import { DatumskiOpseg } from 'src/app/model/DatumskiOpseg';
import { Chart } from 'chart.js';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prihodi-vozila',
  templateUrl: './prihodi-vozila.component.html',
  styleUrls: ['./prihodi-vozila.component.css']
})
export class PrihodiVozilaComponent implements OnInit {

  datum1 : Date = new Date();
  datum2 : Date = new Date();
  prihod : Prihod = new Prihod();
  trenutniPrihod : number;
  trenutnaValuta : string = "";
  prikaz : boolean = false;
  datumskiOpseg : DatumskiOpseg = new DatumskiOpseg();
  prikazGrafika : boolean = false;
  chart = [];
  idKorisnika : number;

  constructor(private korisnikServis : korisnikServis, private router : Router, private rentCarServis : rentCarServis) {
    
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
      }
    )
   }

  ngOnInit() {
  }

  posalji() {
    this.datumskiOpseg.datum1 = this.datum1;
    this.datumskiOpseg.datum2 = this.datum2;
    this.rentCarServis.vratiPrihod(this.datumskiOpseg, this.idKorisnika).subscribe(
      data => {
        this.prihod = data;
        this.trenutniPrihod = this.prihod.iznos;
        this.trenutnaValuta = this.prihod.valuta;
        this.prikaz = true;
        this.datum1 = new Date();
        this.datum2 = new Date();
      }
    )
  }

  ok() {
    this.prikaz = false;
  }

  prikaziDnevnu() {
    var ctx = <HTMLCanvasElement> document.getElementById("chart");
    var c1 = ctx.getContext('2d');
    this.rentCarServis.vratiStatistikuPoDanu().subscribe(
      res => {
        this.chart = new Chart(c1, {
          type: 'bar',
          data: {
            labels: res.labele,
            datasets: [
              {
                data: res.vrednosti,
                label: 'prodate karte',
                borderColor: '#3cba9f',
                borderWidth: 1,
                fill : false
              }
            ]
          },
          options: {
            legend: {
              display: false
            },
            scales: {
              xAxes: [{
                display: true
              }],
              yAxes: [{
                display: true,
                ticks: {
                  beginAtZero:true
                }
              }],
            }
          }
        });
        this.prikazGrafika = true;
      }
    )
  }

  prikaziNedeljnu() {
    var ctx = <HTMLCanvasElement> document.getElementById("chart");
    var c1 = ctx.getContext('2d');
    this.rentCarServis.vratiStatistikuPoNedelji().subscribe(
      res => {
        this.chart = new Chart(c1, {
          type: 'bar',
          data: {
            labels: res.labele,
            datasets: [
              {
                data: res.vrednosti,
                label: 'prodate karte',
                borderColor: '#3cba9f',
                borderWidth: 1,
                fill : false
              }
            ]
          },
          options: {
            legend: {
              display: false
            },
            scales: {
              xAxes: [{
                display: true
              }],
              yAxes: [{
                display: true,
                ticks: {
                  beginAtZero:true
                }
              }],
            }
          }
        });
        this.prikazGrafika = true;
      }
    )
  } 

  prikaziGodisnju() {
    var ctx = <HTMLCanvasElement> document.getElementById("chart");
    var c1 = ctx.getContext('2d');
    this.rentCarServis.vratiStatistikuPoGodini().subscribe(
      res => {
        this.chart = new Chart(c1, {
          type: 'bar',
          data: {
            labels: res.labele,
            datasets: [
              {
                data: res.vrednosti,
                label: 'prodate karte',
                borderColor: '#3cba9f',
                borderWidth: 1,
                fill : false
              }
            ]
          },
          options: {
            legend: {
              display: false
            },
            scales: {
              xAxes: [{
                display: true
              }],
              yAxes: [{
                display: true,
                ticks: {
                  beginAtZero:true
                }
              }],
            }
          }
        });
        this.prikazGrafika = true;
      }
    )
  }

}
