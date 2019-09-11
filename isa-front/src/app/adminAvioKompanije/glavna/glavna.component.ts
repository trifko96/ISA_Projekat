import { Component, OnInit } from '@angular/core';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/model/Korisnik';

@Component({
  selector: 'app-glavna',
  templateUrl: './glavna.component.html',
  styleUrls: ['./glavna.component.css']
})
export class GlavnaComponent implements OnInit {

  navLinks = [
    {path: "avioKompanija", label: "Info stranica"},
    {path: "aerodromi", label: "Aerodromi"},
    {path: "avioni", label: "Avioni"},
    {path: "letovi", label: "Letovi"},
    {path: "profil", label: "Profil"},
    {path: "statistika", label: "Statistika"}
  ]
  korisnik : Korisnik = new Korisnik();

  constructor(private korisnikServis : korisnikServis, private router : Router) {
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
      },

      error => {
        this.router.navigate(["glavnaNeregistrovani/prijava"]);
      }
    )
  }

  ngOnInit() {
    
  }

  logout(){
    this.korisnikServis.odjava().subscribe(
      data => {
        this.router.navigate(["/glavnaNeregistrovani"]);
      }
    )
  }

}
