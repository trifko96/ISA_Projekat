package ISA.project.dto;

import ISA.project.model.StatusZahteva;
import ISA.project.model.ZahteviZaPrijateljstvo;

public class ZahteviZaPrijateljstvoDTO {

	private long id;
	private KorisnikDTO posiljalac;
	private KorisnikDTO primalac;
	private StatusZahteva status;
	
	public ZahteviZaPrijateljstvoDTO() {
		
	}
	
	public ZahteviZaPrijateljstvoDTO(ZahteviZaPrijateljstvo z) {
		this.id = z.getId();
		this.status = z.getStatus();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public KorisnikDTO getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(KorisnikDTO posiljalac) {
		this.posiljalac = posiljalac;
	}

	public KorisnikDTO getPrimalac() {
		return primalac;
	}

	public void setPrimalac(KorisnikDTO primalac) {
		this.primalac = primalac;
	}

	public StatusZahteva getStatus() {
		return status;
	}

	public void setStatus(StatusZahteva status) {
		this.status = status;
	}
	
	
}
