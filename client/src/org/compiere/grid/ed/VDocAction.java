/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 *****************************************************************************/
package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;
import org.compiere.wf.*;

/**
 *	Displays valid Document Action Options based on context
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VDocAction.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VDocAction extends CDialog
	implements ActionListener
{
	/**
	 *	Constructor.
	 *  Started from APanel. 
	 * 	getNumberOfOptions()is used to determine to show the dialog
	 *  @param WindowNo window no
	 *  @param mTab tab
	 * 	@param Record_ID record id
	 *  @param button button
	 */
	public VDocAction (int WindowNo, GridTab mTab, VButton button, int Record_ID)
	{
		super(Env.getWindow(WindowNo), Msg.translate(Env.getCtx(), "DocAction"), true);
		log.config("");
		m_WindowNo = WindowNo;
		m_mTab = mTab;
		//
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}

		//	dynamic init preparation
		m_AD_Table_ID = Env.getContextAsInt(Env.getCtx(), WindowNo, "BaseTable_ID");
		if (s_value == null)
			readReference();
		//
		dynInit(Record_ID);
		//
		AEnv.positionCenterWindow(Env.getWindow(WindowNo), this);
	}	//	VDocAction

	//
	private int					m_WindowNo = 0;
	private int					m_AD_Table_ID;
	private boolean				m_OKpressed = false;
	private boolean				m_batch = false;
	private GridTab         		m_mTab;
	//
	private static String[]		s_value = null;
	private static String[]		s_name;
	private static String[]		s_description;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDocAction.class);
	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel northPanel = new CPanel();
	private CComboBox actionCombo = new CComboBox();
	private JLabel actionLabel = new JLabel();
	private JScrollPane centerPane = new JScrollPane();
	private JTextArea message = new JTextArea();
	private FlowLayout northLayout = new FlowLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private JButton batchButton = ConfirmPanel.createProcessButton(
			Msg.getMsg(Env.getCtx(), "StartBackground"));

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		mainPanel.setLayout(mainLayout);
		actionLabel.setText(Msg.translate(Env.getCtx(), "DocAction"));
		actionCombo.addActionListener(this);
		message.setLineWrap(true);
		message.setPreferredSize(new Dimension(350, 35));
		message.setWrapStyleWord(true);
		message.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		message.setEditable(false);
		northPanel.setLayout(northLayout);
		northLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(mainPanel);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(actionLabel, null);
		northPanel.add(actionCombo, null);
		mainPanel.add(centerPane, BorderLayout.CENTER);
		centerPane.getViewport().add(message, null);
		//
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addButton(batchButton);
		confirmPanel.addActionListener(this);
		batchButton.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dynamic Init - determine valid DocActions based on DocStatus for the different documents.
	 *  <pre>
	 *  DocStatus (131)
			??                         Unknown
			AP *                       Approved
			CH                         Changed
			CL *                       Closed
			CO *                       Completed
			DR                         Drafted
			IN                         Inactive
			NA                         Not Approved
			PE                         Posting Error
			PO *                       Posted
			PR *                       Printed
			RE                         Reversed
			TE                         Transfer Error
			TR *                       Transferred
			VO *                       Voided
			XX                         Being Processed
	 *
	 *   DocAction (135)
			--                         <None>
			AP *                       Approve
			CL *                       Close
			CO *                       Complete
			PO *                       Post
			PR *                       Print
			RA                         Reverse - Accrual
			RC                         Reverse - Correction
			RE                         RE-activate
			RJ                         Reject
			TR *                       Transfer
			VO *                       Void
			XL                         Unlock
	 *  </pre>
	 * 	@param Record_ID id
	 */
	private void dynInit(int Record_ID)
	{
		String DocStatus = (String)m_mTab.getValue("DocStatus");
		String DocAction = (String)m_mTab.getValue("DocAction");
		//
		Object Processing = m_mTab.getValue("Processing");
		String OrderType = Env.getContext(Env.getCtx(), m_WindowNo, "OrderType");
		String IsSOTrx = Env.getContext(Env.getCtx(), m_WindowNo, "IsSOTrx");

		if (DocStatus == null)
		{
			message.setText("*** ERROR ***");
			return;
		}

		log.fine("DocStatus=" + DocStatus 
			+ ", DocAction=" + DocAction + ", OrderType=" + OrderType 
			+ ", IsSOTrx=" + IsSOTrx + ", Processing=" + Processing 
			+ ", AD_Table_ID=" + m_AD_Table_ID + ", Record_ID=" + Record_ID);
		//
		String[] options = new String[s_value.length];
		int index = 0;

		/**
		 * 	Check Existence of Workflow Acrivities
		 */
		String wfStatus = MWFActivity.getActiveInfo(Env.getCtx(), m_AD_Table_ID, Record_ID); 
		if (wfStatus != null)
		{
			ADialog.error(m_WindowNo, this, "WFActiveForRecord", wfStatus);
			return;
		}
		
		//	Status Change
		if (!checkStatus(m_mTab.getTableName(), Record_ID, DocStatus))
		{
			ADialog.error(m_WindowNo, this, "DocumentStatusChanged");
			return;
		}
		
		/*******************
		 *  General Actions
		 */

		//	Locked
		if (Processing != null)
		{
			boolean locked = "Y".equals(Processing);
			if (!locked && Processing instanceof Boolean)
				locked = ((Boolean)Processing).booleanValue();
			if (locked)
				options[index++] = DocumentEngine.ACTION_Unlock;
		}

		//	Approval required           ..  NA
		if (DocStatus.equals(DocumentEngine.STATUS_NotApproved))
		{
			options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	Draft/Invalid				..  DR/IN
		else if (DocStatus.equals(DocumentEngine.STATUS_Drafted)
			|| DocStatus.equals(DocumentEngine.STATUS_Invalid))
		{
			options[index++] = DocumentEngine.ACTION_Complete;
		//	options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	In Process                  ..  IP
		else if (DocStatus.equals(DocumentEngine.STATUS_InProgress)
			|| DocStatus.equals(DocumentEngine.STATUS_Approved))
		{
			options[index++] = DocumentEngine.ACTION_Complete;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	Complete                    ..  CO
		else if (DocStatus.equals(DocumentEngine.STATUS_Completed))
		{
			options[index++] = DocumentEngine.ACTION_Close;
		}
		//	Waiting Payment
		else if (DocStatus.equals(DocumentEngine.STATUS_WaitingPayment)
			|| DocStatus.equals(DocumentEngine.STATUS_WaitingConfirmation))
		{
			options[index++] = DocumentEngine.ACTION_Void;
			options[index++] = DocumentEngine.ACTION_Prepare;
		}
		//	Closed, Voided, REversed    ..  CL/VO/RE
		else if (DocStatus.equals(DocumentEngine.STATUS_Closed) 
			|| DocStatus.equals(DocumentEngine.STATUS_Voided) 
			|| DocStatus.equals(DocumentEngine.STATUS_Reversed))
			return;

		/********************
		 *  Order
		 */
		if (m_AD_Table_ID == MOrder.Table_ID)
		{
			//	Draft                       ..  DR/IP/IN
			if (DocStatus.equals(DocumentEngine.STATUS_Drafted)
				|| DocStatus.equals(DocumentEngine.STATUS_InProgress)
				|| DocStatus.equals(DocumentEngine.STATUS_Invalid))
			{
				options[index++] = DocumentEngine.ACTION_Prepare;
				options[index++] = DocumentEngine.ACTION_Close;
				//	Draft Sales Order Quote/Proposal - Process
				if ("Y".equals(IsSOTrx)
					&& ("OB".equals(OrderType) || "ON".equals(OrderType)))
					DocAction = DocumentEngine.ACTION_Prepare;
			}
			//	Complete                    ..  CO
			else if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_ReActivate;
			}
			else if (DocStatus.equals(DocumentEngine.STATUS_WaitingPayment))
			{
				options[index++] = DocumentEngine.ACTION_ReActivate;
				options[index++] = DocumentEngine.ACTION_Close;
			}
		}
		/********************
		 *  Shipment
		 */
		else if (m_AD_Table_ID == MInOut.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  Invoice
		 */
		else if (m_AD_Table_ID == MInvoice.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  Payment
		 */
		else if (m_AD_Table_ID == MPayment.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  GL Journal
		 */
		else if (m_AD_Table_ID == MJournal.Table_ID || m_AD_Table_ID == MJournalBatch.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
				options[index++] = DocumentEngine.ACTION_Reverse_Accrual;
			}
		}
		/********************
		 *  Allocation
		 */
		else if (m_AD_Table_ID == MAllocationHdr.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  Bank Statement
		 */
		else if (m_AD_Table_ID == MBankStatement.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
			}
		}
		/********************
		 *  Inventory Movement, Physical Inventory
		 */
		else if (m_AD_Table_ID == MMovement.Table_ID
			|| m_AD_Table_ID == MInventory.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}


		/**
		 *	Fill actionCombo
		 */
		for (int i = 0; i < index; i++)
		{
			//	Serach for option and add it
			boolean added = false;
			for (int j = 0; j < s_value.length && !added; j++)
				if (options[i].equals(s_value[j]))
				{
					actionCombo.addItem(s_name[j]);
					added = true;
				}
		}

		//	setDefault
		if (DocAction.equals("--"))		//	If None, suggest closing
			DocAction = DocumentEngine.ACTION_Close;
		String defaultV = "";
		for (int i = 0; i < s_value.length && defaultV.equals(""); i++)
			if (DocAction.equals(s_value[i]))
				defaultV = s_name[i];
		if (!defaultV.equals(""))
			actionCombo.setSelectedItem(defaultV);
	}	//	dynInit

	/**
	 * 	Check Status Change
	 *	@param TableName table name
	 *	@param Record_ID record
	 *	@param DocStatus current doc status
	 *	@return true if status not changed
	 */
	private boolean checkStatus (String TableName, int Record_ID, String DocStatus)
	{
		String sql = "SELECT 2 FROM " + TableName 
			+ " WHERE " + TableName + "_ID=" + Record_ID
			+ " AND DocStatus='" + DocStatus + "'";
		int result = DB.getSQLValue(null, sql);
		return result == 2;
	}	//	checkStatusChange

	
	/**
	 *	Number of options available (to decide to display it)
	 *  @return item count
	 */
	public int getNumberOfOptions()
	{
		return actionCombo.getItemCount();
	}	//	getNumberOfOptions

	/**
	 *	Should the process be started?
	 *  @return OK pressed
	 */
	public boolean isStartProcess()
	{
		return m_OKpressed;
	}	//	isStartProcess

	/**
	 *	Should the process be started in batch?
	 *  @return batch
	 */
	public boolean isBatch()
	{
		return m_batch;
	}	//	IsBatch

	/**
	 *	Fill Vector with DocAction Ref_List(135) values
	 */
	private void readReference()
	{
		String sql;
		if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
			sql = "SELECT Value, Name, Description FROM AD_Ref_List "
				+ "WHERE AD_Reference_ID=135 ORDER BY Name";
		else
			sql = "SELECT l.Value, t.Name, t.Description "
				+ "FROM AD_Ref_List l, AD_Ref_List_Trl t "
				+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID"
				+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
				+ " AND l.AD_Reference_ID=135 ORDER BY t.Name";

		ArrayList<String> v_value = new ArrayList<String>();
		ArrayList<String> v_name = new ArrayList<String>();
		ArrayList<String> v_description = new ArrayList<String>();
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				if (description == null)
					description = "";
				//
				v_value.add(value);
				v_name.add(name);
				v_description.add(description);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}

		//	convert to arrays
		int size = v_value.size();
		s_value = new String[size];
		s_name = new String[size];
		s_description = new String[size];
		for (int i = 0; i < size; i++)
		{
			s_value[i] = (String)v_value.get(i);
			s_name[i] = (String)v_name.get(i);
			s_description[i] = (String)v_description.get(i);
		}
	}	//	readReference

	
	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		m_batch = e.getSource() == batchButton;
		if (m_batch || e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (save())
			{
				dispose();
				m_OKpressed = true;
				return;
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			return;
		}
		else if (e.getSource() != actionCombo)
			return;

		/**
		 *	ActionCombo: display the description for the selection
		 */
		int index = getSelectedIndex();
		//	Display descriprion
		if (index != -1)
		{
			message.setText(s_description[index]);
		//	log.finer("DocAction=" + s_name[index] + " - " + s_value[index]);
		}
	}	//	actionPerformed


	/**
	 *	Get index of selected choice
	 *  @return index or -a
	 */
	private int getSelectedIndex()
	{
		int index = -1;

		//	get Selection
		String sel = (String)actionCombo.getSelectedItem();
		if (sel == null)
			return index;

		//	find it in vector
		for (int i = 0; i < s_name.length && index == -1; i++)
			if (sel.equals(s_name[i]))
				index = i;
		//
		return index;
	}	//	getSelectedIndex

	/**
	 *	Save to Database
	 *  @return true if saved to Tab
	 */
	private boolean save()
	{
		int index = getSelectedIndex();
		if (index == -1)
			return false;

		//	Save Selection
		log.config("DocAction=" + s_value[index]);
		m_mTab.setValue("DocAction", s_value[index]);
		return true;
	}	//	save

}	//	VDocAction
