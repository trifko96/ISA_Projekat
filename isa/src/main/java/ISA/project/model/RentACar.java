package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class RentACar {
	
	@Id
	@GeneratedValue
	private long rentACarId;
	
	private String naziv;
	private String adresa;
	private String opis;
	
	@OneToMany(targetEntity=Vozilo.class, mappedBy="rentACar", cascade = CascadeType.ALL)
	private List<Vozilo> spisakVozila = new ArrayList<>();
	
	@OneToMany(targetEntity=Lokacija.class, mappedBy="rentACar", cascade = CascadeType.ALL)
	private List<Lokacija> lokacije = new ArrayList<>();
	
	//private List<Vozilo> cenovnik = new ArrayList<>();
	
	private ArrayList<Integer> ocene;
	private double prihod;
	private ArrayList<RezervacijaVozila> listaRezervacija;
	
	@OneToMany(targetEntity=Korisnik.class, mappedBy="rentACar", cascade = CascadeType.ALL)
	private List<Korisnik> administratori = new ArrayList<>();
	
	public RentACar() {
		
	}
	
	public RentACar(String naziv, String adresa, String opis, ArrayList<Vozilo> spisakVozila) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.spisakVozila = spisakVozila;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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

	public List<Vozilo> getSpisakVozila() {
		return spisakVozila;
	}

	public void setSpisakVozila(List<Vozilo> spisakVozila) {
		this.spisakVozila = spisakVozila;
	}

	/*public List<Vozilo> getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(ArrayList<Vozilo> cenovnik) {
		this.cenovnik = cenovnik;
	}*/

	public List<Lokacija> getLokacije() {
		return lokacije;
	}

	public void setLokacije(List<Lokacija> lokacije) {
		this.lokacije = lokacije;
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

	public ArrayList<RezervacijaVozila> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<RezervacijaVozila> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	public long getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(long rentACarId) {
		this.rentACarId = rentACarId;
	}

	public List<Korisnik> getAdministratori() {
		return administratori;
	}

	public void setAdministratori(List<Korisnik> administratori) {
		this.administratori = administratori;
	}

	

	
	

}
