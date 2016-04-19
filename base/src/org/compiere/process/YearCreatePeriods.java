/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.sql.Timestamp;

import org.compiere.model.MYear;
import org.compiere.util.AdempiereUserError;

/**
 *	Create Periods of year
 *	
 *  @author Jorg Janke
 *  @version $Id: YearCreatePeriods.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 325 ] SvrProcess must handle mandatory error on Process Parameters
 *		@see https://github.com/adempiere/adempiere/issues/325
 */
public class YearCreatePeriods extends SvrProcess
{
	/**	Parameter Name StartDate	*/
	private final String	PARAMETERNAME_StartDate = "StartDate";
	/**	Parameter Name DateFormat	*/
	private final String	PARAMETERNAME_DateFormat = "DateFormat";
	/**	Internal Variables			*/
	private int	p_C_Year_ID = 0;
	private Timestamp p_StartDate;
	private String p_DateFormat;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		p_StartDate = getParameterAsTimestamp(PARAMETERNAME_StartDate);
		p_DateFormat = getParameterAsString(PARAMETERNAME_DateFormat);
		p_C_Year_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt ()
		throws Exception
	{
		MYear year = new MYear (getCtx(), p_C_Year_ID, get_TrxName());
		if (p_C_Year_ID == 0 || year.get_ID() != p_C_Year_ID)
			throw new AdempiereUserError ("@NotFound@: @C_Year_ID@ - " + p_C_Year_ID);
		log.info(year.toString());
		//
		if (year.createStdPeriods(null, p_StartDate, p_DateFormat))
			return "@OK@";
		return "@Error@";
	}	//	doIt
	
}	//	YearCreatePeriods
