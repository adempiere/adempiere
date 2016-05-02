/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
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
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.process;

import java.sql.Timestamp;


/** Generated Process for (Create Periods )
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class YearCreatePeriodsAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_Year Create Periods";
	/** Process Name 	*/
	private static final String NAME = "Create Periods";
	/** Process Id 	*/
	private static final int ID = 100;
 
	/**	Parameter Name for StartDate	*/
	public static final String StartDate = "StartDate";
	/**	Parameter Name for DateFormat	*/
	public static final String DateFormat = "DateFormat";

	/**	Parameter Value for startDate	*/
	private Timestamp startDate;
	/**	Parameter Value for dateFormat	*/
	private String dateFormat;
 

	@Override
	protected void prepare()
	{
		startDate = getParameterAsTimestamp(StartDate);
		dateFormat = getParameterAsString(DateFormat);
	}

	/**	 Getter Parameter Value for startDate	*/
	protected Timestamp getStartDate() {
		return startDate;
	}

	/**	 Getter Parameter Value for dateFormat	*/
	protected String getDateFormat() {
		return dateFormat;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}