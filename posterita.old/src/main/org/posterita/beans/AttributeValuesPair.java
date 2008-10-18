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

import org.compiere.util.KeyNamePair;


public class AttributeValuesPair 
{
    private KeyNamePair makeAttributeValue;
    private KeyNamePair modelAttributeValue;
    private KeyNamePair modelGroupAttributeValue;
    private KeyNamePair colourAttributeValue;
    private KeyNamePair transmissionAttributeValue;
    private KeyNamePair yearAttributeValue;
    private KeyNamePair styleAttributeValue;
    private KeyNamePair sizeAttributeValue;
    private KeyNamePair designAttributeValue;
    private KeyNamePair brandAttributeValue;
    
     
    public KeyNamePair getBrandAttributeValue() 
    {
        return brandAttributeValue;
    }

    public void setBrandAttributeValue(KeyNamePair brandAttributeValue) 
    {
        this.brandAttributeValue = brandAttributeValue;
    }

    public KeyNamePair getColourAttributeValue() 
    {
        return colourAttributeValue;
    }
    
    public void setColourAttributeValue(KeyNamePair colourAttributeValue) 
    {
        this.colourAttributeValue = colourAttributeValue;
    }
    
    public KeyNamePair getModelAttributeValue() 
    {
        return modelAttributeValue;
    }
    
    public void setModelAttributeValue(KeyNamePair modelAttributeValue) 
    {
        this.modelAttributeValue = modelAttributeValue;
    }
    
    
    public KeyNamePair getTransmissionAttributeValue() 
    {
        return transmissionAttributeValue;
    }
    
    public void setTransmissionAttributeValue(KeyNamePair transmissionAttributeValue) 
    {
        this.transmissionAttributeValue = transmissionAttributeValue;
    }
    
    public KeyNamePair getYearAttributeValue() 
    {
        return yearAttributeValue;
    }
    
    public void setYearAttributeValue(KeyNamePair yearAttributeValue) 
    {
        this.yearAttributeValue = yearAttributeValue;
    }
    
    public KeyNamePair getMakeAttributeValue()
    {
        return makeAttributeValue;
    }
    
    public void setMakeAttributeValue(KeyNamePair makeAttributeValue)
    {
        this.makeAttributeValue = makeAttributeValue;
    }
        
    public KeyNamePair getModelGroupAttributeValue() 
    {
        return modelGroupAttributeValue;
    }
    
    public void setModelGroupAttributeValue(KeyNamePair modelGroupAttributeValue) 
    {
        this.modelGroupAttributeValue = modelGroupAttributeValue;
    }
    
    public KeyNamePair getDesignAttributeValue()
    {
        return designAttributeValue;
    }
    
    public void setDesignAttributeValue(KeyNamePair designAttributeValue)
    {
        this.designAttributeValue = designAttributeValue;
    }
    
    public KeyNamePair getSizeAttributeValue()
    {
        return sizeAttributeValue;
    }
    
    public void setSizeAttributeValue(KeyNamePair sizeAttributeValue)
    {
        this.sizeAttributeValue = sizeAttributeValue;
    }
    
    public KeyNamePair getStyleAttributeValue()
    {
        return styleAttributeValue;
    }
    
    public void setStyleAttributeValue(KeyNamePair styleAttributeValue)
    {
        this.styleAttributeValue = styleAttributeValue;
    }
}
