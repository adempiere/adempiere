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
 * Created on 08-Jul-2005 by alok
 *
 */
package org.posterita.model;

import org.compiere.model.MInOut;

import org.posterita.exceptions.OperationException;


public class UDIMInOut extends UDIPO
{
	public UDIMInOut(MInOut inOut)
	{
		super(inOut);
	}
	
	public MInOut getMInOut()
	{
		return (MInOut) getPO();
	}
    
    public void processIt(String docAction) throws OperationException
    {
        MInOut inOut = getMInOut();
    	
        boolean processed =  inOut.processIt(docAction);
        
        inOut.setProcessed(processed);
        
        inOut.save();
        
        if (!processed)
            throw new OperationException("Cannot process inOut to: " + docAction + " "+inOut.getProcessMsg());
    }
	
}
