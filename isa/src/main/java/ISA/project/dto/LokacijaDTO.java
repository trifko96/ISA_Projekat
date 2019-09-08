package ISA.project.dto;

import ISA.project.model.Lokacija;

public class LokacijaDTO {
	
	private long id;
	private String adresa;
	
	public LokacijaDTO() {}
	
	public LokacijaDTO(Lokacija lokacija)
	{
		this.id = lokacija.getLokacijaId();
		this.adresa = lokacija.getAdresa();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	

}
