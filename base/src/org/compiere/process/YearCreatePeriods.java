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
 *	@author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 */
public class YearCreatePeriods extends YearCreatePeriodsAbstract
{
	/**	Internal Variables			*/
	private int yearId = 0;

	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		super.prepare();
		yearId = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt ()
		throws Exception
	{
		MYear year = new MYear (getCtx(), yearId, get_TrxName());
		if (yearId == 0 || year.get_ID() != yearId)
			throw new AdempiereUserError ("@NotFound@: @C_Year_ID@ - " + yearId);
		log.info(year.toString());
		//
		if (year.createStdPeriods(null, getStartDate(), getDateFormat()))
			return "@OK@";
		return "@Error@";
	}	//	doIt
	
}	//	YearCreatePeriods
