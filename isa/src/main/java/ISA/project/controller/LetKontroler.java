package ISA.project.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import ISA.project.dto.FilterLetDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.PretragaLetDTO;
import ISA.project.dto.VoziloDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.RentACar;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.service.AvioKompanijaServis;
import ISA.project.service.KorisnikServis;
import ISA.project.service.LetServis;

@RestController
@RequestMapping(value="/Let")
public class LetKontroler {

	@Autowired
	LetServis servis;
	
	@Autowired 
	AvioKompanijaServis avioServis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	LetRepozitorijum letRep;
	
	@RequestMapping(value="/vratiLetove/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<LetDTO>> vratiLetove(@PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		List<LetDTO> letovi = servis.vratiLetove(avio);
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajLet/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<LetDTO>> dodajLet(@RequestBody LetDTO let, @PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		servis.dodajLet(let, avio);
		List<LetDTO> letovi = servis.vratiLetove(avio);
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiSveLetove", method = RequestMethod.GET)
	public ResponseEntity<List<LetDTO>> vratiSveLetove(){
		List<LetDTO> letovi = servis.vratiSveLetove();
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pretraziLet", method = RequestMethod.POST)
	public ResponseEntity<List<LetDTO>> pretraziLet(@RequestBody PretragaLetDTO let){
		List<LetDTO> letovi = servis.pretraziLet(let);
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/filtrirajLet", method = RequestMethod.POST)
	public ResponseEntity<List<LetDTO>> filtrirajLet(@RequestBody FilterLetDTO let){
		List<LetDTO> letovi = servis.filtrirajLet(let);
		return new ResponseEntity<>(letovi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rezervisaniLetovi", method = RequestMethod.GET)
	public ResponseEntity<List<LetDTO>> vratiRezLetove(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		//RentACar rent = k.getRentACar();
		List<LetDTO> pom = servis.vratiRezLetove(k);
		return new ResponseEntity<>(pom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/otkaziRezervacijuLeta/{idLeta}", method = RequestMethod.POST)
	public ResponseEntity<List<LetDTO>> otkaziRezervacijuLeta(@RequestBody LetDTO ldto, @PathVariable Long idLeta, @Context HttpServletRequest request) 
	{
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija avio = avioServis.nadjiKompanijuPoKorisniku(k);
		
		Let let = letRep.vratiLet(idLeta);
		
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        java.sql.Date tomorrow = new java.sql.Date(date.getTime() + 3*60*60*1000);
        //System.out.println(tomorrow);

		if (tomorrow.before(let.getVremePoletanja())) 
		{
			
			List<LetDTO> ltdo = servis.otkaziRezervaciju(idLeta, k);

			return new ResponseEntity<>(ltdo, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value="/oceniLet/{id}/{idLeta}/{opcija}", method = RequestMethod.GET)
	public ResponseEntity<List<LetDTO>> oceniKompaniju(@PathVariable long id, @PathVariable long idLeta, @PathVariable double opcija){
		List<LetDTO> lista = servis.oceniLet(id, idLeta, opcija);
		if(lista != null) {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
