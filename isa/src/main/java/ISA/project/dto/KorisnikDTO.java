package ISA.project.dto;

import ISA.project.model.Korisnik;
import ISA.project.model.UlogaKorisnika;

public class KorisnikDTO {

	private long id;
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private String grad;
	private String brTelefona;
	private UlogaKorisnika uloga;
	private long avioKompanija;
	private long hotel;
	private long rentACar;
	
	public KorisnikDTO() {
		
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		this.id = korisnik.getId();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.email = korisnik.getEmail();
		this.lozinka = korisnik.getLozinka();
		this.grad = korisnik.getGrad();
		this.brTelefona = korisnik.getBrTelefona();
		this.uloga = korisnik.getUloga();
		if(korisnik.getAvioKompanija() != null)
			this.avioKompanija = korisnik.getAvioKompanija().getId();
		if(korisnik.getRentACar() != null)
			this.rentACar = korisnik.getRentACar().getRentACarId();
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

	public long getAvioKompanija() {
		return avioKompanija;
	}

	public void setAvioKompanija(long avioKompanija) {
		this.avioKompanija = avioKompanija;
	}

	public long getHotel() {
		return hotel;
	}

	public void setHotel(long hotel) {
		this.hotel = hotel;
	}

	public long getRentACar() {
		return rentACar;
	}

	public void setRentACar(long rentACar) {
		this.rentACar = rentACar;
	}
	
	
	
}
