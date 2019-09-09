import { NgModule, Component } from '@angular/core';
import { Route, RouterModule } from '@angular/router';
import { GlavnaNeregistrovaniComponent } from './neregistrovaniKorisnik/glavna-neregistrovani/glavna-neregistrovani.component';
import { AvioNeregistrovaniComponent } from './neregistrovaniKorisnik/avio-neregistrovani/avio-neregistrovani.component';
import { HotelNeregistrovaniComponent } from './neregistrovaniKorisnik/hotel-neregistrovani/hotel-neregistrovani.component';
import { RentACarNeregistrovaniComponent } from './neregistrovaniKorisnik/rent-acar-neregistrovani/rent-acar-neregistrovani.component';
import { PrijavaComponent } from './neregistrovaniKorisnik/prijava/prijava.component';
import { RegistracijaComponent } from './neregistrovaniKorisnik/registracija/registracija.component';
import { GlavnaRegistrovaniComponent } from './registrovaniKorisnik/glavna-registrovani/glavna-registrovani.component';
import { ProfilKorisnikaComponent } from './registrovaniKorisnik/profil-korisnika/profil-korisnika.component';
import { IstorijaRezervacijaComponent } from './registrovaniKorisnik/istorija-rezervacija/istorija-rezervacija.component';
import { RezervisanjeComponent } from './registrovaniKorisnik/rezervisanje/rezervisanje.component';
import { ZahteviComponent } from './registrovaniKorisnik/zahtevi/zahtevi.component';
import { GlavnaComponent } from './adminAvioKompanije/glavna/glavna.component';
import { AvioKompanijaComponent } from './adminAvioKompanije/avio-kompanija/avio-kompanija.component';
import { AerodromiComponent } from './adminAvioKompanije/aerodromi/aerodromi.component';
import { AvioniComponent } from './adminAvioKompanije/avioni/avioni.component';
import { LetoviComponent } from './adminAvioKompanije/letovi/letovi.component';
import { ProfilComponent } from './adminAvioKompanije/profil/profil.component';
import { GlavnaRentACarComponent } from './adminRentACar/glavna-rent-acar/glavna-rent-acar.component';
import { InfoStranicaComponent } from './adminRentACar/info-stranica/info-stranica.component';
import { CenovnikComponent } from './adminRentACar/cenovnik/cenovnik.component';
import { LicniPodaciComponent } from './adminRentACar/licni-podaci/licni-podaci.component';
import { PrihodiVozilaComponent } from './adminRentACar/prihodi-vozila/prihodi-vozila.component';
import { VozilaComponent } from './adminRentACar/vozila/vozila.component';
import { RezervisanaVozilaComponent } from './adminRentACar/rezervisana-vozila/rezervisana-vozila.component';
import { GlavnaAdminSistemaComponent } from './adminSistema/glavna-admin-sistema/glavna-admin-sistema.component';
import { AdminSistemaComponent } from './adminSistema/admin-sistema/admin-sistema.component';
import { AvioKompanijeComponent } from './adminSistema/avio-kompanije/avio-kompanije.component';
import { HoteliComponent } from './adminSistema/hoteli/hoteli.component';
import { RentACarComponent } from './adminSistema/rent-acar/rent-acar.component';
import { PromenaLozinkeComponent } from './adminAvioKompanije/promena-lozinke/promena-lozinke.component';
import { StatistikaComponent } from './adminAvioKompanije/statistika/statistika.component';
import { RezervisanjeHotelaComponent } from './registrovaniKorisnik/rezervisanje-hotela/rezervisanje-hotela.component';
import { RezervisanjeVozilaComponent } from './registrovaniKorisnik/rezervisanje-vozila/rezervisanje-vozila.component';
import { IzmeniLozinkuComponent } from './adminRentACar/izmeni-lozinku/izmeni-lozinku.component';
import { LokacijeComponent } from './adminRentACar/lokacije/lokacije.component';


const routes: Route[] = [
  {path: '', redirectTo: '/glavnaNeregistrovani/prijava', pathMatch: 'full'},
  {path: 'promenaLozinke', component: PromenaLozinkeComponent},
  {path: 'izmeniLozinku', component: IzmeniLozinkuComponent},
  {path: 'glavnaNeregistrovani', component: GlavnaNeregistrovaniComponent,
    children : [
      {path: '', redirectTo: 'prijava', pathMatch: 'full'},
      {path: 'avioNeregistrovani', component: AvioNeregistrovaniComponent},
      {path: 'hotelNeregistrovani', component: HotelNeregistrovaniComponent},
      {path: 'rentNeregistrovani', component: RentACarNeregistrovaniComponent},
      {path: 'prijava', component: PrijavaComponent},
      {path: 'registracija', component: RegistracijaComponent},
      {path: '**', redirectTo: 'avioNeregistrovani', pathMatch: 'full'},
    ]
  },
  {path: 'glavnaRegistrovani', component: GlavnaRegistrovaniComponent,
    children : [
      {path: '', redirectTo: 'profil', pathMatch: 'full'},
      {path: 'profil', component: ProfilKorisnikaComponent},
      {path: 'istorijaRezervacija', component: IstorijaRezervacijaComponent},
      {path: 'rezervisanje', component: RezervisanjeComponent},
      {path: 'rezervisanjeHotela', component: RezervisanjeHotelaComponent},
      {path: 'rezervisanjeVozila', component: RezervisanjeVozilaComponent},
      {path: 'zahtevi', component: ZahteviComponent},
      {path: '**', redirectTo: 'profil', pathMatch: 'full'},
    ]
  },
  {path: 'glavna', component: GlavnaComponent,
    children : [
      {path: '', redirectTo: 'avioKompanija', pathMatch: 'full'},
      {path: 'avioKompanija', component: AvioKompanijaComponent},
      {path: 'aerodromi', component: AerodromiComponent},
      {path: 'avioni', component: AvioniComponent},
      {path: 'letovi', component: LetoviComponent},
      {path: 'profil', component: ProfilComponent},
      {path: 'statistika', component: StatistikaComponent},
      {path: '**', redirectTo: 'avioKompanija', pathMatch: 'full'},
    ]
  },
  {path: 'glavnaRentACar', component: GlavnaRentACarComponent,
    children : [
      {path: '', redirectTo: 'infoStranica', pathMatch: 'full'},
      {path: 'infoStranica', component: InfoStranicaComponent},
      {path: 'filijale', component: LokacijeComponent},
      {path: 'licniPodaci', component: LicniPodaciComponent},
      {path: 'prihodiVozila', component: PrihodiVozilaComponent},
      {path: 'vozila', component: VozilaComponent},
      {path: '**', redirectTo: 'infoStranica', pathMatch: 'full'},
    ]
  },
  {path: 'glavnaAdminSistema', component: GlavnaAdminSistemaComponent,
    children : [
      {path: '', redirectTo: 'adminSistema', pathMatch: 'full'},
      {path: 'adminSistema', component: AdminSistemaComponent},
      {path: 'avioKompanije', component: AvioKompanijeComponent},
      {path: 'hoteli', component: HoteliComponent},
      {path: 'rentACar', component: RentACarComponent},
      {path: '**', redirectTo: 'adminSistema', pathMatch: 'full'},
    ]
  },
  {path: '**', redirectTo: '/glavnaNeregistrovani', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
