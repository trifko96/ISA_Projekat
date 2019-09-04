import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Korisnik } from '../model/Korisnik';

@Injectable({
    providedIn : 'root',
})
export class korisnikServis {

    constructor(private http : HttpClient) {}

    registracija(k : Korisnik){
        return this.http.post<Korisnik>("api/Korisnik/registracija", k);
    }

    verifikacija(mail : string){
        return this.http.get<Korisnik>("api/Korisnik/verifikacija/"+mail);
    }

    logovanje(k : Korisnik){
        return this.http.post<Korisnik>("api/Korisnik/logovanje", k);
    }

    odjava(){
        return this.http.get<Korisnik>("api/Korisnik/odjava");
    }

    promenaLozinke(k : Korisnik){
        return this.http.post<Korisnik>("api/Korisnik/promenaLozinke", k);
    }

    izmenaLozinke(k : Korisnik){
        return this.http.post<Korisnik>("api/Korisnik/izmenaLozinke", k);
    }

    vratiTrenutnogKorisnika(){
        return this.http.get<Korisnik>("api/Korisnik/trenutniKorisnik");
    }

    izmenaKorisnika(k : Korisnik){
        return this.http.post<Korisnik>("api/Korisnik/izmenaKorisnika",k);
    }

}