package ISA.project.dto;

import ISA.project.model.RentACar;

public class RentACarDTO {
	
	private long id;
	private String naziv;
	private String adresa;
	private String opis;
	private double ocena;
	private double brojOcena;
	private double prihod;
	
	public RentACarDTO(){
		
	}
	
	public RentACarDTO(RentACar rentACar) {
		this.id = rentACar.getRentACarId();
		this.naziv = rentACar.getNaziv();
		this.adresa = rentACar.getAdresa();
		this.opis = rentACar.getOpis();
		this.ocena = rentACar.getOcena();
		this.brojOcena = rentACar.getBrojOcena();
		this.prihod = rentACar.getPrihod();
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

	public double getPrihod() {
		return prihod;
	}

	public void setPrihod(double prihod) {
		this.prihod = prihod;
	}
	
	

}
