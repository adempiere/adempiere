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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.AlignType;
import org.apache.ecs.Element;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.textarea;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.GridField;
import org.compiere.model.MChat;
import org.compiere.model.MChatEntry;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;


/**
 *	Web (window) Chat
 *	
 *  @author Jorg Janke
 *  @version $Id: WChat.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WChat extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6826855534161342772L;
	
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	private MChat			m_chat;
	private boolean	m_readOnly;
	private boolean	m_hasDependents;
	private boolean	m_hasCallout;	
	private int		m_displayLength;
	private String 	m_columnName;
		
	/**
	 * Initialize global variables
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WChat.init");
	}   //  init

	/**
	 * Process the HTTP Get request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.fine("doGet");
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		//
		WebDoc doc = null;
		if (ws == null)
		{
			doc = WebDoc.createPopup("No Context");			
			doc.addPopupClose(wsc.ctx);
		}
		else
		{	
			doc = CreateChatPage (ws, wsc, doc, 0 );		
		}		
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{		
		WebDoc doc = null;
		
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);		
		
		String data = WebUtil.getParameter(request, "chatinput");
		int CM_ChatID = WebUtil.getParameterAsInt(request, "CM_ChatID");
		int AD_Table_ID = WebUtil.getParameterAsInt(request, "AD_Table_ID");
		int record_ID = WebUtil.getParameterAsInt(request, "record_ID");
		String description = WebUtil.getParameter(request, "description");
		
		if (data != null && data.length() > 0)
		{		
			if (CM_ChatID == 0){		
				m_chat = new MChat (wsc.ctx, AD_Table_ID, record_ID, description, null);
				m_chat.save();
			}			
			MChatEntry entry = new MChatEntry(m_chat, data);
			entry.save();
						
		}	//	data to be saved
		
		doc = CreateChatPage (ws, wsc, doc, m_chat.getCM_Chat_ID());
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost
	/**
	 * 	Create Text Field
	 * 	@param data initial value
	 * 	@param rows no of rows
	 *	@return td
	 */
	private td getTextField (String data, int rows)
	{
	textarea text = new textarea (m_columnName, rows, m_displayLength)
	.addElement(Util.maskHTML(data));
	text.setID(m_columnName + "F");
	text.setName(m_columnName);
	text.setDisabled(m_readOnly);	
	//
	if (m_hasDependents || m_hasCallout)
		text.setOnChange("startUpdate(this);");
	
	return createTD(text);
	}
	
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
			td.setColSpan(3);
		return td;
	}	//	createTD

	/**
	 * 	Create Left Top aligned TD
	 *	@param element element
	 *	@return td table data
	 */
	private WebDoc CreateChatPage (WWindowStatus ws, WebSessionCtx wsc, WebDoc doc, int CM_Chat_ID)

	{	
	doc = WebDoc.createPopup ("Chat ");

	td center = doc.addWindowCenter(false);			
	int record_ID = ws.curTab.getRecord_ID();
	log.info("Record_ID=" + record_ID);
	if (record_ID == -1)	//	No Key
	{
		log.info("Record does not exist");
		return doc;
	}
	//	Find display
	String infoName = null;
	String infoDisplay = null;
	for (int i = 0; i < ws.curTab.getFieldCount(); i++)
	{
		GridField field = ws.curTab.getField(i);
		if (field.isKey())
			infoName = field.getHeader();
		if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
			&& field.getValue() != null)
			infoDisplay = field.getValue().toString();
		if (infoName != null && infoDisplay != null)
			break;
	}
	String description = infoName + ": " + infoDisplay;
	//
	
	
	if (ws.curTab.getCM_ChatID() > 0)
		m_chat = new MChat (wsc.ctx, ws.curTab.getCM_ChatID(), null);
	else if (CM_Chat_ID > 0)
		m_chat = new MChat (wsc.ctx, CM_Chat_ID, null);
	else
		m_chat = new MChat (wsc.ctx, ws.curTab.getAD_Table_ID(), record_ID, description, null);
	
	String text = m_chat.getHistory(MChat.CONFIDENTIALTYPE_Internal).toString();
	
	
	form myForm = new form ("WChat")
	.setName("chat");
	myForm.setOnSubmit("this.Submit.disabled=true;return true;");
	if (CM_Chat_ID==0)
		myForm.addElement(new input(input.TYPE_HIDDEN, "CM_ChatID", ws.curTab.getCM_ChatID()));
	else
		myForm.addElement(new input(input.TYPE_HIDDEN, "CM_ChatID", CM_Chat_ID));
	myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Table_ID", ws.curTab.getAD_Table_ID()));
	myForm.addElement(new input(input.TYPE_HIDDEN, "record_ID", record_ID));
	myForm.addElement(new input(input.TYPE_HIDDEN, "description", description));			
	
	table myTable = new table("0", "0", "5", "100%", null);
	myTable.setID("WChatParameter");	
	m_displayLength = 80;	
	
	//history field
	myTable.addElement(new tr()			
	.addElement( new td("History")));
	m_readOnly = true;
	table HistoryTable = new table("1", "0", "5", "100%", null);
	HistoryTable.addElement(new tr()			
	.addElement( new td(text)		
		.setRowSpan(10)
		.setAlign(AlignType.LEFT)
		.setVAlign(AlignType.TOP)		
		.setColSpan(4)));			
	myTable.addElement(HistoryTable);
	
	//input field
	myTable.addElement(new tr()			
	.addElement( new td("Input")));
	m_readOnly = false;
	m_columnName = "chatinput";
	myTable.addElement(new tr()
	.addElement(getTextField ("", 10)));
	
//	 Reset
	String textbtn = "Reset";
	if (wsc.ctx != null)
		text = Msg.getMsg (wsc.ctx, "Reset");		
	input restbtn = new input(input.TYPE_RESET, textbtn, "  "+text);		
	restbtn.setID(text);
	restbtn.setClass("resetbtn");	
	
	//	Submit
	textbtn = "Submit";
	if (wsc.ctx != null)
		text = Msg.getMsg (wsc.ctx, "Submit");		
	input submitbtn = new input(input.TYPE_SUBMIT, textbtn, "  "+text);		
	submitbtn.setID(text);
	submitbtn.setClass("submitbtn");
	
	//	Close
	textbtn = "Close";
	if (wsc.ctx != null)
		text = Msg.getMsg (wsc.ctx, "Close");		
	input closebtn = new input(input.TYPE_SUBMIT, textbtn, "  "+text);		
	closebtn.setID(text);
	closebtn.setClass("closebtn");
	closebtn.setOnClick ("self.close();return false;");	
	
	
	myTable.addElement(new tr()
		.addElement(new td(null, AlignType.RIGHT, AlignType.MIDDLE, false, 
				restbtn))
		.addElement(new td(null, AlignType.CENTER, AlignType.MIDDLE, false, 
				submitbtn ))
		.addElement(new td(null, AlignType.LEFT, AlignType.MIDDLE, false, 
				closebtn)));
	myForm.addElement(myTable);
	center.addElement(myForm);
	
	return doc;
}
	
}	//	WReport
