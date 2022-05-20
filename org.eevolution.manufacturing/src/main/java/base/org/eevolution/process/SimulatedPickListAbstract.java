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

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Simulated Pick List)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class SimulatedPickListAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "PP_Simulated Pick List";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Simulated Pick List";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53307;
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Transaction Date	*/
	public static final String DATETRX = "DateTrx";
	/**	Parameter Name for Qty Required	*/
	public static final String QTYREQUIRED = "QtyRequired";
	/**	Parameter Name for Backflush Group	*/
	public static final String BACKFLUSHGROUP = "BackflushGroup";
	/**	Parameter Name for Level no	*/
	public static final String SIMULATELEVELNO = "SimulateLevelNo";
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Transaction Date	*/
	private Timestamp dateTrx;
	/**	Parameter Value for Qty Required	*/
	private BigDecimal qtyRequired;
	/**	Parameter Value for Backflush Group	*/
	private String backflushGroup;
	/**	Parameter Value for Level no	*/
	private int simulateLevelNo;

	@Override
	protected void prepare() {
		productId = getParameterAsInt(M_PRODUCT_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		dateTrx = getParameterAsTimestamp(DATETRX);
		qtyRequired = getParameterAsBigDecimal(QTYREQUIRED);
		backflushGroup = getParameterAsString(BACKFLUSHGROUP);
		simulateLevelNo = getParameterAsInt(SIMULATELEVELNO);
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

	/**	 Getter Parameter Value for Transaction Date	*/
	protected Timestamp getDateTrx() {
		return dateTrx;
	}

	/**	 Setter Parameter Value for Transaction Date	*/
	protected void setDateTrx(Timestamp dateTrx) {
		this.dateTrx = dateTrx;
	}

	/**	 Getter Parameter Value for Qty Required	*/
	protected BigDecimal getQtyRequired() {
		return qtyRequired;
	}

	/**	 Setter Parameter Value for Qty Required	*/
	protected void setQtyRequired(BigDecimal qtyRequired) {
		this.qtyRequired = qtyRequired;
	}

	/**	 Getter Parameter Value for Backflush Group	*/
	protected String getBackflushGroup() {
		return backflushGroup;
	}

	/**	 Setter Parameter Value for Backflush Group	*/
	protected void setBackflushGroup(String backflushGroup) {
		this.backflushGroup = backflushGroup;
	}

	/**	 Getter Parameter Value for Level no	*/
	protected int getSimulateLevelNo() {
		return simulateLevelNo;
	}

	/**	 Setter Parameter Value for Level no	*/
	protected void setSimulateLevelNo(int simulateLevelNo) {
		this.simulateLevelNo = simulateLevelNo;
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