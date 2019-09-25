package ISA.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.RezervacijaVozilo;

public interface RezervacijaVoziloRepozitorijum extends JpaRepository<RezervacijaVozilo, Long> {
	

	@Query("select count(rez) from RezervacijaVozilo rez where (rez.vozilo.voziloId=?1) and rez.otkazano is false and ((?2<=rez.datumRezervacijaOd and rez.datumRezervacijaOd<=?3) or (?2<=rez.datumRezervacijaDo and rez.datumRezervacijaDo<=?3) or (?2>=rez.datumRezervacijaOd and rez.datumRezervacijaDo>=?3))")
	int getBrojRezervacijaUIntervalu(Long id, Date rezOd, Date rezDo);
	
	@Query("select rezervacija from RezervacijaVozilo rezervacija where rezervacija.emailKorisnika = ?1")
	List<RezervacijaVozilo> vratiRezervacijePoMailu(String email);
	
	@Query("select rezervacija from RezervacijaVozilo rezervacija where rezervacija.idRezVozilo = ?1")
	RezervacijaVozilo vratiRezervacijuPoId(long id);
	
	@Query("select rezervacija from RezervacijaVozilo rezervacija where rezervacija.vozilo.rentACar.rentACarId = ?1")
	List<RezervacijaVozilo> vratiRezervacijePoRentACar(long id);
	
	@Query("select rezervacija from RezervacijaVozilo rezervacija where rezervacija.vozilo.voziloId = ?1")
	List<RezervacijaVozilo> vratiRezervacijuPoVozilu(long id);
	
	@Query("select rezervacija from RezervacijaVozilo rezervacija where rezervacija.vozilo.voziloId = ?1 and rezervacija.otkazano is false")
	List<RezervacijaVozilo> vratiNeOtkazaneRezervacije(long id);

}
