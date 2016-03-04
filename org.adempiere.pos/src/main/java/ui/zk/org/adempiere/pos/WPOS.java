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
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.pos.test.SideServer;
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
import org.adempiere.webui.panel.StatusBarPanel;
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
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Timer;

/**
 *	Point of Sales Main Window. 
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOS extends CPOS implements IFormController, EventListener, POSPanelInterface, POSScalesPanelInterface {
	/**
	 * 	Constructor - see init 
	 */
	public WPOS()
	{
		//Setting Keyboard Manager
		SettingKeyboardFocusManager();
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		init();
	}	//	PosPanel

	private void SettingKeyboardFocusManager()
	{
		if (isVirtualKeyboard()) {
			m_focusMgr = new PosKeyboardFocusManager();
			KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
		}
	}

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
	/**	Timer for logout					*/
	private Timer							logoutTimer;
	/**	Timer for User Pin					*/
	private Timer 							userPinTimer;
	/** Is Correct User Pin					*/
	private Boolean							isCorrectUserPin;
	/** User Pin Listener 					*/
	private WPOSUserPinListener 			userPinListener;
	/** Order Panel							*/
	private WPOSActionPanel 				v_ActionPanel;
	private WPOSProductPanel 				f_ProductKeysPanel;
	private WPOSOrderLinePanel 				f_OrderLinePanel;
	/** Quantity panel **/
	private WPOSQuantityPanel 				v_QuantityPanel;
	/**	Info Product Panel	*/
	private WPOSInfoProduct 				infoProductPanel;

	/** Status Bar 							*/
	private StatusBarPanel 					statusBar = new StatusBarPanel();
	
	/** Actions 							*/
	private Button 							b_ok 		 = new Button("Ok");
	private Button 							b_cancel	 = new Button("Cancel");
	
	/**	Today's (login) date				*/
	private Timestamp						m_today 	 = Env.getContextAsDate(ctx, "#Date");
	private HashMap<Integer, WPOSKeyboard> 	keyboards 	 = new HashMap<Integer, WPOSKeyboard>();
	private Listbox 						listTerminal = ListboxFactory.newDropdownListbox();
	private List<MPOS> poss;
	
	/** Default Font Size Medium 				*/
	public static final String 	FONTSIZEMEDIUM	= "Font-size:medium;";
	/** Default Font Size Small 				*/
	public static final String 	FONTSIZESMALL 	= "Font-size:small;";
	/** Default Font Size Large 				*/
	public static final String 	FONTSIZELARGE 	= "Font-size:x-large;";
	/** Default Font Weight	 					*/
	public static final String 	FONTSTYLE 		= "font-weight:bold;";
	/** Status bar info							*/
	private String 							statusBarInfo = "";	
	/** Side Server for Printer 				*/
	private static SideServer 				m_SideServer;

	/**
	 *	zk Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init ()
	{
		log.info("init - SalesRep_ID=" + Env.getAD_User_ID(getCtx()));
		windowNo = form.getWindowNo();
		isCorrectUserPin = null;
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
		
		if (getAutoLogoutDelay() > 0 && logoutTimer == null) {
			logoutTimer = new Timer(1000);
			logoutTimer.start();
		}
		
		startServerSocket();
	}	//	init

	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit()
	{ 
		setMPOS();
		userPinListener = new WPOSUserPinListener(this);
		//Delay 5 seconds by default
		userPinTimer = new Timer((getAutoLogoutDelay() + 5)  * 1000);
		userPinTimer.addEventListener(Events.ON_TIMER, userPinListener);
		userPinListener.setTimer(userPinTimer);
		userPinTimer.setRunning(false);
		Borderlayout mainLayout = new Borderlayout();	
		v_ActionPanel = new WPOSActionPanel(this);
		f_ProductKeysPanel = new WPOSProductPanel(this);
		f_OrderLinePanel = new WPOSOrderLinePanel(this);
		infoProductPanel = new WPOSInfoProduct(this);
		v_QuantityPanel = new WPOSQuantityPanel(this);
		East east = new East();
		Center center = new Center();
		North actionPanel = new North();
		North qtyPanel = new North();
		South southPanel = new South();
		Center table = new Center();
		Borderlayout fullPanel = new Borderlayout();
		Borderlayout mediumPanel = new Borderlayout();
		statusBar.appendChild(userPinTimer);
		southPanel.appendChild(statusBar);
		center.setStyle("border: none; width:40%");
		center.appendChild(fullPanel);
		mainLayout.appendChild(center);
		center.setStyle("border: none; height:100%");
		fullPanel.setWidth("100%");
		fullPanel.setHeight("100%");
		
		table.appendChild(f_OrderLinePanel);
		actionPanel.appendChild(v_ActionPanel);
		east.appendChild(f_ProductKeysPanel);
		east.appendChild(infoProductPanel);
		east.setSplittable(true);
		east.setStyle("border: none; min-width:44%; width:44%");
		actionPanel.setStyle("border: none; height:auto; position:relative;float:left;overflow:auto; ");

		qtyPanel.appendChild(v_QuantityPanel);
		
		fullPanel.appendChild(actionPanel);

		Center centerPanel = new Center();
		fullPanel.appendChild(centerPanel);
		centerPanel.appendChild(mediumPanel);
		if(IsShowLineControl())
			mediumPanel.appendChild(qtyPanel);
		
		mediumPanel.appendChild(table);
		//	FR [ 44 ] Change Button location
		actionPanel.setStyle("border: none; width:42%; height:auto;position:relative;float:left;overflow:auto;");
		qtyPanel.setStyle("border: none; width:60%; height:auto;");
		table.setStyle("border: none; width:30%; height:auto;");
		
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.appendChild(east);
		mainLayout.appendChild(southPanel);
		form.appendChild(mainLayout);
		//	Seek to last
		if(hasRecord()){
			lastRecord();	
		}
		refreshPanel();
		form.setHeight("100%");
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
		String msg = Msg.getMsg(ctx, "SelectPOS");
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
		for(MPOS pos : poss){
			listTerminal.addItem(pos.getKeyNamePair());
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
	 * @return getProperties ctx
	 */
	public Properties getCtx()
	{
		return ctx;
	}

	public WPOSKeyboard getKeyboard(int keyLayoutId) {
		if (keyLayoutId > 0 ){
			WPOSKeyboard keyboard = new WPOSKeyboard(this, keyLayoutId);
			keyboards.put(keyLayoutId, keyboard);
			keyboard.setWidth("750px");
			keyboard.setHeight("350px");
			return keyboard;
		}
		return null;
	}
	
	public WPOSKeyboard getKeyboard(int keyLayoutId, WPOSTextField field) {
		if (keyLayoutId > 0 ){
			WPOSKeyboard keyboard = new WPOSKeyboard(this, keyLayoutId);
			keyboard.setPosTextField(field);
			keyboards.put(keyLayoutId, keyboard);
			return keyboard;
		}
		return null;
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

		if ( logoutTimer != null )
			logoutTimer.stop();
		logoutTimer = null;
		
		if (isVirtualKeyboard()) {
			if (m_focusMgr != null)
				m_focusMgr.stop();
			m_focusMgr = null;
		}
		//
		if (m_frame != null)
			m_frame.detach();
		
		m_frame = null;
		ctx = null;
	}	//	dispose

	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getTarget().equals(b_ok)){
			setM_POS(poss.get(listTerminal.getSelectedIndex()));
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
	public String validatePayment() {
		return null;
	}

	@Override
	public void refreshPanel() {
		//	Reload from DB
		reloadOrder();
		f_OrderLinePanel.refreshPanel();
		v_ActionPanel.refreshPanel();
		f_ProductKeysPanel.refreshPanel();
		if(!hasLines()) {
			infoProductPanel.resetValues();
			v_QuantityPanel.resetPanel();
		}
	}

	/**
	 * Refresh Product Info from Key
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		infoProductPanel.refreshProduct(key , getQty() , getM_PriceList_ID() , getC_BPartner_ID());
		//parameterPanel.invalidate();
	}

	/**
	 * Refresh Product Info from Product
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProductInfo(int p_M_Product_ID) {
		infoProductPanel.refreshProduct(p_M_Product_ID , getQty() , getM_PriceList_ID() , getC_BPartner_ID());
		//parameterPanel.invalidate();
	}

	@Override
	public void getMainFocus() {
	}

	/**
	 * Add or replace order line
	 * @param p_M_Product_ID
	 * @param m_QtyOrdered
	 * @return void
	 */
	public void addOrUpdateLine(int p_M_Product_ID, BigDecimal m_QtyOrdered) {
		//	Create Order if not exists
		if (!hasOrder()) {
			newOrder();
		}
		//	Show Product Info
		refreshProductInfo(p_M_Product_ID);
		//	
		String lineError = addOrUpdate(p_M_Product_ID, m_QtyOrdered);
		if (lineError != null) {
			log.warning("POS Error " + lineError);
			FDialog.error(0, 
					m_frame, Msg.parseTranslation(ctx, lineError));
		}
		//	Update Info
		refreshPanel();
		f_OrderLinePanel.seekFromProduct(p_M_Product_ID);
	}
	
	@Override
	public void changeViewPanel() {
		f_OrderLinePanel.changeViewPanel();
		v_QuantityPanel.changeViewPanel();
		v_QuantityPanel.refreshPanel();
	}
	
	/**
	 * New Order
	 * @return void
	 */
	public void newOrder() {
		newOrder(0);
		infoProductPanel.resetValues();
		v_QuantityPanel.resetPanel();
		getMainFocus();
	}

	@Override
	public javax.swing.Timer getScalesTimer() {
		return null;
	}

	public int getWindowNo()
	{
		return windowNo;
	}

	@Override
	public String getProductUOMSymbol() {
		return null;
	}

	@Override
	public void setScalesMeasure(String measure) {

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
	 * Refresh Header
	 * @return void
	 */
	public void refreshHeader() {
		reloadOrder();
		v_ActionPanel.changeViewPanel();
		f_ProductKeysPanel.refreshPanel();
	}
	
	/**
	 * Update Line Table
	 * @param p_M_Product_ID
	 */
	public void updateLineTable() {
		f_OrderLinePanel.updateLine();
	}

	@Override
	public void hideScales() {

	}

	@Override
	public void showKeyboard() {

	}

	/**
	 * Show Collect Payment Panel
	 * @return void
	 */
	public void showCollectPayment()
	{
		f_ProductKeysPanel.getCollectPayment().showCollect();
	}
	
	/**
	 * Close Collect Payment Panel
	 * @return void
	 */
	public void closeCollectPayment()
	{
		f_ProductKeysPanel.closeCollectPayment();
	}
	
	public void disablePOSButtons()
	{

		v_QuantityPanel.resetPanel();
		v_ActionPanel.resetPanel();
	}
	@Override
	public void moveUp() {
		f_OrderLinePanel.moveUp();
	}

	@Override
	public void moveDown() {
		f_OrderLinePanel.moveDown();
	}

	public void changeViewQuantityPanel()
	{
		v_QuantityPanel.changeViewPanel();
	}

	public StatusBarPanel getStatusBar()
	{
		return statusBar;
	}

	public void addStatusBarInfo(String info)
	{
		statusBarInfo = statusBarInfo + " " + info + " ";
		getStatusBar().setStatusLine(statusBarInfo);
	}

	public int getC_OrderLine_ID()
	{
		return f_OrderLinePanel.getC_OrderLine_ID();
	}
	
	/**
	 * return User Pin Listener
	 * @return
     */
	public EventListener getUserPinListener()
	{
		return userPinListener;
	}
	
	/**
	 * return User Pin Listener
	 * @return
     */
	public void setUserPinListener(Event e)
	{
		userPinListener.doPerformAction(e);
	}
	
	/**
	 * set the correct user pin
	 * @param isCorrectUserPin
     */
	protected void setIsCorrectUserPin(boolean isCorrectUserPin)
	{
		this.isCorrectUserPin = isCorrectUserPin;
	}

	/**
	 * Set user PIN based on pin validation
	 * @param userPin
     */
	protected void validateAndSetUserPin(char[] userPin)
	{
		if (isCorrectUserPin != null && isCorrectUserPin)
			return;
		boolean isValidUserPin = isValidUserPin(userPin);
		if (isValidUserPin)
		{
			System.out.println(userPinTimer.isRunning());
			userPinTimer.start();
			setIsCorrectUserPin(isValidUserPin);

			System.out.println(userPinTimer.isRunning());
		}
	}

	/**
	 * invalidate user pin
	 */
	protected void invalidateUserPin()
	{
		this.isCorrectUserPin = null;
	}

	/**
	 * Is correct User Pin asynchronous validation
	 * @return
     */
	public boolean isUserPinValid()
	{
		if (!isRequiredPIN())
			return true;

		if (isCorrectUserPin == null || !isCorrectUserPin) {
			//FDialog.error(0,Msg.parseTranslation(getCtx(), ("@Supervisor_ID@: @UserPin@ @IsInvalid@.")));
			throw new AdempiereException("@Supervisor_ID@: @UserPin@ @IsInvalid@.");
		}

		return isCorrectUserPin;
	}
	
	/**
	 * Start Server Socket
	 * @return void
	 */
	private void startServerSocket(){
		m_SideServer = new SideServer();
		new Thread(m_SideServer).start();
	}

	/**
	 * Print File
	 * @param data
	 */
	public void printFile(byte[] data, int record_ID){
		m_SideServer.printFile(data, record_ID);
	}
	
	
}	//	PosPanel
