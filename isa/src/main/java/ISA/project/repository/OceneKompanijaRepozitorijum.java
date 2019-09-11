package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.OceneKompanija;

public interface OceneKompanijaRepozitorijum extends JpaRepository<OceneKompanija, Long> {

	@Query("select ocene from OceneKompanija ocene where ocene.idKorisnika = :idKor and ocene.idKompanije = :idKom")
	OceneKompanija vratiOcenu(@Param("idKor") long idKor, @Param("idKom") long idKom);
}
