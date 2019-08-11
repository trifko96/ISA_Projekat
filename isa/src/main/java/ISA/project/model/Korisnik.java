package ISA.project.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private String grad;
	private String brTelefona;
	private UlogaKorisnika uloga;
	private Boolean verifikovan;
	private Boolean prvoLogovanje;
	
	@OneToMany(targetEntity=RezervacijaKarata.class,mappedBy="korisnik", cascade = CascadeType.ALL)
	private List<RezervacijaKarata> listaRezervacija = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="avioKompanijaId", referencedColumnName="kompanijaId")
	private AvioKompanija avioKompanija = null;
	
	//private Hotel hotel = null;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IdRentACar", referencedColumnName="rentACarId")
	private RentACar rentACar = null;
	
	@OneToMany(targetEntity=AvionskaKarta.class,mappedBy="korisnik", cascade = CascadeType.ALL)
	private List<AvionskaKarta> karte = new ArrayList<>();
	
	public Korisnik() {
		
	}
	
	public Korisnik(String ime, String prezime, String email, String lozinka, String grad, String brTelefona) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.grad = grad;
		this.brTelefona = brTelefona;
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

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	public AvioKompanija getAvioKompanija() {
		return avioKompanija;
	}

	public void setAvioKompanija(AvioKompanija avioKompanija) {
		this.avioKompanija = avioKompanija;
	}

	/*public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}*/

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public List<RezervacijaKarata> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(List<RezervacijaKarata> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	public List<AvionskaKarta> getKarte() {
		return karte;
	}

	public void setKarte(List<AvionskaKarta> karte) {
		this.karte = karte;
	}
	
	public Boolean getVerifikovan() {
		return verifikovan;
	}

	public void setVerifikovan(Boolean verifikovan) {
		this.verifikovan = verifikovan;
	}

	public Boolean getPrvoLogovanje() {
		return prvoLogovanje;
	}

	public void setPrvoLogovanje(Boolean prvoLogovanje) {
		this.prvoLogovanje = prvoLogovanje;
	}

}
