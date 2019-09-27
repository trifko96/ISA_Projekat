package ISA.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AvionDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.service.AvioKompanijaServis;
import ISA.project.service.AvionServis;
import ISA.project.service.KorisnikServis;

@RestController
@RequestMapping(value="/Avion")
public class AvionKontroler {

	@Autowired
	AvionServis servis;
	
	@Autowired 
	AvioKompanijaServis avioServis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@RequestMapping(value="/vratiAvione/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AvionDTO>> vratiAvione(@PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		List<AvionDTO> lista = servis.vratiAvione(avio);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiAvioneZaLet/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AvionDTO>> vratiAvioneZaLet(@PathVariable long id){
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		List<AvionDTO> lista = servis.vratiAvioneZaLet(avio);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajAvion/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<AvionDTO>> dodajAvion(@RequestBody AvionDTO adto, @PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		String s = servis.dodajAvion(adto, avio);
		List<AvionDTO> avioni = servis.vratiAvione(avio);
		if(s == "ok") {
			return new ResponseEntity<>(avioni, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/izmeniAvion", method = RequestMethod.POST)
	public ResponseEntity<List<AvionDTO>> izmeniAvion(@RequestBody AvionDTO adto, @Context HttpServletRequest request){
		String s = servis.izmeniAvion(adto);
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija av = avioServis.nadjiKompanijuPoKorisniku(k);
		List<AvionDTO> lista = servis.vratiAvione(av);
		if(s == "ok") {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/vratiAvion/{id}", method = RequestMethod.GET)
	public ResponseEntity<AvionDTO> vratiAvion(@PathVariable long id){
		AvionDTO avion = servis.vratiAvionPoLetu(id);
		return new ResponseEntity<>(avion, HttpStatus.OK);
	}
	
}
