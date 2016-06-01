package com.prodigious.festivities.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.prodigious.festivities.model.Festivity;


@Transactional
public interface FestivityService extends CrudRepository<Festivity, Integer> {

   List<Festivity> findByName(String name);

   List<Festivity> findByStartDateAfter(Date startDate);

   List<Festivity> findByStartDateBetween(Date startDate, Date endDate);

   List<Festivity> findByNamePlace(String namePlace);

}