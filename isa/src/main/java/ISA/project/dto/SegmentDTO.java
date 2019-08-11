package ISA.project.dto;

import java.util.ArrayList;
import java.util.List;

import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.TipKlase;

public class SegmentDTO {

	private long idSegmenta;
	private TipKlase tip;
	private int brojRedova;
	private int brojKolona;
	private List<SedisteDTO> listaSedista = new ArrayList<>();
	
	public SegmentDTO() {
		
	}
	
	public SegmentDTO(Segment s) {
		this.idSegmenta = s.getId();
		this.tip = s.getTip();
		this.brojKolona = s.getBrojKolona();
		this.brojRedova = s.getBrojRedova();
	}

	public long getId() {
		return idSegmenta;
	}

	public void setId(long id) {
		this.idSegmenta = id;
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

	public List<SedisteDTO> getListaSedista() {
		return listaSedista;
	}

	public void setListaSedista(List<SedisteDTO> listaSedista) {
		this.listaSedista = listaSedista;
	}
	
	
}
