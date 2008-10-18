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
 *
 */
package org.posterita.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;



/**
 * @author alok
 */

public class DocumentHistortFilterBean
{
	
	    private TreeSet<UDIPair> bPartnerList = new TreeSet<UDIPair>();
	    private TreeSet<UDIPair> docStatusList = new TreeSet<UDIPair>();
	    
	    public DocumentHistortFilterBean(ArrayList beans)
	    {
	        
	        if(beans!=null)
	        {
	            Iterator iter = beans.iterator();
	                     
	            while(iter.hasNext())
	            {
	                DocumentHistoryBean bean = (DocumentHistoryBean) iter.next();
	                
	                if(bean.getBpartnerId()!=null)
	                {
	                    UDIPair pair = new UDIPair(bean.getBpartnerId(),bean.getPartnerName());
	                    bPartnerList.add(pair);                      
	                }
	                
	               if(bean.getDocStatus()!= null)
	                {
	                   UDIPair docStatusPair = new UDIPair(bean.getDocStatusCode(), bean.getDocStatus());                             
	                   docStatusList.add(docStatusPair);
	                }                
	            } 
	        }    

	    }
	    

	    public TreeSet getBPartnerList()
	    {
	        return bPartnerList;
	    }
	    
	    public TreeSet getDocStatusList() 
	    {
	        return docStatusList;
	    }



	
}
