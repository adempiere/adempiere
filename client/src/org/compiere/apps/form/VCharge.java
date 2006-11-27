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
package org.compiere.apps.form;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Create Charge from Accounts
 *
 *  @author Jorg Janke
 *  @version $Id: VCharge.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class VCharge extends CPanel
	implements FormPanel, ActionListener
{
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
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
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init


	/**	Window No			*/
	private int         m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 	m_frame;

	/** Account Element     */
	private int         m_C_Element_ID = 0;
	/** AccountSchema       */
	private int         m_C_AcctSchema_ID = 0;
	/** Default Charge Tax Category */
	private int         m_C_TaxCategory_ID = 0;
	private int         m_AD_Client_ID = 0;
	private int         m_AD_Org_ID = 0;
	private int         m_CreatedBy = 0;
	private MAcctSchema  m_acctSchema = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VCharge.class);
	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel newPanel = new CPanel();
	private TitledBorder newBorder;
	private GridBagLayout newLayout = new GridBagLayout();
	private JLabel valueLabel = new JLabel();
	private JTextField valueField = new JTextField();
	private JCheckBox isExpense = new JCheckBox();
	private JLabel nameLabel = new JLabel();
	private JTextField nameField = new JTextField();
	private JButton newButton = new JButton();
	private CPanel accountPanel = new CPanel();
	private TitledBorder accountBorder;
	private BorderLayout accountLayout = new BorderLayout();
	private CPanel accountOKPanel = new CPanel();
	private JButton accountButton = new JButton();
	private FlowLayout accountOKLayout = new FlowLayout();
	private JScrollPane dataPane = new JScrollPane();
	private MiniTable dataTable = new MiniTable();
	private ConfirmPanel confirmPanel = new ConfirmPanel();

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		newBorder = new TitledBorder("");
		accountBorder = new TitledBorder("");
		mainPanel.setLayout(mainLayout);
		newPanel.setBorder(newBorder);
		newPanel.setLayout(newLayout);
		newBorder.setTitle(Msg.getMsg(Env.getCtx(), "ChargeNewAccount"));
		valueLabel.setText(Msg.translate(Env.getCtx(), "Value"));
		isExpense.setSelected(true);
		isExpense.setText(Msg.getMsg(Env.getCtx(), "Expense"));
		nameLabel.setText(Msg.translate(Env.getCtx(), "Name"));
		nameField.setColumns(20);
		valueField.setColumns(10);
		newButton.setText(Msg.getMsg(Env.getCtx(), "Create"));
		newButton.addActionListener(this);
		accountPanel.setBorder(accountBorder);
		accountPanel.setLayout(accountLayout);
		accountBorder.setTitle(Msg.getMsg(Env.getCtx(), "ChargeFromAccount"));
		accountButton.setText(Msg.getMsg(Env.getCtx(), "Create"));
		accountButton.addActionListener(this);
		accountOKPanel.setLayout(accountOKLayout);
		accountOKLayout.setAlignment(FlowLayout.RIGHT);
		confirmPanel.addActionListener(this);
		//
		mainPanel.add(newPanel, BorderLayout.NORTH);
		newPanel.add(valueLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		newPanel.add(valueField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		newPanel.add(nameLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		newPanel.add(nameField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		newPanel.add(isExpense, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		newPanel.add(newButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		mainPanel.add(accountPanel, BorderLayout.CENTER);
		accountPanel.add(accountOKPanel, BorderLayout.SOUTH);
		accountOKPanel.add(accountButton, null);
		accountPanel.add(dataPane, BorderLayout.CENTER);
		dataPane.getViewport().add(dataTable, null);
	}   //  jbInit

	/**
	 *  Dynamic Init
	 *  - Get defaults for primary AcctSchema
	 *  - Create Table with Accounts
	 */
	private void dynInit()
	{
		m_C_AcctSchema_ID = Env.getContextAsInt(Env.getCtx(), "$C_AcctSchema_ID");
		//  get Element
		String sql = "SELECT C_Element_ID FROM C_AcctSchema_Element "
			+ "WHERE ElementType='AC' AND C_AcctSchema_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_C_AcctSchema_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				m_C_Element_ID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		if (m_C_Element_ID == 0)
			return;


		//  Table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		sql = "SELECT C_ElementValue_ID,Value, Name, AccountType "
			+ "FROM C_ElementValue "
			+ "WHERE AccountType IN ('R','E')"
			+ " AND IsSummary='N'"
			+ " AND C_Element_ID=? "
			+ "ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_C_Element_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(4);
				line.add(new Boolean(false));       //  0-Selection
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				line.add(pp);                       //  1-Value
				line.add(rs.getString(3));          //  2-Name
				boolean isExpenseType = rs.getString(4).equals("E");
				line.add(new Boolean(isExpenseType));   //  3-Expense
				data.add(line);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		//  Header Info
		Vector<String> columnNames = new Vector<String>(4);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Value"));
		columnNames.add(Msg.translate(Env.getCtx(), "Name"));
		columnNames.add(Msg.getMsg(Env.getCtx(), "Expense"));

		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		dataTable.setModel(model);
		//
		dataTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		dataTable.setColumnClass(1, String.class, true);        //  1-Value
		dataTable.setColumnClass(2, String.class, true);        //  2-Name
		dataTable.setColumnClass(3, Boolean.class, true);       //  3-Expense
		//  Table UI
		dataTable.autoSize();

		//  Other Defaults
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		m_AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());
		m_CreatedBy = Env.getAD_User_ID(Env.getCtx());

		//  TaxCategory
		sql = "SELECT C_TaxCategory_ID FROM C_TaxCategory "
			+ "WHERE IsDefault='Y' AND AD_Client_ID=?";
		m_C_TaxCategory_ID = 0;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				m_C_TaxCategory_ID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
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

	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand());
		//
		if (e.getActionCommand().equals(ConfirmPanel.A_OK) || m_C_Element_ID == 0)
			dispose();
		//  new Account
		else if (e.getSource().equals(newButton))
			createNew();
		else if (e.getSource().equals(accountButton))
			createAccount();
	}   //  actionPerformed

	/**
	 *  Create new Account and Charge
	 */
	private void createNew()
	{
		log.config("");
		//  Get Input
		String value = valueField.getText();
		if (value.length() == 0)
		{
			valueField.setBackground(AdempierePLAF.getFieldBackground_Error());
			return;
		}
		String name = nameField.getText();
		if (name.length() == 0)
		{
			nameField.setBackground(AdempierePLAF.getFieldBackground_Error());
			return;
		}
		//  Create Element
		int C_ElementValue_ID = create_ElementValue (value, name, isExpense.isSelected());
		if (C_ElementValue_ID == 0)
		{
			ADialog.error(m_WindowNo, this, "ChargeNotCreated", name);
			return;
		}
		//  Create Charge
		int C_Charge_ID = create_Charge(name, C_ElementValue_ID);
		if (C_Charge_ID == 0)
		{
			ADialog.error(m_WindowNo, this, "ChargeNotCreated", name);
			return;
		}
		ADialog.info(m_WindowNo, this, "ChargeCreated", name);
	}   //  createNew

	/**
	 *  Create Charges from Accounts
	 */
	private void createAccount()
	{
		log.config("");
		//
		StringBuffer listCreated = new StringBuffer();
		StringBuffer listRejected = new StringBuffer();
		//
		TableModel model = dataTable.getModel();
		int rows = model.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)model.getValueAt(i, 0)).booleanValue())
			{
				KeyNamePair pp = (KeyNamePair)model.getValueAt(i, 1);
				int C_ElementValue_ID = pp.getKey();
				String name = (String)model.getValueAt(i, 2);
				//
				int C_Charge_ID = create_Charge(name, C_ElementValue_ID);
				if (C_Charge_ID == 0)
				{
					if (listRejected.length() > 0)
						listRejected.append(", ");
					listRejected.append(name);
				}
				else
				{
					if (listCreated.length() > 0)
						listCreated.append(", ");
					listCreated.append(name);
				}
				//  reset selection
				model.setValueAt(new Boolean(false), i, 0);
			}
		}
		if (listCreated.length() > 0)
			ADialog.info(m_WindowNo, this, "ChargeCreated", listCreated.toString());
		if (listRejected.length() > 0)
			ADialog.error(m_WindowNo, this, "ChargeNotCreated", listRejected.toString());
	}   //  createAccount

	
	/**************************************************************************
	 *  Create ElementValue for primary AcctSchema
	 *  @param value value
	 *  @param name name
	 *  @param isExpenseType is expense
	 *  @return element value
	 */
	private int create_ElementValue (String value, String name, boolean isExpenseType)
	{
		log.config(name);
		//
		MElementValue ev = new MElementValue(Env.getCtx(), value, name, null,
			isExpenseType ? MElementValue.ACCOUNTTYPE_Expense : MElementValue.ACCOUNTTYPE_Revenue, 
				MElementValue.ACCOUNTSIGN_Natural,
				false, false, null);
		ev.setAD_Org_ID(m_AD_Org_ID);
		if (!ev.save())
			log.log(Level.WARNING, "C_ElementValue_ID not created");
		return ev.getC_ElementValue_ID();
	}   //  create_ElementValue

	/**
	 *  Create Charge and account entries for primary AcctSchema.
	 *
	 *	@param name name
	 * 	@param C_ElementValue_ID element value
	 * 	@return charge
	 */
	private int create_Charge (String name, int C_ElementValue_ID)
	{
		log.config(name + " - ");
		//
		MCharge charge = new MCharge(Env.getCtx(), 0, null);
		charge.setName(name);
		charge.setC_TaxCategory_ID(m_C_TaxCategory_ID);
		if (!charge.save())
		{
			log.log(Level.SEVERE, name + " not created");
			return 0;
		}

		//  Get AcctSchama
		if (m_acctSchema == null)
			m_acctSchema = new MAcctSchema(Env.getCtx(), m_C_AcctSchema_ID, null);
		if (m_acctSchema == null || m_acctSchema.getC_AcctSchema_ID() == 0)
			return 0;

		//	Target Account
		MAccount defaultAcct = MAccount.getDefault(m_acctSchema, true);	//	optional null
		MAccount acct = MAccount.get(Env.getCtx(), 
			charge.getAD_Client_ID(), charge.getAD_Org_ID(), 
			m_acctSchema.getC_AcctSchema_ID(), 
			C_ElementValue_ID, defaultAcct.getC_SubAcct_ID(),
			defaultAcct.getM_Product_ID(), defaultAcct.getC_BPartner_ID(), defaultAcct.getAD_OrgTrx_ID(), 
			defaultAcct.getC_LocFrom_ID(), defaultAcct.getC_LocTo_ID(), defaultAcct.getC_SalesRegion_ID(), 
			defaultAcct.getC_Project_ID(), defaultAcct.getC_Campaign_ID(), defaultAcct.getC_Activity_ID(),
			defaultAcct.getUser1_ID(), defaultAcct.getUser2_ID(), 
			defaultAcct.getUserElement1_ID(), defaultAcct.getUserElement2_ID()); 
		if (acct == null)
			return 0;

		//  Update Accounts
		StringBuffer sql = new StringBuffer("UPDATE C_Charge_Acct ");
		sql.append("SET CH_Expense_Acct=").append(acct.getC_ValidCombination_ID());
		sql.append(", CH_Revenue_Acct=").append(acct.getC_ValidCombination_ID());
		sql.append(" WHERE C_Charge_ID=").append(charge.getC_Charge_ID());
		sql.append(" AND C_AcctSchema_ID=").append(m_C_AcctSchema_ID);
		//
		int no = DB.executeUpdate(sql.toString(), null);
		if (no != 1)
			log.log(Level.SEVERE, "Update #" + no + "\n" + sql.toString());
		//
		return charge.getC_Charge_ID();
	}   //  create_Charge

}   //  VCharge
