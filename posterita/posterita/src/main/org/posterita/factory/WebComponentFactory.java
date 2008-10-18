/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 */
package org.posterita.factory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ecs.Element;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.select;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.Lookup;
import org.compiere.model.MProcessPara;
import org.compiere.model.MRefList;
import org.compiere.model.MRefTable;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;
import org.posterita.businesslogic.administration.RoleManager;

/**
 * Factory to load a web component which corresponds to the display 
 * type of a process parameter and populates it with the appropriate
 * values as per the constraints of the parameter. 
 * 
 * @author sendy
 * 
 */
public class WebComponentFactory 
{

	public static org.apache.ecs.Element getWebComponent(Properties ctx, MProcessPara parameter) throws Exception 
	{
		Element element = null;
		label name = new label().addElement(parameter.getName());
		td label = new td().addElement(name);
		td editor = new td().setAlign("left");
		

		input textFrom = new input(input.TYPE_TEXT, parameter.getColumnName()+ "From", "");
		input textTo = new input(input.TYPE_TEXT, parameter.getColumnName()	+ "To", "");
		input text = new input(input.TYPE_TEXT, parameter.getColumnName(), "");

		int displayType = parameter.getAD_Reference_ID();

		if (DisplayType.isLookup(displayType) || DisplayType.isID(displayType)) 
		{
			option[] options = getOptions(ctx, parameter);
			select select = new select(parameter.getColumnName(), options);
			editor = editor.addElement(select);
		} 
		else if (displayType == DisplayType.YesNo) 
		{
			input inputCheck = new input(input.TYPE_CHECKBOX, parameter.getColumnName(), "Y");
			editor = editor.addElement(inputCheck);
		} 
		else if (DisplayType.isNumeric(displayType)
				|| displayType == DisplayType.String) // SENDYFIXME need to add calculator for numeric
		{
			if (parameter.isRange()) 
			{
				editor = editor.addElement(textFrom).addElement("-").addElement(textTo);
			} 
			else 
			{
				editor = editor.addElement(text);
			}
		} 
		else if (DisplayType.isDate(displayType)) 
		{
			if (parameter.isRange()) 
			{
				String idFrom = textFrom.getAttribute("name");
				textFrom.setID(idFrom);
				script scriptFrom = getScript(idFrom);
				input calendarFromBtn = getCalendarBtn(idFrom);
				editor = editor.addElement(textFrom).addElement(calendarFromBtn).addElement(scriptFrom).addElement("-");

				String idTo = textTo.getAttribute("name");
				textTo.setID(idTo);
				script scriptTo = getScript(idTo);
				input calendarToBtn = getCalendarBtn(idTo);

				editor = editor.addElement(textTo).addElement(calendarToBtn).addElement(scriptTo);
			} 
			else 
			{
				String id = text.getAttribute("name");
				text.setID(id);
				script script = getScript(id);
				input calendarBtn = getCalendarBtn(id);

				editor = editor.addElement(text).addElement(calendarBtn).addElement(script);
			}
		} 
		else // FIXME need to cater for other display types such as locator, location, etc
		{
			editor = editor.addElement(text);
		}
		
		td tdContent = label.addElement(editor);
		element = new tr().addElement(tdContent);
		return element;
	}

	private static option[] getOptions(Properties ctx, MProcessPara parameter)
			throws Exception 
	{
		int displayType = parameter.getAD_Reference_ID();
		String columnName = parameter.getColumnName();
		String tableName = null;
		String whereClause = null;
		String orderBy = null;
		option[] options = null;

		Lookup lookup = parameter.getLookup();
		if (displayType == DisplayType.Table || displayType == DisplayType.Search)
		{
			int refValueId = parameter.getAD_Reference_Value_ID();

			if (refValueId != 0) 
			{
				MRefTable refTable = getRefTable(ctx, parameter.getAD_Reference_Value_ID(), null);
				tableName = refTable.getAD_Table().getTableName();
				whereClause = refTable.getWhereClause();
				String validation = lookup.getValidation();

				if (validation != null && !validation.equals(""))
				{
					whereClause += " AND " + validation;
				}
				orderBy = refTable.getOrderByClause();
			} 
			else
			{
				tableName = columnName.substring(0, columnName.length() - 3);
			}
		} 
		else if (displayType == DisplayType.TableDir
				|| displayType == DisplayType.Locator
				|| displayType == DisplayType.Location) 
		{
			tableName = columnName.substring(0, columnName.length() - 3);
		}

		if (displayType != DisplayType.List) // get key name pair from table
		{
			ArrayList<KeyNamePair> keyNamePair = getKeyNamePair(ctx, parameter,
					tableName, whereClause, orderBy, null);
			options = new option[keyNamePair.size() + 1];
			int count = 1;
			options[0] = new option(null);

			for (KeyNamePair pair : keyNamePair) 
			{
				options[count] = new option(pair.getName(), pair.getKey());
				options[count].addElement(pair.getName());
				count++;
			}
		} 
		else // get value name list
		{
			ValueNamePair[] refListVNP = MRefList.getList(ctx, parameter
					.getAD_Reference_Value_ID(), false);
			options = new option[refListVNP.length + 1];
			int count = 1;
			options[0] = new option(null);

			for (ValueNamePair pair : refListVNP) 
			{
				options[count] = new option(pair.getName(), pair.getValue());
				options[count].addElement(pair.getName());
				count++;
			}
		}

		return options;
	}

	private static input getCalendarBtn(String id) 
	{
		input calendarBtn = new input(input.TYPE_BUTTON, "", "");
		calendarBtn.setID(id + "Btn");
		calendarBtn.setClass("calendar-icon float-left");

		return calendarBtn;
	}

	private static script getScript(String id)
	{
		script s = new script("function setDate(calendar){};"
				+ "Calendar.setup({ inputField : '" + id
				+ "', ifFormat : '%Y-%m-%d',showTime : true," + "button : '"
				+ id + "Btn', onUpdate : setDate });");
		return s;
	}

	private static MRefTable getRefTable(Properties ctx,
			int reference_Value_ID, String trxName)
	{
		String sql = "SELECT * from AD_Ref_Table where AD_Reference_ID = "
				+ reference_Value_ID;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MRefTable refTable = null;
		try 
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				refTable = new MRefTable(ctx, rs, trxName);
			}
		} 
		catch (SQLException e)
		{
		} 
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return refTable;
	}

	private static ArrayList<KeyNamePair> getKeyNamePair(Properties ctx,
			MProcessPara parameter, String tableName, String whereClause,
			String orderBy, String trxName)
	{
		String orgs = RoleManager.getRoleViewableOrgAccess(ctx);
		StringBuffer sql = new StringBuffer();

		if (whereClause != null && !whereClause.equals(""))
		{
			sql.append(whereClause + " AND");
		}
		sql.append(" isActive = 'Y'").append(
				" AND AD_Client_ID IN (0," + Env.getAD_Client_ID(ctx) + ")")
				.append(" AND AD_Org_ID IN (" + orgs + ")");

		if (orderBy != null && !orderBy.equals(""))
		{
			sql.append(" Order by " + orderBy);
		}

		int ids[] = PO.getAllIDs(tableName, sql.toString(), trxName);
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();

		for (int id : ids) 
		{
			Lookup lookup = parameter.getLookup();
			String value = lookup.getDisplay(id);
			KeyNamePair pair = new KeyNamePair(id, value);
			list.add(pair);
		}
		return list;
	}

}
