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

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;

import org.compiere.swing.CButton;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  ADempiere PAttribute UI.
 *  Provides support for the PAttribute editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempierePAttributeUI extends AdempiereEditorAbstractUI
{
	
	CLogger log = CLogger.getCLogger(AdempierePAttributeUI.class);
	
	/**	Number of Columns (30)		*/
	public final static int SIZE = 30;

	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempierePAttributeUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempierePAttributeUI();
	}   //  CreateUI

    @Override
    protected ImageIcon getButtonIcon() {
    	return Env.getImageIcon("PAttribute10.gif");
    }
    
    @Override
    protected String getPhantomString() {

    	return "123456789012345678901234567890";
    	
    }
    
    /**
     * Create the editor using the appropriate text component. 
     * Child classes should override to provide the appropriate editor.
     * @return the Text Component. The default is a CTextField.
     */
    @Override
    protected JTextComponent createEditorComponent() {
    	
    	CTextField editor = new CTextField(SIZE);
		editor.setHorizontalAlignment(JTextField.LEADING);
		editor.setEditable(false);
		editor.setFocusable(false);

    	return editor;
    	
    }
    
	@Override
    protected CButton createButton() {
		
		CButton button = new CButton();
		button.setFocusable(true);
		return button;

    }
	
}   //  AdempierePAttributeUI
