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

import org.compiere.process.SvrProcess;

/** Generated Process for (Release & Print Outbound Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ReleaseInOutBoundAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "WM_InOutbound Print & Release Picking";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Release & Print Outbound Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53184;
	/**	Parameter Name for Warehouse Area Type	*/
	private static final String WM_AREA_TYPE_ID = "WM_Area_Type_ID";
	/**	Parameter Name for Warehouse Section Type	*/
	private static final String WM_SECTION_TYPE_ID = "WM_Section_Type_ID";
	/**	Parameter Name for Outbound Locator	*/
	private static final String M_LOCATOR_ID = "M_Locator_ID";
	/**	Parameter Name for Document Type	*/
	private static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Document Action	*/
	private static final String DOCACTION = "DocAction";
	/**	Parameter Name for Delivery Rule	*/
	private static final String DELIVERYRULE = "DeliveryRule";
	/**	Parameter Name for Delivery Via	*/
	private static final String DELIVERYVIARULE = "DeliveryViaRule";
	/**	Parameter Name for Shipper	*/
	private static final String M_SHIPPER_ID = "M_Shipper_ID";
	/**	Parameter Name for Freight Cost Rule	*/
	private static final String FREIGHTCOSTRULE = "FreightCostRule";
	/**	Parameter Name for Freight Category	*/
	private static final String M_FREIGHTCATEGORY_ID = "M_FreightCategory_ID";
	/**	Parameter Name for Is Print Pick List	*/
	private static final String ISPRINTPICKLIST = "IsPrintPickList";
	/**	Parameter Name for Is Create Supply	*/
	private static final String ISCREATESUPPLY = "IsCreateSupply";
	/**	Parameter Value for Warehouse Area Type	*/
	private int areaTypeId;
	/**	Parameter Value for Warehouse Section Type	*/
	private int sectionTypeId;
	/**	Parameter Value for Outbound Locator	*/
	private int locatorId;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Delivery Rule	*/
	private String deliveryRule;
	/**	Parameter Value for Delivery Via	*/
	private String deliveryViaRule;
	/**	Parameter Value for Shipper	*/
	private int shipperId;
	/**	Parameter Value for Freight Cost Rule	*/
	private String freightCostRule;
	/**	Parameter Value for Freight Category	*/
	private int freightCategoryId;
	/**	Parameter Value for Is Print Pick List	*/
	private boolean isPrintPickList;
	/**	Parameter Value for Is Create Supply	*/
	private boolean isCreateSupply;

	@Override
	protected void prepare() {
		areaTypeId = getParameterAsInt(WM_AREA_TYPE_ID);
		sectionTypeId = getParameterAsInt(WM_SECTION_TYPE_ID);
		locatorId = getParameterAsInt(M_LOCATOR_ID);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		docAction = getParameterAsString(DOCACTION);
		deliveryRule = getParameterAsString(DELIVERYRULE);
		deliveryViaRule = getParameterAsString(DELIVERYVIARULE);
		shipperId = getParameterAsInt(M_SHIPPER_ID);
		freightCostRule = getParameterAsString(FREIGHTCOSTRULE);
		freightCategoryId = getParameterAsInt(M_FREIGHTCATEGORY_ID);
		isPrintPickList = getParameterAsBoolean(ISPRINTPICKLIST);
		isCreateSupply = getParameterAsBoolean(ISCREATESUPPLY);
	}

	/**	 Getter Parameter Value for Warehouse Area Type	*/
	protected int getAreaTypeId() {
		return areaTypeId;
	}

	/**	 Setter Parameter Value for Warehouse Area Type	*/
	protected void setAreaTypeId(int areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	/**	 Getter Parameter Value for Warehouse Section Type	*/
	protected int getSectionTypeId() {
		return sectionTypeId;
	}

	/**	 Setter Parameter Value for Warehouse Section Type	*/
	protected void setSectionTypeId(int sectionTypeId) {
		this.sectionTypeId = sectionTypeId;
	}

	/**	 Getter Parameter Value for Outbound Locator	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Setter Parameter Value for Outbound Locator	*/
	protected void setLocatorId(int locatorId) {
		this.locatorId = locatorId;
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

	/**	 Getter Parameter Value for Delivery Rule	*/
	protected String getDeliveryRule() {
		return deliveryRule;
	}

	/**	 Setter Parameter Value for Delivery Rule	*/
	protected void setDeliveryRule(String deliveryRule) {
		this.deliveryRule = deliveryRule;
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

	/**	 Getter Parameter Value for Is Print Pick List	*/
	protected boolean isPrintPickList() {
		return isPrintPickList;
	}

	/**	 Setter Parameter Value for Is Print Pick List	*/
	protected void setIsPrintPickList(boolean isPrintPickList) {
		this.isPrintPickList = isPrintPickList;
	}

	/**	 Getter Parameter Value for Is Create Supply	*/
	protected boolean isCreateSupply() {
		return isCreateSupply;
	}

	/**	 Setter Parameter Value for Is Create Supply	*/
	protected void setIsCreateSupply(boolean isCreateSupply) {
		this.isCreateSupply = isCreateSupply;
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