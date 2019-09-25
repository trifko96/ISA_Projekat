package ISA.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ISA.project.dto.AerodromDTO;
import ISA.project.model.Aerodrom;
import ISA.project.model.AvioKompanija;
import ISA.project.model.Korisnik;
import ISA.project.repository.AerodromRepozitorijum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AerodromServisTest {

	@Mock
	private AerodromRepozitorijum repozitorijumMock;
	
	@Mock
	private Aerodrom aerodromMock;
	
	@InjectMocks
	private AerodromServis aerodromServis;
	
	@Test
	public void testVratiAerodromPoImenu() {
		AerodromDTO aero = new AerodromDTO();
		aero.setIme("Aero");
		when(repozitorijumMock.vratiAerodromPoImenu("Aero")).thenReturn(aerodromMock);
		Aerodrom a = aerodromServis.vratiAerodromPoImenu(aero);
		assertEquals(a, aerodromMock);
		
		verify(repozitorijumMock, times(1)).vratiAerodromPoImenu("Aero");
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiAerodrome() {
		AvioKompanija a = new AvioKompanija();
		a.setId(1);
		when(repozitorijumMock.vratiAerodrome(1)).thenReturn(Arrays.asList(new Aerodrom("Aero", "Grad")));
		List<AerodromDTO> aerodromi = aerodromServis.vratiAerodrome(a);
		assertEquals(aerodromi.size(), 1);
		
		verify(repozitorijumMock, times(1)).vratiAerodrome(1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiSve() {
	
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new Aerodrom("Aero", "Grad")));
		List<AerodromDTO> aerodromi = aerodromServis.vratiSveAerodrome();
		assertEquals(aerodromi.size(), 1);
	
		verify(repozitorijumMock, times(1)).findAll();
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testDodajAerodrom() {
		
		Aerodrom aero = new Aerodrom();
		aero.setIme("Ime");
		aero.setGrad("Grad");
		AerodromDTO adto = new AerodromDTO();
		adto.setIme("Ime");
		adto.setGrad("Grad");
		AvioKompanija a = new AvioKompanija();
		a.setId(1);
		when(repozitorijumMock.save(aero)).thenReturn(aero);
		Aerodrom aerodrom = repozitorijumMock.save(aero);
		
		assertEquals(aerodrom.getIme(), "Ime");
		when(repozitorijumMock.vratiAerodrome(1)).thenReturn(Arrays.asList(aero));
		List<AerodromDTO> aerodromi = aerodromServis.vratiAerodrome(a);
		assertEquals(aerodromi.get(0).getId(), aero.getId());
		
		verify(repozitorijumMock, times(1)).save(aero);
		verify(repozitorijumMock, times(1)).vratiAerodrome(1);
        verifyNoMoreInteractions(repozitorijumMock);
	}
}
