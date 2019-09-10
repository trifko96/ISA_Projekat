package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.Sediste;

public interface SedisteRepozitorijum extends JpaRepository<Sediste, Long> {

	@Query("select sediste from Sediste sediste where sediste.idSedista = ?1")
	public Sediste vratiSediste(long id);
	
	@Query("select sediste from AvionskaKarta karta where karta.idKarte = ?1")
	public Sediste vratiSedistePoKarti(long id);
}
