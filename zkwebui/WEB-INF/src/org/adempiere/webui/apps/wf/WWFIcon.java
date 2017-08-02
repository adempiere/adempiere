/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.webui.apps.wf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.adempiere.webui.apps.AEnv;
import org.compiere.util.CLogger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;


/**
 *	Work Flow Icon
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: WFIcon.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1176">
 * 		@see FR [ 1176 ] Look and feel style to ADempiere 390 - Change icons on Work Flow</a>
 */
public class WWFIcon implements Icon {
	
	/**
	 * 	Constructor
	 *	@param action image indicator
	 */
	public WWFIcon (String action) {
		this.action= action;
	}	//	WFIcon


	private static int 		WIDTH = 20;		//	Image is 16x16
	private static int 		HEIGHT = 20;

	/**	Action				*/
	private String			action = null;
	private static CLogger 	log = CLogger.getCLogger(WWFIcon.class);

	/**
	 *	Draw the icon at the specified location.  Icon implementations
	 *	may use the Component argument to get properties useful for
	 *	painting, e.g. the foreground or background color.
	 *
	 * 	@param c	Component
	 * 	@param g	Graphics
	 * 	@param x	X
	 * 	@param y	Y
	 * @see javax.swing.Icon#paintIcon(Component, Graphics, int, int)
	 */
	public void paintIcon (Component c, Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D)g;
		AImage image = null;
		try {
			image = new AImage(Executions.encodeToURL(AEnv.getMenuIconFile(action)));
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		//	Edit image
		if (image != null) {
			ImageIcon icon = image.toImageIcon();
			int xI = x + ((WIDTH - icon.getIconWidth()) / 2);
			int yI = y + ((HEIGHT - icon.getIconHeight()) / 2);
			icon.paintIcon(c, g, xI, yI);
		} else { //	draw dot
			int size = 10;
			int xI = x + ((WIDTH - size) / 2);
			int yI = y + ((HEIGHT - size) / 2);
			g2D.setColor(Color.magenta);
			g2D.fillOval(xI, yI, size, size);
		}
	}	//	PaintIcon

	/**
	 *	Returns the icon's width.
	 *	@return an int specifying the fixed width of the icon.
	 * @see javax.swing.Icon#getIconWidth()
	 */
	public int getIconWidth() {
		return WIDTH;
	}	//	getIconWidth

	/**
	 *	Returns the icon's height.
	 *	@return an int specifying the fixed height of the icon.
	 * @see javax.swing.Icon#getIconHeight()
	 */
	public int getIconHeight() {
		return HEIGHT;
	}	//	getIconHeight

}	//	WFFIcon
