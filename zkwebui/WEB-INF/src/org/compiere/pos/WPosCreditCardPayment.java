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
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
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
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Space;


/**
 * 
 * @author Raul Muñoz 20/03/2015, 12:50 
 */
public class WPosCreditCardPayment extends Window implements WPosKeyListener, EventListener, WTableModelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961106531807910948L;


	private Properties p_ctx;
	
	private boolean bAcept = false;
	private WPOSKeyboard keyboard; 
	private Label fTenderAmt;
	private Textbox fAmount;
	private int cont;
	private final String FONT_SIZE = "Font-size:medium;";
	private final String FONT_BOLD = "font-weight:700";
	/** Button Width = 55			*/
	private static final int	WIDTH = 55;	
	/** Button Height = 55			*/
	private static final int	HEIGHT = 55;
	private Button 			fNew;
	private Button 			fPlus;
	private Button 			fReset;
	private Checkbox		chckEditPay;
	private Textbox			fSumAmount;
	private Label			fGrandTotal;
	private Label	 		fPayAmt;
	private double 			dSumAmount;
	private boolean 		bEdit = false;
	private int 			RowEdit = 0;
	private Button 			fDelete;
	private Button			fProcess;
	private Button			fCancel;
	private Listbox 		fCCardType = ListboxFactory.newDropdownListbox();
	private PosOrderModel 	m_order;
	private WPosBasePanel 	m_posPanel;
	private MPOS			m_pos;
	private Label lCCardNo;
	private Label lCCardName;
	private Label lCCardType;
	private Label lCCardMonth;
	private Label lCCardVC;
	private WPosTextField fCCardMonth;
	private WPosTextField fCCardVC;
	private WPosTextField fCCardNo;
	private WPosTextField fCCardName;
	
	private static ColumnInfo[] s_layout = new ColumnInfo[] { 
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Payment_ID"), "C_Payment_ID", IDColumn.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "CreditCardNumber"), "CreditCardNumber", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Expires"), "Expires", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "CVC"), "CVC", String.class), 
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
	
	public WPosCreditCardPayment(WPosPayment posPayment) {
		super();
		p_ctx = Env.getCtx();
		setTitle(Msg.translate(p_ctx, "CreditCard"));
		setClosable(true);
		m_grandTotal = posPayment.getGranTotal();
		m_balance = posPayment.getBalance();
		m_posPanel = posPayment.p_posPanel;
		m_order = posPayment.getPosOrderModel();
		m_pos = posPayment.p_pos;
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
		Label lCheck = new Label(Msg.translate(p_ctx, "CreditCard"));
		row.appendChild(lCheck.rightAlign());
		row.appendChild(new Space());
		
		fPayAmt = new Label(m_balance);
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
		westLayout.setHeight("300px");
		
		rows = westLayout.newRows();
		row = rows.newRow();

		row.setSpans("1,2");
		lCCardType = new Label(Msg.translate(p_ctx, "CreditCardType"));
		row = rows.newRow();
		row.appendChild(lCCardType.rightAlign());
		
//		.setStyle(FONT_SIZE+FONT_BOLD);
		
		/**
		 *	Load Credit Cards
		 */
		ValueNamePair[] ccs = m_order.getCreditCards(new BigDecimal (fPayAmt.getValue()));
		
		// default to cash payment
		for(int x = 0; x < ccs.length; x++){
			fCCardType.appendItem(ccs[x].getName(),String.valueOf(ccs[x].getValue()));
		}

		row.appendChild(fCCardType);
		fCCardType.setStyle(FONT_SIZE);

		
		row = rows.newRow();
		row.setSpans("1,2");
		fCCardNo = new WPosTextField(m_posPanel, m_pos.getOSNP_KeyLayout_ID(),  new DecimalFormat("#"));
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		
		row = rows.newRow();
		row.setSpans("1,2");
		row.appendChild(lCCardNo.rightAlign());
		row.appendChild(fCCardNo);
		fCCardNo.setStyle(FONT_SIZE);
		
		fCCardName = new WPosTextField(m_posPanel, m_pos.getOSK_KeyLayout_ID());
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		row = rows.newRow();
		row.setSpans("1,2");
		row.appendChild(lCCardName.rightAlign());
		row.appendChild(fCCardName);
		fCCardName.setStyle(FONT_SIZE);
		
		fCCardMonth = new WPosTextField(m_posPanel, m_pos.getOSNP_KeyLayout_ID(), new DecimalFormat("#"));
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		
		row = rows.newRow();
		row.setSpans("1,2");
		row.appendChild(lCCardMonth.rightAlign());
		row.appendChild(fCCardMonth);
		fCCardMonth.setStyle(FONT_SIZE);

		fCCardVC = new WPosTextField(m_posPanel, m_pos.getOSNP_KeyLayout_ID(),  new DecimalFormat("#"));
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		row = rows.newRow();
		row.setSpans("1,2");
		row.appendChild(lCCardVC.rightAlign());
		row.appendChild(fCCardVC);
		fCCardVC.setStyle(FONT_SIZE);
		
		row = rows.newRow();
		row.setSpans("1,2");
		Label lAmount = new Label(Msg.translate(p_ctx, "Amount")+":");
		fAmount = new Textbox();
		lAmount.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(lAmount.rightAlign());
		row.appendChild(fAmount);
		fAmount.setStyle(FONT_SIZE);

		row = rows.newRow();
		row.setHeight("55px");
		row.setStyle("border:0px");
		
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
		row.setHeight("55px");

		Panel buttonsPanel = new Panel();
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		fDelete = createButtonAction("Delete", KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.F4));
		buttonsPanel.appendChild(fDelete);
		fDelete.addActionListener(this);
		
		buttonsPanel.appendChild(new Space());
		buttonsPanel.appendChild(new Space());
		fCancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_F6, Event.F6));
		buttonsPanel.appendChild(fCancel);
		fProcess = createButtonAction("Process", KeyStroke.getKeyStroke(KeyEvent.VK_F5, Event.F5));
		buttonsPanel.appendChild(fProcess);
		
		row.appendChild(buttonsPanel);
		
		row = rows.newRow();

		row.appendChild(new Space());
		row.appendChild(new Space());
		Label lGrantTotal = new Label(Msg.translate(p_ctx, "GrandTotal")+":");
		row.appendChild(lGrantTotal.rightAlign());
		fGrandTotal = new Label(m_grandTotal);
		row.appendChild(fGrandTotal);

		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		Label lPayAmt = new Label(Msg.translate(p_ctx, "PayAmt")+":");
		row.appendChild(lPayAmt.rightAlign());
	
		row.appendChild(fPayAmt);
		

		
		//
		Center center = new Center();
		center.setStyle("border: none");
		center.setAutoscroll(true);
		m_table = ListboxFactory.newDataTable();
		m_sql = m_table.prepareTable(s_layout, s_sqlFrom, s_sqlWhere, false, "c_payment") + " ORDER BY c_payment_id";
		m_table.setWidth("100%");
		
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

			Qty = Double.parseDouble(model_local.getValueAt(i, 5).toString());
			payamt = payamt + Qty;
		}

		fSumAmount.setText(payamt.toString());
		dSumAmount = payamt;
		fSumAmount.setText(df.format(payamt));

	}
	
	public void newPayment() {
		this.fCCardNo.setText("");
		this.fCCardName.setText("");
		this.fCCardMonth.setText("");
		this.fCCardVC.setText("");
		this.fAmount.setValue(Env.ZERO.toString());
		this.fAmount.setText("");
		setEditable(true);
		fNew.setEnabled(false);
	}
	
	public void addPayment() {
		ListModelTable model_local = (ListModelTable) m_table.getModel();

		IDColumn C_Payment_ID = null;
		String ccNumber = fCCardNo.getText();
		String name = fCCardName.getText();
		String expires = fCCardMonth.getText();
		String cvc = fCCardVC.getText();
		Double payamt;
		payamt = Double.parseDouble(this.fAmount.getValue().toString());
		if (bEdit) {
			bEdit = false;
			chckEditPay.setSelected(false);
			
			model_local.setValueAt(C_Payment_ID, this.RowEdit, 0);
			model_local.setValueAt(ccNumber, this.RowEdit, 1);
			model_local.setValueAt(name, this.RowEdit, 2);
			model_local.setValueAt(expires, this.RowEdit, 3);
			model_local.setValueAt(cvc, this.RowEdit, 4);
			model_local.setValueAt(payamt, this.RowEdit, 5);
		} else {
			ArrayList<Object>  fill = new ArrayList<Object>();
			fill.add(C_Payment_ID);
			fill.add(ccNumber);
			fill.add(name);
			fill.add(expires);
			fill.add(cvc);
			fill.add(payamt);
			model_local.add(fill);
			m_table.setModel(model_local);
		}

		setEditable(false);
		fNew.setEnabled(true);
		fCCardNo.setText("");
		fCCardName.setText("");
		fCCardMonth.setText("");
		fCCardVC.setText("");
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

				String ccNumber = model_local.getValueAt(selectedRow[i], 1).toString();
				String name = model_local.getValueAt(selectedRow[i], 2).toString();
				String expires = model_local.getValueAt(selectedRow[i], 3).toString();
				String cvc = model_local.getValueAt(selectedRow[i], 4).toString();
				fCCardNo.setText(ccNumber);
				fCCardName.setText(name);
				fCCardMonth.setText(expires);
				fCCardVC.setText(cvc);
				fAmount.setText(model_local.getValueAt(i, 5).toString());
				bEdit = true;
				RowEdit = selectedRow[i];
				setEditable(true);
				fNew.setEnabled(false);
				fPlus.setEnabled(true);
				fCCardNo.setFocus(true);
				chckEditPay.setSelected(true);
				break;
			}

		}
	}
	
	private void setEditable(boolean bAccion) {

		fReset.setEnabled(bAccion);
		fPlus.setEnabled(bAccion);
		fCCardNo.setEnabled(bAccion);
		fCCardName.setEnabled(bAccion);
		fCCardVC.setEnabled(bAccion);
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
		else if(event.getTarget().equals(fDelete)){
			if (bEdit) {
				return;
			}
			ListModelTable model = m_table.getModel();
				int selectedRow = m_table.getSelectedRow();
					model.remove(selectedRow);
				
			setUpdatePay();
		}
		else if(event.getTarget().equals(fCancel)){
			bAcept = false;
			dispose();
		}
		else if(event.getTarget().equals(fProcess)){
			bAcept = true;
			dispose();
		}
		else if(event.getTarget().equals(chckEditPay)){
			if (bEdit) {
				chckEditPay.setSelected(true);
				return;
			}
			ListModelTable model = m_table.getModel();
			if (model.getRowCount() > 0) {
				int selectedRow = m_table.getSelectedRow();

					String ccNumber = model.getValueAt(selectedRow, 1).toString();
					String name = model.getValueAt(selectedRow, 2).toString();
					String expires = model.getValueAt(selectedRow, 3).toString();
					String cvc = model.getValueAt(selectedRow, 4).toString();
					Double payamt;
					payamt = Double.parseDouble(model.getValueAt(selectedRow, 5).toString());
					
					fCCardNo.setText(ccNumber);
					fCCardName.setText(name);
					fCCardMonth.setText(expires);
					fCCardVC.setText(cvc);
					fAmount.setText(payamt.toString());
					bEdit = true;
					RowEdit = selectedRow;
					setEditable(true);
					fPlus.setEnabled(false);
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
	public boolean getbAcept(){
		return bAcept;
	}
	public String getSumAmount(){
		return fSumAmount.getValue();
	}
	@Override
	public void tableChanged(WTableModelEvent event) {
		m_table.repaint();
		
	}
}
