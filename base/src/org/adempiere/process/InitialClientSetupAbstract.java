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

package org.adempiere.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Initial Client Setup Process)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InitialClientSetupAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "InitialClientSetup";
	/** Process Name 	*/
	private static final String NAME = "Initial Client Setup Process";
	/** Process Id 	*/
	private static final int ID = 53161;
 
	/**	Parameter Name for ClientName	*/
	public static final String ClientName = "ClientName";
	/**	Parameter Name for OrgName	*/
	public static final String OrgName = "OrgName";
	/**	Parameter Name for OrgValue	*/
	public static final String OrgValue = "OrgValue";
	/**	Parameter Name for C_Currency_ID	*/
	public static final String C_Currency_ID = "C_Currency_ID";
	/**	Parameter Name for C_Country_ID	*/
	public static final String C_Country_ID = "C_Country_ID";
	/**	Parameter Name for CoAFile	*/
	public static final String CoAFile = "CoAFile";
	/**	Parameter Name for IsUseBPDimension	*/
	public static final String IsUseBPDimension = "IsUseBPDimension";
	/**	Parameter Name for IsUseProductDimension	*/
	public static final String IsUseProductDimension = "IsUseProductDimension";
	/**	Parameter Name for IsUseProjectDimension	*/
	public static final String IsUseProjectDimension = "IsUseProjectDimension";
	/**	Parameter Name for IsUseCampaignDimension	*/
	public static final String IsUseCampaignDimension = "IsUseCampaignDimension";
	/**	Parameter Name for IsUseSalesRegionDimension	*/
	public static final String IsUseSalesRegionDimension = "IsUseSalesRegionDimension";
	/**	Parameter Name for StartDate	*/
	public static final String StartDate = "StartDate";
	/**	Parameter Name for HistoryYears	*/
	public static final String HistoryYears = "HistoryYears";
	/**	Parameter Name for C_Region_ID	*/
	public static final String C_Region_ID = "C_Region_ID";
	/**	Parameter Name for CityName	*/
	public static final String CityName = "CityName";
	/**	Parameter Name for C_City_ID	*/
	public static final String C_City_ID = "C_City_ID";
	/**	Parameter Name for Postal	*/
	public static final String Postal = "Postal";
	/**	Parameter Name for Address1	*/
	public static final String Address1 = "Address1";
	/**	Parameter Name for Phone	*/
	public static final String Phone = "Phone";
	/**	Parameter Name for Phone2	*/
	public static final String Phone2 = "Phone2";
	/**	Parameter Name for Fax	*/
	public static final String Fax = "Fax";
	/**	Parameter Name for EMail	*/
	public static final String EMail = "EMail";
	/**	Parameter Name for TaxID	*/
	public static final String TaxID = "TaxID";
	/**	Parameter Name for DUNS	*/
	public static final String DUNS = "DUNS";
	/**	Parameter Name for Logo_ID	*/
	public static final String Logo_ID = "Logo_ID";
	/**	Parameter Name for AdminUserName	*/
	public static final String AdminUserName = "AdminUserName";
	/**	Parameter Name for NormalUserName	*/
	public static final String NormalUserName = "NormalUserName";
	/**	Parameter Name for BankName	*/
	public static final String BankName = "BankName";
	/**	Parameter Name for RoutingNo	*/
	public static final String RoutingNo = "RoutingNo";
	/**	Parameter Name for AccountNo	*/
	public static final String AccountNo = "AccountNo";

	/**	Parameter Value for clientName	*/
	private String clientName;
	/**	Parameter Value for organizationName	*/
	private String organizationName;
	/**	Parameter Value for orgKey	*/
	private String orgKey;
	/**	Parameter Value for currencyId	*/
	private int currencyId;
	/**	Parameter Value for countryId	*/
	private int countryId;
	/**	Parameter Value for chartofAccountsFile	*/
	private String chartofAccountsFile;
	/**	Parameter Value for isBPAccounting	*/
	private boolean isBPAccounting;
	/**	Parameter Value for isProductAccounting	*/
	private boolean isProductAccounting;
	/**	Parameter Value for isProjectAccounting	*/
	private boolean isProjectAccounting;
	/**	Parameter Value for isCampaignAccounting	*/
	private boolean isCampaignAccounting;
	/**	Parameter Value for isSalesRegionAccounting	*/
	private boolean isSalesRegionAccounting;
	/**	Parameter Value for startDate	*/
	private Timestamp startDate;
	/**	Parameter Value for historyYears	*/
	private int historyYears;
	/**	Parameter Value for regionId	*/
	private int regionId;
	/**	Parameter Value for cityName	*/
	private String cityName;
	/**	Parameter Value for cityId	*/
	private int cityId;
	/**	Parameter Value for zIP	*/
	private String zIP;
	/**	Parameter Value for address1	*/
	private String address1;
	/**	Parameter Value for phone	*/
	private String phone;
	/**	Parameter Value for 2ndPhone	*/
	private String phone2;
	/**	Parameter Value for fax	*/
	private String fax;
	/**	Parameter Value for eMailAddress	*/
	private String eMailAddress;
	/**	Parameter Value for taxID	*/
	private String taxID;
	/**	Parameter Value for d-U-N-S	*/
	private String duns;
	/**	Parameter Value for logo	*/
	private String logo;
	/**	Parameter Value for administrativeUserName	*/
	private String administrativeUserName;
	/**	Parameter Value for normalUserName	*/
	private String normalUserName;
	/**	Parameter Value for bankName	*/
	private String bankName;
	/**	Parameter Value for routingNo	*/
	private String routingNo;
	/**	Parameter Value for accountNo	*/
	private String accountNo;
 

	@Override
	protected void prepare()
	{
		clientName = getParameterAsString(ClientName);
		organizationName = getParameterAsString(OrgName);
		orgKey = getParameterAsString(OrgValue);
		currencyId = getParameterAsInt(C_Currency_ID);
		countryId = getParameterAsInt(C_Country_ID);
		chartofAccountsFile = getParameterAsString(CoAFile);
		isBPAccounting = getParameterAsBoolean(IsUseBPDimension);
		isProductAccounting = getParameterAsBoolean(IsUseProductDimension);
		isProjectAccounting = getParameterAsBoolean(IsUseProjectDimension);
		isCampaignAccounting = getParameterAsBoolean(IsUseCampaignDimension);
		isSalesRegionAccounting = getParameterAsBoolean(IsUseSalesRegionDimension);
		startDate = getParameterAsTimestamp(StartDate);
		historyYears = getParameterAsInt(HistoryYears);
		regionId = getParameterAsInt(C_Region_ID);
		cityName = getParameterAsString(CityName);
		cityId = getParameterAsInt(C_City_ID);
		zIP = getParameterAsString(Postal);
		address1 = getParameterAsString(Address1);
		phone = getParameterAsString(Phone);
		phone2 = getParameterAsString(Phone2);
		fax = getParameterAsString(Fax);
		eMailAddress = getParameterAsString(EMail);
		taxID = getParameterAsString(TaxID);
		duns = getParameterAsString(DUNS);
		logo = getParameterAsString(Logo_ID);
		administrativeUserName = getParameterAsString(AdminUserName);
		normalUserName = getParameterAsString(NormalUserName);
		bankName = getParameterAsString(BankName);
		routingNo = getParameterAsString(RoutingNo);
		accountNo = getParameterAsString(AccountNo);
	}

	/**	 Getter Parameter Value for clientName	*/
	protected String getClientName() {
		return clientName;
	}

	/**	 Getter Parameter Value for organizationName	*/
	protected String getOrganizationName() {
		return organizationName;
	}

	/**	 Getter Parameter Value for orgKey	*/
	protected String getOrgKey() {
		return orgKey;
	}

	/**	 Getter Parameter Value for currencyId	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Getter Parameter Value for countryId	*/
	protected int getCountryId() {
		return countryId;
	}

	/**	 Getter Parameter Value for chartofAccountsFile	*/
	protected String getChartofAccountsFile() {
		return chartofAccountsFile;
	}

	/**	 Getter Parameter Value for isBPAccounting	*/
	protected boolean isBPAccounting() {
		return isBPAccounting;
	}

	/**	 Getter Parameter Value for isProductAccounting	*/
	protected boolean isProductAccounting() {
		return isProductAccounting;
	}

	/**	 Getter Parameter Value for isProjectAccounting	*/
	protected boolean isProjectAccounting() {
		return isProjectAccounting;
	}

	/**	 Getter Parameter Value for isCampaignAccounting	*/
	protected boolean isCampaignAccounting() {
		return isCampaignAccounting;
	}

	/**	 Getter Parameter Value for isSalesRegionAccounting	*/
	protected boolean isSalesRegionAccounting() {
		return isSalesRegionAccounting;
	}

	/**	 Getter Parameter Value for startDate	*/
	protected Timestamp getStartDate() {
		return startDate;
	}

	/**	 Getter Parameter Value for historyYears	*/
	protected int getHistoryYears() {
		return historyYears;
	}

	/**	 Getter Parameter Value for regionId	*/
	protected int getRegionId() {
		return regionId;
	}

	/**	 Getter Parameter Value for cityName	*/
	protected String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = clientName;
	}

	/**	 Getter Parameter Value for cityId	*/
	protected int getCityId() {
		return cityId;
	}

	/**	 Getter Parameter Value for zIP	*/
	protected String getZIP() {
		return zIP;
	}

	/**	 Getter Parameter Value for address1	*/
	protected String getAddress1() {
		return address1;
	}

	/**	 Getter Parameter Value for phone	*/
	protected String getPhone() {
		return phone;
	}

	/**	 Getter Parameter Value for 2ndPhone	*/
	protected String getPhone2() {
		return phone2;
	}

	/**	 Getter Parameter Value for fax	*/
	protected String getFax() {
		return fax;
	}

	/**	 Getter Parameter Value for eMailAddress	*/
	protected String getEMailAddress() {
		return eMailAddress;
	}

	/**	 Getter Parameter Value for taxID	*/
	protected String getTaxID() {
		return taxID;
	}

	/**	 Getter Parameter Value for d-U-N-S	*/
	protected String getDUNS() {
		return duns;
	}

	/**	 Getter Parameter Value for logo	*/
	protected String getLogo() {
		return logo;
	}

	/**	 Getter Parameter Value for administrativeUserName	*/
	protected String getAdministrativeUserName() {
		return administrativeUserName;
	}

	/**	 Getter Parameter Value for normalUserName	*/
	protected String getNormalUserName() {
		return normalUserName;
	}

	/**	 Getter Parameter Value for bankName	*/
	protected String getBankName() {
		return bankName;
	}

	/**	 Getter Parameter Value for routingNo	*/
	protected String getRoutingNo() {
		return routingNo;
	}

	/**	 Getter Parameter Value for accountNo	*/
	protected String getAccountNo() {
		return accountNo;
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