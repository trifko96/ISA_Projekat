package ISA.project.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AerodromDTO;
import ISA.project.dto.AvionDTO;
import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.FilterLetDTO;
import ISA.project.dto.KorisnikDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.LokacijePresedanjaDTO;
import ISA.project.dto.PretragaLetDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.dto.SegmentDTO;
import ISA.project.model.Aerodrom;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Avion;
import ISA.project.model.AvionskaKarta;
import ISA.project.model.Let;
import ISA.project.model.LokacijePresedanja;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.StatusSedista;
import ISA.project.model.TipKlase;
import ISA.project.repository.AerodromRepozitorijum;
import ISA.project.repository.AvionRepozitorijum;
import ISA.project.repository.LetRepozitorijum;

@Service
public class LetServis {

	@Autowired
	LetRepozitorijum repozitorijum;
	
	@Autowired
	AerodromRepozitorijum aeroRepo;
	
	@Autowired
	AvionRepozitorijum avioRepo;
	
	public List<LetDTO> vratiLetove(AvioKompanija a){
		List<LetDTO> letoviDTO = new ArrayList<>();
		List<Let> letovi = repozitorijum.vratiLetove(a.getId());
		
		for(Let l : letovi) {
			AerodromDTO aero1 = new AerodromDTO();
			AerodromDTO aero2 = new AerodromDTO();
			AvionDTO avio = new AvionDTO();
			List<AvionskaKartaDTO> karte = new ArrayList<AvionskaKartaDTO>();
			List<LokacijePresedanjaDTO> lokacije = new ArrayList<LokacijePresedanjaDTO>();
			
			
			aero1 = new AerodromDTO(l.getPolaznaDestinacija());
			aero2 = new AerodromDTO(l.getOdredisnaDestinacija());
			for(AvionskaKarta avKarta : l.getKarte()) {
				AvionskaKartaDTO ad = new AvionskaKartaDTO(avKarta);
				karte.add(ad);
			}
			for(LokacijePresedanja lp : l.getLokacijePresedanja()) {
				LokacijePresedanjaDTO lpDTO = new LokacijePresedanjaDTO(lp);
				lpDTO.setAerodrom(new AerodromDTO(lp.getAerodrom()));
				lokacije.add(lpDTO);
			}
			
			Avion avion = l.getAvion();
			List<SegmentDTO> klase = new ArrayList<>();
			for(Segment seg : avion.getKlasa()) {
				List<SedisteDTO> lista = new ArrayList<>();
				for(Sediste sed : seg.getListaSedista()) {
					lista.add(new SedisteDTO(sed));
				}
				SegmentDTO sd = new SegmentDTO(seg);
				sd.setListaSedista(lista);
				klase.add(sd);
			}
			avio = new AvionDTO(avion);
			avio.setKlase(klase);
			
			LetDTO letdto = new LetDTO(l);
			letdto.setAvion(avio);
			letdto.setKarte(karte);
			letdto.setLokacije(lokacije);
			letdto.setPolaznaDestinacija(aero1);
			letdto.setOdredisnaDestinacija(aero2);
			if(l.getBrojOcena() != 0) {
				letdto.setOcena(l.getOcene()/l.getBrojOcena());
			} else {
				letdto.setOcena(0);
			}
			
			letoviDTO.add(letdto);		
		}
		return letoviDTO;
	}
	
	public void dodajLet(LetDTO let, AvioKompanija a) {
		Let l = new Let();
		l.setAvioKompanija(a);
		l.setTip(let.getTip());
		l.setVremePoletanja(let.getDatumPoletanja());
		l.setVremeSletanja(let.getDatumSletanja());
		if(let.getTip().equals("ROUND_TRIP")) {
			l.setVremeDolaskaNazad(let.getDatumDolaskaNazad());
			l.setVremePolaskaNazad(let.getDatumPolaskaNazad());
		}
		Avion avion = avioRepo.vratiAvion(let.getAvion().getId());
		l.setAvion(avion);
		avion.setSlobodan(false);
		Aerodrom a1 = aeroRepo.vratiAerodromPoImenu(let.getPolaznaDestinacija().getIme());
		Aerodrom a2 = aeroRepo.vratiAerodromPoImenu(let.getOdredisnaDestinacija().getIme());
		l.setPolaznaDestinacija(a1);
		l.setOdredisnaDestinacija(a2);
		l.setCenaPrveKlase(let.getCenaPrveKlase());
		l.setCenaKarteBiznisKlase(let.getCenaKarteBiznisKlase());
		l.setCenaKarteEkonomskeKlase(let.getCenaKarteEkonomskeKlase());
		l.setPopust(let.getPopust());
		
		List<LokacijePresedanja> lokacije = new ArrayList<>();
		for(LokacijePresedanjaDTO lp : let.getLokacije()) {
			LokacijePresedanja lok = new LokacijePresedanja();
			Aerodrom aero = aeroRepo.vratiAerodromPoImenu(lp.getAerodrom().getIme());
			lok.setAerodrom(aero);
			lok.setLet(l);
			lokacije.add(lok);
		}
		l.setLokacijePresedanja(lokacije);
		l.generisiKarte();
		repozitorijum.save(l);
	}
	
	public List<LetDTO> vratiSveLetove(){
		List<LetDTO> letoviDTO = new ArrayList<>();
		List<Let> letovi = repozitorijum.findAll();
		
		for(Let l : letovi) {
			AerodromDTO aero1 = new AerodromDTO();
			AerodromDTO aero2 = new AerodromDTO();
			AvionDTO avio = new AvionDTO();
			List<AvionskaKartaDTO> karte = new ArrayList<AvionskaKartaDTO>();
			List<LokacijePresedanjaDTO> lokacije = new ArrayList<LokacijePresedanjaDTO>();
			
			
			aero1 = new AerodromDTO(l.getPolaznaDestinacija());
			aero2 = new AerodromDTO(l.getOdredisnaDestinacija());
			for(AvionskaKarta avKarta : l.getKarte()) {
				AvionskaKartaDTO ad = new AvionskaKartaDTO(avKarta);
				karte.add(ad);
			}
			for(LokacijePresedanja lp : l.getLokacijePresedanja()) {
				LokacijePresedanjaDTO lpDTO = new LokacijePresedanjaDTO(lp);
				lpDTO.setAerodrom(new AerodromDTO(lp.getAerodrom()));
				lokacije.add(lpDTO);
			}
			
			Avion avion = l.getAvion();
			List<SegmentDTO> klase = new ArrayList<>();
			for(Segment seg : avion.getKlasa()) {
				List<SedisteDTO> lista = new ArrayList<>();
				for(Sediste sed : seg.getListaSedista()) {
					lista.add(new SedisteDTO(sed));
				}
				SegmentDTO sd = new SegmentDTO(seg);
				sd.setListaSedista(lista);
				klase.add(sd);
			}
			avio = new AvionDTO(avion);
			avio.setKlase(klase);
			
			LetDTO letdto = new LetDTO(l);
			letdto.setAvion(avio);
			letdto.setKarte(karte);
			letdto.setLokacije(lokacije);
			letdto.setPolaznaDestinacija(aero1);
			letdto.setOdredisnaDestinacija(aero2);
			if(l.getBrojOcena() != 0) {
				letdto.setOcena(l.getOcene()/l.getBrojOcena());
			} else {
				letdto.setOcena(0);
			}
			
			letoviDTO.add(letdto);		
		}
		return letoviDTO;
	}
	
	public List<LetDTO> pretraziLet(PretragaLetDTO let){
		List<Let> letovi = repozitorijum.findAll();
		List<Let> letovi1 = new ArrayList<>();
		List<Let> letovi2 = new ArrayList<>();
		List<Let> letovi3 = new ArrayList<>();
		List<Let> letovi4 = new ArrayList<>();
		List<Let> letovi5 = new ArrayList<>();
		List<Let> letovi6 = new ArrayList<>();
		
		for(Let l : letovi) {
			if(l.getPolaznaDestinacija().getGrad().equals(let.getMestoPoletanja())) {
				letovi1.add(l);
			}
		}
		
		for(Let l : letovi1) {
			if(l.getOdredisnaDestinacija().getGrad().equals(let.getMestoSletanja())) {
				letovi2.add(l);
			}
		}
		
		for(Let l : letovi2) {
			if(l.getTip().equals(let.getTip())) {
				letovi3.add(l);
			}
		}
		
		for(Let l : letovi3) {
			int brSlobodnih = 0;
			List<Sediste> sedista = new ArrayList<>();
			TipKlase t;
			if(let.getKlasa().equals("PRVA")) {
				t = TipKlase.PRVA;
			} else if(let.getKlasa().equals("BIZNIS")) {
				t = TipKlase.BIZNIS;
			} else {
				t = TipKlase.EKONOMSKA;
			}
			for(Segment s : l.getAvion().getKlasa()) {
				if(s.getTip().equals(t)) {
					sedista = s.getListaSedista();
					break;
				}
			}
			
			for(Sediste s : sedista) {
				if(s.getStatus().equals(StatusSedista.SLOBODNO)) {
					brSlobodnih++;
				}
			}
			
			if(brSlobodnih >= let.getBrOsoba()) {
				letovi4.add(l);
			}
		}
		
		for(Let l : letovi4) {
			if(DateUtils.isSameDay(l.getVremePoletanja(), let.getVremePoletanja())) {
				letovi5.add(l);			
			}
		}
		
		for(Let l : letovi5) {
			if(DateUtils.isSameDay(l.getVremeSletanja(), let.getVremeSletanja())) {
				letovi6.add(l);
			}
		}
	
		List<LetDTO> letoviDTO = new ArrayList<>();
		for(Let l : letovi6) {
			AerodromDTO aero1 = new AerodromDTO();
			AerodromDTO aero2 = new AerodromDTO();
			AvionDTO avio = new AvionDTO();
			List<AvionskaKartaDTO> karte = new ArrayList<AvionskaKartaDTO>();
			List<LokacijePresedanjaDTO> lokacije = new ArrayList<LokacijePresedanjaDTO>();
			
			
			aero1 = new AerodromDTO(l.getPolaznaDestinacija());
			aero2 = new AerodromDTO(l.getOdredisnaDestinacija());
			for(AvionskaKarta avKarta : l.getKarte()) {
				AvionskaKartaDTO ad = new AvionskaKartaDTO(avKarta);
				karte.add(ad);
			}
			for(LokacijePresedanja lp : l.getLokacijePresedanja()) {
				LokacijePresedanjaDTO lpDTO = new LokacijePresedanjaDTO(lp);
				lpDTO.setAerodrom(new AerodromDTO(lp.getAerodrom()));
				lokacije.add(lpDTO);
			}
			
			Avion avion = l.getAvion();
			List<SegmentDTO> klase = new ArrayList<>();
			for(Segment seg : avion.getKlasa()) {
				List<SedisteDTO> lista = new ArrayList<>();
				for(Sediste sed : seg.getListaSedista()) {
					lista.add(new SedisteDTO(sed));
				}
				SegmentDTO sd = new SegmentDTO(seg);
				sd.setListaSedista(lista);
				klase.add(sd);
			}
			avio = new AvionDTO(avion);
			avio.setKlase(klase);
			
			LetDTO letdto = new LetDTO(l);
			letdto.setAvion(avio);
			letdto.setKarte(karte);
			letdto.setLokacije(lokacije);
			letdto.setPolaznaDestinacija(aero1);
			letdto.setOdredisnaDestinacija(aero2);
			if(l.getBrojOcena() != 0) {
				letdto.setOcena(l.getOcene()/l.getBrojOcena());
			} else {
				letdto.setOcena(0);
			}
			
			letoviDTO.add(letdto);		
		}
		
		return letoviDTO;
	}
	
	public List<LetDTO> filtrirajLet(FilterLetDTO let){
		List<LetDTO> letoviDTO = new ArrayList<>();
		List<Let> letovi = repozitorijum.findAll();
		List<Let> letovi1 = new ArrayList<>();
		List<Let> letovi2 = new ArrayList<>();
		
		for(Let l : letovi) {
			if(l.getAvioKompanija().getNaziv().equals(let.getAvioKompanija())) {
				letovi1.add(l);
			}
		}
		
		for(Let l : letovi1) {
			int znak = 0;
			if(l.getCenaKarteBiznisKlase() <= let.getCena()) {
				znak++;
			} else if(l.getCenaKarteEkonomskeKlase() <= let.getCena()) {
				znak++;
			} else if(l.getCenaPrveKlase() <= let.getCena()) {
				znak++;
			}
			if(znak != 0) {
				letovi2.add(l);
			}
		}
		
		for(Let l : letovi2) {
			AerodromDTO aero1 = new AerodromDTO();
			AerodromDTO aero2 = new AerodromDTO();
			AvionDTO avio = new AvionDTO();
			List<AvionskaKartaDTO> karte = new ArrayList<AvionskaKartaDTO>();
			List<LokacijePresedanjaDTO> lokacije = new ArrayList<LokacijePresedanjaDTO>();
			
			
			aero1 = new AerodromDTO(l.getPolaznaDestinacija());
			aero2 = new AerodromDTO(l.getOdredisnaDestinacija());
			for(AvionskaKarta avKarta : l.getKarte()) {
				AvionskaKartaDTO ad = new AvionskaKartaDTO(avKarta);
				karte.add(ad);
			}
			for(LokacijePresedanja lp : l.getLokacijePresedanja()) {
				LokacijePresedanjaDTO lpDTO = new LokacijePresedanjaDTO(lp);
				lpDTO.setAerodrom(new AerodromDTO(lp.getAerodrom()));
				lokacije.add(lpDTO);
			}
			
			Avion avion = l.getAvion();
			List<SegmentDTO> klase = new ArrayList<>();
			for(Segment seg : avion.getKlasa()) {
				List<SedisteDTO> lista = new ArrayList<>();
				for(Sediste sed : seg.getListaSedista()) {
					lista.add(new SedisteDTO(sed));
				}
				SegmentDTO sd = new SegmentDTO(seg);
				sd.setListaSedista(lista);
				klase.add(sd);
			}
			avio = new AvionDTO(avion);
			avio.setKlase(klase);
			
			LetDTO letdto = new LetDTO(l);
			letdto.setAvion(avio);
			letdto.setKarte(karte);
			letdto.setLokacije(lokacije);
			letdto.setPolaznaDestinacija(aero1);
			letdto.setOdredisnaDestinacija(aero2);
			if(l.getBrojOcena() != 0) {
				letdto.setOcena(l.getOcene()/l.getBrojOcena());
			} else {
				letdto.setOcena(0);
			}
			
			letoviDTO.add(letdto);		
		}
		
		return letoviDTO;
	}
}
