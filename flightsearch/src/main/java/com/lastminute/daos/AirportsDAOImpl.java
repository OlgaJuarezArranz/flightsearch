/**
 * flight search
 */
package com.lastminute.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.lastminute.entities.Airports;

/**
 * @author ojuarez
 *
 */
@Repository("airportsDAO")
public class AirportsDAOImpl extends AbstractDAO<Integer, Airports> implements AirportsDAO {

	@Override
	public List<Integer> findcityIdsList(String originCity, String destinationCity) {
		Criteria criteria = createEntityCriteria();

		List<String> values = new ArrayList<String>();
		values.add(originCity);
		values.add(destinationCity);
		criteria.add(Restrictions.in("airportCity", values));
		List<Integer> cityIdsList = new ArrayList<Integer>();
		List<Airports> airportsList = (List<Airports>) criteria.list();
		// quitar
		for (int i = 0; i < airportsList.size(); i++) {
			Logger.getLogger(getClass().getName()).info(" *** la lista de  de los aeropuertos : "
					+ airportsList.get(i).getAirportId() + " " + airportsList.get(i).getAirportCity());
		}
		// fin quitar
		Map<String, Integer> cityMap = new HashMap<String, Integer>();
		for (Airports a : airportsList) {
			if (a.getAirportCity().equals(originCity)) {
				cityMap.put(originCity, a.getAirportId());
			}
			if (a.getAirportCity().equals(destinationCity)) {
				cityMap.put(destinationCity, a.getAirportId());
			}
		}
		cityIdsList.add(0, cityMap.get(originCity));
		cityIdsList.add(1, cityMap.get(destinationCity));
		// quitar
		for (int i = 0; i < cityIdsList.size(); i++) {
			Logger.getLogger(getClass().getName())
					.info(" *** la lista de ids de los aeropuertos en orden: " + cityIdsList.get(i));
		}
		// fin quitar
		return cityIdsList;
	}
}
