package ISA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.model.AvioKompanija;
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
}
