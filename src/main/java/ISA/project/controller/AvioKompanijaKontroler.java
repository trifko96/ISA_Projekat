package ISA.project.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.service.AvioKompanijaServis;

@RestController
@RequestMapping(value="/AvioKompanija")
public class AvioKompanijaKontroler {
	
	AvioKompanijaServis servis;

	@RequestMapping(value="/vratiKompaniju/{id}", method = RequestMethod.GET)
	public AvioKompanijaDTO vratiAvioKompaniju(@PathVariable long id) {
		
		AvioKompanijaDTO akd = servis.nadjiKompaniju(id);
		if(akd != null)
			return akd;
		else
			return null;
	}
}
