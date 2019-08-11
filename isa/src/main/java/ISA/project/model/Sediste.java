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
	private StatusSedista status;
	
	@OneToOne(mappedBy="sediste")
	@JoinColumn(name="idKarte")
	private AvionskaKarta karta;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="segmentId", referencedColumnName="idSegmenta")
	private Segment segment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rezervacijaId", referencedColumnName="id")
	private RezervacijaKarata rezervisanOd;
	
	private int natpis;
	
	private boolean granica;

	public Sediste() {
		
	}
	
	public Sediste(int brReda, int brKolone, Segment segment, int natpis, StatusSedista status, boolean granica) {
		super();
		this.brReda = brReda;
		this.brKolone = brKolone;
		this.segment = segment;
		this.natpis = natpis;
		this.status = status;
		this.granica = granica;
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

	/*public RezervacijaKarata getRezervisanOd() {
		return rezervisanOd;
	}

	public void setRezervisanOd(RezervacijaKarata rezervisanOd) {
		this.rezervisanOd = rezervisanOd;
	}*/
	
	public int getNatpis() {
		return natpis;
	}

	public void setNatpis(int natpis) {
		this.natpis = natpis;
	}

	public StatusSedista getStatus() {
		return status;
	}

	public void setStatus(StatusSedista status) {
		this.status = status;
	}

	public boolean isGranica() {
		return granica;
	}

	public void setGranica(boolean granica) {
		this.granica = granica;
	}
}
