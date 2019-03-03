package ISA.project.model;

import java.util.ArrayList;

public class Hotel {

	private String ime;
	private String adresa;
	private String opis;
	private ArrayList<Integer> ocene;
	private double prihod;
	private ArrayList<Soba> sobe;
	private int brojSlobodnihSoba;
	private ArrayList<Soba> cenovnik;
	private ArrayList<RezervacijaSmestaja> listaRezervacija;
	private Korisnik administrator;

	public Hotel(String ime, String adresa, String opis, ArrayList<Soba> sobe) {
		super();
		this.ime = ime;
		this.adresa = adresa;
		this.opis = opis;
		this.sobe = sobe;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Integer> getOcene() {
		return ocene;
	}

	public void setOcene(ArrayList<Integer> ocene) {
		this.ocene = ocene;
	}

	public double getPrihod() {
		return prihod;
	}

	public void setPrihod(double prihod) {
		this.prihod = prihod;
	}

	public ArrayList<Soba> getSobe() {
		return sobe;
	}

	public void setSobe(ArrayList<Soba> sobe) {
		this.sobe = sobe;
	}
	
	public int getBrojSlobodnihSoba() {
		return brojSlobodnihSoba;
	}

	public void setBrojSlobodnihSoba(int brojSlobodnihSoba) {
		this.brojSlobodnihSoba = brojSlobodnihSoba;
	}

	public ArrayList<Soba> getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(ArrayList<Soba> cenovnik) {
		this.cenovnik = cenovnik;
	}

	public ArrayList<RezervacijaSmestaja> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<RezervacijaSmestaja> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	public Korisnik getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Korisnik administrator) {
		this.administrator = administrator;
	}
}
