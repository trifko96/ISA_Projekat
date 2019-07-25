package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.project.model.RentACar;

public interface RentACarRepozitorijum extends JpaRepository<RentACar, Long> {

	public RentACar save(RentACar rentACar);
}
