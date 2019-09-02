package ISA.project.dto;

import java.util.Date;

import ISA.project.model.Let;

public class LetZaKarteDTO {

	private long id;
	private Date datumPoletanja;
	private Date datumSletanja;
	private AerodromDTO polaznaDestinacija;
	private AerodromDTO odredisnaDestinacija;
	private double popust;
	private String imeAviona;
	
	public LetZaKarteDTO() {
		
	}
	
	public LetZaKarteDTO(Let l) {
		this.id = l.getIdLeta();
		this.datumPoletanja = l.getVremePoletanja();
		this.datumSletanja = l.getVremeSletanja();
		this.popust = l.getPopust();
		this.imeAviona = l.getAvion().getIme();
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

	public AerodromDTO getPolaznaDestinacija() {
		return polaznaDestinacija;
	}

	public void setPolaznaDestinacija(AerodromDTO polaznaDestinacija) {
		this.polaznaDestinacija = polaznaDestinacija;
	}

	public AerodromDTO getOdredisnaDestinacija() {
		return odredisnaDestinacija;
	}

	public void setOdredisnaDestinacija(AerodromDTO odredisnaDestinacija) {
		this.odredisnaDestinacija = odredisnaDestinacija;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}

	public String getImeAviona() {
		return imeAviona;
	}

	public void setImeAviona(String imeAviona) {
		this.imeAviona = imeAviona;
	}
	
	
}
