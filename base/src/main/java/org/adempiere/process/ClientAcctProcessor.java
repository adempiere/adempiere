/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz - globalqss                                           *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.globalqss.com)                                *
**********************************************************************/

package org.adempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import org.compiere.acct.Doc;
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MPPOrder;

/**
 * 	Client Accounting Processor
 *	
 *  @author Carlos Ruiz
 *  @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 *  @see  [ 1250 ] Client Accounting Processor error to get standard timestamp</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1250">
 */
public class ClientAcctProcessor extends SvrProcess
{
	/* The Accounting Schema */
	private int  p_C_AcctSchema_ID;
	/* The Table */
	private int  p_AD_Table_ID;

	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	/** Client info					*/
	private MClient 			m_client = null;
	/**	Accounting Schema			*/
	private MAcctSchema[] 		m_ass = null;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_Table_ID"))
				p_AD_Table_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("C_AcctSchema_ID=" + p_C_AcctSchema_ID + ", AD_Table_ID=" + p_AD_Table_ID);
		
		if (! MClient.isClientAccounting())
			throw new AdempiereUserError(Msg.getMsg(getCtx(), "ClientAccountingNotEnabled"));

		m_client = MClient.get(getCtx(), getAD_Client_ID());
		
		if (p_C_AcctSchema_ID == 0)
			m_ass = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		else	//	only specific accounting schema
			m_ass = new MAcctSchema[] {new MAcctSchema (getCtx(), p_C_AcctSchema_ID, get_TrxName())};
		
		postSession();

		addLog(m_summary.toString());

		return "@OK@";
	}	//	doIt

	/**
	 * 	Post Session
	 */
	private void postSession()
	{
		List<BigDecimal> listProcessedOn = new ArrayList<BigDecimal>();
		for (Timestamp dateacct: getListDateAcct())
		{
			listProcessedOn.clear();
			listProcessedOn.add(Env.ZERO); // to include potential null values

			//get current time from db
			Timestamp ts = DB.getSQLValueTS(get_TrxName(), "SELECT getdate() FROM DUAL");
			//go back 2 second to be safe (to avoid posting documents being completed at this precise moment)
			long ms = ts.getTime()- (2 * 1000);
			ts = new Timestamp(ms);
			long mili = ts.getTime();
			BigDecimal value = new BigDecimal(Long.toString(mili));

			//first pass, collect all ts (FR 2962094 - required for weighted average costing)
			int[] documentsTableID = Doc.getDocumentsTableID();
			String[] documentsTableName = Doc.getDocumentsTableName();
			for (int i = 0; i < documentsTableID.length; i++)
			{
				int AD_Table_ID = documentsTableID[i];
				String TableName = documentsTableName[i];
				//	Post only special documents
				if (p_AD_Table_ID != 0
						&& p_AD_Table_ID != AD_Table_ID)
					continue;

				StringBuffer sql = new StringBuffer ("SELECT DISTINCT ProcessedOn FROM ").append(TableName)
						.append(" WHERE AD_Client_ID=? AND ProcessedOn<?")
						.append(" AND Processed='Y' AND Posted='N' AND IsActive='Y' and " + getColumnNameDateAcct(AD_Table_ID) + "  = ? ");
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
					pstmt.setInt(1, getAD_Client_ID());
					pstmt.setBigDecimal(2, value);
					pstmt.setTimestamp(3, dateacct);
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						BigDecimal processedOn = rs.getBigDecimal(1);
						if (!listProcessedOn.contains(processedOn))
							listProcessedOn.add(processedOn);
					}
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, sql.toString(), e);
				}
				finally
				{
					DB.close(rs, pstmt);
				}
			}

			// initialize counters per table
			int[] count = new int[documentsTableID.length];
			int[] countError = new int[documentsTableID.length];
			for (int i = 0; i < count.length; i++) {
				count[i] = 0;
				countError[i] = 0;
			}

			//sort and post in the processed date order
			Collections.sort(listProcessedOn);
			for (BigDecimal processedOn : listProcessedOn)
			{

				for (int i = 0; i < documentsTableID.length; i++)
				{
					int AD_Table_ID = documentsTableID[i];
					String TableName = documentsTableName[i];
					//	Post only special documents
					if (p_AD_Table_ID != 0
							&& p_AD_Table_ID != AD_Table_ID)
						continue;
					//  SELECT * FROM table
					StringBuffer sql = new StringBuffer ("SELECT * FROM ").append(TableName)
							.append(" WHERE AD_Client_ID=? AND (ProcessedOn");
					if (processedOn.compareTo(Env.ZERO) != 0)
						sql.append("=?");
					else
						sql.append(" IS NULL OR ProcessedOn=0");
					sql.append(") AND Processed='Y' AND Posted='N' AND IsActive='Y'")
					.append(" ORDER BY Created");
					//
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try
					{
						pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
						pstmt.setInt(1, getAD_Client_ID());
						if (processedOn.compareTo(Env.ZERO) != 0)
							pstmt.setBigDecimal(2, processedOn);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							count[i]++;
							boolean ok = true;
							// Run every posting document in own transaction
							String innerTrxName = Trx.createTrxName("CAP");
							Trx innerTrx = Trx.get(innerTrxName, true);
							String postStatus = Doc.STATUS_NotPosted; 
							Doc doc = Doc.get (m_ass, AD_Table_ID, rs, innerTrxName);
							try
							{
								if (doc == null)
								{
									log.severe(getName() + ": No Doc for " + TableName);
									ok = false;
								}
								else
								{
									String error = doc.post(false, false);   //  post no force/repost
									ok = (error == null);
									postStatus = doc.getPostStatus();
								}
							}
							catch (Exception e)
							{
								log.log(Level.SEVERE, getName() + ": " + TableName, e);	
								ok = false;
							}
							finally
							{
								if (ok)
									innerTrx.commit();
								else {
									innerTrx.rollback();
									// save the posted status error (out of trx)
									StringBuffer sqlupd = new StringBuffer("UPDATE ")
									.append(doc.get_TableName()).append(" SET Posted='").append(postStatus)
									.append("',Processing='N' ")
									.append("WHERE ")
									.append(doc.get_TableName()).append("_ID=").append(doc.get_ID());
									DB.executeUpdateEx(sqlupd.toString(), null);
								}
								innerTrx.close();
								innerTrx = null;
							}
							if (!ok)
								countError[i]++;
						}
					}
					catch (Exception e)
					{
						log.log(Level.SEVERE, sql.toString(), e);
					}
					finally
					{
						DB.close(rs, pstmt);
					}

				} // for tableID

			} // for processedOn

			for (int i = 0; i < documentsTableID.length; i++)
			{
				String TableName = documentsTableName[i];
				if (count[i] > 0)
				{
					m_summary.append(TableName).append("=").append(count[i]);
					if (countError[i] > 0)
						m_summary.append("(Errors=").append(countError[i]).append(")");
					m_summary.append(" - ");
					log.finer(getName() + ": " + m_summary.toString());
				}
				else
					log.finer(getName() + ": " + TableName + " - no work");
			}
		}

	}	//	postSession
	
	private List<Timestamp> getListDateAcct()
	{
		//get current time from db
		Timestamp ts = DB.getSQLValueTS(get_TrxName(), "SELECT getdate() FROM DUAL");
		//go back 2 second to be safe (to avoid posting documents being completed at this precise moment)
		long ms = ts.getTime()- (2 * 1000);
		ts = new Timestamp(ms);
		long mili = ts.getTime();
		BigDecimal value = new BigDecimal(Long.toString(mili));

		List<Timestamp> listDateAcct = new ArrayList<Timestamp>();

		//get current time from db
		
		//first pass, collect all ts (FR 2962094 - required for weighted average costing)
		int[] documentsTableID = Doc.getDocumentsTableID();
		String[] documentsTableName = Doc.getDocumentsTableName();
		for (int i = 0; i < documentsTableID.length; i++)
		{
			int AD_Table_ID = documentsTableID[i];
			String TableName = documentsTableName[i];
			//	Post only special documents
			if (p_AD_Table_ID != 0
				&& p_AD_Table_ID != AD_Table_ID)
				continue;
			
			StringBuffer sql = new StringBuffer ("SELECT DISTINCT " + getColumnNameDateAcct(AD_Table_ID) +" FROM ").append(TableName)
				.append(" WHERE AD_Client_ID=? ")
				.append(" AND Processed='Y' AND Posted='N' AND IsActive='Y'");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, getAD_Client_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					Timestamp dateacct = rs.getTimestamp(1);
					if (!listDateAcct.contains(dateacct))
						listDateAcct.add(dateacct);
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql.toString(), e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}

		  Collections.sort(listDateAcct);
		  return listDateAcct;
	}

	private String getColumnNameDateAcct(int AD_Table_ID)
	{
		if (AD_Table_ID == MBankStatement.Table_ID)
			return MBankStatement.COLUMNNAME_StatementDate;
		if (AD_Table_ID == MProjectIssue.Table_ID)
			return MProjectIssue.COLUMNNAME_MovementDate;
		if (AD_Table_ID == MInventory.Table_ID)
			return MInventory.COLUMNNAME_MovementDate;
		if (AD_Table_ID == MMovement.Table_ID)
			return MMovement.COLUMNNAME_MovementDate;
		if (AD_Table_ID == MProduct.Table_ID)
			return MProduction.COLUMNNAME_MovementDate;
		if (AD_Table_ID == MRequisition.Table_ID)
			return MRequisition.COLUMNNAME_DateDoc;
		if (AD_Table_ID == MPPOrder.Table_ID)
			return MPPOrder.COLUMNNAME_DateOrdered;
		if (AD_Table_ID == MDDOrder.Table_ID)
			return MDDOrder.COLUMNNAME_DateOrdered;
		if (AD_Table_ID == MProduction.Table_ID)
			return MProductionBatch.COLUMNNAME_MovementDate;
		if (AD_Table_ID == MProductionBatch.Table_ID)
			return MProduction.COLUMNNAME_MovementDate;

		return MInvoice.COLUMNNAME_DateAcct;
	}

}	//	ClientAcctProcessor
