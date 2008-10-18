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

package org.posterita.businesslogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.posterita.beans.SearchBean;
import org.posterita.exceptions.OperationException;

public class SearchManager
{

	public static SearchBean getSearchParams(String searchText) throws OperationException
	{
		if(searchText == null)
			throw new OperationException("Search text cannot be null");
		
		
		SearchBean searchBean = new SearchBean(searchText);
		searchBean = parseExactSearch(searchBean);
		searchBean = parseAndSearch(searchBean);
		searchBean = parseOrSearch(searchBean);
		
		searchBean.setSearchText(searchText);
		
		return searchBean;
	}
	
	private static SearchBean parseExactSearch(SearchBean searchBean)
	{
		String searchText = searchBean.getSearchText().trim();
	
		int ind11 = searchText.indexOf("\"");
		
		if(ind11 == -1)
			return searchBean;
		
		String lText1 = searchText.substring(0, ind11).trim();
		String rText1 = searchText.substring(ind11 + 1).trim();
		
		int ind12 = rText1.indexOf("\"");
		
		if(ind12 == -1)
		{
			searchText = searchText.replaceAll("\"", "");
			searchBean.setSearchText(searchText);
			return searchBean;
		}
		
		String lText2 = rText1.substring(0, ind12);
		String rText2 = rText1.substring(ind12 + 1);
		
		String exactSearchText = lText2;
		String remSearchText = lText1.trim() + " " + rText2.trim();
		
		searchBean.setSearchText(remSearchText);
		searchBean.getAndCriteriasList().add(exactSearchText);
		
		return parseExactSearch(searchBean);
	}
	
	private static SearchBean parseAndSearch(SearchBean searchBean)
	{
		String searchText = searchBean.getSearchText();
		if(searchText == null || searchText.length() == 0)
			return searchBean;
		
		String regexStr = "\\+[\\w]+";
		
		Pattern regex = Pattern.compile(regexStr);
        Matcher regexMatcher = regex.matcher(searchText);
        
        if(regexMatcher.find())
        {
            int startInd = regexMatcher.start();
            int endInd = regexMatcher.end();
            
            String subStr = searchText.substring(startInd, endInd);
            subStr = subStr.replaceAll("\\+", "");
            searchBean.getAndCriteriasList().add(subStr);
            
            searchText = searchText.substring(0, startInd) + searchText.substring(endInd);
            searchBean.setSearchText(searchText);
            
            parseAndSearch(searchBean);
        }
        
        return searchBean;
	}
	
	private static SearchBean parseOrSearch(SearchBean searchBean)
	{
		String searchText = searchBean.getSearchText();
		if(searchText == null || searchText.length() == 0)
			return searchBean;
		
		String regexStr = "[\\w]+";
		
		Pattern regex = Pattern.compile(regexStr);
        Matcher regexMatcher = regex.matcher(searchText);
        
        if(regexMatcher.find())
        {
            int startInd = regexMatcher.start();
            int endInd = regexMatcher.end();
            
            String subStr = searchText.substring(startInd, endInd);
            subStr = subStr.replaceAll("\\+", "");
            searchBean.getOrCriteriasList().add(subStr);
            
            searchText = searchText.substring(0, startInd) + searchText.substring(endInd);
            searchBean.setSearchText(searchText);
            
            parseOrSearch(searchBean);
        }

        return searchBean;        
	}
}
