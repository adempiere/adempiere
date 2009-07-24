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

package org.compiere.pos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.compiere.apps.AppsAction;
import org.compiere.model.MPOS;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;

/**
 *	POS Sub Panel Base Class.
 *	The Panel knows where to position itself in the POS Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @version $Id: PosSubPanel.java,v 1.3 2004/07/12 04:10:04 jjanke Exp $
 */
public abstract class PosSubPanel extends CPanel 
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
	public PosSubPanel (PosPanel posPanel)
	{
		super();
		p_posPanel = posPanel;
		p_pos = posPanel.p_pos;
		init();
	}	//	PosSubPanel
	
	/** POS Panel							*/
	protected PosPanel 				p_posPanel = null;
	/**	Underlying POS Model				*/
	protected MPOS					p_pos = null;
	/**	Position of SubPanel in Main		*/
	protected GridBagConstraints	p_position = null;
	/** Context								*/
	protected Properties			p_ctx = Env.getCtx();
	

	/** Button Width = 40			*/
	private static final int	WIDTH = 45;	
	/** Button Height = 40			*/
	private static final int	HEIGHT = 35;	
	/** Inset 1all					*/
	public static Insets 		INSETS1 = new Insets(1,1,1,1);	
	/** Inset 2all					*/
	public static Insets 		INSETS2 = new Insets(2,2,2,2);	
	
	/**
	 * 	Initialize
	 */
	protected abstract void init();
	
	
	/**
	 * 	Get Panel Position
	 */
	protected GridBagConstraints getGridBagConstraints()
	{
		if (p_position == null)
		{
			p_position = new GridBagConstraints();
			p_position.anchor = GridBagConstraints.NORTHWEST;
			p_position.fill = GridBagConstraints.BOTH;
			p_position.weightx = 0.1;
			p_position.weighty = 0.1;
		}
		return p_position;
	}	//	getGridBagConstraints
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		p_pos = null;
	}	//	dispose

	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected CButton createButtonAction (String action, KeyStroke accelerator)
	{
		AppsAction act = new AppsAction(action, accelerator, false);
		act.setDelegate(this);
		CButton button = (CButton)act.getButton();
		button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		button.setMinimumSize(getPreferredSize());
		button.setMaximumSize(getPreferredSize());
		button.setFocusable(false);
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
		button.setMinimumSize(getPreferredSize());
		button.setMaximumSize(getPreferredSize());
		button.setFocusable(false);
		return button;
	}	//	getButton

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
	}	//	actinPerformed

}	//	PosSubPanel
