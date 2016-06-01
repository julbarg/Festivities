package com.prodigious.festivities.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.prodigious.festivities.dto.FestivityDTO;
import com.prodigious.festivities.util.Util;

@Entity
@Table(name = "festivity")
public class Festivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "name")
	private String name;

	@Basic(optional = false)
	@NotNull
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "name_place")
	private String namePlace;

	public Festivity() {

	}

	public Festivity(FestivityDTO festivityDTO) {
		this.name = festivityDTO.getName();
		this.startDate = Util.getDate(festivityDTO.getStart());
		this.endDate = Util.getDate(festivityDTO.getEnd());
		this.namePlace = festivityDTO.getPlace();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNamePlace() {
		return namePlace;
	}

	public void setNamePlace(String namePlace) {
		this.namePlace = namePlace;
	}

}
