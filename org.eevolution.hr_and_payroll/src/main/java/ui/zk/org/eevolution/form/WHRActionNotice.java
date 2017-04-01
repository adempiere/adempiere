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
 * Contributor(s): Oscar Gomez & Victor Perez www.e-evolution.com             *
 * Copyright (C) 2003-2011 e-Evolution,SC. All Rights Reserved.               *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.component.ConfirmPanel;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.model.MRefList;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRPeriod;
import org.eevolution.model.MHRProcess;
import org.eevolution.model.X_HR_Concept;
import org.eevolution.service.HRActionNotice;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Div;
import org.zkoss.zul.Space;

/**
 * HRActionNotice Form
 * 
 * @author oscar.gomez@e-evolution.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/779">
 * 		@see FR [ 779 ] Payroll Action Notice is slow</a>
 */
public class WHRActionNotice extends HRActionNotice implements IFormController,
		EventListener, WTableModelListener, ValueChangeListener {

	private CustomForm form = new CustomForm();

	/**
	 * Initialize Panel
	 * 
	 * @param WindowNo
	 *            window
	 * @param frame
	 *            frame
	 */
	public WHRActionNotice() {
		try {
			super.dynInit();
			dynInit();
			zkInit();
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	} // init

	//
	private Borderlayout mainLayout = new Borderlayout();
	private Grid parameterPanel = new GridFactory().newGridLayout();
	// Process
	private Label labelProcess = new Label();
	private Combobox fieldProcess = new Combobox();
	// BPartner
	private Label labelBPartner = new Label();
	private Combobox fieldEmployee = new Combobox();
	// Date
	private Label labelValidFrom = new Label();
	public WDateEditor fieldValidFrom = new WDateEditor();
	// Concept
	private Label labelConcept = new Label();
	private Combobox fieldConcept = new Combobox();
	private Combobox fieldTextLookup = new Combobox();
	// ColumnType
	private Label labelColumnType = new Label();
	private WStringEditor fieldColumnType = new WStringEditor();
	// Concept's
	private WDateEditor fieldDate = new WDateEditor();
	public WNumberEditor fieldQty = new WNumberEditor();
	public WNumberEditor fieldAmount = new WNumberEditor();
	public WStringEditor fieldText = new WStringEditor();
	//
	private Label labelDescription = new Label();
	public WStringEditor fieldDescription = new WStringEditor();
	private Button bOk = new Button();
	
	private WListbox miniTable = ListboxFactory.newDataTable();

	Language language = Language.getLoginLanguage(); // Base Language
	int AD_Column_ID = 0;

	/**
	 * Static Init
	 * 
	 * @throws Exception
	 */
	private void zkInit() throws Exception {
		form.setWidth("99%");
		form.setHeight("100%");
		form.setStyle("position: absolute; padding: 0; margin: 0");
		form.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");
		// Process
		labelProcess.setText(Msg.translate(Env.getCtx(), "HR_Process_ID"));
		fieldProcess.addEventListener(Events.ON_SELECT, this);
		// BPartner
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fieldEmployee.addEventListener(Events.ON_SELECT, this);
		// Date
		labelValidFrom.setText(Msg.translate(Env.getCtx(), "Date"));
		fieldValidFrom.addValueChangeListener(this);
		// Concept
		labelConcept.setText(Msg.translate(Env.getCtx(), "HR_Concept_ID"));
		fieldConcept.addEventListener(Events.ON_SELECT, this);
		// Concept's
		labelColumnType.setText(Msg.translate(Env.getCtx(), "ColumnType"));

		labelDescription.setText(Msg.translate(Env.getCtx(), "Description"));
		fieldDate.setVisible(false);
		fieldDate.addValueChangeListener(this);
		fieldQty.setVisible(false);
		fieldQty.addValueChangeListener(this);
		fieldAmount.setVisible(false);
		fieldAmount.addValueChangeListener(this);
		fieldText.setVisible(false);
		fieldText.addValueChangeListener(this);
		fieldDescription.addValueChangeListener(this);
		fieldColumnType.setReadWrite(false);
		ConfirmPanel panel = new ConfirmPanel(false, false, false, false, false, false, false);
		bOk = panel.createButton(ConfirmPanel.A_OK);
		bOk.addActionListener(this);

		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);

		Rows rows = new Rows();
		rows.setParent(parameterPanel);

		Row row = rows.newRow();
		row.appendChild(labelProcess.rightAlign());
		row.appendChild(fieldProcess);
		fieldProcess.setWidth("100%");
		row.appendChild(labelBPartner.rightAlign());
		row.appendChild(fieldEmployee);
		fieldEmployee.setWidth("100%");

		row = rows.newRow();
		row.appendChild(labelValidFrom.rightAlign());
		row.appendChild(fieldValidFrom.getComponent());
		row.appendChild(labelConcept.rightAlign());
		row.appendChild(fieldConcept);
		fieldConcept.setWidth("100%");

		row = rows.newRow();
		//  Add div for Qty-Amount-Date-Text-RuleEngine
		Div div = new Div();
		row.appendChild(labelColumnType.rightAlign());
		row.appendChild(fieldColumnType.getComponent());
		row.appendChild(new Space());
		div.appendChild(fieldDate.getComponent());
		div.appendChild(fieldQty.getComponent());
		div.appendChild(fieldAmount.getComponent());
		div.appendChild(fieldText.getComponent());
		div.appendChild(fieldTextLookup);
		row.appendChild(div);
		fieldDate.fillHorizontal();
		fieldQty.fillHorizontal();
		fieldAmount.fillHorizontal();
		fieldText.fillHorizontal();
		fieldValidFrom.fillHorizontal();
		fieldTextLookup.setWidth("100%");
		//	End Yamel Senih
		row = rows.newRow();
		row.appendChild(labelDescription.rightAlign());
		row.appendChild(fieldDescription.getComponent());
		row.appendChild(new Space());
		row.appendChild(bOk);
		//	
		Center center = new Center();
		center.setFlex(true);
		center.appendChild(miniTable);
		mainLayout.appendChild(center);
		miniTable.setVflex(true);
		miniTable.setFixedLayout(true);
		miniTable.setWidth("99%");
		miniTable.setHeight("99%");
		configureMiniTable();

	} // jbInit

	/**
	 * Configure Table
	 */
	private void configureMiniTable() {

		ColumnInfo[] layout = {
				new ColumnInfo(Msg.translate(Env.getCtx(), "AD_Org_ID"), "", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "HR_Concept_ID"), "", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "ValidFrom"), "", Timestamp.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "ColumnType"), "", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"), "", BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Amount"), "", BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "ServiceDate"), "", Timestamp.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "TextMsg"), "", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "", String.class) 
				};

		miniTable.prepareTable(layout, "", "", false, "");
	}

	/**
	 * Dynamic Init (prepare dynamic fields)
	 * 
	 * @throws Exception
	 *             if Lookups cannot be initialized
	 */
	@Override
	public void dynInit() throws Exception {
		KeyNamePair[] processData = getProcess();
		for (KeyNamePair pp : processData)
			fieldProcess.appendItem(pp.getName(), pp);
		fieldProcess.setSelectedIndex(0);
	} // dynInit

	/**
	 * Load Text Message Lookup
	 * @param referenceId
	 * @return void
	 */
	private void loadTextMsgLookup(int referenceId) {
		//	Remove all Items
		fieldTextLookup.removeAllItems();
		isLookupTextMsg = false;
		//	Valid reference
		if(referenceId == 0)
			return;
		//	Set to new
		ArrayList<ValueNamePair> conceptData = getConceptReference(referenceId);
		for(ValueNamePair vp : conceptData) {
			fieldTextLookup.appendItem(vp.getName(), vp);
		}
		//	Set Flag
		isLookupTextMsg = true;
	}
	
	/**************************************************************************
	 * Action Listener. - MultiCurrency - Allocate
	 * 
	 * @param e
	 *            event
	 */
	@Override
	public void onEvent(Event e) {
		KeyNamePair processKeyNamePair =  (KeyNamePair) fieldProcess.getSelectedItem().getValue();
		if (e.getTarget() == fieldProcess && processKeyNamePair != null && processKeyNamePair.getKey() > 0) {
			payrollProcess = new MHRProcess(Env.getCtx(), processKeyNamePair.getKey(), null);
			payrollProcessId = payrollProcess.getHR_Process_ID();
			if(payrollProcess.getHR_Period_ID() > 0) {
				MHRPeriod period = MHRPeriod.get(Env.getCtx(), payrollProcess.getHR_Period_ID());
				dateStart= period.getStartDate();
				dateEnd  = period.getEndDate();
			} else {
				dateEnd = payrollProcess.getDateAcct();
			}
			fieldEmployee.removeAllItems();
			KeyNamePair[] employeeData = getEmployeeValid(payrollProcess);
			for (KeyNamePair vp : employeeData) {
				fieldEmployee.appendItem(vp.getName(), vp);
			}
		}
		//	
		if (e.getTarget() == fieldEmployee) {
			partnerId = ((KeyNamePair) fieldEmployee.getSelectedItem().getValue()).getKey();
			//	Validate
			if (partnerId > 0) {
				fieldValidFrom.setValue(dateEnd);
				fieldConcept.removeAllItems();
				KeyNamePair[] conceptData = getConcept(getPayrollProcess(), fieldProcess != null);
				for (KeyNamePair vp : conceptData) {
					fieldConcept.appendItem(vp.getName(), vp);
				}
			}
		}

		if (e.getTarget() == fieldConcept) {
			KeyNamePair valueNamePair = (KeyNamePair) fieldConcept.getSelectedItem().getValue();
			if (valueNamePair != null) {
				conceptId = ((KeyNamePair) fieldConcept.getSelectedItem().getValue()).getKey();
			}
			//	
			if (conceptId > 0) {
				MHRConcept concept = MHRConcept.get(Env.getCtx(), conceptId);
				//	Load Data Combo Box
				loadTextMsgLookup(concept.getAD_Reference_ID());
				//	
				String columnType = MRefList.getListName(Env.getCtx(), 
						MHRConcept.COLUMNTYPE_AD_Reference_ID, concept.getColumnType());
				fieldColumnType.setValue(columnType);
				fieldColumnType.setVisible(true);

				movementId = seekMovement((Timestamp) fieldValidFrom.getValue());
				if (movementId > 0) {
					MHRMovement movementFound = new MHRMovement(Env.getCtx(), movementId, null);
					fieldDescription.setValue(movementFound.getDescription());
					fieldText.setValue("");
					fieldDate.setValue(null);
					fieldQty.setValue(Env.ZERO);
					fieldAmount.setValue(Env.ZERO);
					if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Quantity)) { // Quantity
						fieldQty.setValue(movementFound.getQty());
					} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Amount)) { // Amount
						fieldAmount.setValue(movementFound.getAmount());
					} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Text)) { // Text
						//	Verify Reference
						if(isLookupTextMsg) {
							fieldTextLookup.setValue(movementFound.getTextMsg());
						} else {
							fieldText.setValue(movementFound.getTextMsg());
						}
					} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Date)) { // Date
						fieldDate.setValue(movementFound.getServiceDate());
					}
				}

				if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Quantity)) { // Concept Type
					fieldQty.setVisible(true);
					fieldQty.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldTextLookup.setVisible(false);
				} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Amount)) {
					fieldQty.setVisible(false);
					fieldAmount.setVisible(true);
					fieldAmount.setReadWrite(true);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldTextLookup.setVisible(false);
				} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Date)) {
					fieldQty.setVisible(false);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(true);
					fieldDate.setReadWrite(true);
					fieldText.setVisible(false);
					fieldTextLookup.setVisible(false);
				} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Text)) {
					//	Verify Reference
					if(isLookupTextMsg) {
						fieldText.setVisible(false);
						fieldText.setReadWrite(false);
						fieldTextLookup.setVisible(true);
					} else {
						fieldTextLookup.setVisible(false);
						fieldText.setVisible(true);
						fieldText.setReadWrite(true);
					}
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
				}
			}
		}
		if (e.getTarget() == bOk) {
			if(fieldConcept.getSelectedItem() != null)
				conceptId = ((KeyNamePair) fieldConcept.getSelectedItem().getValue()).getKey();
				partnerId = ((KeyNamePair) fieldEmployee.getSelectedItem().getValue()).getKey();
				payrollId = getPayrollProcess().getHR_Payroll_ID();
				if(payrollProcess.getHR_Period_ID() > 0) {
					MHRPeriod period = MHRPeriod.get(Env.getCtx(), payrollProcess.getHR_Period_ID());
					dateStart = period.getStartDate();
					dateEnd = period.getEndDate();
				} else {
					dateEnd = payrollProcess.getDateAcct();
				}
				quantity = (BigDecimal) fieldQty.getValue();
				amount = (BigDecimal) fieldAmount.getValue();
				//	Get from List
				if(isLookupTextMsg) {
					text = (String) ((ValueNamePair)fieldTextLookup.getSelectedItem().getValue()).getValue();
				} else {
					text = (String) fieldText.getValue();
				}
				serviceDate = (Timestamp) fieldDate.getValue();
				description = (String) fieldDescription.getValue();
				validFrom = (Timestamp) fieldValidFrom.getValue();
				validTo =  (Timestamp) fieldValidFrom.getValue();

			if (conceptId <= 0
					|| fieldProcess.getSelectedItem().getValue() == null
					|| ((KeyNamePair) fieldProcess.getSelectedItem().getValue()).getKey() <= 0
					|| fieldEmployee.getSelectedItem().getValue() == null
					|| ((KeyNamePair) fieldEmployee.getSelectedItem().getValue()).getKey() <= 0) { // required fields
			} else {
				saveMovement();
			}
		}
		//	
		executeQuery();

		return;
	} // actionPerformed

	private void executeQuery() {
		executeQuery(Env.getCtx(), miniTable, 0);
		//	
		miniTable.repaint();
		miniTable.autoSize();
	}

	/**************************************************************************
	 * Action Listener
	 * 
	 * @param e
	 *            event
	 */
	public void actionPerformed(ActionEvent e) {
		log.fine("Event " + e);
		log.fine("Event Source " + e.getSource());
		return;
	} // actionPerformed

	/**
	 * Vetoable Change Listener. - Business Partner - Currency - Date
	 * 
	 * @param e
	 *            event
	 */
	@Override
	public void valueChange(ValueChangeEvent e) {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		if (value == null)
			return;
	} // vetoableChange

	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * 
	 * @return
	 */
	@Override
	public ADForm getForm() {
		return form;
	}

	@Override
	public void tableChanged(WTableModelEvent event) {
		
	}
} // VAllocation
