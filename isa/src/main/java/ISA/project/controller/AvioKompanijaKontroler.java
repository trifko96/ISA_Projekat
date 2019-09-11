package ISA.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.dto.DatumskiOpsegDTO;
import ISA.project.dto.PrihodDTO;
import ISA.project.dto.StatistikaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.repository.AvioKompanijaRepozitorijum;
import ISA.project.service.AvioKompanijaServis;
import ISA.project.service.KorisnikServis;

@RestController
@RequestMapping(value="/AvioKompanija")
public class AvioKompanijaKontroler {
	
	@Autowired
	AvioKompanijaServis servis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	AvioKompanijaRepozitorijum avioRep;

	@RequestMapping(value="/vratiKompaniju/{id}", method = RequestMethod.GET)
	public ResponseEntity<AvioKompanijaDTO> vratiAvioKompaniju(@PathVariable long id) {
		
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija a = k.getAvioKompanija();
		AvioKompanijaDTO akd = servis.nadjiKompaniju(a.getId());
		if(akd != null)
			return new ResponseEntity<>(akd, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/izmeniKompaniju", method = RequestMethod.POST)
	public ResponseEntity<AvioKompanijaDTO> izmeniKompaniju(@RequestBody AvioKompanijaDTO aDTO){
		AvioKompanija a = servis.nadjiAvioKompaniju(aDTO.getId());
		AvioKompanijaDTO akd = servis.nadjiKompaniju(aDTO.getId());
		String retVal = servis.vratiKompanije(aDTO);
		if(retVal.equals("ok")) {
			a.setNaziv(aDTO.getNaziv());
			a.setAdresa(aDTO.getAdresa());
			a.setOpis(aDTO.getOpis());
			a.setKoordinata1(aDTO.getKoordinata1());
			a.setKoordinata2(aDTO.getKoordinata2());
			a.setInfoPrtljag(aDTO.getInfoPrtljag());
			servis.sacuvajKompaniju(a);
			return new ResponseEntity<>(akd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/vratiPrihod/{id}", method = RequestMethod.POST)
	public ResponseEntity<PrihodDTO> vratiPrihod(@RequestBody DatumskiOpsegDTO datumDTO, @PathVariable long id){
		//Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik k = korisnikServis.vratiKorisnikaPoId(id);
		AvioKompanija a = k.getAvioKompanija();
		PrihodDTO prihod = servis.vratiPrihod(datumDTO, a);
		return new ResponseEntity<>(prihod, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiStatistikuPoDanu", method = RequestMethod.GET)
	public ResponseEntity<StatistikaDTO> vratiStatistikuPoDanu(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija a = k.getAvioKompanija();
		StatistikaDTO stat = servis.vratiStatistikuPoDanu(a);
		return new ResponseEntity<>(stat, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiStatistikuPoNedelji", method = RequestMethod.GET)
	public ResponseEntity<StatistikaDTO> vratiStatistikuPoNedelji(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija a = k.getAvioKompanija();
		StatistikaDTO stat = servis.vratiStatistikuPoNedelji(a);
		return new ResponseEntity<>(stat, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiStatistikuPoGodini", method = RequestMethod.GET)
	public ResponseEntity<StatistikaDTO> vratiStatistikuPoGodini(@Context HttpServletRequest request){
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		AvioKompanija a = k.getAvioKompanija();
		StatistikaDTO stat = servis.vratiStatistikuPoGodini(a);
		return new ResponseEntity<>(stat, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vratiSveKompanije", method = RequestMethod.GET)
	public ResponseEntity<List<AvioKompanijaDTO>> vratiSveKompanije(){
		List<AvioKompanijaDTO> listaDTO = new ArrayList<>();
		listaDTO = servis.vratiSveKompanije();
		return new ResponseEntity<>(listaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sort/{uslov}", method = RequestMethod.GET)
	public List<AvioKompanija> getSortedRents(@PathVariable String uslov) {
		System.out.println("Uslov je " + uslov);
		
		List<AvioKompanija> svi = avioRep.findAll();
		List<AvioKompanija> sortiranaLista = new ArrayList<AvioKompanija>();

		if (uslov.equals("NameA")) {
			// sortiraj po nazivu od A-Z
			Collections.sort(svi, AvioKompanija.AvioKompanijaComparator);
			for (AvioKompanija A : svi) {
				sortiranaLista.add(A);
			}

		} else  {
			// sortiraj po nazivu od Z-A
			Collections.sort(svi, AvioKompanija.AvioKompanijaComparator);
			for (int i = svi.size() - 1; i >= 0; i--) {
				sortiranaLista.add(svi.get(i));
			}

		
		}
		return sortiranaLista;
		
	}
	
	@RequestMapping(value="/oceniKompaniju/{id}/{id1}/{opcija}", method = RequestMethod.GET)
	public ResponseEntity<List<AvioKompanijaDTO>> oceniKompaniju(@PathVariable long id, @PathVariable long id1, @PathVariable double opcija){
		List<AvioKompanijaDTO> lista = servis.oceniKompaniju(id, id1, opcija);
		if(lista != null) {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
