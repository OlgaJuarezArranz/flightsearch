/**
 * flight search
 */

package com.lastminute.daos;

import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
public interface SearchDAO {

	public void setSearch (Search search);
	
	public Search getSearch ();
}
