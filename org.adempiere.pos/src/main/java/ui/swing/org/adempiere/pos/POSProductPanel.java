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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MPOSKey;
import org.compiere.pos.PosKeyListener;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	Function Key Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  @version $Id: SubFunctionKeys.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 */
public class POSProductPanel extends POSSubPanel 
	implements PosKeyListener, ActionListener, I_POSPanel, POSQueryListener {
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
	}	//	POSProductPanel
	
	/**	Header Panel		*/
	private CPanel 			v_HeaderPanel;
	/**	Total Panel			*/;
	private CPanel 			v_TotalPanel;
	/**	Doc Info Panel		*/
	private CPanel 			v_DocInfoPanel;
	/**	Total Title Border	*/
	private TitledBorder 	v_TotalTitle;
	/**	Doc Title Border	*/
	private TitledBorder 	v_DocTitle;
	/**	Sales Representative*/
	private CLabel	 		f_lb_SalesRep;
	private CLabel	 		f_SalesRep;
	/**	Document Type		*/
	private CLabel 			f_lb_DocumentType;
	private CLabel 			f_DocumentType;
	/**	Document No			*/
	private CLabel 			f_lb_DocumentNo;
	private CLabel 			f_DocumentNo;
	/**	Total Lines			*/
	private CLabel	 		f_lb_TotalLines;
	private CLabel	 		f_TotalLines;
	/**	Tax Amount			*/
	private CLabel 			f_lb_TaxAmount;
	private CLabel 			f_TaxAmount;
	/**	Grand Total			*/
	private CLabel 			f_lb_GrandTotal;
	private CLabel 			f_GrandTotal;
	/**	Product Name		*/
	private POSTextField	f_BPartnerName;
	/**	Keyboard			*/
	private POSKeyPanel 	f_Keyboard;
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(POSProductPanel.class);

	/**
	 * 	Initialize
	 */
	public void init() {
		int C_POSKeyLayout_ID = m_pos.getC_POSKeyLayout_ID();
		//	Set Layout
		setLayout(new GridBagLayout());
		//	Set Right Padding
		int m_RightPadding = 0;
		//	Document Panel
		//	Set Font and Format
		v_HeaderPanel = new CPanel(new GridBagLayout());
		v_DocInfoPanel = new CPanel(new GridBagLayout());
		v_TotalPanel = new CPanel(new GridBagLayout());
		//	For Doc Title Border
		v_DocTitle = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "InfoOrder"));
		v_DocTitle.setTitleFont(v_POSPanel.getFont());
		v_DocTitle.setTitleColor(AdempierePLAF.getTextColor_Label());
		v_DocInfoPanel.setBorder(v_DocTitle);
		//	For Total Title Border
		v_TotalTitle = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "Totals"));
		v_TotalTitle.setTitleFont(v_POSPanel.getFont());
		v_TotalTitle.setTitleColor(AdempierePLAF.getTextColor_Label());
		v_TotalPanel.setBorder(v_TotalTitle);
		//	For Document Info
		//	For Sales Representative
		f_lb_SalesRep = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_SalesRep_ID) + ":");
		f_lb_SalesRep.setFont(v_POSPanel.getPlainFont());
		//	Add
		v_DocInfoPanel.add(f_lb_SalesRep, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_SalesRep = new CLabel();
		f_SalesRep.setFont(v_POSPanel.getFont());
		f_lb_SalesRep.setLabelFor(f_SalesRep);
		//	Add
		v_DocInfoPanel.add(f_SalesRep, new GridBagConstraints(3, 0, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		//	For Tax Amount
		f_lb_DocumentType = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_C_DocType_ID) + ":");
		f_lb_DocumentType.setFont(v_POSPanel.getPlainFont());
		//	Add
		v_DocInfoPanel.add(f_lb_DocumentType, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_DocumentType = new CLabel();
		f_DocumentType.setFont(v_POSPanel.getFont());
		f_lb_DocumentType.setLabelFor(f_DocumentType);
		//	Add
		v_DocInfoPanel.add(f_DocumentType, new GridBagConstraints(3, 1, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		//	For Grand Total
		f_lb_DocumentNo = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DocumentNo)+":");
		f_lb_DocumentNo.setFont(v_POSPanel.getPlainFont());
		//	Add
		v_DocInfoPanel.add(f_lb_DocumentNo, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_DocumentNo = new CLabel();
		f_DocumentNo.setFont(v_POSPanel.getFont());
		f_lb_DocumentNo.setLabelFor(f_DocumentNo);
		//	Change Size
		//	Add
		v_DocInfoPanel.add(f_DocumentNo,  new GridBagConstraints(3, 3, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		
		//	For Totals
		//	For Total Lines
		f_lb_TotalLines = new CLabel (Msg.getMsg(Env.getCtx(), "SubTotal") + ":");
		f_lb_TotalLines.setFont(v_POSPanel.getPlainFont());
		//	Add
		v_TotalPanel.add(f_lb_TotalLines, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_TotalLines = new CLabel();
		f_TotalLines.setFont(v_POSPanel.getFont());
		f_lb_TotalLines.setLabelFor(f_TotalLines);
		//	Add
		v_TotalPanel.add(f_TotalLines, new GridBagConstraints(3, 0, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		//	For Tax Amount
		f_lb_TaxAmount = new CLabel (Msg.translate(Env.getCtx(), I_C_OrderLine.COLUMNNAME_C_Tax_ID) + ":");
		f_lb_TaxAmount.setFont(v_POSPanel.getPlainFont());
		//	Add
		v_TotalPanel.add(f_lb_TaxAmount, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_TaxAmount = new CLabel();
		f_TaxAmount.setFont(v_POSPanel.getFont());
		f_lb_TaxAmount.setLabelFor(f_TaxAmount);
		//	Add
		v_TotalPanel.add(f_TaxAmount, new GridBagConstraints(3, 1, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		//	For Grand Total
		f_lb_GrandTotal = new CLabel (Msg.getMsg(v_POSPanel.getCtx(), "Total") + ":");
		f_lb_GrandTotal.setFont(v_POSPanel.getFont());
		//	Add
		v_TotalPanel.add(f_lb_GrandTotal, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	
		f_GrandTotal = new CLabel();
		f_GrandTotal.setFont(v_POSPanel.getBigFont());
		f_lb_GrandTotal.setLabelFor(f_GrandTotal);
		//	Change Size
		//	Add
		v_TotalPanel.add(f_GrandTotal,  new GridBagConstraints(3, 3, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		
		//	Add Doc Info
		v_HeaderPanel.add(v_DocInfoPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		//	Add to Header
		v_HeaderPanel.add(v_TotalPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		
		//	For Product
		String labelName = Msg.translate(Env.getCtx(), I_C_BPartner.COLUMNNAME_IsCustomer);
		//	
		f_BPartnerName = new POSTextField(labelName, v_POSPanel.getKeyboard());
		f_BPartnerName.setName(labelName);
		f_BPartnerName.setPlaceholder(labelName);
		f_BPartnerName.addActionListener(this);
		f_BPartnerName.requestFocusInWindow();
		f_BPartnerName.setFont(v_POSPanel.getFont());
		f_BPartnerName.setPreferredSize(new Dimension(530, 50));
		f_BPartnerName.setMinimumSize(new Dimension(530, 50));
		//	Add Header
		GridBagConstraints m_HeaderConstraint = new GridBagConstraints();
		m_HeaderConstraint.fill = GridBagConstraints.BOTH;
		m_HeaderConstraint.weightx = 1;
		m_HeaderConstraint.gridy = 0;
		add(v_HeaderPanel, m_HeaderConstraint);
		//	Add Product Name
		GridBagConstraints m_ProductConstraint = new GridBagConstraints();
		m_ProductConstraint.fill = GridBagConstraints.HORIZONTAL;
		m_ProductConstraint.weightx = 0;
		m_ProductConstraint.gridy = 1;
		m_ProductConstraint.gridwidth = 2;
		v_HeaderPanel.add(f_BPartnerName, m_ProductConstraint);
		//	Add Keyboard
		GridBagConstraints m_KeyboardConstraint = new GridBagConstraints();
		m_KeyboardConstraint.fill = GridBagConstraints.BOTH;
		m_KeyboardConstraint.weightx = 1;
		m_KeyboardConstraint.weighty = 1;
		m_KeyboardConstraint.gridy = 2;
		//	For Key Panel
		f_Keyboard = new POSKeyPanel(C_POSKeyLayout_ID, this);
		add(f_Keyboard, m_KeyboardConstraint);
		//	Refresh
		refreshPanel();
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
		if (v_POSPanel.hasOrder() 
				&& v_POSPanel.isCompleted()) {
			//	Show Product Info
			v_POSPanel.refreshProductInfo(key);
			return;
		}
		// Add line
		v_POSPanel.addLine(key.getM_Product_ID(), key.getQty());
		//	Show Product Info
		v_POSPanel.refreshProductInfo(key);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		//	Name
		if (e.getSource() == f_BPartnerName) {
			findBPartner();
		}
	}
	
	/**
	 * 	Find/Set BPartner
	 */
	private void findBPartner() {
		String query = f_BPartnerName.getText();
		//	
		if (query == null || query.length() == 0)
			return;
		
		// unchanged
		if (v_POSPanel.hasBPartner() 
				&& v_POSPanel.compareBPName(query))
			return;
		
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		boolean noNumber = true;
		char[] qq = query.toCharArray();
		for (int i = 0; i < qq.length; i++) {
			if (Character.isDigit(qq[i])) {
				noNumber = false;
				break;
			}
		} try {
			Integer.parseInt(query);
		} catch (Exception e) {
			allNumber = false;
		}
		String Value = query;
		String Name = (allNumber ? null : query);
		String EMail = (query.indexOf('@') != -1 ? query : null); 
		String Phone = (noNumber ? null : query);
		String City = null;
		//
		MBPartnerInfo[] results = MBPartnerInfo.find(m_ctx, Value, Name, 
			/*Contact, */null, EMail, Phone, City);
		
		//	Set Result
		if (results.length == 1) {
			MBPartner bp = MBPartner.get(m_ctx, results[0].getC_BPartner_ID());
			v_POSPanel.setC_BPartner_ID(bp.getC_BPartner_ID());
			f_BPartnerName.setText(bp.getName());
			return;
		} else {	//	more than one
			QueryBPartner qt = new QueryBPartner(v_POSPanel);
			qt.addOptionListener(this);
			qt.setResults(results);
			qt.showView();
		}
		//	Default return
		return;
	}	//	findBPartner

	@Override
	public void refreshPanel() {
		log.fine("RefreshPanel");
		if (!v_POSPanel.hasOrder()) {
			//	Document Info
			f_SalesRep.setText(v_POSPanel.getSalesRepName());
			f_DocumentType.setText(Msg.getMsg(v_POSPanel.getCtx(), "Order"));
			f_DocumentNo.setText(Msg.getMsg(v_POSPanel.getCtx(), "New"));
			f_TotalLines.setText(v_POSPanel.getNumberFormat().format(Env.ZERO));
			f_GrandTotal.setText(v_POSPanel.getNumberFormat().format(Env.ZERO));
			f_TaxAmount.setText(v_POSPanel.getNumberFormat().format(Env.ZERO));
			f_BPartnerName.setText(null);
		} else {
			String currencyISO_Code = v_POSPanel.getCurSymbol();
			BigDecimal m_TotalLines = v_POSPanel.getTotalLines();
			BigDecimal m_GrandTotal = v_POSPanel.getGrandTotal();
			BigDecimal m_TaxAmt = m_GrandTotal.subtract(m_TotalLines);
			//	Set Values
			//	Document Info
			f_SalesRep.setText(v_POSPanel.getSalesRepName());
			f_DocumentType.setText(v_POSPanel.getDocumentTypeName());
			f_DocumentNo.setText(v_POSPanel.getDocumentNo());
			f_TotalLines.setText(currencyISO_Code + " " + v_POSPanel.getNumberFormat().format(m_TotalLines));
			f_GrandTotal.setText(currencyISO_Code + " " + v_POSPanel.getNumberFormat().format(m_GrandTotal));
			f_TaxAmount.setText(currencyISO_Code + " " + v_POSPanel.getNumberFormat().format(m_TaxAmt));
			f_BPartnerName.setText(v_POSPanel.getBPName());
		}
		//	Repaint
		v_TotalPanel.validate();
		v_TotalPanel.repaint();
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		if(v_POSPanel.hasOrder()) {
			//	When order is not completed, you can change BP
			f_BPartnerName.setEnabled(!v_POSPanel.isCompleted());
		} else {
			f_BPartnerName.setEnabled(true);
		}
	}

	@Override
	public void okAction(I_POSQuery query) {
		if (query.getRecord_ID() > 0) {
			f_BPartnerName.setText(query.getValue());
			if(!v_POSPanel.hasOrder()) {
				v_POSPanel.newOrder(query.getRecord_ID());
			} else {
				v_POSPanel.setC_BPartner_ID(query.getRecord_ID());
			}
			//	
			log.fine("C_BPartner_ID=" + query.getRecord_ID());
		}
	}

	@Override
	public void cancelAction(I_POSQuery query) {
		//	Nothing
	}
}	//	POSProductPanel