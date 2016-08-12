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

package org.adempiere.pos.search;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.adempiere.pos.WPOS;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.compiere.apps.ConfirmPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.South;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public abstract class WPOSQuery extends Window implements POSQueryInterface, MouseListener, ListSelectionListener, EventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6318532272101473014L;
	
	/**
	 * *** Constructor ***
	 * @throws HeadlessException
	 */
	public WPOSQuery() throws HeadlessException {
		super();
	}

	/**
	 * 	Constructor
	 */
	public WPOSQuery (WPOS posPanel)
	{
		super();
		this.posPanel = posPanel;
		ctx = this.posPanel.getCtx();
		initMainPanel();
		this.setAttribute("mode", "modal");
		this.setTitle(Msg.getMsg(ctx, "Query"));
		this.setBorder("normal");
		this.setWidth("910px");
		this.setHeight("500px");
		this.setContentStyle("overflow: auto");
		this.setSizable(true);      
        this.setMaximizable(true); 
        this.setVisible(true);
        
		init();
	}	//	PosQueryBPartner
	
	protected Properties 		ctx;
	/** POS Panel					*/
	protected WPOS 				posPanel = null;
	/** The Table					*/
	protected WListbox 			posTable;
	/** Panel						*/
	protected Panel 			northPanel;
	/** Actions						*/
	protected ConfirmPanel	 	confirmPanel;
	/**	Refresh Action	*/
	private Button 				buttonRefresh;
	/**	Reset Action	*/
	private Button 				buttonReset;
	/**	Ok Action		*/
	private Button 				buttonOk;
	/**	Cancel Action	*/
	private Button 				buttonCancel;
	/**	New Action		*/
	private Button 				buttonNew;
	/**	Edit Action		*/
	public 	Button 				buttonEdit;
	public  Borderlayout 		mainLayout;
	private BusyDialog 		progressWindow;
	/**	Listener		*/
	private POSQueryListener listener;
	/**	Logger			*/
	protected static CLogger logger = CLogger.getCLogger(WPOSQuery.class);
	
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
	 * For New action
	 * @return void
	 */
	protected void newAction() {
		
	}
	
	/**
	 * Init Main Panel
	 * @return void
	 */
	private void initMainPanel() {
		//	Instance Panel
		//
		Panel buttonsPanel = new Panel();
		Rows rows = null;
		Row row = null;
		South north = new South();

		northPanel = new Panel();
		mainLayout = new Borderlayout();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		Grid productLayout = GridFactory.newGridLayout();
		northPanel.appendChild(productLayout);
		rows = productLayout.newRows();
		row = rows.newRow();
		buttonsPanel.setAlign("Center");
		row.setHeight("65px");
		row.setSpans("6");

		buttonNew = createButtonAction("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		buttonNew.setId("New");
		buttonsPanel.appendChild(buttonNew);
		buttonNew.addActionListener(this);

		buttonEdit = createButtonAction("Edit", KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		buttonEdit.setId("Edit");
		buttonsPanel.appendChild(buttonEdit);
		buttonEdit.addActionListener(this);
		
		buttonReset = createButtonAction("Reset", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		buttonsPanel.appendChild(buttonReset);
		buttonReset.setId("Reset");
		buttonRefresh = createButtonAction("Refresh", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		buttonsPanel.appendChild(buttonRefresh);
		buttonRefresh.setId("Refresh");
		
		
		buttonOk = createButtonAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		buttonsPanel.appendChild(buttonOk);
		buttonOk.setId("Ok");
		buttonCancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		buttonsPanel.appendChild(buttonCancel);
		buttonCancel.setId("Cancel");
		buttonOk.setTooltiptext(Msg.translate(ctx, "Ok"));
		buttonCancel.setTooltiptext(Msg.translate(ctx, "Cancel"));
		buttonOk.setTooltiptext(Msg.translate(ctx, "Ok"));
		buttonCancel.setTooltiptext(Msg.translate(ctx, "Cancel"));
		buttonNew.setTooltiptext(Msg.translate(ctx, "New"));
		buttonEdit.setTooltiptext(Msg.translate(ctx, "Edit"));
		buttonRefresh.setTooltiptext(Msg.translate(ctx, "Refresh"));
		row.appendChild(buttonsPanel);
		//	Center
		posTable = new WListbox();
		
		posTable.addActionListener(this);
		//	Visible New
		buttonNew.setVisible(false);
		buttonEdit.setVisible(false);
		buttonEdit.setEnabled(false);
	}

	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e)
	{
		//  Single click with selected row => exit
		if (e.getClickCount() > 0 && posTable.getSelectedRow() != -1) {
			select();
			close();
		}
	}   //  mouseClicked
	
    
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	public void mousePressed (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	
	/**
	 * 	Table selection changed
	 *	@param e event
	 */
	public void valueChanged (ListSelectionEvent e)	{
		if (e.getValueIsAdjusting())
			return;
		select();
	}	//	valueChanged
	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected Button createButtonAction(String action, KeyStroke accelerator) {
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setWidth("50px");
		button.setHeight("50px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	
	/**
	 *  Lock User Interface.
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI () {
		progressWindow = new BusyDialog();
		progressWindow.setPage(this.getPage());
		progressWindow.doHighlighted();
	}   //  lockUI
	
	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 */
	public void unlockUI ()	{		
		if (progressWindow != null) {
			progressWindow.dispose();
			progressWindow = null;
		}
	}
	
	public void okAction() {
		//	Fire
		if(listener != null) {
			listener.okAction(this);
		}
	}
	/**
	 * Add Listener
	 * @param listener
	 * @return void
	 */
	public void addOptionListener(POSQueryListener listener) {
		this.listener = listener;
	}
}