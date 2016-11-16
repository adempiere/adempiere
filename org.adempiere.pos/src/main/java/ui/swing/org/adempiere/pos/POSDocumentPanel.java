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

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.ADialog;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MPOSKey;
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
 *  @author victor.perez@e-evolution.com , http://www.e-evolution.com
 *  <li> Implement best practices
 *  @version $Id: SubFunctionKeys.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 */
public class POSDocumentPanel extends POSSubPanel
	implements POSKeyListener, ActionListener, POSPanelInterface, POSQueryListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSDocumentPanel(VPOS posPanel) {
		super (posPanel);
	}	//	POSDocumentPanel
	
	/**	Header Panel		*/
	private CPanel 			headerPanel;
	/**	Total Panel			*/;
	private CPanel 			totalPanel;
	/**	Doc Info Panel		*/
	private CPanel 			documentInfoPanel;
	/**	Total Title Border	*/
	private TitledBorder 	totalTitle;
	/**	Doc Title Border	*/
	private TitledBorder 	documentTitle;
	/**	Sales Representative*/
	private CLabel 			labelSalesRep;
	private CLabel 			fieldSalesRep;
	/**	Document Type		*/
	private CLabel 			labelDocumentType;
	private CLabel 			fieldDocumentType;
	/**	Document No			*/
	private CLabel 			labelDocumentNo;
	private CLabel 			fieldDocumentNo;
	/** Document Status		*/
	private CLabel 			labelDocumentStatus;
	private CLabel 			fieldDocumentStatus;

	/** Document Status		*/
	private CLabel 			labelDocumentDate;
	private CLabel 			fieldDocumentDate;


	/**	Total Lines			*/
	private CLabel 			labelTotalLines;
	private CLabel 			fieldTotalLines;
	/**	Tax Amount			*/
	private CLabel 			labelTaxAmount;
	private CLabel 			fieldTaxAmount;
	/**	Grand Total			*/
	private CLabel 			labelGrandTotal;
	private CLabel 			fieldGrandTotal;
	/**	Product Name		*/
	private POSTextField 	fieldPartnerName;
	/**	Keyboard			*/
	private POSKeyPanel 	keyboardPanel;
	/** Collect 			*/
	private VCollect 		collectPayment;

	/** Scala Dialog 		*/
	private POSScalesPanel 	scalesPanel;
	/**	Logger				*/
	private static CLogger logger = CLogger.getCLogger(POSDocumentPanel.class);

	/**
	 * 	Initialize
	 */
	public void init() {
		int posKeyLayoutId = posPanel.getC_POSKeyLayout_ID();
		//	Set Layout
		setLayout(new GridBagLayout());
		//	Set Right Padding
		int rightPadding = 0;
		//	Document Panel
		//	Set Font and Format
		headerPanel = new CPanel(new GridBagLayout());
		documentInfoPanel = new CPanel(new GridBagLayout());
		totalPanel = new CPanel(new GridBagLayout());
		//	For Doc Title Border
		documentTitle = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "InfoOrder"));
		documentTitle.setTitleFont(posPanel.getFont());
		documentTitle.setTitleColor(AdempierePLAF.getTextColor_Label());
		documentInfoPanel.setBorder(documentTitle);
		//	For Total Title Border
		totalTitle = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "Totals"));
		totalTitle.setTitleFont(posPanel.getFont());
		totalTitle.setTitleColor(AdempierePLAF.getTextColor_Label());
		totalPanel.setBorder(totalTitle);
		//	For Document Info
		labelDocumentNo = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DocumentNo) + ":");
		labelDocumentNo.setFont(posPanel.getPlainFont());
		//	Add
		documentInfoPanel.add(labelDocumentNo, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldDocumentNo = new CLabel();
		fieldDocumentNo.setFont(posPanel.getFont());
		labelDocumentNo.setLabelFor(fieldDocumentNo);
		//	Change Size
		//	Add
		documentInfoPanel.add(fieldDocumentNo,  new GridBagConstraints(3, 0, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));

		//	For Tax Amount
		labelDocumentType = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_C_DocType_ID) + ":");
		labelDocumentType.setFont(posPanel.getPlainFont());
		//	Add
		documentInfoPanel.add(labelDocumentType, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldDocumentType = new CLabel();
		fieldDocumentType.setFont(posPanel.getFont());
		labelDocumentType.setLabelFor(fieldDocumentType);
		//	Add
		documentInfoPanel.add(fieldDocumentType, new GridBagConstraints(3, 1, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));

		//	Document status
		labelDocumentStatus = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DocStatus) + ":");
		labelDocumentStatus.setFont(posPanel.getPlainFont());
		//	Add
		documentInfoPanel.add(labelDocumentStatus, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldDocumentStatus = new CLabel();
		fieldDocumentStatus.setFont(posPanel.getFont());
		labelDocumentStatus.setLabelFor(fieldDocumentStatus);
		//	Add
		documentInfoPanel.add(fieldDocumentStatus, new GridBagConstraints(3, 3, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));


		//	For Sales Representative
		labelSalesRep = new CLabel (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_SalesRep_ID) + ":");
		labelSalesRep.setFont(posPanel.getPlainFont());
		//	Add
		documentInfoPanel.add(labelSalesRep, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldSalesRep = new CLabel();
		fieldSalesRep.setFont(posPanel.getFont());
		labelSalesRep.setLabelFor(fieldSalesRep);
		//	Add
		documentInfoPanel.add(fieldSalesRep, new GridBagConstraints(3, 4, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));


		//	For Grand Total
		//	For Totals
		//	For Total Lines
		labelDocumentDate = new CLabel (Msg.translate(Env.getCtx(), "Date") + ":");
		labelDocumentDate.setFont(posPanel.getPlainFont());
		//	Add
		totalPanel.add(labelDocumentDate, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldDocumentDate = new CLabel();
		fieldDocumentDate.setFont(posPanel.getFont());
		labelDocumentDate.setLabelFor(fieldDocumentDate);

		//	Add
		totalPanel.add(fieldDocumentDate, new GridBagConstraints(3, 0, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));


		labelTotalLines = new CLabel (Msg.getMsg(Env.getCtx(), "SubTotal") + ":");
		labelTotalLines.setFont(posPanel.getPlainFont());
		//	Add
		totalPanel.add(labelTotalLines, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldTotalLines = new CLabel();
		fieldTotalLines.setFont(posPanel.getFont());
		labelTotalLines.setLabelFor(fieldTotalLines);
		//	Add
		totalPanel.add(fieldTotalLines, new GridBagConstraints(3, 1, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));
		//	For Tax Amount
		labelTaxAmount = new CLabel (Msg.translate(Env.getCtx(), I_C_OrderLine.COLUMNNAME_C_Tax_ID) + ":");
		labelTaxAmount.setFont(posPanel.getPlainFont());
		//	Add
		totalPanel.add(labelTaxAmount, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldTaxAmount = new CLabel();
		fieldTaxAmount.setFont(posPanel.getFont());
		labelTaxAmount.setLabelFor(fieldTaxAmount);
		//	Add
		totalPanel.add(fieldTaxAmount, new GridBagConstraints(3, 2, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));
		//	For Grand Total
		labelGrandTotal = new CLabel (Msg.getMsg(posPanel.getCtx(), "Total") + ":");
		labelGrandTotal.setFont(posPanel.getFont());
		//	Add
		totalPanel.add(labelGrandTotal, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
		fieldGrandTotal = new CLabel();
		fieldGrandTotal.setFont(posPanel.getBigFont());
		labelGrandTotal.setLabelFor(fieldGrandTotal);
		//	Change Size
		//	Add
		totalPanel.add(fieldGrandTotal,  new GridBagConstraints(3, 4, 1, 1, 1, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, rightPadding), 0, 0));
		
		//	Add Doc Info
		headerPanel.add(documentInfoPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		//	Add to Header
		headerPanel.add(totalPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		
		//	For Product
		String labelName = Msg.translate(Env.getCtx(), I_C_BPartner.COLUMNNAME_IsCustomer);
		//	
		fieldPartnerName = new POSTextField(labelName, posPanel.getKeyboard());
		fieldPartnerName.setName(labelName);
		fieldPartnerName.setPlaceholder(labelName);
		fieldPartnerName.addActionListener(this);
		fieldPartnerName.setFocusable(true);
		fieldPartnerName.setFont(posPanel.getFont());
		fieldPartnerName.setPreferredSize(new Dimension(530, 50));
		fieldPartnerName.setMinimumSize(new Dimension(530, 50));
		//	Add Header
		GridBagConstraints headerConstraint = new GridBagConstraints();
		headerConstraint.fill = GridBagConstraints.BOTH;
		headerConstraint.weightx = 1;
		headerConstraint.gridy = 0;
		add(headerPanel, headerConstraint);
		//	Add Product Name
		GridBagConstraints partnerConstraint = new GridBagConstraints();
		partnerConstraint.fill = GridBagConstraints.HORIZONTAL;
		partnerConstraint.weightx = 0;
		partnerConstraint.gridy = 1;
		partnerConstraint.gridwidth = 2;
		headerPanel.add(fieldPartnerName, partnerConstraint);
		//	Add Keyboard
		GridBagConstraints keyboardConstraint = new GridBagConstraints();
		keyboardConstraint.fill = GridBagConstraints.BOTH;
		keyboardConstraint.weightx = 1;
		keyboardConstraint.weighty = 1;
		keyboardConstraint.gridy = 2;
		//	For Key Panel
		keyboardPanel = new POSKeyPanel(posKeyLayoutId, this);
		add(keyboardPanel, keyboardConstraint);

		GridBagConstraints collectPaymentConstraint = new GridBagConstraints();
		collectPaymentConstraint.fill = GridBagConstraints.BOTH;
		collectPaymentConstraint.fill = GridBagConstraints.BOTH;
		collectPaymentConstraint.weightx = 1;
		collectPaymentConstraint.weighty = 1;
		collectPaymentConstraint.gridy = 2;

		collectPayment = new VCollect(posPanel);
		collectPayment.hidePanel();
		add(collectPayment.getPanel(), collectPaymentConstraint);

		GridBagConstraints scalesConstraint = new GridBagConstraints();
		scalesConstraint.fill = GridBagConstraints.BOTH;
		scalesConstraint.fill = GridBagConstraints.BOTH;
		scalesConstraint.weightx = 1;
		scalesConstraint.weighty = 1;
		scalesConstraint.gridy = 2;

		scalesPanel = new POSScalesPanel(posPanel);
		scalesPanel.hidePanel();
		add(scalesPanel.getPanel(), scalesConstraint);

		//	Refresh
		refreshPanel();
	}	//	init

	/**
	 * Call back from key panel
	 */
	@Override
	public void keyReturned(MPOSKey key) {
		// processed order
		if (posPanel.hasOrder()
				&& posPanel.isCompleted()) {
			//	Show Product Info
			posPanel.refreshProductInfo(key);
			return;
		}
		// Add line
		try{
			//  Issue 139
			posPanel.setAddQty(true);
			posPanel.addOrUpdateLine(key.getM_Product_ID(), key.getQty());
			posPanel.refreshPanel();
			posPanel.changeViewPanel();
			posPanel.getMainFocus();
		} catch (Exception exception) {
			ADialog.error(posPanel.getWindowNo(), this, exception.getLocalizedMessage());
		}
		//	Show Product Info
		posPanel.refreshProductInfo(key);
		return;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		//	Name
		if (e.getSource() == fieldPartnerName) {
			if(posPanel.isDrafted() || posPanel.isInProgress())  {
				findBPartner();		
			}
		}
	}

	@Override
	public void okAction(POSQueryInterface query) {
		if (query.getRecord_ID() > 0) {
			fieldPartnerName.setText(query.getValue());
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(query.getRecord_ID());
			} else {
				posPanel.configureBPartner(query.getRecord_ID());
			}
			//
			logger.fine("C_BPartner_ID=" + query.getRecord_ID());
		}
	}

	@Override
	public void cancelAction(POSQueryInterface query) {
		//	Nothing
	}

	/**
	 * 	Find/Set BPartner
	 */
	private void findBPartner() {
		String query = fieldPartnerName.getText();
		//	
		if (query == null || query.length() == 0)
			return;
		
		// unchanged
		if (posPanel.hasBPartner()
				&& posPanel.compareBPName(query))
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
		String value = query;
		String taxId = query;
		String name = (allNumber ? null : query);
		String name2 = (allNumber ? null : query);
		String contact = (allNumber ? null : query);
		String eMail = (query.indexOf('@') != -1 ? query : null);
		String phone = (noNumber ? null : query);
		String city = null;
		//
		MBPartnerInfo[] results = MBPartnerInfo.find(ctx, value, taxId,
			name, name2, contact , eMail, phone, city);
		
		//	Set Result
		if (results.length == 1) {
			MBPartner bp = MBPartner.get(ctx, results[0].getC_BPartner_ID());
			posPanel.configureBPartner(bp.getC_BPartner_ID());
			fieldPartnerName.setText(bp.getName());
		} else {	//	more than one
			changeBusinessPartner(results);
		}
		//	Default return
		posPanel.refreshPanel();
		return;
	}	//	findBPartner

	@Override
	public void refreshPanel() {
		logger.fine("RefreshPanel");
		if (!posPanel.hasOrder()) {
			//	Document Info
			totalTitle.setTitle(Msg.getMsg(Env.getCtx(), "Totals"));
			fieldSalesRep.setText(posPanel.getSalesRepName());
			fieldDocumentType.setText(Msg.getMsg(posPanel.getCtx(), "Order"));
			fieldDocumentNo.setText(Msg.getMsg(posPanel.getCtx(), "New"));
			fieldDocumentStatus.setText("");
			fieldDocumentDate.setText("");
			fieldTotalLines.setText(posPanel.getNumberFormat().format(Env.ZERO));
			fieldGrandTotal.setText(posPanel.getNumberFormat().format(Env.ZERO));
			fieldTaxAmount.setText(posPanel.getNumberFormat().format(Env.ZERO));
			fieldPartnerName.setText(null);
		} else {
			//	Set Values
			//	Document Info
			String currencyISOCode = posPanel.getCurSymbol();
			totalTitle.setTitle(Msg.getMsg(Env.getCtx(), "Totals") + " (" +currencyISOCode + ")");
			fieldSalesRep.setText(posPanel.getSalesRepName());
			fieldDocumentType.setText(posPanel.getDocumentTypeName());
			fieldDocumentNo.setText(posPanel.getDocumentNo());
			fieldDocumentStatus.setText(posPanel.getOrder().getDocStatusName());
			fieldDocumentDate.setText(posPanel.getDateOrderedForView());
			fieldTotalLines.setText(posPanel.getTotaLinesForView());
			fieldGrandTotal.setText(posPanel.getGrandTotalForView());
			fieldTaxAmount.setText(posPanel.getTaxAmtForView());
			fieldPartnerName.setText(posPanel.getBPName());
		}
		//	Repaint
		totalPanel.invalidate();
		totalPanel.repaint();
	}

	/**
	 * 	Change in Order the Business Partner, including Price list and location
	 *  In Order and POS
	 *  @param results
	 */
	public boolean changeBusinessPartner(MBPartnerInfo[] results) {
		// Change to another BPartner
		QueryBPartner qt = new QueryBPartner(posPanel);
		qt.addOptionListener(this);
		qt.setResults(results);
		qt.showView();
		if (qt.getRecord_ID() > 0) {
			fieldPartnerName.setText(posPanel.getBPName());
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(qt.getRecord_ID());
				posPanel.refreshPanel();
			} else {
				posPanel.configureBPartner(qt.getRecord_ID());
			}
			logger.fine("C_BPartner_ID=" + qt.getRecord_ID());
			return true;
		}
		return false;
	}

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		if(posPanel.hasOrder()) {
			//	When order is not completed, you can change BP
			fieldPartnerName.setEnabled(!posPanel.isCompleted());
		} else {
			fieldPartnerName.setEnabled(true);
		}
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}

	/**
	 * Get Collect Payment Panel
	 * @return VCollect
	 */
	public VCollect getCollectPayment()
	{




		return collectPayment.load(posPanel);
	}

	public POSScalesPanel getScalesPanel()
	{
		return scalesPanel;
	}

	public POSKeyPanel getKeyboard()
	{
		return keyboardPanel;
	}

}