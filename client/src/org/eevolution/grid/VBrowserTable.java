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
package org.eevolution.grid;

import java.awt.Component;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.adempiere.model.I_AD_Browse_Field;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MViewColumn;
import org.compiere.grid.ed.VCellRenderer;
import org.compiere.grid.ed.VHeaderRenderer;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IDColumnEditor;
import org.compiere.minigrid.IDColumnRenderer;
import org.compiere.minigrid.ROCellEditor;
import org.compiere.model.GridField;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.form.VBrowser;

/**
 * @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 * @author victor.perez@www.e-evolution.com, e-Evolution
 *         Browse Table
 *         Extends CTable, Methods Copy from Minitable implements VLookup Objects for grid
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>FR [ 245 ] Change Smart Browse to MVC
 * 		@see https://github.com/adempiere/adempiere/issues/245
 * 		<li>BR [ 257 ] Smart Browse does not get the hidden fields in Selection Browse
 * 		@see https://github.com/adempiere/adempiere/issues/257
 * 		<li>BR [ 456 ] Smart Browser fill bad value for search
 * 		@see https://github.com/adempiere/adempiere/issues/456
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/560">
 * 		FR [ 560 ] SB on ZK have always editable the columns</a>
 */
public class VBrowserTable extends CTable implements IBrowserTable {
    /**
     *
     */
    private static final long serialVersionUID = 2853772547464132497L;

    /**
     * Logger.
     */
    private static CLogger log = CLogger.getCLogger(VBrowserTable.class);
    /**	Rows				*/
    private BrowserRow browserRows = null;
    /**	Current Row			*/
    private VBrowser browser;
    /**
     * List of R/W columns
     */
    private ArrayList<Integer> m_readWriteColumn = new ArrayList<Integer>();
    /**
     * List of Column Width
     */
    private ArrayList<Integer> m_minWidth = new ArrayList<Integer>();
    /**
     * Color Column Index of Model
     */
    private int m_colorColumnIndex = -1;
    /**
     * Color Column compare data
     */
    private Object m_colorDataCompare = Env.ZERO;
    /**
     * Multi Selection mode (default false)
     */
    private boolean m_multiSelection = false;
    /**
     * Is Total Show
     */
    private boolean showTotals = false;
    /**	Auto-Size				*/
    private boolean autoResize = true;
    /**
     * Context *
     */
    private Properties ctx = Env.getCtx();
    
    /** Layout set in prepareTable and used in loadTable.    */
	private List<MBrowseField> browserFields = null;

    /**
     * Default Constructor
     */
    public VBrowserTable(VBrowser browser) {
        super();
        browserRows = new BrowserRow(this);
        setCellSelectionEnabled(false);
        setRowSelectionAllowed(false);
        //  Default Editor
        this.setCellEditor(new ROCellEditor());

        this.browser = browser;
    }   //  MiniTable

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
        return (getColumnModel().getColumn(col));
    }

    /**
     * Return number of columns in the mini table
     */
    public int getColumnCount() {
        return (getColumnModel().getColumnCount());
    }

    /**
     * Size Columns.
     * Uses Mimimun Column Size
     */
    public void autoSize() {
        if (!autoResize)
            return;

        long start = System.currentTimeMillis();
        //
        final int SLACK = 8;        //	making sure it fits in a column
        final int MAXSIZE = 300;    //	max size of a column
        //
        TableModel model = this.getModel();
        int size = model.getColumnCount();
        //	for all columns
        for (int col = 0; col < size; col++) {
            //  Column & minimum width
            TableColumn tc = this.getColumnModel().getColumn(col);
            int width = 0;
            if (m_minWidth.size() > col)
                width = ((Integer) m_minWidth.get(col)).intValue();
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
            for (int row = 0; row < maxRow; row++) {
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
        }    //	for all columns
        log.finer("Cols=" + size + " - " + (System.currentTimeMillis() - start) + "ms");
    }    //	autoSize


    /**
     * Is Cell Editable
     *
     * @param row    row
     * @param column column
     * @return true if editable
     */
    public boolean isCellEditable(int row, int column) {
        //  if the first column is a boolean and it is false, it is not editable
    	//	Get Selected
        boolean isSelected = false;
        Object value = getValueAt(row, 0);
        if(value instanceof Boolean) {
        	isSelected = ((Boolean) value).booleanValue();
        } else if(value instanceof IDColumn) {
        	isSelected = ((IDColumn) value).isSelected();
        }
        //  is the column RW?
        if (column == 0 
        		|| (isSelected
        				&& m_readWriteColumn.contains(new Integer(column)))) {
        	return true;
        }
        //	Default
        return false;
    }   //  isCellEditable

    /**
     * Set Column to ReadOnly
     *
     * @param column   column
     * @param readOnly read only
     */
    public void setColumnReadOnly(int column, boolean readOnly) {
        //  Column is ReadWrite
        if (m_readWriteColumn.contains(new Integer(column))) {
            //  Remove from list
            if (readOnly) {
                int size = m_readWriteColumn.size();
                for (int i = 0; i < size; i++) {
                    if (((Integer) m_readWriteColumn.get(i)).intValue() == column) {
                        m_readWriteColumn.remove(i);
                        break;
                    }
                }
            }   //  ReadOnly
        }
        //  current column is R/O - ReadWrite - add to list
        else if (!readOnly)
            m_readWriteColumn.add(new Integer(column));
    }   //  setColumnReadOnly
    
    @Override
    public String prepareTable(List<MBrowseField> fields, boolean multiSelection) {
        StringBuffer sql = new StringBuffer("");
        m_multiSelection = multiSelection;
        browserFields = fields;
        int col = 0;
        //  Add columns & sql
        for (MBrowseField field : fields) {
            MViewColumn columnView = field.getAD_View_Column();
            //  create sql
            if (col > 0 && columnView.getColumnSQL().length() > 0)
                sql.append(", ");

            if (field.isKey()) {
                setKey(col);
                field.setName("#");
            }

            sql.append(columnView.getColumnSQL())
                    .append(" ")
                    .append("AS")
                    .append(" ")
                    .append(columnView.getColumnName());

            browserRows.addBrowserField(field, col);
            if (field.isDisplayed()) {
                addColumn(field.get_Translation(I_AD_Browse_Field.COLUMNNAME_Name));
            }
            //	BR [ 257 ]
            col++;
        }
        //	Set Column Class
        col = 0;
        for (MBrowseField field : fields) {
            if (field.isDisplayed()) {
                setColumnClass(col,
                        MBrowseField.createGridFieldVO(field, browser.getWindowNo()),
                        field.getAD_Reference_ID(),
                        field.isReadOnly(),
                    	//	#845 Bad translation for table column
                        field.get_Translation(I_AD_Browse_Field.COLUMNNAME_Name));
                col++;
            }
        }
        //	
        return sql.toString();
    }   //  prepareTable

    /**
     * Add Table Column.
     * after adding a column, you need to set the column classes again
     * (DefaultTableModel fires TableStructureChanged, which calls
     * JTable.tableChanged .. createDefaultColumnsFromModel
     *
     * @param header header
     */
    public void addColumn(String header) {

        if (getModel() instanceof DefaultTableModel) {
            DefaultTableModel model = (DefaultTableModel) getModel();
            model.addColumn(Util.cleanAmp(header));
        } else
            throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
    }   //  addColumn

    /**
     * Set Column Editor & Renderer to Class
     *
     * @param index       column index
     * @param gridField
     * @param displayType define Type Value IDColumn, Boolean, Double (Quantity), BigDecimal (Amount), Integer, Timestamp, String (default)
     * @param readOnly    read only flag
     * @param header      optional header value
     */
    public void setColumnClass(int index, GridField gridField, int displayType, boolean readOnly, String header) {
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
        if (index == p_keyColumnIndex) {
            tc.setCellRenderer(new IDColumnRenderer(m_multiSelection));
            if (m_multiSelection) {
                tc.setCellEditor(new IDColumnEditor());
                setColumnReadOnly(index, false);
            } else {
                tc.setCellEditor(new ROCellEditor());
            }
            m_minWidth.add(new Integer(10));
            tc.setMaxWidth(20);
            tc.setPreferredWidth(20);
            tc.setResizable(false);

            tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
        } else {
            tc.setCellRenderer(new VCellRenderer(gridField));
            tc.setCellEditor(new VBrowserCellEditor(gridField));
            m_minWidth.add(new Integer(30));
            tc.setHeaderRenderer(new VHeaderRenderer(displayType));
        }
    }   //  setColumnClass

    /**
     * Clear Table Content
     *
     * @param no number of rows
     */
    public void setRowCount(int no) {
        if (getModel() instanceof DefaultTableModel) {
            DefaultTableModel model = (DefaultTableModel) getModel();
            model.setRowCount(no);
            //	log.config( "MiniTable.setRowCount", "rows=" + getRowCount() + ", cols=" + getColumnCount());
        } else
            throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
    }   //  setRowCount

    /**
     * Get the key of currently selected row based on layout defined in prepareTable
     *
     * @return ID if key
     */
    public Integer getSelectedRowKey() {

        if (browserRows.getColumnCount() == 0)
            throw new UnsupportedOperationException("Layout not defined");

        int row = getSelectedRow();
        if (row != -1 && p_keyColumnIndex != -1) {
            Object data = getModel().getValueAt(row, p_keyColumnIndex);
            if (data instanceof IDColumn)
                data = ((IDColumn) data).getRecord_ID();
            if (data instanceof Integer)
                return (Integer) data;
        }
        return null;
    }   //  getSelectedRowKey

    /**
     * @return collection of selected IDs
     */
    public Collection<Integer> getSelectedKeys() {
        if (browserRows.getColumnCount() == 0) {
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

    /**
     * Single Selection Table
     *
     * @return true if multiple rows can be selected
     */
    public boolean isMultiSelection() {
        return m_multiSelection;
    }   //  isMultiSelection

    /**
     * Set Single Selection
     *
     * @param multiSelection multiple selections
     */
    public void setMultiSelection(boolean multiSelection) {
        m_multiSelection = multiSelection;
    }   //  setMultiSelection

    /**
     * Set the Column to determine the color of the row (based on model index)
     *
     * @param modelIndex model index
     */
    public void setColorColumn(int modelIndex) {
        m_colorColumnIndex = modelIndex;
    }   //  setColorColumn


    /**
     * Set ColorColumn comparison criteria
     *
     * @param dataCompare data
     */
    public void setColorCompare(Object dataCompare) {
        m_colorDataCompare = dataCompare;
    }   //

    /**
     * Get ColorCode for Row.
     * <pre>
     * 	If numerical value in compare column is
     * 		negative = -1,
     *      positive = 1,
     *      otherwise = 0
     *  If Timestamp
     *  </pre>
     *
     * @param row row
     * @return color code
     */
    public int getColorCode(int row) {
        if (m_colorColumnIndex == -1)
            return 0;

        Object data = getModel().getValueAt(row, m_colorColumnIndex);
        int cmp = 0;

        //	We need to have a Number
        if (data == null)
            return 0;
        try {
            if (data instanceof Timestamp) {
                if (m_colorDataCompare == null || !(m_colorDataCompare instanceof Timestamp))
                    m_colorDataCompare = new Timestamp(System.currentTimeMillis());
                cmp = ((Timestamp) m_colorDataCompare).compareTo((Timestamp) data);
            } else {
                if (m_colorDataCompare == null || !(m_colorDataCompare instanceof BigDecimal))
                    m_colorDataCompare = Env.ZERO;
                if (!(data instanceof BigDecimal))
                    data = new BigDecimal(data.toString());
                cmp = ((BigDecimal) m_colorDataCompare).compareTo((BigDecimal) data);
            }
        } catch (Exception e) {
            return 0;
        }
        if (cmp > 0)
            return -1;
        if (cmp < 0)
            return 1;
        return 0;
    }   //  getColorCode

    /**
     * Shoe Totals
     *
     * @return
     */
    public boolean isShowTotals() {
        return showTotals;
    }

    /**
     * set Shoe Totals
     *
     * @param show
     */
    public void setShowTotals(boolean show) {
        showTotals = show;
    }

    /**
     * Adding a new row with the totals
     */
    public void addTotals() {

        if (getRowCount() == 0 || this.browserRows.getNoViewColumns() == 0)
            return;

        Object[] total = new Object[this.browserRows.getNoViewColumns()];

        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < this.browserRows.getNoViewColumns(); col++) {
                Object data = getModel().getValueAt(row, col);
                //Class<?> c = layout[col].getColClass();
                int ReferenceType = this.browserRows.getBrowserField(this.browserRows.getTableIndex(col)).getAD_Reference_ID();
                //if (c == BigDecimal.class)
                if (DisplayType.isNumeric(ReferenceType)) {
                    BigDecimal subtotal = Env.ZERO;
                    if (total[col] != null) {
                        if (total[col] instanceof BigDecimal)
                            subtotal = (BigDecimal) (total[col]);
                        if (total[col] instanceof Integer)
                            subtotal = new BigDecimal((Integer)total[col]);

                    }

                    BigDecimal amt = Env.ZERO;
                    if (data == null)
                        amt = Env.ZERO;
                    if (data instanceof BigDecimal)
                        amt = (BigDecimal) data;
                    if (data instanceof Integer)
                        amt = new BigDecimal((Integer) data);
                    if (subtotal == null)
                        subtotal = Env.ZERO;

                    total[col] = subtotal.add(amt);
                }
            }
        }

        //adding total row
        int row = getRowCount() + 1;
        boolean markerSet = false;
        setRowCount(row);
        for (int col = 0; col < this.browserRows.getNoViewColumns(); col++) {
            MBrowseField field = this.browserRows.getBrowserField(this.browserRows.getTableIndex(col));
            GridField gridField = MBrowseField.createGridFieldVO(field, browser.getWindowNo());
            if (DisplayType.isNumeric(field.getAD_Reference_ID())) {
                gridField.setValue(total[col], true);
                setValueAt(row - 1, col, gridField);
            } else {
                if (DisplayType.isText(field.getAD_Reference_ID()) && !markerSet) {
                    gridField.setValue(" Σ ", true);
                    //setValueAt(" Σ ", row - 1, col);
                    setValueAt(row - 1, col, gridField);
                    markerSet = true;
                } else {
                    gridField.setValue(null, true);
                    setValueAt(row - 1, col , gridField);
                }
            }
        }
    }

    /**
     * Get Browse Rows Data
     *
     * @return BrowserRows
     * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 15/10/2013, 10:01:47
     */
    public BrowserRow getData() {
        return browserRows;
    }

    /**
     * Set Value At
     *
     * @param row
     * @param column
     * @param gridField
     */
    public void setValueAt(int row, int column, GridField gridField) {
        if (gridField == null)
            throw new UnsupportedOperationException("No GridField");

        browserRows.setValue(row, browserRows.getTableIndex(column), gridField);

        if (gridField.isDisplayed())
            super.setValueAt(gridField.getValue(), row, column);
    }//setValueAt
    
    //	FR [ 245 ]
    @Override
    public void setValueAt(Object value, int row, int column) {
    	GridField gridField = browserRows.getValue(row, browserRows.getTableIndex(column));
    	gridField.setValue(value, true);
    	//	Set standard method
    	setValueAt(row, column, gridField);
    }//setValueAt

    /**
     * Set Value to any column
     * @param row
     * @param column
     * @param gridField
     */
    private void setValueAnyColumn(int row, int column, GridField gridField) {
    	if (gridField == null)
			throw new UnsupportedOperationException("No GridField");
    	
    	browserRows.setValue(row, column, gridField);
    	//	For display
    	if (gridField.isDisplayed())
            super.setValueAt(gridField.getValue(), row, browserRows.getDisplayIndex(column));
    }
    
    
    /**
     * @param col
     */
    private void setKey(int col) {
        p_keyColumnIndex = col;
        browser.keyColumnIndex = col;
    }//setKey

    /**
     * Stop Sort will write After
     * Sort Table
     *
     * @param modelColumnIndex model column sort index
     */
    @Override
    protected void sort(final int modelColumnIndex) {
        Boolean isCanSort = true;
        for (MBrowseField browseField :getFields()){
            if (!browseField.isReadOnly()){
                isCanSort=false;
                break;
            }
        }
        if (isCanSort)
            super.sort(modelColumnIndex);
    }   //  sort

    /**
     * Get Fields
     * @return
     */
    public List<MBrowseField> getFields() {
    	return browserFields;
    }
    
    @Override
	public int loadTable(ResultSet rs) {
		long start = System.currentTimeMillis();
		//	Row
		int row = 0;
		int no = 0;
		//	Delete Row
		setRowCount(row);
		try {
			log.fine("Start load - "
					+ (System.currentTimeMillis() - start) + "ms");
			while (rs.next()) {
				no++;
				setRowCount(row + 1);
				int colOffset = 1; // columns start with 1
				int column = 0;
				for (MBrowseField field : getFields()) {
					Object value = null;
					if (field.isKey()
							&& DisplayType.isID(field.getAD_Reference_ID())
							&& !field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\""))
                        value = new IDColumn(rs.getInt(column + colOffset));
					else if (field.isKey()
							&&  (DisplayType.isNumeric(field.getAD_Reference_ID())
									&& field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\"")
									|| (!DisplayType.isID(field.getAD_Reference_ID()) 
											&& DisplayType.Integer != field.getAD_Reference_ID())))
						value  = new IDColumn(no);
					else if (DisplayType.isID(field.getAD_Reference_ID())
							|| field.getAD_Reference_ID() == DisplayType.Integer) {
						Integer id = rs.getInt(column + colOffset);
						value = id != 0 ? id : null;
					}
					else if (DisplayType.isNumeric(field.getAD_Reference_ID()))
						value = rs.getBigDecimal(column + colOffset);
					else if (DisplayType.isDate(field.getAD_Reference_ID()))
						value = rs.getTimestamp(column + colOffset);
					else if (DisplayType.YesNo == field.getAD_Reference_ID()){
						value = rs.getString(column + colOffset);
						if (value != null)
							value= value.equals("Y");
					}
					else
						value = rs.getObject(column + colOffset);

					GridField gridField = MBrowseField.createGridFieldVO(field , browser.getWindowNo());
					gridField.setValue(value, true);
					//	Set Value
					setValueAnyColumn(row, column, gridField);
					column ++;
				}
				//	Increment Row
				row++;
			}
			
			
			log.fine("End load - " + (System.currentTimeMillis() - start)
					+ "ms");
		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
		}
		//no = detail.getRowCount();
		log.fine("#" + no + " - " + (System.currentTimeMillis() - start)
				+ "ms");
		if (isShowTotals())
			addTotals();
		//	
		autoSize();
		//
		return no;
	}

	@Override
	public String processCallOut(GridField field, Object value,
			Object oldValue, int currentRow, int currentColumn) {
		return browserRows.processCallOut(ctx, browser.getWindowNo(), 
				field, value, oldValue, 
				currentRow, currentColumn);
	}

	@Override
	public GridField getGridFieldAt(int row, int column) {
		return browserRows.getValue(row, browserRows.getTableIndex(column));
	}
}   //  BrowseTable