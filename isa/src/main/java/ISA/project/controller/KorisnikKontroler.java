package ISA.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<KorisnikDTO> registracija(@RequestBody KorisnikDTO korisnik) {
		
		String retVal = servis.registracija(korisnik);
		if(retVal == "ok") {
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/logovanje", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KorisnikDTO> logovanje(@RequestBody KorisnikDTO korisnik, @Context HttpServletRequest request) {
		
		String retVal = servis.logovanje(korisnik);
		Korisnik pom = servis.vratiKorisnika(korisnik);
		if(pom != null) {
			request.getSession().setAttribute("ulogovan", pom);
			KorisnikDTO pomDTO = new KorisnikDTO(pom);
			pomDTO.setPoruka(retVal);
			return new ResponseEntity<>(pomDTO, HttpStatus.OK);
		} else {
			KorisnikDTO pomDTO = new KorisnikDTO();
			pomDTO.setPoruka(retVal);
			return new ResponseEntity<>(pomDTO, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/odjava", method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> odjava(@Context HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		request.getSession().invalidate();
		KorisnikDTO kDTO = new KorisnikDTO(k);
		return new ResponseEntity<>(kDTO, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/promenaLozinke", method = RequestMethod.POST)
	public ResponseEntity<KorisnikDTO> promenaLozinke(@RequestBody KorisnikDTO kdto){
		Korisnik k = servis.vratiKorisnika(kdto);
		String s = servis.promeniLozinku(kdto);
		if(!k.getLozinka().equals(s)) {
			k.setLozinka(s);
			k.setPrvoLogovanje(true);
			servis.sacuvaj(k);
			KorisnikDTO kd = new KorisnikDTO(k);
			kd.setPoruka("ok");
			return new ResponseEntity<>(kd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/izmenaLozinke", method = RequestMethod.POST)
	public ResponseEntity<KorisnikDTO> izmenaLozinke(@RequestBody KorisnikDTO kdto){
		Korisnik k = servis.vratiKorisnika(kdto);
		String s = servis.promeniLozinku(kdto);
		k.setLozinka(s);
		servis.sacuvaj(k);
		KorisnikDTO kd = new KorisnikDTO(k);
		return new ResponseEntity<>(kd, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/verifikacija/{mail}",
			method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> verifikacija(@PathVariable String mail){
		
		Korisnik k = servis.vratiKorisnikaMail(mail);
		KorisnikDTO kDTO = new KorisnikDTO(k);
		try {
			eservis.sendNotificaitionAsync(k);
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}

		return new ResponseEntity<>(kDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/aktiviraj/{mail}",
			method = RequestMethod.GET)
	public String activateUser(@PathVariable String mail){

		String s = servis.aktivacija(mail);
		return s;
		
	}
	
	@RequestMapping(value="/trenutniKorisnik",method = RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> trenutniKorisnik(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		KorisnikDTO kd = new KorisnikDTO(k);
		if(k != null) {
			return new ResponseEntity<>(kd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/izmenaKorisnika", method = RequestMethod.POST)
	public ResponseEntity<KorisnikDTO> izmenaKorisnika(@RequestBody KorisnikDTO kdto){
		Korisnik k = servis.vratiKorisnikaId(kdto);
		Korisnik k1 = servis.vratiKorisnika(kdto);
		if(k1 != null && k1.getEmail().equals(k.getEmail())) {
			k.setBrTelefona(kdto.getBrTelefona());
			k.setEmail(kdto.getEmail());
			k.setGrad(kdto.getGrad());
			k.setIme(kdto.getIme());
			k.setPrezime(kdto.getPrezime());
			servis.sacuvaj(k);
			return new ResponseEntity<>(new KorisnikDTO(k), HttpStatus.OK);
		}
		else {
			if(k1 == null) {
				k.setBrTelefona(kdto.getBrTelefona());
				k.setEmail(kdto.getEmail());
				k.setGrad(kdto.getGrad());
				k.setIme(kdto.getIme());
				k.setPrezime(kdto.getPrezime());
				servis.sacuvaj(k);
				return new ResponseEntity<>(new KorisnikDTO(k), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	

}
