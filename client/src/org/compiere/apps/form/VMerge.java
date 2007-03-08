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
import java.util.logging.*;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Merge Dialog.
 * 	Restriction - fails for Accounting
 *
 *	@author Jorg Janke
 *	@version $Id: VMerge.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VMerge extends CPanel 
	implements FormPanel, ActionListener
{
	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;
	/**	Total Count			*/
	private int				m_totalCount = 0;
	/** Error Log			*/
	private StringBuffer	m_errorLog = new StringBuffer();
	/**	Connection			*/
	//private Connection		m_con = null;
	private Trx 			m_trx = null;         
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VMerge.class);

	static private String	AD_ORG_ID = "AD_Org_ID";
	static private String	C_BPARTNER_ID = "C_BPartner_ID";
	static private String	AD_USER_ID = "AD_User_ID";
	static private String	M_PRODUCT_ID = "M_Product_ID";

	/** Tables to delete (not update) for AD_Org	*/
	static private String[]	s_delete_Org = new String[]
		{"AD_OrgInfo"};
	/** Tables to delete (not update) for AD_User	*/
	static private String[]	s_delete_User = new String[]
		{"AD_User_Roles"};
	/** Tables to delete (not update) for C_BPartner	*/
	static private String[]	s_delete_BPartner = new String[]
		{"C_BP_Employee_Acct", "C_BP_Vendor_Acct", "C_BP_Customer_Acct", 
		"T_Aging"};
	/** Tables to delete (not update) for M_Product		*/
	static private String[]	s_delete_Product = new String[]
		{"M_Product_PO", "M_Replenish", "T_Replenish", 
		"M_ProductPrice", "M_Product_Costing",
		"M_Product_Trl", "M_Product_Acct"};		//	M_Storage

	private String[]	m_columnName = null;
	private CLabel[]	m_label = null;
	private VLookup[]	m_from = null;
	private VLookup[]	m_to = null;
	private String[]	m_deleteTables = null;


	private BorderLayout mainLayout = new BorderLayout();
	private CPanel CenterPanel = new CPanel();
	private GridLayout centerLayout = new GridLayout();
	private CLabel mergeFromLabel = new CLabel();
	private CLabel mergeToLabel = new CLabel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info( "VMerge.init - WinNo=" + m_WindowNo);
		try
		{
			preInit();
			jbInit ();
			frame.getContentPane().add(this, BorderLayout.CENTER);
		//	frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	init

	/**
	 * 	Pre Init
	 */
	private void preInit()
	{
		int count = 4;			//	** Update **
		m_columnName = new String[count];
		m_label = new CLabel[count];
		m_from = new VLookup[count];
		m_to = new VLookup[count];

		//	** Update **
		preInit (0, 2163, DisplayType.TableDir, AD_ORG_ID);		//	C_Order.AD_Org_ID
		preInit (1, 2762, DisplayType.Search, C_BPARTNER_ID);	//	C_Order.C_BPartner_ID
		preInit (2, 971, DisplayType.Search, AD_USER_ID);		//	AD_User_Roles.AD_User_ID
		preInit (3, 2221, DisplayType.Search, M_PRODUCT_ID);	//	C_OrderLine.M_Product_ID
	}	//	preInit

	/**
	 * 	Pre Init Line
	 *	@param index index
	 *	@param AD_Column_ID id
	 *	@param displayType display type
	 *	@param ColumnName column name
	 */
	private void preInit (int index, int AD_Column_ID, int displayType, String ColumnName)
	{
		m_columnName[index] = ColumnName;
		String what = Msg.translate(Env.getCtx(), ColumnName);
		m_label[index] = new CLabel(what);
		m_from[index] = new VLookup (ColumnName, false, false, true,
			MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, AD_Column_ID, displayType));
		m_to[index] = new VLookup (ColumnName, false, false, true,
			MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, AD_Column_ID, displayType));
	}	//	preInit

	/**
	 * 	Static init
	 * 	@throws java.lang.Exception
	 */
	void jbInit () throws Exception
	{
		this.setLayout (mainLayout);
		mainLayout.setHgap (5);
		mainLayout.setVgap (5);
		//
		this.add (confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
		//
		centerLayout.setHgap (5);
		centerLayout.setVgap (5);
		centerLayout.setColumns (3);
		centerLayout.setRows (m_label.length+1);
		//
		CenterPanel.setLayout (centerLayout);
		this.add (CenterPanel, BorderLayout.CENTER);
		CenterPanel.add (new CLabel(), null);
		CenterPanel.add (mergeFromLabel, null);
		CenterPanel.add (mergeToLabel, null);
		//
		Font heading = mergeFromLabel.getFont();
		heading = new Font(heading.getName(), Font.BOLD, heading.getSize());
		mergeFromLabel.setFont (heading);
		mergeFromLabel.setRequestFocusEnabled (false);
		mergeFromLabel.setText (Msg.getMsg(Env.getCtx(), "MergeFrom"));
		mergeToLabel.setFont (heading);
		mergeToLabel.setText (Msg.getMsg(Env.getCtx(), "MergeTo"));
		//
		for (int i = 0; i < m_label.length; i++)
		{
			CenterPanel.add (m_label[i], null);
			CenterPanel.add (m_from[i], null);
			CenterPanel.add (m_to[i], null);
		}
	}	//	jbInit

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
	 *  Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			return;
		}
		//
		String columnName = null;
		String from_Info = null;
		String to_Info = null;
		int from_ID = 0;
		int to_ID = 0;
		//	get first merge pair
		for (int i = 0; (i < m_columnName.length && from_ID == 0 && to_ID == 0); i++)
		{
			Object value = m_from[i].getValue();
			if (value != null)
			{
				if (value instanceof Integer)
					from_ID = ((Integer)value).intValue();
				else
					continue;
				value = m_to[i].getValue();
				if (value != null && value instanceof Integer)
					to_ID = ((Integer)value).intValue();
				else
					from_ID = 0;
				if (from_ID != 0)
				{
					columnName = m_columnName[i];
					from_Info = m_from[i].getDisplay ();
					to_Info = m_to[i].getDisplay ();
				}
			}
		}	//	get first merge pair

		if (from_ID == 0 || from_ID == to_ID)
			return;

		String msg = Msg.getMsg(Env.getCtx(), "MergeFrom") + " = " + from_Info
			+ "\n" + Msg.getMsg(Env.getCtx(), "MergeTo") + " = " + to_Info;
		if (!ADialog.ask(m_WindowNo, this, "MergeQuestion", msg))
			return;

		//	** Update **
		if (columnName.equals(AD_ORG_ID))
			m_deleteTables = s_delete_Org;
		else if (columnName.equals(AD_USER_ID))
			m_deleteTables = s_delete_User;
		else if (columnName.equals(C_BPARTNER_ID))
			m_deleteTables = s_delete_BPartner;
		else if (columnName.equals(M_PRODUCT_ID))
			m_deleteTables = s_delete_Product;

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		confirmPanel.getOKButton().setEnabled(false);
		//
		boolean success = merge (columnName, from_ID, to_ID);
		postMerge(columnName, to_ID);
		//
		confirmPanel.getOKButton().setEnabled(true);
		setCursor(Cursor.getDefaultCursor());
		//
		if (success)
		{
			ADialog.info (m_WindowNo, this, "MergeSuccess", 
				msg + " #" + m_totalCount);
		}
		else
		{
			ADialog.error(m_WindowNo, this, "MergeError", 
				m_errorLog.toString());
			return;
		}
		dispose();
	}   //  actionPerformed


	/**
	 * 	Merge.
	 *	@param ColumnName column
	 *	@param from_ID from
	 *	@param to_ID to
	 *	@return true if merged
	 */
	private boolean merge (String ColumnName, int from_ID, int to_ID)
	{
		String TableName = ColumnName.substring(0, ColumnName.length()-3);
		log.config(ColumnName
			+ " - From=" + from_ID + ",To=" + to_ID);

		boolean success = true;
		m_totalCount = 0;
		m_errorLog = new StringBuffer();
		String sql = "SELECT t.TableName, c.ColumnName "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "WHERE t.IsView='N'"
				+ " AND t.TableName NOT IN ('C_TaxDeclarationAcct')"
				+ " AND ("
				+ "(c.ColumnName=? AND c.IsKey='N')"		//	#1 - direct
			+ " OR "
				+ "c.AD_Reference_Value_ID IN "				//	Table Reference
					+ "(SELECT rt.AD_Reference_ID FROM AD_Ref_Table rt"
					+ " INNER JOIN AD_Column cc ON (rt.AD_Table_ID=cc.AD_Table_ID AND rt.AD_Key=cc.AD_Column_ID) "
					+ "WHERE cc.IsKey='Y' AND cc.ColumnName=?)"	//	#2
			+ ") "
			+ "ORDER BY t.LoadSeq DESC";
		PreparedStatement pstmt = null;
		
		try
		{
			
			m_trx = Trx.get(Trx.createTrxName("merge"), true);
			//
			pstmt = DB.prepareStatement(sql, m_trx.createTrxName());
			pstmt.setString(1, ColumnName);
			pstmt.setString(2, ColumnName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String tName = rs.getString(1);
				String cName = rs.getString(2);
				if (!TableName.equals(tName))	//	to be sure - sql should prevent it
				{
					int count = mergeTable (tName, cName, from_ID, to_ID);
					if (count < 0)
						success = false;
					else
						m_totalCount += count;
				}
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			//
			log.config("Success=" + success
				+ " - " + ColumnName + " - From=" + from_ID + ",To=" + to_ID);
			if (success)
			{
				sql = "DELETE " + TableName + " WHERE " + ColumnName + "=" + from_ID;
				


				
				if ( DB.executeUpdate(sql, m_trx.getTrxName()) < 0 )
				{
					m_errorLog.append(Env.NL).append("DELETE ").append(TableName)
					.append(" - ");
				    success = false;
					log.config(m_errorLog.toString());
					m_trx.rollback();
					return false;
				}
				
			}
			//
			if (success)
				m_trx.commit();
			else
				m_trx.rollback();
			
			m_trx.close();

		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, ColumnName, ex);
		}
		//	Cleanup
		try
		{
			if (pstmt != null)
				pstmt.close();

		}
		catch (Exception ex)
		{
		}
		pstmt = null;
		return success;
	}	//	merge


	/**
	 * 	Merge Table
	 * 	@param TableName table
	 * 	@param ColumnName column
	 * 	@param from_ID from
	 * 	@param to_ID to
	 * 	@return -1 for error or number of changes
	 */
	private int mergeTable (String TableName, String ColumnName, int from_ID, int to_ID)
	{
		log.fine(TableName + "." + ColumnName + " - From=" + from_ID + ",To=" + to_ID);
		String sql = "UPDATE " + TableName
			+ " SET " + ColumnName + "=" + to_ID
			+ " WHERE " + ColumnName + "=" + from_ID;
		boolean delete = false;
		for (int i = 0; i < m_deleteTables.length; i++)
		{
			if (m_deleteTables[i].equals(TableName))
			{
				delete = true;
				sql = "DELETE " + TableName + " WHERE " + ColumnName + "=" + from_ID;
			}
		}

		int count = DB.executeUpdate(sql, m_trx.getTrxName());
        
		
		if (  count < 0 )
		{
					
			count = -1;
			m_errorLog.append(Env.NL)
				.append(delete ? "DELETE " : "UPDATE ")
				.append(TableName).append(" - ")
				.append(" - ").append(sql);
			log.config(m_errorLog.toString());
			m_trx.rollback();
		
		}
		log.fine(count
				+ (delete ? " -Delete- " : " -Update- ") + TableName);
		
		
		return count;
	}	//	mergeTable

	/**
	 * 	Post Merge
	 *	@param ColumnName column name
	 *	@param to_ID ID
	 */
	private void postMerge (String ColumnName, int to_ID)
	{
		if (ColumnName.equals(AD_ORG_ID))
		{
			
		}
		else if (ColumnName.equals(AD_USER_ID))
		{
			
		}
		else if (ColumnName.equals(C_BPARTNER_ID))
		{
			MBPartner bp = new MBPartner (Env.getCtx(), to_ID, null);
			if (bp.get_ID() != 0)
			{
				MPayment[] payments = MPayment.getOfBPartner(Env.getCtx(), bp.getC_BPartner_ID(), null);
				for (int i = 0; i < payments.length; i++) 
				{
					MPayment payment = payments[i];
					if (payment.testAllocation())
						payment.save();
				}
				MInvoice[] invoices = MInvoice.getOfBPartner(Env.getCtx(), bp.getC_BPartner_ID(), null);
				for (int i = 0; i < invoices.length; i++) 
				{
					MInvoice invoice = invoices[i];
					if (invoice.testAllocation())
						invoice.save();
				}
				bp.setTotalOpenBalance();
				bp.setActualLifeTimeValue();
				bp.save();
			}
		}
		else if (ColumnName.equals(M_PRODUCT_ID))
		{
			
		}
	}	//	postMerge

	
}	//	VMerge
