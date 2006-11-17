/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  WLookup Servlet.
 *  <p>
 *  The servlet is invoked by a parent window via
 *  <code>
 *  WLookup?FormName=formName%ColumnName=columnName
 *  </code>
 *  and assumes that in the opening window/form there are two fields
 *  <code>
 *  opener.document.formName.columnName - The (hidden) field for the ID
 *  opener.document.formName.columnName_D - The display field for the value
 *  </code>
 *  When selecting an entry, the window is closed and the value of the two fields set.
 *
 *  @author Jorg Janke
 *  @version  $Id: WLookup.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WLookup extends HttpServlet
{
	/**	Logger			*/
	protected static CLogger	log = CLogger.getCLogger(WLookup.class);
	
	/**
	 * Initialize global variables
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WLookup.init");
	}   //  init

	/**
	 * Process the HTTP Get request - initial Start
	 * Needs to have parameters FormName and ColumnName
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		WebEnv.dump(request);
		WebEnv.dump(request.getSession());
		//
		WWindowStatus ws = WWindowStatus.get(request);
		if (ws == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		//  Get Mandatory Parameters
		String columnName = WebUtil.getParameter (request, "ColumnName");
		log.info ("ColumnName=" + columnName + " - " + ws.toString());
		//
		GridField mField = ws.curTab.getField(columnName);
		log.config("ColumnName=" + columnName 
			+ ", MField=" + mField);
		if (mField == null || columnName == null 
			|| columnName.equals(""))
		{
			WebUtil.createErrorPage(request, response, this, 
				Msg.getMsg(ws.ctx, "ParameterMissing"));
			return;
		}
		//	parent = framesetWindow
		//	Label - Dtata - Field - Button
		String targetBase = "parent.WWindow." + WWindow.FORM_NAME + "." + columnName;
	//	Object value = ws.curTab.getValue(columnName);

		//  Create Document
		WebDoc doc = WebDoc.createPopup (mField.getHeader());
		doc.addPopupClose();

		boolean hasDependents = ws.curTab.hasDependants(columnName);
		boolean hasCallout = mField.getCallout().length() > 0;
		
		//  Reset
		button reset = new button();
		reset.addElement("Reset");                      //  translate
		String script = targetBase + "F.value='';" + targetBase + "D.value='';closePopup();";
		if (hasDependents || hasCallout)
			script += "startUpdate(" + targetBase + "F);";
		reset.setOnClick(script);
		//
		doc.getTable().addElement(new tr()
			.addElement(fillTable(ws, mField, targetBase, hasDependents || hasCallout))
			.addElement(reset));
		//
		doc.addPopupClose();
	//	log.trace(log.l6_Database, doc.toString());
		WebUtil.createResponse (request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request - perform Get
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		log.config("");
		doGet(request, response);
	}   //  doPost


	/**************************************************************************
	 *  Fill Table (Generic)
	 *
	 *	@param ws        WindowStatus
	 *	@param mField    the Field
	 *	@param targetBase target field string - add field Type
	 *	@param addStart add startUpdate
	 *	@return  Table with selection
	 */
	private table fillTable (WWindowStatus ws, GridField mField, 
		String targetBase, boolean addStart)
	{
		/**
		if (mField.getColumnName().equals("C_BPartner_ID"))
			return fillTable_BPartner (ws, mField, target);
		else if (mField.getColumnName().equals("M_Product_ID"))
			return fillTable_Product (ws, mField, target);
		**/
		//
		table table = new table("1");	//	Border 1
		table.setID("WLookup");
		tr line = new tr();
		line.addElement(new th("&nbsp")).addElement(new th(Msg.translate(ws.ctx, "Name")));
		table.addElement(line);

		Lookup lookup = mField.getLookup();
		log.info(mField.getColumnName());
		
		//  Fill & list options
		lookup.fillComboBox(mField.isMandatory(false), true, true, true);   //  no context check
		int size = lookup.getSize();
		for (int i = 0; i < size; i++)
		{
			Object lValue = lookup.getElementAt(i);
			if (!(lValue != null && lValue instanceof KeyNamePair))
				continue;
			//
		//	log.trace(log.l6_Database, lValue.toString());
			KeyNamePair np = (KeyNamePair)lValue;
			button button = new button();
			button.addElement("&gt;");
			StringBuffer script = new StringBuffer();
			script.append(targetBase).append("D.value='").append(np.getKey()).append("';")
				.append(targetBase).append("F.value='").append(np.getName())
				.append("';closePopup();");
			if (addStart)
				script.append("startUpdate(").append(targetBase).append("F);");
			button.setOnClick(script.toString());
			//
			line = new tr();
			line.addElement(new td(button));
			String name = np.getName();
			if (name == null || name.length() == 0)
				name = "&nbsp";
			line.addElement(new td(name));
			table.addElement(line);
		}
		//  Restore
		lookup.fillComboBox(true);
		return table;
	}   //  fillTable
	
	
	/**************************************************************************
	 *  Fill Table BPartner
	 *
	 * @param ws        WindowStatus
	 * @param mField    the Field
	 * @param targetBase    target field string
	 * @return  Table with selection
	 */
	private table fillTable_BPartner (WWindowStatus ws, GridField mField, String targetBase)
	{
		return null;
	}   //  fillTable_BPartner

	/**
	 *  Fill Table Product
	 *
	 * @param ws        WindowStatus
	 * @param mField    the Field
	 * @param targetBase    target field string
	 * @return  Table with selection
	 */
	private table fillTable_Product (WWindowStatus ws, GridField mField, String targetBase)
	{
		return null;
	}   //  fillTable_Product

}   //  WLookup
