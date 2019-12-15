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

import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  ADempiere Lookup UI.
 *  Provides support for a lookup editor that can use a text field or combo box.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereLookupUI extends AdempiereEditorAbstractUI
{
	
	static CLogger log = CLogger.getCLogger(AdempiereLookupUI.class);
	
	/** A class holder for the VComboBox class */
	private static Class<?> VComboBox = null;
	
	/** The lookup comboBox */
	private CComboBox comboBox;

	
	/** The buttonIcon to use. Defaults to the PickOpen symbol. */
	private ImageIcon buttonIcon = Env.getImageIcon("PickOpen10.gif");
	
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereLookupUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereLookupUI();
	}   //  CreateUI
	

	static {
		
		if (VComboBox == null)
		{
			try {	
				VComboBox = Class.forName("org.compiere.grid.ed.VComboBox");			
			} catch (ClassNotFoundException 
					| IllegalArgumentException 
					| SecurityException e) {
				log.severe(e.getMessage());
			}
		}		
	}
	
	
	@Override
    protected CButton createButton() {
		
    	return new CButton();
    	
    }

	
    @Override
    protected ImageIcon getButtonIcon() {
    	return buttonIcon;
    }


	public void setEditorType(String columnName) {
		
		if (columnName.equals("C_BPartner_ID"))
			buttonIcon = Env.getImageIcon("BPartner10.gif");
		else if (columnName.equals("M_Product_ID"))
			buttonIcon = Env.getImageIcon("Product10.gif");
		
		if (buttonComponent != null)
			buttonComponent.setIcon(buttonIcon);
		
		// Defaults to the PickOpen image.
		
	}
    
	@Override
    public void installUI(JComponent c) {
			
		super.installUI(c);

		comboBox = createComboBoxComponent();
				
    }

	/**
	 * Create the combo box component of the lookup editor
	 * @return the comboBox
	 */
	private CComboBox createComboBoxComponent() {
		
		try {
			
			return (CComboBox) VComboBox.getConstructor().newInstance();
			
		} catch (InstantiationException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException 
				| NoSuchMethodException 
				| SecurityException e) {
			log.severe(e.getMessage());
			return null;
		}
		
	}
	
	public CComboBox getComboBox() {
		return comboBox;
	}

}   //  AdempiereLookupUI
