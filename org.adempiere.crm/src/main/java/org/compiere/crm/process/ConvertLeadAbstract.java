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

package org.compiere.crm.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Convert Lead)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ConvertLeadAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "AD_User Convert Lead";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Convert Lead";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53276;
	/**	Parameter Name for CreateOpportunity	*/
	public static final String CREATEOPPORTUNITY = "CreateOpportunity";
	/**	Parameter Name for Expected Close Date	*/
	public static final String EXPECTEDCLOSEDATE = "ExpectedCloseDate";
	/**	Parameter Name for Description	*/
	public static final String DESCRIPTION = "Description";
	/**	Parameter Name for Sales Stage	*/
	public static final String C_SALESSTAGE_ID = "C_SalesStage_ID";
	/**	Parameter Name for Opportunity Amount	*/
	public static final String OPPORTUNITYAMT = "OpportunityAmt";
	/**	Parameter Name for Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Value for CreateOpportunity	*/
	private boolean isCreateOpportunity;
	/**	Parameter Value for Expected Close Date	*/
	private Timestamp expectedCloseDate;
	/**	Parameter Value for Description	*/
	private String description;
	/**	Parameter Value for Sales Stage	*/
	private int salesStageId;
	/**	Parameter Value for Opportunity Amount	*/
	private BigDecimal opportunityAmt;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;

	@Override
	protected void prepare() {
		isCreateOpportunity = getParameterAsBoolean(CREATEOPPORTUNITY);
		expectedCloseDate = getParameterAsTimestamp(EXPECTEDCLOSEDATE);
		description = getParameterAsString(DESCRIPTION);
		salesStageId = getParameterAsInt(C_SALESSTAGE_ID);
		opportunityAmt = getParameterAsBigDecimal(OPPORTUNITYAMT);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		salesRepId = getParameterAsInt(SALESREP_ID);
	}

	/**	 Getter Parameter Value for CreateOpportunity	*/
	protected boolean isCreateOpportunity() {
		return isCreateOpportunity;
	}

	/**	 Setter Parameter Value for CreateOpportunity	*/
	protected void setCreateOpportunity(boolean isCreateOpportunity) {
		this.isCreateOpportunity = isCreateOpportunity;
	}

	/**	 Getter Parameter Value for Expected Close Date	*/
	protected Timestamp getExpectedCloseDate() {
		return expectedCloseDate;
	}

	/**	 Setter Parameter Value for Expected Close Date	*/
	protected void setExpectedCloseDate(Timestamp expectedCloseDate) {
		this.expectedCloseDate = expectedCloseDate;
	}

	/**	 Getter Parameter Value for Description	*/
	protected String getDescription() {
		return description;
	}

	/**	 Setter Parameter Value for Description	*/
	protected void setDescription(String description) {
		this.description = description;
	}

	/**	 Getter Parameter Value for Sales Stage	*/
	protected int getSalesStageId() {
		return salesStageId;
	}

	/**	 Setter Parameter Value for Sales Stage	*/
	protected void setSalesStageId(int salesStageId) {
		this.salesStageId = salesStageId;
	}

	/**	 Getter Parameter Value for Opportunity Amount	*/
	protected BigDecimal getOpportunityAmt() {
		return opportunityAmt;
	}

	/**	 Setter Parameter Value for Opportunity Amount	*/
	protected void setOpportunityAmt(BigDecimal opportunityAmt) {
		this.opportunityAmt = opportunityAmt;
	}

	/**	 Getter Parameter Value for Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Sales Representative	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Sales Representative	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
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