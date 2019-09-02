package ISA.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AvionDTO;
import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.model.Avion;
import ISA.project.model.Korisnik;
import ISA.project.model.Segment;
import ISA.project.service.AvionServis;
import ISA.project.service.EmailServis;
import ISA.project.service.SedisteServis;

@RestController
@RequestMapping(value = "/Sediste")
public class SedisteKontroler {

	@Autowired
	SedisteServis servis;
	
	@Autowired
	AvionServis avioServis;
	
	@Autowired
	EmailServis eservis;
	
	private Logger logger = LoggerFactory.getLogger(SedisteKontroler.class);
	
	@RequestMapping(value = "/izmeniSediste", method = RequestMethod.POST)
	public ResponseEntity<AvionDTO> izmeniSediste(@RequestBody SedisteDTO s){
		servis.izmeniSediste(s);
		AvionDTO ad = avioServis.vratiAvion(s);
		return new ResponseEntity<>(ad, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajSediste", method = RequestMethod.POST)
	public ResponseEntity<AvionDTO> dodajSediste(@RequestBody SedisteDTO s){
		Segment seg = servis.dodajSediste(s);
		AvionDTO ad = avioServis.vratiAvionPoKlasi(seg.getAvion().getId());
		return new ResponseEntity<>(ad, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiBrzeKarte", method = RequestMethod.POST)
	public ResponseEntity<List<AvionskaKartaDTO>> vratiBrzeKarte(@RequestBody LetDTO l){
		List<AvionskaKartaDTO> avioKarte = servis.vratiBrzeKarte(l);
		return new ResponseEntity<>(avioKarte, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiSveBrzeKarte", method = RequestMethod.GET)
	public ResponseEntity<List<AvionskaKartaDTO>> vratiSveBrzeKarte(){
		List<AvionskaKartaDTO> lista = servis.vratiSveBrzeKarte();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value="/brzoRezervisi", method = RequestMethod.POST)
	public ResponseEntity<List<AvionskaKartaDTO>> brzoRezervisi(@Context HttpServletRequest request, @RequestBody AvionskaKartaDTO a){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		servis.brzoRezervisi(a, k);
		List<AvionskaKartaDTO> lista = servis.vratiSveBrzeKarte();
		try {
			eservis.obavestenjeORezervaciji(k, a);
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
}
