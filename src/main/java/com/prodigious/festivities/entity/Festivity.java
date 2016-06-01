package com.prodigious.festivities.entity;

import java.util.Date;

public class Festivity {

	private String name;

	private Date startDate;

	private Date dateRange;

	private String namePlace;

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

	public Date getDateRange() {
		return dateRange;
	}

	public void setDateRange(Date dateRange) {
		this.dateRange = dateRange;
	}

	public String getNamePlace() {
		return namePlace;
	}

	public void setNamePlace(String namePlace) {
		this.namePlace = namePlace;
	}

}
