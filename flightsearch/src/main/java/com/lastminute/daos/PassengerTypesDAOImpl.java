/**
 * flights search
 */
package com.lastminute.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.lastminute.entities.PassengerTypes;
import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
@Repository("passengerTypesDAO")
public class PassengerTypesDAOImpl extends AbstractDAO<Integer, PassengerTypes> implements PassengerTypesDAO {

	@Override
	public Map<Integer, PassengerTypes> lookForRulesApplied(Search search) {
		Criteria criteria = createEntityCriteria();
		// quitar

		Logger.getLogger(getClass().getName())
				.info(" +++ numero de pasageros adultos : " + search.getAdultPassengerNum());
		Logger.getLogger(getClass().getName())
				.info(" +++ numero de pasageros ninnos : " + search.getChildPassengerNum());
		Logger.getLogger(getClass().getName())
				.info(" +++ numero de pasageros bebes : " + search.getInfantPassengerNum());

		// fin quitar
		List<Integer> restrictionsList = new ArrayList<Integer>();
		if (search.getAdultPassengerNum() != 0) {
			restrictionsList.add(search.getAdultPassengerNum());
				}
		if (search.getChildPassengerNum() != 0) {
			restrictionsList.add(search.getChildPassengerNum());
		}
		if (search.getInfantPassengerNum() != 0) {
			restrictionsList.add(search.getInfantPassengerNum());
		}
		criteria.add(Restrictions.in("passengerTypeId", restrictionsList));
		List<PassengerTypes> passengerRules = criteria.list();
		// quitar
		for (int i = 0; i < passengerRules.size(); i++) {
			Logger.getLogger(getClass().getName())
					.info(" +++ la lista de los tipos de pasageros de la busqueda: "
							+ passengerRules.get(i).getPassengerTypeId() + " "
							+ passengerRules.get(i).getPassengerTypeDescription() + " "
							+ passengerRules.get(i).getPassengerTypePrice() + " "
							+ passengerRules.get(i).isPassengerTypeDaysRule());
		}
		// fin quitar
		Map<Integer, PassengerTypes> passengerRulesMap = new HashMap<Integer, PassengerTypes>();
		for (int i = 0; i < passengerRules.size(); i++) {
			// quitar: este for no da problemas
			Logger.getLogger(getClass()).info(" $$$$ iteracion dentro del for = " + i);
			// fin quitar
			Integer intKey =new Integer(passengerRules.get(i).getPassengerTypeId());
			//quitar
			Logger.getLogger(getClass().getName()).info(" $$$ la clave que intento meter al map " + intKey);
			Logger.getLogger(getClass().getName()).info(" $$$ el elemento que intento meter al map " + passengerRules.get(i));
			//fin quitar
			passengerRulesMap.put(intKey, passengerRules.get(i));
		}
		// quitar
		Logger.getLogger(getClass()).info(" $$$$ salgo del for del problema ");

		Iterator<Integer> it = passengerRulesMap.keySet().iterator();
		//Set<Integer> keySet = passengerRulesMap.keySet();
		// quitar
		Logger.getLogger(getClass().getName()).info(" +++ antes del while, despues de llamar al iterator");
		
//		for (Integer k: keySet){
//			Integer key = k;
//			PassengerTypes value = passengerRules.get(k)
//		}

		while (it.hasNext()) {
			Integer key = it.next();
			PassengerTypes value = passengerRulesMap.get(key);

			Logger.getLogger(getClass().getName()).info(" +++ en el while, key= " + key);
			Logger.getLogger(getClass().getName()).info(" +++ en el while, value= " + value);
			Logger.getLogger(getClass().getName())
					.info(" +++ Map por id como clave de los tipos de pasageros de la busqueda: " + key + " " + value);

		}
		// quitar
		Logger.getLogger(getClass().getName()).info(" +++ despues del while");
		// fin quitar
		return passengerRulesMap;

	}
}
