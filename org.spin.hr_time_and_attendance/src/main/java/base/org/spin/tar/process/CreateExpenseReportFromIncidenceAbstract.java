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

package org.spin.tar.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Create Expense Report (From Incidence))
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CreateExpenseReportFromIncidenceAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBP_CreateExpenseReport";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Expense Report (From Incidence)";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54136;
	/**	Parameter Name for Report Date	*/
	public static final String DATEREPORT = "DateReport";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Price List	*/
	public static final String M_PRICELIST_ID = "M_PriceList_ID";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Invoiced	*/
	public static final String ISINVOICED = "IsInvoiced";
	/**	Parameter Value for Report Date	*/
	private Timestamp dateReport;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Price List	*/
	private int priceListId;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Invoiced	*/
	private String isInvoiced;

	@Override
	protected void prepare() {
		dateReport = getParameterAsTimestamp(DATEREPORT);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		priceListId = getParameterAsInt(M_PRICELIST_ID);
		docAction = getParameterAsString(DOCACTION);
		isInvoiced = getParameterAsString(ISINVOICED);
	}

	/**	 Getter Parameter Value for Report Date	*/
	protected Timestamp getDateReport() {
		return dateReport;
	}

	/**	 Setter Parameter Value for Report Date	*/
	protected void setDateReport(Timestamp dateReport) {
		this.dateReport = dateReport;
	}

	/**	 Getter Parameter Value for Warehouse	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Price List	*/
	protected int getPriceListId() {
		return priceListId;
	}

	/**	 Setter Parameter Value for Price List	*/
	protected void setPriceListId(int priceListId) {
		this.priceListId = priceListId;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Invoiced	*/
	protected String getIsInvoiced() {
		return isInvoiced;
	}

	/**	 Setter Parameter Value for Invoiced	*/
	protected void setIsInvoiced(String isInvoiced) {
		this.isInvoiced = isInvoiced;
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