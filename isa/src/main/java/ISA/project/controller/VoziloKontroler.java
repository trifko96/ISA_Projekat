package ISA.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.VoziloDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.RentACar;
import ISA.project.model.Vozilo;
import ISA.project.service.RentACarServis;
import ISA.project.service.VoziloServis;


@RestController
@RequestMapping(value="/Vozilo")
public class VoziloKontroler {
	
	@Autowired
	VoziloServis servis;
	
	@Autowired
	RentACarServis carServis;
	
	@RequestMapping(value = "/dodajNovo", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> dodajNovo(@RequestBody VoziloDTO vdto, @Context HttpServletRequest request){
		Vozilo v = servis.vratiVoziloPoNazivu(vdto);
		if(v == null) {
			Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
			//AvioKompanija avio = k.getAvioKompanija();
			RentACar rent = carServis.nadjiRentACarPoKorisniku(k);
			List<VoziloDTO> pom = servis.dodajVozilo(rent, vdto);
			return new ResponseEntity<>(pom, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/vratiVozilo", method = RequestMethod.GET)
	public ResponseEntity<List<VoziloDTO>> vratiVozilo(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar rent = k.getRentACar();
		List<VoziloDTO> pom = servis.vratiVozila(rent);
		return new ResponseEntity<>(pom, HttpStatus.OK);
	}
	
	@RequestMapping(value="/obrisiVozilo", method = RequestMethod.POST)
	public ResponseEntity<List<VoziloDTO>> obrisiVozilo(@RequestBody VoziloDTO vdto, @Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		RentACar rent = carServis.nadjiRentACarPoKorisniku(k);
		String s = servis.obrisiVozilo(vdto, rent);
		List<VoziloDTO> lista = servis.vratiVozila(rent);
		if(s == "ok")
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/izmeniVozilo", method = RequestMethod.POST)
	public ResponseEntity<VoziloDTO> izmeniVozilo(@RequestBody VoziloDTO vDTO){
		Vozilo v = servis.nadjiVozilo(vDTO.getId());
		VoziloDTO vkd = servis.nadjiVoziloDTO(vDTO.getId());
		String retVal = servis.vratiVoziloIzmena(vDTO);
		if(retVal.equals("ok")) { 
			v.setCena(vDTO.getCena());
			v.setNaziv(vDTO.getNaziv());
			v.setMarka(vDTO.getMarka());
			v.setModel(vDTO.getModel());
			v.setGodinaProizvodnje(vDTO.getGodinaProizvodnje());
			v.setBrSedista(vDTO.getBrSedista());
			v.setTip(vDTO.getTip());
			v.setNaPopustu(vDTO.getNaPopustu());
			v.setAdresaLokacije(vDTO.getAdresaLokacije());
			v.setPopust(vDTO.getPopust());
			servis.sacuvajVozilo(v);
			return new ResponseEntity<>(vkd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
