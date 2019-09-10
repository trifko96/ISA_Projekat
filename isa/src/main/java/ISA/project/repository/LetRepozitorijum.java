package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.Let;

public interface LetRepozitorijum extends JpaRepository<Let, Long> {

	@Query("select let from Let let where let.avioKompanija.kompanijaId = ?1")
	public List<Let> vratiLetove(long id);
	
	@Query("select let from Let let where let.idLeta = ?1")
	public Let vratiLet(long id);
	
	@Query("select let from Let let where let.avion.idAviona = ?1")
	public Let vratiLetPoAvionu(long id);
	
	@Query("select let from Let let where let.idLeta = ?1")
	public Let vratiLetPoKarti(long id);
}
