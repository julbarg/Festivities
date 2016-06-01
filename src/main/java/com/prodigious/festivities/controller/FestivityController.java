package com.prodigious.festivities.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prodigious.festivities.dto.FestivityDTO;
import com.prodigious.festivities.model.Festivity;
import com.prodigious.festivities.service.FestivityService;
import com.prodigious.festivities.util.Util;

@RestController
@RequestMapping("/festivityapi")
public class FestivityController {

	@Autowired
	private FestivityService festivityService;

	public static final String MESSAGE_WELCOME = "Welcome to Prodigious - Festity API";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("msg", "JCG Hello World!");
		return MESSAGE_WELCOME;
	}

	@RequestMapping(value = "/festivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> listAll() {
		List<Festivity> festivities = festivityService.findAll();
		if (festivities.isEmpty()) {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);
	}

	@RequestMapping(value = "/festivity/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> getFestityByName(String name) {
		List<Festivity> festivities = festivityService.findByName(name);
		if (festivities.isEmpty()) {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);
	}

	@RequestMapping(value = "/festivity/start_date", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> getFestityByStartDate(
			String startDate) {

		Date parseStartDate = Util.getDate(startDate);
		if (parseStartDate != null) {
			List<Festivity> festivities = festivityService.findByStartName(Util
					.getDate(startDate));
			if (festivities.isEmpty()) {
				return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Festivity>>(festivities,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/festivity/date_range", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> getFestityByDateRange(
			String startDate, String endDate) {

		Date parseStartDate = Util.getDate(startDate);
		Date parseEndDate = Util.getDate(endDate);
		if (parseStartDate != null && parseEndDate != null) {
			List<Festivity> festivities = festivityService.findByDataRange(
					parseStartDate, parseEndDate);
			if (festivities.isEmpty()) {
				return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Festivity>>(festivities,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/festivity/name_place", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> getFestityByNamePlace(
			String namePlace) {

		List<Festivity> festivities = festivityService.findByPlace(namePlace);
		if (festivities.isEmpty()) {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);

	}

	@RequestMapping(value = "/festivity/new", method = RequestMethod.POST)
	public ResponseEntity<Festivity> newFestity(
			@RequestBody FestivityDTO festivityDTO) {
		Festivity festivity = festivityService.newFestivity(festivityDTO);

		return new ResponseEntity<Festivity>(festivity, HttpStatus.OK);

	}

	@RequestMapping(value = "/festivity/update", method = RequestMethod.POST)
	public ResponseEntity<Festivity> updateFestity(String name,
			@RequestBody FestivityDTO festivityDTO) {

		Festivity festivity = festivityService.updateFestivity(name,
				festivityDTO);

		return new ResponseEntity<Festivity>(festivity, HttpStatus.OK);

	}

}
