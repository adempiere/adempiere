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

import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.KeyStroke;

import org.adempiere.pos.service.I_POSPanel;
import org.compiere.grid.ed.VNumber;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Button panel Up Down
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Victor Perez , victor.perez@e-evolution.com , e-Evolution http//www.e-evolution.com
 */
public class POSQuantityPanel extends POSSubPanel implements I_POSPanel, ActionListener {

	public POSQuantityPanel(VPOS posPanel) {
		super(posPanel);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8002370020007368736L;
	
	/** Buttons Controls  		*/
	private CButton 			f_bUp;
	private CButton 			f_bDelete;
	private CButton 			f_bDown;
	private CButton 			f_bPlus;
	private CButton 			f_bMinus;
	private VNumber		 		f_Price;
	private VNumber		 		f_Quantity;
	/**	Button Panel			*/
	private CPanel				v_ButtonPanel;
	/**	Padding					*/
	private int 				m_TopP;
	private int 				m_LeftP;
	private int 				m_BottonP;
	private int 				m_RightP;

	private final BigDecimal ACTION_QUANTITY    =  Env.ONE;
	
	@Override
	protected void init() {
		//	Content
		setLayout(new GridBagLayout());
		//	Button Panel
		v_ButtonPanel = new CPanel(new GridBagLayout());
		//	
		m_TopP = 0;
		m_LeftP = 1;
		m_BottonP = 0;
		m_RightP = 1;


		f_bDelete = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, Event.SHIFT_MASK));
		v_ButtonPanel.add(f_bDelete, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		f_bPlus = createButtonAction("Plus", null);
		v_ButtonPanel.add(f_bPlus, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		f_bMinus = createButtonAction("Minus", null);
		v_ButtonPanel.add(f_bMinus, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		f_bUp = createButtonAction("Previous", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		v_ButtonPanel.add(f_bUp, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		f_bDown = createButtonAction("Next", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		v_ButtonPanel.add(f_bDown, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		CLabel qtyLabel = new CLabel(Msg.translate(Env.getCtx(), "QtyOrdered"));
		v_ButtonPanel.add(qtyLabel, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		//
		f_Quantity = new VNumber();
		f_Quantity.addActionListener(this);
		f_Quantity.setFont(v_POSPanel.getFont());
		f_Quantity.setPreferredSize(new Dimension(100, 50));
		f_Quantity.setMinimumSize(new Dimension(100, 50));
		f_Quantity.setValue(Env.ZERO);
		v_ButtonPanel.add(f_Quantity, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		CLabel priceLabel = new CLabel(Msg.translate(Env.getCtx(), "PriceActual"));
		v_ButtonPanel.add(priceLabel, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));

		//
		f_Price = new VNumber();
		f_Price.setValue(Env.ZERO);
		f_Price.addActionListener(this);
		f_Price.setFont(v_POSPanel.getFont());
		f_Price.setPreferredSize(new Dimension(100, 50));
		f_Price.setMinimumSize(new Dimension(100, 50));
		v_ButtonPanel.add(f_Price, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		
		add(v_ButtonPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));

		changeStatus(false);
	}

	/**
	 * 	Distribute actions
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(f_bUp)){
			v_POSPanel.moveUp();
			return;
		}
		else if (e.getSource().equals(f_bDown)){
			v_POSPanel.moveDown();
			return;
		}
		if (e.getSource().equals(f_bMinus)){
			f_Quantity.setValue(((BigDecimal) f_Quantity.getValue()).subtract(ACTION_QUANTITY));
		}
		else if (e.getSource().equals(f_bPlus)){
			f_Quantity.setValue(((BigDecimal) f_Quantity.getValue()).add(ACTION_QUANTITY));
		}
		else if (e.getSource().equals(f_bDelete)){
			f_Quantity.setValue(0.0);
		}

		v_POSPanel.setQty((BigDecimal)f_Quantity.getValue());
		v_POSPanel.setPrice((BigDecimal)f_Price.getValue());
		v_POSPanel.updateLineTable();

	}

	@Override
	public void refreshPanel() {
		if(v_POSPanel.hasLines()){
			f_bDown.setEnabled(true);
			f_bUp.setEnabled(true);
		} else {
			f_bDown.setEnabled(false);
			f_bUp.setEnabled(false);
		}
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}

	@Override
	public String validatePanel() {
		return null;
	}

	/**
	 * Change Status 
	 * @param p_Status
	 */
	public void changeStatus(boolean p_Status) {
		f_Quantity.setEnabled(p_Status);
		f_Price.setEnabled(p_Status);
		f_bDelete.setEnabled(p_Status);
		f_bPlus.setEnabled(p_Status);
		f_bMinus.setEnabled(p_Status);
	}
	
	@Override
	public void changeViewPanel() {
		if(v_POSPanel.getQty().compareTo(Env.ZERO) == 0)
			changeStatus(false);
		else
			changeStatus(true);
		f_Quantity.setValue(v_POSPanel.getQty().doubleValue());
		f_Price.setValue(v_POSPanel.getPrice().doubleValue());
	}
}