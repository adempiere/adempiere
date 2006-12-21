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
package org.compiere.process;

import java.math.*;
import java.sql.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.report.*;
import org.compiere.util.*;

/**
 *	Accounting Fact Reset
 *	
 *  @author Jorg Janke
 *  @version $Id: FactAcctReset.java,v 1.5 2006/09/21 21:05:02 jjanke Exp $
 */
public class FactAcctReset extends SvrProcess
{
	/**	Client Parameter		*/
	private int		p_AD_Client_ID = 0;
	/** Table Parameter			*/
	private int		p_AD_Table_ID = 0;
	/**	Delete Parameter		*/
	private boolean	p_DeletePosting = false;
	
	private int		m_countReset = 0;
	private int		m_countDelete = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Table_ID"))
				p_AD_Table_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeletePosting"))
				p_DeletePosting = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perrform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("AD_Client_ID=" + p_AD_Client_ID 
			+ ", AD_Table_ID=" + p_AD_Table_ID + ", DeletePosting=" + p_DeletePosting);
		//	List of Tables with Accounting Consequences
		String sql = "SELECT AD_Table_ID, TableName "
			+ "FROM AD_Table t "
			+ "WHERE t.IsView='N'";
		if (p_AD_Table_ID > 0)
			sql += " AND t.AD_Table_ID=" + p_AD_Table_ID;
		sql += " AND EXISTS (SELECT * FROM AD_Column c "
				+ "WHERE t.AD_Table_ID=c.AD_Table_ID AND c.ColumnName='Posted' AND c.IsActive='Y')";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int AD_Table_ID = rs.getInt(1);
				String TableName = rs.getString(2);
				if (p_DeletePosting)
					delete (TableName, AD_Table_ID);
				else
					reset (TableName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//	Balances
		if (p_DeletePosting)
			FinBalance.updateBalanceClient(getCtx(), p_AD_Client_ID);		//	delete
		//
		return "@Updated@ = " + m_countReset + ", @Deleted@ = " + m_countDelete;
	}	//	doIt

	/**
	 * 	Reset Accounting Table and update count
	 *	@param TableName table
	 */
	private void reset (String TableName)
	{
		String sql = "UPDATE " + TableName
			+ " SET Processing='N' WHERE AD_Client_ID=" + p_AD_Client_ID
			+ " AND (Processing<>'N' OR Processing IS NULL)";
		int unlocked = DB.executeUpdate(sql, get_TrxName());
		//
		sql = "UPDATE " + TableName
			+ " SET Posted='N' WHERE AD_Client_ID=" + p_AD_Client_ID
			+ " AND (Posted NOT IN ('Y','N') OR Posted IS NULL) AND Processed='Y'";
		int invalid = DB.executeUpdate(sql, get_TrxName());
		//
		if (unlocked + invalid != 0)
			log.fine(TableName + " - Unlocked=" + unlocked + " - Invalid=" + invalid);
		m_countReset += unlocked + invalid; 
	}	//	reset

	/**
	 * 	Delete Accounting Table where period status is open and update count.
	 * 	@param TableName table name
	 *	@param AD_Table_ID table
	 */
	private void delete (String TableName, int AD_Table_ID)
	{
		reset(TableName);
		m_countReset = 0;
		//
		String docBaseType = null;
		if (AD_Table_ID == MInvoice.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_APInvoice 
				+ "','" + MPeriodControl.DOCBASETYPE_APCreditMemo
				+ "','" + MPeriodControl.DOCBASETYPE_ARInvoice
				+ "','" + MPeriodControl.DOCBASETYPE_ARCreditMemo
				+ "','" + MPeriodControl.DOCBASETYPE_ARProFormaInvoice + "')";
		else if (AD_Table_ID == MInOut.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_MaterialDelivery
				+ "','" + MPeriodControl.DOCBASETYPE_MaterialReceipt + "')";
		else if (AD_Table_ID == MPayment.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_APPayment
				+ "','" + MPeriodControl.DOCBASETYPE_ARReceipt + "')";
		else if (AD_Table_ID == MOrder.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_SalesOrder
				+ "','" + MPeriodControl.DOCBASETYPE_PurchaseOrder + "')";
		else if (AD_Table_ID == MProjectIssue.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ProjectIssue + "'";
		else if (AD_Table_ID == MBankStatement.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_BankStatement + "'";
		else if (AD_Table_ID == MCash.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_CashJournal + "'";
		else if (AD_Table_ID == MAllocationHdr.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_PaymentAllocation + "'";
		else if (AD_Table_ID == MJournal.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_GLJournal + "'";
	//	else if (AD_Table_ID == M.Table_ID)
	//		docBaseType = "= '" + MPeriodControl.DOCBASETYPE_GLDocument + "'";
		else if (AD_Table_ID == MMovement.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialMovement + "'";
		else if (AD_Table_ID == MRequisition.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_PurchaseRequisition + "'";
		else if (AD_Table_ID == MInventory.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialPhysicalInventory + "'";
		else if (AD_Table_ID == X_M_Production.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialProduction + "'";
		else if (AD_Table_ID == MMatchInv.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchInvoice + "'";
		else if (AD_Table_ID == MMatchPO.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchPO + "'";
		//
		if (docBaseType == null)
		{
			String s = TableName + ": Unknown DocBaseType";
			log.severe(s);
			addLog(s);
			docBaseType = "";
			return;
		}
		else
			docBaseType = " AND pc.DocBaseType " + docBaseType;
		
		//	Doc
		String sql1 = "UPDATE " + TableName + " doc"
			+ " SET Posted='N', Processing='N' "
			+ "WHERE AD_Client_ID=" + p_AD_Client_ID
			+ " AND (Posted<>'N' OR Posted IS NULL OR Processing<>'N' OR Processing IS NULL)"
			+ " AND EXISTS (SELECT * FROM C_PeriodControl pc"
				+ " INNER JOIN Fact_Acct fact ON (fact.C_Period_ID=pc.C_Period_ID) "
				+ "WHERE pc.PeriodStatus = 'O'" + docBaseType
				+ " AND fact.AD_Table_ID=" + AD_Table_ID
				+ " AND fact.Record_ID=doc." + TableName + "_ID)";
		int reset = DB.executeUpdate(sql1, get_TrxName()); 
		//	Fact
		String sql2 = "DELETE Fact_Acct fact "
			+ "WHERE AD_Client_ID=" + p_AD_Client_ID
			+ " AND AD_Table_ID=" + AD_Table_ID
			+ " AND EXISTS (SELECT * FROM C_PeriodControl pc "
				+ "WHERE pc.PeriodStatus = 'O'" + docBaseType
				+ " AND fact.C_Period_ID=pc.C_Period_ID)";
		int deleted = DB.executeUpdate(sql2, get_TrxName());
		//
		log.info(TableName + "(" + AD_Table_ID + ") - Reset=" + reset + " - Deleted=" + deleted);
		String s = TableName + " - Reset=" + reset + " - Deleted=" + deleted;
		addLog(s);
		if (reset == 0)
			log.finest(sql1);
		if (deleted == 0)
			log.finest(sql2);
		//
		m_countReset += reset;
		m_countDelete += deleted;
	}	//	delete

}	//	FactAcctReset
