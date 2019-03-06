package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
public class AvioKompanija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long kompanijaId;
	
	private String naziv;
	
	private String adresa;
	
	private String opis;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "AERODROMI_KOMPANIJE", 
			joinColumns= {@JoinColumn(name="kompanijaId")},
			inverseJoinColumns= {@JoinColumn(name="aerodromId")}
	)
	private List<Aerodrom> aerodromi = new ArrayList<>();
	
	@OneToMany(targetEntity=Let.class,mappedBy="avioKompanija", cascade = CascadeType.ALL)
	private List<Let> letovi = new ArrayList<>();
	
	private ArrayList<Integer> ocene;
	
	private double prihod;
	
	private String infoPrtljag;
	
	@OneToMany(targetEntity=Korisnik.class, mappedBy="avioKompanija", cascade = CascadeType.ALL)
	private List<Korisnik> administratori = new ArrayList<>();
	
	@OneToMany(targetEntity=Avion.class, mappedBy="avioKompanija", cascade = CascadeType.ALL)
	private List<Avion> avioni = new ArrayList<>();
	
	public AvioKompanija() {
		
	}
	
	public AvioKompanija(String naziv, String adresa, String opis, ArrayList<Aerodrom> aerodromi, ArrayList<Let> letovi,
			String infoPrtljag) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.aerodromi = aerodromi;
		this.letovi = letovi;
		this.infoPrtljag = infoPrtljag;
	}
	
	public long getId() {
		return kompanijaId;
	}

	public void setId(long id) {
		this.kompanijaId = id;
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

	public List<Aerodrom> getDestinacije() {
		return aerodromi;
	}

	public void setDestinacije(ArrayList<Aerodrom> aerodromi) {
		this.aerodromi = aerodromi;
	}

	public List<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(ArrayList<Let> letovi) {
		this.letovi = letovi;
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

	public String getInfoPrtljag() {
		return infoPrtljag;
	}

	public void setInfoPrtljag(String infoPrtljag) {
		this.infoPrtljag = infoPrtljag;
	}

	public List<Korisnik> getAdministrator() {
		return administratori;
	}

	public void setAdministrator(List<Korisnik> administrator) {
		this.administratori = administrator;
	}

	public List<Avion> getAvioni() {
		return avioni;
	}

	public void setAvioni(ArrayList<Avion> avioni) {
		this.avioni = avioni;
	}
	
	
}
