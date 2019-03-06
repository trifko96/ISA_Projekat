package ISA.project.model;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Vozilo {
	
	@Id
	@GeneratedValue
	private long voziloId;
	
	private double cena;
	private String naziv;
	private String marka;
	private String model;
	private String godinaProizvodnje;
	private int brSedista;
	private String tip;
	private ArrayList<Integer> ocena;
	private boolean rezervisano;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rentACarId", referencedColumnName="rentACarId")
	private RentACar rentACar;
	
	
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


	public boolean isRezervisano() {
		return rezervisano;
	}


	public void setRezervisano(boolean rezervisano) {
		this.rezervisano = rezervisano;
	}


	public RentACar getRentACar() {
		return rentACar;
	}


	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}
	
	
	
	
	

}
