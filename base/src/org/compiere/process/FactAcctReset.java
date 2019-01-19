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
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MBankStatement;
import org.compiere.model.MCash;
import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MRequisition;
import org.compiere.model.X_A_Asset_Addition;
import org.compiere.model.X_A_Asset_Disposed;
import org.compiere.model.X_A_Depreciation_Entry;
import org.compiere.model.X_M_Production;
import org.compiere.model.X_M_ProductionBatch;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.model.X_DD_Order;
import org.eevolution.model.X_HR_Process;
import org.eevolution.model.X_PP_Cost_Collector;
import org.eevolution.model.X_PP_Order;

/**
 *	Accounting Fact Reset
 *	
 *  @author Jorg Janke
 *  @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 *  @see  [ 1249 ] Severe error the reset account not work when use range date</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1249">
 *
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
	private Timestamp p_DateAcct_From = null ;
	private Timestamp p_DateAcct_To = null;
	
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
			else if (name.equals("DateAcct"))
			{
				p_DateAcct_From = (Timestamp)para[i].getParameter();
				p_DateAcct_To = (Timestamp)para[i].getParameter_To();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perform process.
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
				Trx.run(trxName -> {
					if (p_DeletePosting)
						delete(TableName, AD_Table_ID, trxName);
					else
						reset(TableName, AD_Table_ID, trxName);
				});
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
		//
		return "@Updated@ = " + m_countReset + ", @Deleted@ = " + m_countDelete;
	}	//	doIt

	/**
	 * 	Reset Accounting Table and update count
	 * @param TableName table
	 * @param trxName
	 */
	private void reset(String TableName, int AD_Table_ID, String trxName)
	{
		MAcctSchema as = MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema();
		boolean autoPeriod = as != null && as.isAutoPeriodControl();
		Timestamp today = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);
		if (autoPeriod)
		{
			Timestamp temp = TimeUtil.addDays(today, - as.getPeriod_OpenHistory());
			if ( p_DateAcct_From == null || p_DateAcct_From.before(temp) ) {
				p_DateAcct_From = temp;
				log.info("DateAcct From set to: " + p_DateAcct_From);
			}
			temp = TimeUtil.addDays(today, as.getPeriod_OpenFuture());
			if ( p_DateAcct_To == null || p_DateAcct_To.after(temp) ) {
				p_DateAcct_To = temp;
				log.info("DateAcct To set to: " + p_DateAcct_To);
			}
		}

		String docBaseType = getDocumentBaseType(AD_Table_ID, TableName);
		String acctDate = getDateAcct(AD_Table_ID);
		StringBuilder resetUpdate = new StringBuilder();

		resetUpdate.append("UPDATE ").append(TableName).append(" SET Processing='N' WHERE AD_Client_ID=")
				 .append(p_AD_Client_ID).append(" AND (Processing<>'N' OR Processing IS NULL)");

		int unlocked = DB.executeUpdate(resetUpdate.toString(), trxName);

		resetUpdate = new StringBuilder();
		resetUpdate.append("UPDATE ").append(TableName)
				 .append(" SET Posted='N' WHERE AD_Client_ID=").append(p_AD_Client_ID)
				 .append(" AND (Posted NOT IN ('Y','N') OR Posted IS NULL) OR ");
		// Validate that document is posted
		resetUpdate.append("( Posted = 'Y' AND NOT EXISTS (SELECT 1 FROM Fact_Acct fa  WHERE AD_Table_ID=")
				.append(AD_Table_ID).append(" AND Record_ID=").append(TableName).append(".").append(TableName).append("_ID)");
		// Validate that period is open for this document and document type base
		resetUpdate.append(" AND EXISTS (SELECT 1 FROM C_AcctSchema ac ,C_PeriodControl pc , C_Period p WHERE ");
		resetUpdate.append(" p.C_Period_ID = pc.C_Period_ID AND ").append(acctDate).append(" >= p.StartDate AND ").append(acctDate).append(" <= p.EndDate ");
		if ( autoPeriod )
			resetUpdate.append(" AND ac.C_AcctSchema_ID=").append(as.getC_AcctSchema_ID()).append(" AND ac.AutoPeriodControl = 'Y' ")
					   .append(" AND pc.C_Period_ID = ac.C_Period_ID ")
					   .append(" AND DateAcct >= addDays(").append(acctDate).append(" , ac.Period_OpenHistory * -1) ")
					   .append(" AND DateAcct <= addDays(").append(acctDate).append(" , ac.Period_OpenFuture) ");

			resetUpdate.append(" AND pc.PeriodStatus = 'O' AND pc.DocBaseType ").append(docBaseType).append("))");

		int invalid = DB.executeUpdate(resetUpdate.toString(), trxName);
		//
		if (unlocked + invalid != 0)
			log.fine(TableName + " - Unlocked=" + unlocked + " - Invalid=" + invalid);
		m_countReset += unlocked + invalid; 
	}	//	reset

	/**
	 * 	Delete Accounting Table where period status is open and update count.
	 * @param TableName table name
	 *	@param AD_Table_ID table
	 * @param trxName
	 */
	private void delete(String TableName, int AD_Table_ID, String trxName)
	{
		Timestamp today = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);

		MAcctSchema as = MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema();
		boolean autoPeriod = as != null && as.isAutoPeriodControl();
		if (autoPeriod)
		{
			Timestamp temp = TimeUtil.addDays(today, - as.getPeriod_OpenHistory());
			if ( p_DateAcct_From == null || p_DateAcct_From.before(temp) ) {
				p_DateAcct_From = temp;
				log.info("DateAcct From set to: " + p_DateAcct_From);
			}
			temp = TimeUtil.addDays(today, as.getPeriod_OpenFuture());
			if ( p_DateAcct_To == null || p_DateAcct_To.after(temp) ) {
				p_DateAcct_To = temp;
				log.info("DateAcct To set to: " + p_DateAcct_To);
			}
		}

		reset(TableName, AD_Table_ID, trxName);
		m_countReset = 0;
		//
		String docBaseType = getDocumentBaseType(AD_Table_ID, TableName);
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
		String sql1 = "UPDATE " + TableName
			+ " SET Posted='N', Processing='N' "
			+ "WHERE AD_Client_ID=" + p_AD_Client_ID
			+ " AND (Posted<>'N' OR Posted IS NULL OR Processing<>'N' OR Processing IS NULL)"
			+ " AND EXISTS (SELECT 1 FROM C_PeriodControl pc"
			+ " INNER JOIN Fact_Acct fact ON (fact.C_Period_ID=pc.C_Period_ID) "
			+ " WHERE fact.AD_Table_ID=" + AD_Table_ID
			+ " AND fact.Record_ID=" + TableName + "." + TableName + "_ID";
		if ( !autoPeriod )
			sql1 += " AND pc.PeriodStatus = 'O'" + docBaseType;
		if (p_DateAcct_From != null)
			sql1 += " AND TRUNC(fact.DateAcct, 'DD') >= " + DB.TO_DATE(p_DateAcct_From);
		if (p_DateAcct_To != null)
			sql1 += " AND TRUNC(fact.DateAcct, 'DD') <= " + DB.TO_DATE(p_DateAcct_To);
		sql1 += ")";

		log.log(Level.FINE, sql1);

		int reset = DB.executeUpdate(sql1, trxName);
		//	Fact
		String sql2 = "DELETE Fact_Acct "
			+ "WHERE AD_Client_ID=" + p_AD_Client_ID
			+ " AND AD_Table_ID=" + AD_Table_ID;
		if ( !autoPeriod )
			sql2 += " AND EXISTS (SELECT 1 FROM C_PeriodControl pc "
				+ "WHERE pc.PeriodStatus = 'O'" + docBaseType
				+ " AND Fact_Acct.C_Period_ID=pc.C_Period_ID)";
		else
			sql2 += " AND EXISTS (SELECT 1 FROM C_PeriodControl pc "
				+ "WHERE Fact_Acct.C_Period_ID=pc.C_Period_ID)";
		if (p_DateAcct_From != null)
			sql2 += " AND TRUNC(Fact_Acct.DateAcct, 'DD') >= " + DB.TO_DATE(p_DateAcct_From);
		if (p_DateAcct_To != null)
			sql2 += " AND TRUNC(Fact_Acct.DateAcct, 'DD') <= " + DB.TO_DATE(p_DateAcct_To);

		log.log(Level.FINE, sql2);
		
		int deleted = DB.executeUpdate(sql2, get_TrxName());
		//
		log.info(TableName + "( ID : " +  AD_Table_ID + ") - Reset=" + reset + " - Deleted=" + deleted);
		String s = TableName + " - Reset=" + reset + " - Deleted=" + deleted;
		addLog(s);
		//
		m_countReset += reset;
		m_countDelete += deleted;
	}	//	delete

	public String getDateAcct(int tableId)
	{
		String docDateField = "DateAcct";
		if (tableId == MProjectIssue.Table_ID)
			docDateField = MProjectIssue.COLUMNNAME_MovementDate;
		else if (tableId == MBankStatement.Table_ID)
			docDateField = MBankStatement.COLUMNNAME_EftStatementDate;
		else if (tableId == MMovement.Table_ID)
			docDateField = MMovement.COLUMNNAME_MovementDate;
		else if (tableId == MRequisition.Table_ID)
			docDateField = MRequisition.COLUMNNAME_DateDoc;
		else if (tableId == MInventory.Table_ID)
			docDateField = MInventory.COLUMNNAME_MovementDate;
		else if (tableId == X_M_Production.Table_ID)
			docDateField = X_M_Production.COLUMNNAME_MovementDate;
		else if (tableId == MOrder.Table_ID)
			docDateField = MOrder.COLUMNNAME_DateOrdered;
		else if (tableId == X_PP_Order.Table_ID)
			docDateField = X_PP_Order.COLUMNNAME_DateOrdered;
		else if (tableId == X_DD_Order.Table_ID)
			docDateField = X_DD_Order.COLUMNNAME_DateOrdered;
		else if (tableId == X_M_ProductionBatch.Table_ID)
			docDateField = X_M_ProductionBatch.COLUMNNAME_MovementDate;

		return docDateField;
	}

	public String getDocumentBaseType(int tableId, String tableName)
	{
		String docBaseType = null;
		if (tableId == MInvoice.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_APInvoice
					+ "','" + MPeriodControl.DOCBASETYPE_APCreditMemo
					+ "','" + MPeriodControl.DOCBASETYPE_ARInvoice
					+ "','" + MPeriodControl.DOCBASETYPE_ARCreditMemo
					+ "','" + MPeriodControl.DOCBASETYPE_ARProFormaInvoice + "')";
		else if (tableId == MInOut.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_MaterialDelivery
					+ "','" + MPeriodControl.DOCBASETYPE_MaterialReceipt + "')";
		else if (tableId == MPayment.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_APPayment
					+ "','" + MPeriodControl.DOCBASETYPE_ARReceipt + "')";
		else if (tableId == MOrder.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_SalesOrder
					+ "','" + MPeriodControl.DOCBASETYPE_PurchaseOrder + "')";
		else if (tableId == MProjectIssue.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ProjectIssue + "'";
		else if (tableId == MBankStatement.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_BankStatement + "'";
		else if (tableId == MCash.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_CashJournal + "'";
		else if (tableId == MAllocationHdr.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_PaymentAllocation + "'";
		else if (tableId == MJournal.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_GLJournal + "'";
			//	else if (AD_Table_ID == M.Table_ID)
			//		docBaseType = "= '" + MPeriodControl.DOCBASETYPE_GLDocument + "'";
		else if (tableId == MMovement.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialMovement + "'";
		else if (tableId == MRequisition.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_PurchaseRequisition + "'";
		else if (tableId == MInventory.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialPhysicalInventory + "'";
		else if (tableId == X_M_Production.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialProduction + "'";
		else if (tableId == MMatchInv.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchInvoice + "'";
		else if (tableId == MMatchPO.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchPO + "'";
		else if (tableId == X_PP_Order.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_ManufacturingOrder
					+ "','" + MPeriodControl.DOCBASETYPE_MaintenanceOrder
					+ "','" + MPeriodControl.DOCBASETYPE_QualityOrder + "')";
		else if (tableId == X_PP_Cost_Collector.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_ManufacturingCostCollector+"')";
		else if (tableId == X_DD_Order.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_DistributionOrder+ "'";
		else if (tableId == X_HR_Process.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_Payroll+ "'";
		else if (tableId == X_PP_Cost_Collector.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ManufacturingCostCollector+ "'";
		else if (tableId == X_A_Asset_Addition.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsAddition +  "'";
		else if (tableId == X_A_Depreciation_Entry.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsDisposal + "'";
		else if (tableId == X_A_Asset_Disposed.Table_ID)
			docBaseType = "= '"+ MPeriodControl.DOCBASETYPE_FixedAssetsDepreciation + "'";
		else if (tableId == X_M_ProductionBatch.Table_ID)
			docBaseType = "= '" +MPeriodControl.DOCBASETYPE_ManufacturingPlannedOrder + "'";
		else
			docBaseType = "IS NOT NULL ";

		return docBaseType;
	}

}	//	FactAcctReset
