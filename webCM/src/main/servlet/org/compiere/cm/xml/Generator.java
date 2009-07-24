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
package org.compiere.cm.xml;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.compiere.Adempiere;
import org.compiere.cm.HttpServletCM;
import org.compiere.cm.cache.Chat;
import org.compiere.cm.cache.ContainerElement;
import org.compiere.cm.cache.ContainerTree;
import org.compiere.cm.utils.Counter;
import org.compiere.cm.utils.RequestAnalyzer;
import org.compiere.model.MAd;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MNewsChannel;
import org.compiere.model.MTable;
import org.compiere.model.MTemplate;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.model.X_CM_Container_Element;
import org.compiere.model.X_CM_NewsChannel;
import org.compiere.model.X_CM_TemplateTable;
import org.compiere.util.DB;
import org.compiere.util.Language;
import org.compiere.util.WebInfo;

/**
 * This Generator builds up the XML for the XSLT Template Engine
 * 
 * @author Yves Sandfort
 * @version $Id$
 */
public class Generator
{

	protected StringBuffer	xmlCode = new StringBuffer ();

	protected RequestAnalyzer thisRequest;

		/**
     * Generator Object for the generation of XML Context
     * 
     * @param thisServlet
     *            currentServletHandler
     * @param httpRequest
     *            currentRequest
     * @param tempRequest
     *            currentRequestAnalyzer
	 * @param xmlAppend 
     */
	public Generator (HttpServletCM thisServlet,
		HttpServletRequest httpRequest, RequestAnalyzer tempRequest, StringBuffer xmlAppend)
	{
		thisRequest = tempRequest;
		xmlCode.append ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n").append (
			"<!-- ").append (org.compiere.Adempiere.getSummaryAscii ()).append (
			" -->\n").append ("<webCM>\n").append ("<build>").append (
			thisServlet.getBuildDate ()).append ("</build>\n");
		generateSystemHeader (thisServlet);
		generateProjectHeader ();
		generateThisContainer (thisServlet.getContainerElementCache (),
			thisServlet.getChatCache ());
		generateExternalTables (thisServlet.getDomainCache ().getCtx (), httpRequest);
		generateContainerTree (thisServlet.getContainerTreeCache ());
		MTemplate thisTemplate = thisRequest.getCM_Container ().getTemplate ();
		if (thisTemplate.isNews ())
			generateNewsFeed (thisServlet.getDomainCache ().getCtx ());
		if (thisTemplate.isUseAd ())
			generateAdCode (httpRequest, thisTemplate);
		// TODO change the decision via IsRequest boolean for Template
		if (thisTemplate.getName().toUpperCase().indexOf("REQUEST") > -1)
			generateRequest(httpRequest, thisServlet.getXMLCache(), thisServlet.getCtx ());
		generateCommunity(thisServlet.getXMLCache ());
		generateSessionEquiv(thisServlet, httpRequest, thisServlet.getCtx ());
		queryStringToXML (httpRequest);
		xmlCode.append(xmlAppend);
	}

	private void generateSystemHeader (HttpServletCM thisServlet)
	{
		xmlCode.append ("<system>\n");
		xmlCode.append ("<adempiere>\n");
		xmlCode.append ("<mainversion>" + Adempiere.MAIN_VERSION
			+ "</mainversion>\n");
		xmlCode.append ("<dateversion>" + Adempiere.DATE_VERSION
			+ "</dateversion>\n");
		xmlCode.append ("<dbversion>" + Adempiere.DB_VERSION + "</dbversion>\n");
		xmlCode.append ("</adempiere>\n");
		xmlCode.append ("<deployment>\n");
		xmlCode.append ("<internalMediaURL>"
			+ thisServlet.getInternalMediaURL () + "</internalMediaURL>\n");
		xmlCode.append ("<sessionMediaURL>"
			+ thisServlet.getExternalMediaURL () + "</sessionMediaURL>\n");
		xmlCode.append ("</deployment>\n");
		Calendar cal = Calendar.getInstance ();
		xmlCode.append ("<currentday>" + cal.get (Calendar.DAY_OF_MONTH)
			+ "</currentday>\n");
		xmlCode.append ("<currentmonth>" + cal.get (Calendar.MONTH)
			+ "</currentmonth>\n");
		xmlCode.append ("<currentyear>" + cal.get (Calendar.YEAR)
			+ "</currentyear>\n");
		xmlCode.append ("<currenthour>" + cal.get (Calendar.HOUR_OF_DAY)
			+ "</currenthour>\n");
		xmlCode.append ("<currentminute>" + cal.get (Calendar.MINUTE)
			+ "</currentminute>\n");
		xmlCode.append ("<currentsecond>" + cal.get (Calendar.SECOND)
			+ "</currentsecond>\n");
		xmlCode.append ("<activesessions>" + Counter.getActiveSessions ()
			+ "</activesessions>\n");
		xmlCode.append ("</system>\n");
	}
	
	private void generateSessionEquiv(HttpServletCM thisServlet, HttpServletRequest httpRequest, Properties ctx)
	{
		xmlCode.append("<session>\n");
		HttpSession thisSession = httpRequest.getSession (false);
		if (thisSession!=null) {
			if (thisSession.getAttribute (WebInfo.NAME)!=null) 
			{
				WebInfo wi = (WebInfo) thisSession.getAttribute (WebInfo.NAME);
				xmlCode.append ("<WebInfo>\n");
				xmlCode.append ("<Info><![CDATA[" + wi.getInfo () + "]]></Info>\n");
				if (wi.getUser_ID ()>0) 
				{
					MUser thisUser = MUser.get (ctx, wi.getAD_User_ID ());
					xmlCode = thisUser.get_xmlString (xmlCode);
					MBPartner thisBPartner = new MBPartner(ctx, wi.getC_BPartner_ID (), null);
					if (thisBPartner!=null)
						xmlCode = thisBPartner.get_xmlString (xmlCode);
					MBPartnerLocation thisBPartnerLocation = thisBPartner.getPrimaryC_BPartner_Location ();
					if (thisBPartnerLocation!=null)
						xmlCode = thisBPartnerLocation.get_xmlString (xmlCode);
					MLocation thisLocation = MLocation.getBPLocation (ctx, thisBPartnerLocation.get_ID (), null);
					if (thisLocation!=null)
						xmlCode = thisLocation.get_xmlString (xmlCode);
					MBPBankAccount[] theseBPBankAccount = thisBPartner.getBankAccounts (true);
					if (theseBPBankAccount!=null && theseBPBankAccount.length>0)
						for (int i=0; i<theseBPBankAccount.length;i++)
							xmlCode = theseBPBankAccount[i].get_xmlString (xmlCode);
					if (thisSession.getAttribute ("EMail")==null)
						thisSession.setAttribute ("EMail", thisUser.getEMail ());
				}
				if (wi.getWebUser ().getPasswordMessage ()!=null)
				{
					xmlCode.append ("<PasswordMessage><![CDATA[" + wi.getWebUser ().getPasswordMessage () + "]]></PasswordMessage>\n");
					wi.getWebUser ().setPasswordMessage (null);
				}
				if (wi.getWebUser ().getSaveErrorMessage ()!=null)
				{
					xmlCode.append ("<SaveErrorMessage><![CDATA[" + wi.getWebUser ().getSaveErrorMessage () + "]]></SaveErrorMessage>\n");
					wi.getWebUser ().setSaveErrorMessage (null);
				}
				if (thisSession.getAttribute("hdrMessage")!=null) {
					xmlCode.append ("<hdrMessage><![CDATA[" + thisSession.getAttribute("hdrMessage") + "]]></hdrMessage>\n");
					thisSession.removeAttribute ("hdrMessage");
				}
				xmlCode.append ("<WebUser>\n");
				xmlCode.append ("<LoggedIn>" + wi.getWebUser ().isLoggedIn () + "</LoggedIn>\n");
				xmlCode.append ("</WebUser>\n");
				xmlCode.append ("</WebInfo>\n");
			}
			if (thisSession.getAttribute ("EMail")!=null) 
				xmlCode.append ("<EMail><![CDATA[" + thisSession.getAttribute("EMail") + "]]></EMail>\n");
		}
		xmlCode.append("</session>\n");
	}
	
	private void generateCommunity(org.compiere.cm.cache.XML thisXML) 
	{
		xmlCode.append("<dictionary>\n");
		xmlCode.append(thisXML.getXML ("C_Country"));
		xmlCode.append("</dictionary>\n");
	}

	/**
	 * Creates the XML tree for request tables.
	 * 
	 * @param httpRequest
	 * @param thisXML
	 * @param ctx
	 */
	private void generateRequest(HttpServletRequest httpRequest, org.compiere.cm.cache.XML thisXML, Properties ctx) 
	{
		HttpSession thisSession = httpRequest.getSession (false);
		WebInfo wi = null;
		if (thisSession!=null) {
			if (thisSession.getAttribute (WebInfo.NAME)!=null) 
				wi = (WebInfo) thisSession.getAttribute (WebInfo.NAME);

			xmlCode.append("<requestTables>\n");
			int l_nClientID = Integer.parseInt(ctx.getProperty("#AD_Client_ID"));
			
			if (wi != null) {
				if (wi.getC_BPartner_ID() != -1) {
					genTable("AD_User", "(AD_User.IsActive='Y' AND AD_User.AD_Client_ID=" + l_nClientID + "  AND AD_User.C_BPartner_ID=@C_BPartner_ID@) ORDER BY AD_User.AD_User_ID", false, ctx, wi);
					genTable("C_BPartner", "(C_BPartner.IsActive='Y' AND C_BPartner.AD_Client_ID=" + l_nClientID + "  AND C_BPartner.C_BPartner_ID=@C_BPartner_ID@)ORDER BY C_BPartner.C_BPartner_ID", false, ctx, wi);
					genTable("C_Invoice", "(C_Invoice.IsActive='Y' AND C_Invoice.AD_Client_ID=" + l_nClientID + "  AND C_Invoice.C_BPartner_ID=@C_BPartner_ID@) ORDER BY C_Invoice.C_Invoice_ID", false, ctx, wi);
					genTable("C_Order", "(C_Order.IsActive='Y' AND C_Order.AD_Client_ID=" + l_nClientID + "  AND C_Order.C_BPartner_ID=@C_BPartner_ID@) ORDER BY C_Order.C_Order_ID", false, ctx, wi);
					genTable("C_Payment", "(C_Payment.IsActive='Y' AND C_Payment.AD_Client_ID=" + l_nClientID + "  AND C_Payment.C_BPartner_ID=@C_BPartner_ID@) ORDER BY C_Payment.C_Payment_ID", false, ctx, wi);
					genTable("C_Project", "(C_Project.IsActive='Y' AND C_Project.AD_Client_ID=" + l_nClientID + "  AND C_Project.C_BPartner_ID=@C_BPartner_ID@) ORDER BY C_Project.C_Project_ID", false, ctx, wi);
					genTable("M_InOut", "(M_InOut.IsActive='Y' AND M_InOut.AD_Client_ID=" + l_nClientID + "  AND M_InOut.C_BPartner_ID=@C_BPartner_ID@) ORDER BY M_InOut.M_InOut_ID", false, ctx, wi);
					genTable("R_Request", "(R_Request.IsActive='Y' AND R_Request.AD_Client_ID=" + l_nClientID + "  AND R_Request.C_BPartner_ID=@C_BPartner_ID@) ORDER BY R_Request.R_Request_ID", true, ctx, wi);
					genTable("R_RequestUpdate", "(R_RequestUpdate.IsActive='Y' AND R_RequestUpdate.AD_Client_ID=" + l_nClientID + "  AND R_RequestUpdate.R_Request_ID IN (SELECT R_Request.R_Request_ID FROM R_Request WHERE R_Request.IsActive='Y' AND R_Request.AD_Client_ID=" + l_nClientID + "  AND R_Request.C_BPartner_ID=@C_BPartner_ID@)) ORDER BY R_RequestUpdate.R_RequestUpdate_ID", true, ctx, wi);				
				}
				genTable("A_Asset", "A_Asset.IsActive='Y' AND A_Asset.AD_Client_ID=" + l_nClientID + "  ORDER BY A_Asset.A_Asset_ID", false, ctx, wi);
				//genTable("AD_Role", "AD_Role.IsActive='Y' AND AD_Role.AD_Client_ID=" + l_nClientID + "  ORDER BY AD_Role.AD_Role_ID", false, ctx, wi);
				genTable("C_Activity", "C_Activity.IsActive='Y' AND C_Activity.AD_Client_ID=" + l_nClientID + "  ORDER BY C_Activity.C_Activity_ID", false, ctx, wi);				
				genTable("C_Campaign", "C_Campaign.IsActive='Y' AND C_Campaign.AD_Client_ID=" + l_nClientID + "  ORDER BY C_Campaign.C_Campaign_ID", false, ctx, wi);
				genTable("M_Product", "M_Product.IsActive='Y' AND M_Product.AD_Client_ID=" + l_nClientID + " AND M_Product.Value like 'cd_%' ORDER BY M_Product.M_Product_ID", false, ctx, wi);
				genTable("M_RMA", "M_RMA.IsActive='Y' AND M_RMA.AD_Client_ID=" + l_nClientID + "  ORDER BY M_RMA.M_RMA_ID", false, ctx, wi);
				genTable("R_Category", "R_Category.IsActive='Y' AND R_Category.AD_Client_ID=" + l_nClientID + "  ORDER BY R_Category.R_Category_ID", false, ctx, wi);
				genTable("R_Group", "R_Group.IsActive='Y' AND R_Group.AD_Client_ID=" + l_nClientID + "  ORDER BY R_Group.R_Group_ID", false, ctx, wi);
				genTable("R_MailText", "R_MailText.IsActive='Y' AND R_MailText.AD_Client_ID=" + l_nClientID + "  ORDER BY R_MailText.R_MailText_ID", true, ctx, wi);
				genTable("R_RequestType", "R_RequestType.IsActive='Y' AND R_RequestType.AD_Client_ID=" + l_nClientID + "  ORDER BY R_RequestType.R_RequestType_ID", false, ctx, wi);
				genTable("R_Resolution", "R_Resolution.IsActive='Y' AND R_Resolution.AD_Client_ID=" + l_nClientID + "  ORDER BY R_Resolution.R_Resolution_ID", false, ctx, wi);
				genTable("R_StandardResponse", "R_StandardResponse.IsActive='Y' AND R_StandardResponse.AD_Client_ID=" + l_nClientID + "  ORDER BY R_StandardResponse.R_StandardResponse_ID", false, ctx, wi);
				genTable("R_Status", "R_Status.IsActive='Y' AND R_Status.AD_Client_ID=" + l_nClientID + "  ORDER BY R_Status.R_Status_ID", false, ctx, wi);
				
				genTable("_PriorityRule", "(AD_Ref_List.IsActive='Y' AND AD_Ref_List.AD_Reference_ID=154) ORDER BY AD_Ref_List.AD_Ref_List_ID", false, ctx, wi);
				genTable("R_Request_Confidential", "(AD_Ref_List.IsActive='Y' AND AD_Ref_List.AD_Reference_ID=340) ORDER BY AD_Ref_List.AD_Ref_List_ID", false, ctx, wi);
				genTable("R_Request_Due_Typ", "(AD_Ref_List.IsActive='Y' AND AD_Ref_List.AD_Reference_ID=222) ORDER BY AD_Ref_List.AD_Ref_List_ID", false, ctx, wi);
				genTable("R_Request_Next_Action", "(AD_Ref_List.IsActive='Y' AND AD_Ref_List.AD_Reference_ID=219) ORDER BY AD_Ref_List.AD_Ref_List_ID", false, ctx, wi);
				genTable("R_Request_TaskStatus", "(AD_Ref_List.IsActive='Y' AND AD_Ref_List.AD_Reference_ID=366) ORDER BY AD_Ref_List.AD_Ref_List_ID", false, ctx, wi);
			}					
			xmlCode.append("</requestTables>\n");
		}
	}

	private void generateProjectHeader ()
	{
		xmlCode.append ("<project>\n");
		xmlCode = thisRequest.getWebProject ().get_xmlString (xmlCode);
		if (thisRequest.getWebProjectDomain () != null)
			xmlCode = thisRequest.getWebProjectDomain ()
				.get_xmlString (xmlCode);
		xmlCode.append ("\n</project>\n");
	}

	private void generateThisContainer (ContainerElement containerElementCache,
		Chat chatCache)
	{
		xmlCode.append ("<thisContainer>\n");
		xmlCode = thisRequest.getCM_Container ().get_xmlString (xmlCode);
		/*int[] tableKeys = X_CM_Chat.getAllIDs ("CM_Chat", "AD_Table_ID="
			+ thisRequest.getCM_Container ().get_Table_ID ()
			+ " AND Record_ID=" + thisRequest.getCM_Container ().get_ID (),
			"WebCM");
		if (tableKeys != null)
		{
			if (tableKeys.length > 0)
			{
				for (int i = 0; i < tableKeys.length; i++)
				{
					xmlCode = chatCache.getCM_Chat (tableKeys[i])
						.get_xmlString (xmlCode);
				}
			}
		}*/
		int[] tableKeys = X_CM_Container_Element.getAllIDs ("CM_Container_Element",
			"CM_Container_ID=" + thisRequest.getCM_Container ().get_ID (),
			"WebCM");
		if (tableKeys.length > 0)
		{
			// Found elements, so let's show them...
			for (int i = 0; i < tableKeys.length; i++)
			{
				X_CM_Container_Element thisElement = containerElementCache
					.getCM_Container_Element (tableKeys[i], thisRequest
						.getCM_Container ().getCM_WebProject_ID ());
				if (thisElement != null)
				{
					StringBuffer newCode = new StringBuffer ();
					thisElement.get_xmlString (newCode);
					if (newCode.indexOf ("<body>") >= 0)
						newCode.delete (newCode
							.indexOf ("<ContentHTML><![CDATA[") + 22, newCode
							.indexOf ("<body>") + 6);
					if (newCode.indexOf ("</body>") >= 0)
						newCode.delete (newCode.indexOf ("</body>"), newCode
							.indexOf ("]]></ContentHTML>"));
					xmlCode.append (newCode);
				}
			}
		}
		xmlCode.append ("\n</thisContainer>\n");
	}

	/**
	 * Creates the nodes for the request tables in the XML-tree.
	 * 
	 * @param tableName
	 * @param whereClause
	 * @param completeXML
	 * @param ctx
	 * @param wi
	 */
	private void genTable(String tableName, String whereClause, boolean completeXML, Properties ctx, WebInfo wi) {
		String l_szTrxName = null;
		StringBuffer tmpCode = new StringBuffer();		
		String dataTableName = tableName;
		String l_whereClause = replaceSessionElements(wi, whereClause);
		
		tmpCode.append("<" + tableName + ">\n");
		
		// For the different AD_Ref_List tables
		if (whereClause.indexOf("AD_Reference") > -1) {
			dataTableName = "AD_Ref_List";
		}
		int[] l_nIDs = PO.getAllIDs(dataTableName, l_whereClause, l_szTrxName);
		int[] l_nTableIDs = MTable.getAllIDs("AD_Table", "TableName='" + dataTableName + "'", l_szTrxName);
		if (l_nTableIDs.length > 0) {
			MTable table = MTable.get (ctx, l_nTableIDs[0]);
			PO l_Object = null;
			
			// If the table should contain complete xml use the PO function get_xmlString
			if (completeXML) {
				for (int i = 0; i < l_nIDs.length; i++) {
					l_Object = table.getPO(l_nIDs[i], l_szTrxName);
					l_Object.get_xmlString(tmpCode);
				}
			// else only append ID and DisplayName
			} else {
				String sql = MLookupFactory.getLookup_TableDirEmbed(Language.getLanguage("en"), dataTableName + "_ID", dataTableName);
				sql = sql.concat(" AND " + l_whereClause);
				PreparedStatement pstm = DB.prepareStatement(sql, l_szTrxName);
				ResultSet rs = null;
				try {
					rs = pstm.executeQuery();				
				} catch (Exception e) {
					
				}
				for (int i = 0; i < l_nIDs.length; i++) {
					l_Object = table.getPO(l_nIDs[i], l_szTrxName);				
					tmpCode.append("<" + dataTableName + " AD_Table_ID=\"" + table.get_ID() + "\" Record_ID=\"" + l_Object.get_ID() + "\">\n");
					tmpCode.append("<" + dataTableName + "_ID>");
					tmpCode.append(l_Object.get_ID());
					tmpCode.append("</" + dataTableName + "_ID>\n");
					// Only AD_Ref_List works with value as reference
					if (dataTableName.equals("AD_Ref_List")) {
						tmpCode.append("<Value>");
						tmpCode.append("<![CDATA[" + l_Object.get_Value("Value")+ "]]>\n");
						tmpCode.append("</Value>\n");
					}
					// for these two table the BPartner is needed
					if (dataTableName.equals("AD_User") || dataTableName.equals("C_Project")) {
						tmpCode.append("<C_BPartner_ID>");
						tmpCode.append(l_Object.get_Value("C_BPartner_ID"));
						tmpCode.append("</C_BPartner_ID>\n");						
					}
					tmpCode.append("<DisplayName>\n");				
					try {
						if (rs.next()) {
							tmpCode.append("<![CDATA[" + rs.getString(1) + "]]>\n");
						}
					} catch (SQLException e) {
						tmpCode.append("<![CDATA[" + e.getMessage() + "]]\n");
					}
					tmpCode.append("</DisplayName>\n");
					tmpCode.append("</" + dataTableName + ">\n");
				}
				try {
					rs.close();
					pstm.close();
				} catch (Exception e) {
					
				}
			}
		}
		tmpCode.append("</" + tableName + ">\n");
		xmlCode.append(tmpCode);
	}

	private void generateExternalTables (Properties ctx, HttpServletRequest httpRequest)
	{
		HttpSession thisSession = httpRequest.getSession (false);
		WebInfo wi = null;
		if (thisSession!=null)
			if (thisSession.getAttribute (WebInfo.NAME)!=null) 
				wi = (WebInfo) thisSession.getAttribute (WebInfo.NAME);
				

		int[] tableKeys = X_CM_TemplateTable.getAllIDs ("CM_TemplateTable",
			"CM_Template_ID="
				+ thisRequest.getCM_Container ().getCM_Template_ID (), "WebCM");
		if (tableKeys.length > 0)
		{
			xmlCode.append ("<externalTables>\n");
			for (int i = 0; i < tableKeys.length; i++)
			{
				X_CM_TemplateTable thisTemplateTable = new X_CM_TemplateTable (
					ctx, tableKeys[i], "WebCM");
				try
				{
					StringBuffer tempXML = new StringBuffer();
					tempXML.append ("<" + thisTemplateTable.getName () + ">\n");
					MTable table = MTable.get (ctx, thisTemplateTable
						.getAD_Table_ID ());
					String trxName = null;
					int[] ids = PO.getAllIDs (table.getTableName (),
						replaceSessionElements(wi,thisTemplateTable.getWhereClause ()), trxName);
					if (ids!=null && ids.length>0) {
						for (int j = 0; j < ids.length; j++)
						{
							PO po = null;
							po = table.getPO (ids[j], null);
							if (po != null)
							{
								tempXML = po.get_xmlString (tempXML);
							}
						}
					}
					tempXML.append ("\n</" + thisTemplateTable.getName ()
						+ ">\n");
					xmlCode.append(tempXML);
				}
				catch (Exception e)
				{
					e.printStackTrace ();
				}
			}
			xmlCode.append ("\n</externalTables>\n");
		}
	}
	
	public String replaceSessionElements(WebInfo wi, String whereClause) 
	{
		if (wi!=null && whereClause!=null && whereClause.indexOf ("@")>=0) {
			if (whereClause.indexOf ("@C_BPartner_ID@")>0)
				whereClause = org.compiere.util.Util.replace(whereClause,"@C_BPartner_ID@", "" + wi.getC_BPartner_ID ());
		}
		return whereClause;
	}

	private void generateContainerTree (ContainerTree containerTreeCache)
	{
/*		xmlCode.append (containerTreeCache.getContainerTree (containerTreeCache
			.getCtx (), thisRequest.getWebProject ().get_ID (), null));*/
		xmlCode.append (containerTreeCache.getContainerTree (
			thisRequest.getWebProject ().get_ID (), null));
	}

	/**
     * Get should return the complete XML Code for this page
     * 
     * @return current XML Code for this request
     */
	public String get ()
	{
		return xmlCode.toString () + "\n</webCM>\n";
	}

	/*
     * private void addPObject(PO thisObject) { xmlCode =
     * thisObject.get_xmlString(xmlCode); }
     */
	private void queryStringToXML (HttpServletRequest request)
	{
		xmlCode.append ("  <request>\n");
		xmlCode.append ("    <query_complete><![CDATA["
			+ request.getQueryString () + "]]></query_complete>\n");
		Enumeration e = request.getParameterNames ();
		String tempArray[] = new String[255];
		int j = 0;
		while (e.hasMoreElements ())
		{
			String name = (String)e.nextElement ();
			tempArray[j] = name;
			j++;
		}
		String nameArray[] = new String[j];
		for (int i = 0; i < j; i++)
			nameArray[i] = tempArray[i];
		java.util.Arrays.sort (nameArray);
		for (int i = 0; i < j; i++)
		{
			if (nameArray[i] != null)
			{
				xmlCode.append ("    <querystring>\n");
				String vals[] = (String[])request
					.getParameterValues (nameArray[i]);
				if (vals != null)
				{
					for (int k = 0; k < vals.length; k++)
					{
						xmlCode.append ("      <name>"
							+ nameArray[i]
							+ "</name><value>"
							+ org.compiere.util.Util.replace (
								org.compiere.util.Util.replace (vals[k],
									"'", "&#39;"), "&", "&amp;") + "</value>\n");
					}
				}
				else
				{
					xmlCode
						.append ("      <name>" + nameArray[i] + "</name>\n");
				}
				xmlCode.append ("    </querystring>\n");
			}
		}
		xmlCode.append ("  </request>\n");
	}

	private void generateNewsFeed (Properties ctx)
	{
		xmlCode.append ("<rss version=\"2.0\">");
		int[] theseChannels = X_CM_NewsChannel
			.getAllIDs ("CM_NewsChannel", "CM_WebProject_ID="
				+ thisRequest.getWebProject ().getCM_WebProject_ID (), "WevbCM");
		if (theseChannels != null && theseChannels.length > 0)
		{
			for (int i = 0; i < theseChannels.length; i++)
			{
				MNewsChannel thisChannel = new MNewsChannel (ctx,
					theseChannels[i], "WebCM");
				thisChannel.get_rss2ChannelCode(xmlCode, false);			
			}
		}
		xmlCode.append ("</rss>");
	}

	private void generateAdCode (HttpServletRequest httpRequest,
		MTemplate thisTemplate)
	{
		xmlCode.append ("<ad>");
		MAd[] thisAd = thisTemplate.getAds ();
		if (thisAd != null)
		{
			for (int i = 0; i < thisAd.length; i++)
				if (thisAd[i] != null)
					thisAd[i].get_xmlString (xmlCode);
		}
		xmlCode.append ("</ad>");
	}
}
