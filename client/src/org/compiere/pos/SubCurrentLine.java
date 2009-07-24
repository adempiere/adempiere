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

import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.logging.Level;

import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.compiere.grid.ed.VNumber;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrderTax;
import org.compiere.model.MProduct;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Current Line Sub Panel
 * 
 * @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � Jorg Janke
 * @version $Id: SubCurrentLine.java,v 1.3 2004/07/24 04:31:52 jjanke Exp $
 * red1 - [2093355 ] Small bugs in OpenXpertya POS
 */
public class SubCurrentLine extends PosSubPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4023538043556457231L;

	/**
	 * Constructor
	 * 
	 * @param posPanel
	 *            POS Panel
	 */
	public SubCurrentLine(PosPanel posPanel) {
		super(posPanel);
	} //	PosSubCurrentLine

	private CButton f_new;

	private CButton f_reset;

	private CButton f_plus;

	private CButton f_minus;

	private CLabel f_currency;

	private VNumber f_price;

	private CLabel f_uom;

	private VNumber f_quantity;

	private MOrder m_order = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubCurrentLine.class);
	
	/**
	 * Initialize
	 */ 
	public void init() {
		//	Title
		TitledBorder border = new TitledBorder(Msg.getMsg(Env.getCtx(),
				"CurrentLine"));
		setBorder(border);

		//	Content
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = INSETS2;
		gbc.gridy = 0;
		//	--
		f_new = createButtonAction("Save", KeyStroke.getKeyStroke(
				KeyEvent.VK_INSERT, Event.SHIFT_MASK));
		gbc.gridx = 0;
		add(f_new, gbc);
		//
		f_reset = createButtonAction("Reset", null);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(f_reset, gbc);
		//
		f_currency = new CLabel("---");
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = .1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(f_currency, gbc);
		//
		f_price = new VNumber("PriceActual", false, false, true,
				DisplayType.Amount, Msg.translate(Env.getCtx(), "PriceActual"));
		f_price.addActionListener(this);
		f_price.setColumns(10, 25);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		add(f_price, gbc);
		setPrice(Env.ZERO);
		//	--
		f_uom = new CLabel("--");
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = .1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(f_uom, gbc);
		//
		f_minus = createButtonAction("Minus", null);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		add(f_minus, gbc);
		//
		f_quantity = new VNumber("QtyOrdered", false, false, true,
				DisplayType.Quantity, Msg.translate(Env.getCtx(), "QtyOrdered"));
		f_quantity.addActionListener(this);
		f_quantity.setColumns(5, 25);
		add(f_quantity, gbc);
		setQty(Env.ONE);
		//
		f_plus = createButtonAction("Plus", null);
		add(f_plus, gbc);
	} //	init

	/**
	 * Get Panel Position
	 */
	public GridBagConstraints getGridBagConstraints() {
		GridBagConstraints gbc = super.getGridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		return gbc;
	} //	getGridBagConstraints

	/**
	 * Dispose - Free Resources
	 */
	public void dispose() {
		super.dispose();
	} //	dispose

	/**
	 * Action Listener
	 * 
	 * @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "SubCurrentLine - actionPerformed: " + action);
		//	New / Reset
		if (action.equals("Save"))
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
		p_posPanel.updateInfo();
	} //	actionPerformed

	/***************************************************************************
	 * Set Currency
	 * 
	 * @param currency
	 *            currency
	 */
	public void setCurrency(String currency) {
		if (currency == null)
			f_currency.setText("---");
		else
			f_currency.setText(currency);
	} //	setCurrency

	/**
	 * Set UOM
	 * 
	 * @param UOM
	 */
	public void setUOM(String UOM) {
		if (UOM == null)
			f_uom.setText("--");
		else
			f_uom.setText(UOM);
	} //	setUOM

	/**
	 * Set Price
	 * 
	 * @param price
	 */
	public void setPrice(BigDecimal price) {
		if (price == null)
			price = Env.ZERO;
		f_price.setValue(price);
		boolean rw = Env.ZERO.compareTo(price) == 0 || p_pos.isModifyPrice();
		f_price.setReadWrite(rw);
	} //	setPrice

	/**
	 * Get Price
	 * 
	 * @return price
	 */
	public BigDecimal getPrice() {
		return (BigDecimal) f_price.getValue();
	} //	getPrice

	/**
	 * Set Qty
	 * 
	 * @param qty
	 */
	public void setQty(BigDecimal qty) {
		f_quantity.setValue(qty);
	} //	setQty

	/**
	 * Get Qty
	 * 
	 * @return qty
	 */
	public BigDecimal getQty() {
		return (BigDecimal) f_quantity.getValue();
	} //	getQty

	/***************************************************************************
	 * New Line
	 */
	public void newLine() {
		p_posPanel.f_product.setM_Product_ID(0);
		setQty(Env.ONE);
		setPrice(Env.ZERO);
	} //	newLine

	/**
	 * Save Line
	 * 
	 * @return true if saved
	 */
	public boolean saveLine() {
		MProduct product = p_posPanel.f_product.getProduct();
		if (product == null)
			return false;
		BigDecimal QtyOrdered = (BigDecimal) f_quantity.getValue();
		BigDecimal PriceActual = (BigDecimal) f_price.getValue();
		MOrderLine line = createLine(product, QtyOrdered, PriceActual);
		if (line == null)
			return false;
		if (!line.save())
			return false;
		//
		newLine();
		return true;
	} //	saveLine

	/** 
	 * to erase the lines from order
	 * @return true if deleted
	 */
	public void deleteLine (int row) {
		if (m_order != null && row != -1 )
		{
			MOrderLine[] lineas = m_order.getLines(true, null);
			int numLineas = lineas.length;
			if (numLineas > row)
			{
				//delete line from order - true only when DRAFT is not PREPARE-IT()
				lineas[row].delete(true);
				for (int i = row; i < (numLineas - 1); i++)
					lineas[i] = lineas[i + 1];
				lineas[numLineas - 1] = null;				
			}
		}
	} //	deleteLine
	
	/**
	 * Delete order from database
	 * 
	 * @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � ConSerTi
	 */		
	public void deleteOrder () {
		if (m_order != null)
		{
			if (m_order.getDocStatus().equals("DR"))
			{
				MOrderLine[] lineas = m_order.getLines();
				if (lineas != null)
				{
					int numLineas = lineas.length;
					if (numLineas > 0)
						for (int i = numLineas - 1; i >= 0; i--)
						{
							if (lineas[i] != null)
								deleteLine(i);
						}
				}
				
				MOrderTax[] taxs = m_order.getTaxes(true);
				if (taxs != null)
				{
					int numTax = taxs.length;
					if (numTax > 0)
						for (int i = taxs.length - 1; i >= 0; i--)
						{
							if (taxs[i] != null)
								taxs[i].delete(true);
							taxs[i] = null;
						}
				}
				
				m_order.delete(true);
				m_order = null;
			}
		}
	} //	deleteOrder
	
	/**
	 * Create new order
	 * 
	 * @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � ConSerTi
	 */
	public void newOrder()
	{
		m_order = null;
		m_order = getOrder();	
	}

	/**
	 * Get/create Header
	 * 
	 * @return header or null
	 */
	public MOrder getOrder() {
		if (m_order == null) {
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
			if (partner == null || partner.get_ID() == 0) {
				log.log(Level.SEVERE, "SubCurrentLine.getOrder - no BPartner");

				return null;
			}
			log.info( "SubCurrentLine.getOrder -" + partner);
			m_order.setBPartner(partner);
			p_posPanel.f_bpartner.setC_BPartner_ID(partner.getC_BPartner_ID());
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
			m_order.setPaymentRule(MOrder.PAYMENTRULE_Cash);
			if (!m_order.save())
			{
				m_order = null;
				log.severe("Unable create Order.");
			}
		}
		return m_order;
	} //	getHeader

	/**
	 * @author Comunidad de Desarrollo OpenXpertya 
	 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
	 *         *Copyright � ConSerTi
	 */
	public void setBPartner()
	{
		if (m_order != null)
			if (m_order.getDocStatus().equals("DR"))
			{
				MBPartner partner = p_posPanel.f_bpartner.getBPartner();
				//get default from mpos if no selection make
				if (partner == null || partner.get_ID() == 0)
					partner = p_pos.getBPartner();
				if (partner == null || partner.get_ID() == 0) {
					log.warning("SubCurrentLine.getOrder - no BPartner");
				}
				else
				{
					log.info("SubCurrentLine.getOrder -" + partner);
					m_order.setBPartner(partner);
					MOrderLine[] lineas = m_order.getLines();
					for (int i = 0; i < lineas.length; i++)
					{
						lineas[i].setC_BPartner_ID(partner.getC_BPartner_ID());
						lineas[i].setTax();
						lineas[i].save();
					}
					m_order.save();
				}
			}
	}

	/**
	 * Create new Line
	 * 
	 * @return line or null
	 */
	public MOrderLine createLine(MProduct product, BigDecimal QtyOrdered,
			BigDecimal PriceActual) {
		MOrder order = getOrder();
		if (order == null)
			return null;
		if (!order.getDocStatus().equals("DR"))
			return null;
		//add new line or increase qty
		
		// catch Exceptions at order.getLines()
		int numLineas = 0;
		MOrderLine[] lineas = null;
		try
		{
			lineas = order.getLines("","");
			numLineas = lineas.length;
			for (int i = 0; i < numLineas; i++)
			{
				if (lineas[i].getM_Product_ID() == product.getM_Product_ID())
				{
					//increase qty
					double current = lineas[i].getQtyEntered().doubleValue();
					double toadd = QtyOrdered.doubleValue();
					double total = current + toadd;
					lineas[i].setQty(new BigDecimal(total));
					lineas[i].setPrice(); //	sets List/limit
					lineas[i].save();
					return lineas[i];
				}
			}
		}
		catch (Exception e)
		{
			log.severe("Order lines cannot be created - " + e.getMessage());
		}

        //create new line
		MOrderLine line = new MOrderLine(order);
		line.setProduct(product);
		line.setQty(QtyOrdered);
			
		line.setPrice(); //	sets List/limit
		line.setPrice(PriceActual);
		line.save();
		return line;
			
	} //	createLine

	/**
	 * @param m_c_order_id
	 */
	public void setOldOrder(int m_c_order_id) 
	{
		deleteOrder();
		m_order = new MOrder(p_ctx , m_c_order_id, null);
		p_posPanel.updateInfo();
	}
	
	/**
	 * @param m_c_order_id
	 */
	public void setOrder(int m_c_order_id) 
	{
		m_order = new MOrder(p_ctx , m_c_order_id, null);
	}
} //	PosSubCurrentLine
