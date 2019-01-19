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

import java.text.DecimalFormat;

import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.I_C_Order;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MPOSKey;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Style;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSDocumentPanel extends WPOSSubPanel implements POSKeyListener, POSPanelInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;
	
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSDocumentPanel(WPOS posPanel) {
		super (posPanel);
	}	//	PosSubFunctionKeys
	
	/** Fields               */
	private WPOSTextField	bPartnerName;
	private Label 			salesRep;
	private Label	 		totalLines;
	private Label	 		taxAmount;
	private Label	 		grandTotal;
	private Label	 		documentType;
	private Label 			documentNo;
	private Label 			documentStatus;
	private Label 			documentDate;
	private boolean			isKeyboard;

	/**	Format				*/
	private DecimalFormat	m_Format;
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(WPOSDocumentPanel.class);
	/**	Panels				*/
	private Caption 		v_TitleBorder;
	private Caption 		v_TitleInfo;
	private Groupbox 		v_TotalsGroup;
	private Groupbox 		v_InfOrderGroup;
	private Grid 			v_TotalsPanel;
	private Grid 			v_OrderPanel;
	private Grid 			v_GroupPanel;
	/** Collect 			*/
	private WCollect 		collectPayment;
	/** Scala Dialog 		*/
	private WPOSScalesPanel 	scalesPanel;
	private WPOSKeyPanel 	keyboardPanel;
	private Row 			row; 
	
	@Override
	public void init(){
		int C_POSKeyLayout_ID = posPanel.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		isKeyboard = false;
		v_TotalsPanel = GridFactory.newGridLayout();
		v_TotalsPanel.setHeight("100%");
		v_TotalsPanel.setStyle("width:130%;height:100%");
		v_OrderPanel = GridFactory.newGridLayout();
		
		v_OrderPanel.setStyle("border: none; width:130%; height:100%");
		v_GroupPanel = GridFactory.newGridLayout();
		v_GroupPanel.setWidth("100%");
		v_GroupPanel.setHeight("auto");
		
		//  Define the criteria rows and grid  
		Rows rows = new Rows();
		//
		row = new Row();
		rows.appendChild(row);
		rows.setHeight("100%");
		rows.setWidth("100%");
		v_TotalsGroup = new Groupbox();
		v_InfOrderGroup = new Groupbox();
		v_InfOrderGroup.appendChild(v_OrderPanel);
		v_InfOrderGroup.setWidth("85%");
		row.appendChild(v_InfOrderGroup);
		row.appendChild(v_TotalsGroup);
		// BP
		bPartnerName = new WPOSTextField(Msg.translate(Env.getCtx(), "IsCustomer"), posPanel.getKeyboard());
		bPartnerName.setHeight("35px");
		bPartnerName.setStyle(WPOS.FONTSIZEMEDIUM+"; font-weight:bold");
		bPartnerName.setWidth("97%");
		bPartnerName.addEventListener(this);
		
		row = rows.newRow();
		row.setSpans("2");
		row.setHeight("10px");
		row.appendChild(bPartnerName);
		
		
		v_GroupPanel.appendChild(rows);
		v_GroupPanel.setStyle("Overflow:hidden;");
		v_OrderPanel.setStyle("Overflow:hidden;");
		v_TotalsGroup.appendChild(v_TotalsPanel);
		v_TotalsGroup.setWidth("65%");
		
		v_TitleBorder = new Caption(Msg.getMsg(Env.getCtx(), "Totals"));
		Style style = new Style();
		style.setContent(".z-fieldset { margin-left:-5px }"
		    + ".z-combo-item-text { Font-family:Courier New}"
				+ ".z-fieldset legend {font-size: medium; font-weight:bold; width:100%;} "
				+ ".input-search table tr td input{font-size: medium; font-weight:bold; width:100%; height:20px;}"
				+ ".Table-OrderLine tr th div{font-size: 13px; padding:5px} "
				+ ".Table-OrderLine tr td div, .Table-OrderLine tr td div input{font-size: 13; height:auto}"
				+ ".label-description {"+WPOS.FONTSIZEMEDIUM+" display:block; height:15px; font-weight:bold; width: 415px; overflow:hidden;}"
				+ ".fontLarge label  {font-size: medium;}"
				+ "div.z-grid-body {-moz-box-shadow: 0 0 0px #888;-webkit-box-shadow: 0 0 0px #888;box-shadow: 0 0 0px #888;}");
		style.setParent(v_TitleBorder);
		v_TotalsGroup.appendChild(v_TitleBorder);

		v_TitleInfo = new Caption(Msg.getMsg(Env.getCtx(), "InfoOrder"));
		
		v_InfOrderGroup.appendChild(v_TitleInfo);

		rows = null;
		row = null;
		rows = v_OrderPanel.newRows();

		appendChild(v_GroupPanel);

		//
		row = rows.newRow();
		row.setHeight("10px");
		
		Label f_lb_DocumentNo = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DocumentNo) + ":");
		f_lb_DocumentNo.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(f_lb_DocumentNo.rightAlign());
		
		documentNo = new Label();
		documentNo.setStyle(WPOS.FONTSIZEMEDIUM+"; font-weight:bold");
		row.appendChild(documentNo.rightAlign());
		
		row = rows.newRow();
		row.setHeight("20px");
		row.setWidth("100%");
		Label f_lb_DocumentType = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_C_DocType_ID) + ":");
		f_lb_DocumentType.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(f_lb_DocumentType.rightAlign());
		
		documentType = new Label();
		documentType.setClass("label-description");
		documentType.setStyle(WPOS.FONTSIZEMEDIUM+"; font-weight:bold; width:auto !important;max-width:225px !important; white-space:pre;");
		row.appendChild(documentType.rightAlign());
		
		row = rows.newRow();
		row.setHeight("20px");
		
		Label f_lb_DocumentStatus = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DocStatus) + ":");
		f_lb_DocumentStatus.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(f_lb_DocumentStatus.rightAlign());
		documentStatus= new Label();
		documentStatus.setStyle(WPOS.FONTSIZEMEDIUM+"; font-weight:bold");
		row.appendChild(documentStatus.rightAlign());
		
		row = rows.newRow();
		row.setHeight("20px");
		
		Label f_lb_SalesRep = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_SalesRep_ID) + ":");
		f_lb_SalesRep.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(f_lb_SalesRep.rightAlign());
		
		salesRep = new Label(posPanel.getSalesRepName());
		salesRep.setStyle(WPOS.FONTSIZEMEDIUM+"; font-weight:bold");
		row.appendChild(salesRep.rightAlign());
		
		
		row = rows.newRow();
		rows = v_TotalsPanel.newRows();

		//
		row = rows.newRow();
		row.setHeight("10px");

		Label lDocumentDate = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DateOrdered) + ":");
		lDocumentDate.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(lDocumentDate);
		
		documentDate = new Label();
		documentDate.setStyle(WPOS.FONTSIZEMEDIUM+"; font-weight:bold");
		row.appendChild(documentDate.rightAlign());
		
		row = rows.newRow();
		row.setHeight("10px");
		
		Label lNet = new Label (Msg.translate(Env.getCtx(), "SubTotal")+":");
		lNet.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(lNet);
		
		totalLines = new Label(String.valueOf(DisplayType.Amount));
		totalLines.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(totalLines.rightAlign());
		
		totalLines.setText("0.00");
		
		row = rows.newRow();
		row.setHeight("20px");
		
		Label lTax = new Label (Msg.translate(Env.getCtx(), "C_Tax_ID")+":");
		lTax.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(lTax);
		taxAmount = new Label(String.valueOf(DisplayType.Amount));
		taxAmount.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(taxAmount.rightAlign());
		taxAmount.setText(Env.ZERO.toString());
		
		row = rows.newRow();
		Label lTotal = new Label (Msg.translate(Env.getCtx(), "GrandTotal")+":");
		lTotal.setStyle(WPOS.FONTSIZEMEDIUM);
		row.appendChild(lTotal);
		grandTotal = new Label(String.valueOf(DisplayType.Amount));
		row.appendChild(grandTotal.rightAlign());
		grandTotal.setText(Env.ZERO.toString());
		grandTotal.setStyle("Font-size:1.9em;font-weight:bold");

		// Center Panel
		Grid layout = GridFactory.newGridLayout();

		org.adempiere.webui.component.Panel centerPanel = new org.adempiere.webui.component.Panel();
		appendChild(centerPanel);
		centerPanel.setStyle("overflow:auto; height:75%");
		centerPanel.appendChild(layout);
		layout.setWidth("100%");
		layout.setStyle("");
		
		rows = layout.newRows();
		
	
		keyboardPanel = new WPOSKeyPanel(C_POSKeyLayout_ID, this);
		row = rows.newRow();
		row.setHeight("50%");
		row.setSpans("4");
		row.appendChild(keyboardPanel);
		
		collectPayment = new WCollect(posPanel);

		scalesPanel = new WPOSScalesPanel(posPanel);
		scalesPanel.hidePanel();
		//add(scalesPanel.getPanel(), scalesConstraint);
		
		//	Refresh
		totalLines.setText(m_Format.format(Env.ZERO));
		grandTotal.setText(m_Format.format(Env.ZERO));
		taxAmount.setText(m_Format.format(Env.ZERO));
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
			FDialog.error(posPanel.getWindowNo(), this, exception.getLocalizedMessage());
		}
		//	Show Product Info
		posPanel.refreshProductInfo(key);
		return;
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		//	Name
		if(e.getTarget().equals(bPartnerName.getComponent(WPOSTextField.SECONDARY)) && e.getName().equals(Events.ON_FOCUS) && !isKeyboard){
			isKeyboard = true;
			if(!bPartnerName.showKeyboard()){
				findBPartner();
			}
			if(posPanel.getKeyboard() == null){
				bPartnerName.setValue(" ");
				findBPartner();
			}
			bPartnerName.setFocus(true);
		}
		if(e.getTarget().equals(bPartnerName.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
			isKeyboard = false;
		}
	}
	
	/**
	 * 	Find/Set BPartner
	 */
	private void findBPartner() {
		String query = bPartnerName.getText();
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
			contact, name, name2 , eMail, phone, city);

		//	Set Result
		if (results.length == 1) {
			MBPartner bp = MBPartner.get(ctx, results[0].getC_BPartner_ID());
			posPanel.configureBPartner(bp.getC_BPartner_ID());
			bPartnerName.setText(bp.getName()+"");
		} else {	//	more than one
			changeBusinessPartner(results);
		}
		//	Default return
		posPanel.refreshPanel();
		return;
	}	//	findBPartner

	@Override
	public void refreshPanel() {
		log.fine("RefreshPanel");
		if (!posPanel.hasOrder()) {
			//	Document Info
			v_TitleBorder.setLabel(Msg.getMsg(Env.getCtx(), "Totals"));
			salesRep.setText(posPanel.getSalesRepName());
			documentType.setText(Msg.getMsg(posPanel.getCtx(), "Order"));
			documentNo.setText(Msg.getMsg(posPanel.getCtx(), "New"));
			documentStatus.setText("");
			documentDate.setText("");
			totalLines.setText(posPanel.getNumberFormat().format(Env.ZERO));
			grandTotal.setText(posPanel.getNumberFormat().format(Env.ZERO));
			taxAmount.setText(posPanel.getNumberFormat().format(Env.ZERO));
			bPartnerName.setText(null);
		} else {
			//	Set Values
			//	Document Info
			String currencyISOCode = posPanel.getCurSymbol();
			v_TitleBorder.setLabel(Msg.getMsg(Env.getCtx(), "Totals") + " (" +currencyISOCode + ")");
			salesRep.setText(posPanel.getSalesRepName());
			documentType.setText(posPanel.getDocumentTypeName());
			documentNo.setText(posPanel.getDocumentNo());
			documentStatus.setText(posPanel.getOrder().getDocStatusName());
			documentDate.setText(posPanel.getDateOrderedForView());
			totalLines.setText(posPanel.getTotaLinesForView());
			grandTotal.setText(posPanel.getGrandTotalForView());
			taxAmount.setText(posPanel.getTaxAmtForView());
			bPartnerName.setText(posPanel.getBPName());
		}
		//	Repaint
		v_TotalsPanel.invalidate();
		v_OrderPanel.invalidate();
		v_GroupPanel.invalidate();
	}


	/**
	 * 	Change in Order the Business Partner, including Price list and location
	 *  In Order and POS
	 *  @param results
	 */
	public boolean changeBusinessPartner(MBPartnerInfo[] results) {
		// Change to another BPartner
		WQueryBPartner qt = new WQueryBPartner(posPanel);

		qt.setResults(results);
		AEnv.showWindow(qt);
		if (qt.getRecord_ID() > 0) {
			bPartnerName.setText(posPanel.getBPName());
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(qt.getRecord_ID());
				posPanel.refreshPanel();
			} else {
				posPanel.configureBPartner(qt.getRecord_ID());
			}
			log.fine("C_BPartner_ID=" + qt.getRecord_ID());
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
			bPartnerName.setReadonly(posPanel.isCompleted());
		} else {
			bPartnerName.setReadonly(false);
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
	 * @return WCollect
	 */
	public WCollect getCollectPayment()
	{
		row.removeChild(keyboardPanel);
		row.setHeight("50%");
		row.setSpans("4");
		row.appendChild(collectPayment.getPanel());
		return collectPayment.load(posPanel);
	}

	public void closeCollectPayment(){
		row.removeChild(collectPayment.getPanel());
		row.setHeight("50%");
		row.setSpans("4");
		row.appendChild(keyboardPanel);
	}
	public WPOSScalesPanel getScalesPanel()
	{
		return scalesPanel;
	}

	public WPOSKeyPanel getKeyboard()
	{
		return keyboardPanel;
	}

}