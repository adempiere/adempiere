/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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
package org.adempiere.pos;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFormattedTextField;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;

/**
 * Formatted Text field with on-screen keyboard support
 * 
 * @author Paul Bowden Adaxa Pty Ltd
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * <li> Changes for generic use
 */
public class POSTextField extends JFormattedTextField
		implements MouseListener , KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2643611240006156141L;

	/**
	 * Text field with keyboard
	 * *** Constructor ***
	 * @param title
	 * @param keyboard
	 */
	public POSTextField(String title, POSKeyboard keyboard) {
		super();
		//	
		this.keyboard = keyboard;
		//	Valid and add Listener
		if (this.keyboard != null) {
			addMouseListener(this);
		}
		//	Set Title
		setName(title);
	}
	
	/**
	 * Text field without keyboard
	 * *** Constructor ***
	 * @param title
	 */
	public POSTextField(String title) {
		this(title, null);
	}
	
	/**	Key Board				*/
	private POSKeyboard 	keyboard;
	/**	Place Holder			*/
	private String 			placeHolder;
	/**	Default Font			*/
	private Font 			defaultFont = AdempierePLAF.getFont_Field().deriveFont(Font.PLAIN, 16);
	
	public void mouseReleased(MouseEvent mouseEvent) {
		//	Not yet implemented
	}

	public void mousePressed(MouseEvent mouseEvent) {
		//	Not yet implemented
	}

	public void mouseExited(MouseEvent mouseEvent) {
		//	Not yet implemented
	}

	public void mouseEntered(MouseEvent mouseEvent) {
		//	Not yet implemented
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		//	When the mouse is clicked
		if (isEnabled() 
				&& isEditable()
				&& keyboard != null) {
			keyboard.setTitle(getName());
			keyboard.setPosTextField(this);
			AEnv.positionCenterScreen(keyboard);
			keyboard.setVisible(true);
			if(keyboard.isOk()) {
				fireActionPerformed();
			}
		}
	}
	
	/**
	 * Get Place Holder
	 * @return String
	 */
	public String getPlaceholder() {
		return placeHolder;
	}
	
	/**
	 * Set Place Holder
	 * @param placeHolder
	 * @return void
	 */
	public void setPlaceholder(final String placeHolder) {
		this.placeHolder = placeHolder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);
		
		//	Valid Null
		if (placeHolder == null
				|| placeHolder.length() == 0
				|| getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getDisabledTextColor());
		g.setFont(defaultFont);
		g.drawString(placeHolder, getMargin().left  ,(getSize().height)/2 + getFont().getSize()/2 );
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}