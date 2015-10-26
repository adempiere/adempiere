/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.ImageIcon;

import org.compiere.impexp.ImpFormat;
import org.compiere.process.DocumentTypeVerify;
import org.compiere.process.ProcessInfo;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 * Initial Setup Model
 *
 * @author Jorg Janke
 * @version $Id: MSetup.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1795384 ] Setup: create default accounts records is too rigid
 * @author Carlos Ruiz - globalqss
 * 			<li>Setup correctly IsSOTrx for return documents
 */
public class MSetup
{
	/**
	 * No-arg constructor call initialize
	 * 
	 */
	public MSetup() {
	}
	/**
	 *  Constructor
	 *  @param ctx context
	 *  @param WindowNo window
	 */
	public MSetup(Properties ctx, int WindowNo)
	{
		initialize(ctx, WindowNo);
	}   //  MSetup



	public void initialize(Properties ctx, int WindowNo) {
		m_ctx = new Properties(ctx);	//	copy
		m_lang = Env.getAD_Language(m_ctx);
		m_WindowNo = WindowNo;
	}

	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());

	protected Trx				m_trx = Trx.get(Trx.createTrxName("Setup"), true);
	protected Properties      m_ctx;
	protected String          m_lang;
	protected int             m_WindowNo;
	protected StringBuffer    m_info;
	//
	protected String          m_clientName;
//	private String          m_orgName;
	//
	protected String          m_stdColumns = "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy";
	protected String          m_stdValues;
	protected String          m_stdValuesOrg;
	//
	protected NaturalAccountMap<String,MElementValue> m_nap = null;
	//
	protected MClient			m_client;
	protected MOrg			m_org;
	protected MAcctSchema		m_as;
	//
	protected int     		AD_User_ID;
	protected String  		AD_User_Name;
	protected int     		AD_User_U_ID;
	protected String  		AD_User_U_Name;
	protected MCalendar		m_calendar;
	protected int     		m_AD_Tree_Account_ID;
	protected int     		C_Cycle_ID;
	private int 			C_Element_ID;
	//
	protected boolean         m_hasProject = false;
	protected boolean         m_hasMCampaign = false;
	protected boolean         m_hasSRegion = false;

	/**
	 *  Create Client Info.
	 *  - Client, Trees, Org, Role, User, User_Role
	 *  @param clientName client name
	 *  @param orgName org name
	 *  @param userClient user id client
	 *  @param userOrg user id org
	 * @param logoFile 
	 *  @return true if created
	 */
	public boolean createClient (String clientName, String orgValue, String orgName,
		String userClient, String userOrg, String phone, String phone2, String fax, String eMail, String taxID,
		String DUNS, String logoFile, int Country_ID)
	{
		log.info(clientName);
		m_trx.start();
		
		//  info header
		m_info = new StringBuffer();
		//  Standard columns
		String name = null;
		String sql = null;
		int no = 0;

		/**
		 *  Create Client
		 */
		name = clientName;
		if (name == null || name.length() == 0)
			name = "newClient";
		m_clientName = name;
		m_client = new MClient(m_ctx, 0, true, m_trx.getTrxName());
		m_client.setValue(m_clientName);
		m_client.setName(m_clientName);
		m_client.setIsUseBetaFunctions(false);
        m_client.setIsCostImmediate(true);
		m_client.setAutoArchive(MClient.AUTOARCHIVE_ExternalDocuments);
		
		MCountry country = MCountry.get(m_ctx, Country_ID);
		if ( country.getAD_Language() != null )
			m_client.setAD_Language(country.getAD_Language());
		
		if (!m_client.save())
		{
			String err = "Client NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}


		
		int AD_Client_ID = m_client.getAD_Client_ID();
		Env.setContext(m_ctx, m_WindowNo, "AD_Client_ID", AD_Client_ID);
		Env.setContext(m_ctx, "#AD_Client_ID", AD_Client_ID);

		//	Standard Values
		m_stdValues = String.valueOf(AD_Client_ID) + ",0,'Y',SysDate,0,SysDate,0";
		//  Info - Client
		m_info.append(Msg.translate(m_lang, "AD_Client_ID")).append("=").append(name).append("\n");

		//	Setup Sequences
		if (!MSequence.checkClientSequences (m_ctx, AD_Client_ID, m_trx.getTrxName()))
		{
			String err = "Sequences NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		
		//  Trees and Client Info
		if (!m_client.setupClientInfo(m_lang))
		{
			String err = "Client Info NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		m_AD_Tree_Account_ID = m_client.getSetup_AD_Tree_Account_ID();

		/**
		 *  Create Org
		 */
		name = orgName;
		if (name == null || name.length() == 0)
			name = "newOrg";
		if (orgValue == null || orgValue.length() == 0)
			orgValue = name;
		m_org = new MOrg (m_client, orgValue, name);
		if (!m_org.save())
		{
			String err = "Organization NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		Env.setContext(m_ctx, m_WindowNo, "AD_Org_ID", getAD_Org_ID());
		Env.setContext(m_ctx, "#AD_Org_ID", getAD_Org_ID());
		m_stdValuesOrg = AD_Client_ID + "," + getAD_Org_ID() + ",'Y',SysDate,0,SysDate,0";
		//  Info
		m_info.append(Msg.translate(m_lang, "AD_Org_ID")).append("=").append(name).append("\n");
		
		// Set Organization Phone, Phone2, Fax, EMail
		MOrgInfo orgInfo = MOrgInfo.get(m_ctx, getAD_Org_ID(), m_trx.getTrxName());
		orgInfo.setPhone(phone);
		orgInfo.setPhone2(phone2);
		orgInfo.setFax(fax);
		orgInfo.setEMail(eMail);
		
		
		if (taxID != null && taxID.length() > 0) {
			orgInfo.setTaxID(taxID);
		}
		
		if (!Util.isEmpty(DUNS))
			orgInfo.setDUNS(DUNS);
		
		if ( !Util.isEmpty(logoFile) )
		{
			byte[] data = null;
			File file = new File (logoFile);

			//  See if we can load & display it
			try
			{
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024*8];   //  8kB
				int length = -1;
				while ((length = fis.read(buffer)) != -1)
					os.write(buffer, 0, length);
				fis.close();
				data = os.toByteArray();
				os.close();

				MImage logo = new MImage(m_ctx, 0, m_trx.getTrxName());

				//  Save info
				logo.setName(file.getName());
				logo.setImageURL(file.getPath());
				logo.setBinaryData(data);
				logo.saveEx();

				MClientInfo clientInfo = m_client.getInfo();
				if ( clientInfo != null )
				{
					clientInfo.setLogo_ID(logo.getAD_Image_ID());
					clientInfo.saveEx(m_trx.getTrxName());
				}
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "Failed to load logo image", e);
			}
		}
		
		if (!orgInfo.save())
		{
			String err = "Organization Info NOT Updated";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		
		/**
		 *  Create Roles
		 *  - Admin
		 *  - User
		 */
		name = m_clientName + " Admin";
		MRole admin = new MRole(m_ctx, 0, m_trx.getTrxName());
		admin.setClientOrg(m_client);
		admin.setName(name);
		admin.setUserLevel(MRole.USERLEVEL_ClientPlusOrganization);
		admin.setPreferenceType(MRole.PREFERENCETYPE_Client);
		admin.setIsShowAcct(true);
		if (!admin.save())
		{
			String err = "Admin Role A NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//	OrgAccess x, 0
		MRoleOrgAccess adminClientAccess = new MRoleOrgAccess (admin, 0);
		if (!adminClientAccess.save())
			log.log(Level.SEVERE, "Admin Role_OrgAccess 0 NOT created");
		//  OrgAccess x,y
		MRoleOrgAccess adminOrgAccess = new MRoleOrgAccess (admin, m_org.getAD_Org_ID());
		if (!adminOrgAccess.save())
			log.log(Level.SEVERE, "Admin Role_OrgAccess NOT created");
		
		//  Info - Admin Role
		m_info.append(Msg.translate(m_lang, "AD_Role_ID")).append("=").append(name).append("\n");

		//
		name = m_clientName + " User";
		MRole user = new MRole (m_ctx, 0, m_trx.getTrxName());
		user.setClientOrg(m_client);
		user.setName(name);
		if (!user.save())
		{
			String err = "User Role A NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  OrgAccess x,y
		MRoleOrgAccess userOrgAccess = new MRoleOrgAccess (user, m_org.getAD_Org_ID());
		if (!userOrgAccess.save())
			log.log(Level.SEVERE, "User Role_OrgAccess NOT created");
		
		//  Info - Client Role
		m_info.append(Msg.translate(m_lang, "AD_Role_ID")).append("=").append(name).append("\n");

		/**
		 *  Create Users
		 *  - Client
		 *  - Org
		 */
		name = userClient;
		if (name == null || name.length() == 0)
			name = m_clientName + "Client";
		AD_User_ID = getNextID(AD_Client_ID, "AD_User");
		AD_User_Name = name;
		name = DB.TO_STRING(name);
		sql = "INSERT INTO AD_User(" + m_stdColumns + ",AD_User_ID,"
			+ "Name,Description,Password)"
			+ " VALUES (" + m_stdValues + "," + AD_User_ID + ","
			+ name + "," + name + "," + name + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
		{
			String err = "Admin User NOT inserted - " + AD_User_Name;
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "AD_User_ID")).append("=").append(AD_User_Name).append("/").append(AD_User_Name).append("\n");

		name = userOrg;
		if (name == null || name.length() == 0)
			name = m_clientName + "Org";
		AD_User_U_ID = getNextID(AD_Client_ID, "AD_User");
		AD_User_U_Name = name;
		name = DB.TO_STRING(name);
		sql = "INSERT INTO AD_User(" + m_stdColumns + ",AD_User_ID,"
			+ "Name,Description,Password)"
			+ " VALUES (" + m_stdValues + "," + AD_User_U_ID + ","
			+ name + "," + name + "," + name + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
		{
			String err = "Org User NOT inserted - " + AD_User_U_Name;
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "AD_User_ID")).append("=").append(AD_User_U_Name).append("/").append(AD_User_U_Name).append("\n");

		/**
		 *  Create User-Role
		 */
		//  ClientUser          - Admin & User
		sql = "INSERT INTO AD_User_Roles(" + m_stdColumns + ",AD_User_ID,AD_Role_ID)"
			+ " VALUES (" + m_stdValues + "," + AD_User_ID + "," + admin.getAD_Role_ID() + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "UserRole ClientUser+Admin NOT inserted");
		sql = "INSERT INTO AD_User_Roles(" + m_stdColumns + ",AD_User_ID,AD_Role_ID)"
			+ " VALUES (" + m_stdValues + "," + AD_User_ID + "," + user.getAD_Role_ID() + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "UserRole ClientUser+User NOT inserted");
		//  OrgUser             - User
		sql = "INSERT INTO AD_User_Roles(" + m_stdColumns + ",AD_User_ID,AD_Role_ID)"
			+ " VALUES (" + m_stdValues + "," + AD_User_U_ID + "," + user.getAD_Role_ID() + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "UserRole OrgUser+Org NOT inserted");

		//	Processors
		MAcctProcessor ap = new MAcctProcessor(m_client, AD_User_ID);
		ap.saveEx();
		
		MRequestProcessor rp = new MRequestProcessor (m_client, AD_User_ID);
		rp.saveEx();
		
		log.info("fini");
		return true;
	}   //  createClient



	/**************************************************************************
	 *  Create Accounting elements.
	 *  - Calendar
	 *  - Account Trees
	 *  - Account Values
	 *  - Accounting Schema
	 *  - Default Accounts
	 *
	 *  @param currency currency
	 *  @param hasProduct has product segment
	 *  @param hasBPartner has bp segment
	 *  @param hasProject has project segment
	 *  @param hasMCampaign has campaign segment
	 *  @param hasSRegion has sales region segment
	 * @param historyYears 
	 * @param startDate 
	 *  @param AccountingFile file name of accounting file
	 *  @return true if created
	 */
	public boolean createAccounting(KeyNamePair currency,
		boolean hasProduct, boolean hasBPartner, boolean hasProject,
		boolean hasMCampaign, boolean hasSRegion,
		Timestamp startDate, int historyYears, File AccountingFile)
	{
		log.info(m_client.toString());
		//
		m_hasProject = hasProject;
		m_hasMCampaign = hasMCampaign;
		m_hasSRegion = hasSRegion;

		//  Standard variables
		m_info = new StringBuffer();
		String name = null;
		StringBuffer sqlCmd = null;
		int no = 0;

		if (!createCalendar(startDate, historyYears))
			return false;

		//	Create Account Elements
		name = m_clientName + " " + Msg.translate(m_lang, "Account_ID");
		MElement element = new MElement (m_client, name, 
			MElement.ELEMENTTYPE_Account, m_AD_Tree_Account_ID);
		if (!element.save())
		{
			String err = "Acct Element NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		C_Element_ID = element.getC_Element_ID();
		m_info.append(Msg.translate(m_lang, "C_Element_ID")).append("=").append(name).append("\n");

		//	Create Account Values
		m_nap = new NaturalAccountMap<String,MElementValue>(m_ctx, m_trx.getTrxName());
		String errMsg = m_nap.parseFile(AccountingFile);
		if (errMsg.length() != 0)
		{
			log.log(Level.SEVERE, errMsg);
			m_info.append(errMsg);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		if (m_nap.saveAccounts(getAD_Client_ID(), getAD_Org_ID(), C_Element_ID))
			m_info.append(Msg.translate(m_lang, "C_ElementValue_ID")).append(" # ").append(m_nap.size()).append("\n");
		else
		{
			String err = "Acct Element Values NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		int C_ElementValue_ID = m_nap.getC_ElementValue_ID("DEFAULT_ACCT");
		log.fine("C_ElementValue_ID=" + C_ElementValue_ID);

		/**
		 *  Create AccountingSchema
		 */
		m_as = new MAcctSchema (m_client, currency);
		if (!m_as.save())
		{
			String err = "AcctSchema NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "C_AcctSchema_ID")).append("=").append(m_as.getName()).append("\n");

		/**
		 *  Create AccountingSchema Elements (Structure)
		 */
		String sql2 = null;
		if (Env.isBaseLanguage(m_lang, "AD_Reference"))	//	Get ElementTypes & Name
			sql2 = "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=181";
		else
			sql2 = "SELECT l.Value, t.Name FROM AD_Ref_List l, AD_Ref_List_Trl t "
				+ "WHERE l.AD_Reference_ID=181 AND l.AD_Ref_List_ID=t.AD_Ref_List_ID"
				+ " AND t.AD_Language=" + DB.TO_STRING(m_lang); //bug [ 1638421 ]
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			int AD_Client_ID = m_client.getAD_Client_ID();
			stmt = DB.prepareStatement(sql2, m_trx.getTrxName());
			rs = stmt.executeQuery();
			while (rs.next())
			{
				String ElementType = rs.getString(1);
				name = rs.getString(2);
				//
				String IsMandatory = null;
				String IsBalanced = "N";
				int SeqNo = 0;
				int C_AcctSchema_Element_ID = 0;

				if (ElementType.equals("OO"))
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "Y";
					IsBalanced = "Y";
					SeqNo = 10;
				}
				else if (ElementType.equals("AC"))
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "Y";
					SeqNo = 20;
				}
				else if (ElementType.equals("PR") && hasProduct)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "N";
					SeqNo = 30;
				}
				else if (ElementType.equals("BP") && hasBPartner)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "N";
					SeqNo = 40;
				}
				else if (ElementType.equals("PJ") && hasProject)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "N";
					SeqNo = 50;
				}
				else if (ElementType.equals("MC") && hasMCampaign)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "N";
					SeqNo = 60;
				}
				else if (ElementType.equals("SR") && hasSRegion)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					IsMandatory = "N";
					SeqNo = 70;
				}
				//	Not OT, LF, LT, U1, U2, AY

				if (IsMandatory != null)
				{
					sqlCmd = new StringBuffer ("INSERT INTO C_AcctSchema_Element(");
					sqlCmd.append(m_stdColumns).append(",C_AcctSchema_Element_ID,C_AcctSchema_ID,")
						.append("ElementType,Name,SeqNo,IsMandatory,IsBalanced) VALUES (");
					sqlCmd.append(m_stdValues).append(",").append(C_AcctSchema_Element_ID).append(",").append(m_as.getC_AcctSchema_ID()).append(",")
						.append("'").append(ElementType).append("','").append(name).append("',").append(SeqNo).append(",'")
						.append(IsMandatory).append("','").append(IsBalanced).append("')");
					no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
					if (no == 1)
						m_info.append(Msg.translate(m_lang, "C_AcctSchema_Element_ID")).append("=").append(name).append("\n");

					/** Default value for mandatory elements: OO and AC */
					if (ElementType.equals("OO"))
					{
						sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET Org_ID=");
						sqlCmd.append(getAD_Org_ID()).append(" WHERE C_AcctSchema_Element_ID=").append(C_AcctSchema_Element_ID);
						no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
						if (no != 1)
							log.log(Level.SEVERE, "Default Org in AcctSchamaElement NOT updated");
					}
					if (ElementType.equals("AC"))
					{
						sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET C_ElementValue_ID=");
						sqlCmd.append(C_ElementValue_ID).append(", C_Element_ID=").append(C_Element_ID);
						sqlCmd.append(" WHERE C_AcctSchema_Element_ID=").append(C_AcctSchema_Element_ID);
						no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
						if (no != 1)
							log.log(Level.SEVERE, "Default Account in AcctSchamaElement NOT updated");
					}
				}
			}
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "Elements", e1);
			m_info.append(e1.getMessage());
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}
		//  Create AcctSchema


		//  Create Defaults Accounts
		try {
			createAccountingRecord(X_C_AcctSchema_GL.Table_Name);
			createAccountingRecord(X_C_AcctSchema_Default.Table_Name);
		}
		catch (Exception e) {
			String err = e.getLocalizedMessage();
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		//  GL Categories
		createGLCategory("Standard", MGLCategory.CATEGORYTYPE_Manual, true);
		int GL_None = createGLCategory("None", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_GL = createGLCategory("Manual", MGLCategory.CATEGORYTYPE_Manual, false);
		int GL_ARI = createGLCategory("AR Invoice", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_ARR = createGLCategory("AR Receipt", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_MM = createGLCategory("Material Management", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_API = createGLCategory("AP Invoice", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_APP = createGLCategory("AP Payment", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_CASH = createGLCategory("Cash/Payments", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_Manufacturing = createGLCategory("Manufacturing", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_Distribution = createGLCategory("Distribution", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_Payroll = createGLCategory("Payroll", MGLCategory.CATEGORYTYPE_Document, false);

		//	Base DocumentTypes
		int ii = createDocType("GL Journal", Msg.getElement(m_ctx, "GL_Journal_ID"), 
			MDocType.DOCBASETYPE_GLJournal, null, 0, 0, 1000, GL_GL, false);
		if (ii == 0)
		{
			String err = "Document Type not created";
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		createDocType("GL Journal Batch", Msg.getElement(m_ctx, "GL_JournalBatch_ID"), 
			MDocType.DOCBASETYPE_GLJournal, null, 0, 0, 100, GL_GL, false);
		//	MDocType.DOCBASETYPE_GLDocument
		//
		int DT_I = createDocType("AR Invoice", Msg.getElement(m_ctx, "C_Invoice_ID", true), 
			MDocType.DOCBASETYPE_ARInvoice, null, 0, 0, 100000, GL_ARI, false);
		int DT_II = createDocType("AR Invoice Indirect", Msg.getElement(m_ctx, "C_Invoice_ID", true), 
			MDocType.DOCBASETYPE_ARInvoice, null, 0, 0, 150000, GL_ARI, false);
		int DT_IC = createDocType("AR Credit Memo", Msg.getMsg(m_ctx, "CreditMemo"), 
			MDocType.DOCBASETYPE_ARCreditMemo, null, 0, 0, 170000, GL_ARI, false);
		//	MDocType.DOCBASETYPE_ARProFormaInvoice
		
		createDocType("AP Invoice", Msg.getElement(m_ctx, "C_Invoice_ID", false), 
			MDocType.DOCBASETYPE_APInvoice, null, 0, 0, 0, GL_API, false);
		int DT_IPC = createDocType("AP CreditMemo", Msg.getMsg(m_ctx, "CreditMemo"), 
			MDocType.DOCBASETYPE_APCreditMemo, null, 0, 0, 0, GL_API, false);
		createDocType("Match Invoice", Msg.getElement(m_ctx, "M_MatchInv_ID", false), 
			MDocType.DOCBASETYPE_MatchInvoice, null, 0, 0, 390000, GL_API, false);
		
		createDocType("AR Receipt", Msg.getElement(m_ctx, "C_Payment_ID", true), 
			MDocType.DOCBASETYPE_ARReceipt, null, 0, 0, 0, GL_ARR, false);
		createDocType("AP Payment", Msg.getElement(m_ctx, "C_Payment_ID", false), 
			MDocType.DOCBASETYPE_APPayment, null, 0, 0, 0, GL_APP, false);
		createDocType("Allocation", "Allocation", 
			MDocType.DOCBASETYPE_PaymentAllocation, null, 0, 0, 490000, GL_CASH, false);

		int DT_S  = createDocType("MM Shipment", "Delivery Note", 
			MDocType.DOCBASETYPE_MaterialDelivery, null, 0, 0, 500000, GL_MM, false);
		int DT_SI = createDocType("MM Shipment Indirect", "Delivery Note", 
			MDocType.DOCBASETYPE_MaterialDelivery, null, 0, 0, 550000, GL_MM, false);
		int DT_VRM = createDocType("MM Vendor Return", "Vendor Return", 
	            MDocType.DOCBASETYPE_MaterialDelivery, null, 0, 0, 590000, GL_MM, true);
		
		createDocType("MM Receipt", "Vendor Delivery", 
			MDocType.DOCBASETYPE_MaterialReceipt, null, 0, 0, 0, GL_MM, false);
		int DT_RM = createDocType("MM Customer Return", "Customer Return", 
			MDocType.DOCBASETYPE_MaterialReceipt, null, 0, 0, 570000, GL_MM, true);
		
		createDocType("Purchase Order", Msg.getElement(m_ctx, "C_Order_ID", false), 
			MDocType.DOCBASETYPE_PurchaseOrder, null, 0, 0, 800000, GL_None, false);
		createDocType("Match PO", Msg.getElement(m_ctx, "M_MatchPO_ID", false), 
			MDocType.DOCBASETYPE_MatchPO, null, 0, 0, 890000, GL_None, false);
		createDocType("Purchase Requisition", Msg.getElement(m_ctx, "M_Requisition_ID", false), 
			MDocType.DOCBASETYPE_PurchaseRequisition, null, 0, 0, 900000, GL_None, false);
		createDocType("Vendor Return Material", "Vendor Return Material Authorization",
		    MDocType.DOCBASETYPE_PurchaseOrder, MDocType.DOCSUBTYPESO_ReturnMaterial, DT_VRM, 
		    DT_IPC, 990000, GL_MM, false);
		        
		createDocType("Bank Statement", Msg.getElement(m_ctx, "C_BankStatemet_ID", true), 
			MDocType.DOCBASETYPE_BankStatement, null, 0, 0, 700000, GL_CASH, false);
		createDocType("Cash Journal", Msg.getElement(m_ctx, "C_Cash_ID", true),
			MDocType.DOCBASETYPE_CashJournal, null, 0, 0, 750000, GL_CASH, false);
		
		createDocType("Material Movement", Msg.getElement(m_ctx, "M_Movement_ID", false),
			MDocType.DOCBASETYPE_MaterialMovement, null, 0, 0, 610000, GL_MM, false);
		createDocType("Physical Inventory", Msg.getElement(m_ctx, "M_Inventory_ID", false), 
			MDocType.DOCBASETYPE_MaterialPhysicalInventory, null, 0, 0, 620000, GL_MM, false);
		createDocType("Material Production", Msg.getElement(m_ctx, "M_Production_ID", false), 
			MDocType.DOCBASETYPE_MaterialProduction, null, 0, 0, 630000, GL_MM, false);
		createDocType("Project Issue", Msg.getElement(m_ctx, "C_ProjectIssue_ID", false), 
			MDocType.DOCBASETYPE_ProjectIssue, null, 0, 0, 640000, GL_MM, false);

		//  Order Entry
		createDocType("Binding offer", "Quotation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_Quotation, 
			0, 0, 10000, GL_None, false);
		createDocType("Non binding offer", "Proposal", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_Proposal, 
			0, 0, 20000, GL_None, false);
		createDocType("Prepay Order", "Prepay Order", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_PrepayOrder, 
			DT_S, DT_I, 30000, GL_None, false);
		createDocType("Customer Return Material", "Customer Return Material Authorization", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_ReturnMaterial, 
			DT_RM, DT_IC, 30000, GL_None, false);
		createDocType("Standard Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_StandardOrder, 
			DT_S, DT_I, 50000, GL_None, false);
		createDocType("Credit Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_OnCreditOrder, 
			DT_S, DT_I, 60000, GL_None, false);   //  RE
		createDocType("Warehouse Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_WarehouseOrder, 
			DT_S, DT_I,	70000, GL_None, false);    //  LS
		
		//Manufacturing Document
		createDocType("Manufacturing Order", "Manufacturing Order", 
			MDocType.DOCBASETYPE_ManufacturingOrder, null,
			0, 0, 80000, GL_Manufacturing, false);
		createDocType("Manufacturing Cost Collector","Cost Collector", 
			MDocType.DOCBASETYPE_ManufacturingCostCollector, null, 
			0, 0, 81000, GL_Manufacturing, false);
		createDocType("Maintenance Order","Maintenance Order",
			MDocType.DOCBASETYPE_MaintenanceOrder, null,
			0, 0, 86000, GL_Manufacturing, false);
		createDocType("Quality Order","Quality Order",
				MDocType.DOCBASETYPE_QualityOrder, null,
			0, 0, 87000, GL_Manufacturing, false);
		createDocType("Distribution Order","Distribution Order", 
			MDocType.DOCBASETYPE_DistributionOrder, null,
			0, 0, 88000, GL_Distribution, false);
		//Payroll
		createDocType("Payroll","Payroll", 
			MDocType.DOCBASETYPE_Payroll, null,
			0, 0, 90000, GL_Payroll, false);

		int DT = createDocType("POS Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_POSOrder, 
			DT_S, DT_I, 80000, GL_None, false);    // Bar
		//	POS As Default for window SO
		createPreference("C_DocTypeTarget_ID", String.valueOf(DT), 143);

		//  Update ClientInfo
		sqlCmd = new StringBuffer ("UPDATE AD_ClientInfo SET ");
		sqlCmd.append("C_AcctSchema1_ID=").append(m_as.getC_AcctSchema_ID())
			.append(", C_Calendar_ID=").append(m_calendar.getC_Calendar_ID())
			.append(" WHERE AD_Client_ID=").append(m_client.getAD_Client_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
		{
			String err = "ClientInfo not updated";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		//	Validate Completeness
		DocumentTypeVerify.createDocumentTypes(m_ctx, getAD_Client_ID(), null, m_trx.getTrxName());
		DocumentTypeVerify.createPeriodControls(m_ctx, getAD_Client_ID(), null, m_trx.getTrxName());
		//
		log.info("fini");
		
		return true;
	}   //  createAccounting
	
	
	private boolean createCalendar(Timestamp startDate, int historyYears) {
		/**
		 *  Create Calendar
		 */
		m_calendar = new MCalendar(m_client);
		if (!m_calendar.save())
		{
			String err = "Calendar NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "C_Calendar_ID")).append("=").append(m_calendar.getName()).append("\n");

		Calendar cal = Calendar.getInstance(m_client.getLocale());
		if (startDate != null)
			cal.setTime(startDate);
		else 
		{
			cal.set(Calendar.DATE,1);
			cal.set(Calendar.MONTH, 0);
		}
		
		if ( historyYears < 0 )
			historyYears = 0;
		
		cal.add(Calendar.YEAR, -historyYears);
		
		// get last day of financial year
		Calendar lastday = Calendar.getInstance();
		lastday.setTime(cal.getTime());
		lastday.add(Calendar.YEAR, 1);
		lastday.add(Calendar.DATE, -1);
		
		for ( int i = 0; i < historyYears + 5; i++)  // create next 5 years
		{
			MYear year = new MYear (m_calendar);
			String Year = String.valueOf(lastday.get(Calendar.YEAR));
			year.setFiscalYear(Year);
			if (year.save())
				year.createStdPeriods(m_client.getLocale(), new Timestamp(cal.getTimeInMillis()), "yyyy-MM");
			if (year == null)
				log.log(Level.SEVERE, "Year NOT inserted");
			

			cal.add(Calendar.YEAR, 1);
			lastday.add(Calendar.YEAR, 1);
		}
		return true;
	}
	
	public boolean importChart(File chart)
	{
		// import chart of accounts automatically
		ImpFormat importer = ImpFormat.load ("Accounting - Accounts");
		importer.loadFile(m_ctx, chart, m_trx.getTrxName(), m_client.getAD_Client_ID(), 0, true);
		

		//	Process
		MProcess process = MProcess.get(m_ctx, 197);  // Import_Account
		MPInstance pInstance = new MPInstance(process, 0);
		pInstance.setAD_Client_ID(m_client.getAD_Client_ID());
		pInstance.setAD_Org_ID(0);
		for (MPInstancePara para : pInstance.getParameters())
		{
			String name = para.getParameterName();
			if ("AD_Client_ID".equals(name))
				para.setP_Number(m_client.getAD_Client_ID());
			else if ("C_Element_ID".equals(name))
				para.setP_Number(C_Element_ID);
			
			para.saveEx();
		}
		//
		ProcessInfo pi = new ProcessInfo (process.getName(), process.getAD_Process_ID(),
			0, 0);
		pi.setAD_User_ID(getAD_User_ID());
		pi.setAD_Client_ID(m_client.getAD_Client_ID());
		pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
		if ( !process.processItWithoutTrxClose(pi, m_trx))
			return false;
		

		boolean success = m_trx.commit();
		m_trx.close();
		return success;
	}
	
	private void createAccountingRecord(String tableName) throws Exception
	{
		MTable table = MTable.get(m_ctx, tableName);
		PO acct = table.getPO(0, m_trx.getTrxName());
		
		MColumn[] cols = table.getColumns(false);
		for (MColumn c : cols) {
			String columnName = c.getColumnName();
			if (c.isStandardColumn()) {
			}
			else if (DisplayType.Account == c.getAD_Reference_ID()) {
				acct.set_Value(columnName, getAcct(columnName));
				log.info("Account: " + columnName);
			}
			else if (DisplayType.YesNo == c.getAD_Reference_ID()) {
				acct.set_Value(columnName, Boolean.TRUE);
				log.info("YesNo: " + c.getColumnName());
			}
		}
		acct.setAD_Client_ID(m_client.getAD_Client_ID());
		acct.set_Value(I_C_AcctSchema.COLUMNNAME_C_AcctSchema_ID, m_as.getC_AcctSchema_ID());
		//
		if (!acct.save()) {
			throw new AdempiereUserError(CLogger.retrieveErrorString(table.getName() + " not created"));
		}
		
	}


	/**
	 * Get Account ID for key
	 * @param key key
	 * @return C_ValidCombination_ID
	 * @throws AdempiereUserError 
	 */
	private Integer getAcct (String key) throws AdempiereUserError
	{
		log.fine(key);
		//  Element
		int C_ElementValue_ID = m_nap.getC_ElementValue_ID(key.toUpperCase());
		if (C_ElementValue_ID == 0)
		{
			throw new AdempiereUserError("Account not defined: " + key);
		}

		MAccount vc = MAccount.getDefault(m_as, true);	//	optional null
		vc.setAD_Org_ID(0);		//	will be overwritten
		vc.setAccount_ID(C_ElementValue_ID);
		if (!vc.save())
		{
			throw new AdempiereUserError("Not Saved - Key=" + key + ", C_ElementValue_ID=" + C_ElementValue_ID);
		}
		int C_ValidCombination_ID = vc.getC_ValidCombination_ID();
		if (C_ValidCombination_ID == 0)
		{
			throw new AdempiereUserError("No account - Key=" + key + ", C_ElementValue_ID=" + C_ElementValue_ID);
		}
		return C_ValidCombination_ID;
	}   //  getAcct

	/**
	 *  Create GL Category
	 *  @param Name name
	 *  @param CategoryType category type MGLCategory.CATEGORYTYPE_*
	 *  @param isDefault is default value
	 *  @return GL_Category_ID
	 */
	private int createGLCategory (String Name, String CategoryType, boolean isDefault)
	{
		MGLCategory cat = new MGLCategory (m_ctx, 0, m_trx.getTrxName());
		cat.setName(Name);
		cat.setCategoryType(CategoryType);
		cat.setIsDefault(isDefault);
		if (!cat.save())
		{
			log.log(Level.SEVERE, "GL Category NOT created - " + Name);
			return 0;
		}
		//
		return cat.getGL_Category_ID();
	}   //  createGLCategory

	/**
	 *  Create Document Types with Sequence
	 *  @param Name name
	 *  @param PrintName print name
	 *  @param DocBaseType document base type
	 *  @param DocSubTypeSO sales order sub type
	 *  @param C_DocTypeShipment_ID shipment doc
	 *  @param C_DocTypeInvoice_ID invoice doc
	 *  @param StartNo start doc no
	 *  @param GL_Category_ID gl category
	 *  @param isReturnTrx is return trx
	 *  @return C_DocType_ID doc type or 0 for error
	 */
	private int createDocType (String Name, String PrintName,
		String DocBaseType, String DocSubTypeSO,
		int C_DocTypeShipment_ID, int C_DocTypeInvoice_ID,
		int StartNo, int GL_Category_ID, boolean isReturnTrx)
	{
		MSequence sequence = null;
		if (StartNo != 0)
		{
			sequence = new MSequence(m_ctx, getAD_Client_ID(), Name, StartNo, m_trx.getTrxName());
			if (!sequence.save())
			{
				log.log(Level.SEVERE, "Sequence NOT created - " + Name);
				return 0;
			}
		}
		
		MDocType dt = new MDocType (m_ctx, DocBaseType, Name, m_trx.getTrxName());
		if (PrintName != null && PrintName.length() > 0)
			dt.setPrintName(PrintName);	//	Defaults to Name
		if (DocSubTypeSO != null)
			dt.setDocSubTypeSO(DocSubTypeSO);
		if (C_DocTypeShipment_ID != 0)
			dt.setC_DocTypeShipment_ID(C_DocTypeShipment_ID);
		if (C_DocTypeInvoice_ID != 0)
			dt.setC_DocTypeInvoice_ID(C_DocTypeInvoice_ID);
		if (GL_Category_ID != 0)
			dt.setGL_Category_ID(GL_Category_ID);
		if (sequence == null)
			dt.setIsDocNoControlled(false);
		else
		{
			dt.setIsDocNoControlled(true);
			dt.setDocNoSequence_ID(sequence.getAD_Sequence_ID());
		}
		dt.setIsSOTrx();
		if (isReturnTrx)
			dt.setIsSOTrx(!dt.isSOTrx());
		if (!dt.save())
		{
			log.log(Level.SEVERE, "DocType NOT created - " + Name);
			return 0;
		}
		//
		return dt.getC_DocType_ID();
	}   //  createDocType

	
	/**************************************************************************
	 *  Create Default main entities.
	 *  - Dimensions & BPGroup, Prod Category)
	 *  - Location, Locator, Warehouse
	 *  - PriceList
	 *  - Cashbook, PaymentTerm
	 *  @param C_Country_ID country
	 *  @param City city
	 *  @param C_Region_ID region
	 *  @param C_Currency_ID currency
	 *  @return true if created
	 */
	public boolean createEntities (int C_Country_ID, String City, int C_Region_ID, int C_Currency_ID, String postal, String address1)
	{
		if (m_as == null)
		{
			log.severe ("No AcctountingSChema");
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		log.info("C_Country_ID=" + C_Country_ID 
			+ ", City=" + City + ", C_Region_ID=" + C_Region_ID);
		m_info.append("\n----\n");
		//
		String defaultName = Msg.translate(m_lang, "Standard");
		String defaultEntry = "'" + defaultName + "',";
		StringBuffer sqlCmd = null;
		int no = 0;

		//	Create Marketing Channel/Campaign
		int C_Channel_ID = getNextID(getAD_Client_ID(), "C_Channel");
		sqlCmd = new StringBuffer("INSERT INTO C_Channel ");
		sqlCmd.append("(C_Channel_ID,Name,");
		sqlCmd.append(m_stdColumns).append(") VALUES (");
		sqlCmd.append(C_Channel_ID).append(",").append(defaultEntry);
		sqlCmd.append(m_stdValues).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Channel NOT inserted");
		int C_Campaign_ID = getNextID(getAD_Client_ID(), "C_Campaign");
		sqlCmd = new StringBuffer("INSERT INTO C_Campaign ");
		sqlCmd.append("(C_Campaign_ID,C_Channel_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,Costs) VALUES (");
		sqlCmd.append(C_Campaign_ID).append(",").append(C_Channel_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("0)");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no == 1)
			m_info.append(Msg.translate(m_lang, "C_Campaign_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "Campaign NOT inserted");
		if (m_hasMCampaign)
		{
			//  Default
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_Campaign_ID=").append(C_Campaign_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='MC'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			if (no != 1)
				log.log(Level.SEVERE, "AcctSchema ELement Campaign NOT updated");
		}

		//	Create Sales Region
		int C_SalesRegion_ID = getNextID(getAD_Client_ID(), "C_SalesRegion");
		sqlCmd = new StringBuffer ("INSERT INTO C_SalesRegion ");
		sqlCmd.append("(C_SalesRegion_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,IsSummary) VALUES (");
		sqlCmd.append(C_SalesRegion_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("'N')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no == 1)
			m_info.append(Msg.translate(m_lang, "C_SalesRegion_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "SalesRegion NOT inserted");
		if (m_hasSRegion)
		{
			//  Default
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_SalesRegion_ID=").append(C_SalesRegion_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='SR'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			if (no != 1)
				log.log(Level.SEVERE, "AcctSchema ELement SalesRegion NOT updated");
		}

		/**
		 *  Business Partner
		 */
		MBPGroup bpg = createBPGroups(defaultName);

		//	Create BPartner
		MBPartner bp = new MBPartner (m_ctx, 0, m_trx.getTrxName());
		bp.setValue(defaultName);
		bp.setName(defaultName);
		bp.setBPGroup(bpg);
		if (bp.save())
			m_info.append(Msg.translate(m_lang, "C_BPartner_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "BPartner NOT inserted");
		//  Location for Standard BP
		MLocation bpLoc = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		bpLoc.saveEx();
		MBPartnerLocation bpl = new MBPartnerLocation(bp);
		bpl.setC_Location_ID(bpLoc.getC_Location_ID());
		if (!bpl.save())
			log.log(Level.SEVERE, "BP_Location (Standard) NOT inserted");
		//  Default
		sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
		sqlCmd.append("C_BPartner_ID=").append(bp.getC_BPartner_ID());
		sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
		sqlCmd.append(" AND ElementType='BP'");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "AcctSchema Element BPartner NOT updated");
		// createPreference("C_BPartner_ID", String.valueOf(bp.getC_BPartner_ID()), 143);

		/**
		 *  Product
		 */
		//  Create Product Category
		MProductCategory pc = new MProductCategory(m_ctx, 0, m_trx.getTrxName());
		pc.setValue(defaultName);
		pc.setName(defaultName);
		pc.setIsDefault(true);
		if (pc.save())
			m_info.append(Msg.translate(m_lang, "M_Product_Category_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "Product Category NOT inserted");

		//  UOM (EA)
		int C_UOM_ID = 100;

		int C_TaxCategory_ID = createTax(C_Country_ID, defaultEntry);

		//	Create Product
		MProduct product = new MProduct (m_ctx, 0, m_trx.getTrxName());
		product.setValue(defaultName);
		product.setName(defaultName);
		product.setC_UOM_ID(C_UOM_ID);
		product.setM_Product_Category_ID(pc.getM_Product_Category_ID());
		product.setC_TaxCategory_ID(C_TaxCategory_ID);
		if (product.save())
			m_info.append(Msg.translate(m_lang, "M_Product_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "Product NOT inserted");
		//  Default
		sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
		sqlCmd.append("M_Product_ID=").append(product.getM_Product_ID());
		sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
		sqlCmd.append(" AND ElementType='PR'");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "AcctSchema Element Product NOT updated");

		/**
		 *  Location, Warehouse, Locator
		 */
		//  Location (Company)
		MLocation loc = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		loc.setAddress1(address1);
		loc.setPostal(postal);
		loc.saveEx();
		sqlCmd = new StringBuffer ("UPDATE AD_OrgInfo SET C_Location_ID=");
		sqlCmd.append(loc.getC_Location_ID()).append(" WHERE AD_Org_ID=").append(getAD_Org_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Location NOT inserted");
		createPreference("C_Country_ID", String.valueOf(C_Country_ID), 0);

		//  Location (Warehouse)
		loc = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		loc.setAddress1(address1);
		loc.setPostal(postal);
		loc.saveEx();
		
		//  Default Warehouse
		MWarehouse wh = new MWarehouse(m_ctx, 0, m_trx.getTrxName());
		wh.setValue(defaultName);
		wh.setName(defaultName);
		wh.setC_Location_ID(loc.getC_Location_ID());
		if (!wh.save())
			log.log(Level.SEVERE, "Warehouse NOT inserted");

		//   Locator
		MLocator locator = new MLocator(wh, defaultName);
		locator.setIsDefault(true);
		if (!locator.save())
			log.log(Level.SEVERE, "Locator NOT inserted");

		//  Update ClientInfo
		sqlCmd = new StringBuffer ("UPDATE AD_ClientInfo SET ");
		sqlCmd.append("C_BPartnerCashTrx_ID=").append(bp.getC_BPartner_ID());
		sqlCmd.append(",M_ProductFreight_ID=").append(product.getM_Product_ID());
//		sqlCmd.append("C_UOM_Volume_ID=");
//		sqlCmd.append(",C_UOM_Weight_ID=");
//		sqlCmd.append(",C_UOM_Length_ID=");
//		sqlCmd.append(",C_UOM_Time_ID=");
		sqlCmd.append(" WHERE AD_Client_ID=").append(getAD_Client_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
		{
			String err = "ClientInfo not updated";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			return false;
		}

		/**
		 *  Other
		 */
		//  PriceList
		MPriceList pl = new MPriceList(m_ctx, 0, m_trx.getTrxName());
		pl.setName(defaultName);
		pl.setC_Currency_ID(C_Currency_ID);
		pl.setIsDefault(true);
		if (!pl.save())
			log.log(Level.SEVERE, "PriceList NOT inserted");
		//  Price List
		MDiscountSchema ds = new MDiscountSchema(m_ctx, 0, m_trx.getTrxName());
		ds.setName(defaultName);
		ds.setDiscountType(MDiscountSchema.DISCOUNTTYPE_Pricelist);
		if (!ds.save())
			log.log(Level.SEVERE, "DiscountSchema NOT inserted");
		//  PriceList Version
		MPriceListVersion plv = new MPriceListVersion(pl);
		plv.setName();
		plv.setM_DiscountSchema_ID(ds.getM_DiscountSchema_ID());
		if (!plv.save())
			log.log(Level.SEVERE, "PriceList_Version NOT inserted");
		//  ProductPrice
		MProductPrice pp = new MProductPrice(plv, product.getM_Product_ID(), 
			Env.ONE, Env.ONE, Env.ONE);
		if (!pp.save())
			log.log(Level.SEVERE, "ProductPrice NOT inserted");


		//	Create Sales Rep for Client-User
		MBPartner bpCU = new MBPartner (m_ctx, 0, m_trx.getTrxName());
		bpCU.setValue(AD_User_U_Name);
		bpCU.setName(AD_User_U_Name);
		bpCU.setBPGroup(bpg);
		bpCU.setIsEmployee(true);
		bpCU.setIsSalesRep(true);
		if (bpCU.save())
			m_info.append(Msg.translate(m_lang, "SalesRep_ID")).append("=").append(AD_User_U_Name).append("\n");
		else
			log.log(Level.SEVERE, "SalesRep (User) NOT inserted");
		//  Location for Client-User
		MLocation bpLocCU = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		bpLocCU.saveEx();
		MBPartnerLocation bplCU = new MBPartnerLocation(bpCU);
		bplCU.setC_Location_ID(bpLocCU.getC_Location_ID());
		if (!bplCU.save())
			log.log(Level.SEVERE, "BP_Location (User) NOT inserted");
		//  Update User
		sqlCmd = new StringBuffer ("UPDATE AD_User SET C_BPartner_ID=");
		sqlCmd.append(bpCU.getC_BPartner_ID()).append(" WHERE AD_User_ID=").append(AD_User_U_ID);
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "User of SalesRep (User) NOT updated");


		//	Create Sales Rep for Client-Admin
		MBPartner bpCA = new MBPartner (m_ctx, 0, m_trx.getTrxName());
		bpCA.setValue(AD_User_Name);
		bpCA.setName(AD_User_Name);
		bpCA.setBPGroup(bpg);
		bpCA.setIsEmployee(true);
		bpCA.setIsSalesRep(true);
		if (bpCA.save())
			m_info.append(Msg.translate(m_lang, "SalesRep_ID")).append("=").append(AD_User_Name).append("\n");
		else
			log.log(Level.SEVERE, "SalesRep (Admin) NOT inserted");
		//  Location for Client-Admin
		MLocation bpLocCA = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		bpLocCA.saveEx();
		MBPartnerLocation bplCA = new MBPartnerLocation(bpCA);
		bplCA.setC_Location_ID(bpLocCA.getC_Location_ID());
		if (!bplCA.save())
			log.log(Level.SEVERE, "BP_Location (Admin) NOT inserted");
		//  Update User
		sqlCmd = new StringBuffer ("UPDATE AD_User SET C_BPartner_ID=");
		sqlCmd.append(bpCA.getC_BPartner_ID()).append(" WHERE AD_User_ID=").append(AD_User_ID);
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "User of SalesRep (Admin) NOT updated");


		createPaymentTerms();

		//  Project Cycle
		C_Cycle_ID = getNextID(getAD_Client_ID(), "C_Cycle");
		sqlCmd = new StringBuffer ("INSERT INTO C_Cycle ");
		sqlCmd.append("(C_Cycle_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name,C_Currency_ID) VALUES (");
		sqlCmd.append(C_Cycle_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(C_Currency_ID).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Cycle NOT inserted");

		/**
		 *  Organization level data	===========================================
		 */

		//	Create Default Project
		int C_Project_ID = getNextID(getAD_Client_ID(), "C_Project");
		sqlCmd = new StringBuffer ("INSERT INTO C_Project ");
		sqlCmd.append("(C_Project_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,C_Currency_ID,IsSummary) VALUES (");
		sqlCmd.append(C_Project_ID).append(",").append(m_stdValuesOrg).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append(C_Currency_ID).append(",'N')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no == 1)
			m_info.append(Msg.translate(m_lang, "C_Project_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "Project NOT inserted");
		//  Default Project
		if (m_hasProject)
		{
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_Project_ID=").append(C_Project_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='PJ'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			if (no != 1)
				log.log(Level.SEVERE, "AcctSchema ELement Project NOT updated");
		}

		//  CashBook
		MCashBook cb = new MCashBook(m_ctx, 0, m_trx.getTrxName());
		cb.setName(defaultName);
		cb.setC_Currency_ID(C_Currency_ID);
		if (cb.save())
			m_info.append(Msg.translate(m_lang, "C_CashBook_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "CashBook NOT inserted");
		//
		
		log.info("finish");
		return true;
	}   //  createEntities
	
	/*
	 * Create standard payment terms
	 */
	protected void createPaymentTerms() {
		StringBuffer sqlCmd;
		int no;
		//  Payment Term
		int C_PaymentTerm_ID = getNextID(getAD_Client_ID(), "C_PaymentTerm");
		sqlCmd = new StringBuffer ("INSERT INTO C_PaymentTerm ");
		sqlCmd.append("(C_PaymentTerm_ID,").append(m_stdColumns).append(",");
		sqlCmd.append("Value,Name,NetDays,GraceDays,DiscountDays,Discount,DiscountDays2,Discount2,IsDefault) VALUES (");
		sqlCmd.append(C_PaymentTerm_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append("'Immediate','Immediate',0,0,0,0,0,0,'Y')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "PaymentTerm NOT inserted");
	}
	
	/*
	 * Create standard BP groups and return default one
	 */
	protected MBPGroup createBPGroups(String defaultName) {
		//  Create BP Group
		MBPGroup bpg = new MBPGroup (m_ctx, 0, m_trx.getTrxName());
		bpg.setValue(defaultName);
		bpg.setName(defaultName);
		bpg.setIsDefault(true);
		if (bpg.save())
			m_info.append(Msg.translate(m_lang, "C_BP_Group_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "BP Group NOT inserted");
		return bpg;
	}



	protected int createTax(int C_Country_ID, String defaultEntry) {
		StringBuffer sqlCmd;
		int no;
		//  TaxCategory
		int C_TaxCategory_ID = getNextID(getAD_Client_ID(), "C_TaxCategory");
		sqlCmd = new StringBuffer ("INSERT INTO C_TaxCategory ");
		sqlCmd.append("(C_TaxCategory_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name,IsDefault) VALUES (");
		sqlCmd.append(C_TaxCategory_ID).append(",").append(m_stdValues).append(", ");
		if (C_Country_ID == 100)    // US
			sqlCmd.append("'Sales Tax','Y')");
		else
			sqlCmd.append(defaultEntry).append("'Y')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "TaxCategory NOT inserted");
		
		sqlCmd = new StringBuffer ("INSERT INTO C_TaxCategory_Trl (AD_Language,C_TaxCategory_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy)");
		sqlCmd.append(" SELECT l.AD_Language,t.C_TaxCategory_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_TaxCategory t");
		sqlCmd.append(" WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_TaxCategory_ID=").append(C_TaxCategory_ID);
		sqlCmd.append(" AND NOT EXISTS (SELECT * FROM C_TaxCategory_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.C_TaxCategory_ID=t.C_TaxCategory_ID)");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no < 0)
			log.log(Level.SEVERE, "TaxCategory Translation NOT inserted");

		//  Tax - Zero Rate
		MTax tax = new MTax (m_ctx, "Standard", Env.ZERO, C_TaxCategory_ID, m_trx.getTrxName());
		tax.setIsDefault(true);
		if (tax.save())
			m_info.append(Msg.translate(m_lang, "C_Tax_ID"))
				.append("=").append(tax.getName()).append("\n");
		else
			log.log(Level.SEVERE, "Tax NOT inserted");
		return C_TaxCategory_ID;
	}

	/**
	 *  Create Preference
	 *  @param Attribute attribute
	 *  @param Value value
	 *  @param AD_Window_ID window
	 */
	private void createPreference (String Attribute, String Value, int AD_Window_ID)
	{
		int AD_Preference_ID = getNextID(getAD_Client_ID(), "AD_Preference");
		StringBuffer sqlCmd = new StringBuffer ("INSERT INTO AD_Preference ");
		sqlCmd.append("(AD_Preference_ID,").append(m_stdColumns).append(",");
		sqlCmd.append("Attribute,Value,AD_Window_ID) VALUES (");
		sqlCmd.append(AD_Preference_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append("'").append(Attribute).append("','").append(Value).append("',");
		if (AD_Window_ID == 0)
			sqlCmd.append("NULL)");
		else
			sqlCmd.append(AD_Window_ID).append(")");
		int no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Preference NOT inserted - " + Attribute);
	}   //  createPreference

	
	/**************************************************************************
	 * 	Get Next ID
	 * 	@param AD_Client_ID client
	 * 	@param TableName table name
	 * 	@return id
	 */
	protected int getNextID (int AD_Client_ID, String TableName)
	{
		//	TODO: Exception 
		return DB.getNextID (AD_Client_ID, TableName, m_trx.getTrxName());
	}	//	getNextID

	/**
	 *  Get Client
	 *  @return AD_Client_ID
	 */
	public int getAD_Client_ID()
	{
		return m_client.getAD_Client_ID();
	}
	/**
	 * 	Get AD_Org_ID
	 *	@return AD_Org_ID
	 */
	public int getAD_Org_ID()
	{
		return m_org.getAD_Org_ID();
	}
	/**
	 * 	Get AD_User_ID
	 *	@return AD_User_ID
	 */
	public int getAD_User_ID()
	{
		return AD_User_ID;
	}
	/**
	 * 	Get Info
	 *	@return Info
	 */
	public String getInfo()
	{
		return m_info.toString();
	}

	/**
	 * 	Rollback Internal Transaction
	 */
	public void rollback() {
		try {
			m_trx.rollback();
			m_trx.close();
		} catch (Exception e) {}
	}
	public void createBank(String bankName, String routingNo,
			String accountNo, int currencyId) {
		
		MBank bank = new MBank(m_ctx, 0, m_trx.getTrxName());
		bank.setAD_Org_ID(0);
		bank.setName(bankName);
		bank.setRoutingNo(routingNo);
		bank.saveEx();
		MBankAccount account = new MBankAccount(m_ctx, 0, m_trx.getTrxName());
		account.setC_Bank_ID(bank.getC_Bank_ID());
		account.setAccountNo(accountNo);
		account.setC_Currency_ID(currencyId);
		account.setBankAccountType(MBankAccount.BANKACCOUNTTYPE_Checking);
		account.saveEx();
		
	}
}   //  MSetup
