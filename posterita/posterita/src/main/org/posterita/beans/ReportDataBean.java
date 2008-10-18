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
 * Created on Nov 21, 2005 by praveen
 *
 */
package org.posterita.beans;


public class ReportDataBean 
{
    private String model;
    private int count;
    private int automatic;
    private int manual;
    private int sale;
    private int target;
    
    public ReportDataBean()
    {
        model = "";
        count = 0;
        automatic = 0;
        manual = 0;
        sale = 0;
        target = 0;
    }

    public int getAutomatic() 
    {
        return automatic;
    }
    
    public void setAutomatic(int automatic) 
    {
        this.automatic = automatic;
    }
    
    public int getCount() 
    {
        return count;
    }
    
    public void setCount(int count) 
    {
        this.count = count;
    }
    
    public int getManual() 
    {
        return manual;
    }
    
    public void setManual(int manual) 
    {
        this.manual = manual;
    }
    
    public String getModel() 
    {
        return model;
    }
    
    public void setModel(String model) 
    {
        this.model = model;
    }
    
    public int getSale() 
    {
        return sale;
    }
    
    public void setSale(int sale) {
        this.sale = sale;        
    }
    
    public int getTarget() 
    {
        return target;
    }
    
    public void setTarget(int target) 
    {
        this.target = target;
    }
}
