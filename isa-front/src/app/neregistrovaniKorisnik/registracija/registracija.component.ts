import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Korisnik } from 'src/app/model/Korisnik';
import * as $ from 'jquery';
import { korisnikServis } from 'src/app/service/korisnikServis';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  korisnik : Korisnik = new Korisnik();
  ponovljenaLozinka : string = "";
  poruka : string = "";
  lozinke : string = "";
  porukaTelefon : string = "";

  constructor(private http : HttpClient, private korisnikServis :  korisnikServis) { }

  ngOnInit() {
  }

  submit(){

    let provera : boolean = false;

    if(this.korisnik.ime == ""){
      $("#name").addClass('border-danger');
      provera = true;
    } else {
      $("#name").removeClass('border-danger');
    }

    if(this.korisnik.prezime == ""){
      $("#surname").addClass('border-danger');
      provera = true;
    } else {
      $("#surname").removeClass('border-danger');
    }

    if(this.korisnik.email == ""){
      $("#email").addClass('border-danger');
      provera = true;
    } else {
      $("#email").removeClass('border-danger');
    }

    if(this.korisnik.brTelefona == ""){
      $("#brTel").addClass('border-danger');
      provera = true;
    } else {
      $("#brTel").removeClass('border-danger');
    }

    if(this.ponovljenaLozinka == ""){
      $("#password1").addClass('border-danger');
      provera = true;
    } else {
      $("#password1").removeClass('border-danger');
    }

    if(this.korisnik.lozinka == ""){
      $("#password").addClass('border-danger');
      provera = true;
    } else {
      $("#password").removeClass('border-danger');
    }

    if(this.korisnik.grad == ""){
      $("#grad").addClass('border-danger');
      provera = true;
    } else {
      $("#grad").removeClass('border-danger');
    }

    if(this.ponovljenaLozinka != this.korisnik.lozinka){
      provera = true;
      this.lozinke = "Lozinke se ne poklapaju!";
      $("#password").addClass('border-danger');
      $("#password1").addClass('border-danger');
    } else {
      this.lozinke = "";
      $("#password").removeClass('border-danger');
      $("#password1").removeClass('border-danger');
    }

    if(isNaN(+this.korisnik.brTelefona)){
      provera = true;
      this.porukaTelefon = "Dozvoljen je unos samo brojeva!";
      $("#brTel").addClass('border-danger');
    } else {
      this.porukaTelefon = "";
      $("#brTel").removeClass('border-danger');
    }

    if(!provera){
      this.korisnikServis.registracija(this.korisnik).subscribe(
        data => {
          this.poruka = "Dobicete mail za verifikaciju!";
          this.korisnikServis.verifikacija(data.email).subscribe(
            data => {
              this.korisnik = new Korisnik();
              this.ponovljenaLozinka = "";
              this.poruka = "";
            }
          );
        },

        error => {
          this.poruka = "Mail koji ste uneli vec postoji!"
        }
      );
    }
  }

}
