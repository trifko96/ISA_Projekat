package ISA.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AvionDTO;
import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.model.Avion;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.Segment;
import ISA.project.model.TipKlase;
import ISA.project.service.AvionServis;
import ISA.project.service.EmailServis;
import ISA.project.service.KorisnikServis;
import ISA.project.service.SedisteServis;

@RestController
@RequestMapping(value = "/Sediste")
public class SedisteKontroler {

	@Autowired
	SedisteServis servis;
	
	@Autowired
	AvionServis avioServis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
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
	
	@RequestMapping(value="/brzoRezervisi/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<AvionskaKartaDTO>> brzoRezervisi(@PathVariable long id, @RequestBody AvionskaKartaDTO a){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		String poruka = servis.brzoRezervisi(a, k);
		if(poruka.equals("ok")) {
			List<AvionskaKartaDTO> lista = servis.vratiSveBrzeKarte();
			try {
				eservis.obavestenjeORezervaciji(k, a);
			}catch( Exception e ){
				logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			}
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/rezervisi/{id}", method = RequestMethod.POST)
	public ResponseEntity<RezervacijaDTO> rezervisi(@PathVariable long id, @RequestBody RezervacijaDTO r){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		List<Korisnik> korisnici = korisnikServis.vratiKorisnike();
		long idPom = 0;
		double cena = 0;
		for(RezervacijaKarataDTO rez : r.getKarte()) {
			idPom = rez.getIdSedista();
			break;
		}
		Let l = servis.vratiPodatkeOLetu(idPom);
		Segment s = servis.vratiPodatkeOKlasi(idPom);
		if(s.getTip().equals(TipKlase.BIZNIS)) {
			cena = l.getCenaKarteBiznisKlase();
		} else if(s.getTip().equals(TipKlase.EKONOMSKA)) {
			cena = l.getCenaKarteEkonomskeKlase();
		} else {
			cena = l.getCenaPrveKlase();
		}
		String poruka = servis.rezervisi(r, k);
		if(poruka.equals("ok")) {
			try {
				eservis.rezervacijaInformacije(r, k, l, s);
			}catch( Exception e ){
				logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			}
			
			for(RezervacijaKarataDTO rez : r.getKarte()) {
				double pom = 0;
				for(Korisnik kor : korisnici) {
					if(rez.getEmail().equals(kor.getEmail())) {
						pom++;
					}
				}
				if((pom != 0) && (!rez.getEmail().equals(k.getEmail()))) {
					try {
						eservis.pozivZaLet(l, cena, rez);
					}catch( Exception e ){
						logger.info("Greska prilikom slanja emaila: " + e.getMessage());
					}
				}
			}
			
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/potvrdi/{id}", method = RequestMethod.GET)
	public String potvrdiRezervaciju(@PathVariable long id) {
		servis.potvrdi(id);
		return "Potvrdili ste rezervaciju!";
	}
	
	@RequestMapping(value="/otkazi/{id}", method = RequestMethod.GET)
	public String otkaziRezervaciju(@PathVariable long id) {
		servis.otkazi(id);
		return "Otkazali ste rezervaciju!";
	}
	
}
