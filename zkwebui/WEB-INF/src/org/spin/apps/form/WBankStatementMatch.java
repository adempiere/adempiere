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
import org.adempiere.webui.component.ListModelTable;
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
import org.compiere.minigrid.IDColumn;
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 835032945941924591L;

		public void setProcessInfo(org.compiere.process.ProcessInfo pi) {
			setFromPO(pi);
			setWindowNo(pi.getWindowNo());
			if(getBankAccountId() > 0
					&& bankAccountField != null) {
				bankAccountField.setValue(getBankAccountId());
				bankAccountField.setReadWrite(false);
			}
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
	private Panel importedPaymentPanel = new Panel();
	private Panel matchedPaymentPanel = new Panel();
	
	private Panel centerPanel = new Panel();
	
	private Label matchedPaymentLabel = new Label();
	private Label importedPaymentLabel = new Label();
	private Label currentPaymentLabel = new Label();

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
		
		matchedPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched"));
		currentPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Current"));
		importedPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Imported"));
		importedPaymentPanel.appendChild(importedPaymentLayout);
		matchedPaymentPanel.appendChild(matchedPaymentLayout);
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
		Panel importedPaymentPanel = new Panel();

		East east = new East();
		east.appendChild(importedPaymentPanel);
		importedPaymentPanel.appendChild(currentPaymentLabel);
		importedPaymentPanel.appendChild(currentPaymentTable);
		east.setWidth("70%");
		east.setSplittable(true);
		east.setStyle("border: none");
		centerPaymentLayout.appendChild(east);
		centerPaymentLayout.setStyle("border: none");
		Center paymentTableCenter = new Center();

		Panel paymentTablePanel = new Panel();
		paymentTableCenter.appendChild(paymentTablePanel);
		paymentTablePanel.appendChild(importedPaymentLabel);
		paymentTablePanel.appendChild(importedPaymentTable);
		paymentTableCenter.setStyle("border: none");
		
		centerPaymentLayout.appendChild(paymentTableCenter);
		centerPaymentLayout.setWidth("100%");

		Panel northCenter = new Panel();
		northCenter.setStyle("border: 1px solid #000; height:200px");
		northCenter.appendChild(centerPaymentLayout);
		//
		importedPaymentLayout.setWidth("100%");
		importedPaymentLayout.setHeight("100%");
		importedPaymentLayout.setStyle("border: none");

		
		matchedPaymentPanel.setWidth("100%");
		matchedPaymentPanel.setHeight("100%");
		matchedPaymentPanel.appendChild(matchedPaymentLabel);
		matchedPaymentPanel.appendChild(matchedPaymentTable);
		
		centerPanel.appendChild(northCenter);
		centerPanel.appendChild(matchedPaymentPanel);
		
		matchedPaymentTable.setMultiSelection(true);
		
		mainLayout.appendChild(north);
		mainLayout.appendChild(center);
		mainLayout.appendChild(south);
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
		bankAccountField.setMandatory(true);
		//  BPartner
		columnId = 3499;        //  C_Invoice.C_BPartner_ID
		MLookup lookupBP = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, columnId, DisplayType.Search);
		bpartnerSearch = new WSearchEditor("C_BPartner_ID", true, false, true, lookupBP);
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
			refresh();
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_PROCESS)) {
			if(!isHasSelection()) {
				statusBar.setStatusLine(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched") + ": " + actionMatchUnMatch());
			} else {
				statusBar.setStatusLine(Msg.translate(Env.getCtx(), "BankStatementMatch.UnMatched") + ": " + actionUnMatchSelected(matchedPaymentTable));
			}
			loadMatchedPaymentsFromMatch();
			changeMessageButton();
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			saveData();
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
		boolean isUpdate = (e.getType() == WTableModelEvent.CONTENTS_CHANGED);
		//  Not a table update
		if (!isUpdate) {
			return;
		}
		//	
		boolean isMatched = false;
		int row = e.getFirstRow();
		if(e.getModel().equals(currentPaymentTable.getModel())) {
			int matchedRow = currentPaymentTable.getSelectedRow();
			if(matchedRow != row) {
				return;
			}
			if(matchedRow >= 0) {
				IDColumn paymentIdColumn = (IDColumn) currentPaymentTable.getValueAt(matchedRow, 0);
				isMatched = paymentApplyForMatch(paymentIdColumn.getRecord_ID());
			}
		} else if(e.getModel().equals(importedPaymentTable.getModel())) {
			int matchedRow = importedPaymentTable.getSelectedRow();
			if(matchedRow != row) {
				return;
			}
			if(matchedRow >= 0) {
				IDColumn importedPaymentIdColumn = (IDColumn) importedPaymentTable.getValueAt(matchedRow, 0);
				isMatched = importedPaymentApplyForMatch(importedPaymentIdColumn.getRecord_ID());
			}
		} else if(e.getModel().equals(matchedPaymentTable.getModel())) {
			int matchedRow = matchedPaymentTable.getSelectedRow();
			if(matchedRow != row) {
				return;
			}
			if(matchedRow >= 0) {
				IDColumn matchedPaymentIdColumn = (IDColumn) matchedPaymentTable.getValueAt(matchedRow, 0);
				selectFromMatch(currentPaymentTable, importedPaymentTable, matchedPaymentIdColumn.getRecord_ID());
				changeMessageButton();
			}
		}
		//	Validate screen
		if(isMatched) {
			loadMatchedPaymentsFromMatch();
		}
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
			if(isFromStatement()) {
				dispose();
			}
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
			Clients.showBusy(null, true);
			loadPayments();
			loadImportedPayments();
			loadMatchedPayments();
			Clients.showBusy(null, false);
		} else {
			FDialog.error(getWindowNo(), getForm(), "ValidationError", Msg.parseTranslation(Env.getCtx(), message));
		}
	}
	
	/**
	 * Get parameters for search
	 */
	private void getParameters() {
		setBankAccountId((int) (bankAccountField.getValue() != null? bankAccountField.getValue(): 0));
		setAmtFrom((amtFromField.getValue() != null? (BigDecimal) amtFromField.getValue(): null));
		setAmtTo((amtToField.getValue() != null? (BigDecimal) amtToField.getValue(): null));
		setDateFrom((dateFromField.getValue() != null? (Timestamp) dateFromField.getValue(): null));
		setDateTo((dateToField.getValue() != null? (Timestamp) dateToField.getValue(): null));
		//	Get for matched
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
			bankAccountField.setReadWrite(false);
			if(dateToField.getValue() == null) {
				dateToField.setValue(getStatementDate());
			}
		} else {
			bankAccountField.setReadWrite(true);
		}
		simulateMatchButton.setLabel(getButtonMatchMessage());
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
		currentPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		currentPaymentTable.setData(model, getCurrentPaymentColumnNames());
		// 
		configureCurrentPaymentTable(currentPaymentTable);
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
		importedPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		importedPaymentTable.setData(model, getImportedPaymentColumnNames());
		// 
		configureImportedPaymentTable(importedPaymentTable);
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
		matchedPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		matchedPaymentTable.setData(model, getMatchedPaymentColumnNames());
		// 
		configureMatchedPaymentTable(matchedPaymentTable);
		//	
	}
	
	/**
	 * Change Message from selection
	 */
	private void changeMessageButton() {
		boolean deleteAllocation = false;
		for (int row = 0; row < matchedPaymentTable.getRowCount(); row++) {
			IDColumn record = (IDColumn) matchedPaymentTable.getValueAt(row, 0);
			if(record != null
					&& record.isSelected()) {
				deleteAllocation = true;
				break;
			}
		}
		setHasSelection(deleteAllocation);
		//	change button
		simulateMatchButton.setLabel(getButtonMatchMessage(deleteAllocation));
	}
	
	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * @return
	 */
	public ADForm getForm() {
		return form;
	}
}   //  WAllocation
