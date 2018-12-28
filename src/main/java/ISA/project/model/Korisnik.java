package ISA.project.model;

import java.util.ArrayList;

public class Korisnik {

	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private String grad;
	private String brTelefona;
	private UlogaKorisnika uloga;
	private ArrayList<Korisnik> listaPrijatelja;
	
	public Korisnik(String ime, String prezime, String email, String lozinka, String grad, String brTelefona) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.grad = grad;
		this.brTelefona = brTelefona;
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

	public ArrayList<Korisnik> getListaPrijatelja() {
		return listaPrijatelja;
	}

	public void setListaPrijatelja(ArrayList<Korisnik> listaPrijatelja) {
		this.listaPrijatelja = listaPrijatelja;
	}
	
	
}
