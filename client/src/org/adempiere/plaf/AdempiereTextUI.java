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

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.plaf.ComponentUI;

import org.compiere.swing.CTextArea;

/**
 *  ADempiere Text UI.
 *  Provides support for a Text editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereTextUI extends AdempiereMemoUI
{
	
	/** Max Display Length - 50 */
	public static final int MAXDISPLAY_LENGTH = 50;
	
	private int displayLength = MAXDISPLAY_LENGTH;  // Defaults to max

	private int fieldLength;

	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereDateUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereTextUI();
	}   //  CreateUI
    
	/**
	 * Set the field length as the total number of characters.
	 * If the field length is more than 300, the TextUI will be 
	 * shown on three rows, otherwise it will occupy 2 rows.
	 * @param fieldLength the number of characters
	 */
	public void setFieldLength(int fieldLength)
	{
		
		this.fieldLength= fieldLength;
		int rows = fieldLength < 300 ? 2 : 3;
		setDisplaySize(rows, displayLength);
		
	}
	
	/**
	 * Sets the display length (width) in terms of columns
	 * @param displayLength the displayLength to set
	 */
	public void setDisplayLength(int displayLength)
	{
		
		this.displayLength = displayLength > MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : displayLength;
		int rows = fieldLength < 300 ? 2 : 3;
		setDisplaySize(rows, this.displayLength);
		
	}
	
}   