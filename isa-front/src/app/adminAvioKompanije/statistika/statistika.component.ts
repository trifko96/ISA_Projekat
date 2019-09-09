import { Component, OnInit } from '@angular/core';
import { avioServis } from 'src/app/service/avioServis';
import { Prihod } from 'src/app/model/Prihod';
import { DatumskiOpseg } from 'src/app/model/DatumskiOpseg';
import { Chart } from 'chart.js';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-statistika',
  templateUrl: './statistika.component.html',
  styleUrls: ['./statistika.component.css']
})
export class StatistikaComponent implements OnInit {

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

  constructor(private avioServis : avioServis, private korisnikServis : korisnikServis, private router : Router) { 
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
      }
    )
  }

  ngOnInit() {
  }

  posalji() {
    this.datumskiOpseg.datum1 = this.datum1;
    this.datumskiOpseg.datum2 = this.datum2;
    this.avioServis.vratiPrihod(this.datumskiOpseg, this.idKorisnika).subscribe(
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
    this.avioServis.vratiStatistikuPoDanu().subscribe(
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
    this.avioServis.vratiStatistikuPoNedelji().subscribe(
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
    this.avioServis.vratiStatistikuPoGodini().subscribe(
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
