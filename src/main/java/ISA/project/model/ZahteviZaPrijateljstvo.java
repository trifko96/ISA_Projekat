package ISA.project.model;

import javax.persistence.*;

@Entity
public class ZahteviZaPrijateljstvo {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="posiljalac")
	private Korisnik posiljalac;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="primalac")
	private Korisnik primalac;
	
	private StatusZahteva status;
	
	public ZahteviZaPrijateljstvo() {
		
	}
	
	public ZahteviZaPrijateljstvo(Korisnik posiljalac, Korisnik primalac, StatusZahteva status) {
		super();
		this.posiljalac = posiljalac;
		this.primalac = primalac;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Korisnik getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(Korisnik posiljalac) {
		this.posiljalac = posiljalac;
	}

	public Korisnik getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Korisnik primalac) {
		this.primalac = primalac;
	}

	public StatusZahteva getStatus() {
		return status;
	}

	public void setStatus(StatusZahteva status) {
		this.status = status;
	}
	
	

}
