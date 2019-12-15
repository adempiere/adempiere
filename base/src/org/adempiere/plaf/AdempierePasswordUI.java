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

package org.adempiere.plaf;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import org.compiere.grid.ed.MDocString;
import org.compiere.grid.ed.VOvrCaret;
import org.compiere.swing.CPassword;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  ADempiere Password UI.
 *  Provides support for a Password editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempierePasswordUI extends AdempiereEditorAbstractUI
{
	
	CLogger log = CLogger.getCLogger(AdempierePasswordUI.class);
	
	/** Max Display Length - 60 */
	public static final int MAXDISPLAY_LENGTH = org.compiere.model.GridField.MAXDISPLAY_LENGTH;
	
	private int displayLength = MAXDISPLAY_LENGTH;  // Defaults to max

	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempierePassowrdUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempierePasswordUI();
	}   //  CreateUI
    
    /**
     * Create the editor using the appropriate text component. 
     * Child classes should override to provide the appropriate editor.
     * @return the Text Component. The default is a CTextField.
     */
    protected JTextComponent createEditorComponent() {
    	
    	CPassword editor = new CPassword(displayLength> MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : displayLength);
		editor.setHorizontalAlignment(JTextField.LEADING);
    	return editor;
    	
    }
    
	/**
	 * 	Set Display Length
	 */
	public void setDisplayLength(int length)
	{
		
		displayLength = length;
		isDisplaySizeDirty = true;
		isMinimumSizeDirty = true;
		((CPassword) getEditorComponent()).setColumns(displayLength> MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : displayLength);
		
	}

	/**
	 * Set the format for the password
	 * @param format
	 * @param fieldLength
	 */
	public void setFormat(String format, int fieldLength) {
		
		if (format == null)
			format = "";
		if (format.length() != 0 || fieldLength != 0)
			getEditorComponent().setDocument(new MDocString(format, fieldLength, getEditorComponent()));

	}

	/**
	 * Set the caret to use when editing the password
	 * @param caret
	 */
	public void setCaret(Caret caret) {
		((CPassword) getEditorComponent()).setCaret(caret);
	}
	
}   