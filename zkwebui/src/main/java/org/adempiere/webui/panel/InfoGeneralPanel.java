/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * Zk Port
 * @author Elaine
 * @version	InfoGeneral.java Adempiere Swing UI 3.4.1 
 *
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoGeneralPanel extends InfoPanel implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -665127800885078238L;
	
	private int fieldID = 0;
	private Textbox txt1;
	private Textbox txt2;
	private Textbox txt3;
	private Textbox txt4;
	
	private Label lbl1;
	private Label lbl2;
	private Label lbl3;
	private Label lbl4;
		
	/** String Array of Column Info */
	private ColumnInfo[] m_generalLayout;
	
	/** list of query columns */
	private ArrayList<String> m_queryColumns = new ArrayList<String>();
	
	/** list of query columns (SQL) */
	private ArrayList<String> m_queryColumnsSql = new ArrayList<String>();
	
	/**
	 *	Standard Constructor
	 *  @param queryValue Query Value
	 * 	@param WindowNo window no
	 * 	@param tableName The name of the table to search
	 * 	@param keyColumn The name of the keyColumn in the table.
	 * 	@param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 * 	@param whereClause where clause
	 *  @param lookup True if the column has a lookup - open modal
	 */
	@Deprecated
	public InfoGeneralPanel(String queryValue, int windowNo, String tableName, 
			String keyColumn, boolean multipleSelection, String whereClause) 
	{
		this(windowNo, true, 0, queryValue,  tableName, keyColumn, false, false, whereClause);
	}

	/**
	 *	Standard Constructor
	 *  @param record_id The record ID to find
	 *  @param value Query Value
	 * 	@param WindowNo window no
	 * 	@param tableName The name of the table to search
	 * 	@param keyColumn The name of the keyColumn in the table.
	 * 	@param multiSelection multiple selections
	 * 	@param isSOTrx True if the records should be filtered for sales transactions
	 * 	@param whereClause The where clause of the search
	 */
	public InfoGeneralPanel(int record_id, String value, int windowNo, 
			String tableName, String keyColumn, 
			boolean multipleSelection, String whereClause) 
	{
		this(windowNo, true, record_id, value, tableName, keyColumn, multipleSelection, 
				false, whereClause);
	}
	/**
	 *	Standard Constructor
	 *  @param record_id The record ID to find
	 *  @param value Query Value
	 * 	@param WindowNo window no
	 * 	@param tableName The name of the table to search
	 * 	@param keyColumn The name of the keyColumn in the table.
	 * 	@param multiSelection multiple selections
	 * 	@param isSOTrx True if the records should be filtered for sales transactions
	 * 	@param whereClause The where clause of the search
	 *  @param lookup True if the column has a lookup - open modal
	 */
	public InfoGeneralPanel(int windowNo, boolean modal, int record_id, String value,  
			String tableName, String keyColumn, 
			boolean multipleSelection, boolean saveResults, String whereClause) 
	{
		super (windowNo, modal, tableName, keyColumn, multipleSelection, saveResults, whereClause);
		log.info(tableName + " - " + keyColumn + " - " + whereClause);				
		setTitle(Msg.getMsg(Env.getCtx(), "Info"));
		//
		if (!initInfoTable())  // Populates m_generalLayout
			return;
		//
		setTableLayout(m_generalLayout);
		setFromClause(tableName);
		setOrderClause("2");
		StringBuffer where = new StringBuffer("IsActive='Y'");
		if (whereClause.length() > 0)
			where.append(" AND ").append(p_whereClause);
		setWhereClause(where.toString());
		//
		statInit();
		initInfo (record_id, value);
		//		
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
        {
			prepareAndExecuteQuery();
        }
        //
        p_loadedOK = true;		
	}
	
	private void initComponents()
	{
		
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(lbl1.rightAlign());
		row.appendChild(txt1);
		row.appendChild(lbl2.rightAlign());
		row.appendChild(txt2);
		row.appendChild(lbl3.rightAlign());
		row.appendChild(txt3);
		row.appendChild(lbl4.rightAlign());
		row.appendChild(txt4);

		p_criteriaGrid.appendChild(rows);
		super.setSizes();
	}

	private void statInit()
	{
		txt1 = new Textbox();
		txt2 = new Textbox();
		txt3 = new Textbox();
		txt4 = new Textbox();
		
		lbl1 = new Label();
		lbl2 = new Label();
		lbl3 = new Label();
		lbl4 = new Label();
		
		initComponents();
	}
	
	protected void initInfo()
	{
		initInfo(0,"");
	}
	
	private void initInfo (int record_id, String value)
	{
		//	Set & enable Fields		
		lbl1.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), m_queryColumns.get(0).toString())));
		txt1.setAttribute("zk_component_ID", "Lookup_txt1_" + m_queryColumns.get(0).toString());
		txt1.addEventListener(Events.ON_CHANGE, this);
		//
		if (m_queryColumns.size() > 1)
		{
			lbl2.setValue(Msg.translate(Env.getCtx(), m_queryColumns.get(1).toString()));
			txt2.setAttribute("zk_component_ID", "Lookup_txt2_" + m_queryColumns.get(1).toString());
			txt2.addEventListener(Events.ON_CHANGE, this);
		}
		else
		{
			lbl2.setVisible(false);
			txt2.setVisible(false);
		}
		//
		if (m_queryColumns.size() > 2)
		{
			lbl3.setValue(Msg.translate(Env.getCtx(), m_queryColumns.get(2).toString()));
			txt3.setAttribute("zk_component_ID", "Lookup_txt3_" + m_queryColumns.get(2).toString());
			txt3.addEventListener(Events.ON_CHANGE, this);
		}
		else
		{
			lbl3.setVisible(false);
			txt3.setVisible(false);
		}
		//
		if (m_queryColumns.size() > 3)
		{
			lbl4.setValue(Msg.translate(Env.getCtx(), m_queryColumns.get(3).toString()));
			txt4.setAttribute("zk_component_ID", "Lookup_txt4_" + m_queryColumns.get(3).toString());
			txt4.addEventListener(Events.ON_CHANGE, this);
		}
		else
		{
			lbl4.setVisible(false);
			txt4.setVisible(false);
		}
		//  Set values
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}
		
		if (record_id != 0)
		{
			fieldID = record_id;
		}
		else
		{
			if (value != null && value.length() > 0)
			{
				txt1.setValue(value);
			}
		}
	}

	private boolean initInfoTable ()
	{
		//	Get Query Columns
		
		String sql = "SELECT c.ColumnName, t.AD_Table_ID, t.TableName, c.ColumnSql "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)"
			+ "WHERE c.AD_Reference_ID=10"
			+ " AND t.TableName=?"	//	#1
			//	Displayed in Window
			+ " AND EXISTS (SELECT * FROM AD_Field f "
				+ "WHERE f.AD_Column_ID=c.AD_Column_ID"
				+ " AND f.IsDisplayed='Y' AND f.IsEncrypted='N' AND f.ObscureType IS NULL) "
			+ "ORDER BY c.IsIdentifier DESC, c.SeqNo";
		
		int AD_Table_ID = 0;
		String tableName = null;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, p_tableName);
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next())
			{
				m_queryColumns.add(rs.getString(1));
				String columnSql = rs.getString(4);
				
				if (columnSql != null && columnSql.length() > 0)
					m_queryColumnsSql.add(columnSql);
				else
					m_queryColumnsSql.add(rs.getString(1));
				
				if (AD_Table_ID == 0)
				{
					AD_Table_ID = rs.getInt(2);
					tableName = rs.getString(3);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return false;
		}
		
		//	Miminum check
		if (m_queryColumns.size() == 0)
		{
			log.log(Level.SEVERE, "No query columns found");
			return false;
		}
		
		log.finest("Table " + tableName + ", ID=" + AD_Table_ID 
			+ ", QueryColumns #" + m_queryColumns.size());
		
		//	Only 4 Query Columns
		while (m_queryColumns.size() > 4) 
		{
			m_queryColumns.remove(m_queryColumns.size()-1);
			m_queryColumnsSql.remove(m_queryColumnsSql.size()-1);
		}
		
		//  Set Title
		String title = Msg.translate(Env.getCtx(), tableName + "_ID");  //  best bet
		
		if (title.endsWith("_ID"))
			title = Msg.translate(Env.getCtx(), tableName);             //  second best bet
		
		setTitle(getTitle() + " " + title);

		//	Get Display Columns
		
		ArrayList<ColumnInfo> list = new ArrayList<ColumnInfo>();
		sql = "SELECT c.ColumnName, c.AD_Reference_ID, c.IsKey, f.IsDisplayed, c.AD_Reference_Value_ID, c.ColumnSql "
			+ "FROM AD_Column c"
			+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Tab tab ON (t.AD_Window_ID=tab.AD_Window_ID)"
			+ " INNER JOIN AD_Field f ON (tab.AD_Tab_ID=f.AD_Tab_ID AND f.AD_Column_ID=c.AD_Column_ID) "
			+ "WHERE t.AD_Table_ID=? "
			+ " AND (c.IsKey='Y' OR "
				+ " (f.IsEncrypted='N' AND f.ObscureType IS NULL)) "
			+ "ORDER BY c.IsKey DESC, f.SeqNo";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String columnName = rs.getString(1);
				int displayType = rs.getInt(2);
				boolean isKey = rs.getString(3).equals("Y");
				boolean isDisplayed = rs.getString(4).equals("Y");
				int AD_Reference_Value_ID = rs.getInt(5);
				String columnSql = rs.getString(6);

				if (columnSql == null || columnSql.length() == 0)
					columnSql = columnName;
				
				//  Default
				StringBuffer colSql = new StringBuffer(columnSql);
				Class<?> colClass = null;
				
				if (isKey)
					colClass = IDColumn.class;
				else if (!isDisplayed)
					;
				else if (displayType == DisplayType.YesNo)
					colClass = Boolean.class;
				else if (displayType == DisplayType.Amount)
					colClass = BigDecimal.class;
				else if (displayType == DisplayType.Number || displayType == DisplayType.Quantity)
					colClass = Double.class;
				else if (displayType == DisplayType.Integer)
					colClass = Integer.class;
				else if (displayType == DisplayType.String || displayType == DisplayType.Text || displayType == DisplayType.Memo)
					colClass = String.class;
				else if (DisplayType.isDate(displayType))
					colClass = Timestamp.class;
				//  ignore Binary, Button, ID, RowID
				else if (displayType == DisplayType.List)
				{
					if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
						colSql = new StringBuffer("(SELECT l.Name FROM AD_Ref_List l WHERE l.AD_Reference_ID=")
							.append(AD_Reference_Value_ID).append(" AND l.Value=").append(columnSql)
							.append(") AS ").append(columnName);
					else
						colSql = new StringBuffer("(SELECT t.Name FROM AD_Ref_List l, AD_Ref_List_Trl t "
							+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID AND l.AD_Reference_ID=")
							.append(AD_Reference_Value_ID).append(" AND l.Value=").append(columnSql)
							.append(" AND t.AD_Language='").append(Env.getAD_Language(Env.getCtx()))
							.append("') AS ").append(columnName);
					colClass = String.class;
				}

				if (colClass != null)
				{
					list.add(new ColumnInfo(Msg.translate(Env.getCtx(), columnName), colSql.toString(), colClass));
					log.finest("Added Column=" + columnName);
				}
				else
					log.finest("Not Added Column=" + columnName);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return false;
		}
		
		if (list.size() == 0)
		{
			FDialog.error(p_WindowNo, this, "Error", "No Info Columns");
			log.log(Level.SEVERE, "No Info for AD_Table_ID=" + AD_Table_ID + " - " + sql);
			return false;
		}
		
		log.finest("InfoColumns #" + list.size()); 

		//  Convert ArrayList to Array
		m_generalLayout = new ColumnInfo[list.size()];
		list.toArray(m_generalLayout);
		return true;
	}
	
	@Override
	public String getSQLWhere() 
	{
		StringBuffer sql = new StringBuffer();
		if(isResetRecordID())  // Set in Info.java.
			fieldID = 0;
		
		if(!(fieldID==0))
		{
			sql.append(" AND ").append(getTableName()).append(".").append(getKeyColumn()).append(" = ?");
		}
		addSQLWhere (sql, 0, txt1);
		addSQLWhere (sql, 1, txt2);
		addSQLWhere (sql, 2, txt3);
		addSQLWhere (sql, 3, txt4);
		return sql.toString();
	}
	
	private void addSQLWhere(StringBuffer sql, int index, Textbox value)
	{
		if (isValidSQLText(value) && index < m_queryColumns.size())
		{
			// Angelo Dabala' (genied) nectosoft: [2893220] avoid to append string parameters directly because of special chars like quote(s)
			sql.append(" AND UPPER(").append(m_queryColumnsSql.get(index).toString()).append(") LIKE ?");
		}
	}

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 * 	@param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		if (!(fieldID == 0))
			pstmt.setInt(index++, fieldID);
		if (isValidSQLText(txt1))
			pstmt.setString(index++, getSQLText(txt1));
		if (isValidSQLText(txt2))
			pstmt.setString(index++, getSQLText(txt2));
		if (isValidSQLText(txt3))
			pstmt.setString(index++, getSQLText(txt3));
		if (isValidSQLText(txt4))
			pstmt.setString(index++, getSQLText(txt4));
	}   //  setParameters

	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
			txt1.hasChanged()	||
			txt2.hasChanged() ||
			txt3.hasChanged() ||
			txt4.hasChanged()
			);
	}
	
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		txt1.set_oldValue();
		txt2.set_oldValue();
		txt3.set_oldValue();
		txt4.set_oldValue();
		return;
	}
}
