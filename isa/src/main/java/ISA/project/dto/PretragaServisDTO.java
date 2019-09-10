package ISA.project.dto;

import java.sql.Date;

public class PretragaServisDTO {

	private String lokacija;
	private Date datumOd;
	private Date datumDo;
	
	public PretragaServisDTO() {
		
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
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
	
	
}
