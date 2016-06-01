package com.prodigious.festivities.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prodigious.festivities.entity.Festivity;

@RestController
@RequestMapping("/festivities")
public class FestivityController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("msg", "JCG Hello World!");
		return "Hola";
	}

	@RequestMapping(value = "/findAll")
	public ResponseEntity<List<Festivity>> listAll() {
		Festivity festivity1 = new Festivity();
		festivity1.setName("UNO");
		festivity1.setNamePlace("CENTER");

		List<Festivity> festivities = new ArrayList<Festivity>();
		festivities.add(festivity1);

		return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);

	}
}
