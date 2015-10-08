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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.compiere.apps.AEnv;
import org.compiere.apps.AppsAction;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.PO;
import org.compiere.pos.PosTable;
import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
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
 *  @version $Id: PosQuery.java,v 2.0 2015/09/01 00:00:00 
 */
public abstract class POSQuery extends CDialog 
	implements MouseListener, ActionListener, VetoableChangeListener, WindowFocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6379099059318219432L;
	
	/**	Context			*/
	protected Properties 	m_ctx;
	/** POS Panel		*/
	protected VPOS 			v_POSPanel = null;
	/** The Table		*/
	protected PosTable 		m_table;
	/**	New Action		*/
	private CButton 		f_New;
	/**	Refresh Action	*/
	private CButton 		f_Reset;
	/**	Refresh Action	*/
	private CButton 		f_Refresh;
	/**	Ok Action		*/
	private CButton 		f_Ok;
	/**	Cancel Action	*/
	private CButton 		f_Cancel;
	/**	North Panel		*/
	protected CPanel 		v_ParameterPanel;
	/**	Center Scroll	*/
	private CScrollPane 	v_CenterScroll;
	/**	Confirm Panel	*/
	private CPanel 			v_ConfirmPanel;
	/**	Main Panel		*/
	private CPanel 			v_MainPanel;
	
	/**	Logger			*/
	protected static CLogger log = CLogger.getCLogger(QueryProduct.class);
	/**	Default Width	*/
	private final int		BUTTON_WIDTH 	= 50;
	/**	Default Height		*/
	private final int		BUTTON_HEIGHT 	= 50;
	
	
	public POSQuery() throws HeadlessException {
		super();
	}
	
	/**
	 * Init Main Panel
	 * @return void
	 */
	private void initMainPanel() {
		//	Instance Panel
		//	For Table
		m_table = new PosTable();
		v_MainPanel = new CPanel(new GridBagLayout());		
		v_ParameterPanel = new CPanel(new GridBagLayout());
		v_CenterScroll = new CScrollPane(m_table);
		v_ConfirmPanel = new CPanel(new GridBagLayout());
		//	
		v_CenterScroll.setPreferredSize(new Dimension(800, 400));
		//	
		
		//	Create Buttons
		f_New = createButtonAction("New", KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		f_Reset = createButtonAction("Reset", KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		f_Refresh = createButtonAction("Refresh", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		f_Cancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		f_Ok = createButtonAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		//	
		v_ConfirmPanel.add(f_New, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		v_ConfirmPanel.add(f_Reset, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		v_ConfirmPanel.add(f_Refresh, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		v_ConfirmPanel.add(f_Ok, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		v_ConfirmPanel.add(f_Cancel, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		
		//	Add To Main Panel
		v_MainPanel.add(v_ParameterPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		v_MainPanel.add(v_CenterScroll, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		v_MainPanel.add(v_ConfirmPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		//	Add Main Panel to Content
		getContentPane().add(v_MainPanel);
		//	Visible New
		f_New.setVisible(false);
		//	Add Listener
		m_table.addMouseListener(this);
//		m_table.getSelectionModel().addListSelectionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info(e.getActionCommand());
		if (f_New.equals(e.getSource())) {
			newAction();
			dispose();
			return;
		} if (f_Refresh.equals(e.getSource())) {
			refresh();
			return;
		} else if (f_Reset.equals(e.getSource())) {
			reset();
			return;
		} else if(f_Cancel.equals(e.getSource())) {
			cancel();
		} else if (f_Ok.equals(e.getSource())) {
			select();
			close();
		} else if(e.getSource() instanceof POSTextField
				|| e.getSource() instanceof CCheckBox) {
			refresh();
		}
	}
	
	@Override
	public void vetoableChange (PropertyChangeEvent e) {
		log.info(e.getPropertyName());
		if(e.getSource() instanceof VDate
				|| e.getSource() instanceof VNumber
				|| e.getSource() instanceof CComboBox){
			refresh();
		}
	}

	/**
	 * Set Visible the new button
	 * @return void
	 */
	protected void addNewAction() {
		f_New.setVisible(true);
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
	
	@Override
	public void dispose() {
		removeAll();
		v_ParameterPanel = null;
		v_CenterScroll = null;
		v_ConfirmPanel = null;
		m_table = null;
		super.dispose();
	}

	/**
	 * 	Constructor
	 */
	public POSQuery (VPOS posPanel) {
		super(Env.getWindow(posPanel.getWindowNo()), true);
		v_POSPanel = posPanel;
		m_ctx = posPanel.getCtx();
		initMainPanel();
		init();
		pack();
		addWindowFocusListener(this);
		AEnv.positionCenterScreen(this);
	}	//	PosQuery

	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e) {
		//  Single click with selected row => exit
		if (e.getClickCount() > 1 && m_table.getSelectedRow() != -1) {
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
		if (e.getClickCount() > 1 
				&& m_table.getSelectedRow() != -1)	{
			select();
			close();
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
		AppsAction act = new AppsAction(action, accelerator, false);
		act.setDelegate(this);
		CButton button = (CButton)act.getButton();
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.setMinimumSize(getPreferredSize());
		button.setMaximumSize(getPreferredSize());
		button.setFocusable(false);
		return button;
	}	//	getButtonAction

	@Override
	public void windowGainedFocus(WindowEvent e) {
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		cancel();
	}
	
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
}