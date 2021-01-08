package com.waracle.cakemgr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.repostory.CakeRepository;

/**
 * @author gfeng
 */

@Service
public class CakeService {

	@Autowired
	private CakeRepository repo;
	
	public List<CakeEntity> getAllCakes(){
		return repo.findAll();
	}
	
	public CakeEntity createCake(CakeEntity cake) {
		final CakeEntity newCake = repo.save(cake);
		
		return newCake;
	}
	
	public Optional<CakeEntity> getCakeById(Long id) {
		final Optional<CakeEntity> cake = repo.findById(id);
						
		return cake;
	}
}
