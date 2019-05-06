package ISA.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.KorisnikDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.UlogaKorisnika;
import ISA.project.service.EmailServis;
import ISA.project.service.KorisnikServis;


@RestController
@RequestMapping(value="/Korisnik")
public class KorisnikKontroler {
	
	@Autowired
	KorisnikServis servis;
	
	@Autowired
	EmailServis eservis;
	
	private Logger logger = LoggerFactory.getLogger(KorisnikKontroler.class);
	
	@RequestMapping(value="/registracija", method = RequestMethod.POST, consumes="application/json")
	public String registracija(@RequestBody KorisnikDTO korisnik) {
		
		String retVal = servis.registracija(korisnik);
		return retVal;
	}
	
	@RequestMapping(value="/logovanje", method = RequestMethod.POST, consumes="application/json")
	public String logovanje(@RequestBody KorisnikDTO korisnik, @Context HttpServletRequest request) {
		
		String retVal = servis.logovanje(korisnik);
		Korisnik pom = servis.vratiKorisnika(korisnik);
		request.getSession().setAttribute("ulogovan", pom);
		return retVal;
	}
	
	@RequestMapping(value="/odjava", method = RequestMethod.POST, consumes="application/json")
	public void odjava(@Context HttpServletRequest request) {
		
		request.getSession().invalidate();
		
	}
	
	@RequestMapping(value="/verifikacija/{mail}",
			method = RequestMethod.GET)
	public String verifikacija(@PathVariable String mail){
		
		Korisnik k = servis.vratiKorisnikaMail(mail);

		try {
			eservis.sendNotificaitionAsync(k);
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}

		return "success";
	}
	
	@RequestMapping(value="/aktiviraj/{mail}",
			method = RequestMethod.GET)
	public String activateUser(@PathVariable String mail){

		String s = servis.aktivacija(mail);
		return s;
		
	}

}
