package ISA.project.model;

import java.util.ArrayList;
import java.util.Date;

public class RezervacijaSmestaja {

	private Date datumDolaska;
	private int brojNocenja;
	private int brojGostiju;
	private int brojSoba; //proveri posle zbog tipova soba
	private ArrayList<Soba> listaSoba;
	
	public RezervacijaSmestaja(Date datumDolaska, int brojNocenja, int brojGostiju, int brojSoba,
			ArrayList<Soba> listaSoba) {
		super();
		this.datumDolaska = datumDolaska;
		this.brojNocenja = brojNocenja;
		this.brojGostiju = brojGostiju;
		this.brojSoba = brojSoba;
		this.listaSoba = listaSoba;
	}

	public Date getDatumDolaska() {
		return datumDolaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		this.datumDolaska = datumDolaska;
	}

	public int getBrojNocenja() {
		return brojNocenja;
	}

	public void setBrojNocenja(int brojNocenja) {
		this.brojNocenja = brojNocenja;
	}

	public int getBrojGostiju() {
		return brojGostiju;
	}

	public void setBrojGostiju(int brojGostiju) {
		this.brojGostiju = brojGostiju;
	}

	public int getBrojSoba() {
		return brojSoba;
	}

	public void setBrojSoba(int brojSoba) {
		this.brojSoba = brojSoba;
	}

	public ArrayList<Soba> getListaSoba() {
		return listaSoba;
	}

	public void setListaSoba(ArrayList<Soba> listaSoba) {
		this.listaSoba = listaSoba;
	}
	
	
	
	
}
