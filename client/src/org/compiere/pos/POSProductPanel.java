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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import net.miginfocom.swing.MigLayout;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_Product;
import org.compiere.model.MPOSKey;
import org.compiere.model.MWarehousePrice;
import org.compiere.pos.search.QueryProduct;
import org.compiere.swing.CLabel;
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
public class POSProductPanel extends PosSubPanel 
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
	
	/**	Document No			*/
	private CLabel 			f_lb_DocumentNo;
	private CLabel 			f_DocumentNo;
	/**	Sales Rep.			*/
	private CLabel			f_lb_SalesRep_Name;
	private CLabel			f_SalesRep_Name;
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
	private CLabel 			f_lb_ProductName;
	private POSTextField	f_ProductName;
	
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
		int C_POSKeyLayout_ID = p_pos.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		//	Set Layout
		this.setLayout(new MigLayout("fill, ins 20 10"));
		//	Set Font and Format
		font = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 18);
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
 		// For Document No
		f_lb_DocumentNo = new CLabel(Msg.getMsg(Env.getCtx(), I_C_Order.COLUMNNAME_DocumentNo)+":");
		f_lb_DocumentNo.setFont(font);
		//	Add
		add(f_lb_DocumentNo, "growx");
		//	
		f_DocumentNo = new CLabel();
		f_DocumentNo.setFont(font);
		//	Add
		add(f_DocumentNo, "pushx");
		//	For Total Lines
		f_lb_TotalLines = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_TotalLines) + ":");
		f_lb_TotalLines.setFont(font);
		//	Add
		add(f_lb_TotalLines, "");
		//	
		f_TotalLines = new CLabel();
		f_TotalLines.setFont(font);
//		f_lb_TotalLines.setLabelFor(f_TotalLines);
		//	Add
		add(f_TotalLines, "wrap, growx, pushx");
		// Sales Representative
		f_lb_SalesRep_Name = new CLabel(Msg.translate(Env.getCtx(), "POS.SalesRep_ID") + ":");
		f_lb_SalesRep_Name.setFont(font);
		//	Add
		add(f_lb_SalesRep_Name, "growx"); 
		//	
		f_SalesRep_Name = new CLabel();
		f_SalesRep_Name.setFont(font);
		f_lb_SalesRep_Name.setLabelFor(f_SalesRep_Name);
		//	Add
		add (f_SalesRep_Name, "");
		//	For Tax Amount
		f_lb_TaxAmount = new CLabel (Msg.translate(Env.getCtx(), I_C_OrderLine.COLUMNNAME_C_Tax_ID) + ":");
		f_lb_TaxAmount.setFont(font);
		//	Add
		add(f_lb_TaxAmount, "growx");
		//	
		f_TaxAmount = new CLabel();
		f_TaxAmount.setFont(font);
		f_lb_TaxAmount.setLabelFor(f_TaxAmount);
		//	Add
		add(f_TaxAmount, "wrap, growx, pushx");
		//	For Line
		f_lb_Line = new CLabel ("___________________");
		f_lb_Line.setFont(font);
		//	Add
		add(f_lb_Line, "span, growx, wrap");
		//	For Grand Total
		f_lb_GrandTotal = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_GrandTotal)+":");
		f_lb_GrandTotal.setFont(font);
		//	Add
		add(f_lb_GrandTotal, "cell 2 4, growx");
		//	
		f_GrandTotal = new CLabel();
		f_GrandTotal.setFont(font);
		f_GrandTotal.setMinimumSize(new Dimension(150,5));
		f_lb_GrandTotal.setLabelFor(f_GrandTotal);
		//	Add
		add(f_GrandTotal, "wrap, growx, pushx,cell 3 4");
		//	For Product
		String labelName = Msg.translate(Env.getCtx(), I_M_Product.COLUMNNAME_M_Product_ID);
		f_lb_ProductName = new CLabel(labelName);
		f_lb_ProductName.setFont(font);
		//	Add
		add(f_lb_ProductName, "split 2,spanx 4");
		//	
		f_ProductName = new POSTextField(labelName, v_POSPanel.getKeyboard());
		f_ProductName.setName("ProductName");
		f_ProductName.addActionListener(this);
		f_ProductName.requestFocusInWindow();
		f_ProductName.setFont(font);
		//	Add
		add (f_ProductName, "spanx 3, growx, h 30:30:, wrap");
		//	For Key Panel
		PosKeyPanel panel = new PosKeyPanel(C_POSKeyLayout_ID, this);
		//	Add
		add(panel, "growx, growy, span");
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
		String lineError = v_POSPanel.saveLine(p_M_Product_ID, m_QtyOrdered);
		if (lineError != null) {
			log.warning("POS Error " + lineError);
			ADialog.error(v_POSPanel.getWindowNo(), 
					this, Msg.parseTranslation(p_ctx, lineError));
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
		try
		{
			Integer.getInteger(query);
		}
		catch (Exception e)
		{
			allNumber = false;
		}
		String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);
		
		MWarehousePrice[] results = null;
		
		//	setParameter();
		//
		results = MWarehousePrice.find (p_ctx,
				v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID(), 
				Value, Name, UPC, SKU, null);
		
		//	Set Result
		if (results.length == 0) {
			String message = Msg.getMsg(p_ctx,  "POS.SearchProductNF");	//	TODO Translate it: Search Product Not Found
			ADialog.warn(v_POSPanel.getWindowNo(), null, message + " " + query);
		} else if (results.length == 1) {	//	one
			addLine(results[0].getM_Product_ID(), Env.ONE);
			f_ProductName.setText(results[0].getName());
		} else {	//	more than one
			QueryProduct qt = new QueryProduct(v_POSPanel);
			qt.setResults(results);
			qt.setQueryData(v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID());
			qt.setVisible(true);
		}
	}	//	findProduct

	@Override
	public void refreshPanel() {
		if (!v_POSPanel.hasOrder()) {
			f_DocumentNo.setText("");
			f_SalesRep_Name.setText("");
			f_TotalLines.setText(m_Format.format(Env.ZERO));
			f_GrandTotal.setText(m_Format.format(Env.ZERO));
			f_TaxAmount.setText(m_Format.format(Env.ZERO));
		} else {
			BigDecimal m_TotalLines = v_POSPanel.getTotalLines();
			BigDecimal m_GrandTotal = v_POSPanel.getGrandTotal();
			BigDecimal m_TaxAmt = m_GrandTotal.subtract(m_TotalLines);
			//	Set Values
			f_DocumentNo.setText(v_POSPanel.getDocumentNo());
			f_SalesRep_Name.setText(v_POSPanel.getSalesRepName());
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