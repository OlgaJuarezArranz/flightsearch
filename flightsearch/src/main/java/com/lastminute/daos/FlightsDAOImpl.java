/**
 * flights search
 */
package com.lastminute.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lastminute.entities.Flights;
import com.lastminute.entities.FoundFlights;
import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
@Repository("flightsDAO")
public class FlightsDAOImpl extends AbstractDAO<Integer, Flights> implements FlightsDAO {

	@Override
	public List<Flights> findFlights(List<Integer> cityIdsList) throws Exception {
		//quitar
		Logger.getLogger(getClass().getName()).info("***1");
		for (Integer i: cityIdsList){
			Logger.getLogger(getClass().getName()).info("*** en el for: " + i);
		}
		//fin quitar
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("airportsByFlightOriginAirportId", cityIdsList.get(0)));
		criteria.add(Restrictions.eq("airportsByFlightDestinationAirportId", cityIdsList.get(1)));
		Logger.getLogger(getClass().getName()).info("***2");
		List<Flights> foundFlightsList = new ArrayList<Flights>();
		Logger.getLogger(getClass().getName()).info("***3");
		try {
		foundFlightsList = criteria.list();
		} catch (Exception ex) {
			//quitar
			Logger.getLogger(getClass().getName()).info("***4");
			//fin quitar
			throw new Exception();
		}
		return foundFlightsList;
	}

	@Override
	public List<FoundFlights> foundAndCalculateFlightsAndPrices(Search search) {

		return null;
	}
}
