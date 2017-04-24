/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz - globalqss                                           *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.globalqss.com)                                *
***********************************************************************/

package org.adempiere.process;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MCity;
import org.compiere.model.MCountry;
import org.compiere.model.MCurrency;
import org.compiere.model.MLanguage;
import org.compiere.model.MSetup;
import org.compiere.model.Query;
import org.compiere.print.PrintUtil;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

/**
 * 	Process to create a new client (tenant)
 *	
 *  @author Carlos Ruiz
 *    [ 2598506 ] FR - Implement Initial Client Setup
 */
public class InitialClientSetup extends InitialClientSetupAbstract
{
	/** WindowNo for this process */
	public static final int     WINDOW_THIS_PROCESS = 9999;

	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		super.prepare();
	}

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("InitialClientSetup"
				+ ": ClientName=" + getClientName()
				+ ", OrgValue=" + getOrgKey()
				+ ", OrgName=" + getOrganizationName()
				+ ", AdminUserName=" + getAdministrativeUserName()
				+ ", NormalUserName=" + getNormalUserName()
				+ ", C_Currency_ID=" + getCurrencyId()
				+ ", C_Country_ID=" + getCountryId()
				+ ", C_Region_ID=" + getRegionId()
				+ ", CityName=" + getCityName()
				+ ", C_City_ID=" + getCityId()
				+ ", IsUseBPDimension=" + isBPAccounting()
				+ ", IsUseProductDimension=" + isProductAccounting()
				+ ", IsUseProjectDimension=" + isProjectAccounting()
				+ ", IsUseCampaignDimension=" + isCampaignAccounting()
				+ ", IsUseSalesRegionDimension=" + isSalesRegionAccounting()
				+ ", ChartofAccountsFile=" + getChartofAccountsFile()
			);
		// Validations
		// Validate Mandatory parameters
		if (   getClientName() == null || getClientName().length() == 0
			|| getOrganizationName() == null || getOrganizationName().length() == 0
			|| getCurrencyId() <= 0
			|| getCountryId() <= 0
			|| getChartofAccountsFile() == null || getChartofAccountsFile().length() == 0
			)
			throw new IllegalArgumentException("Missing required parameters");

		// Validate Uniqueness of client and users name
		//	Unique Client Name
		if (DB.executeUpdate("UPDATE AD_Client SET CreatedBy=0 WHERE Name=?", new Object[] {getClientName()}, false, null) != 0)
			throw new AdempiereException("@NotUnique@ " + getClientName());

		//	Unique User Names
		if (DB.executeUpdate("UPDATE AD_User SET CreatedBy=0 WHERE Name=?", new Object[] {getAdministrativeUserName()}, false, null) != 0)
			throw new AdempiereException("@NotUnique@ " + getAdministrativeUserName());
		if (DB.executeUpdate("UPDATE AD_User SET CreatedBy=0 WHERE Name=?", new Object[] {getNormalUserName()}, false, null) != 0)
			throw new AdempiereException("@NotUnique@ " + getNormalUserName());

		// City_ID overrides CityName if both used
		if (getCityId() > 0) {
			MCity city = MCity.get(getCtx(), getCityId());
			if (!city.getName().equals(getCityName())) {
				log.info("City name changed from " + getCityName() + " to " + city.getName());
				setCityName(city.getName());
			}
		}

		// Validate existence and read permissions on CoA file
		File chartofAccountsFile = new File(getChartofAccountsFile());
		if (!chartofAccountsFile.exists())
			throw new AdempiereException("CoaFile " + getChartofAccountsFile() + " does not exist");
		if (!chartofAccountsFile.canRead())
			throw new AdempiereException("Cannot read CoaFile " + getChartofAccountsFile());
		if (!chartofAccountsFile.isFile())
			throw new AdempiereException("CoaFile " + getChartofAccountsFile() + " is not a file");
		if (chartofAccountsFile.length() <= 0L)
			throw new AdempiereException("CoaFile " + getChartofAccountsFile() + " is empty");

		// Process
		MSetup setup = null;
		
		MCountry country = MCountry.get(getCtx(), getCountryId());
		try
		{
			Class<?> ppClass = Class.forName("org.compiere.model.MSetup_" + country.getCountryCode());
			if (ppClass != null)
				setup = (MSetup) ppClass.newInstance();
		}
		catch (Exception e)    //  NoClassDefFound
		{
			// ignore as country specific setup class may not exist
		}
			
		if ( setup == null )
			setup = new MSetup();

		setup.initialize(Env.getCtx(), WINDOW_THIS_PROCESS);
		if (! setup.createClient(getClientName(), getOrgKey(), getOrganizationName(), getAdministrativeUserName(), getNormalUserName()
				, getPhone(), getPhone2(), getFax(), getEMailAddress(), getTaxID(), getDUNS(), getLogo(), getCountryId()) ) {
			setup.rollback();
			throw new AdempiereException("Create client failed");
		}
		addLog(setup.getInfo());
		//  Generate Accounting
		MCurrency currency = MCurrency.get(getCtx(), getCurrencyId());
		KeyNamePair currencyKeyNamePair = new KeyNamePair(getCurrencyId(), currency.getDescription());
		if (!setup.createAccounting(currencyKeyNamePair,
				isProductAccounting() , isBPAccounting() , isProjectAccounting(),
			isCampaignAccounting(), isSalesRegionAccounting(), getStartDate(), getHistoryYears(),
			chartofAccountsFile)) {
			setup.rollback();
			throw new AdempiereException("@AccountSetupError@");
		}
		//  Generate Entities
		if (!setup.createEntities(getCountryId(), getCityName(), getRegionId(), getCurrencyId(), getZIP(), getAddress1())) {
			setup.rollback();
			throw new AdempiereException("@AccountSetupError@");
		}
		// Create Bank
		if ( !Util.isEmpty(getBankName()) && !Util.isEmpty(getRoutingNo()) && !Util.isEmpty(getAccountNo()))
		{
			setup.createBank(getBankName(), getRoutingNo(), getAccountNo(), getCurrencyId());
		}
		// load chart of accounts
		if ( !setup.importChart(chartofAccountsFile) ){
			setup.rollback();
			throw new AdempiereException("@AccountSetupError@");
		}
		addLog(setup.getInfo());
		//	Create Print Documents
		PrintUtil.setupPrintForm(setup.getAD_Client_ID());
       // Update translation after create a new tenant
		String whereClause   = MLanguage.COLUMNNAME_IsSystemLanguage+"='Y' AND "+ MLanguage.COLUMNNAME_IsActive+"='Y'"; //Adempiere-53 Changes
		List<MLanguage> list = new Query(Env.getCtx(), MLanguage.Table_Name, whereClause, get_TrxName()).list();
		for (MLanguage lang : list)
		{
			log.fine ("Updating Translation - " + lang);
			lang.maintain(true);
		}	//	for

		return "@OK@";
	}
	
	
	public static void main(String[] args) {
		
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.CONFIG);
		String propFileName = Adempiere.getAdempiereHome() + File.separatorChar + "clientsetup.properties";
		if (args.length > 0)
			propFileName = args[0];

		ProcessInfo pi = new ProcessInfo("Initial Client Setup", 53161);
		pi.setAD_Client_ID(0);
		pi.setAD_User_ID(100);
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(propFileName));
		
			pi.addParameter("ClientName", prop.getProperty("ClientName"), prop.getProperty("ClientName"));
			pi.addParameter("OrgValue", prop.getProperty("OrgValue"), prop.getProperty("OrgValue"));
			pi.addParameter("OrgName", prop.getProperty("OrgName"), prop.getProperty("OrgName"));
			pi.addParameter("AdminUserName", prop.getProperty("AdminUserName"), prop.getProperty("AdminUserName"));
			pi.addParameter("NormalUserName", prop.getProperty("NormalUserName"), prop.getProperty("NormalUserName"));
			if (prop.getProperty("CurrencyCode") !=null)
			{
				MCurrency currency = MCurrency.get(Env.getCtx(), prop.getProperty("CurrencyCode"));
				if (currency != null)
					pi.addParameter("C_Currency_ID", currency.getC_Currency_ID(), currency.getISO_Code());
			}
			
			if (prop.getProperty("CountryCode") !=null)
			{
				MCountry country = MCountry.get(Env.getCtx(), prop.getProperty("CountryCode"));
				if (country != null)
					pi.addParameter("C_Country_ID", country.getC_Country_ID(), country.getCountryCode());
			}
			
			pi.addParameter("C_Region_ID", prop.getProperty("C_Region_ID"), prop.getProperty("C_Region_ID"));
			pi.addParameter("CityName", prop.getProperty("CityName"), prop.getProperty("CityName"));
			pi.addParameter("C_City_ID", prop.getProperty("C_City_ID"), prop.getProperty("C_City_ID"));
			pi.addParameter("Postal", prop.getProperty("Postal"), prop.getProperty("Postal"));
			pi.addParameter("Address1", prop.getProperty("Address1"), prop.getProperty("Address1"));
			pi.addParameter("IsUseBPDimension", prop.getProperty("IsUseBPDimension"), prop.getProperty("IsUseBPDimension"));
			pi.addParameter("IsUseProductDimension", prop.getProperty("IsUseProductDimension"), prop.getProperty("IsUseProductDimension"));
			pi.addParameter("IsUseProjectDimension", prop.getProperty("IsUseProjectDimension"), prop.getProperty("IsUseProjectDimension"));
			pi.addParameter("IsUseCampaignDimension", prop.getProperty("IsUseCampaignDimension"), prop.getProperty("IsUseCampaignDimension"));
			pi.addParameter("IsUseSalesRegionDimension", prop.getProperty("IsUseSalesRegionDimension"), prop.getProperty("IsUseSalesRegionDimension"));
			pi.addParameter("CoAFile", prop.getProperty("CoAFile"), prop.getProperty("CoAFile"));
			pi.addParameter("Phone", prop.getProperty("Phone"), prop.getProperty("Phone"));
			pi.addParameter("Phone2", prop.getProperty("Phone2"), prop.getProperty("Phone2"));
			pi.addParameter("Fax", prop.getProperty("Fax"), prop.getProperty("Fax"));
			pi.addParameter("EMail", prop.getProperty("EMail"), prop.getProperty("EMail"));
			pi.addParameter("TaxID", prop.getProperty("TaxID"), prop.getProperty("TaxID"));
			pi.addParameter("Logo", prop.getProperty("Logo"), prop.getProperty("Logo"));
			pi.addParameter("StartDate", prop.getProperty("StartDate"), prop.getProperty("StartDate"));
			pi.addParameter("HistoryYears", prop.getProperty("HistoryYears"), prop.getProperty("HistoryYears"));
			pi.addParameter("DUNS", prop.getProperty("DUNS"), prop.getProperty("DUNS"));
			pi.addParameter("BankName", prop.getProperty("BankName"), prop.getProperty("BankName"));
			pi.addParameter("RoutingNo", prop.getProperty("RoutingNo"), prop.getProperty("RoutingNo"));
			pi.addParameter("AccountNo", prop.getProperty("AccountNo"), prop.getProperty("AccountNo"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		InitialClientSetup setup = new InitialClientSetup();
		setup.startProcess(Env.getCtx(), pi, null);
		
		
	}

}
