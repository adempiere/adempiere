/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/

package org.compiere.process;

import java.sql.Timestamp;


/** Generated Process for (Create Periods )
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class YearCreatePeriodsAbstract extends SvrProcess
{
	/** Process Name 	*/
	public static final String ProcessName = "Create Periods";
	/** Process Id 	*/
	public static final int ProcessId = 100;
 
	/**	Parameter Name for StartDate	*/
	public static final String StartDate = "StartDate";
	/**	Parameter Name for DateFormat	*/
	public static final String DateFormat = "DateFormat";

	/**	Parameter Value for StartDate	*/
	protected Timestamp startDate;
	/**	Parameter Value for DateFormat	*/
	protected String dateFormat;
 

	@Override
	protected void prepare()
	{
		startDate = getParameterAsTimestamp(StartDate);
		dateFormat = getParameterAsString(DateFormat);
	}

	@Override
	protected abstract String doIt() throws Exception;
}