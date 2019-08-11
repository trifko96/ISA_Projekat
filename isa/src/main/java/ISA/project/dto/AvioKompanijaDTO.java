package ISA.project.dto;

import ISA.project.model.AvioKompanija;

public class AvioKompanijaDTO {

	private long id;
	private String naziv;
	private String adresa;
	private double koordinata1;
	private double koordinata2;
	private String opis;
	private double prihod;
	private String infoPrtljag;
	private double ocena;
	private double brojOcena;
	
	public AvioKompanijaDTO() {
		
	}
	
	public AvioKompanijaDTO(AvioKompanija avioKompanija) {
		this.id = avioKompanija.getId();
		this.naziv = avioKompanija.getNaziv();
		this.adresa = avioKompanija.getAdresa();
		this.opis = avioKompanija.getOpis();
		this.prihod = avioKompanija.getPrihod();
		this.infoPrtljag = avioKompanija.getInfoPrtljag();
		this.ocena = avioKompanija.getOcene();
		this.brojOcena = avioKompanija.getBrojOcena();
		this.koordinata1 = avioKompanija.getKoordinata1();
		this.koordinata2 = avioKompanija.getKoordinata2();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getPrihod() {
		return prihod;
	}

	public void setPrihod(double prihod) {
		this.prihod = prihod;
	}

	public String getInfoPrtljag() {
		return infoPrtljag;
	}

	public void setInfoPrtljag(String infoPrtljag) {
		this.infoPrtljag = infoPrtljag;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public double getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(double brojOcena) {
		this.brojOcena = brojOcena;
	}
	
	public double getKoordinata1() {
		return koordinata1;
	}

	public void setKoordinata1(double koordinata1) {
		this.koordinata1 = koordinata1;
	}

	public double getKoordinata2() {
		return koordinata2;
	}

	public void setKoordinata2(double koordinata2) {
		this.koordinata2 = koordinata2;
	}
	
	
}
