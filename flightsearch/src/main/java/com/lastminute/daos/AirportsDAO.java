/**
 * flight search
 */
package com.lastminute.daos;

import java.util.List;

import com.lastminute.entities.Airports;

/**
 * @author ojuarez
 *
 */
public interface AirportsDAO {
	
	public List<Integer> findcityIdsList(String originCity, String destinationCity);

}
