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

package org.compiere.pos;

import java.awt.KeyboardFocusManager;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.model.MPOS;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zul.Iframe;

/**
 *	Point of Sales Main Window.
 * @author Raul Muñoz 19/03/2015, 12:57
 *
 */
public class WPOS extends CPOS implements IFormController, EventListener {
	/**
	 * 	Constructor - see init 
	 */
	public WPOS()
	{
		super ();
		m_focusMgr = new PosKeyboardFocusManager();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
		zkinit();
	}	//	PosPanel
	

	private CustomForm form = new CustomForm();
	/**	Window No			*/
//	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private Iframe 		m_frame;
	/**	Logger				*/
	private CLogger			log = CLogger.getCLogger(getClass());
	
	/** Keyoard Focus Manager		*/
	private PosKeyboardFocusManager	m_focusMgr = null;
	
	/** Order Panel				*/
	protected WSubOrder 		f_order;
	/** Current Line				*/
	
	private boolean action = false;

	private Button b_ok = new Button("Ok");
	private Button b_cancel = new Button("Cancel");
	private int m_Sales_ID = 0;
	private Window selection;
	//	Today's (login) date		*/
	private Timestamp			m_today = Env.getContextAsDate(m_ctx, "#Date");
	
	private Iframe frame;
	private HashMap<Integer, WPOSKeyboard> keyboards = new HashMap<Integer, WPOSKeyboard>();
	public Panel parameterPanel = new Panel();
	private Listbox listTerminal = ListboxFactory.newDropdownListbox();
	/**
	 *	zk Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void zkinit ()
	{

		m_SalesRep_ID = Env.getAD_User_ID(m_ctx);
		log.info("init - SalesRep_ID=" + m_SalesRep_ID);
//		m_WindowNo = 0;
		m_frame = frame;
		//
		try
		{
			dynInit();
			
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "init", e);
		}
		
	}	//	init

	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit()
	{
		
		if (!setMPOS()){
			dispose();
			return false;
		}
		
		f_order = new WSubOrder(this);
		form.appendChild(f_order);
		
		return true;
	}	//	dynInit

	/**
	 * Load POS
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return boolean
	 */
	

	/**
	 * 	Set MPOS
	 *	@return true if found/set
	 */
	private boolean setMPOS()
	{
		m_SalesRep_ID = Env.getAD_User_ID(m_ctx);
		boolean ok = setPOS();
		if(!ok && msgLocator == null){
			MPOS[] poss = getPOSs();
			//	Select POS
			String msg = Msg.getMsg(m_ctx, "SelectPOS");
			selection = new Window();
			Panel mainPanel = new Panel();
			Panel panel = new Panel();
			selection.setTitle(msg);
			Borderlayout mainLayout = new Borderlayout();
			Grid layout = GridFactory.newGridLayout();
			selection.appendChild(panel);
			selection.setWidth("200px");
			selection.setHeight("100px");
			//	North
			Panel centerPanel = new Panel();
			mainPanel.appendChild(mainLayout);
			mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
			mainLayout.setHeight("100%");
			mainLayout.setWidth("100%");
			//
			Center center = new Center();
			center.setStyle("border: none");
			mainLayout.appendChild(center);
			center.appendChild(centerPanel);
			centerPanel.appendChild(layout);
			layout.setWidth("100%");
			layout.setHeight("100%");
			selection.appendChild(mainPanel);
			Rows rows = null;
			Row row = null;
			rows = layout.newRows();
			row = rows.newRow();
			for(int x=0; x<poss.length; x++){
				listTerminal.addItem(poss[x].getKeyNamePair());
			}
			b_ok.addActionListener(this);
			b_cancel.addEventListener("onClick", this);
			row.setSpans("2");
			row.appendChild(listTerminal);
			row = rows.newRow();
			row.appendChild(b_ok);
			row.appendChild(b_cancel);
			AEnv.showWindow(selection);
		}
		else
			return true;
		
			
		return action;
	}	//	setMPOS
	
	/**
	 * 	Get POSs for specific Sales Rep or all
	 *	@param SalesRep_ID
	 *	@return array of POS
	 */
	private MPOS[] getPOSs (int SalesRep_ID)
	{
		String pass_field = "SalesRep_ID";
		int pass_ID = SalesRep_ID;
		if (SalesRep_ID==0)
			{
			pass_field = "AD_Client_ID";
			pass_ID = Env.getAD_Client_ID(m_ctx);
			}
		return MPOS.getAll(m_ctx, pass_field, pass_ID);
	}	//	getPOSs
	
	
	
	/**************************************************************************
	 * 	Get Today's date
	 *	@return date
	 */
	public Timestamp getToday()
	{
		return m_today;
	}	//	getToday

	/**
	 * Get the properties for the process calls that it needs
	 * 
	 * @return getProperties m_ctx
	 */
	public Properties getCtx()
	{
		return m_ctx;
	}

	public WPOSKeyboard getKeyboard(int keyLayoutId) {
			WPOSKeyboard keyboard = new WPOSKeyboard(this, keyLayoutId);
			keyboards.put(keyLayoutId, keyboard);
			return keyboard;
	}
	
	public WPOSKeyboard getKeyboard(int keyLayoutId, WPosTextField field) {
		WPOSKeyboard keyboard = new WPOSKeyboard(this, keyLayoutId);
		keyboard.setPosTextField(field);
		keyboards.put(keyLayoutId, keyboard);
		return keyboard;
	}
	
	public WPOSKeyboard getKeyboard(int keyLayoutId, Window wPosQuery, WPosTextField field) {
			WPOSKeyboard keyboard = new WPOSKeyboard(wPosQuery,this, keyLayoutId, field);
			keyboards.put(keyLayoutId, keyboard);
			return keyboard;
	}
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		keyboards.clear();
		keyboards = null;
		
		if (m_focusMgr != null)
			m_focusMgr.stop();
		m_focusMgr = null;
		//
		if (f_order != null)
			f_order.dispose();
		f_order = null;
		
		if (m_frame != null)
			m_frame.detach();
		
		m_frame = null;
		m_ctx = null;
	}	//	dispose

	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getTarget().equals(b_ok)){
			MPOS[] poss = getPOSs (m_Sales_ID);
			m_POS = poss[listTerminal.getSelectedIndex()];
			action = true;
			selection.dispose();
		}
		if(e.getTarget().equals(b_cancel)){
			action = false;
			selection.dispose();
		}
	}

	@Override
	public ADForm getForm() {
		return form;
	}
}	//	PosPanel

