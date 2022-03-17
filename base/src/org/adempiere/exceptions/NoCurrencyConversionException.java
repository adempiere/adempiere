/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.exceptions;

import java.sql.Timestamp;
import java.text.DateFormat;

import org.compiere.model.MConversionType;
import org.compiere.model.MCurrency;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 * Any exception that occurs when no currency conversion rate was found
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class NoCurrencyConversionException extends AdempiereException
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param C_Currency_ID
	 * @param C_Currency_ID_To
	 * @param ConvDate
	 * @param C_ConversionType_ID
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 */
	public NoCurrencyConversionException (int C_Currency_ID, int C_Currency_ID_To,
			Timestamp ConvDate,
			int C_ConversionType_ID,
			int AD_Client_ID, int AD_Org_ID)
	{
		super(buildMessage(C_Currency_ID, C_Currency_ID_To,
				ConvDate,
				C_ConversionType_ID,
				AD_Client_ID, AD_Org_ID));
	}

	private static final String buildMessage(int C_Currency_ID, int C_Currency_ID_To,
			Timestamp ConvDate,
			int C_ConversionType_ID,
			int AD_Client_ID, int AD_Org_ID)
	{
		DateFormat df = DisplayType.getDateFormat(DisplayType.Date);
		
		StringBuffer sb = new StringBuffer("@NoCurrencyConversion@ ")
			.append(MCurrency.getISO_Code(Env.getCtx(), C_Currency_ID))
			.append("->")
			.append(MCurrency.getISO_Code(Env.getCtx(), C_Currency_ID_To));
		//
		sb.append(", @Date@: ");
		if (ConvDate != null)
			sb.append(df.format(ConvDate));
		else
			sb.append("*");
		//
		sb.append(", @C_ConversionType_ID@: ");
		if (C_ConversionType_ID > 0)
		{
			final String sql = "SELECT "+MConversionType.COLUMNNAME_Name+" FROM "+MConversionType.Table_Name
								+" WHERE "+MConversionType.COLUMNNAME_C_ConversionType_ID+"=?";
			String name = DB.getSQLValueString(null, sql, C_ConversionType_ID);
			sb.append(name);
		}
		else
		{
			sb.append("*");
		}
		return sb.toString();
	}
}
