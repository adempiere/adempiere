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

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Generare Outbound Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateInOutBoundAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "WM_InOutbound Generate";
	/** Process Name 	*/
	private static final String NAME = "Generare Outbound Order";
	/** Process Id 	*/
	private static final int ID = 53186;
 
	/**	Parameter Name for POReference	*/
	public static final String POReference = "POReference";
	/**	Parameter Name for M_Locator_ID	*/
	public static final String M_Locator_ID = "M_Locator_ID";
	/**	Parameter Name for DeliveryRule	*/
	public static final String DeliveryRule = "DeliveryRule";
	/**	Parameter Name for C_DocType_ID	*/
	public static final String C_DocType_ID = "C_DocType_ID";
	/**	Parameter Name for DocAction	*/
	public static final String DocAction = "DocAction";
	/**	Parameter Name for ShipDate	*/
	public static final String ShipDate = "ShipDate";
	/**	Parameter Name for PickDate	*/
	public static final String PickDate = "PickDate";
	/**	Parameter Name for PriorityRule	*/
	public static final String PriorityRule = "PriorityRule";
	/**	Parameter Name for DeliveryViaRule	*/
	public static final String DeliveryViaRule = "DeliveryViaRule";
	/**	Parameter Name for M_Shipper_ID	*/
	public static final String M_Shipper_ID = "M_Shipper_ID";
	/**	Parameter Name for FreightCostRule	*/
	public static final String FreightCostRule = "FreightCostRule";
	/**	Parameter Name for M_FreightCategory_ID	*/
	public static final String M_FreightCategory_ID = "M_FreightCategory_ID";

	/**	Parameter Value for orderReference	*/
	private String orderReference;
	/**	Parameter Value for locatorId	*/
	private int locatorId;
	/**	Parameter Value for deliveryRule	*/
	private String deliveryRule;
	/**	Parameter Value for documentTypeId	*/
	private int documentTypeId;
	/**	Parameter Value for documentAction	*/
	private String documentAction;
	/**	Parameter Value for shipDate	*/
	private Timestamp shipDate;
	/**	Parameter Value for pickDate	*/
	private Timestamp pickDate;
	/**	Parameter Value for priority	*/
	private String priority;
	/**	Parameter Value for deliveryVia	*/
	private String deliveryVia;
	/**	Parameter Value for shipperId	*/
	private int shipperId;
	/**	Parameter Value for freightCostRule	*/
	private String freightCostRule;
	/**	Parameter Value for freightCategoryId	*/
	private int freightCategoryId;
 

	@Override
	protected void prepare()
	{
		orderReference = getParameterAsString(POReference);
		locatorId = getParameterAsInt(M_Locator_ID);
		deliveryRule = getParameterAsString(DeliveryRule);
		documentTypeId = getParameterAsInt(C_DocType_ID);
		documentAction = getParameterAsString(DocAction);
		shipDate = getParameterAsTimestamp(ShipDate);
		pickDate = getParameterAsTimestamp(PickDate);
		priority = getParameterAsString(PriorityRule);
		deliveryVia = getParameterAsString(DeliveryViaRule);
		shipperId = getParameterAsInt(M_Shipper_ID);
		freightCostRule = getParameterAsString(FreightCostRule);
		freightCategoryId = getParameterAsInt(M_FreightCategory_ID);
	}

	/**	 Getter Parameter Value for orderReference	*/
	protected String getOrderReference() {
		return orderReference;
	}

	/**	 Getter Parameter Value for locatorId	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Getter Parameter Value for deliveryRule	*/
	protected String getDeliveryRule() {
		return deliveryRule;
	}

	/**	 Getter Parameter Value for documentTypeId	*/
	protected int getDocumentTypeId() {
		return documentTypeId;
	}

	/**	 Getter Parameter Value for documentAction	*/
	protected String getDocumentAction() {
		return documentAction;
	}

	/**	 Getter Parameter Value for shipDate	*/
	protected Timestamp getShipDate() {
		return shipDate;
	}

	/**	 Getter Parameter Value for pickDate	*/
	protected Timestamp getPickDate() {
		return pickDate;
	}

	/**	 Getter Parameter Value for priority	*/
	protected String getPriority() {
		return priority;
	}

	/**	 Getter Parameter Value for deliveryVia	*/
	protected String getDeliveryVia() {
		return deliveryVia;
	}

	/**	 Getter Parameter Value for shipperId	*/
	protected int getShipperId() {
		return shipperId;
	}

	/**	 Getter Parameter Value for freightCostRule	*/
	protected String getFreightCostRule() {
		return freightCostRule;
	}

	/**	 Getter Parameter Value for freightCategoryId	*/
	protected int getFreightCategoryId() {
		return freightCategoryId;
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