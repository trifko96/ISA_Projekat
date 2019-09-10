import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AvioKompanijaComponent } from './adminAvioKompanije/avio-kompanija/avio-kompanija.component';
import { LetoviComponent } from './adminAvioKompanije/letovi/letovi.component';
import { AerodromiComponent } from './adminAvioKompanije/aerodromi/aerodromi.component';
import { AvioniComponent } from './adminAvioKompanije/avioni/avioni.component';
import { ProfilComponent } from './adminAvioKompanije/profil/profil.component';
import { GlavnaComponent } from './adminAvioKompanije/glavna/glavna.component';
import { GlavnaRentACarComponent } from './adminRentACar/glavna-rent-acar/glavna-rent-acar.component';
import { InfoStranicaComponent } from './adminRentACar/info-stranica/info-stranica.component';
import { VozilaComponent } from './adminRentACar/vozila/vozila.component';
import { CenovnikComponent } from './adminRentACar/cenovnik/cenovnik.component';
import { RezervisanaVozilaComponent } from './adminRentACar/rezervisana-vozila/rezervisana-vozila.component';
import { PrihodiVozilaComponent } from './adminRentACar/prihodi-vozila/prihodi-vozila.component';
import { LicniPodaciComponent } from './adminRentACar/licni-podaci/licni-podaci.component';
import { AvioKompanijeComponent } from './adminSistema/avio-kompanije/avio-kompanije.component';
import { HoteliComponent } from './adminSistema/hoteli/hoteli.component';
import { RentACarComponent } from './adminSistema/rent-acar/rent-acar.component';
import { AdminSistemaComponent } from './adminSistema/admin-sistema/admin-sistema.component';
import { GlavnaNeregistrovaniComponent } from './neregistrovaniKorisnik/glavna-neregistrovani/glavna-neregistrovani.component';
import { AvioNeregistrovaniComponent } from './neregistrovaniKorisnik/avio-neregistrovani/avio-neregistrovani.component';
import { HotelNeregistrovaniComponent } from './neregistrovaniKorisnik/hotel-neregistrovani/hotel-neregistrovani.component';
import { RentACarNeregistrovaniComponent } from './neregistrovaniKorisnik/rent-acar-neregistrovani/rent-acar-neregistrovani.component';
import { RegistracijaComponent } from './neregistrovaniKorisnik/registracija/registracija.component';
import { PrijavaComponent } from './neregistrovaniKorisnik/prijava/prijava.component';
import { GlavnaAdminSistemaComponent } from './adminSistema/glavna-admin-sistema/glavna-admin-sistema.component';
import { GlavnaRegistrovaniComponent } from './registrovaniKorisnik/glavna-registrovani/glavna-registrovani.component';
import { IstorijaRezervacijaComponent } from './registrovaniKorisnik/istorija-rezervacija/istorija-rezervacija.component';
import { RezervisanjeComponent } from './registrovaniKorisnik/rezervisanje/rezervisanje.component';
import { ProfilKorisnikaComponent } from './registrovaniKorisnik/profil-korisnika/profil-korisnika.component';
import { ZahteviComponent } from './registrovaniKorisnik/zahtevi/zahtevi.component';
import { FormsModule, FormBuilder } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PromenaLozinkeComponent } from './adminAvioKompanije/promena-lozinke/promena-lozinke.component';
import { aerodromServis } from './service/aerodromServis';
import { DatepickerModule, BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { TimepickerModule } from 'ngx-bootstrap/timepicker';
import { DatetimePopupModule } from 'ngx-bootstrap-datetime-popup';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { StatistikaComponent } from './adminAvioKompanije/statistika/statistika.component';
import { RezervisanjeHotelaComponent } from './registrovaniKorisnik/rezervisanje-hotela/rezervisanje-hotela.component';
import { RezervisanjeVozilaComponent } from './registrovaniKorisnik/rezervisanje-vozila/rezervisanje-vozila.component';
import { IzmeniLozinkuComponent } from './adminRentACar/izmeni-lozinku/izmeni-lozinku.component';
import { LokacijeComponent } from './adminRentACar/lokacije/lokacije.component';
import { rezervacijaServis } from './service/rezervacijaServis';
import { zahteviServis } from './service/zahteviServis';
import { avionServis } from './service/avionServis';
import { avioServis } from './service/avioServis';
import { korisnikServis } from './service/korisnikServis';
import { letServis } from './service/letServis';

@NgModule({
  declarations: [
    AppComponent,
    AvioKompanijaComponent,
    LetoviComponent,
    AerodromiComponent,
    AvioniComponent,
    ProfilComponent,
    GlavnaComponent,
    GlavnaRentACarComponent,
    InfoStranicaComponent,
    VozilaComponent,
    CenovnikComponent,
    RezervisanaVozilaComponent,
    PrihodiVozilaComponent,
    LicniPodaciComponent,
    AvioKompanijeComponent,
    HoteliComponent,
    RentACarComponent,
    AdminSistemaComponent,
    GlavnaNeregistrovaniComponent,
    AvioNeregistrovaniComponent,
    HotelNeregistrovaniComponent,
    RentACarNeregistrovaniComponent,
    RegistracijaComponent,
    PrijavaComponent,
    GlavnaAdminSistemaComponent,
    GlavnaRegistrovaniComponent,
    IstorijaRezervacijaComponent,
    RezervisanjeComponent,
    ProfilKorisnikaComponent,
    ZahteviComponent,
    PromenaLozinkeComponent,
    StatistikaComponent,
    RezervisanjeHotelaComponent,
    RezervisanjeVozilaComponent,
    IzmeniLozinkuComponent,
    LokacijeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [
    aerodromServis,
    avionServis,
    avioServis,
    korisnikServis,
    letServis,
    rezervacijaServis,
    zahteviServis,
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
