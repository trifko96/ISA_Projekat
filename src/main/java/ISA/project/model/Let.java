package ISA.project.model;

import java.util.ArrayList;
import java.util.Date;

public class Let {

	private Date vremePoletanja;
	private Date vremeSletanja;
	// vreme putovanja???
	private double duzinaPutovanja;
	private ArrayList<Integer> ocene;
	private int segment; //???
	private int [][] sedista;
	private String lokacijePresedanja; // da li treba i broj???
	//cena karte???
	
	public Let(Date vremePoletanja, Date vremeSletanja, double duzinaPutovanja, int segment, int[][] sedista,
			String lokacijePresedanja) {
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

	public int getSegment() {
		return segment;
	}

	public void setSegment(int segment) {
		this.segment = segment;
	}

	public int[][] getSedista() {
		return sedista;
	}

	public void setSedista(int[][] sedista) {
		this.sedista = sedista;
	}

	public String getLokacijePresedanja() {
		return lokacijePresedanja;
	}

	public void setLokacijePresedanja(String lokacijePresedanja) {
		this.lokacijePresedanja = lokacijePresedanja;
	}
	
	
}
