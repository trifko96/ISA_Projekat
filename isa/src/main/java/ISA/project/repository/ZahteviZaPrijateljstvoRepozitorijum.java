package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.ZahteviZaPrijateljstvo;

public interface ZahteviZaPrijateljstvoRepozitorijum extends JpaRepository<ZahteviZaPrijateljstvo, Long> {

	@Query("select zahtev from ZahteviZaPrijateljstvo zahtev where (zahtev.posiljalac.id = ?1 or zahtev.primalac.id = ?1) and zahtev.status = ISA.project.model.StatusZahteva.PRIHVACEN")
	public List<ZahteviZaPrijateljstvo> vratiMojePrijatelje(long id);
	
	@Query("select zahtev from ZahteviZaPrijateljstvo zahtev where zahtev.primalac.id = ?1 and zahtev.status = ISA.project.model.StatusZahteva.POSLAT")
	public List<ZahteviZaPrijateljstvo> vratiMojeZahteve(long id);
	
	@Query("select zahtev from ZahteviZaPrijateljstvo zahtev where zahtev.posiljalac.id = :id1 and zahtev.primalac.id = :id")
	public ZahteviZaPrijateljstvo vratiZahtev(@Param("id") long id, @Param("id1") long id1);
	
	@Query("select zahtev from ZahteviZaPrijateljstvo zahtev where zahtev.posiljalac.id = ?1 or zahtev.primalac.id = ?1")
	public List<ZahteviZaPrijateljstvo> vratiOstaleKorisnike(long id);
	
	@Query("select zahtev from ZahteviZaPrijateljstvo zahtev where zahtev.id = ?1")
	public ZahteviZaPrijateljstvo vratiPoId(long id);
}
