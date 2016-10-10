/**
 * flights search
 */
package com.lastminute.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ojuarez
 *
 */
@Entity
@Table(name = "airports")
public class Airports implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "airport_id")
	private int airportId;

	@NotNull
	@Column(name = "airport_iata_code")
	private String airportIataCode;

	@Column(name = "airport_city")
	private String airportCity;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy =
	// "airportsByFlightDestinationAirportId")
	// private Collection<Flights> flightsForFlightDestinationAirportId;
	//
	// @OneToMany(cascade = CascadeType.ALL, mappedBy =
	// "airportsByFlightOriginAirportId")
	// private Collection<Flights> flightsForFlightOriginAirportId;

	// constructors
	public Airports() {

	}

	public Airports(int airportId, String airportIataCode, String airportCity) {
		this.airportId = airportId;
		this.airportIataCode = airportIataCode;
		this.airportCity = airportCity;
	}

	// public Airports(int airportId, String airportIataCode, String
	// airportCity,
	// Collection<Flights> flightsForFlightDestinationAirportId,
	// Collection<Flights> flightsForFlightOriginAirportId) {
	// this.airportId = airportId;
	// this.airportIataCode = airportIataCode;
	// this.airportCity = airportCity;
	// this.flightsForFlightDestinationAirportId =
	// flightsForFlightDestinationAirportId;
	// this.flightsForFlightOriginAirportId = flightsForFlightOriginAirportId;
	// }

//	public Airports(int airportId, String airportIataCode, String airportCity,
//			Collection<Flights> flightsForFlightDestinationAirportId,
//			Collection<Flights> flightsForFlightOriginAirportId) {
//		this.airportId = airportId;
//		this.airportIataCode = airportIataCode;
//		this.airportCity = airportCity;
//
//	}

	// setters and getters
	public int getAirportId() {
		return this.airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getAirportIataCode() {
		return this.airportIataCode;
	}

	public void setAirportIataCode(String airportIataCode) {
		this.airportIataCode = airportIataCode;
	}

	public String getAirportCity() {
		return this.airportCity;
	}

	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

//	public Collection<Flights> getFlightsForFlightDestinationAirportId() {
//		return this.flightsForFlightDestinationAirportId;
//	}
//
//	public void setFlightsForFlightDestinationAirportId(Collection<Flights> flightsForFlightDestinationAirportId) {
//		this.flightsForFlightDestinationAirportId = flightsForFlightDestinationAirportId;
//	}
}
