package ISA.project.dto;

import ISA.project.model.Sediste;
import ISA.project.model.StatusSedista;

public class SedisteDTO {

	private long idSedista;
	private int brReda;
	private int brKolone;
	private boolean zauzeto;
	private StatusSedista status;
	private int natpis;
	private long pom;
	private boolean granica;
	
	public SedisteDTO() {
		
	}
	
	public SedisteDTO(Sediste s) {
		this.idSedista = s.getId();
		this.brReda = s.getBrReda();
		this.brKolone = s.getBrKolone();
		this.zauzeto = s.isZauzeto();
		this.status = s.getStatus();
		this.natpis = s.getNatpis();
		this.granica = s.isGranica();
	}

	public long getId() {
		return idSedista;
	}

	public void setId(long id) {
		this.idSedista = id;
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

	public StatusSedista getStatus() {
		return status;
	}

	public void setStatus(StatusSedista status) {
		this.status = status;
	}

	public int getNatpis() {
		return natpis;
	}

	public void setNatpis(int natpis) {
		this.natpis = natpis;
	}

	public long getPom() {
		return pom;
	}

	public void setPom(long pom) {
		this.pom = pom;
	}

	public boolean isGranica() {
		return granica;
	}

	public void setGranica(boolean granica) {
		this.granica = granica;
	}
	
	
}
