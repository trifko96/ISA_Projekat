package ISA.project.dto;

import java.util.ArrayList;
import java.util.List;

import ISA.project.model.Avion;
import ISA.project.model.Segment;

public class AvionDTO {

	private long id;
	private String ime;
	private List<SegmentDTO> klase = new ArrayList<>();
	
	public AvionDTO() {
		
	}
	
	public AvionDTO(Avion avion) {
		this.id = avion.getId();
		this.ime = avion.getIme();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<SegmentDTO> getKlase() {
		return klase;
	}

	public void setKlase(List<SegmentDTO> klase) {
		this.klase = klase;
	}

	
	
}
