package com.waracle.cakemgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

/**
 * @author gfeng
 */

@Controller
public class CakeController {
	@Autowired
	private CakeService service;
	
	@RequestMapping("/")
	public String index(final Model model) {
		model.addAttribute("cakes", service.getAllCakes());
		return "index";
	}
	
	@RequestMapping(path="/create")
	public String createCake() {
		
		return "create";
	}
	
	@PostMapping(path="/create")
	public String saveCake(final Model model, final CakeEntity cake) {
		final CakeEntity updated = service.createCake(cake);
		
		model.addAttribute("result", updated);
		return "create";
	}
}
