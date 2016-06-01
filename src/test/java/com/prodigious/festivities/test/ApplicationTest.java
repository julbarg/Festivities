package com.prodigious.festivities.test;

import static com.jayway.restassured.RestAssured.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.prodigious.festivities.Application;
import com.prodigious.festivities.dto.FestivityDTO;
import com.prodigious.festivities.model.Festivity;
import com.prodigious.festivities.service.FestivityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApplicationTest {

	@Autowired
	FestivityService festivityService;

	@Value("${local.server.port}")
	int port;

	Festivity battallaDeBoyaca;
	Integer id;

	SimpleDateFormat formatter;

	@Before
	public void setUp() throws ParseException {
		FestivityDTO festivityDTO = new FestivityDTO();
		festivityDTO.setName("BatallaDeBoyaca");
		festivityDTO.setStart("2016-08-7T01:00:00.001Z");
		festivityDTO.setEnd("2016-08-08T23:59:01.001Z");
		festivityDTO.setPlace("Colombia");

		battallaDeBoyaca = new Festivity(festivityDTO);
		battallaDeBoyaca = festivityService.save(battallaDeBoyaca);
		id = battallaDeBoyaca.getId();
		RestAssured.port = port;
	}

	@Test
	public void fetchFestivityByName() {
		String name = battallaDeBoyaca.getName();
		when().get("/festivityapi/festivity/name?name={name}", name).then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", Matchers.anything("BatallaDeBoyaca"));
	}

	@Test
	public void fetchFestivityByPlace() {
		String namePlace = battallaDeBoyaca.getNamePlace();
		when().get("/festivityapi/festivity/name_place?namePlace={namePlace}",
				namePlace).then().statusCode(HttpStatus.SC_OK)
				.body("namePlace", Matchers.anything("Colombia"));
	}

	@Test
	public void deleteFestivity() {
		System.out.println(id);
		when().get("/festivityapi/festivity/delete?id={id}", id).then()
				.statusCode(HttpStatus.SC_OK);
	}

}
