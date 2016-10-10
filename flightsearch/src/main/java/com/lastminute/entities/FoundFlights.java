/**
 * flight search
 */
package com.lastminute.entities;

import java.io.Serializable;

/**
 * @author ojuarez
 *
 */
public class FoundFlights implements Serializable {

	private String foundFlightCode;
	private double foundFlightTotalPrice;
	

	// contructors
	public FoundFlights() {

	}

	public FoundFlights(String foundFlightCode, double foundFlightTotalPrice) {
		this.foundFlightCode = foundFlightCode;
		this.foundFlightTotalPrice = foundFlightTotalPrice;
	}
	
	//setters and getters
	public String getFoundFlightCode() {
		return foundFlightCode;
	}

	public void setFoundFlightCode(String foundFlightCode) {
		this.foundFlightCode = foundFlightCode;
	}

	public double getFoundFlightTotalPrice() {
		return foundFlightTotalPrice;
	}

	public void setFoundFlightTotalPrice(double foundFlightTotalPrice) {
		this.foundFlightTotalPrice = foundFlightTotalPrice;
	}

}
