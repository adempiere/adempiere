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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;


import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.ListHeader;
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
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;

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
	private WTableDirEditor chargeField = null;
	private WNumberEditor differenceAmtField = new WNumberEditor("DifferenceAmt", false, true, false, DisplayType.Amount, Msg.translate(Env.getCtx(), "DifferenceAmt"));
	private Label differenceAmtLabel = new Label();
	private Label chargeLabel = new Label();
	private Button acceptManualMatchButton;
	private South matchedSouth;
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
		acceptManualMatchButton = new Button(Msg.translate(Env.getCtx(), "OK"));
		acceptManualMatchButton.setTooltiptext(Msg.translate(Env.getCtx(), "BankStatementMatch.ManualMatch"));
		acceptManualMatchButton.setEnabled(false);
		acceptManualMatchButton.addActionListener(this);
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

		actionPanel.setStyle("display:flex; justify-content:space-between; align-items:center; width:100%; padding:4px 0");

		Panel leftActionPanel = new Panel();
		leftActionPanel.setStyle("display:flex; align-items:center; gap:6px; flex-wrap:wrap");
		leftActionPanel.appendChild(simulateMatchButton);

		Panel diffPanel = new Panel();
		diffPanel.setStyle("display:flex; align-items:center; gap:4px");
		differenceAmtField.getComponent().setWidth("100px");
		diffPanel.appendChild(differenceAmtLabel);
		diffPanel.appendChild(differenceAmtField.getComponent());
		leftActionPanel.appendChild(diffPanel);

		Panel chargePanel = new Panel();
		chargePanel.setStyle("display:flex; align-items:center; gap:4px");
		chargeField.getComponent().setWidth("180px");
		chargePanel.appendChild(chargeLabel);
		chargePanel.appendChild(chargeField.getComponent());
		leftActionPanel.appendChild(chargePanel);

		leftActionPanel.appendChild(acceptManualMatchButton);

		Panel rightActionPanel = new Panel();
		rightActionPanel.appendChild(confirmPanel);

		actionPanel.appendChild(leftActionPanel);
		actionPanel.appendChild(rightActionPanel);
		
		
		Center center = new Center();
		center.setStyle("border: none");
		center.appendChild(centerPanel);

		// East: pagos del sistema — North=label, Center=tabla (scroll automático)
		Borderlayout currentLayout = new Borderlayout();
		North currentNorth = new North();
		currentNorth.setStyle("border: none");
		currentNorth.appendChild(currentPaymentLabel);
		Center currentCenter = new Center();
		currentCenter.setStyle("border: none");
		currentCenter.appendChild(currentPaymentTable);
		currentLayout.appendChild(currentNorth);
		currentLayout.appendChild(currentCenter);

		East east = new East();
		east.setWidth("50%");
		east.setSplittable(true);
		east.setStyle("border: none");
		east.appendChild(currentLayout);
		centerPaymentLayout.appendChild(east);
		centerPaymentLayout.setWidth("100%");
		centerPaymentLayout.setHeight("100%");
		centerPaymentLayout.setStyle("border: none");

		// Center: importados del banco — North=label, Center=tabla (scroll automático)
		Borderlayout importedLayout = new Borderlayout();
		North importedNorth = new North();
		importedNorth.setStyle("border: none");
		importedNorth.appendChild(importedPaymentLabel);
		Center importedCenter = new Center();
		importedCenter.setStyle("border: none");
		importedCenter.appendChild(importedPaymentTable);
		importedLayout.appendChild(importedNorth);
		importedLayout.appendChild(importedCenter);

		Center paymentTableCenter = new Center();
		paymentTableCenter.setStyle("border: none");
		paymentTableCenter.appendChild(importedLayout);
		centerPaymentLayout.appendChild(paymentTableCenter);

		// Inner Borderlayout principal: Center=tablas pagos, South=matched colapsible
		Borderlayout innerCenterLayout = new Borderlayout();

		Center paymentsRegion = new Center();
		paymentsRegion.setStyle("border: none");
		paymentsRegion.appendChild(centerPaymentLayout);
		innerCenterLayout.appendChild(paymentsRegion);

		matchedSouth = new South();
		matchedSouth.setTitle(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched"));
		matchedSouth.setCollapsible(true);
		matchedSouth.setSplittable(true);
		matchedSouth.setAutoscroll(true);
		matchedSouth.setFlex(true);
		matchedSouth.setSize("250px");
		matchedSouth.setOpen(false);
		matchedSouth.setStyle("border: none");
		matchedSouth.appendChild(matchedPaymentTable);
		innerCenterLayout.appendChild(matchedSouth);

		centerPanel.setStyle("width:100%; height:100%");
		centerPanel.appendChild(innerCenterLayout);
		//	Selection is handled here in batch instead of relying on the renderer's
		//	ON_SELECT loop (one model event per row): the first model event makes
		//	WListbox re-render the row items, the re-created items lose their
		//	selection and the renderer loop stops firing events for the remaining
		//	rows, so a header select-all only ever processed the first row.
		//	This listener is registered before the renderer's one, so after it runs
		//	every IDColumn mirrors the item selection and the renderer loop is a no-op.
		EventListener selectionListener = new EventListener() {
			public void onEvent(Event e) throws Exception {
				WListbox tbl = (WListbox) e.getTarget();
				if (tbl == matchedPaymentTable) {
					//	Sync IDColumn flags so unmatch processes every checked row
					for (int row = 0; row < tbl.getRowCount(); row++) {
						IDColumn id = (IDColumn) tbl.getValueAt(row, 0);
						org.zkoss.zul.Listitem item = tbl.getItemAtIndex(row);
						if (id != null && item != null) {
							id.setSelected(item.isSelected());
						}
					}
					changeMessageButton();
					return;
				}
				boolean isCurrent = tbl == currentPaymentTable;
				boolean anyMatched = false;
				String blockedMessage = null;
				for (int row = 0; row < tbl.getRowCount(); row++) {
					IDColumn id = (IDColumn) tbl.getValueAt(row, 0);
					org.zkoss.zul.Listitem item = tbl.getItemAtIndex(row);
					if (id == null || item == null) {
						continue;
					}
					int recordId = id.getRecord_ID();
					boolean wanted = item.isSelected();
					boolean current = isCurrent
							? getSelectedPaymentIds().contains(recordId)
							: getSelectedImportedPaymentIds().contains(recordId);
					if (wanted == current) {
						//	No state change: just heal a stale IDColumn flag
						id.setSelected(wanted);
						continue;
					}
					if (wanted) {
						String blocked = isCurrent
								? validatePaymentSelection(recordId)
								: validateImportedSelection(recordId);
						if (blocked != null) {
							item.setSelected(false);
							id.setSelected(false);
							blockedMessage = blocked;
							continue;
						}
					}
					id.setSelected(wanted);
					if (isCurrent) {
						anyMatched |= paymentApplyForMatch(recordId);
					} else {
						anyMatched |= importedPaymentApplyForMatch(recordId);
					}
				}
				statusBar.setStatusLine(blockedMessage != null ? blockedMessage : "");
				if (anyMatched) {
					Events.echoEvent("onAfterAutoMatch", centerPanel, null);
				}
				updateManualMatchUI();
			}
		};
		currentPaymentTable.addEventListener(Events.ON_SELECT, selectionListener);
		importedPaymentTable.addEventListener(Events.ON_SELECT, selectionListener);
		matchedPaymentTable.addEventListener(Events.ON_SELECT, selectionListener);

		//	Re-apply visual checks from IDColumn state after a deferred model render.
		//	Unlike Swing, ZK does not derive the checkbox from the IDColumn, and the
		//	header select-all only fires events for rows whose IDColumn differs from
		//	the item selection, so both must be kept in sync.
		EventListener syncSelectionListener = new EventListener() {
			public void onEvent(Event e) throws Exception {
				WListbox tbl = (WListbox) e.getTarget();
				for (int row = 0; row < tbl.getRowCount(); row++) {
					IDColumn id = (IDColumn) tbl.getValueAt(row, 0);
					org.zkoss.zul.Listitem item = tbl.getItemAtIndex(row);
					if (id != null && item != null && item.isSelected() != id.isSelected()) {
						item.setSelected(id.isSelected());
					}
				}
			}
		};
		currentPaymentTable.addEventListener("onSyncSelection", syncSelectionListener);
		importedPaymentTable.addEventListener("onSyncSelection", syncSelectionListener);

		centerPanel.addEventListener("onAfterAutoMatch", new EventListener() {
			public void onEvent(Event evt) throws Exception {
				loadMatchedPaymentsFromMatch();
				fillImportedPayments(getUnmatchedImportedRows());
				fillPayments(getUnmatchedPaymentRows());
				showMatchedPanel();
			}
		});

		matchedPaymentTable.setMultiSelection(true);
		importedPaymentTable.setMultiSelection(true);
		currentPaymentTable.setMultiSelection(true);

		differenceAmtLabel.setText(Msg.translate(Env.getCtx(), "DifferenceAmt"));
		chargeLabel.setText(Msg.translate(Env.getCtx(), "C_Charge_ID"));

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
		//  Charge
		int chargeColumnId = 61804; // C_AllocationLine.C_Charge_ID
		MLookup chargeLookup = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0, chargeColumnId, DisplayType.TableDir);
		chargeField = new WTableDirEditor("C_Charge_ID", false, false, true, chargeLookup);
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
			fillImportedPayments(getUnmatchedImportedRows());
			fillPayments(getUnmatchedPaymentRows());
			showMatchedPanel();
			changeMessageButton();
		} else if (e.getTarget().equals(acceptManualMatchButton)) {
			boolean reverseMatch = isReverseMatch();
			if (reverseMatch && !getSelectedImportedPaymentIds().isEmpty()) {
				// Reverse match: N payments -> 1 imported
				int chargeId = chargeField.getValue() != null ? ((Number) chargeField.getValue()).intValue() : 0;
				BigDecimal differenceAmt = Optional.ofNullable((BigDecimal) differenceAmtField.getValue()).orElse(Env.ZERO);
				if (chargeId <= 0
						&& differenceAmt.compareTo(Env.ZERO) != 0) {
					FDialog.error(getWindowNo(), getForm(), Msg.parseTranslation(Env.getCtx(), "@C_Charge_ID@ @IsMandatory@"));
				} else {
					// Save IDs BEFORE forceManualMatchAllPayments resets them
					List<Integer> matchedImportedIdsList = new ArrayList<>(getSelectedImportedPaymentIds());
					List<Integer> matchedPaymentIdsList = new ArrayList<>(getSelectedPaymentIds());
					int importedId = matchedImportedIdsList.get(0);
					boolean matched = forceManualMatchAllPayments(importedId, chargeId);
					if (matched) {
						loadMatchedPaymentsFromMatch();
						removeRowsFromImportedTable(matchedImportedIdsList);
						removeRowsFromCurrentTable(matchedPaymentIdsList);
						showMatchedPanel();
					}
					updateManualMatchUI();
				}
			} else {
				// Existing: N imported -> 1 payment
				int selectedPaymentId = getCurrentSelectedPaymentId();
				if (selectedPaymentId > 0 && !getSelectedImportedPaymentIds().isEmpty()) {
					int chargeId = chargeField.getValue() != null ? ((Number) chargeField.getValue()).intValue() : 0;
					BigDecimal differenceAmt = Optional.ofNullable((BigDecimal) differenceAmtField.getValue()).orElse(Env.ZERO);
					if (chargeId <= 0
							&& differenceAmt.compareTo(Env.ZERO) !=0 ) {
						FDialog.error(getWindowNo(), getForm(), Msg.parseTranslation(Env.getCtx(), "@C_Charge_ID@ @IsMandatory@"));
					} else {
						List<Integer> matchedImportedIds = new ArrayList<>(getSelectedImportedPaymentIds());
						boolean matched = forceManualMatchAll(selectedPaymentId, chargeId);
						if (matched) {
							loadMatchedPaymentsFromMatch();
							removeRowsFromImportedTable(matchedImportedIds);
							removeRowFromCurrentTable(selectedPaymentId);
							showMatchedPanel();
						}
						updateManualMatchUI();
					}
				}
			}
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
		int row = e.getFirstRow();
		if (row < 0) {
			return;
		}
		//  Selection is handled in batch on ON_SELECT; here only the ChargeAmt
		//  edit of the matched table (editable column 8) reaches the model
		if(e.getModel().equals(matchedPaymentTable.getModel())
				&& e.getColumn() == 8
				&& row < matchedPaymentTable.getRowCount()) {
			IDColumn matchedPaymentIdColumn = (IDColumn) matchedPaymentTable.getValueAt(row, 0);
			if (matchedPaymentIdColumn != null) {
				BigDecimal chargeAmt = (BigDecimal) matchedPaymentTable.getValueAt(row, 8);
				setImportedChargeAmt(matchedPaymentIdColumn.getRecord_ID(), chargeAmt);
			}
		}
	}   //  tableChanged

	/**
	 * Validate if a payment row can be checked.
	 * @return translated message when blocked, null when allowed
	 */
	private String validatePaymentSelection(int paymentId) {
		// Block only if multiple imported AND already have payments (many-to-many)
		if (getSelectedImportedPaymentIds().size() > 1 && getSelectedPaymentIds().size() >= 1) {
			return Msg.translate(Env.getCtx(), "BankStatementMatch.ManyToManyNotAllowed");
		}
		//	Income and outcome can be mixed, the controller distributes the amounts with sign
		return null;
	}

	/**
	 * Validate if an imported row can be checked.
	 * @return translated message when blocked, null when allowed
	 */
	private String validateImportedSelection(int importedId) {
		// Block only if multiple payments AND already have imported selected (many-to-many)
		if (getSelectedPaymentIds().size() > 1 && getSelectedImportedPaymentIds().size() >= 1) {
			return Msg.translate(Env.getCtx(), "BankStatementMatch.ManyToManyNotAllowed");
		}
		//	Income and outcome can be mixed, the controller distributes the amounts with sign
		return null;
	}

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
		//  Keep the sort applied by the user, replacing the model resets it
		SortState sortState = SortState.of(currentPaymentTable);
		//  Remove previous listeners
		currentPaymentTable.getModel().removeTableModelListener(this);
		//  Row vectors are reused between fills: align IDColumn flags with the controller selection
		syncIdColumnSelection(data, getSelectedPaymentIds());
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		currentPaymentTable.setData(model, getCurrentPaymentColumnNames());
		//
		configureCurrentPaymentTable(currentPaymentTable);
		applyColumnWidths(currentPaymentTable,
				null,      // 0 Select (IDColumn: 35px auto)
				"90px",    // 1 DateTrx
				"60px",    // 2 IsReceipt
				"110px",   // 3 DocumentNo
				"150px",   // 4 BPartner
				"90px",    // 5 TenderType
				"60px",    // 6 Currency
				"100px",   // 7 Amount
				null       // 8 Description - ocupa el espacio restante
		);
		sortState.restore(currentPaymentTable);
		//	Restore visual checks once the deferred render is done
		Events.echoEvent("onSyncSelection", currentPaymentTable, null);
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
		//  Keep the sort applied by the user, replacing the model resets it
		SortState sortState = SortState.of(importedPaymentTable);
		//  Remove previous listeners
		importedPaymentTable.getModel().removeTableModelListener(this);
		//  Row vectors are reused between fills: align IDColumn flags with the controller selection
		syncIdColumnSelection(data, getSelectedImportedPaymentIds());
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		importedPaymentTable.setData(model, getImportedPaymentColumnNames());
		//
		configureImportedPaymentTable(importedPaymentTable);
		applyColumnWidths(importedPaymentTable,
				null,      // 0 Select (IDColumn: 35px auto)
				"90px",    // 1 DateTrx
				"60px",    // 2 IsReceipt
				"110px",   // 3 ReferenceNo
				"150px",   // 4 BPartner
				"60px",    // 5 Currency
				"100px",   // 6 Amount
				null       // 7 Memo - ocupa el espacio restante
		);
		sortState.restore(importedPaymentTable);
		//	Restore visual checks once the deferred render is done
		Events.echoEvent("onSyncSelection", importedPaymentTable, null);
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
		fillMatchedPayments(matchedPayments);
	}
	
	/**
	 * Fill Matched Payments
	 * @param data
	 */
	private void fillMatchedPayments(Vector<Vector<Object>> data) {
		//  Keep the sort applied by the user, replacing the model resets it
		SortState sortState = SortState.of(matchedPaymentTable);
		//  Remove previous listeners
		matchedPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		matchedPaymentTable.setData(model, getMatchedPaymentColumnNames());
		//
		configureMatchedPaymentTable(matchedPaymentTable);
		applyColumnWidths(matchedPaymentTable,
				null,      // 0 Select (IDColumn: 35px auto)
				"90px",    // 1 DateTrx
				"60px",    // 2 IsReceipt
				"110px",   // 3 DocumentNo
				"130px",   // 4 BPartner
				"80px",    // 5 TenderType
				"60px",    // 6 Currency
				"100px",   // 7 Amount (TrxAmt)
				"100px",   // 8 ChargeAmt
				"120px",   // 9 Description
				"100px",   // 10 ReferenceNo
				"120px",   // 11 ChargeName
				null       // 12 Memo - ocupa el espacio restante
		);
		sortState.restore(matchedPaymentTable);
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
	 * Overrides column widths set by autoSize() (which uses header-text × 9px).
	 * Pass null to leave a column at its auto-calculated width.
	 */
	private void applyColumnWidths(WListbox table, String... widths) {
		org.zkoss.zul.Listhead head = table.getListhead();
		if (head == null) return;
		List<org.zkoss.zk.ui.Component> children = head.getChildren();
		for (int i = 0; i < widths.length && i < children.size(); i++) {
			if (widths[i] != null) {
				((ListHeader) children.get(i)).setWidth(widths[i]);
			}
		}
	}

	/**
	 * Sort applied on a listbox, taken before the model is replaced and applied again after it.
	 *
	 * WListbox.setData recreates the list head, so the sort direction chosen by the user on the
	 * Listheader is lost and the new model arrives on the order given by the query.
	 */
	private static class SortState {

		private static final String ASCENDING = "ascending";
		private static final String DESCENDING = "descending";

		private final int columnIndex;
		private final boolean ascending;

		private SortState(int columnIndex, boolean ascending) {
			this.columnIndex = columnIndex;
			this.ascending = ascending;
		}

		/**
		 * Take the sort currently applied on a table
		 * @param table
		 * @return
		 */
		private static SortState of(WListbox table) {
			org.zkoss.zul.Listhead head = table.getListhead();
			if (head != null) {
				List<org.zkoss.zk.ui.Component> children = head.getChildren();
				for (int index = 0; index < children.size(); index++) {
					org.zkoss.zk.ui.Component child = children.get(index);
					if (child instanceof org.zkoss.zul.Listheader) {
						String direction = ((org.zkoss.zul.Listheader) child).getSortDirection();
						if (ASCENDING.equals(direction) || DESCENDING.equals(direction)) {
							return new SortState(index, ASCENDING.equals(direction));
						}
					}
				}
			}
			//	Not sorted
			return new SortState(-1, true);
		}

		/**
		 * Apply the sort again, it does nothing when the table was not sorted
		 * @param table
		 */
		private void restore(WListbox table) {
			if (columnIndex < 0) {
				return;
			}
			org.zkoss.zul.Listhead head = table.getListhead();
			if (head == null) {
				return;
			}
			List<org.zkoss.zk.ui.Component> children = head.getChildren();
			if (columnIndex >= children.size()
					|| !(children.get(columnIndex) instanceof org.zkoss.zul.Listheader)) {
				return;
			}
			org.zkoss.zul.Listheader header = (org.zkoss.zul.Listheader) children.get(columnIndex);
			//	Columns without a comparator, like the selection one, cannot be sorted
			if (header.getSortAscending() == null && header.getSortDescending() == null) {
				return;
			}
			header.sort(ascending);
		}
	}

	private void showMatchedPanel() {
		if (!matchedSouth.isOpen()) {
			matchedSouth.setOpen(true);
		}
	}

	/**
	 * Align the IDColumn selected flag of each row with the controller selection.
	 * Rows kept out of sync suppress the header select-all events (the renderer
	 * only fires a change when IDColumn and item selection differ).
	 */
	private void syncIdColumnSelection(Vector<Vector<Object>> data, List<Integer> selectedIds) {
		for (Vector<Object> rowData : data) {
			IDColumn id = (IDColumn) rowData.get(0);
			if (id != null) {
				id.setSelected(selectedIds.contains(id.getRecord_ID()));
			}
		}
	}

	private void updateManualMatchUI() {
		boolean hasImportedSelection = !getSelectedImportedPaymentIds().isEmpty();
		boolean hasPaymentSelection = getCurrentSelectedPaymentId() > 0
				|| getSelectedPaymentIds().size() > 1;
		if (hasImportedSelection && hasPaymentSelection) {
			BigDecimal diff = calculateManualDifference();
			if (diff != null) {
				differenceAmtField.setValue(diff);
				boolean hasDiff = diff.compareTo(BigDecimal.ZERO) != 0;
				chargeField.setReadWrite(hasDiff);
				if (!hasDiff) {
					chargeField.setValue(null);
				}
				acceptManualMatchButton.setEnabled(true);
			}
		} else {
			differenceAmtField.setValue(null);
			chargeField.setValue(null);
			chargeField.setReadWrite(false);
			acceptManualMatchButton.setEnabled(false);
		}
	}

	private void removeRowsFromImportedTable(List<Integer> idsToRemove) {
		ListModelTable model = (ListModelTable) importedPaymentTable.getModel();
		model.removeTableModelListener(this);
		for (int row = model.size() - 1; row >= 0; row--) {
			@SuppressWarnings("unchecked")
			Vector<Object> rowData = (Vector<Object>) model.get(row);
			IDColumn id = (IDColumn) rowData.get(0);
			if (id != null && idsToRemove.contains(id.getRecord_ID())) {
				model.remove(row);
			}
		}
		model.addTableModelListener(this);
	}

	private void removeRowFromCurrentTable(int paymentId) {
		ListModelTable model = (ListModelTable) currentPaymentTable.getModel();
		model.removeTableModelListener(this);
		for (int row = model.size() - 1; row >= 0; row--) {
			@SuppressWarnings("unchecked")
			Vector<Object> rowData = (Vector<Object>) model.get(row);
			IDColumn id = (IDColumn) rowData.get(0);
			if (id != null && id.getRecord_ID() == paymentId) {
				model.remove(row);
				break;
			}
		}
		model.addTableModelListener(this);
	}

	private void removeRowsFromCurrentTable(List<Integer> paymentIds) {
		ListModelTable model = (ListModelTable) currentPaymentTable.getModel();
		model.removeTableModelListener(this);
		for (int row = model.size() - 1; row >= 0; row--) {
			@SuppressWarnings("unchecked")
			Vector<Object> rowData = (Vector<Object>) model.get(row);
			IDColumn id = (IDColumn) rowData.get(0);
			if (id != null && paymentIds.contains(id.getRecord_ID())) {
				model.remove(row);
			}
		}
		model.addTableModelListener(this);
	}

	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * @return
	 */
	public ADForm getForm() {
		return form;
	}
}   //  WAllocation
