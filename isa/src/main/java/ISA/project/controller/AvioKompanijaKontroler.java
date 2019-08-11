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

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.service.AvioKompanijaServis;

@RestController
@RequestMapping(value="/AvioKompanija")
public class AvioKompanijaKontroler {
	
	@Autowired
	AvioKompanijaServis servis;

	@RequestMapping(value="/vratiKompaniju", method = RequestMethod.GET)
	public ResponseEntity<AvioKompanijaDTO> vratiAvioKompaniju(@Context HttpServletRequest request) {
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija a = k.getAvioKompanija();
		AvioKompanijaDTO akd = servis.nadjiKompaniju(a.getId());
		if(akd != null)
			return new ResponseEntity<>(akd, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/izmeniKompaniju", method = RequestMethod.POST)
	public ResponseEntity<AvioKompanijaDTO> izmeniKompaniju(@RequestBody AvioKompanijaDTO aDTO){
		AvioKompanija a = servis.nadjiAvioKompaniju(aDTO.getId());
		AvioKompanijaDTO akd = servis.nadjiKompaniju(aDTO.getId());
		String retVal = servis.vratiKompanije(aDTO);
		if(retVal.equals("ok")) {
			a.setNaziv(aDTO.getNaziv());
			a.setAdresa(aDTO.getAdresa());
			a.setOpis(aDTO.getOpis());
			a.setKoordinata1(aDTO.getKoordinata1());
			a.setKoordinata2(aDTO.getKoordinata2());
			a.setInfoPrtljag(aDTO.getInfoPrtljag());
			servis.sacuvajKompaniju(a);
			return new ResponseEntity<>(akd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
