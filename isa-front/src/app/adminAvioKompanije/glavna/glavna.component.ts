import { Component, OnInit } from '@angular/core';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

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
