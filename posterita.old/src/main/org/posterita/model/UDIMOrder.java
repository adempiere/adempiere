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
 * Created on 28-Jun-2005 by alok
 *
 */
package org.posterita.model;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.DocumentEngine;

import org.posterita.exceptions.OperationException;


public class UDIMOrder extends UDIPO
{
	
	public UDIMOrder(MOrder order)
	{
		super(order);
	}
	
	
	public void prepareIt() throws OperationException
	{
	    String status = ((MOrder)getPO()).prepareIt();
	    if(status.equals(DocumentEngine.STATUS_Invalid))
	    {
	        throw new OperationException("Could not Prepare the order: " + ((MOrder)getPO()).getProcessMsg());
	    }
	}
	
	public void processIt(String docAction) throws OperationException
	{
	    
		boolean processed =  ((MOrder)getPO()).processIt(docAction);
        
		((MOrder)getPO()).setProcessed(processed);
		
		((MOrder)getPO()).save();
        
        if (!processed)
            throw new OperationException("Cannot process Order to: " + docAction + " "+((MOrder)getPO()).getProcessMsg());
	}
	
	
	public MOrder getOrder()
	{
	    return ((MOrder)getPO());
	}
	
	public int getC_DocTypeTarget_ID()
	{
	    return ((MOrder)getPO()).getC_DocTypeTarget_ID();
	}
	
	public MOrderLine[] getLines()
	{
	    return ((MOrder)getPO()).getLines();
	}

}
