package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.AvioKompanija;

public interface AvioKompanijaRepozitorijum extends JpaRepository<AvioKompanija, Long> {

	public AvioKompanija save(AvioKompanija avioKompanija);
	
	@Query("select kompanija from AvioKompanija kompanija where kompanija.kompanijaId = ?1")
	public AvioKompanija vratiAvioKompanijuPoId(long id);
	
	@Query("select kompanija from AvioKompanija kompanija where kompanija.kompanijaId != ?1")
	public List<AvioKompanija> vratiAvioKompanije(long id);
	
	@Query("select avioKompanija from Korisnik korisnik where korisnik.id = ?1")
	public AvioKompanija vratiKompanijuPoKorisniku(long id);
}
