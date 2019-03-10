package ISA.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class RezervacijaKarata {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="korisnikId", referencedColumnName="id")
	private Korisnik korisnik;
	
	@OneToMany(targetEntity=Sediste.class,mappedBy="rezervisanOd", cascade = CascadeType.ALL)
	private List<Sediste> listaSedista = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLeta", referencedColumnName="idLeta")
	private Let let;
	
	public RezervacijaKarata() {
		
	}
	
	public RezervacijaKarata(ArrayList<Long> listaKorisnika, ArrayList<Sediste> listaSedista, Let let) {
		super();
		this.listaSedista = listaSedista;
		this.let = let;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Sediste> getListaSedista() {
		return listaSedista;
	}

	public void setListaSedista(ArrayList<Sediste> listaSedista) {
		this.listaSedista = listaSedista;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
	
}
