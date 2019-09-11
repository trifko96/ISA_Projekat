package ISA.project.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;



@Entity
public class AvioKompanija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long kompanijaId;
	
	private String naziv;
	 
	private String adresa;
	
	private double koordinata1;
	
	private double koordinata2;
	
	private String opis;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "AERODROMI_KOMPANIJE", 
			joinColumns= {@JoinColumn(name="kompanijaId")},
			inverseJoinColumns= {@JoinColumn(name="aerodromId")}
	)
	private List<Aerodrom> aerodromi = new ArrayList<>();
	
	@OneToMany(targetEntity=Let.class,mappedBy="avioKompanija", cascade = CascadeType.ALL)
	private List<Let> letovi = new ArrayList<>();
	
	private double prihod;
	
	private String infoPrtljag;
	
	private double ocena;
	
	private double brojOcena;
	
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

	public double getOcene() {
		return ocena;
	}

	public void setOcene(double ocene) {
		this.ocena = ocene;
	}

	public double getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(double brojOcena) {
		this.brojOcena = brojOcena;
	}

	public double getKoordinata1() {
		return koordinata1;
	}

	public void setKoordinata1(double koordinata1) {
		this.koordinata1 = koordinata1;
	}

	public double getKoordinata2() {
		return koordinata2;
	}

	public void setKoordinata2(double koordinata2) {
		this.koordinata2 = koordinata2;
	}
	
	public void dodajAerodrom(Aerodrom a) {
		aerodromi.add(a);
	}
	
	public void obrisiAerodrom(Aerodrom a) {
		aerodromi.remove(a);
	}
	
	public void dodajAvion(Avion a) {
		avioni.add(a);
	}
	
	public void oceniKompaniju(double ocena) {
		this.ocena += ocena;
	}
	
	public void povecajBrojOcena() {
		this.brojOcena++;
	}
	
	public static Comparator<AvioKompanija> AvioKompanijaComparator = new Comparator<AvioKompanija>() {

		public int compare(AvioKompanija a1, AvioKompanija a2) {
			String name1 = a1.getNaziv().toUpperCase();
			String name2 = a2.getNaziv().toUpperCase();

			// sortiranje od A-Z
			return name1.compareTo(name2);

		}
	};
}
