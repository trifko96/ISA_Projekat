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

import ISA.project.dto.LetDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.service.AvioKompanijaServis;
import ISA.project.service.LetServis;

@RestController
@RequestMapping(value="/Let")
public class LetKontroler {

	@Autowired
	LetServis servis;
	
	@Autowired 
	AvioKompanijaServis avioServis;
	
	@RequestMapping(value="/vratiLetove", method = RequestMethod.GET)
	public ResponseEntity<List<LetDTO>> vratiLetove(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		List<LetDTO> letovi = servis.vratiLetove(avio);
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajLet", method = RequestMethod.POST)
	public ResponseEntity<List<LetDTO>> dodajLet(@RequestBody LetDTO let, @Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		servis.dodajLet(let, avio);
		List<LetDTO> letovi = servis.vratiLetove(avio);
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
}
