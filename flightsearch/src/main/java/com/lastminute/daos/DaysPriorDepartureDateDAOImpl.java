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
		// quitar

		Logger.getLogger(getClass().getName())
				.info(" --- numero de dias previos a la salida : " + daysPriorDepartureDate);
		// fin quitar
		if (daysPriorDepartureDate > 30) {
			// quitar

			Logger.getLogger(getClass().getName()).info(" --- paso por el mayor de 30");
			// fin quitar
			criteria.add(Restrictions.eq("daysPriorId", new Integer(1)));
		} else if ((daysPriorDepartureDate <= 30) && (daysPriorDepartureDate > 15)) {
			// quitar

			Logger.getLogger(getClass().getName()).info(" --- paso por el menos o igual que 30 y mayor que 15");
			// fin quitar
			criteria.add(Restrictions.eq("daysPriorId", new Integer(2)));
		} else if ((daysPriorDepartureDate <= 15) && (daysPriorDepartureDate > 2)) {
			// quitar

			Logger.getLogger(getClass().getName()).info(" --- paso por el menos o igual que 15 y mayor que 2");
			// fin quitar
			criteria.add(Restrictions.eq("daysPriorId", new Integer(3)));
		} else {
			// quitar

			Logger.getLogger(getClass().getName()).info(" --- paso por el menor de 3");
			// fin quitar
			criteria.add(Restrictions.eq("daysPriorId", new Integer(4)));
		}
		DaysPriorDepartureDate daysPrior = (DaysPriorDepartureDate) criteria.uniqueResult();
		// quitar

		Logger.getLogger(getClass().getName())
				.info(" --- resultado de la busqueda en la tabla days_prior_departure_date : id= "
						+ daysPrior.getDaysPriorId() + " rango de diass= " + daysPrior.getDaysPriorDescription()
						+ " porcentage del precio= " + daysPrior.getDaysPriorBasePrice());
		// fin quitar
		return daysPrior.getDaysPriorBasePrice();
	}
}
