package ISA.project.dto;

public class PrihodDTO {

	private double iznos;
	private String valuta;
	
	public PrihodDTO() {
		
	}
	
	public PrihodDTO(double iznos, String valuta) {
		this.iznos = iznos;
		this.valuta = valuta;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	
	
}
