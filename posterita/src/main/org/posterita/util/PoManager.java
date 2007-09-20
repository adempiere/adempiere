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
import org.compiere.util.CLogger;
import org.compiere.util.ValueNamePair;
import org.posterita.exceptions.OperationException;

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
	
	public static void save(PO po, String trxName) throws OperationException
	{
		boolean saved = po.save(trxName);
		if(! saved )
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
}
