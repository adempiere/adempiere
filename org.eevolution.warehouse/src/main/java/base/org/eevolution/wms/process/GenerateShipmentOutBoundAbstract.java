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

/** Generated Process for (Generate Shipments from Outbound Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateShipmentOutBoundAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "WM_InOutbound Generate Shipment";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Shipments from Outbound Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53185;
 
	/**	Parameter Name for IsIncludeNotAvailable	*/
	private static final String ISINCLUDENOTAVAILABLE = "IsIncludeNotAvailable";
	/**	Parameter Name for Document Action	*/
	private static final String DOCACTION = "DocAction";
	/**	Parameter Name for Movement Date	*/
	private static final String MOVEMENTDATE = "MovementDate";

	/**	Parameter Value for IsIncludeNotAvailable	*/
	private boolean isIncludeNotAvailable;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Movement Date	*/
	private Timestamp movementDate;
 

	@Override
	protected void prepare() {
		isIncludeNotAvailable = getParameterAsBoolean(ISINCLUDENOTAVAILABLE);
		docAction = getParameterAsString(DOCACTION);
		movementDate = getParameterAsTimestamp(MOVEMENTDATE);
	}

	/**	 Getter Parameter Value for IsIncludeNotAvailable	*/
	protected boolean isIncludeNotAvailable() {
		return isIncludeNotAvailable;
	}

	/**	 Setter Parameter Value for IsIncludeNotAvailable	*/
	protected void setIsIncludeNotAvailable(boolean isIncludeNotAvailable) {
		this.isIncludeNotAvailable = isIncludeNotAvailable;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Movement Date	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Setter Parameter Value for Movement Date	*/
	protected void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
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