import { RezervacijaKarataDTO } from './RezervacijaKarataDTO';
import { Vozilo } from './Vozilo';
import { RezervacijaVozilo } from './RezervacijaVozilo';

export class Rezervacija{
    karte : RezervacijaKarataDTO[] = [];
    rezervacijaVozilo : RezervacijaVozilo = new RezervacijaVozilo();
}