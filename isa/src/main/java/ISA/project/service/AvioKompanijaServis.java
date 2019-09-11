package ISA.project.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.dto.DatumskiOpsegDTO;
import ISA.project.dto.PrihodDTO;
import ISA.project.dto.StatistikaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.AvionskaKarta;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.OceneKompanija;
import ISA.project.model.StatusSedista;
import ISA.project.repository.AvioKompanijaRepozitorijum;
import ISA.project.repository.AvionskaKartaRepozitorijum;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.repository.OceneKompanijaRepozitorijum;

@Service
public class AvioKompanijaServis {

	@Autowired
	AvioKompanijaRepozitorijum repozitorijum;
	
	@Autowired
	LetRepozitorijum letRepo;
	
	@Autowired
	AvionskaKartaRepozitorijum kartaRepo;
	
	@Autowired
	OceneKompanijaRepozitorijum oceneRepo;
	
	public AvioKompanijaDTO nadjiKompaniju(long id) {
		
		AvioKompanija avio = repozitorijum.vratiAvioKompanijuPoId(id);
		if(avio != null)
			return new AvioKompanijaDTO(avio);
		else 
			return null;
	}
	
	public AvioKompanija nadjiAvioKompaniju(long id) {
		
		AvioKompanija avio = repozitorijum.vratiAvioKompanijuPoId(id);
		if(avio != null)
			return avio;
		else 
			return null;
	}
	
	public void sacuvajKompaniju(AvioKompanija a) {
		repozitorijum.save(a);
	}
	
	public String vratiKompanije(AvioKompanijaDTO adto) {
		int brojac = 0;
		List<AvioKompanija> avio = repozitorijum.vratiAvioKompanije(adto.getId());
		for(AvioKompanija a : avio) {
			if(a.getNaziv().equals(adto.getNaziv())) {
				brojac++;
			}
		}
		if(brojac != 0)
			return "greska";
		else
			return "ok";
	}
	
	public AvioKompanija nadjiKompanijuPoKorisniku(Korisnik k) {
		AvioKompanija a1 = repozitorijum.vratiKompanijuPoKorisniku(k.getId());
		return a1;
	}
	
	public PrihodDTO vratiPrihod(DatumskiOpsegDTO d, AvioKompanija a) {
		List<Let> letovi = letRepo.vratiLetove(a.getId());
		double prihod = 0;
		for(Let l : letovi) {
			for(AvionskaKarta av : l.getKarte()) {
				if(av.getSediste().getStatus().equals(StatusSedista.REZERVISANO)) {
					if((!(av.getDatum().before(d.getDatum1())) && !(av.getDatum().after(d.getDatum2()))) || (DateUtils.isSameDay(av.getDatum(), d.getDatum1())) || (DateUtils.isSameDay(av.getDatum(), d.getDatum2()))) {
						prihod += av.getCena();
					}
				}
			}
		}
		PrihodDTO prihodDto = new PrihodDTO(prihod, "EUR");
		return prihodDto;
	}
	
	public StatistikaDTO vratiStatistikuPoDanu(AvioKompanija a) {
		List<Object[]> stat = kartaRepo.vratiStatistikuPoDanu(a.getId());
		StatistikaDTO statDTO = new StatistikaDTO();
		
		for(Object[] s : stat) {
			Date dan = (Date) s[0];
			long brojKarata = (long) s[1];
			
			statDTO.dodajLabelu(dan.toString());
			statDTO.dodajVrednost(brojKarata);
		}
		
		return statDTO;
	}
	
	public StatistikaDTO vratiStatistikuPoNedelji(AvioKompanija a) {
		List<Object[]> stat = kartaRepo.vratiStatistikuPoNedelji(a.getId());
		StatistikaDTO statDTO = new StatistikaDTO();
	
		for(Object[] s : stat) {
			statDTO.dodajLabelu((String)s[0]);
			statDTO.dodajVrednost((Long) s[1]);
		}
		
		return statDTO;
	}
	
	public StatistikaDTO vratiStatistikuPoGodini(AvioKompanija a) {
		List<Object[]> stat = kartaRepo.vratiStatistikuPoGodini(a.getId());
		StatistikaDTO statDTO = new StatistikaDTO();
	
		for(Object[] s : stat) {
			statDTO.dodajLabelu(s[0].toString());
			statDTO.dodajVrednost((Long) s[1]);
		}
		
		return statDTO;
	}
	
	public List<AvioKompanijaDTO> vratiSveKompanije(){
		List<AvioKompanija> lista = new ArrayList<>();
		lista = repozitorijum.findAll();
		List<AvioKompanijaDTO> listaDTO = new ArrayList<>();
		for(AvioKompanija a : lista) {
			listaDTO.add(new AvioKompanijaDTO(a));
		}
		
		return listaDTO;
	}
	
	public List<AvioKompanijaDTO> oceniKompaniju(long id, long id1, double ocena){
		OceneKompanija ocene = oceneRepo.vratiOcenu(id, id1);
		if(ocene == null) {
			AvioKompanija a = repozitorijum.vratiAvioKompanijuPoId(id1);
			a.oceniKompaniju(ocena);
			a.povecajBrojOcena();
			repozitorijum.save(a);
			OceneKompanija o = new OceneKompanija();
			o.setIdKompanije(id1);
			o.setIdKorisnika(id);
			oceneRepo.save(o);
			List<AvioKompanija> avio = repozitorijum.findAll();
			List<AvioKompanijaDTO> avioDTO = new ArrayList<>();
			for(AvioKompanija av : avio) {
				avioDTO.add(new AvioKompanijaDTO(av));
			}
			return avioDTO;
		} else {
			return null;
		}
	}
}
