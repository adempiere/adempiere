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
 * Created on Aug 24, 2006
 */


package org.posterita.formatter;

import java.util.Properties;

import org.compiere.model.MAttributeInstance;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MProduct;


public class PrintFormatter extends Formatter
{

    public Object format(Object target)
    {
        if(target==null)
            return null;
        
        MProduct product = (MProduct)target;
        int attributeSetInstanceId = product.getM_AttributeSetInstance_ID();
        Properties ctx = product.getCtx();
        
        String whereClause="AD_CLIENT_ID="+product.getAD_Client_ID()+" and M_ATTRIBUTESETINSTANCE_ID="+attributeSetInstanceId;
        int [] attributeValuesIds = MAttributeInstance.getAllIDs(MAttributeInstance.Table_Name,whereClause,null); 
        
        MAttributeValue attrValue=null;
        
        String productName="";       
        
        for(int i=0;i<attributeValuesIds.length;i++)
        {
            attrValue = new MAttributeValue(ctx,attributeValuesIds[i],null);
            
            if (attrValue.getDescription()==null)
                productName=productName+" "+attrValue.getName();
            else
                productName=productName+" "+attrValue.getDescription();
            
        }
        return productName.trim();
    }
    
    @Override
    public Object unformat(Object target) {
        
        return null;
    }
    
}
