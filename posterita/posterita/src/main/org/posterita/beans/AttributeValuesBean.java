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
*/
package org.posterita.beans;


import java.util.ArrayList;

import org.compiere.util.KeyNamePair;

public class AttributeValuesBean extends UDIBean
{
    private ArrayList<KeyNamePair> brand = new ArrayList<KeyNamePair>();
    private ArrayList<Object> model = new ArrayList<Object>();
    private ArrayList design = new ArrayList();
    private ArrayList colour = new ArrayList();
    private ArrayList size = new ArrayList();
    private ArrayList trx = new ArrayList();
    private ArrayList years = new ArrayList();
    private ArrayList ETAList = new ArrayList();
    private ArrayList<KeyNamePair> make = new ArrayList<KeyNamePair>();
    private ArrayList modelGroup = new ArrayList();
    
    public void setAttributeId(Integer attributeId) 
    {	
        this.attributeId = attributeId;
    }
    
    public Integer getAttributeSetId() 
    {
        return attributeSetId;
    }
    
	public ArrayList getAttributeSet()
	{
		return attributeSet;
	}
	public void setAttributeSet(ArrayList attributeSet)
	{
		this.attributeSet = attributeSet;
	}
    private ArrayList attributeSet;
    
    public ArrayList getColour() 
    {
        return colour;
    }
    
    public void setColour(ArrayList colour) 
    {
        this.colour = colour;
    }
    
    public ArrayList<Object> getModel() 
    {
        return model;
    }
    
    public void setModel(ArrayList<Object> model) 
    {
        this.model = model;
    }
    
    public ArrayList getTrx() 
    {
        return trx;
    }
    
    public void setTrx(ArrayList trx) 
    {
        this.trx = trx;
    }
    
    public ArrayList getYears() 
    {
        return years;
    }
    
    public void setYears(ArrayList years) 
    {
        this.years = years;
    }
    
    public ArrayList<KeyNamePair> getMake()
    {
        return make;
    }
    
    public void setMake(ArrayList<KeyNamePair> make)
    {
        this.make = make;
    }
        
    public ArrayList getModelGroup() 
    {
        return modelGroup;
    }
    
    public void setModelGroup(ArrayList modelGroup)
    {
        this.modelGroup = modelGroup;
    }
    
    public ArrayList getETAList()
    {
        return ETAList;
    }
    
    public void setETAList(ArrayList list)
    {
        ETAList = list;
    }

    public ArrayList<KeyNamePair> getBrand() {
        return brand;
    }

    public void setBrand(ArrayList<KeyNamePair> brand) {
        this.brand = brand;
    }

    public ArrayList getDesign() {
        return design;
    }

    public void setDesign(ArrayList design) {
        this.design = design;
    }

    public ArrayList getSize() {
        return size;
    }

    public void setSize(ArrayList size) {
        this.size = size;
    }
    
    
}
