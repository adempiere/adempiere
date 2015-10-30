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

import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.session.SessionManager;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;


/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com 
 */
public abstract class WPOSSubPanel extends Borderlayout 
	implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -158167614949876569L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSSubPanel (WPOS posPanel) {
		super();
		v_POSPanel = posPanel;
		init();
	}	//	PosSubPanel
	
	/** POS Panel							*/
	protected WPOS 					v_POSPanel;
	/** Context								*/
	protected Properties			m_ctx = Env.getCtx();
	

	/** Button Width = 55			*/
	private static final int	WIDTH = 55;	
	/** Button Height = 55			*/
	private static final int	HEIGHT = 55;
	
	/**
	 * 	Initialize
	 */
	protected abstract void init();
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose() {
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected Button createButtonAction (String action, KeyStroke accelerator)
	{
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setTooltiptext(Msg.translate(m_ctx, action));
		button.setWidth(WIDTH+"px");
		button.setHeight(HEIGHT+"px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected Button createButtonAction (String action, int m_OSK_KeyLayout_ID)
	{
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setTooltiptext(Msg.translate(m_ctx, action));
		button.setId(m_OSK_KeyLayout_ID+"");
		button.setWidth(WIDTH+"px");
		button.setHeight(HEIGHT+"px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	
	/**
	 * 	Create Standard Button
	 *	@param text text
	 *	@return button
	 */
	protected Button createButton (String text)
	{
	//	if (text.indexOf("<html>") == -1)
	//		text = "<html><h4>" + text + "</h4></html>";
		Button button = new Button(text);
		button.addActionListener(this);
//		button.setPreferredSize(new Dimension(WIDTH, HEIGHT));

//		button.setFocusable(false);
		return button;
	}	//	getButton

}	//	PosSubPanel
