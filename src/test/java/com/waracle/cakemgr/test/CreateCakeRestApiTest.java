package com.waracle.cakemgr.test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

/**
 * @author gfeng
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateCakeRestApiTest {
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
		
		given(service.createCake(Mockito.any())).willReturn(testCake);
			
		mockMvc.perform(post("http://localhost:" + port + "/cakes")
			.contentType(MediaType.APPLICATION_JSON).content(toJson(testCake)))
			.andExpect(status().isOk());
			
		verify(service, VerificationModeFactory.times(1)).createCake(Mockito.any());
        reset(service);
	}
	
	private byte[] toJson(Object object) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
