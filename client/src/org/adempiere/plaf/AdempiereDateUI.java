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
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;

import org.compiere.swing.CButton;
import org.compiere.swing.CTextField;
import org.compiere.swing.CTextPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  ADempiere Date UI.
 *  Provides support for a date editor that uses a styled document in a text pane.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereDateUI extends AdempiereEditorAbstractUI
{
	
	CLogger log = CLogger.getCLogger(AdempiereDateUI.class);
	
	Class<?> VDate;
	private SimpleDateFormat format = null;	
	
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereDateUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereDateUI();
	}   //  CreateUI
	
	@Override
    protected JTextComponent createEditorComponent() {
    	
    	return new CTextPane();
    	
    }

	@Override
    protected CButton createButton() {
		
    	return new CButton();
    	
    }


	public void setFormat(SimpleDateFormat format) {
		this.format = format;
		isDisplaySizeDirty = true;
		isMinimumSizeDirty = true;
	}

    @Override
    protected ImageIcon getButtonIcon() {
    	return Env.getImageIcon("Calendar10.gif");
    }
    
    @Override
    protected String getPhantomString() {

    	//  The PHANTOM size is determined by the date format string length
        //  If one is defined, use it. Otherwise, stick with the default
        if (format == null)
        {
			// Load the date format.  It will be used to set the PHANTOM string length
			Method method;
			try {
				method = VDate.getMethod("getFormat",(Class<?>[]) null);
				format = (SimpleDateFormat) method.invoke(container, (Object[]) null);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				log.severe(e.getLocalizedMessage());
			}
		
		    //  The display size will be the same as a text field
		    //  If the format is set, use that as the prototype text
		    //  otherwise, use the default defined in the PHANTOM
		    //  instantiation as "MM/dd/yyyy"
		    if (format != null)
		    {
		    	return format.toLocalizedPattern();
		    }
        }
        
        // Nothing set in the editor, so use the default.
        if (format == null)
        	format = new SimpleDateFormat();
        
        return format.toLocalizedPattern();

    }
}   //  AdempiereDateUI
