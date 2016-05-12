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


/** Generated Process for (Generate Movement)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class MovementGenerateAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "M_Generate Movement";
	/** Process Name 	*/
	private static final String NAME = "Generate Movement";
	/** Process Id 	*/
	private static final int ID = 53046;
 
	/**	Parameter Name for M_Warehouse_ID	*/
	public static final String M_Warehouse_ID = "M_Warehouse_ID";
	/**	Parameter Name for MovementDate	*/
	public static final String MovementDate = "MovementDate";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for DatePromised	*/
	public static final String DatePromised = "DatePromised";
	/**	Parameter Name for IsUnconfirmedInOut	*/
	public static final String IsUnconfirmedInOut = "IsUnconfirmedInOut";
	/**	Parameter Name for DocAction	*/
	public static final String DocAction = "DocAction";
	/**	Parameter Name for ConsolidateDocument	*/
	public static final String ConsolidateDocument = "ConsolidateDocument";

	/**	Parameter Value for warehouseId	*/
	private int warehouseId;
	/**	Parameter Value for movementDate	*/
	private Timestamp movementDate;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for datePromised	*/
	private Timestamp datePromised;
	/**	Parameter Value for isOrderswithunconfirmedShipments	*/
	private boolean isOrderswithunconfirmedShipments;
	/**	Parameter Value for documentAction	*/
	private String documentAction;
	/**	Parameter Value for isConsolidatetooneDocument	*/
	private boolean isConsolidatetooneDocument;
 

	@Override
	protected void prepare()
	{
		warehouseId = getParameterAsInt(M_Warehouse_ID);
		movementDate = getParameterAsTimestamp(MovementDate);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		datePromised = getParameterAsTimestamp(DatePromised);
		isOrderswithunconfirmedShipments = getParameterAsBoolean(IsUnconfirmedInOut);
		documentAction = getParameterAsString(DocAction);
		isConsolidatetooneDocument = getParameterAsBoolean(ConsolidateDocument);
	}

	/**	 Getter Parameter Value for warehouseId	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Getter Parameter Value for movementDate	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for datePromised	*/
	protected Timestamp getDatePromised() {
		return datePromised;
	}

	/**	 Getter Parameter Value for isOrderswithunconfirmedShipments	*/
	protected boolean isOrderswithunconfirmedShipments() {
		return isOrderswithunconfirmedShipments;
	}

	/**	 Getter Parameter Value for documentAction	*/
	protected String getDocumentAction() {
		return documentAction;
	}

	/**	 Getter Parameter Value for isConsolidatetooneDocument	*/
	protected boolean isConsolidatetooneDocument() {
		return isConsolidatetooneDocument;
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