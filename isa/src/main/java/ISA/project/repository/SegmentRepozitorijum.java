package ISA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ISA.project.model.Segment;

public interface SegmentRepozitorijum extends JpaRepository<Segment, Long> {

	@Query("select segment from Segment segment where segment.idSegmenta = ?1")
	public Segment vratiKlasu(long id);
}
