package com.waracle.cakemgr;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.repostory.CakeRepository;

/**
 * @author gfeng
 */

@SpringBootApplication
public class CakeManagerApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(CakeManagerApplication.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CakeRepository cakeRepo;

	@Value("${com.waracle.cakemgr.data.url}")
	private String dataUrl;

	public static void main(String[] args) {
		SpringApplication.run(CakeManagerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMillis(300000)).setReadTimeout(Duration.ofMillis(300000)).build();
	}

	@Override
	public void run(String... args) {
		log.info("StartApplication...");
		log.info("Loading data");

		if (dataUrl == null || dataUrl.isEmpty()) {
			log.info("dataUrl is empty");
			return;
		}
			
		final HttpEntity<String> entity = restTemplate.getForEntity(dataUrl, String.class);
		final String body = entity.getBody();

		log.info(String.format("Data from %s\n%s", dataUrl, body.toString()));

		final ObjectMapper mapper = new ObjectMapper();

		try {
			final List<CakeEntity> cakes = Arrays.asList(mapper.readValue(body, CakeEntity[].class));
			
			cakes.stream().forEach(t -> {
				cakeRepo.save(t);
			});
			
			log.info("Data loaded to DB");
		} catch (JsonProcessingException e) {
			log.error("Cannot load data from " + dataUrl, e);
		}
	}
}
