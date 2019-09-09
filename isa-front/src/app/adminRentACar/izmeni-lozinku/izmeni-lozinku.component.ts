import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'src/app/model/Korisnik';
import * as $ from 'jquery';
import { Router } from '@angular/router';
import { korisnikServis } from 'src/app/service/korisnikServis';

@Component({
  selector: 'app-izmeni-lozinku',
  templateUrl: './izmeni-lozinku.component.html',
  styleUrls: ['./izmeni-lozinku.component.css']
})
export class IzmeniLozinkuComponent implements OnInit {
  korisnik : Korisnik = new Korisnik();
  poruka : string = "";
  pomLozinka : string = "";
  trenutniKorisnik : Korisnik = new Korisnik();

  constructor(private korisnikServis : korisnikServis, private router : Router) {}
 
  ngOnInit() {
  }

  onClick(){
    let provera : boolean = false;

    if(this.korisnik.lozinka == ""){
      $("#loz").addClass('border-danger');
      provera = true;
    } else {
      $("#loz").removeClass('border-danger');
    }

    if(this.pomLozinka == ""){
      $("#loz1").addClass('border-danger');
      provera = true;
    } else {
      $("#loz1").removeClass('border-danger');
    }

    if(this.korisnik.lozinka != this.pomLozinka){
      $("#loz").addClass('border-danger');
      $("#loz1").addClass('border-danger');
      this.poruka = "Lozinke se ne poklapaju!";
      provera = true;
    } else {
      $("#loz").removeClass('border-danger');
      $("#loz1").removeClass('border-danger');
      this.poruka = "";
      provera = false;
    }

    if(!provera){
      this.korisnikServis.vratiTrenutnogKorisnika().subscribe(
        data => {
          this.trenutniKorisnik = data;
          this.trenutniKorisnik.lozinka = this.korisnik.lozinka;
          this.korisnikServis.promenaLozinke(this.trenutniKorisnik).subscribe(
            data => {
              this.router.navigate(["/glavnaRentACar"]);
            },
            error => {
              this.poruka = "Uneli ste vasu postojecu lozinku!";
            }
          )
        }
      );
    }
  }


}
