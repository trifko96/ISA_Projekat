package ISA.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OceneLet {

	@Id
	@GeneratedValue
	private long id;
	private long idKorisnika;
	private long idLeta;
	
	public OceneLet() {
		
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

	public long getIdLeta() {
		return idLeta;
	}

	public void setIdLeta(long idLeta) {
		this.idLeta = idLeta;
	}
	
	
}
