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

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MPOS;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.event.EventListener;

/**
 * 
 * @author Raul Muñoz 20/03/2015 
 */
public abstract class WPosQuery extends Window implements MouseListener, ListSelectionListener, EventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6318532272101473014L;
	
	protected Properties 		p_ctx;
	/** POS Panel							*/
	protected WPosBasePanel 	p_posPanel = null;
	/** Order 								*/
	protected WSubOrder 		p_order = null;
	/**	Underlying POS Model				*/
	protected MPOS 				p_pos = null;
	/** The Table					*/
	protected WListbox 			m_table;
	protected Panel 			northPanel;
	protected ConfirmPanel	 	confirm;
	protected Button 			f_up;
	protected Button 			f_down;
	/**	Logger			*/
	protected static CLogger log = CLogger.getCLogger(QueryProduct.class);

	public WPosQuery() throws HeadlessException {
		super();
	}

	protected abstract void close();

	public abstract void reset();


	protected abstract void init();
	protected abstract void enableButtons();

	/**
	 * 	Constructor
	 */
	public WPosQuery (WPosBasePanel posPanel, WSubOrder order)
	{
		super();
		p_posPanel = posPanel;
		p_order = order;
		p_pos = posPanel.p_pos;
		p_ctx = p_pos.getCtx();
		this.setAttribute("mode", "modal");
		this.setBorder("normal");
		this.setWidth("850px");
		this.setHeight("500px");
		this.setContentStyle("overflow: auto");
		this.setSizable(true);      
        this.setMaximizable(true); 
        this.setVisible(true);
        
		init();
	}	//	PosQueryBPartner
	
	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e)
	{
		//  Single click with selected row => exit
		if (e.getClickCount() > 0 && m_table.getSelectedRow() != -1)
		{
			enableButtons();
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
	public void valueChanged (ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			return;
		enableButtons();
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