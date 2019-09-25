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
	private long idRezervacije;
	
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

	public long getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(long idRezervacije) {
		this.idRezervacije = idRezervacije;
	}


	
	
}
