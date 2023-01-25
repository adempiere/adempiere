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

package org.compiere.request.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Invoice Requests)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class RequestInvoiceAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "R_RequestInvoice";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Invoice Requests";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 324;
	/**	Parameter Name for Request Type	*/
	public static final String R_REQUESTTYPE_ID = "R_RequestType_ID";
	/**	Parameter Name for Group	*/
	public static final String R_GROUP_ID = "R_Group_ID";
	/**	Parameter Name for Category	*/
	public static final String R_CATEGORY_ID = "R_Category_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Value for Request Type	*/
	private int requestTypeId;
	/**	Parameter Value for Group	*/
	private int groupId;
	/**	Parameter Value for Category	*/
	private int categoryId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Product	*/
	private int productId;

	@Override
	protected void prepare() {
		requestTypeId = getParameterAsInt(R_REQUESTTYPE_ID);
		groupId = getParameterAsInt(R_GROUP_ID);
		categoryId = getParameterAsInt(R_CATEGORY_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
	}

	/**	 Getter Parameter Value for Request Type	*/
	protected int getRequestTypeId() {
		return requestTypeId;
	}

	/**	 Setter Parameter Value for Request Type	*/
	protected void setRequestTypeId(int requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

	/**	 Getter Parameter Value for Group	*/
	protected int getGroupId() {
		return groupId;
	}

	/**	 Setter Parameter Value for Group	*/
	protected void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**	 Getter Parameter Value for Category	*/
	protected int getCategoryId() {
		return categoryId;
	}

	/**	 Setter Parameter Value for Category	*/
	protected void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
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