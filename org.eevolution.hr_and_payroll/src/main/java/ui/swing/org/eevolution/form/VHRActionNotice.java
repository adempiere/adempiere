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
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRPeriod;
import org.eevolution.model.MHRProcess;

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
 */
public class VHRActionNotice extends HRActionNotice implements FormPanel, ActionListener, VetoableChangeListener
{
	public static CTextField fieldDescription = new CTextField(22);
	public static VDate fieldValidFrom = new VDate();
	public static VNumber fieldQty = new VNumber();
	public static VNumber fieldAmount = new VNumber();
	public static VDate fieldDate = new VDate();
	public static CTextField fieldText = new CTextField(22);
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	public static VComboBox fieldProcess = new VComboBox();
	public static VComboBox fieldEmployee = new VComboBox();
	public static VComboBox fieldConcept = new VComboBox();

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
	private static CTextField fieldColumnType = new CTextField(18);
	private CLabel labelConcept = new CLabel();
	private JLabel labelValue = new JLabel();
	private JLabel dataStatus = new JLabel();
	private JScrollPane dataPane = new JScrollPane();
	private VNumber fieldRuleE = new VNumber();
	private static MiniTable miniTable = new MiniTable();
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
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "Y");
		try
		{
			super.dynInit();
			dynInit();
			jbInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.setSize(1000, 400);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "init", ex);
		}
	}	//	init


	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit()
	{
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
		configureMiniTable(miniTable);
	}	//	fillPicks


	public static void executeQuery()
	{
		executeQuery(Env.getCtx(),miniTable);
	}   //  executeQuery


	/**************************************************************************
	 *  vetoableChange
	 *  @param e event
	 */
	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException 
	{
		fieldConcept.setReadWrite(true);
		log.fine("Event"+ e);
		log.fine("Event Source "+ e.getSource());
		log.fine("Event Property "+ e.getPropertyName());
		Integer   HR_Period_ID = new MHRProcess(Env.getCtx(),(Integer)fieldProcess.getValue(),null).getHR_Period_ID(); 
		String date = DB.TO_DATE((Timestamp)fieldValidFrom.getValue());
		int existRange = DB.getSQLValueEx(null,"SELECT HR_Period_ID FROM HR_Period WHERE " +date+
				" >= StartDate AND "+date+	" <= EndDate AND HR_Period_ID = "+HR_Period_ID);
		// Exist of Range Payroll
		if ( existRange < 0){
			fieldConcept.setReadWrite(false);
			return;
		}
		if (fieldConcept != null)
			sHR_Movement_ID = seekMovement((Timestamp)fieldValidFrom.getValue());  // exist movement record to date actual
	}   //  vetoableChange


	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		
		log.fine("Event "+ e);
		log.fine("Event Source "+ e.getSource());
		if ( e.getSource().equals(fieldProcess) ) {					// Process
			KeyNamePair pp = (KeyNamePair)fieldProcess.getSelectedItem();
			if (pp != null){
				m_HR_Process_ID = pp.getKey();
				m_process = new MHRProcess(Env.getCtx(),m_HR_Process_ID, null);
					if(m_process.getHR_Period_ID() > 0)
					{
						MHRPeriod period = MHRPeriod.get(Env.getCtx(), m_process.getHR_Period_ID());
						m_dateStart= period.getStartDate();				
						m_dateEnd  = period.getEndDate();
					}
					else
					{
						m_dateEnd = m_process.getDateAcct();
					}
				m_HR_Payroll_ID = m_process.getHR_Payroll_ID();
				fieldEmployee.removeAllItems();

				for(KeyNamePair ppt : getEmployeeValid(m_process))
				{
					fieldEmployee.addItem(ppt);
				}
				fieldEmployee.setSelectedIndex(0);
				fieldEmployee.setReadWrite(true);
			}
		}
		else if ( e.getSource().equals(fieldEmployee) ){			// Employee
			KeyNamePair pp = (KeyNamePair)fieldEmployee.getSelectedItem();
			if ( pp != null )
				m_C_BPartner_ID = pp.getKey();
			if ( m_C_BPartner_ID > 0){			
				fieldValidFrom.setValue(m_dateEnd);
				fieldValidFrom.setReadWrite(true);
								
				ArrayList<ValueNamePair> conceptData = getConcept(m_process, fieldProcess != null);
				for(ValueNamePair vp : conceptData)
					fieldConcept.addItem(vp);

				fieldConcept.setReadWrite(true);
				executeQuery();
			}
		}
		else if ( e.getSource().equals(fieldConcept) ) {			// Concept
			ValueNamePair pp = (ValueNamePair)fieldConcept.getSelectedItem();
			if (pp != null)
			{
				try{
					m_HR_Concept_ID = Integer.parseInt(pp.getValue());
				}
				catch(Exception ex){
					m_HR_Concept_ID = 0;
				}
			}
			
			if (m_HR_Concept_ID > 0) {
				MHRConcept concept = MHRConcept.get(Env.getCtx(),m_HR_Concept_ID);
				// Name To Type Column
				fieldColumnType.setValue(DB.getSQLValueStringEx(null, getSQL_ColumnType(Env.getCtx(), "?"), concept.getColumnType() )); 
				sHR_Movement_ID = seekMovement((Timestamp)fieldValidFrom.getValue()); //  exist movement record to date actual				

				if (sHR_Movement_ID > 0){
					MHRMovement movementFound = new MHRMovement(Env.getCtx(),sHR_Movement_ID,null);
					fieldDescription.setValue(movementFound.getDescription());
					fieldText.setValue("");
					fieldDate.setValue(null);
					fieldQty.setValue(Env.ZERO);
					fieldAmount.setValue(Env.ZERO);
					if ( concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity) )	// Quantity
						fieldQty.setValue(movementFound.getQty());
					else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount) )	// Amount
						fieldAmount.setValue(movementFound.getAmount());
					else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Text) )	// Text
						fieldText.setValue(movementFound.getTextMsg());
					else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Date) )	// Date
						fieldDate.setValue(movementFound.getServiceDate());
				}
				
				if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity)){				// Concept Type
					fieldQty.setVisible(true);
					fieldQty.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);				
				} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount)){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(true);
					fieldAmount.setReadWrite(true);
					fieldDate.setVisible(false);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Date)){
					fieldQty.setVisible(false);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(true);
					fieldDate.setReadWrite(true);
					fieldText.setVisible(false);
					fieldRuleE.setVisible(false);
				} else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Text)){
					fieldText.setVisible(true);
					fieldText.setReadWrite(true);
					fieldAmount.setVisible(false);
					fieldDate.setVisible(false);
					fieldRuleE.setVisible(false);
				}
				fieldDescription.setReadWrite(true);
			}
		} // Concept
		else if (e instanceof ActionEvent && e.getSource().equals(bOk) ){					 // Movement SAVE
			ValueNamePair pp = (ValueNamePair)fieldConcept.getSelectedItem();
			m_HR_Concept_ID = Integer.parseInt(pp.getValue());
			if ( m_HR_Concept_ID <= 0
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

	public static void saveMovement()
	{
		MHRConcept conceptOK   = MHRConcept.get(Env.getCtx(),m_HR_Concept_ID);
		int mov = sHR_Movement_ID > 0 ? sHR_Movement_ID : 0;
		MHRMovement movementOK = new MHRMovement(Env.getCtx(),mov,null);
		movementOK.setDescription(fieldDescription.getValue() != null ? (String)fieldDescription.getValue().toString() : "");
		movementOK.setHR_Process_ID((Integer)fieldProcess.getValue());
		movementOK.setC_BPartner_ID((Integer)fieldEmployee.getValue());
		movementOK.setHR_Concept_ID(Integer.parseInt((String)fieldConcept.getValue()));
		movementOK.setHR_Concept_Category_ID(conceptOK.getHR_Concept_Category_ID());
		movementOK.setColumnType(conceptOK.getColumnType());
		movementOK.setQty(fieldQty.getValue() != null ? (BigDecimal)fieldQty.getValue() : Env.ZERO);
		movementOK.setAmount(fieldAmount.getValue() != null ? (BigDecimal)fieldAmount.getValue() : Env.ZERO );
		movementOK.setTextMsg(fieldText.getValue() != null ? (String)fieldText.getValue().toString() : "");
		movementOK.setServiceDate(fieldDate.getValue() != null ? (Timestamp)fieldDate.getValue() : null);
		movementOK.setValidFrom((Timestamp)fieldValidFrom.getTimestamp());
		movementOK.setValidTo((Timestamp)fieldValidFrom.getTimestamp());
		MHREmployee employee  = MHREmployee.getActiveEmployee(Env.getCtx(), movementOK.getC_BPartner_ID(), null);
		if (employee != null) {
			movementOK.setAD_Org_ID(employee.getAD_Org_ID());
			movementOK.setHR_Department_ID(employee.getHR_Department_ID());
			movementOK.setHR_Job_ID(employee.getHR_Job_ID());
			movementOK.setC_Activity_ID(employee.getC_Activity_ID() > 0 ? employee.getC_Activity_ID() : employee.getHR_Department().getC_Activity_ID());
		}
		movementOK.setIsManual(true);
		movementOK.saveEx();
			// check if user saved an empty record and delete it
		if ( (movementOK.getAmount() == null || Env.ZERO.compareTo(movementOK.getAmount()) == 0)
					&& (movementOK.getQty() == null || Env.ZERO.compareTo(movementOK.getQty()) == 0)
					&& (movementOK.getServiceDate() == null)
					&& (movementOK.getTextMsg() == null || movementOK.getTextMsg().trim().length() == 0)) {
		movementOK.deleteEx(false);
		}
		executeQuery();
		fieldValidFrom.setValue(m_dateEnd);
		fieldColumnType.setValue("");
		fieldQty.setValue(Env.ZERO);
		fieldAmount.setValue(Env.ZERO);
		fieldQty.setReadWrite(false);
		fieldAmount.setReadWrite(false);
		fieldText.setReadWrite(false);
		fieldDescription.setReadWrite(false);
		sHR_Movement_ID = 0; // Initial not exist record in Movement to actual date
		// clear fields
		fieldDescription.setValue("");
		fieldText.setValue("");
		fieldDate.setValue(null);
		fieldQty.setValue(Env.ZERO);
		fieldAmount.setValue(Env.ZERO);
		fieldConcept.setSelectedIndex(0);
	}
}   //  VHRActionNotice
