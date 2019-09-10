package ISA.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OceneKompanija {

	@Id
	@GeneratedValue
	private long id;
	private long idKorisnika;
	private long idKompanije;
	
	public OceneKompanija() {
		
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

	public long getIdKompanije() {
		return idKompanije;
	}

	public void setIdKompanije(long idKompanije) {
		this.idKompanije = idKompanije;
	}
	
	
}
