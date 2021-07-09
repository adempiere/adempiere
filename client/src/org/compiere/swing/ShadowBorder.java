/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;

/**
 * A border with a drop shadow. 
 * Extracted from JGoodies look demo.
 * @author Low Heng Sin
 * @version 2006-11-24 
 */
public class ShadowBorder extends AbstractBorder {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 4027643023639628783L;
	private static final Insets INSETS = new Insets(1, 1, 3, 3);

    public Insets getBorderInsets(Component c) { return INSETS; }

    public void paintBorder(Component c, Graphics g,
        int x, int y, int w, int h) {
            
        Color shadow        = UIManager.getColor("controlShadow");
        if (shadow == null) {
            shadow = Color.GRAY;
        }
        Color lightShadow   = new Color(shadow.getRed(), 
                                        shadow.getGreen(), 
                                        shadow.getBlue(), 
                                        170);
        Color lighterShadow = new Color(shadow.getRed(),
                                        shadow.getGreen(),
                                        shadow.getBlue(),
                                        70);
        g.translate(x, y);
        
        g.setColor(shadow);
        g.fillRect(0, 0, w - 3, 1);
        g.fillRect(0, 0, 1, h - 3);
        g.fillRect(w - 3, 1, 1, h - 3);
        g.fillRect(1, h - 3, w - 3, 1);
        // Shadow line 1
        g.setColor(lightShadow);
        g.fillRect(w - 3, 0, 1, 1);
        g.fillRect(0, h - 3, 1, 1);
        g.fillRect(w - 2, 1, 1, h - 3);
        g.fillRect(1, h - 2, w - 3, 1);
        // Shadow line2
        g.setColor(lighterShadow);
        g.fillRect(w - 2, 0, 1, 1);
        g.fillRect(0, h - 2, 1, 1);
        g.fillRect(w-2, h-2, 1, 1);
        g.fillRect(w - 1, 1, 1, h - 2);
        g.fillRect(1, h - 1, w - 2, 1);
        g.translate(-x, -y);
    }
}
