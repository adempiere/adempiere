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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;


/**
 * Forecast Model
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com     
 */
public class MForecast extends  X_M_Forecast
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 66771328316032322L;

	/**
	 * Standard Constructor
	 * @param ctx context
	 * @param M_ForecastLine_ID id
	 */
	public MForecast (Properties ctx, int M_Forecast_ID, String trxName)
	{
		super (ctx, M_Forecast_ID, trxName);
	}	//	MForecastLine

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set
	 */
	public MForecast (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MForecast
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		Boolean isActiveOld = (Boolean) get_ValueOld(COLUMNNAME_IsActive);			
		
		if(isActiveOld != null && is_ValueChanged(MForecast.COLUMNNAME_IsActive))
		{
			for (MForecastLine line : getLines(isActiveOld))
			{
				line.setIsActive(isActive());
				line.saveEx();
			}
		}
		return true;
	}
	

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return true if can be saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success || newRecord)
			return success;
		
		return true;
	}
	
	public List<MForecastLine> getLines(boolean isActive)
	{
		final StringBuilder whereClause = new StringBuilder();
		whereClause.append(MForecast.COLUMNNAME_M_Forecast_ID).append("=? AND ");
		whereClause.append(MForecast.COLUMNNAME_IsActive).append("=?");
		
		return new Query(getCtx(),MForecastLine.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setParameters(get_ID(), isActive)
		.list();
	}
}	//	MForecast
