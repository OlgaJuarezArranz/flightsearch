/**
 * flight search
 */
package com.lastminute.daos;

import org.springframework.stereotype.Repository;

import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
@Repository("searchDAO")
public class SearchDAOImpl implements SearchDAO {

	@Override
	public void setSearch(Search search) {
		setSearch(search);
	}
	
	@Override
	public Search getSearch(){
		return getSearch();
	}

}
