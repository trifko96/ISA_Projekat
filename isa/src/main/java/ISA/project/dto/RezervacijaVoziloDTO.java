package ISA.project.dto;

import java.util.Date;

import ISA.project.model.RezervacijaVozilo;

public class RezervacijaVoziloDTO {
	
	private long idRezVozilo;
	private Date datumRezervacijaOd;
	private Date datumRezervacijaDo;
	private Date trenutniDatumRezervacija;
	private String emailKorisnika;
	private String mestoPreuzimanja;
	private String mestoVracanja;
	private double prosecnaOcena;
	private int brOcena;
	private int ocene;
	
	
	private VoziloDTO vozilo;
	
	public RezervacijaVoziloDTO() {
		
	}
	
	public RezervacijaVoziloDTO(RezervacijaVozilo vozilo)
	{
		this.idRezVozilo = vozilo.getIdRezVozilo();
		this.datumRezervacijaOd = vozilo.getDatumRezervacijaOd();
		this.datumRezervacijaDo = vozilo.getDatumRezervacijaDo();
		this.trenutniDatumRezervacija = vozilo.getTrenutniDatumRezervacija();
		this.emailKorisnika = vozilo.getEmailKorisnika();
		this.mestoPreuzimanja = vozilo.getMestoPreuzimanja();
		this.mestoVracanja = vozilo.getMestoVracanja();	
		this.prosecnaOcena = vozilo.getProsecnaOcena();
		this.brOcena = vozilo.getBrOcena();
		this.ocene = vozilo.getOcene();
	}
	
	
	
	public long getIdRezVozilo() {
		return idRezVozilo;
	}

	public void setIdRezVozilo(long idRezVozilo) {
		this.idRezVozilo = idRezVozilo;
	}

	public Date getDatumRezervacijaOd() {
		return datumRezervacijaOd;
	}
	public void setDatumRezervacijaOd(Date datumRezervacijaOd) {
		this.datumRezervacijaOd = datumRezervacijaOd;
	}
	public Date getDatumRezervacijaDo() {
		return datumRezervacijaDo;
	}
	public void setDatumRezervacijaDo(Date datumRezervacijaDo) {
		this.datumRezervacijaDo = datumRezervacijaDo;
	}
	public Date getTrenutniDatumRezervacija() {
		return trenutniDatumRezervacija;
	}
	public void setTrenutniDatumRezervacija(Date trenutniDatumRezervacija) {
		this.trenutniDatumRezervacija = trenutniDatumRezervacija;
	}
	public String getEmailKorisnika() {
		return emailKorisnika;
	}
	public void setEmailKorisnika(String emailKorisnika) {
		this.emailKorisnika = emailKorisnika;
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

	public VoziloDTO getVozilo() {
		return vozilo;
	}

	public void setVozilo(VoziloDTO vozilo) {
		this.vozilo = vozilo;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public int getBrOcena() {
		return brOcena;
	}

	public void setBrOcena(int brOcena) {
		this.brOcena = brOcena;
	}

	public int getOcene() {
		return ocene;
	}

	public void setOcene(int ocene) {
		this.ocene = ocene;
	}

	
	
	

}
