package ISA.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OceneVozilo {

	@Id
	@GeneratedValue
	private long id;
	private long idKorisnika;
	private long idVozila;
	
	public OceneVozilo() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public long getIdVozila() {
		return idVozila;
	}

	public void setIdVozila(long idVozila) {
		this.idVozila = idVozila;
	}
	
	
}
