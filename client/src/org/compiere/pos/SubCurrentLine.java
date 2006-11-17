/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.pos;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import javax.swing.*;
import javax.swing.border.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import java.util.logging.*;
import org.compiere.util.*;


/**
 *	POS Current Line Sub Panel
 *	
 *  @author Jorg Janke
 *  @version $Id: SubCurrentLine.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class SubCurrentLine extends PosSubPanel implements ActionListener
{
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public SubCurrentLine (PosPanel posPanel)
	{
		super (posPanel);
	}	//	PosSubCurrentLine
	
	private CButton f_new;
	private CButton f_reset;
	private CButton f_plus;
	private CButton f_minus;
	
	private CLabel 		f_currency;
	private VNumber 	f_price;
	private CLabel 		f_uom;
	private VNumber 	f_quantity;
	
	private MOrder		m_order = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubCurrentLine.class);
	
	/**
	 * 	Initialize
	 */
	public void init()
	{
		//	Title
		TitledBorder border = new TitledBorder(Msg.getMsg(Env.getCtx(), "CurrentLine"));
		setBorder(border);
		
		//	Content
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = INSETS2;
		gbc.gridy = 0;
		//	--
		f_new = createButtonAction("New", KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK));
		gbc.gridx = 0;
		add (f_new, gbc);
		//
		f_reset = createButtonAction("Reset", null);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add (f_reset, gbc);
		//
		f_currency = new CLabel("---");
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = .1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add (f_currency, gbc);
		//
		f_price = new VNumber("PriceActual", false, false, true, DisplayType.Amount, 
			Msg.translate(Env.getCtx(), "PriceActual"));
		f_price.addActionListener(this);
		f_price.setColumns(10, 25);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		add (f_price, gbc);
		setPrice(Env.ZERO);
		//	--
		f_uom = new CLabel("--");
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = .1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add (f_uom, gbc);
		//
		f_minus = createButtonAction("Minus", null);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		add (f_minus, gbc);
		//
		f_quantity = new VNumber("QtyOrdered", false, false, true, DisplayType.Quantity, 
			Msg.translate(Env.getCtx(), "QtyOrdered"));
		f_quantity.addActionListener(this);
		f_quantity.setColumns(5, 25);
		add (f_quantity, gbc);
		setQty (Env.ONE);
		//
		f_plus = createButtonAction("Plus", null);
		add (f_plus, gbc);
	}	//	init

	/**
	 * 	Get Panel Position
	 */
	public GridBagConstraints getGridBagConstraints()
	{
		GridBagConstraints gbc = super.getGridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		return gbc;
	}	//	getGridBagConstraints
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		super.dispose();
	}	//	dispose

	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "SubCurrentLine - actionPerformed: " + action);
		//	New / Reset
		if (action.equals("New"))
			saveLine();
		else if (action.equals("Reset"))
			newLine();
		//	Plus
		else if (action.equals("Plus"))
			f_quantity.plus();
		//	Minus
		else if (action.equals("Minus"))
			f_quantity.minus(1);
		//	VNumber
		else if (e.getSource() == f_price)
			f_price.setValue(f_price.getValue());
		else if (e.getSource() == f_quantity)
			f_quantity.setValue(f_quantity.getValue());
	}	//	actionPerformed
	
	
	/**************************************************************************
	 * 	Set Currency 
	 *	@param currency currency
	 */
	public void setCurrency (String currency)
	{
		if (currency == null)
			f_currency.setText("---");
		else
			f_currency.setText(currency);
	}	//	setCurrency

	/**
	 * 	Set UOM 
	 *	@param UOM UOM
	 */
	public void setUOM (String UOM)
	{
		if (UOM == null)
			f_uom.setText("--");
		else
			f_uom.setText(UOM);
	}	//	setUOM
	
	/**
	 * 	Set Price 
	 *	@param price price
	 */
	public void setPrice (BigDecimal price)
	{
		if (price == null)
			price = Env.ZERO;
		f_price.setValue(price);
		boolean rw = Env.ZERO.compareTo(price) == 0
			|| p_pos.isModifyPrice();
		f_price.setReadWrite(rw);
	}	//	setPrice

	/**
	 * 	Get Price 
	 *	@return price
	 */
	public BigDecimal getPrice ()
	{
		return (BigDecimal)f_price.getValue();
	}	//	getPrice

	/**
	 * 	Set Qty 
	 *	@param qty qty
	 */
	public void setQty (BigDecimal qty)
	{
		f_quantity.setValue(qty);
	}	//	setPrice

	/**
	 * 	Get Qty
	 *	@return qty
	 */
	public BigDecimal getQty ()
	{
		return (BigDecimal)f_quantity.getValue();
	}	//	getPrice

	
	/**************************************************************************
	 * 	New Line
	 */
	public void newLine()
	{
		p_posPanel.f_product.setM_Product_ID(0);
		setQty(Env.ONE);
		setPrice(Env.ZERO);
		p_posPanel.f_lines.updateTable(m_order);
	}	//	newLine
	
	/**
	 * 	Save Line
	 *	@return true if saved
	 */
	public boolean saveLine()
	{
		MProduct product = p_posPanel.f_product.getProduct(); 
		if (product == null)
			return false;
		BigDecimal QtyOrdered = (BigDecimal)f_quantity.getValue();
		BigDecimal PriceActual = (BigDecimal)f_price.getValue();
		MOrderLine line = createLine(product, QtyOrdered, PriceActual);
		if (line == null)
			return false;
		if (!line.save())
			return false;
		//
		newLine();
		return true;
	}	//	saveLine
	
	/**
	 * 	Get/create Header
	 *	@return header or null
	 */
	public MOrder getOrder()
	{
		if (m_order == null)
		{
			m_order = new MOrder(Env.getCtx(), 0, null);
			m_order.setAD_Org_ID(p_pos.getAD_Org_ID());
			m_order.setIsSOTrx(true);
			if (p_pos.getC_DocType_ID() != 0)
				m_order.setC_DocTypeTarget_ID(p_pos.getC_DocType_ID());
			else
				m_order.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_POS);
			MBPartner partner = p_posPanel.f_bpartner.getBPartner(); 
			if (partner == null || partner.get_ID() == 0)
				partner = p_pos.getBPartner();
			if (partner == null || partner.get_ID() == 0)
			{
				log.log(Level.SEVERE, "SubCurrentLine.getOrder - no BPartner");
				return null;
			}
			log.info( "SubCurrentLine.getOrder -" + partner);
			m_order.setBPartner(partner);
			int id = p_posPanel.f_bpartner.getC_BPartner_Location_ID();
			if (id != 0)
				m_order.setC_BPartner_Location_ID(id);
			id = p_posPanel.f_bpartner.getAD_User_ID();
			if (id != 0)
				m_order.setAD_User_ID(id);
			//
			m_order.setM_PriceList_ID(p_pos.getM_PriceList_ID());
			m_order.setM_Warehouse_ID(p_pos.getM_Warehouse_ID());
			m_order.setSalesRep_ID(p_pos.getSalesRep_ID());
			if (!m_order.save())
				m_order = null;
		}
		return m_order;
	}	//	getHeader
	
	/**
	 *	Create new Line
	 *	@return line or null
	 */
	public MOrderLine createLine(MProduct product, 
		BigDecimal QtyOrdered, BigDecimal PriceActual)
	{
		MOrder order = getOrder();
		if (order == null)
			return null;
		MOrderLine line = new MOrderLine (order);
		line.setProduct(product);
		line.setQty(QtyOrdered);
		line.setPrice();	//	sets List/limit
		line.setPrice(PriceActual);
		line.save();
		return line;
	}	//	getLine

}	//	PosSubCurrentLine
