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
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;

import org.compiere.swing.CButton;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  ADempiere Binary UI.
 *  Provides support for a binary editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereBinaryUI extends AdempiereEditorAbstractUI
{
	
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereBinaryUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereBinaryUI();
	}   //  CreateUI

	private CButton openButton;
	private CButton saveButton;
	
	@Override
    public void installUI(JComponent c) {
		
    	super.installUI(c);
    	
    	//  Convert the editorPanel to a GridLayout to hold the 
    	//  buttons.
    	editorPanel.setLayout(new GridLayout(1, 2));;		
		editorPanel.setPreferredSize(new Dimension(500, 25));

		// Configure the Call Button
		openButton = new CButton();
		openButton.setIcon(Env.getImageIcon("Open16.gif"));
		openButton.setToolTipText(Msg.translate(Env.getCtx(), "Upload"));
		editorPanel.add(openButton);

		// Configure the Edit button
		saveButton = new CButton();
		saveButton.setIcon(Env.getImageIcon("Save16.gif"));
		saveButton.setToolTipText(Msg.translate(Env.getCtx(), "Download"));
		editorPanel.add(saveButton);

    }

    @Override
    protected JTextComponent createEditorComponent() {
    	
    	// No editor - return null
    	return null;
    	
    }

	/**
	 * @return the openButton
	 */
	public CButton getOpenButton() {
		return openButton;
	}

	/**
	 * @return the saveButton
	 */
	public CButton getSaveButton() {
		return saveButton;
	}

}   //  AdempiereBinaryUI
