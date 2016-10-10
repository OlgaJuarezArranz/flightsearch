/**
 * flights search
 */
package com.lastminute.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastminute.daos.SearchDAO;
import com.lastminute.entities.Search;

/**
@author ojuarez
*
*/

@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService{

	@Autowired
    private SearchDAO searchDAO;
	
	@Override
	public void setSearch(Search search) {
		searchDAO.setSearch(search);
	}
	
	@Override
	public Search getSearch (){
		return searchDAO.getSearch();
	}

}
