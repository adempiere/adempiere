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

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;
import org.compiere.model.*;

/**
 *	Payroll for HRayroll Module
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRPayroll.java,v 1.0 2005/10/05 ogomezi
 */
public class MHRPayroll extends X_HR_Payroll
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Payroll_ID id
	 */
	public MHRPayroll (Properties ctx, int HR_Payroll_ID, String trxName)
	{
		super (ctx, HR_Payroll_ID, trxName);
		if (HR_Payroll_ID == 0)
		{
			setProcessing (false);	// N
		}		
	}	//	HRPayroll

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MHRPayroll (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 */
	public MHRPayroll (MCalendar calendar)
	{
		this (calendar.getCtx(), 0, calendar.get_TrxName());
		setClientOrg(calendar);
		//setC_Calendar_ID(calendar.getC_Calendar_ID());
	}	//	HRPayroll
	
}	//	MPayroll
