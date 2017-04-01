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


/** Generated Process for (Synchronize Terminology)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class SynchronizeTerminologyAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "AD_Synchronize";
	/** Process Name 	*/
	private static final String NAME = "Synchronize Terminology";
	/** Process Id 	*/
	private static final int ID = 172;
 
	/**	Parameter Name for IsCreateElement	*/
	public static final String IsCreateElement = "IsCreateElement";
	/**	Parameter Name for IsDeletingUnusedElement	*/
	public static final String IsDeletingUnusedElement = "IsDeletingUnusedElement";

	/**	Parameter Value for isCreateElementsfromColumnorParameters	*/
	private boolean isCreateElementsfromColumnOrParameters;
	/**	Parameter Value for isDeletingunusedElements	*/
	private boolean isDeletingUnusedElements;
 

	@Override
	protected void prepare()
	{
		isCreateElementsfromColumnOrParameters = getParameterAsBoolean(IsCreateElement);
		isDeletingUnusedElements = getParameterAsBoolean(IsDeletingUnusedElement);
	}

	/**	 Getter Parameter Value for isCreateElementsfromColumnOrParameters	*/
	protected boolean isCreateElementsfromColumnOrParameters() {
		return isCreateElementsfromColumnOrParameters;
	}

	/**	 Getter Parameter Value for isDeletingUnusedElements	*/
	protected boolean isDeletingUnusedElements() {
		return isDeletingUnusedElements;
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