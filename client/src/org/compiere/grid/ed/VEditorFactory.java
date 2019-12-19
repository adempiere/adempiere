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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.text.JTextComponent;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAccountLookup;
import org.compiere.model.MImage;
import org.compiere.model.MLocationLookup;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MPAttributeLookup;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;

/**
 *  Factory for VEditor and its Label for single Row display and multi row-editor
 *
 *  @see VCellRenderer for multi-row display
 *  @author  Jorg Janked
 *  @version $Id: VEditorFactory.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 *  
 *  @author Michael McKay, 
 * 		<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 
 * @version 3.9.4
 */
public class VEditorFactory
{
	/**
	 *  Create Editor for MField.
	 *  The Name is set to the column name for dynamic display management
	 *  @param mField MField
	 *  @param tableEditor true if table editor
	 *  @return grid editor
	 */
	public static VEditor getEditor (GridField mField, boolean tableEditor)
	{
		return getEditor (null, mField, tableEditor);
	}   //  getEditor

	/**
	 *  Create Editor for MField.
	 *  The Name is set to the column name for dynamic display management
	 *  @param mTab MTab
	 *  @param mField MField
	 *  @param tableEditor true if table editor
	 *  @return grid editor
	 */
	public static VEditor getEditor (GridTab mTab, GridField mField, boolean tableEditor)
	{
		if (mField == null)
			return null;

		VEditor editor = null;
		int displayType = mField.getDisplayType();
		String columnName = mField.getColumnName();
		boolean mandatory = mField.isMandatory(false);      //  no context check
		boolean readOnly = mField.isReadOnly();
		boolean updateable = mField.isUpdateable();
		int WindowNo = mField.getWindowNo();

		//  Not a Field
		if (mField.isHeading())
			return null;

		//	String (clear/password)
		if (displayType == DisplayType.String
			|| displayType == DisplayType.PrinterName
			|| (tableEditor && (displayType == DisplayType.Text || displayType == DisplayType.TextLong)) )
		{
			if (mField.isEncryptedField())
			{
				VPassword vs = new VPassword (columnName, mandatory, readOnly, updateable,
					mField.getDisplayLength(), mField.getFieldLength(), mField.getVFormat(), tableEditor);
				vs.setName (columnName);
				vs.setField (mField);
				editor = vs;
			}
			else
			{
				VString vs = new VString (columnName, mandatory, readOnly, updateable,
					mField.getDisplayLength(), mField.getFieldLength(), 
					mField.getVFormat(), mField.getObscureType(), tableEditor);
				vs.setName (columnName);
				vs.setField (mField);
				if (mField.isAutocomplete()) {
					ADempiereAutoCompleteDecorator.decorate((JTextComponent) vs.getEditorComponent(), mField.getEntries(), false);
				}
				editor = vs;
			}
		}
		//	URL
		else if (displayType == DisplayType.URL)
		{
			VURL vs = new VURL (columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength(), tableEditor);
			vs.setName (columnName);
			vs.setField (mField);
			editor = vs;
		}
		
		/** Printer Name
		else if (displayType == DisplayType.PrinterName)
		{
			VPrinterName vs = new VPrinterName (columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength(), mField.getVFormat());
			vs.setName (columnName);
			vs.setField (mField);
			editor = vs;
		}	**/
		
		//	File Path / Name
		else if (displayType == DisplayType.FilePath || displayType == DisplayType.FileName || displayType == DisplayType.FilePathOrName)
		{
			VFile file = new VFile (columnName, mandatory, readOnly, updateable,
				mField.getFieldLength(), displayType == DisplayType.FilePathOrName, displayType == DisplayType.FileName,
				tableEditor);
			file.setName(columnName);
			file.setField(mField);
			editor = file;
		}

		//	Lookup
		else if (DisplayType.isLookup(displayType) || displayType == DisplayType.ID)
		{
			VLookup vl = new VLookup(columnName, mandatory, readOnly, updateable,
				mField.getLookup(), tableEditor);
			vl.setName(columnName);
			vl.setField (mField);
			editor = vl;
		}

		//	Number
		else if (DisplayType.isNumeric(displayType))
		{
			VNumber vn = new VNumber(columnName, mandatory, readOnly, updateable,
				displayType, mField.getHeader(), tableEditor);
			vn.setRange(mField.getValueMin(), mField.getValueMax());
			vn.setName(columnName);
			vn.setField (mField);
			editor = vn;
		}

		//	YesNo
		else if (displayType == DisplayType.YesNo)
		{
			VCheckBox vc = new VCheckBox(columnName, mandatory, readOnly, updateable,
				mField.getHeader(), mField.getDescription(), tableEditor);
			vc.setName(columnName);
			vc.setField (mField);
			editor = vc;
		}

		//	Text (single row)
		else if (displayType == DisplayType.Text)
		{
			VText vt = new VText(columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength(), tableEditor);
			vt.setName(columnName);
			vt.setField (mField);
			editor = vt;
		}

		//	Memo (single row)
		else if (displayType == DisplayType.Memo)
		{
			VMemo vt = new VMemo(columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength(), tableEditor);
			vt.setName(columnName);
			vt.setField (mField);
			editor = vt;
		}

		//	Date
		else if (DisplayType.isDate(displayType))
		{
			if (displayType == DisplayType.DateTime)
				readOnly = true;
			VDate vd = new VDate(columnName, mandatory, readOnly, updateable,
				displayType, mField.getHeader(), tableEditor);
			vd.setName(columnName);
			vd.setField (mField);
			editor = vd;
		}

		//	Location
		else if (displayType == DisplayType.Location)
		{
			VLocation loc = new VLocation (mTab, columnName, mandatory, readOnly, updateable,
				(MLocationLookup)mField.getLookup(), tableEditor);
			loc.setName(columnName);
			loc.setField (mField);
			editor = loc;
		}

		//	Locator
		else if (displayType == DisplayType.Locator)
		{
			VLocator loc = new VLocator (columnName, mandatory, readOnly, updateable,
				(MLocatorLookup)mField.getLookup(), WindowNo, tableEditor);
			loc.setName(columnName);
			loc.setField (mField);
			editor = loc;
		}

		//	Account
		else if (displayType == DisplayType.Account)
		{
			//hengsin: bug [ 1711795 ] Combination copied itself in Grid mode
			/*
			VAccount acct = new VAccount (columnName, mandatory, readOnly, updateable,
				(MAccountLookup)mField.getLookup(), mField.getHeader());*/
			VAccount acct = new VAccount (columnName, mandatory, readOnly, updateable,
					new MAccountLookup (mField.getVO().ctx, mField.getWindowNo()), mField.getHeader(), 
					tableEditor);
			acct.setName(columnName);
			acct.setField (mField);
			editor = acct;
		}

		//	Button
		else if (displayType == DisplayType.Button)
		{
			VButton button = new VButton(columnName, mandatory, readOnly, updateable,
				mField.getHeader(), mField.getDescription(), mField.getHelp(), mField.getAD_Process_ID(),
				tableEditor);
			button.setName(columnName);
			button.setField (mField);
			if (mField != null && mField.getAD_Image_ID() > 0)
			{
				MImage icon = MImage.get(mField.getVO().ctx , mField.getAD_Image_ID());
				if (icon != null)
					button.setIcon(icon.getIcon());
			}
			editor = button;
		}

		//  Assignment
		else if (displayType == DisplayType.Assignment)
		{
			VAssignment assign = new VAssignment (mandatory, readOnly, updateable, tableEditor);
			assign.setName(columnName);
			assign.setField (mField);
			editor = assign;
		}

		//  Color
		else if (displayType == DisplayType.Color)
		{
			VColor color = new VColor (mTab, mandatory, readOnly, tableEditor);
			color.setName(columnName);
			color.setField (mField);
			editor = color;
		}

		//  Image
		else if (displayType == DisplayType.Image)
		{
			VImage image = new VImage (columnName, WindowNo, tableEditor);
			image.setName(columnName);
			image.setField (mField);
			editor = image;
		}

		//  PAttribute
		else if (displayType == DisplayType.PAttribute)
		{
			VPAttribute attrib = new VPAttribute (mTab, mandatory, readOnly, updateable, WindowNo,
				(MPAttributeLookup)mField.getLookup(), false, tableEditor);
			attrib.setName(columnName);
			attrib.setField (mField);
			editor = attrib;
		}
		
		//  Long Text (CLob)
		else if (displayType == DisplayType.TextLong)
		{
			VTextLong vt = new VTextLong (columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength(), tableEditor);
			vt.setName(columnName);
			vt.setField (mField);
			editor = vt;
		}

		//  Binary (BLob)
		else if (displayType == DisplayType.Binary)
		{
			VBinary bin = new VBinary (columnName, WindowNo, tableEditor);
			bin.setName(columnName);
			bin.setField (mField);
			editor = bin;
		}
		
		// Chart
		else if (displayType == DisplayType.Chart )
		{
			VChart chart = new VChart(mField.getAD_Chart_ID(), WindowNo, tableEditor);
			editor = chart;
		}

		else
			log.log(Level.WARNING, columnName + " - Unknown Type: " + displayType);

		return editor;
	}	//	getEditor

	/**
	 *  Create Label for MField. (null for YesNo/Button)
	 *  The Name is set to the column name for dynamic display management
	 *
	 *  @param mField MField
	 *  @return Label
	 */
	public static CLabel getLabel (GridField mField)
	{
		if (mField == null)
			return null;

		int displayType = mField.getDisplayType();

		//	No Label for FieldOnly, CheckBox, Button
		if (mField.isFieldOnly()
				|| displayType == DisplayType.YesNo
				|| displayType == DisplayType.Button
				|| displayType == DisplayType.Chart)
			return null;
		//
		CLabel label = new CLabel(mField.getHeader(), mField.getDescription());
		label.setName(mField.getColumnName());
	//	label.setFont(AdempierePLAF.getFont_Label());
	//	label.setForeground(AdempierePLAF.getTextColor_Label());
		return label;
	}   //  getLabel
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VEditorFactory.class);
	

	/**
	 * Creates basic editors based on a class for Timestamps, BigDecimals
	 * Integers and Doubles. All other classes will default to a string editor.
	 * @param the base class the editor should represent
	 * @return an appropriate editor.
	 */
	public static VEditor getEditor(Class<?> c, boolean tableCellEditor)
	{
		VEditorAbstract editor;
		
		//  Date
		if (c == Timestamp.class)
			editor = new VDate();
		else if (c == BigDecimal.class)
			editor = new VNumber("Amount", false, false, true, DisplayType.Amount, "Amount");
		else if (c == Double.class)
			editor = new VNumber("Number", false, false, true, DisplayType.Number, "Number");
		else if (c == Integer.class)
			editor = new VNumber("Integer", false, false, true, DisplayType.Integer, "Integer");
		else
			editor = new VString();
		
		editor.setTableCellEditor(tableCellEditor);
		return editor;

	}
	
	/**
	 * Creates basic editors based on a class for Timestamps, BigDecimals
	 * Integers and Doubles. All other classes will default to a string editor.
	 * @param the base class the editor should represent
	 * @return an appropriate editor.
	 */
	public static VEditor getEditor(int displayType, boolean tableCellEditor)
	{
		VEditor editor = null;
		
		boolean mandatory = false;
		boolean readOnly = false;
		boolean updateable = true;
		
		if (displayType == DisplayType.String
				|| displayType == DisplayType.PrinterName
				|| (tableCellEditor && (displayType == DisplayType.Text || displayType == DisplayType.TextLong)) )
		{
			editor = new VString();
		}
		else if (displayType == DisplayType.URL)
		{
			editor = new VURL ();
		}
		else if (displayType == DisplayType.FilePath || displayType == DisplayType.FileName || displayType == DisplayType.FilePathOrName)
		{
			VFile vf = new VFile ("FilePath", mandatory, readOnly, updateable, 
					60, true, false);
			editor = vf;
		}
		//	Lookup - the lookup will have to be defined for this editor to function
		else if (DisplayType.isLookup(displayType) || displayType == DisplayType.ID)
		{
			VLookup vl = new VLookup("Lookup", mandatory, readOnly, updateable,
				null);
			editor = vl;
		}

		//	Number
		else if (DisplayType.isNumeric(displayType))
		{
			if (DisplayType.Amount == displayType)
				editor = new VNumber("Amount", mandatory, readOnly, updateable, displayType, "Amount");
			else if(DisplayType.Number == displayType)
				editor = new VNumber("Number", mandatory, readOnly, updateable, displayType, "Number");
			else if (DisplayType.Integer == displayType)
				editor = new VNumber("Integer", mandatory, readOnly, updateable, displayType, "Integer");
		}

		//	YesNo
		else if (displayType == DisplayType.YesNo)
		{
			VCheckBox vc = new VCheckBox("CheckBox", mandatory, readOnly, updateable,
					"CheckBox", "CheckBox", tableCellEditor);
			editor = vc;
		}
		//	Text (single row)
		else if (displayType == DisplayType.Text)
		{
			VText vt = new VText("Text", mandatory, readOnly, updateable,
				60, 500);
			editor = vt;
		}

		//	Memo (single row)
		else if (displayType == DisplayType.Memo)
		{
			VMemo vt = new VMemo("Memo", mandatory, readOnly, updateable,
				60, 500);
			editor = vt;
		}

		//	Date
		else if (DisplayType.isDate(displayType))
		{
			if (displayType == DisplayType.DateTime)
				readOnly = true;
			VDate vd = new VDate(displayType);
			editor = vd;
		}

		//	Location
		else if (displayType == DisplayType.Location)
		{
			VLocation loc = new VLocation ("Location", mandatory, readOnly, updateable,
				null);
			editor = loc;
		}

		//	Locator
		else if (displayType == DisplayType.Locator)
		{
			VLocator loc = new VLocator ("Locator", mandatory, readOnly, updateable,
				null, 0);
			editor = loc;
		}

		//	Account
		else if (displayType == DisplayType.Account)
		{
			VAccount acct = new VAccount ("Account", mandatory, readOnly, updateable,
					null, "Account");
			editor = acct;
		}

		//	Button
		else if (displayType == DisplayType.Button)
		{
			VButton button = new VButton("Button", mandatory, readOnly, updateable,
				"Button", "Button", "", 0);
			editor = button;
		}

		//  Assignment
		else if (displayType == DisplayType.Assignment)
		{
			VAssignment assign = new VAssignment (mandatory, readOnly, updateable);
			editor = assign;
		}

		//  Color
		else if (displayType == DisplayType.Color)
		{
			VColor color = new VColor (null, mandatory, readOnly);
			editor = color;
		}

		//  Image
		else if (displayType == DisplayType.Image)
		{
			VImage image = new VImage ("Image", 0);
			editor = image;
		}

		//  PAttribute
		else if (displayType == DisplayType.PAttribute)
		{
			VPAttribute attrib = new VPAttribute (null, mandatory, readOnly, updateable, 
					0, null, false);
			editor = attrib;
		}
		
		//  Long Text (CLob)
		else if (displayType == DisplayType.TextLong)
		{
			VTextLong vt = new VTextLong ("LongText", mandatory, readOnly, updateable,
				60, 500);
			editor = vt;
		}

		//  Binary (BLob)
		else if (displayType == DisplayType.Binary)
		{
			VBinary bin = new VBinary ("Binary", 0);
			editor = bin;
		}
		
		// Chart
		else if (displayType == DisplayType.Chart )
		{
			VChart chart = new VChart();
			editor = chart;
		}
		else
			log.log(Level.WARNING, "Unknown Type: " + displayType);

		if (editor != null && editor instanceof VEditorAbstract)
			((VEditorAbstract) editor).setTableCellEditor(tableCellEditor);

				
		return editor;

	}

	public static VEditor getEditor(Class<?> editorClass, int displayType, boolean tableCellEditor) {

		if (displayType > 0)
			return VEditorFactory.getEditor(displayType, tableCellEditor);
		else if (editorClass != null)
			return VEditorFactory.getEditor(editorClass, tableCellEditor);
		else
			return null;
			
	}
	
	
}   //  VEditorFactory
