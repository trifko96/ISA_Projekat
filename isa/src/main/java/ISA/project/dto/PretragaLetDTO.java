package ISA.project.dto;

import java.util.Date;

public class PretragaLetDTO {

	private Date vremePoletanja;
	private Date vremePovratka;
	private String mestoPoletanja;
	private String mestoSletanja;
	private String tip;
	private String klasa;
	private int brOsoba;
	
	public PretragaLetDTO() {
		
	}

	public Date getVremePoletanja() {
		return vremePoletanja;
	}

	public void setVremePoletanja(Date vremePoletanja) {
		this.vremePoletanja = vremePoletanja;
	}

	public Date getVremePovratka() {
		return vremePovratka;
	}

	public void setVremePovratka(Date vremeSletanja) {
		this.vremePovratka = vremeSletanja;
	}

	public String getMestoPoletanja() {
		return mestoPoletanja;
	}

	public void setMestoPoletanja(String mestoPoletanja) {
		this.mestoPoletanja = mestoPoletanja;
	}

	public String getMestoSletanja() {
		return mestoSletanja;
	}

	public void setMestoSletanja(String mestoSletanja) {
		this.mestoSletanja = mestoSletanja;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getKlasa() {
		return klasa;
	}

	public void setKlasa(String klasa) {
		this.klasa = klasa;
	}

	public int getBrOsoba() {
		return brOsoba;
	}

	public void setBrOsoba(int brOsoba) {
		this.brOsoba = brOsoba;
	}
	
	
}
