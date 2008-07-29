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

import java.util.logging.Level;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAccountLookup;
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
					mField.getDisplayLength(), mField.getFieldLength(), mField.getVFormat());
				vs.setName (columnName);
				vs.setField (mField);
				editor = vs;
			}
			else
			{
				VString vs = new VString (columnName, mandatory, readOnly, updateable,
					mField.getDisplayLength(), mField.getFieldLength(), 
					mField.getVFormat(), mField.getObscureType());
				vs.setName (columnName);
				vs.setField (mField);
				if (mField.isAutocomplete()) {
					ADempiereAutoCompleteDecorator.decorate(vs, mField.getEntries(), false);
				}
				editor = vs;
			}
		}
		//	URL
		else if (displayType == DisplayType.URL)
		{
			VURL vs = new VURL (columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength());
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
		else if (displayType == DisplayType.FilePath || displayType == DisplayType.FileName)
		{
			VFile file = new VFile (columnName, mandatory, readOnly, updateable,
				mField.getFieldLength(), displayType == DisplayType.FileName);
			file.setName(columnName);
			file.setField(mField);
			editor = file;
		}

		//	Lookup
		else if (DisplayType.isLookup(displayType) || displayType == DisplayType.ID)
		{
			VLookup vl = new VLookup(columnName, mandatory, readOnly, updateable,
				mField.getLookup());
			vl.setName(columnName);
			vl.setField (mField);
			editor = vl;
		}

		//	Number
		else if (DisplayType.isNumeric(displayType))
		{
			VNumber vn = new VNumber(columnName, mandatory, readOnly, updateable,
				displayType, mField.getHeader());
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
				mField.getDisplayLength(), mField.getFieldLength());
			vt.setName(columnName);
			vt.setField (mField);
			editor = vt;
		}

		//	Memo (single row)
		else if (displayType == DisplayType.Memo)
		{
			VMemo vt = new VMemo(columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength());
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
				displayType, mField.getHeader());
			vd.setName(columnName);
			vd.setField (mField);
			editor = vd;
		}

		//	Location
		else if (displayType == DisplayType.Location)
		{
			VLocation loc = new VLocation (mTab, columnName, mandatory, readOnly, updateable,
				(MLocationLookup)mField.getLookup());
			loc.setName(columnName);
			loc.setField (mField);
			editor = loc;
		}

		//	Locator
		else if (displayType == DisplayType.Locator)
		{
			VLocator loc = new VLocator (columnName, mandatory, readOnly, updateable,
				(MLocatorLookup)mField.getLookup(), WindowNo);
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
					new MAccountLookup (mField.getVO().ctx, mField.getWindowNo()), mField.getHeader());
			acct.setName(columnName);
			acct.setField (mField);
			editor = acct;
		}

		//	Button
		else if (displayType == DisplayType.Button)
		{
			VButton button = new VButton(columnName, mandatory, readOnly, updateable,
				mField.getHeader(), mField.getDescription(), mField.getHelp(), mField.getAD_Process_ID());
			button.setName(columnName);
			button.setField (mField);
			editor = button;
		}

		//  Assignment
		else if (displayType == DisplayType.Assignment)
		{
			VAssignment assign = new VAssignment (mandatory, readOnly, updateable);
			assign.setName(columnName);
			assign.setField (mField);
			editor = assign;
		}

		//  Color
		else if (displayType == DisplayType.Color)
		{
			VColor color = new VColor (mTab, mandatory, readOnly);
			color.setName(columnName);
			color.setField (mField);
			editor = color;
		}

		//  Image
		else if (displayType == DisplayType.Image)
		{
			VImage image = new VImage (columnName, WindowNo);
			image.setName(columnName);
			image.setField (mField);
			editor = image;
		}

		//  PAttribute
		else if (displayType == DisplayType.PAttribute)
		{
			VPAttribute attrib = new VPAttribute (mTab, mandatory, readOnly, updateable, WindowNo,
				(MPAttributeLookup)mField.getLookup());
			attrib.setName(columnName);
			attrib.setField (mField);
			editor = attrib;
		}
		
		//  Long Text (CLob)
		else if (displayType == DisplayType.TextLong)
		{
			VTextLong vt = new VTextLong (columnName, mandatory, readOnly, updateable,
				mField.getDisplayLength(), mField.getFieldLength());
			vt.setName(columnName);
			vt.setField (mField);
			editor = vt;
		}

		//  Binary (BLob)
		else if (displayType == DisplayType.Binary)
		{
			VBinary bin = new VBinary (columnName, WindowNo);
			bin.setName(columnName);
			bin.setField (mField);
			editor = bin;
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
				|| displayType == DisplayType.Button)
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
	

}   //  VEditorFactory
