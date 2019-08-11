package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.Avion;

public interface AvionRepozitorijum extends JpaRepository<Avion, Long> {

	@Query("select avion from Avion avion where avion.avioKompanija.kompanijaId = ?1")
	public List<Avion> vratiAvione(long id);
	
	@Query("select avion from Avion avion where avion.idAviona = ?1")
	public Avion vratiAvion(long id);
	
	public List<Avion> findAll();
	
	@Query("select avion from Avion avion where avion.idAviona != ?1")
	public List<Avion> vratiOstaleAvione(long id);
}
