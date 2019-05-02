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

/** Generated Process for (Generate Not Realized Gain / Loss for Multi-Currency Account)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class GenerateNotRealizedGainLossAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "GL Generate Not Realized Gain / Loss";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Not Realized Gain / Loss for Multi-Currency Account";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54198;
	/**	Parameter Name for Accounting Schema	*/
	public static final String C_ACCTSCHEMA_ID = "C_AcctSchema_ID";
	/**	Parameter Name for Revaluation Date	*/
	public static final String DATEREVAL = "DateReval";
	/**	Parameter Name for Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Revaluation Conversion Type	*/
	public static final String C_CONVERSIONTYPEREVAL_ID = "C_ConversionTypeReval_ID";
	/**	Parameter Name for Create New Journal	*/
	public static final String ISCREATENEWJOURNAL = "IsCreateNewJournal";
	/**	Parameter Name for Revaluation Document Type	*/
	public static final String C_DOCTYPEREVAL_ID = "C_DocTypeReval_ID";
	/**	Parameter Name for Batch Description	*/
	public static final String BATCHDESCRIPTION = "BatchDescription";
	/**	Parameter Value for Accounting Schema	*/
	private int acctSchemaId;
	/**	Parameter Value for Revaluation Date	*/
	private Timestamp dateReval;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Revaluation Conversion Type	*/
	private int conversionTypeRevalId;
	/**	Parameter Value for Create New Journal	*/
	private String isCreateNewJournal;
	/**	Parameter Value for Revaluation Document Type	*/
	private int docTypeRevalId;
	/**	Parameter Value for Batch Description	*/
	private String batchDescription;

	@Override
	protected void prepare() {
		acctSchemaId = getParameterAsInt(C_ACCTSCHEMA_ID);
		dateReval = getParameterAsTimestamp(DATEREVAL);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		conversionTypeRevalId = getParameterAsInt(C_CONVERSIONTYPEREVAL_ID);
		isCreateNewJournal = getParameterAsString(ISCREATENEWJOURNAL);
		docTypeRevalId = getParameterAsInt(C_DOCTYPEREVAL_ID);
		batchDescription = getParameterAsString(BATCHDESCRIPTION);
	}

	/**	 Getter Parameter Value for Accounting Schema	*/
	protected int getAcctSchemaId() {
		return acctSchemaId;
	}

	/**	 Setter Parameter Value for Accounting Schema	*/
	protected void setAcctSchemaId(int acctSchemaId) {
		this.acctSchemaId = acctSchemaId;
	}

	/**	 Getter Parameter Value for Revaluation Date	*/
	protected Timestamp getDateReval() {
		return dateReval;
	}

	/**	 Setter Parameter Value for Revaluation Date	*/
	protected void setDateReval(Timestamp dateReval) {
		this.dateReval = dateReval;
	}

	/**	 Getter Parameter Value for Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Revaluation Conversion Type	*/
	protected int getConversionTypeRevalId() {
		return conversionTypeRevalId;
	}

	/**	 Setter Parameter Value for Revaluation Conversion Type	*/
	protected void setConversionTypeRevalId(int conversionTypeRevalId) {
		this.conversionTypeRevalId = conversionTypeRevalId;
	}

	/**	 Getter Parameter Value for Create New Journal	*/
	protected String getIsCreateNewJournal() {
		return isCreateNewJournal;
	}

	/**	 Setter Parameter Value for Create New Journal	*/
	protected void setIsCreateNewJournal(String isCreateNewJournal) {
		this.isCreateNewJournal = isCreateNewJournal;
	}

	/**	 Getter Parameter Value for Revaluation Document Type	*/
	protected int getDocTypeRevalId() {
		return docTypeRevalId;
	}

	/**	 Setter Parameter Value for Revaluation Document Type	*/
	protected void setDocTypeRevalId(int docTypeRevalId) {
		this.docTypeRevalId = docTypeRevalId;
	}

	/**	 Getter Parameter Value for Batch Description	*/
	protected String getBatchDescription() {
		return batchDescription;
	}

	/**	 Setter Parameter Value for Batch Description	*/
	protected void setBatchDescription(String batchDescription) {
		this.batchDescription = batchDescription;
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