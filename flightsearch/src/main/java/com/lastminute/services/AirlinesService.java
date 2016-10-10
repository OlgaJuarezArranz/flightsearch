/**
 * flight search
 */
package com.lastminute.services;

import java.util.List;
import java.util.Map;

import com.lastminute.entities.Airlines;

/**
 * @author ojuarez
 *
 */
public interface AirlinesService {
	
	public Map<String, Double> lookForInfantPrice(List<String> airlinesCodeList);
}
