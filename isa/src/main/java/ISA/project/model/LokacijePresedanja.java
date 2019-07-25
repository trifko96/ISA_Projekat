package ISA.project.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class LokacijePresedanja {

	@Id
	@GeneratedValue
	private long idLokacije;
	
	@ManyToOne
    @JoinColumn(name="aerodromId")
	private Aerodrom aerodrom;
	
	private Date vremeSletanja;
	
	private Date vremePoletanja;
	
	@ManyToOne
	@JoinColumn(name="letId", referencedColumnName="idLeta")
	private Let let;
	
	public LokacijePresedanja() {
		
	}

	public LokacijePresedanja(Aerodrom aerodrom, Date vremeSletanja, Date vremePoletanja, Let let) {
		super();
		this.aerodrom = aerodrom;
		this.vremeSletanja = vremeSletanja;
		this.vremePoletanja = vremePoletanja;
		this.let = let;
	}

	public long getIdLokacije() {
		return idLokacije;
	}

	public void setIdLokacije(long idLokacije) {
		this.idLokacije = idLokacije;
	}

	public Aerodrom getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(Aerodrom aerodrom) {
		this.aerodrom = aerodrom;
	}

	public Date getVremeSletanja() {
		return vremeSletanja;
	}

	public void setVremeSletanja(Date vremeSletanja) {
		this.vremeSletanja = vremeSletanja;
	}

	public Date getVremePoletanja() {
		return vremePoletanja;
	}

	public void setVremePoletanja(Date vremePoletanja) {
		this.vremePoletanja = vremePoletanja;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}
	
	
	
}
