package com.prodigious.festivities.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodigious.festivities.dto.FestivityDTO;
import com.prodigious.festivities.model.Festivity;
import com.prodigious.festivities.util.Util;

@Service("festivityServiceImpl")
@Transactional
public class FestivityServiceImpl implements FestivityService {

	@Override
	public ArrayList<Festivity> findAll() {

		ArrayList<Festivity> listFestivities = new ArrayList<Festivity>();
		Festivity festivity1 = new Festivity();
		festivity1.setName("UNO");
		festivity1.setNamePlace("CENTER");

		Festivity festivity2 = new Festivity();
		festivity2.setName("DOS");
		festivity2.setNamePlace("SOUTH");

		listFestivities.add(festivity1);
		listFestivities.add(festivity2);

		return listFestivities;
	}

	@Override
	public ArrayList<Festivity> findByName(String name) {
		ArrayList<Festivity> listFestivities = new ArrayList<Festivity>();
		Festivity festivity1 = new Festivity();
		festivity1.setName(name);
		festivity1.setNamePlace(name);

		listFestivities.add(festivity1);

		return listFestivities;
	}

	@Override
	public ArrayList<Festivity> findByStartName(Date startDate) {
		ArrayList<Festivity> listFestivities = new ArrayList<Festivity>();
		Festivity festivity1 = new Festivity();
		festivity1.setStartDate(startDate);

		listFestivities.add(festivity1);

		return listFestivities;
	}

	@Override
	public ArrayList<Festivity> findByDataRange(Date startDate, Date endDate) {
		ArrayList<Festivity> listFestivities = new ArrayList<Festivity>();
		Festivity festivity1 = new Festivity();
		festivity1.setStartDate(startDate);
		festivity1.setEndDate(endDate);

		listFestivities.add(festivity1);

		return listFestivities;
	}

	@Override
	public ArrayList<Festivity> findByPlace(String namePlace) {
		ArrayList<Festivity> listFestivities = new ArrayList<Festivity>();
		Festivity festivity1 = new Festivity();
		festivity1.setNamePlace(namePlace);

		listFestivities.add(festivity1);

		return listFestivities;
	}

	@Override
	public Festivity newFestivity(FestivityDTO festivity) {
		Festivity festivity1 = new Festivity();
		festivity1.setName(festivity.getName());
		festivity1.setStartDate(Util.getDate(festivity.getStartDate()));
		festivity1.setEndDate(Util.getDate(festivity.getEndDate()));
		festivity1.setNamePlace(festivity.getNamePlace());

		return festivity1;
	}

	@Override
	public Festivity updateFestivity(String name, FestivityDTO festivity) {
		Festivity festivity1 = new Festivity();
		festivity1.setName(festivity.getName());
		festivity1.setStartDate(Util.getDate(festivity.getStartDate()));
		festivity1.setEndDate(Util.getDate(festivity.getEndDate()));
		festivity1.setNamePlace(festivity.getNamePlace() + " cambio");

		return festivity1;
	}

}
