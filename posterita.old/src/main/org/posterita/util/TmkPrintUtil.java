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
 * Created on Aug 23, 2006
 */


package org.posterita.util;


public class TmkPrintUtil 
{

	 private static final int TOTAL_PAGE_LENGTH=40;
	    private static final int MAX_ALLOWED_FOR_FIELD=15;
	    public static String alignRight(String data)
	    {
	        int dataLength=data.length();
	        String padding=" ";
	        for( int i=0;i<TOTAL_PAGE_LENGTH-dataLength;i++)
	        {
	            padding=padding+" ";
	        }
	        return padding+data;
	    }
	    
	    public static String alignInTwoline(String data)
	    {
	        String finaldata="";
	        String initData=data;
	        
	        for(int i=0;i<data.length()/14;i++)
	        {
	            if(initData.length()>14)
	            {
	                finaldata=finaldata+"\n"+initData.substring(0,MAX_ALLOWED_FOR_FIELD);
	                initData=initData.substring(MAX_ALLOWED_FOR_FIELD,initData.length()-1);
	            }
	            
	        }
	        
	        finaldata=finaldata+"\n"+initData;
	        
	       return finaldata.trim(); 
	            
	            
	    }
	    
	    
	    public static String alignInCorners(String data1,String data2)
	    {
	        
	        
	        return null;
	    }
}
