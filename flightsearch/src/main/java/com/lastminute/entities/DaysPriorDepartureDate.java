/**
 * flights search
 */
package com.lastminute.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ojuarez
 *
 */
@Entity
@Table(name = "days_prior_departure_date")
public class DaysPriorDepartureDate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "days_prior_id")
	private int daysPriorId;

	@Column(name = "days_prior_description")
	private String daysPriorDescription;

	@Column(name = "days_prior_base_price")
	private Double daysPriorBasePrice;

	//private int numDaysPriorDepartureDate;

	// contructors
	public DaysPriorDepartureDate() {
	}

	public DaysPriorDepartureDate(int daysPriorId) {
		this.daysPriorId = daysPriorId;
	}

	public DaysPriorDepartureDate(int daysPriorId, String daysPriorDescription, Double daysPriorBasePrice) {
		this.daysPriorId = daysPriorId;
		this.daysPriorDescription = daysPriorDescription;
		this.daysPriorBasePrice = daysPriorBasePrice;
	}

	// setters and getters
	public int getDaysPriorId() {
		return this.daysPriorId;
	}

	public void setDaysPriorId(int daysPriorId) {
		this.daysPriorId = daysPriorId;
	}

	public String getDaysPriorDescription() {
		return this.daysPriorDescription;
	}

	public void setDaysPriorDescription(String daysPriorDescription) {
		this.daysPriorDescription = daysPriorDescription;
	}

	public Double getDaysPriorBasePrice() {
		return this.daysPriorBasePrice;
	}

	public void setDaysPriorBasePrice(Double daysPriorBasePrice) {
		this.daysPriorBasePrice = daysPriorBasePrice;
	}

//	public int getNumDaysPriorDepartureDate() {
//		return this.numDaysPriorDepartureDate;
//	}
//
//	public void setNumDaysPriorDepartureDate(int numDaysPriorDepartureDate) {
//		this.numDaysPriorDepartureDate = numDaysPriorDepartureDate;
//	}
}
