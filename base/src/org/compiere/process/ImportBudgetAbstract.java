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

package org.compiere.process;

import java.sql.Timestamp;
/** Generated Process for (Import Budget)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ImportBudgetAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "Import_Budget";
	/** Process Name 	*/
	private static final String NAME = "Import Budget";
	/** Process Id 	*/
	private static final int ID = 53487;
 
	/**	Parameter Name for DateAcct	*/
	public static final String DateAcct = "DateAcct";
	/**	Parameter Name for No_Of_Periods	*/
	public static final String No_Of_Periods = "No_Of_Periods";
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for BatchDescription	*/
	public static final String BatchDescription = "BatchDescription";
	/**	Parameter Name for C_AcctSchema_ID	*/
	public static final String C_AcctSchema_ID = "C_AcctSchema_ID";
	/**	Parameter Name for DeleteOldImported	*/
	public static final String DeleteOldImported = "DeleteOldImported";
	/**	Parameter Name for DocAction	*/
	public static final String DocAction = "DocAction";

	/**	Parameter Value for accountDate	*/
	private Timestamp accountDate;
	/**	Parameter Value for noOfPeriods	*/
	private int noOfPeriods;
	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for batchDescription	*/
	private String batchDescription;
	/**	Parameter Value for accountingSchemaId	*/
	private int accountingSchemaId;
	/**	Parameter Value for isDeleteoldimportedrecords	*/
	private boolean isDeleteoldimportedrecords;
	/**	Parameter Value for documentAction	*/
	private String documentAction;

	@Override
	protected void prepare()
	{
		accountDate = getParameterAsTimestamp(DateAcct);
		noOfPeriods = getParameterAsInt(No_Of_Periods);
		organizationId = getParameterAsInt(AD_Org_ID);
		batchDescription = getParameterAsString(BatchDescription);
		accountingSchemaId = getParameterAsInt(C_AcctSchema_ID);
		isDeleteoldimportedrecords = getParameterAsBoolean(DeleteOldImported);
		documentAction = getParameterAsString(DocAction);
	}

	/**	 Getter Parameter Value for accountDate	*/
	protected Timestamp getAccountDate() {
		return accountDate;
	}

	/**	 Getter Parameter Value for noOfPeriods	*/
	protected int getNoOfPeriods() {
		return noOfPeriods;
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for batchDescription	*/
	protected String getBatchDescription() {
		return batchDescription;
	}

	/**	 Getter Parameter Value for accountingSchemaId	*/
	protected int getAccountingSchemaId() {
		return accountingSchemaId;
	}

	/**	 Getter Parameter Value for isDeleteoldimportedrecords	*/
	protected boolean isDeleteoldimportedrecords() {
		return isDeleteoldimportedrecords;
	}

	/**	 Getter Parameter Value for documentAction	*/
	protected String getDocumentAction() {
		return documentAction;
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