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
@Table(name = "airlines")
public class Airlines implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "airline_id")
	private int airlineId;

	@NotNull
	@Column(name = "airline_iata_code")
	private String airlineIataCode;

	@Column(name = "airline_name")
	private String airlineName;

	@NotNull
	@Column(name = "airline_infant_price")
	private double airlineInfantPrice;

	// constructors
	public Airlines() {
	}

	public Airlines(int airlineId, String airlineIataCode, double airlineInfantPrice) {
		this.airlineId = airlineId;
		this.airlineIataCode = airlineIataCode;
		this.airlineInfantPrice = airlineInfantPrice;
	}

	public Airlines(int airlineId, String airlineIataCode, String airlineName, double airlineInfantPrice) {
		this.airlineId = airlineId;
		this.airlineIataCode = airlineIataCode;
		this.airlineName = airlineName;
		this.airlineInfantPrice = airlineInfantPrice;
	}

	// setters and getters
	public int getAirlineId() {
		return this.airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineIataCode() {
		return this.airlineIataCode;
	}

	public void setAirlineIataCode(String airlineIataCode) {
		this.airlineIataCode = airlineIataCode;
	}

	public String getAirlineName() {
		return this.airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public double getAirlineInfantPrice() {
		return this.airlineInfantPrice;
	}

	public void setAirlineInfantPrice(double airlineInfantPrice) {
		this.airlineInfantPrice = airlineInfantPrice;
	}

}
