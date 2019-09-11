package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.OceneKompanija;
import ISA.project.model.OceneLet;

public interface OceneLetRepozitorijum extends JpaRepository<OceneLet, Long> {

	@Query("select ocene from OceneLet ocene where ocene.idKorisnika = :idKor and ocene.idLeta = :idLet")
	OceneLet vratiOcenu(@Param("idKor") long idKor, @Param("idLet") long idLet);
}
