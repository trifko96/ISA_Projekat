package ISA.project.model;

import java.util.ArrayList;

import javax.persistence.*;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RezervacijaKarata {

	@Id
	@GeneratedValue
	private long id;
	
	private ArrayList<Korisnik> listaKorisnika;
	private ArrayList<Sediste> listaSedista;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idLeta", referencedColumnName="idLeta")
	private Let let;
	
	public RezervacijaKarata(ArrayList<Korisnik> listaKorisnika, ArrayList<Sediste> listaSedista, Let let) {
		super();
		this.listaKorisnika = listaKorisnika;
		this.listaSedista = listaSedista;
		this.let = let;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Korisnik> getListaKorisnika() {
		return listaKorisnika;
	}

	public void setListaKorisnika(ArrayList<Korisnik> listaKorisnika) {
		this.listaKorisnika = listaKorisnika;
	}

	public ArrayList<Sediste> getListaSedista() {
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
	
	
	
}
