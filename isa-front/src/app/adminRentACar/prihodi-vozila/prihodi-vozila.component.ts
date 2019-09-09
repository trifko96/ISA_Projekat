import { Component, OnInit } from '@angular/core';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prihodi-vozila',
  templateUrl: './prihodi-vozila.component.html',
  styleUrls: ['./prihodi-vozila.component.css']
})
export class PrihodiVozilaComponent implements OnInit {

  idKorisnika : number;

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
        this.idKorisnika = data.id;
      }
    )
   }

  ngOnInit() {
  }

}
