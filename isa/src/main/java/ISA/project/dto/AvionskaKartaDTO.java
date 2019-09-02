package ISA.project.dto;

import ISA.project.model.AvionskaKarta;

public class AvionskaKartaDTO {

	private long id;
	private double cena;
	private int brSedista;
	private String klasa;
	private LetZaKarteDTO let;
	
	public AvionskaKartaDTO() {
		
	}
	
	public AvionskaKartaDTO(AvionskaKarta a) {
		this.id = a.getIdKarte();
		this.cena = a.getCena();
		this.brSedista = a.getSediste().getNatpis();
		this.klasa = a.getSediste().getSegment().getTip().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getBrSedista() {
		return brSedista;
	}

	public void setBrSedista(int brSedista) {
		this.brSedista = brSedista;
	}

	public String getKlasa() {
		return klasa;
	}

	public void setKlasa(String klasa) {
		this.klasa = klasa;
	}

	public LetZaKarteDTO getLet() {
		return let;
	}

	public void setLet(LetZaKarteDTO let) {
		this.let = let;
	}
	
	
}
