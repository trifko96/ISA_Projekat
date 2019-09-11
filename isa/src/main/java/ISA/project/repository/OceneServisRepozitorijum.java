package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.OceneKompanija;
import ISA.project.model.OceneServis;

public interface OceneServisRepozitorijum extends JpaRepository<OceneServis, Long> {

	@Query("select ocene from OceneServis ocene where ocene.idKorisnika = :idKor and ocene.idServisa = :idSer")
	OceneServis vratiOcenu(@Param("idKor") long idKor, @Param("idSer") long idSer);
}
