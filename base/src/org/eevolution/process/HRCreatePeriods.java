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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Oscar Gómez  www.e-evolution.com                           *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
//package org.eevolution.process;
package org.eevolution.process;

import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.SvrProcess;
import org.eevolution.model.*;

/**
 *	Create Periods of Payroll
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRCreatePeriods.java,v 1.0 2005/10/05 04:58:38 ogomezi Exp $
 */
public class HRCreatePeriods extends SvrProcess
{
	private int	p_HR_Payroll_ID = 0;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		int payroll_ID = 0;

	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt ()
		throws Exception
	{
		int year_ID = getRecord_ID();
		p_HR_Payroll_ID = DB.getSQLValue("HR_Year", "SELECT HR_Payroll_ID FROM HR_Year WHERE HR_Year_ID = "+year_ID);
		MHRYear year = new MHRYear (getCtx(), getRecord_ID(), get_TrxName());		
		if (year_ID == 0 || year.get_ID() != year_ID)
			return "Year not exist";
		else if(year.isProcessed())
			return "No Created, The Period's exist";
		log.info(year.toString());
		//
		if (year.createPeriods(p_HR_Payroll_ID,year_ID, year.getC_Year_ID())){
			year.setProcessed(true);
			year.save();
			return "@OK@ Create Periods";
		}
		return Msg.translate(getCtx(), "PeriodNotValid");
	}	//	doIt
}	//	YearCreatePeriods
