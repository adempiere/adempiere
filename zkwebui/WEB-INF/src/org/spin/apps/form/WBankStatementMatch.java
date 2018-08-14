/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.apps.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
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
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;

/**
 * ZK View class for handle all method of Bank Statement Matcher
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1699 ] Add support view for Bank Statement
 * @see https://github.com/adempiere/adempiere/issues/1699
 */
public class WBankStatementMatch extends BankStatementMatchController
	implements IFormController, EventListener, WTableModelListener, ValueChangeListener
{
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7806119329546820204L;
	
	private CustomForm form = new CustomForm(){
		public void setProcessInfo(org.compiere.process.ProcessInfo pi) {
			setFromPO(pi);
			setWindowNo(pi.getWindowNo());
			
//			if(bPartnerId > 0) {
//				bpartnerSearch.setValue(bPartnerId);
//			}
//			if(currencyId > 0) {
//				currencyPick.setValue(currencyId);
//			}
//			if(orgId > 0) {
//				organizationPick.setValue(orgId);
//			}
//			if(isFromParent()) {
//				bpartnerSearch.setReadWrite(false);
//				loadBPartner();
//				setDefaultRecord(paymentTable, invoiceTable);
//			} else {
//				calculate();
//			}
		};
	};

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public WBankStatementMatch()
	{
		setWindowNo(getWindowNo());
		try
		{
			super.dynInit();
			dynInit();
			zkInit();
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
	private Panel actionPanel = new Panel();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Label bpartnerLabel = new Label();
	private WSearchEditor bpartnerSearch = null;
	private Label dateFromLabel = new Label();
	private Label dateToLabel = new Label();
	private Label bankAccountLabel = new Label();
	private Label matchModeLabel = new Label();
	private WNumberEditor amtFromField = new WNumberEditor("AmtFrom", false, false, true, DisplayType.Quantity, "xMatched");
	private WNumberEditor amtToField = new WNumberEditor("AmtTo", false, false, true, DisplayType.Quantity, "xMatchedTo");
	private WDateEditor dateFromField = new WDateEditor("DateFrom", false, false, true, "DateFrom");
	private WDateEditor dateToField = new WDateEditor("DateTo", false, false, true, "DateTo");
	private Label amtFromLabel = new Label();
	private Label amtToLabel = new Label();
	private Label searchLabel = new Label();
	private Listbox matchMode = ListboxFactory.newDropdownListbox(m_matchMode);
	private Button searchButton;
	private Button simulateMatchButton;
	
	private WListbox matchedPaymentTable = ListboxFactory.newDataTable();
	private WListbox currentPaymentTable = ListboxFactory.newDataTable();
	private WListbox importedPaymentTable = ListboxFactory.newDataTable();
	private Borderlayout infoPanel = new Borderlayout();
	private Panel importedPaymentPanel = new Panel();
	private Panel matchedPaymentPanel = new Panel();
	
	private Panel centerPanel = new Panel();
	
	private Label matchedPaymentLabel = new Label();
	private Label importedPaymentLabel = new Label();
	private Borderlayout matchedPaymentLayout = new Borderlayout();
	private Borderlayout importedPaymentLayout = new Borderlayout();
	private Borderlayout centerPaymentLayout = new Borderlayout();
	private Label paymentInfo = new Label();
	private Label invoiceInfo = new Label();
	private Grid actionLayout = GridFactory.newGridLayout();
	private WTableDirEditor bankAccountField = null;
	private StatusBarPanel statusBar = new StatusBarPanel();
	
	/**	Confirm Panel		*/
	private ConfirmPanel confirmPanel;
	private Panel southPanel = new Panel();

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		//	
		confirmPanel = new ConfirmPanel(true);
		searchButton = confirmPanel.createButton(ConfirmPanel.A_REFRESH);
		searchButton.addActionListener(this);
		simulateMatchButton = confirmPanel.createButton(ConfirmPanel.A_PROCESS);
		simulateMatchButton.setLabel(getButtonMatchMessage());
		simulateMatchButton.addActionListener(this);
		//	
		dateFromLabel.setText(Msg.translate(Env.getCtx(), "DateTrx"));
		dateToLabel.setText("-");
		amtFromLabel.setText(Msg.translate(Env.getCtx(), "PayAmt"));
		amtToLabel.setText("-");
		matchModeLabel.setText(Msg.translate(Env.getCtx(), "MatchMode"));		
		
		form.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		parameterPanel.appendChild(parameterLayout);
		actionPanel.appendChild(actionLayout);
		bpartnerLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		
		matchedPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Imported"));
		importedPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched"));
		importedPaymentPanel.appendChild(matchedPaymentLayout);
		matchedPaymentPanel.appendChild(importedPaymentLayout);
		invoiceInfo.setText(".");
		paymentInfo.setText(".");
		confirmPanel.addActionListener(this);
		bankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		
		
		North north = new North();
		north.setStyle("border: none");
		north.appendChild(parameterPanel);
		
		Rows rows = null;
		Row row = null;
		
		parameterLayout.setWidth("100%");
		rows = parameterLayout.newRows();
		//	
		row = rows.newRow();
		row.appendChild(bankAccountLabel.rightAlign());
		row.appendChild(bankAccountField.getComponent());
		row.appendChild(bpartnerLabel.rightAlign());
		row.appendChild(bpartnerSearch.getComponent());
		//	Payment Amount
		row = rows.newRow();
		row.appendChild(amtFromLabel.rightAlign());
		row.appendChild(amtFromField.getComponent());
		row.appendChild(amtToLabel.rightAlign());
		row.appendChild(amtToField.getComponent());
		//	Payment Date
		row = rows.newRow();
		row.appendChild(dateFromLabel.rightAlign());
		row.appendChild(dateFromField.getComponent());
		row.appendChild(dateToLabel.rightAlign());
		row.appendChild(dateToField.getComponent());
		//	Match Mode and Refresh
		row = rows.newRow();
		row.appendChild(matchModeLabel.rightAlign());
		row.appendChild(matchMode);
		row.appendChild(searchLabel.rightAlign());
		row.appendChild(searchButton);
		//	
		South south = new South();
		south.setStyle("border: none");
		south.appendChild(southPanel);
		southPanel.appendChild(actionPanel);
		
		actionPanel.appendChild(actionLayout);
		actionLayout.setWidth("100%");
		rows = actionLayout.newRows();
		row = rows.newRow();
		row.appendChild(simulateMatchButton);
		row.appendChild(new Space());
		row.appendChild(confirmPanel);
		
		
		Center center = new Center();
		center.appendChild(centerPanel);
		//	
		Panel northCenterPanel = new Panel();
		northCenterPanel.appendChild(centerPaymentLayout);
		East east = new East();
		east.appendChild(currentPaymentTable);
		
		northCenterPanel.appendChild(east);
		West west = new West();
		
		northCenterPanel.appendChild(west);
		west.appendChild(importedPaymentTable);
		
		North northCenter = new North();
		northCenter.appendChild(northCenterPanel);
		//	
		South southCenter = new South();
		southCenter.appendChild(matchedPaymentPanel);
		//	
		centerPanel.appendChild(northCenter);
		centerPanel.appendChild(southCenter);
		
		
		mainLayout.appendChild(north);
		mainLayout.appendChild(center);
		mainLayout.appendChild(south);
		
//		importedPaymentPanel.appendChild(matchedPaymentLayout);
//		importedPaymentPanel.setWidth("100%");
//		importedPaymentPanel.setHeight("100%");
//		matchedPaymentLayout.setWidth("100%");
//		matchedPaymentLayout.setHeight("100%");
//		matchedPaymentLayout.setStyle("border: none");
//		
//		matchedPaymentPanel.appendChild(importedPaymentLayout);
//		matchedPaymentPanel.setWidth("100%");
//		matchedPaymentPanel.setHeight("100%");
//		importedPaymentLayout.setWidth("100%");
//		importedPaymentLayout.setHeight("100%");
//		importedPaymentLayout.setStyle("border: none");
//		
//		north = new North();
//		north.setStyle("border: none");
//		matchedPaymentLayout.appendChild(north);
//		north.appendChild(matchedPaymentLabel);
//		
//		north = new North();
//		north.setStyle("border: none");
//		importedPaymentLayout.appendChild(north);
//		north.appendChild(importedPaymentLabel);
//		
//		
//		
//		
//		south = new South();
//		south.setStyle("border: none");
//		matchedPaymentLayout.appendChild(south);
//		south.appendChild(paymentInfo.rightAlign());
//		Center center = new Center();
//		
//		matchedPaymentLayout.appendChild(center);
//		center.appendChild(importedPaymentTable);
//		importedPaymentTable.setWidth("99%");
//		importedPaymentTable.setHeight("100%");
//		importedPaymentTable.setMultiSelection(true);
//		center.setStyle("border: none");
//		
//		currentPaymentTable.setWidth("99%");
//		currentPaymentTable.setHeight("99%");
//		currentPaymentTable.setMultiSelection(true);
//		
//		
//		
//		south = new South();
//		south.setStyle("border: none");
//		importedPaymentLayout.appendChild(south);
//		south.appendChild(invoiceInfo.rightAlign());
//		center = new Center();
//		importedPaymentLayout.appendChild(center);
//		
//		matchedPaymentTable.setWidth("99%");
//		matchedPaymentTable.setHeight("99%");
//		matchedPaymentTable.setMultiSelection(true);
//		
//		center.setStyle("border: none");
//		//
//		center = new Center();
//		center.setFlex(true);
//		mainLayout.appendChild(center);
//		center.appendChild(infoPanel);
//		
//		infoPanel.setStyle("border: none");
//		infoPanel.setWidth("100%");
//		infoPanel.setHeight("100%");
//		
//		north = new North();
//		north.setStyle("border: none");
//		north.setHeight("49%");
//		infoPanel.appendChild(north);
//		north.appendChild(importedPaymentPanel);
//		north.setSplittable(true);
//		center = new Center();
//		center.setStyle("border: none");
//		center.setFlex(true);
//		infoPanel.appendChild(center);
//		center.appendChild(matchedPaymentPanel);
	}   //  jbInit

	/**
	 *  Dynamic Init (prepare dynamic fields)
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception {
		//  Currency
		int columnId = 4917;    //  C_BankStatement.C_BankAccount_ID
		MLookup lookupCur = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, columnId, DisplayType.TableDir);
		bankAccountField = new WTableDirEditor("C_BankAccount_ID", true, false, true, lookupCur);
		if(getBankAccountId() > 0) {
			bankAccountField.setValue(getBankAccountId());
		}
		bankAccountField.addValueChangeListener(this);

		//  BPartner
		columnId = 3499;        //  C_Invoice.C_BPartner_ID
		MLookup lookupBP = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, columnId, DisplayType.Search);
		bpartnerSearch = new WSearchEditor("C_BPartner_ID", true, false, true, lookupBP);
		bpartnerSearch.addValueChangeListener(this);
		//	For other
		dateFromField.addValueChangeListener(this);
		dateToField.addValueChangeListener(this);
		amtFromField.addValueChangeListener(this);
		amtToField.addValueChangeListener(this);
		//  Translation
		statusBar.setStatusLine("");
		statusBar.setStatusDB("");
	}   //  dynInit
	
	/**************************************************************************
	 *  Action Listener.
	 *  - MultiCurrency
	 *  - Allocate
	 *  @param e event
	 */
	public void onEvent(Event e) {
		log.config("");
		if (e.getTarget().getId().equals(ConfirmPanel.A_REFRESH)) {
			Clients.showBusy(null, true);
			refresh();
			Clients.showBusy(null, false);
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_PROCESS)) {
			Clients.showBusy(null, true);
			if(!isHasSelection()) {
				statusBar.setStatusLine(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched") + ": " + actionMatchUnMatch());
			} else {
				statusBar.setStatusLine(Msg.translate(Env.getCtx(), "BankStatementMatch.UnMatched") + ": " + actionUnMatchSelected(matchedPaymentTable));
			}
			loadMatchedPaymentsFromMatch();
			changeMessageButton();
			Clients.showBusy(null, false);
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			Clients.showBusy(null, true);
			saveData();
			Clients.showBusy(null, false);
		}
	}   //  actionPerformed
	
	/**
	 * 	Dispose
	 */
	public void dispose() {
		if(isFromStatement()) {
			form.dispose();
		} else {
			SessionManager.getAppDesktop().closeActiveWindow();
		}
	}	//	dispose

	/**
	 *  Table Model Listener.
	 *  - Recalculate Totals
	 *  @param e event
	 */
	public void tableChanged(WTableModelEvent e) {
//		boolean isUpdate = (e.getType() == WTableModelEvent.CONTENTS_CHANGED);
//		//  Not a table update
//		if (!isUpdate) {
//			return;
//		}
//
//		// The writeoff() function causes additional tableChanged events which can be ignored.  
//		if(m_isCalculating)
//			return;
//		m_isCalculating = true;
//		Clients.showBusy(null,true);
//		
//		int row = e.getFirstRow();
//		int col = e.getColumn();
//		boolean isInvoice = (e.getModel().equals(matchedPaymentTable.getModel()));
//		
////		String msg = writeOff(row, col, isInvoice, paymentTable, invoiceTable, isAutoWriteOff);
////		if(msg != null && msg.length() > 0)
////			FDialog.warn(getWindowNo(), "AllocationWriteOffWarn");
//
//		Clients.showBusy(null,false);
//		m_isCalculating = false;
	}   //  tableChanged

	/**
	 *  Vetoable Change Listener.
	 *  - Business Partner
	 *  - Currency
	 * 	- Date
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e) {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		if (name.equals("C_BankAccount_ID")) {
			if (value == null) {
				setBankAccountId(0);
			} else {
				setBankAccountId((int) value);
			}
		} else if(name.equals("C_BPartner_ID")) {
			if (value == null) {
				setBpartnerId(0);
			} else {
				setBpartnerId((int) value);
			}
		} else if(name.equals("AmtFrom")) {
			if (value == null) {
				setAmtFrom(null);
			} else {
				setAmtFrom((BigDecimal) value);
			}
		} else if(name.equals("AmtTo")) {
			if (value == null) {
				setAmtTo(null);
			} else {
				setAmtTo((BigDecimal) value);
			}
		} else if(name.equals("DateFrom")) {
			if (value == null) {
				setDateFrom(null);
			} else {
				setDateFrom((Timestamp) value);
			}
		} else if(name.equals("DateTo")) {
			if (value == null) {
				setDateTo(null);
			} else {
				setDateTo((Timestamp) value);
			}
		}
	}   //  vetoableChange
	
	
	/**************************************************************************
	 *  Save Data
	 */
	public void saveData() {
		if(!isAvailableForSave()) {
			return;
		}
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					statusBar.setStatusLine(saveData(getWindowNo(), trxName));
				}
			});
			//	If Ok
			refresh();
		} catch (Exception e) {
			FDialog.error(getWindowNo(), "Error", e.getLocalizedMessage());
			Clients.showBusy(null, false);
		} finally {
			//	
		}
	}   //  saveData
	
	/**
	 * Refresh Data
	 */
	private void refresh() {
		clear();
		setIsAvailableForSave(false);
		getParameters();
		String message = validateParameters();
		if(Util.isEmpty(message)) {
			loadPayments();
			loadImportedPayments();
			loadMatchedPayments();
		} else {
//			ADialog.error(getWindowNo(), null, "ValidationError", Msg.parseTranslation(Env.getCtx(), message));
		}
	}
	
	/**
	 * Get parameters for search
	 */
	private void getParameters() {
//		setBankAccountId((int) (bankAccountField.getValue() != null? bankAccountField.getValue(): 0));
//		setAmtFrom((amtFromField.getValue() != null? (BigDecimal) amtFromField.getValue(): null));
//		setAmtTo((amtToField.getValue() != null? (BigDecimal) amtToField.getValue(): null));
//		setDateFrom((dateFromField.getValue() != null? (Timestamp) dateFromField.getValue(): null));
//		setDateTo((dateToField.getValue() != null? (Timestamp) dateToField.getValue(): null));
//		//	Get for matched
		setMatchMode(matchMode.getSelectedIndex());
		//	
		chageLayout();
		//	
		setHasSelection(false);
		statusBar.setStatusLine("");
	}
	
	/**
	 * Apply changes for view
	 */
	private void chageLayout() {
		//	Disable account
		if(isFromStatement()) {
//			bankAccountField.setEnabled(false);
//			bankAccountField.setReadWrite(false);
//			if(dateToField.getValue() == null) {
//				dateToField.setValue(getStatementDate());
//			}
		} else {
//			bankAccountField.setEnabled(true);
//			bankAccountField.setReadWrite(true);
		}
//		simulateMatchButton.setText(getButtonMatchMessage());
//		simulateMatchButton.setToolTipText(getButtonMatchMessage());
	}
	
	/**
	 * Load Payments from DB
	 */
	private void loadPayments() {
		fillPayments(getPaymentData());
	}
	
	/**
	 * Fill Payments
	 * @param data
	 */
	private void fillPayments(Vector<Vector<Object>> data) {
		//  Remove previous listeners
//		currentPaymentTable.getModel().removeTableModelListener(this);
//		//  Set Model
//		DefaultTableModel model = new DefaultTableModel(data, getPaymentColumnNames());
//		model.addTableModelListener(this);
//		currentPaymentTable.setModel(model);
//		// 
//		configurePaymentTable(currentPaymentTable);
	}
	
	/**
	 * Load Imported Payments from DB
	 */
	private void loadImportedPayments() {
		fillImportedPayments(getImportedPaymentData());
	}
	
	/**
	 * Fill Imported Payments
	 * @param data
	 */
	private void fillImportedPayments(Vector<Vector<Object>> data) {
		//  Remove previous listeners
//		importedPaymentTable.getModel().removeTableModelListener(this);
//		//  Set Model
//		DefaultTableModel model = new DefaultTableModel(data, getImportedPaymentColumnNames());
//		model.addTableModelListener(this);
//		importedPaymentTable.setModel(model);
//		// 
//		configureImportedPaymentTable(importedPaymentTable);
	}
	
	/**
	 * Load Matched Payments from DB
	 */
	private void loadMatchedPayments() {
		fillMatchedPayments(new Vector<Vector<Object>>());
	}
	
	/**
	 * Load Matched payments from a match process
	 */
	private void loadMatchedPaymentsFromMatch() {
		Vector<Vector<Object>> matchedPayments = getMatchedPayments();
		setIsAvailableForSave(matchedPayments != null && !matchedPayments.isEmpty());
		fillMatchedPayments(matchedPayments);
	}
	
	/**
	 * Fill Matched Payments
	 * @param data
	 */
	private void fillMatchedPayments(Vector<Vector<Object>> data) {
		//  Remove previous listeners
//		matchedPaymentTable.getModel().removeTableModelListener(this);
//		//  Set Model
//		DefaultTableModel model = new DefaultTableModel(data, getMatchedPaymentColumnNames());
//		model.addTableModelListener(this);
//		matchedPaymentTable.setModel(model);
//		// 
//		configureMatchedPaymentTable(matchedPaymentTable);
		//	
	}
	
	/**
	 * Change Message from selection
	 */
	private void changeMessageButton() {
//		boolean deleteAllocation = false;
//		for (int row = 0; row < matchedPaymentTable.getRowCount(); row++) {
//			IDColumn record = (IDColumn) matchedPaymentTable.getValueAt(row, 0);
//			if(record != null
//					&& record.isSelected()) {
//				deleteAllocation = true;
//				break;
//			}
//		}
//		setHasSelection(deleteAllocation);
//		//	change button
//		simulateMatchButton.setText(getButtonMatchMessage(deleteAllocation));
//		simulateMatchButton.setToolTipText(getButtonMatchMessage(deleteAllocation));
	}
	
	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * @return
	 */
	public ADForm getForm() {
		return form;
	}
}   //  WAllocation
