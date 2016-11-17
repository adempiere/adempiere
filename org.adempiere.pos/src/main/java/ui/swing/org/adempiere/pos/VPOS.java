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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.pos.service.POSScalesPanelInterface;
import org.compiere.apps.ADialog;
import org.compiere.apps.StatusBar;
import org.compiere.apps.Waiting;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class VPOS extends CPOS implements FormPanel, POSPanelInterface, POSScalesPanelInterface {
	
	/**	FormFrame					*/
	private CFrame 							frame;
	/**	Main Panel					*/
	private CPanel 							mainPanel;
	/**	Divider Pane				*/
	private JSplitPane 						dividerPane;
	/**	Left Panel					*/
	private CPanel 							leftPanel;
	/** Order Panel					*/
	private POSActionPanel 					actionPanel;
	/** Quantity panel 				*/
	private POSQuantityPanel 				quantityPanel;
	/**	Info Product Panel			*/
	private POSInfoProduct 					infoProductPanel;
	/** Current Line				*/
	private POSOrderLinePanel 				orderLinePanel;
	/** Function Keys				*/
	private POSDocumentPanel 				documentPanel;
	/** Status Bar 					*/
	private StatusBar 						statusBar;
	/**	Timer for logout			*/
	private Timer 							logoutTimer;
	/**	Timer for User Pin			*/
	private Timer 							userPinTimer;
	/** Find Product Timer 			*/
	private Timer 							scalesTimer;
	/** Is Correct User Pin			*/
	private Boolean							isCorrectUserPin;
	/** User Pin Listener 			*/
	private POSUserPinListener 				userPinListener;
	/** Electronic Scales			*/
	private POSScalesListener 				scalesListener;
	/** Keyoard Focus Manager		*/
	private POSKeyboardFocusManager 		focusManager;
	/**	Focus Management			*/
	private KeyboardFocusManager 			originalKeyboardFocusManager;
	/**	Key Boards					*/
	private HashMap<Integer, POSKeyboard> 	keyboards = new HashMap<Integer, POSKeyboard>();
	
	/**	Logger						*/
	private CLogger 						logger = CLogger.getCLogger(getClass());
	/**	Font						*/
	private Font 							font;
	/**	Plain Font					*/
	private Font 							plainFont;
	/**	Big Font					*/
	private Font 							bigFont;
	/**	Big Plain Font				*/
	private Font 							bigPlainFont;
	/**	Default Height				*/
	public int 								fieldHeight;
	/**	Plus Button Size			*/
	private int 							buttonSize;
	/** Status bar info				*/
	private String 							statusBarInfo;
	/**	Waiting Dialog				*/
	private Waiting 						waiting;
	
	/**
	 * *** Constructor ***
	 */
	public VPOS() {
		super();

		mainPanel = new CPanel(new BorderLayout());
		mainPanel.setFocusCycleRoot(true);
		dividerPane = new JSplitPane();
		dividerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		dividerPane.setBorder(BorderFactory.createEtchedBorder());
		dividerPane.setContinuousLayout(true);
		dividerPane.setDividerLocation(dividerPane.getSize().width
				- dividerPane.getInsets().right
				- dividerPane.getDividerSize()
				- 100);
		mainPanel.add(dividerPane, BorderLayout.CENTER);
		statusBar = new StatusBar();
		statusBarInfo = "";
		mainPanel.add(statusBar , BorderLayout.SOUTH);
		//	
		font = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 12);
		plainFont = AdempierePLAF.getFont_Field().deriveFont(Font.PLAIN, 12);
		bigFont = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 14);
		bigPlainFont = AdempierePLAF.getFont_Field().deriveFont(Font.PLAIN, 14);
		
		fieldHeight = 45;
		buttonSize = 45;
		
	}
	
	/**
	 * Load keyboard
	 */
	private void settingKeyboardFocusManager() {
		if (isVirtualKeyboard()) {
			originalKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			focusManager = new POSKeyboardFocusManager();
			KeyboardFocusManager.setCurrentKeyboardFocusManager(focusManager);
		}
	}

	@Override
	public void init(int WindowNo, FormFrame frame) {
		this.frame = frame.getCFrame();
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.frame.setResizable(true);
		//	
		logger.info("init - SalesRep_ID=" + Env.getAD_User_ID(getCtx()));
		setWindowNo(WindowNo);
		frame.setJMenuBar(null);

		if (!loadPOS())
		{

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					CFrame closeFrame = frame.getCFrame();
					closeFrame.dispatchEvent(new WindowEvent(closeFrame, WindowEvent.WINDOW_CLOSING));
					dispose();
				}
			});
			return;
		}


		userPinListener = new POSUserPinListener(this);
		//Delay 5 seconds by default
		userPinTimer = new javax.swing.Timer((getPINEntryTimeout() + 10)  * 1000, userPinListener);
		if (isPresentElectronicScales()) {
			scalesListener = new POSScalesListener(this);
			scalesTimer = new javax.swing.Timer(400, scalesListener);
		}
		isCorrectUserPin = null;

		settingKeyboardFocusManager();

		if (getM_POS() == null) {
			if (this.frame != null)
				this.frame.dispose();
			return;
		}
		//
		try {
			if (!dynInit()) {
				dispose();
				return;
			}
			//	Add to frame
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		}
		catch (AdempierePOSException exception)
		{
			ADialog.error(getWindowNo(), this.frame, exception.getLocalizedMessage());
			dispose();
			return;
		}

		logger.config( "PosPanel.init - " + mainPanel.getPreferredSize());
		
		if (getAutoLogoutDelay() > 0 && logoutTimer == null) {
			logoutTimer = new javax.swing.Timer(1000,
					new ActionListener() {

				PointerInfo pi = null;
				long lastMouseMove  = System.currentTimeMillis();
				long lastKeyboardEvent = System.currentTimeMillis();
				public void actionPerformed(ActionEvent actionEvent) {
					try {
						long now = actionEvent.getWhen();
						PointerInfo newPi = MouseInfo.getPointerInfo();
						// mouse moved
						if (newPi != null && pi != null
								&& !pi.getLocation().equals(newPi.getLocation())) {
							lastMouseMove = now;
						}
						pi = newPi;

						if (isVirtualKeyboard())
							lastKeyboardEvent = focusManager.getLastWhen();
						else
							lastKeyboardEvent = 0;

						if (getAutoLogoutDelay() * 1000
								< now - Math.max(lastKeyboardEvent, lastMouseMove)) {
							//	new PosLogin(this);
						}
					} catch (AdempiereException exception) {
							ADialog.error(getWindowNo(), getFrame() , exception.getLocalizedMessage());
					}
				}
			});
			logoutTimer.start();
		}
		if (isVirtualKeyboard())
			focusManager.start();
	}
	
	/**
	 * Get Main Frame
	 * @return
	 * @return CFrame
	 */
	public CFrame getFrame() {
		return frame;
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
	 * Get Plain font
	 * @return
	 * @return Font
	 */
	public Font getPlainFont() {
		return plainFont;
	}
	
	/**
	 * Get big Font
	 * @return
	 * @return Font
	 */
	public Font getBigFont() {
		return bigFont;
	}
	
	/**
	 * Get Big Plain
	 * @return
	 * @return Font
	 */
	public Font getBigPlainFont() {
		return bigPlainFont;
	}
	
	/**
	 * Get Field Lenght
	 * @return
	 * @return int
	 */
	public int getFieldHeight() {
		return fieldHeight;
	}
	
	/**
	 * Get Button Size
	 * @return
	 * @return int
	 */
	public int getButtonSize() {
		return buttonSize;
	}
	
	/**
	 * Resize Split Panel
	 * @return void
	 */
	public void autoSize() {
		dividerPane.setResizeWeight(.6d);
	}

	/**
	 *
	 * @return
     */
	public Dimension getSize() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	}

	/**
	 * Load POS
	 * @return String
	 */
	private boolean loadPOS() {
		int salesRep_ID = Env.getAD_User_ID(getCtx());
		setPOS(salesRep_ID);

		if(getM_POS() != null) {
			validLocator();
			return true;
		}
		//	Select POS
		int orgId = Env.getAD_Org_ID(getCtx());
		String msg = Msg.getMsg(getCtx(), "SelectPOS");
		String title = Env.getHeader(getCtx(), getWindowNo());
		Object selection = JOptionPane.showInputDialog(frame, msg, title,
				JOptionPane.QUESTION_MESSAGE, null, getPOSByOrganization(orgId).toArray(), null);

		if (selection != null) {
			setM_POS((MPOS)selection);
			validLocator();
		} else {
			return false;
		}
		//	
		return true;
	}
	
	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit() {
		frame.setTitle("Adempiere POS: " + getPOSName());
		//	Create Sub Panels
		leftPanel = new CPanel(new GridBagLayout());
		actionPanel = new POSActionPanel(this);
		infoProductPanel = new POSInfoProduct(this);
		quantityPanel = new POSQuantityPanel(this);

		leftPanel.add(actionPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0
				,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		leftPanel.add(infoProductPanel, new GridBagConstraints(0, 1, 1, 1, 1, 0
				,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		leftPanel.add(quantityPanel, new GridBagConstraints(0, 2, 1, 1, 1, 0
				,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0 , 10 , 0), 0, 0));

		//
		orderLinePanel = new POSOrderLinePanel(this);
		leftPanel.add(orderLinePanel, new GridBagConstraints(0, 4, 1, 1, 1, 1
				,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		documentPanel = new POSDocumentPanel(this);
		dividerPane.add(leftPanel, JSplitPane.LEFT);
		dividerPane.add(documentPanel, JSplitPane.RIGHT);

		statusBar.setInfo("");
		//	Seek to last
		if(hasRecord())
			lastRecord();
		refreshPanel();
		return true;
	}	//	dynInit
	
	/**
	 * Add or replace order line
	 * @param productId
	 * @param qtyOrdered
	 * @return void
	 */
	public void addOrUpdateLine(int productId, BigDecimal qtyOrdered) throws Exception{
		//	Create Order if none exists
		if (!hasOrder()) {
			newOrder();
		}
		//	
		String lineError = addOrUpdate(productId, qtyOrdered);
		if (lineError != null) {
			logger.warning("POS Error " + lineError);
			ADialog.error(getWindowNo(),
					mainPanel, Msg.parseTranslation(ctx, lineError));
		}
		refreshPanel();
	}

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose() {
		keyboards.clear();
		keyboards = null;
		if ( logoutTimer != null )
			logoutTimer.stop();
		logoutTimer = null;

		if ( userPinTimer != null )
			userPinTimer.stop();
		userPinTimer = null;

		if ( scalesTimer != null )
			scalesTimer.stop();
		scalesTimer = null;

		if (isVirtualKeyboard()) {
			if (focusManager != null)
				focusManager.stop();
			focusManager = null;
			KeyboardFocusManager.setCurrentKeyboardFocusManager(originalKeyboardFocusManager);
		}
		//
		if (actionPanel != null)
			actionPanel.dispose();
		actionPanel = null;
		if (orderLinePanel != null) {
			// if ( m_order != null )
			// 	m_order.deleteOrder();
			orderLinePanel.dispose();
		}
		orderLinePanel = null;
		if (documentPanel != null)
			documentPanel.dispose();
		documentPanel = null;
		if (frame != null)
			frame.dispose();
		frame = null;
	}	//	dispose
	
	/**
	 * Get Keyboard
	 */
	public POSKeyboard getKeyboard(int keyLayoutId) {
		if ( keyboards.containsKey(keyLayoutId) )
			return keyboards.get(keyLayoutId);
		else if (keyLayoutId > 0 ){
			POSKeyboard keyboard = new POSKeyboard(mainPanel, keyLayoutId);
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
	 * New Order
	 * @return void
	 */
	public void newOrder() throws Exception{
		newOrder(0);
		infoProductPanel.resetValues();
		quantityPanel.resetPanel();
		getMainFocus();
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
		}
		quantityPanel.refreshPanel();
	}
	
	/**
	 * Refresh Product Info
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		infoProductPanel.refreshProduct(key , getQty() , getM_PriceList_ID() , getC_BPartner_ID());
	}
	
	/**
	 * Refresh Product Info
	 * @param productId
	 * @return void
	 */
	public void refreshProductInfo(int productId) {
		infoProductPanel.refreshProduct( productId , getQty() , getM_PriceList_ID() , getC_BPartner_ID());
	}
	
	/**
	 * Refresh Header
	 * @return void
	 */
	public void refreshHeader() {
		reloadOrder();
		actionPanel.refreshPanel();
		documentPanel.refreshPanel();
	}

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		quantityPanel.changeViewPanel();
		orderLinePanel.changeViewPanel();
	}

	/**
	 * Update Line Table
	 */
	public void updateLineTable() {
		orderLinePanel.updateLine();
	}
	
	@Override
	public void moveUp() {
		orderLinePanel.moveUp();
	}

	@Override
	public void moveDown() {
		orderLinePanel.moveDown();
	}

	public StatusBar getStatusBar()
	{
		return statusBar;
	}

	public void addStatusBarInfo(String info)
	{
		statusBarInfo = statusBarInfo + " " + info + " ";
		getStatusBar().setStatusLine(statusBarInfo);
	}

	/**
	 * return User Pin Listener
	 * @return
     */
	public ActionListener getUserPinListener()
	{
		return userPinListener;
	}

	/**
	 * return Electronic Scales Listener
	 * @return
	 */
	public POSScalesListener getScalesListener()
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
			userPinTimer.restart();
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
			POSUserPinDialog.show(this);

		if (isCorrectUserPin == null || !isCorrectUserPin)
			throw new AdempiereException("@Supervisor_ID@: @UserPin@ @IsInvalid@.");

		return isCorrectUserPin;
	}

	public void showCollectPayment()
	{
		documentPanel.getCollectPayment().showPanel();
	}

	public void showScales()
	{
		documentPanel.getScalesPanel().showPanel();
	}

	public void hideScales()
	{
		documentPanel.getScalesPanel().hidePanel();
	}

	public void setScalesMeasure(String measure)
	{
		documentPanel.getScalesPanel().setMeasure(measure);
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
		actionPanel.resetPanel();
		orderLinePanel.disableTable();
	}
	
	public void restoreTable() {
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
		super.setQty(qty);
		quantityPanel.setQuantity(getQty());
	}

	public void updateProductPlaceholder(String name)
	{
		actionPanel.updateProductPlaceholder(name);
	}

	/**
	 * Show a busy dialog or hide it
	 * @param busy
	 */
	public void setBusy(boolean busy) {
		if(busy) {
//			waiting = new Waiting(getFrame(), Msg.parseTranslation(getCtx(), "@Processing@"), false, 5);
//			waiting.toFront();
			getFrame().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		} else {
//			if(waiting != null) {
//				waiting.dispose();
//				waiting = null;
//			}
			getFrame().getContentPane().setCursor(Cursor.getDefaultCursor());
		}
	}

	/**
	 * Show a message on busy dialog if it exists
	 * @param message
	 */
	public void setBusyMessage(String message) {
//		if(waiting != null)
//			waiting.setText(message);
	}
	
	/**
	 * Gerify if is busy
	 * @return
	 */
	public boolean isBusy() {
		return waiting != null;
	}
}