package ISA.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OceneServis {

	@Id
	@GeneratedValue
	private long id;
	private long idKorisnika;
	private long idServisa;
	
	public OceneServis() {
		
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

	public long getIdServisa() {
		return idServisa;
	}

	public void setIdServisa(long idServisa) {
		this.idServisa = idServisa;
	}
	
	
}
