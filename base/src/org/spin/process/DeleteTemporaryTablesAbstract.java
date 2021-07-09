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

package org.spin.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Delete Temporary Tables)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class DeleteTemporaryTablesAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "DeleteTemporaryTables";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Delete Temporary Tables";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54407;
	/**	Parameter Name for Days to keep Log	*/
	public static final String KEEPLOGDAYS = "KeepLogDays";
	/**	Parameter Value for Days to keep Log	*/
	private int keepLogDays;

	@Override
	protected void prepare() {
		keepLogDays = getParameterAsInt(KEEPLOGDAYS);
	}

	/**	 Getter Parameter Value for Days to keep Log	*/
	protected int getKeepLogDays() {
		return keepLogDays;
	}

	/**	 Setter Parameter Value for Days to keep Log	*/
	protected void setKeepLogDays(int keepLogDays) {
		this.keepLogDays = keepLogDays;
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