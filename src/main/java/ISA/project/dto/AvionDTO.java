package ISA.project.dto;

import ISA.project.model.Avion;

public class AvionDTO {

	private long id;
	private String ime;
	
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
	
	
}
