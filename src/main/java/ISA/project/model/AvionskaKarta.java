package ISA.project.model;

import javax.persistence.*;

@Entity
public class AvionskaKarta {
	
	@Id
	@GeneratedValue
	private long idKarte;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLeta", referencedColumnName="idLeta")
	private Let let;
	
	private double cena;
	private double popust;
	
	@OneToOne
	@JoinColumn(name="idSedista")
	private Sediste sediste;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idKorisnika", referencedColumnName="id")
	private Korisnik korisnik;
	
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

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
	
	
}
