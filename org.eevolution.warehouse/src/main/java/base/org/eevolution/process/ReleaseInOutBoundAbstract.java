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
/** Generated Process for (Release & Print Outbound Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ReleaseInOutBoundAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "WM_InOutbound Print & Release Picking";
	/** Process Name 	*/
	private static final String NAME = "Release & Print Outbound Order";
	/** Process Id 	*/
	private static final int ID = 53184;
 
	/**	Parameter Name for WM_Area_Type_ID	*/
	public static final String WM_Area_Type_ID = "WM_Area_Type_ID";
	/**	Parameter Name for WM_Section_Type_ID	*/
	public static final String WM_Section_Type_ID = "WM_Section_Type_ID";
	/**	Parameter Name for M_Locator_ID	*/
	public static final String M_Locator_ID = "M_Locator_ID";
	/**	Parameter Name for C_DocType_ID	*/
	public static final String C_DocType_ID = "C_DocType_ID";
	/**	Parameter Name for DocAction	*/
	public static final String DocAction = "DocAction";
	/**	Parameter Name for DeliveryRule	*/
	public static final String DeliveryRule = "DeliveryRule";
	/**	Parameter Name for DeliveryViaRule	*/
	public static final String DeliveryViaRule = "DeliveryViaRule";
	/**	Parameter Name for M_Shipper_ID	*/
	public static final String M_Shipper_ID = "M_Shipper_ID";
	/**	Parameter Name for FreightCostRule	*/
	public static final String FreightCostRule = "FreightCostRule";
	/**	Parameter Name for M_FreightCategory_ID	*/
	public static final String M_FreightCategory_ID = "M_FreightCategory_ID";
	/**	Parameter Name for IsPrintPickList	*/
	public static final String IsPrintPickList = "IsPrintPickList";
	/**	Parameter Name for IsCreateSupply	*/
	public static final String IsCreateSupply = "IsCreateSupply";

	/**	Parameter Value for warehouseAreaTypeId	*/
	private int warehouseAreaTypeId;
	/**	Parameter Value for warehouseSectionTypeId	*/
	private int warehouseSectionTypeId;
	/**	Parameter Value for outboundLocatorId	*/
	private int outboundLocatorId;
	/**	Parameter Value for documentTypeId	*/
	private int documentTypeId;
	/**	Parameter Value for documentAction	*/
	private String documentAction;
	/**	Parameter Value for deliveryRule	*/
	private String deliveryRule;
	/**	Parameter Value for deliveryVia	*/
	private String deliveryVia;
	/**	Parameter Value for shipperId	*/
	private int shipperId;
	/**	Parameter Value for freightCostRule	*/
	private String freightCostRule;
	/**	Parameter Value for freightCategoryId	*/
	private int freightCategoryId;
	/**	Parameter Value for isPrintPickList	*/
	private boolean isPrintPickList;
	/**	Parameter Value for isCreateSupply	*/
	private boolean isCreateSupply;
 

	@Override
	protected void prepare()
	{
		warehouseAreaTypeId = getParameterAsInt(WM_Area_Type_ID);
		warehouseSectionTypeId = getParameterAsInt(WM_Section_Type_ID);
		outboundLocatorId = getParameterAsInt(M_Locator_ID);
		documentTypeId = getParameterAsInt(C_DocType_ID);
		documentAction = getParameterAsString(DocAction);
		deliveryRule = getParameterAsString(DeliveryRule);
		deliveryVia = getParameterAsString(DeliveryViaRule);
		shipperId = getParameterAsInt(M_Shipper_ID);
		freightCostRule = getParameterAsString(FreightCostRule);
		freightCategoryId = getParameterAsInt(M_FreightCategory_ID);
		isPrintPickList = getParameterAsBoolean(IsPrintPickList);
		isCreateSupply = getParameterAsBoolean(IsCreateSupply);
	}

	/**	 Getter Parameter Value for warehouseAreaTypeId	*/
	protected int getWarehouseAreaTypeId() {
		return warehouseAreaTypeId;
	}

	/**	 Getter Parameter Value for warehouseSectionTypeId	*/
	protected int getWarehouseSectionTypeId() {
		return warehouseSectionTypeId;
	}

	/**	 Getter Parameter Value for outboundLocatorId	*/
	protected int getOutboundLocatorId() {
		return outboundLocatorId;
	}

	/**	 Getter Parameter Value for documentTypeId	*/
	protected int getDocumentTypeId() {
		return documentTypeId;
	}

	/**	 Getter Parameter Value for documentAction	*/
	protected String getDocumentAction() {
		return documentAction;
	}

	/**	 Getter Parameter Value for deliveryRule	*/
	protected String getDeliveryRule() {
		return deliveryRule;
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

	/**	 Getter Parameter Value for isPrintPickList	*/
	protected boolean isPrintPickList() {
		return isPrintPickList;
	}

	/**	 Getter Parameter Value for isCreateSupply	*/
	protected boolean isCreateSupply() {
		return isCreateSupply;
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