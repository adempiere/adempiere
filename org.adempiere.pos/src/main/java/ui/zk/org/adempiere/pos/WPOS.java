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

import java.awt.KeyboardFocusManager;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.I_POSPanel;
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
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MPOS;
import org.compiere.pos.PosKeyboardFocusManager;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Iframe;

/**
 *	Point of Sales Main Window.
 * @author Raul Muñoz 19/03/2015, 12:57
 *
 */
public class WPOS extends CPOS implements IFormController, EventListener, I_POSPanel {
	/**
	 * 	Constructor - see init 
	 */
	public WPOS()
	{
		m_focusMgr = new PosKeyboardFocusManager();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
		init();
	}	//	PosPanel
	

	private CustomForm form = new CustomForm();
	/**	FormFrame			*/
	private Iframe 		m_frame;
	/**	Logger				*/
	private CLogger			log = CLogger.getCLogger(getClass());
	
	/** Keyoard Focus Manager		*/
	private PosKeyboardFocusManager	m_focusMgr = null;
	
	/** Order Panel				*/
	private WPOSActionPanel f_OrderPanel;
	private WPOSProductPanel f_ProductKeysPanel;
	private WPOSOrderLinePanel f_OrderLinePanel;
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
	/**	POS Message					*/
	private String 				m_POSMsg;	
	/**	POS Configuration		*/
	private MPOS 				m_POS;
	/**
	 *	zk Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init ()
	{

		
		log.info("init - SalesRep_ID=" + getSalesRep_ID());
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
		Borderlayout mainLayout = new Borderlayout();	
		
		if (!setMPOS()){
			m_POSMsg = "@POS.NoPOSForUser@";
			dispose();
			return false;
		}

		f_OrderPanel = new WPOSActionPanel(this);
		f_ProductKeysPanel = new WPOSProductPanel(this);
		f_OrderLinePanel = new WPOSOrderLinePanel(this);

		East east = new East();
		Center center = new Center();
		North north = new North();
		Borderlayout fullPanel = new Borderlayout();
		
		center.setStyle("border: none; width:40%");
		center.appendChild(fullPanel);
		mainLayout.appendChild(center);

		center.setStyle("border: none; width:40%");
		fullPanel.setWidth("100%");
		fullPanel.setHeight("100%");
		Center cente2r = new Center();
		cente2r.appendChild(f_OrderLinePanel);
		north.appendChild(f_OrderPanel);
		east.appendChild(f_ProductKeysPanel);
		east.setStyle("border: none; width:40%");

		fullPanel.appendChild(cente2r);
		fullPanel.appendChild(north);
		north.setStyle("border: none; width:40%; height:15%");
		cente2r.setStyle("border: none; width:40%;  height:85%; ");
		
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.appendChild(east);

		form.appendChild(mainLayout);
		
		return true;
	}	//	dynInit

	/**
	 * Load POS
	 * @author Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return boolean
	 */
	

	/**
	 * 	Set MPOS
	 *	@return true if found/set
	 */
	private boolean setMPOS()
	{
		boolean ok = setPOS();
		if(!ok){
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
			setM_POS(m_POS);
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
	
	/**
	 * Get POS Keyboard
	 * @author Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return POSKeyboard
	 */
	public WPOSKeyboard getKeyboard() {
		return getKeyboard(getOSKeyLayout_ID());
	}
	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void refreshPanel() {
		//	Reload from DB
		reloadOrder();
		f_OrderPanel.changeViewPanel();
		f_ProductKeysPanel.refreshPanel();
		f_OrderLinePanel.refreshPanel();
		
	}

	@Override
	public void changeViewPanel() {
	
		
	}
	/**
	 * New Order
	 * @author Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void newOrder() {
		//	Do you want to use the alternate Document type?
		boolean isDocType = FDialog.ask(0, null, Msg.getMsg(m_ctx, "POS.AlternateDT"));
		setC_BPartner_ID(0);
		newOrder(isDocType);
	}
}	//	PosPanel

