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

package org.adempiere.pos;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.compiere.apps.AppsAction;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;

/**
 *	POS Sub Panel Base Class.
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li>Change Name
 *  
 */
public abstract class POSSubPanel extends CPanel 
	implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -158167614949876569L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSSubPanel (VPOS posPanel)
	{
		super();
		this.posPanel = posPanel;
		init();
	}	//	PosSubPanel
	
	/** POS Panel							*/
	protected VPOS 				posPanel;
	/** Context								*/
	protected Properties 		ctx = Env.getCtx();
	

	/** Button Width = 50			*/
	private static final int	WIDTH = 50;	
	/** Button Height = 50			*/
	private static final int	HEIGHT = 50;
	
	/**
	 * 	Initialize
	 */
	protected abstract void init();

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose() {
		//	
	}	//	dispose
	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected CButton createButtonAction (String action, KeyStroke accelerator)
	{
		String acceleratorText = "";
		if (action != null && accelerator != null) {

			if (accelerator != null) {
				int modifiers = accelerator.getModifiers();
				if (modifiers >= 0) {
					acceleratorText = "(" + KeyEvent.getKeyModifiersText(modifiers);
					//acceleratorText += "+";
				}
				acceleratorText += KeyEvent.getKeyText(accelerator.getKeyCode());
			}
			posPanel.addStatusBarInfo(action + acceleratorText + ")");
		}

		AppsAction act = new AppsAction(action , accelerator , acceleratorText , false);  //AppsAction(action, accelerator, false);
		act.setDelegate(this);
		CButton button = (CButton)act.getButton();
		button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		button.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		button.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		return button;
	}	//	getButtonAction
	
	/**
	 * 	Create Standard Button
	 *	@param text text
	 *	@return button
	 */
	protected CButton createButton (String text)
	{
	//	if (text.indexOf("<html>") == -1)
	//		text = "<html><h4>" + text + "</h4></html>";
		CButton button = new CButton(text);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		button.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		button.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		button.setFocusable(false);
		return button;
	}	//	getButton

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
	}	//	actionPerformed

}	//	PosSubPanel
