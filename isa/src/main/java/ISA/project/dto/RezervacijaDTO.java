package ISA.project.dto;

import java.util.ArrayList;
import java.util.List;

public class RezervacijaDTO {

	private List<RezervacijaKarataDTO> karte = new ArrayList<RezervacijaKarataDTO>();
	
	
	public RezervacijaDTO() {
		
	}


	public List<RezervacijaKarataDTO> getKarte() {
		return karte;
	}


	public void setKarte(List<RezervacijaKarataDTO> karte) {
		this.karte = karte;
	}
	
	
}
