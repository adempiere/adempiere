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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
		implements MouseListener, FocusListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2643611240006156141L;

	/**
	 * Text field with keyboard
	 * *** Constructor ***
	 * @param p_Title
	 * @param p_Keyboard
	 */
	public POSTextField(String p_Title, POSKeyboard p_Keyboard) {
		super();
		//	
		m_Keyboard = p_Keyboard;
		//	Valid and add Listener
		if (p_Keyboard != null) {
			addMouseListener(this);
		}
		//	Set Title
		setName(p_Title);
	}
	
	/**
	 * Text field without keyboard
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_Title
	 */
	public POSTextField(String p_Title) {
		this(p_Title, null);
	}
	
	/**	Key Board				*/
	private POSKeyboard m_Keyboard;
	/**	Place Holder			*/
	private String 			m_PlaceHolder;
	/**	Default Font			*/
	private Font 			m_Font = AdempierePLAF.getFont_Field().deriveFont(Font.PLAIN, 18);
	
	public void mouseReleased(MouseEvent arg0) {
		//	Not yet implemented
	}

	public void mousePressed(MouseEvent arg0) {
		//	Not yet implemented
	}

	public void mouseExited(MouseEvent arg0) {
		//	Not yet implemented		
	}

	public void mouseEntered(MouseEvent arg0) {
		//	Not yet implemented
	}

	public void mouseClicked(MouseEvent arg0) {
		//	When the mouse is clicked
		if (isEnabled() 
				&& isEditable()
				&& m_Keyboard != null) {
			m_Keyboard.setTitle(getName());
			m_Keyboard.setPosTextField(this);
			AEnv.positionCenterScreen(m_Keyboard);
			m_Keyboard.setVisible(true);
			if(m_Keyboard.isOk()) {
				fireActionPerformed();
			}
		}
	}

	public void focusGained(FocusEvent e) {
		//	Not yet implemented
	}

	public void focusLost(FocusEvent e) {
		//	Not yet implemented
	}

	/**
	 * Get Place Holder
	 * @return String
	 */
	public String getPlaceholder() {
		return m_PlaceHolder;
	}
	
	/**
	 * Set Place Holder
	 * @param p_PlaceHolder
	 * @return void
	 */
	public void setPlaceholder(final String p_PlaceHolder) {
		m_PlaceHolder = p_PlaceHolder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);
		
		//	Valid Null
		if (m_PlaceHolder == null
				|| m_PlaceHolder.length() == 0 
				|| getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getDisabledTextColor());
		g.setFont(m_Font);
		g.drawString(m_PlaceHolder, getMargin().left  ,(getSize().height)/2 + getFont().getSize()/2 );
	}
}