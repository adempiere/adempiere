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

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.logging.*;

import javax.swing.*;


import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.*;
import org.compiere.util.*;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VDate;


import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.eevolution.model.*;
/**
 *  Create Manual Payments From (AP) Invoices or (AR) Credit Memos.
 *  Allows user to select Invoices for payment.
 *  When Processed, PaySelection is created
 *  and optionally posted/generated and printed
 *
 *  @author Oscar Gomez
 *  @version $Id: VHRIncidence.java
 */
public class VHRActionNotice extends CPanel
	implements FormPanel,VetoableChangeListener, ActionListener
{
	/** @todo withholding */
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try {
			jbInit();
			dynInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.setSize(1000, 400);
		}
		catch(Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**	Window No			*/
	private int           m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 	  m_frame;
	/** Format                  */
	private DecimalFormat aformat = DisplayType.getNumberFormat(DisplayType.Amount);
	private DecimalFormat qformat = DisplayType.getNumberFormat(DisplayType.Quantity);
	//Language language = Language.getLoginLanguage();//	Base Language
	/** Client ID               */
	private int	m_AD_Client_ID = 0;
	private int	HR_Process_ID = 0;
	private int	C_BPartner_ID  = 0;
	private int	HR_Concept_ID = 0;
	private int	HR_Period_ID  = 0;
	private int HR_Payroll_ID = 0;
	private int sHR_Movement_ID = 0; // // initial not exist record in Movement to actual date
	private Timestamp dateStart = null;
	private Timestamp dateEnd   = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VHRActionNotice.class);
	//
	private CPanel           mainPanel = new CPanel();
	private BorderLayout    mainLayout = new BorderLayout();
	private CPanel      parameterPanel = new CPanel();
	private CLabel        labelProcess = new CLabel();
	private VComboBox     fieldProcess = new VComboBox();
	private CLabel       labelEmployee = new CLabel();
	private VComboBox    fieldEmployee = new VComboBox();
	private CLabel     labelColumnType = new CLabel();
	private CTextField fieldColumnType = new CTextField(18);
	private CLabel        labelConcept = new CLabel();
	private VComboBox     fieldConcept = new VComboBox();
	private JLabel          labelValue = new JLabel();
	private VNumber           fieldQty = new VNumber();
	private VNumber        fieldAmount = new VNumber();
	private VDate            fieldDate = new VDate();
	private CTextField       fieldText = new CTextField(22);
	private VNumber         fieldRuleE = new VNumber();
	private JLabel          dataStatus = new JLabel();
	private JScrollPane       dataPane = new JScrollPane();
	private MiniTable        miniTable = new MiniTable();
	private CPanel        commandPanel = new CPanel();
	private JButton            bCancel = ConfirmPanel.createCancelButton(true);
	private JButton          bGenerate = ConfirmPanel.createProcessButton(true);
	private FlowLayout   commandLayout = new FlowLayout();
	private JButton                bOk = ConfirmPanel.createOKButton(true);
	private CLabel      labelValidFrom = new CLabel();
	private VDate       fieldValidFrom = new VDate();
	private CLabel       labelDescription = new CLabel();
	private CTextField   fieldDescription = new CTextField(22);
	private GridBagLayout parameterLayout = new GridBagLayout();	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		mainPanel.setLayout(mainLayout);
		///mainPanel.setSize(500, 500);
		mainPanel.setPreferredSize(new Dimension(1000, 400));
		parameterPanel.setLayout(parameterLayout);
		// Process
		labelProcess.setText(Msg.translate(Env.getCtx(), "Proceso"));
        getProcess();
        fieldProcess.setMandatory(true);
        fieldProcess.addActionListener(this);
		// Employee
		labelEmployee.setText(Msg.translate(Env.getCtx(), "Empleado"));
        fieldEmployee.setReadWrite(false);
        fieldEmployee.setMandatory(true);
        fieldEmployee.addActionListener(this);
        fieldEmployee.addVetoableChangeListener(this);
        // Concept
        labelConcept.setText(Msg.translate(Env.getCtx(), "Concepto"));
        getConceptValid();
        fieldConcept.setReadWrite(false);
        fieldConcept.setMandatory(true);        
		fieldConcept.addActionListener(this);
		// ValidFrom
		labelValidFrom.setText(Msg.translate(Env.getCtx(), "Date"));
		fieldValidFrom.setReadWrite(false);
		fieldValidFrom.setMandatory(true);
		fieldValidFrom.addVetoableChangeListener(this);
		// Description
		labelDescription.setText(Msg.translate(Env.getCtx(), "Description"));
		fieldDescription.setValue("");
		fieldDescription.setReadWrite(false);
		// ColumnType
		labelColumnType.setText(Msg.getMsg(Env.getCtx(), "ColumnType"));
		fieldColumnType.setReadWrite(false);		
		// Qty-Amount-Date-Text-RuleEngine
		fieldQty.setReadWrite(false);
		fieldQty.setDisplayType(DisplayType.Quantity);
		fieldQty.setVisible(true);
		fieldAmount.setDisplayType(DisplayType.Amount);
		fieldAmount.setVisible(false);
		fieldDate.setVisible(false);
		fieldText.setVisible(false);
		fieldRuleE.setVisible(false);
		//
		bOk.addActionListener(this);
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		// Process
		parameterPanel.add(labelProcess,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldProcess,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Employee
		parameterPanel.add(labelEmployee,  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldEmployee,   new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// ValidFrom
		parameterPanel.add(labelValidFrom,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldValidFrom,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Concepto
		parameterPanel.add(labelConcept,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldConcept,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		// ColumnType
		parameterPanel.add(labelColumnType,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldColumnType,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		// Qty-Amount-Date-Text-RuleEngine
		parameterPanel.add(labelValue,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldQty,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldAmount,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldDate,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldText,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		// Description
		parameterPanel.add(labelDescription,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldDescription,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
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
	 *  Dynamic Init.
	 *  - Load Bank Info
	 *  - Load BPartner
	 *  - Init Table
	 */
	private void dynInit()
	{
		miniTable.addColumn("HR_Movement_ID"); 	// 0
		miniTable.addColumn("AD_Org_ID");			// 1
		miniTable.addColumn("HR_Concept_ID");		// 2
		miniTable.addColumn("ValidFrom");			// 3
		miniTable.addColumn("ColumnType");			// 4
		miniTable.addColumn("Qty");					// 5
		miniTable.addColumn("Amount");				// 6
		miniTable.addColumn("ServiceDate");			// 7
		miniTable.addColumn("Text");				// 8
		miniTable.addColumn("AD_Rule_Engine_ID");		// 9
		miniTable.addColumn("Description");			// 10
		//  set details
		miniTable.setColumnClass(0, IDColumn.class, false, " ");
		miniTable.setColumnClass(1, String.class, true, "Org"); //Msg.translate(Env.getCtx(), "AD_Org_ID"));
		miniTable.setColumnClass(2, String.class, true, "Concepto"); //Msg.translate(Env.getCtx(), "HR_Concept_ID"));
		miniTable.setColumnClass(3, Timestamp.class, true, "Movimiento"); //Msg.translate(Env.getCtx(), "ValidFrom"));
		miniTable.setColumnClass(4, String.class, true, "Tipo de Concepto"); //Msg.translate(Env.getCtx(), "ColumnType"));
		miniTable.setColumnClass(5, BigDecimal.class, true, "Cantidad"); //Msg.translate(Env.getCtx(), "Qty"));
		miniTable.setColumnClass(6, BigDecimal.class, true, "Importe"); //Msg.translate(Env.getCtx(), "Amount"));
		miniTable.setColumnClass(7, Timestamp.class, true, "Fecha"); //Msg.translate(Env.getCtx(), "ServiceDate"));
		miniTable.setColumnClass(8, String.class, true, "Texto"); //Msg.translate(Env.getCtx(), "Text"));		
		miniTable.setColumnClass(9, String.class, true, "Regla"); //Msg.translate(Env.getCtx(), "E_RuleEngine_ID"));
		miniTable.setColumnClass(10, String.class, true, "Descripcion"); //Msg.translate(Env.getCtx(), "Description"));
		//
		miniTable.autoSize();
	}   //  dynInit
	
	
	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	
	/**************************************************************************
	 *  vetoableChange
	 *  @param e event
	 */
	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException 
	{
		fieldConcept.setReadWrite(true);
		System.out.println("Evento"+ e);
		System.out.println("Evento Fuente "+ e.getSource());
		System.out.println("Evento Propiedad"+ e.getPropertyName());
		//if (e.getPropertyName().equals("AD_Org_ID"))
		if ( e.getSource().equals(fieldProcess) ) {					// Process
			KeyNamePair pp = (KeyNamePair)fieldProcess.getSelectedItem();
			if (pp != null){
				HR_Process_ID = pp.getKey();
				MHRProcess process = new MHRProcess(Env.getCtx(),HR_Process_ID,null);
				dateStart= new X_HR_Period(Env.getCtx(),process.getHR_Period_ID(),null).getStartDate();				
				dateEnd  = new X_HR_Period(Env.getCtx(),process.getHR_Period_ID(),null).getEndDate();
				HR_Period_ID = process.getHR_Period_ID();
				HR_Payroll_ID = process.getHR_Payroll_ID();
				getEmployeeValid(new MHRProcess(Env.getCtx(),HR_Process_ID,null));
				fieldEmployee.setReadWrite(true);
			}
		}
		else if ( e.getSource().equals(fieldEmployee) ){			// Employee
			KeyNamePair pp = (KeyNamePair)fieldEmployee.getSelectedItem();
			int C_BPartner_ID = 0;
			if ( pp != null )
				C_BPartner_ID = pp.getKey();
			if ( C_BPartner_ID > 0){			
				fieldValidFrom.setValue(dateEnd);
				fieldValidFrom.setReadWrite(true);
				getConceptValid();
				fieldConcept.setReadWrite(true);
				executeQuery();
			}
		}
		else if ( e.getSource().equals(fieldConcept) ) {			// Concept
			KeyNamePair pp = (KeyNamePair)fieldConcept.getSelectedItem();
			int HR_Concept_ID = 0;
			String sqlColumnType = "";
			if (pp != null)
				HR_Concept_ID = pp.getKey();
			if ( HR_Concept_ID > 0 ){
				sqlColumnType = "SELECT rl.Name FROM AD_Ref_List rl WHERE rl.AD_Reference_ID=" // ColumnType Name of ReferenceList 
					+ " (SELECT col.AD_Reference_Value_ID FROM AD_Column col WHERE col.Name ='Column Type' " 
			        + " AND col.AD_Table_ID=(SELECT tab.AD_Table_ID FROM AD_Table tab WHERE tab.TableName='HR_Concept')) " 
			        + " AND Value = (SELECT ColumnType FROM HR_Concept WHERE HR_Concept_ID = ? )";
				// Name To Type Column
				fieldColumnType.setValue(DB.getSQLValueString("HR_Concept", sqlColumnType, HR_Concept_ID )); 
				MHRConcept concept = new MHRConcept (Env.getCtx(),HR_Concept_ID,null);
				sHR_Movement_ID = seekMovement(); //  exist movement record to date actual				
				//
				if (concept.getColumnType().equals("Q")){				// Concept Type
					fieldQty.setVisible(true);
					fieldQty.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);				
				} else if (concept.getColumnType().equals("A")){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(true);
					fieldAmount.setReadWrite(true);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals("D")){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(true);
					fieldDate.setReadWrite(true);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals("T")){
					fieldText.setVisible(true);
					fieldText.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldRuleE.setVisible(false);
				}
				fieldDescription.setReadWrite(true);
			}
		} // Concept
		Integer   HR_Period_ID = new MHRProcess(Env.getCtx(),(Integer)fieldProcess.getValue(),null).getHR_Period_ID(); 
		String date = DB.TO_DATE((Timestamp)fieldValidFrom.getValue());
		int existRange = DB.getSQLValue("HR_Period","SELECT * FROM HR_Period WHERE " +date+
						 " >= StartDate AND "+date+	" <= EndDate AND HR_Period_ID = "+HR_Period_ID);
		// Exist of Range Payroll
		if ( existRange < 0){
			fieldConcept.setReadWrite(false);
			return;
		}
		if (fieldConcept != null)
			sHR_Movement_ID = seekMovement();  // exist movement record to date actual
	}   //  actionPerformed

	
	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Evento"+ e);
		System.out.println("Evento Fuente "+ e.getSource());
		
		
		if ( e.getSource().equals(fieldProcess) ) {					// Process
			KeyNamePair pp = (KeyNamePair)fieldProcess.getSelectedItem();
			if (pp != null){
				HR_Process_ID = pp.getKey();
				MHRProcess process = new MHRProcess(Env.getCtx(),HR_Process_ID,null);
				dateStart= new X_HR_Period(Env.getCtx(),process.getHR_Period_ID(),null).getStartDate();				
				dateEnd  = new X_HR_Period(Env.getCtx(),process.getHR_Period_ID(),null).getEndDate();
				HR_Period_ID = process.getHR_Period_ID();
				HR_Payroll_ID = process.getHR_Payroll_ID();
				getEmployeeValid(new MHRProcess(Env.getCtx(),HR_Process_ID,null));
				fieldEmployee.setReadWrite(true);
			}
		}
		else if ( e.getSource().equals(fieldEmployee) ){			// Employee
			KeyNamePair pp = (KeyNamePair)fieldEmployee.getSelectedItem();
			int C_BPartner_ID = 0;
			if ( pp != null )
				C_BPartner_ID = pp.getKey();
			if ( C_BPartner_ID > 0){			
				fieldValidFrom.setValue(dateEnd);
				fieldValidFrom.setReadWrite(true);
				getConceptValid();
				fieldConcept.setReadWrite(true);
				executeQuery();
			}
		}
		else if ( e.getSource().equals(fieldConcept) ) {			// Concept
			KeyNamePair pp = (KeyNamePair)fieldConcept.getSelectedItem();
			int HR_Concept_ID = 0;
			String sqlColumnType = "";
			if (pp != null)
				HR_Concept_ID = pp.getKey();
			if ( HR_Concept_ID > 0 ){
				sqlColumnType = "SELECT rl.Name FROM AD_Ref_List rl WHERE rl.AD_Reference_ID=" // ColumnType Name of ReferenceList 
					+ " (SELECT col.AD_Reference_Value_ID FROM AD_Column col WHERE col.Name ='Column Type' " 
			        + " AND col.AD_Table_ID=(SELECT tab.AD_Table_ID FROM AD_Table tab WHERE tab.TableName='HR_Concept')) " 
			        + " AND Value = (SELECT ColumnType FROM HR_Concept WHERE HR_Concept_ID = ? )";
				// Name To Type Column
				fieldColumnType.setValue(DB.getSQLValueString("HR_Concept", sqlColumnType, HR_Concept_ID )); 
				MHRConcept concept = new MHRConcept (Env.getCtx(),HR_Concept_ID,null);
				sHR_Movement_ID = seekMovement(); //  exist movement record to date actual				
				//
				if (concept.getColumnType().equals("Q")){				// Concept Type
					fieldQty.setVisible(true);
					fieldQty.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);				
				} else if (concept.getColumnType().equals("A")){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(true);
					fieldAmount.setReadWrite(true);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals("D")){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(true);
					fieldDate.setReadWrite(true);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals("T")){
					fieldText.setVisible(true);
					fieldText.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldRuleE.setVisible(false);
				}
				fieldDescription.setReadWrite(true);
			}
		} // Concept
		else if (e.getSource().equals(bOk) ){					 // Movement SAVE
			KeyNamePair pp = (KeyNamePair)fieldConcept.getSelectedItem();
			int HR_Concept_ID = pp.getKey();
			MHRConcept conceptOK   = new MHRConcept(Env.getCtx(),HR_Concept_ID,null);
			int mov = sHR_Movement_ID > 0 ? sHR_Movement_ID : 0;
			MHRMovement movementOK = new MHRMovement(Env.getCtx(),mov,null);
			movementOK.setDescription((String)fieldDescription.getValue().toString());
			movementOK.setHR_Process_ID((Integer)fieldProcess.getValue());
			movementOK.setC_BPartner_ID((Integer)fieldEmployee.getValue());
			movementOK.setHR_Concept_ID((Integer)fieldConcept.getValue());
			movementOK.setColumnType(conceptOK.getColumnType());
			movementOK.setQty(fieldQty.getValue() != null ? (BigDecimal)fieldQty.getValue() : Env.ZERO);
			BigDecimal data =  (BigDecimal)fieldQty.getValue() ;
			movementOK.setAmount(fieldAmount.getValue() != null ? (BigDecimal)fieldAmount.getValue() : Env.ZERO );
			movementOK.setTextMsg(fieldText.getValue() != null ? "" : (String)fieldText.getValue().toString());
			movementOK.setValidFrom((Timestamp)fieldValidFrom.getTimestamp());
			movementOK.setValidTo((Timestamp)fieldValidFrom.getTimestamp());
			movementOK.save();
			executeQuery();			
			fieldValidFrom.setValue(dateEnd);
			fieldColumnType.setValue("");
			fieldQty.setValue(Env.ZERO);
			fieldAmount.setValue(Env.ZERO);
			fieldQty.setReadWrite(false);
			fieldAmount.setReadWrite(false);
			fieldText.setReadWrite(false);
			fieldDescription.setValue("");
			fieldDescription.setReadWrite(false);
			sHR_Movement_ID = 0; // Initial not exist record in Movement to actual date
		}
	}
	
	
	/**
	 *  Query Info
	 */
	private void executeQuery()
	{	
		StringBuffer sqlQuery = new StringBuffer("SELECT o.Name,hp.Name,"   // AD_Org_ID, HR_Process_ID -- 1,2
				+ " bp.Name,hc.Name,hm.ValidFrom,"		// HR_Employee_ID,HR_Concept_ID,ValidFrom,ColumnType -- 3,4,5
				+ " (SELECT rl.Name FROM AD_Ref_List rl WHERE rl.AD_Reference_ID="
				+ " (SELECT col.AD_Reference_Value_ID FROM AD_Column col WHERE col.Name ='Column Type'" 
				+ " AND col.AD_Table_ID = (SELECT tab.AD_Table_ID  FROM AD_Table tab "
				+ " WHERE tab.TableName = 'HR_Concept')) AND rl.Value=hc.ColumnType) As ColumnType,"	// 6 ColumnType(Reference Name)
				+ " hm.Qty,hm.Amount,hm.ServiceDate,hm.Text,er.Name,hm.Description "	// Qty,Amount,ServiceDate,Text,AD_Rule_Engine_ID,Description -- 7,8,9,10,11,12
				+ " FROM HR_Movement hm "
				+ " INNER JOIN AD_Org o ON (hm.AD_Org_ID=o.AD_Org_ID)"
				+ " INNER JOIN HR_Process hp ON (hm.HR_Process_ID=hp.HR_Process_ID)"
				+ " INNER JOIN C_BPartner bp ON (hm.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN HR_Employee e ON (e.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN HR_Concept hc ON (hm.HR_Concept_ID=hc.HR_Concept_ID)"
				+ " LEFT OUTER JOIN AD_Rule_Engine er ON (hm.AD_Rule_Engine_ID=er.AD_Rule_Engine_ID)"
			    + " WHERE hm.Processed='N' AND hp.HR_Process_ID = " +fieldProcess.getValue()
			    //+ " AND IsStatic = 'Y' " // Solo aplique Incidencias [add 30Dic2006 para ver Todo]
			    + " AND hp.C_BPartner_ID = " + fieldEmployee.getValue()
				+ " AND (Qty > 0 OR Amount > 0 OR Text != '' OR ServiceDate IS NOT NULL) ");
			    if (fieldValidFrom.getValue() == null)
        sqlQuery.append(" AND " +DB.TO_DATE(dateStart)+" >= hm.ValidFrom  AND "+DB.TO_DATE(dateEnd)+" <=  hm.ValidTo ");
		sqlQuery.append(" ORDER BY hm.AD_Org_ID,hp.HR_Process_ID,bp.Name,hm.ValidFrom,hm.HR_Concept_ID");
		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
		//  Execute
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlQuery.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//  extend table
				miniTable.setRowCount(row+1);
				//  set values
				miniTable.setColumnClass(0, IDColumn.class, false, " ");
				miniTable.setValueAt(rs.getString(1), row, 1);		// AD_Org_ID
				miniTable.setValueAt(rs.getString(4), row, 2);		// HR_Concept_ID
				miniTable.setValueAt(rs.getTimestamp(5), row, 3);	// ValidFrom
				miniTable.setValueAt(rs.getString(6), row, 4);		// ColumnType
				miniTable.setValueAt(rs.getObject(7) != null ? rs.getBigDecimal(7) : Env.ZERO, row, 5);	// Qty
				miniTable.setValueAt(rs.getObject(8) != null ? rs.getBigDecimal(8) : Env.ZERO, row, 6);	// Amount
				miniTable.setValueAt(rs.getTimestamp(9), row, 7);	// ServiceDate
				miniTable.setValueAt(rs.getString(10), row, 8);		// Text
				miniTable.setValueAt(rs.getString(11), row, 9);		// AD_Rule_Engine_ID
				miniTable.setValueAt(rs.getString(12), row, 10);	// Description
				//  prepare next
				row++;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e) {
			log.log(Level.SEVERE, sqlQuery.toString(), e);
		}
		miniTable.autoSize();
	}   //  executeQuery
	
	
	
	/**
	 *  get Process
	 *  parameter: MHRProcess
	 */
	public void getProcess()
	{
		KeyNamePair pp = new KeyNamePair(0, "");
		fieldProcess.addItem(pp);		
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT hrp.HR_Process_ID,hrp.DocumentNo ||'-'|| hrp.Name,hrp.DocumentNo,hrp.Name FROM HR_Process hrp",
					"hrp",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) + " AND hrp.IsActive = 'Y' ";
		sql += " ORDER BY hrp.DocumentNo, hrp.Name";
		try{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				fieldProcess.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e){
			log.log(Level.SEVERE, sql, e);
		}
		fieldProcess.setSelectedIndex(0);	
	} //getProcess
	
	
	
	/**
	 *  get Employee 
	 *  to Valid  Payroll-Departmant-Employee of Process Actual 
	 *  parameter: MHRProcess
	 */
	public void getEmployeeValid(MHRProcess process)
	{
		if(process == null)
			return;
		//fieldEmployee.removeAllItems(); // Limpia antes de Agregar
		KeyNamePair pp = new KeyNamePair(0, "");
		fieldEmployee.addItem(pp);		
		String sql = MRole.getDefault().addAccessSQL(
		"SELECT bp.C_BPartner_ID,bp.Name FROM HR_Employee hrpe INNER JOIN C_BPartner bp ON(bp.C_BPartner_ID=hrpe.C_BPartner_ID)",
			"hrpe",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) + " AND hrpe.IsActive = 'Y' ";
 		if ( process.getHR_Payroll_ID() != 0){
 			sql += " AND (hrpe.HR_Payroll_ID =" +process.getHR_Payroll_ID()+ " OR hrpe.HR_Payroll_ID is NULL)" ;
 			/*if ( process.getHR_Department_ID() != 0 );
 				sql += " AND (hrpe.HR_Department_ID =" +process.getHR_Department_ID()+" OR hrpe.HR_Department_ID is NULL)" ;
 			if ( process.getHR_Employee_ID() != 0 );
 				sql += " AND (hrpe.HR_Employee_ID =" + process.getHR_Employee_ID()+" OR hrpe.HR_Employee_ID is NULL)" ;*/
 		}
		sql += " ORDER BY bp.Name ";
		try{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				fieldEmployee.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e){
			log.log(Level.SEVERE, sql, e);
		}
		fieldEmployee.setSelectedIndex(0);	
	} //getEmployeeValid

	
	
	/**
	 *  get Concept 
	 *  to Valide Dates of Process
	 *  parameter: MHRProcess
	 */
	public void getConceptValid()
	{
		if( fieldProcess == null )
			return;
		//fieldConcept.removeAllItems(); // Limpia antes de Agregar
		KeyNamePair pp = new KeyNamePair(0, "");
		fieldConcept.addItem(pp);		
		String sql = "SELECT hrpc.HR_Concept_ID,hrpc.Name,hrpc.Value "
				    + " FROM HR_Concept hrpc "
					+ " INNER JOIN HR_Attribute hrpa ON (hrpa.HR_Concept_ID=hrpc.HR_Concept_ID)"
					+ " WHERE hrpc.AD_Client_ID = " +Env.getAD_Client_ID(Env.getCtx())
					+ " AND hrpc.IsActive = 'Y' AND hrpc.IsRegistered = 'Y' "
					+ " AND (hrpa.HR_Payroll_ID = " +HR_Payroll_ID+ "OR hrpa.HR_Payroll_ID IS NULL)";
 		if (fieldProcess != null){ // Process
 			; //sql += " AND " +DB.TO_DATE(dateStart)+ " >= hrpa.ValidFrom  AND " +DB.TO_DATE(dateEnd)+ "<= hrpa.ValidTo";
/* 			if (process.getHR_Payroll_ID() != 0) // Process & Payroll
 				sql = sql + " AND (hrpa.HR_Payroll_ID = " +process.getHR_Payroll_ID()+ " OR hrpa.HR_Payroll_ID is NULL)" ;
 			if (process.getHR_Department_ID() != 0 ); // Process & Department
 				sql = sql + " AND (hrpa.HR_Department_ID = " +process.getHR_Department_ID()+" OR hrpa.HR_Department_ID is NULL)" ;
 			if (process.getHR_Department_ID() != 0 ); // Process & Employee
 				sql = sql + " AND (hrpa.HR_Employee_ID = " + process.getHR_Employee_ID()+" OR hrpa.HR_Employee_ID is NULL)" ;*/
 		}
			sql = sql +" ORDER BY hrpc.Value";
		try	{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				fieldConcept.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e){
			log.log(Level.SEVERE, sql, e);
		}
		fieldConcept.setSelectedIndex(0);	
	} //getConceptValid	

	
	/**
	 *  get Process
	 *  parameter: MHRProcess
	 */
	public void ColumnType(MHRConcept concept)
	{

	}
		
	/**
	 *  get record Found to Movement Payroll
	 *  parameter: 
	 */
	public int seekMovement()
	{
		if(fieldConcept.getValue() == null )
			return 0;
		int HR_Movement_ID = 0;
		String date = DB.TO_DATE((Timestamp)fieldValidFrom.getValue());
		int Process_ID  = 0; KeyNamePair ppp = (KeyNamePair)fieldProcess.getSelectedItem();
		int Employee_ID = 0; KeyNamePair ppe = (KeyNamePair)fieldEmployee.getSelectedItem();
		int Concept_ID  = 0; KeyNamePair ppc = (KeyNamePair)fieldConcept.getSelectedItem();
		//
		Process_ID  = ppp != null ? ppp.getKey(): null;
		Employee_ID = ppe != null ? ppe.getKey(): null;
		Concept_ID  = ppc != null ? ppc.getKey(): null;
		MHRConcept concept = new MHRConcept(Env.getCtx(),Concept_ID,null);
		//
		if ( (Process_ID+Employee_ID+Concept_ID) > 0 ){
			HR_Movement_ID = DB.getSQLValue("Movement","SELECT HR_Movement_ID "
					+" FROM HR_Movement WHERE HR_Process_ID = "+Process_ID
					+" AND C_BPartner_ID =" +Employee_ID+ " AND HR_Concept_ID = "+Concept_ID
					+" AND TRUNC(ValidFrom) = TRUNC(" + date +")");
			if (HR_Movement_ID > 0){  // exist record in Movement Payroll
				sHR_Movement_ID = HR_Movement_ID;
				MHRMovement movementFound = new MHRMovement(Env.getCtx(),sHR_Movement_ID,null);
				//
				fieldDescription.setValue(movementFound.getDescription());
				if ( concept.getColumnType().equals("Q") )	// Quantity
					fieldQty.setValue(movementFound.getQty());
				else if (concept.getColumnType().equals("A") )	// Amount
					fieldAmount.setValue(movementFound.getAmount());
				else if (concept.getColumnType().equals("T") )	// Text
					fieldText.setValue(movementFound.getTextMsg());
				else if (concept.getColumnType().equals("D") )	// Date
					fieldDate.setValue(movementFound.getServiceDate());
			}
		}
		return HR_Movement_ID;
	} //seekMovement
}   //  VIncidence
