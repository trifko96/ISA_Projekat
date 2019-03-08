package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.project.model.Korisnik;

public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> {

	public Korisnik save(Korisnik korisnik);
	
}
