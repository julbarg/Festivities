package com.prodigious.festivities.service;

import java.util.ArrayList;
import java.util.Date;

import com.prodigious.festivities.dto.FestivityDTO;
import com.prodigious.festivities.model.Festivity;

public interface FestivityService {

	ArrayList<Festivity> findAll();

	ArrayList<Festivity> findByName(String name);

	ArrayList<Festivity> findByStartName(Date startDate);

	ArrayList<Festivity> findByDataRange(Date startDate, Date endDate);

	ArrayList<Festivity> findByPlace(String namePlace);

	Festivity newFestivity(FestivityDTO festivity);

	Festivity updateFestivity(String name, FestivityDTO festivity);

}