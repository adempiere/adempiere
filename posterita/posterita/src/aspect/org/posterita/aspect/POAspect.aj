/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/

package org.posterita.aspect;

import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.exceptions.POException;
import org.posterita.exceptions.TerminalLockedException;
import org.compiere.model.PO;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.ValueNamePair;

/**
 * @author ashley
 */
public aspect POAspect 
{
    pointcut savePO():
        call(public boolean PO.save());
    
    boolean around() : savePO() 
    {
        if (thisJoinPoint.getTarget() instanceof X_C_Order)
        {
            X_C_Order order = (X_C_Order)thisJointPoint.getTarget();
            int terminalId = order.getC_POS_ID();
            
            if (terminalId > 0 && POSTerminalManager.isTerminalLocked(po.getCtx(), terminalId))
            {
                throw new POException("Terminal is locked");
            }
        }
        
        boolean val = proceed();
        
        if (!val)
        {
            Exception ex = CLogger.retrieveException();
            ValueNamePair err = CLogger.retrieveError();
            String msg;
            
            if (ex != null)
            {
                msg = ex.getMessage();
            }
            else if (err != null)
            {
                msg = err.getValue() + " " + err.getName();
            }
            else if (thisJoinPoint.getTarget() instanceof DocAction)                 
            {
                msg = ((DocAction)thisJoinPoint.getTarget()).getProcessMsg();
            }
            else
            {
                msg = "Unknown";
            }
            
            throw new POException("Could not save PO - Class: " + thisJoinPoint.getTarget().getClass().getName() + " Cause: " + msg);
        }
        
        return val;
    }
}