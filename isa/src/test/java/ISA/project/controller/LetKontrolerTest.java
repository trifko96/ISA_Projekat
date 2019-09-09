package ISA.project.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ISA.project.dto.AerodromDTO;
import ISA.project.dto.AvionDTO;
import ISA.project.dto.DatumskiOpsegDTO;
import ISA.project.dto.FilterLetDTO;
import ISA.project.dto.LetDTO;
import ISA.project.dto.PretragaLetDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetKontrolerTest {

	private static final String URL_PREFIX = "/Let";
	
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
	public void testVratiLetove() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiLetove/7" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
	@Test
	public void testVratiSveLetove() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiSveLetove" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
	@Test
	public void testFiltrirajLet() throws Exception {
		FilterLetDTO l = new FilterLetDTO();
		l.setAvioKompanija("Air-Serbia");
		l.setCena(20);
		String json = ISA.project.utils.TestUtil.json(l);
		this.mockMvc.perform(post(URL_PREFIX + "/filtrirajLet").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
	@Test
	public void testPretraziLet() throws Exception {
		PretragaLetDTO l = new PretragaLetDTO();
		l.setBrOsoba(2);
		l.setKlasa("PRVA");
		l.setTip("ONE_WAY");
		l.setMestoPoletanja("Nis");
		l.setMestoSletanja("Beograd");
		l.setVremePovratka(null);
		l.setVremePoletanja(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		String json = ISA.project.utils.TestUtil.json(l);
		this.mockMvc.perform(post(URL_PREFIX + "/pretraziLet").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
}
