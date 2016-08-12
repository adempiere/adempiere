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

package org.adempiere.pos.test;

import java.awt.KeyboardFocusManager;
import java.util.Properties;

import org.compiere.Adempiere;
import org.compiere.apps.AEnv;
import org.compiere.apps.AKeyboardFocusManager;
import org.compiere.apps.ALogin;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MSession;
import org.compiere.swing.CFrame;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Splash;

/**
 *	Point of Sales Main Client. 
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class POSClient {
		 
		public static void main(String[] args) {

			/**	Properties		*/
			Properties m_ctx = new Properties();
			Adempiere.startup(true);	//	needs to be here for UI
			Splash splash = new  Splash("POS Client");
			final FormFrame frame = new FormFrame(new CFrame("POS Client"));
			//  Focus Traversal
			KeyboardFocusManager.setCurrentKeyboardFocusManager(AKeyboardFocusManager.get());

			ALogin login = new ALogin(splash);
			if (!login.initLogin())		//	no automatic login
			{
				//	Center the window
				try {
					AEnv.showCenterScreen(login);	//	HTML load errors
				} catch (Exception ex) {
					
				}
				if (!login.isConnected() || !login.isOKpressed())
					AEnv.exit(1);
			}

			//  Check Build
			if (!DB.isBuildOK(m_ctx))
				AEnv.exit(1);

			//  Check DB	(AppsServer Version checked in Login)
			DB.isDatabaseOK(m_ctx);
		
			splash.setText(Msg.getMsg(m_ctx, "Loading"));
			splash.toFront();
		
			//
			if (!Adempiere.startupEnvironment(true)) // Load Environment
				System.exit(1);		
			MSession.get (Env.getCtx(), true);		//	Start Session
			
			//  Default Image
			frame.setIconImage(Adempiere.getImage16());

			splash.dispose();
			splash = null;	
			POSClientWindow m_window;
			m_window= new POSClientWindow(m_ctx);
			m_window.setVisible(true);
		 
		}
}

