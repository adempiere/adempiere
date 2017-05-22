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

package org.eevolution.process;

import org.compiere.process.SvrProcess;
/** Generated Process for (Demands and Supplies to schedule)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class MRPScheduleAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "MRP_Schedule";
	/** Process Name 	*/
	private static final String NAME = "Demands and Supplies to schedule";
	/** Process Id 	*/
	private static final int ID = 53369;
 
	/**	Parameter Name for Planner_ID	*/
	public static final String Planner_ID = "Planner_ID";
	/**	Parameter Name for Priority	*/
	public static final String Priority = "Priority";
	/**	Parameter Name for Line	*/
	public static final String Line = "Line";

	/**	Parameter Value for plannerId	*/
	private int plannerId;
	/**	Parameter Value for priority	*/
	private String priority;
	/**	Parameter Value for lineNo	*/
	private int lineNo;
 

	@Override
	protected void prepare()
	{
		plannerId = getParameterAsInt(Planner_ID);
		priority = getParameterAsString(Priority);
		lineNo = getParameterAsInt(Line);
	}

	/**	 Getter Parameter Value for plannerId	*/
	protected int getPlannerId() {
		return plannerId;
	}

	/**	 Getter Parameter Value for priority	*/
	protected String getPriority() {
		return priority;
	}

	/**	 Getter Parameter Value for lineNo	*/
	protected int getLineNo() {
		return lineNo;
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