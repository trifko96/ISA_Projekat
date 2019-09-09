package ISA.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.RentACarDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.RentACar;
import ISA.project.repository.RentACarRepozitorijum;



@Service
public class RentACarServis {
	
	@Autowired
	RentACarRepozitorijum repozitorijum;
	
public RentACarDTO nadjiRentCar(long id) {
	
		RentACar rent = repozitorijum.vratiRentACarPoId(id);
		if(rent != null)
			return new RentACarDTO(rent);
		else 
			return null;
	}

public RentACar nadjiRentACar(long id) {
	
	RentACar rent = repozitorijum.vratiRentACarPoId(id);
	if(rent != null)
		return rent;
	else 
		return null;
}

public void sacuvajRentACar(RentACar r) {
	repozitorijum.save(r);
}

public String vratiRentACar(RentACarDTO rdto) {
	int brojac = 0;
	List<RentACar> rent = repozitorijum.vratiRentACar(rdto.getId());
	for(RentACar r : rent) {
		if(r.getNaziv().equals(rdto.getNaziv())) {
			brojac++;
		}
	}
	if(brojac != 0)
		return "greska";
	else
		return "ok";
}

public RentACar nadjiRentACarPoKorisniku(Korisnik k) {
	RentACar r1 = repozitorijum.vratiRentACarPoKorisniku(k.getId());
	return r1;
}

}
