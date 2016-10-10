/**
 * flights search
 */
package com.lastminute.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastminute.daos.AirlinesDAO;
import com.lastminute.entities.Airlines;

/**
 * @author ojuarez
 *
 */

@Service("airlinesService")
@Transactional
public class AirlinesServiceImpl implements AirlinesService {
	
	@Autowired
    private AirlinesDAO airlinesDAO;
	
	@Override
	public Map<String, Double> lookForInfantPrice(List<String> airlinesCodeList){
		return airlinesDAO.lookForInfantPrice(airlinesCodeList);
	}
	
}
