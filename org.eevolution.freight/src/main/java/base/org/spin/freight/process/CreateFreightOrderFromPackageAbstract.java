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

/** Generated Process for (Create Freight Order From Package)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class CreateFreightOrderFromPackageAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBP_CreateFreightOrderFromPackage";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Freight Order From Package";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54430;
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Date Ordered	*/
	public static final String DATEORDERED = "DateOrdered";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Shipper	*/
	public static final String M_SHIPPER_ID = "M_Shipper_ID";
	/**	Parameter Name for Driver	*/
	public static final String DD_DRIVER_ID = "DD_Driver_ID";
	/**	Parameter Name for Vehicle	*/
	public static final String DD_VEHICLE_ID = "DD_Vehicle_ID";
	/**	Parameter Name for Overwrite Freight Cost Rule	*/
	public static final String ISOVERWRITEFREIGHTCOSTRULE = "IsOverwriteFreightCostRule";
	/**	Parameter Name for Freight Cost Rule	*/
	public static final String FREIGHTCOSTRULE = "FreightCostRule";
	/**	Parameter Name for Freight Amount	*/
	public static final String FREIGHTAMT = "FreightAmt";
	/**	Parameter Name for Freight Category	*/
	public static final String M_FREIGHTCATEGORY_ID = "M_FreightCategory_ID";
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Date Ordered	*/
	private Timestamp dateOrdered;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Shipper	*/
	private int shipperId;
	/**	Parameter Value for Driver	*/
	private int driverId;
	/**	Parameter Value for Vehicle	*/
	private int vehicleId;
	/**	Parameter Value for Overwrite Freight Cost Rule	*/
	private boolean isOverwriteFreightCostRule;
	/**	Parameter Value for Freight Cost Rule	*/
	private String freightCostRule;
	/**	Parameter Value for Freight Amount	*/
	private BigDecimal freightAmt;
	/**	Parameter Value for Freight Category	*/
	private int freightCategoryId;

	@Override
	protected void prepare() {
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		dateOrdered = getParameterAsTimestamp(DATEORDERED);
		docAction = getParameterAsString(DOCACTION);
		shipperId = getParameterAsInt(M_SHIPPER_ID);
		driverId = getParameterAsInt(DD_DRIVER_ID);
		vehicleId = getParameterAsInt(DD_VEHICLE_ID);
		isOverwriteFreightCostRule = getParameterAsBoolean(ISOVERWRITEFREIGHTCOSTRULE);
		freightCostRule = getParameterAsString(FREIGHTCOSTRULE);
		freightAmt = getParameterAsBigDecimal(FREIGHTAMT);
		freightCategoryId = getParameterAsInt(M_FREIGHTCATEGORY_ID);
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
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

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
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

	/**	 Getter Parameter Value for Overwrite Freight Cost Rule	*/
	protected boolean isOverwriteFreightCostRule() {
		return isOverwriteFreightCostRule;
	}

	/**	 Setter Parameter Value for Overwrite Freight Cost Rule	*/
	protected void setIsOverwriteFreightCostRule(boolean isOverwriteFreightCostRule) {
		this.isOverwriteFreightCostRule = isOverwriteFreightCostRule;
	}

	/**	 Getter Parameter Value for Freight Cost Rule	*/
	protected String getFreightCostRule() {
		return freightCostRule;
	}

	/**	 Setter Parameter Value for Freight Cost Rule	*/
	protected void setFreightCostRule(String freightCostRule) {
		this.freightCostRule = freightCostRule;
	}

	/**	 Getter Parameter Value for Freight Amount	*/
	protected BigDecimal getFreightAmt() {
		return freightAmt;
	}

	/**	 Setter Parameter Value for Freight Amount	*/
	protected void setFreightAmt(BigDecimal freightAmt) {
		this.freightAmt = freightAmt;
	}

	/**	 Getter Parameter Value for Freight Category	*/
	protected int getFreightCategoryId() {
		return freightCategoryId;
	}

	/**	 Setter Parameter Value for Freight Category	*/
	protected void setFreightCategoryId(int freightCategoryId) {
		this.freightCategoryId = freightCategoryId;
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