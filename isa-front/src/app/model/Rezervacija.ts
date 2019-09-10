import { RezervacijaKarataDTO } from './RezervacijaKarataDTO';
import { Vozilo } from './Vozilo';

export class Rezervacija{
    karte : RezervacijaKarataDTO[] = [];
    vozilo : Vozilo = new Vozilo();
}