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

import ISA.project.dto.LokacijaDTO;
import ISA.project.dto.PretragaServisDTO;
import ISA.project.dto.RentACarDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.RentACar;
import ISA.project.service.KorisnikServis;
import ISA.project.service.LokacijaServis;
import ISA.project.service.RentACarServis;


@RestController
@RequestMapping(value="/RentACar")
public class RentACarKontroler {

	@Autowired
	RentACarServis servis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	LokacijaServis lokacijaServis;

	@RequestMapping(value="/vratiRentACar", method = RequestMethod.GET)
	public ResponseEntity<RentACarDTO> vratiRentACar(@Context HttpServletRequest request) {
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar r = k.getRentACar();
		RentACarDTO rkd = servis.nadjiRentCar(r.getRentACarId());
		if(rkd != null)
			return new ResponseEntity<>(rkd, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/izmeniRentACar", method = RequestMethod.POST)
	public ResponseEntity<RentACarDTO> izmeniRentACar(@RequestBody RentACarDTO rDTO){
		RentACar r = servis.nadjiRentACar(rDTO.getId());
		RentACarDTO rkd = servis.nadjiRentCar(rDTO.getId());
		String retVal = servis.vratiRentACar(rDTO);
		if(retVal.equals("ok")) { 
			r.setNaziv(rDTO.getNaziv());
			r.setAdresa(rDTO.getAdresa());
			r.setOpis(rDTO.getOpis());
			servis.sacuvajRentACar(r);
			return new ResponseEntity<>(rkd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/vratiSveServise", method = RequestMethod.GET)
	public ResponseEntity<List<RentACarDTO>> vratiSveServise(){
		List<RentACarDTO> lista = servis.vratiServise();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vratiFilijale/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<LokacijaDTO>> vratiFilijale(@RequestBody RentACarDTO r, @PathVariable long id){
		RentACar rent = servis.nadjiRentACar(id);
		List<LokacijaDTO> lista = lokacijaServis.vratiLokacije(rent);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pretraziServise", method = RequestMethod.POST)
	public ResponseEntity<List<RentACarDTO>> pretraziServise(@RequestBody PretragaServisDTO p){
		List<RentACarDTO> servisiDTO = servis.pretraziServise(p);
		return new ResponseEntity<>(servisiDTO, HttpStatus.OK);
	}
}
