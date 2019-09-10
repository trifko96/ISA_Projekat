package ISA.project.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.DatumskiOpsegDTO;
import ISA.project.dto.PrihodDTO;
import ISA.project.dto.LokacijaDTO;
import ISA.project.dto.PretragaServisDTO;
import ISA.project.dto.RentACarDTO;
import ISA.project.dto.StatistikaDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.Lokacija;
import ISA.project.model.RentACar;
import ISA.project.model.Vozilo;
import ISA.project.repository.RentACarRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;



@Service
public class RentACarServis {
	
	@Autowired
	RentACarRepozitorijum repozitorijum;
	
	@Autowired
	VoziloRepozitorijum vozRepo;
	
public RentACarDTO nadjiRentCar(long id) {
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
					if((v.getDatumOd().after(p.getDatumDo()) || v.getDatumDo().before(p.getDatumOd())) && v.getNaPopustu().equals("NE")) {
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
	List<Vozilo> vozila = vozRepo.vratiVozila(r.getRentACarId());
	double prihod = 0;
		for(Vozilo v : vozila) {
			if(v.isRezervisano()) {
				if((!(v.getTrenutniDatum().before(d.getDatum1())) && !(v.getTrenutniDatum().after(d.getDatum2()))) || (DateUtils.isSameDay(v.getTrenutniDatum(), d.getDatum1())) || (DateUtils.isSameDay(v.getTrenutniDatum(), d.getDatum2()))) {
					prihod += v.getCena();
				}
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

}
