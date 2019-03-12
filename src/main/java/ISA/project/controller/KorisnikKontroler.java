package ISA.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.KorisnikDTO;
import ISA.project.model.Korisnik;
import ISA.project.service.KorisnikServis;


@RestController
@RequestMapping(value="/Korisnik")
public class KorisnikKontroler {
	
	@Autowired
	KorisnikServis servis;
	
	@RequestMapping(value="/registracija", method = RequestMethod.POST, consumes="application/json")
	public String registracija(@RequestBody KorisnikDTO korisnik) {
		
		String retVal = servis.registracija(korisnik);
		return retVal;
	}

}
