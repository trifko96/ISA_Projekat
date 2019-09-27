package ISA.project.controller;

import static org.hamcrest.CoreMatchers.hasItem;
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
import ISA.project.dto.LokacijaDTO;
import ISA.project.dto.PretragaServisDTO;
import ISA.project.dto.RentACarDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarKontrolerTest {
	
private static final String URL_PREFIX = "/RentACar";
	
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
	public void testVratiSveServise() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiSveServise" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(89)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(67)));
	}
	
	@Test
	public void testVratiRentACar() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiRentACarPom/78" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value((67)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testPretraziServise() throws Exception {
		PretragaServisDTO p = new PretragaServisDTO();
		p.setLokacija("Bulevar Evrope 2");
		p.setDatumDo(null);
		p.setDatumOd(null);
		String json = ISA.project.utils.TestUtil.json(p);
		this.mockMvc.perform(post(URL_PREFIX + "/pretraziServise").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(67));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testVratiFilijale() throws Exception {
		RentACarDTO r = new RentACarDTO();
		r.setAdresa("Nemanje Vujovica 5, 37000 Cuprija");
		r.setId(89);
		r.setBrojOcena(0);
		r.setNaziv("Rentiranje");
		r.setOcena(0);
		r.setOpis("opisano je");
		r.setPrihod(0);
		String json = ISA.project.utils.TestUtil.json(r);
		this.mockMvc.perform(post(URL_PREFIX + "/vratiFilijale/89").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(34)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(21)));
	}


}
