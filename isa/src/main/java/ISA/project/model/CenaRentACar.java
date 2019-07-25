package ISA.project.model;

public class CenaRentACar {
	
	Vozilo vozilo;
	private int brojDana;
	
	public CenaRentACar(Vozilo vozilo, int brojDana) {
		super();
		this.vozilo = vozilo;
		this.brojDana = brojDana;
	}

	
	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public int getBrojDana() {
		return brojDana;
	}

	public void setBrojDana(int brojDana) {
		this.brojDana = brojDana;
	}
	
	public double izracunajCenu(Vozilo vozilo, int brDana) {
		
		return vozilo.getCena() * brDana;
	}
	
	
	
	

}
