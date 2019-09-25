package ISA.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ISA.project.dto.VoziloDTO;

@Entity
public class RezervacijaVozilo {
	
	@Id
	@GeneratedValue
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
	private boolean otkazano;
	
	@OneToOne
	@JoinColumn(name = "voziloId")
	private Vozilo vozilo;
	
	@OneToOne
	@JoinColumn(name="id")
	private Rezervacija rezervacija;
	
	public RezervacijaVozilo() {}
	
	
	public RezervacijaVozilo(long idRezVozilo, Date datumRezervacijaOd, Date datumRezervacijaDo,
			Date trenutniDatumRezervacija, String emailKorisnika, String mestoPreuzimanja, String mestoVracanja,
			double prosecnaOcena, int brOcena, int ocene, Vozilo vozilo, Rezervacija rezervacija) {
		super();
		this.idRezVozilo = idRezVozilo;
		this.datumRezervacijaOd = datumRezervacijaOd;
		this.datumRezervacijaDo = datumRezervacijaDo;
		this.trenutniDatumRezervacija = trenutniDatumRezervacija;
		this.emailKorisnika = emailKorisnika;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.mestoVracanja = mestoVracanja;
		this.prosecnaOcena = prosecnaOcena;
		this.brOcena = brOcena;
		this.ocene = ocene;
		this.vozilo = vozilo;
		this.rezervacija = rezervacija;
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
	
	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
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
	
	
	public void oceniVozilo(double ocena) {
		this.ocene += ocena;
	}
	
	public void povecajBrojOcena() {
		this.brOcena++;
	}


	public boolean isOtkazano() {
		return otkazano;
	}


	public void setOtkazano(boolean otkazano) {
		this.otkazano = otkazano;
	}
	
	

}
