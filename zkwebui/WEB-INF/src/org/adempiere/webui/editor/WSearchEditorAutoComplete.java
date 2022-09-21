/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Cristina Ghita, c.ghita@metas.ro,  METAS GROUP  					  *
 * FR ADEMPIERE-191 Autocomplete functionality       						  *
 * https://adempiere.atlassian.net/browse/ADEMPIERE-190                       *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.webui.editor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.POWrapper;
import org.adempiere.webui.component.AutoCompleter;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MTable;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.Event;

public class WSearchEditorAutoComplete extends AutoCompleter
{

	private static final long serialVersionUID = 1232991620268493371L;

	/**
	 * Alters text from accented to unaccented or vice-versa
	 * 
	 * Params: String - text to be altered numeric - 1 for accented->unaccented ; 2 for unaccented->accented
	 */

	public static final String FUNC_unaccent_string = "unaccent_string";

	private final WSearchEditor editor;
	private final MLookup lookup;
	private final String[] searchColumns;
	private final String[] searchColumnsSQL;

	public WSearchEditorAutoComplete(WSearchEditor editor, MLookup lookup)
	{
		super();
		this.editor = editor;
		this.lookup = lookup;
		//
		final MTable table = MTable.get(Env.getCtx(), lookup.getTableName());
		final List<String> searchList = new ArrayList<String>();
		final List<String> searchSQLList = new ArrayList<String>();

		for (MColumn c : table.getColumns(false))
		{
			if (DisplayType.isText(c.getAD_Reference_ID())
					&& (c.isIdentifier() || c.isSelectionColumn()))
			{
				searchList.add(c.getColumnName());
				if (c.isVirtualColumn())
				{
					searchSQLList.add(c.getColumnSQL());
				}
				else
				{
					searchSQLList.add(c.getColumnName());
				}
			}
		}

		this.searchColumns = searchList.toArray(new String[searchList.size()]);
		this.searchColumnsSQL = searchSQLList.toArray(new String[searchSQLList
				.size()]);
		//
		// Set Popup Mininum Chars:
		final int popupMinimumChars = POWrapper.create(table, I_AD_Table.class)
				.getACTriggerLength();
		if (popupMinimumChars > 0)
		{
			setPopupMinimumChars(popupMinimumChars);
		}
	}

	@Override
	protected boolean updateListData()
	{
		// clearing list
		removeAllItems();
		setDict(null);
		setDescription(null);
		//
		final String search = getSearchText();
		Object userObject = getUserOject();
		if (userObject != null && !isMatching(userObject, search))
		{
			setUserObject(null);
		}
		//
		final ArrayList<Object> list = new ArrayList<Object>();
		boolean truncated = false;

		//
		// Load list from database
		final ArrayList<Object> params = new ArrayList<Object>();
		final String sql = getSelectSQL(search, params);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next())
			{
				if (i > 0 && i > m_maxItems)
				{
					list.add(ITEM_More);
					truncated = true;
					break;
				}
				Object o = fetchUserObject(rs);
				list.add(o);
				i++;
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		// if there is no items on the list return false, to not show the pop-up
		if (list.isEmpty())
		{
			setStyle("background:red");
			return false;
		}

		// If the list has only one item, but that item is not equals with
		// return false to not show any popup
		userObject = getUserOject();
		if (!truncated && list.size() == 1 && userObject != null
				&& list.get(0).equals(userObject))
		{
			log.finest("nothing to do 1");
			return false;
		}

		// if first list item matched then select it
		if (isMatching(list.get(0), search))
		{
			setUserObject(list.get(0));
			return true;
		}

		// List updated, show we need to show the pop-up
		setStyle(defaultStyle);
		return true;
	}

	@Override
	protected String getSelectSQL(String search, List<Object> params)
	{
		String searchSQL = Util.stripDiacritics(search);

		if (!searchSQL.startsWith("%"))
		{
			searchSQL = "%" + searchSQL;
		}
		if (!searchSQL.endsWith("%"))
		{
			searchSQL += "%";
		}

		final StringBuffer sql_select = new StringBuffer();
		final StringBuffer sql_where = new StringBuffer();

		sql_where.append(lookup.getTableName()).append(".IsActive=?");
		params.add(true);

		if (searchColumns.length > 0)
		{
			sql_where.append(" AND (");
			for (int i = 0; i < searchColumns.length; i++)
			{
				sql_select.append(",");
				if (i > 0)
				{
					sql_where.append(" OR ");
				}
				sql_where.append("UPPER(" + FUNC_unaccent_string + "(");
				if (searchColumns[i].equals(searchColumnsSQL[i]))
				{
					sql_select.append(lookup.getTableName()).append(".").append(searchColumnsSQL[i]).append(" AS ").append(searchColumns[i]);
					sql_where.append(lookup.getTableName()).append(".").append(searchColumns[i]);
				}
				else
				{
					sql_select.append(searchColumnsSQL[i]).append(" AS ").append(searchColumns[i]);
					sql_where.append(lookup.getTableName()).append(".").append(searchColumns[i]);
				}
				sql_where.append(", 1)) LIKE UPPER(" + FUNC_unaccent_string + "(?, 1)) ");
				// .append(") LIKE "+DB.TO_STRING(searchSQL));
				params.add(searchSQL);
			}
			// Full generated identifier search
			sql_where.append(" OR UPPER(" + FUNC_unaccent_string + "(ZZ_DisplayName, 1)) LIKE UPPER(" + FUNC_unaccent_string + "(?, 1))");
			params.add(searchSQL);
			//
			sql_where.append(") ");
		}
		else
		{
			log.warning("No search columns found");
			sql_where.append(" AND 1=2 ");
		}

		final MLookupInfo info = lookup.getLookupInfo();
		String sql = info.Query;

		//
		// Dynamic validation:
		if (!info.IsValidated)
		{
			String validation = Env.parseContext(info.ctx, info.WindowNo, info.ValidationCode, false);
			info.parsedValidationCode = validation;
			if (validation.length() == 0 && info.ValidationCode.length() > 0)
			{
				log.fine(info.KeyColumn + ": Loader NOT Validated: " + info.ValidationCode);
			}
			else
			{
				log.fine(info.KeyColumn + ": Loader Validated: " + validation);
				int posFrom = sql.lastIndexOf(" FROM ");
				boolean hasWhere = sql.indexOf(" WHERE ", posFrom) != -1;
				//
				int posOrder = sql.lastIndexOf(" ORDER BY ");
				if (posOrder != -1)
					sql = sql.substring(0, posOrder)
							+ (hasWhere ? " AND " : " WHERE ") + validation
							+ sql.substring(posOrder);
				else
					sql += (hasWhere ? " AND " : " WHERE ") + validation;
				if (CLogMgt.isLevelFinest())
					log.fine(info.KeyColumn + ": Validation=" + validation);
			}
		}

		//
		// Autocomplete filter:
		final StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(sql.replace("," + lookup.getTableName()
				+ ".IsActive FROM " + lookup.getTableName() + " WHERE ",
				" AS ZZ_DisplayName" + "," + lookup.getTableName()
						+ ".IsActive " + sql_select + " FROM "
						+ lookup.getTableName() + " WHERE "));
		int i = sqlBuf.lastIndexOf(" ORDER BY ");
		if (i > 0)
		{
			sqlBuf.insert(i, ") " + lookup.getTableName() + " WHERE " + sql_where);
		}
		else
		{
			sqlBuf.append(") " + lookup.getTableName() + " WHERE " + sql_where);
		}
		sqlBuf.insert(0, "SELECT * FROM (");
		sql = sqlBuf.toString();

		//
		return sql;
	}

	@Override
	protected Object fetchUserObject(ResultSet rs) throws SQLException
	{
		final boolean isNumber = lookup.getColumnName().endsWith("_ID");

		String name = rs.getString(3);
		if (isNumber)
		{
			int key = rs.getInt(1);
			KeyNamePair p = new KeyNamePair(key, name);
			return p;
		}
		else
		{
			String value = rs.getString(2);
			ValueNamePair p = new ValueNamePair(value, name);
			return p;
		}
	}
	
	@Override
	public void setUserObject(Object userObject)
	{
		String textOld = getText();
		//
		super.setUserObject(userObject);
		//
		
		Object value = null;
		if (userObject == null)
		{
			editor.setValue(null);
		}
		else if (userObject instanceof ValueNamePair)
		{
			ValueNamePair vnp = (ValueNamePair)userObject;
			value = vnp.getValue();
		}
		else if (userObject instanceof KeyNamePair)
		{
			KeyNamePair knp = (KeyNamePair)userObject;
			value = knp.getKey();
		}
		else
		{
			log.warning("Not supported - " + userObject + ", class="
					+ userObject.getClass());
			return;
		}
		
		editor.setValue(value);
		if (value == null)
		{
			setText(textOld);
		}
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		// nothing to do
	}
}
