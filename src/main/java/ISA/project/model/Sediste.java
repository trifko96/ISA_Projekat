package ISA.project.model;

import javax.persistence.*;

@Entity
public class Sediste {

	@Id
	@GeneratedValue
	private long idSedista;
	private int brReda;
	private int brKolone;
	private boolean zauzeto;
	
	@OneToOne(mappedBy="sediste")
	@JoinColumn(name="idKarte")
	private AvionskaKarta karta;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="segmentId", referencedColumnName="idSegmenta")
	private Segment segment;

	
	public Sediste(int brReda, int brKolone) {
		super();
		this.brReda = brReda;
		this.brKolone = brKolone;
	}

	public int getBrReda() {
		return brReda;
	}

	public void setBrReda(int brReda) {
		this.brReda = brReda;
	}

	public int getBrKolone() {
		return brKolone;
	}

	public void setBrKolone(int brKolone) {
		this.brKolone = brKolone;
	}

	public boolean isZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	
	public long getId() {
		return idSedista;
	}

	public void setId(long id) {
		this.idSedista = id;
	}

	public AvionskaKarta getKarta() {
		return karta;
	}

	public void setKarta(AvionskaKarta karta) {
		this.karta = karta;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
}
