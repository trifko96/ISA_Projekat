package ISA.project.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class AvionskaKarta {
	
	@Id
	@GeneratedValue
	private long idKarte;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLeta", referencedColumnName="idLeta")
	private Let let;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idRezervacije", referencedColumnName="id")
	private Rezervacija rezervacija;
	
	private double cena;
	private double popust;
	private Date datum;
	
	@OneToOne
	@JoinColumn(name="idSedista")
	private Sediste sediste;
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idKorisnika", referencedColumnName="id")
	private Korisnik korisnik;*/
	private String imePutnika;
	private String prezimePutnika;
	private String emailPutnika;
	private String brTelefonaPutnika;
	private String brojPasosaPutnika;
	
	public AvionskaKarta() {
		
	}
	
	public AvionskaKarta(Let let, double cena, Sediste sediste) {
		super();
		this.let = let;
		this.cena = cena;
		this.sediste = sediste;
	}

	public long getIdKarte() {
		return idKarte;
	}

	public void setIdKarte(long idKarte) {
		this.idKarte = idKarte;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	/*public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}*/

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public String getImePutnika() {
		return imePutnika;
	}

	public void setImePutnika(String imePutnika) {
		this.imePutnika = imePutnika;
	}

	public String getPrezimePutnika() {
		return prezimePutnika;
	}

	public void setPrezimePutnika(String prezimePutnika) {
		this.prezimePutnika = prezimePutnika;
	}

	public String getEmailPutnika() {
		return emailPutnika;
	}

	public void setEmailPutnika(String emailPutnika) {
		this.emailPutnika = emailPutnika;
	}

	public String getBrTelefonaPutnika() {
		return brTelefonaPutnika;
	}

	public void setBrTelefonaPutnika(String brTelefonaPutnika) {
		this.brTelefonaPutnika = brTelefonaPutnika;
	}

	public String getBrojPasosaPutnika() {
		return brojPasosaPutnika;
	}

	public void setBrojPasosaPutnika(String brojPasosaPutnika) {
		this.brojPasosaPutnika = brojPasosaPutnika;
	}
	
	
	
	
}
