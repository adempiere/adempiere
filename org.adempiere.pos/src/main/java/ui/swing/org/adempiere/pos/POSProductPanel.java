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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.service.I_POSPanel;
import org.compiere.apps.ADialog;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_Product;
import org.compiere.model.MPOSKey;
import org.compiere.model.MWarehousePrice;
import org.compiere.pos.PosKeyListener;
import org.adempiere.pos.search.QueryProduct;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	Function Key Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  @version $Id: SubFunctionKeys.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 */
public class POSProductPanel extends POSSubPanel 
	implements PosKeyListener, ActionListener, I_POSPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSProductPanel (VPOS posPanel) {
		super (posPanel);
	}	//	PosSubFunctionKeys
	
	/**	Panels				*/;
	private CPanel 			v_HeaderPanel;
	private TitledBorder 	v_TitleBorder;
	/**	Document No			*/
//	private CLabel 			f_lb_DocumentNo;
//	private CLabel 			f_DocumentNo;
	/**	Sales Rep.			*/
//	private CLabel			f_lb_SalesRep_Name;
//	private CLabel			f_SalesRep_Name;
	/**	Total Lines			*/
	private CLabel	 		f_lb_TotalLines;
	private CLabel	 		f_TotalLines;
	/**	Tax Amount			*/
	private CLabel 			f_lb_TaxAmount;
	private CLabel 			f_TaxAmount;
	/**	Line				*/
	private CLabel			f_lb_Line;
	/**	Grand Total			*/
	private CLabel 			f_lb_GrandTotal;
	private CLabel 			f_GrandTotal;
	/**	Product Name		*/
	private POSTextField	f_ProductName;
	/**	Keyboard			*/
	private POSKeyPanel 	f_Keyboard;
	/**	Format				*/
	private DecimalFormat	m_Format;
	/**	Font				*/
	private Font 			font;
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(POSProductPanel.class);

	/**
	 * 	Initialize
	 */
	public void init() {
		int C_POSKeyLayout_ID = m_pos.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		//	Set Layout
		setLayout(new GridBagLayout());
		//	Document Panel
		//	Set Font and Format
		v_HeaderPanel = new CPanel(new GridBagLayout());
		//	Set Border
		font = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 18);
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		//	For Title Border
		v_TitleBorder = BorderFactory.createTitledBorder("");
		v_TitleBorder.setTitleFont(font);
		v_HeaderPanel.setBorder(v_TitleBorder);
 		// For Document No
//		f_lb_DocumentNo = new CLabel(Msg.getMsg(Env.getCtx(), I_C_Order.COLUMNNAME_DocumentNo)+":");
//		f_lb_DocumentNo.setFont(font);
		//	Add
//		v_HeaderPanel.add(f_lb_DocumentNo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
//				,GridBagConstraints.WEST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	
//		f_DocumentNo = new CLabel();
//		f_DocumentNo.setFont(font);
		//	Add
//		v_HeaderPanel.add(f_DocumentNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
//				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	For Total Lines
		f_lb_TotalLines = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_TotalLines) + ":");
		f_lb_TotalLines.setFont(font);
		//	Add
		v_HeaderPanel.add(f_lb_TotalLines, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_TotalLines = new CLabel();
		f_TotalLines.setFont(font);
		f_lb_TotalLines.setLabelFor(f_TotalLines);
		//	Add
		v_HeaderPanel.add(f_TotalLines, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		// Sales Representative
//		f_lb_SalesRep_Name = new CLabel(Msg.translate(Env.getCtx(), "POS.SalesRep_ID") + ":");
//		f_lb_SalesRep_Name.setFont(font);
		//	Add
//		v_HeaderPanel.add(f_lb_SalesRep_Name, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
//				,GridBagConstraints.WEST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	
//		f_SalesRep_Name = new CLabel();
//		f_SalesRep_Name.setFont(font);
//		f_lb_SalesRep_Name.setLabelFor(f_SalesRep_Name);
		//	Add
//		v_HeaderPanel.add (f_SalesRep_Name, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
//				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	For Tax Amount
		f_lb_TaxAmount = new CLabel (Msg.translate(Env.getCtx(), I_C_OrderLine.COLUMNNAME_C_Tax_ID) + ":");
		f_lb_TaxAmount.setFont(font);
		//	Add
		v_HeaderPanel.add(f_lb_TaxAmount, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_TaxAmount = new CLabel();
		f_TaxAmount.setFont(font);
		f_lb_TaxAmount.setLabelFor(f_TaxAmount);
		//	Add
		v_HeaderPanel.add(f_TaxAmount, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Line
		f_lb_Line = new CLabel ("___________________");
		f_lb_Line.setFont(font);
		//	Add
		v_HeaderPanel.add(f_lb_Line, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Grand Total
		f_lb_GrandTotal = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_GrandTotal)+":");
		f_lb_GrandTotal.setFont(font);
		//	Add
		v_HeaderPanel.add(f_lb_GrandTotal, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_GrandTotal = new CLabel();
		f_GrandTotal.setFont(font);
		f_lb_GrandTotal.setLabelFor(f_GrandTotal);
		//	Change Size
		//	Add
		v_HeaderPanel.add(f_GrandTotal,  new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Product
		String labelName = Msg.translate(Env.getCtx(), I_M_Product.COLUMNNAME_M_Product_ID);
		//	
		f_ProductName = new POSTextField(labelName, v_POSPanel.getKeyboard());
		f_ProductName.setName("ProductName");
		f_ProductName.setPlaceholder(labelName);
		f_ProductName.addActionListener(this);
		f_ProductName.requestFocusInWindow();
		f_ProductName.setFont(font);
		f_ProductName.setPreferredSize(new Dimension(v_POSPanel.PRODUCT_PANEL_WIDTH, v_POSPanel.FIELD_HEIGHT));
		//	For Key Panel
		f_Keyboard = new POSKeyPanel(C_POSKeyLayout_ID, this);
		//	Add Header
		GridBagConstraints m_HeaderConstraint = new GridBagConstraints();
		m_HeaderConstraint.fill = GridBagConstraints.HORIZONTAL;
		m_HeaderConstraint.weightx = 1;
		m_HeaderConstraint.gridy = 0;
		add(v_HeaderPanel, m_HeaderConstraint);
		//	Add Product Name
		GridBagConstraints m_ProductConstraint = new GridBagConstraints();
		m_ProductConstraint.fill = GridBagConstraints.HORIZONTAL;
		m_ProductConstraint.weightx = 1;
		m_ProductConstraint.gridy = 1;
		add (f_ProductName,  m_ProductConstraint);
		//	Add Keyboard
		GridBagConstraints m_KeyboardConstraint = new GridBagConstraints();
		m_KeyboardConstraint.fill = GridBagConstraints.BOTH;
		m_KeyboardConstraint.weightx = 1;
		m_KeyboardConstraint.gridy = 2;
		add(f_Keyboard,  m_KeyboardConstraint);
		//	Refresh
		f_TotalLines.setText(m_Format.format(Env.ZERO));
		f_GrandTotal.setText(m_Format.format(Env.ZERO));
		f_TaxAmount.setText(m_Format.format(Env.ZERO));
	}	//	init
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose() {
		super.dispose();
	}	//	dispose

	/**
	 * Call back from key panel
	 */
	public void keyReturned(MPOSKey key) {
		// processed order
		if (v_POSPanel.getM_Order() != null 
				&& v_POSPanel.getM_Order().isProcessed())
			return;
		// Add line
		addLine(key.getM_Product_ID(), key.getQty());
		return;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		//	Name
		if (e.getSource() == f_ProductName) {
			findProduct();
		}
	}
	
	/**
	 * Add or replace order line
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_M_Product_ID
	 * @param m_QtyOrdered
	 * @return void
	 */
	private void addLine(int p_M_Product_ID, BigDecimal m_QtyOrdered) {
		//	Create Ordder if not exists
		if (!v_POSPanel.hasOrder()) {
			v_POSPanel.newOrder();
		}
		//	
		String lineError = v_POSPanel.add(p_M_Product_ID, m_QtyOrdered);
		if (lineError != null) {
			log.warning("POS Error " + lineError);
			ADialog.error(v_POSPanel.getWindowNo(), 
					this, Msg.parseTranslation(m_ctx, lineError));
		}
		//	Update Info
		v_POSPanel.refreshPanel();
	}
	
	/**************************************************************************
	 * 	Find/Set Product & Price
	 */
	private void findProduct() {
		String query = f_ProductName.getText();
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		try {
			Integer.getInteger(query);
		} catch (Exception e) {
			allNumber = false;
		}
		String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);
		
		MWarehousePrice[] results = null;
		
		//	setParameter();
		//
		results = MWarehousePrice.find (m_ctx,
				v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID(), 
				Value, Name, UPC, SKU, null);
		
		//	Set Result
//		if (results.length == 0) {
//			String message = Msg.getMsg(p_ctx,  "POS.SearchProductNF");	//	TODO Translate it: Search Product Not Found
//			ADialog.warn(v_POSPanel.getWindowNo(), null, message + " " + query);
//		} else 
		if (results.length == 1) {	//	one
			addLine(results[0].getM_Product_ID(), Env.ONE);
			f_ProductName.setText(results[0].getName());
		} else {	//	more than one
			QueryProduct qt = new QueryProduct(v_POSPanel);
			qt.setQueryData(v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID());
			qt.setVisible(true);
			if (qt.getM_Product_ID() > 0) {
				f_ProductName.setText(qt.getProductName());
				addLine(qt.getM_Product_ID(), Env.ONE);
			}
		}
	}	//	findProduct

	@Override
	public void refreshPanel() {
		if (!v_POSPanel.hasOrder()) {
			v_TitleBorder.setTitle("");
			f_TotalLines.setText(m_Format.format(Env.ZERO));
			f_GrandTotal.setText(m_Format.format(Env.ZERO));
			f_TaxAmount.setText(m_Format.format(Env.ZERO));
		} else {
			BigDecimal m_TotalLines = v_POSPanel.getTotalLines();
			BigDecimal m_GrandTotal = v_POSPanel.getGrandTotal();
			BigDecimal m_TaxAmt = m_GrandTotal.subtract(m_TotalLines);
			//	Set Values
			v_TitleBorder.setTitle(v_POSPanel.getSalesRepName() + "[" + v_POSPanel.getDocumentNo() + "]");
			v_HeaderPanel.setBorder(v_TitleBorder);
			v_HeaderPanel.validate();
			v_HeaderPanel.repaint();
			f_TotalLines.setText(m_Format.format(m_TotalLines));
			f_GrandTotal.setText(m_Format.format(m_GrandTotal));
			f_TaxAmount.setText(m_Format.format(m_TaxAmt));
		}
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		
	}
}	//	POSProductPanel