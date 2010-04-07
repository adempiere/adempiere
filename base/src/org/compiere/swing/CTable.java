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
package org.compiere.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.util.MSort;
import org.jdesktop.swingx.icon.ColumnControlIcon;

/**
 * Model Independent enhanced JTable.
 * Provides sizing and sorting.
 * 
 * @author	Jorg Janke
 * @version	$Id: CTable.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 * 
 * @author	Teo Sarca, SC ARHIPAC SERVICE SRL - BF [ 1585369 ], FR [ 1753943 ]
 */
public class CTable extends JTable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 975975420639030844L;

	/**
	 *	Default Constructor
	 */
	public CTable()
	{
		super(new DefaultTableModel());
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // teo_sarca - FR [ 1753943 ]
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTableHeader().addMouseListener(new CTableMouseListener());
		setSurrendersFocusOnKeystroke(true);
		//Default row height too narrow
		setRowHeight(getFont().getSize() + 8);
		
		setColumnControlVisible(true);
		addHierarchyListener(createHierarchyListener());
	}	//	CTable

	private HierarchyListener createHierarchyListener() {
		return new HierarchyListener() {

			public void hierarchyChanged(HierarchyEvent e) {
				if (e.getChangeFlags() == HierarchyEvent.PARENT_CHANGED)
					configureColumnControl();
			}
			
		};
	}

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
	/** state variable to indicate sorting in progress **/
	protected boolean sorting;
	
	/**	Logger			*/
	private static Logger log = Logger.getLogger(CTable.class.getName());

	/**
     * ScrollPane's original vertical scroll policy. If the column control is
     * visible the policy is set to ALWAYS.
     */
    private int verticalScrollPolicy;
	
    /**
     * Flag to indicate if the column control is visible.
     */
    private boolean columnControlVisible = false;
    
    /**
     * The component used a column control in the upper trailing corner of 
     * an enclosing <code>JScrollPane</code>.
     */
    private JComponent columnControlButton;
    
    private List<TableColumn> hiddenColumns = new ArrayList<TableColumn>();
    
    private Map<TableColumn, ColumnAttributes> columnAttributesMap
    	= new HashMap<TableColumn, ColumnAttributes>();
    
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
						if (comp != null) 
						{
							int rowWidth = comp.getPreferredSize().width;
							width = Math.max(width, rowWidth);
						}
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
	
	public void packColumn(TableColumn column) 
	{
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
					if (comp != null) 
					{
						int rowWidth = comp.getPreferredSize().width;
						width = Math.max(width, rowWidth);
					}
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
	}

	
	/**
	 *  Sort Table
	 *  @param modelColumnIndex model column sort index
	 */
	@SuppressWarnings("unchecked")
	protected void sort (final int modelColumnIndex)
	{
		int rows = getRowCount();
		if (rows == 0)
			return;
		
		sorting = true;

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
		final MSort sort = new MSort(0, null);
		sort.setSortAsc(p_asc);
		/* teo_sarca: commented: [ 1585369 ] CTable sorting is TOO LAZY *
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
		*/
		// teo_sarca: [ 1585369 ] CTable sorting is TOO LAZY
		Collections.sort(model.getDataVector(), new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Object item1 = ((Vector)o1).get(modelColumnIndex);
				Object item2 = ((Vector)o2).get(modelColumnIndex);
				return sort.compare(item1, item2);
			}
		});
		
		//  selection
		clearSelection();
		if (selected != null)
		{
			for (int r = 0; r < rows; r++)
			{
				if (selected.equals(getValueAt(r, selCol)))
				{
					setRowSelectionInterval(r,r);
					scrollRectToVisible(getCellRect(r, modelColumnIndex, true)); // teo_sarca: bug fix [ 1585369 ] 
					break;
				}
			}
		}   //  selected != null
		
		sorting = false;
	}   //  sort

	
	@Override
	public void tableChanged(TableModelEvent e) {
		if (e != null && e.getFirstRow() == 0 && e.getLastRow() == Integer.MAX_VALUE &&
			e.getColumn() == TableModelEvent.ALL_COLUMNS && e.getType() == TableModelEvent.UPDATE)
		{
			if (!sorting)
			{
				//reset sort state after refresh
				p_asc = true;
				p_lastSortIndex = -1;
			}
		}
		else
		{
			if (getRowCount() == 0)
			{
				//reset sort state after clear
				p_asc = true;
				p_lastSortIndex = -1;
			}
		}
			
		super.tableChanged(e);
	}

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
		private TableColumn cachedResizingColumn = null;

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
			if (isInResizeRegion(e))
			{
				if (e.getClickCount() == 2)
					packColumn(cachedResizingColumn);
				uncacheResizingColumn();
			}
			else
			{
				int vc = getColumnModel().getColumnIndexAtX(e.getX());
			//	log.info( "Sort " + vc + "=" + getColumnModel().getColumn(vc).getHeaderValue());
				int mc = convertColumnIndexToModel(vc);
				TableColumn column = getTableHeader().getResizingColumn();
				if (column != null) return;
				sort(mc);
			}
		}
		
		public void mouseReleased(MouseEvent e) {
            cacheResizingColumn(e);
        }

        public void mousePressed(MouseEvent e) {
            cacheResizingColumn(e);
        }

        private void cacheResizingColumn(MouseEvent e) {
            TableColumn column = getTableHeader().getResizingColumn();
            if (column != null) {
                cachedResizingColumn  = column;
            }
        }

        private void uncacheResizingColumn() {
            cachedResizingColumn = null;
        }

        private boolean isInResizeRegion(MouseEvent e) {
            return cachedResizingColumn != null; // inResize;
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
            uncacheResizingColumn();
        }

        public void mouseDragged(MouseEvent e) {
            uncacheResizingColumn();
        }

	}	//  CTableMouseListener

	
	@Override
	public void setFont(Font font) {
		super.setFont(font);
		//Update row height
		setRowHeight(getFont().getSize() + 8);
	}
	
	/**
	 * @return column index
	 */
	public int getSortColumn() {
		return p_lastSortIndex;
	}
	
	/**
	 * @return boolean
	 */
	public boolean isSortAscending() {
		return p_asc;
	}
	
	/**
     * Returns the column control visible property.
     * <p>
     * 
     * @return boolean to indicate whether the column control is visible.
     * @see #setColumnControlVisible(boolean)
     * @see #setColumnControl(JComponent)
     */
    public boolean isColumnControlVisible() {
        return columnControlVisible;
    }

    /**
     * Sets the column control visible property. If true and
     * <code>JXTable</code> is contained in a <code>JScrollPane</code>, the
     * table adds the column control to the trailing corner of the scroll pane.
     * <p>
     * 
     * Note: if the table is not inside a <code>JScrollPane</code> the column
     * control is not shown even if this returns true. In this case it's the
     * responsibility of the client code to actually show it.
     * <p>
     * 
     * The default value is <code>false</code>.
     * 
     * @param visible boolean to indicate if the column control should be shown
     * @see #isColumnControlVisible()
     * @see #setColumnControl(JComponent)
     * 
     */
    public void setColumnControlVisible(boolean visible) {
        boolean old = isColumnControlVisible();
        this.columnControlVisible = visible;
        if (old != isColumnControlVisible()) {
            configureColumnControl();
            firePropertyChange("columnControlVisible", old, !old);
        }
    }
    
    /**
     * Returns the component used as column control. Lazily creates the 
     * control to the default if it is <code>null</code>.
     * 
     * @return component for column control, guaranteed to be != null.
     * @see #setColumnControl(JComponent)
     * @see #createDefaultColumnControl()
     */
    public JComponent getColumnControl() {
        if (columnControlButton == null) {
            columnControlButton = createDefaultColumnControl();
        }
        return columnControlButton;
    }
    
    /**
     * Creates the default column control used by this table.
     * This implementation returns a <code>ColumnControlButton</code> configured
     * with default <code>ColumnControlIcon</code>.
     *   
     * @return the default component used as column control.
     * @see #setColumnControl(JComponent)
     * @see org.jdesktop.swingx.table.ColumnControlButton
     * @see org.jdesktop.swingx.icon.ColumnControlIcon
     */
    protected JComponent createDefaultColumnControl() {
        return new CColumnControlButton(this, new ColumnControlIcon());
    }
    
	/**
     * Configures the upper trailing corner of an enclosing 
     * <code>JScrollPane</code>.
     * 
     * Adds/removes the <code>ColumnControl</code> depending on the 
     * <code>columnControlVisible</code> property.<p>
     * 
     * @see #setColumnControlVisible(boolean)
     * @see #setColumnControl(JComponent)
     */
    protected void configureColumnControl() {
        Container p = getParent();
        if (p instanceof JViewport) {
            Container gp = p.getParent();
            if (gp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) gp;
                // Make certain we are the viewPort's view and not, for
                // example, the rowHeaderView of the scrollPane -
                // an implementor of fixed columns might do this.
                JViewport viewport = scrollPane.getViewport();
                if (viewport == null || viewport.getView() != this) {
                    return;
                }
                if (isColumnControlVisible()) {
                    verticalScrollPolicy = scrollPane
                            .getVerticalScrollBarPolicy();
                    scrollPane.setCorner(JScrollPane.UPPER_TRAILING_CORNER,
                            getColumnControl());

                    scrollPane
                            .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                } else {
                    if (verticalScrollPolicy != 0) {
                        // Fix #155-swingx: reset only if we had force always before
                        // PENDING: JW - doesn't cope with dynamically changing the policy
                        // shouldn't be much of a problem because doesn't happen too often?? 
                        scrollPane.setVerticalScrollBarPolicy(verticalScrollPolicy);
                    }
                    try {
                        scrollPane.setCorner(JScrollPane.UPPER_TRAILING_CORNER,
                                null);
                    } catch (Exception ex) {
                        // Ignore spurious exception thrown by JScrollPane. This
                        // is a Swing bug!
                    }

                }
            }
        }
    }

    /**
     * 
     * @param column
     * @return boolean
     */
	public boolean isColumnVisible(TableColumn column) 
	{
		return !hiddenColumns.contains(column);
	}
	
	/**
	 * Hide or show column
	 * @param column
	 * @param visible
	 */
	public void setColumnVisibility(TableColumn column, boolean visible) 
	{
		if (visible)
		{
			if (isColumnVisible(column)) return;
			ColumnAttributes attributes = columnAttributesMap.get(column);
			if (attributes == null) return;
			
			column.setCellEditor(attributes.cellEditor);
			column.setCellRenderer(attributes.cellRenderer);
			column.setMinWidth(attributes.minWidth);
			column.setMaxWidth(attributes.maxWidth);
			column.setPreferredWidth(attributes.preferredWidth);
			columnAttributesMap.remove(column);
			hiddenColumns.remove(column);
		}
		else 
		{
			if (!isColumnVisible(column)) return;
			
			ColumnAttributes attributes = new ColumnAttributes();
			attributes.cellEditor = column.getCellEditor();
			attributes.cellRenderer = column.getCellRenderer();
			attributes.minWidth = column.getMinWidth();
			attributes.maxWidth = column.getMaxWidth();
			attributes.preferredWidth = column.getPreferredWidth();
			columnAttributesMap.put(column, attributes);
			
			TableCellNone h = new TableCellNone(column.getIdentifier() != null ?
        			column.getIdentifier().toString() : column.getHeaderValue().toString());
        	column.setCellEditor(h);
        	column.setCellRenderer(h);
        	column.setMinWidth(0);
        	column.setMaxWidth(0);            	
        	column.setPreferredWidth(0);
        	
        	hiddenColumns.add(column);
		}
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
			int vColIndex) {
		Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		if (!this.isCellEditable(rowIndex, vColIndex))
			return c; 
		if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
			c.setBackground(AdempierePLAF.getFieldBackground_Selected());
		} else {
			// If not shaded, match the table's background
			c.setBackground(getBackground());
		}
		if (isCellSelected(rowIndex, vColIndex))
			c.setBackground(AdempierePLAF.getFieldBackground_ReadOnly());
		return c;
	}
	
	class ColumnAttributes {
		protected TableCellEditor cellEditor;

    	protected TableCellRenderer cellRenderer;

		protected Object headerValue;

		protected int minWidth;

		protected int maxWidth;

		protected int preferredWidth;
	}

}	//	CTable
