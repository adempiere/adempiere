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

package org.adempiere.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Replenish Plan Report)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CalculateReplenishPlanAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "ReplenishPlan";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Replenish Plan Report";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53685;
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Locator	*/
	public static final String M_LOCATOR_ID = "M_Locator_ID";
	/**	Parameter Name for Date Start	*/
	public static final String DATESTART = "DateStart";
	/**	Parameter Name for Finish Date	*/
	public static final String DATEFINISH = "DateFinish";
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Locator	*/
	private int locatorId;
	/**	Parameter Value for Date Start	*/
	private Timestamp dateStart;
	/**	Parameter Value for Finish Date	*/
	private Timestamp dateFinish;

	@Override
	protected void prepare() {
		productId = getParameterAsInt(M_PRODUCT_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		locatorId = getParameterAsInt(M_LOCATOR_ID);
		dateStart = getParameterAsTimestamp(DATESTART);
		dateFinish = getParameterAsTimestamp(DATEFINISH);
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Warehouse	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Locator	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Setter Parameter Value for Locator	*/
	protected void setLocatorId(int locatorId) {
		this.locatorId = locatorId;
	}

	/**	 Getter Parameter Value for Date Start	*/
	protected Timestamp getDateStart() {
		return dateStart;
	}

	/**	 Setter Parameter Value for Date Start	*/
	protected void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	/**	 Getter Parameter Value for Finish Date	*/
	protected Timestamp getDateFinish() {
		return dateFinish;
	}

	/**	 Setter Parameter Value for Finish Date	*/
	protected void setDateFinish(Timestamp dateFinish) {
		this.dateFinish = dateFinish;
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