/**
 * flights search
 */
package com.lastminute.daos;

import java.util.List;

import com.lastminute.entities.Flights;
import com.lastminute.entities.FoundFlights;
import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
public interface FlightsDAO {

	public List<Flights> findFlights(List<Integer> cityIdsList) throws Exception;
	
	public List<FoundFlights> foundAndCalculateFlightsAndPrices(Search search)throws Exception;

}
