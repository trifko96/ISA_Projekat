package ISA.project.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
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

import ISA.project.dto.AerodromDTO;
import ISA.project.dto.AvionskaKartaDTO;
import ISA.project.dto.LetDTO;
import ISA.project.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AerodromKontrolerTest {

private static final String URL_PREFIX = "/Aerodrom";
	
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
	public void testVratiSveAerodrome() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiSveAerodrome" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(3)));
	}
	
	@Test
	public void testVratiAerodrome() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiAerodrome/7" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(2)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDodajPostojeci() throws Exception {
		AerodromDTO a = new AerodromDTO();
		a.setGrad("Mostar");
		a.setId(3);
		a.setIme("Medjunarodni aerodrom");
		List<AerodromDTO> lista = new ArrayList<>();
		lista.add(a);
		String json = ISA.project.utils.TestUtil.json(lista);
		this.mockMvc.perform(post(URL_PREFIX + "/dodajPostojeci/7").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(3)));
	}
	
	@Test
	public void dummyTest(){
		assertTrue(true);
	}
}
