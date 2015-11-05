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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Properties;

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
import org.compiere.model.MPOSKey;
import org.compiere.pos.PosKeyboardFocusManager;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
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
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WPOS extends CPOS implements IFormController, EventListener, I_POSPanel {
	/**
	 * 	Constructor - see init 
	 */
	public WPOS()
	{
		m_focusMgr = new PosKeyboardFocusManager();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		init();
	}	//	PosPanel
	

	private CustomForm 						form 		 = new CustomForm();
	/**	FormFrame							*/
	private Iframe 							m_frame;
	/**	Logger								*/
	private CLogger							log 		 = CLogger.getCLogger(getClass());
	private DecimalFormat					m_Format;
	/** Window								*/
	private Window 							selection;
	/** Window No 							*/
	private int 							windowNo 	 = 0 ;
	/** Panel								*/
	public Panel 							v_Panel 	 = new Panel();
	/** Keyoard Focus Manager				*/
	private PosKeyboardFocusManager			m_focusMgr   = null;
	
	/** Order Panel							*/
	private WPOSActionPanel 				v_ActionPanel;
	private WPOSProductPanel 				f_ProductKeysPanel;
	private WPOSOrderLinePanel 				f_OrderLinePanel;
	
	/** Actions 							*/
	private Button 							b_ok 		 = new Button("Ok");
	private Button 							b_cancel	 = new Button("Cancel");
	
	/**	Today's (login) date				*/
	private Timestamp						m_today 	 = Env.getContextAsDate(m_ctx, "#Date");
	private HashMap<Integer, WPOSKeyboard> 	keyboards 	 = new HashMap<Integer, WPOSKeyboard>();
	private Listbox 						listTerminal = ListboxFactory.newDropdownListbox();
	private MPOS[] 							poss; 
	
	/** Default Font Size Medium 				*/
	public static final String 	FONTSIZEMEDIUM	= "Font-size:medium;";
	/** Default Font Size Small 				*/
	public static final String 	FONTSIZESMALL 	= "Font-size:small;";
	/** Default Font Size Large 				*/
	public static final String 	FONTSIZELARGE 	= "Font-size:x-large;";
	/** Default Font Weight	 					*/
	public static final String 	FONTSTYLE 		= "font-weight:bold;";

	/**
	 *	zk Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init ()
	{
		log.info("init - SalesRep_ID=" + Env.getAD_User_ID(getCtx()));
		windowNo = form.getWindowNo();
		//
		try
		{
			dynInit();
			
		}
		catch (AdempierePOSException exception)
		{
			FDialog.error( getWindowNo() , m_frame , exception.getLocalizedMessage());
			dispose();
			return;
		}
		
	}	//	init

	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit()
	{ 
		setMPOS();
		Borderlayout mainLayout = new Borderlayout();	
		v_ActionPanel = new WPOSActionPanel(this);
		f_ProductKeysPanel = new WPOSProductPanel(this);
		f_OrderLinePanel = new WPOSOrderLinePanel(this);
		East east = new East();
		Center center = new Center();
		North north = new North();
		Borderlayout fullPanel = new Borderlayout();
		
		center.setStyle("border: none; width:44%");
		center.appendChild(fullPanel);
		mainLayout.appendChild(center);

		center.setStyle("border: none; width:44%");
		fullPanel.setWidth("80%");
		fullPanel.setHeight("100%");
		Center v_Table = new Center();
		v_Table.appendChild(f_OrderLinePanel);
		north.appendChild(v_ActionPanel);
		east.appendChild(f_ProductKeysPanel);
		east.setStyle("border: none; width:53%");

		fullPanel.appendChild(v_Table);
		fullPanel.appendChild(north);
		north.setStyle("border: none; width:44%; height:290px");
		v_Table.setStyle("border: none; width:44%;  height:100%; ");
		
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.appendChild(east);

		form.appendChild(mainLayout);
		//	Seek to last
		if(hasRecord()){
			lastRecord();	
		}
		refreshPanel();
		return true;
	}	//	dynInit

	/**
	 * 	Set MPOS
	 *	@return true if found/set
	 */
	private void setMPOS() {
		int salesRep_ID = Env.getAD_User_ID(getCtx());
		setPOS(salesRep_ID);
		if(getM_POS() != null) {
			validLocator();
			return;
		}
		poss = getPOSs(salesRep_ID);
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
			
	}	//	setMPOS
	
	
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
			keyboard.setWidth("750px");
			keyboard.setHeight("350px");
			return keyboard;
	}
	
	public WPOSKeyboard getKeyboard(int keyLayoutId, WPOSTextField field) {
		WPOSKeyboard keyboard = new WPOSKeyboard(this, keyLayoutId);
		keyboard.setPosTextField(field);
		keyboards.put(keyLayoutId, keyboard);
		return keyboard;
	}
	
	public WPOSKeyboard getKeyboard(int keyLayoutId, Window wPosQuery, WPOSTextField field) {
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
			setM_POS(poss[listTerminal.getSelectedIndex()]);
			selection.dispose();
		}
		if(e.getTarget().equals(b_cancel)){
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
		v_ActionPanel.refreshPanel();
		v_ActionPanel.changeViewPanel();
		f_ProductKeysPanel.refreshPanel();
		f_OrderLinePanel.refreshPanel();
		
	}

	/**
	 * Add or replace order line
	 * @param p_M_Product_ID
	 * @param m_QtyOrdered
	 * @return void
	 */
	public void addLine(int p_M_Product_ID, BigDecimal m_QtyOrdered) {
		//	Create Ordder if not exists
		if (!hasOrder()) {
			newOrder();
		}
		//	Show Product Info
		refreshProductInfo(p_M_Product_ID);
		//	
		String lineError = add(p_M_Product_ID, m_QtyOrdered);
		if (lineError != null) {
			log.warning("POS Error " + lineError);
			FDialog.error(0, 
					m_frame, Msg.parseTranslation(m_ctx, lineError));
		}
		//	Update Info
		refreshPanel();
	}
	
	@Override
	public void changeViewPanel() {
		
	}
	
	/**
	 * New Order
	 * @return void
	 */
	public void newOrder() {
		newOrder(0);
	}
	
	public int getWindowNo()
	{
		return windowNo;
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
	 * Refresh Product Info
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		v_ActionPanel.refreshProductInfo(key);
	}
	
	/**
	 * Refresh Header
	 * @return void
	 */
	public void refreshHeader() {
		reloadOrder();
		v_ActionPanel.changeViewPanel();
		f_ProductKeysPanel.refreshPanel();
	}
	
	/**
	 * Refresh Product Info
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProductInfo(int p_M_Product_ID) {
		v_ActionPanel.refreshProductInfo(p_M_Product_ID);
	}
	
}	//	PosPanel
