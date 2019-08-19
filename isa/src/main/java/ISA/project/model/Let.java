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
	private Date vremePolaskaNazad;
	private Date vremeDolaskaNazad;
	
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
	private double cenaPrveKlase;
	private double popust;
	private String tip;
	
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
		this.ocene = 0;
		this.brojOcena = 0;
		this.brProdatihKarata = 0;
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

	public Date getVremePolaskaNazad() {
		return vremePolaskaNazad;
	}

	public void setVremePolaskaNazad(Date vremePolaskaNazad) {
		this.vremePolaskaNazad = vremePolaskaNazad;
	}

	public Date getVremeDolaskaNazad() {
		return vremeDolaskaNazad;
	}

	public void setVremeDolaskaNazad(Date vremeDolaskaNazad) {
		this.vremeDolaskaNazad = vremeDolaskaNazad;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
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

	public double getCenaPrveKlase() {
		return cenaPrveKlase;
	}

	public void setCenaPrveKlase(double cenaPrveKlase) {
		this.cenaPrveKlase = cenaPrveKlase;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}
	
	public void oceni(double ocena) {
		this.ocene += ocena;
	}
	
	public void povecajBrojOcena() {
		this.brojOcena++;
	}

	public List<LokacijePresedanja> getLokacijePresedanja() {
		return lokacijePresedanja;
	}

	public void setLokacijePresedanja(List<LokacijePresedanja> lokacijePresedanja) {
		this.lokacijePresedanja = lokacijePresedanja;
	}
	
	public void generisiKarte() {
		double cena;
		for(Segment s : avion.getKlasa()) {
			if(s.getTip().equals(TipKlase.PRVA)) {
				cena = cenaPrveKlase;
			} else if(s.getTip().equals(TipKlase.BIZNIS)) {
				cena = cenaKarteBiznisKlase;
			} else {
				cena = cenaKarteEkonomskeKlase;
			}
			
			for(Sediste sed : s.getListaSedista()) {
				if(sed.getStatus().equals(StatusSedista.BRZA_REZERVACIJA)) {
					cena = cena - (cena*popust / 100);
				}
				karte.add(new AvionskaKarta(this, cena, sed));
			}
		}
	}
}
