package ISA.project.dto;

import java.util.ArrayList;

public class StatistikaDTO {

	private ArrayList<String> labele = new ArrayList<String>();
	private ArrayList<Long> vrednosti = new ArrayList<Long>();
	
	public StatistikaDTO() {
		
	}

	public ArrayList<String> getLabele() {
		return labele;
	}

	public void setLabele(ArrayList<String> labele) {
		this.labele = labele;
	}

	public ArrayList<Long> getVrednosti() {
		return vrednosti;
	}

	public void setVrednosti(ArrayList<Long> vrednosti) {
		this.vrednosti = vrednosti;
	}
	
	public void dodajLabelu(String labela) {
		labele.add(labela);
	}
	
	public void dodajVrednost(Long vrednost) {
		vrednosti.add(vrednost);
	}
}
