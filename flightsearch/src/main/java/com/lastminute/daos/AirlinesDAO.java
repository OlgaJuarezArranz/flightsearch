/**
 * flight search
 */
package com.lastminute.daos;

import java.util.List;
import java.util.Map;

import com.lastminute.entities.Airlines;

/**
 * @author ojuarez
 *
 */
public interface AirlinesDAO {
	
	public Map<String, Double> lookForInfantPrice(List<String> airlinesCodeList);
}
