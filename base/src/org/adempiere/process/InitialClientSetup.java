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
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
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
public class InitialClientSetup extends SvrProcess
{
	
	// Process Parameters
	protected String p_ClientName = null;
	protected String p_OrgValue = null;
	protected String p_OrgName = null;
	protected String p_AdminUserName = null;
	protected String p_NormalUserName = null;
	protected int p_C_Currency_ID = 0;
	protected int p_C_Country_ID = 0;
	protected int p_C_Region_ID = 0;
	protected String p_CityName = null;
	protected String p_Postal = null;
	protected String p_Address1 = null;
	protected String p_Phone = null;
	protected String p_Phone2 = null;
	protected String p_Fax = null;
	protected String p_EMail = null;
	protected String p_TaxID = null;
	protected int p_C_City_ID = 0;
	protected boolean p_IsUseBPDimension = true;
	protected boolean p_IsUseProductDimension = true;
	protected boolean p_IsUseProjectDimension = false;
	protected boolean p_IsUseCampaignDimension = false;
	protected boolean p_IsUseSalesRegionDimension = false;
	protected String p_CoAFile = null;
	protected String p_logoFile = null;
	protected Timestamp p_startDate = null;
	protected int		p_historyYears = 0;
	protected String	p_DUNS = null;
	protected String	p_bankName = null;
	protected String	p_routingNo = null;
	protected String 	p_accountNo = null;

	/** WindowNo for this process */
	public static final int     WINDOW_THIS_PROCESS = 9999;

	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("ClientName"))
				p_ClientName = (String) para[i].getParameter();
			else if (name.equals("OrgValue"))
				p_OrgValue = (String) para[i].getParameter();
			else if (name.equals("OrgName"))
				p_OrgName = (String) para[i].getParameter();
			else if (name.equals("AdminUserName"))
				p_AdminUserName = (String) para[i].getParameter();
			else if (name.equals("NormalUserName"))
				p_NormalUserName = (String) para[i].getParameter();
			else if (name.equals("C_Currency_ID"))
				p_C_Currency_ID = para[i].getParameterAsInt();
			else if (name.equals("C_Country_ID"))
				p_C_Country_ID = para[i].getParameterAsInt();
			else if (name.equals("C_Region_ID"))
				p_C_Region_ID = para[i].getParameterAsInt();
			else if (name.equals("CityName"))
				p_CityName = (String) para[i].getParameter();
			else if (name.equals("C_City_ID"))
				p_C_City_ID = para[i].getParameterAsInt();
			else if (name.equals("Postal"))
				p_Postal = (String) para[i].getParameter();
			else if (name.equals("Address1"))
				p_Address1 = (String) para[i].getParameter();
			else if (name.equals("IsUseBPDimension"))
				p_IsUseBPDimension = para[i].getParameter().equals("Y");
			else if (name.equals("IsUseProductDimension"))
				p_IsUseProductDimension = para[i].getParameter().equals("Y");
			else if (name.equals("IsUseProjectDimension"))
				p_IsUseProjectDimension = para[i].getParameter().equals("Y");
			else if (name.equals("IsUseCampaignDimension"))
				p_IsUseCampaignDimension = para[i].getParameter().equals("Y");
			else if (name.equals("IsUseSalesRegionDimension"))
				p_IsUseSalesRegionDimension = para[i].getParameter().equals("Y");
			else if (name.equals("CoAFile"))
				p_CoAFile = (String) para[i].getParameter();
			else if (name.equals("Phone"))
				p_Phone = (String) para[i].getParameter();
			else if (name.equals("Phone2"))
				p_Phone2 = (String) para[i].getParameter();
			else if (name.equals("Fax"))
				p_Fax = (String) para[i].getParameter();
			else if (name.equals("EMail"))
				p_EMail = (String) para[i].getParameter();
			else if (name.equals("TaxID"))
				p_TaxID = (String) para[i].getParameter();
			else if (name.equals("Logo"))
				p_logoFile = (String) para[i].getParameter();
			else if (name.equals("StartDate"))
				p_startDate = (Timestamp) para[i].getParameter();
			else if (name.equals("HistoryYears"))
				p_historyYears = para[i].getParameterAsInt();
			else if (name.equals("DUNS"))
				p_DUNS = (String) para[i].getParameter();
			else if (name.equals("BankName"))
				p_bankName = (String) para[i].getParameter();
			else if (name.equals("RoutingNo"))
				p_routingNo = (String) para[i].getParameter();
			else if (name.equals("AccountNo"))
				p_accountNo = (String) para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("InitialClientSetup"
				+ ": ClientName=" + p_ClientName
				+ ", OrgValue=" + p_OrgValue
				+ ", OrgName=" + p_OrgName
				+ ", AdminUserName=" + p_AdminUserName
				+ ", NormalUserName=" + p_NormalUserName
				+ ", C_Currency_ID=" + p_C_Currency_ID
				+ ", C_Country_ID=" + p_C_Country_ID
				+ ", C_Region_ID=" + p_C_Region_ID
				+ ", CityName=" + p_CityName
				+ ", C_City_ID=" + p_C_City_ID
				+ ", IsUseBPDimension=" + p_IsUseBPDimension
				+ ", IsUseProductDimension=" + p_IsUseProductDimension
				+ ", IsUseProjectDimension=" + p_IsUseProjectDimension
				+ ", IsUseCampaignDimension=" + p_IsUseCampaignDimension
				+ ", IsUseSalesRegionDimension=" + p_IsUseSalesRegionDimension
				+ ", CoAFile=" + p_CoAFile
			);

		// Validations

		// Validate Mandatory parameters
		if (   p_ClientName == null || p_ClientName.length() == 0
			|| p_OrgName == null || p_OrgName.length() == 0
			|| p_C_Currency_ID <= 0
			|| p_C_Country_ID <= 0
			|| p_CoAFile == null || p_CoAFile.length() == 0
			)
			throw new IllegalArgumentException("Missing required parameters");

		// Validate Uniqueness of client and users name
		//	Unique Client Name
		if (DB.executeUpdate("UPDATE AD_Client SET CreatedBy=0 WHERE Name=?", new Object[] {p_ClientName}, false, null) != 0)
			throw new AdempiereException("@NotUnique@ " + p_ClientName);

		//	Unique User Names
		if (DB.executeUpdate("UPDATE AD_User SET CreatedBy=0 WHERE Name=?", new Object[] {p_AdminUserName}, false, null) != 0)
			throw new AdempiereException("@NotUnique@ " + p_AdminUserName);
		if (DB.executeUpdate("UPDATE AD_User SET CreatedBy=0 WHERE Name=?", new Object[] {p_NormalUserName}, false, null) != 0)
			throw new AdempiereException("@NotUnique@ " + p_NormalUserName);

		// City_ID overrides CityName if both used
		if (p_C_City_ID > 0) {
			MCity city = MCity.get(getCtx(), p_C_City_ID);
			if (! city.getName().equals(p_CityName)) {
				log.info("City name changed from " + p_CityName + " to " + city.getName());
				p_CityName = city.getName();
			}
		}

		// Validate existence and read permissions on CoA file
		File coaFile = new File(p_CoAFile);
		if (!coaFile.exists())
			throw new AdempiereException("CoaFile " + p_CoAFile + " does not exist");
		if (!coaFile.canRead())
			throw new AdempiereException("Cannot read CoaFile " + p_CoAFile);
		if (!coaFile.isFile())
			throw new AdempiereException("CoaFile " + p_CoAFile + " is not a file");
		if (coaFile.length() <= 0L)
			throw new AdempiereException("CoaFile " + p_CoAFile + " is empty");

		// Process
		MSetup ms = null;
		
		MCountry country = MCountry.get(getCtx(), p_C_Country_ID);
		try
		{
			Class<?> ppClass = Class.forName("org.compiere.model.MSetup_" + country.getCountryCode());
			if (ppClass != null)
				ms = (MSetup) ppClass.newInstance();
		}
		catch (Exception e)    //  NoClassDefFound
		{
			// ignore as country specific setup class may not exist
		}
			
		if ( ms == null )
			ms = new MSetup();

		ms.initialize(Env.getCtx(), WINDOW_THIS_PROCESS);

		if (! ms.createClient(p_ClientName, p_OrgValue, p_OrgName, p_AdminUserName, p_NormalUserName
				, p_Phone, p_Phone2, p_Fax, p_EMail, p_TaxID, p_DUNS, p_logoFile, p_C_Country_ID) ) {
			ms.rollback();
			throw new AdempiereException("Create client failed");
		}
			
		addLog(ms.getInfo());

		//  Generate Accounting
		MCurrency currency = MCurrency.get(getCtx(), p_C_Currency_ID);
		KeyNamePair currency_kp = new KeyNamePair(p_C_Currency_ID, currency.getDescription());
		if (!ms.createAccounting(currency_kp,
			p_IsUseProductDimension, p_IsUseBPDimension, p_IsUseProjectDimension,
			p_IsUseCampaignDimension, p_IsUseSalesRegionDimension, p_startDate, p_historyYears,
			coaFile)) {
			ms.rollback();
			throw new AdempiereException("@AccountSetupError@");
		}

		//  Generate Entities
		if (!ms.createEntities(p_C_Country_ID, p_CityName, p_C_Region_ID, p_C_Currency_ID, p_Postal, p_Address1)) {
			ms.rollback();
			throw new AdempiereException("@AccountSetupError@");
		}
		
		if ( !Util.isEmpty(p_bankName) && !Util.isEmpty(p_routingNo) && !Util.isEmpty(p_accountNo) )
		{
			ms.createBank(p_bankName, p_routingNo, p_accountNo, p_C_Currency_ID);
		}
		
		// load chart of accounts
		if ( !ms.importChart(coaFile) ){
			ms.rollback();
			throw new AdempiereException("@AccountSetupError@");
		}
		
		addLog(ms.getInfo());

		//	Create Print Documents
		PrintUtil.setupPrintForm(ms.getAD_Client_ID());
		
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
		
		//System.out.println("Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
		
		
	}

}
