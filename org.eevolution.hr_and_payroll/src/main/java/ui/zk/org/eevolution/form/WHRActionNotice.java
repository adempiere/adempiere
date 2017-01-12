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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.process.ProcessInfo;
import org.compiere.util.DB;
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
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Space;

/**
 * HRActionNotice Form
 * 
 * @author oscar.gomez@e-evolution.com
 */
public class WHRActionNotice extends HRActionNotice implements IFormController,
		EventListener, WTableModelListener, ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7806119329546820204L;

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
	private Listbox fieldProcess = ListboxFactory.newDropdownListbox();
	// BPartner
	private Label labelBPartner = new Label();
	private Listbox fieldEmployee = ListboxFactory.newDropdownListbox();
	// Date
	private Label labelValidFrom = new Label();
	public WDateEditor fieldValidFrom = new WDateEditor();
	// Concept
	private Label labelConcept = new Label();
	private Listbox fieldConcept = ListboxFactory.newDropdownListbox();
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

	// private Grid parameterLayout = GridFactory.newGridLayout();
	private WListbox miniTable = ListboxFactory.newDataTable();

	@SuppressWarnings("unused")
	private ProcessInfo m_pi;

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
		fieldProcess.addActionListener(this);
		// BPartner
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fieldEmployee.addActionListener(this);
		// Date
		labelValidFrom.setText(Msg.translate(Env.getCtx(), "Date"));
		fieldValidFrom.addValueChangeListener(this);
		// Concept
		labelConcept.setText(Msg.translate(Env.getCtx(), "HR_Concept_ID"));
		fieldConcept.addActionListener(this);
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

		bOk.setLabel(Msg.getMsg(Env.getCtx(), "Process"));
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
		row.appendChild(labelColumnType.rightAlign());
		row.appendChild(fieldColumnType.getComponent());
		row.appendChild(fieldDate.getComponent());
		row.appendChild(fieldQty.getComponent());
		row.appendChild(fieldAmount.getComponent());
		row.appendChild(fieldText.getComponent());

		row = rows.newRow();
		row.appendChild(labelDescription.rightAlign());
		row.appendChild(fieldDescription.getComponent());
		row.appendChild(new Space());
		row.appendChild(bOk);

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

	private void configureMiniTable() {

		ColumnInfo[] layout = {
				// new ColumnInfo(" ", " ", IDColumn.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "AD_Org_ID"), "",
						String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "HR_Concept_ID"),
						"", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "ValidFrom"), "",
						Timestamp.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "ColumnType"), "",
						String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"), "",
						BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Amount"), "",
						BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "ServiceDate"), "",
						Timestamp.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Text"), "",
						String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "",
						String.class) };

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
			if (e.getTarget() == fieldProcess) {
				fieldEmployee.removeAllItems();
				List<KeyNamePair> employeeData = getEmployeeValid(payrollProcess);
				for (KeyNamePair vp : employeeData)
					fieldEmployee.appendItem(vp.getName(), vp);
			}
		}

		if (e.getTarget() == fieldEmployee) {
			partnerId = ((KeyNamePair) fieldEmployee.getSelectedItem().getValue()).getKey();
			fieldConcept.removeAllItems();
			ArrayList<ValueNamePair> conceptData = getConcept(getPayrollProcess(), fieldProcess != null);
			for (ValueNamePair vp : conceptData)
				fieldConcept.appendItem(vp.getName(), vp);
		}

		if (e.getTarget() == fieldConcept) {
			ValueNamePair valueNamePair = (ValueNamePair) fieldConcept.getSelectedItem().getValue();
			if (valueNamePair != null)
				conceptId = Integer.parseInt(((ValueNamePair) fieldConcept.getSelectedItem().getValue()).getValue());

			if (conceptId > 0) {

				MHRConcept concept = MHRConcept.get(Env.getCtx(), conceptId);
				fieldColumnType.setValue(DB.getSQLValueStringEx(null, getSQL_ColumnType(Env.getCtx(), "?"), concept.getColumnType()));
				fieldColumnType.setVisible(true);

				movementId = seekMovement((Timestamp) fieldValidFrom.getValue());
				if (movementId > 0) {
					MHRMovement movementFound = new MHRMovement(Env.getCtx(), movementId, null);
					fieldDescription.setValue(movementFound.getDescription());
					fieldText.setValue("");
					fieldDate.setValue(null);
					fieldQty.setValue(Env.ZERO);
					fieldAmount.setValue(Env.ZERO);
					if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Quantity)) // Quantity
						fieldQty.setValue(movementFound.getQty());
					else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Amount)) // Amount
						fieldAmount.setValue(movementFound.getAmount());
					else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Text)) // Text
						fieldText.setValue(movementFound.getTextMsg());
					else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Date)) // Date
						fieldDate.setValue(movementFound.getServiceDate());
				}

				if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Quantity)) { // Concept Type
					fieldQty.setVisible(true);
					fieldQty.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
				} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Amount)) {
					fieldQty.setVisible(false);
					fieldAmount.setVisible(true);
					fieldAmount.setReadWrite(true);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
				} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Date)) {
					fieldQty.setVisible(false);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(true);
					fieldDate.setReadWrite(true);
					fieldText.setVisible(false);
				} else if (concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Text)) {
					fieldText.setVisible(true);
					fieldText.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
				}
			}
		}
		if (e.getTarget() == bOk) {
			if(fieldConcept.getSelectedItem() != null)
				conceptId = Integer.parseInt(((ValueNamePair) fieldConcept.getSelectedItem().getValue()).getValue());
				partnerId = ((KeyNamePair) fieldEmployee.getSelectedItem().getValue()).getKey();
				payrollId = getPayrollProcess().getHR_Payroll_ID();
				if(payrollProcess.getHR_Period_ID() > 0) {
					MHRPeriod period = MHRPeriod.get(Env.getCtx(), payrollProcess.getHR_Period_ID());
					dateStart = period.getStartDate();
					dateEnd = period.getEndDate();
				}
				else
					dateEnd = payrollProcess.getDateAcct();
				quantity = (BigDecimal) fieldQty.getValue();
				amount = (BigDecimal) fieldAmount.getValue();
				text = (String) fieldText.getValue();
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
				executeQuery();
				//fieldValidFrom.setValue(dateEnd);
				fieldColumnType.setValue("");
				fieldQty.setValue(Env.ZERO);
				fieldAmount.setValue(Env.ZERO);
				//fieldQty.setReadWrite(false);
				//fieldAmount.setReadWrite(false);
				//fieldText.setReadWrite(false);
				//fieldDescription.setReadWrite(true);
				HRActionNotice.movementId = 0; // Initial not exist record in Movement to actual
				// date
				// clear fields
				fieldDescription.setValue("");
				fieldText.setValue("");
				fieldDate.setValue(null);
				fieldQty.setValue(Env.ZERO);
				fieldAmount.setValue(Env.ZERO);
				fieldConcept.setSelectedIndex(0);
			}
		}

		executeQuery();

		return;
	} // actionPerformed

	private void executeQuery() {
		StringBuffer sqlQuery = new StringBuffer(
				"SELECT DISTINCT o.Name,hp.Name," // AD_Org_ID, HR_Process_ID --
													// 1,2
						+ " bp.Name,hc.Name,hm.ValidFrom," // HR_Employee_ID,HR_Concept_ID,ValidFrom,ColumnType
															// -- 3,4,5
						+ "("
						+ getSQL_ColumnType(Env.getCtx(), "hc.ColumnType")
						+ ") AS ColumnType," // 6 ColumnType(Reference Name)
						+ " hm.Qty,hm.Amount,hm.ServiceDate,hm.TextMsg,er.Name,hm.Description " // Qty,Amount,ServiceDate,Text,AD_Rule_ID,Description
																								// --
																								// 7,8,9,10,11,12
						+ " , HR_Movement_id, hm.AD_Org_ID,hp.HR_Process_ID,hm.HR_Concept_ID " // to
																								// make
																								// the
																								// distinct
																								// useful
						+ " FROM HR_Movement hm "
						+ " INNER JOIN AD_Org o ON (hm.AD_Org_ID=o.AD_Org_ID)"
						+ " INNER JOIN HR_Process hp ON (hm.HR_Process_ID=hp.HR_Process_ID)"
						+ " INNER JOIN C_BPartner bp ON (hm.C_BPartner_ID=bp.C_BPartner_ID)"
						+ " INNER JOIN HR_Employee e ON (e.C_BPartner_ID=bp.C_BPartner_ID)"
						+ " INNER JOIN HR_Concept hc ON (hm.HR_Concept_ID=hc.HR_Concept_ID)"
						+ " LEFT OUTER JOIN AD_Rule er ON (hm.AD_Rule_ID=er.AD_Rule_ID)"
						+ " WHERE hm.Processed='N' AND hp.HR_Process_ID = "
						+ payrollProcessId
						// + " AND IsStatic = 'Y' " // Just apply incidences
						// [add 30Dic2006 to see everything]
						+ " AND hm.C_BPartner_ID = " + partnerId
		// +
		// " AND (Qty > 0 OR Amount > 0 OR hm.TextMsg IS NOT NULL OR ServiceDate IS NOT NULL) "
		);
		// if (fieldValidFrom.getValue() != null ) {
		// sqlQuery.append(" AND "
		// +DB.TO_DATE(m_dateStart)+" >= hm.ValidFrom  AND "+DB.TO_DATE(m_dateEnd)+" <=  hm.ValidTo ");
		// }
		sqlQuery.append(" ORDER BY hm.AD_Org_ID,hp.HR_Process_ID,bp.Name,hm.ValidFrom,hm.HR_Concept_ID");
		// reset table
		int row = 0;
		miniTable.setRowCount(row);
		// Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sqlQuery.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// extend table
				miniTable.setRowCount(row + 1);
				// set values
				// miniTable.setColumnClass(0, IDColumn.class, false, " ");
				miniTable.setValueAt(rs.getString(1), row, 0); // AD_Org_ID
				miniTable.setValueAt(rs.getString(4), row, 1); // HR_Concept_ID
				miniTable.setValueAt(rs.getTimestamp(5), row, 2); // ValidFrom
				miniTable.setValueAt(rs.getString(6), row, 3); // ColumnType
				miniTable.setValueAt(
						rs.getObject(7) != null ? rs.getBigDecimal(7)
								: Env.ZERO, row, 4); // Qty
				miniTable.setValueAt(
						rs.getObject(8) != null ? rs.getBigDecimal(8)
								: Env.ZERO, row, 5); // Amount
				miniTable.setValueAt(rs.getTimestamp(9), row, 6); // ServiceDate
				miniTable.setValueAt(rs.getString(10), row, 7); // TextMsg
				// miniTable.setValueAt(rs.getString(11), row, 9); // AD_Rule_ID
				miniTable.setValueAt(rs.getString(12), row, 8); // Description
				// prepare next
				row++;

			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlQuery.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
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
		// TODO Auto-generated method stub

	}
} // VAllocation
