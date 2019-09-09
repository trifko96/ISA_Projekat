import { Component, OnInit } from '@angular/core';
import { AvioKompanija } from 'src/app/model/AvioKompanija';
import { avioServis } from 'src/app/service/avioServis';
import { HttpClient } from '@angular/common/http';
declare var ol: any;
import * as $ from 'jquery';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-avio-kompanija',
  templateUrl: './avio-kompanija.component.html',
  styleUrls: ['./avio-kompanija.component.css']
})
export class AvioKompanijaComponent implements OnInit {

  avioKompanija : AvioKompanija = new AvioKompanija();
  avioKompanijaIzmena : AvioKompanija = new AvioKompanija();
  prosecnaOcena : number = 0;
  map : any;
  izmena : boolean = false;
  pomKord1 : number = 0;
  pomKord2 : number = 0;
  pomAdresa : string = "";
  poruka : string = "";
  idKorisnika : number;


  constructor(private avioServis : avioServis, private http : HttpClient, private korisnikServis : korisnikServis, private router : Router) { 
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

        this.avioServis.vratiKompaniju(this.idKorisnika).subscribe(
          data => {
            this.avioKompanija = data;
            if(data.brojOcena != 0){
              this.prosecnaOcena = data.ocena/data.brojOcena;
            }
            this.setMarker([data.koordinata2,data.koordinata1]);
            this.map.getView().setCenter(ol.proj.fromLonLat([data.koordinata2,data.koordinata1]));
          }
        )
      }
    )
    
  }

  ngOnInit() {
    this.initilizeMap(this.avioKompanija.koordinata2,this.avioKompanija.koordinata1);
  }

  initilizeMap(cord1, cord2){
    this.map = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.OSM()
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([cord1,cord2]),
        zoom: 10
      })
    });

    this.map.on('click', (event) => {
  		event.preventDefault();
  	
      var coord = ol.proj.toLonLat(event.coordinate); 
      this.setMarker(coord);

      this.http.get<any>('map/reverse?format=json&lon=' + coord[0] + '&lat=' + coord[1]).subscribe(
        data =>{
          if(this.izmena == true){
            $("#adresaKom").val(data.display_name);
            this.pomKord1 = data.lat;
            this.pomKord2 = data.lon;
          }
        })
  	});
  }

  setMarker(coords){

    var layersToRemove = [];
    this.map.getLayers().forEach(function (layer) {
        if (layer.get('name') != undefined && layer.get('name') === 'markerVector') {
            layersToRemove.push(layer);
        }
    });

    var len = layersToRemove.length;
    for(var i = 0; i < len; i++) {
        this.map.removeLayer(layersToRemove[i]);
    }

    var marker = new ol.Feature({
        geometry: new ol.geom.Point(ol.proj.transform(coords, 'EPSG:4326', 'EPSG:3857')),
    });

    var markers = new ol.source.Vector({
        features: [marker]
    });

    var markerVectorLayer = new ol.layer.Vector({
        source: markers,
    });
    this.map.addLayer(markerVectorLayer);
    markerVectorLayer.set('name','markerVector');
  }

  Izmena(){
    if(this.izmena == true){
      this.izmena = false;
      this.avioKompanijaIzmena.naziv = $("#nazivKom").val();
      this.avioKompanijaIzmena.adresa = $("#adresaKom").val();
      this.avioKompanijaIzmena.opis = $("#opisKom").val();
      this.avioKompanijaIzmena.infoPrtljag = $("#prtljagKom").val();
      if(this.pomKord1 != 0){
        this.avioKompanijaIzmena.koordinata1 = this.pomKord1;
        this.avioKompanijaIzmena.koordinata2 = this.pomKord2;
        this.setMarker([this.pomKord1,this.pomKord2]);
      } else {
        this.avioKompanijaIzmena.koordinata1 = this.avioKompanija.koordinata1;
        this.avioKompanijaIzmena.koordinata2 = this.avioKompanija.koordinata2;
      }
      this.avioKompanijaIzmena.id = this.avioKompanija.id;
      this.avioKompanijaIzmena.brojOcena = this.avioKompanija.brojOcena;
      this.avioKompanijaIzmena.ocena = this.avioKompanija.ocena;
      this.avioKompanijaIzmena.prihod = this.avioKompanija.prihod;
      this.avioServis.izmeniKompaniju(this.avioKompanijaIzmena).subscribe(
        data => {
          this.avioServis.vratiKompaniju(this.idKorisnika).subscribe(
            data => {
              this.avioKompanija = data;
              $("#nazivKom").val("");
              $("#adresaKom").val("");
              $("#opisKom").val("");
              $("#prtljagKom").val("");
              this.poruka = "";
            }
          )
        },
        error => {
          this.poruka = "Uneto ime vec postoji!";
        }
      )
    } else {
      this.poruka = "Odaberite kompaniju za izmenu!";
    }
  }

  onClick(){
    $("#nazivKom").val(this.avioKompanija.naziv);
    $("#adresaKom").val(this.avioKompanija.adresa);
    $("#opisKom").val(this.avioKompanija.opis);
    $("#prtljagKom").val(this.avioKompanija.infoPrtljag);
    this.setMarker([this.avioKompanija.koordinata2,this.avioKompanija.koordinata1]);
    this.map.getView().setCenter(ol.proj.fromLonLat([this.avioKompanija.koordinata2,this.avioKompanija.koordinata1]));
    this.izmena = true;
    this.poruka = ""; 
  }

}
