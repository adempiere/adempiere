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

package org.compiere.grid.ed;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;

import org.adempiere.plaf.AdempiereEditorAbstractUI;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.grid.VTable;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.compiere.model.Lookup;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;

import sun.swing.DefaultLookup;

/**
 *  Table Cell Renderer based on DisplayType
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VCellRenderer.java,v 1.4 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Teo Sarca
 * 		<li>FR [ 2866571 ] VCellRenderer: implement getters
 * 			https://sourceforge.net/tracker/?func=detail&aid=2866571&group_id=176962&atid=879335</li>
 * 		<li>FR [ 3051618 ] VCellRenderer: preferred width from field.</li>
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 
 * @version 3.9.4
 */
public final class VCellRenderer extends DefaultTableCellRenderer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3135422746697244864L;
	private GridField gridField;
	private VEditor editor = null;

    /** The look and feel delegate for the editor component. */
    protected transient ComponentUI ui;

	/**
	 *	Constructor for Grid
	 *  @param mField field model
	 */
	public VCellRenderer(GridField mField)
	{
		
		super();
		
		gridField =  mField;
		// new GridField(mField.getVO());  // Make a copy so the lookup changes don't cause events
		// gridField.setGridTab(mField.getGridTab()); // Use the tab reference for context
		columnName = mField.getColumnName();
		this.setName(columnName);
		lookup = gridField.getLookup();
		isPassword = gridField.isEncryptedField();
		
		editor  = VEditorFactory.getEditor(gridField, true);
		
		setFormats(gridField.getDisplayType());

	}	//	VCellRenderer

	/**
	 *  Constructor for MiniGrid
	 *  @param displayType Display Type
	 */
	public VCellRenderer (int displayType)
	{
		super();
				
		editor = VEditorFactory.getEditor(displayType, true);
				
		setFormats(displayType);

	}   //  VCellRenderer

	private void setFormats(int displayType) {

		m_displayType = displayType;
		
		//	Number
		if (DisplayType.isNumeric(m_displayType))
		{
			numberFormat = DisplayType.getNumberFormat(m_displayType);
			setHorizontalAlignment(JLabel.RIGHT);
		}
		//	Date
		else if (DisplayType.isDate(m_displayType))
			dateFormat = DisplayType.getDateFormat(m_displayType);
		//
		
	}

	private int 				m_displayType;
	private String				columnName = null;
	private Lookup              lookup = null;
	private boolean             isPassword = false;
	//
	private SimpleDateFormat 	dateFormat = null;
	private DecimalFormat		numberFormat = null;
//	private VCheckBox           checkBox = null;
	private boolean editable;
	private boolean displayed;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VCellRenderer.class);
	
	/**
	 *	Get TableCell RendererComponent.
	 *  @param table table
	 *  @param value value
	 *  @param isSelected selected
	 *  @param hasFocus focus
	 *  @param row row
	 *  @param col col
	 *  @return component
	 */
	public Component getTableCellRendererComponent (JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int column)
	{

//		log.fine("isSelected " + isSelected+ " row/col " + row + "/" + column + " value=" + value);
//		currentRow = row; currentColumn = column;
		
        if (table == null) {
            return this; // JLabel
        }

        // In case the renderer is invoked while the editor for this cell is still active
        // Any changes to the cell will be lost
        if (table.isEditing() && table.getSelectedColumn() == column && table.getSelectedRow() == row)
        	table.removeEditor();
        
        //  Choose the component based on the field
        //  and whether it is editable or not.  Use 
        //  a JComponent so we can set borders and 
        //  foreground/background colors
        
		JComponent c = createEditor(table, row, column); 
		
		if (ui instanceof AdempiereEditorAbstractUI)
		{
			((AdempiereEditorAbstractUI) ui).setHasFocus(hasFocus);
		}
				
		setEditable(table, row, column);

        if (displayed)
        	setValue(value);
        else
        	setValue(null);

        setColorsAndFont(c, table, gridField, value, row, column, isSelected, hasFocus, editable, ui);
        
        setBorders(c, table, row, column, isSelected, hasFocus, editable, ui);
        
		return c;
				
	}	//	getTableCellRendererComponent


	private void setEditable(JTable table, int row, int column) {
		
		// Can't use the context to test if a cell is displayed or editable as the context is only set
		// for the selected row.  Context doesn't work for tables.  Need to access the actual table data.
		// This limits the info to the current window and tab. This isn't a problem though. Still, can't use
		// gridField.isEditable(true) or gridField.isDisplayed(true) without creating some unpredictable
		// behaviour. The method gridField.isDisplayed(table,row) was added to address this.
		GridTable gridTable = null;
		if (table instanceof CTable)
		{
			if (((CTable) table).getModel() instanceof GridTable)
				gridTable = (GridTable) ((CTable) table).getModel();
		}

		displayed = gridField == null || (gridTable != null && gridField.isDisplayed(gridTable, row));
		
		editable = displayed && (gridField == null 
								|| gridField.isEditable(gridTable, row)) 
								&& table.isCellEditable(row, column);			
	}

	public static void setBorders(JComponent c, JTable table, int row, int column, boolean isSelected, boolean hasFocus, boolean editable, ComponentUI ui) {
	
		if (table instanceof VTable)
			setBordersVTable(c, isSelected, hasFocus, editable, ui);
		if (table instanceof MiniTable)
			setBordersMiniTable(c, table, row, column, isSelected, hasFocus);
	}
	
	private static void setBordersMiniTable(JComponent c, JTable table, int row, int column, boolean isSelected, boolean hasFocus)
	{
		MiniTable mt = (MiniTable) table;
		
		// Row is selected
		Color selectedColor = AdempierePLAF.getFieldBackground_Selected();
		// Even row
		Color normalColor = AdempierePLAF.getFieldBackground_Normal();
		// Odd row
		Color backColor = AdempierePLAF.getInfoBackground();
		// Lead row border
		Color borderColor = AdempierePLAF.getFieldBackground_Mandatory();

		CompoundBorder cb = null;

		ListSelectionModel rsm = mt.getSelectionModel();
		if (!(row == rsm.getLeadSelectionIndex())) 
		{
			if (rsm.isSelectedIndex(row)) // Highlighted but not the lead
			{
				c.setBackground(selectedColor);
				c.setBorder(new MatteBorder(1, 1, 1, 1, selectedColor));
			} 
			else if (row % 2 == 0) // Not selected but even in number
			{
				c.setBackground(normalColor);
				c.setBorder(new MatteBorder(1, 1, 1, 1, normalColor));
			} else // Not selected and odd in number
			{
				// If not shaded, match the table's background
				c.setBackground(backColor);
				c.setBorder(new MatteBorder(1, 1, 1, 1, backColor));
			}

			// Buttons and checkboxes need to have the border turned off
			if (c.getClass().equals(JCheckBox.class)) 
			{
				((JCheckBox) c).setBorderPainted(false);
				((JCheckBox) c).setOpaque(true);
			} else if (c.getClass().equals(JButton.class)) {
				((JButton) c).setBorderPainted(false);
				((JButton) c).setOpaque(true);
			}

		} else {
			if (c.getClass().equals(JCheckBox.class)) 
			{
				((JCheckBox) c).setBorderPainted(true);
				((JCheckBox) c).setOpaque(true);
			} else if (c.getClass().equals(JButton.class)) {
				((JButton) c).setBorderPainted(true);
				((JButton) c).setOpaque(true);
			}

			// Define border - compound border maintains the spacing of 1px around the field
			if (column == 0) {
				cb = new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 1)),
						new MatteBorder(1, 1, 1, 0, borderColor));
			} else if (column == mt.getColumnCount() - 1) {
				cb = new CompoundBorder(new EmptyBorder(new Insets(0, 1, 0, 0)),
						new MatteBorder(1, 0, 1, 1, borderColor));
			} else {
				cb = new CompoundBorder(new EmptyBorder(new Insets(0, 1, 0, 1)),
						new MatteBorder(1, 0, 1, 0, borderColor));
			}
			// Set border
			c.setBorder(cb);
			c.setBackground(selectedColor);				
		}
	}
	
	private static void setBordersVTable(JComponent c, boolean isSelected, boolean hasFocus, boolean editable, ComponentUI ui)
	{
        if (!hasFocus) {
    		// Borders
    		if (editable)
    		{
				setBorder(c, BorderFactory.createEmptyBorder(0,0,0,0));
    		}
    		else {  // Not editable
    			setBorder(c, noFocusBorder);
    		}
        }
    	else {  // Has the focus
     		
            Border border = null;
            if (isSelected) {
                border = UIManager.getBorder("Table.focusSelectedCellHighlightBorder");
            }
            if (border == null) {	
                border = DefaultLookup.getBorder(c, ui, "Table.focusCellHighlightBorder");
            }
            setBorder(c, border);
        } 
	}

	private static void setBorder(JComponent c, Border border) {
		
		if (c instanceof VEditorAbstract && ((VEditorAbstract) c).getUI() instanceof AdempiereEditorAbstractUI)
		{
			AdempiereEditorAbstractUI ui = (AdempiereEditorAbstractUI) ((VEditorAbstract) c).getUI();
			ui.setBorder(border);
		}
		else
		{
			c.setBorder(border);
		}
	}

	private JComponent createEditor(JTable table, int row, int column) {
		
		JComponent c = null;
		
		setEditable(table, row, column);
		
		if (editable && editor != null)
		{
			//  Use the model field to create an editor so the cell 
			//  looks the same as when it is selected and edited including
			//  the helper function buttons.  Otherwise, the buttons appear
			//  and disappear as the cell is switched between the editor
			//  and the renderer which is confusing.  Don't attach any 
			//  listeners so value changes and focus will be ignored
			
			if (editor instanceof VEditorAbstract)
			{
				ui = ((VEditorAbstract) editor).getUI();			
			}

			c = (JComponent) editor;
			
		}
		else
		{
			//  If not an editable field, create the renderer as either 
			//  a simple checkbox, a normal field, or this (a text label).
			if ((displayed && (m_displayType == DisplayType.YesNo ||
					m_displayType == DisplayType.ID)) && editor != null)
			{
				c = (JComponent) editor;
				if (editor instanceof VEditorAbstract)
					ui = ((VEditorAbstract) editor).getUI();
				else if (editor instanceof VCheckBox)
					ui = ((VCheckBox) editor).getUI();
			}
			else
			{	//	returns JLabel
				c = this;
				ui = this.getUI();
			}
		}
		
		// Update the checkbox look and feel
		if (c instanceof JCheckBox)
		{
			((JCheckBox) c).setMargin(new Insets(0,0,0,0));
			((JCheckBox) c).setHorizontalAlignment(JLabel.CENTER);
			((JCheckBox) c).setOpaque(true);
			((JCheckBox) c).setBorderPainted(true);
		}
		
		return c;
	}

	public static void setColorsAndFont(JComponent c, JTable table, GridField gridField, Object value, int row, int column, boolean isSelected, boolean hasFocus, boolean editable, ComponentUI ui) {
		if (table instanceof VTable)
			setColorsAndFontVTable(c, table, gridField, value, row, column, isSelected, hasFocus, editable, ui);
		if (table instanceof MiniTable)
			setColorsAndFontMiniTable(c, table, value, row, column, isSelected, hasFocus);
	}

	private static void setColorsAndFontMiniTable(JComponent c, JTable table, Object value, int row, int column, boolean isSelected, boolean hasFocus) 
	{
		// All handled in the setBordersMiniTable method
	}
	
	private static void setColorsAndFontVTable(JComponent c, JTable table, GridField gridField, Object value, int row, int column, boolean isSelected, boolean hasFocus, boolean editable, ComponentUI ui) 
	{

        Color fg = null;
        Color bg = null;
        
        // Color Code needs to override all other foreground colors
		int cCode = 0;
		//  Grid
		if (table instanceof org.compiere.grid.VTable)
			cCode = ((org.compiere.grid.VTable)table).getColorCode (row);
		//  MiniGrid
		else if (table instanceof org.compiere.minigrid.MiniTable)
			cCode = ((org.compiere.minigrid.MiniTable)table).getColorCode (row);
		//
		if (cCode == 0)
			;											//	Black
		else if (cCode < 0)
			fg = AdempierePLAF.getTextColor_Issue();		//	Red
		else
			fg = AdempierePLAF.getTextColor_OK();		//	Blue
		
		//  Drop location in a table is interesting but not used
		//  The code here is copied from the default behaviour in case
		//  drop locations are defined
        JTable.DropLocation dropLocation = table.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsertRow()
                && !dropLocation.isInsertColumn()
                && dropLocation.getRow() == row
                && dropLocation.getColumn() == column) {

            fg = fg == null ? DefaultLookup.getColor(table, ui, "Table.dropCellForeground"): fg;
            bg = DefaultLookup.getColor(table, ui, "Table.dropCellBackground");

            isSelected = true;
        }

        // The colors of the table cells are defined in the PLAF
        // There are eight possibilities for the three states: selected; focused; editable
        // Then there are the colors for mandatory and error backgrounds for editable fields.
        
        // Foreground color
        if (isSelected && !editable && !hasFocus) 
        {
            c.setForeground(fg == null ? table.getSelectionForeground()
            								: fg);
        }
        else
        {
        	c.setForeground(fg == null ? AdempierePLAF.getTextColor_Normal() 
        									: fg);
        }
        
    	Color background = null;
        Color alternateRowColor = null;
        

        boolean error = false;
        boolean mandatory = gridField != null ? gridField.isMandatory(true, false) : ((VTable) table).isCellMandatory(row, column);
		if (c instanceof VEditor) // Implies displayed and editable
			{
			boolean manMissing = false;
			boolean noValue = value == null || value.toString().length() == 0;
			if (noValue && mandatory)    //  check context
				manMissing = true;
			error = manMissing || (gridField != null && gridField.isError());
			mandatory = mandatory && !(c instanceof VCheckBox); // Not necessary to flag check boxes as mandatory
		}

		if (c instanceof VButton)	
		{
        	background = AdempierePLAF.getFormBackground();
		}
		else if (!isSelected && !editable && !hasFocus && !error && !mandatory)
        {
        	background = AdempierePLAF.getTableRow_NotSelectedInactiveNormal();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_NotSelectedInactiveNormal();
        }
        else if (!isSelected && !editable && !hasFocus && !error && mandatory)
        {
        	background = AdempierePLAF.getTableRow_NotSelectedInactiveMandatory();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_NotSelectedInactiveMandatory();
        }
        else if (!isSelected && !editable && !hasFocus && error) // Don't care about mandatory
        {
        	background = AdempierePLAF.getTableRow_NotSelectedInactiveError();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_NotSelectedInactiveError();
        }
        else if (!isSelected && editable && !hasFocus && !error && !mandatory)
        {
        	background = AdempierePLAF.getTableRow_NotSelectedActiveNormal();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_NotSelectedActiveNormal();        	
        }
        else if (!isSelected && editable && !hasFocus && !error && mandatory)
        {
        	background = AdempierePLAF.getTableRow_NotSelectedActiveMandatory();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_NotSelectedActiveMandatory();        	
        }
        else if (!isSelected && editable && !hasFocus && error) // Don't care about mandatory
        {
        	background = AdempierePLAF.getTableRow_NotSelectedActiveError();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_NotSelectedActiveError();
        }
        else if (isSelected && !editable && !hasFocus && !error && !mandatory)
        {
        	background = AdempierePLAF.getTableRow_SelectedInactiveNormal();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_SelectedInactiveNormal();
        }
        else if (isSelected && !editable && !hasFocus && !error && mandatory)
        {
        	background = AdempierePLAF.getTableRow_SelectedInactiveMandatory();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_SelectedInactiveMandatory();
        }
        else if (isSelected && !editable && !hasFocus && error)
        {
        	background = AdempierePLAF.getTableRow_SelectedInactiveError();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_SelectedInactiveError();
        }
        else if (isSelected && editable && !hasFocus && !error && !mandatory)
        {
        	background = AdempierePLAF.getTableRow_SelectedActiveNormal();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_SelectedActiveNormal();
        }
        else if (isSelected && editable && !hasFocus && !error && mandatory)
        {
        	background = AdempierePLAF.getTableRow_SelectedActiveMandatory();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_SelectedActiveMandatory();
        }
        else if (isSelected && editable && !hasFocus && error)
        {
        	background = AdempierePLAF.getTableRow_SelectedActiveError();
        	alternateRowColor = AdempierePLAF.getTableRowAlternate_SelectedActiveError();
        }
        else if (!editable && hasFocus && !error && !mandatory)  // hasFocus ==> isSelected
        {
        	background = AdempierePLAF.getTableCell_FocusedInactiveNormal();
        }
        else if (!editable && hasFocus && !error && mandatory)
        {
        	background = AdempierePLAF.getTableCell_FocusedInactiveMandatory();
        }
        else if (!editable && hasFocus && error)
        {
        	background = AdempierePLAF.getTableCell_FocusedInactiveError();
        }
        else if (editable && hasFocus && !error && !mandatory)
        {
        	background = AdempierePLAF.getTableCell_FocusedActiveNormal();
        }
        else if (editable && hasFocus && !error && mandatory)
        {
        	background = AdempierePLAF.getTableCell_FocusedActiveMandatory();
        }
        else if (editable && hasFocus && error)
        {
        	background = AdempierePLAF.getTableCell_FocusedActiveError();
        }
        
        if (alternateRowColor != null && row % 2 != 0) {
            background = alternateRowColor;
        }
        
		c.setBackground(bg == null ? background
			: bg);

		// Font
		c.setFont(table.getFont());

	}

	/**
	 *	Format Display Value.
	 *	Default is JLabel
	 *  @param value (key)value
	 */
	protected void setValue (Object value)
	{
		String displayValue;

//		log.fine("Setting value for column (row/col):" + columnName + " (" + currentRow + "/" + currentColumn + ") to " + value);

		try
		{
			if (value == null)
				if (lookup != null)
					displayValue = lookup.getDisplay(value);
				else
					displayValue = "";
			//	Number
			else if (DisplayType.isNumeric(m_displayType))
				displayValue = numberFormat.format(value);
			//	Date
			else if (DisplayType.isDate(m_displayType))
				displayValue = dateFormat.format(value);
			//	Row ID
			else if (m_displayType == DisplayType.RowID)
				displayValue = "";
			//	Lookup
			else if (lookup != null && (DisplayType.isLookup(m_displayType)
					|| m_displayType == DisplayType.Location
					|| m_displayType == DisplayType.Account
					|| m_displayType == DisplayType.Locator
					|| m_displayType == DisplayType.PAttribute ))
			{
				displayValue = lookup.getDisplay(value);
			}
				//	Button
			else if (m_displayType == DisplayType.Button)
			{
				if ("Record_ID".equals(columnName))
					displayValue = "#" + value + "#";
				else
					displayValue = "";
			}
			//  Password (fixed string)
			else if (isPassword)
				displayValue = "**********";
			// Default - convert to a string
			else
				displayValue = value.toString();
			
			//  If the renderer represents an editable field, don't bother 
			//  setting the value - just update the display. Speed is 
			//  important and its only for display.
			if (editable && editor != null)
			{
				if (editor.getComponent() instanceof JTextComponent)
					((JTextComponent)editor.getComponent()).setText(displayValue);
				else if (editor.getComponent() instanceof VComboBox)
				{
					Component textEditor = ((VComboBox)editor.getComponent()).getEditor().getEditorComponent();
					((JTextComponent) textEditor).setText(displayValue);
				}
				else if (editor.getComponent() instanceof VButton)
					((VButton)editor.getComponent()).setValue(value);
				else if (editor instanceof VCheckBox)
				{
					if (value instanceof Boolean)
						editor.setValue(((Boolean)value).booleanValue());
					else
						editor.setValue("Y".equals(value));
					return;
				}
			}
		}
		catch (Exception e)
		{
			String clazz = value==null ? "" : value.getClass().getName();
			log.log(Level.SEVERE, "(" + value + ") " + clazz , e);
			displayValue = value.toString();
		}
		if (gridField != null)
			gridField.setError(false);
		super.setValue(displayValue);
	}	//	setValue

	/**
	 *  to String
	 *  @return String representation
	 */
	public String toString()
	{
		return "VCellRenderer[" + columnName 
			+ ",DisplayType=" + m_displayType + " - " + lookup + "]";
	}   //  toString

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (lookup != null)
			lookup.dispose();
		lookup = null;
	}	//	dispose

	public String getColumnName()
	{
		return columnName;
	}
	
	public Lookup getLookup()
	{
		return lookup;
	}

	public int getDisplayType()
	{
		return m_displayType;
	}

	public boolean isPassword()
	{
		return isPassword;
	}

	public JComponent getRenderer()
	{
		//  If the editor is defined as the renderer, return it
		//  otherwise, return null
		return (JComponent) editor;
	}

}	//	VCellRenderer
