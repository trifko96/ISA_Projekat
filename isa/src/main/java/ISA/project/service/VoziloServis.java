package ISA.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AerodromDTO;
import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.dto.AvionDTO;
import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.LokacijePresedanjaDTO;
import ISA.project.dto.PretragaVoziloDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.dto.SegmentDTO;
import ISA.project.dto.VoziloDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Avion;
import ISA.project.model.AvionskaKarta;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.LokacijePresedanja;
import ISA.project.model.OceneKompanija;
import ISA.project.model.OceneVozilo;
import ISA.project.model.RentACar;
import ISA.project.model.Rezervacija;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.StatusSedista;
import ISA.project.model.Vozilo;
import ISA.project.repository.AvionskaKartaRepozitorijum;
import ISA.project.repository.KorisnikRepozitorijum;
import ISA.project.repository.OceneVoziloRepozitorijum;
import ISA.project.repository.RentACarRepozitorijum;
import ISA.project.repository.RezervacijaRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;


@Service
public class VoziloServis {
	
	@Autowired
	VoziloRepozitorijum repozitorijum;
	
	@Autowired
	RentACarRepozitorijum carRepozitorijum;
	
	@Autowired
	SedisteRepozitorijum sedisteRepo;
	
	@Autowired
	AvionskaKartaRepozitorijum kartaRepo;
	
	@Autowired
	KorisnikRepozitorijum korisnikRepo;
	
	@Autowired
	RezervacijaRepozitorijum rezervacijaRepo;
	
	@Autowired
	OceneVoziloRepozitorijum oceneRepo;
	
	public Vozilo vratiVoziloPoNazivu(VoziloDTO vdto) {
		Vozilo v = repozitorijum.vratiVoziloPoNazivu(vdto.getId());
		return v;
	}
	
	public List<VoziloDTO> dodajVozilo(RentACar r, VoziloDTO vdto){
		Vozilo vozi = new Vozilo();
		vozi.setCena(vdto.getCena());
		vozi.setNaziv(vdto.getNaziv());
		vozi.setMarka(vdto.getMarka());
		vozi.setModel(vdto.getModel());
		vozi.setGodinaProizvodnje(vdto.getGodinaProizvodnje());
		vozi.setBrSedista(vdto.getBrSedista());
		vozi.setTip(vdto.getTip());
		vozi.setNaPopustu(vdto.getNaPopustu());
		vozi.setAdresaLokacije(vdto.getAdresaLokacije());
		vozi.setPopust(vdto.getPopust());
		vozi.setRentACar(r);
		//vozi.dodajRentACar(r);
		r.dodajVozilo(vozi);
		repozitorijum.save(vozi);
		//avioRepozitorijum.save(a);
		List<Vozilo> vozila = repozitorijum.vratiVozila(r.getRentACarId());
		List<VoziloDTO> voziDTO = new ArrayList<VoziloDTO>();
		for(Vozilo vPom : vozila) {
			voziDTO.add(new VoziloDTO(vPom));
		}
		return voziDTO;
	}
	
	public List<VoziloDTO> vratiVozila(RentACar r){
		List<Vozilo> vozila = repozitorijum.vratiVozila(r.getRentACarId());
		List<VoziloDTO> voziDTO = new ArrayList<VoziloDTO>();
		for(Vozilo vPom : vozila) {
			voziDTO.add(new VoziloDTO(vPom));
		}
		return voziDTO;
	}
	
	public String obrisiVozilo(VoziloDTO v, RentACar rent){
		Vozilo vozi = repozitorijum.vratiVoziloPoNazivu(v.getId());		
		rent.obrisiVozilo(vozi);		
		repozitorijum.delete(vozi);
		
		return "ok";
		
	}
	
	public VoziloDTO nadjiVoziloDTO(long id) {
		
		Vozilo vozi = repozitorijum.vratiVoziloPoNazivu(id);
		if(vozi != null)
			return new VoziloDTO(vozi);
		else 
			return null;
	}

	public Vozilo nadjiVozilo(long id) {
	
		Vozilo vozi = repozitorijum.vratiVoziloPoNazivu(id);
		if(vozi != null)
			return vozi;
		else 
			return null;
	}
	
	public String vratiVoziloIzmena(VoziloDTO vdto) {
		int brojac = 0;
		List<Vozilo> vozi = repozitorijum.vratiVozilaPoNazivu(vdto.getId());
		for(Vozilo v : vozi) {
			if(v.getNaziv().equals(vdto.getNaziv())) {
				brojac++;
			}
		}
		if(brojac != 0)
			return "greska";
		else
			return "ok";
	}
	
	public void sacuvajVozilo(Vozilo v) {
		repozitorijum.save(v);
	}
	
	public List<VoziloDTO> vratiBrzaVozila(){
		List<Vozilo> vozila = repozitorijum.findAll();
		List<VoziloDTO> vozilaDTO = new ArrayList<>();
		for(Vozilo v : vozila) {
			if(!v.isRezervisano() && v.getNaPopustu().equals("DA")) {
				vozilaDTO.add(new VoziloDTO(v));
			}
		}
		return vozilaDTO;
	}
	
	@Transactional
	synchronized public String brzoRezervisi(RezervacijaDTO r, Korisnik k, VoziloDTO v) {
		Rezervacija rezervacija = new Rezervacija();
		List<Korisnik> korisnici = korisnikRepo.findAll();
		Vozilo voz = repozitorijum.vratiVoziloPoNazivu(v.getId());
		int br = 0;
		for(RezervacijaKarataDTO rdto : r.getKarte()) {
			Sediste sed = sedisteRepo.vratiSediste(rdto.getIdSedista());
			if(sed.getStatus().equals(StatusSedista.REZERVISANO)) {
				br++;
			}
		}
		if(br != 0 || voz.isRezervisano()) {
			return "greska";
		} else {
			for(RezervacijaKarataDTO rdto : r.getKarte()) {
				int tmp = 0;
				AvionskaKarta a = kartaRepo.vratiKartu(rdto.getIdSedista());
				a.setBrojPasosaPutnika(rdto.getBrPasosa());
				a.setBrTelefonaPutnika(rdto.getBrTelefona());
				a.setEmailPutnika(rdto.getEmail());
				a.setImePutnika(rdto.getIme());
				a.setPrezimePutnika(rdto.getPrezime());
				a.setDatum(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				//a.setRezervacija(rezervacija);
				for(Korisnik kor : korisnici) {
					if(kor.getEmail().equals(rdto.getEmail()) && !k.getEmail().equals(rdto.getEmail())) {
						tmp++;
					}
				}
				//if(tmp == 0) {
					Sediste s = sedisteRepo.vratiSediste(rdto.getIdSedista());
					s.setStatus(StatusSedista.REZERVISANO);
					sedisteRepo.save(s);
				//}
				kartaRepo.save(a);
				rezervacija.getKarte().add(a);
			}
			voz.setTrenutniDatum(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			voz.setDatumOd(v.getDatumOd());
			voz.setEmailKorisnika(v.getEmailKorisnika());
			voz.setMestoPreuzimanja(v.getAdresaLokacije());
			voz.setMestoVracanja(v.getAdresaLokacije());
			voz.setRezervisano(true);
			//voz.getRezervacije().add(rezervacija);
			voz.setDatumDo(v.getDatumDo());
			rezervacija.setVozilo(voz);
			rezervacijaRepo.save(rezervacija);
			return "ok";
		}
	}
	
	public List<VoziloDTO> pretraziVozilo(PretragaVoziloDTO p, long id){
		List<Vozilo> vozila = repozitorijum.findAll();
		List<Vozilo> lista1 = new ArrayList<>();
		List<Vozilo> lista2 = new ArrayList<>();
		List<VoziloDTO> listaDTO = new ArrayList<>();
		
		for(Vozilo v : vozila) {
			if(v.getTip().equals(p.getTipVozila()) && (v.getBrSedista() >= p.getBrojPutnika()) && (v.getRentACar().getRentACarId() == id)) {
				lista1.add(v);
			}
		}
		
		for(Vozilo v : lista1) {
			if(v.getDatumDo() != null) {
				if((v.getDatumOd().after(p.getDatumVracanja()) || v.getDatumDo().before(p.getDatumPreuzimanja())) && v.getNaPopustu().equals("NE") && !v.isRezervisano()) {
					lista2.add(v);
					break;
				} 
			} else if(v.getNaPopustu().equals("NE")){
				lista2.add(v);
				break;
			}
		}
		
		for(Vozilo v : lista2) {
			listaDTO.add(new VoziloDTO(v));
		}
		
		return listaDTO;
		
	}
	
	public List<VoziloDTO> vratiRezVozila(Korisnik k){
		List<Vozilo> vozila = repozitorijum.vratiRezervisana(k.getEmail());
		List<VoziloDTO> voziDTO = new ArrayList<VoziloDTO>();
			
		for(Vozilo voz : vozila) {
				voziDTO.add(new VoziloDTO(voz));;
			}
		
		return voziDTO;
		
	}
	
	public List<VoziloDTO> otkaziRezervaciju(long id, Korisnik k){
		Vozilo vozilo = repozitorijum.vratiVoziloPoNazivu(id);
		
		vozilo.setEmailKorisnika("");
		vozilo.setTrenutniDatum(null);
		vozilo.setDatumOd(null);
		vozilo.setDatumDo(null);
		vozilo.setRezervisano(false);
		repozitorijum.save(vozilo);
		
		
		List<VoziloDTO> vozila = vratiRezVozila(k);
		return vozila;
	}
	
	public List<VoziloDTO> oceniVozilo(long id, long idv, double ocena){
		OceneVozilo ocene = oceneRepo.vratiOcenu(id, idv);
		if(ocene == null) {
			Vozilo v = repozitorijum.vratiVoziloPoNazivu(idv);
			v.oceniVozilo(ocena);
			v.povecajBrojOcena();
			repozitorijum.save(v);
			OceneVozilo o = new OceneVozilo();
			o.setIdVozila(idv);
			o.setIdKorisnika(id);
			oceneRepo.save(o);
			Korisnik k = korisnikRepo.vratiKorisnikaPoId(id);
			List<VoziloDTO> lista = vratiRezVozila(k);
			return lista;
		} else {
			return null;
		}
	}

}
