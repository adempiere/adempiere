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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;

/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public abstract class CAbstractForm extends CPanel implements FormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1748655281844742688L;

	// Later it may inherit some functionallity. Therefore its included any longer.
	class FrameHandler extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {

			// Disposing isn't necessary here, because its handled by the form frame itself.
			//dispose();
		}
		
	}

    private FormFrame frame;
    private FrameHandler handler;
    
    
    public CAbstractForm() {
    	
    	handler = new FrameHandler();
    }
    
	public void init (int WindowNo, FormFrame frame) {
		
		this.frame = frame;
		frame.addWindowListener(handler);
	}
	
	public void dispose() {
		
		handler = null;
	}

	public int getWindowNo() {
		//	Yamel Senih FR [ 114 ] 2015-11-23
		return Env.getWindowNo(frame.getContainer());
	}
	
	public CFrame getWindow() {
		//	Yamel Senih FR [ 114 ] 2015-11-23
		return frame.getCFrame();
	}
}
