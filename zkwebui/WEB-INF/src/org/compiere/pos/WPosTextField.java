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
import java.text.Format;

import org.adempiere.webui.component.Textbox;

/**
 * Formatted Text field with on-screen keyboard support
 * @author Paul Bowden
 * Adaxa Pty Ltd
 *
 */
public class WPosTextField extends Textbox {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2453719110038264481L;
	WPosBasePanel pos = null;
	int keyLayoutId = 0;
		
	public WPosTextField( WPosBasePanel pos, final int posKeyLayout_ID, Format format ) {
		super();
		
		if ( posKeyLayout_ID > 0 )
			addEventListener("onMouse", this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		
	}
	
	public int getKeyLayoutId() {
		return keyLayoutId;
	}
	public WPosTextField( WPosBasePanel pos, final int posKeyLayout_ID) {
		super();
		
		if ( posKeyLayout_ID > 0 )
			addEventListener("onMouse", this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		
	}
}
