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
import ISA.project.dto.DatumiPopustDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.LokacijePresedanjaDTO;
import ISA.project.dto.PretragaVoziloDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.dto.RezervacijaVoziloDTO;
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
import ISA.project.model.RezervacijaVozilo;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.StatusSedista;
import ISA.project.model.Vozilo;
import ISA.project.repository.AvionskaKartaRepozitorijum;
import ISA.project.repository.KorisnikRepozitorijum;
import ISA.project.repository.OceneVoziloRepozitorijum;
import ISA.project.repository.RentACarRepozitorijum;
import ISA.project.repository.RezervacijaRepozitorijum;
import ISA.project.repository.RezervacijaVoziloRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;


@Service
public class VoziloServis {
	
	@Autowired
	VoziloRepozitorijum repozitorijum;
	
	@Autowired
	RezervacijaVoziloRepozitorijum rezVozRepozitorijum;
	
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
		vozi.setDatumPopustOd(vdto.getDatumPopustOd());
 		vozi.setDatumPospustDo(vdto.getDatumPopustDo());
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
		List<RezervacijaVozilo> rezervacije = rezVozRepozitorijum.vratiNeOtkazaneRezervacije(vozi.getVoziloId());
		if(rezervacije.size()==0) {
			rent.obrisiVozilo(vozi);		
			repozitorijum.delete(vozi);
			return "ok";
		}
		else
			return "greska";
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
	
	public List<VoziloDTO> vrati()
	{
		List<Vozilo> vozila = repozitorijum.findAll();
		List<VoziloDTO> vozilaDTO = new ArrayList<>();
		
		for(Vozilo v : vozila) {
			
			vozilaDTO.add(new VoziloDTO(v));
		}
		
		return vozilaDTO;
		
	}
	
	public String vratiVoziloIzmena(VoziloDTO vdto) {
		Vozilo vozi = repozitorijum.vratiVoziloPoNazivu(vdto.getId());	
		List<RezervacijaVozilo> rezervacije = rezVozRepozitorijum.vratiNeOtkazaneRezervacije(vozi.getVoziloId());
		if(rezervacije.size()==0)
			return "ok";
		else
			return "greska";
	}
	
	public void sacuvajVozilo(Vozilo v) {
		repozitorijum.save(v);
	}
	
	public List<VoziloDTO> vratiBrzaVozila(DatumiPopustDTO d){
		List<Vozilo> vozila = repozitorijum.vratiVozilaNaPopustu();
		List<VoziloDTO> vozilaDTO = new ArrayList<>();
		List<RezervacijaVozilo> rezervacije = new ArrayList<>();
		for(Vozilo v : vozila) {
			rezervacije = rezVozRepozitorijum.vratiRezervacijuPoVozilu(v.getVoziloId());
			if(rezervacije.size()==0) {
				if((v.getDatumPopustOd().before(d.getPocetni()) || v.getDatumPopustOd().equals(d.getPocetni())) && (v.getDatumPospustDo().after(d.getKrajnji()) || v.getDatumPospustDo().equals(d.getKrajnji()))) {
					vozilaDTO.add(new VoziloDTO(v));
				}
			}
		}
		return vozilaDTO;
	}
	
	@Transactional
	synchronized public String brzoRezervisi(RezervacijaDTO r, Korisnik k) {
		Rezervacija rezervacija = new Rezervacija();
		RezervacijaVozilo rezVozilo = new RezervacijaVozilo();
		List<Korisnik> korisnici = korisnikRepo.findAll();
		//Vozilo voz = repozitorijum.vratiVoziloPoNazivu(v.getId());
		int br = 0;
		for(RezervacijaKarataDTO rdto : r.getKarte()) {
			Sediste sed = sedisteRepo.vratiSediste(rdto.getIdSedista());
			if(sed.getStatus().equals(StatusSedista.REZERVISANO)) {
				br++;
			}
		}
		if(br != 0) {
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
			rezVozilo.setTrenutniDatumRezervacija(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			rezVozilo.setDatumRezervacijaOd(r.getRezervacijaVozilo().getDatumRezervacijaOd());
			rezVozilo.setEmailKorisnika(r.getRezervacijaVozilo().getEmailKorisnika());
			rezVozilo.setMestoPreuzimanja(r.getRezervacijaVozilo().getMestoPreuzimanja());
			rezVozilo.setMestoVracanja(r.getRezervacijaVozilo().getMestoVracanja());
			rezVozilo.setOtkazano(false);
			Vozilo vozilo = repozitorijum.vratiVoziloPoNazivu(r.getRezervacijaVozilo().getVozilo().getId());
			rezVozilo.setVozilo(vozilo);
			//voz.getRezervacije().add(rezervacija);
			rezVozilo.setDatumRezervacijaDo(r.getRezervacijaVozilo().getDatumRezervacijaDo());
			rezervacija.setRezervacijaVozilo(rezVozilo);
			rezVozRepozitorijum.save(rezVozilo);
			rezervacijaRepo.save(rezervacija);
			return "ok";
		}
	}
	
	public List<VoziloDTO> pretraziVozilo(PretragaVoziloDTO p, long id){
		List<Vozilo> vozila = repozitorijum.findAll();
		List<RezervacijaVozilo> rezervacijeVozila = rezVozRepozitorijum.findAll(); 
		List<Vozilo> lista1 = new ArrayList<>();
		List<Vozilo> lista2 = new ArrayList<>();
		List<VoziloDTO> listaDTO = new ArrayList<>();
		
		for(Vozilo v : vozila) {
			if(v.getTip().equals(p.getTipVozila()) && (v.getBrSedista() >= p.getBrojPutnika()) && (v.getRentACar().getRentACarId() == id)) {
				lista1.add(v);
			}
		}
		
		for(Vozilo v : lista1){
			lista2.add(v);
		}
		
		for(Vozilo v : lista1) {
			int provera = rezVozRepozitorijum.getBrojRezervacijaUIntervalu(v.getVoziloId(), p.getDatumPreuzimanja(), p.getDatumVracanja());
			if(provera != 0) {
				lista2.remove(v);
				continue;
			}
			if(v.getDatumPopustOd()==null)
				continue;
			if((v.getDatumPopustOd().after(p.getPocetni())) && (v.getDatumPospustDo().before(p.getKrajnji()))) {
					lista2.remove(v);
				}	
			
		}
		
		
		for(Vozilo v : lista2) {
			listaDTO.add(new VoziloDTO(v));
		}
		
		return listaDTO;
		
	}
	
	public List<RezervacijaVoziloDTO> vratiRezVozila(Korisnik k){
		List<RezervacijaVozilo> vozila = rezVozRepozitorijum.vratiRezervacijePoMailu(k.getEmail());
		List<RezervacijaVoziloDTO> voziDTO = new ArrayList<RezervacijaVoziloDTO>();
			
		for(RezervacijaVozilo voz : vozila) {
			if(voz.isOtkazano())
				continue;
			else {
			VoziloDTO vozilo = new VoziloDTO(voz.getVozilo());
			RezervacijaVoziloDTO rezDTO = new RezervacijaVoziloDTO(voz);
			rezDTO.setVozilo(vozilo);
			voziDTO.add(rezDTO);
			}
		}
		
		return voziDTO;
		
	}
	
	public List<RezervacijaVoziloDTO> otkaziRezervaciju(long id, Korisnik k){
		RezervacijaVozilo rezervacija = rezVozRepozitorijum.vratiRezervacijuPoId(id);
		rezervacija.setOtkazano(true);
		rezVozRepozitorijum.save(rezervacija);
		//rezVozRepozitorijum.delete(rezervacija);
		List<RezervacijaVoziloDTO> rez = vratiRezVozila(k);
		for(RezervacijaVoziloDTO r : rez) {
			if(rezervacija.getIdRezVozilo()==r.getIdRezVozilo())
				rez.remove(r);
		}
		return rez;
		
	}
	
	public List<RezervacijaVoziloDTO> oceniVozilo(long id, long idr, double ocena){
		OceneVozilo ocene = oceneRepo.vratiOcenu(id, idr);
		if(ocene == null) {
			RezervacijaVozilo r = rezVozRepozitorijum.vratiRezervacijuPoId(idr);
			r.oceniVozilo(ocena);
			r.povecajBrojOcena();
			rezVozRepozitorijum.save(r);
			OceneVozilo o = new OceneVozilo();
			o.setIdRezervacije(idr);
			o.setIdKorisnika(id);
			oceneRepo.save(o);
			Korisnik k = korisnikRepo.vratiKorisnikaPoId(id);
			List<RezervacijaVoziloDTO> lista = vratiRezVozila(k);
			return lista;
		} else {
			return null;
		}
	}

}
