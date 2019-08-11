import { Component, OnInit } from '@angular/core';

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
    {path: "cenovnik", label: "Cenovnik"},
    {path: "prihodiVozila", label: "Prihodi"},
    {path: "licniPodaci", label: "Licni podaci"}
  ]

  constructor() { }

  ngOnInit() {
  }

}
