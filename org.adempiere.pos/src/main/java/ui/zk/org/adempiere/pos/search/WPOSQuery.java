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
 */
public abstract class WPOSQuery extends Window implements  MouseListener, ListSelectionListener, EventListener{

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
		v_POSPanel = posPanel;
		p_ctx = v_POSPanel.getCtx();
		initMainPanel();
		this.setAttribute("mode", "modal");
		this.setTitle(Msg.getMsg(p_ctx, "Query"));
		this.setBorder("normal");
		this.setWidth("910px");
		this.setHeight("500px");
		this.setContentStyle("overflow: auto");
		this.setSizable(true);      
        this.setMaximizable(true); 
        this.setVisible(true);
        
		init();
	}	//	PosQueryBPartner
	
	protected Properties 		p_ctx;
	/** POS Panel					*/
	protected WPOS 				v_POSPanel = null;
	/** The Table					*/
	protected WListbox 			m_table;
	/** Panel						*/
	protected Panel 			northPanel;
	/** Actions						*/
	protected ConfirmPanel	 	confirmPanel;
	private Button	 			f_Refresh;
	private Button	 			f_Reset;
	private Button	 			f_Ok;
	private Button	 			f_Cancel;
	private Button 				f_New;
	public 	Button 				f_Edit;
	public  Borderlayout 		mainLayout;
	/**	Logger			*/
	protected static CLogger log = CLogger.getCLogger(WPOSQuery.class);
	
	/**
	 * Set Visible the new button
	 * @return void
	 */
	protected void addNewAction() {
		f_New.setVisible(true);
		f_Edit.setVisible(true);
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

		f_New = createButtonAction("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		f_New.setId("New");
		buttonsPanel.appendChild(f_New);
		f_New.addActionListener(this);

		f_Edit = createButtonAction("Edit", KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		f_Edit.setId("Edit");
		buttonsPanel.appendChild(f_Edit);
		f_Edit.addActionListener(this);
		
		f_Reset = createButtonAction("Reset", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		buttonsPanel.appendChild(f_Reset);
		f_Reset.setId("Reset");
		f_Refresh = createButtonAction("Refresh", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		buttonsPanel.appendChild(f_Refresh);
		f_Refresh.setId("Refresh");
		
		
		f_Ok = createButtonAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		buttonsPanel.appendChild(f_Ok);
		f_Ok.setId("Ok");
		f_Cancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		buttonsPanel.appendChild(f_Cancel);
		f_Cancel.setId("Cancel");
		f_Ok.setTooltiptext(Msg.translate(p_ctx, "Ok"));
		f_Cancel.setTooltiptext(Msg.translate(p_ctx, "Cancel"));
		f_Ok.setTooltiptext(Msg.translate(p_ctx, "Ok"));
		f_Cancel.setTooltiptext(Msg.translate(p_ctx, "Cancel"));
		f_New.setTooltiptext(Msg.translate(p_ctx, "New"));
		f_Edit.setTooltiptext(Msg.translate(p_ctx, "Edit"));
		f_Refresh.setTooltiptext(Msg.translate(p_ctx, "Refresh"));
		row.appendChild(buttonsPanel);
		//	Center
		m_table = new WListbox();
		
		m_table.addActionListener(this);
		//	Visible New
		f_New.setVisible(false);
		f_Edit.setVisible(false);
		f_Edit.setEnabled(false);
	}

	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e)
	{
		//  Single click with selected row => exit
		if (e.getClickCount() > 0 && m_table.getSelectedRow() != -1) {
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

	
}