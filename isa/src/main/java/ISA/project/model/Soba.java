package ISA.project.model;

import java.util.ArrayList;

public class Soba {
	
	private int sprat;
	private TipSobe tip;
	private Hotel hotel;
	private boolean rezervisana;
	private int cena;
	private ArrayList<Integer> ocene;
	
	
	public Soba(int sprat, TipSobe tip, Hotel hotel, int cena) {
		super();
		this.sprat = sprat;
		this.tip = tip;
		this.hotel = hotel;
		this.cena = cena;
	}


	public int getSprat() {
		return sprat;
	}


	public void setSprat(int sprat) {
		this.sprat = sprat;
	}


	public TipSobe getTip() {
		return tip;
	}


	public void setTip(TipSobe tip) {
		this.tip = tip;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public boolean isRezervisana() {
		return rezervisana;
	}


	public void setRezervisana(boolean rezervisana) {
		this.rezervisana = rezervisana;
	}


	public int getCena() {
		return cena;
	}


	public void setCena(int cena) {
		this.cena = cena;
	}


	public ArrayList<Integer> getOcene() {
		return ocene;
	}


	public void setOcene(ArrayList<Integer> ocene) {
		this.ocene = ocene;
	}
	
	
	
	
}
