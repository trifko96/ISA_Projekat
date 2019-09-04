package ISA.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.KorisnikDTO;
import ISA.project.dto.ZahteviZaPrijateljstvoDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.StatusZahteva;
import ISA.project.model.UlogaKorisnika;
import ISA.project.model.ZahteviZaPrijateljstvo;
import ISA.project.repository.KorisnikRepozitorijum;
import ISA.project.repository.ZahteviZaPrijateljstvoRepozitorijum;

@Service
public class ZahteviZaPrijateljstvoServis {

	@Autowired
	ZahteviZaPrijateljstvoRepozitorijum repozitorijum;
	
	@Autowired
	KorisnikRepozitorijum repo;
	
	public List<KorisnikDTO> vratiMojePrijatelje(Korisnik k){
		List<ZahteviZaPrijateljstvo> lista = repozitorijum.vratiMojePrijatelje(k.getId());
		List<KorisnikDTO> korisnici = new ArrayList<>();
		for(ZahteviZaPrijateljstvo z : lista) {
			if(z.getPosiljalac().getId() == k.getId()) {
				korisnici.add(new KorisnikDTO(z.getPrimalac()));
			} else {
				korisnici.add(new KorisnikDTO(z.getPosiljalac()));
			}
		}
		return korisnici;
	}
	
	public List<KorisnikDTO> vratiMojeZahteve(Korisnik k){
		List<ZahteviZaPrijateljstvo> lista = repozitorijum.vratiMojeZahteve(k.getId());
		List<KorisnikDTO> korisnici = new ArrayList<>();
		for(ZahteviZaPrijateljstvo z : lista) {
			korisnici.add(new KorisnikDTO(z.getPosiljalac()));
		}
		return korisnici;
	}
	
	public List<KorisnikDTO> vratiOstaleKorisnike(Korisnik k, String ime){
		List<ZahteviZaPrijateljstvo> zahtevi = repozitorijum.vratiOstaleKorisnike(k.getId());
		List<Korisnik> korisnici = repo.findAll();
		List<Korisnik> korisniciPom = new ArrayList<>();
		List<Korisnik> korisniciPom1 = new ArrayList<>();
		for(ZahteviZaPrijateljstvo z : zahtevi) {
			if(z.getPosiljalac().getId() != k.getId()) {
				korisniciPom.add(z.getPosiljalac());
			} else {
				korisniciPom.add(z.getPrimalac());
			}
		}
		for(Korisnik kor : korisnici) {
			int brojac = 0;
			for(Korisnik kp : korisniciPom) {
				if(kor.getId() == kp.getId()) {
					brojac++;
				}
			}
			if(brojac == 0) {
				korisniciPom1.add(kor);
			}
		}
		List<Korisnik> korisniciPom2 = new ArrayList<>();
		List<Korisnik> korisniciPom3 = new ArrayList<>();
		String ime1 = ime.toLowerCase();
		for(Korisnik kor1 : korisniciPom1) {
			String s = (kor1.getIme() + " " + kor1.getPrezime()).toLowerCase();
			if(s.contains(ime1)) {
				korisniciPom2.add(kor1);
			}
		}
		
		for(Korisnik kor2 : korisniciPom2) {
			if(kor2.getUloga().equals(UlogaKorisnika.OBICAN_KORISNIK) && kor2.getId() != k.getId()) {
				korisniciPom3.add(kor2);
			}
		}
		
		List<KorisnikDTO> korDTO = new ArrayList<>();
		for(Korisnik kd : korisniciPom3) {
			korDTO.add(new KorisnikDTO(kd));
		}
		
		return korDTO;
	}
	
	public void obrisiPrijatelja(Korisnik k, long id){
		List<ZahteviZaPrijateljstvo> zahtevi = repozitorijum.findAll();
		ZahteviZaPrijateljstvo zahtev = new ZahteviZaPrijateljstvo();
		for(ZahteviZaPrijateljstvo z : zahtevi) {
			if((z.getPosiljalac().getId() == k.getId()) && (z.getPrimalac().getId() == id)) {
				zahtev = z;
			} else if((z.getPosiljalac().getId() == id) && (z.getPrimalac().getId() == k.getId())) {
				zahtev = z;
			}
		}
		ZahteviZaPrijateljstvo zp = repozitorijum.vratiPoId(zahtev.getId());
		repozitorijum.delete(zp);
	}
	
	public void dodajPrijatelja(Korisnik k, KorisnikDTO kDTO){
		Korisnik korisnik = repo.vratiKorisnikaPoEmailu(kDTO.getEmail());
		ZahteviZaPrijateljstvo z = new ZahteviZaPrijateljstvo(k, korisnik, StatusZahteva.POSLAT);
		repozitorijum.save(z);
	}
	
	public void prihvatiZahtev(Korisnik k, long id){
		ZahteviZaPrijateljstvo z = repozitorijum.vratiZahtev(k.getId(), id);
		z.setStatus(StatusZahteva.PRIHVACEN);
		repozitorijum.save(z);
	}
	
	public void izbrisiZahtev(Korisnik k, long id){
		ZahteviZaPrijateljstvo z = repozitorijum.vratiZahtev(k.getId(), id);
		repozitorijum.delete(z);
	}
	
	public List<KorisnikDTO> vratiOstale(Korisnik k){
		List<ZahteviZaPrijateljstvo> zahtevi = repozitorijum.vratiOstaleKorisnike(k.getId());
		List<Korisnik> korisnici = repo.findAll();
		List<Korisnik> korisniciPom = new ArrayList<>();
		List<Korisnik> korisniciPom1 = new ArrayList<>();
		for(ZahteviZaPrijateljstvo z : zahtevi) {
			if(z.getPosiljalac().getId() != k.getId()) {
				korisniciPom.add(z.getPosiljalac());
			} else {
				korisniciPom.add(z.getPrimalac());
			}
		}
		for(Korisnik kor : korisnici) {
			int brojac = 0;
			for(Korisnik kp : korisniciPom) {
				if(kor.getId() == kp.getId()) {
					brojac++;
				}
			}
			if(brojac == 0) {
				korisniciPom1.add(kor);
			}
		}
		
		List<KorisnikDTO> korDTO = new ArrayList<>();
		for(Korisnik kd : korisniciPom1) {
			korDTO.add(new KorisnikDTO(kd));
		}
		
		return korDTO;
		
	}
	
	public List<KorisnikDTO> vratiPrijateljeZaLet(Korisnik k){
		List<ZahteviZaPrijateljstvo> lista = repozitorijum.vratiMojePrijatelje(k.getId());
		List<KorisnikDTO> korisnici = new ArrayList<>();
		for(ZahteviZaPrijateljstvo z : lista) {
			if(z.getPosiljalac().getId() == k.getId()) {
				korisnici.add(new KorisnikDTO(z.getPrimalac()));
			} else {
				korisnici.add(new KorisnikDTO(z.getPosiljalac()));
			}
		}
		korisnici.add(new KorisnikDTO(k));
		
		return korisnici;
	}
}
