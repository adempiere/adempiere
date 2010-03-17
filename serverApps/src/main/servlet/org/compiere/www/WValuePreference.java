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
package org.compiere.www;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.AlignType;
import org.apache.ecs.Element;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.select;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;


/**
 *	HTML Process and Report UI
 *
 *  @author Jorg Janke
 *  @version  $Id: WValuePreference.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WValuePreference extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6283213348834944668L;
	
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	//Modified by Rob Klein 4/29/07
	private String errorMessage = null;
	
	private Properties      m_ctx;
	//private int             m_WindowNo;
	private int             m_AD_Client_ID;
	private int             m_AD_Org_ID;
	private int             m_AD_User_ID;
	private int             m_AD_Window_ID;
	private String          m_Attribute;
	private String          m_DisplayAttribute;
	private String          m_Value;
	private String          m_DisplayValue;
	private int             m_DisplayType;
	private MRole			m_role;
	private String			m_Explination;	
	private Boolean 		m_Client;
	private Boolean 		m_User;
	private Boolean 		m_Window;
	private Boolean 		m_Org;
	/**
	 *	Initialize global variables
	 *	@param config 
	 *	@throws ServletException 
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WValuePreference.init");
	}   //  init

	/**
	 *	Process the HTTP Get request.
	 *	Initial Call
	 *	@param request 
	 *	@param response 
	 *	@throws ServletException 
	 *	@throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//  Get Session attributes		
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
	  	WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}

		if (ws == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		
		String m_UpdateResult = "";		
		WebDoc doc = null;
		//Get/Set Parameter	
		m_ctx = wsc.ctx;
		m_AD_Window_ID = WebUtil.getParameterAsInt(request, "AD_Window_ID");
		m_AD_Client_ID = WebUtil.getParameterAsInt(request, "AD_Client_ID");
		m_AD_Org_ID = WebUtil.getParameterAsInt(request, "AD_Org_ID");
		m_AD_User_ID = WebUtil.getParameterAsInt(request, "AD_User_ID");
		m_Attribute = WebUtil.getParameter(request, "Attribute");
		m_DisplayAttribute = WebUtil.getParameter(request, "DisplayAtrribute");
		m_Value = WebUtil.getParameter(request, "Value");
		m_DisplayValue = WebUtil.getParameter(request, "DisplayValue");
		m_DisplayType = WebUtil.getParameterAsInt(request, "DisplayType");
		m_role = MRole.getDefault (wsc.ctx, false);
		m_Client = false;
		m_User = false;
		m_Window = false;
		m_Org = false;
			
		doc = createParameterPage(ws, wsc, request, m_UpdateResult);

		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request.
	 *  Get Parameters and Process
	 *	@param request 
	 *	@param response 
	 *	@throws ServletException 
	 *	@throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		
		WebDoc doc = null;		
		//  Get Session attributes
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
	  	WWindowStatus ws = WWindowStatus.get(request);
	  	String	m_UpdateResult = "";
	  	
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		
		log.info("This is #1");
		m_Client = WebUtil.getParameterAsBoolean(request, "Client");
		m_User = WebUtil.getParameterAsBoolean(request, "User");
		m_Window = WebUtil.getParameterAsBoolean(request, "Window");
		m_Org = WebUtil.getParameterAsBoolean(request, "Org");
		log.info("This is #2");
		String m_Action = WebUtil.getParameter(request, "PostAction");
		
		
		setExplanation();
		
		log.info("This is the m_Action"+m_Action);
		if (m_Action.equals("Delete"))
				m_UpdateResult = delete( m_Client, m_User,  m_Window,  m_Org);
		else if (m_Action.equals("Add"))
				m_UpdateResult = insert( m_Client, m_User,  m_Window,  m_Org);		
		
		log.info("This is the call");
		doc = createParameterPage(ws, wsc, request, m_UpdateResult);
		
		WebUtil.createResponse(request, response, this, null, doc, false);
		return;
		
		//Modified by Rob Klein 6/01/07
		//WebDoc doc = createProcessPage(request, AD_Process_ID, AD_Window_ID);
		//createProcessPage(request, response, AD_Process_ID, AD_Window_ID);
		//
		
	}   //  doPost

	//Modified by Rob Klein 4/29/07
	/**************************************************************************
	 * 	Create Parameter Page
	 * 	@param wsc web session context
	 *	@param AD_Menu_ID Menu
	 *	@return Page
	 */
	private WebDoc createParameterPage (WWindowStatus ws, WebSessionCtx wsc, HttpServletRequest request,
			String m_UpdateResult)
	{
		form myForm = null;
		myForm = new form("WValuePreference")
		.setName("valuepreference");		
		
		table table = new table("0", "0", "5", "100%", null);		
		table.setID("WValuePrefTable");
		tr line = new tr();
		line.addElement(new td("&nbsp;").setRowSpan(6));
		table.addElement(line);
		
				
		//Attribute Field
		line
			.addElement(getLabel(Msg.translate(m_ctx,"Attribute")))		
			.addElement(new td(new input(input.TYPE_TEXT, "DisplayAttribute", m_DisplayAttribute)));
		table.addElement(line);
		
		//Key Field		
		line = new tr();		
		line
			.addElement(getLabel(Msg.translate(m_ctx,"Key")))				
			.addElement(new td(new input(input.TYPE_TEXT, "DisplayAttribute", m_DisplayValue)));
		table.addElement(line);
		line = new tr();
		line.addElement(new td("&nbsp;").setRowSpan(6));
		
		//Checkboxs Field
		table tablecheckbox = new table("0", "0", "5", "80%", null);
		line = new tr();
		input checkbox1 = new input(input.TYPE_CHECKBOX, "Client", m_AD_Client_ID);
		checkbox1.setChecked(m_Client);
		checkbox1.setOnClick("updateValuePref('Update');");
		checkbox1.setTagText(Msg.translate(m_ctx,"AD_Client_ID"));
		input checkbox2 = new input(input.TYPE_CHECKBOX, "Org", m_AD_Org_ID);		
		checkbox2.setChecked(m_Org);
		checkbox2.setOnClick("updateValuePref('Update');");
		checkbox2.setTagText(Msg.translate(m_ctx,"AD_Org_ID"));
		input checkbox3 = new input(input.TYPE_CHECKBOX, "User", m_AD_User_ID);
		checkbox3.setChecked(m_User);
		checkbox3.setOnClick("updateValuePref('Update');");
		checkbox3.setTagText(Msg.translate(m_ctx,"AD_User_ID"));
		input checkbox4 = new input(input.TYPE_CHECKBOX, "Window", m_AD_Window_ID);
		checkbox4.setChecked(m_Window);
		checkbox4.setOnClick("updateValuePref('Update');");
		checkbox4.setTagText(Msg.translate(m_ctx,"AD_Window_ID"));
		line		
			.addElement(new td(checkbox1).setWidth("25%"))
			.addElement(new td(checkbox2).setWidth("25%"))
			.addElement(new td(checkbox3).setWidth("25%"))
			.addElement(new td(checkbox4).setWidth("25%"));			
		tablecheckbox.addElement(line);
		table.addElement(tablecheckbox);
		line = new tr();
		line.addElement(new td("&nbsp;").setRowSpan(6));
		
		//Explination
		table tableexplination = new table("0", "0", "5", "80%", null);		
		setExplanation();
		line.addElement(new td(Msg.translate(m_ctx,m_Explination)).setAlign(AlignType.CENTER));
		tableexplination.addElement(line);
		table.addElement(tableexplination);
		
		line = new tr();
		line.addElement(new td("&nbsp;").setRowSpan(6));
		
		//Update Results
		table tableresult = new table("0", "0", "5", "80%", null);		
		line.addElement(new td(Msg.translate(m_ctx,m_UpdateResult)).setAlign(AlignType.CENTER));
		tableresult.addElement(line);
		table.addElement(tableresult);
		
		
		line = new tr();
		line.addElement(new td("&nbsp;").setRowSpan(6));
		//	Reset
		String textbtn = "Delete";
		if (wsc.ctx != null)
			textbtn = Msg.getMsg (wsc.ctx, "Delete");		
		input restbtn = new input(input.TYPE_BUTTON, textbtn, "  "+textbtn);		
		restbtn.setID(textbtn);
		restbtn.setOnClick("updateValuePref('Delete')");
		restbtn.setClass("deletebtn");	
		
		//	Submit
		textbtn = "Submit";
		if (wsc.ctx != null)
			textbtn = Msg.getMsg (wsc.ctx, "Submit");		
		input submitbtn = new input(input.TYPE_BUTTON, textbtn, "  "+textbtn);		
		submitbtn.setID(textbtn);
		submitbtn.setOnClick("updateValuePref('Add')");
		submitbtn.setClass("submitbtn");
		
		//	Close
		textbtn = "Close";
		if (wsc.ctx != null)
			textbtn = Msg.getMsg (wsc.ctx, "Close");		
		input closebtn = new input(input.TYPE_SUBMIT, textbtn, "  "+textbtn);		
		closebtn.setID(textbtn);
		closebtn.setClass("closebtn");
		closebtn.setOnClick ("self.close();return false;");	
		
		table tablebuttons = new table("0", "0", "5", "100%", null);
		tablebuttons.addElement(new tr()
		.addElement(new td(null, AlignType.CENTER, AlignType.MIDDLE, false, 
				restbtn))
		.addElement(new td(null, AlignType.CENTER, AlignType.MIDDLE, false, 
				submitbtn))
		.addElement(new td(null, AlignType.CENTER, AlignType.MIDDLE, false, 
				closebtn)));
		table.addElement(tablebuttons);
		myForm.addElement(table);
		
		WebDoc doc = WebDoc.createPopup ("Set Value Preference");		

		td center = doc.addWindowCenter(false);
		
		myForm
		.addElement(new input(input.TYPE_HIDDEN, "PostAction", ""))
		.addElement(table);
		
		center.addElement(myForm);
		
		return doc;			
	}	//	createParameterPage
		
	/**
	 * 	Get Select Field
	 *	@param lookup lookup
	 *	@param dataValue default value
	 *	@return selction td
	 */
	private td createSelectField (String m_columnName, option[] options)
	{		
		select sel = new select(m_columnName, options);
		sel.setID(m_columnName);
		sel.setDisabled(false);
		sel.setClass("Cmandatory");
		
		//
		return createTD(sel);

	}	//	getSelectField
	/**
	 * 	Create Left Top aligned TD
	 *	@param element element
	 *	@return td table data
	 */
	private td createTD (Element element)
	{
		td td = new td()
			.addElement(element)
			.setAlign(AlignType.LEFT)
			.setVAlign(AlignType.TOP);	
		return td;
	}	//	createTD
	/**
	 * 	Get the field Label
	 *	@return label
	 */
	public td getLabel(String mLabel)
	{		
		//
		label myLabel = new label(mLabel + "F", null, Util.maskHTML(mLabel));
		myLabel.setID(mLabel + "L");		
		//
		td td = new td()
			.addElement(myLabel)
			.setAlign(AlignType.RIGHT)
			.setVAlign(AlignType.TOP);
		return td;
	}	//	getLabel
	
	/**
	 *  Set Explanation
	 */
	private void setExplanation()
	{	
		/** @todo translation */
		StringBuffer expl = new StringBuffer("For ");
		if (m_Client && m_Org)
			expl.append("this Client and Organization");
		else if (m_Client && !m_Org)
			expl.append("all Organizations of this Client");
		else if (!m_Client && m_Org)
		{
			m_Org = false;
			expl.append("entire System");
		}
		else
			expl.append("entire System");
		//
		if (m_User)
			expl.append(", this User");
		else
			expl.append(", all Users");
		//
		if (m_Window)
			expl.append(" and this Window");
		else
			expl.append(" and all Windows");
		//
		if (Env.getLanguage(m_ctx).isBaseLanguage())
		{
			m_Explination =  expl.toString ();			
		}		
	}   //  setExplanation	
	/**
	 *  Delete Preference
	 *  @return number of rows deleted
	 */
	public String delete(Boolean m_Client,Boolean m_User, Boolean m_Window, Boolean m_Org)
	{
		log.info("");
		String	m_UpdateResult;
		StringBuffer sql = new StringBuffer ("DELETE FROM AD_Preference WHERE ");
		sql.append("AD_Client_ID=").append(m_Client ? m_AD_Client_ID : 0);
		sql.append(" AND AD_Org_ID=").append(m_Org ? m_AD_Org_ID : 0);
		if (m_User)
			sql.append(" AND AD_User_ID=").append(m_AD_User_ID);
		else
			sql.append(" AND AD_User_ID IS NULL");
		if (m_Window)
			sql.append(" AND AD_Window_ID=").append(m_AD_Window_ID);
		else
			sql.append(" AND AD_Window_ID IS NULL");
		sql.append(" AND Attribute='").append(m_Attribute).append("'");
		//
		log.fine( sql.toString());
		int no = DB.executeUpdate(sql.toString(), null);
		if (no > 0){
			Env.setContext(m_ctx, getContextKey(m_Window), (String)null);
			m_UpdateResult = no+" Record Deleted";			
		}
		else
			m_UpdateResult = "Record not found for deletion";
		return m_UpdateResult;
	}   //  delete

	/**
	 *  Get Context Key
	 *  @return Context Key
	 */
	private String getContextKey(Boolean m_Window)
	{
		if (m_Window)
			return "P" + m_AD_Window_ID + "|" + m_Attribute;
		else
			return "P|" + m_Attribute;
	}   //  getContextKey

	/**
	 *  Save to Disk
	 */
	public String insert(Boolean m_Client,Boolean m_User, Boolean m_Window, Boolean m_Org)
	{
		log.info("");
		String	m_UpdateResult;
		int no=0;
		//  --- Delete first
		m_UpdateResult  = delete(m_Client, m_User, m_Window, m_Org);
		
		//	Handle NULL
		if (m_Value == null || m_Value.length() == 0)
		{
			if (DisplayType.isLookup(m_DisplayType))
				m_Value = "-1";	//	 -1 may cause problems (BPartner - M_DiscountSchema
			else if (DisplayType.isDate(m_DisplayType))
				m_Value = " ";
			else
			{	
				m_UpdateResult = m_UpdateResult+" Can not update record";
				return m_UpdateResult;
			}
		}

		//  --- Inserting
		int Client_ID = m_Client ? m_AD_Client_ID : 0;
		int Org_ID = m_Org ? m_AD_Org_ID : 0;
		int AD_Preference_ID = DB.getNextID(m_ctx, "AD_Preference", null);
		//
		StringBuffer sql = new StringBuffer ("INSERT INTO AD_Preference ("
			+ "AD_Preference_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,CreatedBy,Updated,UpdatedBy,"
			+ "AD_Window_ID, AD_User_ID, Attribute, Value) VALUES (");
		sql.append(AD_Preference_ID).append(",").append(Client_ID).append(",").append(Org_ID)
			.append(", 'Y',SysDate,").append(m_AD_User_ID).append(",SysDate,").append(m_AD_User_ID).append(", ");
		if (m_Window)
			sql.append(m_AD_Window_ID).append(",");
		else
			sql.append("NULL,") ;
		if (m_User)
			sql.append(m_AD_User_ID).append(",");
		else
			sql.append("NULL,");
		//
		sql.append(DB.TO_STRING(m_Attribute)).append(",").append(DB.TO_STRING(m_Value)).append(")");
		//
		log.fine( sql.toString());
		no = DB.executeUpdate(sql.toString(), null);
		if (no >0)
			m_UpdateResult = no+" Record Inserted";
		else
			m_UpdateResult = "Record not Inserted";
		
		return m_UpdateResult;
	}   //  insert
	
}   //  WValuePrefernce
