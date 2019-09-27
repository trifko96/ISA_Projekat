package ISA.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ISA.project.dto.PretragaServisDTO;
import ISA.project.dto.RentACarDTO;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.model.RentACar;
import ISA.project.repository.RentACarRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarServisTest {

	@Mock
	private RentACarRepozitorijum repozitorijumMock;
	
	@Mock
	private RentACar servisMock;
	
	@InjectMocks
	private RentACarServis rentServis;
	
	@Test
	public void testnadjiRentCar() {
		RentACarDTO rdto = new RentACarDTO();
		RentACar r = new RentACar();
		r.setRentACarId(1);
		when(repozitorijumMock.vratiRentACarPoId(1)).thenReturn(r);
		rdto = rentServis.nadjiRentCar(1);
		assertEquals(rdto.getId(), r.getRentACarId());
		verify(repozitorijumMock, times(1)).vratiRentACarPoId(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testnadjiRentACar() {
		RentACar r = new RentACar();
		when(repozitorijumMock.vratiRentACarPoId(1)).thenReturn(servisMock);
		r = rentServis.nadjiRentACar(1);
		assertEquals(r.getRentACarId(), servisMock.getRentACarId());
		verify(repozitorijumMock, times(1)).vratiRentACarPoId(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiRentACar() {
		RentACarDTO rd = new RentACarDTO();
		RentACar r = new RentACar();
		r.setNaziv("naziv");
		rd.setId(1);
		rd.setNaziv("rent");
		String s;
		when(repozitorijumMock.vratiRentACar(1)).thenReturn(Arrays.asList(r));
		s = rentServis.vratiRentACar(rd);
		
		assertEquals(s, "ok");
		verify(repozitorijumMock, times(1)).vratiRentACar(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testServisPoKorisniku() {
		Korisnik k = new Korisnik();
		k.setId(1);
		when(repozitorijumMock.vratiRentACarPoKorisniku(1)).thenReturn(servisMock);
		RentACar rent = rentServis.nadjiRentACarPoKorisniku(k);
		assertEquals(rent, servisMock);
		
		verify(repozitorijumMock, times(1)).vratiRentACarPoKorisniku(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiSve() {
		RentACar r = new RentACar();
		r.setRentACarId(1);
		r.setNaziv("naziv");
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(r));
		List<RentACarDTO> servisi = rentServis.vratiServise();
		assertEquals(servisi.size(), 1);
	
		verify(repozitorijumMock, times(1)).findAll();
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	
}
