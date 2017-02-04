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
/** Generated Process for (Export Format Generator)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class ExportFormatGeneratorAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "EXP_Format Generator";
	/** Process Name 	*/
	private static final String NAME = "Export Format Generator";
	/** Process Id 	*/
	private static final int ID = 53085;
 
	/**	Parameter Name for AD_Window_ID	*/
	public static final String AD_Window_ID = "AD_Window_ID";
	/**	Parameter Name for IsInsertRecord	*/
	public static final String IsInsertRecord = "IsInsertRecord";
	/**	Parameter Name for IsMandatory	*/
	public static final String IsMandatory = "IsMandatory";

	/**	Parameter Value for windowId	*/
	private int windowId;
	/**	Parameter Value for iscludesonlytheTabsthatInsertrecords	*/
	private boolean iscludesonlytheTabsthatInsertrecords;
	/**	Parameter Value for iscludesonlythemandatorycolumns	*/
	private boolean iscludesonlythemandatorycolumns;
 

	@Override
	protected void prepare()
	{
		windowId = getParameterAsInt(AD_Window_ID);
		iscludesonlytheTabsthatInsertrecords = getParameterAsBoolean(IsInsertRecord);
		iscludesonlythemandatorycolumns = getParameterAsBoolean(IsMandatory);
	}

	/**	 Getter Parameter Value for windowId	*/
	protected int getWindowId() {
		return windowId;
	}

	/**	 Getter Parameter Value for iscludesonlytheTabsthatInsertrecords	*/
	protected boolean iscludesonlytheTabsthatInsertrecords() {
		return iscludesonlytheTabsthatInsertrecords;
	}

	/**	 Getter Parameter Value for iscludesonlythemandatorycolumns	*/
	protected boolean iscludesonlythemandatorycolumns() {
		return iscludesonlythemandatorycolumns;
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