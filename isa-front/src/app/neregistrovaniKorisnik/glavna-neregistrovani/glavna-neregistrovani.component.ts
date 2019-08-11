import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-glavna-neregistrovani',
  templateUrl: './glavna-neregistrovani.component.html',
  styleUrls: ['./glavna-neregistrovani.component.css']
})
export class GlavnaNeregistrovaniComponent implements OnInit {

  navLinks = [
    {path: "avioNeregistrovani", label: "Avio kompanije"},
    {path: "hotelNeregistrovani", label: "Hoteli"},
    {path: "rentNeregistrovani", label: "Rent A Car servisi"},
    {path: "prijava", label: "Prijava"},
    {path: "registracija", label: "Registracija"}
  ]

  constructor() { }

  ngOnInit() {
  }

}
