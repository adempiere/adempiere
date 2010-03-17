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
import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.button;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.util.CLogger;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;

public class WAccount extends HttpServlet
{
     /**
	 * 
	 */
	private static final long serialVersionUID = 7932467581955114222L;
	
	private static final String FIELD_NAME = "FieldName";
     private static final String FIELD_VALUE = "FieldValue";
     private static final String P_Command   = "PCommand";
     private static final String P_FORM_NAME   = "PForm";

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WAccount.init");
	} 
	private static CLogger log = CLogger.getCLogger(WAccount.class);
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.config( "WAccount.doGet");
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null || ws == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		String columnName = WebUtil.getParameter (request, "ColumnName");
		GridField mField = ws.curTab.getField(columnName);
		log.config(" ColumnName=" + columnName + ", MField=" + mField.toString());
		if (mField == null  || columnName == null ||  columnName.equals(""))
		{
			WebUtil.createTimeoutPage(request, response, this,
				Msg.getMsg(wsc.ctx, "ParameterMissing"));
			return;
		}
		String target = "opener.document.WForm." + columnName;

		WebDoc doc = WebDoc.create (mField.getHeader());
		body body = doc.getBody();
		body.setOnBlur("self.focus();");
		body.addElement(fillTable(ws, mField, target));

		button reset = new button();
		reset.addElement("Reset");               
		reset.setOnClick(target + "D.value='';" + target + "F.value='';window.close();");
		button cancel = new button();
		cancel.addElement("Cancel");              
		cancel.setOnClick("window.close();");
		body.addElement(new p(AlignType.RIGHT)
			.addElement(reset)
			.addElement("&nbsp")
			.addElement(cancel));
		WebUtil.createResponse (request, response, this, null, doc, true);
	} 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		log.config( "WAccount.doPost");
		doGet(request, response);
	} 
	private table fillTable (WWindowStatus ws, GridField mField, String target)
	{
		table table = new table("1");
		tr line = new tr();
		line.addElement(new th("&nbsp")).addElement(new th(Msg.translate(ws.ctx, "Name")));
		table.addElement(line);

		Lookup lookup = mField.getLookup();
		lookup.fillComboBox(mField.isMandatory(false), true, true, true); 
		int size = lookup.getSize();
		for (int i = 0; i < size; i++)
		{
			Object lValue = lookup.getElementAt(i);
			if (!(lValue != null && lValue instanceof KeyNamePair))
				continue;
			KeyNamePair np = (KeyNamePair)lValue;
			button button = new button();
			button.addElement("&gt;");
			StringBuffer script = new StringBuffer(target);
			script.append("D.value='").append(np.getKey()).append("';")
				.append(target).append("F.value='").append(np.getName()).append("';window.close();");
			button.setOnClick(script.toString());
			line = new tr();
			line.addElement(new td(button));
			String name = np.getName();
			if (name == null || name.length() == 0)
				name = "&nbsp";
			line.addElement(new td(name));
			table.addElement(line);
		}
		lookup.fillComboBox(true);
		return table;
	}
}