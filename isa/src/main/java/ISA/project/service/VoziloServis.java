package ISA.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.VoziloDTO;
import ISA.project.model.RentACar;
import ISA.project.model.Vozilo;
import ISA.project.repository.RentACarRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;


@Service
public class VoziloServis {
	
	@Autowired
	VoziloRepozitorijum repozitorijum;
	
	@Autowired
	RentACarRepozitorijum carRepozitorijum;
	
	public Vozilo vratiVoziloPoNazivu(VoziloDTO vdto) {
		Vozilo v = repozitorijum.vratiVoziloPoNazivu(vdto.getNaziv());
		return v;
	}
	
	public List<VoziloDTO> dodajVozilo(RentACar r, VoziloDTO vdto){
		Vozilo vozi = new Vozilo();
		vozi.setNaziv(vdto.getNaziv());
		vozi.setMarka(vdto.getMarka());
		vozi.setModel(vdto.getModel());
		vozi.setGodinaProizvodnje(vdto.getGodinaProizvodnje());
		vozi.setBrSedista(vdto.getBrSedista());
		vozi.setNaPopustu(vdto.getNaPopustu());
		vozi.setAdresaLokacije(vdto.getAdresaLokacije());
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
		Vozilo vozi = repozitorijum.vratiVoziloPoNazivu(v.getNaziv());		
		rent.obrisiVozilo(vozi);		
		repozitorijum.delete(vozi);
		
		return "ok";
		
	}
	
	

}
