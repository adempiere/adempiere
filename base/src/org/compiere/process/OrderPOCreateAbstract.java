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

import java.sql.Timestamp;

/** Generated Process for (Generate PO from Sales Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class OrderPOCreateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Order PO_Create";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate PO from Sales Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 193;
	/**	Parameter Name for Date Ordered	*/
	public static final String DATEORDERED = "DateOrdered";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Vendor	*/
	public static final String VENDOR_ID = "Vendor_ID";
	/**	Parameter Name for Order	*/
	public static final String C_ORDER_ID = "C_Order_ID";
	/**	Parameter Name for Drop Shipment	*/
	public static final String ISDROPSHIP = "IsDropShip";
	/**	Parameter Value for Date Ordered	*/
	private Timestamp dateOrdered;
	/**	Parameter Value for Date Ordered(To)	*/
	private Timestamp dateOrderedTo;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Vendor	*/
	private int vendorId;
	/**	Parameter Value for Order	*/
	private int orderId;
	/**	Parameter Value for Drop Shipment	*/
	private String isDropShip;

	@Override
	protected void prepare() {
		dateOrdered = getParameterAsTimestamp(DATEORDERED);
		dateOrderedTo = getParameterToAsTimestamp(DATEORDERED);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		vendorId = getParameterAsInt(VENDOR_ID);
		orderId = getParameterAsInt(C_ORDER_ID);
		isDropShip = getParameterAsString(ISDROPSHIP);
	}

	/**	 Getter Parameter Value for Date Ordered	*/
	protected Timestamp getDateOrdered() {
		return dateOrdered;
	}

	/**	 Setter Parameter Value for Date Ordered	*/
	protected void setDateOrdered(Timestamp dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	/**	 Getter Parameter Value for Date Ordered(To)	*/
	protected Timestamp getDateOrderedTo() {
		return dateOrderedTo;
	}

	/**	 Setter Parameter Value for Date Ordered(To)	*/
	protected void setDateOrderedTo(Timestamp dateOrderedTo) {
		this.dateOrderedTo = dateOrderedTo;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Vendor	*/
	protected int getVendorId() {
		return vendorId;
	}

	/**	 Setter Parameter Value for Vendor	*/
	protected void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	/**	 Getter Parameter Value for Order	*/
	protected int getOrderId() {
		return orderId;
	}

	/**	 Setter Parameter Value for Order	*/
	protected void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**	 Getter Parameter Value for Drop Shipment	*/
	protected String getIsDropShip() {
		return isDropShip;
	}

	/**	 Setter Parameter Value for Drop Shipment	*/
	protected void setIsDropShip(String isDropShip) {
		this.isDropShip = isDropShip;
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