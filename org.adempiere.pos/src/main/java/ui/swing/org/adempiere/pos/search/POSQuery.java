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
package org.adempiere.pos.search;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.AEnv;
import org.compiere.apps.AppsAction;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.PO;
import org.adempiere.pos.POSTable;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * 
 * Abstract dialog
 * 
 *  @author former authors from Java POS
 *  @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  @author Dixon Martinez, dmartinez@erpcya.com, ERPCYA http://www.erpcya.com
 *  @author victor.perez@e-evolution.com , http://www.e-evolution.com
 *  @version $Id: PosQuery.java,v 2.0 2015/09/01 00:00:00 
 */
public abstract class POSQuery extends CDialog
	implements MouseListener, ActionListener, KeyListener ,
		VetoableChangeListener, POSQueryInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6379099059318219432L;
	
	/**	Context			*/
	protected Properties 	ctx;
	/** POS Panel		*/
	protected VPOS 			posPanel = null;
	/** The Table		*/
	protected POSTable 		posTable;
	/**	New Action		*/
	private CButton 		buttonNew;
	/**	Edit Action		*/
	private CButton 		buttonEdit;
	/**	Refresh Action	*/
	private CButton 		buttonReset;
	/**	Refresh Action	*/
	private CButton 		buttonRefresh;
	/**	Ok Action		*/
	private CButton 		buttonOk;
	/**	Cancel Action	*/
	private CButton 		buttonCancel;
	/**	North Panel		*/
	protected CPanel 		parameterPanel;
	/**	Center Scroll	*/
	private CScrollPane 	centerScroll;
	/**	Confirm Panel	*/
	private CPanel 			confirmPanel;
	/**	Main Panel		*/
	private CPanel 			mainPanel;

	/** Status Bar 					*/
	private StatusBar statusBar;
	
	/**	Logger			*/
	protected static CLogger logger = CLogger.getCLogger(QueryOrderHistory.class);
	/**	Listener		*/
	private POSQueryListener listener;
	/**	Default Width	*/
	private final int		BUTTON_WIDTH 	= 50;
	/**	Default Height		*/
	private final int		BUTTON_HEIGHT 	= 50;
	/** Status bar info				*/
	private String 			statusBarInfo;
	
	
	public POSQuery() throws HeadlessException {
		super();
	}
	
	/**
	 * Init Main Panel
	 * @return void
	 */
	private void initMainPanel() {
		statusBarInfo = "";
		//	Instance Panel
		setLayout(new GridBagLayout());
		//	For Table
		posTable = new POSTable();
		mainPanel = new CPanel(new GridBagLayout());
		mainPanel.setFocusCycleRoot(true);
		statusBar = new StatusBar();

		posTable.growScrollbars();

		parameterPanel = new CPanel(new GridBagLayout());
		centerScroll = new CScrollPane(posTable);
		confirmPanel = new CPanel(new GridBagLayout());
		//	
		centerScroll.setPreferredSize(new Dimension(800, 400));
		//	
		
		//	Create Buttons
		buttonNew = createButtonAction("New", KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		buttonEdit = createButtonAction("Edit", KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		buttonReset = createButtonAction("Reset", KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		buttonRefresh = createButtonAction("Refresh", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		buttonCancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		buttonOk = createButtonAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		//	
		confirmPanel.add(buttonNew, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		confirmPanel.add(buttonEdit, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		confirmPanel.add(buttonReset, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		confirmPanel.add(buttonRefresh, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		confirmPanel.add(buttonOk, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		confirmPanel.add(buttonCancel, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		
		//	Add To Main Panel
		mainPanel.add(parameterPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		mainPanel.add(centerScroll, new GridBagConstraints(0, 1, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		mainPanel.add(confirmPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		mainPanel.add(statusBar, new GridBagConstraints(0, 3, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

		//	Add Main Panel to Content
		getContentPane().add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	//	Visible New and Edit
		buttonNew.setVisible(false);
		buttonEdit.setVisible(false);
		// Enable Button Edit
		buttonEdit.setEnabled(false);
		//	Add Listener
		posTable.addMouseListener(this);
		posTable.addKeyListener(this);
	}
	
	/**
	 * Add Listener
	 * @param listener
	 * @return void
	 */
	public void addOptionListener(POSQueryListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Fire Ok Action
	 */
	public void setOkAction() {
		close();
		//	Fire
		if(listener != null) {
			listener.okAction(this);
		}
	}
	
	/**
	 * Fire Cancel Action
	 */
	public void setCancelAction() {
		//	Fire
		if(listener != null) {
			listener.cancelAction(this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info(e.getActionCommand());
		if (buttonNew.equals(e.getSource())) {
			newAction();
			dispose();
			return;
		}if (buttonEdit.equals(e.getSource())) {
			editAction();
			dispose();
			return;
		} if (buttonRefresh.equals(e.getSource())) {
			if(posTable == null)
				return;
			//	Refresh
			refresh();
			posTable.requestFocus();
			return;
		} else if (buttonReset.equals(e.getSource())) {
			reset();
			return;
		} else if(buttonCancel.equals(e.getSource())) {
			cancel();
			setCancelAction();
		} else if (buttonOk.equals(e.getSource())) {
			select();
			setOkAction();
		} else if(e.getSource() instanceof POSTextField
				|| e.getSource() instanceof CCheckBox) {
			if(posTable == null)
				return;
			//	Refresh
			refresh();
		}
	}
	
	@Override
	public void vetoableChange (PropertyChangeEvent e) {
		logger.info(e.getPropertyName());
		if(e.getSource() instanceof VDate
				|| e.getSource() instanceof VNumber
				|| e.getSource() instanceof CComboBox){
			if(posTable == null)
				return;
			//	Refresh
			refresh();
		}
	}

	/**
	 * Set Visible the new button
	 * @return void
	 */
	protected void addNewAction() {
		buttonNew.setVisible(true);
		buttonEdit.setVisible(true);
	}
	
	/**
	 * Close Window
	 * @return void
	 */
	protected abstract void close();

	/**
	 * Reset Panel
	 * @return void
	 */
	public abstract void reset();
	
	/**
	 * Refresh Panel
	 * @return void
	 */
	public abstract void refresh();

	/**
	 * Init Panel
	 * @return void
	 */
	protected abstract void init();
	
	/**
	 * Select Row
	 * @return void
	 */
	protected abstract void select();
	
	/**
	 * Cancel Search
	 * @return void
	 */
	protected abstract void cancel();
	
	/**
	 * Set Result from search
	 * @param results
	 * @return void
	 */
	protected abstract void setResults(PO[] results);
	
	/**
	 * For New action
	 * @return void
	 */
	protected void newAction() {
		
	}
	
	/**
	 * For Edit action
	 * @return void
	 */
	protected void editAction() {
		
	}
	
	@Override
	public void dispose() {
		removeAll();
		parameterPanel = null;
		centerScroll = null;
		confirmPanel = null;
		posTable = null;
		super.dispose();
	}

	/**
	 * 	Constructor
	 */
	public POSQuery (VPOS posPanel) {
		super(Env.getWindow(posPanel.getWindowNo()), true);
		this.posPanel = posPanel;
		ctx = posPanel.getCtx();
		initMainPanel();
		init();
		pack();
		AEnv.positionCenterScreen(this);
	}	//	PosQuery

	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e) {
		//  Single click with selected row => exit
		if (e.getClickCount() > 1 && posTable.getSelectedRow() != -1) {
			select();
			close();
		}
	}   //  mouseClicked

	@Override
	public void mouseEntered (MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited (MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed (MouseEvent e) {
		//	Add support search Business Partner
		//  Single click with selected row => exit
		if(posTable.getSelectedRow() != -1)
			buttonEdit.setEnabled(true);
		
		if (e.getClickCount() > 1 
				&& posTable.getSelectedRow() != -1)	{
			
			select();
			setOkAction();
		}
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		
	}

	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected CButton createButtonAction(String action, KeyStroke accelerator) {
		String acceleratorText = "";
		if (action != null && accelerator != null) {
			int modifiers = accelerator.getModifiers();
			if (modifiers >= 0) {
				acceleratorText = "(" + KeyEvent.getKeyModifiersText(modifiers);
				//acceleratorText += "+";
			}
			acceleratorText += KeyEvent.getKeyText(accelerator.getKeyCode());
			addStatusBarInfo(action + acceleratorText + ")");
		}

		AppsAction act = new AppsAction(action, accelerator, false);
		act.setDelegate(this);
		CButton button = (CButton)act.getButton();
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.setMinimumSize(getPreferredSize());
		button.setMaximumSize(getPreferredSize());
		button.setFocusable(false);
		return button;
	}	//	getButtonAction
	
	/**
	 * Show View
	 * @return void
	 */
	public void showView() {
		SwingUtilities.invokeLater(
				new Runnable() { 
					public void run() { 
						setVisible(true);
					} 
				} 
		);
	}

	public void addStatusBarInfo(String info)
	{
		statusBarInfo = statusBarInfo + " " + info + " ";
		statusBar.setStatusLine(statusBarInfo);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(	posTable.equals(e.getSource()) &&
			KeyEvent.VK_ENTER == e.getKeyCode() )
		{
			select();
			close();
			//	Fire
			if (listener != null) {
				listener.okAction(this);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}