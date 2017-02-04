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


/** Generated Process for (Apply migration)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class MigrationApplyAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "AD_Migration Apply";
	/** Process Name 	*/
	private static final String NAME = "Apply migration";
	/** Process Id 	*/
	private static final int ID = 53173;
 
	/**	Parameter Name for IsForce	*/
	public static final String IsForce = "IsForce";

	/**	Parameter Value for isForce	*/
	private boolean isForce;
 

	@Override
	protected void prepare()
	{
		isForce = getParameterAsBoolean(IsForce);
	}

	/**	 Getter Parameter Value for isForce	*/
	protected boolean isForce() {
		return isForce;
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