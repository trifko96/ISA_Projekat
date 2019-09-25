package ISA.project.dto;

import java.util.Date;

public class PretragaVoziloDTO {

	private Date datumPreuzimanja;
	private Date datumVracanja;
	private String mestoPreuzimanja;
	private String mestoVracanja;
	private String tipVozila;
	private int brojPutnika;
	private Date pocetni;
	private Date krajnji;
	
	public PretragaVoziloDTO() {
		
	}

	public Date getDatumPreuzimanja() {
		return datumPreuzimanja;
	}

	public void setDatumPreuzimanja(Date datumPreuzimanja) {
		this.datumPreuzimanja = datumPreuzimanja;
	}

	public Date getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(Date datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public String getMestoPreuzimanja() {
		return mestoPreuzimanja;
	}

	public void setMestoPreuzimanja(String mestoPreuzimanja) {
		this.mestoPreuzimanja = mestoPreuzimanja;
	}

	public String getMestoVracanja() {
		return mestoVracanja;
	}

	public void setMestoVracanja(String mestoVracanja) {
		this.mestoVracanja = mestoVracanja;
	}

	public String getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(String tipVozila) {
		this.tipVozila = tipVozila;
	}

	public int getBrojPutnika() {
		return brojPutnika;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	public Date getPocetni() {
		return pocetni;
	}

	public void setPocetni(Date pocetni) {
		this.pocetni = pocetni;
	}

	public Date getKrajnji() {
		return krajnji;
	}

	public void setKrajnji(Date krajnji) {
		this.krajnji = krajnji;
	}
	
	
	
}
