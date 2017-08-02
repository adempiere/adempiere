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

package org.compiere.process;



/** Generated Process for (Update Quantity)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InventoryCountUpdateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Inventory Update";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Update Quantity";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 106;
	/**	Parameter Name for Set Inventory Count to	*/
	private static final String INVENTORYCOUNTSET = "InventoryCountSet";
	/**	Parameter Name for Is Update Count Qty	*/
	private static final String ISUPDATECOUNTQTY = "IsUpdateCountQty";
	/**	Parameter Value for Set Inventory Count to	*/
	private String inventoryCountSet;
	/**	Parameter Value for Is Update Count Qty	*/
	private boolean isUpdateCountQty;

	@Override
	protected void prepare() {
		inventoryCountSet = getParameterAsString(INVENTORYCOUNTSET);
		isUpdateCountQty = getParameterAsBoolean(ISUPDATECOUNTQTY);
	}

	/**	 Getter Parameter Value for Set Inventory Count to	*/
	protected String getInventoryCountSet() {
		return inventoryCountSet;
	}

	/**	 Setter Parameter Value for Set Inventory Count to	*/
	protected void setInventoryCountSet(String inventoryCountSet) {
		this.inventoryCountSet = inventoryCountSet;
	}

	/**	 Getter Parameter Value for Is Update Count Qty	*/
	protected boolean isUpdateCountQty() {
		return isUpdateCountQty;
	}

	/**	 Setter Parameter Value for Is Update Count Qty	*/
	protected void setIsUpdateCountQty(boolean isUpdateCountQty) {
		this.isUpdateCountQty = isUpdateCountQty;
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