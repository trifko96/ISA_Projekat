package ISA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.KorisnikDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.UlogaKorisnika;
import ISA.project.repository.KorisnikRepozitorijum;

@Service
public class KorisnikServis {
	
	@Autowired
	KorisnikRepozitorijum repozitorijum;
	
	public String registracija(KorisnikDTO korisnik)
	{
		Korisnik k = new Korisnik(korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(), korisnik.getLozinka(), korisnik.getGrad(), korisnik.getBrTelefona());
		k.setUloga(UlogaKorisnika.OBICAN_KORISNIK);
		
		Korisnik k1 = repozitorijum.vratiKorisnikaPoEmailu(korisnik.getEmail());
		if(k1 != null)
			return "greska";
		else {
			repozitorijum.save(k);
			return "ok";
		}
	}

}
