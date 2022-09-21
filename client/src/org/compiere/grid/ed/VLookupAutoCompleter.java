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
package org.compiere.grid.ed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.JTextComponent;

import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.model.POWrapper;
import org.compiere.apps.search.FieldAutoCompleter;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MTable;
import org.compiere.util.CLogMgt;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

public class VLookupAutoCompleter extends FieldAutoCompleter
{

	/**
	 * Alters text from accented to unaccented or vice-versa
	 * 
	 * Params: String - text to be altered numeric - 1 for accented->unaccented ; 2 for unaccented->accented
	 */

	public static final String FUNC_unaccent_string = "unaccent_string";

	private final VLookup editor;
	private final MLookup lookup;
	private final String[] searchColumns;
	private final String[] searchColumnsSQL;

	public VLookupAutoCompleter(JTextComponent comp, VLookup editor,
			MLookup lookup)
	{
		super(comp);
		this.editor = editor;
		this.lookup = lookup;
		//
		MTable table = MTable.get(Env.getCtx(), lookup.getTableName());
		List<String> searchList = new ArrayList<String>();
		List<String> searchSQLList = new ArrayList<String>();
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

		//
		// When editor value is changed (i.e. on focus lost) hide the popup
		editor.addVetoableChangeListener(new VetoableChangeListener()
		{
			@Override
			public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
			{
				hidePopup();
			}
		});
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
	protected String getSelectSQL(final String searchInput, final int caretPosition, final List<Object> params)
	{
		final String search;
		if (caretPosition > 0 && caretPosition < searchInput.length())
		{
			search = new StringBuilder(searchInput).insert(caretPosition, "%").toString();
		}
		else
		{
			search = searchInput;
		}

		String searchSQL = search;
		if (!searchSQL.startsWith("%"))
		{
			searchSQL = "%" + searchSQL;
		}
		if (!searchSQL.endsWith("%"))
		{
			searchSQL += "%";
		}

		StringBuffer sql_select = new StringBuffer();
		StringBuffer sql_where = new StringBuffer();
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
	public boolean isEnabled()
	{
		return this.textBox.hasFocus();
	}

	@Override
	public void setUserObject(Object userObject)
	{
		String textOld = textBox.getText();
		int caretPosition = textBox.getCaretPosition();
		//
		super.setUserObject(userObject);
		// Object valueOld = editor.getValue();
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
		editor.actionCombo(value);
		if (value == null)
		{
			textBox.setText(textOld);
			textBox.setCaretPosition(caretPosition);
		}
	}
}
