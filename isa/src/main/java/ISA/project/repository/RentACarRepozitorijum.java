package ISA.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.RentACar;

public interface RentACarRepozitorijum extends JpaRepository<RentACar, Long> {

	public RentACar save(RentACar rentACar);
	
	@Query("select rentCar from RentACar rentCar where rentCar.rentACarId = ?1")
	public RentACar vratiRentACarPoId(long id);
	
	@Query("select rentCar from RentACar rentCar where rentCar.rentACarId != ?1")
	public List<RentACar> vratiRentACar(long id);
	
	@Query("select rentACar from Korisnik korisnik where korisnik.id = ?1")
	public RentACar vratiRentACarPoKorisniku(long id);
}
