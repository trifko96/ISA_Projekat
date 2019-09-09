package ISA.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.LokacijaDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.Lokacija;
import ISA.project.model.RentACar;
import ISA.project.service.LokacijaServis;
import ISA.project.service.RentACarServis;


@RestController
@RequestMapping(value="/Lokacija")
public class LokacijaKontroler {

	@Autowired
	LokacijaServis servis;
	
	@Autowired
	RentACarServis carServis;
	
	@RequestMapping(value = "/dodajNovu", method = RequestMethod.POST)
	public ResponseEntity<List<LokacijaDTO>> dodajNovi(@RequestBody LokacijaDTO adto, @Context HttpServletRequest request){
		Lokacija a = servis.vratiLokacijuPoAdresi(adto);
		if(a == null) {
			Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
			//AvioKompanija avio = k.getAvioKompanija();
			RentACar avio = carServis.nadjiRentACarPoKorisniku(k);
			List<LokacijaDTO> pom = servis.dodajLokaciju(avio, adto);
			return new ResponseEntity<>(pom, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value="/vratiLokacije", method = RequestMethod.GET)
	public ResponseEntity<List<LokacijaDTO>> vratiAerodrome(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar avio = k.getRentACar();
		List<LokacijaDTO> pom = servis.vratiLokacije(avio);
		return new ResponseEntity<>(pom, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiSlobodneLokacije", method = RequestMethod.GET)
	public ResponseEntity<List<LokacijaDTO>> vratiSlobodneAerodrome(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar avio = k.getRentACar();
		List<LokacijaDTO> slobodni = servis.vratiSlobodneLokacije(avio);
		return new ResponseEntity<>(slobodni, HttpStatus.OK);
	}
	
	@RequestMapping(value="/obrisiLokaciju", method = RequestMethod.POST)
	public ResponseEntity<List<LokacijaDTO>> obrisiAerodrom(@RequestBody LokacijaDTO adto, @Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar avio = carServis.nadjiRentACarPoKorisniku(k);
		String s = servis.obrisiLokaciju(adto, avio);
		List<LokacijaDTO> lista = servis.vratiLokacije(avio);
		if(s == "ok")
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/vratiSveLokacije", method = RequestMethod.GET)
	public ResponseEntity<List<LokacijaDTO>> vratiSveLokacije(){
		List<LokacijaDTO> aerodromi = servis.vratiSveLokacije();
		return new ResponseEntity<>(aerodromi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/izmeniLokaciju", method = RequestMethod.POST)
	public ResponseEntity<LokacijaDTO> izmeniLokaciju(@RequestBody LokacijaDTO lDTO){
		Lokacija l = servis.nadjiLokaciju(lDTO.getId());
		LokacijaDTO lkd = servis.nadjiLokacijuDTO(lDTO.getId());
		String retVal = servis.vratiLokacijuIzmena(lDTO);
		if(retVal.equals("ok")) { 
			l.setAdresa(lDTO.getAdresa());
			servis.sacuvajLokaciju(l);
			return new ResponseEntity<>(lkd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
