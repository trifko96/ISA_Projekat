package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.model.Vozilo;

public interface VoziloRepozitorijum extends JpaRepository<Vozilo, Long> {
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	public Vozilo vratiVoziloPoNazivu(@Param("id") long id);
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	public List<Vozilo> vratiVozilaPoNazivu(@Param("id") long id);
	
	@Query("select spisakVozila from RentACar rentACar where rentACar.rentACarId =:id")
	public List<Vozilo> vratiVozila(@Param("id") long id);
	
	/*@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = ?1")
	public RentACar vratiVoziloPoId(long id);*/
	
	@Query("select vozilo.trenutniDatum, count(vozilo) from Vozilo vozilo where "
			+ "vozilo.rentACar.rentACarId = :idRentACar and vozilo.rezervisano = 1 group by day(vozilo.trenutniDatum)")
	public List<Object[]> vratiStatistikuPoDanu(@Param("idRentACar") long idRentACar);
	
	@Query(value = "select concat(year(trenutniDatum), '/', week(trenutniDatum)), count(*) from Vozilo vozilo where vozilo.rentACar.rentACarId = :idRentACar and vozilo.rezervisano = 1 group by concat(year(trenutniDatum), '/', week(trenutniDatum))")
	public List<Object[]> vratiStatistikuPoNedelji(@Param("idRentACar") long idRentACar);
	
	@Query(value = "select year(trenutniDatum), count(*) from Vozilo vozilo where vozilo.rentACar.rentACarId = :idRentACar and vozilo.rezervisano = 1 group by year(trenutniDatum)")
	public List<Object[]> vratiStatistikuPoGodini(@Param("idRentACar") long idRentACar);
	
	public List<Vozilo> findAll();
	
	@Modifying
	@Transactional
	void delete(Vozilo vozilo);

}
