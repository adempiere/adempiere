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

package org.compiere.process;

import java.sql.Timestamp;

/** Generated Process for (Create Dunning Run)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class DunningRunCreateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_DunningRun_Create";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Dunning Run";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 289;
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Include Disputed	*/
	public static final String INCLUDEINDISPUTE = "IncludeInDispute";
	/**	Parameter Name for Only Sales Invoices	*/
	public static final String ONLYSOTRX = "OnlySOTrx";
	/**	Parameter Name for Default Sales Rep	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Name for Dunning Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Include All Currencies	*/
	public static final String ISALLCURRENCIES = "IsAllCurrencies";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Dunning Date	*/
	public static final String DUNNINGDATE = "DunningDate";
	/**	Parameter Name for Dunning	*/
	public static final String C_DUNNING_ID = "C_Dunning_ID";
	/**	Parameter Name for Dunning Level	*/
	public static final String C_DUNNINGLEVEL_ID = "C_DunningLevel_ID";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Include Disputed	*/
	private boolean isIncludeInDispute;
	/**	Parameter Value for Only Sales Invoices	*/
	private boolean isOnlySOTrx;
	/**	Parameter Value for Default Sales Rep	*/
	private int salesRepId;
	/**	Parameter Value for Dunning Currency	*/
	private int currencyId;
	/**	Parameter Value for Include All Currencies	*/
	private boolean isAllCurrencies;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Dunning Date	*/
	private Timestamp dunningDate;
	/**	Parameter Value for Dunning	*/
	private int dunningId;
	/**	Parameter Value for Dunning Level	*/
	private int dunningLevelId;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		isIncludeInDispute = getParameterAsBoolean(INCLUDEINDISPUTE);
		isOnlySOTrx = getParameterAsBoolean(ONLYSOTRX);
		salesRepId = getParameterAsInt(SALESREP_ID);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		isAllCurrencies = getParameterAsBoolean(ISALLCURRENCIES);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		dunningDate = getParameterAsTimestamp(DUNNINGDATE);
		dunningId = getParameterAsInt(C_DUNNING_ID);
		dunningLevelId = getParameterAsInt(C_DUNNINGLEVEL_ID);
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Include Disputed	*/
	protected boolean isIncludeInDispute() {
		return isIncludeInDispute;
	}

	/**	 Setter Parameter Value for Include Disputed	*/
	protected void setIncludeInDispute(boolean isIncludeInDispute) {
		this.isIncludeInDispute = isIncludeInDispute;
	}

	/**	 Getter Parameter Value for Only Sales Invoices	*/
	protected boolean isOnlySOTrx() {
		return isOnlySOTrx;
	}

	/**	 Setter Parameter Value for Only Sales Invoices	*/
	protected void setOnlySOTrx(boolean isOnlySOTrx) {
		this.isOnlySOTrx = isOnlySOTrx;
	}

	/**	 Getter Parameter Value for Default Sales Rep	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Default Sales Rep	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
	}

	/**	 Getter Parameter Value for Dunning Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Dunning Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Include All Currencies	*/
	protected boolean isAllCurrencies() {
		return isAllCurrencies;
	}

	/**	 Setter Parameter Value for Include All Currencies	*/
	protected void setIsAllCurrencies(boolean isAllCurrencies) {
		this.isAllCurrencies = isAllCurrencies;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Dunning Date	*/
	protected Timestamp getDunningDate() {
		return dunningDate;
	}

	/**	 Setter Parameter Value for Dunning Date	*/
	protected void setDunningDate(Timestamp dunningDate) {
		this.dunningDate = dunningDate;
	}

	/**	 Getter Parameter Value for Dunning	*/
	protected int getDunningId() {
		return dunningId;
	}

	/**	 Setter Parameter Value for Dunning	*/
	protected void setDunningId(int dunningId) {
		this.dunningId = dunningId;
	}

	/**	 Getter Parameter Value for Dunning Level	*/
	protected int getDunningLevelId() {
		return dunningLevelId;
	}

	/**	 Setter Parameter Value for Dunning Level	*/
	protected void setDunningLevelId(int dunningLevelId) {
		this.dunningLevelId = dunningLevelId;
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