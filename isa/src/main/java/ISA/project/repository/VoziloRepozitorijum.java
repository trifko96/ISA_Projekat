package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.model.Vozilo;

public interface VoziloRepozitorijum extends JpaRepository<Vozilo, Long> {
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	public Vozilo vratiVoziloPoNazivu(@Param("id") long id);
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	public List<Vozilo> vratiVozilaPoNazivu(@Param("id") long id);
	
	@Query("select spisakVozila from RentACar rentACar where rentACar.rentACarId =:id")
	public List<Vozilo> vratiVozila(@Param("id") long id);
	
	/*@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = ?1")
	public RentACar vratiVoziloPoId(long id);*/
	
	public List<Vozilo> findAll();
	
	@Modifying
	@Transactional
	void delete(Vozilo vozilo);

}
