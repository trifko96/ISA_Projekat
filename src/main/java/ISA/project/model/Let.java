package ISA.project.model;

import java.util.ArrayList;
import java.util.Date;

public class Let {

	private Date vremePoletanja;
	private Date vremeSletanja;
	private Aerodrom polaznaDestinacija;
	private Aerodrom odredisnaDestinacija;
	private double duzinaPutovanja;
	private ArrayList<Integer> ocene;
	private String segment;
	private int [][] sedista;
	private int [][] karte;
	private ArrayList<String> lokacijePresedanja;
	private int brDestinacija;
	private double cenaKarte;
	private ArrayList<RezervacijaKarata> listaRezervacija;
	private AvioKompanija avioKompanija;
	
	public Let(Date vremePoletanja, Date vremeSletanja, double duzinaPutovanja, String segment, int[][] sedista,
			ArrayList<String> lokacijePresedanja) {
		super();
		this.vremePoletanja = vremePoletanja;
		this.vremeSletanja = vremeSletanja;
		this.duzinaPutovanja = duzinaPutovanja;
		this.segment = segment;
		this.sedista = sedista;
		this.lokacijePresedanja = lokacijePresedanja;
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

	public double getDuzinaPutovanja() {
		return duzinaPutovanja;
	}

	public void setDuzinaPutovanja(double duzinaPutovanja) {
		this.duzinaPutovanja = duzinaPutovanja;
	}

	public ArrayList<Integer> getOcene() {
		return ocene;
	}

	public void setOcene(ArrayList<Integer> ocene) {
		this.ocene = ocene;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public int[][] getSedista() {
		return sedista;
	}

	public void setSedista(int[][] sedista) {
		this.sedista = sedista;
	}

	public int[][] getKarte() {
		return karte;
	}

	public void setKarte(int[][] karte) {
		this.karte = karte;
	}

	public ArrayList<String> getLokacijePresedanja() {
		return lokacijePresedanja;
	}

	public void setLokacijePresedanja(ArrayList<String> lokacijePresedanja) {
		this.lokacijePresedanja = lokacijePresedanja;
	}
	
	public double getCenaKarte() {
		return cenaKarte;
	}

	public int getBrDestinacija() {
		return brDestinacija;
	}

	public void setBrDestinacija(int brDestinacija) {
		this.brDestinacija = brDestinacija;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
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

	public ArrayList<RezervacijaKarata> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<RezervacijaKarata> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	public long trajanjeLeta() {
		return (this.vremeSletanja.getTime() - this.vremePoletanja.getTime());
	}

	public AvioKompanija getAvioKompanija() {
		return avioKompanija;
	}

	public void setAvioKompanija(AvioKompanija avioKompanija) {
		this.avioKompanija = avioKompanija;
	}
}
