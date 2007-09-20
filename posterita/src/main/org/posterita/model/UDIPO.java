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
package org.posterita.model;

import java.util.Properties;

import org.compiere.model.PO;

import org.posterita.exceptions.OperationException;

public abstract class UDIPO
{
    private PO po;
    private Properties ctx;
    
    public Properties getCtx()
    {
        return ctx;
    }
 
    
    public UDIPO(PO po)
    {
        this.po = po;
        this.ctx = po.getCtx();
    }
    
    public int getID()
    {
    	return po.get_ID();
    }
    
    public int getAD_Org_ID()
    {
    	return po.getAD_Org_ID();
    }
   
    public void save() throws OperationException
    {
        boolean saved = po.save();
        
        if (!saved)
            throw new OperationException("Cannot save PO object, Class: " + po.getClass().getName());
    }
    
//    public void save(String trxname) throws OperationException
//    {
//        boolean saved = po.save(trxname);
//        
//        if (!saved)
//            throw new OperationException("connot save po object ");
//    }
   
    
    public PO getPO()
    {
        return po;
    }
    
    public void setPO(PO po)
    {
        this.po = po;
    }
    
   
}
