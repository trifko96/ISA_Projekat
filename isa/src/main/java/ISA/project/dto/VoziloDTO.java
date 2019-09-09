package ISA.project.dto;

import ISA.project.model.Vozilo;

public class VoziloDTO {
	
	private long id;
	
	private double cena;
	private String naziv;
	private String marka;
	private String model;
	private String godinaProizvodnje;
	private int brSedista;
	private String tip;
	private double prosecnaOcena;
	private int brOcena;
	private boolean rezervisano;
	private String naPopustu;
	private String adresaLokacije;
	private double popust;
	
	VoziloDTO(){}
	
	public VoziloDTO(Vozilo vozilo){
	
		this.id = vozilo.getVoziloId();
		this.cena = vozilo.getCena();
		this.naziv = vozilo.getNaziv();
		this.marka = vozilo.getMarka();
		this.model = vozilo.getModel();
		this.godinaProizvodnje = vozilo.getGodinaProizvodnje();
		this.brSedista = vozilo.getBrSedista();
		this.tip = vozilo.getTip();
		this.prosecnaOcena = vozilo.getProsecnaOcena();
		this.brOcena = vozilo.getBrOcena();
		this.rezervisano = vozilo.isRezervisano();
		this.naPopustu = vozilo.getNaPopustu();
		this.adresaLokacije = vozilo.getAdresaLokacije();
		this.popust = vozilo.getPopust();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(String godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public int getBrSedista() {
		return brSedista;
	}

	public void setBrSedista(int brSedista) {
		this.brSedista = brSedista;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public int getBrOcena() {
		return brOcena;
	}

	public void setBrOcena(int brOcena) {
		this.brOcena = brOcena;
	}

	public boolean isRezervisano() {
		return rezervisano;
	}

	public void setRezervisano(boolean rezervisano) {
		this.rezervisano = rezervisano;
	}

	public String getNaPopustu() {
		return naPopustu;
	}

	public void setNaPopustu(String naPopustu) {
		this.naPopustu = naPopustu;
	}

	public String getAdresaLokacije() {
		return adresaLokacije;
	}

	public void setAdresaLokacije(String adresaLokacije) {
		this.adresaLokacije = adresaLokacije;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}
	
	
	

}
