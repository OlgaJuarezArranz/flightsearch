/**
 * flights search
 */
package com.lastminute.services;

import java.util.List;

import com.lastminute.entities.Flights;
import com.lastminute.entities.FoundFlights;
import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
public interface FlightsService {
	
	public  List<Flights> findFlights(List<Integer> cityIdsList)throws Exception;
	
	public List<FoundFlights> foundAndCalculateFlightsAndPrices(Search search) throws Exception;

}
