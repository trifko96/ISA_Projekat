package ISA.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public void izmeniSediste(SedisteDTO s) {
		Sediste sediste = repozitorijum.vratiSedistePoId(s.getId());
		if(!sediste.getStatus().equals(StatusSedista.REZERVISANO)) {
			sediste.setStatus(s.getStatus());
			AvionskaKarta a = kartaRepo.vratiKartuPoSedistu(sediste.getId());
			Let l = letRepozitorijum.vratiLetPoAvionu(sediste.getSegment().getAvion().getId());
			if(s.getStatus().equals(StatusSedista.BRZA_REZERVACIJA)) {
				if(a != null) {
					a.setCena(a.getCena() - a.getCena()*l.getPopust() / 100);
					kartaRepo.save(a);
				}
			}
			repozitorijum.save(sediste);
		}
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
	
	@Transactional
	public String brzoRezervisi(AvionskaKartaDTO a, Korisnik k) {
		AvionskaKarta karta = kartaRepo.vratiKartuPoId(a.getId());
		Sediste s = repozitorijum.vratiSedistePoKarti(karta.getIdKarte());
		if(!karta.getSediste().getStatus().equals(StatusSedista.BRZA_REZERVACIJA)) {
			return "greska";
		} else {
			karta.setImePutnika(k.getIme());
			karta.setPrezimePutnika(k.getPrezime());
			karta.setBrTelefonaPutnika(k.getBrTelefona());
			karta.setEmailPutnika(k.getEmail());
			karta.setDatum(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			s.setStatus(StatusSedista.REZERVISANO);
			repozitorijum.save(s);
			kartaRepo.save(karta);
			return "ok";
		}
	}
	
	@Transactional
	public String rezervisi(RezervacijaDTO r, Korisnik k) {
		Rezervacija rezervacija = new Rezervacija();
		ArrayList<Long> ids = new ArrayList<>();
		for(RezervacijaKarataDTO rdto : r.getKarte()) {
			ids.add(rdto.getIdSedista());
		}
		
		List<AvionskaKarta> listaKarata = kartaRepo.vratiKartePoIdSedista(ids);
		
		List<Sediste> sedista = repozitorijum.vratiSedista(ids);
		
		
		List<Korisnik> korisnici = korisnikRepo.findAll();
		int br = 0;
		for(Sediste sed : sedista) {
			if(!sed.getStatus().equals(StatusSedista.SLOBODNO)) {
				br++;
			}
		}
		if(br != 0) {
			return "greska";
		} else {
			for(RezervacijaKarataDTO rdto : r.getKarte()) {
				for(AvionskaKarta av : listaKarata) {
					if(av.getSediste().getId() == rdto.getIdSedista()) {
						int tmp = 0;
						//AvionskaKarta a = kartaRepo.vratiKartu(rdto.getIdSedista());
						av.setBrojPasosaPutnika(rdto.getBrPasosa());
						av.setBrTelefonaPutnika(rdto.getBrTelefona());
						av.setEmailPutnika(rdto.getEmail());
						av.setImePutnika(rdto.getIme());
						av.setPrezimePutnika(rdto.getPrezime());
						av.setDatum(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
						//a.setRezervacija(rezervacija);
						for(Korisnik kor : korisnici) {
							if(kor.getEmail().equals(rdto.getEmail()) && !k.getEmail().equals(rdto.getEmail())) {
								tmp++;
							}
						}
						//if(tmp == 0) {
							//Sediste s = repozitorijum.vratiSediste(rdto.getIdSedista());
						for(Sediste s : sedista) {	
							s.setStatus(StatusSedista.REZERVISANO);
							repozitorijum.save(s);
						}
						//}
						kartaRepo.save(av);
						rezervacija.getKarte().add(av);
					}
				}
			}
			rezRepo.save(rezervacija);
			return "ok";
		}
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