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

package org.eevolution.wms.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generare Outbound Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateInOutBoundAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "WM_InOutbound Generate";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generare Outbound Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53186;
 
	/**	Parameter Name for Order Reference	*/
	private static final String POREFERENCE = "POReference";
	/**	Parameter Name for Locator	*/
	private static final String M_LOCATOR_ID = "M_Locator_ID";
	/**	Parameter Name for Delivery Rule	*/
	private static final String DELIVERYRULE = "DeliveryRule";
	/**	Parameter Name for Document Type	*/
	private static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Document Action	*/
	private static final String DOCACTION = "DocAction";
	/**	Parameter Name for Ship Date	*/
	private static final String SHIPDATE = "ShipDate";
	/**	Parameter Name for Pick Date	*/
	private static final String PICKDATE = "PickDate";
	/**	Parameter Name for Priority	*/
	private static final String PRIORITYRULE = "PriorityRule";
	/**	Parameter Name for Delivery Via	*/
	private static final String DELIVERYVIARULE = "DeliveryViaRule";
	/**	Parameter Name for Shipper	*/
	private static final String M_SHIPPER_ID = "M_Shipper_ID";
	/**	Parameter Name for Freight Cost Rule	*/
	private static final String FREIGHTCOSTRULE = "FreightCostRule";
	/**	Parameter Name for Freight Category	*/
	private static final String M_FREIGHTCATEGORY_ID = "M_FreightCategory_ID";

	/**	Parameter Value for Order Reference	*/
	private String pOReference;
	/**	Parameter Value for Locator	*/
	private int locatorId;
	/**	Parameter Value for Delivery Rule	*/
	private String deliveryRule;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Ship Date	*/
	private Timestamp shipDate;
	/**	Parameter Value for Pick Date	*/
	private Timestamp pickDate;
	/**	Parameter Value for Priority	*/
	private String priorityRule;
	/**	Parameter Value for Delivery Via	*/
	private String deliveryViaRule;
	/**	Parameter Value for Shipper	*/
	private int shipperId;
	/**	Parameter Value for Freight Cost Rule	*/
	private String freightCostRule;
	/**	Parameter Value for Freight Category	*/
	private int freightCategoryId;
 

	@Override
	protected void prepare() {
		pOReference = getParameterAsString(POREFERENCE);
		locatorId = getParameterAsInt(M_LOCATOR_ID);
		deliveryRule = getParameterAsString(DELIVERYRULE);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		docAction = getParameterAsString(DOCACTION);
		shipDate = getParameterAsTimestamp(SHIPDATE);
		pickDate = getParameterAsTimestamp(PICKDATE);
		priorityRule = getParameterAsString(PRIORITYRULE);
		deliveryViaRule = getParameterAsString(DELIVERYVIARULE);
		shipperId = getParameterAsInt(M_SHIPPER_ID);
		freightCostRule = getParameterAsString(FREIGHTCOSTRULE);
		freightCategoryId = getParameterAsInt(M_FREIGHTCATEGORY_ID);
	}

	/**	 Getter Parameter Value for Order Reference	*/
	protected String getPOReference() {
		return pOReference;
	}

	/**	 Setter Parameter Value for Order Reference	*/
	protected void setPOReference(String pOReference) {
		this.pOReference = pOReference;
	}

	/**	 Getter Parameter Value for Locator	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Setter Parameter Value for Locator	*/
	protected void setLocatorId(int locatorId) {
		this.locatorId = locatorId;
	}

	/**	 Getter Parameter Value for Delivery Rule	*/
	protected String getDeliveryRule() {
		return deliveryRule;
	}

	/**	 Setter Parameter Value for Delivery Rule	*/
	protected void setDeliveryRule(String deliveryRule) {
		this.deliveryRule = deliveryRule;
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

	/**	 Getter Parameter Value for Ship Date	*/
	protected Timestamp getShipDate() {
		return shipDate;
	}

	/**	 Setter Parameter Value for Ship Date	*/
	protected void setShipDate(Timestamp shipDate) {
		this.shipDate = shipDate;
	}

	/**	 Getter Parameter Value for Pick Date	*/
	protected Timestamp getPickDate() {
		return pickDate;
	}

	/**	 Setter Parameter Value for Pick Date	*/
	protected void setPickDate(Timestamp pickDate) {
		this.pickDate = pickDate;
	}

	/**	 Getter Parameter Value for Priority	*/
	protected String getPriorityRule() {
		return priorityRule;
	}

	/**	 Setter Parameter Value for Priority	*/
	protected void setPriorityRule(String priorityRule) {
		this.priorityRule = priorityRule;
	}

	/**	 Getter Parameter Value for Delivery Via	*/
	protected String getDeliveryViaRule() {
		return deliveryViaRule;
	}

	/**	 Setter Parameter Value for Delivery Via	*/
	protected void setDeliveryViaRule(String deliveryViaRule) {
		this.deliveryViaRule = deliveryViaRule;
	}

	/**	 Getter Parameter Value for Shipper	*/
	protected int getShipperId() {
		return shipperId;
	}

	/**	 Setter Parameter Value for Shipper	*/
	protected void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	/**	 Getter Parameter Value for Freight Cost Rule	*/
	protected String getFreightCostRule() {
		return freightCostRule;
	}

	/**	 Setter Parameter Value for Freight Cost Rule	*/
	protected void setFreightCostRule(String freightCostRule) {
		this.freightCostRule = freightCostRule;
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