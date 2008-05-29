/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Forcast Line Model
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MForcastLine.java,v 1.11 2005/05/17 05:29:52 vpj-cd Exp $
 */
public class MForecastLine extends  X_M_ForecastLine
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_ForecastLine_ID id
	 */
	public MForecastLine (Properties ctx, int M_ForecastLine_ID, String trxName)
	{
		super (ctx, M_ForecastLine_ID, trxName);
		if (M_ForecastLine_ID == 0)
		{		
		}
		
	}	//	MForcastLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MForecastLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRequisitionLine

	
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save.
	 * 	Update Total on Header
	 *	@param newRecord if new record
	 *	@param success save was success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
                //begin e-evolution vpj-cd 10/30/2004
                MPPMRP.M_ForecastLine(this,false);
                //end e-evolution vpj-cd 10/30/2004

		return true;
	}	//	afterSave

	
	/**
	 * 	After Delete
	 *	@param success
	 *	@return true/false
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
        //begin e-evolution vpj-cd 10/30/2004
        MPPMRP.M_ForecastLine(this,true);
        //end e-evolution vpj-cd 10/30/2004
		return true;
	}	//	afterDelete	
	
}	//	MForcastLine
