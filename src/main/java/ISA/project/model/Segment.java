package ISA.project.model;

public class Segment {

	private TipKlase tip;
	private int brojRedova;
	private int brojKolona;
	private Sediste [][] listaSedista;
	
	
	public Segment(TipKlase tip, int brojRedova, int brojKolona) {
		super();
		this.tip = tip;
		this.brojRedova = brojRedova;
		this.brojKolona = brojKolona;
	}

	public TipKlase getTip() {
		return tip;
	}

	public void setTip(TipKlase tip) {
		this.tip = tip;
	}

	public int getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(int brojRedova) {
		this.brojRedova = brojRedova;
	}

	public int getBrojKolona() {
		return brojKolona;
	}

	public void setBrojKolona(int brojKolona) {
		this.brojKolona = brojKolona;
	}

	public Sediste[][] getListaSedista() {
		return listaSedista;
	}

	public void setListaSedista(Sediste[][] listaSedista) {
		this.listaSedista = listaSedista;
	}
	
	
}
