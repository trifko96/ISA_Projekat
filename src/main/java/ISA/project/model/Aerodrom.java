package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Aerodrom {

	@Id
	@GeneratedValue
	private long aerodromId;
	
	private String ime;
	
	private String grad;
	
	@ManyToMany(mappedBy="aerodromi", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<AvioKompanija> avioKompanije = new ArrayList<>();
	
	public Aerodrom(String ime, String grad, ArrayList<AvioKompanija> avioKompanije) {
		super();
		this.ime = ime;
		this.grad = grad;
		this.avioKompanije = avioKompanije;
	}

	public long getId() {
		return aerodromId;
	}

	public void setId(long id) {
		this.aerodromId = id;
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

	public List<AvioKompanija> getAvioKompanije() {
		return avioKompanije;
	}

	public void setAvioKompanije(ArrayList<AvioKompanija> avioKompanije) {
		this.avioKompanije = avioKompanije;
	}
	
	
	
}
