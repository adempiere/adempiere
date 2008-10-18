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
 * Created on Nov 17, 2005 by praveen
 *
 */
package org.posterita.beans;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.posterita.core.TimestampConvertor;


public class ReportImageBean 
{
    private String imagePath;
    private String imageTitle;
    private String altText;
    private String requestParams;
    private String dateFrom;
    private String dateTo;
    
    public ReportImageBean()
    {
        initDateFrom();
        initDateTo();
    }
    
    private void initDateFrom()
    {
        SimpleDateFormat df = new  SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
        
        Calendar calendar = GregorianCalendar.getInstance();
        
        if(calendar.get(Calendar.MONTH) == Calendar.JANUARY)
        {
            calendar.set(Calendar.DATE,9);
        }
        else
        {
            calendar.set(Calendar.DATE,1);
        }
        
        calendar.set(Calendar.HOUR_OF_DAY,9);
        calendar.set(Calendar.MINUTE,15);
        calendar.set(Calendar.SECOND,0);
        dateFrom = df.format(calendar.getTime());  
    }
    
    private void initDateTo()
    {
        SimpleDateFormat df = new  SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
        
        Calendar calendar = GregorianCalendar.getInstance();
        //int maxNoOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);        
		//calendar.set(Calendar.DATE,maxNoOfDays);		
		dateTo = df.format(calendar.getTime());
    }

    public String getAltText() 
    {
        return altText;
    }
    
    public void setAltText(String altText) 
    {
        this.altText = altText;
    }
    
    public String getImagePath() 
    {
        return imagePath;
    }
    
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
    
    public String getImageTitle()
    {
        return imageTitle;
    }
    
    public void setImageTitle(String imageTitle)
    {
        this.imageTitle = imageTitle;
    }
        
    public void setRequestParams(String requestParams)
    {
        this.requestParams = requestParams;
    }    
    
    public String getDateFrom() 
    {
        return dateFrom;
    }
    
    public void setDateFrom(String dateFrom) 
    {
        this.dateFrom = dateFrom;
    }
    
    public String getDateTo() 
    {
        return dateTo;
    }
    
    public void setDateTo(String dateTo)
    {
        this.dateTo = dateTo;
    }
    
    public String getRequestParams() 
    {
        requestParams = null;
        
        try 
        {
            requestParams = "dateFrom=" + URLEncoder.encode(dateFrom,"UTF-8") + 
            "&dateTo=" + URLEncoder.encode(dateTo,"UTF-8") +
            "&imagePath=" + URLEncoder.encode(imagePath,"UTF-8") +
            "&imageTitle=" + URLEncoder.encode(imageTitle,"UTF-8");
        }
        catch (UnsupportedEncodingException e) 
        {
            
        }		        
        return requestParams;
    }
}
