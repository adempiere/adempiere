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



/** Generated Process for (Create Package)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class PackageCreateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Package_Create";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Package";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 282;
	/**	Parameter Name for Shipment/Receipt	*/
	public static final String M_INOUT_ID = "M_InOut_ID";
	/**	Parameter Name for Shipper	*/
	public static final String M_SHIPPER_ID = "M_Shipper_ID";
	/**	Parameter Value for Shipment/Receipt	*/
	private int inOutId;
	/**	Parameter Value for Shipper	*/
	private int shipperId;

	@Override
	protected void prepare() {
		inOutId = getParameterAsInt(M_INOUT_ID);
		shipperId = getParameterAsInt(M_SHIPPER_ID);
	}

	/**	 Getter Parameter Value for Shipment/Receipt	*/
	protected int getInOutId() {
		return inOutId;
	}

	/**	 Setter Parameter Value for Shipment/Receipt	*/
	protected void setInOutId(int inOutId) {
		this.inOutId = inOutId;
	}

	/**	 Getter Parameter Value for Shipper	*/
	protected int getShipperId() {
		return shipperId;
	}

	/**	 Setter Parameter Value for Shipper	*/
	protected void setShipperId(int shipperId) {
		this.shipperId = shipperId;
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