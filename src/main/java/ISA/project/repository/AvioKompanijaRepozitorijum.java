package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.project.model.AvioKompanija;

public interface AvioKompanijaRepozitorijum extends JpaRepository<AvioKompanija, Long> {

	public AvioKompanija save(AvioKompanija avioKompanija);
}
