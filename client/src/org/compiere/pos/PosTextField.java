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
package org.compiere.pos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Format;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

/**
 * Formatted Text field with on-screen keyboard support
 * @author Paul Bowden
 * Adaxa Pty Ltd
 *
 */
public class PosTextField extends JFormattedTextField implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2453719110038264481L;
	private DefaultFormatterFactory formatFactory = new DefaultFormatterFactory();
	PosBasePanel pos = null;
	int keyLayoutId = 0;
	private String title;
		
	public PosTextField(String title, PosBasePanel pos, final int posKeyLayout_ID, Format format ) {
		super(format);
		
		if ( posKeyLayout_ID > 0 )
			addMouseListener(this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		this.title = title;
		
	}
	
	public PosTextField(String title, PosBasePanel pos, final int posKeyLayout_ID, AbstractFormatter formatter ) {
		super(formatter);
		
		if ( posKeyLayout_ID > 0 )
			addMouseListener(this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		this.title = title;
		
	}
	
	
	public PosTextField(String title, PosBasePanel pos, final int posKeyLayout_ID) {
		super();
		
		if ( posKeyLayout_ID > 0 )
			addMouseListener(this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		this.title = title;
		
	}

	public void mouseReleased(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}

	public void mouseClicked(MouseEvent arg0) {

		if ( isEnabled() && isEditable() )
		{
			POSKeyboard keyboard = pos.getKeyboard(keyLayoutId); 
			keyboard.setTitle(title);
			keyboard.setPosTextField(this);
			keyboard.setVisible(true);
			fireActionPerformed();
		}
	}
}
