package ISA.project.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.dto.DatumskiOpsegDTO;
import ISA.project.dto.PrihodDTO;
import ISA.project.dto.LokacijaDTO;
import ISA.project.dto.PretragaServisDTO;
import ISA.project.dto.RentACarDTO;
import ISA.project.dto.StatistikaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.model.Lokacija;
import ISA.project.model.OceneKompanija;
import ISA.project.model.OceneServis;
import ISA.project.model.RentACar;
import ISA.project.model.RezervacijaVozilo;
import ISA.project.model.Vozilo;
import ISA.project.repository.OceneServisRepozitorijum;
import ISA.project.repository.RentACarRepozitorijum;
import ISA.project.repository.RezervacijaVoziloRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;



@Service
public class RentACarServis {
	
	@Autowired
	RentACarRepozitorijum repozitorijum;
	
	@Autowired
	VoziloRepozitorijum vozRepo;
	
	@Autowired
	OceneServisRepozitorijum oceneRepo;
	
	@Autowired
	RezervacijaVoziloRepozitorijum rezervacijaRep;

	public RentACarDTO nadjiRentCar(long id) {
	
		RentACar rent = repozitorijum.vratiRentACarPoId(id);
		if(rent != null)
			return new RentACarDTO(rent);
		else 
			return null;
	}

	public RentACar nadjiRentACar(long id) {
	
		RentACar rent = repozitorijum.vratiRentACarPoId(id);
		if(rent != null)
			return rent;
		else 
			return null;
	}

	public void sacuvajRentACar(RentACar r) {
		repozitorijum.save(r);
	}

	public String vratiRentACar(RentACarDTO rdto) {
		int brojac = 0;
		List<RentACar> rent = repozitorijum.vratiRentACar(rdto.getId());
		for(RentACar r : rent) {
			if(r.getNaziv().equals(rdto.getNaziv())) {
				brojac++;
			}
		}
		if(brojac != 0)
			return "greska";
		else
			return "ok";
	}

	public RentACar nadjiRentACarPoKorisniku(Korisnik k) {
		RentACar r1 = repozitorijum.vratiRentACarPoKorisniku(k.getId());
		return r1;
	}
	
	public List<RentACarDTO> vratiServise(){
		List<RentACar> listaRent = repozitorijum.findAll();
		List<RentACarDTO> lista = new ArrayList<>();
		for(RentACar r : listaRent) {
			lista.add(new RentACarDTO(r));
		}
		return lista;
	}
	
	public List<RentACarDTO> pretraziServise(PretragaServisDTO p){
		List<RentACar> lista1 = repozitorijum.findAll();
		List<RentACar> lista2 = new ArrayList<>();
		List<RentACar> lista3 = new ArrayList<>();
		List<RentACarDTO> listaDTO = new ArrayList<>();
		
		for(RentACar r : lista1) {
			for(Lokacija l : r.getLokacije()) {
				if(l.getAdresa().equals(p.getLokacija())) {
					lista2.add(r);
					break;
				}
			}
		}
		
		for(RentACar r : lista2) {
			for(Vozilo v : r.getSpisakVozila()) {
				if(v.getDatumDo() != null) {
					if((v.getDatumOd().after(p.getDatumDo()) || v.getDatumDo().before(p.getDatumOd())) && v.getNaPopustu().equals("NE") && !v.isRezervisano()) {
						lista3.add(r);
						break;
					} 
				} else if(v.getNaPopustu().equals("NE")){
					lista3.add(r);
					break;
				}
			}
		}
		
		for(RentACar r : lista3) {
			listaDTO.add(new RentACarDTO(r));
		}
		
		return listaDTO;
	}

	public PrihodDTO vratiPrihod(DatumskiOpsegDTO d, RentACar r) {
		List<RezervacijaVozilo> rezervacije = rezervacijaRep.vratiRezervacijePoRentACar(r.getRentACarId());
		double prihod = 0;
			for(RezervacijaVozilo rez : rezervacije) {
					if(!(rez.isOtkazano()) && (!(rez.getTrenutniDatumRezervacija().before(d.getDatum1())) && !(rez.getTrenutniDatumRezervacija().after(d.getDatum2()))) || (DateUtils.isSameDay(rez.getTrenutniDatumRezervacija(), d.getDatum1())) || (DateUtils.isSameDay(rez.getTrenutniDatumRezervacija(), d.getDatum2()))) {
						double vreme = rez.getDatumRezervacijaDo().getTime() - rez.getDatumRezervacijaOd().getTime(); 
						vreme /= (1000*60*60*24);
						prihod += (rez.getVozilo().getCena()*vreme);
					}
				
			}
		PrihodDTO prihodDto = new PrihodDTO(prihod, "EUR");
		return prihodDto;
	}

	public StatistikaDTO vratiStatistikuPoDanu(RentACar r) {
		List<Object[]> stat = vozRepo.vratiStatistikuPoDanu(r.getRentACarId());
		StatistikaDTO statDTO = new StatistikaDTO();
		
		for(Object[] s : stat) {
			Date dan = (Date) s[0];
			long brojRezervacija = (long) s[1];
			
			statDTO.dodajLabelu(dan.toString());
			statDTO.dodajVrednost(brojRezervacija);
		}
		
		return statDTO;
	}

	public StatistikaDTO vratiStatistikuPoNedelji(RentACar r) {
		List<Object[]> stat = vozRepo.vratiStatistikuPoNedelji(r.getRentACarId());
		StatistikaDTO statDTO = new StatistikaDTO();
	
		for(Object[] s : stat) {
			statDTO.dodajLabelu((String)s[0]);
			statDTO.dodajVrednost((Long) s[1]);
		}
		
		return statDTO;
	}

	public StatistikaDTO vratiStatistikuPoGodini(RentACar r) {
		List<Object[]> stat = vozRepo.vratiStatistikuPoGodini(r.getRentACarId());
		StatistikaDTO statDTO = new StatistikaDTO();
	
		for(Object[] s : stat) {
			statDTO.dodajLabelu(s[0].toString());
			statDTO.dodajVrednost((Long) s[1]);
		}
		
		return statDTO;
	}
	
	public List<RentACarDTO> oceniServis(long id, long idr, double ocena){
		OceneServis ocene = oceneRepo.vratiOcenu(id, idr);
		if(ocene == null) {
			RentACar r = repozitorijum.vratiRentACarPoId(idr);
			r.povecajBrojOcena();
			r.oceniServis(ocena);
			repozitorijum.save(r);
			OceneServis o = new OceneServis();
			o.setIdServisa(idr);
			o.setIdKorisnika(id);
			oceneRepo.save(o);
			List<RentACar> rent = repozitorijum.findAll();
			List<RentACarDTO> rentDTO = new ArrayList<>();
			for(RentACar re : rent) {
				rentDTO.add(new RentACarDTO(re));
			}
			return rentDTO;
		} else {
			return null;
		}
	}



}
