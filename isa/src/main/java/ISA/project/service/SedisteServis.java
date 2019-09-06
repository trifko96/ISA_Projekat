package ISA.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import ISA.project.dto.AerodromDTO;
import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.LetZaKarteDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.model.Avion;
import ISA.project.model.AvionskaKarta;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.Rezervacija;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.StatusSedista;
import ISA.project.model.TipKlase;
import ISA.project.repository.AvionskaKartaRepozitorijum;
import ISA.project.repository.KorisnikRepozitorijum;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.repository.RezervacijaRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;
import ISA.project.repository.SegmentRepozitorijum;

@Service
public class SedisteServis {

	@Autowired
	SedisteRepozitorijum repozitorijum;
	
	@Autowired
	SegmentRepozitorijum segmentRepozitorijum;
	
	@Autowired
	LetRepozitorijum letRepozitorijum;
	
	@Autowired
	AvionskaKartaRepozitorijum kartaRepo;
	
	@Autowired
	RezervacijaRepozitorijum rezRepo;
	
	@Autowired
	KorisnikRepozitorijum korisnikRepo;
	
	public void izmeniSediste(SedisteDTO s) {
		Sediste sediste = repozitorijum.vratiSediste(s.getId());
		sediste.setStatus(s.getStatus());
		AvionskaKarta a = kartaRepo.vratiKartu(sediste.getId());
		Let l = letRepozitorijum.vratiLetPoAvionu(sediste.getSegment().getAvion().getId());
		if(a != null) {
			a.setCena(a.getCena() - a.getCena()*l.getPopust() / 100);
		}
		repozitorijum.save(sediste);
		kartaRepo.save(a);
	}
	
	public Segment dodajSediste(SedisteDTO s) {
		Segment segment = segmentRepozitorijum.vratiKlasu(s.getPom());
		Sediste sediste = new Sediste(s.getBrReda(), s.getBrKolone(), segment, s.getNatpis(), s.getStatus(), s.isGranica());
		segment.dodajSediste(sediste);
		segmentRepozitorijum.save(segment);
		return segment;
	}
	
	public List<AvionskaKartaDTO> vratiBrzeKarte(LetDTO l){
		Let let = letRepozitorijum.vratiLet(l.getId());
		Avion av = let.getAvion();
		List<Sediste> listaSedista = new ArrayList<>();
		for(Segment s : av.getKlasa()) {
			for(Sediste sed : s.getListaSedista()) {
				if(sed.getStatus().equals(StatusSedista.BRZA_REZERVACIJA)) {
					listaSedista.add(sed);
				}
			}
		}
		List<AvionskaKartaDTO> avioKarteDto = new ArrayList<>();
		for(Sediste s : listaSedista) {
			AvionskaKarta aKarta = kartaRepo.vratiKartu(s.getId());
			avioKarteDto.add(new AvionskaKartaDTO(aKarta));
		}
		
		return avioKarteDto;
	}
	
	public List<AvionskaKartaDTO> vratiSveBrzeKarte(){
		List<Let> letovi = letRepozitorijum.findAll();
		List<AvionskaKarta> brzeKarte = new ArrayList<>();
		List<AvionskaKartaDTO> brzeKarteDTO = new ArrayList<>();
		for(Let l : letovi) {
			for(AvionskaKarta karta : l.getKarte()) {
				if(karta.getSediste().getStatus().equals(StatusSedista.BRZA_REZERVACIJA)) {
					brzeKarte.add(karta);
				}
			}
		}
		
		for(AvionskaKarta a : brzeKarte) {
			AvionskaKartaDTO adto = new AvionskaKartaDTO(a);
			if(a.getSediste().getSegment().getTip().equals(TipKlase.BIZNIS)) {
				adto.setCena(a.getLet().getCenaKarteBiznisKlase());
			} else if(a.getSediste().getSegment().getTip().equals(TipKlase.EKONOMSKA)) {
				adto.setCena(a.getLet().getCenaKarteEkonomskeKlase());
			} else {
				adto.setCena(a.getLet().getCenaPrveKlase());
			}
			LetZaKarteDTO let = new LetZaKarteDTO(a.getLet());
			let.setPolaznaDestinacija(new AerodromDTO(a.getLet().getPolaznaDestinacija()));
			let.setOdredisnaDestinacija(new AerodromDTO(a.getLet().getOdredisnaDestinacija()));
			adto.setLet(let);
			brzeKarteDTO.add(adto);
		}
		
		return brzeKarteDTO;
	}
	
	public void brzoRezervisi(AvionskaKartaDTO a, Korisnik k) {
		AvionskaKarta karta = kartaRepo.vratiKartuPoId(a.getId());
		karta.setImePutnika(k.getIme());
		karta.setPrezimePutnika(k.getPrezime());
		karta.setBrTelefonaPutnika(k.getBrTelefona());
		karta.setEmailPutnika(k.getEmail());
		karta.setDatum(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		Sediste s = karta.getSediste();
		s.setStatus(StatusSedista.REZERVISANO);
		repozitorijum.save(s);
		kartaRepo.save(karta);
	}
	
	@Transactional
	synchronized public void rezervisi(RezervacijaDTO r, Korisnik k) {
		Rezervacija rezervacija = new Rezervacija();
		List<Korisnik> korisnici = korisnikRepo.findAll();
		for(RezervacijaKarataDTO rdto : r.getKarte()) {
			int tmp = 0;
			AvionskaKarta a = kartaRepo.vratiKartu(rdto.getIdSedista());
			a.setBrojPasosaPutnika(rdto.getBrPasosa());
			a.setBrTelefonaPutnika(rdto.getBrTelefona());
			a.setEmailPutnika(rdto.getEmail());
			a.setImePutnika(rdto.getIme());
			a.setPrezimePutnika(rdto.getPrezime());
			a.setDatum(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			for(Korisnik kor : korisnici) {
				if(kor.getEmail().equals(rdto.getEmail()) && !k.getEmail().equals(rdto.getEmail())) {
					tmp++;
				}
			}
			if(tmp == 0) {
				Sediste s = repozitorijum.vratiSediste(rdto.getIdSedista());
				s.setStatus(StatusSedista.REZERVISANO);
				repozitorijum.save(s);
			}
			kartaRepo.save(a);
			rezervacija.getKarte().add(a);
		}
		rezRepo.save(rezervacija);
	}
	
	public Let vratiPodatkeOLetu(long id) {
		Sediste s = repozitorijum.vratiSediste(id);
		Avion a = s.getSegment().getAvion();
		Let l = letRepozitorijum.vratiLetPoAvionu(a.getId());
		return l;
	}
	
	public Segment vratiPodatkeOKlasi(long id) {
		Sediste s = repozitorijum.vratiSediste(id);
		Segment pom = s.getSegment();
		return pom;
	}
	
	public void potvrdi(long id) {
		Sediste s = repozitorijum.vratiSediste(id);
		s.setStatus(StatusSedista.REZERVISANO);
		repozitorijum.save(s);
	}
	
	public void otkazi(long id) {
		Sediste s = repozitorijum.vratiSediste(id);
		AvionskaKarta a = kartaRepo.vratiKartu(id);
		s.setStatus(StatusSedista.SLOBODNO);
		a.setBrojPasosaPutnika("");
		a.setBrTelefonaPutnika("");
		a.setEmailPutnika("");
		a.setImePutnika("");
		a.setPrezimePutnika("");
		repozitorijum.save(s);
		kartaRepo.save(a);
	}
}
