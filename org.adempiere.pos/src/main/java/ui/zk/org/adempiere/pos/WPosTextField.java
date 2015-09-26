/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos;

import java.text.Format;

import org.adempiere.webui.component.Textbox;

/**
 * 
 * @author Raul Muñoz 20/03/2015 
 */
public class WPosTextField extends Textbox {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2453719110038264481L;
	WPOS pos = null;
	int keyLayoutId = 0;
		
	public WPosTextField( WPOS pos, final int posKeyLayout_ID, Format format ) {
		super();
		
		if ( posKeyLayout_ID > 0 )
			addEventListener("onMouse", this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		
	}
	
	public int getKeyLayoutId() {
		return keyLayoutId;
	}
	public WPosTextField( WPOS pos, final int posKeyLayout_ID) {
		super();
		
		if ( posKeyLayout_ID > 0 )
			addEventListener("onMouse", this);
		
		keyLayoutId = posKeyLayout_ID;
		this.pos = pos;
		
	}
}
