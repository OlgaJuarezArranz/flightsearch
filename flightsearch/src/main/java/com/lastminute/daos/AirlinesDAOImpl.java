/**
 * flight search
 */
package com.lastminute.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lastminute.entities.Airlines;

/**
 * @author ojuarez
 *
 */
@Repository("airlinesDAO")
public class AirlinesDAOImpl extends AbstractDAO<Integer, Airlines> implements AirlinesDAO {
	
	 public AirlinesDAOImpl() {
		
	}

	@Override
	public Map<String, Double> lookForInfantPrice(List<String> airlinesCodeList) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.in("airlineIataCode", airlinesCodeList));
		List<Airlines> airlinesList = criteria.list();
		Map<String, Double> infantPriceMap = new HashMap<String, Double>();
		for (int i = 0; i < airlinesList.size(); i++) {
			Double d = new Double(airlinesList.get(i).getAirlineInfantPrice());
			infantPriceMap.put(airlinesList.get(i).getAirlineIataCode(), d);
		}
		return infantPriceMap;
	}

}
