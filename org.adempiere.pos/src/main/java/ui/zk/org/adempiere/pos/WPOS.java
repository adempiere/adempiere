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
import java.util.HashMap;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.pos.service.POSScalesPanelInterface;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
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
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
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

	/** Window No 							*/
	private int 							windowNo 	 = 0 ;
	private CustomForm 						form 		 = new CustomForm();
	/**	FormFrame							*/
	private Iframe 							frame;
	/**	Logger								*/
	private CLogger							log 		 = CLogger.getCLogger(getClass());
	/** Window								*/
	private Window 							selection;
	/** Panel								*/
	public Panel 							v_Panel 	 = new Panel();
	/**	Timer for logout					*/
	private Timer							logoutTimer;
	/**	Timer for User Pin					*/
	private Timer 							userPinTimer;
	/** Find scales Timer 					*/
	private Timer 							scalesTimer;
	/** Is Correct User Pin			*/
	private Boolean							isCorrectUserPin;
	/** User Pin Listener 			*/
	private WPOSUserPinListener 			userPinListener;
	/** Electronic Scales			*/
	private WPOSScalesListener 				scalesListener;
	/** Keyoard Focus Manager				*/
	private POSKeyboardFocusManager 		focusManager = null;
	/**	Key Boards							*/
	private HashMap<Integer, WPOSKeyboard> 	keyboards 	 = new HashMap<Integer, WPOSKeyboard>();
	/** Order Panel							*/
	private WPOSActionPanel 				actionPanel;
	/** Quantity panel **/
	private WPOSQuantityPanel 				quantityPanel;
	/**	Info Product Panel	*/
	private WPOSInfoProduct 				infoProductPanel;
	/** Current Line		*/
	private WPOSOrderLinePanel 				orderLinePanel;
	/** Function Keys		*/
	private WPOSDocumentPanel 				documentPanel;
	/** Status Bar 							*/
	private StatusBarPanel 					statusBar = new StatusBarPanel();

	/** Actions 							*/
	private Button 							okButton = new Button("Ok");
	private Button 							cancelButton = new Button("Cancel");
	private ConfirmPanel 					confirm;
	private Listbox 						listTerminal = ListboxFactory.newDropdownListbox();
	private List<MPOS> poss;

	/** Default Font Size Medium 				*/
	public static final String 	FONTSIZEMEDIUM	= "Font-size:1.3em;";
	/** Default Font Size Small 				*/
	public static final String 	FONTSIZESMALL 	= "Font-size:small;";
	/** Default Font Size Large 				*/
	public static final String 	FONTSIZELARGE 	= "Font-size:large;";
	/** Default Font Weight	 					*/
	public static final String 	FONTSTYLE 		= "font-weight:bold;";
	/** Status bar info							*/
	private String 							statusBarInfo = "";
	/**
	 * 	Constructor - see init 
	 */
	public WPOS() {
		//Setting Keyboard Manager
		settingKeyboardFocusManager();
		init();
	}	//	PosPanel

	/**
	 * Load keyboard
	 */
	private void settingKeyboardFocusManager() {
		if (isVirtualKeyboard()) {
			focusManager = new POSKeyboardFocusManager();
			KeyboardFocusManager.setCurrentKeyboardFocusManager(focusManager);
		}
	}



	/**
	 *	zk Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init () {
		log.info("init - SalesRep_ID=" + Env.getAD_User_ID(getCtx()));
		windowNo = form.getWindowNo();
		isCorrectUserPin = null;
		//
		try {
			dynInit();	
		} catch (AdempierePOSException exception) {
			FDialog.error( getWindowNo() , frame, exception.getLocalizedMessage());
			dispose();
			return;
		}
		
		if (getAutoLogoutDelay() > 0 && logoutTimer == null) {
			logoutTimer = new Timer(1000);
			logoutTimer.start();
		}
	}	//	init

	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit() { 
		setMPOS();
		userPinListener = new WPOSUserPinListener(this);
		//Delay 5 seconds by default
		userPinTimer = new Timer((getPINEntryTimeout() + 5)  * 1000);
		userPinTimer.addEventListener(Events.ON_TIMER, userPinListener);
		userPinListener.setTimer(userPinTimer);
		userPinTimer.setRunning(false);
		Borderlayout mainLayout = new Borderlayout();	
		actionPanel = new WPOSActionPanel(this);
		documentPanel = new WPOSDocumentPanel(this);
		orderLinePanel = new WPOSOrderLinePanel(this);
		infoProductPanel = new WPOSInfoProduct(this);
		quantityPanel = new WPOSQuantityPanel(this);
		East east = new East();
		Center center = new Center();
		West westPanel = new West();
		South southPanel = new South();
		Borderlayout fullPanel = new Borderlayout();
		Borderlayout mediumPanel = new Borderlayout();
		statusBar.appendChild(userPinTimer);
		southPanel.appendChild(statusBar);
		center.setStyle("border: none; width:40%");
		center.appendChild(fullPanel);
		mainLayout.appendChild(center);
		center.setStyle("border: none; height:auto%;");
		fullPanel.setWidth("100%");
		fullPanel.setHeight("auto");
		fullPanel.setStyle("overflow:hidden;height:auto;");

		westPanel.appendChild(this.actionPanel);
		east.appendChild(documentPanel);
		this.actionPanel.appendChild(infoProductPanel.getPanel());
		if(IsShowLineControl())
			this.actionPanel.appendChild(quantityPanel.getPanel());
		this.actionPanel.appendChild(orderLinePanel);
		
		east.setSplittable(true);
		east.setStyle("border: none; min-width:44%; width:44%");

		fullPanel.appendChild(westPanel);

		Center centerPanel = new Center();
		fullPanel.appendChild(centerPanel);
		centerPanel.appendChild(mediumPanel);

		//	FR [ 44 ] Change Button location
		westPanel.setStyle("display:inline-block;border: none; width:100%; height:auto;float:left;overflow:hidden;");
		
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
		int orgId = Env.getAD_Org_ID(getCtx());
		poss = getPOSByOrganization(orgId);
		//	Select POS
		String msg = Msg.getMsg(getCtx(), "SelectPOS");
		selection = new Window();
		Panel mainPanel = new Panel();
		Panel panel = new Panel();
		selection.setTitle(msg);
		Borderlayout mainLayout = new Borderlayout();
		Grid layout = GridFactory.newGridLayout();
		selection.appendChild(panel);
		selection.setWidth("200px");
		selection.setHeight("140px");
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
		okButton.addActionListener(this);
		cancelButton.addEventListener("onClick", this);
		okButton.setWidth("45px");
		okButton.setHeight("45px");
		cancelButton.setWidth("45px");
		cancelButton.setHeight("45px");
		listTerminal.setHeight("45px");
		listTerminal.setStyle("height:45px;"+WPOS.FONTSIZEMEDIUM);
		row.setSpans("2");
		row.appendChild(listTerminal);
		row.setHeight("45px");
		row = rows.newRow();
		confirm = new ConfirmPanel(true);
		confirm.addActionListener(this);
		confirm.getOKButton().setWidth("55px");
		confirm.getOKButton().setHeight("55px");
		confirm.getButton(ConfirmPanel.A_CANCEL).setWidth("55px");
		confirm.getButton(ConfirmPanel.A_CANCEL).setHeight("55px");
		
		row.appendChild(confirm);
		row.setHeight("55px");
		AEnv.showWindow(selection);
			
	}	//	setMPOS

	/**	
	 * Get Keyboard
	 * @param keyLayoutId
	 * @return
	 */
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
	
	/**
	 * Get Keyboard with text field
	 * @param keyLayoutId
	 * @param field
	 * @return
	 */
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
			if (focusManager != null)
				focusManager.stop();
			focusManager = null;
		}
		//
		if (frame != null)
			frame.detach();
		
		frame = null;
	}	//	dispose

	@Override
	public void onEvent(Event e) throws Exception {
		String action = e.getTarget().getId();
		if(action.equals(ConfirmPanel.A_OK)){
			setM_POS(poss.get(listTerminal.getSelectedIndex()));
			selection.dispose();
		}
		if(action.equals(ConfirmPanel.A_CANCEL)){
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
		orderLinePanel.refreshPanel();
		actionPanel.refreshPanel();
		documentPanel.refreshPanel();
		if(!hasLines()) {
			infoProductPanel.resetValues();
			quantityPanel.resetPanel();
			actionPanel.resetPanel();
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
		infoProductPanel.invalidate();
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
					frame, Msg.parseTranslation(getCtx(), lineError));
		}
		//	Update Info
		refreshPanel();
		orderLinePanel.seekFromProduct(p_M_Product_ID);
	}
	
	@Override
	public void changeViewPanel() {
		quantityPanel.changeViewPanel();
		orderLinePanel.changeViewPanel();
//		quantityPanel.refreshPanel();
	}
	
	/**
	 * New Order
	 * @return void
	 */
	public void newOrder() {
		newOrder(0);
		infoProductPanel.resetValues();
		quantityPanel.resetPanel();
		getMainFocus();
	}

	public int getWindowNo()
	{
		return windowNo;
	}

	@Override
	public void setScalesMeasure(String measure) {

	}
	
	/**
	 * Refresh Header
	 * @return void
	 */
	public void refreshHeader() {
		reloadOrder();
		actionPanel.changeViewPanel();
		documentPanel.refreshPanel();
	}
	
	/**
	 * Update Line Table
	 * @param p_M_Product_ID
	 */
	public void updateLineTable() {
		orderLinePanel.updateLine();
	}

	@Override
	public void hideScales() {

	}

	/**
	 * Show Collect Payment Panel
	 * @return void
	 */
	public void showCollectPayment()
	{
		documentPanel.getCollectPayment().showCollect();
	}
	
	/**
	 * Close Collect Payment Panel
	 * @return void
	 */
	public void closeCollectPayment()
	{
		documentPanel.closeCollectPayment();
	}

	@Override
	public void moveUp() {
		orderLinePanel.moveUp();
	}

	@Override
	public void moveDown() {
		orderLinePanel.moveDown();
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
		return orderLinePanel.getC_OrderLine_ID();
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
	 * return Electronic Scales Listener
	 * @return
	 */
	public WPOSScalesListener getScalesListener()
	{
		return scalesListener;
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
			userPinTimer.start();
			setIsCorrectUserPin(isValidUserPin);
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

		if (isCorrectUserPin == null)
			WPOSUserPinDialog.show(this);

		if (isCorrectUserPin == null || !isCorrectUserPin)
			throw new AdempiereException("@Supervisor_ID@: @UserPin@ @IsInvalid@.");

		return isCorrectUserPin;
	}

	public void showKeyboard()
	{
		documentPanel.getKeyboard().showPanel();
	}

	public void hideKeyboard()
	{
		documentPanel.getKeyboard().hidePanel();
	}

	public void disablePOSButtons()
	{
		infoProductPanel.resetValues();
		quantityPanel.resetPanel();
		actionPanel.disableButtons();
		orderLinePanel.disableTable();
	}
	
	public void restoreTable(){
		orderLinePanel.enableTable();
	}
	
	public String getProductUOMSymbol()
	{
		return infoProductPanel.getUOMSymbol();
	}

	public Timer getUserPinTimer()
	{
		return userPinTimer;
	}

	public Timer getScalesTimer()
	{
		return scalesTimer;
	}

	public void quantityRequestFocus()
	{
		quantityPanel.requestFocus();
	}

	public void getMainFocus()
	{
		actionPanel.getMainFocus();
	}

	/**
	 * Set Quantity of Product
	 * @param qty
	 */
	public void setQty(BigDecimal qty) {
		quantityPanel.setQuantity(qty);
		super.setQty(qty);
	}
	
	public void updateProductPlaceholder(String name)
	{
		actionPanel.updateProductPlaceholder(name);
	}
}	//	PosPanel
