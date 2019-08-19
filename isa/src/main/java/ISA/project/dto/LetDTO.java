package ISA.project.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ISA.project.model.Let;

public class LetDTO {

	private long id;
	private Date datumPoletanja;
	private Date datumSletanja;
	private Date datumPolaskaNazad;
	private Date datumDolaskaNazad;
	private AerodromDTO polaznaDestinacija;
	private AerodromDTO odredisnaDestinacija;
	private double ocena;
	private double cenaKarteBiznisKlase;
	private double cenaKarteEkonomskeKlase;
	private double cenaPrveKlase;
	private AvionDTO avion;
	private List<AvionskaKartaDTO> karte = new ArrayList<>();
	private List<LokacijePresedanjaDTO> lokacije = new ArrayList<>();
	private String tip;
	private double popust;
	
	public LetDTO() {
		
	}
	
	public LetDTO(Let l) {
		this.id = l.getIdLeta();
		this.datumPoletanja = l.getVremePoletanja();
		this.datumSletanja = l.getVremeSletanja();
		this.cenaKarteBiznisKlase = l.getCenaKarteBiznisKlase();
		this.cenaKarteEkonomskeKlase = l.getCenaKarteEkonomskeKlase();
		this.cenaPrveKlase = l.getCenaPrveKlase();
		this.datumDolaskaNazad = l.getVremeDolaskaNazad();
		this.datumPolaskaNazad = l.getVremePolaskaNazad();
		this.tip = l.getTip();
		this.popust = l.getPopust();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumPoletanja() {
		return datumPoletanja;
	}

	public void setDatumPoletanja(Date datumPoletanja) {
		this.datumPoletanja = datumPoletanja;
	}

	public Date getDatumSletanja() {
		return datumSletanja;
	}

	public void setDatumSletanja(Date datumSletanja) {
		this.datumSletanja = datumSletanja;
	}

	public Date getDatumPolaskaNazad() {
		return datumPolaskaNazad;
	}

	public void setDatumPolaskaNazad(Date datumPolaskaNazad) {
		this.datumPolaskaNazad = datumPolaskaNazad;
	}

	public Date getDatumDolaskaNazad() {
		return datumDolaskaNazad;
	}

	public void setDatumDolaskaNazad(Date datumDolaskaNazad) {
		this.datumDolaskaNazad = datumDolaskaNazad;
	}

	public AerodromDTO getPolaznaDestinacija() {
		return polaznaDestinacija;
	}

	public void setPolaznaDestinacija(AerodromDTO polaznaDestinacija) {
		this.polaznaDestinacija = polaznaDestinacija;
	}

	public AerodromDTO getOdredisnaDestinacija() {
		return odredisnaDestinacija;
	}

	public void setOdredisnaDestinacija(AerodromDTO odredisnaDestinacija) {
		this.odredisnaDestinacija = odredisnaDestinacija;
	}

	public double getOcena() {
		return ocena;
	}
	
	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public double getCenaKarteBiznisKlase() {
		return cenaKarteBiznisKlase;
	}

	public void setCenaKarteBiznisKlase(double cenaKarteBiznisKlase) {
		this.cenaKarteBiznisKlase = cenaKarteBiznisKlase;
	}

	public double getCenaKarteEkonomskeKlase() {
		return cenaKarteEkonomskeKlase;
	}

	public void setCenaKarteEkonomskeKlase(double cenaKarteEkonomskeKlase) {
		this.cenaKarteEkonomskeKlase = cenaKarteEkonomskeKlase;
	}

	public double getCenaPrveKlase() {
		return cenaPrveKlase;
	}

	public void setCenaPrveKlase(double cenaPrveKlase) {
		this.cenaPrveKlase = cenaPrveKlase;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public AvionDTO getAvion() {
		return avion;
	}

	public void setAvion(AvionDTO avion) {
		this.avion = avion;
	}

	public List<AvionskaKartaDTO> getKarte() {
		return karte;
	}

	public void setKarte(List<AvionskaKartaDTO> karte) {
		this.karte = karte;
	}

	public List<LokacijePresedanjaDTO> getLokacije() {
		return lokacije;
	}

	public void setLokacije(List<LokacijePresedanjaDTO> lokacije) {
		this.lokacije = lokacije;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}
	
	
}
