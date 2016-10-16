/**
 * flights search
 */
package com.lastminute.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
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
     
        List<Integer> restrictionsList = new ArrayList<Integer>();
        if (search.getAdultPassengerNum() != 0) {
            Integer one = new Integer(1);
            restrictionsList.add(one);
        }
        if (search.getChildPassengerNum() != 0) {
            Integer two = new Integer(2);
            restrictionsList.add(two);
        }
        if (search.getInfantPassengerNum() != 0) {
            Integer three = new Integer(3);
            restrictionsList.add(three);
        }
        criteria.add(Restrictions.in("passengerTypeId", restrictionsList));
        List<PassengerTypes> passengerRules = criteria.list();
  
        Map<Integer, PassengerTypes> passengerRulesMap = new HashMap<Integer, PassengerTypes>();
        for (int i = 0; i < passengerRules.size(); i++) {
          
            Integer intKey = new Integer(passengerRules.get(i).getPassengerTypeId());
    
            passengerRulesMap.put(intKey, passengerRules.get(i));
        }
            
        return passengerRulesMap;

    }
}
