package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.model.Vozilo;

public interface VoziloRepozitorijum extends JpaRepository<Vozilo, Long> {
	
	@Query("select vozilo from Vozilo vozilo where vozilo.naziv = :naziv")
	public Vozilo vratiVoziloPoNazivu(@Param("naziv") String naziv);
	
	@Query("select spisakVozila from RentACar rentACar where rentACar.rentACarId =:id")
	public List<Vozilo> vratiVozila(@Param("id") long id);
	
	public List<Vozilo> findAll();
	
	@Modifying
	@Transactional
	void delete(Vozilo vozilo);

}
