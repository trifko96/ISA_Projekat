package ISA.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.LokacijaDTO;
import ISA.project.model.Lokacija;
import ISA.project.model.RentACar;
import ISA.project.repository.LokacijaRepozitorijum;
import ISA.project.repository.RentACarRepozitorijum;

@Service
public class LokacijaServis {
	
	@Autowired
	LokacijaRepozitorijum repozitorijum;
	
	@Autowired
	RentACarRepozitorijum carRepozitorijum;
	
	
	public Lokacija vratiLokacijuPoAdresi(LokacijaDTO adto) {
		Lokacija l = repozitorijum.vratiLokacijuPoAdresi(adto.getAdresa());
		return l;
	}
	
	public List<LokacijaDTO> dodajLokaciju(RentACar a, LokacijaDTO adto){
		Lokacija aero = new Lokacija();
		aero.setAdresa(adto.getAdresa());
		aero.setRentACar(a);
		//aero.dodajKompaniju(a);
		a.dodajLokaciju(aero);
		repozitorijum.save(aero);
		//carRepozitorijum.save(a);
		//avioRepozitorijum.save(a);
		List<Lokacija> lokacije = repozitorijum.vratiLokacije(a.getRentACarId());
		List<LokacijaDTO> aeroDTO = new ArrayList<LokacijaDTO>();
		for(Lokacija aPom : lokacije) {
			aeroDTO.add(new LokacijaDTO(aPom));
		}
		return aeroDTO;
	}
	
	public List<LokacijaDTO> dodajPostojeci(RentACar a, List<LokacijaDTO> lokacije){
		for(LokacijaDTO adto : lokacije) {
			Lokacija aero = vratiLokacijuPoAdresi(adto);
			//aero.dodajRentACar(a);
			a.dodajLokaciju(aero);
			repozitorijum.save(aero);
		}
		List<LokacijaDTO> povratna = vratiLokacije(a);
		return povratna;
	}
	
	public List<LokacijaDTO> vratiLokacije(RentACar a){
		List<Lokacija> lokacije = repozitorijum.vratiLokacije(a.getRentACarId());
		List<LokacijaDTO> aeroDTO = new ArrayList<LokacijaDTO>();
		for(Lokacija aPom : lokacije) {
			aeroDTO.add(new LokacijaDTO(aPom));
		}
		return aeroDTO;
	}
	
	public List<LokacijaDTO> vratiSlobodneLokacije(RentACar a){
		int brojac = 0;
		List<Lokacija> lokacije = repozitorijum.findAll();
		List<Lokacija> mojeLokacije = repozitorijum.vratiLokacije(a.getRentACarId());
		List<Lokacija> povratna = new ArrayList<>();
		for(Lokacija aero : lokacije) {
			brojac = 0;
			for(Lokacija aeroM : mojeLokacije) {
				if(aero.getAdresa().equals(aeroM.getAdresa())) {
					brojac++;
				}
			}
			if(brojac == 0) {
				povratna.add(aero);
			}
		}
		List<LokacijaDTO> aeroDTO = new ArrayList<>();
		for(Lokacija p : povratna) {
			aeroDTO.add(new LokacijaDTO(p));
		}
		
		return aeroDTO;
	}
	
	public String obrisiLokaciju(LokacijaDTO a, RentACar avio){
		Lokacija aero = repozitorijum.vratiLokacijuPoAdresi(a.getAdresa());
		
		
		//aero.obrisiKompaniju(avio);
		avio.obrisiLokaciju(aero);
		
		repozitorijum.delete(aero);
		
		return "ok";
		
	}
	
	public List<LokacijaDTO> vratiSveLokacije(){
		List<Lokacija> lista = repozitorijum.findAll();
		List<LokacijaDTO> listaDTO = new ArrayList<>();
		for(Lokacija a : lista) {
			listaDTO.add(new LokacijaDTO(a));
		}
		return listaDTO;
	}
	
public LokacijaDTO nadjiLokacijuDTO(long id) {
		
		Lokacija lok = repozitorijum.vratiLokacijuPoNazivu(id);
		if(lok != null)
			return new LokacijaDTO(lok);
		else 
			return null;
	}

	public Lokacija nadjiLokaciju(long id) {
	
		Lokacija lok = repozitorijum.vratiLokacijuPoNazivu(id);
		if(lok != null)
			return lok;
		else 
			return null;
	}
	
	public String vratiLokacijuIzmena(LokacijaDTO ldto) {
		int brojac = 0;
		List<Lokacija> lok = repozitorijum.vratiLokacijePoNazivu(ldto.getId());
		for(Lokacija l : lok) {
			if(l.getAdresa().equals(ldto.getAdresa())) {
				brojac++;
			}
		}
		if(brojac != 0)
			return "greska";
		else
			return "ok";
	}
	
	public void sacuvajLokaciju(Lokacija l) {
		repozitorijum.save(l);
	}

	public List<LokacijaDTO> vratiLokacijePoRent(long id){
		List<Lokacija> lista = repozitorijum.vratiLokacije(id);
		List<LokacijaDTO> listaDTO = new ArrayList<>();
		for(Lokacija l : lista) {
			listaDTO.add(new LokacijaDTO(l));
		}
		return listaDTO;
	}

}
