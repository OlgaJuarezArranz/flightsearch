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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ojuarez
 *
 */
@Entity
@Table(name = "flights")
public class Flights implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "flight_id")
	private int flightId;

//	@NotNull
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "flight_destination_airport_id")
//	private Airports airportsByFlightDestinationAirportId;
	
	@NotNull
	@Column(name = "flight_destination_airport_id")
	private int airportsByFlightDestinationAirportId;

//	@NotNull
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "flight_origin_airport_id")
//		private Airports airportsByFlightOriginAirportId;
	
	@NotNull
	@Column(name = "flight_origin_airport_id")
	private int airportsByFlightOriginAirportId;

	@NotNull
	@Column(name = "flight_code")
	private String flightCode;

	@NotNull
	@Column(name = "flight_base_price")
	private int flightBasePrice;

	// constructors
	public Flights() {
	}

//	public Flights(int flightId, Airports airportsByFlightDestinationAirportId,
//			Airports airportsByFlightOriginAirportId, String flightCode, int flightBasePrice) {
//		this.flightId = flightId;
//		this.airportsByFlightDestinationAirportId = airportsByFlightDestinationAirportId;
//		this.airportsByFlightOriginAirportId = airportsByFlightOriginAirportId;
//		this.flightCode = flightCode;
//		this.flightBasePrice = flightBasePrice;
//	}
	
	public Flights(int flightId, int airportsByFlightDestinationAirportId,
			int airportsByFlightOriginAirportId, String flightCode, int flightBasePrice) {
		this.flightId = flightId;
		this.airportsByFlightDestinationAirportId = airportsByFlightDestinationAirportId;
		this.airportsByFlightOriginAirportId = airportsByFlightOriginAirportId;
		this.flightCode = flightCode;
		this.flightBasePrice = flightBasePrice;
	}

	// setters and getters
	public int getFlightId() {
		return this.flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getAirportsByFlightDestinationAirportId() {
		return this.airportsByFlightDestinationAirportId;
	}

	public void setAirportsByFlightDestinationAirportId(int airportsByFlightDestinationAirportId) {
		this.airportsByFlightDestinationAirportId = airportsByFlightDestinationAirportId;
	}

	public int getAirportsByFlightOriginAirportId() {
		return this.airportsByFlightOriginAirportId;
	}

	public void setAirportsByFlightOriginAirportId(int airportsByFlightOriginAirportId) {
		this.airportsByFlightOriginAirportId = airportsByFlightOriginAirportId;
	}

	public String getFlightCode() {
		return this.flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public int getFlightBasePrice() {
		return this.flightBasePrice;
	}

	public void setFlightBasePrice(int flightBasePrice) {
		this.flightBasePrice = flightBasePrice;
	}
}
