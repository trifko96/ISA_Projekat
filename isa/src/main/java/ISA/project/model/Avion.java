package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Avion {

	@Id
	@GeneratedValue
	private long idAviona;
	
	private String ime;
	
	@OneToMany(targetEntity=Segment.class,mappedBy="avion", cascade = CascadeType.ALL)
	private List<Segment> klase = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idAvioKompanije", referencedColumnName="kompanijaId")
	private AvioKompanija avioKompanija;
	
	private boolean slobodan;
	
	public Avion() {
		
	}
	
	public Avion(String ime, List<Segment> klase, AvioKompanija avioKompanija) {
		super();
		this.ime = ime;
		this.klase = klase;
		this.avioKompanija = avioKompanija;
	}

	public long getId() {
		return idAviona;
	}

	public void setId(long id) {
		this.idAviona = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public AvioKompanija getAvioKompanija() {
		return avioKompanija;
	}

	public void setAvioKompanija(AvioKompanija avioKompanija) {
		this.avioKompanija = avioKompanija;
	}

	public List<Segment> getKlasa() {
		return klase;
	}

	public void setKlasa(List<Segment> klase) {
		this.klase = klase;
	}

	public boolean isSlobodan() {
		return slobodan;
	}

	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}
	
	
	
}
