import { Component, OnInit } from '@angular/core';
import { korisnikServis } from 'src/app/service/korisnikServis';
import { Router } from '@angular/router';

@Component({
  selector: 'app-glavna-rent-acar',
  templateUrl: './glavna-rent-acar.component.html',
  styleUrls: ['./glavna-rent-acar.component.css']
})
export class GlavnaRentACarComponent implements OnInit {

  navLinks = [
    {path: "infoStranica", label: "Info stranica"},
    {path: "vozila", label: "Vozila"},
    {path: "rezervisanaVozila", label: "Rezervisana vozila"},
    {path: "filijale", label: "Filijale"},
    {path: "prihodiVozila", label: "Prihodi"},
    {path: "licniPodaci", label: "Licni podaci"}
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
