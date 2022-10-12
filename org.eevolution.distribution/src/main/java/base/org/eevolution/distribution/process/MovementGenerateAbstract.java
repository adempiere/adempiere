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

package org.eevolution.distribution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Movement)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class MovementGenerateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Generate Movement";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Movement";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53046;
	/**	Parameter Name for Warehouse	*/
	private static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Movement Date	*/
	private static final String MOVEMENTDATE = "MovementDate";
	/**	Parameter Name for Business Partner 	*/
	private static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Date Promised	*/
	private static final String DATEPROMISED = "DatePromised";
	/**	Parameter Name for Orders with unconfirmed Shipments	*/
	private static final String ISUNCONFIRMEDINOUT = "IsUnconfirmedInOut";
	/**	Parameter Name for Document Action	*/
	private static final String DOCACTION = "DocAction";
	/**	Parameter Name for Consolidate to one Document	*/
	private static final String CONSOLIDATEDOCUMENT = "ConsolidateDocument";
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Movement Date	*/
	private Timestamp movementDate;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Date Promised	*/
	private Timestamp datePromised;
	/**	Parameter Value for Orders with unconfirmed Shipments	*/
	private boolean isUnconfirmedInOut;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Consolidate to one Document	*/
	private boolean isConsolidateDocument;

	@Override
	protected void prepare() {
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		movementDate = getParameterAsTimestamp(MOVEMENTDATE);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		datePromised = getParameterAsTimestamp(DATEPROMISED);
		isUnconfirmedInOut = getParameterAsBoolean(ISUNCONFIRMEDINOUT);
		docAction = getParameterAsString(DOCACTION);
		isConsolidateDocument = getParameterAsBoolean(CONSOLIDATEDOCUMENT);
	}

	/**	 Getter Parameter Value for Warehouse	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Movement Date	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Setter Parameter Value for Movement Date	*/
	protected void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Date Promised	*/
	protected Timestamp getDatePromised() {
		return datePromised;
	}

	/**	 Setter Parameter Value for Date Promised	*/
	protected void setDatePromised(Timestamp datePromised) {
		this.datePromised = datePromised;
	}

	/**	 Getter Parameter Value for Orders with unconfirmed Shipments	*/
	protected boolean isUnconfirmedInOut() {
		return isUnconfirmedInOut;
	}

	/**	 Setter Parameter Value for Orders with unconfirmed Shipments	*/
	protected void setIsUnconfirmedInOut(boolean isUnconfirmedInOut) {
		this.isUnconfirmedInOut = isUnconfirmedInOut;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Consolidate to one Document	*/
	protected boolean isConsolidateDocument() {
		return isConsolidateDocument;
	}

	/**	 Setter Parameter Value for Consolidate to one Document	*/
	protected void setConsolidateDocument(boolean isConsolidateDocument) {
		this.isConsolidateDocument = isConsolidateDocument;
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