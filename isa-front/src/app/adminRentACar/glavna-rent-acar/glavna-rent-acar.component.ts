import { Component, OnInit } from '@angular/core';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/model/Korisnik';

@Component({
  selector: 'app-glavna-rent-acar',
  templateUrl: './glavna-rent-acar.component.html',
  styleUrls: ['./glavna-rent-acar.component.css']
})
export class GlavnaRentACarComponent implements OnInit {

  navLinks = [
    {path: "infoStranica", label: "Info stranica"},
    {path: "vozila", label: "Vozila"},
    {path: "filijale", label: "Filijale"},
    {path: "prihodiVozila", label: "Prihodi"},
    {path: "licniPodaci", label: "Licni podaci"}
  ]

  korisnik : Korisnik = new Korisnik();

  constructor(private korisnikServis : korisnikServis, private router : Router) {
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
