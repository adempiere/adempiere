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

/** Generated Process for (Initial Client Setup Process)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InitialClientSetupAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "InitialClientSetup";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Initial Client Setup Process";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53161;
	/**	Parameter Name for Client Name	*/
	private static final String CLIENTNAME = "ClientName";
	/**	Parameter Name for Organization Name	*/
	private static final String ORGNAME = "OrgName";
	/**	Parameter Name for Org Key	*/
	private static final String ORGVALUE = "OrgValue";
	/**	Parameter Name for Currency	*/
	private static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Country	*/
	private static final String C_COUNTRY_ID = "C_Country_ID";
	/**	Parameter Name for Chart of Accounts File	*/
	private static final String COAFILE = "CoAFile";
	/**	Parameter Name for BP Accounting	*/
	private static final String ISUSEBPDIMENSION = "IsUseBPDimension";
	/**	Parameter Name for Product Accounting	*/
	private static final String ISUSEPRODUCTDIMENSION = "IsUseProductDimension";
	/**	Parameter Name for Project Accounting	*/
	private static final String ISUSEPROJECTDIMENSION = "IsUseProjectDimension";
	/**	Parameter Name for Campaign Accounting	*/
	private static final String ISUSECAMPAIGNDIMENSION = "IsUseCampaignDimension";
	/**	Parameter Name for Sales Region Accounting	*/
	private static final String ISUSESALESREGIONDIMENSION = "IsUseSalesRegionDimension";
	/**	Parameter Name for Start Date	*/
	private static final String STARTDATE = "StartDate";
	/**	Parameter Name for HistoryYears	*/
	private static final String HISTORYYEARS = "HistoryYears";
	/**	Parameter Name for Region	*/
	private static final String C_REGION_ID = "C_Region_ID";
	/**	Parameter Name for City Name	*/
	private static final String CITYNAME = "CityName";
	/**	Parameter Name for City	*/
	private static final String C_CITY_ID = "C_City_ID";
	/**	Parameter Name for ZIP	*/
	private static final String POSTAL = "Postal";
	/**	Parameter Name for Address 1	*/
	private static final String ADDRESS1 = "Address1";
	/**	Parameter Name for Phone	*/
	private static final String PHONE = "Phone";
	/**	Parameter Name for 2nd Phone	*/
	private static final String PHONE2 = "Phone2";
	/**	Parameter Name for Fax	*/
	private static final String FAX = "Fax";
	/**	Parameter Name for EMail Address	*/
	private static final String EMAIL = "EMail";
	/**	Parameter Name for Tax ID	*/
	private static final String TAXID = "TaxID";
	/**	Parameter Name for D-U-N-S	*/
	private static final String DUNS = "DUNS";
	/**	Parameter Name for Logo	*/
	private static final String LOGO_ID = "Logo_ID";
	/**	Parameter Name for Administrative User Name	*/
	private static final String ADMINUSERNAME = "AdminUserName";
	/**	Parameter Name for Normal User Name	*/
	private static final String NORMALUSERNAME = "NormalUserName";
	/**	Parameter Name for Bank Name	*/
	private static final String BANKNAME = "BankName";
	/**	Parameter Name for Routing No	*/
	private static final String ROUTINGNO = "RoutingNo";
	/**	Parameter Name for Account No	*/
	private static final String ACCOUNTNO = "AccountNo";
	/**	Parameter Value for Client Name	*/
	private String clientName;
	/**	Parameter Value for Organization Name	*/
	private String orgName;
	/**	Parameter Value for Org Key	*/
	private String orgValue;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Country	*/
	private int countryId;
	/**	Parameter Value for Chart of Accounts File	*/
	private String coAFile;
	/**	Parameter Value for BP Accounting	*/
	private boolean isUseBPDimension;
	/**	Parameter Value for Product Accounting	*/
	private boolean isUseProductDimension;
	/**	Parameter Value for Project Accounting	*/
	private boolean isUseProjectDimension;
	/**	Parameter Value for Campaign Accounting	*/
	private boolean isUseCampaignDimension;
	/**	Parameter Value for Sales Region Accounting	*/
	private boolean isUseSalesRegionDimension;
	/**	Parameter Value for Start Date	*/
	private Timestamp startDate;
	/**	Parameter Value for HistoryYears	*/
	private int historyYears;
	/**	Parameter Value for Region	*/
	private int regionId;
	/**	Parameter Value for City Name	*/
	private String cityName;
	/**	Parameter Value for City	*/
	private int cityId;
	/**	Parameter Value for ZIP	*/
	private String postal;
	/**	Parameter Value for Address 1	*/
	private String address1;
	/**	Parameter Value for Phone	*/
	private String phone;
	/**	Parameter Value for 2nd Phone	*/
	private String phone2;
	/**	Parameter Value for Fax	*/
	private String fax;
	/**	Parameter Value for EMail Address	*/
	private String eMail;
	/**	Parameter Value for Tax ID	*/
	private String taxID;
	/**	Parameter Value for D-U-N-S	*/
	private String dUNS;
	/**	Parameter Value for Logo	*/
	private String logo;
	/**	Parameter Value for Administrative User Name	*/
	private String adminUserName;
	/**	Parameter Value for Normal User Name	*/
	private String normalUserName;
	/**	Parameter Value for Bank Name	*/
	private String bankName;
	/**	Parameter Value for Routing No	*/
	private String routingNo;
	/**	Parameter Value for Account No	*/
	private String accountNo;

	@Override
	protected void prepare() {
		clientName = getParameterAsString(CLIENTNAME);
		orgName = getParameterAsString(ORGNAME);
		orgValue = getParameterAsString(ORGVALUE);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		countryId = getParameterAsInt(C_COUNTRY_ID);
		coAFile = getParameterAsString(COAFILE);
		isUseBPDimension = getParameterAsBoolean(ISUSEBPDIMENSION);
		isUseProductDimension = getParameterAsBoolean(ISUSEPRODUCTDIMENSION);
		isUseProjectDimension = getParameterAsBoolean(ISUSEPROJECTDIMENSION);
		isUseCampaignDimension = getParameterAsBoolean(ISUSECAMPAIGNDIMENSION);
		isUseSalesRegionDimension = getParameterAsBoolean(ISUSESALESREGIONDIMENSION);
		startDate = getParameterAsTimestamp(STARTDATE);
		historyYears = getParameterAsInt(HISTORYYEARS);
		regionId = getParameterAsInt(C_REGION_ID);
		cityName = getParameterAsString(CITYNAME);
		cityId = getParameterAsInt(C_CITY_ID);
		postal = getParameterAsString(POSTAL);
		address1 = getParameterAsString(ADDRESS1);
		phone = getParameterAsString(PHONE);
		phone2 = getParameterAsString(PHONE2);
		fax = getParameterAsString(FAX);
		eMail = getParameterAsString(EMAIL);
		taxID = getParameterAsString(TAXID);
		dUNS = getParameterAsString(DUNS);
		logo = getParameterAsString(LOGO_ID);
		adminUserName = getParameterAsString(ADMINUSERNAME);
		normalUserName = getParameterAsString(NORMALUSERNAME);
		bankName = getParameterAsString(BANKNAME);
		routingNo = getParameterAsString(ROUTINGNO);
		accountNo = getParameterAsString(ACCOUNTNO);
	}

	/**	 Getter Parameter Value for Client Name	*/
	protected String getClientName() {
		return clientName;
	}

	/**	 Setter Parameter Value for Client Name	*/
	protected void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**	 Getter Parameter Value for Organization Name	*/
	protected String getOrgName() {
		return orgName;
	}

	/**	 Setter Parameter Value for Organization Name	*/
	protected void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**	 Getter Parameter Value for Org Key	*/
	protected String getOrgValue() {
		return orgValue;
	}

	/**	 Setter Parameter Value for Org Key	*/
	protected void setOrgValue(String orgValue) {
		this.orgValue = orgValue;
	}

	/**	 Getter Parameter Value for Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Country	*/
	protected int getCountryId() {
		return countryId;
	}

	/**	 Setter Parameter Value for Country	*/
	protected void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**	 Getter Parameter Value for Chart of Accounts File	*/
	protected String getCoAFile() {
		return coAFile;
	}

	/**	 Setter Parameter Value for Chart of Accounts File	*/
	protected void setCoAFile(String coAFile) {
		this.coAFile = coAFile;
	}

	/**	 Getter Parameter Value for BP Accounting	*/
	protected boolean isUseBPDimension() {
		return isUseBPDimension;
	}

	/**	 Setter Parameter Value for BP Accounting	*/
	protected void setIsUseBPDimension(boolean isUseBPDimension) {
		this.isUseBPDimension = isUseBPDimension;
	}

	/**	 Getter Parameter Value for Product Accounting	*/
	protected boolean isUseProductDimension() {
		return isUseProductDimension;
	}

	/**	 Setter Parameter Value for Product Accounting	*/
	protected void setIsUseProductDimension(boolean isUseProductDimension) {
		this.isUseProductDimension = isUseProductDimension;
	}

	/**	 Getter Parameter Value for Project Accounting	*/
	protected boolean isUseProjectDimension() {
		return isUseProjectDimension;
	}

	/**	 Setter Parameter Value for Project Accounting	*/
	protected void setIsUseProjectDimension(boolean isUseProjectDimension) {
		this.isUseProjectDimension = isUseProjectDimension;
	}

	/**	 Getter Parameter Value for Campaign Accounting	*/
	protected boolean isUseCampaignDimension() {
		return isUseCampaignDimension;
	}

	/**	 Setter Parameter Value for Campaign Accounting	*/
	protected void setIsUseCampaignDimension(boolean isUseCampaignDimension) {
		this.isUseCampaignDimension = isUseCampaignDimension;
	}

	/**	 Getter Parameter Value for Sales Region Accounting	*/
	protected boolean isUseSalesRegionDimension() {
		return isUseSalesRegionDimension;
	}

	/**	 Setter Parameter Value for Sales Region Accounting	*/
	protected void setIsUseSalesRegionDimension(boolean isUseSalesRegionDimension) {
		this.isUseSalesRegionDimension = isUseSalesRegionDimension;
	}

	/**	 Getter Parameter Value for Start Date	*/
	protected Timestamp getStartDate() {
		return startDate;
	}

	/**	 Setter Parameter Value for Start Date	*/
	protected void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**	 Getter Parameter Value for HistoryYears	*/
	protected int getHistoryYears() {
		return historyYears;
	}

	/**	 Setter Parameter Value for HistoryYears	*/
	protected void setHistoryYears(int historyYears) {
		this.historyYears = historyYears;
	}

	/**	 Getter Parameter Value for Region	*/
	protected int getRegionId() {
		return regionId;
	}

	/**	 Setter Parameter Value for Region	*/
	protected void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	/**	 Getter Parameter Value for City Name	*/
	protected String getCityName() {
		return cityName;
	}

	/**	 Setter Parameter Value for City Name	*/
	protected void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**	 Getter Parameter Value for City	*/
	protected int getCityId() {
		return cityId;
	}

	/**	 Setter Parameter Value for City	*/
	protected void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**	 Getter Parameter Value for ZIP	*/
	protected String getPostal() {
		return postal;
	}

	/**	 Setter Parameter Value for ZIP	*/
	protected void setPostal(String postal) {
		this.postal = postal;
	}

	/**	 Getter Parameter Value for Address 1	*/
	protected String getAddress1() {
		return address1;
	}

	/**	 Setter Parameter Value for Address 1	*/
	protected void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**	 Getter Parameter Value for Phone	*/
	protected String getPhone() {
		return phone;
	}

	/**	 Setter Parameter Value for Phone	*/
	protected void setPhone(String phone) {
		this.phone = phone;
	}

	/**	 Getter Parameter Value for 2nd Phone	*/
	protected String getPhone2() {
		return phone2;
	}

	/**	 Setter Parameter Value for 2nd Phone	*/
	protected void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**	 Getter Parameter Value for Fax	*/
	protected String getFax() {
		return fax;
	}

	/**	 Setter Parameter Value for Fax	*/
	protected void setFax(String fax) {
		this.fax = fax;
	}

	/**	 Getter Parameter Value for EMail Address	*/
	protected String getEMail() {
		return eMail;
	}

	/**	 Setter Parameter Value for EMail Address	*/
	protected void setEMail(String eMail) {
		this.eMail = eMail;
	}

	/**	 Getter Parameter Value for Tax ID	*/
	protected String getTaxID() {
		return taxID;
	}

	/**	 Setter Parameter Value for Tax ID	*/
	protected void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	/**	 Getter Parameter Value for D-U-N-S	*/
	protected String getDUNS() {
		return dUNS;
	}

	/**	 Setter Parameter Value for D-U-N-S	*/
	protected void setDUNS(String dUNS) {
		this.dUNS = dUNS;
	}

	/**	 Getter Parameter Value for Logo	*/
	protected String getLogo() {
		return logo;
	}

	/**	 Setter Parameter Value for Logo	*/
	protected void setLogo(String logo) {
		this.logo = logo;
	}

	/**	 Getter Parameter Value for Administrative User Name	*/
	protected String getAdminUserName() {
		return adminUserName;
	}

	/**	 Setter Parameter Value for Administrative User Name	*/
	protected void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	/**	 Getter Parameter Value for Normal User Name	*/
	protected String getNormalUserName() {
		return normalUserName;
	}

	/**	 Setter Parameter Value for Normal User Name	*/
	protected void setNormalUserName(String normalUserName) {
		this.normalUserName = normalUserName;
	}

	/**	 Getter Parameter Value for Bank Name	*/
	protected String getBankName() {
		return bankName;
	}

	/**	 Setter Parameter Value for Bank Name	*/
	protected void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**	 Getter Parameter Value for Routing No	*/
	protected String getRoutingNo() {
		return routingNo;
	}

	/**	 Setter Parameter Value for Routing No	*/
	protected void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	/**	 Getter Parameter Value for Account No	*/
	protected String getAccountNo() {
		return accountNo;
	}

	/**	 Setter Parameter Value for Account No	*/
	protected void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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