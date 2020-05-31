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

package org.eevolution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Valuation Effective Date)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ValuationEffectiveDateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Valuation Effective Date";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Valuation Effective Date";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53222;
	/**	Parameter Name for Valuation Date	*/
	public static final String DATEVALUE = "DateValue";
	/**	Parameter Name for Accounting Schema	*/
	public static final String C_ACCTSCHEMA_ID = "C_AcctSchema_ID";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Product Category	*/
	public static final String M_PRODUCT_CATEGORY_ID = "M_Product_Category_ID";
	/**	Parameter Name for Cost Type	*/
	public static final String M_COSTTYPE_ID = "M_CostType_ID";
	/**	Parameter Name for Cost Element	*/
	public static final String M_COSTELEMENT_ID = "M_CostElement_ID";
	/**	Parameter Name for Price List Version	*/
	public static final String M_PRICELIST_VERSION_ID = "M_PriceList_Version_ID";
	/**	Parameter Value for Valuation Date	*/
	private Timestamp dateValue;
	/**	Parameter Value for Accounting Schema	*/
	private int acctSchemaId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Product Category	*/
	private int productCategoryId;
	/**	Parameter Value for Cost Type	*/
	private int costTypeId;
	/**	Parameter Value for Cost Element	*/
	private int costElementId;
	/**	Parameter Value for Price List Version	*/
	private int priceListVersionId;

	@Override
	protected void prepare() {
		dateValue = getParameterAsTimestamp(DATEVALUE);
		acctSchemaId = getParameterAsInt(C_ACCTSCHEMA_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		productCategoryId = getParameterAsInt(M_PRODUCT_CATEGORY_ID);
		costTypeId = getParameterAsInt(M_COSTTYPE_ID);
		costElementId = getParameterAsInt(M_COSTELEMENT_ID);
		priceListVersionId = getParameterAsInt(M_PRICELIST_VERSION_ID);
	}

	/**	 Getter Parameter Value for Valuation Date	*/
	protected Timestamp getDateValue() {
		return dateValue;
	}

	/**	 Setter Parameter Value for Valuation Date	*/
	protected void setDateValue(Timestamp dateValue) {
		this.dateValue = dateValue;
	}

	/**	 Getter Parameter Value for Accounting Schema	*/
	protected int getAcctSchemaId() {
		return acctSchemaId;
	}

	/**	 Setter Parameter Value for Accounting Schema	*/
	protected void setAcctSchemaId(int acctSchemaId) {
		this.acctSchemaId = acctSchemaId;
	}

	/**	 Getter Parameter Value for Warehouse	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Product Category	*/
	protected int getProductCategoryId() {
		return productCategoryId;
	}

	/**	 Setter Parameter Value for Product Category	*/
	protected void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	/**	 Getter Parameter Value for Cost Type	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Setter Parameter Value for Cost Type	*/
	protected void setCostTypeId(int costTypeId) {
		this.costTypeId = costTypeId;
	}

	/**	 Getter Parameter Value for Cost Element	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Setter Parameter Value for Cost Element	*/
	protected void setCostElementId(int costElementId) {
		this.costElementId = costElementId;
	}

	/**	 Getter Parameter Value for Price List Version	*/
	protected int getPriceListVersionId() {
		return priceListVersionId;
	}

	/**	 Setter Parameter Value for Price List Version	*/
	protected void setPriceListVersionId(int priceListVersionId) {
		this.priceListVersionId = priceListVersionId;
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