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
package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessPanel;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.MiniTable;
import org.compiere.minigrid.MiniTable.MiniTableSelectionListener;
import org.compiere.minigrid.MiniTable.RowSelectionEvent;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MSysConfig;
import org.compiere.model.X_C_PaySelection;
import org.compiere.plaf.CompiereColor;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 *  Create Manual Payments From (AP) Invoices or (AR) Credit Memos.
 *  Allows user to select Invoices for payment.
 *  When Processed, PaySelection is created
 *  and optionally posted/generated and printed
 *
 *  @author Jorg Janke
 *  @version $Id: VPaySelect.java,v 1.2 2008/07/11 08:20:12 cruiz Exp $
 *  
 *  @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * 				<li>release/380 bug fix - PaySelect behaviour for auto query, window size,
 * 					use of VLookup for BPartner field and better event processing
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *				<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *				@see https://github.com/adempiere/adempiere/issues/265
 */
@Deprecated
public class VPaySelect extends PaySelect implements FormPanel, ActionListener, ASyncProcess, PropertyChangeListener, MiniTableSelectionListener
{
	/** @todo withholding */
	private CPanel panel = new CPanel();

	private boolean m_loadedOK = false;

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

		try
		{
			jbInit();
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
	
	/**	FormFrame			*/
	private FormFrame 		m_frame;

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private CLabel labelBankAccount = new CLabel();
	private VComboBox fieldBankAccount = new VComboBox();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CLabel labelBankBalance = new CLabel();
	private CLabel labelCurrency = new CLabel();
	private CLabel labelBalance = new CLabel();
	private VCheckBox onlyDue = new VCheckBox();
	private CLabel labelBPartner = new CLabel();
	private VLookup fieldBPartner;
	private CLabel labelDtype = new CLabel();
	private VComboBox fieldDtype = new VComboBox();

	private JLabel dataStatus = new JLabel();
	private JScrollPane dataPane = new JScrollPane();
	private MiniTable miniTable = new MiniTable();
	private CPanel commandPanel = new CPanel();
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bGenerate = ConfirmPanel.createProcessButton(true);
	private FlowLayout commandLayout = new FlowLayout();
	private JButton bRefresh = ConfirmPanel.createRefreshButton(true);
	private CLabel labelPayDate = new CLabel();
	private VDate fieldPayDate = new VDate();
	private CLabel labelPaymentRule = new CLabel();
	private VComboBox fieldPaymentRule = new VComboBox();
	
	private VCheckBox checkAutoQuery = new VCheckBox();
	private static String SYSCONFIG_INFO_AUTO_QUERY = "INFO_AUTO_QUERY";

	/** Window Width                */
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screensize = toolkit.getScreenSize();
	protected final int        SCREEN_WIDTH = screensize.width > 1000 ? 1000 : screensize.width - 100;
	protected final int        SCREEN_HEIGHT = screensize.height > 1000 ? 800 : screensize.height - 100;

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(panel);
		//
		mainPanel.setLayout(mainLayout);
		parameterPanel.setLayout(parameterLayout);
		//
		labelBankAccount.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		fieldBankAccount.addActionListener(this);
		//
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fieldBPartner = new VLookup("C_BPartner_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
				MColumn.getColumn_ID(MBPartner.Table_Name, MBPartner.COLUMNNAME_C_BPartner_ID),
				DisplayType.Search));
		fieldBPartner.addActionListener(this);
		//
		bRefresh.addActionListener(this);
		//
		labelDtype.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
		fieldDtype.addActionListener(this);
		//
		labelPayDate.setText(Msg.translate(Env.getCtx(), "PayDate"));
		fieldPayDate.addActionListener(this);
		//
		labelPaymentRule.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		fieldPaymentRule.addActionListener(this);
		//
		labelBankBalance.setText(Msg.translate(Env.getCtx(), "CurrentBalance"));
		labelBalance.setText("0");
		//
		onlyDue.setText(Msg.getMsg(Env.getCtx(), "OnlyDue"));
		onlyDue.addActionListener(this);
		//
		dataStatus.setText(" ");
		//
		checkAutoQuery.setText(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.setToolTipText(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.setName("AutoQuery");
		checkAutoQuery.setSelected(MSysConfig.getValue(SYSCONFIG_INFO_AUTO_QUERY,"Y",Env.getAD_Client_ID(Env.getCtx())).equals("Y"));  
		checkAutoQuery.addActionListener(this);
		//
		bGenerate.addActionListener(this);
		bCancel.addActionListener(this);
		//
		miniTable.addPropertyChangeListener(this);

		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		mainPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

		parameterPanel.add(labelBankAccount,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldBankAccount,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		parameterPanel.add(labelBankBalance,  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelCurrency,  new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));

		parameterPanel.add(labelBalance,   new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		parameterPanel.add(labelBPartner,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldBPartner,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		parameterPanel.add(labelDtype,   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldDtype,    new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelPaymentRule,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			parameterPanel.add(fieldPaymentRule,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		parameterPanel.add(checkAutoQuery,    new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));


		parameterPanel.add(labelPayDate,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldPayDate,   new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(onlyDue,  new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(bRefresh,    new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		mainPanel.add(dataStatus, BorderLayout.SOUTH);
		mainPanel.add(dataPane, BorderLayout.CENTER);
		dataPane.getViewport().add(miniTable, null);
		//
		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		commandPanel.add(bCancel, null);
		commandPanel.add(bGenerate, null);
		
		m_frame.getContentPane().add(commandPanel, BorderLayout.SOUTH);
		m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

	}   //  jbInit

	/**
	 *  Dynamic Init.
	 *  - Load Bank Info
	 *  - Load Document Type
	 *  - Init Table
	 */
	private void dynInit()
	{
		ArrayList<BankInfo> bankAccountData = getBankAccountData();
		for(BankInfo bi : bankAccountData)
			fieldBankAccount.addItem(bi);

		if (fieldBankAccount.getItemCount() == 0)
			ADialog.error(m_WindowNo, panel, "VPaySelectNoBank");
		else
			fieldBankAccount.setSelectedIndex(0);		
		
		loadBankInfo();
		
		ArrayList<KeyNamePair> docTypeData = getDocTypeData();
		for(KeyNamePair pp : docTypeData)
			fieldDtype.addItem(pp);
		
		prepareTable(miniTable);
		miniTable.addMiniTableSelectionListener(this); // To enable buttons
		
		//
		fieldPayDate.setMandatory(true);
		fieldPayDate.setValue(new Timestamp(System.currentTimeMillis()));
	}   //  dynInit

	/**
	 *  Load Bank Info - Load Info from Bank Account and valid Documents (PaymentRule)
	 */
	private void loadBankInfo()
	{
		m_loadedOK = false; // Prevent event processing
		
		log.fine("");
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem();
		if (bi == null)
			return;
		labelCurrency.setText(bi.Currency);
		labelBalance.setText(m_format.format(bi.Balance));

		//  PaymentRule
		fieldPaymentRule.removeAllItems();
		
		ArrayList<ValueNamePair> paymentRuleData = getPaymentRuleData(bi);
		for(ValueNamePair vp : paymentRuleData)
			fieldPaymentRule.addItem(vp);
		fieldPaymentRule.setSelectedIndex(0);
		
		m_loadedOK = true; // Enable event processing
	}   //  loadBankInfo

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
		Timestamp payDate = (Timestamp)fieldPayDate.getValue();
		miniTable.setColorCompare(payDate);
		log.config("PayDate=" + payDate);
		
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem();
		
		ValueNamePair paymentRule = (ValueNamePair) fieldPaymentRule.getSelectedItem();
		KeyNamePair docType = (KeyNamePair) fieldDtype.getSelectedItem();
		
		int c_bpartner_id = 0;
		if (fieldBPartner.getValue() != null)
			c_bpartner_id = ((Integer) fieldBPartner.getValue()).intValue();
		
		loadTableInfo(bi, payDate, paymentRule, onlyDue.isSelected(), c_bpartner_id, docType, miniTable);
		
		calculateSelection();
	}   //  loadTableInfo

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
	 *  ActionListener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		boolean triggerRefresh = false;
		
		if(!m_loadedOK)
			return;

		Object source = e.getSource();
		String cmd = e.getActionCommand();
		
		//  Trigger an update if any criteria change
		if (source instanceof VComboBox && ((VComboBox) source).getParent() instanceof VLookup)
		{
			source = ((VComboBox) source).getParent();
			VLookup vl = ((VLookup)source);
			
			//  Discard changes from mouse movements and keyboard entries
			//  Respond only to the Enter key which causes "comboBoxEdited"
			//  VLookups trigger multiple events in search mode. Reject 
			//  events that don't have changes from the last action.
			if(cmd.equals("comboBoxChanged"))		
			{
				if (!vl.hasChanged())
					return;
				else
					triggerRefresh = true;
			}
			else if(cmd.equals("comboBoxEdited"))
			{
				if (!vl.hasChanged())
				{
					vl.requestFocus();
					return;
				}
				triggerRefresh = true;						
			}
		}
		else if (source instanceof CTextField)
		{
			CTextField tf = ((CTextField) source);
	
			if (tf.getParent() instanceof VLookup)
			{
				// Ignore it.  User typed into the VLookup text field.
				// Look for events form the VLookup VComboBox component
				// instead.
				return;
			}
			else if (tf.hasChanged())  //  The change may have come from another field
			{
				triggerRefresh = true;
			}
		}
		else if (e.getSource() instanceof VCheckBox)
		{
			//  Check box changes generally always cause a refresh
			//  Capture changes that don't 
			triggerRefresh = true;
			
			VCheckBox cb = (VCheckBox) e.getSource();
			if (cb.getName().equals("AutoQuery"))
			{
				//  Only trigger a refresh if the check box is selected
				if(!cb.isSelected())
				{
					return;
				}
			}
		}
		else if(cmd.equals("comboBoxChanged"))		
		{
			VComboBox vcb = (VComboBox) source;
			if (!vcb.hasChanged())
			{
				return;
			} else {
				triggerRefresh = true;
			}
		}
		else if(cmd.equals("comboBoxEdited"))
		{
			VComboBox vcb = (VComboBox) source;			
			if (!vcb.hasChanged())
			{
				vcb.requestFocus();
				return;
			}
			triggerRefresh = true;						
		}

		
		if (triggerRefresh && e.getSource() == fieldBankAccount)
		{
			loadBankInfo();
		}

		//  Generate PaySelection
		if (e.getSource() == bGenerate)
		{
			generatePaySelect();
			dispose();
		}
		else if (e.getSource() == bCancel)
			dispose();
				
		// Refresh if the autoquery feature is selected or the refresh button is clicked.
		if (e.getSource() == bRefresh || (checkAutoQuery.isSelected() && triggerRefresh))
		{
			setFieldOldValues();
			loadTableInfo();
		}

	}   //  actionPerformed

	/**
	 * Property Change Listener
	 * @param e event
	 */
	public void propertyChange(PropertyChangeEvent e)
	{
		// Respond to updates to the table
		if (e.getPropertyName() == "p_table_update")
			calculateSelection();
	}   
	
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
		miniTable.stopEditor(true);
		if (miniTable.getRowCount() == 0)
			return;
		miniTable.setRowSelectionInterval(0,0);
		calculateSelection();
		if (m_noSelected == 0)
			return;
		
		String msg = generatePaySelect(miniTable, (ValueNamePair) fieldPaymentRule.getSelectedItem(), 
				fieldPayDate.getTimestamp(), (BankInfo)fieldBankAccount.getSelectedItem());
		
		if(msg != null && msg.length() > 0)		
		{
			ADialog.error(m_WindowNo, panel, "SaveError", msg);
			return;
		}

		//	FR [ 297 ]
		//  Ask to Post it
		if (!ADialog.ask(m_WindowNo, panel, "VPaySelectGenerate?", "(" + m_ps.getDocumentNo() + ")"))
			return;
		
		//  Prepare Process
		int AD_Proces_ID = 155;	//	C_PaySelection_CreatePayment
		ProcessInfo pi = new ProcessInfo (m_frame.getTitle(), AD_Proces_ID,
			X_C_PaySelection.Table_ID, m_ps.getC_PaySelection_ID());
		pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		
		ProcessPanel pp = new ProcessPanel(m_WindowNo, pi);
		//	Execute Process
		//	BR [ 265 ]
		ProcessCtl.process(this, m_WindowNo, pp, pi, trx);
	//	ProcessCtl worker = new ProcessCtl(this, pi, trx);
	//	worker.start();     //  complete tasks in unlockUI
	}   //  generatePaySelect

	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
		panel.setEnabled(false);
		m_isLocked = true;
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi process info
	 */
	public void unlockUI (ProcessInfo pi)
	{
	//	this.setEnabled(true);
	//	m_isLocked = false;
		//  Ask to Print it		//	Window is disposed
		if (!ADialog.ask(0, panel, "VPaySelectPrint?", "(" + pi.getSummary() + ")"))
			return;

		//  Start PayPrint
		int AD_Form_ID = 106;	//	Payment Print/Export
		FormFrame ff = new FormFrame();
		ff.openForm (AD_Form_ID);
		//	Set Parameter
		if (m_ps != null)
		{
			VPayPrint pp = (VPayPrint)ff.getFormPanel();
			pp.setPaySelection(m_ps.getC_PaySelection_ID());
		}
		//
		ff.pack();
		panel.setVisible(false);
                AEnv.addToWindowManager(ff);
		AEnv.showCenterScreen(ff);
		this.dispose();
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.config("-");
	}   //  executeASync

	@Override
	public void rowSelected(RowSelectionEvent e) {

		calculateSelection();
		
	}

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
