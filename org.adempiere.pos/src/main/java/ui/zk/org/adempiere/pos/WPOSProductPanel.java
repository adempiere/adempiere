package org.adempiere.pos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.adempiere.pos.search.WQueryProduct;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.I_C_Order;
import org.compiere.model.MPOSKey;
import org.compiere.model.MWarehousePrice;
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
	
	private WPosTextField	f_ProductName;
	private Button			f_HiddenField;
	private Label	 		f_TotalLines;
	private Label	 		f_TaxAmount;
	private Label	 		f_GrandTotal;
	private Label	 		f_DocumentType;
	private Label 			f_DocumentNo;

	/**	Format				*/
	private DecimalFormat	m_Format;
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(WPOSProductPanel.class);
	private int cont;
	private Caption 		v_TitleBorder;
	private Caption 		v_TitleInfo;
	private Groupbox 		v_TotalsGroup;
	private Groupbox 		v_InfOrderGroup;
	private Grid 			v_TotalsPanel;
	private Grid 			v_OrderPanel;
	private Grid 			v_GroupPanel;

	private Label 			f_salesRep;
	private Label 			productLabel;
	
	@Override
	public void init(){
		int C_POSKeyLayout_ID = p_pos.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		cont = 0;

		v_TotalsPanel = GridFactory.newGridLayout();
		v_TotalsPanel.setWidth("350px");
		v_TotalsPanel.setHeight("100%");

		v_OrderPanel = GridFactory.newGridLayout();
		v_OrderPanel.setWidth("320px");
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
		productLabel = new Label(Msg.translate(Env.getCtx(), "M_Product_ID"));
		
		f_ProductName = new WPosTextField(v_POSPanel, p_pos.getOSK_KeyLayout_ID());
		f_ProductName.setWidth("100%");
		
		f_ProductName.setHeight("35px");
		f_ProductName.setStyle("Font-size:medium; font-weight:bold");
		f_ProductName.setName("Name");
		f_ProductName.setReadonly(true);
		f_ProductName.addEventListener(Events.ON_FOCUS,this);
		f_ProductName.setValue(productLabel.getValue());
		
		
		row = new Row();
		rows.appendChild(row);
		row.setSpans("2");
		row.appendChild(f_ProductName);

		v_GroupPanel.appendChild(rows);
		
		v_TotalsGroup.appendChild(v_TotalsPanel);
		
		v_TitleBorder = new Caption(Msg.getMsg(Env.getCtx(), "Totals"));
		Style style = new Style();
		style.setContent(".z-fieldset legend {font-size: medium; font-weight:bold;} "
				+ ".Table-OrderLine tr th div{font-size: 14px; font-weight:bold; padding:5px} "
				+ ".Table-OrderLine tr td div, .Table-OrderLine tr td div input{font-size: medium; height:25px}"
				+ ".label-description {font-size: medium; display:block; height:15px; font-weight:bold; width: 350px; overflow:hidden;}");
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
		
		f_salesRep = new Label(v_POSPanel.getSalesRepName());
		f_salesRep.setStyle("Font-size:medium; font-weight:bold");
		row.appendChild(f_salesRep.rightAlign());
		
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
		f_GrandTotal.setStyle("Font-size:medium");

		f_HiddenField = new Button();
		
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
			f_TotalLines.setText(m_Format.format(Env.ZERO));
			f_GrandTotal.setText(m_Format.format(Env.ZERO));
			f_TaxAmount.setText(m_Format.format(Env.ZERO));
		} else {
			BigDecimal m_TotalLines = v_POSPanel.getTotalLines();
			BigDecimal m_GrandTotal = v_POSPanel.getGrandTotal();
			BigDecimal m_TaxAmt = m_GrandTotal.subtract(m_TotalLines);
			String currencyISO_Code = v_POSPanel.getCurSymbol();
			//	Set Values
			
			f_TotalLines.setText(currencyISO_Code +" "+ m_Format.format(m_TotalLines));
			f_GrandTotal.setText(currencyISO_Code +" "+ m_Format.format(m_GrandTotal));
			f_TaxAmount.setText(currencyISO_Code +" "+ m_Format.format(m_TaxAmt));
			f_DocumentNo.setText(v_POSPanel.getDocumentNo());
			f_DocumentType.setText(v_POSPanel.getDocumentTypeName());
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
		addLine(key.getM_Product_ID(), key.getQty());
		//	Show Product Info
		v_POSPanel.refreshProductInfo(key);
		return;
		
	}

	public boolean showKeyboard(WPosTextField field, Label label) {
		if(field.getText().equals(label.getValue()))
			field.setValue("");
		WPOSKeyboard keyboard =  v_POSPanel.getKeyboard(field.getKeyLayoutId()); 
		keyboard.setWidth("750px");
		keyboard.setHeight("380px");
		keyboard.setPosTextField(field);	
		AEnv.showWindow(keyboard);
		if(field.getText().equals("")) 
			field.setValue(label.getValue());
		return keyboard.isCancel();
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		//	Name
		cont++;
		if(cont<2){
			if (e.getTarget().equals(f_ProductName)) {
				if(e.getTarget().equals(f_ProductName)) {
					if(!showKeyboard(f_ProductName,productLabel))
						findProduct(); 
				}
			}
		}else {
			cont=0;
			f_HiddenField.setFocus(true);
		}	
	}
	/**
	 * 	Find/Set Product & Price
	 */
	private void findProduct()
	{
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

		//
		results = MWarehousePrice.find  (m_ctx,
				v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID(), 
				Value, Name, UPC, SKU, null);
		
		//	Set Result
//		if (results.length == 0)
//		{
//			String message = Msg.getMsg(p_ctx,  "POS.SearchProductNF");
//			FDialog.warn(0, null, message +" "+ query,"");
//		}
		if (results.length == 1)
		{
			addLine(results[0].getM_Product_ID(), Env.ONE);
			f_ProductName.setValue(results[0].getName());
			f_HiddenField.setFocus(true);
		}
		else	//	more than one
		{
			WQueryProduct qt = new WQueryProduct(v_POSPanel);
			qt.setResults(results);
			qt.setQueryData(v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID());
			AEnv.showWindow(qt);
			if (qt.getRecord_ID() > 0) {
				f_ProductName.setText(qt.getValue());
				addLine(qt.getRecord_ID(), Env.ONE);
			
			}
		}
	}	//	findProduct
	
	/**
	 * Add or replace order line
	 * @author Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_M_Product_ID
	 * @param m_QtyOrdered
	 * @return void
	 */
	private void addLine(int p_M_Product_ID, BigDecimal m_QtyOrdered) {
		//	Create Ordder if not exists
		if (!v_POSPanel.hasOrder()) {
			v_POSPanel.newOrder();
		}
		//	Show Product Info
		v_POSPanel.refreshProductInfo(p_M_Product_ID);
		//	
		String lineError = v_POSPanel.add(p_M_Product_ID, m_QtyOrdered);
		if (lineError != null) {
			log.warning("POS Error " + lineError);
			FDialog.error(0, 
					this, Msg.parseTranslation(m_ctx, lineError));
		}
		//	Update Info
		v_POSPanel.refreshPanel();
	}
}
