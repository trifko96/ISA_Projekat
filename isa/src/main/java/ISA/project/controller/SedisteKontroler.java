package ISA.project.controller;

import java.util.List;

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
import ISA.project.model.Segment;
import ISA.project.service.AvionServis;
import ISA.project.service.SedisteServis;

@RestController
@RequestMapping(value = "/Sediste")
public class SedisteKontroler {

	@Autowired
	SedisteServis servis;
	
	@Autowired
	AvionServis avioServis;
	
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
	
}
