package ISA.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ISA.project.dto.LokacijaDTO;
import ISA.project.dto.RezervacijaVoziloDTO;
import ISA.project.dto.VoziloDTO;
import ISA.project.model.Korisnik;
import ISA.project.model.Lokacija;
import ISA.project.model.RentACar;
import ISA.project.model.RezervacijaVozilo;
import ISA.project.model.Vozilo;
import ISA.project.repository.RezervacijaVoziloRepozitorijum;
import ISA.project.repository.VoziloRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoziloServisTest {

	@Mock
	private VoziloRepozitorijum repozitorijumMock;
	
	@Mock
	private RezervacijaVoziloRepozitorijum rezRepozitorijumMock;
	
	@InjectMocks
	private VoziloServis voziloServis;
	
	@Mock
	private Vozilo voziloMock;
	
	@Test
	public void testVratiVoziloPoNazivu() {
		VoziloDTO v = new VoziloDTO();
		v.setId(1);
		when(repozitorijumMock.vratiVoziloPoNazivu(1)).thenReturn(voziloMock);
		
		Vozilo vozilo = voziloServis.vratiVoziloPoNazivu(v);
		
		assertEquals(vozilo.getVoziloId(), voziloMock.getVoziloId());
		
		verify(repozitorijumMock, times(1)).vratiVoziloPoNazivu(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testnadjiVozilo() {
		Vozilo v = new Vozilo();
		when(repozitorijumMock.vratiVoziloPoNazivu(1)).thenReturn(voziloMock);
		v = voziloServis.nadjiVozilo(1);
		assertEquals(v.getVoziloId(), voziloMock.getVoziloId());
		verify(repozitorijumMock, times(1)).vratiVoziloPoNazivu(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVrati() {
		Vozilo v = new Vozilo();
		v.setVoziloId(1);
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(v));
		List<VoziloDTO> vozila = voziloServis.vrati();
		assertEquals(vozila.size(), 1);
	
		verify(repozitorijumMock, times(1)).findAll();
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiVozila() {
		RentACar r = new RentACar();
		r.setRentACarId(1);
		Vozilo v = new Vozilo();
		v.setVoziloId(1);
		List<VoziloDTO> lista = new ArrayList<>();
		when(repozitorijumMock.vratiVozila(1)).thenReturn(Arrays.asList(v));
		lista = voziloServis.vratiVozila(r);
		
		assertEquals(v.getVoziloId(), lista.get(0).getId());
		verify(repozitorijumMock, times(1)).vratiVozila(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiRezVozila() {
		List<RezervacijaVoziloDTO> lista = new ArrayList<>();
		RezervacijaVozilo rez = new RezervacijaVozilo();
		rez.setOtkazano(false);
		rez.setIdRezVozilo(1);
		rez.setBrOcena(0);
		rez.setOcene(0);
		rez.setProsecnaOcena(0);
		rez.setEmailKorisnika("kkk@gmail.com");
		rez.setMestoPreuzimanja("mesto1");
		rez.setMestoVracanja("mesto2");
		rez.setDatumRezervacijaDo(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		rez.setDatumRezervacijaOd(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		rez.setTrenutniDatumRezervacija(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		Vozilo v = new Vozilo();
		v.setVoziloId(1);
		v.setAdresaLokacije("adresa");
		v.setBrOcena(0);
		v.setBrSedista(3);
		v.setCena(20);
		v.setOcene(0);
		v.setRezervisano(false);
		v.setNaPopustu("NE");
		v.setPopust(0);
		v.setTip("tip");
		v.setGodinaProizvodnje("2001");
		v.setMarka("marka");
		v.setModel("model");
		v.setNaziv("naziv");
		rez.setVozilo(v);
		v.setProsecnaOcena(0);
		v.setDatumPopustOd(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		v.setDatumPospustDo(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		Korisnik k = new Korisnik();
		k.setEmail("kkk@gmail.com");
		when(rezRepozitorijumMock.vratiRezervacijePoMailu("kkk@gmail.com")).thenReturn(Arrays.asList(rez));
		
		lista = voziloServis.vratiRezVozila(k);
		
		assertEquals(lista.size(), 1);
		verify(rezRepozitorijumMock, times(1)).vratiRezervacijePoMailu("kkk@gmail.com");
		verifyNoMoreInteractions(repozitorijumMock);
	}
}
