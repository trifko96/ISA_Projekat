package ISA.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.service.AvioKompanijaServis;

@RestController
@RequestMapping(value="/AvioKompanija")
public class AvioKompanijaKontroler {
	
	@Autowired
	AvioKompanijaServis servis;

	@RequestMapping(value="/vratiKompaniju", method = RequestMethod.GET)
	public AvioKompanijaDTO vratiAvioKompaniju(@Context HttpServletRequest request) {
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija a = k.getAvioKompanija();
		AvioKompanijaDTO akd = servis.nadjiKompaniju(a.getId());
		if(akd != null)
			return akd;
		else
			return null;
	}
}
