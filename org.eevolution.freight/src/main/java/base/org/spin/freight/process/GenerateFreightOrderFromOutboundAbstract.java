/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.spin.freight.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Freight Order from Outbound Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class GenerateFreightOrderFromOutboundAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "GenerateFreightOrderFromOutbound";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Freight Order from Outbound Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54161;
	/**	Parameter Name for In & Out Bound Order	*/
	public static final String WM_INOUTBOUND_ID = "WM_InOutBound_ID";
	/**	Parameter Name for Shipper	*/
	public static final String M_SHIPPER_ID = "M_Shipper_ID";
	/**	Parameter Name for Driver	*/
	public static final String DD_DRIVER_ID = "DD_Driver_ID";
	/**	Parameter Name for Vehicle	*/
	public static final String DD_VEHICLE_ID = "DD_Vehicle_ID";
	/**	Parameter Name for Freight	*/
	public static final String M_FREIGHT_ID = "M_Freight_ID";
	/**	Parameter Name for Freight Amount	*/
	public static final String FREIGHTAMT = "FreightAmt";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Date Ordered	*/
	public static final String DATEORDERED = "DateOrdered";
	/**	Parameter Value for In & Out Bound Order	*/
	private int inOutBoundId;
	/**	Parameter Value for Shipper	*/
	private int shipperId;
	/**	Parameter Value for Driver	*/
	private int driverId;
	/**	Parameter Value for Vehicle	*/
	private int vehicleId;
	/**	Parameter Value for Freight	*/
	private int freightId;
	/**	Parameter Value for Freight Amount	*/
	private BigDecimal freightAmt;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Date Ordered	*/
	private Timestamp dateOrdered;

	@Override
	protected void prepare() {
		inOutBoundId = getParameterAsInt(WM_INOUTBOUND_ID);
		shipperId = getParameterAsInt(M_SHIPPER_ID);
		driverId = getParameterAsInt(DD_DRIVER_ID);
		vehicleId = getParameterAsInt(DD_VEHICLE_ID);
		freightId = getParameterAsInt(M_FREIGHT_ID);
		freightAmt = getParameterAsBigDecimal(FREIGHTAMT);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		docAction = getParameterAsString(DOCACTION);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		dateOrdered = getParameterAsTimestamp(DATEORDERED);
	}

	/**	 Getter Parameter Value for In & Out Bound Order	*/
	protected int getInOutBoundId() {
		return inOutBoundId;
	}

	/**	 Setter Parameter Value for In & Out Bound Order	*/
	protected void setInOutBoundId(int inOutBoundId) {
		this.inOutBoundId = inOutBoundId;
	}

	/**	 Getter Parameter Value for Shipper	*/
	protected int getShipperId() {
		return shipperId;
	}

	/**	 Setter Parameter Value for Shipper	*/
	protected void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	/**	 Getter Parameter Value for Driver	*/
	protected int getDriverId() {
		return driverId;
	}

	/**	 Setter Parameter Value for Driver	*/
	protected void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	/**	 Getter Parameter Value for Vehicle	*/
	protected int getVehicleId() {
		return vehicleId;
	}

	/**	 Setter Parameter Value for Vehicle	*/
	protected void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**	 Getter Parameter Value for Freight	*/
	protected int getFreightId() {
		return freightId;
	}

	/**	 Setter Parameter Value for Freight	*/
	protected void setFreightId(int freightId) {
		this.freightId = freightId;
	}

	/**	 Getter Parameter Value for Freight Amount	*/
	protected BigDecimal getFreightAmt() {
		return freightAmt;
	}

	/**	 Setter Parameter Value for Freight Amount	*/
	protected void setFreightAmt(BigDecimal freightAmt) {
		this.freightAmt = freightAmt;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Document Date	*/
	protected Timestamp getDateDoc() {
		return dateDoc;
	}

	/**	 Setter Parameter Value for Document Date	*/
	protected void setDateDoc(Timestamp dateDoc) {
		this.dateDoc = dateDoc;
	}

	/**	 Getter Parameter Value for Date Ordered	*/
	protected Timestamp getDateOrdered() {
		return dateOrdered;
	}

	/**	 Setter Parameter Value for Date Ordered	*/
	protected void setDateOrdered(Timestamp dateOrdered) {
		this.dateOrdered = dateOrdered;
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