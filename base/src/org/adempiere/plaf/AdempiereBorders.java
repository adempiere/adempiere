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

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicBorders;

import org.compiere.util.CLogger;

/**
 * Provides support for the Adempeire Look and Feel
 * 
 * Copied from the Goodies Plastic look and feel
 * 
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 
 * @version 3.9.4
 *
 */
final class AdempiereBorders {
	
	static CLogger log = CLogger.getCLogger(AdempiereBorders.class);

	private AdempiereBorders() {
		// To prevent instantiation
	}
	
	private static Border abstractEditorButtonBorder;
	
	static Border getAbstractEditorButtonBorder() {
		if (abstractEditorButtonBorder == null)
		{
			abstractEditorButtonBorder = new CompoundBorder(
					new ComboBoxArrowButtonBorder(),
					new BasicBorders.MarginBorder());
		}
		return abstractEditorButtonBorder;
	}

	private static final class ComboBoxArrowButtonBorder extends AbstractBorder implements UIResource {

	    protected static final Insets INSETS = new Insets(1, 1, 1, 1);
	    
	    protected static Class<?> VEditorAbstract = null;
	    protected static Method isReadWrite = null;
	    
	    public ComboBoxArrowButtonBorder() {
			try {
				VEditorAbstract = Class.forName("org.compiere.grid.ed.VEditorAbstract");
			} catch (ClassNotFoundException e) {
				log.severe(e.getMessage());
			}
			
			try {
				isReadWrite = VEditorAbstract.getMethod("isReadWrite");
			} catch (NoSuchMethodException | SecurityException 
					| IllegalArgumentException e) {
				log.severe(e.getMessage());
			}

	    }
	    
	    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
	    	
	        AbstractButton button = (AbstractButton) c;
	        ButtonModel model = button.getModel();

	        // Find the parent - this should only be 2 or three deep
	        Container editor = null;
	        Container parent = c.getParent();
	        while (parent != null)
	        {
	        	if (VEditorAbstract.isAssignableFrom(parent.getClass()))
	        	{
	        		editor = parent;
	        		break;
	        	}
	        	else
	        		parent = parent.getParent();
	        }
	        
	        //  If the editor is a VEditorAbstract, then its possible
	        //  for the editor to be disabled while the button is still
	        //  enabled.  In this case, we still want the button border
	        //  to be the disabled border to match the rest of the editor.
	        //  Otherwise, it looks displaced.
	        boolean editorEnabled = false;
	        if (editor != null 
	        		&& VEditorAbstract != null 
	        		&& isReadWrite != null
	        		&& VEditorAbstract.isAssignableFrom(editor.getClass()))
	        {
				try {
					editorEnabled = (boolean) isReadWrite.invoke(editor);
				} catch (IllegalAccessException 
						| IllegalArgumentException 
						| InvocationTargetException e) {
					log.severe(e.getMessage());
				}
	        }
	        
	        if (model.isEnabled() || editorEnabled) {
	            boolean isPressed = model.isPressed() && model.isArmed();

	            if (isPressed)
	                AdempiereUtils.drawPressed3DBorder(g, x, y, w, h);
	            else
	                AdempiereUtils.drawButtonBorder(g, x, y, w, h, false);
	        } else {
	            AdempiereUtils.drawDisabledBorder(g, x, y, w, h);
	        }
	    }

	    public Insets getBorderInsets(Component c) { return INSETS; }
	}
}
