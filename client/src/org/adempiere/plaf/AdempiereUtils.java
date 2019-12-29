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

import java.awt.Graphics;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;

/**
 * A set of utilities to assist with the drawing of borders. Copied largely
 * from the PlasticBorders class but modified to deal with the compound 
 * editors used in Adempiere.
 * 
 * @author Michael McKay, mckayERP@gmail.com
 * 
 * @version 3.9.4
 */
public final class AdempiereUtils {

	private AdempiereUtils() {
		// Private to prevent instantiation
	}

	/**
	 * Draw a disabled border
	 */
	static void drawDisabledBorder(Graphics g, int x, int y, int w, int h) {
		g.setColor(PlasticLookAndFeel.getControlShadow());
		drawRect(g, x, y, w - 1, h - 1);
	}

	/*
	 * Copied from <code>PlasticUtils</code>
	 */
	static void drawFlush3DBorder(Graphics g, int x, int y, int w, int h) {
		g.translate(x, y);
		g.setColor(PlasticLookAndFeel.getControlHighlight());
		drawRect(g, 1, 1, w - 2, h - 2);
		g.drawLine(0, h - 1, 0, h - 1);
		g.drawLine(w - 1, 0, w - 1, 0);
		g.setColor(PlasticLookAndFeel.getControlDarkShadow());
		drawRect(g, 0, 0, w - 2, h - 2);
		g.translate(-x, -y);
	}
	
	/*
	 * Copied from <code>MetalUtils</code>.
	 */
	static void drawPressed3DBorder(Graphics g, int x, int y, int w, int h) {
		g.translate(x, y);
		drawFlush3DBorder(g, 0, 0, w, h);
		g.setColor(PlasticLookAndFeel.getControlShadow());
		g.drawLine(1, 1, 1, h - 3);
		g.drawLine(1, 1, w - 3, 1);
		g.translate(-x, -y);
	}
	
	/*
	 * Copied from <code>MetalUtils</code>.
	 */
    static void drawButtonBorder(Graphics g, int x, int y, int w, int h, boolean active) {
        if (active) {
            drawActiveButtonBorder(g, x, y, w, h);
        } else {
            drawFlush3DBorder(g, x, y, w, h);
		}
    }

	/*
	 * Copied from <code>MetalUtils</code>.
	 */
    static void drawActiveButtonBorder(Graphics g, int x, int y, int w, int h) {
        drawFlush3DBorder(g, x, y, w, h);
        g.setColor( PlasticLookAndFeel.getPrimaryControl() );
		g.drawLine( x+1, y+1, x+1, h-3 );
		g.drawLine( x+1, y+1, w-3, x+1 );
        g.setColor( PlasticLookAndFeel.getPrimaryControlDarkShadow() );
		g.drawLine( x+2, h-2, w-2, h-2 );
		g.drawLine( w-2, y+2, w-2, h-2 );
    }
    
    /*
     * Copied from <code>PlasticUtils</code>
     */
    private static void drawRect(Graphics g, int x, int y, int w, int h) {
        g.fillRect(x,   y,   w+1, 1);
        g.fillRect(x,   y+1, 1,   h);
        g.fillRect(x+1, y+h, w,   1);
        g.fillRect(x+w, y+1, 1,   h);
    }


}
