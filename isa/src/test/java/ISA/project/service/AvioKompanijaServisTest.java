package ISA.project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ISA.project.dto.AvioKompanijaDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.repository.AvioKompanijaRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvioKompanijaServisTest {

	@Mock
	private AvioKompanijaRepozitorijum repozitorijumMock;
	
	@Mock
	private AvioKompanija kompanijaMock;
	
	@InjectMocks
	private AvioKompanijaServis kompanijaServis;
	
	@Test
	public void testNadjiAvioKompaniju() {
		
		when(repozitorijumMock.vratiAvioKompanijuPoId(1)).thenReturn(kompanijaMock);
		AvioKompanija avio = kompanijaServis.nadjiAvioKompaniju(1);
		assertEquals(kompanijaMock, avio);
		verify(repozitorijumMock, times(1)).vratiAvioKompanijuPoId(1);
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	@Test
	public void testNadjiKompaniju() {
		
		when(repozitorijumMock.vratiAvioKompanijuPoId(1)).thenReturn(kompanijaMock);
		AvioKompanijaDTO avio = kompanijaServis.nadjiKompaniju(1);
		assertEquals(kompanijaMock.getId(), avio.getId());
		verify(repozitorijumMock, times(1)).vratiAvioKompanijuPoId(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testSacuvajKompaniju() {
		AvioKompanija a = new AvioKompanija("Avio", "Adresa", "Opis", "20");
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(a));
		AvioKompanija avio = new AvioKompanija();
		avio.setAdresa("Adresa1");
		avio.setBrojOcena(0);
		avio.setInfoPrtljag("21");
		avio.setKoordinata1(22);
		avio.setKoordinata2(23);
		avio.setNaziv("Naziv");
		avio.setOcene(0);
		avio.setOpis("Opis");
		avio.setPrihod(45);
		
		when(repozitorijumMock.save(avio)).thenReturn(avio);
		kompanijaServis.sacuvajKompaniju(avio);
		int dbSizeBeforeAdd = kompanijaServis.vratiSve().size();
		
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(a, avio));
        List<AvioKompanija> kompanije = kompanijaServis.vratiSve();
        assertEquals(kompanije.size(), dbSizeBeforeAdd + 1);
        AvioKompanija av = kompanije.get(kompanije.size() - 1);
        assertEquals(av.getNaziv(), "Naziv");
        verify(repozitorijumMock, times(2)).findAll();
        verify(repozitorijumMock, times(1)).save(avio);
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiSve() {
	
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new AvioKompanija("Naziv", "Adresa", "Opis", "24")));
		List<AvioKompanija> kompanije = kompanijaServis.vratiSve();
		assertEquals(kompanije.size(), 1);
	
		verify(repozitorijumMock, times(1)).findAll();
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testNadjiKompanijuPoKorisniku() {
		Korisnik k = new Korisnik();
		k.setId(1);
		when(repozitorijumMock.vratiKompanijuPoKorisniku(1)).thenReturn(kompanijaMock);
		AvioKompanija avio = kompanijaServis.nadjiKompanijuPoKorisniku(k);
		assertEquals(avio, kompanijaMock);
		
		verify(repozitorijumMock, times(1)).vratiKompanijuPoKorisniku(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	
	
	
	
	
}
