package ISA.project.model;

import java.util.ArrayList;
import java.util.Date;

public class Let {

	private Date vremePoletanja;
	private Date vremeSletanja;
	private Aerodrom polaznaDestinacija;
	private Aerodrom odredisnaDestinacija;
	private double vremePutovanja;
	private ArrayList<Integer> ocene;
	private ArrayList<Aerodrom> lokacijePresedanja;
	private int brPresedanja;
	private double cenaKarteBiznisKlase;
	private double cenaKarteEkonomskeKlase;
	private ArrayList<RezervacijaKarata> listaRezervacija;
	private AvioKompanija avioKompanija;
	private Avion avion;
	private int brProdatihKarata;
	
	
	public Let(Date vremePoletanja, Date vremeSletanja, double vremePutovanja,
			ArrayList<Aerodrom> lokacijePresedanja) {
		super();
		this.vremePoletanja = vremePoletanja;
		this.vremeSletanja = vremeSletanja;
		this.vremePutovanja = vremePutovanja;
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

	public double getVremePutovanja() {
		return vremePutovanja;
	}

	public void setVremePutovanja(double vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}

	public ArrayList<Integer> getOcene() {
		return ocene;
	}

	public void setOcene(ArrayList<Integer> ocene) {
		this.ocene = ocene;
	}

	public ArrayList<Aerodrom> getLokacijePresedanja() {
		return lokacijePresedanja;
	}

	public void setLokacijePresedanja(ArrayList<Aerodrom> lokacijePresedanja) {
		this.lokacijePresedanja = lokacijePresedanja;
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
}
