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

import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.model.Avion;
import ISA.project.model.AvionskaKarta;
import ISA.project.model.Korisnik;
import ISA.project.model.Let;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.StatusSedista;
import ISA.project.model.TipKlase;
import ISA.project.repository.AvionskaKartaRepozitorijum;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;
import ISA.project.repository.SegmentRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SedisteServisTest {

	@Mock
	private SedisteRepozitorijum sedisteRepozitorijumMock;
	
	@Mock
	private SegmentRepozitorijum segmentRepozitorijumMock;
	
	@Mock
	private LetRepozitorijum letRepozitorijumMock;
	
	@Mock
	private AvionskaKartaRepozitorijum kartaRepozitorijumMock;
	
	@Mock
	private Sediste sedisteMock;
	
	@InjectMocks
	private SedisteServis sedisteServis;
	
	@Test
	@Transactional
    @Rollback(true)
	public void testDodajSediste() {
		
		Segment segment = new Segment();
		Sediste sediste = new Sediste();
		sediste.setId(1);
		segment.setId(1);
		segment.dodajSediste(sediste);
		SedisteDTO sed = new SedisteDTO();
		sed.setId(1);
		sed.setBrKolone(1);
		sed.setBrReda(1);
		sed.setGranica(true);
		sed.setNatpis(1);
		sed.setZauzeto(false);
		sed.setStatus(StatusSedista.SLOBODNO);
		sed.setPom(1);
		
		when(segmentRepozitorijumMock.vratiKlasu(1)).thenReturn(segment);
		
		Segment s = sedisteServis.dodajSediste(sed);
		
		assertEquals(s.getId(), segment.getId());
		
		verify(segmentRepozitorijumMock, times(1)).vratiKlasu(1);
		verify(segmentRepozitorijumMock, times(1)).save(segment);
		verifyNoMoreInteractions(segmentRepozitorijumMock);
		
	}
	
	@Test
	public void testVratiBrzeKarte() {
		Avion a = new Avion();
		List<Segment> segmenti = new ArrayList<>();
		Segment s = new Segment();
		Sediste sed = new Sediste();
		sed.setId(1);
		sed.setNatpis(1);
		sed.setStatus(StatusSedista.BRZA_REZERVACIJA);
		AvionskaKarta av = new AvionskaKarta();
		av.setIdKarte(1);
		av.setSediste(sed);
		av.setCena(25);
		segmenti.add(s);
		a.setKlasa(segmenti);
		s.setAvion(a);
		s.setTip(TipKlase.BIZNIS);
		sed.setSegment(s);
		s.dodajSediste(sed);
		Let l = new Let();
		l.setAvion(a);
		LetDTO ld = new LetDTO();
		ld.setId(1);
		
		when(letRepozitorijumMock.vratiLet(1)).thenReturn(l);
		when(kartaRepozitorijumMock.vratiKartu(1)).thenReturn(av);
		
		List<AvionskaKartaDTO> karte = new ArrayList<>();
		karte = sedisteServis.vratiBrzeKarte(ld);
		
		assertEquals(karte.get(0).getId(), av.getIdKarte());
		
		verify(letRepozitorijumMock, times(1)).vratiLet(1);
		verify(kartaRepozitorijumMock, times(1)).vratiKartu(1);
		verifyNoMoreInteractions(letRepozitorijumMock);
		verifyNoMoreInteractions(kartaRepozitorijumMock);
	}
	
	@Test
	public void testVratiPodatkeOLetu() {
		Avion a = new Avion();
		a.setId(1);
		List<Segment> segmenti = new ArrayList<>();
		Segment s = new Segment();
		Sediste sed = new Sediste();
		sed.setId(1);
		sed.setNatpis(1);
		sed.setStatus(StatusSedista.BRZA_REZERVACIJA);
		segmenti.add(s);
		a.setKlasa(segmenti);
		s.setAvion(a);
		s.setTip(TipKlase.BIZNIS);
		sed.setSegment(s);
		s.dodajSediste(sed);
		Let l = new Let();
		l.setAvion(a);
		
		when(letRepozitorijumMock.vratiLetPoAvionu(1)).thenReturn(l);
		
		when(sedisteRepozitorijumMock.vratiSediste(1)).thenReturn(sed);
		
		Let let = sedisteServis.vratiPodatkeOLetu(1);
		
		assertEquals(let.getIdLeta(), l.getIdLeta());
		
		verify(letRepozitorijumMock, times(1)).vratiLetPoAvionu(1);
		verify(sedisteRepozitorijumMock, times(1)).vratiSediste(1);
		verifyNoMoreInteractions(letRepozitorijumMock);
		verifyNoMoreInteractions(sedisteRepozitorijumMock);
	}
	
	@Test
	public void testVratiPodatkeOKlasi() {
		Segment s = new Segment();
		s.setId(1);
		Sediste sed = new Sediste();
		sed.setId(1);
		sed.setNatpis(1);
		s.setTip(TipKlase.BIZNIS);
		sed.setSegment(s);
		s.dodajSediste(sed);
		
		when(sedisteRepozitorijumMock.vratiSediste(1)).thenReturn(sed);
		
		Segment seg = sedisteServis.vratiPodatkeOKlasi(1);
		
		assertEquals(seg.getId(), s.getId());
	
		verify(sedisteRepozitorijumMock, times(1)).vratiSediste(1);
		verifyNoMoreInteractions(sedisteRepozitorijumMock);
	}
	
	@Test
	public void testBrzoRezervisi() {
		Korisnik k = new Korisnik();
		k.setIme("Pavle");
		k.setPrezime("Trifkovic");
		k.setBrTelefona("0612222333");
		k.setEmail("ddd@gmail.com");
		AvionskaKartaDTO a = new AvionskaKartaDTO();
		a.setId(1);
		AvionskaKarta av = new AvionskaKarta();
		av.setIdKarte(1);
		
		Sediste sed = new Sediste();
		sed.setStatus(StatusSedista.SLOBODNO);
		av.setSediste(sed);
		
		when(kartaRepozitorijumMock.vratiKartuPoId(1)).thenReturn(av);
		
		when(sedisteRepozitorijumMock.vratiSedistePoKarti(1)).thenReturn(sed);
		
		String s = sedisteServis.brzoRezervisi(a, k);
		
		assertEquals(s, "ok");
		
		verify(sedisteRepozitorijumMock, times(1)).vratiSedistePoKarti(1);
		verify(sedisteRepozitorijumMock, times(1)).save(sed);
		verifyNoMoreInteractions(sedisteRepozitorijumMock);
		
		verify(kartaRepozitorijumMock, times(1)).vratiKartuPoId(1);
		verify(kartaRepozitorijumMock, times(1)).save(av);
		verifyNoMoreInteractions(kartaRepozitorijumMock);
	}
}
