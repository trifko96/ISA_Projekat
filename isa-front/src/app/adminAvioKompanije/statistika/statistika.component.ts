import { Component, OnInit } from '@angular/core';
import { avioServis } from 'src/app/service/avioServis';
import { Prihod } from 'src/app/model/Prihod';
import { DatumskiOpseg } from 'src/app/model/DatumskiOpseg';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-statistika',
  templateUrl: './statistika.component.html',
  styleUrls: ['./statistika.component.css']
})
export class StatistikaComponent implements OnInit {

  datumi : Date[] = [];
  prihod : Prihod = new Prihod();
  trenutniPrihod : number;
  trenutnaValuta : string = "";
  prikaz : boolean = false;
  datumskiOpseg : DatumskiOpseg = new DatumskiOpseg();
  prikazGrafika : boolean = false;
  chart = [];

  constructor(private avioServis : avioServis) { }

  ngOnInit() {
  }

  posalji() {
    this.datumskiOpseg.datum1 = this.datumi[0];
    this.datumskiOpseg.datum2 = this.datumi[1];
    this.avioServis.vratiPrihod(this.datumskiOpseg).subscribe(
      data => {
        this.prihod = data;
        this.trenutniPrihod = this.prihod.iznos;
        this.trenutnaValuta = this.prihod.valuta;
        this.prikaz = true;
      }
    )
  }

  ok() {
    this.prikaz = false;
  }

  zatvori() {
    this.prikazGrafika = false;
  }

  prikaziDnevnu() {
    var ctx = <HTMLCanvasElement> document.getElementById("chart");
    if(ctx === null){
      var ctx = <HTMLCanvasElement> document.getElementById("chart");
    }
    var c1 = ctx.getContext('2d');
    this.avioServis.vratiStatistikuPoDanu().subscribe(
      res => {
        this.chart = new Chart(c1, {
          type: 'line',
          data: {
            labels: res.labele,
            datasets: [
              {
                data: res.vrednosti,
                borderColor: '#3cba9f',
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
                display: true
              }],
            }
          }
        });
        this.prikazGrafika = true;
      }
    )
  }

  prikaziNedeljnu() {
    this.avioServis.vratiStatistikuPoNedelji().subscribe(
      res => {
        this.chart = new Chart('canvas', {
          type: 'line',
          data: {
            labels: res.labele,
            datasets: [
              {
                data: res.vrednosti,
                borderColor: '#3cba9f',
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
                display: true
              }],
            }
          }
        });
        this.prikazGrafika = true;
      }
    )
  }

  prikaziGodisnju() {
    this.avioServis.vratiStatistikuPoGodini().subscribe(
      res => {
        this.chart = new Chart('canvas', {
          type: 'line',
          data: {
            labels: res.labele,
            datasets: [
              {
                data: res.vrednosti,
                borderColor: '#3cba9f',
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
                display: true
              }],
            }
          }
        });
        this.prikazGrafika = true;
      }
    )
  }

}
