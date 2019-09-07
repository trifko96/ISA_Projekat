package ISA.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AerodromDTO;
import ISA.project.model.Aerodrom;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.service.AerodromServis;
import ISA.project.service.AvioKompanijaServis;
import ISA.project.service.KorisnikServis;

@RestController
@RequestMapping(value="/Aerodrom")
public class AerodromKontroler {

	@Autowired
	AerodromServis servis;
	
	@Autowired
	AvioKompanijaServis avioServis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/dodajNovi/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<AerodromDTO>> dodajNovi(@RequestBody AerodromDTO adto, @PathVariable long id){
		Aerodrom a = servis.vratiAerodromPoImenu(adto);
		if(a == null) {
			//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
			//AvioKompanija avio = k.getAvioKompanija();
			Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
			AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
			List<AerodromDTO> pom = servis.dodajAerodrom(avio, adto);
			return new ResponseEntity<>(pom, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/dodajPostojeci/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<AerodromDTO>> dodajPostojeci(@RequestBody List<AerodromDTO> aerodromi, @PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		List<AerodromDTO> ret = servis.dodajPostojeci(avio, aerodromi);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiAerodrome/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AerodromDTO>> vratiAerodrome(@PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = k.getAvioKompanija();
		List<AerodromDTO> pom = servis.vratiAerodrome(avio);
		return new ResponseEntity<>(pom, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiSlobodneAerodrome/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AerodromDTO>> vratiSlobodneAerodrome(@PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = k.getAvioKompanija();
		List<AerodromDTO> slobodni = servis.vratiSlobodneAerodrome(avio);
		return new ResponseEntity<>(slobodni, HttpStatus.OK);
	}
	
	@RequestMapping(value="/obrisiAerodrom/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<AerodromDTO>> obrisiAerodrom(@RequestBody AerodromDTO adto, @PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		String s = servis.obrisiAerodrom(adto, avio);
		List<AerodromDTO> lista = servis.vratiAerodrome(avio);
		if(s == "ok")
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/vratiSveAerodrome", method = RequestMethod.GET)
	public ResponseEntity<List<AerodromDTO>> vratiSveAerodrome(){
		List<AerodromDTO> aerodromi = servis.vratiSveAerodrome();
		return new ResponseEntity<>(aerodromi, HttpStatus.OK);
	}
}
