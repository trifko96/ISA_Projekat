package ISA.project.dto;

import ISA.project.model.Aerodrom;

public class AerodromDTO {

	private long id;
	private String ime;
	private String grad;
	
	public AerodromDTO(Aerodrom aerodrom) {
		this.id = aerodrom.getId();
		this.ime = aerodrom.getIme();
		this.grad = aerodrom.getGrad();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	
	
}
