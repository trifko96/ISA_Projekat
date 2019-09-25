package ISA.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ISA.project.dto.AerodromDTO;
import ISA.project.dto.AvionDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.model.Aerodrom;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Avion;
import ISA.project.model.Let;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.repository.AerodromRepozitorijum;
import ISA.project.repository.AvionRepozitorijum;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvionServisTest {

	@Mock
	private AvionRepozitorijum repozitorijumMock;
	
	@Mock
	private SedisteRepozitorijum sedisteRepozitorijumMock;
	
	@Mock
	private Avion avionMock;
	
	@Mock
	private LetRepozitorijum letRepozitorijumMock;
	
	@InjectMocks
	private AvionServis avionServis;
	
	@Test
	public void testVratiAvione() {
		AvioKompanija a = new AvioKompanija();
		a.setId(1);
		when(repozitorijumMock.vratiAvione(1)).thenReturn(Arrays.asList(new Avion("Avion")));
		List<AvionDTO> avioni = avionServis.vratiAvione(a);
		assertEquals(avioni.size(), 1);
		
		verify(repozitorijumMock, times(1)).vratiAvione(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiAvioneZaLet() {
		AvioKompanija a = new AvioKompanija();
		a.setId(1);
		Avion avion = new Avion();
		avion.setIme("Avion");
		avion.setSlobodan(true);
		when(repozitorijumMock.vratiAvione(1)).thenReturn(Arrays.asList(avion));
		List<AvionDTO> avioni = avionServis.vratiAvione(a);
		assertEquals(avioni.size(), 1);
		assertEquals(avioni.get(0).getIme(), "Avion");
		
		verify(repozitorijumMock, times(1)).vratiAvione(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiAvion() {
		Avion avion = new Avion();
		avion.setId(1);
		avion.setIme("Avion");
		List<Segment> segmenti = new ArrayList<>();
		Segment segment = new Segment();
		Sediste sediste = new Sediste();
		sediste.setId(1);
		sediste.setSegment(segment);
		segment.dodajSediste(sediste);
		segmenti.add(segment);
		avion.setKlasa(segmenti);
		segment.setAvion(avion);
		SedisteDTO sedDTO = new SedisteDTO();
		sedDTO.setId(1);
		when(repozitorijumMock.vratiAvion(1)).thenReturn(avion);
		when(sedisteRepozitorijumMock.vratiSediste(1)).thenReturn(sediste);
		
		AvionDTO avionDTO = avionServis.vratiAvion(sedDTO);
		assertEquals(avionDTO.getId(), avion.getId());
		
		verify(repozitorijumMock, times(1)).vratiAvion(1);
		verify(sedisteRepozitorijumMock, times(1)).vratiSediste(1);
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	@Test
	public void testVratiAvionPoKlasi() {
		Avion a = new Avion();
		a.setId(1);
		when(repozitorijumMock.vratiAvion(1)).thenReturn(a);
		AvionDTO adto = avionServis.vratiAvionPoKlasi(1);
		assertEquals(a.getId(), adto.getId());
		verify(repozitorijumMock, times(1)).vratiAvion(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiAvionPoLetu() {
		Avion a = new Avion();
		a.setId(1);
		AvionDTO adto = new AvionDTO();
		Let l = new Let();
		l.setIdLeta(1);
		l.setAvion(a);
		when(letRepozitorijumMock.vratiLet(1)).thenReturn(l);
		adto = avionServis.vratiAvionPoLetu(1);
		assertEquals(a.getId(), adto.getId());
		verify(letRepozitorijumMock, times(1)).vratiLet(1);
		verifyNoMoreInteractions(letRepozitorijumMock);
		
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testDodajAvion() {
		AvionDTO a = new AvionDTO();
		a.setIme("Avion");
		a.setId(1);
		Avion avion = new Avion();
		avion.setId(2);
		avion.setIme("Avion2");
		avion.setSlobodan(true);
		AvioKompanija avio = new AvioKompanija();
		avio.setId(1);
		avio.setAdresa("Adresa");
		avio.setOpis("Opis");
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(avion));
		String s = avionServis.dodajAvion(a, avio);
		assertEquals(s, "ok");
		verify(repozitorijumMock, times(1)).findAll();
	}
}
