package ISA.project.model;

public class Avion {

	private String ime;
	private Segment biznisKlasa;
	private Segment ekonomskaKlasa;
	private AvioKompanija avioKompanija;
	
	public Avion(String ime, Segment biznisKlasa, Segment ekonomskaKlasa, AvioKompanija avioKompanija) {
		super();
		this.ime = ime;
		this.biznisKlasa = biznisKlasa;
		this.ekonomskaKlasa = ekonomskaKlasa;
		this.avioKompanija = avioKompanija;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public AvioKompanija getAvioKompanija() {
		return avioKompanija;
	}

	public void setAvioKompanija(AvioKompanija avioKompanija) {
		this.avioKompanija = avioKompanija;
	}

	public Segment getBiznisKlasa() {
		return biznisKlasa;
	}

	public void setBiznisKlasa(Segment biznisKlasa) {
		this.biznisKlasa = biznisKlasa;
	}

	public Segment getEkonomskaKlasa() {
		return ekonomskaKlasa;
	}

	public void setEkonomskaKlasa(Segment ekonomskaKlasa) {
		this.ekonomskaKlasa = ekonomskaKlasa;
	}
	
	
	
}
