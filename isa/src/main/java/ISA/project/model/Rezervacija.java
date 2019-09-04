package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(targetEntity=AvionskaKarta.class,mappedBy="rezervacija", cascade = CascadeType.ALL)
	private List<AvionskaKarta> karte = new ArrayList<>();
	
	public Rezervacija() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<AvionskaKarta> getKarte() {
		return karte;
	}

	public void setKarte(List<AvionskaKarta> karte) {
		this.karte = karte;
	}
	
	
}
