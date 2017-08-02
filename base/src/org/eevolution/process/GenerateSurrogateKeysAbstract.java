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
/** Generated Process for (Generate Surrogate Keys)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateSurrogateKeysAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "AD_Table Generate surrogate keys";
	/** Process Name 	*/
	private static final String NAME = "Generate Surrogate Keys";
	/** Process Id 	*/
	private static final int ID = 53892;
 
	/**	Parameter Name for AD_Table_ID	*/
	public static final String AD_Table_ID = "AD_Table_ID";
	/**	Parameter Name for IsGenerateUUID	*/
	public static final String IsGenerateUUID = "IsGenerateUUID";

	/**	Parameter Value for tableId	*/
	private int tableId;
	/**	Parameter Value for isGenerateUUIDforallrecords	*/
	private boolean isGenerateUUIDforallrecords;
 

	@Override
	protected void prepare()
	{
		tableId = getParameterAsInt(AD_Table_ID);
		isGenerateUUIDforallrecords = getParameterAsBoolean(IsGenerateUUID);
	}

	/**	 Getter Parameter Value for tableId	*/
	protected int getTableId() {
		return tableId;
	}

	/**	 Getter Parameter Value for isGenerateUUIDforallrecords	*/
	protected boolean isGenerateUUIDforallrecords() {
		return isGenerateUUIDforallrecords;
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