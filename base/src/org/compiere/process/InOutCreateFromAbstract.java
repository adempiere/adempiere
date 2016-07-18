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

/** Generated Process for (In Out Create From)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class InOutCreateFromAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "SBP_InOutCreateFrom";
	/** Process Name 	*/
	private static final String NAME = "In Out Create From";
	/** Process Id 	*/
	private static final int ID = 53851;
 
	/**	Parameter Name for M_Locator_ID	*/
	public static final String M_Locator_ID = "M_Locator_ID";

	/**	Parameter Value for locator	*/
	private int locator;
 

	@Override
	protected void prepare()
	{
		locator = getParameterAsInt(M_Locator_ID);
	}

	/**	 Getter Parameter Value for locator	*/
	protected int getLocator() {
		return locator;
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