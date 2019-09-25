package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.OceneLet;
import ISA.project.model.OceneVozilo;

public interface OceneVoziloRepozitorijum extends JpaRepository<OceneVozilo, Long> {

	@Query("select ocene from OceneVozilo ocene where ocene.idKorisnika = :idKor and ocene.idRezervacije = :idRez")
	OceneVozilo vratiOcenu(@Param("idKor") long idKor, @Param("idRez") long idLet);
}
