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

import ISA.project.dto.KorisnikDTO;
import ISA.project.dto.ZahteviZaPrijateljstvoDTO;
import ISA.project.model.Korisnik;
import ISA.project.service.ZahteviZaPrijateljstvoServis;

@RestController
@RequestMapping(value="/Zahtevi")
public class ZahteviZaPrijateljstvoKontroler {

	@Autowired
	ZahteviZaPrijateljstvoServis servis;
	
	@RequestMapping(value="/vratiMojePrijatelje", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> vratiMojePrijatelje(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		List<KorisnikDTO> prijatelji = servis.vratiMojePrijatelje(k);
		return new ResponseEntity<>(prijatelji, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiMojeZahteve", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> vratiMojeZahteve(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		List<KorisnikDTO> zahtevi = servis.vratiMojeZahteve(k);
		return new ResponseEntity<>(zahtevi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiOstaleKorisnike/{ime}", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> vratiOstaleKorisnike(@Context HttpServletRequest request, @PathVariable String ime){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		List<KorisnikDTO> korisnici = servis.vratiOstaleKorisnike(k, ime);
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value="/obrisiPrijatelja/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> obrisiPrijatelja(@Context HttpServletRequest request, @PathVariable long id){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		servis.obrisiPrijatelja(k, id);
		List<KorisnikDTO> prijatelji = servis.vratiMojePrijatelje(k);
		return new ResponseEntity<>(prijatelji, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajPrijatelja", method = RequestMethod.POST)
	public ResponseEntity<List<KorisnikDTO>> dodajPrijatelja(@Context HttpServletRequest request, @RequestBody KorisnikDTO kDTO){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		servis.dodajPrijatelja(k, kDTO);
		List<KorisnikDTO> korisnici = servis.vratiOstale(k);
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value="/prihvatiZahtev/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> prihvatiZahtev(@Context HttpServletRequest request, @PathVariable long id){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		servis.prihvatiZahtev(k, id);
		List<KorisnikDTO> prijatelji = servis.vratiMojePrijatelje(k);
		return new ResponseEntity<>(prijatelji, HttpStatus.OK);
	}
	
	@RequestMapping(value="/izbrisiZahtev/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> izbrisiZahtev(@Context HttpServletRequest request, @PathVariable long id){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		servis.izbrisiZahtev(k, id);
		List<KorisnikDTO> prijatelji = servis.vratiMojePrijatelje(k);
		return new ResponseEntity<>(prijatelji, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiPrijateljeZaLet", method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> vratiPrijateljeZaLet(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		List<KorisnikDTO> korisniciDTO = servis.vratiPrijateljeZaLet(k);
		return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
	}
	
}
