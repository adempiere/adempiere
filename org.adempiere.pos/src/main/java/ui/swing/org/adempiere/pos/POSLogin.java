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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import org.compiere.apps.AppsAction;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Login;
import org.compiere.util.Msg;

/**
 * 
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * <li> Add Support to new POS Text Field
 */
public class POSLogin extends CDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8490567722808711399L;
	private VPOS posPanel;
	private POSTextField username;
	private POSTextField pin;
	private CButton bProcess;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSLogin (VPOS posPanel)
	{
		super (Env.getWindow(posPanel.getWindowNo()),Msg.translate(posPanel.getCtx(), "Login"), true);
		init();
		this.posPanel = posPanel;
	}

	private void init() {
		CPanel panel = new CPanel();
		panel.setLayout(new MigLayout());
		getContentPane().add(panel);
		
		panel.add(new CLabel(Msg.translate(posPanel.getCtx(),"SalesRep_ID")));
		
		username = new POSTextField(Msg.translate(posPanel.getCtx(),"SalesRep_ID"), posPanel.getKeyboard());
		
		panel.add( username, "wrap");
		
		panel.add(new CLabel(Msg.translate(posPanel.getCtx(), "UserPIN")));
		
		pin = new POSTextField(Msg.translate(posPanel.getCtx(), "UserPIN"), posPanel.getKeyboard());
		
		panel.add(pin, "");
		
		AppsAction act = new AppsAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), false);
		act.setDelegate(this);
		bProcess = (CButton)act.getButton();
		bProcess.setFocusable(false);
		panel.add (bProcess, "h 50!, w 50!");
		
		pack();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource().equals(bProcess) )
		{
			Login login = new Login(posPanel.getCtx());
			login.getRoles(username.getText(), pin.getText());
		}
		
		dispose();
	}
}