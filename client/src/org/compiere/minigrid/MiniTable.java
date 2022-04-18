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
package org.compiere.minigrid;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.search.Info_Column;
import org.compiere.grid.ed.VCellRenderer;
import org.compiere.grid.ed.VHeaderRenderer;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.PO;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

/**
 *  Mini Table.
 *  Default Read Only Table for Boolean, String, Number, Timestamp values
 *  <p>
 *  After initializing the Table Model, you need to call setColumnClass,
 *  add columns via addColumn or in one go prepare the table.
 *  <code>
 *  MiniTable mt = new MiniTable();
 *  String sql = mt.prepareTable(..);   //  table defined
 *  //  add where to the sql statement
 *  ResultSet rs = ..
 *  mt.loadTable(rs);
 *  rs.close();
 *  </code>
 *  @author     Jorg Janke
 *  @version    $Id: MiniTable.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1891082 ] NPE on MiniTable when you hide some columns
 * 				<li>FR [ 1974299 ] Add MiniTable.getSelectedKeys method
 * 				<li>FR [ 2847295 ] MiniTable multiselection checkboxes not working
 * 					https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2847295&group_id=176962
 * @author Teo Sarca, teo.sarca@gmail.com
 * 				<li>BF [ 2876895 ] MiniTable.loadTable: NPE if column is null
 * 					https://sourceforge.net/tracker/?func=detail&aid=2876895&group_id=176962&atid=879332
 * 
 * @author Michael McKay, 
 * 		<li><a href="https://adempiere.atlassian.net/browse/ADEMPIERE-71">ADMPIERE-71</a> MiniTable causes exception when adding totals
 * 			to tables with no text fields in the first or second column
 * 		<li><a href="https://adempiere.atlassian.net/browse/ADEMPIERE-72">ADMPIERE-72</a> VLookup and Info Window improvements
 * 		<li><a href="https://adempiere.atlassian.net/browse/ADEMPIERE-241">ADMPIERE-241</a> Adding Select All checkbox to table header.
 * 		<li>release/380 - fix row selection event handling to fire single event per row selection. Also code clean-up.
 * 
 */
public class MiniTable extends CTable implements IMiniTable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2853772547464132497L;

	public class TablePropertyListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent e)
		{
			String propertyName = e.getPropertyName();
			if (propertyName == "model")
			{
				//  Reset
				((MiniTable) e.getSource()).setShowGrid(false);
				((MiniTable) e.getSource()).setIntercellSpacing(new Dimension(0, 0));
			}
		}
	}

	public interface MiniTableSelectionListener extends EventListener {
		public  abstract void rowSelected(RowSelectionEvent e);
	}

	@SuppressWarnings("serial")
	public class RowSelectionEvent extends AWTEvent {
	    public static final int ROW_TOGGLED = AWTEvent.RESERVED_ID_MAX + 1;
	    public RowSelectionEvent(MiniTable source, int id) {
	        super(source, id);
	    }
	}
	
	/**
	 *  Default Constructor
	 */
	public MiniTable()
	{
		super();
		//	log.config( "MiniTable");
		this.setCellSelectionEnabled(true);
		this.setRowSelectionAllowed(true);
		//  Default Editor
		this.setCellEditor(new ROCellEditor());

		this.setShowGrid(false);
		this.setIntercellSpacing(new Dimension(0, 0));
		
		//  Set up the keyboard interactions
		this.setSurrendersFocusOnKeystroke(true);  //  Default behaviour is to surrender the focus.
		//  Customise row selection confirmation
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "doMatchIdToSelection");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl SPACE"), "doToggleID");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift SPACE"), "doToggleBySelection");
		//  Disable column selections
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("KP_RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl KP_RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("shift RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("shift KP_RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift KP_RIGHT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("KP_LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl KP_LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("shift LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("shift KP_LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift LEFT"), "doNothing");
		this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift KP_LEFT"), "doNothing");
		
		//  Customize the row selection behaviour
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "doSelectRowDown");  //  Tab moves to next row
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, Event.SHIFT_MASK), "doSelectRowUp");  //  Tab moves to previous row
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "doSelectRowUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("KP_UP"), "doSelectRowUp");
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "doSelectRowDown");
		this.getInputMap().put(KeyStroke.getKeyStroke("KP_DOWN"), "doSelectRowDown");

		// Add Action functions to table Action Map.  Otherwise, the defaults will be used.
		this.getActionMap().put("doNothing", doNothing);
		this.getActionMap().put("doSelectAll", doSelectAll);
		this.getActionMap().put("doSelectRowDown", doSelectRowDown);
		this.getActionMap().put("doSelectRowUp", doSelectRowUp);
		this.getActionMap().put("doAddRowDown", doAddRowDown);
		this.getActionMap().put("doAddRowUp", doAddRowUp);
		this.getActionMap().put("doAddRowDownExtend", doAddRowDownExtend);
		this.getActionMap().put("doAddRowUpExtend", doAddRowUpExtend);
		this.getActionMap().put("doChangeLeadDown", doChangeLeadDown);
		this.getActionMap().put("doChangeLeadUp", doChangeLeadUp);
		this.getActionMap().put("doMatchIdToSelection", doMatchIdToSelection);
		this.getActionMap().put("doToggleID", doToggleID);
		this.getActionMap().put("doToggleBySelection", doToggleBySelection);
		//
		this.setShowTotals(false);
		this.getSelectionModel().addListSelectionListener(this);
		this.addPropertyChangeListener(new TablePropertyListener());
		
		//  Add additional config items that may change later
		config_table();

		this.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe)
			{
				((MiniTable) fe.getSource()).getParent().repaint();
			}
			
			public void focusLost(FocusEvent fe)
			{
				if (((MiniTable) fe.getSource()) != null)
					((MiniTable) fe.getSource()).getParent().repaint();
			}
		});

	}   //  MiniTable

	private boolean pressedInTable = false;
	private int 	pressedRow = -1;
	private int		pressedColumn = -1;
	private boolean wasDragged = false;
	
	/**  In multi-selection tables, does a single click select uniquely that record or 
	 *   toggle the selection of that record (add or remove from the selection).  Defaults
	 *   to false (add/remove).
	 */
	private boolean m_singleClickTogglesSelection = true;

    /** A list of event listeners for this component. */
    protected EventListenerList listenerList = new EventListenerList();

	/**
	 * @return true if a click on a row adds or removes the row from the selection
	 */
	protected boolean isSingleClickTogglesSelection() {
		return m_singleClickTogglesSelection;
	}

	/**
	 * If set to false, the clicked row will become the selection.  If set to true, 
	 * a click will add or remove the row from the selection. Similar to Ctrl-Click 
	 * when set to false.  
	 * @param clickTogglesSelection the m_singleClickTogglesSelection to set
	 */
	protected void setSingleClickTogglesSelection(boolean clickTogglesSelection) {
		m_singleClickTogglesSelection = clickTogglesSelection;
	}

	@Override protected void processMouseEvent(MouseEvent me)
	{	
		MiniTable table = ((MiniTable) me.getSource());
		if (me.isPopupTrigger())
		{
			try
			{
				super.processMouseEvent(me);
			}
			catch(Exception e)
			{
			}
			return;			
		}
		else if (me.getID() == MouseEvent.MOUSE_CLICKED && me.getClickCount() % 2 == 0)
		{
			if (isMultiSelection() && isDoubleClickTogglesSelection())
			{
				toggleLeadRowChecked();
			}
			else
			{
				try
				{
					super.processMouseEvent(me);
				}
				catch(Exception e)
				{
				}
				return;
			}
		}
		else if (me.getID() == MouseEvent.MOUSE_RELEASED)
		{
			if(pressedInTable)
			{
				//  The mouse press action occurred in the table
				//  The mouse release may have occurred anywhere
				boolean releasedInTable = false;
				if (table.rowAtPoint(me.getPoint()) >= 0 && table.columnAtPoint(me.getPoint()) >= 0)
					releasedInTable = true;
				
				if (!releasedInTable)
				{
					//  The mouse may be off the table
					//  Toggle the ID columns according to the selection (highlight)
					toggleRowCheckedBySelection();
					return;
				}
				//  Check for same cell and edit that cell if possible
				if (pressedRow == table.rowAtPoint(me.getPoint()) && pressedColumn == table.columnAtPoint(me.getPoint()))
				{
					//  The action occurred in the same sell
					boolean editable = this.isCellEditable(pressedRow, 
														   pressedColumn); 
		            if (editable)
		            {
		            	try{super.processMouseEvent(me);}
		            	catch(Exception e){}
		            	fireRowSelectionEvent(); // To ensure the selection event is noticed.
		            	return;
		            }
				}
				
				//  Check for Ctrl-click or drag
			    int ctrlMask = MouseEvent.CTRL_DOWN_MASK;
			    if ((!wasDragged && ((me.getModifiersEx() & (ctrlMask)) == ctrlMask)) ||	//  same row - ctrl-click
		    		(isMultiSelection() && isSingleClickTogglesSelection()))				//  dragged or no ctrl key
	    		{
			    	if (pressedRow == table.rowAtPoint(me.getPoint()))
			    		toggleLeadRowChecked();  //  The current row only
			    	else
			    		toggleRowCheckedRange(pressedRow, table.rowAtPoint(me.getPoint())); // A range
			    }
			    else
			    	matchCheckWithSelectedRows();
			}
			pressedInTable = false;
			pressedRow = -1;
			pressedColumn = -1;
			wasDragged = false;
 		}
		else if (me.getID() == MouseEvent.MOUSE_PRESSED)
	    {
			int rows = table.getRowCount();
			int columns = table.getColumnCount();
			
			if (table.getShowTotals())
			{
				rows = rows-1;
			}
			pressedRow = table.rowAtPoint(me.getPoint());
			pressedColumn = table.columnAtPoint(me.getPoint());
			if (pressedRow >= 0 && pressedRow < rows && pressedColumn >= 0 && pressedColumn < columns)
			{
				pressedInTable = true;
			}
			else
			{
				pressedInTable = false;
				pressedRow = -1;
				pressedColumn = -1;
			}
	    }
		
		//  For all events
		if(!pressedInTable)
		{
			return;
		}
		else
	    {
			try
			{
				super.processMouseEvent(me);
			}
			catch(Exception e)
			{
			}
	    }
	}

	//  Ignore mouse motion that started over the total row.
	protected void processMouseMotionEvent(MouseEvent mme)
	{
		if (mme.getID() == MouseEvent.MOUSE_DRAGGED)
		{
			if (!pressedInTable)
			{
				wasDragged = false;
				mme.consume(); // Pretend it didn't originate in the table
				return;
			}
			else if (pressedInTable) // valid
			{
				wasDragged = true;
				MiniTable table = ((MiniTable) mme.getSource());

				if (this.getShowTotals())
				{
					//  Check if we are over the total row
					int totalRow = table.getRowCount()-1;
					if (totalRow == table.rowAtPoint(mme.getPoint()))
					{
						mme.consume(); // Mouse is being dragged over the total row. Ignore it.
						return;					
					}						
				}
				
				if(!isMultiSelection())
				{
					ListSelectionModel rsm = (ListSelectionModel) table.getSelectionModel();
					int leadRow = rsm.getLeadSelectionIndex();
					rsm.setSelectionInterval(leadRow, leadRow);
				}
			}
			super.processMouseMotionEvent(mme);
		}
	}

	public static final String SYSCONFIG_INFO_DEFAULTSELECTED = "INFO_DEFAULTSELECTED";
	public static final String SYSCONFIG_INFO_DOUBLECLICKTOGGLESSELECTION = "INFO_DOUBLECLICKTOGGLESSELECTION";

	/** List of R/W columns     */
	private ArrayList<Integer>   m_readWriteColumn = new ArrayList<Integer>();
	/** List of Column Width    */
	private ArrayList<Integer>   m_minWidth = new ArrayList<Integer>();

	/** Color Column Index of Model     */
	private int         m_colorColumnIndex = -1;
	/** Color Column compare data       */
	private Object      m_colorDataCompare = Env.ZERO;

	/** Multi Selection mode (default false) */
	private boolean     m_multiSelection = false;
	/** True if double click on a row toggles if row is selected (multi selection mode only) */
	private boolean				p_doubleClickTogglesSelection = MSysConfig.getBooleanValue(SYSCONFIG_INFO_DOUBLECLICKTOGGLESSELECTION, false, Env.getAD_Client_ID(Env.getCtx()));
	/** Specify if the records should be checked(selected) by default (multi selection mode only) */
	private boolean				p_isDefaultSelected = MSysConfig.getBooleanValue(SYSCONFIG_INFO_DEFAULTSELECTED, false, Env.getAD_Client_ID(Env.getCtx()));
	/** Lauout set in prepareTable and used in loadTable    */
	private ColumnInfo[]        m_layout = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MiniTable.class);
	/** Is Total Show */
	private boolean showTotals = false;
	private boolean autoResize = true;

	public boolean isAutoResize() {
		return autoResize;
	}

	public void setAutoResize(boolean autoResize) {
		this.autoResize = autoResize;
	}

	/**
	 * Gets the swing column of given index. No index checking 
	 * is done.
	 * 
	 * @param col
	 * @return
	 */
	public TableColumn getColumn(int col) {
		return(getColumnModel().getColumn(col));
	}

	/**
	 * Return number of columns in the mini table
	 */
	public int getColumnCount() {
		return(getColumnModel().getColumnCount());
	}
	
	/**
	 *	Size Columns.
	 *  Uses Mimimum Column Size
	 */
	public void autoSize()
	{
		if ( !autoResize  )
			return;
		
		long start = System.currentTimeMillis();
		//
		final int SLACK = 8;		//	making sure it fits in a column
		final int MAXSIZE = 300;    //	max size of a column
		//
		TableModel model = this.getModel();
		int size = model.getColumnCount();
		//	for all columns
		for (int col = 0; col < size; col++)
		{
			//  Column & minimum width
			TableColumn tc = this.getColumnModel().getColumn(col);
			int width = 0;
			if (m_minWidth.size() > col)
				width = ((Integer)m_minWidth.get(col)).intValue();
		//  log.config( "Column=" + col + " " + column.getHeaderValue());

			//	Header
			TableCellRenderer renderer = tc.getHeaderRenderer();
			if (renderer == null)
				renderer = new DefaultTableCellRenderer();
			Component comp = renderer.getTableCellRendererComponent
				(this, tc.getHeaderValue(), false, false, 0, 0);
		//	log.fine( "Hdr - preferred=" + comp.getPreferredSize().width + ", width=" + comp.getWidth());
			width = Math.max(width, comp.getPreferredSize().width + SLACK);

			//	Cells
			int maxRow = Math.min(30, getRowCount());       //  first 30 rows
			for (int row = 0; row < maxRow; row++)
			{
				renderer = getCellRenderer(row, col);
				comp = renderer.getTableCellRendererComponent
					(this, getValueAt(row, col), false, false, row, col);
				if (comp != null) {
					int rowWidth = comp.getPreferredSize().width + SLACK;
					width = Math.max(width, rowWidth);
				}
			}
			//	Width not greater ..
			width = Math.min(MAXSIZE, width);
			tc.setPreferredWidth(width);
		//	log.fine( "width=" + width);
		}	//	for all columns
		log.finer("Cols=" + size + " - " + (System.currentTimeMillis()-start) + "ms");
	}	//	autoSize

	
	/**
	 * Determines if the row is marked selected in the key column. The table 
	 * selection status (highlight) is not considered.
	 * @param row
	 * @return true if the row is marked selected in the key column
	 */
	public boolean isRowChecked(int row)
	{
		int keyColumn = this.getKeyColumnIndex();
		
		if (keyColumn < 0)
			return false;
		
		//  The selection can be indicated by an IDColumn or Boolean in the keyColumn position
		Object data = getValueAt(row, convertColumnIndexToView(keyColumn)); 
		if (data instanceof IDColumn)
			return ((IDColumn) data).isSelected();
		else if (data instanceof Boolean)
			return (Boolean) data;

		return	false;
	}

	/**
	 *  Is Cell Editable
	 *  @param row view row
	 *  @param column view column
	 *  @return true if read-write
	 */
	public boolean isCellEditable(int row, int column)
	{
		//  The column has to be read-write and the row has to be selected 
		//  in order for the cell to be editable.
		
		int modelColumn = convertColumnIndexToModel(column);
		
		if ((modelColumn == 0 && this.getValueAt(row,column) instanceof Boolean) || isRowChecked(row))
		{
			//  is the column RW?
			if (m_readWriteColumn.contains(Integer.valueOf(modelColumn)))
				return true;
		}		
		return false;
	}   //  isCellEditable

	/**
	 *  Set Column to ReadOnly
	 *  @param column column
	 *  @param readOnly read only
	 */
	public void setColumnReadOnly (int column, boolean readOnly)
	{
		//  Column is ReadWrite
		if (m_readWriteColumn.contains(Integer.valueOf(column))) {
			//  Remove from list
			if (readOnly)
			{
				int size = m_readWriteColumn.size();
				for (int i = 0; i < size; i++)
				{
					if (((Integer)m_readWriteColumn.get(i)).intValue() == column)
					{
						m_readWriteColumn.remove(i);
						break;
					}
				}
			}   //  ReadOnly
		}
		//  current column is R/O - ReadWrite - add to list
		else if (!readOnly)
			m_readWriteColumn.add(Integer.valueOf(column));
	}   //  setColumnReadOnly

	
	/**************************************************************************
	 *  Prepare Table and return SQL
	 *
	 *  @param layout    array of column info
	 *  @param from      SQL FROM content
	 *  @param where     SQL WHERE content
	 *  @param multiSelection multiple selections
	 *  @param tableName table name
	 *  @return SQL
	 */
	public String prepareTable(ColumnInfo[] layout, 
		String from, String where, boolean multiSelection, String tableName)
	{
		m_layout = layout;
		setMultiSelection(multiSelection);
		//
		StringBuffer sql = new StringBuffer ("SELECT ");
		//  add columns & sql
		for (int i = 0; i < layout.length; i++)
		{
			//  create sql
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			//  adding ID column
			if (layout[i].isKeyPairCol())
				sql.append(",").append(layout[i].getKeyPairColSQL());

			//  add to model
			addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				p_keyColumnIndex = i;
		}
		//  set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append( " FROM ").append(from);
		sql.append(" WHERE ").append(where);

		//  Table Selection
		setRowSelectionAllowed(true);
		
		//	org.compiere.apps.form.VMatch.dynInit calls routine for initial init only
		if (from.length() == 0)
			return sql.toString();
		//
		String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(), 
			tableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finest(finalSQL);
		return finalSQL;
	}   //  prepareTable

	/**
	 *  Add Table Column.
	 *  after adding a column, you need to set the column classes again
	 *  (DefaultTableModel fires TableStructureChanged, which calls
	 *  JTable.tableChanged .. createDefaultColumnsFromModel
	 *  @param header header
	 */
	public void addColumn (String header)
	{
		if (getModel() instanceof DefaultTableModel)
		{
			DefaultTableModel model = (DefaultTableModel)getModel();
			model.addColumn(Util.cleanAmp(header));
		}
		else
			throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
	}   //  addColumn

	/**
	 * Add change listener to the column - used to listen to selection events
	 * involving ID Columns or Check boxes that are independent of list selection
	 * events.  If the column is not a ID Column or Check box, it will be ignored.
	 * @param column - the column number to listen to
	 * @param listener - the listener - implements ItemListener
	 */
	public void addChangeListener(int column, ChangeListener listener)
	{
		TableColumn tc = getColumn(column);
		TableCellRenderer tcr = tc.getCellRenderer();
		
		if (tcr instanceof IDColumnRenderer || tcr instanceof CheckRenderer)
		{
			((IDColumnRenderer) tcr).addChangeListener(listener);
		}
		else if (tcr instanceof CheckRenderer)
		{
			((CheckRenderer) tcr).addChangeListener(listener);
		}
	}
	/**
	 *  Set Column Editor & Renderer to Class.
	 *  (after all columns were added)
	 *  @param index column index
	 *  @param c   class of column - determines renderere
	 *  @param readOnly read only flag
	 */
	public void setColumnClass (int index, @SuppressWarnings("rawtypes") Class c, boolean readOnly)
	{
		setColumnClass(index, c, readOnly, null);
	}   //  setColumnClass

	/**
	 *  Set Column Editor & Renderer to Class
	 *  (after all columns were added)
	 *  Lauout of IDColumn depemds on multiSelection
	 *  @param index column index
	 *  @param c   class of column - determines renderere/editors supported:
	 *  IDColumn, Boolean, Double (Quantity), BigDecimal (Amount), Integer, Timestamp, String (default)
	 *  @param readOnly read only flag
	 *  @param header optional header value
	 */
	public void setColumnClass (int index, @SuppressWarnings("rawtypes") Class c, boolean readOnly, String header)
	{
		setColumnClass (index, c, 0  , readOnly, header);
	}
	
	/**
	 *  Set Column Editor & Renderer to Class
	 *  (after all columns were added)
	 *  Layout of IDColumn depends on multiSelection
	 *  @param index column index
	 *  @param c   class of column - determines renderer/editors supported:
	 *  @param DisplayType define Type Value
	 *  IDColumn, Boolean, Double (Quantity), BigDecimal (Amount), Integer, Timestamp, String (default)
	 *  @param readOnly read only flag
	 *  @param header optional header value
	 */
	public void setColumnClass (int index, @SuppressWarnings("rawtypes") Class c, int displayType ,boolean readOnly, String header)
	{
	//	log.config( "MiniTable.setColumnClass - " + index, c.getName() + ", r/o=" + readOnly);
		TableColumn tc = getColumnModel().getColumn(index);
		if (tc == null)
			return;
		//  Set R/O
		setColumnReadOnly(index, readOnly);

		//  Header
		if (header != null && header.length() > 0)
			tc.setHeaderValue(Util.cleanAmp(header));

		//  ID Column & Selection
		if (c == IDColumn.class)
		{
			IDColumnRenderer idcr = new IDColumnRenderer(m_multiSelection);
			tc.setCellRenderer(idcr);
			if (m_multiSelection)
			{
				VHeaderRenderer vhr = new VHeaderRenderer(m_multiSelection);
				tc.setCellEditor(new IDColumnEditor());
				tc.setHeaderRenderer(vhr);
				idcr.addChangeListener(vhr);  //  Connect the IDColumn with the header
				setColumnReadOnly(index, false);
			}
			else
			{
				tc.setCellEditor(new ROCellEditor());
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
			}
			m_minWidth.add(Integer.valueOf(10));
			tc.setMaxWidth(20);
			tc.setPreferredWidth(20);
			tc.setResizable(false);
		}
		//  Boolean
		else if (DisplayType.YesNo == displayType || c == Boolean.class )
		{
			CheckRenderer cr = new CheckRenderer();
			tc.setCellRenderer(cr);
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.YesNo));
			}
			else
			{
				if (m_multiSelection)
				{
					VHeaderRenderer vhr = new VHeaderRenderer(m_multiSelection);
					setColumnReadOnly(index, false);
					CCheckBox check = new CCheckBox();
					check.setMargin(new Insets(0,0,0,0));
					check.setHorizontalAlignment(SwingConstants.CENTER);
					tc.setCellEditor(new DefaultCellEditor(check));
					tc.setHeaderRenderer(vhr);
					cr.addChangeListener(vhr);  //  Connect the check control with the header
				}
			}
			m_minWidth.add(Integer.valueOf(30));
			
		}
		//  Date
		else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType ||  c == Timestamp.class )
		{
			if(DisplayType.DateTime == displayType)
				tc.setCellRenderer(new VCellRenderer(DisplayType.DateTime));
			else 
				tc.setCellRenderer(new VCellRenderer(DisplayType.Date));
			
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType)
				tc.setCellEditor(new MiniCellEditor(c, displayType));
			else 
				tc.setCellEditor(new MiniCellEditor(c));
			
			m_minWidth.add(Integer.valueOf(30));
			if (DisplayType.DateTime == displayType)
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.DateTime));
			else 
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Date));
		}
		//  Amount
		else if (DisplayType.Amount == displayType || c == BigDecimal.class )
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Amount));
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				m_minWidth.add(Integer.valueOf(70));
			}
			else
			{
				tc.setCellEditor(new MiniCellEditor(c));
				m_minWidth.add(Integer.valueOf(80));
			}
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Number
		else if (DisplayType.Number == displayType || c == Double.class)
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Number));
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				m_minWidth.add(Integer.valueOf(70));
			}
			else
			{
				tc.setCellEditor(new MiniCellEditor(c));
				m_minWidth.add(Integer.valueOf(80));
			}
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Integer
		else if (DisplayType.Integer == displayType || c == Integer.class )
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Integer));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(c));
			m_minWidth.add(Integer.valueOf(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  String
		else
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.String));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(String.class));
			m_minWidth.add(Integer.valueOf(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.String));
		}
	//	log.fine( "Renderer=" + tc.getCellRenderer().toString() + ", Editor=" + tc.getCellEditor().toString());
	}   //  setColumnClass

	/**
	 *  Clear Table Content
	 *  @param no number of rows
	 */
	public void setRowCount (int no)
	{
		if (getModel() instanceof DefaultTableModel)
		{
			DefaultTableModel model = (DefaultTableModel)getModel();
			model.setRowCount(no);
		//	log.config( "MiniTable.setRowCount", "rows=" + getRowCount() + ", cols=" + getColumnCount());
		}
		else
			throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
	}   //  setRowCount

	
	/**************************************************************************
	 *	Load Table from ResultSet - The ResultSet is not closed
	 *
	 *  @param rs ResultSet with the column layout defined in prepareTable
	 */
	public void loadTable(ResultSet rs)
	{
		if (m_layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		//  Clear Table
		setRowCount(0);
		//
		try
		{
			while (rs.next())
			{
				int row = getRowCount();
				setRowCount(row+1);
				int colOffset = 1;  //  columns start with 1
				for (int col = 0; col < m_layout.length; col++)
				{
					Object data = null;
					Class<?> c = m_layout[col].getColClass();
					int colIndex = col + colOffset;
					if (c == IDColumn.class)
						data = new IDColumn(rs.getInt(colIndex));
					else if (c == Boolean.class)
						data = Boolean.valueOf("Y".equals(rs.getString(colIndex)));
					else if (c == Timestamp.class)
						data = rs.getTimestamp(colIndex);
					else if (c == BigDecimal.class)
						data = rs.getBigDecimal(colIndex);
					else if (c == Double.class)
						data = rs.getDouble(colIndex);
					else if (c == Integer.class)
						data = rs.getInt(colIndex);
					else if (c == KeyNamePair.class)
					{
						String display = rs.getString(colIndex);
						int key = rs.getInt(colIndex+1);
						data = new KeyNamePair(key, display);
						colOffset++;
					}
					else
					{
						String s = rs.getString(colIndex);
						if (s != null)
							data = s.trim();	//	problems with NCHAR
					}
					//  store
					setValueAt(data, row, col);
			//		log.fine( "r=" + row + ", c=" + col + " " + m_layout[col].getColHeader(),
			//			"data=" + data.toString() + " " + data.getClass().getName() + " * " + m_table.getCellRenderer(row, col));
				}
				

			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "", e);
		}
		if(getShowTotals())
			addTotals(m_layout);
		autoSize();
		log.config("Row(rs)=" + getRowCount());
		
		
	}	//	loadTable

	/**
	 *	Load Table from Object Array
	 *  @param pos array of POs 
	 */
	public void loadTable(PO[] pos)
	{
		if (m_layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		//  Clear Table
		setRowCount(0);
		//
		for (int i = 0; i < pos.length; i++)
		{
			PO myPO = pos[i];
			int row = getRowCount();
			setRowCount(row+1);
			
			for (int col = 0; col < m_layout.length; col++)
			{
				String columnName = m_layout[col].getColSQL();
				Object data = myPO.get_Value(columnName);
				if (data != null)
				{
					Class<?> c = m_layout[col].getColClass();
					if (c == IDColumn.class)
						data = new IDColumn(((Integer)data).intValue());
					else if (c == Double.class)
						data = new Double(((BigDecimal)data).doubleValue());
				}
				//  store
				setValueAt(data, row, col);
			}
		}
		if(getShowTotals())
			addTotals(m_layout);
		autoSize();
		log.config("Row(array)=" + getRowCount());
	}	//	loadTable
	/**
	 * 	Set Model index of Key Column.
	 *  If not set, column 0 is used.
	 * 	@param keyColumnIndex model index
	 */
	public void setKeyColumnIndex (int keyColumnIndex)
	{
		super.setKeyColumnIndex (keyColumnIndex);
	}	//	setKeyColumnIndex

	/**
	 * 	Get Model index of Key Column
	 *  @return model index
	 */
	public int getKeyColumnIndex()
	{
		return super.getKeyColumnIndex();
	}	//	getKeyColumnIndex

	/**
	 *  Get the key of a row based on layout defined in prepareTable
	 *  @return ID if key
	 */
	public int getRowKey(int row)
	{
		if (getKeyColumnIndex() < 0)
			throw new UnsupportedOperationException("Key Column is not defined");
		
		int rows = this.getRowCount();
		
		if (this.getShowTotals())
			rows = rows - 1;

		if (row >= 0 && row < rows)
		{
	        Object data = getValueAt(row, convertColumnIndexToView(getKeyColumnIndex())); //  Test
			if (data instanceof IDColumn)
			{
				IDColumn id = (IDColumn)data;
				return id.getRecord_ID().intValue();
			}
		}
		return 0;
	}   //  getRowKey

	/**
	 *  Get the key of currently selected row based on layout defined in prepareTable
	 *  @return ID if key
	 */
	public Integer getSelectedRowKey()
	{
		int selectedRow = getSelectedRow();
		return Integer.valueOf(getRowKey(selectedRow));
	}   //  getSelectedRowKey

	/**
	 * @return collection of selected IDs
	 */
	public ArrayList<Integer> getSelectedKeys()
	{
		if (getModel() == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}
		if (p_keyColumnIndex < 0)
		{
			throw new UnsupportedOperationException("Key Column is not defined");
		}
		//
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int row = 0; row < getRowCount(); row++)
		{
			Object data = getModel().getValueAt(row, p_keyColumnIndex);
			if (data instanceof IDColumn)
			{
				IDColumn record = (IDColumn)data;
				if (record.isSelected())
				{
					list.add(record.getRecord_ID());
				}
			}
		}
		return list;
	}

	/**
	 *  Get the record id of the lead (highlighted) row
	 *  @return selected key
	 */
	public int getLeadRowKey()
	{
		int leadRow = getSelectionModel().getLeadSelectionIndex();		
		return getRowKey(leadRow);
		
	}   //  getLeadRowKey

	/**************************************************************************
	 *  Get Layout
	 *  @return Array of ColumnInfo
	 */
	public ColumnInfo[] getLayoutInfo()
	{
		return m_layout;
	}   //  getLayout

	/**
	 *  Set Single Selection
	 *  @param multiSelection multiple selections
	 */
	public void setMultiSelection (boolean multiSelection)
	{
		m_multiSelection = multiSelection;
		config_table(); //  Config for multi selection
	}   //  setMultiSelection

	/**
	 *  Single Selection Table
	 *  @return true if multiple rows can be selected
	 */
	public boolean isMultiSelection()
	{
		return m_multiSelection;
	}   //  isMultiSelection

	/**
	 * (for multi-selection only)
	 * @param value true if double click should toggle record selection
	 */
	public void setDoubleClickTogglesSelection(boolean value)
	{
		p_doubleClickTogglesSelection = value;
	}
	
	/**
	 * (for multi-selection only)
	 * @return true if double click should toggle record selection
	 */
	public boolean isDoubleClickTogglesSelection()
	{
		return p_doubleClickTogglesSelection;
	}

	/**
	 *	Set the Column to determine the color of the row (based on model index)
	 *  @param modelIndex model index
	 */
	public void setColorColumn (int modelIndex)
	{
		m_colorColumnIndex = modelIndex;
	}   //  setColorColumn

	/**
	 * Specify if the records should be checked(selected) by default.
	 * (for multi-selection only)
	 * @param value
	 */
	public void setDefaultSelected(boolean value)
	{
		p_isDefaultSelected = value;
	}
	
	/**
	 * (for multi-selection only)
	 * @return true if records are selected by default
	 */
	public boolean isDefaultSelected()
	{
		return p_isDefaultSelected;
	}

	/**
	 *  Set ColorColumn comparison criteria
	 *  @param dataCompare data
	 */
	public void setColorCompare (Object dataCompare)
	{
		m_colorDataCompare = dataCompare;
	}   //

	/**
	 *	Get ColorCode for Row.
	 *  <pre>
	 *	If numerical value in compare column is
	 *		negative = -1,
	 *      positive = 1,
	 *      otherwise = 0
	 *  If Timestamp
	 *  </pre>
	 * @param row row
	 * @return color code
	 */
	public int getColorCode (int row)
	{
		if (m_colorColumnIndex  == -1)
			return 0;

		Object data = getModel().getValueAt(row, m_colorColumnIndex);
		int cmp = 0;

		//	We need to have a Number
		if (data == null)
			return 0;
		try
		{
			if (data instanceof Timestamp)
			{
				if (m_colorDataCompare == null || !(m_colorDataCompare instanceof Timestamp))
					m_colorDataCompare = new Timestamp(System.currentTimeMillis());
				cmp = ((Timestamp)m_colorDataCompare).compareTo((Timestamp)data);
			}
			else
			{
				if (m_colorDataCompare == null || !(m_colorDataCompare instanceof BigDecimal))
					m_colorDataCompare = Env.ZERO;
				if (!(data instanceof BigDecimal))
					data = new BigDecimal(data.toString());
				cmp = ((BigDecimal)m_colorDataCompare).compareTo((BigDecimal)data);
			}
		}
		catch (Exception e)
		{
			return 0;
		}
		if (cmp > 0)
			return -1;
		if (cmp < 0)
			return 1;
		return 0;
	}   //  getColorCode
	

	/**
	 *  Set if Totals is Show
	 *  @param boolean Show
	 */
	public void setShowTotals(boolean show)
	{
		showTotals= show;
	}
	/**
	 *  get if Totals is Show
	 *  @param boolean Show
	 */
	public boolean getShowTotals()
	{
		return showTotals;
	}
	
	/**
	 *  Adding a new row with the totals
	 */
	public void addTotals(ColumnInfo[] layout)
	{
		if (getRowCount() == 0 || layout.length == 0)
			return;
		
		Object[] total = new Object[layout.length];
		
		for (int row = 0 ; row < getRowCount(); row ++)
		{

				for (int col = 0; col < layout.length; col++)
				{
					int modelRow = convertRowIndexToModel(row);
					int modelCol = convertColumnIndexToModel(col);
					Object data = getModel().getValueAt(modelRow, modelCol);
					Class<?> c = layout[modelCol].getColClass();
					if (c == BigDecimal.class)
					{	
						BigDecimal subtotal = Env.ZERO;
						if(total[col]!= null)
							subtotal = (BigDecimal)(total[col]);
							
						BigDecimal amt =  (BigDecimal) data;
						if(subtotal == null)
							subtotal = Env.ZERO;
						if(amt == null )
							amt = Env.ZERO;
						total[col] = subtotal.add(amt);
					}
					else if (c == Double.class)
					{
						Double subtotal = new Double(0);
						if(total[col] != null)
							subtotal = (Double)(total[col]);
						
						Double amt =  (Double) data;
						if(subtotal == null)
							subtotal = new Double(0);
						if(amt == null )
							subtotal = new Double(0);
						total[col] = subtotal + amt;
						
					}		
				}	
		}
		
		//adding total row

		int row = getRowCount() + 1;
		boolean markerSet = false;
		setRowCount(row);
		for (int col = 0; col < layout.length; col++)
		{
			int modelCol = convertColumnIndexToModel(col);
			Class<?> c = layout[modelCol].getColClass();
			if (c == BigDecimal.class)
			{	
				setValueAt(total[col] , row - 1, col);
			}
			else if (c == Double.class)
			{
				setValueAt(total[col] , row -1 , col);
			}
			else 
			{	
				if(c == String.class && !markerSet)
				{	
					setValueAt(" Σ  " , row -1 , col);
					markerSet = true;
				}	
				else
					setValueAt(null , row - 1, col );	
			}	
			
		}
	}

	/**
	 *  Adding a new row with the totals
	 */
	public void addTotals(Info_Column[] layout)
	{
		addTotals((ColumnInfo[]) layout);
/*		if (getRowCount() == 0 || layout.length == 0)
			return;
		
		Object[] total = new Object[layout.length];
		
		for (int row = 0 ; row < getRowCount(); row ++)
		{

				for (int col = 0; col < layout.length; col++)
				{
					int viewRow = convertRowIndexToView(row);
					int viewCol = convertColumnIndexToView(col);
					int modelRow = convertRowIndexToModel(row);
					int modelCol = convertColumnIndexToModel(col);
					Object data = getModel().getValueAt(modelRow, modelCol);
					Class<?> c = layout[modelCol].getColClass();
					if (c == BigDecimal.class)
					{	
						BigDecimal subtotal = Env.ZERO;
						if(total[col]!= null)
							subtotal = (BigDecimal)(total[col]);
							
						BigDecimal amt =  (BigDecimal) data;
						if(subtotal == null)
							subtotal = Env.ZERO;
						if(amt == null )
							amt = Env.ZERO;
						total[col] = subtotal.add(amt);
					}
					else if (c == Double.class)
					{
						Double subtotal = new Double(0);
						if(total[col] != null)
							subtotal = (Double)(total[col]);
						
						Double amt =  (Double) data;
						if(subtotal == null)
							subtotal = new Double(0);
						if(amt == null )
							subtotal = new Double(0);
						total[col] = subtotal + amt;
						
					}		
				}	
		}
		
		//adding total row

		int row = getRowCount() + 1;
		boolean markerSet = false;
		setRowCount(row);
		for (int col = 0; col < layout.length; col++)
		{
			int modelCol = convertColumnIndexToModel(col);
			Class<?> c = layout[modelCol].getColClass();
			if (c == BigDecimal.class)
			{	
				setValueAt(total[col] , row - 1, col);
			}
			else if (c == Double.class)
			{
				setValueAt(total[col] , row -1 , col);
			}
			else
			{	
				if(c == String.class && !markerSet)
				{	
					setValueAt(" Σ  " , row -1 , col);
					markerSet = true;
				}	
				else
					setValueAt(null , row - 1, col );	
			}	
			
		}
		*/
	}
	
	private void config_table()
	{
		//  The child class has to setup the behaviour of the ENTER key.  Default is to ignore it.
		//  Change behaviour for multi-selection
		if(isMultiSelection())
		{
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl A"), "doSelectAll");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl UP"), "doChangeLeadUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl KP_UP"), "doChangeLeadUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift UP"), "doAddRowUpExtend");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift KP_UP"), "doAddRowUpExtend");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift UP"), "doAddRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift KP_UP"), "doAddRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl DOWN"), "doChangeLeadDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl KP_DOWN"), "doChangeLeadDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift DOWN"), "doAddRowDownExtend");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift KP_DOWN"), "doAddRowDownExtend");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift DOWN"), "doAddRowDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift KP_DOWN"), "doAddRowDown");
		}
		else
		{
			this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doNothing");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl A"), "doNothing");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl UP"), "doSelectRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl KP_UP"), "doSelectRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift UP"), "doSelectRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift KP_UP"), "doSelectRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift UP"), "doSelectRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift KP_UP"), "doSelectRowUp");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl DOWN"), "doSelectRowDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl KP_DOWN"), "doSelectRowDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift DOWN"), "doSelectRowDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("shift KP_DOWN"), "doSelectRowDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift DOWN"), "doSelectRowDown");
			this.getInputMap().put(KeyStroke.getKeyStroke("ctrl shift KP_DOWN"), "doSelectRowDown");			
		}

	}

	public TableCellRenderer getCellRenderer(int row, int column)
	{
		Object editorClass = null;
		try {
			editorClass = this.getColumnModel().getColumn(column).getCellEditor().getClass();
		} catch (Exception e) {}  //  Possible NPE if the table was not setup properly.
        boolean editable = this.isCellEditable(row, column);
		if (editable && editorClass == MiniCellEditor.class)
		{
	        Color borderColor = AdempierePLAF.getFieldBackground_Mandatory();
	        CompoundBorder cb = null;
	        
			//  Set the borders on the editor
			MiniCellEditor jc = ((MiniCellEditor) this.getColumnModel().getColumn(column).getCellEditor());
    		if (column == 0)
    		{
    			cb = new CompoundBorder(new EmptyBorder(new Insets(0,0,0,1)),new MatteBorder(1, 1, 1, 0, borderColor));
    			jc.setBorder(cb);
    		}
    		else if (column == this.getColumnCount()-1)
    		{
    			cb = new CompoundBorder(new EmptyBorder(new Insets(0,1,0,0)),new MatteBorder(1, 0, 1, 1, borderColor));
    			jc.setBorder(cb);
    		}
    		else
    		{
    			cb = new CompoundBorder(new EmptyBorder(new Insets(0,1,0,1)),new MatteBorder(1, 0, 1, 0, borderColor));
    			jc.setBorder(cb);
    		}
		}
		return super.getCellRenderer(row, column);
	}
	
    //  Determine editor to be used by row
    public TableCellEditor getCellEditor(int row, int column)
    {        
    	return super.getCellEditor(row, column);   
    }

	public Component prepareRenderer(
            TableCellRenderer renderer, int row, int column)
    {

        Component c = super.prepareRenderer(renderer, row, column);
        JComponent jc = (JComponent)c;
        if (c==null) return c;
        
        //  Row is selected
        Color selectedColor = AdempierePLAF.getFieldBackground_Selected();
        //  Even row
        Color normalColor = AdempierePLAF.getFieldBackground_Normal();
        //  Odd row
        Color backColor = AdempierePLAF.getInfoBackground();
        //  Lead row border
        Color borderColor = AdempierePLAF.getFieldBackground_Mandatory();
        
        CompoundBorder cb = null;

        ListSelectionModel rsm = this.getSelectionModel();
        boolean readOnly = !this.isCellEditable(row, column);
        if (!(row == rsm.getLeadSelectionIndex()))
        {
        	if (rsm.isSelectedIndex(row)) //  Highlighted but not the lead
        	{
        		c.setBackground(selectedColor);
        		jc.setBorder(new MatteBorder(1, 1, 1, 1, selectedColor) );
        	}
        	else if (row % 2 == 0)  //  Not selected but even in number
    		{ 
    			c.setBackground(normalColor);
        		jc.setBorder(new MatteBorder(1, 1, 1, 1, normalColor) );
    		} 
    		else  //  Not selected and odd in number
    		{
    			// If not shaded, match the table's background
    			c.setBackground(backColor);
        		jc.setBorder(new MatteBorder(1, 1, 1, 1, backColor) );
    		}
        	
        	//  Buttons and checkboxes need to have the border turned on
        	if (c.getClass().equals(JCheckBox.class))
        	{
        		((JCheckBox) c).setBorderPainted(false);
        	}
        	else if (c.getClass().equals(JButton.class))
        	{
        		((JButton) c).setBorderPainted(false);
        	}

        }
        else
        {
        	if (c.getClass().equals(JCheckBox.class))
        	{
        		((JCheckBox) c).setBorderPainted(true);
        	}
        	else if (c.getClass().equals(JButton.class))
        	{
        		((JButton) c).setBorderPainted(true);
        	}
        	
        	//  Define border - compond border maintains the spacing of 1px around the field
    		if (column == 0)
    		{
    			cb = new CompoundBorder(new EmptyBorder(new Insets(0,0,0,1)),new MatteBorder(1, 1, 1, 0, borderColor));
    		}
    		else if (column == this.getColumnCount()-1)
    		{
    			cb = new CompoundBorder(new EmptyBorder(new Insets(0,1,0,0)),new MatteBorder(1, 0, 1, 1, borderColor));
    		}
    		else
    		{
    			cb = new CompoundBorder(new EmptyBorder(new Insets(0,1,0,1)),new MatteBorder(1, 0, 1, 0, borderColor));
    		}
			//  Set border
    		jc.setBorder(cb);
    		//  Set background color
    		if (!readOnly &&  this.isRowChecked(row))
    			c.setBackground(normalColor);
    		else
    			c.setBackground(selectedColor);        			
        }

        return c;
    }	

	/**
     * Performs the action.
     * @param e
     * @param actionName determines which action to perform
     * @param dy the number of rows over which to perform the action.
     */
    private void doAction(ActionEvent e, String actionName, int dy)
    {
        int leadRow = 0;
        
        MiniTable table = (MiniTable)e.getSource();

        if (table.getRowCount() <= 0 || table.getColumnCount() <= 0) 
        {
            // bail - don't try to move selection on an empty table
            return;
        }
        
        ListSelectionModel rsm = table.getSelectionModel();
        int index = rsm.getLeadSelectionIndex();
        int compare = table.getRowCount();
        
        if(table.getShowTotals())
        	compare = compare -1;
        index = index < compare ? index : -1;

        if (dy != 0) 
        {
        	if (dy < 0) // Up
        	{
        		//  Check limit at the top
        		leadRow = Math.min(Math.max(index+dy, 0), index);
        	}
        	else if (dy > 0)  // Down
        	{
        		//  Check the limit at the bottom
                leadRow = Math.min(Math.max(index+dy, 0), compare-1);        		
        	}
        	
        	if (actionName.equals("SelectRowUp") ||
    			actionName.equals("SelectRowDown"))
        	{
        		rsm.clearSelection();
        		rsm.addSelectionInterval(leadRow, leadRow);
        		matchCheckWithSelectedRows();
        	}
        	else if (actionName.equals("AddRowUp") ||
    				 actionName.equals("AddRowDown"))
        	{
        		// Determine if the focused row is selected
	            Object data = table.getValueAt(index, table.convertColumnIndexToView(getKeyColumnIndex())); //  Test the first row
				if (data instanceof IDColumn)
				{
					rsm.addSelectionInterval(index, leadRow);	
					setRowChecked(index,true);
					setRowChecked(leadRow,true);	
	            }
        	}
        	else if (actionName.equals("AddRowUpExtend") ||
        			 actionName.equals("AddRowDownExtend"))
        	{
            	table.changeSelection(index, 0, false, true);
            	table.changeSelection(leadRow, 0, false, true);
            	matchCheckWithSelectedRows();        		
        	}
        	else if (actionName.equals("ChangeLeadUp") ||
        			 actionName.equals("ChangeLeadDown"))
        	{
				// Determine if the focused row is selected
				if(isRowChecked(index))
				{
					rsm.addSelectionInterval(index, index);		//  Select the original row			
				}
				else if(!isRowChecked(index) && table.isRowSelected(index))
				{
					rsm.removeSelectionInterval(index, index);
				}
            	((DefaultListSelectionModel) rsm).moveLeadSelectionIndex(leadRow);        		
        	}
        }
        
        Rectangle cellRect = table.getCellRect(leadRow, 0, false);
        if (cellRect != null) {
            table.scrollRectToVisible(cellRect);
        }
    }


    @SuppressWarnings("serial")
	private Action doNothing = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
        	//log.fine("Doing Nothing!!");
            //do nothing
        }
    };

    @SuppressWarnings("serial")
	private Action doSelectAll = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
          
        	int leadRow = 0;
            
            MiniTable table = (MiniTable)e.getSource();

            if (table.getRowCount() <= 0 || table.getColumnCount() <= 0) 
            {
                // bail - don't try to move selection on an empty table
                return;
            }
            
            ListSelectionModel rsm = table.getSelectionModel();
            int index = 0;
            int compare = table.getRowCount();
            
            if(table.getShowTotals())
            	compare = compare -1;

            leadRow = compare-1;        		
            	    					
			rsm.addSelectionInterval(index, leadRow);
			matchCheckWithSelectedRows();        		
			((DefaultListSelectionModel) rsm).moveLeadSelectionIndex(index);
            
            Rectangle cellRect = table.getCellRect(index, 0, false);
            if (cellRect != null) {
                table.scrollRectToVisible(cellRect);
            }
        }
    };

    @SuppressWarnings("serial")
	private Action doSelectRowDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) 
        {
        	int dy = 1;       
        	String actionName = "SelectRowDown";
            doAction(e, actionName, dy);
        }
    };

    @SuppressWarnings("serial")
	private Action doSelectRowUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) 
        {
            int dy = -1;
        	String actionName = "SelectRowUp";
            doAction(e, actionName, dy);
        }            
    };

    @SuppressWarnings("serial")
	private Action doAddRowUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) 
        {        	
        	int dy = -1;
            String actionName = "AddRowUp";
            doAction(e, actionName, dy);
        }
    };
    
    @SuppressWarnings("serial")
	private Action doAddRowDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            int dy = 1;
            String actionName = "AddRowDown";
            doAction(e, actionName, dy);
        }
    };

    @SuppressWarnings("serial")
	private Action doAddRowUpExtend = new AbstractAction() {
        public void actionPerformed(ActionEvent e) 
        {
            int dy = -1;
            String actionName = "AddRowUpExtend";
            doAction(e, actionName, dy);
        }
    };
    
    @SuppressWarnings("serial")
	private Action doAddRowDownExtend = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            int dy = 1;
            String actionName = "AddRowDownExtend";
            doAction(e, actionName, dy);            
        }
    };

    @SuppressWarnings("serial")
	private Action doChangeLeadUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) 
        {
            int dy = -1;
            String actionName = "ChangeLeadUp";
            doAction(e, actionName, dy);            
        }
    };

    @SuppressWarnings("serial")
	private Action doChangeLeadDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) 
        {
            int dy = 1;
            String actionName = "ChangeLeadDown";
            doAction(e, actionName, dy);            
        }
    };

    @SuppressWarnings("serial")
	protected Action doMatchIdToSelection = new AbstractAction() {
    	
    	//  General approach copied from BasicTableUI.java
        public void actionPerformed(ActionEvent e) {
        	matchCheckWithSelectedRows();
        }
    };

    @SuppressWarnings("serial")
	protected Action doToggleID = new AbstractAction() {
    	
    	public void actionPerformed(ActionEvent e) {
        	toggleLeadRowChecked();
        }
    };

    @SuppressWarnings("serial")
	protected Action doToggleBySelection = new AbstractAction() {
    	
    	public void actionPerformed(ActionEvent e) {
        	toggleRowCheckedBySelection();
        }
    };
	private boolean m_changingMultipleRows;

    /**
     * Match the row check with the row selection (highlight) in the table
     */
    public void matchCheckWithSelectedRows() {

    	int rows = this.getRowCount();

		if (this.getShowTotals())
			rows = rows - 1;

    	if (rows <= 0)
    		return;

		// Check if the lead row is selected, if not, select it
		ListSelectionModel rsm = this.getSelectionModel();
		int leadRow = rsm.getLeadSelectionIndex();
		if ((leadRow >=0 && leadRow < rows && !rsm.isSelectedIndex(leadRow)))
		{
			if (isMultiSelection())
				rsm.addSelectionInterval(leadRow, leadRow);
			else
				rsm.setSelectionInterval(leadRow, leadRow);
		}
		
		//  In case
		if (this.getShowTotals())
		{
			int totalRow = this.getRowCount()-1;
			if (rsm.isSelectedIndex(totalRow))
				rsm.removeIndexInterval(totalRow, totalRow);
		}
		//  Set the id column to match the selection
		int selectedRows[] = this.getSelectedRows();
		
		m_changingMultipleRows = true;
		
		if (selectedRows.length < rows) //  Not everything is selected
		{
			//  Deselect everything
			for (int row = 0; row < rows; row++)
			{
				setRowChecked(row, false);
			}
		}
		//  Set the selected rows
		for (int row : selectedRows)
		{
			setRowChecked(row, true);
		}
		
		fireRowSelectionEvent();
		m_changingMultipleRows = false;
	}

    /**
     * Toggles the selection checkbox of the current lead row. Adds or removes the lead row from
     * the selection accordingly.  
     */
    private void toggleLeadRowChecked() {

    	// Check if the lead row is selected, if not, select it
		ListSelectionModel rsm = this.getSelectionModel();
		int leadRow = rsm.getLeadSelectionIndex();
		if (leadRow == -1)
			return;

		//  Toggle
		setRowChecked(leadRow, !isRowChecked(leadRow));
		if (isRowChecked(leadRow))
		{
			if (isMultiSelection())
				rsm.addSelectionInterval(leadRow, leadRow);
			else
				rsm.setSelectionInterval(leadRow, leadRow);
		}
		else
		{
			rsm.removeSelectionInterval(leadRow, leadRow);
			rsm.setLeadSelectionIndex(leadRow);
		}
	}

    /**
     * Toggles the selection checkbox of the given row. Adds or removes the row from
     * the selection accordingly.
     * @param row - the row in the view to toggle  
     */
    private void toggleRowChecked(int row) {

    	//  Range check
		int rows = this.getRowCount();
		if(this.getShowTotals())
			rows = rows - 1;		
		if (row < 0 || row >= rows)
			return;

		ListSelectionModel rsm = this.getSelectionModel();

		//  Toggle
    	setRowChecked(row, !isRowChecked(row));
		if (isRowChecked(row))
		{
			if (isMultiSelection())
				rsm.addSelectionInterval(row, row);
			else
				rsm.setSelectionInterval(row, row);
		}
		else
		{
			rsm.removeSelectionInterval(row, row);
		}
	}

    /**
     * Toggles the selection checkbox of a range or rows. Adds or removes the rows from
     * the selection accordingly.
     * @param index0 - one end of the range
     * @param index1 - the other end of the range. Can be less than, equal or greater than index0.
     */
    private void toggleRowCheckedRange(int index0, int index1) {
		if (isMultiSelection())
		{
			if (getKeyColumnIndex() >= 0)
			{
				int rows = this.getRowCount();
				if(this.getShowTotals())
					rows = rows - 1;
				
				if (index0 < 0 || index0 >= rows || index1 < 0 || index1 >= rows)
					return;

				ListSelectionModel rsm = this.getSelectionModel();
				int leadRow = rsm.getLeadSelectionIndex();

				int low = index0 <= index1 ? index0 : index1;
				int high = index0 <= index1 ? index1 : index0;

				// Limit the number of row selection events to once per change
				m_changingMultipleRows = true;
				
				for (int row = low; row <= high; row++)
				{
					toggleRowChecked(row);
				}
				//  Return the lead to its original location
				rsm.setLeadSelectionIndex(leadRow);

				fireRowSelectionEvent();
				m_changingMultipleRows = false;

			}
		}
	}

    /**
     * Toggles the ID selection of the selected rows.  Multi-selection only.
     */
    private void toggleRowCheckedBySelection() {
		if (isMultiSelection())
		{
			ListSelectionModel rsm = this.getSelectionModel();
			int leadRow = rsm.getLeadSelectionIndex();
			
			int[] selectedRows = this.getSelectedRows();
			
			// Limit the number of row selection events to once per change
			if (selectedRows.length > 1)
				m_changingMultipleRows = true;
			else
				m_changingMultipleRows = false;
			
			for (int row : selectedRows)
			{
				toggleRowChecked(row);
			}
			//  Return the lead to its original location
			rsm.setLeadSelectionIndex(leadRow);
			
			if (m_changingMultipleRows)
			{
				fireRowSelectionEvent();
				m_changingMultipleRows = false;
			}
		}
	}

    /**
     * If the table row has a IDColumn or a boolean checkbox in the KeyColumnIndex
     * this function will set the checkbox according to the setValue parameter
     * @param row - the view row
     * @param setValue - the checkbox value to set 
     */
    public void setRowChecked(int row, boolean setValue)
    {   	
        //  The key column will be defined or zero by default.
    	//  Check the class of the data in the cell to verify if 
    	//  it is a selection column.  Selection columns can be
    	//  of type IDColumn or Boolean.
    	Object data = this.getValueAt(row, this.convertColumnIndexToView(getKeyColumnIndex()));
		if (data instanceof IDColumn)
		{
			IDColumn id = (IDColumn)data;
			id.setSelected(setValue);
			
		}
		else if (data instanceof Boolean)
		{
			data = setValue;
		}
		else return;
		
		this.setValueAt(data, row, this.convertColumnIndexToView(getKeyColumnIndex()));
		
		if (!m_changingMultipleRows)
			fireRowSelectionEvent();

    }
    
    public void fireRowSelectionEvent() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Lazily create the event:
        RowSelectionEvent rowSelectionEvent = new RowSelectionEvent(this, RowSelectionEvent.ROW_TOGGLED);

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MiniTableSelectionListener.class) {
                ((MiniTableSelectionListener)listeners[i+1]).rowSelected(rowSelectionEvent);
            }          
        }
    }    

    /**
     * Adds a <code>ChangeListener</code> to the button.
     * @param l the listener to be added
     */
    public void addMiniTableSelectionListener(MiniTableSelectionListener l) {
        listenerList.add(MiniTableSelectionListener.class, l);
    }

    /**
     * {@inheritDoc}
     */
    public void removeMiniTableSelectionListener(MiniTableSelectionListener l) {
        listenerList.remove(MiniTableSelectionListener.class, l);
    }

}   //  MiniTable
