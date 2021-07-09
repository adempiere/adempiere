/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.grid;

import java.awt.Component;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.compiere.grid.ed.VCellEditor;
import org.compiere.grid.ed.VCellRenderer;
import org.compiere.grid.ed.VFrozenColumnRenderer;
import org.compiere.grid.ed.VHeaderRenderer;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IDColumnEditor;
import org.compiere.minigrid.IDColumnRenderer;
import org.compiere.minigrid.IMiniTable;
import org.compiere.minigrid.ROCellEditor;
import org.compiere.minigrid.RowSelectionEvent;
import org.compiere.minigrid.SelectionListener;
import org.compiere.minigrid.TableChangeEvent;
import org.compiere.minigrid.TableChangeListener;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.MRole;
import org.compiere.model.PO;
import org.compiere.swing.CColumnControlButton;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;
import org.jdesktop.swingx.action.BoundAction;

/**
 * Table Grid based on CTable. Used in GridController
 *
 * @author Jorg Janke
 * @version $Id: VTable.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL - FR [ 1753943 ]
 * @author mckayERP www.mckayERP.com
 *         <li>#271 Allow immediate editing of GridTable cells in multi-row view
 *         <li>Allow VTable to be used in place of a MiniTable where editing of
 *         cells is required
 *         <li>Add capability to freeze columns
 */
public class VTable extends CTable implements IMiniTable, PropertyChangeListener, FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2655102084935019329L;
	private final static String PACK_ALL_COMMAND = CColumnControlButton.COLUMN_CONTROL_MARKER + "packAll";
	private final static String DELETE_CELL_VALUE = "DeleteCellValue";
	private final static String RESET_CELL_VALUE = "ResetCellValue";
	private final static String SELECT_ALL_ROWS = "SelectAllRows";

	private final static String SWITCH_LINES_DOWN = "switchLinesDown";
	private final static String SWITCH_LINES_UP = "switchLinesUp";

	/**
	 * Define the ui class ID. It will be added to the look and feel to define the
	 * ui class to use.
	 * 
	 * @since 3.9.4
	 */
	private final static String uiClassID = "TableUI";

	/**
	 * Provides the UI Class ID string
	 * 
	 * @since 3.9.4
	 */
	@Override
	public String getUIClassID() {
		return uiClassID;
	}

	/**
	 * An action to delete the contents of a cell when the delete key is pressed.
	 * The deleted value is lost
	 * 
	 * @author Michael McKay, mckayERP@gmail.com
	 * @since 3.9.4
	 */
	@SuppressWarnings("serial")
	class DeleteAction extends AbstractAction {
		VTable table;

		public DeleteAction(VTable table) {
			super(DELETE_CELL_VALUE);
			this.table = table;
		}

		public void actionPerformed(ActionEvent e) {
			// Note, you can use getSelectedRows() and/or getSelectedColumns
			// to get all the rows/columns that have being selected
			// and simply loop through them using the same method as
			// described below.
			// As is, it will only get the lead selection
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			if (row >= 0 && col >= 0) {
				row = table.convertRowIndexToModel(row);
				col = table.convertColumnIndexToModel(col);
				// Record the original value so the user can escape the edit
				// and recover it.
				preDeleteValue = table.getModel().getValueAt(row, col);
				table.getModel().setValueAt(null, row, col);
				table.editCellAt(row, col);
			}
		}
	}

	/**
	 * An action to handle the ignore function when editing a cell.
	 * 
	 * @author Michael McKay, mckayERP@gmail.com
	 * @since 3.9.4
	 */
	@SuppressWarnings("serial")
	class ResetCellAction extends AbstractAction {
		VTable table;

		public ResetCellAction(VTable table) {
			super(RESET_CELL_VALUE);
			this.table = table;
		}

		public void actionPerformed(ActionEvent e) {
			// Escape is used to trigger the APanel ignore action
			// but it also is used to cause the cell to stop
			// editing. Unfortunately, the stopEditor call
			// is never made so we are doing it here instead.
			if (!table.isEditing())
				return;

			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();

			Object value = table.getValueAt(row, column);

			// Revert to the preDelete or preChange value, if any
			if (preDeleteValue != null)
				value = preDeleteValue;

			table.stopEditor(false);
			table.setValueAt(value, row, column);
		}
	}

	/**
	 * An action to select all rows in a table if the table has multi-selection
	 * enabled.
	 * 
	 * @author Michael McKay, mckayERP@gmail.com
	 * @since 3.9.4
	 *
	 */
	@SuppressWarnings("serial")
	class SelectAllRowsAction extends AbstractAction {
		VTable table;

		public SelectAllRowsAction(VTable table) {
			super(SELECT_ALL_ROWS);
			this.table = table;
		}

		@Override
		public boolean isEnabled() {
			return table.isMultiSelection();
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() instanceof JTableHeader) {
				TableColumnModel model = ((JTableHeader) e.getSource()).getColumnModel();
				Enumeration<TableColumn> columns = model.getColumns();
				while (columns.hasMoreElements()) {
					TableColumn column = columns.nextElement();
					if (column.getHeaderRenderer() != null && column.getHeaderRenderer().equals(e.getSource())) {
						if (column.getHeaderRenderer() instanceof VHeaderRenderer) {
							JCheckBox checkAll = ((VHeaderRenderer) column.getHeaderRenderer()).getSelectAllCheckBox();
							if (checkAll != null) {
								checkAll.doClick();
								break;
							}
						}
					}
				}
			}
		}
	} // selectAllRowsAction

	/**
	 * Simple action class for the resorting of tablelines (switch line no).
	 * Delegates actionPerformed to APanel. This was moved from the APanel class to
	 * support frozen columns.
	 *
	 * @author Karsten Thiemann, kthiemann@adempiere.org
	 *
	 * @since 3.9.4
	 */
	class SwitchAction extends AbstractAction {

		// Moved from APanel to support frozen columns
		// The frozen column table and the main table use the same model,
		// so switching rows in one will also switch rows in the other.

		/**
		 *
		 */
		private static final long serialVersionUID = 3837712049468116744L;

		/** action name */
		private String name;

		/**
		 * Constructor.
		 * 
		 * @param name
		 * @param accelerator
		 * @param al
		 */
		SwitchAction(String name, KeyStroke accelerator) {
			super(name);
			putValue(Action.NAME, name); // Display
			putValue(Action.SHORT_DESCRIPTION, name); // Tooltip
			putValue(Action.ACCELERATOR_KEY, accelerator); // KeyStroke
			putValue(Action.ACTION_COMMAND_KEY, name); // ActionCammand
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			// up-key + shift
			if (VTable.this.gridController == null)
				return;

			// Stop editing, if any
			removeEditor();
			GridTab currentTab = VTable.this.gridController.getMTab();

			if (SWITCH_LINES_UP.equals(name)) {
				currentTab.switchRows(currentTab.getCurrentRow(), currentTab.getCurrentRow() - 1, getSortColumn(),
						isSortAscending());
			} else if (SWITCH_LINES_DOWN.equals(name)) {
				currentTab.switchRows(currentTab.getCurrentRow(), currentTab.getCurrentRow() + 1, getSortColumn(),
						isSortAscending());
			}

			requestFocus();

		} // actionPerformed

		public String getName() {
			return name;
		}
	}

	/** The grid controller used to manage this table */
	private GridController gridController;

	/** The ColumnInfo array that defines the table layout */
	private ColumnInfo[] layout;

	/** A flag indicating if the table allows multi-selection of rows */
	private boolean allowsMultiSelection;

	/** List of R/W columns */
	private ArrayList<Integer> readWriteColumns = new ArrayList<Integer>();
	/** List of mandatory columns */
	private ArrayList<Integer> mandatoryColumns = new ArrayList<Integer>();
	/** List of Column Width */
	private ArrayList<Integer> minWidths = new ArrayList<Integer>();

	// Some basic status flags
	private boolean autoResize;
	private boolean changingMultipleRows;
	private boolean isSelectingAll;
	private boolean isDeselectingAll;

	/** A holder for the value of the cell prior to the delete action */
	private Object preDeleteValue;

	private int lastSelectedRow = 0;
	private int lastSelectedColumn = 0;

	/**
	 * A flag indicating whether the table allows frozen columns. Default is true.
	 */
	private boolean isFreezeColumnsEnabled = true;

	/** A flag indicating if the table has any frozen columns. */
	private boolean hasFrozenColumns = false;

	/** The frozen column renderer being used */
	private VFrozenColumnRenderer frozenColumnRenderer;

	/** The actions for switching rows in a sequenced table */
	private SwitchAction aSwitchLinesDownAction, aSwitchLinesUpAction;

	/**
	 * Default Constructor
	 */
	public VTable() {
		super();
		setAutoscrolls(true);
		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		// Add a focus listener to ensure that the row selection is set
		// when focus is gained and removed when focus is lost.
		this.addFocusListener(this);

		// Ensure the table header does not receive the focus so the tab seqence moves
		// smoothly into and out of the table
		this.getTableHeader().setFocusable(false);

		new VTableExcelAdapter(this); // teo_sarca - FR [ 1753943 ]

		// Listen to list selection events to assist with multi-seleciton
		getSelectionModel().addListSelectionListener(this);

		// Add input map and actions to manage the Delete and Reset actions
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE_CELL_VALUE);
		getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				RESET_CELL_VALUE);
		getActionMap().put(DELETE_CELL_VALUE, new DeleteAction(this));
		getActionMap().put(RESET_CELL_VALUE, new ResetCellAction(this));

		// Prevent the use of Ctrl-up or Ctrl-down that normally extends the selection
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.CTRL_MASK), "none");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.CTRL_MASK), "none");
		getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.CTRL_MASK),
				"none");
		getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.CTRL_MASK),
				"none");

		getActionMap().put(PACK_ALL_COMMAND, createPackAllAction());

		// Add the actions that switch lines in a sequenced table.
		initSwitchLineAction();

	} // VTable

	private Action createPackAllAction() {
		// TODO: localization
		BoundAction action = new BoundAction("Size All Column", PACK_ALL_COMMAND);
		action.setLongDescription("Size all column to fit content");
		action.registerCallback(this, "packAll");
		return action;
	}

	/**
	 * Size all column to fit content.
	 */
	public void packAll() {
		autoSize(true);
	}

	/** Logger */
	private static CLogger log = CLogger.getCLogger(VTable.class);

	/**
	 * Property Change Listener for CurrentRow. - Selects the current row if not
	 * already selected - Required when navigating via Buttons
	 * 
	 * @param evt event
	 */
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals(GridTab.PROPERTY)) {

			int row = ((Integer) evt.getNewValue()).intValue();
			int selRow = getSelectedRow();
			if (row == selRow)
				return;

			log.fine(GridTab.PROPERTY + "=" + row + " from " + selRow);

			setRowSelectionInterval(row, row);
			setColumnSelectionInterval(0, 0);
			Rectangle cellRect = getCellRect(row, 0, false);
			if (cellRect != null)
				scrollRectToVisible(cellRect);

		}
	} // propertyChange

	/**
	 * Get ColorCode for Row.
	 * 
	 * <pre>
	 *	If numerical value in compare column is
	 *		negative = -1,
	 *      positive = 1,
	 *      otherwise = 0
	 * </pre>
	 * 
	 * @param row row
	 * @return color code
	 */
	public int getColorCode(int row) {
		if (getModel() instanceof GridTable)
			return ((GridTable) getModel()).getColorCode(row);

		// TODO add MiniTable functionality for color code
		return 0;

	} // getColorCode

	/**
	 * Sort Table Overrides CTable.sort() to allow the gridTable model to provide
	 * sorting on model data
	 * 
	 * @param modelColumnIndex model column sort index
	 */
	@Override
	protected void sort(int modelColumnIndex) {

		if (sorting)
			return;

		int rows = getRowCount();
		if (rows == 0)
			return;

		// Record the current value so the cell can be selected again.
		// A cell may not be selected so note that as well.
		int column = this.getSelectedColumn();
		int row = this.getSelectedRow();
		Object value = null;
		boolean hasSelectedCell = false;

		if (0 <= row && row < this.getRowCount() && 0 <= column && column < this.getColumnCount()) {
			value = this.getValueAt(row, column);
			hasSelectedCell = true;
		}

		TableModel model = getModel();

		sorting = true;
		if (!(model instanceof GridTable)) {
			// Revert to the CTable implementation of the sort
			super.sort(modelColumnIndex);
		} else {
			// other sort column
			if (modelColumnIndex != p_lastSortIndex)
				p_asc = true;
			else
				p_asc = !p_asc;

			p_lastSortIndex = modelColumnIndex;

			log.config("#" + modelColumnIndex + " - rows=" + rows + ", asc=" + p_asc);

			((GridTable) model).sort(modelColumnIndex, p_asc);
		}

		// Try to reselect the previously selected row
		if (hasSelectedCell && row >= 0) {
			for (int r = 0; r < rows; r++) {
				if (value.equals(getValueAt(r, column))) {
					setRowSelectionInterval(r, r);
					this.changeSelection(r, column, false, false);
					scrollRectToVisible(getCellRect(r, column, true)); // teo_sarca: bug fix [ 1585369 ]
					break;
				}
			}

		} // selected != null

		sorting = false;

		// table model fires "Sorted" DataStatus event which causes MTab to position to
		// row 0
	} // sort

	/**
	 * toString
	 * 
	 * @return String representation
	 */
	public String toString() {
		return new StringBuffer("VTable[").append(getModel()).append("]").toString();
	} // toString

	/**
	 * Set the grid controller that controls this table.
	 * 
	 * @param gridController
	 * @since 3.9.4
	 */
	public void setGridController(GridController gridController) {

		this.gridController = gridController;

		if (this.frozenColumnRenderer != null)
			frozenColumnRenderer.setGridController(gridController);

	}

	/**
	 * Get the grid controller that is controlling this table
	 * 
	 * @return the grid controller
	 * @since 3.9.4
	 */
	public GridController getGridController() {

		return gridController;

	}

	/**
	 * Set the column as readOnly. This adds the column to the list of read only
	 * columns.
	 * 
	 * @param column, the column index
	 * @param readOnly, true if the column is read only
	 */
	@Override
	public void setColumnReadOnly(int column, boolean readOnly) {

		// Remove the column from the list if it is there. This
		// does nothing if the column is not in the list.
		readWriteColumns.remove(new Integer(column));

		// If readOnly, add the column
		if (!readOnly)
			readWriteColumns.add(new Integer(column));
	}

	/**
	 * Set the column as mandatory. This adds the column to the list of mandatory
	 * columns.
	 * 
	 * @param column, the column index
	 * @param mandatory, true if the column is mandatory
	 */
	@Override
	public void setColumnMandatory(int column, boolean mandatory) {

		mandatoryColumns.remove(new Integer(column));

		// If mandatory, add the column.
		if (mandatory)
			mandatoryColumns.add(new Integer(column));

	}

	/**
	 * Prepare the table data and column models.
	 * 
	 * @param       layout, the ColumnInfo array that defines the columns
	 * @param from  the SQL From clause to use when searching for data
	 * @param where the SQL Where clause to use when searching for data
	 * @param       multiSelection, true if the table should allow multiselection
	 * @return the full SQL string to use in a query to generate data for the table.
	 */
	@Override
	public String prepareTable(ColumnInfo[] layout, String from, String where, boolean multiSelection,
			String tableName) {

		// Remove the table model info
		clear();

		this.layout = layout;

		setRowSelectionAllowed(true);
		setMultiSelection(multiSelection);

		// Set the columns and column class. This is done in two
		// steps: first to add the column, which clears the class;
		// and second to set the correct class for the column.
		// TODO: Combine these into a single op?
		// Step 1
		for (int i = 0; i < layout.length; i++)
			addColumn(layout[i].getColHeader());
		// Step 2
		for (int i = 0; i < layout.length; i++) {
			setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].isMandatory(),
					layout[i].getColHeader());
			// TODO enable color column
			if (layout[i].getColClass() == IDColumn.class)
				p_keyColumnIndex = i;
		}

		// Build the sql
		StringBuffer sql = new StringBuffer("SELECT ");
		// add columns & sql
		for (int i = 0; i < layout.length; i++) {
			// create sql
			if (i > 0)
				sql.append(", ");
			// adding ID column
			if (layout[i].isKeyPairCol())
				sql.append(layout[i].getKeyPairColSQL()).append(", ");
			sql.append(layout[i].getColSQL());

		}

		sql.append(" FROM ").append(from);
		sql.append(" WHERE ").append(where);

		// org.compiere.apps.form.VMatch.dynInit calls routine for initial init only
		if (from.length() == 0)
			return sql.toString();
		//
		String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(), tableName, MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
		log.finest(finalSQL);
		return finalSQL;
	} // prepareTable

	/**
	 * Add Table Column. after adding a column, you need to set the column classes
	 * again (DefaultTableModel fires TableStructureChanged, which calls
	 * JTable.tableChanged .. createDefaultColumnsFromModel
	 * 
	 * @param header header
	 */
	@Override
	public void addColumn(String header) {
		if (getModel() instanceof DefaultTableModel) {
			DefaultTableModel model = (DefaultTableModel) getModel();
			model.addColumn(Util.cleanAmp(header));
		} else
			throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
	} // addColumn

	/**
	 * Set Column Editor & Renderer to Class. (after all columns were added)
	 * 
	 * @param index    column index
	 * @param c        class of column - determines renderere
	 * @param readOnly read only flag
	 */
	@Override
	public void setColumnClass(int index, @SuppressWarnings("rawtypes") Class c, boolean readOnly) {
		setColumnClass(index, c, readOnly, null);
	} // setColumnClass

	/**
	 * Set Column Editor & Renderer to Class (after all columns were added) Lauout
	 * of IDColumn depemds on multiSelection
	 * 
	 * @param index    column index
	 * @param c        class of column - determines renderere/editors supported:
	 *                 IDColumn, Boolean, Double (Quantity), BigDecimal (Amount),
	 *                 Integer, Timestamp, String (default)
	 * @param readOnly read only flag
	 * @param header   optional header value
	 */
	@Override
	public void setColumnClass(int index, @SuppressWarnings("rawtypes") Class c, boolean readOnly, String header) {
		setColumnClass(index, c, 0, readOnly, false, header);
	}

	/**
	 * Set Column Editor & Renderer to Class (after all columns were added) Lauout
	 * of IDColumn depemds on multiSelection
	 * 
	 * @param index     column index
	 * @param c         class of column - determines renderere/editors supported:
	 *                  IDColumn, Boolean, Double (Quantity), BigDecimal (Amount),
	 *                  Integer, Timestamp, String (default)
	 * @param readOnly  read only flag
	 * @param mandatory mandatory flag
	 * @param header    optional header value
	 */
	public void setColumnClass(int index, @SuppressWarnings("rawtypes") Class c, boolean readOnly, boolean mandatory,
			String header) {
		setColumnClass(index, c, 0, readOnly, mandatory, header);
	}

	/**
	 * Set Column Editor & Renderer to Class (after all columns were added) Layout
	 * of IDColumn depends on multiSelection
	 * 
	 * @param index       column index
	 * @param c           class of column - determines renderer/editors supported:
	 * @param DisplayType define Type Value IDColumn, Boolean, Double (Quantity),
	 *                    BigDecimal (Amount), Integer, Timestamp, String (default)
	 * @param readOnly    read only flag
	 * @param header      optional header value
	 */
	public void setColumnClass(int index, @SuppressWarnings("rawtypes") Class c, int displayType, boolean readOnly,
			String header) {
		setColumnClass(index, c, 0, readOnly, false, header);
	}

	/**
	 * Set Column Editor & Renderer to Class (after all columns were added) Layout
	 * of IDColumn depends on multiSelection
	 * 
	 * @param index       column index
	 * @param c           class of column - determines renderer/editors supported:
	 * @param DisplayType define Type Value IDColumn, Boolean, Double (Quantity),
	 *                    BigDecimal (Amount), Integer, Timestamp, String (default)
	 * @param readOnly    read only flag
	 * @param mandatory   mandatory flag
	 * @param header      optional header value
	 */
	public void setColumnClass(int index, @SuppressWarnings("rawtypes") Class c, int displayType, boolean readOnly,
			boolean mandatory, String header) {

		TableColumn tc = getColumnModel().getColumn(index);
		if (tc == null)
			return;
		// Set R/O
		setColumnReadOnly(index, readOnly);
		setColumnMandatory(index, mandatory);

		// Header
		if (header != null && header.length() > 0)
			tc.setHeaderValue(Util.cleanAmp(header));

		if (displayType == 0)
			displayType = DisplayType.getDisplayTypeFromClass(c);

		tc.setHeaderRenderer(new VHeaderRenderer(displayType));
		tc.setCellRenderer(new VCellRenderer(displayType));
		tc.setCellEditor(new VCellEditor(c, displayType));

		// ID Column & Selection
		if (c == IDColumn.class) {
			IDColumnRenderer idcr = new IDColumnRenderer(allowsMultiSelection);
			tc.setCellRenderer(idcr);

			if (allowsMultiSelection) {
				// Multi-selection requires a different header with a "Select All" checkbox
				VHeaderRenderer vhr = new VHeaderRenderer(allowsMultiSelection);
				tc.setCellEditor(new IDColumnEditor());
				tc.setHeaderRenderer(vhr);
				idcr.addChangeListener(vhr); // Connect the IDColumn with the header
				setColumnReadOnly(index, false); // Change to R/W so the selection is possible
				getTableHeader().addMouseListener(vhr);
			} else {
				tc.setCellEditor(new ROCellEditor());
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
			}
			minWidths.add(new Integer(10));
			tc.setMaxWidth(20);
			tc.setPreferredWidth(20);
			tc.setResizable(false);

			this.getTableHeader().setFocusable(allowsMultiSelection);

		}
		// Boolean
		else if (DisplayType.YesNo == displayType || c == Boolean.class) {
			if (!readOnly && allowsMultiSelection) {
				//				//  This allows users to select all or none of a checkbox field
				//				//  Not sure where or how this would work in a VTable. Would it
				//				//  be a good idea to allow IsActive to have a "select all"?
				//				VHeaderRenderer vhr = new VHeaderRenderer(allowsMultiSelection);
				//				tc.setHeaderRenderer(vhr);
				//				((CCheckBox) ((VCellEditor) tc.getCellEditor()).getEditor()).addChangeListener(vhr);  //  Connect the check control with the header
				//		        getTableHeader().addMouseListener(vhr);
				//		        getTableHeader().addFocusListener(vhr);			        
			}
			minWidths.add(new Integer(30));

		}
		// Date
		else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType || c == Timestamp.class) {
			minWidths.add(new Integer(30));
		}
		// Amount
		else if (DisplayType.Amount == displayType || c == BigDecimal.class) {
			minWidths.add(new Integer(80));
		}
		// Number
		else if (DisplayType.Number == displayType || c == Double.class) {
			minWidths.add(new Integer(80));
		}
		// Integer
		else if (DisplayType.Integer == displayType || c == Integer.class) {
			minWidths.add(new Integer(30));
		}
		// String
		else {
			minWidths.add(new Integer(30));
		}

		// log.fine( "Renderer=" + tc.getCellRenderer().toString() + ", Editor=" +
		// tc.getCellEditor().toString());
	} // setColumnClass

	@Override
	public void loadTable(ResultSet rs) {

		if (layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		// Clear Table
		setRowCount(0);
		//
		try {
			while (rs.next()) {
				int row = getRowCount();
				setRowCount(row + 1);
				int colOffset = 1; // columns start with 1
				for (int col = 0; col < layout.length; col++) {
					Object data = null;
					Class<?> c = layout[col].getColClass();
					int colIndex = col + colOffset;
					if (c == IDColumn.class)
						data = new IDColumn(rs.getInt(colIndex));
					else if (c == Boolean.class)
						data = new Boolean("Y".equals(rs.getString(colIndex)));
					else if (c == Timestamp.class)
						data = rs.getTimestamp(colIndex);
					else if (c == BigDecimal.class)
						data = rs.getBigDecimal(colIndex);
					else if (c == Double.class)
						data = rs.getDouble(colIndex);
					else if (c == Integer.class)
						data = rs.getInt(colIndex);
					else if (c == KeyNamePair.class) {
						String display = rs.getString(colIndex);
						int key = rs.getInt(colIndex + 1);
						data = new KeyNamePair(key, display);
						colOffset++;
					} else {
						String s = rs.getString(colIndex);
						if (s != null)
							data = s.trim(); // problems with NCHAR
					}

					// store
					setValueAt(data, row, col);

					// log.fine( "r=" + row + ", c=" + col + " " + m_layout[col].getColHeader(),
					// "data=" + data.toString() + " " + data.getClass().getName() + " * " +
					// m_table.getCellRenderer(row, col));
				}

			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
		}

		// TODO = add show totals to VTable?
		//		if(getShowTotals())
		//			addTotals(layout);

		autoSize();
		log.config("Row(rs)=" + getRowCount());

	} // loadTable

	/**
	 * Load Table from Object Array
	 * 
	 * @param pos array of POs
	 */
	public void loadTable(PO[] pos) {
		if (layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		// Clear Table
		setRowCount(0);
		//
		for (int i = 0; i < pos.length; i++) {
			PO myPO = pos[i];
			int row = getRowCount();
			setRowCount(row + 1);

			for (int col = 0; col < layout.length; col++) {
				String columnName = layout[col].getColSQL();
				Object data = myPO.get_Value(columnName);
				if (data != null) {
					Class<?> c = layout[col].getColClass();
					if (c == IDColumn.class)
						data = new IDColumn(((Integer) data).intValue());
					else if (c == Double.class)
						data = new Double(((BigDecimal) data).doubleValue());
				}
				// store
				setValueAt(data, row, col);
			}
		}

		//		if(getShowTotals())
		//			addTotals(layout);

		autoSize();
		log.config("Row(array)=" + getRowCount());

	} // loadTable

	@Override
	public ArrayList<Integer> getSelectedKeys() {

		if (getModel() == null) {
			throw new UnsupportedOperationException("Layout not defined");
		}
		if (p_keyColumnIndex < 0) {
			throw new UnsupportedOperationException("Key Column is not defined");
		}
		//
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int row = 0; row < getRowCount(); row++) {
			Object data = getModel().getValueAt(row, p_keyColumnIndex);
			if (data instanceof IDColumn) {
				IDColumn record = (IDColumn) data;
				if (record.isSelected()) {
					list.add(record.getRecord_ID());
				}
			}
		}
		return list;
	}

	@Override
	public Integer getSelectedRowKey() {
		int selectedRow = getSelectedRow();
		return new Integer(getRowKey(selectedRow));
	}

	@Override
	public int getRowKey(int row) {

		if (getKeyColumnIndex() < 0)
			throw new UnsupportedOperationException("Key Column is not defined");

		int rows = this.getRowCount();

		if (this.getShowTotals())
			rows = rows - 1;

		if (row >= 0 && row < rows) {
			Object data = getValueAt(row, convertColumnIndexToView(getKeyColumnIndex())); // Test
			if (data instanceof IDColumn) {
				IDColumn id = (IDColumn) data;
				return id.getRecord_ID().intValue();
			}
		}
		return 0;

	}

	@Override
	public boolean isRowChecked(int row) {

		int keyColumn = this.getKeyColumnIndex();

		if (keyColumn < 0)
			return false;

		// The selection can be indicated by an IDColumn or Boolean in the keyColumn
		// position
		Object data = getValueAt(row, convertColumnIndexToView(keyColumn));
		if (data instanceof IDColumn)
			return ((IDColumn) data).isSelected();
		else if (data instanceof Boolean)
			return (Boolean) data;

		return false;

	}

	@Override
	public void setRowCount(int rowCount) {

		if (getModel() instanceof DefaultTableModel) {
			DefaultTableModel model = (DefaultTableModel) getModel();
			model.setRowCount(rowCount);
			// log.config( "MiniTable.setRowCount", "rows=" + getRowCount() + ", cols=" +
			// getColumnCount());
		} else
			throw new IllegalArgumentException("Model must be instance of DefaultTableModel");

	}

	@Override
	public ColumnInfo[] getLayoutInfo() {

		return layout;

	}

	@Override
	public boolean getShowTotals() {
		// Not implemented in VTable
		return false;
	}

	@Override
	public void setMultiSelection(boolean multiSelection) {

		allowsMultiSelection = multiSelection;

		if (allowsMultiSelection) {
			this.getTableHeader().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), SELECT_ALL_ROWS);
			this.getTableHeader().getActionMap().put(SELECT_ALL_ROWS, new SelectAllRowsAction(this));
		}
	}

	@Override
	public boolean isMultiSelection() {
		return allowsMultiSelection;
	}

	@Override
	public void setColorCompare(Object dataCompare) {
		// TODO - not implemented for VTable
	}

	@Override
	public void autoSize() {
		if (!autoResize)
			return;

		long start = System.currentTimeMillis();
		//
		final int SLACK = 8; // making sure it fits in a column
		final int MAXSIZE = 300; // max size of a column
		//
		TableModel model = this.getModel();
		int size = model.getColumnCount();
		// for all columns
		for (int col = 0; col < size; col++) {
			// Column & minimum width
			TableColumn tc = this.getColumnModel().getColumn(col);
			int width = 0;
			if (minWidths.size() > col)
				width = ((Integer) minWidths.get(col)).intValue();
			// log.config( "Column=" + col + " " + column.getHeaderValue());

			// Header
			TableCellRenderer renderer = tc.getHeaderRenderer();
			if (renderer == null)
				renderer = new DefaultTableCellRenderer();
			Component comp = renderer.getTableCellRendererComponent(this, tc.getHeaderValue(), false, false, 0, 0);
			// log.fine( "Hdr - preferred=" + comp.getPreferredSize().width + ", width=" +
			// comp.getWidth());
			width = Math.max(width, comp.getPreferredSize().width + SLACK);

			// Cells
			int maxRow = Math.min(30, getRowCount()); // first 30 rows
			for (int row = 0; row < maxRow; row++) {
				renderer = getCellRenderer(row, col);
				boolean isSelected = this.isRowSelected(row);
				comp = renderer.getTableCellRendererComponent(this, getValueAt(row, col), isSelected, false, row, col);
				if (comp != null) {
					int rowWidth = comp.getPreferredSize().width + SLACK;
					width = Math.max(width, rowWidth);
				}
			}
			// Width not greater ..
			width = Math.min(MAXSIZE, width);
			tc.setPreferredWidth(width);
			// log.fine( "width=" + width);
		} // for all columns
		log.finer("Cols=" + size + " - " + (System.currentTimeMillis() - start) + "ms");
	}

	@Override
	public void setRowChecked(int row, boolean value) {

		// The key column will be defined or zero by default.
		// Check the class of the data in the cell to verify if
		// it is a selection column. Selection columns can be
		// of type IDColumn or Boolean.
		Object data = this.getValueAt(row, this.convertColumnIndexToView(getKeyColumnIndex()));
		if (data instanceof IDColumn) {
			IDColumn id = (IDColumn) data;
			id.setSelected(value);

		} else if (data instanceof Boolean) {
			data = value;
		} else
			return;

		this.setValueAt(data, row, this.convertColumnIndexToView(getKeyColumnIndex()));

		if (!changingMultipleRows)
			fireRowSelectionEvent();

	}

	/**
	 * Fire a row selection event for this table
	 */
	public void fireRowSelectionEvent() {

		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Lazily create the event:
		RowSelectionEvent rowSelectionEvent = new RowSelectionEvent(this, RowSelectionEvent.ROW_TOGGLED);

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == SelectionListener.class) {
				((SelectionListener) listeners[i + 1]).rowSelected(rowSelectionEvent);
			}
		}
	}

	/**
	 * Fire a select all rows event for this table. The event can have a start and
	 * end. Row selection events will be fired for each row in the table.
	 * 
	 * @param allRows
	 * @param start
	 */
	public void fireSelectAllEvent(boolean allRows, boolean start) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Lazily create the event:
		int id = allRows ? RowSelectionEvent.ALL_ROWS_SELECTED : RowSelectionEvent.ALL_ROWS_DESELECTED;
		if (!start)
			id = RowSelectionEvent.ALL_ROWS_EVENT_COMPLETED;
		RowSelectionEvent rowSelectionEvent = new RowSelectionEvent(this, id);

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == SelectionListener.class) {
				((SelectionListener) listeners[i + 1]).rowSelected(rowSelectionEvent);
			}
		}
	}

	@Override
	public void recreateListHead() {
		// No action required in Swing.
	}

	/**
	 * Clear the table of all data in both the view and model
	 */
	@Override
	public void clear() {
		// First, stop auto-updates
		boolean flag = getAutoCreateColumnsFromModel();
		setAutoCreateColumnsFromModel(false);
		setRowCount(0);
		// Wipe the columns
		DefaultTableColumnModel tc = new DefaultTableColumnModel();
		setColumnModel(tc);
		// Wipe the table data
		MultiSelectionTableModel tm = new MultiSelectionTableModel();
		setModel(tm);
		// Re-establish the auto-updates
		setAutoCreateColumnsFromModel(flag);
	}

	/**
	 * Adds a <code>SelectionListener</code> to the table.
	 * 
	 * @param l the listener to be added
	 */
	@Override
	public void addTableSelectionListener(SelectionListener l) {
		// Try to remove it to ensure no duplication
		listenerList.remove(SelectionListener.class, l);
		listenerList.add(SelectionListener.class, l);
	}

	@Override
	public void removeTableSelectionListener(SelectionListener l) {

		listenerList.remove(SelectionListener.class, l);

	}

	@Override
	public void addTableChangeListener(TableChangeListener l) {

		listenerList.remove(TableChangeListener.class, l);
		listenerList.add(TableChangeListener.class, l);

	}

	@Override
	public void removeTableChangeListener(TableChangeListener l) {

		listenerList.remove(TableChangeListener.class, l);

	}

	/**
	 * Returns true if the cell at <code>row</code> and <code>column</code> is
	 * editable. Otherwise, invoking <code>setValueAt</code> on the cell will have
	 * no effect.
	 * <p>
	 * <b>Note</b>: The column is specified in the table view's display order, and
	 * not in the <code>TableModel</code>'s column order. This is an important
	 * distinction because as the user rearranges the columns in the table, the
	 * column at a given index in the view will change. Meanwhile the user's actions
	 * never affect the model's column ordering.
	 *
	 *
	 * @param row    the row whose value is to be queried
	 * @param column the column whose value is to be queried
	 * @return true if the cell is editable
	 * @see #setValueAt
	 */
	public boolean isCellEditable(int row, int column) {

		if (getModel() instanceof GridTable)
			return super.isCellEditable(row, column);
		else if (readWriteColumns.contains(convertColumnIndexToModel(column)))
			return super.isCellEditable(row, column);

		return false;

	}

	@Override
	public boolean isCellMandatory(int row, int column) {

		if (mandatoryColumns.contains(convertColumnIndexToModel(column)))
			return true;

		return false;
	}

	/**
	 * Convert the Swing TableModelEvent to a generic TableChangeEvent that can be
	 * used with controllers. Changes to the header row or all columns are ignored.
	 */
	public void tableChanged(TableModelEvent e) {

		int firstRow = e.getFirstRow();
		int lastRow = e.getLastRow();
		int col = e.getColumn();

		if (firstRow != TableModelEvent.HEADER_ROW && lastRow != TableModelEvent.HEADER_ROW
				&& col != TableModelEvent.ALL_COLUMNS) {
			for (int row = firstRow; row <= lastRow; row++) {
				Object value = this.getModel().getValueAt(row, col);
				TableChangeEvent tce = new TableChangeEvent(this, row, col, value);
				fireTableChange(tce);
			}
		}
		super.tableChanged(e);
	}

	/**
	 * Fire a generic TableChangeEvent that can work with controllers in multiple
	 * UIs
	 * 
	 * @param tce The generic TableChangeEvent
	 */
	private void fireTableChange(TableChangeEvent tce) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TableChangeListener.class) {
				((TableChangeListener) listeners[i + 1]).tableChanged(tce);
			}
		}
	}

	@Override
	public void setSelectingAll(boolean eventUnderway) {

		isSelectingAll = eventUnderway;

	}

	@Override
	public boolean isSelectingAll() {

		return isSelectingAll;

	}

	@Override
	public void setDeselectingAll(boolean eventUnderway) {

		isDeselectingAll = eventUnderway;

	}

	@Override
	public boolean isDeselectingAll() {

		return isDeselectingAll;

	}

	/**
	 * Invoked when the table gains the focus. Ensures that at least one row is
	 * selected so the user has a visible indication of where the cursor is.
	 */
	@Override
	public void focusGained(FocusEvent e) {

		int row = this.getSelectedRow();
		int column = this.getSelectedColumn();

		if (row == -1 || column == -1) {
			// Need to ensure a row/column is selected
			row = row == -1 ? lastSelectedRow : 0;
			column = column == -1 ? lastSelectedColumn : 0;
			changeSelection(row, column, false, false);
		}
	}

	/**
	 * Invoked when the table loses focus. Records the currently selected row and
	 * column and then clears the selection to give the user feedback that the table
	 * is not holding the focus.
	 */
	@Override
	public void focusLost(FocusEvent e) {

		if (e.isTemporary())
			return;

		lastSelectedRow = this.getSelectedRow();
		lastSelectedColumn = this.getSelectedColumn();
		this.getSelectionModel().clearSelection();

	}

	/**
	 * @return true if the table has Freeze Columns Enabled
	 */
	public boolean isFreezeColumnsEnabled() {
		return isFreezeColumnsEnabled;
	}

	/**
	 * Enables or disables the Freeze Columns feature
	 * 
	 * @param isFreezeColumnsEnabled the isFreezeColumnsEnabled to set
	 */
	public void setFreezeColumnsEnabled(boolean isFreezeColumnsEnabled) {
		this.isFreezeColumnsEnabled = isFreezeColumnsEnabled;
	}

	/**
	 * Sets the flag indicating if the table currently is showing any frozen columns
	 * 
	 * @param hasFrozen - true if frozen columns are showing.
	 */
	public void setHasFrozenColumns(boolean hasFrozen) {

		hasFrozenColumns = hasFrozen;

	}

	/**
	 * Returns true if the table is currently showing any frozen columns
	 * 
	 * @return the hasFrozenColumns
	 */
	public boolean hasFrozenColumns() {
		return hasFrozenColumns;
	}

	/**
	 * Set the frozen column renderer for this table.
	 * 
	 * @param renderer
	 */
	public void setFrozenColumnRenderer(VFrozenColumnRenderer renderer) {

		frozenColumnRenderer = renderer;

	}

	/**
	 * Gets the frozen column renderer being used by this table.
	 * 
	 * @return the frozenColumnRenderer
	 */
	public VFrozenColumnRenderer getFrozenColumnRenderer() {
		return frozenColumnRenderer;
	}

	/**
	 * Removes the default KeyStroke action for the up/down keys and adds switch
	 * line actions.
	 */
	private void initSwitchLineAction() {

		aSwitchLinesDownAction = new SwitchAction(SWITCH_LINES_DOWN,
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.SHIFT_MASK));
		aSwitchLinesUpAction = new SwitchAction(SWITCH_LINES_UP,
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.SHIFT_MASK));

		getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.SHIFT_MASK),
				aSwitchLinesDownAction.getName());
		getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.SHIFT_MASK),
				aSwitchLinesUpAction.getName());
		getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.SHIFT_MASK),
				aSwitchLinesDownAction.getName());
		getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.SHIFT_MASK),
				aSwitchLinesUpAction.getName());

		getActionMap().put(aSwitchLinesDownAction.getName(), aSwitchLinesDownAction);
		getActionMap().put(aSwitchLinesUpAction.getName(), aSwitchLinesUpAction);

	}

} // VTable
