package ISA.project.model;

import java.util.ArrayList;

public class AvioKompanija {

	private String naziv;
	private String adresa;
	private String opis;
	private ArrayList<Aerodrom> destinacije;
	private ArrayList<Let> letovi;
	private ArrayList<Let> kartePopust;
	private ArrayList<Integer> ocene;
	private double prihod;
	private String infoPrtljag;
	private Korisnik administrator;
	
	public AvioKompanija(String naziv, String adresa, String opis, ArrayList<Aerodrom> destinacije, ArrayList<Let> letovi,
			String infoPrtljag) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.destinacije = destinacije;
		this.letovi = letovi;
		this.infoPrtljag = infoPrtljag;
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

	public ArrayList<Aerodrom> getDestinacije() {
		return destinacije;
	}

	public void setDestinacije(ArrayList<Aerodrom> destinacije) {
		this.destinacije = destinacije;
	}

	public ArrayList<Let> getLetovi() {
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
	
	public ArrayList<Let> getKartePopust() {
		return kartePopust;
	}

	public void setKartePopust(ArrayList<Let> kartePopust) {
		this.kartePopust = kartePopust;
	}

	public Korisnik getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Korisnik administrator) {
		this.administrator = administrator;
	}
	
	
}
