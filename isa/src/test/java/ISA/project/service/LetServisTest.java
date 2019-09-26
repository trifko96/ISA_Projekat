package ISA.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
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

import ISA.project.dto.FilterLetDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.PretragaLetDTO;
import ISA.project.model.Aerodrom;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Avion;
import ISA.project.model.Let;
import ISA.project.model.Sediste;
import ISA.project.model.Segment;
import ISA.project.model.StatusSedista;
import ISA.project.model.TipKlase;
import ISA.project.repository.AvionRepozitorijum;
import ISA.project.repository.LetRepozitorijum;
import ISA.project.repository.SedisteRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetServisTest {

	@Mock
	private LetRepozitorijum repozitorijumMock;
	
	@Mock
	private Let letMock;
	
	@InjectMocks
	private LetServis letServis;
	
	@Test
	public void testPretraziLet() {
		PretragaLetDTO let = new PretragaLetDTO();
		let.setBrOsoba(1);
		let.setKlasa("BIZNIS");
		let.setMestoPoletanja("Novi Sad");
		let.setMestoSletanja("Beograd");
		let.setTip("ONE_WAY");
		let.setVremePoletanja(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		let.setVremePovratka(new GregorianCalendar(2019, Calendar.SEPTEMBER, 3).getTime());
		
		Avion a = new Avion();
		a.setId(1);
		a.setIme("Ime");
		Segment s = new Segment();
		s.setTip(TipKlase.BIZNIS);
		Sediste sed = new Sediste();
		sed.setId(1);
		sed.setStatus(StatusSedista.SLOBODNO);
		sed.setSegment(s);
		List<Segment> segmenti = new ArrayList<>();
		segmenti.add(s);
		a.setKlasa(segmenti);
		s.dodajSediste(sed);
		
		Aerodrom aero = new Aerodrom();
		aero.setGrad("Novi Sad");
		
		Aerodrom aero1 = new Aerodrom();
		aero1.setGrad("Beograd");
		
		Let l = new Let();
		l.setIdLeta(1);
		l.setAvion(a);
		l.setPolaznaDestinacija(aero);
		l.setOdredisnaDestinacija(aero1);
		l.setTip("ONE_WAY");
		l.setVremePoletanja(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		l.setVremePolaskaNazad(new GregorianCalendar(2019, Calendar.SEPTEMBER, 3).getTime());
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(l));
		List<LetDTO> letovi = letServis.pretraziLet(let);
		assertEquals(letovi.get(0).getId(), 1);
		
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiLetovePoKompaniji() {
		AvioKompanija a = new AvioKompanija();
		a.setId(1);
		Avion avion = new Avion();
		avion.setId(1);
		avion.setIme("ime");
		Aerodrom aero1 = new Aerodrom();
		Aerodrom aero2 = new Aerodrom();
		aero1.setId(1);
		aero1.setGrad("Beograd");
		aero1.setIme("Aero1");
		aero2.setId(2);
		aero2.setGrad("Novi Sad");
		aero2.setIme("Aero2");
		Let l = new Let();
		l.setIdLeta(1);
		l.setAvion(avion);
		l.setPolaznaDestinacija(aero1);
		l.setOdredisnaDestinacija(aero2);
		
		when(repozitorijumMock.vratiLetove(1)).thenReturn(Arrays.asList(l));
		
		List<LetDTO> letovi = letServis.vratiLetove(a);
		assertEquals(letovi.get(0).getId(), l.getIdLeta());
		
		verify(repozitorijumMock, times(1)).vratiLetove(1);
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	@Test
	public void testVratiSveLetove() {
		AvioKompanija a = new AvioKompanija();
		a.setId(1);
		Avion avion = new Avion();
		avion.setId(1);
		avion.setIme("ime");
		Aerodrom aero1 = new Aerodrom();
		Aerodrom aero2 = new Aerodrom();
		aero1.setId(1);
		aero1.setGrad("Beograd");
		aero1.setIme("Aero1");
		aero2.setId(2);
		aero2.setGrad("Novi Sad");
		aero2.setIme("Aero2");
		Let l = new Let();
		l.setIdLeta(1);
		l.setAvion(avion);
		l.setPolaznaDestinacija(aero1);
		l.setOdredisnaDestinacija(aero2);
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(l));
		
		List<LetDTO> letovi = letServis.vratiSveLetove();
		assertEquals(letovi.get(0).getId(), l.getIdLeta());
		
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	@Test
	public void testFiltrirajLet() {
		AvioKompanija a = new AvioKompanija();
		a.setNaziv("Avio");
		Avion avion = new Avion();
		avion.setId(1);
		avion.setIme("ime");
		Aerodrom aero1 = new Aerodrom();
		Aerodrom aero2 = new Aerodrom();
		aero1.setId(1);
		aero1.setGrad("Beograd");
		aero1.setIme("Aero1");
		aero2.setId(2);
		aero2.setGrad("Novi Sad");
		aero2.setIme("Aero2");
		Let l = new Let();
		l.setIdLeta(1);
		l.setAvioKompanija(a);
		l.setCenaKarteBiznisKlase(20);
		l.setCenaKarteEkonomskeKlase(20);
		l.setCenaPrveKlase(20);
		l.setAvion(avion);
		l.setPolaznaDestinacija(aero1);
		l.setOdredisnaDestinacija(aero2);
		FilterLetDTO f = new FilterLetDTO();
		f.setAvioKompanija("Avio");
		f.setCena(25);
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(l));
		
		List<LetDTO> letovi = new ArrayList<>();
		letovi = letServis.filtrirajLet(f);
		
		assertEquals(letovi.get(0).getId(), l.getIdLeta());
		
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
}
