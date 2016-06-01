package com.prodigious.festivities.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

		ArrayList<Festivity> listFestivity = new ArrayList<Festivity>();

		for (Festivity festivity : festivityService.findAll()) {
			listFestivity.add(festivity);
		}
		System.out.println("SIZE: " + listFestivity.size());

		if (listFestivity.isEmpty()) {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Festivity>>(listFestivity, HttpStatus.OK);
	}

	@RequestMapping(value = "/festivity/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> getFestityByName(String name) {

		List<Festivity> listFestivity = festivityService.findByName(name);
		if (listFestivity.isEmpty()) {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Festivity>>(listFestivity, HttpStatus.OK);
	}

	@RequestMapping(value = "/festivity/start_date", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Festivity>> getFestityByStartDate(
			String startDate) {

		Date parseStartDate = Util.getDate(startDate);
		if (parseStartDate != null) {

			Iterable<Festivity> iterableFestivity = festivityService
					.findByStartDateAfter(parseStartDate);
			List<Festivity> listFestivity = new LinkedList<Festivity>();
			for (Festivity festivity : iterableFestivity) {
				listFestivity.add(festivity);
			}

			if (listFestivity.isEmpty()) {
				return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Festivity>>(listFestivity,
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
			List<Festivity> festivities = festivityService
					.findByStartDateBetween(parseStartDate, parseEndDate);

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

		List<Festivity> festivities = festivityService
				.findByNamePlace(namePlace);
		if (festivities.isEmpty()) {
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);

	}

	@RequestMapping(value = "/festivity/new", method = RequestMethod.POST)
	public ResponseEntity<Festivity> newFestity(
			@RequestBody FestivityDTO festivityDTO) {

		Festivity festivity = new Festivity(festivityDTO);
		festivity = festivityService.save(festivity);

		return new ResponseEntity<Festivity>(festivity, HttpStatus.OK);

	}

	@RequestMapping(value = "/festivity/update", method = RequestMethod.POST)
	public ResponseEntity<Festivity> updateFestity(Integer id,
			@RequestBody FestivityDTO festivityDTO) {

		Festivity festivity = festivityService.findOne(id);
		festivity.setName(festivityDTO.getName());
		Date startDate = Util.getDate(festivityDTO.getStart());
		Date endDate = Util.getDate(festivityDTO.getEnd());
		festivity.setStartDate(startDate != null ? startDate : festivity
				.getStartDate());
		festivity
				.setEndDate(endDate != null ? endDate : festivity.getEndDate());
		festivity.setNamePlace(festivity.getNamePlace());

		festivityService.save(festivity);

		return new ResponseEntity<Festivity>(festivity, HttpStatus.OK);

	}

	@RequestMapping(value = "/festivity/delete", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> delete(Integer id) {
		try {
			Festivity festivity = new Festivity(id);
			festivityService.delete(festivity);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/festivity/load", method = RequestMethod.POST)
	public ResponseEntity<Void> load(
			@RequestBody List<FestivityDTO> listFestivityDTO) {

		for (FestivityDTO festivity : listFestivityDTO) {
			System.out.println(festivity.getName());
		}

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
