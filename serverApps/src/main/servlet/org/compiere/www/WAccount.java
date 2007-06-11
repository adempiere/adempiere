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
import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  WAccount Servlet.
 *  <p>
 *  The servlet is invoked by a parent window via
 *  <code>
 *  WAccount?FormName=formName%ColumnName=columnName
 *  </code>
 *  and assumes that in the opening window/form there are two fields
 *  <code>
 *  opener.document.formName.columnName - The (hidden) field for the ID
 *  opener.document.formName.columnName_D - The display field for the value
 *  </code>
 *  When selecting an entry, the window is closed and the value of the two fields set.
 *
 *  @author Jorg Janke
 *  @version  $Id: WAccount.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WAccount extends HttpServlet
{
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
			throw new ServletException("WAccount.init");
	}   //  init
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WAccount.class);

	/**
	 * Process the HTTP Get request - initial Start
	 * Needs to have parameters FormName and ColumnName
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.config("WAccount.doGet");
		WebSessionCtx wsc = WebSessionCtx.get(request); 
		WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null || ws == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		//  Get Mandatory Parameters
		String formName = WebUtil.getParameter (request, "FormName");
		String columnName = WebUtil.getParameter (request, "ColumnName");
		//
		GridField mField = ws.curTab.getField(columnName);
		//Modified by Rob Klein 4/29/07
		log.config("FormName=" + formName + ", ColumnName=" + columnName + ", MField=" + mField.toString());		
		if (mField == null ||  columnName == null || columnName.equals(""))
		{
			WebUtil.createTimeoutPage(request, response, this,  
				Msg.getMsg(wsc.ctx, "ParameterMissing"));
			return;
		}
	//	Object value = ws.curTab.getValue(columnName);
		String target = "opener.document." + formName + "." + columnName;
		String targetBase = "'" + columnName;
		//Modifeid by Rob Klein 4/229/07
		//  Create Document
		WebDoc doc = WebDoc.createPopup (mField.getHeader());
		body body = doc.getBody();
		body.setOnBlur("self.focus();");
		body.addElement(fillTable(ws, mField, targetBase));
		//Modified by Rob Klein 4/29/07
		//  Reset, Cancel
		button reset = new button();
		reset.addElement("Reset");                      //  translate
		reset.setOnClick(targetBase + "F.value='';" + targetBase + "D.value='';self.close();");
		button cancel = new button();
		cancel.addElement("Cancel");                    //  translate
		cancel.setOnClick("self.close();return false;");
		body.addElement(new p(AlignType.RIGHT)
			.addElement(reset)
			.addElement("&nbsp")
			.addElement(cancel));
		//
	//	log.fine( doc.toString());
		WebUtil.createResponse (request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request - perform doGet
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.config( "WAccount.doPost");
		doGet(request, response);
	}   //  doPost


	/*************************************************************************/

	/**
	 *  Fill Table (Generic)
	 *
	 * @param ws        WindowStatus
	 * @param mField    the Field
	 * @param target    target field string
	 * @return  Table with selection
	 */
	private table fillTable (WWindowStatus ws, GridField mField, String target)
	{
		table table = new table("1");
		//Modified by Rob klein 4/29/07
		table.setClass("table-autosort table-autostripe table-stripeclass:alternate");
		table.addElement("<thead>");
		tr line = new tr();
		line.addElement(new th("&nbsp")).addElement(new th(Msg.translate(ws.ctx, "Name")).setClass("table-sortable:default"));
		table.addElement(line);
		table.addElement("</thead>");
		table.addElement("<tbody>");
		
		//  Fill & list options
		Lookup lookup = mField.getLookup();
		lookup.fillComboBox(mField.isMandatory(false), true, true, true);   //  no context check
		int size = lookup.getSize();
		for (int i = 0; i < size; i++)
		{
			Object lValue = lookup.getElementAt(i);
			if (!(lValue != null && lValue instanceof KeyNamePair))
				continue;
			//
		//	log.fine( lValue.toString());
			KeyNamePair np = (KeyNamePair)lValue;
			button button = new button();
			button.addElement("&gt;");
			//Modified by Rob Klein 4/29/07
			StringBuffer script = new StringBuffer();
			script
				//.append("';closePopup();")
				.append("startUpdate(").append(target).append("F',")
				.append(target).append("D','").append(np.getKey()).append("',")
				.append(target).append("F','").append(np.getName())
				.append("');return false;");
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
		table.addElement("</tbody>");
		return table;
	}   //  fillTable

}   //  WAccount
