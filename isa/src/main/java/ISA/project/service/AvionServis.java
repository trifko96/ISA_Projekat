package ISA.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AvionDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.dto.SegmentDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Avion;
import ISA.project.model.Let;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.repository.AvionRepozitorijum;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;

@Service
public class AvionServis {

	@Autowired
	AvionRepozitorijum repozitorijum;
	
	@Autowired
	SedisteRepozitorijum sedisteRepo;
	
	@Autowired
	LetRepozitorijum letRepo;
	
	public List<AvionDTO> vratiAvione(AvioKompanija a){
		List<Avion> avioni = repozitorijum.vratiAvione(a.getId());
		List<AvionDTO> avioniDTO = new ArrayList<>();
		for(Avion av : avioni) {
			List<SegmentDTO> klase = new ArrayList<>();
			for(Segment seg : av.getKlasa()) {
				List<SedisteDTO> lista = new ArrayList<>();
				for(Sediste s : seg.getListaSedista()) {
					lista.add(new SedisteDTO(s));
				}
				SegmentDTO sd = new SegmentDTO(seg);
				sd.setListaSedista(lista);
				klase.add(sd);
			}
			AvionDTO ad = new AvionDTO(av);
			ad.setKlase(klase);
			avioniDTO.add(ad);
		}
		return avioniDTO;
	}
	
	public List<AvionDTO> vratiAvioneZaLet(AvioKompanija a){
		List<Avion> avioni = repozitorijum.vratiAvione(a.getId());
		List<Avion> avioniPom = new ArrayList<>();
		for(Avion avionPom : avioni) {
			if(avionPom.isSlobodan()) {
				avioniPom.add(avionPom);
			}
		}
		List<AvionDTO> avioniDTO = new ArrayList<>();
		for(Avion av : avioniPom) {
			List<SegmentDTO> klase = new ArrayList<>();
			for(Segment seg : av.getKlasa()) {
				List<SedisteDTO> lista = new ArrayList<>();
				for(Sediste s : seg.getListaSedista()) {
					lista.add(new SedisteDTO(s));
				}
				SegmentDTO sd = new SegmentDTO(seg);
				sd.setListaSedista(lista);
				klase.add(sd);
			}
			AvionDTO ad = new AvionDTO(av);
			ad.setKlase(klase);
			avioniDTO.add(ad);
		}
		return avioniDTO;
	}
	
	public AvionDTO vratiAvion(SedisteDTO s) {
		Sediste sediste = sedisteRepo.vratiSediste(s.getId());
		Avion a = repozitorijum.vratiAvion(sediste.getSegment().getAvion().getId());
		List<SegmentDTO> klase = new ArrayList<>();
		for(Segment seg : a.getKlasa()) {
			List<SedisteDTO> lista = new ArrayList<>();
			for(Sediste sed : seg.getListaSedista()) {
				lista.add(new SedisteDTO(sed));
			}
			SegmentDTO sd = new SegmentDTO(seg);
			sd.setListaSedista(lista);
			klase.add(sd);
		}
		AvionDTO adto = new AvionDTO(a);
		adto.setKlase(klase);
		return adto;
	}
	
	public AvionDTO vratiAvionPoKlasi(long id) {
		Avion a = repozitorijum.vratiAvion(id);
		List<SegmentDTO> klase = new ArrayList<>();
		for(Segment seg : a.getKlasa()) {
			List<SedisteDTO> lista = new ArrayList<>();
			for(Sediste sed : seg.getListaSedista()) {
				lista.add(new SedisteDTO(sed));
			}
			SegmentDTO sd = new SegmentDTO(seg);
			sd.setListaSedista(lista);
			klase.add(sd);
		}
		AvionDTO adto = new AvionDTO(a);
		adto.setKlase(klase);
		return adto;
	}
	
	public String dodajAvion(AvionDTO a, AvioKompanija avio){
		List<Segment> klase = new ArrayList<>();
		
		List<Avion> avioni = repozitorijum.findAll();
		int br = 0;
		for(Avion av : avioni) {
			if(av.getIme().equals(a.getIme()))
				br++;
		}
		if(br != 0) {
			return "greska";
		} else {
			for(SegmentDTO s : a.getKlase()) {
				klase.add(new Segment(s.getTip(), s.getBrojRedova(), s.getBrojKolona()));
			}
			
			Avion avion = new Avion();
			avion.setIme(a.getIme());
			avion.setKlasa(klase);
			avion.setAvioKompanija(avio);
			avion.setSlobodan(true);
			avio.dodajAvion(avion);
			for(Segment s : klase) {
				s.izgenerisiSedista();
				s.setAvion(avion);
			}
			repozitorijum.save(avion);
			return "ok";
		}
	}
	
	public String izmeniAvion(AvionDTO a) {
		Avion av = repozitorijum.vratiAvion(a.getId());
		List<Avion> avioni = repozitorijum.vratiOstaleAvione(a.getId());
		int brojac = 0;
		for(Avion avion : avioni) {
			if(avion.getIme().equals(a.getIme()))
				brojac++;
		}
		if(brojac != 0) {
			return "greska";
		} else {
			av.setIme(a.getIme());
			repozitorijum.save(av);
			return "ok";
		}
	}
	
	public AvionDTO vratiAvionPoLetu(long id) {
		Let l = letRepo.vratiLet(id);
		Avion avion = l.getAvion();
		List<SegmentDTO> klase = new ArrayList<>();
		for(Segment seg : avion.getKlasa()) {
			List<SedisteDTO> lista = new ArrayList<>();
			for(Sediste sed : seg.getListaSedista()) {
				lista.add(new SedisteDTO(sed));
			}
			SegmentDTO sd = new SegmentDTO(seg);
			sd.setListaSedista(lista);
			klase.add(sd);
		}
		AvionDTO adto = new AvionDTO(avion);
		adto.setKlase(klase);
		return adto;
	}
}
