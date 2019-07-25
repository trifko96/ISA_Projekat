package ISA.project.model;

public class CeneSoba {

	private Soba soba;
	private int brojNocenja;
	
	public CeneSoba(Soba soba, int brojNocenja) {
		super();
		this.soba = soba;
		this.brojNocenja = brojNocenja;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

	public int getBrojNocenja() {
		return brojNocenja;
	}

	public void setBrojNocenja(int brojNocenja) {
		this.brojNocenja = brojNocenja;
	}
	
	public double izracunajCenu(Soba soba, int brojNocenja) {
		return soba.getCena() * brojNocenja;
	}
}
