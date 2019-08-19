package ISA.project.dto;

import java.util.Date;

import ISA.project.model.LokacijePresedanja;

public class LokacijePresedanjaDTO {

	private long id;
	private Date datumPoletanja;
	private Date datumSletanja;
	private AerodromDTO aerodrom;
	
	public LokacijePresedanjaDTO() {
		
	}
	
	public LokacijePresedanjaDTO(LokacijePresedanja l) {
		this.id = l.getIdLokacije();
		this.datumPoletanja = l.getVremePoletanja();
		this.datumSletanja = l.getVremeSletanja();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumPoletanja() {
		return datumPoletanja;
	}

	public void setDatumPoletanja(Date datumPoletanja) {
		this.datumPoletanja = datumPoletanja;
	}

	public Date getDatumSletanja() {
		return datumSletanja;
	}

	public void setDatumSletanja(Date datumSletanja) {
		this.datumSletanja = datumSletanja;
	}

	public AerodromDTO getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(AerodromDTO aerodrom) {
		this.aerodrom = aerodrom;
	}
	
	
}
