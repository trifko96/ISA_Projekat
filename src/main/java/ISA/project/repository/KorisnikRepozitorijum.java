package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.Korisnik;

public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> {

	public Korisnik save(Korisnik korisnik);
	
	@Query("select korisnik from Korisnik korisnik where korisnik.email = ?1")
	public Korisnik vratiKorisnikaPoEmailu(String email);
}
