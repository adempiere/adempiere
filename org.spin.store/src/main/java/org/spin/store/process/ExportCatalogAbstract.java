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

package org.spin.store.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Export Catalog to Elastic Search)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ExportCatalogAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "VSF_ExportCatalog";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Export Catalog to Elastic Search";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 1000003;
	/**	Parameter Name for Web Store	*/
	public static final String W_STORE_ID = "W_Store_ID";
	/**	Parameter Name for Export Product	*/
	public static final String ISEXPORTPRODUCT = "IsExportProduct";
	/**	Parameter Name for Export Product Category	*/
	public static final String ISEXPORTPRODUCTCATEGORY = "IsExportProductCategory";
	/**	Parameter Name for Export Product Attribute	*/
	public static final String ISEXPORTPRODUCTATTRIBUTE = "IsExportProductAttribute";
	/**	Parameter Name for Export Tax	*/
	public static final String ISEXPORTTAX = "IsExportTax";
	/**	Parameter Value for Web Store	*/
	private int storeId;
	/**	Parameter Value for Export Product	*/
	private boolean isExportProduct;
	/**	Parameter Value for Export Product Category	*/
	private boolean isExportProductCategory;
	/**	Parameter Value for Export Product Attribute	*/
	private boolean isExportProductAttribute;
	/**	Parameter Value for Export Tax	*/
	private boolean isExportTax;

	@Override
	protected void prepare() {
		storeId = getParameterAsInt(W_STORE_ID);
		isExportProduct = getParameterAsBoolean(ISEXPORTPRODUCT);
		isExportProductCategory = getParameterAsBoolean(ISEXPORTPRODUCTCATEGORY);
		isExportProductAttribute = getParameterAsBoolean(ISEXPORTPRODUCTATTRIBUTE);
		isExportTax = getParameterAsBoolean(ISEXPORTTAX);
	}

	/**	 Getter Parameter Value for Web Store	*/
	protected int getStoreId() {
		return storeId;
	}

	/**	 Setter Parameter Value for Web Store	*/
	protected void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**	 Getter Parameter Value for Export Product	*/
	protected boolean isExportProduct() {
		return isExportProduct;
	}

	/**	 Setter Parameter Value for Export Product	*/
	protected void setIsExportProduct(boolean isExportProduct) {
		this.isExportProduct = isExportProduct;
	}

	/**	 Getter Parameter Value for Export Product Category	*/
	protected boolean isExportProductCategory() {
		return isExportProductCategory;
	}

	/**	 Setter Parameter Value for Export Product Category	*/
	protected void setIsExportProductCategory(boolean isExportProductCategory) {
		this.isExportProductCategory = isExportProductCategory;
	}

	/**	 Getter Parameter Value for Export Product Attribute	*/
	protected boolean isExportProductAttribute() {
		return isExportProductAttribute;
	}

	/**	 Setter Parameter Value for Export Product Attribute	*/
	protected void setIsExportProductAttribute(boolean isExportProductAttribute) {
		this.isExportProductAttribute = isExportProductAttribute;
	}

	/**	 Getter Parameter Value for Export Tax	*/
	protected boolean isExportTax() {
		return isExportTax;
	}

	/**	 Setter Parameter Value for Export Tax	*/
	protected void setIsExportTax(boolean isExportTax) {
		this.isExportTax = isExportTax;
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