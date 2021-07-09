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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.plaf.ComponentUI;

import org.compiere.grid.ed.VEditorAbstract;
import org.compiere.swing.CScrollingTextPane;
import org.compiere.util.CLogger;

/**
 *  ADempiere TextLong UI.
 *  Provides support for a TextLong editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 */
public class AdempiereTextLongUI extends AdempiereEditorAbstractUI
{
	
	CLogger log = CLogger.getCLogger(AdempiereTextLongUI.class);
	
	CScrollingTextPane editorComponent;

	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereDateUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereTextLongUI();
	}   //  CreateUI
    
	@Override
    public void installUI(JComponent c) {
			
		//  The component should be the container for the editor
		//  typically a JPane but it could be the editor if the
		//  editor has no button
		this.container = c;
		
		//  This Look and Feel UI only applies to the VEditorAbstract class or its subclasses
		if (!(c instanceof VEditorAbstract))
			return;
		
		isTableCellEditor = ((VEditorAbstract) c).isTableCellEditor();
				
		//  Create the text field editor. For the long text
		//  this is a scrolling text pane
		editorComponent = new CScrollingTextPane();
		
		if (editorComponent != null)
		{
			editorComponent.setForeground(AdempierePLAF.getTextColor_Normal());
		}
		
		// Change the layout manager to an overlayLayout
		if ( ! (container.getLayout() instanceof OverlayLayout))
		{
			container.setLayout(new OverlayLayout(container));
		}
		
		//  Create a panel that will display the cell focus border. It
		//  will be transparent and sit as an overlay on the editor. This
		//  is done to prevent the application of a border from changing
		//  the internal position of the editor components.  Icons on the
		//  right or left tend to shift when the border is applied directly
		//  to their parent panel.
		borderPanel.setOpaque(false);
		borderPanel.setBackground(new Color(0,0,0,0));  // Transparent

		//  Set the borders. This depends on whether the editor
		//  is being used in a table cell or not.
		setBorders();

		editorPanel = new JPanel(new BorderLayout());
		if (editorComponent != null)
			editorPanel.add(editorComponent, BorderLayout.CENTER);

		c.removeAll();  // In case the install runs more than once.
		c.add(borderPanel);
		c.add(editorPanel);
				
    }

	public void addFocusListener(FocusListener listener) {
		
		editorComponent.addFocusListener(listener);
		
	}


	public void addMouseListener(MouseListener mouseAdapter) {
		
		editorComponent.addMouseListener(mouseAdapter);
		
	}

	@Override
    protected Dimension getDisplaySize() {

    	return new Dimension (500,80);
    	
    }


}   