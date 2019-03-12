package ISA.project.service;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.repository.AvioKompanijaRepozitorijum;

public class AvioKompanijaServis {

	AvioKompanijaRepozitorijum repozitorijum;
	
	public AvioKompanijaDTO nadjiKompaniju(long id) {
		
		AvioKompanija avio = repozitorijum.vratiAvioKompanijuPoId(id);
		if(avio != null)
			return new AvioKompanijaDTO(avio);
		else 
			return null;
	}
}
