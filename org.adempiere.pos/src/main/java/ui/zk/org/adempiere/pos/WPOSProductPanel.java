package org.adempiere.pos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.model.I_C_Order;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MPOSKey;
import org.compiere.pos.PosKeyListener;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Style;


public class WPOSProductPanel extends WPosSubPanel implements PosKeyListener, I_POSPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;
	
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSProductPanel (WPOS posPanel) {
		super (posPanel);
	}	//	PosSubFunctionKeys
	
	private WPosTextField	f_BPartnerName;
	private boolean			isKeyboard;
	private Label	 		f_TotalLines;
	private Label	 		f_TaxAmount;
	private Label	 		f_GrandTotal;
	private Label	 		f_DocumentType;
	private Label 			f_DocumentNo;
	private Label			l_BPartner;

	/**	Format				*/
	private DecimalFormat	m_Format;
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(WPOSProductPanel.class);
	private Caption 		v_TitleBorder;
	private Caption 		v_TitleInfo;
	private Groupbox 		v_TotalsGroup;
	private Groupbox 		v_InfOrderGroup;
	private Grid 			v_TotalsPanel;
	private Grid 			v_OrderPanel;
	private Grid 			v_GroupPanel;

	private Label 			f_SalesRep;
	
	@Override
	public void init(){
		int C_POSKeyLayout_ID = p_pos.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		isKeyboard = false;
		v_TotalsPanel = GridFactory.newGridLayout();
		v_TotalsPanel.setWidth("325px");
		v_TotalsPanel.setHeight("100%");
		
		v_OrderPanel = GridFactory.newGridLayout();
		v_OrderPanel.setWidth("300px");
		v_OrderPanel.setHeight("100%");

		v_GroupPanel = GridFactory.newGridLayout();
		v_GroupPanel.setWidth("100%");
		v_GroupPanel.setHeight("100%");
		
		//  Define the criteria rows and grid  
		Rows rows = new Rows();
		//
		Row row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1");
		
		v_TotalsGroup = new Groupbox();
		v_TotalsGroup.setWidth("100%");
		v_InfOrderGroup = new Groupbox();
		v_InfOrderGroup.setWidth("80%");
		v_InfOrderGroup.appendChild(v_OrderPanel);

		row.appendChild(v_InfOrderGroup);
		row.appendChild(v_TotalsGroup);
		// BP
		l_BPartner = new Label(Msg.translate(Env.getCtx(), "IsCustomer"));
		
		f_BPartnerName = new WPosTextField(p_pos.getOSK_KeyLayout_ID());
		f_BPartnerName.setHeight("35px");
		f_BPartnerName.setStyle("Font-size:medium; font-weight:bold");
		f_BPartnerName.setWidth("97%");
		f_BPartnerName.setValue(l_BPartner.getValue());
		f_BPartnerName.addEventListener(this);
		
		row = new Row();
		rows.appendChild(row);
		row.setSpans("2");
		row.appendChild(f_BPartnerName);
		
		v_GroupPanel.appendChild(rows);
		v_GroupPanel.setStyle("Overflow:hidden;");
		v_OrderPanel.setStyle("Overflow:hidden;");
		v_TotalsGroup.appendChild(v_TotalsPanel);
		
		v_TitleBorder = new Caption(Msg.getMsg(Env.getCtx(), "Totals"));
		Style style = new Style();
		style.setContent(".z-fieldset legend {font-size: medium; font-weight:bold;} "
				+ ".Table-OrderLine tr th div{font-size: 14px; font-weight:bold; padding:5px} "
				+ ".Table-OrderLine tr td div, .Table-OrderLine tr td div input{font-size: medium; height:auto}"
				+ ".label-description {font-size: medium; display:block; height:15px; font-weight:bold; width: 350px; overflow:hidden;}"
				+ ".fontLarge label  {font-size: medium;}");
		style.setParent(v_TitleBorder);
		v_TotalsGroup.appendChild(v_TitleBorder);

		v_TitleInfo = new Caption(Msg.getMsg(Env.getCtx(), "InfoOrder"));
		v_InfOrderGroup.appendChild(v_TitleInfo);
		
		rows = null;
		row = null;
		rows = v_OrderPanel.newRows();

		North north = new North();
		
		north.appendChild(v_GroupPanel);
		north.setStyle("border: none; width:50%; height:170px");
		//
		row = rows.newRow();
		row.setHeight("10px");
		
		Label f_lb_SalesRep = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_SalesRep_ID) + ":");
		f_lb_SalesRep.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_lb_SalesRep.rightAlign());
		
		f_SalesRep = new Label(v_POSPanel.getSalesRepName());
		f_SalesRep.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_SalesRep.rightAlign());
		
		row = rows.newRow();
		row.setHeight("30px");
		
		Label f_lb_DocumentType = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_C_DocType_ID) + ":");
		f_lb_DocumentType.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_lb_DocumentType.rightAlign());
		
		f_DocumentType = new Label();
		f_DocumentType.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_DocumentType.rightAlign());
		
		row = rows.newRow();
		row.setHeight("30px");
		
		Label f_lb_DocumentNo = new Label (Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_DocumentNo) + ":");
		f_lb_DocumentNo.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_lb_DocumentNo.rightAlign());
		
		f_DocumentNo = new Label();
		f_DocumentNo.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_DocumentNo.rightAlign());
		
		rows = null;
		row = null;
		rows = v_TotalsPanel.newRows();

		//
		row = rows.newRow();
		row.setHeight("10px");

		Label lNet = new Label (Msg.translate(Env.getCtx(), "SubTotal")+":");
		lNet.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(lNet);
		f_TotalLines = new Label(String.valueOf(DisplayType.Amount));
		f_TotalLines.setStyle("Font-size:medium;");
		row.appendChild(f_TotalLines.rightAlign());
		
		f_TotalLines.setText("0.00");
		
		row = rows.newRow();
		row.setHeight("30px");
		
		Label lTax = new Label (Msg.translate(Env.getCtx(), "C_Tax_ID")+":");
		lTax.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(lTax);
		f_TaxAmount = new Label(String.valueOf(DisplayType.Amount));
		f_TaxAmount.setStyle("Font-size:medium");
		row.appendChild(f_TaxAmount.rightAlign());
		f_TaxAmount.setText(Env.ZERO.toString());
		
		row = rows.newRow();
		Label lTotal = new Label (Msg.translate(Env.getCtx(), "GrandTotal")+":");
		lTotal.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(lTotal);
		f_GrandTotal = new Label(String.valueOf(DisplayType.Amount));
		row.appendChild(f_GrandTotal.rightAlign());
		f_GrandTotal.setText(Env.ZERO.toString());
		f_GrandTotal.setStyle(WPOS.FONTSIZELARGE);

		Center center = new Center();
		Grid layout = GridFactory.newGridLayout();

		center.setStyle("border: none; overflow-y:auto;overflow-x:hidden;");
		appendChild(center);
		org.adempiere.webui.component.Panel centerPanel = new org.adempiere.webui.component.Panel();
		center.appendChild(centerPanel);
		centerPanel.appendChild(layout);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setStyle("overflow:auto;");
		
		rows = layout.newRows();
		
		WPosKeyPanel panel = new WPosKeyPanel(C_POSKeyLayout_ID, this);
//		row.appendChild(f_ProductName);
		row = rows.newRow();
		row.setSpans("4");
		row.appendChild(panel);
		north.setAutoscroll(true);
		appendChild(north);
		
		//	Refresh
		f_TotalLines.setText(m_Format.format(Env.ZERO));
		f_GrandTotal.setText(m_Format.format(Env.ZERO));
		f_TaxAmount.setText(m_Format.format(Env.ZERO));

	}
	
	@Override
	public void refreshPanel() {
		if (!v_POSPanel.hasOrder()) {
			f_SalesRep.setText(v_POSPanel.getSalesRepName());
			f_DocumentType.setText(Msg.getMsg(v_POSPanel.getCtx(), "Order"));
			f_DocumentNo.setText(Msg.getMsg(v_POSPanel.getCtx(), "New"));
			f_TotalLines.setText(v_POSPanel.getNumberFormat().format(Env.ZERO));
			f_GrandTotal.setText(v_POSPanel.getNumberFormat().format(Env.ZERO));
			f_TaxAmount.setText(v_POSPanel.getNumberFormat().format(Env.ZERO));
			f_BPartnerName.setText(null);
		} else {
			BigDecimal m_TotalLines = v_POSPanel.getTotalLines();
			BigDecimal m_GrandTotal = v_POSPanel.getGrandTotal();
			BigDecimal m_TaxAmt = m_GrandTotal.subtract(m_TotalLines);
			String currencyISO_Code = v_POSPanel.getCurSymbol();
			//	Set Values
			f_SalesRep.setText(v_POSPanel.getSalesRepName());
			f_DocumentType.setText(v_POSPanel.getDocumentTypeName());
			f_DocumentNo.setText(v_POSPanel.getDocumentNo());
			f_TotalLines.setText(currencyISO_Code + " " + v_POSPanel.getNumberFormat().format(m_TotalLines));
			f_GrandTotal.setText(currencyISO_Code + " " + v_POSPanel.getNumberFormat().format(m_GrandTotal));
			f_TaxAmount.setText(currencyISO_Code + " " + v_POSPanel.getNumberFormat().format(m_TaxAmt));
			f_BPartnerName.setText(v_POSPanel.getBPName());
		}
		
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		
	}

	@Override
	public void keyReturned(MPOSKey key) {
		// processed order
		if (v_POSPanel.hasOrder() 
				&& v_POSPanel.isCompleted()){
					//	Show Product Info
			v_POSPanel.refreshProductInfo(key);
		return;
		}
		// Add line
		v_POSPanel.addLine(key.getM_Product_ID(), key.getQty());
		//	Show Product Info
		v_POSPanel.refreshProductInfo(key);
		return;
	}

	public boolean showKeyboard(WPosTextField field, Label label) {
		isKeyboard = true;
		if(field.getText().equals(label.getValue()))
			field.setValue("");
		WPOSKeyboard keyboard =  v_POSPanel.getKeyboard(field.getKeyLayoutId()); 
		keyboard.setPosTextField(field);	
		AEnv.showWindow(keyboard);
		if(field.getText().equals("")) 
			field.setValue(label.getValue());
		return keyboard.isCancel();
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		//	Name
		if(e.getTarget().equals(f_BPartnerName.getComponent(WPosTextField.SECONDARY)) && e.getName().equals(Events.ON_FOCUS) && !isKeyboard){
			if(!showKeyboard(f_BPartnerName,l_BPartner))
				findBPartner();
			f_BPartnerName.setFocus(true);
		}
		if(e.getTarget().equals(f_BPartnerName.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
			isKeyboard = false;
		}
		//else {
//			changeFocus();
//		}	
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
			v_POSPanel.setC_BPartner_ID(results[0].getC_BPartner_ID());
			f_BPartnerName.setText(bp.getName()+"");
//			f_HiddenField.setFocus(true);
		} else {	//	more than one
			changeBusinessPartner(results);
		}

	}	//	findBPartner

	/**
	 * 	Change in Order the Business Partner, including Price list and location
	 *  In Order and POS
	 *  @param results
	 */
	public boolean changeBusinessPartner(MBPartnerInfo[] results) {
		// Change to another BPartner
		WQueryBPartner qt = new WQueryBPartner(v_POSPanel);
		qt.setResults(results);
		AEnv.showWindow(qt);
		if (qt.getRecord_ID() > 0) {
			f_BPartnerName.setText(v_POSPanel.getBPName());
			if(!v_POSPanel.hasOrder()) {
				v_POSPanel.newOrder(qt.getRecord_ID());
				v_POSPanel.refreshPanel();
			} else {
				v_POSPanel.setC_BPartner_ID(qt.getRecord_ID());
			}
			log.fine("C_BPartner_ID=" + qt.getRecord_ID());
			return true;
		}
		return false;
	}


	
}
