package ISA.project.model;

import java.util.UUID;

public class Lokacija {
	
	private String adresa;
	private RentACar rentACar;
	private UUID id;
	
	
	public Lokacija(String adresa, RentACar rentACar) {
		super();
		this.adresa = adresa;
		this.rentACar = rentACar;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public RentACar getRentACar() {
		return rentACar;
	}


	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}
	
	
	
	

}
