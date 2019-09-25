package ISA.project.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Vozilo {
	
	@Id
	@GeneratedValue
	private long voziloId;
	
	private double cena;
	private String naziv;
	private String marka;
	private String model;
	private String godinaProizvodnje;
	private int brSedista;
	private String tip;
	private double prosecnaOcena;
	private int brOcena;
	private int ocene;
	private boolean rezervisano;
	private String naPopustu;
	private String adresaLokacije;
	private Date trenutniDatum;
	private Date datumOd;
	private Date datumDo;
	private String emailKorisnika;
	private String mestoPreuzimanja;
	private String mestoVracanja;
	private double popust;
	private Date datumPopustOd;
	private Date datumPospustDo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rentACarId", referencedColumnName="rentACarId")
	private RentACar rentACar;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="lokacijaId", referencedColumnName="lokacijaId")
	private Lokacija lokacija;
	
	@OneToOne
	@JoinColumn(name = "idRezVozilo")
	public RezervacijaVozilo rezervacija;
	
	
	public Vozilo() {
		this.brOcena = 0;
		this.ocene = 0;
	}
	
	public Vozilo(double cena, String naziv, String marka, String model, String godinaProizvodnje, int brSedista,
			String tip, double popust, RezervacijaVozilo rezervacija) {
		super();
		this.cena = cena;
		this.naziv = naziv;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brSedista = brSedista;
		this.tip = tip;
		this.popust = popust;
		this.rezervacija = rezervacija;
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


	public int getOcene() {
		return ocene;
	}

	public void setOcene(int ocene) {
		this.ocene = ocene;
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

	public boolean isRezervisano() {
		return rezervisano;
	}


	public void setRezervisano(boolean rezervisano) {
		this.rezervisano = rezervisano;
	}


	public RentACar getRentACar() {
		return rentACar;
	}


	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public String getNaPopustu() {
		return naPopustu;
	}

	public void setNaPopustu(String naPopustu) {
		this.naPopustu = naPopustu;
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

	public long getVoziloId() {
		return voziloId;
	}

	public void setVoziloId(long voziloId) {
		this.voziloId = voziloId;
	}

	public String getAdresaLokacije() {
		return adresaLokacije;
	}

	public void setAdresaLokacije(String adresaLokacije) {
		this.adresaLokacije = adresaLokacije;
	}

	public Date getTrenutniDatum() {
		return trenutniDatum;
	}

	public void setTrenutniDatum(Date trenutniDatum) {
		this.trenutniDatum = trenutniDatum;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	public String getEmailKorisnika() {
		return emailKorisnika;
	}

	public void setEmailKorisnika(String emailKorisnika) {
		this.emailKorisnika = emailKorisnika;
	}

	public String getMestoPreuzimanja() {
		return mestoPreuzimanja;
	}

	public void setMestoPreuzimanja(String mestoPreuzimanja) {
		this.mestoPreuzimanja = mestoPreuzimanja;
	}

	public String getMestoVracanja() {
		return mestoVracanja;
	}

	public void setMestoVracanja(String mestoVracanja) {
		this.mestoVracanja = mestoVracanja;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}


	
	public void oceniVozilo(double ocena) {
		this.ocene += ocena;
	}
	
	public void povecajBrojOcena() {
		this.brOcena++;
	}

	public Date getDatumPopustOd() {
		return datumPopustOd;
	}

	public void setDatumPopustOd(Date datumPopustOd) {
		this.datumPopustOd = datumPopustOd;
	}

	public Date getDatumPospustDo() {
		return datumPospustDo;
	}

	public void setDatumPospustDo(Date datumPospustDo) {
		this.datumPospustDo = datumPospustDo;
	}

	public RezervacijaVozilo getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(RezervacijaVozilo rezervacija) {
		this.rezervacija = rezervacija;
	}
	
	
	

}
