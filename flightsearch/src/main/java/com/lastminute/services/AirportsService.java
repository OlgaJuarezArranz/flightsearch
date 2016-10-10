/**
 * flight search
 */
package com.lastminute.services;

import java.util.List;

import com.lastminute.entities.Airports;

/**
 * @author ojuarez
 *
 */
public interface AirportsService {

	public List<Integer> findcityIdsList(String originCity, String destinationCity);

}
