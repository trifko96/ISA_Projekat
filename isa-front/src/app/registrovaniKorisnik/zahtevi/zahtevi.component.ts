import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'src/app/model/Korisnik';
import { zahteviServis } from 'src/app/service/zahteviServis';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  prijatelji : Korisnik[] = [];
  ponudjeni : Korisnik[] = [];
  korisniciZahtevi : Korisnik[] = [];
  ime : string = "";
  prikaz : boolean = false;

  constructor(private zahteviServis : zahteviServis) { 
    this.zahteviServis.vratiMojePrijatelje().subscribe(
      data => {
        this.prijatelji = data;
      }
    )

    this.zahteviServis.vratiMojeZahteve().subscribe(
      data => {
        this.korisniciZahtevi = data;
      }
    )
  }

  ngOnInit() {
  }

  obrisi(k : Korisnik){
    this.zahteviServis.obrisiPrijatelja(k.id).subscribe(
      data => {
        this.prijatelji = data;
      }
    )
  }

  pretrazi(){
    this.zahteviServis.vratiOstaleKorisnike(this.ime).subscribe(
      data => {
        this.ponudjeni = data;
        this.prikaz = true;
        this.ime = "";
      }
    )
  }

  dodaj(k : Korisnik){
    this.zahteviServis.dodajPrijatelja(k).subscribe(
      data => {
        let index = this.ponudjeni.indexOf(k);
        this.ponudjeni.splice(index, 1);
      }
    )
  }

  ok(){
    this.prikaz = false;
  }

  potvrdi(k : Korisnik){
    this.zahteviServis.prihvatiZahtev(k.id).subscribe(
      data => {
        this.prijatelji = data;
        this.zahteviServis.vratiMojeZahteve().subscribe(
          data => {
            this.korisniciZahtevi = data;
          }
        )
      }
    )
  }

  izbrisi(k : Korisnik){
    this.zahteviServis.izbrisiZahtev(k.id).subscribe(
      data => {
        this.prijatelji = data;
        this.zahteviServis.vratiMojeZahteve().subscribe(
          data => {
            this.korisniciZahtevi = data;
          }
        )
      }
    )
  }

}
