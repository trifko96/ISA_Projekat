package ISA.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Let {

	@Id
	@GeneratedValue
	private long idLeta;
	private Date vremePoletanja;
	private Date vremeSletanja;
	
	@OneToOne
	@JoinColumn(name = "polaznaDestinacijaId")
	private Aerodrom polaznaDestinacija;
	
	@OneToOne
	@JoinColumn(name = "odredisnaDestinacijaId")
	private Aerodrom odredisnaDestinacija;
	
	private double vremePutovanja;
	private double ocene;
	private double brojOcena;
	
	private int brPresedanja;
	private double cenaKarteBiznisKlase;
	private double cenaKarteEkonomskeKlase;
	
	@OneToMany(targetEntity=RezervacijaKarata.class,mappedBy="let", cascade = CascadeType.ALL)
	private List<RezervacijaKarata> listaRezervacija = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="idAviona")
	private Avion avion;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idAvioKompanije", referencedColumnName="kompanijaId")
	private AvioKompanija avioKompanija;
	
	private int brProdatihKarata;
	
	@OneToMany(targetEntity=AvionskaKarta.class,mappedBy="let", cascade = CascadeType.ALL)
	private List<AvionskaKarta> karte = new ArrayList<>();
	
	@OneToMany(targetEntity=LokacijePresedanja.class,mappedBy="let",cascade=CascadeType.ALL)
	private List<LokacijePresedanja> lokacijePresedanja = new ArrayList<>();
	
	public Let() {
		
	}
	
	public Let(Date vremePoletanja, Date vremeSletanja, double vremePutovanja,
			ArrayList<Aerodrom> lokacijePresedanja) {
		super();
		this.vremePoletanja = vremePoletanja;
		this.vremeSletanja = vremeSletanja;
		this.vremePutovanja = vremePutovanja;
	}

	public long getIdLeta() {
		return idLeta;
	}

	public void setIdLeta(long idLeta) {
		this.idLeta = idLeta;
	}

	public Date getVremePoletanja() {
		return vremePoletanja;
	}

	public void setVremePoletanja(Date vremePoletanja) {
		this.vremePoletanja = vremePoletanja;
	}

	public Date getVremeSletanja() {
		return vremeSletanja;
	}

	public void setVremeSletanja(Date vremeSletanja) {
		this.vremeSletanja = vremeSletanja;
	}

	public double getVremePutovanja() {
		return vremePutovanja;
	}

	public void setVremePutovanja(double vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}

	public double getOcene() {
		return ocene;
	}

	public void setOcene(double ocene) {
		this.ocene = ocene;
	}
	
	public double getCenaKarteBiznisKlase() {
		return cenaKarteBiznisKlase;
	}

	public int getBrPresedanja() {
		return brPresedanja;
	}

	public void setBrPresedanja(int brPresedanja) {
		this.brPresedanja = brPresedanja;
	}

	public void setCenaKarteBiznisKlase(double cenaKarte) {
		this.cenaKarteBiznisKlase = cenaKarte;
	}

	public Aerodrom getPolaznaDestinacija() {
		return polaznaDestinacija;
	}

	public void setPolaznaDestinacija(Aerodrom polaznaDestinacija) {
		this.polaznaDestinacija = polaznaDestinacija;
	}

	public Aerodrom getOdredisnaDestinacija() {
		return odredisnaDestinacija;
	}

	public void setOdredisnaDestinacija(Aerodrom odredisnaDestinacija) {
		this.odredisnaDestinacija = odredisnaDestinacija;
	}

	public List<RezervacijaKarata> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<RezervacijaKarata> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	public long trajanjeLeta() {
		return (this.vremeSletanja.getTime() - this.vremePoletanja.getTime());
	}

	public double getCenaKarteEkonomskeKlase() {
		return cenaKarteEkonomskeKlase;
	}

	public void setCenaKarteEkonomskeKlase(double cenaKarteEkonomskeKlase) {
		this.cenaKarteEkonomskeKlase = cenaKarteEkonomskeKlase;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public int getBrProdatihKarata() {
		return brProdatihKarata;
	}

	public void setBrProdatihKarata(int brProdatihKarata) {
		this.brProdatihKarata = brProdatihKarata;
	}

	public List<AvionskaKarta> getKarte() {
		return karte;
	}

	public void setKarte(List<AvionskaKarta> karte) {
		this.karte = karte;
	}

	public AvioKompanija getAvioKompanija() {
		return avioKompanija;
	}

	public void setAvioKompanija(AvioKompanija avioKompanija) {
		this.avioKompanija = avioKompanija;
	}

	public double getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(double brojOcena) {
		this.brojOcena = brojOcena;
	}
}
