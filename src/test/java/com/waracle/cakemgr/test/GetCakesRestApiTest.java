package com.waracle.cakemgr.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

/**
 * @author gfeng
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetCakesRestApiTest {
	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CakeService service;

	@Test
	public void test() throws Exception {
		final CakeEntity testCake = new CakeEntity();
		testCake.setTitle("Test my favorite cake");
		testCake.setDesc("Test cake description");
		testCake.setImage("http://www.myfavorite.ai");
		
		final List<CakeEntity> allCakes = Arrays.asList(testCake);
		
		when(service.getAllCakes()).thenReturn(allCakes);
		
		mockMvc.perform(get("http://localhost:" + port + "/cakes").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].title", is(testCake.getTitle())))
			.andExpect(jsonPath("$[0].desc", is(testCake.getDesc())))
			.andExpect(jsonPath("$[0].image", is(testCake.getImage())));
	}
}
