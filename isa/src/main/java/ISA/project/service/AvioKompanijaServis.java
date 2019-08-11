package ISA.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.repository.AvioKompanijaRepozitorijum;

@Service
public class AvioKompanijaServis {

	@Autowired
	AvioKompanijaRepozitorijum repozitorijum;
	
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
}
