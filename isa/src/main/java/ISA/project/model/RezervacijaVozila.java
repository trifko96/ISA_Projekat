package ISA.project.model;

import java.sql.Date;
import java.util.ArrayList;

public class RezervacijaVozila {
	
	ArrayList<Vozilo> rezervisanaVozila;
	private Date datumPreuzimanja;
	private Lokacija mestoPreuzimanja;
	private Date datumVracanja;
	private Lokacija mestoVracanja;
	private String tipVozilo;
	private int brPutnika;
	
	
	public RezervacijaVozila(Date datumPreuzimanja, Lokacija mestoPreuzimanja, Date datumVracanja,
			Lokacija mestoVracanja, String tipVozilo, int brPutnika) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.datumVracanja = datumVracanja;
		this.mestoVracanja = mestoVracanja;
		this.tipVozilo = tipVozilo;
		this.brPutnika = brPutnika;
	}


	public ArrayList<Vozilo> getRezervisanaVozila() {
		return rezervisanaVozila;
	}


	public void setRezervisanaVozila(ArrayList<Vozilo> rezervisanaVozila) {
		this.rezervisanaVozila = rezervisanaVozila;
	}


	public Date getDatumPreuzimanja() {
		return datumPreuzimanja;
	}


	public void setDatumPreuzimanja(Date datumPreuzimanja) {
		this.datumPreuzimanja = datumPreuzimanja;
	}


	public Lokacija getMestoPreuzimanja() {
		return mestoPreuzimanja;
	}


	public void setMestoPreuzimanja(Lokacija mestoPreuzimanja) {
		this.mestoPreuzimanja = mestoPreuzimanja;
	}


	public Date getDatumVracanja() {
		return datumVracanja;
	}


	public void setDatumVracanja(Date datumVracanja) {
		this.datumVracanja = datumVracanja;
	}


	public Lokacija getMestoVracanja() {
		return mestoVracanja;
	}


	public void setMestoVracanja(Lokacija mestoVracanja) {
		this.mestoVracanja = mestoVracanja;
	}


	public String getTipVozilo() {
		return tipVozilo;
	}


	public void setTipVozilo(String tipVozilo) {
		this.tipVozilo = tipVozilo;
	}


	public int getBrPutnika() {
		return brPutnika;
	}


	public void setBrPutnika(int brPutnika) {
		this.brPutnika = brPutnika;
	}
	
	
	

}
