package ISA.project.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ISA.project.model.Sediste;

public interface SedisteRepozitorijum extends JpaRepository<Sediste, Long> {

	
	@Query("select sediste from Sediste sediste where sediste.idSedista = ?1")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Sediste vratiSediste(long id);
	
	@Query("select sediste from AvionskaKarta karta where karta.idKarte = ?1")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Sediste vratiSedistePoKarti(long id);
	
	@Query("select sediste from Sediste sediste where sediste.idSedista in :ids")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public List<Sediste> vratiSedista(@Param("ids") ArrayList<Long> ids);
}
