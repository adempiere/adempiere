/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.minigrid.DeleteColumn;
import org.adempiere.webui.component.WListItemRenderer;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.exception.ApplicationException;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MRole;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.KeyNamePair;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WPOSTable extends WListbox {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6494322602024830200L;

	public WPOSTable(){
		super();
		setWidth("100%");
		setHeight("100%");
		setFixedLayout(true);
		setVflex(true);
	}
	
	/** Layout set in prepareTable and used in loadTable.    */
	private ColumnInfo[] m_layout = null;
	/** column class types (e.g. Boolean) */
	private ArrayList<Class> m_modelHeaderClass = new ArrayList<Class>();
	/**	Logger. */
	private static CLogger logger = CLogger.getCLogger(MiniTable.class);
	
	/**
	 *	Load Table from ResultSet - The ResultSet is not closed.
	 *
	 *  @param rs 	ResultSet containing data t enter int the table.
	 *  			The contents must conform to the column layout defined in
	 *  			{@link #prepareTable(ColumnInfo[], String, String, boolean, String)}
	 */
	public void loadTable(ResultSet rs)
	{
		int no = 0;
		int row = 0; // model row
		int col = 0; // model column
		Object data = null;
		int rsColIndex = 0; // index into result set
		int rsColOffset = 1;  //  result set columns start with 1
		Class columnClass; // class of the column

		if (getLayout() == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}

		clearTable();

		try
		{
			while (rs.next())
			{
				row = getItemCount();
				setRowCount(row + 1);
				rsColOffset = 1;
				no++;
				for (col = 0; col < m_layout.length; col++)
				{
					//reset the data value
					data=null;
					columnClass = m_layout[col].getColClass();
					rsColIndex = col + rsColOffset;

					if (isColumnClassMismatch(col, columnClass))
					{
						throw new ApplicationException("Cannot enter a " + columnClass.getName()
								+ " in column " + col + ". " +
								"An object of type " + m_modelHeaderClass.get(col).getSimpleName()
								+ " was expected.");
					}
					
					if (columnClass == IDColumn.class && !m_layout[col].getColSQL().equals("'Row' AS \"Row\""))
					{
						data = new IDColumn(rs.getInt(rsColIndex));
					}
					else if (columnClass == IDColumn.class && m_layout[col].getColSQL().equals("'Row' AS \"Row\""))
					{	
						data = new IDColumn(no);
					}	
					else if (columnClass == Boolean.class)
					{
						data = rs.getString(rsColIndex) == null ?  new Boolean(false) : new Boolean(rs.getString(rsColIndex).equals("Y"));
					}
					else if (columnClass == Timestamp.class)
					{
						data = rs.getTimestamp(rsColIndex);
					}
					else if (columnClass == BigDecimal.class)
					{
						data = rs.getBigDecimal(rsColIndex);
					}
					else if (columnClass == Double.class)
					{
						data = new Double(rs.getDouble(rsColIndex));
					}
					else if (columnClass == Integer.class)
					{
						data = new Integer(rs.getInt(rsColIndex));
					}
					else if (columnClass == KeyNamePair.class)
					{
						// TODO factor out this generation
						String display = rs.getString(rsColIndex);
						int key = rs.getInt(rsColIndex + 1);
						data = new KeyNamePair(key, display);
						rsColOffset++;
					}
					else if (columnClass == DeleteColumn.class)
					{
						// TODO factor out this generation
						data = new DeleteColumn(rs.getInt(rsColIndex));
					}
					else
					{
						// TODO factor out this cleanup
						String s = rs.getString(rsColIndex);
						if (s != null)
						{
							data = s.trim();	//	problems with NCHAR
						}
						else
						{
							data=null;
						}
					}
					//  store in underlying model
					getModel().setDataAt(data, row, col);
				}
			}
		}
		catch (SQLException exception)
		{
			logger.log(Level.SEVERE, "", exception);
		}
		
		autoSize();
		if(getShowTotals())
			addTotals(m_layout);
		
		// repaint the table
		this.repaint();

		logger.config("Row(rs)=" + getRowCount());

		return;
	}	//	loadTable
	
	/**
	 *	Load Table from Object Array.
	 *  @param pos array of Persistent Objects
	 */
	public void loadTable(PO[] pos)
	{
		int row = 0;
		int col = 0;
		int poIndex = 0; // index into the PO array
		String columnName;
		Object data;
		Class columnClass;

		if (m_layout == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}

		//  Clear Table
		clearTable();

		for (poIndex = 0; poIndex < pos.length; poIndex++)
		{
			PO myPO = pos[poIndex];
			row = getRowCount();
			setRowCount(row + 1);

			for (col = 0; col < m_layout.length; col++)
			{
				columnName = m_layout[col].getColSQL();
				data = myPO.get_Value(columnName);
				if (data != null)
				{
					columnClass = m_layout[col].getColClass();

					if (isColumnClassMismatch(col, columnClass))
					{
						throw new ApplicationException("Cannot enter a " + columnClass.getName()
								+ " in column " + col + ". " +
								"An object of type " + m_modelHeaderClass.get(col).getSimpleName()
								+ " was expected.");
					}

					if (columnClass == IDColumn.class)
					{
						data = new IDColumn(((Integer)data).intValue());
					}
					else if (columnClass == Double.class)
					{
						data = new Double(((BigDecimal)data).doubleValue());
					}
				}
				//  store
				getModel().setDataAt(data, row, col);
			}
		}

		autoSize();
		if(getShowTotals())
			addTotals(m_layout);

		// repaint the table
		this.repaint();

		logger.config("Row(array)=" + getRowCount());

		return;
	}	//	loadTable
	/**
	 * @param col
	 * @param columnClass
	 * @return
	 */
	private boolean isColumnClassMismatch(int col, Class columnClass)
	{
		return !columnClass.equals(m_modelHeaderClass.get(col));
	}
	 /**
     * Get the table layout.
     *
     * @return the layout of the table
     * @see #setLayout(ColumnInfo[])
     */
	public ColumnInfo[] getLayout()
	{
		return m_layout;
	}
	public void setColumnClass (int index, Class classType, boolean readOnly)
    {
        setColumnReadOnly(index, readOnly);

        WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();

        renderer.setColumnClass(index, classType);

        m_modelHeaderClass.add(classType);

        return;
    }

	/**
	 * Set the attributes of the column.
	 *
	 * @param classType	The class of data that the column will contain
	 * @param readOnly	Whether the data in the column is read only
	 * @param header	The header text for the column
	 *
	 * @see #setColumnClass(int, Class, boolean)
	 * @see #addColumn(String)
	 */
	public void addColumn(Class classType, boolean readOnly, String header)
	{
		m_modelHeaderClass.add(classType);

		setColumnReadOnly(m_modelHeaderClass.size() - 1, readOnly);

		addColumn(header);

		WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();
		renderer.setColumnClass((renderer.getNoColumns() - 1), classType);

 		return;
	}
	
	/**
	 * Set the attributes of the column.
	 *
	 * @param index		The index of the column to be modified
	 * @param classType	The class of data that the column will contain
	 * @param readOnly	Whether the data in the column is read only
	 * @param header	The header text for the column
	 *
	 * @see #setColumnClass(int, Class, boolean)
	 */
	public void setColumnClass (int index, Class classType, boolean readOnly, String header)
	{
		WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();

		setColumnReadOnly(index, readOnly);

		renderer.setColumnHeader(index, header);

		renderer.setColumnClass(index, classType);

		if (index < m_modelHeaderClass.size())
			m_modelHeaderClass.set(index, classType);
		else
			m_modelHeaderClass.add(classType);

 		return;
	}
	
	/**
	 * Set the column information for the table.
	 *
	 * @param layout	The new layout to set for the table
	 * @see #getLayout()
	 */
	private void setLayout(ColumnInfo[] layout)
	{
		this.m_layout = layout;
		getModel().setNoColumns(m_layout.length);

		return;
	}
	@Override
	public String prepareTable(ColumnInfo[] layout,
            String from,
            String where,
            boolean multiSelection,
            String tableName,boolean addAccessSQL)
    {
        int columnIndex = 0;
        StringBuffer sql = new StringBuffer ("SELECT ");
        setLayout(layout);

        clearColumns();
        setColorColumn(-1); // No color column assigned.
        
        setMultiSelection(multiSelection);
        

        //  add columns & sql
        for (columnIndex = 0; columnIndex < layout.length; columnIndex++)
        {
            //  create sql
            if (columnIndex > 0)
            {
                sql.append(", ");
            }
            sql.append(layout[columnIndex].getColSQL());

            //  adding ID column
            if (layout[columnIndex].isKeyPairCol())
            {
                sql.append(",").append(layout[columnIndex].getKeyPairColSQL());
            }

            //  add to model
            addColumn(layout[columnIndex]);

            // set the colour column
            if (layout[columnIndex].isColorColumn())
            {
                setColorColumn(columnIndex);
            }
            if (layout[columnIndex].getColClass() == IDColumn.class)
            {
                setKeyColumnIndex(columnIndex);
            }
        }

        //  set editors (two steps)
        for (columnIndex = 0; columnIndex < layout.length; columnIndex++)
        {
            setColumnClass(columnIndex,
                        layout[columnIndex].getColClass(),
                        layout[columnIndex].isReadOnly(),
                        layout[columnIndex].getColHeader());
        }

        sql.append( " FROM ").append(from);
        sql.append(" WHERE ").append(where);

        if (from.length() == 0)
        {
            return sql.toString();
        }
        //
        if (addAccessSQL)
        {
            String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(),
                                                        tableName,
                                                        MRole.SQL_FULLYQUALIFIED,
                                                        MRole.SQL_RO);

            logger.finest(finalSQL);

            return finalSQL;
        }
        else
        {
            return sql.toString();
        }
    }   // prepareTable
	
	/**
	 * Clear the table columns from both the model and renderer
	 */
	private void clearColumns()
	{
		((WListItemRenderer)getItemRenderer()).clearColumns();
		getModel().setNoColumns(0);

		return;
	}
	
}
