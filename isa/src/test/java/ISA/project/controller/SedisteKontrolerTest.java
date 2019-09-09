package ISA.project.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.PretragaLetDTO;
import ISA.project.dto.RezervacijaDTO;
import ISA.project.dto.RezervacijaKarataDTO;
import ISA.project.dto.SedisteDTO;
import ISA.project.model.StatusSedista;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SedisteKontrolerTest {

	private static final String URL_PREFIX = "/Sediste";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testVratiSveBrzeKarte() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiSveBrzeKarte" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(3)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(7)));
	}
	
	@Test
	public void testVratiBrzeKarte() throws Exception {
		LetDTO l = new LetDTO();
		l.setId(1);
		String json = ISA.project.utils.TestUtil.json(l);
		this.mockMvc.perform(post(URL_PREFIX + "/vratiBrzeKarte").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(3)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(7)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testBrzoRezervisi() throws Exception {
		AvionskaKartaDTO a = new AvionskaKartaDTO();
		a.setId(3);
		String json = ISA.project.utils.TestUtil.json(a);
		this.mockMvc.perform(post(URL_PREFIX + "/brzoRezervisi/7").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(7)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testRezervisi() throws Exception {
		RezervacijaKarataDTO r = new RezervacijaKarataDTO();
		r.setBrPasosa("312323");
		r.setBrSedista(4);
		r.setBrTelefona("4324243");
		r.setEmail("dasd@gmail.com");
		r.setIme("dasdasd");
		r.setPrezime("dadsad");
		r.setIdSedista(4);
		List<RezervacijaKarataDTO> lista = new ArrayList<RezervacijaKarataDTO>();
		lista.add(r);
		RezervacijaDTO rd = new RezervacijaDTO();
		rd.setKarte(lista);
		String json = ISA.project.utils.TestUtil.json(rd);
		this.mockMvc.perform(post(URL_PREFIX + "/rezervisi/7").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDodajSediste() throws Exception {
		SedisteDTO s = new SedisteDTO();
		s.setBrKolone(2);
		s.setBrReda(3);
		s.setGranica(false);
		s.setId(55);
		s.setNatpis(5);
		s.setPom(11);
		s.setStatus(StatusSedista.SLOBODNO);
		s.setZauzeto(false);
		String json = ISA.project.utils.TestUtil.json(s);
		this.mockMvc.perform(post(URL_PREFIX + "/dodajSediste").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testIzmeniSediste() throws Exception {
		SedisteDTO s = new SedisteDTO();
		s.setBrKolone(2);
		s.setBrReda(3);
		s.setGranica(false);
		s.setId(11);
		s.setNatpis(11);
		s.setPom(13);
		s.setStatus(StatusSedista.BRZA_REZERVACIJA);
		s.setZauzeto(false);
		String json = ISA.project.utils.TestUtil.json(s);
		this.mockMvc.perform(post(URL_PREFIX + "/izmeniSediste").contentType(contentType).content(json)).andExpect(status().isOk());
	}
}
