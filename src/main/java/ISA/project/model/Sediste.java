package ISA.project.model;

import java.util.UUID;

public class Sediste {

	private int brReda;
	private int brKolone;
	private boolean zauzeto;
	private UUID id;
	private AvionskaKarta karta;

	
	public Sediste(int brReda, int brKolone) {
		super();
		this.brReda = brReda;
		this.brKolone = brKolone;
	}

	public int getBrReda() {
		return brReda;
	}

	public void setBrReda(int brReda) {
		this.brReda = brReda;
	}

	public int getBrKolone() {
		return brKolone;
	}

	public void setBrKolone(int brKolone) {
		this.brKolone = brKolone;
	}

	public boolean isZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public AvionskaKarta getKarta() {
		return karta;
	}

	public void setKarta(AvionskaKarta karta) {
		this.karta = karta;
	}
}
