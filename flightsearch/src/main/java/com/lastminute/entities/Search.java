/*
 * flight search
 */

package com.lastminute.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ojuarez
 *
 */
public class Search implements Serializable {

	private String originAirport;
	private String destinationAirport;
	private int adultPassengerNum;
	private int childPassengerNum;
	private int infantPassengerNum;
	private int daysPriorDepartureNum;

	// constructors

	public Search() {

	}

	/**
	 * @param originAirport
	 * @param destinationAirport
	 * @param adultPassengerNum
	 * @param childPassengerNum
	 * @param infantPassengerNum
	 * @param daysPriorDepartureNum
	 * 
	 */
	public Search(String originAirport, String destinationAirport, int adultPassengerNum, int childPassengerNum,
			int infantPassengerNum, int daysPriorDepartureNum) {
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.adultPassengerNum = adultPassengerNum;
		this.childPassengerNum = childPassengerNum;
		this.infantPassengerNum = infantPassengerNum;
		this.daysPriorDepartureNum = daysPriorDepartureNum;

	}

	// setters and getters

	/**
	 * @return the originAirport
	 */

	public String getOriginAirport() {
		return originAirport;
	}

	/**
	 * @param originAirport
	 *            the originAirport to set
	 */
	
	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	/**
	 * @return the destinationAirport
	 */
	public String getDestinationAirport() {
		return destinationAirport;
	}

	/**
	 * @param destinationAirport
	 *            the destinationAirport to set
	 */
	
	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	/**
	 * @return the adultPassengerNum
	 */
	public int getAdultPassengerNum() {
		return adultPassengerNum;
	}

	/**
	 * @param adultPassengerNum
	 *            the adultPassengerNum to set
	 */

	public void setAdultPassengerNum(int adultPassengerNum) {
		this.adultPassengerNum = adultPassengerNum;
	}

	/**
	 * @return the childPassengerNum
	 */
	public int getChildPassengerNum() {
		return childPassengerNum;
	}

	/**
	 * @param childPassengerNum
	 *            the childPassengerNum to set
	 */
	
	public void setChildPassengerNum(int childPassengerNum) {
		this.childPassengerNum = childPassengerNum;
	}

	/**
	 * @return the infantPassengerNum
	 */
	public int getInfantPassengerNum() {
		return infantPassengerNum;
	}

	/**
	 * @param infantPassengerNum
	 *            the infantPassengerNum to set
	 */
	
	public void setInfantPassengerNum(int infantPassengerNum) {
		this.infantPassengerNum = infantPassengerNum;
	}

	/**
	 * @return the daysPriorDepartureNum
	 */
	public int getDaysPriorDepartureNum() {
		return daysPriorDepartureNum;
	}

	/**
	 * @param daysPriorDepartureNum
	 *            the daysPriorDepartureNum to set
	 */
	
		public void setDaysPriorDepartureNum(int daysPriorDepartureNum) {
		this.daysPriorDepartureNum = daysPriorDepartureNum;
	}

}
