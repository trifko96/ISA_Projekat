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
    {path: "rezervisanjeHotela", label: "Hoteli"},
    {path: "rezervisanjeVozila", label: "Vozila"},
    {path: "istorijaRezervacija", label: "Istorija rezervacija"},
    {path: "profil", label: "Profil"},
    {path: "zahtevi", label: "Moji prijatelji"}
  ]

  constructor(private korisnikServis : korisnikServis, private router : Router) { }

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
