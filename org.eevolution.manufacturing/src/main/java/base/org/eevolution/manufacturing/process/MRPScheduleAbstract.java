/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

package org.eevolution.manufacturing.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Demands and Supplies to schedule)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class MRPScheduleAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "MRP_Schedule";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Demands and Supplies to schedule";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53369;
 
	/**	Parameter Name for Planner	*/
	private static final String PLANNER_ID = "Planner_ID";
	/**	Parameter Name for Priority	*/
	private static final String PRIORITY = "Priority";
	/**	Parameter Name for Line No	*/
	private static final String LINE = "Line";

	/**	Parameter Value for Planner	*/
	private int plannerId;
	/**	Parameter Value for Priority	*/
	private String priority;
	/**	Parameter Value for Line No	*/
	private int line;
 

	@Override
	protected void prepare() {
		plannerId = getParameterAsInt(PLANNER_ID);
		priority = getParameterAsString(PRIORITY);
		line = getParameterAsInt(LINE);
	}

	/**	 Getter Parameter Value for Planner	*/
	protected int getPlannerId() {
		return plannerId;
	}

	/**	 Setter Parameter Value for Planner	*/
	protected void setPlannerId(int plannerId) {
		this.plannerId = plannerId;
	}

	/**	 Getter Parameter Value for Priority	*/
	protected String getPriority() {
		return priority;
	}

	/**	 Setter Parameter Value for Priority	*/
	protected void setPriority(String priority) {
		this.priority = priority;
	}

	/**	 Getter Parameter Value for Line No	*/
	protected int getLine() {
		return line;
	}

	/**	 Setter Parameter Value for Line No	*/
	protected void setLine(int line) {
		this.line = line;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}