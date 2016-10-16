/**
 * flights search
 */
package com.lastminute.daos;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.lastminute.entities.DaysPriorDepartureDate;

/**
 * @author ojuarez
 *
 */

@Repository("daysPriorDepartureDateDAO")
public class DaysPriorDepartureDateDAOImpl extends AbstractDAO<Integer, DaysPriorDepartureDate>
		implements DaysPriorDepartureDateDAO {

	@Override
	public double lookForPercentage(int daysPriorDepartureDate) {
		Criteria criteria = createEntityCriteria();
	
		if (daysPriorDepartureDate > 30) {
					criteria.add(Restrictions.eq("daysPriorId", new Integer(1)));
		} else if ((daysPriorDepartureDate <= 30) && (daysPriorDepartureDate > 15)) {
				criteria.add(Restrictions.eq("daysPriorId", new Integer(2)));
		} else if ((daysPriorDepartureDate <= 15) && (daysPriorDepartureDate > 2)) {
					criteria.add(Restrictions.eq("daysPriorId", new Integer(3)));
		} else {
				criteria.add(Restrictions.eq("daysPriorId", new Integer(4)));
		}
		DaysPriorDepartureDate daysPrior = (DaysPriorDepartureDate) criteria.uniqueResult();
		
		return daysPrior.getDaysPriorBasePrice();
	}
}
