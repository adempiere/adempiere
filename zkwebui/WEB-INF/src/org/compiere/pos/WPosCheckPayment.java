/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.compiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.PO;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.w3c.dom.events.EventException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Space;


/**
 * 
 * @author Raul Muñoz 20/03/2015, 12:50 
 */
public class WPosCheckPayment extends Window implements WPosKeyListener, EventListener, WTableModelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961106531807910948L;


	private Properties p_ctx;
	private Textbox fRoutNo = new Textbox();
	private Label fBalance = new Label();
	private Button tenderType;
	
	private boolean paid = false;
	private BigDecimal balance = Env.ZERO;
	private Listbox fCreditNotes= ListboxFactory.newDropdownListbox();
	private WPOSKeyboard keyboard; 
	private Label fTenderAmt;
	private Label fReturnAmt;
	private Textbox fAccoNo;
	private Textbox fChckNo;
	private Textbox fAmount;
	private int cont;
	private int keyLayoutId;
	private ArrayList<Object> types;
	private final String FONT_SIZE = "Font-size:medium;";
	private final String FONT_BOLD = "font-weight:700";
	/** Button Width = 55			*/
	private static final int	WIDTH = 55;	
	/** Button Height = 55			*/
	private static final int	HEIGHT = 55;
	private Button 		fNew;
	private Button 		fPlus;
	private Button 		fReset;
	private Checkbox	chckEditPay;
	private Textbox		fSumAmount;
	private Label		fGrandTotal;
	private Label	 	fPayAmt;
	private double 		dSumAmount;
	private boolean 	bEdit = false;
	private int RowEdit = 0;
	
	private static ColumnInfo[] s_layout = new ColumnInfo[] { 
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Payment_ID"), "C_Payment_ID", IDColumn.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "RoutingNo"), "routingno", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "AccountNo"), "accountno", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "CheckNo"), "checkno", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Amount"), "payamt", Double.class)
	};
	/**	From Clause							*/
	private static String s_sqlFrom = "c_payment";
	/** Where Clause						*/
	private static String s_sqlWhere = "c_payment_id=? ";
	private DecimalFormat df = new DecimalFormat("#.##");
	
	/** The Table					*/
	WListbox		m_table;
	/** The Query SQL				*/
	private String			m_sql;
	private String			m_grandTotal;
	private String			m_balance;
	
	public WPosCheckPayment(WPosPayment posPayment) {
		super();
		p_ctx = Env.getCtx();
		setTitle(Msg.translate(p_ctx, "Check"));
		setClosable(true);
		m_grandTotal = posPayment.getGranTotal();
		m_balance = posPayment.getBalance();
		
		init();
	}

	private void init() {
		cont = 0;
		Panel panel = new Panel();
		//	Content
		
		Panel mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		Grid layout = GridFactory.newGridLayout();
		Grid westLayout = GridFactory.newGridLayout();
		Grid southLayout = GridFactory.newGridLayout();
		appendChild(panel);
		
		//	Panels
		Panel northPanel = new Panel();
		Panel westPanel = new Panel();
		Panel southPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		//
		
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		northPanel.appendChild(layout);
		layout.setWidth("100%");
		appendChild(mainPanel);
		Rows rows = null;
		Row row = null;
		rows = layout.newRows();
		row = rows.newRow();
		Label lCheck = new Label(Msg.translate(p_ctx, "Check"));
		row.appendChild(lCheck.rightAlign());
		row.appendChild(new Space());
		
		chckEditPay = new Checkbox();
		chckEditPay.setLabel(Msg.translate(p_ctx, "EditPayment"));
		chckEditPay.addActionListener(this);
		row.appendChild(chckEditPay);

		row.appendChild(new Space());
		row.appendChild(new Space());
		Label lSumAmount = new Label(Msg.translate(p_ctx, "SumAmount"));
		row.appendChild(lSumAmount.rightAlign());
		fSumAmount = new Textbox("0");
		row.appendChild(fSumAmount);
		row = rows.newRow();
		
		//
		West west = new West();
		west.setStyle("border: none");
		mainLayout.appendChild(west);
		west.appendChild(westPanel);
		westPanel.appendChild(westLayout);
		westLayout.setWidth("100%");
		westLayout.setHeight("45%");
		
		rows = westLayout.newRows();
		row = rows.newRow();

		row.setSpans("1,2");
		Label lRoutNo = new Label(Msg.translate(p_ctx, "Routing No")+":");
		lRoutNo.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(lRoutNo.rightAlign());
		row.appendChild(fRoutNo);
		fRoutNo.setStyle(FONT_SIZE);
		
		row = rows.newRow();
		row.setSpans("1,2");
		Label lAccoNo = new Label(Msg.translate(p_ctx, "Account No")+":");
		lAccoNo.setStyle(FONT_SIZE+FONT_BOLD);
		fAccoNo = new Textbox();
		row.appendChild(lAccoNo.rightAlign());
		row.appendChild(fAccoNo);
		fAccoNo.setStyle(FONT_SIZE);

		row = rows.newRow();
		row.setSpans("1,2");
		Label lChckNo = new Label(Msg.translate(p_ctx, "Check No")+":");
		lChckNo.setStyle(FONT_SIZE+FONT_BOLD);
		fChckNo = new Textbox();
		row.appendChild(lChckNo.rightAlign());
		row.appendChild(fChckNo);
		fChckNo.setStyle(FONT_SIZE);
		row = rows.newRow();
		
		row.setSpans("1,2");

		Label lAmount = new Label(Msg.translate(p_ctx, "Amount")+":");
		lChckNo.setStyle(FONT_SIZE+FONT_BOLD);
		fAmount = new Textbox();
		lAmount.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(lAmount.rightAlign());
		row.appendChild(fAmount);
		fAmount.setStyle(FONT_SIZE);

		row = rows.newRow();
		row.setHeight("55px");
		row.setStyle("overflow:visible; border:0px");
		
		// NEW
		fNew = createButtonAction("New", KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		fNew.addActionListener(this);
		row.appendChild(fNew);

		// Plus
		fPlus = createButtonAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		row.appendChild(fPlus);
				
		// Reset
		fReset = createButtonAction("Reset", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, Event.ESCAPE));
		row.appendChild(fReset);

		//	Suth 
		South south = new South();
		mainLayout.appendChild(south);
		south.appendChild(southPanel);
		southPanel.appendChild(southLayout);
		southLayout.setWidth("100%");
		southLayout.setHeight("100%");
		rows = southLayout.newRows();
		row = rows.newRow();
		
		Label lGrantTotal = new Label(Msg.translate(p_ctx, "GrandTotal")+":");
		row.appendChild(lGrantTotal.rightAlign());
		fGrandTotal = new Label(m_grandTotal);
		row.appendChild(fGrandTotal);

		row.appendChild(new Space());
		row.appendChild(new Space());
		Label lPayAmt = new Label(Msg.translate(p_ctx, "PayAmt")+":");
		row.appendChild(lPayAmt.rightAlign());
		fPayAmt = new Label(m_balance);
		row.appendChild(fPayAmt);
		
		//
		Center center = new Center();
		center.setStyle("border: none");
		center.setAutoscroll(true);
		m_table = ListboxFactory.newDataTable();
		m_sql = m_table.prepareTable(s_layout, s_sqlFrom, s_sqlWhere, false, "c_payment") + " ORDER BY c_payment_id";
		m_table.setWidth("100%");
		ListModelTable model = m_table.getModel();
		 s_layout[0].setVisibility(false);
		 
		m_table.repaint();
		m_table.getModel().addTableModelListener(this);
		center.appendChild(m_table);

		mainLayout.appendChild(center);
		
		
	}
	

	public void keyReturned(MPOSKey key) {
		
		String text = key.getText();
		String payAmt = fPayAmt.getValue();
		
		if ( text != null && !text.isEmpty() )
		{
			if ( text.equals(".") && payAmt.indexOf(".") == -1 )
			{
				fPayAmt.setText(payAmt + text);
				return;
			}
			try
			{
				Integer.parseInt(text);		// test if number
				fPayAmt.setText(payAmt + text);
			}
			catch (NumberFormatException e)
			{
				// ignore non-numbers
			}
		}
	}
	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected Button createButtonAction (String action, KeyStroke accelerator)
	{
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setWidth(WIDTH+"px");
		button.setHeight(HEIGHT+"px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	
	/**
	 * 	Create Action Button
	 *	@param action action 
	 *	@return button
	 */
	protected Button createButtonAction (String action, int m_OSK_KeyLayout_ID)
	{
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setId(m_OSK_KeyLayout_ID+"");
		button.setWidth(WIDTH+"px");
		button.setHeight(HEIGHT+"px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	

	
	public void cashpay(){
		
		
	}
	
	public void cashout(){
		BigDecimal tender = new BigDecimal( fTenderAmt.getValue() );
		BigDecimal payAmt = new BigDecimal( fPayAmt.getValue() );
		if(payAmt.compareTo(tender) > 0)
			keyboard.setCashOut(payAmt.subtract(tender));
		else
			keyboard.setCashOut(Env.ZERO);
	}
	private void setUpdatePay() {
		Double payamt = (double) 0;
		Double Qty;
		ListModelTable model_local = (ListModelTable) m_table.getModel();
		for (int i = 0; i < m_table.getRowCount(); i++) {

			Qty = Double.parseDouble(model_local.getValueAt(i, 4).toString());
			payamt = payamt + Qty;
		}

		fSumAmount.setText(payamt.toString());
		dSumAmount = payamt;
		fSumAmount.setText(df.format(payamt));

	}
	
	public void newPayment() {
		this.fRoutNo.setText("");
		this.fAccoNo.setText("");
		this.fChckNo.setText("");
		this.fAmount.setValue(Env.ZERO.toString());
		this.fAmount.setText("");
		setEditable(true);
		fNew.setEnabled(false);
	}
	public void addPayment() {
		ListModelTable model_local = (ListModelTable) m_table.getModel();

		IDColumn C_Payment_ID = null;
		String routingno = this.fRoutNo.getText();
		String accountno = this.fAccoNo.getText();
		String checkno = this.fChckNo.getText();
		Double payamt;
		payamt = Double.parseDouble(this.fAmount.getValue().toString());
		if (bEdit) {
			bEdit = false;
			chckEditPay.setSelected(false);
			model_local.setValueAt(C_Payment_ID, this.RowEdit, 0);
			model_local.setValueAt(routingno, this.RowEdit, 1);
			model_local.setValueAt(accountno, this.RowEdit, 2);
			model_local.setValueAt(checkno, this.RowEdit, 3);
			model_local.setValueAt(payamt, this.RowEdit, 4);
		} else {
			ArrayList<Object>  fill = new ArrayList<Object>();
			fill.add(C_Payment_ID);
			fill.add(routingno);
			fill.add(accountno);
			fill.add(checkno);
			fill.add(payamt);
			model_local.add(fill);
			m_table.setModel(model_local);
		}

		setEditable(false);
		fNew.setEnabled(true);
		fRoutNo.setText("");
		fAccoNo.setText("");
		fChckNo.setText("");
		fAmount.setText("");
		setUpdatePay();
	}
	
	public void editPayment() {
		if (bEdit) {
			chckEditPay.setSelected(true);
			return;
		}
		ListModelTable model_local = (ListModelTable) m_table.getModel();
		if (model_local.getRowCount() > 0) {
			int[] selectedRow = m_table.getSelectedIndices();

			for (int i = 0; i < selectedRow.length; i++) {
				String routingno = model_local.getValueAt(selectedRow[i], 1).toString();
				String accountno = model_local.getValueAt(selectedRow[i], 2).toString();
				String checkno = model_local.getValueAt(selectedRow[i], 3).toString();
				fRoutNo.setText(routingno);
				fAccoNo.setText(accountno);
				fChckNo.setText(checkno);
				fAmount.setText(model_local.getValueAt(i, 4).toString());
				bEdit = true;
				RowEdit = selectedRow[i];
				setEditable(true);
				fNew.setEnabled(false);
				fRoutNo.setFocus(true);
				chckEditPay.setSelected(true);
				break;
			}

		}
	}
	
	private void setEditable(boolean bAccion) {

		fReset.setEnabled(bAccion);
		fPlus.setEnabled(bAccion);
		fAccoNo.setEnabled(bAccion);
		fRoutNo.setEnabled(bAccion);
		fChckNo.setEnabled(bAccion);
		fAmount.setEnabled(bAccion);

	}
	
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		String action = event.getTarget().getId();
		if(event.getTarget().equals(fNew)){
			newPayment();
		}
		else if(event.getTarget().equals(fPlus)){
			addPayment();
		}
		else if(event.getTarget().equals(chckEditPay)){
			if (bEdit) {
				chckEditPay.setSelected(true);
				return;
			}
			ListModelTable model = m_table.getModel();
			if (model.getRowCount() > 0) {
				int selectedRow = m_table.getSelectedRow();


					String routingno = model.getValueAt(selectedRow, 1).toString();
					String accountno = model.getValueAt(selectedRow, 2).toString();
					String checkno = model.getValueAt(selectedRow, 3).toString();
					Double payamt;
					payamt = Double.parseDouble(model.getValueAt(selectedRow, 4).toString());
					
					fRoutNo.setText(routingno);
					fAccoNo.setText(accountno);
					fChckNo.setText(checkno);
					fAmount.setText(payamt.toString());
//					this.dtAmount.setText(model_local.getValueAt(i, 4).toString());
					bEdit = true;
					RowEdit = selectedRow;
					setEditable(true);
					fPlus.setEnabled(false);
//					this.dtRoutingno.requestFocus();
					chckEditPay.setSelected(true);

			}

		}
		else if ( action.equals(ConfirmPanel.A_OK)) {
			onClose();
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))	{
			onClose();
			return;
		}
	}

	@Override
	public void tableChanged(WTableModelEvent event) {
		m_table.repaint();
		
	}
}
