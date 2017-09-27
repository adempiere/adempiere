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

package org.adempiere.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (SB_InOutGenerateFromOrderLine)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class SB_InOutGenerateFromOrderLineAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SB_InOutGenerateFromOrderLine";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "SB_InOutGenerateFromOrderLine";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53925;
	/**	Parameter Name for Shipment Date	*/
	public static final String MOVEMENTDATE = "MovementDate";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Consolidate to one Document	*/
	public static final String CONSOLIDATEDOCUMENT = "ConsolidateDocument";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Value for Shipment Date	*/
	private Timestamp movementDate;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Consolidate to one Document	*/
	private boolean isConsolidateDocument;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;

	@Override
	protected void prepare() {
		movementDate = getParameterAsTimestamp(MOVEMENTDATE);
		docAction = getParameterAsString(DOCACTION);
		isConsolidateDocument = getParameterAsBoolean(CONSOLIDATEDOCUMENT);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
	}

	/**	 Getter Parameter Value for Shipment Date	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Setter Parameter Value for Shipment Date	*/
	protected void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
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

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
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