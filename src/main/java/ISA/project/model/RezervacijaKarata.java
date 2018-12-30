package ISA.project.model;

import java.util.ArrayList;
import java.util.UUID;

public class RezervacijaKarata {

	private UUID id;
	private ArrayList<Korisnik> listaKorisnika;
	private ArrayList<Sediste> listaSedista;
	private Let let;
	
	public RezervacijaKarata(ArrayList<Korisnik> listaKorisnika, ArrayList<Sediste> listaSedista, Let let) {
		super();
		this.listaKorisnika = listaKorisnika;
		this.listaSedista = listaSedista;
		this.let = let;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
