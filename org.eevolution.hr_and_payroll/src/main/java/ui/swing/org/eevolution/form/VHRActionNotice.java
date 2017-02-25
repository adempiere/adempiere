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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.MiniTable;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRPeriod;
import org.eevolution.model.MHRProcess;
import org.eevolution.service.HRActionNotice;

/**
 *  @author Oscar Gomez
 * 			<li>BF [ 2936481 ] no show employee into action notice
 * 			<li>https://sourceforge.net/tracker/?func=detail&aid=2936481&group_id=176962&atid=934929
 *  @author Cristina Ghita, www.arhipac.ro
 *  @version $Id: VHRActionNotice.java
 *  
 *  Contributor: Carlos Ruiz (globalqss) 
 *    [ adempiere-Libero-2840048 ] Apply ABP to VHRActionNotice  
 *    [ adempiere-Libero-2840056 ] Payroll Action Notice - concept list wrong
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/779">
 * 		@see FR [ 779 ] Payroll Action Notice is slow</a>
 */
public class VHRActionNotice extends HRActionNotice implements FormPanel, ActionListener, VetoableChangeListener
{
	private CTextField fieldDescription = new CTextField(22);
	private VDate fieldValidFrom = new VDate();
	private VNumber fieldQty = new VNumber();
	private VNumber fieldAmount = new VNumber();
	private VDate fieldDate = new VDate();
	private CTextField fieldText = new CTextField(22);
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private VComboBox fieldProcess = new VComboBox();
	private VComboBox fieldEmployee = new VComboBox();
	private VComboBox fieldConcept = new VComboBox();
	private VComboBox fieldTextLookup = new VComboBox();

	/**	Window No			*/
	private int           m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 	  m_frame;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VHRActionNotice.class);
	//
	private CLabel labelProcess = new CLabel();
	private CLabel labelEmployee = new CLabel();
	private CLabel labelColumnType = new CLabel();
	private VLookup fieldColumnType = null;
	private CLabel labelConcept = new CLabel();
	private JLabel labelValue = new JLabel();
	private JLabel dataStatus = new JLabel();
	private JScrollPane dataPane = new JScrollPane();
	private VNumber fieldRuleE = new VNumber();
	private MiniTable miniTable = new MiniTable();
	private CPanel commandPanel = new CPanel();
	private FlowLayout commandLayout = new FlowLayout();
	private JButton bOk = ConfirmPanel.createOKButton(true);
	private CLabel labelValidFrom = new CLabel();
	private CLabel labelDescription = new CLabel();
	//
	private GridBagLayout parameterLayout = new GridBagLayout();	

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame) {
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "Y");
		try {
			super.dynInit();
			dynInit();
			jbInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.setSize(1000, 400);
		} catch(Exception ex) {
			log.log(Level.SEVERE, "init", ex);
		}
	}	//	init


	/**
	 * 	Dispose
	 */
	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() {
		CompiereColor.setBackground(mainPanel);
		mainPanel.setLayout(mainLayout);
		///mainPanel.setSize(500, 500);
		mainPanel.setPreferredSize(new Dimension(1000, 400));
		parameterPanel.setLayout(parameterLayout);
		// Process
		labelProcess.setLabelFor(fieldProcess);
		labelProcess.setText(Msg.translate(Env.getCtx(), "HR_Process_ID"));
		// Employee
		labelEmployee.setLabelFor(fieldEmployee);
		labelEmployee.setText(Msg.translate(Env.getCtx(), "HR_Employee_ID"));
		// Concept
		labelConcept.setLabelFor(fieldConcept);
		labelConcept.setText(Msg.translate(Env.getCtx(), "HR_Concept_ID"));
		// ValidFrom
		labelValidFrom.setLabelFor(fieldValidFrom);
		labelValidFrom.setText(Msg.translate(Env.getCtx(), "Date"));
		// Description
		labelDescription.setLabelFor(fieldDescription);
		labelDescription.setText(Msg.translate(Env.getCtx(), "Description"));
		// ColumnType
		labelColumnType.setLabelFor(fieldColumnType);
		labelColumnType.setText(Msg.translate(Env.getCtx(), "ColumnType"));
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		// Process
		parameterPanel.add(labelProcess,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldProcess,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Employee
		parameterPanel.add(labelEmployee,  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldEmployee,   new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// ValidFrom
		parameterPanel.add(labelValidFrom,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldValidFrom,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Concept
		parameterPanel.add(labelConcept,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldConcept,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// ColumnType
		parameterPanel.add(labelColumnType,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldColumnType,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Qty-Amount-Date-Text-RuleEngine
		parameterPanel.add(labelValue,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldQty,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldAmount,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldDate,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//	Text Message
		parameterPanel.add(fieldText,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldTextLookup,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Description
		parameterPanel.add(labelDescription,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldDescription,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Refresh
		parameterPanel.add(bOk, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		// Agree
		mainPanel.add(dataStatus, BorderLayout.SOUTH);
		mainPanel.add(dataPane, BorderLayout.CENTER);
		dataPane.getViewport().add(miniTable, null);
		//
		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);

	}   //  jbInit


	/**
	 *	Fill Picks.
	 *		Column_ID from C_Order
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception
	{
		// Process
		fieldProcess = new VComboBox(getProcess());
		fieldProcess.addActionListener(this);
		fieldProcess.setMandatory(true);
		// Employee
		fieldEmployee.addActionListener(this);
		fieldEmployee.setReadWrite(false);
		fieldEmployee.setMandatory(true);
		// Concept
		fieldConcept.addActionListener(this);
		fieldConcept.setReadWrite(false);
		fieldConcept.setMandatory(true);
		// ValidFrom
		fieldValidFrom.setReadWrite(false);
		fieldValidFrom.setMandatory(true);
		fieldValidFrom.addVetoableChangeListener(this);
		// Description
		fieldDescription.setValue("");
		fieldDescription.setReadWrite(false);
		// ColumnType
		fieldColumnType = new VLookup("ColumnType", true, true, false, getColumnTypeLookup());
		fieldColumnType.setReadWrite(false);
		//	For Text Message like reference
		fieldTextLookup.setReadWrite(true);
		// Qty-Amount-Date-Text-RuleEngine
		fieldQty.setReadWrite(false);
		fieldQty.setDisplayType(DisplayType.Quantity);
		fieldQty.setVisible(true);
		fieldAmount.setDisplayType(DisplayType.Amount);
		fieldAmount.setVisible(false);
		fieldDate.setVisible(false);
		fieldText.setVisible(false);
		fieldTextLookup.setVisible(false);
		fieldRuleE.setVisible(false);
		//
		bOk.addActionListener(this);
		//	Yamel Senih 2013-03-11
		//	Fixed columns increment in minitable
		miniTable = new MiniTable();
		//	End Yamel Senih
		configureMiniTable(miniTable);
	}	//	fillPicks

	/**
	 * Run query
	 */
	private void executeQuery() {
		executeQuery(Env.getCtx(), miniTable, 1);
	}   //  executeQuery


	/**************************************************************************
	 *  vetoableChange
	 *  @param e event
	 */
	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
		fieldConcept.setReadWrite(true);
		log.fine("Event"+ e);
		log.fine("Event Source "+ e.getSource());
		log.fine("Event Property "+ e.getPropertyName());
		Integer   periodId = getPayrollProcess().getHR_Period_ID();
		String date = DB.TO_DATE((Timestamp)fieldValidFrom.getValue());
		int existRange = DB.getSQLValueEx(null,"SELECT HR_Period_ID FROM HR_Period "
						+ "WHERE " + date + " >= StartDate "
						+ "AND " + date + " <= EndDate "
						+ "AND HR_Period_ID = "+periodId);
		// Exist of Range Payroll
		if ( existRange < 0){
			fieldConcept.setReadWrite(false);
			return;
		}
		else {
			fieldConcept.setReadWrite(true);
		}

		if (fieldConcept != null)
			movementId = seekMovement((Timestamp)fieldValidFrom.getValue());  // exist movement record to date actual
	}   //  vetoableChange
	
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
			fieldTextLookup.addItem(vp);
		}
		//	Set Flag
		isLookupTextMsg = true;
	}

	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		log.fine("Event "+ e);
		log.fine("Event Source "+ e.getSource());
		if (e.getSource().equals(fieldProcess)) {					// Process
			KeyNamePair pp = (KeyNamePair)fieldProcess.getSelectedItem();
			if (pp != null){
				payrollProcessId = pp.getKey();
				payrollProcess = new MHRProcess(Env.getCtx(), payrollProcessId, null);
				if(payrollProcess.getHR_Period_ID() > 0) {
					MHRPeriod period = MHRPeriod.get(Env.getCtx(), payrollProcess.getHR_Period_ID());
					dateStart= period.getStartDate();
					dateEnd  = period.getEndDate();
				} else {
					dateEnd = payrollProcess.getDateAcct();
				}
				payrollId = payrollProcess.getHR_Payroll_ID();
				fieldEmployee.removeAllItems();
				for(KeyNamePair ppt : getEmployeeValid(payrollProcess)) {
					fieldEmployee.addItem(ppt);
				}
				fieldEmployee.setSelectedIndex(0);
				fieldEmployee.setReadWrite(true);
			}
		}
		else if (e.getSource().equals(fieldEmployee)){			// Employee
			KeyNamePair keyNamePair = (KeyNamePair)fieldEmployee.getSelectedItem();
			if (keyNamePair != null) {
				partnerId = keyNamePair.getKey();
			}
			if (partnerId > 0){
				fieldValidFrom.setValue(dateEnd);
				fieldValidFrom.setReadWrite(true);
				fieldConcept.removeAllItems();
				for(KeyNamePair vp : getConcept(payrollProcess, fieldProcess != null)) {
					fieldConcept.addItem(vp);
				}
				//	
				fieldConcept.setReadWrite(true);
			}
		}
		else if (e.getSource().equals(fieldConcept)) {			// Concept
			KeyNamePair conceptPair = (KeyNamePair)fieldConcept.getSelectedItem();
			if (conceptPair != null) {
				conceptId = conceptPair.getKey();
			}
			//	
			if (conceptId > 0) {
				MHRConcept concept = MHRConcept.get(Env.getCtx(), conceptId);
				//	Load Data Combo Box
				loadTextMsgLookup(concept.getAD_Reference_ID());
				// Name To Type Column
				fieldColumnType.setValue(concept.getColumnType());
				fieldColumnType.setVisible(true);
				movementId = seekMovement((Timestamp)fieldValidFrom.getValue()); //  exist movement record to date actual

				if (movementId > 0){
					MHRMovement movementFound = new MHRMovement(Env.getCtx(), movementId,null);
					fieldDescription.setValue(movementFound.getDescription());
					fieldText.setValue("");
					fieldDate.setValue(null);
					fieldQty.setValue(Env.ZERO);
					fieldAmount.setValue(Env.ZERO);
					if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity)) {	// Quantity
						fieldQty.setValue(movementFound.getQty());
					} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount)) {	// Amount
						fieldAmount.setValue(movementFound.getAmount());
					} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Text)) {	// Text
						//	Verify Reference
						if(isLookupTextMsg) {
							fieldTextLookup.setValue(movementFound.getTextMsg());
						} else {
							fieldText.setValue(movementFound.getTextMsg());
						}
					} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Date)) {	// Date
						fieldDate.setValue(movementFound.getServiceDate());
					}
				} else {
					fieldQty.setValue(null);
					fieldAmount.setValue(null);
					fieldDescription.setValue(null);
					fieldText.setValue(null);
					fieldTextLookup.setValue(null);
					fieldDate.setValue(null);
				}
				//	
				if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity)){				// Concept Type
					fieldQty.setVisible(true);
					fieldQty.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldTextLookup.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount)){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(true);
					fieldAmount.setReadWrite(true);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldTextLookup.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Date)){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(true);
					fieldDate.setReadWrite(true);
					fieldText.setVisible(false);
					fieldTextLookup.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Text)){
					//	Verify Reference
					if(isLookupTextMsg) {
						fieldText.setVisible(false);
						fieldText.setReadWrite(false);
						fieldTextLookup.setVisible(true);
						fieldTextLookup.setReadWrite(true);
					} else {
						fieldTextLookup.setVisible(false);
						fieldTextLookup.setReadWrite(false);
						fieldText.setVisible(true);
						fieldText.setReadWrite(true);
					}
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldRuleE.setVisible(false);
				}
				fieldDescription.setReadWrite(true);
			}
		} // Concept
		else if (e instanceof ActionEvent && e.getSource().equals(bOk) ){					 // Movement SAVE
			conceptId = ((KeyNamePair) fieldConcept.getSelectedItem()).getKey();
			partnerId = ((KeyNamePair) fieldEmployee.getSelectedItem()).getKey();
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
				text = (String) fieldTextLookup.getValue();
			} else {
				text = (String) fieldText.getValue();
			}
			serviceDate = (Timestamp) fieldDate.getValue();
			description = (String) fieldDescription.getValue();
			validFrom = (Timestamp) fieldValidFrom.getValue();
			validTo =  (Timestamp) fieldValidFrom.getValue();
			if ( conceptId <= 0
				|| fieldProcess.getValue() == null
				|| ((Integer)fieldProcess.getValue()).intValue() <= 0
				|| fieldEmployee.getValue() == null
				|| ((Integer)fieldEmployee.getValue()).intValue() <= 0) {  // required fields
				ADialog.error(m_WindowNo, this.mainPanel, Msg.translate(Env.getCtx(), "FillMandatory")
						+ Msg.translate(Env.getCtx(), "HR_Process_ID") + ", "
						+ Msg.translate(Env.getCtx(), "HR_Employee_ID") + ", "
						+ Msg.translate(Env.getCtx(), "HR_Concept_ID"));
			} else {
				saveMovement();
			}
		}
		executeQuery();
		return;
	}   //  actionPerformed
}   //  VHRActionNotice
