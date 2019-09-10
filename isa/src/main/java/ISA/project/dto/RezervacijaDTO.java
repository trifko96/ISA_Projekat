package ISA.project.dto;

import java.util.ArrayList;
import java.util.List;

import ISA.project.model.Vozilo;

public class RezervacijaDTO {

	private List<RezervacijaKarataDTO> karte = new ArrayList<RezervacijaKarataDTO>();
	private VoziloDTO vozilo;
	
	
	public RezervacijaDTO() {
		
	}


	public List<RezervacijaKarataDTO> getKarte() {
		return karte;
	}


	public void setKarte(List<RezervacijaKarataDTO> karte) {
		this.karte = karte;
	}


	public VoziloDTO getVozilo() {
		return vozilo;
	}


	public void setVozilo(VoziloDTO vozilo) {
		this.vozilo = vozilo;
	}
	
	
}
