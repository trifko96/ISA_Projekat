package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.model.Aerodrom;

public interface AerodromRepozitorijum extends JpaRepository<Aerodrom, Long> {

	@Query("select aerodrom from Aerodrom aerodrom where aerodrom.ime = :ime")
	public Aerodrom vratiAerodromPoImenu(@Param("ime") String ime);
	
	@Query("select aerodromi from AvioKompanija kompanija where kompanija.kompanijaId =:id")
	public List<Aerodrom> vratiAerodrome(@Param("id") long id);
	
	public List<Aerodrom> findAll();
	
	@Modifying
	@Transactional
	void delete(Aerodrom aerodrom);
}
