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
 * Created on Aug 15, 2005 by praveen
 *
 */
package org.posterita.businesslogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import org.posterita.beans.FilterBean;
import org.posterita.beans.UDIPair;

public class HistoryFilterBean 
{
    private TreeSet<UDIPair> bPartnerList = new TreeSet<UDIPair>();
    private TreeSet<UDIPair> docStatusList = new TreeSet<UDIPair>();
    
    public HistoryFilterBean(ArrayList<FilterBean> beans)
    {
        
        if(beans!=null)
        {
            Iterator<FilterBean> iter = beans.iterator();
                     
            while(iter.hasNext())
            {
                FilterBean bean = iter.next();
                
                if(bean.getPartnerId()!=null)
                {
                    UDIPair pair = new UDIPair(bean.getPartnerId(),bean.getPartnerName());
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
    

    public TreeSet<UDIPair> getBPartnerList()
    {
        return bPartnerList;
    }
    
    public TreeSet<UDIPair> getDocStatusList() 
    {
        return docStatusList;
    }



}
