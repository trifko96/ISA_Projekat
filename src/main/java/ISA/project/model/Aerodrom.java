package ISA.project.model;

import java.util.ArrayList;
import java.util.UUID;

public class Aerodrom {

	private UUID id;
	private String ime;
	private String grad;
	private ArrayList<AvioKompanija> avioKompanije;
	
	public Aerodrom(String ime, String grad, ArrayList<AvioKompanija> avioKompanije) {
		super();
		this.ime = ime;
		this.grad = grad;
		this.avioKompanije = avioKompanije;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public ArrayList<AvioKompanija> getAvioKompanije() {
		return avioKompanije;
	}

	public void setAvioKompanije(ArrayList<AvioKompanija> avioKompanije) {
		this.avioKompanije = avioKompanije;
	}
	
	
	
}
