import { Component, OnInit } from '@angular/core';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-glavna-registrovani',
  templateUrl: './glavna-registrovani.component.html',
  styleUrls: ['./glavna-registrovani.component.css']
})
export class GlavnaRegistrovaniComponent implements OnInit {

  navLinks = [
    {path: "rezervisanje", label: "Avio kompanije"},
    {path: "rezervisanjeVozila", label: "Vozila"},
    {path: "istorijaRezervacija", label: "Istorija rezervacija"},
    {path: "profil", label: "Profil"},
    {path: "zahtevi", label: "Moji prijatelji"}
  ]

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
