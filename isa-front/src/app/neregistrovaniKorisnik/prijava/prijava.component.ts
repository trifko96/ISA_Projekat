import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Korisnik } from 'src/app/model/Korisnik';
import * as $ from 'jquery';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prijava',
  templateUrl: './prijava.component.html',
  styleUrls: ['./prijava.component.css']
})
export class PrijavaComponent implements OnInit {

  korisnik : Korisnik = new Korisnik();
  poruka : string = "";

  constructor(private http : HttpClient, private korisnikServis : korisnikServis, private router : Router) { }

  ngOnInit() {
  }

  onClick(){
    let provera : boolean = false;

    if(this.korisnik.email == ""){
      $("#emailValue").addClass('border-danger');
      provera = true;
    } else {
      $("#emailValue").removeClass('border-danger');
    }

    if(this.korisnik.lozinka == ""){
      $("#passValue").addClass('border-danger');
      provera = true;
    } else {
      $("#passValue").removeClass('border-danger');
    }

    if(!provera){
      this.korisnikServis.logovanje(this.korisnik).subscribe(
        data => {
          if(data.poruka == "obican"){
            this.router.navigate(["/glavnaRegistrovani"]);
          } else if(data.poruka == "avio"){
            this.router.navigate(["/glavna"]);
          } else if(data.poruka == "prvo"){
            this.router.navigate(["/promenaLozinke"]);
          } else if(data.poruka == "rent"){
            this.router.navigate(["/glavnaRentACar"]);
          } else if(data.poruka == "sistem"){
            this.router.navigate(["/glavnaAdminSistema"]);
          } else if(data.poruka == "greska"){
            this.poruka = "Uneli ste neispravnu email adresu ili lozinku!";
          } else {
            this.poruka = "Morate verifikovati nalog!";
          }
        }
      );
    }
  }

}
