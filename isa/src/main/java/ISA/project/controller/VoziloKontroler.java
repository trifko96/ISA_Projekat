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

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.dto.DatumiPopustDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.PretragaVoziloDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.dto.RezervacijaVoziloDTO;
import ISA.project.dto.VoziloDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.RentACar;
import ISA.project.model.Rezervacija;
import ISA.project.model.RezervacijaVozilo;
import ISA.project.model.Segment;
import ISA.project.model.TipKlase;
import ISA.project.model.Vozilo;
import ISA.project.repository.RezervacijaVoziloRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;
import ISA.project.service.EmailServis;
import ISA.project.service.KorisnikServis;
import ISA.project.service.RentACarServis;
import ISA.project.service.SedisteServis;
import ISA.project.service.VoziloServis;


@RestController
@RequestMapping(value="/Vozilo")
public class VoziloKontroler {
	
	@Autowired
	VoziloServis servis;
	
	@Autowired
	RentACarServis carServis;
	 
	@Autowired
	KorisnikServis korisnikServis;
	 
	@Autowired 
	EmailServis eservis;
	
	@Autowired
	SedisteServis sedisteServis;
	
	@Autowired
	VoziloRepozitorijum vozRep;
	
	@Autowired
	RezervacijaVoziloRepozitorijum rezVoz;
	
	private Logger logger = LoggerFactory.getLogger(VoziloKontroler.class);
	
	@RequestMapping(value = "/dodajNovo", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> dodajNovo(@RequestBody VoziloDTO vdto, @Context HttpServletRequest request){
		Vozilo v = servis.vratiVoziloPoNazivu(vdto);
		if(v == null) {
			Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
			//AvioKompanija avio = k.getAvioKompanija();
			RentACar rent = carServis.nadjiRentACarPoKorisniku(k);
			List<VoziloDTO> pom = servis.dodajVozilo(rent, vdto);
			return new ResponseEntity<>(pom, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/vratiVozilo", method = RequestMethod.GET)
	public ResponseEntity<List<VoziloDTO>> vratiVozilo(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar rent = k.getRentACar();
		List<VoziloDTO> pom = servis.vratiVozila(rent);
		return new ResponseEntity<>(pom, HttpStatus.OK);
	}
	
	@RequestMapping(value="/obrisiVozilo", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> obrisiVozilo(@RequestBody VoziloDTO vdto, @Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar rent = carServis.nadjiRentACarPoKorisniku(k);
		String s = servis.obrisiVozilo(vdto, rent);
		List<VoziloDTO> lista = servis.vratiVozila(rent);
		if(s == "ok")
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/izmeniVozilo", method = RequestMethod.POST)
	public ResponseEntity<VoziloDTO> izmeniVozilo(@RequestBody VoziloDTO vDTO){
		Vozilo v = servis.nadjiVozilo(vDTO.getId());
		VoziloDTO vkd = servis.nadjiVoziloDTO(vDTO.getId());
		String retVal = servis.vratiVoziloIzmena(vDTO);
		if(retVal.equals("ok")) { 
			v.setCena(vDTO.getCena());
			v.setNaziv(vDTO.getNaziv());
			v.setMarka(vDTO.getMarka());
			v.setModel(vDTO.getModel());
			v.setGodinaProizvodnje(vDTO.getGodinaProizvodnje());
			v.setBrSedista(vDTO.getBrSedista());
			v.setTip(vDTO.getTip());
			v.setNaPopustu(vDTO.getNaPopustu());
			v.setAdresaLokacije(vDTO.getAdresaLokacije());
			v.setPopust(vDTO.getPopust());
			v.setDatumPopustOd(vDTO.getDatumPopustOd());
			v.setDatumPospustDo(vDTO.getDatumPopustDo());
			servis.sacuvajVozilo(v);
			return new ResponseEntity<>(vkd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/vratiBrzaVozila", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> vratiBrzaVozila(@RequestBody DatumiPopustDTO d){
		List<VoziloDTO> vozila = servis.vratiBrzaVozila(d);
		return new ResponseEntity<>(vozila, HttpStatus.OK);
	}
	
	@RequestMapping(value="/brzoRezervisiVozilo/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> brzoRezervisiVozilo(@RequestBody RezervacijaDTO r, @PathVariable long id){
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		List<Korisnik> korisnici = korisnikServis.vratiKorisnike();
		long idPom = 0;
		double cena = 0;
		for(RezervacijaKarataDTO rez : r.getKarte()) {
			idPom = rez.getIdSedista();
			break;
		}
		Let l = sedisteServis.vratiPodatkeOLetu(idPom);
		Segment s = sedisteServis.vratiPodatkeOKlasi(idPom);
		if(s.getTip().equals(TipKlase.BIZNIS)) {
			cena = l.getCenaKarteBiznisKlase();
		} else if(s.getTip().equals(TipKlase.EKONOMSKA)) {
			cena = l.getCenaKarteEkonomskeKlase();
		} else {
			cena = l.getCenaPrveKlase();
		}
		Rezervacija povratnaRezervacija = servis.rezervisi(r, k);
		String poruka = "";
		if(povratnaRezervacija != null) {
			poruka = servis.brzoRezervisi(r, k, povratnaRezervacija);
		}
		if(poruka.equals("ok")) {
			try {
				eservis.rezervacijaInformacijeBrzoVozilo(r, k, l, s, r.getRezervacijaVozilo().getVozilo());
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
			List<VoziloDTO> vozilaDTO = servis.vrati();
			return new ResponseEntity<>(vozilaDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/pretraziVozilo/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> pretraziVozilo(@RequestBody PretragaVoziloDTO p, @PathVariable long id){
		List<VoziloDTO> vozila = servis.pretraziVozilo(p, id);
		return new ResponseEntity<>(vozila, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rezervisanaVozila", method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaVoziloDTO>> vratiRezVozila(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		//RentACar rent = k.getRentACar();
		List<RezervacijaVoziloDTO> pom = servis.vratiRezVozila(k);
		return new ResponseEntity<>(pom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/otkaziRezervacijuVozila/{idRezVozilo}", method = RequestMethod.POST)
	public ResponseEntity<List<RezervacijaVoziloDTO>> otkaziRezervacijuVozila(@RequestBody RezervacijaVoziloDTO rdto, @PathVariable long idRezVozilo, @Context HttpServletRequest request) 
	{
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar rent = carServis.nadjiRentACarPoKorisniku(k);
		
		RezervacijaVozilo voz = rezVoz.vratiRezervacijuPoId(idRezVozilo);
		
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        java.sql.Date tomorrow = new java.sql.Date(date.getTime() + 72*60*60*1000);
        //System.out.println(tomorrow);

		if (tomorrow.before(voz.getDatumRezervacijaOd())) 
		{
			
			List<RezervacijaVoziloDTO> rtdo = servis.otkaziRezervaciju(idRezVozilo, k);

			return new ResponseEntity<>(rtdo, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value="/oceniVozilo/{id}/{idr}/{opcija}", method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaVoziloDTO>> oceniVozilo(@PathVariable long id, @PathVariable long idr, @PathVariable double opcija){
		List<RezervacijaVoziloDTO> lista = servis.oceniVozilo(id, idr, opcija);
		if(lista != null) {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


}
