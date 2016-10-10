/**
 * flights search
 */
package com.lastminute.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastminute.daos.AirportsDAO;
import com.lastminute.entities.Airports;



/**
 * @author ojuarez
 *
 */
@Service("airportsService")
@Transactional
public class AirportsServiceImpl implements AirportsService{
	
	@Autowired
    private AirportsDAO airportsDAO;
	
	@Override
	public List<Integer> findcityIdsList(String originCity, String destinationCity){
		return airportsDAO.findcityIdsList(originCity,destinationCity);
	}

}
