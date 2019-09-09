package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.model.Lokacija;

public interface LokacijaRepozitorijum extends JpaRepository<Lokacija, Long> {
	
	@Query("select lokacija from Lokacija lokacija where lokacija.lokacijaId = :id")
	public Lokacija vratiLokacijuPoNazivu(@Param("id") long id);
	
	@Query("select lokacija from Lokacija lokacija where lokacija.lokacijaId = :id")
	public List<Lokacija> vratiLokacijePoNazivu(@Param("id") long id);
	
	@Query("select lokacija from Lokacija lokacija where lokacija.adresa = :adresa")
	public Lokacija vratiLokacijuPoAdresi(@Param("adresa") String adresa);
	
	@Query("select lokacije from RentACar rentACar where rentACar.rentACarId =:id")
	public List<Lokacija> vratiLokacije(@Param("id") long id);
	
	public List<Lokacija> findAll();
	
	@Modifying
	@Transactional
	void delete(Lokacija lokacija);

}
