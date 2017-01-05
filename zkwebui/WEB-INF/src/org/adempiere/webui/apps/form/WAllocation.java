/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.form.Allocation;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;


/**
 * Allocation Form
 *
 * @author  Jorg Janke
 * @version $Id: VAllocation.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * Contributor : Fabian Aguilar - OFBConsulting - Multiallocation
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/407">
 * 		@see FR [ 407 ] Enhance visualization of allocation payment window</a>
 */
public class WAllocation extends Allocation
	implements IFormController, EventListener, WTableModelListener, ValueChangeListener
{
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7806119329546820204L;
	
	private CustomForm form = new CustomForm();

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public WAllocation()
	{
		setWindowNo(getWindowNo());
		try
		{
			super.dynInit();
			dynInit();
			zkInit();
			calculate();
			southPanel.appendChild(new Separator());
			southPanel.appendChild(statusBar);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init
	
	//
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Panel allocationPanel = new Panel();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Label bpartnerLabel = new Label();
	private WSearchEditor bpartnerSearch = null;
	private WListbox invoiceTable = ListboxFactory.newDataTable();
	private WListbox paymentTable = ListboxFactory.newDataTable();
	private Borderlayout infoPanel = new Borderlayout();
	private Panel paymentPanel = new Panel();
	private Panel invoicePanel = new Panel();
	private Label paymentLabel = new Label();
	private Label invoiceLabel = new Label();
	private Label chargeLabel = new Label();
	private WTableDirEditor chargePick = null;
	private Borderlayout paymentLayout = new Borderlayout();
	private Borderlayout invoiceLayout = new Borderlayout();
	private Label paymentInfo = new Label();
	private Label invoiceInfo = new Label();
	private Grid allocationLayout = GridFactory.newGridLayout();
	private Label differenceLabel = new Label();
	private Textbox differenceField = new Textbox();
	private Label descriptionLabel = new Label();
	private Textbox descriptionField = new Textbox();
	private Label currencyLabel = new Label();
	private WTableDirEditor currencyPick = null;
	private Checkbox multiCurrency = new Checkbox();
	private Label allocCurrencyLabel = new Label();
	private StatusBarPanel statusBar = new StatusBarPanel();
	private Label dateLabel = new Label();
	private WDateEditor dateField = new WDateEditor();
	private Checkbox autoWriteOff = new Checkbox();
	private Label organizationLabel = new Label();
	private WTableDirEditor organizationPick;
	private Label apartLabel = new Label();
	private WTableDirEditor aparPick = null;
	/**	Confirm Panel		*/
	private ConfirmPanel confirmPanel;
	private Panel southPanel = new Panel();

	private boolean m_isCalculating;

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		//	
		confirmPanel = new ConfirmPanel(true);
		form.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		dateLabel.setText(Msg.getMsg(Env.getCtx(), "Date"));
		autoWriteOff.setSelected(false);
		autoWriteOff.setText(Msg.getMsg(Env.getCtx(), "AutoWriteOff", true));
		autoWriteOff.setTooltiptext(Msg.getMsg(Env.getCtx(), "AutoWriteOff", false));
		//
		parameterPanel.appendChild(parameterLayout);
		allocationPanel.appendChild(allocationLayout);
		bpartnerLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		paymentLabel.setText(" " + Msg.translate(Env.getCtx(), "C_Payment_ID"));
		invoiceLabel.setText(" " + Msg.translate(Env.getCtx(), "C_Invoice_ID"));
		paymentPanel.appendChild(paymentLayout);
		invoicePanel.appendChild(invoiceLayout);
		invoiceInfo.setText(".");
		paymentInfo.setText(".");
		chargeLabel.setText(" " + Msg.translate(Env.getCtx(), "C_Charge_ID"));
		differenceLabel.setText(Msg.getMsg(Env.getCtx(), "Difference"));
		differenceField.setText("0");
		differenceField.setStyle("text-align: right");
		descriptionLabel.setText(Msg.getMsg(Env.getCtx(), "Description"));
		confirmPanel.addActionListener(this);
		currencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		multiCurrency.setText(Msg.getMsg(Env.getCtx(), "MultiCurrency"));
		multiCurrency.addActionListener(this);
		allocCurrencyLabel.setText(".");
		
		organizationLabel.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		apartLabel.setText(Msg.translate(Env.getCtx(), "APAR"));
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		
		Rows rows = null;
		Row row = null;
		
		parameterLayout.setWidth("100%");
		rows = parameterLayout.newRows();
		row = rows.newRow();
		row.appendChild(bpartnerLabel.rightAlign());
		row.appendChild(bpartnerSearch.getComponent());
		row.appendChild(dateLabel.rightAlign());
		row.appendChild(dateField.getComponent());
		row.appendChild(organizationLabel.rightAlign());
		row.appendChild(organizationPick.getComponent());
		
		row = rows.newRow();
		row.appendChild(currencyLabel.rightAlign());
		row.appendChild(currencyPick.getComponent());
		Div div = new Div();
		div.setStyle("text-align: center");
		div.appendChild(multiCurrency);
		row.appendChild(div);
		row.appendChild(apartLabel.rightAlign());
		row.appendChild(aparPick.getComponent());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.setSpans("1,1,2,1,1");
		
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(autoWriteOff);
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		south.appendChild(southPanel);
		southPanel.appendChild(allocationPanel);
		
		allocationPanel.appendChild(allocationLayout);
		allocationLayout.setWidth("100%");
		rows = allocationLayout.newRows();
		row = rows.newRow();
		row.appendChild(differenceLabel.rightAlign());
		row.appendChild(allocCurrencyLabel.rightAlign());
		row.appendChild(differenceField);
		row.appendChild(new Space());
		row.appendChild(chargeLabel.rightAlign());
		row.appendChild(chargePick.getComponent());
		row.appendChild(new Space());
		row.appendChild(descriptionLabel.rightAlign());
		row.appendChild(descriptionField);
		row.appendChild(new Space());
		row.appendChild(confirmPanel);
		
		paymentPanel.appendChild(paymentLayout);
		paymentPanel.setWidth("100%");
		paymentPanel.setHeight("100%");
		paymentLayout.setWidth("100%");
		paymentLayout.setHeight("100%");
		paymentLayout.setStyle("border: none");
		
		invoicePanel.appendChild(invoiceLayout);
		invoicePanel.setWidth("100%");
		invoicePanel.setHeight("100%");
		invoiceLayout.setWidth("100%");
		invoiceLayout.setHeight("100%");
		invoiceLayout.setStyle("border: none");
		
		north = new North();
		north.setStyle("border: none");
		paymentLayout.appendChild(north);
		north.appendChild(paymentLabel);
		south = new South();
		south.setStyle("border: none");
		paymentLayout.appendChild(south);
		south.appendChild(paymentInfo.rightAlign());
		Center center = new Center();
		paymentLayout.appendChild(center);
		center.appendChild(paymentTable);
		paymentTable.setWidth("99%");
		paymentTable.setHeight("100%");
		paymentTable.setMultiSelection(true);
		center.setStyle("border: none");
		
		north = new North();
		north.setStyle("border: none");
		invoiceLayout.appendChild(north);
		north.appendChild(invoiceLabel);
		south = new South();
		south.setStyle("border: none");
		invoiceLayout.appendChild(south);
		south.appendChild(invoiceInfo.rightAlign());
		center = new Center();
		invoiceLayout.appendChild(center);
		center.appendChild(invoiceTable);
		invoiceTable.setWidth("99%");
		invoiceTable.setHeight("99%");
		invoiceTable.setMultiSelection(true);
		center.setStyle("border: none");
		//
		center = new Center();
		center.setFlex(true);
		mainLayout.appendChild(center);
		center.appendChild(infoPanel);
		
		infoPanel.setStyle("border: none");
		infoPanel.setWidth("100%");
		infoPanel.setHeight("100%");
		
		north = new North();
		north.setStyle("border: none");
		north.setHeight("49%");
		infoPanel.appendChild(north);
		north.appendChild(paymentPanel);
		north.setSplittable(true);
		center = new Center();
		center.setStyle("border: none");
		center.setFlex(true);
		infoPanel.appendChild(center);
		center.appendChild(invoicePanel);
	}   //  jbInit

	/**
	 *  Dynamic Init (prepare dynamic fields)
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception
	{
		//  Currency
		int AD_Column_ID = 3505;    //  C_Invoice.C_Currency_ID
		MLookup lookupCur = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		currencyPick = new WTableDirEditor("C_Currency_ID", true, false, true, lookupCur);
		currencyPick.setValue(new Integer(m_C_Currency_ID));
		currencyPick.addValueChangeListener(this);

		// Organization filter selection
		AD_Column_ID = 839; //C_Period.AD_Org_ID (needed to allow org 0)
		MLookup lookupOrg = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		organizationPick = new WTableDirEditor("AD_Org_ID", true, false, true, lookupOrg);
		organizationPick.setValue(Env.getAD_Org_ID(Env.getCtx()));
		organizationPick.addValueChangeListener(this);
		
		//  BPartner
		AD_Column_ID = 3499;        //  C_Invoice.C_BPartner_ID
		MLookup lookupBP = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.Search);
		bpartnerSearch = new WSearchEditor("C_BPartner_ID", true, false, true, lookupBP);
		bpartnerSearch.addValueChangeListener(this);

		//  Translation
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "AllocateStatus"));
		statusBar.setStatusDB("");

		//  Date set to Login Date
		dateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		dateField.addValueChangeListener(this);
		
		AD_Column_ID = 61804;    //  C_AllocationLine.C_Charge_ID
		MLookup lookupCharge = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		chargePick = new WTableDirEditor("C_Charge_ID", true, false, true, lookupCharge);
		chargePick.setValue(new Integer(m_C_Charge_ID));
		chargePick.addValueChangeListener(this);
		
		//	APAR
		AD_Column_ID = 14082;    //  T_InvoiceGL.APAR
		MLookup lookupAPAR = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.List);
		aparPick = new WTableDirEditor("APAR", true, false, true, lookupAPAR);
		aparPick.setValue(APAR_A);
		aparPick.addValueChangeListener(this);
	}   //  dynInit
	
	/**************************************************************************
	 *  Action Listener.
	 *  - MultiCurrency
	 *  - Allocate
	 *  @param e event
	 */
	public void onEvent(Event e) {
		log.config("");
		if (e.getTarget().equals(multiCurrency))
			loadBPartner();
		//	Allocate
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			confirmPanel.getOKButton().setEnabled(false);
			m_description = descriptionField.getText();
			saveData();
			loadBPartner();
			confirmPanel.getOKButton().setEnabled(true);
		}
	}   //  actionPerformed
	
	/**
	 * 	Dispose
	 */
	public void dispose() {
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	/**
	 *  Table Model Listener.
	 *  - Recalculate Totals
	 *  @param e event
	 */
	public void tableChanged(WTableModelEvent e)
	{
		boolean isUpdate = (e.getType() == WTableModelEvent.CONTENTS_CHANGED);
		//  Not a table update
		if (!isUpdate)
		{
			calculate();
			return;
		}

		// The writeoff() function causes additional tableChanged events which can be ignored.  
		if(m_isCalculating)
			return;
		m_isCalculating = true;
		Clients.showBusy(null,true);
		
		int row = e.getFirstRow();
		int col = e.getColumn();
		boolean isInvoice = (e.getModel().equals(invoiceTable.getModel()));
		boolean isAutoWriteOff = autoWriteOff.isSelected();
		
		String msg = writeOff(row, col, isInvoice, paymentTable, invoiceTable, isAutoWriteOff);
		if(msg != null && msg.length() > 0)
			FDialog.warn(getWindowNo(), "AllocationWriteOffWarn");

		calculate();
		
		Clients.showBusy(null,false);
		m_isCalculating = false;
	}   //  tableChanged

	/**
	 *  Vetoable Change Listener.
	 *  - Business Partner
	 *  - Currency
	 * 	- Date
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e)
	{
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		
		// Organization
		if (name.equals("AD_Org_ID")) {
			if (value == null)
				setAD_Org_ID(0);
			else
				setAD_Org_ID(((Integer) value).intValue());
			loadBPartner();
		}

		//  BPartner
		if (name.equals("C_BPartner_ID"))
		{
			if (value == null)
			{
				m_C_BPartner_ID = 0;
				bpartnerSearch.setValue(null);
			}
			else
			{
				m_C_BPartner_ID = ((Integer)value).intValue();
				bpartnerSearch.setValue(m_C_BPartner_ID);
			}
			
			checkBPartner();
			loadBPartner();
		}
        else if (name.equals("C_Charge_ID"))
		{
			if (value == null)
			{
     			m_C_Charge_ID = 0;
			}
			else
			{
				m_C_Charge_ID = ((Integer) value).intValue();
			}
			setAllocateButton();
		}

		//	Currency
		else if (name.equals("C_Currency_ID"))
		{
			if (value == null)
			{
				m_C_Currency_ID = 0;
			}
			else
			{
				m_C_Currency_ID = ((Integer) value).intValue();
			}
			loadBPartner();
		}
		//	Date for Multi-Currency
		else if (name.equals("Date") && multiCurrency.isSelected())
		{
			loadBPartner();
		} else if(name.equals("APAR")) {
			if(value == null
					|| value.toString().length() == 0)
				apar = APAR_A;
			else 
				apar = value.toString();
			loadBPartner();
		}
	}   //  vetoableChange
	
	private void setAllocateButton() {

		if (totalDiff.compareTo(new BigDecimal(0.0)) == 0 ^ m_C_Charge_ID > 0)
		{
			confirmPanel.getOKButton().setEnabled(true);
		}
		else
		{
			confirmPanel.getOKButton().setEnabled(false);
		}

		if (totalDiff.compareTo(new BigDecimal(0.0)) == 0)
		{
			chargePick.setValue(null);
			m_C_Charge_ID = 0;
		}
	  }
	
	/**
	 *  Load Business Partner Info
	 *  - Payments
	 *  - Invoices
	 */
	private void loadBPartner()
	{
		Clients.showBusy(null,true);
		
		//checkBPartner();
		Vector<Vector<Object>> data = getPaymentData(multiCurrency.isSelected(), dateField.getValue(), paymentTable);
		Vector<String> columnNames = getPaymentColumnNames(multiCurrency.isSelected());
		
		paymentTable.clear();
		
		//  Remove previous listeners
		paymentTable.getModel().removeTableModelListener(this);
		
		//  Set Model
		ListModelTable modelP = new ListModelTable(data);
		modelP.addTableModelListener(this);
		paymentTable.setData(modelP, columnNames);
		setPaymentColumnClass(paymentTable, multiCurrency.isSelected());
		paymentTable.recreateListHead();
		//

		data = getInvoiceData(multiCurrency.isSelected(), dateField.getValue(), invoiceTable);
		columnNames = getInvoiceColumnNames(multiCurrency.isSelected());
		
		invoiceTable.clear();
		
		//  Remove previous listeners
		invoiceTable.getModel().removeTableModelListener(this);
		
		//  Set Model
		ListModelTable modelI = new ListModelTable(data);
		modelI.addTableModelListener(this);
		invoiceTable.setData(modelI, columnNames);
		setInvoiceColumnClass(invoiceTable, multiCurrency.isSelected());
		invoiceTable.recreateListHead();
		//
		
		calculate(multiCurrency.isSelected());
		
		//  Calculate Totals
		calculate();
		Clients.showBusy(null,false);
	}   //  loadBPartner
	
	public void calculate()
	{
		allocDate = null;
		
		paymentInfo.setText(calculatePayment(paymentTable, multiCurrency.isSelected()));
		invoiceInfo.setText(calculateInvoice(invoiceTable, multiCurrency.isSelected()));
		
		//	Set AllocationDate
		if (allocDate != null)
			dateField.setValue(allocDate);
		//  Set Allocation Currency
		allocCurrencyLabel.setText(currencyPick.getDisplay());
		//  Difference
		totalDiff = totalPay.subtract(totalInv);
		differenceField.setText(format.format(totalDiff));
		
		setAllocateButton();
	}
	
	/**************************************************************************
	 *  Save Data
	 */
	public void saveData()
	{
		setAD_Org_ID();
		try
		{
			Trx.run(new TrxRunnable() 
			{
				public void run(String trxName)
				{
					statusBar.setStatusLine(saveData(getWindowNo(), dateField.getValue(), paymentTable, invoiceTable, trxName));
				}
			});
		}
		catch (Exception e)
		{
			FDialog.error(getWindowNo(), form , "Error", e.getLocalizedMessage());
			return;
		}
	}   //  saveData
	
	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * @return
	 */
	public ADForm getForm()
	{
		return form;
	}
}   //  WAllocation
