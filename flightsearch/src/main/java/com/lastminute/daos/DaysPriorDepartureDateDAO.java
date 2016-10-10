/**
 * flights search
 */
package com.lastminute.daos;

import java.util.List;

import com.lastminute.entities.DaysPriorDepartureDate;

/**
 * @author ojuarez
 *
 */
public interface DaysPriorDepartureDateDAO {
	
	public double lookForPercentage(int daysPriorDepartureDate);

}
