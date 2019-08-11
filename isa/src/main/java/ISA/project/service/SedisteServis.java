package ISA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.SedisteDTO;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.repository.SedisteRepozitorijum;
import ISA.project.repository.SegmentRepozitorijum;

@Service
public class SedisteServis {

	@Autowired
	SedisteRepozitorijum repozitorijum;
	
	@Autowired
	SegmentRepozitorijum segmentRepozitorijum;
	
	public void izmeniSediste(SedisteDTO s) {
		Sediste sediste = repozitorijum.vratiSediste(s.getId());
		sediste.setStatus(s.getStatus());
		repozitorijum.save(sediste);
	}
	
	public Segment dodajSediste(SedisteDTO s) {
		Segment segment = segmentRepozitorijum.vratiKlasu(s.getPom());
		Sediste sediste = new Sediste(s.getBrReda(), s.getBrKolone(), segment, s.getNatpis(), s.getStatus(), s.isGranica());
		segment.dodajSediste(sediste);
		segmentRepozitorijum.save(segment);
		return segment;
	}
	
}
