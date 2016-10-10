/**
 * flights search
 */
package com.lastminute.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastminute.daos.PassengerTypesDAO;
import com.lastminute.entities.PassengerTypes;
import com.lastminute.entities.Search;



/**
 * @author ojuarez
 *
 */
@Service("passengerTypesService")
@Transactional
public class PassengerTypesServiceImpl implements PassengerTypesService{
	
	@Autowired
    private PassengerTypesDAO passengerTypesDAO;
	
	@Override
	public Map<Integer,PassengerTypes> lookForRulesApplied(Search search){
		return passengerTypesDAO.lookForRulesApplied(search);
	}
}
