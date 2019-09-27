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
import org.springframework.test.context.junit4.SpringRunner;

import ISA.project.dto.LokacijaDTO;
import ISA.project.dto.RentACarDTO;
import ISA.project.model.Lokacija;
import ISA.project.model.RentACar;
import ISA.project.repository.LokacijaRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LokacijaServisTest {

	@Mock
	private LokacijaRepozitorijum repozitorijumMock;
	
	@Mock
	private Lokacija lokacijaMock;
	
	@InjectMocks
	private LokacijaServis lokacijaServis;
	
	@Test
	public void testVratiLokacijuPoAdresi() {
		LokacijaDTO lokDTO = new LokacijaDTO();
		lokDTO.setAdresa("adresa");
		
		when(repozitorijumMock.vratiLokacijuPoAdresi("adresa")).thenReturn(lokacijaMock);
		
		Lokacija l = lokacijaServis.vratiLokacijuPoAdresi(lokDTO);
		
		assertEquals(l, lokacijaMock);
		
		verify(repozitorijumMock, times(1)).vratiLokacijuPoAdresi("adresa");
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiLokacije() {
		RentACar r = new RentACar();
		r.setRentACarId(1);
		Lokacija l = new Lokacija();
		l.setLokacijaId(1);
		List<LokacijaDTO> ldto = new ArrayList<>();
		when(repozitorijumMock.vratiLokacije(1)).thenReturn(Arrays.asList(l));
		
		ldto = lokacijaServis.vratiLokacije(r);
		
		assertEquals(ldto.get(0).getId(), l.getLokacijaId());
		
		verify(repozitorijumMock, times(1)).vratiLokacije(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiSve() {
		Lokacija l = new Lokacija();
		l.setLokacijaId(1);
		l.setAdresa("adresa");
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(l));
		List<LokacijaDTO> lokacije = lokacijaServis.vratiSveLokacije();
		assertEquals(lokacije.size(), 1);
	
		verify(repozitorijumMock, times(1)).findAll();
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testnadjiLokaciju() {
		Lokacija l = new Lokacija();
		when(repozitorijumMock.vratiLokacijuPoNazivu(1)).thenReturn(lokacijaMock);
		l = lokacijaServis.nadjiLokaciju(1);
		assertEquals(l.getLokacijaId(), lokacijaMock.getLokacijaId());
		verify(repozitorijumMock, times(1)).vratiLokacijuPoNazivu(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiLokacijePoRent() {
		Lokacija l = new Lokacija();
		l.setAdresa("adresa");
		l.setLokacijaId(1);
		List<LokacijaDTO> lista = new ArrayList<>();
		when(repozitorijumMock.vratiLokacije(1)).thenReturn(Arrays.asList(l));
		lista = lokacijaServis.vratiLokacijePoRent(1);
		
		assertEquals(l.getLokacijaId(), lista.get(0).getId());
		verify(repozitorijumMock, times(1)).vratiLokacije(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
}
