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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.pos;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.I_POSPanel;
import org.compiere.apps.ADialog;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MLocator;
import org.compiere.model.MPOS;
import org.compiere.model.MWarehouse;
import org.compiere.pos.*;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Aug 31, 2015, 12:00:10 AM
 *
 */
public class VPOS extends CPOS implements FormPanel, I_POSPanel {
	
	/**	Window No					*/
	private int         					m_WindowNo;
	/**	FormFrame					*/
	private CFrame 							m_frame;
	/**	Main Pane					*/
	private CPanel							m_MainPane;
	/** Order Panel					*/
	private POSActionPanel 					v_ActionPanel;
	/** Current Line				*/
	private POSOrderLinePanel 				v_OrderLinePanel;
	/** Function Keys				*/
	private POSProductPanel 				v_ProductKeysPanel;
	/**	Timer for logout			*/
	private Timer 							logoutTimer;
	/** Keyoard Focus Manager		*/
	private PosKeyboardFocusManager m_focusMgr;
	/**	Focus Management			*/
	private KeyboardFocusManager 			originalKeyboardFocusManager;
	/**	Key Boards					*/
	private HashMap<Integer, POSKeyboard> 	keyboards = new HashMap<Integer, POSKeyboard>();
	
	/**	Logger						*/
	private CLogger							log = CLogger.getCLogger(getClass());
	/**	Default Width				*/
	public final int						PRODUCT_PANEL_WIDTH = 530;
	/**	Default Height				*/
	public final int						FIELD_HEIGHT = 50;
	
	
	/**
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 */
	public VPOS() {
		super();
		m_MainPane = new CPanel(new MigLayout(" fill","[1!]1[1:1:]","[350]"));
		originalKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		m_focusMgr = new PosKeyboardFocusManager();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
	}

	@Override
	public void init(int WindowNo, FormFrame frame) {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		//	
		m_SalesRep_ID = Env.getAD_User_ID(m_ctx);
		log.info("init - SalesRep_ID=" + m_SalesRep_ID);
		m_WindowNo = WindowNo;
		m_frame = frame;
		frame.setJMenuBar(null);
		//
		try {
			if (!dynInit()) {
				dispose();
				m_frame.setTitle(Msg.translate(Env.getCtx(), msgLocator));
				return;
			}
			//	Add to frame
			frame.getContentPane().add(m_MainPane, BorderLayout.CENTER);
		} catch(Exception e) {
			log.log(Level.SEVERE, "init", e);
		}
		log.config( "PosPanel.init - " + m_MainPane.getPreferredSize());
		
		if (m_POS.getAutoLogoutDelay() > 0 && logoutTimer == null) {
			logoutTimer = new javax.swing.Timer(1000,
					new ActionListener() {

				PointerInfo pi = null;
				long lastMouseMove  = System.currentTimeMillis();
				long lastKeyboardEvent = System.currentTimeMillis();
				public void actionPerformed(ActionEvent e) {
					long now = e.getWhen();
					PointerInfo newPi = MouseInfo.getPointerInfo();
					// mouse moved
					if ( newPi != null && pi != null 
							&& !pi.getLocation().equals(newPi.getLocation())) {
						lastMouseMove = now;
					}
					pi = newPi;

					lastKeyboardEvent = m_focusMgr.getLastWhen();

					if (m_POS.getAutoLogoutDelay()*1000 
							< now - Math.max(lastKeyboardEvent, lastMouseMove)) {
					//	new PosLogin(this);
					}
				}
			});
			logoutTimer.start();
		}
		m_focusMgr.start();
	}
	
	/**
	 * Load POS
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return boolean
	 */
	private boolean loadPOS() {
		boolean ok = setPOS();
		if(!ok
				&& msgLocator == null) {
			//	Select POS
			String msg = Msg.getMsg(m_ctx, "SelectPOS");
			String title = Env.getHeader(m_ctx, m_WindowNo);
			Object selection = JOptionPane.showInputDialog(m_frame, msg, title, 
				JOptionPane.QUESTION_MESSAGE, null, getPOSs(), null);
			if (selection != null) {
				m_POS = (MPOS)selection;
				MWarehouse warehouse = (MWarehouse) m_POS.getM_Warehouse();
				MLocator[] locators = warehouse.getLocators(true);
				for (MLocator mLocator : locators) {
					if (mLocator.isDefault())
						return true;
					else
						continue;
				}
				//	
				msgLocator = "@M_Locator_ID@ @default@ "
						+ "@not.found@ @M_Warehouse_ID@: " 
						+ warehouse.getName();
						;
			}
		} else if(ok) {
			return true;
		}
		//	
		return false;
	}
	
	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit() {
		if (!loadPOS())
			return false;
		m_frame.setTitle("Adempiere POS: " + m_POS.getName());
		//	Create Sub Panels
		v_ActionPanel = new POSActionPanel(this);
		m_MainPane.add(v_ActionPanel, "split 2, flowy, growx, spany, spanx");
		//
		v_OrderLinePanel = new POSOrderLinePanel(this);
		m_MainPane.add(v_OrderLinePanel, "h 200, growx, growy, gaptop 30");
		
		v_ProductKeysPanel = new POSProductPanel(this);
		
		m_MainPane.add(v_ProductKeysPanel, "east");
		
		return true;
	}	//	dynInit

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose() {
		keyboards.clear();
		keyboards = null;
		if ( logoutTimer != null )
			logoutTimer.stop();
		logoutTimer = null;
		
		if (m_focusMgr != null)
			m_focusMgr.stop();
		m_focusMgr = null;
		KeyboardFocusManager.setCurrentKeyboardFocusManager(originalKeyboardFocusManager);
		//
		if (v_ActionPanel != null)
			v_ActionPanel.dispose();
		v_ActionPanel = null;
		if (v_OrderLinePanel != null) {
			// if ( m_order != null )
			// 	m_order.deleteOrder();
			v_OrderLinePanel.dispose();
		}
		v_OrderLinePanel = null;
		if (v_ProductKeysPanel != null)
			v_ProductKeysPanel.dispose();
		v_ProductKeysPanel = null;
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
		m_ctx = null;
	}	//	dispose
	
	/**
	 * Get Keyboard
	 */
	public POSKeyboard getKeyboard(int keyLayoutId) {
		if ( keyboards.containsKey(keyLayoutId) )
			return keyboards.get(keyLayoutId);
		else {
			POSKeyboard keyboard = new POSKeyboard(m_MainPane, keyLayoutId);
			keyboards.put(keyLayoutId, keyboard);
			return keyboard;
		}
	}
	
	/**
	 * Get POS Keyboard
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return POSKeyboard
	 */
	public POSKeyboard getKeyboard() {
		return getKeyboard(getOSKeyLayout_ID());
	}
	
	/**
	 * Get Window Number
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getWindowNo() {
		return m_WindowNo;
	}

	/**
	 * New Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void newOrder() {
		boolean isDocType = ADialog.ask(0, m_MainPane, Msg.getMsg(m_ctx, "POS.AlternateDT"));
		newOrder(isDocType);
		setC_BPartner_ID(0);
	}

	@Override
	public void refreshPanel() {
		//	Reload from DB
		reloadOrder();
		v_ActionPanel.changeViewPanel();
		v_ProductKeysPanel.refreshPanel();
		v_OrderLinePanel.refreshPanel();
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		
	}
}