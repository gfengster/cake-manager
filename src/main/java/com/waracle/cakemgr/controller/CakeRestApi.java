package com.waracle.cakemgr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

/**
 * @author gfeng
 */

@RestController
public class CakeRestApi {
	@Autowired
	private CakeService service;
	
	@GetMapping(path = "/cakes", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<CakeEntity> getAllCakes() {
		final List<CakeEntity> list = service.getAllCakes();
		
		return list;
	}
	
	@PostMapping(path="/cakes", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public CakeEntity creatCake(@RequestBody final CakeEntity cake) {
		
		if (ObjectUtils.isEmpty(cake.getTitle()) || ObjectUtils.isEmpty(cake.getDesc())
				|| ObjectUtils.isEmpty(cake.getImage())) {
			throw new RuntimeException("Cake has empty field.");
		}
		
		final CakeEntity newCake = service.createCake(cake);

		return newCake;
	}
	
	@GetMapping("/cakes/{id}")
	public ResponseEntity<?> getCakeById(@PathVariable("id") Long id) {
		final Optional<CakeEntity> cake = service.getCakeById(id);
		
		if (cake.isPresent()) {
			return new ResponseEntity<CakeEntity>(cake.get(), new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
	}
}
