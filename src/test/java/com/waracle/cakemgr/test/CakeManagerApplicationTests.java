package com.waracle.cakemgr.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.waracle.cakemgr.controller.CakeController;
import com.waracle.cakemgr.controller.CakeRestApi;
import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.repostory.CakeRepository;
import com.waracle.cakemgr.service.CakeService;

/**
 * @author gfeng
 */

@SpringBootTest
class CakeManagerApplicationTests {
	
	@Autowired
	private CakeController controller;
	
	@Autowired
	private CakeRestApi restApi;
	
	@Autowired
	private CakeRepository repo;
	
	@Autowired
	private CakeService service;

	
	private final CakeEntity testCake = new CakeEntity();
	
	@BeforeEach
	void setup (){
		testCake.setId(0L);
		testCake.setTitle("Test my favorite cake");
		testCake.setDesc("Test cake description");
		testCake.setImage("http://www.myfavorite.ai");
	}
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(restApi).isNotNull();
		assertThat(repo).isNotNull();
		assertThat(service).isNotNull();
	}

	@Test
	void testCakeManagerDB() {
		CakeEntity saved = service.createCake(testCake);
		assertEquals(testCake.getTitle(), saved.getTitle());
		assertEquals(testCake.getDesc(), saved.getDesc());
		assertEquals(testCake.getImage(), saved.getImage());
		
		CakeEntity find = service.getCakeById(saved.getId()).get();
		
		assertNotNull(find);
		assertEquals(saved, find);
	}
	

}
