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
 * Contributor: Yamel Senih www.erpcya.com                                    *
 * Contributor: Mario Calderon www.westfalia-it.com                           *
 *****************************************************************************/
package org.adempiere.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.I_POSPanel;
import org.compiere.apps.ADialog;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.pos.AdempierePOSException;
import org.compiere.pos.PosKeyboardFocusManager;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class VPOS extends CPOS implements FormPanel, I_POSPanel {
	
	/**	Window No					*/
	private int         					m_WindowNo;
	/**	FormFrame					*/
	private CFrame 							m_frame;
	/**	Main Panel					*/
	private CPanel							v_MainPane;
	/**	Divider Pane				*/
	private JSplitPane						v_DividerPane;
	/**	Left Panel					*/
	private CPanel							v_LeftPanel;
	/** Order Panel					*/
	private POSActionPanel 					v_ActionPanel;
	/** Current Line				*/
	private POSOrderLinePanel 				v_OrderLinePanel;
	/** Function Keys				*/
	private POSProductPanel 				v_ProductKeysPanel;
	/**	Timer for logout			*/
	private Timer 							logoutTimer;
	/** Keyoard Focus Manager		*/
	private PosKeyboardFocusManager 		m_focusMgr;
	/**	Focus Management			*/
	private KeyboardFocusManager 			originalKeyboardFocusManager;
	/**	Key Boards					*/
	private HashMap<Integer, POSKeyboard> 	keyboards = new HashMap<Integer, POSKeyboard>();
	
	/**	Logger						*/
	private CLogger							log = CLogger.getCLogger(getClass());
	/**	Format						*/
	private DecimalFormat					m_Format;
	/**	Font						*/
	private Font 							font;
	/**	Default Height				*/
	public int								m_FieldHeight;
	/**	Plus Button Size			*/
	private int								m_ButtonSize;
	
	
	/**
	 * *** Constructor ***
	 */
	public VPOS() {
		super();
		v_MainPane = new CPanel(new BorderLayout());
		v_DividerPane = new JSplitPane();
		v_DividerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		v_DividerPane.setBorder(BorderFactory.createEtchedBorder());
		v_DividerPane.setContinuousLayout(true);
		v_DividerPane.setDividerLocation(500);
		v_MainPane.add(v_DividerPane, BorderLayout.CENTER);
		originalKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		m_focusMgr = new PosKeyboardFocusManager();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
		//	Set Border
		font = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 16);
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		m_FieldHeight = 50;
		m_ButtonSize = 50;
		
	}

	@Override
	public void init(int WindowNo, FormFrame frame) {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		//	
		log.info("init - SalesRep_ID=" + Env.getAD_User_ID(getCtx()));
		m_WindowNo = WindowNo;
		m_frame = frame;
		frame.setJMenuBar(null);
		//
		try {
			if (!dynInit()) {
				dispose();
				return;
			}
			//	Add to frame
			frame.getContentPane().add(v_MainPane, BorderLayout.CENTER);
		}
		catch (AdempierePOSException exception)
		{
			ADialog.error(getWindowNo(), m_frame , exception.getLocalizedMessage());
			dispose();
			return;
		}

		log.config( "PosPanel.init - " + v_MainPane.getPreferredSize());
		
		if (getAutoLogoutDelay() > 0 && logoutTimer == null) {
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

					if (getAutoLogoutDelay()*1000 
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
	 * Get main font
	 * @return
	 * @return Font
	 */
	public Font getFont() {
		return font;
	}
	
	/**
	 * Get number format
	 * @return
	 * @return DecimalFormat
	 */
	public DecimalFormat getNumberFormat() {
		return m_Format;
	}
	
	/**
	 * Get Field Lenght
	 * @return
	 * @return int
	 */
	public int getFieldLenght() {
		return m_FieldHeight;
	}
	
	/**
	 * Get Button Size
	 * @return
	 * @return int
	 */
	public int getButtonSize() {
		return m_ButtonSize;
	}
	
	/**
	 * Load POS
	 * @return String
	 */
	private void loadPOS() {
		int salesRep_ID = Env.getAD_User_ID(getCtx());
		setPOS(salesRep_ID);
		if(getM_POS() != null) {
			validLocator();
			return;
		}
		//	Select POS
		String msg = Msg.getMsg(getCtx(), "SelectPOS");
		String title = Env.getHeader(getCtx(), m_WindowNo);
		Object selection = JOptionPane.showInputDialog(m_frame, msg, title,
				JOptionPane.QUESTION_MESSAGE, null, getPOSs(salesRep_ID), null);

		if (selection != null) {
			setM_POS((MPOS)selection);
			validLocator();
		}
	}
	
	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit() {
		loadPOS();
		m_frame.setTitle("Adempiere POS: " + getPOSName());
		//	Create Sub Panels
		v_LeftPanel = new CPanel(new GridBagLayout());
		v_ActionPanel = new POSActionPanel(this);
		v_LeftPanel.add(v_ActionPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		v_LeftPanel.setPreferredSize(new Dimension(500, 800));
		v_LeftPanel.setMinimumSize(new Dimension(500, 800));
		//
		v_OrderLinePanel = new POSOrderLinePanel(this);
		v_LeftPanel.add(v_OrderLinePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1
				,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		v_ProductKeysPanel = new POSProductPanel(this);
		v_ProductKeysPanel.setPreferredSize(new Dimension(500, 800));
		v_ProductKeysPanel.setMinimumSize(new Dimension(500, 800));
		v_DividerPane.add(v_LeftPanel, JSplitPane.LEFT);
		v_DividerPane.add(v_ProductKeysPanel, JSplitPane.RIGHT);		
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
	}	//	dispose
	
	/**
	 * Get Keyboard
	 */
	public POSKeyboard getKeyboard(int keyLayoutId) {
		if ( keyboards.containsKey(keyLayoutId) )
			return keyboards.get(keyLayoutId);
		else if (keyLayoutId > 0 ){
			POSKeyboard keyboard = new POSKeyboard(v_MainPane, keyLayoutId);
			keyboards.put(keyLayoutId, keyboard);
			return keyboard;
		}
		return null;
	}
	
	/**
	 * Get POS Keyboard
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
	 * @param p_C_BPartner_ID
	 * @return void
	 */
	public void newOrder(int p_C_BPartner_ID) {
		boolean isDocType = ADialog.ask(0, v_MainPane, Msg.getMsg(getCtx(), "POS.AlternateDT"));
		newOrder(isDocType, p_C_BPartner_ID);
		setC_BPartner_ID(p_C_BPartner_ID);
	}
	
	/**
	 * New Order
	 * @return void
	 */
	public void newOrder() {
		newOrder(0);
	}

	@Override
	public void refreshPanel() {
		//	Reload from DB
		reloadOrder();
		v_ActionPanel.refreshPanel();
		v_ActionPanel.changeViewPanel();
		v_ProductKeysPanel.refreshPanel();
		v_OrderLinePanel.refreshPanel();
	}
	
	/**
	 * Refresh Product Info
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		v_ActionPanel.refreshProductInfo(key);
	}
	
	/**
	 * Refresh Product Info
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProductInfo(int p_M_Product_ID) {
		v_ActionPanel.refreshProductInfo(p_M_Product_ID);
	}
	
	/**
	 * Refresh Header
	 * @return void
	 */
	public void refreshHeader() {
		reloadOrder();
		v_ActionPanel.changeViewPanel();
		v_ProductKeysPanel.refreshPanel();
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		
	}
}