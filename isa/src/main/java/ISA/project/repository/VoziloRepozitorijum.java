package ISA.project.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.model.AvionskaKarta;
import ISA.project.model.Vozilo;

public interface VoziloRepozitorijum extends JpaRepository<Vozilo, Long> {
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	public Vozilo vratiVoziloPoNazivu(@Param("id") long id);
	
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Vozilo vratiVoziloPoId(@Param("id") long id);
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = :id")
	public List<Vozilo> vratiVozilaPoNazivu(@Param("id") long id);
	
	@Query("select spisakVozila from RentACar rentACar where rentACar.rentACarId =:id")
	public List<Vozilo> vratiVozila(@Param("id") long id);
	
	@Query("select vozilo from Vozilo vozilo where vozilo.naPopustu = 'DA'")
	public List<Vozilo> vratiVozilaNaPopustu();
	
	/*@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = ?1")
	public RentACar vratiVoziloPoId(long id);*/
	
	@Query("select rezervacija.trenutniDatumRezervacija, count(rezervacija) from RezervacijaVozilo rezervacija where "
			+ "rezervacija.vozilo.rentACar.rentACarId = :idRentACar and rezervacija.otkazano is false group by day(rezervacija.trenutniDatumRezervacija)")
	public List<Object[]> vratiStatistikuPoDanu(@Param("idRentACar") long idRentACar);
	
	@Query(value = "select concat(year(trenutniDatumRezervacija), '/', week(trenutniDatumRezervacija)), count(*) from RezervacijaVozilo rezervacija where rezervacija.vozilo.rentACar.rentACarId = :idRentACar and rezervacija.otkazano is false group by concat(year(trenutniDatumRezervacija), '/', week(trenutniDatumRezervacija))")
	public List<Object[]> vratiStatistikuPoNedelji(@Param("idRentACar") long idRentACar);
	
	@Query(value = "select year(trenutniDatumRezervacija), count(*) from RezervacijaVozilo rezervacija where rezervacija.vozilo.rentACar.rentACarId = :idRentACar and rezervacija.otkazano is false group by year(trenutniDatumRezervacija)")
	public List<Object[]> vratiStatistikuPoGodini(@Param("idRentACar") long idRentACar);
	
	@Query("select vozilo from Vozilo vozilo where vozilo.emailKorisnika = :emailKorisnik")
	public List<Vozilo> vratiRezervisana(@Param("emailKorisnik") String emailKorisnik);
	
	public List<Vozilo> findAll();
	
	@Modifying
	@Transactional
	void delete(Vozilo vozilo);

}
