package ISA.project.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.project.dto.KorisnikDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.UlogaKorisnika;
import ISA.project.repository.KorisnikRepozitorijum;

@Service
public class KorisnikServis {
	
	@Autowired
	KorisnikRepozitorijum repozitorijum;
	
	public String registracija(KorisnikDTO korisnik)
	{
		
		Korisnik k1 = repozitorijum.vratiKorisnikaPoEmailu(korisnik.getEmail());
		if(k1 != null)
			return "greska";
		else {
			String pom = "";
			try {
				 pom = enkriptuj(korisnik.getLozinka());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				pom=korisnik.getLozinka();
				e.printStackTrace();
			}
			Korisnik k = new Korisnik(korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(), pom, korisnik.getGrad(), korisnik.getBrTelefona());
			k.setUloga(UlogaKorisnika.OBICAN_KORISNIK);
			k.setVerifikovan(false);
			repozitorijum.save(k);
			return "ok";
		}
	}
	
	public String enkriptuj(String sifra) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		  
		//pretvori sifru  u bajte
        byte[] messageDigest = md.digest(sifra.getBytes()); 
         
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        String povratna=sb.toString();
    	
        return povratna;
	}
	
	public String logovanje(KorisnikDTO korisnik) {
		Korisnik pom = repozitorijum.vratiKorisnikaPoEmailu(korisnik.getEmail());
		if(pom != null) {
			String pomLozinka = "";
			try {
				pomLozinka = enkriptuj(korisnik.getLozinka());
				System.out.println("Lozinka je " + pomLozinka);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!pomLozinka.equals(pom.getLozinka())) {
				return "greska";
			} else {
				if(!pom.getVerifikovan()) {
					return "greska1";
				} else {
					if(pom.getUloga().equals(UlogaKorisnika.OBICAN_KORISNIK))
						return "obican";
					else if(pom.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_AVIOKOMPANIJE))
						return "avio";
					else if(pom.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_HOTELA))
						return "hotel";
					else if(pom.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_RENT_A_CAR))
						return "rent";
					else
						return "sistem";
				}
			}
		} else {
			return "greska";
		}
		
	}
	
	public Korisnik vratiKorisnika(KorisnikDTO k) {
		Korisnik k1 = repozitorijum.vratiKorisnikaPoEmailu(k.getEmail());
		return k1;
	}
	
	public Korisnik vratiKorisnikaMail(String m) {
		Korisnik k1 = repozitorijum.vratiKorisnikaPoEmailu(m);
		return k1;
	}
	
	public String aktivacija(String mail) {
		Korisnik k = repozitorijum.vratiKorisnikaPoEmailu(mail);
		//slanje emaila
		k.setVerifikovan(true);	
		k.setUloga(UlogaKorisnika.OBICAN_KORISNIK);
    	
    	repozitorijum.save(k);
		
		return "Verifikovali ste mail, mozete posetiti sajt.";
	}

}
