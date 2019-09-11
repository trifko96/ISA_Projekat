import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'src/app/model/Korisnik';
import { zahteviServis } from 'src/app/service/zahteviServis';
import { Router } from '@angular/router';
import { korisnikServis } from 'src/app/service/korisnikServis';
import * as $ from 'jquery';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  prijatelji : Korisnik[] = [];
  ponudjeni : Korisnik[] = [];
  korisniciZahtevi : Korisnik[] = [];
  ime : string = "";
  prikaz : boolean = false;

  constructor(private zahteviServis : zahteviServis, private korisnikServis : korisnikServis, private router : Router) { 
    this.korisnikServis.vratiTrenutnogKorisnika().subscribe(
      data => {
        if(data.provera == "ADMINISTRATOR_HOTELA"){
          this.router.navigate([""]);
        } else if(data.provera == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["glavnaRentACar/infoStranica"]);
        } else if(data.provera == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["glavnaAdminSistema/adminSistema"]);
        } else if(data.provera == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate(["glavna/avioKompanija"]);
        }
      },

      error => {
        this.router.navigate(["glavnaNeregistrovani/prijava"]);
      }
    )
    this.zahteviServis.vratiMojePrijatelje().subscribe(
      data => {
        this.prijatelji = data;
      }
    )

    this.zahteviServis.vratiMojeZahteve().subscribe(
      data => {
        this.korisniciZahtevi = data;
      }
    )
  }

  ngOnInit() {
  }

  obrisi(k : Korisnik){
    this.zahteviServis.obrisiPrijatelja(k.id).subscribe(
      data => {
        this.prijatelji = data;
      }
    )
  }

  pretrazi(){
    if(this.ime == ""){
      $("#pretragaIme").addClass('border-danger');
    } else {
      $("#pretragaIme").removeClass('border-danger');
      this.zahteviServis.vratiOstaleKorisnike(this.ime).subscribe(
        data => {
          this.ponudjeni = data;
          this.prikaz = true;
          this.ime = "";
        }
      )
    }
  }

  dodaj(k : Korisnik){
    this.zahteviServis.dodajPrijatelja(k).subscribe(
      data => {
        let index = this.ponudjeni.indexOf(k);
        this.ponudjeni.splice(index, 1);
      }
    )
  }

  ok(){
    this.prikaz = false;
  }

  potvrdi(k : Korisnik){
    this.zahteviServis.prihvatiZahtev(k.id).subscribe(
      data => {
        this.prijatelji = data;
        this.zahteviServis.vratiMojeZahteve().subscribe(
          data => {
            this.korisniciZahtevi = data;
          }
        )
      }
    )
  }

  izbrisi(k : Korisnik){
    this.zahteviServis.izbrisiZahtev(k.id).subscribe(
      data => {
        this.prijatelji = data;
        this.zahteviServis.vratiMojeZahteve().subscribe(
          data => {
            this.korisniciZahtevi = data;
          }
        )
      }
    )
  }

}
