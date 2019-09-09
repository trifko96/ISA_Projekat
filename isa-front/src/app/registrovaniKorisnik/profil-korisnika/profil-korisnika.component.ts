import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'src/app/model/Korisnik';
import { korisnikServis } from 'src/app/service/korisnikServis';
import * as $ from 'jquery';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profil-korisnika',
  templateUrl: './profil-korisnika.component.html',
  styleUrls: ['./profil-korisnika.component.css']
})
export class ProfilKorisnikaComponent implements OnInit {

  korisnik : Korisnik = new Korisnik();
  noviKorisnik : Korisnik = new Korisnik();
  prikazFormeZaIzmenu : boolean = false;
  poruka : string = "";
  poruka1 : string = "";
  dozvolaZaIzmenu : boolean = false;
  novaLozinka : string = "";
  ponovljenaLozinka : string = "";
  porukaLozinke : string = "";

  constructor(private korisnikServis : korisnikServis, private router : Router) { 
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
      }
    )
    this.korisnikServis.vratiTrenutnogKorisnika().subscribe(
      data => {
        this.korisnik = data;
        this.noviKorisnik.id = data.id;
      }
    )
  }

  ngOnInit() {
  }

  izmena(){
    //this.prikazFormeZaIzmenu = true;
    $("#imeKor").val(this.korisnik.ime);
    $("#prezKor").val(this.korisnik.prezime);
    $("#emailKor").val(this.korisnik.email);
    $("#gradKor").val(this.korisnik.grad);
    $("#telKor").val(this.korisnik.brTelefona);
    this.dozvolaZaIzmenu = true;
    this.poruka = "";
  }

  izmeni(){
    if(this.dozvolaZaIzmenu){
      let provera : boolean = false;
      this.noviKorisnik.ime = $("#imeKor").val();
      this.noviKorisnik.prezime = $("#prezKor").val();
      this.noviKorisnik.email = $("#emailKor").val();
      this.noviKorisnik.grad = $("#gradKor").val();
      this.noviKorisnik.brTelefona = $("#telKor").val();
      if(this.noviKorisnik.ime == ""){
        $("#imeKor").addClass('border-danger');
        provera = true;
      } else {
        $("#imeKor").removeClass('border-danger');
      }

      if(this.noviKorisnik.prezime == ""){
        $("#prezKor").addClass('border-danger');
        provera = true;
      } else {
        $("#prezKor").removeClass('border-danger');
      }

      if(this.noviKorisnik.email == ""){
        $("#emailKor").addClass('border-danger');
        provera = true;
      } else {
        $("#emailKor").removeClass('border-danger');
      }

      if(this.noviKorisnik.grad == ""){
        $("#gradKor").addClass('border-danger');
        provera = true;
      } else {
        $("#gradKor").removeClass('border-danger');
      }

      if(this.noviKorisnik.brTelefona == ""){
        $("#telKor").addClass('border-danger');
        provera = true;
      } else {
        $("#telKor").removeClass('border-danger');
      }

      if(isNaN(+this.noviKorisnik.brTelefona)){
        provera = true;
        this.poruka1 = "Dozvoljen unos samo brojeva!";
      } else {
        this.poruka1 = "";
      }
      if(!provera){
        this.korisnikServis.izmenaKorisnika(this.noviKorisnik).subscribe(
          data => {
            this.korisnik = data;
            //this.prikazFormeZaIzmenu = false;
            this.dozvolaZaIzmenu = false;
            $("#imeKor").val("");
            $("#prezKor").val("");
            $("#emailKor").val("");
            $("#gradKor").val("");
            $("#telKor").val("");
          },
          error => {
            this.poruka = "Uneta email adresa je zauzeta!";
          }
        )
      }
    } else {
      this.poruka = "Morate odabrati korisnika!";
    }
  }

  izmenaLozinke(){
    this.prikazFormeZaIzmenu = true;
  }

  izmeniLozinku(){
    let provera1 : boolean = false;
    if(this.novaLozinka == ""){
      $("#loz").addClass('border-danger');
      provera1 = true;
    } else {
      $("#loz").removeClass('border-danger');
    }

    if(this.ponovljenaLozinka == ""){
      $("#ponLoz").addClass('border-danger');
      provera1 = true;
    } else {
      $("#ponLoz").removeClass('border-danger');
    }

    if(this.novaLozinka != this.ponovljenaLozinka){
      this.porukaLozinke = "Lozinke se ne poklapaju!";
      provera1 = true;
    } else {
      this.porukaLozinke = "";
    }

    if(!provera1){
      this.korisnik.lozinka = this.novaLozinka;
      this.korisnikServis.izmenaLozinke(this.korisnik).subscribe(
        data => {
          this.korisnik = data;
          this.prikazFormeZaIzmenu = false;
        }
      )
    }
  }

}
