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
	
	private ArrayList<Long> listaKorisnika;
	
	@OneToMany(targetEntity=Sediste.class,mappedBy="rezervisanOd", cascade = CascadeType.ALL)
	private List<Sediste> listaSedista = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLeta", referencedColumnName="idLeta")
	private Let let;
	
	public RezervacijaKarata() {
		
	}
	
	public RezervacijaKarata(ArrayList<Long> listaKorisnika, ArrayList<Sediste> listaSedista, Let let) {
		super();
		this.listaKorisnika = listaKorisnika;
		//this.listaSedista = listaSedista;
		this.let = let;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Long> getListaKorisnika() {
		return listaKorisnika;
	}

	public void setListaKorisnika(ArrayList<Long> listaKorisnika) {
		this.listaKorisnika = listaKorisnika;
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
