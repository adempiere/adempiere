/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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

/**
	@author ashley
 */

package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MAttributeInstance;
import org.compiere.model.MAttributeValue;

import org.posterita.exceptions.OperationException;
import org.posterita.model.UDIMAttributeInstance;

public class AttributeManager
{
	public  static void createAttributeInstance(Properties ctx, int attributeSetInstanceId, int attributeId, MAttributeValue attributeValue, String trxName) throws OperationException
    {
        MAttributeInstance attInst = new MAttributeInstance(ctx, 0, trxName);
        attInst.setM_AttributeSetInstance_ID(attributeSetInstanceId);
        attInst.setM_Attribute_ID(attributeId);
        attInst.setM_AttributeValue_ID(attributeValue.get_ID());
        
        attInst.setValue(attributeValue.getName());
        
        UDIMAttributeInstance udiMattributeInstance = new UDIMAttributeInstance(attInst);
        udiMattributeInstance.save();
    }
}
