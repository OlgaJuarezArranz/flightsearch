/**
 * flights search
 */
package com.lastminute.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastminute.daos.DaysPriorDepartureDateDAO;
import com.lastminute.entities.DaysPriorDepartureDate;



/**
 * @author ojuarez
 *
 */
@Service("daysPriorDepartureDateService")
@Transactional
public class DaysPriorDepartureDateServiceImpl implements DaysPriorDepartureDateService {
	
	@Autowired
    private DaysPriorDepartureDateDAO daysPriorDepartureDateDAO;

	@Override
	public double lookForPercentage(int daysPriorDepartureDate){
		return daysPriorDepartureDateDAO.lookForPercentage(daysPriorDepartureDate);
	}

}
