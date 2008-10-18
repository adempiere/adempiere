/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
* Created on May 15, 2006 by ashley
* 
*/

package org.posterita.beans;

import java.util.ArrayList;

public class SearchBean
{

	protected String searchText;
	protected ArrayList<String> andCriteriasList;
	protected ArrayList<String> orCriteriasList;
	protected ArrayList<String> notCriteriasList;
	
	public SearchBean(String searchText)
	{
		this.searchText = searchText;
		andCriteriasList = new ArrayList<String>();
		orCriteriasList = new ArrayList<String>();
		notCriteriasList = new ArrayList<String>();
	}

	public ArrayList<String> getAndCriteriasList()
	{
		return andCriteriasList;
	}

	public void setAndCriteriasList(ArrayList<String> andCriteriasList)
	{
		this.andCriteriasList = andCriteriasList;
	}

	public ArrayList<String> getNotCriteriasList()
	{
		return notCriteriasList;
	}

	public void setNotCriteriasList(ArrayList<String> notCriteriasList)
	{
		this.notCriteriasList = notCriteriasList;
	}

	public ArrayList<String> getOrCriteriasList()
	{
		return orCriteriasList;
	}

	public void setOrCriteriasList(ArrayList<String> orCriteriasList)
	{
		this.orCriteriasList = orCriteriasList;
	}

	public String getSearchText()
	{
		return searchText;
	}

	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}	
}
