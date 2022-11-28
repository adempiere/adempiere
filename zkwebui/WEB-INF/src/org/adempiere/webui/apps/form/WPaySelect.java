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
 * Contributors:                                                              *
 * Colin Rooney (croo) Patch 1605368 Fixed Payment Terms & Only due           *
 *****************************************************************************/
package org.adempiere.webui.apps.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WPAttributeEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.core.domains.models.X_C_PaySelection;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.form.PaySelect;
import org.compiere.model.MSysConfig;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;

/**
 *  Create Manual Payments From (AP) Invoices or (AR) Credit Memos.
 *  Allows user to select Invoices for payment.
 *  When Processed, PaySelection is created
 *  and optionally posted/generated and printed
 *
 *  @author Jorg Janke
 *  @version $Id: VPaySelect.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 *  
 *  @author	Michael McKay
 * 				<li>release/380 - fix row selection event handling to fire single event per row selection.
 * 					Use WSearchEditor for BPartner, include auto query and improve handling of criteria events
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document (It is changed to Smart Browse)
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
@Deprecated
public class WPaySelect extends PaySelect
	implements IFormController, EventListener, ValueChangeListener, WTableModelListener, ASyncProcess
{
	/** TODO withholding */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6031404894392912610L;
	
	private CustomForm form = new CustomForm();
	private boolean m_loadedOK = false;

	//
	private Panel mainPanel = new Panel();
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Label labelBankAccount = new Label();
	private Listbox fieldBankAccount = ListboxFactory.newDropdownListbox();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Label labelBankBalance = new Label();
	private Label labelCurrency = new Label();
	private Label labelBalance = new Label();
	private Checkbox onlyDue = new Checkbox();
	private Label labelBPartner = new Label();
	private WSearchEditor fieldBPartner = WSearchEditor.createBPartner(0);
	private Label dataStatus = new Label();
	private WListbox miniTable = ListboxFactory.newDataTable();
	private ConfirmPanel commandPanel = new ConfirmPanel(true, false, false, false, false, false, false);
	private Button bCancel = commandPanel.getButton(ConfirmPanel.A_CANCEL);
	private Button bGenerate = commandPanel.createButton(ConfirmPanel.A_PROCESS);
	private Button bRefresh = commandPanel.createButton(ConfirmPanel.A_REFRESH);
	private Label labelPayDate = new Label();
	private WDateEditor fieldPayDate = new WDateEditor();
	private Label labelPaymentRule = new Label();
	private Listbox fieldPaymentRule = ListboxFactory.newDropdownListbox();
	private Label labelDtype = new Label();
	private Listbox fieldDtype = ListboxFactory.newDropdownListbox();
	private Panel southPanel;
	@SuppressWarnings("unused")
	private ProcessInfo m_pi;
	private boolean m_isLock;

	private Checkbox checkAutoQuery = new Checkbox();
	private static String SYSCONFIG_INFO_AUTO_QUERY = "INFO_AUTO_QUERY";

	/**
	 *	Initialize Panel
	 */
	public WPaySelect()
	{
		try
		{
			zkInit();
			dynInit();	
			m_loadedOK = true;
			
			if (checkAutoQuery.isSelected())
			{
				setFieldOldValues();
				loadTableInfo();
			}

		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		//
		form.appendChild(mainPanel);
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("99%");
		parameterPanel.appendChild(parameterLayout);
		//
		labelBankAccount.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		fieldBankAccount.addActionListener(this);
		fieldBankAccount.setAttribute("zk_component_ID", "Lookup_Criteria_C_BankAccount_ID");
		fieldBankAccount.setAttribute("zk_component_prefix", "Lookup_");
		fieldBankAccount.setAttribute("IsDynamic", "False");
		fieldBankAccount.setAttribute("fieldName", "fieldBankAccount");
		fieldBankAccount.setWidth("200px");		
		//
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fieldBPartner.addValueChangeListener(this);
		fieldBPartner.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fieldBPartner.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldBPartner.getComponent().setAttribute("IsDynamic", "False");
		fieldBPartner.getComponent().setAttribute("fieldName", "fieldBPartner");
		fieldBPartner.getComponent().setWidth("200px");
		//
		bRefresh.addActionListener(this);
		//
		labelPayDate.setText(Msg.translate(Env.getCtx(), "PayDate"));
		fieldPayDate.addValueChangeListener(this);
		fieldPayDate.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_PayDate");
		fieldPayDate.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldPayDate.getComponent().setAttribute("IsDynamic", "False");
		fieldPayDate.getComponent().setAttribute("fieldName", "fieldPayDate");
		fieldPayDate.getComponent().setWidth("200px");
		//
		labelPaymentRule.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		fieldPaymentRule.addActionListener(this);
		fieldPaymentRule.setAttribute("zk_component_ID", "Lookup_Criteria_PaymentRule");
		fieldPaymentRule.setAttribute("zk_component_prefix", "Lookup_");
		fieldPaymentRule.setAttribute("IsDynamic", "False");
		fieldPaymentRule.setAttribute("fieldName", "fieldPaymentRule");
		fieldPaymentRule.setWidth("200px");
		//
		labelDtype.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
		fieldDtype.addActionListener(this);
		fieldDtype.setAttribute("zk_component_ID", "Lookup_Criteria_C_DocType_ID");
		fieldDtype.setAttribute("zk_component_prefix", "Lookup_");
		fieldDtype.setAttribute("IsDynamic", "False");
		fieldDtype.setAttribute("fieldName", "fieldDtype");
		//
		labelBankBalance.setText(Msg.translate(Env.getCtx(), "CurrentBalance"));
		labelBalance.setText("0");
		labelBalance.setAttribute("zk_component_ID", "Lookup_Criteria_CurrentBalance");
		labelBalance.setAttribute("zk_component_prefix", "Lookup_");
		labelBalance.setAttribute("IsDynamic", "False");
		labelBalance.setAttribute("fieldName", "labelBalance");
		//
		onlyDue.setText(Msg.getMsg(Env.getCtx(), "OnlyDue"));
		onlyDue.addActionListener(this);
		onlyDue.setAttribute("zk_component_ID", "Lookup_Criteria_OnlyDue");
		onlyDue.setAttribute("fieldName", "onlyDue");
		onlyDue.setName("OnlyDue");
		onlyDue.setTooltiptext(Msg.getMsg(Env.getCtx(), "OnlyDue"));
		//
		checkAutoQuery.setText(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.addActionListener(this);
		checkAutoQuery.setAttribute("zk_component_ID", "Lookup_Criteria_AutoRefresh");
		checkAutoQuery.setName("AutoQuery");
		checkAutoQuery.setSelected(MSysConfig.getValue(SYSCONFIG_INFO_AUTO_QUERY,"Y",Env.getAD_Client_ID(Env.getCtx())).equals("Y"));  
		//
		dataStatus.setText(" ");
		dataStatus.setPre(true);
		dataStatus.setAttribute("zk_component_ID", "Lookup_Criteria_DataStatus");
		dataStatus.setAttribute("zk_component_prefix", "Lookup_");
		dataStatus.setAttribute("IsDynamic", "False");
		dataStatus.setAttribute("fieldName", "dataStatus");
		//
		bGenerate.addActionListener(this);
		bCancel.addActionListener(this);
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		//
		Rows rows = parameterLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(labelBankAccount.rightAlign());
		row.appendChild(fieldBankAccount);
		row.appendChild(labelBankBalance.rightAlign());
		Panel balancePanel = new Panel();
		balancePanel.appendChild(labelCurrency);
		balancePanel.appendChild(labelBalance);
		row.appendChild(balancePanel);
		row.appendChild(new Space());
		//
		row = rows.newRow();
		row.appendChild(labelBPartner.rightAlign());
		row.appendChild(fieldBPartner.getComponent());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		//
		row = rows.newRow();
		row.appendChild(labelDtype.rightAlign());
		row.appendChild(fieldDtype);
		row.appendChild(labelPaymentRule.rightAlign());
		row.appendChild(fieldPaymentRule);
		row.appendChild(checkAutoQuery);
		//
		row = rows.newRow();
		row.appendChild(labelPayDate.rightAlign());
		row.appendChild(fieldPayDate.getComponent());
		row.appendChild(new Space());
		row.appendChild(onlyDue);
		row.appendChild(bRefresh);
		//
		commandPanel.addButton(bGenerate);
		commandPanel.getButton(ConfirmPanel.A_OK).setVisible(false);
		//
		southPanel = new Panel();
		southPanel.appendChild(dataStatus);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(commandPanel);
		//
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		south.appendChild(southPanel);
		//
		Center center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(miniTable);
	}   //  jbInit

	/**
	 *  Dynamic Init.
	 *  - Load Bank Info
	 *  - Load BPartner
	 *  - Init Table
	 */
	private void dynInit()
	{
		ArrayList<BankInfo> bankAccountData = getBankAccountData();
		for(BankInfo bi : bankAccountData)
			fieldBankAccount.appendItem(bi.toString(), bi);

		if (fieldBankAccount.getItemCount() == 0)
			FDialog.error(m_WindowNo, form, "VPaySelectNoBank");
		else
			fieldBankAccount.setSelectedIndex(0);

		loadBankInfo();
		
		ArrayList<KeyNamePair> docTypeData = getDocTypeData();
		for(KeyNamePair pp : docTypeData)
			fieldDtype.appendItem(pp.getName(), pp);
		
		prepareTable(miniTable);
		
		miniTable.getModel().addTableModelListener(this);
		//
		fieldPayDate.setMandatory(true);
		fieldPayDate.setValue(new Timestamp(System.currentTimeMillis()));
	}   //  dynInit

	/**
	 *  Load Bank Info - Load Info from Bank Account and valid Documents (PaymentRule)
	 */
	private void loadBankInfo()
	{		
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem().getValue();
		if (bi == null)
			return;
		labelCurrency.setText(bi.Currency);
		labelBalance.setText(m_format.format(bi.Balance));

		//  PaymentRule
		fieldPaymentRule.removeAllItems();
		
		ArrayList<ValueNamePair> paymentRuleData = getPaymentRuleData(bi);
		for(ValueNamePair vp : paymentRuleData)
			fieldPaymentRule.appendItem(vp.getName(), vp);
		fieldPaymentRule.setSelectedIndex(0);
	}   //  loadBankInfo

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
		Timestamp payDate = (Timestamp)fieldPayDate.getValue();
		miniTable.setColorCompare(payDate);
		log.config("PayDate=" + payDate);
		
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem().getValue();
		
		ValueNamePair paymentRule = (ValueNamePair) fieldPaymentRule.getSelectedItem().getValue();
		KeyNamePair docType = (KeyNamePair) fieldDtype.getSelectedItem().getValue();

		int c_bpartner_id = 0;
		if (fieldBPartner.getValue() != null)
		{
			Integer id = (Integer)fieldBPartner.getValue();
			c_bpartner_id = id.intValue();
		}

		loadTableInfo(bi, payDate, paymentRule, onlyDue.isSelected(), c_bpartner_id, docType, miniTable);
		
		calculateSelection();
	}   //  loadTableInfo

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	
	/**************************************************************************
	 *  ActionListener
	 *  @param e event
	 */
	public void onEvent (Event e)
	{
		boolean triggerRefresh = false;
		
		if(!m_loadedOK)
			return;

		if (e != null) {
			
	    	Component component = e.getTarget();
	
	    	if (component != null) {
    			//  Generic components in the criteria fields
    			if (component instanceof Textbox)
    			{
    				Textbox tb = ((Textbox) component);

    				if (tb.hasChanged())
    				{
    					triggerRefresh = true;
    				}
    				else
    				{
    					// Special case where text fields don't change but cause an event
    					// Interpret this as a click of the OK button and close EXCEPT
    					// if the dialog was opened from a menu.
    					//if (isModal())
    					//	dispose(true);  //  Save the selection and close;
    					//else
    						return;
    				}
    			}
    			else if (component instanceof Checkbox)
    			{
    				//  Check box changes generally always cause a refresh
    				//  Capture changes that don't in a specific event handler
    				triggerRefresh = true;
    				
    				Checkbox cb = (Checkbox) component;
    				if (cb.getName() != null && cb.getName().equals("AutoQuery"))
    				{
    					//  Only trigger a refresh if the check box is selected
    					if(!cb.isSelected())
    					{
    						return;
    					}
    				}
    			}
    			else 
    			{
    				//  Assume another type of component
    				if(e.getName().equals("onChange") || e.getName().equals("onSelect"))
					{
    						triggerRefresh = true;	
					}
				}
		
    			String fieldName = "";
    			if (component.getAttribute("fieldName") != null)
    			{
    				fieldName = (String) component.getAttribute("fieldName");
    			}
    				
				if (triggerRefresh && fieldName.equals("fieldBankAccount"))
				{
					loadBankInfo();
				}
		
				//  Generate PaySelection
				if (component.equals(bGenerate))
				{
					generatePaySelect();
					dispose();
				}
				else if (component.equals(bCancel))
					dispose();
						
				// Refresh if the autoquery feature is selected or the refresh button is clicked.
				if (component.equals(bRefresh) || (checkAutoQuery.isSelected() && triggerRefresh))
				{
					setFieldOldValues();
					loadTableInfo();
				}
	    	}	
		}
	}   //  actionPerformed

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(WTableModelEvent e)
	{
		if (e.getColumn() == 0)
			calculateSelection();
	}   //  valueChanged

	/**
	 *  Calculate selected rows.
	 *  - add up selected rows
	 */
	public void calculateSelection()
	{
		dataStatus.setText(calculateSelection(miniTable));
		//
		bGenerate.setEnabled(m_noSelected != 0);
	}   //  calculateSelection

	/**
	 *  Generate PaySelection
	 */
	private void generatePaySelect()
	{
		if (miniTable.getRowCount() == 0)
			return;
		miniTable.setSelectedIndices(new int[]{0});
		calculateSelection();
		if (m_noSelected == 0)
			return;

		String msg = generatePaySelect(miniTable, (ValueNamePair) fieldPaymentRule.getSelectedItem().getValue(), 
				new Timestamp(fieldPayDate.getComponent().getValue().getTime()), 
				(BankInfo)fieldBankAccount.getSelectedItem().getValue());
		
		if(msg != null && msg.length() > 0)		
		{
			FDialog.error(m_WindowNo, form, "SaveError", msg);
			return;
		}

		//	FR [ 297 ]
		//  Ask to Post it
		if (!FDialog.ask(m_WindowNo, form, "VPaySelectGenerate?", "(" + m_ps.getDocumentNo()+ ")"))
			return;
		
		//  Prepare Process 
		int procesId = 155;	//	C_PaySelection_CreatePayment

		//	Execute Process
		ProcessModalDialog processModalDialog = new ProcessModalDialog(this, m_WindowNo, procesId, X_C_PaySelection.Table_ID, m_ps.getC_PaySelection_ID(), false);
		if (processModalDialog.isValidDialog()) {
			try {
				processModalDialog.setWidth("500px");
				processModalDialog.setVisible(true);
				processModalDialog.setPage(form.getPage());
				processModalDialog.doModal();
			} catch (SuspendNotAllowedException e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			} catch (InterruptedException e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
		else
			processModalDialog.runProcess();
	}   //  generatePaySelect
	
	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 */
	public void lockUI (ProcessInfo pi)
	{
		if (m_isLock) return;
		m_isLock = true;
		Clients.showBusy(null, true);
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 */
	public void unlockUI (ProcessInfo pi)
	{
		if (!m_isLock) return;
		m_isLock = false;
		m_pi = pi;
		Clients.showBusy(null, false);	
		
		//TODO: The response returned is always Cancel
//		if (!FDialog.ask(0, form, "VPaySelectPrint?", "(" + m_pi.getSummary() + ")"))
//		{
//			dispose();
//			return;
//		}

		this.dispose();
		
		//  Start PayPrint
		int AD_Form_ID = 106;	//	Payment Print/Export
		ADForm form = SessionManager.getAppDesktop().openForm(AD_Form_ID);
		if (m_ps != null)
		{
			WPayPrint pp = (WPayPrint) form.getICustomForm();
			pp.setPaySelection(m_ps.getC_PaySelection_ID());
		}
	}

	public void executeASync(ProcessInfo pi) {
	}

	public boolean isUILocked() {
		return m_isLock;
	}

	public ADForm getForm() {
		return form;
	}

	/**
	 * Capture value changes in WSearchEditor components specifically.
	 * Copy and override as required.
	 * @param evt
	 */
	public void valueChange(ValueChangeEvent evt) 
	{
		Object c = null;
		
		if (evt.getSource() instanceof WSearchEditor)
			c = ((WSearchEditor) evt.getSource()).getComponent();
		else if (evt.getSource() instanceof WPAttributeEditor)
			c = ((WPAttributeEditor) evt.getSource()).getComponent();
		else if (evt.getSource() instanceof WDateEditor)
			c = ((WDateEditor) evt.getSource()).getComponent();
				
		if (c == null)
			return;

		// Pass it off to the event handler to process.
		Event e = new Event("onChange", (Component) c);
		onEvent(e);

	}  //  valueChange

	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fieldBankAccount.set_oldValue();
		fieldBPartner.set_oldValue();
		fieldDtype.set_oldValue();
		fieldPaymentRule.set_oldValue();
		onlyDue.set_oldValue();
		return;
	}

}   //  VPaySelect
