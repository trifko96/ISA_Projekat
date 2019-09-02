package ISA.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AerodromDTO;
import ISA.project.model.Aerodrom;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Let;
import ISA.project.model.LokacijePresedanja;
import ISA.project.repository.AerodromRepozitorijum;
import ISA.project.repository.AvioKompanijaRepozitorijum;
import ISA.project.repository.LetRepozitorijum;

@Service
public class AerodromServis {

	@Autowired
	AerodromRepozitorijum repozitorijum;
	
	@Autowired
	AvioKompanijaRepozitorijum avioRepozitorijum;
	
	@Autowired
	LetRepozitorijum letRepo;
	
	public Aerodrom vratiAerodromPoImenu(AerodromDTO adto) {
		Aerodrom a = repozitorijum.vratiAerodromPoImenu(adto.getIme());
		return a;
	}
	
	public List<AerodromDTO> dodajAerodrom(AvioKompanija a, AerodromDTO adto){
		Aerodrom aero = new Aerodrom();
		aero.setGrad(adto.getGrad());
		aero.setIme(adto.getIme());
		aero.dodajKompaniju(a);
		a.dodajAerodrom(aero);
		repozitorijum.save(aero);
		//avioRepozitorijum.save(a);
		List<Aerodrom> aerodromi = repozitorijum.vratiAerodrome(a.getId());
		List<AerodromDTO> aeroDTO = new ArrayList<AerodromDTO>();
		for(Aerodrom aPom : aerodromi) {
			aeroDTO.add(new AerodromDTO(aPom));
		}
		return aeroDTO;
	}
	
	public List<AerodromDTO> dodajPostojeci(AvioKompanija a, List<AerodromDTO> aerodromi){
		for(AerodromDTO adto : aerodromi) {
			Aerodrom aero = vratiAerodromPoImenu(adto);
			aero.dodajKompaniju(a);
			a.dodajAerodrom(aero);
			repozitorijum.save(aero);
		}
		List<AerodromDTO> povratna = vratiAerodrome(a);
		return povratna;
	}
	
	public List<AerodromDTO> vratiAerodrome(AvioKompanija a){
		List<Aerodrom> aerodromi = repozitorijum.vratiAerodrome(a.getId());
		List<AerodromDTO> aeroDTO = new ArrayList<AerodromDTO>();
		for(Aerodrom aPom : aerodromi) {
			aeroDTO.add(new AerodromDTO(aPom));
		}
		return aeroDTO;
	}
	
	public List<AerodromDTO> vratiSlobodneAerodrome(AvioKompanija a){
		int brojac = 0;
		List<Aerodrom> aerodromi = repozitorijum.findAll();
		List<Aerodrom> mojiAerodromi = repozitorijum.vratiAerodrome(a.getId());
		List<Aerodrom> povratna = new ArrayList<>();
		for(Aerodrom aero : aerodromi) {
			brojac = 0;
			for(Aerodrom aeroM : mojiAerodromi) {
				if(aero.getIme().equals(aeroM.getIme())) {
					brojac++;
				}
			}
			if(brojac == 0) {
				povratna.add(aero);
			}
		}
		List<AerodromDTO> aeroDTO = new ArrayList<>();
		for(Aerodrom p : povratna) {
			aeroDTO.add(new AerodromDTO(p));
		}
		
		return aeroDTO;
	}
	
	public String obrisiAerodrom(AerodromDTO a, AvioKompanija avio){
		Aerodrom aero = repozitorijum.vratiAerodromPoImenu(a.getIme());
		List<Let> letovi = letRepo.findAll();
		for(Let l : letovi) {
			if(l.getPolaznaDestinacija().getIme().equals(aero.getIme())) {
				return "greska";
			} else if(l.getOdredisnaDestinacija().getIme().equals(aero.getIme())) {
				return "greska";
			}
		}
		List<LokacijePresedanja> lokacije = new ArrayList<>();
		for(LokacijePresedanja l : lokacije) {
			if(l.getAerodrom().getIme().equals(aero.getIme())) {
				return "greska";
			}
		}
		aero.obrisiKompaniju(avio);
		avio.obrisiAerodrom(aero);
		
		if(aero.getAvioKompanije().size() >= 1)
			repozitorijum.save(aero);
		else
			repozitorijum.delete(aero);
		
		return "ok";
		
	}
	
	public List<AerodromDTO> vratiSveAerodrome(){
		List<Aerodrom> lista = repozitorijum.findAll();
		List<AerodromDTO> listaDTO = new ArrayList<>();
		for(Aerodrom a : lista) {
			listaDTO.add(new AerodromDTO(a));
		}
		return listaDTO;
	}
}
