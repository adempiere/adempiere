/**
 * 
 * Copyright (c) 2007 Posterita. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Posterita. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Posterita.
 *
 * POSTERITA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. Posterita SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * May 22, 2007 11:46:27 AM by praveen
 *
 */

package org.posterita.util;

import org.compiere.model.PO;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.ValueNamePair;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TerminalLockedException;

public class PoManager 
{
	public static void save(PO po) throws OperationException
	{
		boolean saved = po.save();
		if(! saved )
		{			
			handleError(po);
		}
	}
	
	private static void validatePO(PO po) throws OperationException
	{
	    if (po instanceof X_C_Order)
	    {
	        X_C_Order order = (X_C_Order)po;
	        int terminalId = order.getC_POS_ID();
	        if (terminalId > 0 && POSTerminalManager.isTerminalLocked(po.getCtx(), terminalId))
	        {
	            throw new TerminalLockedException("Terminal is locked");
	        }
	    }
	}
	
	public static void save(PO po, String trxName) throws OperationException
	{
	    validatePO(po);
		boolean saved = po.save(trxName);
		if(!saved)
		{
			handleError(po);
		}
	}
	
	private static void handleError(PO po) throws OperationException
	{
		Exception ex = CLogger.retrieveException();
		ValueNamePair error = CLogger.retrieveError();
		String msg = null;
		if(error != null)
		{
			msg = error.getName();
		}
		
		if(ex != null)
		{
			throw new OperationException("Cannot save PO object. " + po.getClass().getName() + " Cause: " + ex.getMessage());
		}
		
		if(msg != null)
		{
			throw new OperationException("Cannot save PO object. " + po.getClass().getName() + " Cause: " + msg);
		}
		
		throw new OperationException("Cannot save PO object. " + po.getClass().getName());			
	}
	
	public static void processIt(PO po, String processAction) throws OperationException
	{
	    validatePO(po);
		boolean processed = false;
		try
		{
			if(po instanceof DocAction)
			{
				DocAction docPo = (DocAction)po;
				processed = docPo.processIt(processAction);
				save(po);
				
				if (!processed)
				{
					throw new OperationException("Cannot process " + po.getClass().getName() + " to: " + processAction + " "+ docPo.getProcessMsg());
				}
			}
		}
		catch (OperationException ex) // Should not wrap Operation exception instance
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new OperationException(ex.getMessage(), ex);
		}
	}
	
}
