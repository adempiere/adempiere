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
* Created on May 10, 2006 by ashley
* 
*/

package org.posterita.model;

import java.util.Properties;
import org.compiere.model.MAttachment;
import org.compiere.model.Query;
import org.posterita.exceptions.OperationException;

public class MMAttachment extends MAttachment
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MMAttachment(Properties ctx, int AD_Table_ID, int Record_ID, String trxName)
	{
		super(ctx, AD_Table_ID, Record_ID, trxName);
	}
	
	public static MAttachment get (Properties ctx, int AD_Table_ID, int Record_ID, String trxName) throws OperationException
	{
//red1 - using new Query model
		MAttachment retValue = new Query(ctx, Table_Name, "WHERE AD_Table_ID=? AND Record_ID=?", null)
		 					.setParameters(new Object[]{AD_Table_ID,Record_ID, Record_ID})
		 					.first();
		return retValue;
	}	//	get
}
