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
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MPOSKey;
import org.compiere.model.MUser;
import org.compiere.model.MWarehousePrice;
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
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
	}	//	PosSubFunctionKeys
	
	/**	Totals			*/
	private CLabel	 		f_net;
	private CLabel 			f_tax;
	private CLabel 			f_total;
	private CLabel			f_RepName;
	private POSTextField	f_name;
	private CLabel 			f_DocumentNo;
	
	/**	Format				*/
	private DecimalFormat	m_Format;
	/**	Keys				*/
	private MPOSKey[] 		m_keys;
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(POSProductPanel.class);
	

	/**
	 * 	Initialize
	 */
	public void init() {
		int C_POSKeyLayout_ID = p_pos.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		
		PosKeyPanel panel = new PosKeyPanel(C_POSKeyLayout_ID, this);
		this.setLayout(new MigLayout("fill, ins 20 10"));
		Font bigFont = AdempierePLAF.getFont_Field().deriveFont(18f);		
 		// DOC NO
		CLabel lDocNo = new CLabel(Msg.getMsg(Env.getCtx(),MOrder.COLUMNNAME_DocumentNo)+":");
		add (lDocNo, "growx"); 
		lDocNo.setFontBold(true);
		lDocNo.setFont(bigFont);
		lDocNo.setFontBold(true);
		f_DocumentNo = new CLabel("");
		f_DocumentNo.setName(MOrder.COLUMNNAME_DocumentNo);
		f_DocumentNo.setFont(bigFont);
		add (f_DocumentNo, "pushx");
		
		CLabel lNet = new CLabel (Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_TotalLines)+":");
		add(lNet, "");
		f_net = new CLabel("0.00");
		f_net.setFocusable(false);
		f_net.setFont(bigFont);
		f_net.setFontBold(true);
		lNet.setLabelFor(f_net);
		lNet.setFont(bigFont);
		lNet.setFontBold(true);
		add(f_net, "wrap, growx, pushx");
	
		// SALES REP
		CLabel lSalesRep = new CLabel(Msg.translate(Env.getCtx(), "POS.SalesRep_ID")+":");
		add(lSalesRep, "growx"); 
		lSalesRep.setFont(bigFont);
		lSalesRep.setFontBold(true);
		MUser salesrep = new MUser(p_ctx, Env.getAD_User_ID(p_ctx), null);
		f_RepName = new CLabel(salesrep.getName());
		f_RepName.setName("SalesRep");
		f_RepName.setFont(bigFont);
		add (f_RepName, "");

		CLabel lTax = new CLabel (Msg.translate(Env.getCtx(), MInvoiceLine.COLUMNNAME_C_Tax_ID)+":");
		add(lTax, "growx");
		f_tax = new CLabel("0.00");
		f_tax.setFocusable(false);
		f_tax.setFont(bigFont);
		f_tax.setFontBold(true);
		lTax.setLabelFor(f_tax);
		lTax.setFont(bigFont);
		lTax.setFontBold(true);
		add(f_tax, "wrap, growx, pushx");
		//
		//
		CLabel f_Line = new CLabel ("___________________");
		add(f_Line, "span, growx, wrap");
		
		CLabel lTotal = new CLabel (Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_GrandTotal)+":");
		add(lTotal, "cell 2 4, growx");
		f_total = new CLabel("0.00");
		f_total.setFocusable(false);
		f_total.setFont(bigFont);
		f_total.setFontBold(true);
		f_total.setMinimumSize(new Dimension(150,5));
		lTotal.setLabelFor(f_total);
		lTotal.setFont(bigFont);
		lTotal.setFontBold(true);
		add(f_total, "wrap, growx, pushx,cell 3 4");

		CLabel lProduct = new CLabel(Msg.translate(Env.getCtx(), "M_Product_ID"));
		lProduct.setFont(bigFont);
		lProduct.setFontBold(true);
		add(lProduct, "split 2,spanx 4");
		
		f_name = new POSTextField(Msg.translate(Env.getCtx(), "M_Product_ID"), v_POSPanel.getKeyboard());
		f_name.setName("Name");
		f_name.addActionListener(this);
		f_name.requestFocusInWindow();
		f_name.setFont(bigFont);
		add (f_name, "spanx 3, growx, h 30:30:, wrap");

		add(panel, "growx, growy, span");

	}	//	init
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
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
		
		// new line
		v_POSPanel.f_curLine.setM_Product_ID(key.getM_Product_ID());
		v_POSPanel.f_curLine.setPrice();
		v_POSPanel.f_curLine.setQty(key.getQty());
		if ( !v_POSPanel.f_curLine.saveLine() )
		{
			ADialog.error(0, this, "Could not save order line");
		}
		v_POSPanel.updateInfo();
		return;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		//	Name
		if (e.getSource() == f_name) {
			findProduct();
		}
	}
	
	/**************************************************************************
	 * 	Find/Set Product & Price
	 */
	private void findProduct()
	{
		String query = f_name.getText();
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
			/*m_M_PriceList_Version_ID*/ 0, /*m_M_Warehouse_ID*/ 0,
			Value, Name, UPC, SKU, null);
		
		//	Set Result
		if (results.length == 0)
		{
			String message = Msg.translate(p_ctx,  "search product notfound");
			ADialog.warn(v_POSPanel.getWindowNo(), null, message + query);
			v_POSPanel.f_curLine.setM_Product_ID(0);
//			p_posPanel.f_curLine.setPrice(Env.ZERO);
		}
		else if (results.length == 1)
		{
			v_POSPanel.f_curLine.setM_Product_ID(results[0].getM_Product_ID());
			v_POSPanel.f_curLine.setQty(Env.ONE);
			f_name.setText(results[0].getName());
			v_POSPanel.f_curLine.setPrice(results[0].getPriceStd());
			v_POSPanel.f_curLine.saveLine();
		}
		else	//	more than one
		{
			QueryProduct qt = new QueryProduct(v_POSPanel);
			qt.setResults(results);
			qt.setQueryData(/*m_M_PriceList_Version_ID*/0, /*m_M_Warehouse_ID*/0);
			qt.setVisible(true);
		}
	}	//	findProduct

	@Override
	public void refreshPanel() {
		if (!v_POSPanel.hasOrder()) {
			f_DocumentNo.setText("");
			f_net.setText(m_Format.format(Env.ZERO));
			f_total.setText(m_Format.format(Env.ZERO));
			f_tax.setText(m_Format.format(Env.ZERO));
		} else {
			BigDecimal m_TotalLines = v_POSPanel.getTotalLines();
			BigDecimal m_GrandTotal = v_POSPanel.getGrandTotal();
			BigDecimal m_TaxAmt = m_GrandTotal.subtract(m_TotalLines);
			//	Set Values
			f_DocumentNo.setText(v_POSPanel.getDocumentNo());
			f_net.setText(m_Format.format(m_TotalLines));
			f_total.setText(m_Format.format(m_GrandTotal));
			f_tax.setText(m_Format.format(m_TaxAmt));
		}
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		
	}
	
}	//	PosSubFunctionKeys
