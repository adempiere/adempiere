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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/

package org.eevolution.process;

import org.compiere.model.MForecast;
import org.compiere.model.MForecastLine;
import org.compiere.process.SvrProcess;
import org.eevolution.model.MPPMRP;

/**
 * Forecast process generates demands for MRP, the demand can be deactivate.
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class ProcessingForecast extends SvrProcess
{
	private int	p_M_Forecast_ID = 0;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		p_M_Forecast_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt ()
	{
		MForecast forecast = new MForecast(getCtx(),p_M_Forecast_ID, get_TrxName());
		forecast.setProcessed(true);
		forecast.saveEx();
		
		for (MForecastLine fl:forecast.getLines(true))
		{
			MPPMRP.M_ForecastLine(fl);
		}
		
		return "@M_Forecast_ID@ : " + forecast.getName() + " @Processed@";
	}	//	doIt
	
}	//	Create Periods
