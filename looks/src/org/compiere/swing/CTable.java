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
package org.compiere.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import org.compiere.util.*;

/**
 *	Model Independent enhanced JTable.
 *  Provides sizing and sorting
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: CTable.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CTable extends JTable
{
	/**
	 *	Default Constructor
	 */
	public CTable()
	{
		super(new DefaultTableModel());
		setColumnSelectionAllowed(false);
		/* ARHIPAC: TEO: BEGIN: ORIGINAL: @arhipac.tools.annotation.Bug(228) -------------------------------------------- *
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/* ARHIPAC: TEO: BEGIN: MODIFIED: @arhipac.tools.annotation.Bug(228) ------------------------------------------- */
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		/* ARHIPAC: TEO: BEGIN: END: @arhipac.tools.annotation.Bug(228) --------------------------------------------------- */
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTableHeader().addMouseListener(new CTableMouseListener());
		setSurrendersFocusOnKeystroke(true);
		//Default row height too narrow
		setRowHeight(getFont().getSize() + 8);
	}	//	CTable

	/** Last model index sorted */
	protected int         		p_lastSortIndex = -1;
	/** Sort direction          */
	protected boolean     		p_asc = true;

	/** Sizing: making sure it fits in a column	*/
	private final int 			SLACK = 15;
	/** Sizing: max size in pt					*/
	private final int 			MAXSIZE = 250;
	/** Model Index of Key Column   */
	protected int              	p_keyColumnIndex = -1;
	
	/**	Logger			*/
	private static Logger log = Logger.getLogger(CTable.class.getName());


	/**
	 * 	Set Model index of Key Column.
	 *  Used for identifying previous selected row after fort complete to set as selected row.
	 *  If not set, column 0 is used.
	 * 	@param keyColumnIndex model index
	 */
	public void setKeyColumnIndex (int keyColumnIndex)
	{
		p_keyColumnIndex = keyColumnIndex;
	}	//	setKeyColumnIndex

	/**
	 * 	Get Model index of Key Column
	 *  @return model index
	 */
	public int getKeyColumnIndex()
	{
		return p_keyColumnIndex;
	}	//	getKeyColumnIndex

	/**
	 * 	Get Current Row Key Column Value
	 *  @return value or null
	 */
	public Object getSelectedKeyColumnValue()
	{
		int row = getSelectedRow();
		if (row != -1 && p_keyColumnIndex != -1)
			return getModel().getValueAt(row, p_keyColumnIndex);
		return null;
	}	//	getKeyColumnValue

	/**
	 *  Get Selected Value or null
	 *  @return value
	 */
	public Object getSelectedValue()
	{
		int row = getSelectedRow();
		int col = getSelectedColumn();
		if (row == -1 || col == -1)
			return null;
		return getValueAt(row, col);
	}   //  getSelectedValue

	/**
	 *  Stop Table Editors and remove focus
	 *  @param saveValue save value
	 */
	public void stopEditor (boolean saveValue)
	{
		//  MultiRow - remove editors
		ChangeEvent ce = new ChangeEvent(this);
		if (saveValue)
			editingStopped(ce);
		else
			editingCanceled(ce);
		//
		if (getInputContext() != null)
			getInputContext().endComposition();
		//  change focus to next
		transferFocus();
	}   //  stopEditor

	
	/**************************************************************************
	 *	Size Columns.
	 *	@param useColumnIdentifier if false uses plain content -
	 *  otherwise uses Column Identifier to indicate displayed columns
	 */
	public void autoSize (boolean useColumnIdentifier)
	{
		TableModel model = this.getModel();
		int size = model.getColumnCount();
		//	for all columns
		for (int c = 0; c < size; c++)
		{
			TableColumn column = getColumnModel().getColumn(c);
			//	Not displayed columns
			if (useColumnIdentifier
				&& (column.getIdentifier() == null
					|| column.getMaxWidth() == 0
					|| column.getIdentifier().toString().length() == 0))
				continue;

			int width = 0;
			//	Header
			TableCellRenderer renderer = column.getHeaderRenderer();
			if (renderer == null)
				renderer = new DefaultTableCellRenderer();
			Component comp = null;
			if (renderer != null)
				comp = renderer.getTableCellRendererComponent
					(this, column.getHeaderValue(), false, false, 0, 0);
			//
			if (comp != null)
			{
				width = comp.getPreferredSize().width;
				width = Math.max(width, comp.getWidth());

				//	Cells
				int col = column.getModelIndex();
				int maxRow = Math.min(20, getRowCount());
				try
				{
					for (int row = 0; row < maxRow; row++)
					{
						renderer = getCellRenderer(row, col);
						comp = renderer.getTableCellRendererComponent
							(this, getValueAt(row, col), false, false, row, col);
						int rowWidth = comp.getPreferredSize().width;
						width = Math.max(width, rowWidth);
					}
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, column.getIdentifier().toString(), e);
				}
				//	Width not greater than 250
				width = Math.min(MAXSIZE, width + SLACK);
			}
			//
			column.setPreferredWidth(width);
		}	//	for all columns
	}	//	autoSize

	
	/**
	 *  Sort Table
	 *  @param modelColumnIndex model column sort index
	 */
	@SuppressWarnings("unchecked")
	protected void sort (int modelColumnIndex)
	{
		int rows = getRowCount();
		if (rows == 0)
			return;
		//  other column
		if (modelColumnIndex != p_lastSortIndex)
			p_asc = true;
		else
			p_asc = !p_asc;
		p_lastSortIndex = modelColumnIndex;
		//
		log.config("#" + modelColumnIndex + " - rows=" + rows + ", asc=" + p_asc);

		//  Selection
		Object selected = null;
		int selRow = getSelectedRow();
		int selCol = p_keyColumnIndex == -1 ? 0 : p_keyColumnIndex;	//	used to identify current row
		if (getSelectedRow() >= 0)
			selected = getValueAt(selRow, selCol);

		//  Prepare sorting
		DefaultTableModel model = (DefaultTableModel)getModel();
		MSort sort = new MSort(0, null);
		sort.setSortAsc(p_asc);
		/* ARHIPAC: teo_sarca: BEGIN: ORIGINAL: @arhipac.tools.annotation.Bug(226) ----------------------------------- *
		//  while something to sort
		sorting:
		while (true)
		{
			//  Create sortList
			ArrayList<MSort> sortList = new ArrayList<MSort>(rows);
			//	fill with data entity
			for (int i = 0; i < rows; i++)
			{
				Object value = model.getValueAt(i, modelColumnIndex);
				sortList.add(new MSort(i, value));
			}
			//	sort list it
			Collections.sort(sortList, sort);
			//  move out of sequence row
			for (int i = 0; i < rows; i++)
			{
				int index = ((MSort)sortList.get(i)).index;
				if (i != index)
				{
		//			log.config( "move " + i + " to " + index);
					model.moveRow(i,i, index);
					continue sorting;
				}
			}
			//  we are done
		//	log.config("done");
			break;
		}   //  while something to sort
		/* ARHIPAC: teo_sarca: MODIFIED: @arhipac.tools.annotation.Bug(226) -------------------------------------------- */
		// This comparator is used to sort vectors of data
		class ColumnSorter<T> implements Comparator<T> {
			int colIndex;
			MSort sort;
			ColumnSorter(int colIndex, MSort sort) {
				this.colIndex = colIndex;
				this.sort = sort;
			}
			public int compare(Object o1, Object o2) {
				Object item1 = ((Vector)o1).get(this.colIndex);
				Object item2 = ((Vector)o2).get(this.colIndex);
				return this.sort.compare(item1, item2);
			}
		};
		Collections.sort(model.getDataVector(), new ColumnSorter<Object>(modelColumnIndex, sort));
		/* ARHIPAC: teo_sarca: END: @arhipac.tools.annotation.Bug(226) ---------------------------------------------------- */
		
		//  selection
		clearSelection();
		if (selected != null)
		{
			for (int r = 0; r < rows; r++)
			{
				if (selected.equals(getValueAt(r, selCol)))
				{
					setRowSelectionInterval(r,r);
					scrollRectToVisible(getCellRect(r, modelColumnIndex, true)); // @arhipac.tools.annotation.Bug(226)
					break;
				}
			}
		}   //  selected != null
	}   //  sort

	/**
	 *  String Representation
	 *  @return info
	 */
	public String toString()
	{
		return new StringBuffer("CTable[").append(getModel()).append("]").toString();
	}   //  toString

	
	/**************************************************************************
	 *  MouseListener
	 */
	class CTableMouseListener extends MouseAdapter
	{
		/**
		 *  Constructor
		 */
		public CTableMouseListener()
		{
			super();
		}   //  CTableMouseListener

		/**
		 *  Mouse clicked
		 *  @param e event
		 */
		public void mouseClicked (MouseEvent e)
		{
			int vc = getColumnModel().getColumnIndexAtX(e.getX());
		//	log.info( "Sort " + vc + "=" + getColumnModel().getColumn(vc).getHeaderValue());
			int mc = convertColumnIndexToModel(vc);
			sort(mc);
		}
	}	//  CTableMouseListener


	@Override
	public void setFont(Font font) {
		super.setFont(font);
		//Update row height
		setRowHeight(getFont().getSize() + 8);
	}

}	//	CTable
