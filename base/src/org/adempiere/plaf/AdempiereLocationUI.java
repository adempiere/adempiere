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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import org.compiere.swing.CButton;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  ADempiere Location UI.
 *  Provides support for a Location editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereLocationUI extends AdempiereEditorAbstractUI
{
	
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereDateUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereLocationUI();
	}   //  CreateUI

	/** A button to open a map */
	private CButton openMapButton;
	
	/** A button to open the location helper dialog */
	private CButton openDialogButton;

    @Override
    protected ImageIcon getButtonIcon() {
    	return Env.getImageIcon("Open16.gif");
    }
    
    @Override
    public void installUI(JComponent c) {
		
    	//  Create a JPanel to hold the two buttons
    	JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		//  Configure the Call Button
		openMapButton = new CButton();
		openMapButton.setIcon(Env.getImageIcon("Online10.gif"));
		buttonPanel.add(openMapButton);

		//  Configure the Edit button
		openDialogButton = new CButton();
		openDialogButton.setIcon(Env.getImageIcon("Location10.gif"));
		buttonPanel.add(openDialogButton);

		//  Create the standard editor with a text field and no button
    	super.installUI(c);
    	
    	//  Add the buttons to the east panel
		super.getEditorPanel().add(buttonPanel, BorderLayout.EAST);
		
    }
    
	/**
	 * @return the openButton
	 */
	public CButton getOpenMapButton() {
		return openMapButton;
	}

	/**
	 * @return the saveButton
	 */
	public CButton getOpenDialogButton() {
		return openDialogButton;
	}

	@Override
	public void setBorders() {
		
		if (!isTableCellEditor)
		{
			if ( !openMapButton.isVisible() && !openDialogButton.isVisible())
			{
				editorComponent.setBorder(UIManager.getBorder("TextField.border"));
			}
			else
			{
				//  The editor component border needs to extend passed its size to be
				//  overwritten by the button border. In this way, the button and
				//  text editor will appear to share a border. Do this by creating
				//  a compound border that will have four layers, the other empty
				//  border extending past the right side, the standard compound
				//  border and an inner empty border that will separate the text
				//  from the icon by 5 pixels.
				editorComponent.setBorder(BorderFactory.createCompoundBorder(
												BorderFactory.createEmptyBorder(0, 0, 0, -2),
												UIManager.getBorder("TextField.border")));
				
				//  The map button right border should be removed
				openMapButton.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(0, 0, 0, -2),
						UIManager.getBorder("AbstractEditor.ButtonBorder")));
				
				//  The dialog button gets a full border.
				openDialogButton.setBorder(UIManager.getBorder("AbstractEditor.ButtonBorder"));
			}
		}
		else
		{
			
			//  In a table cell, all we want is a border between the buttons and the text.
			//  The cell renderer/editor will apply a border around the JPanel
			
			//  Remove the TextField.border around the editor
			editorComponent.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			
			//  The outer border removes the edges of the ButtonBorder, only leaving the
			//  left edge
			openMapButton.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(-2, 0, -2, -2),
					BorderFactory.createCompoundBorder(
							UIManager.getBorder("AbstractEditor.ButtonBorder"),
							BorderFactory.createEmptyBorder(2, 0, 2, 0))));
			openMapButton.setMargin(new Insets(0, 2, 0, 2));
			
			//  The outer border removes the edges of the ButtonBorder, again only 
			//  leaving the left edge
			openDialogButton.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(-2, 0, -2, -2),
					BorderFactory.createCompoundBorder(
							UIManager.getBorder("AbstractEditor.ButtonBorder"),
							BorderFactory.createEmptyBorder(2, 0, 2, 0))));
			openDialogButton.setMargin(new Insets(0, 2, 0, 2));
			
		}
	}

    /**
     * The minimum size is the size of the display area plus insets plus the button.
     */
    @Override
    public Dimension getMinimumSize( JComponent c ) {
    	
        if ( !isMinimumSizeDirty  ) {
            return new Dimension(cachedMinimumSize);
        }
        
        Dimension size = getDisplaySize();
        
        int buttonWidth = getEditableButtonWidth();
        
        if (openDialogButton != null && openDialogButton.isVisible())
        {
            size.width +=  buttonWidth;
        }
        
        if (openMapButton != null && openMapButton.isVisible())
        {
            size.width +=  buttonWidth;
        }

        cachedMinimumSize.setSize( size.width, size.height );
        isMinimumSizeDirty = false;

        return new Dimension(size);
    }

}   //  AdempiereLocationUI
