/**
 * flights search
 */
package com.lastminute.services;

import java.util.List;
import java.util.Map;

import com.lastminute.entities.PassengerTypes;
import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
public interface PassengerTypesService {
	
	public Map<Integer,PassengerTypes> lookForRulesApplied(Search search);

}
