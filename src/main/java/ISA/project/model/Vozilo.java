package ISA.project.model;

import java.util.ArrayList;
import java.util.UUID;

public class Vozilo {
	
	private double cena;
	private UUID id;
	private String naziv;
	private String marka;
	private String model;
	private String godinaProizvodnje;
	private int brSedista;
	private String tip;
	private ArrayList<Integer> ocena;
	
	
	public Vozilo(double cena, String naziv, String marka, String model, String godinaProizvodnje, int brSedista,
			String tip) {
		super();
		this.cena = cena;
		this.naziv = naziv;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brSedista = brSedista;
		this.tip = tip;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getMarka() {
		return marka;
	}


	public void setMarka(String marka) {
		this.marka = marka;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getGodinaProizvodnje() {
		return godinaProizvodnje;
	}


	public void setGodinaProizvodnje(String godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}


	public int getBrSedista() {
		return brSedista;
	}


	public void setBrSedista(int brSedista) {
		this.brSedista = brSedista;
	}


	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}


	public ArrayList<Integer> getOcena() {
		return ocena;
	}


	public void setOcena(ArrayList<Integer> ocena) {
		this.ocena = ocena;
	}
	
	
	
	
	

}
