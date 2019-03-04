package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Segment {

	@Id
	@GeneratedValue
	private long idSegmenta;
	private TipKlase tip;
	private int brojRedova;
	private int brojKolona;
	
	@OneToMany(targetEntity = Sediste.class, mappedBy="segment", cascade = CascadeType.ALL)
	private List<Sediste> listaSedista = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="avionId", referencedColumnName="idAviona")
	private Avion avion;
	
	
	public Segment(TipKlase tip, int brojRedova, int brojKolona) {
		super();
		this.tip = tip;
		this.brojRedova = brojRedova;
		this.brojKolona = brojKolona;
	}

	public long getId() {
		return idSegmenta;
	}

	public void setId(long id) {
		this.idSegmenta = id;
	}

	public TipKlase getTip() {
		return tip;
	}

	public void setTip(TipKlase tip) {
		this.tip = tip;
	}

	public int getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(int brojRedova) {
		this.brojRedova = brojRedova;
	}

	public int getBrojKolona() {
		return brojKolona;
	}

	public void setBrojKolona(int brojKolona) {
		this.brojKolona = brojKolona;
	}


	public List<Sediste> getListaSedista() {
		return listaSedista;
	}

	public void setListaSedista(List<Sediste> listaSedista) {
		this.listaSedista = listaSedista;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	
	
}
