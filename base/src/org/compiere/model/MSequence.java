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
package org.compiere.model;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.db.CConnection;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Trx;

/**
 *	Sequence Model.
 *	@see org.compiere.process.SequenceCheck
 *  @author Jorg Janke
 *  @version $Id: MSequence.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MSequence extends X_AD_Sequence
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6827013120475678483L;
	/** Use SQL procedure to get next id			*/
	  //begin vpj-cd e-evolution 02/11/2005  PostgreSQL
	//private static final boolean USE_PROCEDURE = true;
	private static boolean USE_PROCEDURE = false;
	//end vpj-cd e-evolution 02/11/2005
	/** Log Level for Next ID Call					*/
	private static final Level LOGLEVEL = Level.ALL;

	private static final int QUERY_TIME_OUT = 10;

	public static int getNextID (int AD_Client_ID, String TableName)
	{
		return getNextID(AD_Client_ID, TableName, null);
	}

	/**
	 *
	 *	Get next number for Key column = 0 is Error.
	 *  @param AD_Client_ID client
	 *  @param TableName table name
	 * 	@param trxName deprecated.
	 *  @return next no or (-1=not found, -2=error)
	 */
	public static int getNextID (int AD_Client_ID, String TableName, String trxName)
	{
		if (TableName == null || TableName.length() == 0)
			throw new IllegalArgumentException("TableName missing");

		int retValue = -1;

		//	Check AdempiereSys
		boolean adempiereSys = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		if (adempiereSys && AD_Client_ID > 11)
			adempiereSys = false;
		//
		if (CLogMgt.isLevel(LOGLEVEL))
			s_log.log(LOGLEVEL, TableName + " - AdempiereSys=" + adempiereSys  + " [" + trxName + "]");
		  //begin vpj-cd e-evolution 09/02/2005 PostgreSQL
		String selectSQL = null;
		if (DB.isOracle() == false)
		{
			selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, AD_Sequence_ID "
				+ "FROM AD_Sequence "
				+ "WHERE Name=?"
				+ " AND IsActive='Y' AND IsTableID='Y' AND IsAutoSequence='Y' "
				+ " FOR UPDATE OF AD_Sequence ";
			USE_PROCEDURE=false;
		}
		else
		{
			selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, AD_Sequence_ID "
			+ "FROM AD_Sequence "
			+ "WHERE Name=?"
			+ " AND IsActive='Y' AND IsTableID='Y' AND IsAutoSequence='Y' ";

			USE_PROCEDURE = true;
		}

		//hengsin: executing getNextID in transaction create huge performance and locking issue
		//Trx trx = trxName == null ? null : Trx.get(trxName, true);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		for (int i = 0; i < 3; i++)
		{
			try
			{
				conn = DB.getConnectionID();
				//	Error
				if (conn == null)
					return -1;

				pstmt = conn.prepareStatement(selectSQL,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, TableName);
				//
				if (!USE_PROCEDURE && DB.getDatabase().isQueryTimeoutSupported())
					pstmt.setQueryTimeout(QUERY_TIME_OUT);
				rs = pstmt.executeQuery();
				if (CLogMgt.isLevelFinest())
					s_log.finest("AC=" + conn.getAutoCommit() + ", RO=" + conn.isReadOnly()
						+ " - Isolation=" + conn.getTransactionIsolation() + "(" + Connection.TRANSACTION_READ_COMMITTED
						+ ") - RSType=" + pstmt.getResultSetType() + "(" + ResultSet.TYPE_SCROLL_SENSITIVE
						+ "), RSConcur=" + pstmt.getResultSetConcurrency() + "(" + ResultSet.CONCUR_UPDATABLE
						+ ")");
				if (rs.next())
				{

					// Get the table
					MTable table = MTable.get(Env.getCtx(), TableName);

					int AD_Sequence_ID = rs.getInt(4);
					boolean gotFromHTTP = false;

					// If maintaining official dictionary try to get the ID from http official server
					if (adempiereSys) {

						String isUseCentralizedID = MSysConfig.getValue("DICTIONARY_ID_USE_CENTRALIZED_ID", "Y"); // defaults to Y
						if ( ( ! isUseCentralizedID.equals("N") ) && ( ! isExceptionCentralized(TableName) ) ) {
							// get ID from http site
							retValue = getNextOfficialID_HTTP(TableName);
							if (retValue > 0) {
								PreparedStatement updateSQL;
								updateSQL = conn.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = ? + 1 WHERE AD_Sequence_ID = ?");
								try {
									updateSQL.setInt(1, retValue);
									updateSQL.setInt(2, AD_Sequence_ID);
									updateSQL.executeUpdate();
								} finally {
									updateSQL.close();
								}
							}
							gotFromHTTP = true;
						}

					}

					boolean queryProjectServer = false;
					if (table.getColumn("EntityType") != null)
						queryProjectServer = true;
					if (!queryProjectServer && MSequence.Table_Name.equalsIgnoreCase(TableName))
						queryProjectServer = true;
					
					// If not official dictionary try to get the ID from http custom server - if configured
					if (queryProjectServer && ( ! adempiereSys ) && ( ! isExceptionCentralized(TableName) ) ) {

						String isUseProjectCentralizedID = MSysConfig.getValue("PROJECT_ID_USE_CENTRALIZED_ID", "N"); // defaults to N
						if (isUseProjectCentralizedID.equals("Y")) {
							// get ID from http site
							retValue = getNextProjectID_HTTP(TableName);
							if (retValue > 0) {
								PreparedStatement updateSQL;
								updateSQL = conn.prepareStatement("UPDATE AD_Sequence SET CurrentNext = GREATEST(CurrentNext, ? + 1) WHERE AD_Sequence_ID = ?");
								try {
									updateSQL.setInt(1, retValue);
									updateSQL.setInt(2, AD_Sequence_ID);
									updateSQL.executeUpdate();
								} finally {
									updateSQL.close();
								}
							}
							gotFromHTTP = true;
						}

					}

					if (! gotFromHTTP) {
						//
						if (USE_PROCEDURE)
						{
							retValue = nextID(conn, AD_Sequence_ID, adempiereSys);
						}
						else
						{
							PreparedStatement updateSQL;
							int incrementNo = rs.getInt(3);
							if (adempiereSys) {
								updateSQL = conn
										.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + ? WHERE AD_Sequence_ID = ?");
								retValue = rs.getInt(2);
							} else {
								updateSQL = conn
										.prepareStatement("UPDATE AD_Sequence SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ?");
								retValue = rs.getInt(1);
							}
							try {
								updateSQL.setInt(1, incrementNo);
								updateSQL.setInt(2, AD_Sequence_ID);
								updateSQL.executeUpdate();
							} finally {
								updateSQL.close();
							}
						}
					}

					//if (trx == null)
					conn.commit();
				}
				else
					s_log.severe ("No record found - " + TableName);

				//
				break;		//	EXIT
			}
			catch (Exception e)
			{
				s_log.log(Level.SEVERE, TableName + " - " + e.getMessage(), e);
				try
				{
					if (conn != null)
						conn.rollback();
				} catch (SQLException e1) { }
			}
			finally
			{
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
				if (conn != null)
				{
					try {
						conn.close();
					} catch (SQLException e) {}
					conn = null;
				}
			}
			Thread.yield();		//	give it time
		}


		//s_log.finest (retValue + " - Table=" + TableName + " [" + trx + "]");
		return retValue;
	}	//	getNextID

	/**
	 * 	Get Next ID
	 *	@param conn connection
	 *	@param AD_Sequence_ID sequence
	 *	@param adempiereSys sys
	 *	@return next id or -1 (error) or -3 (parameter)
	 */
	private static int nextID (Connection conn, int AD_Sequence_ID, boolean adempiereSys)
	{
		if (conn == null || AD_Sequence_ID == 0)
			return -3;
		//
		int retValue = -1;
		String sqlUpdate = "{call nextID(?,?,?)}";
		CallableStatement cstmt = null;
		try
		{
			cstmt = conn.prepareCall (sqlUpdate,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			cstmt.setInt(1, AD_Sequence_ID);
			cstmt.setString(2, adempiereSys ? "Y" : "N");
			cstmt.registerOutParameter(3, Types.INTEGER);
			if (DB.getDatabase().isQueryTimeoutSupported())
			{
				cstmt.setQueryTimeout(QUERY_TIME_OUT);
			}
			cstmt.execute();
			retValue = cstmt.getInt(3);
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, e.toString());
		}
		finally
		{
			DB.close(cstmt);
		}
		return retValue;
	}	//	nextID

	/**
	 * Get next id by year
	 * @param conn
	 * @param AD_Sequence_ID
	 * @param incrementNo
	 * @param calendarYear
	 * @return next id
	 */
	private static int nextIDByYear(Connection conn, int AD_Sequence_ID,
			int incrementNo, String calendarYear) {
		if (conn == null || AD_Sequence_ID == 0)
			return -3;
		//
		int retValue = -1;
		String sqlUpdate = "{call nextIDByYear(?,?,?,?)}";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sqlUpdate, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt(1, AD_Sequence_ID);
			cstmt.setInt(2, incrementNo);
			cstmt.setString(3, calendarYear);
			cstmt.registerOutParameter(4, Types.INTEGER);
			if (DB.getDatabase().isQueryTimeoutSupported())
			{
				cstmt.setQueryTimeout(QUERY_TIME_OUT);
			}
			cstmt.execute();
			retValue = cstmt.getInt(4);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.toString());
		} finally {
			DB.close(cstmt);
		}
		return retValue;
	} // nextID

	/**************************************************************************
	 * 	Get Document No from table
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 */
	public static String getDocumentNo (int AD_Client_ID, String TableName, String trxName)
	{
		return getDocumentNo(AD_Client_ID, TableName, trxName, null);

	}
	/**************************************************************************
	 * 	Get Document No from table
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @param PO
	 *	@return document no or null
	 */
	public static String getDocumentNo (int AD_Client_ID, String TableName, String trxName, PO po)
	{
		if (TableName == null || TableName.length() == 0)
			throw new IllegalArgumentException("TableName missing");

		//	Check AdempiereSys
		boolean adempiereSys = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		if (adempiereSys && AD_Client_ID > 11)
			adempiereSys = false;
		//
		if (CLogMgt.isLevel(LOGLEVEL))
			s_log.log(LOGLEVEL, TableName + " - AdempiereSys=" + adempiereSys  + " [" + trxName + "]");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isStartNewYear = false;
		String dateColumn = null;

		if (!adempiereSys)
		{
			// Get the Start New Year flag
			String startNewYearSQL = "SELECT StartNewYear, DateColumn FROM AD_Sequence "
					+ "WHERE Name = ? AND IsActive = 'Y' AND IsTableID = 'N' AND IsAutoSequence='Y' AND AD_Client_ID = ?";
			try
			{
				pstmt = DB.prepareStatement(startNewYearSQL, trxName);
				pstmt.setString(1, PREFIX_DOCSEQ + TableName);
				pstmt.setInt(2, AD_Client_ID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					isStartNewYear = "Y".equals(rs.getString(1));
					dateColumn = rs.getString(2);
				}
			}
			catch (Exception e)
			{
				s_log.log(Level.SEVERE, "(Table) [" + trxName + "]", e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}


		String selectSQL = null;
		if (DB.isOracle() == false)
		{
			if (isStartNewYear) {
				selectSQL = "SELECT y.CurrentNext, s.CurrentNextSys, s.IncrementNo, s.Prefix, s.Suffix, s.DecimalPattern, s.AD_Sequence_ID "
						+ "FROM AD_Sequence_No y, AD_Sequence s "
						+ "WHERE y.AD_Sequence_ID = s.AD_Sequence_ID "
						+ "AND s.Name = ? "
						+ "AND s.AD_Client_ID = ? "
						+ "AND y.CalendarYear = ? "
						+ "AND s.IsActive='Y' AND s.IsTableID='N' AND s.IsAutoSequence='Y' "
						+ "ORDER BY s.AD_Client_ID DESC "
						+ "FOR UPDATE OF y";
			} else {
				selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, DecimalPattern, AD_Sequence_ID "
						+ "FROM AD_Sequence "
						+ "WHERE Name = ? "
						+ "AND AD_Client_ID = ? "
						+ "AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' "
						+ "ORDER BY AD_Client_ID DESC "
						+ "FOR UPDATE OF AD_Sequence";
			}
			USE_PROCEDURE=false;
		}
		else
		{
			if (isStartNewYear) {
				selectSQL = "SELECT y.CurrentNext, s.CurrentNextSys, s.IncrementNo, Prefix, Suffix, DecimalPattern, s.AD_Sequence_ID "
						+ "FROM AD_Sequence_No y, AD_Sequence s "
						+ "WHERE y.AD_Sequence_ID = s.AD_Sequence_ID "
						+ "AND s.Name = ? "
						+ "AND s.AD_Client_ID = ? "
						+ "AND y.CalendarYear = ? "
						+ "AND s.IsActive='Y' AND s.IsTableID='N' AND s.IsAutoSequence='Y' "
						+ "ORDER BY s.AD_Client_ID DESC";
			} else {
				selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, DecimalPattern, AD_Sequence_ID "
						+ "FROM AD_Sequence "
						+ "WHERE Name = ? "
						+ "AND AD_Client_ID = ? "
						+ "AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' "
						+ "ORDER BY AD_Client_ID DESC";
			}
			USE_PROCEDURE = true;
		}
		Connection conn = null;
		Trx trx = trxName == null ? null : Trx.get(trxName, true);
		//
		int AD_Sequence_ID = 0;
		int incrementNo = 0;
		int next = -1;
		String prefix = "";
		String suffix = "";
		String decimalPattern = "";
		String calendarYear = "";
		try
		{
			if (trx != null)
				conn = trx.getConnection();
			else
				conn = DB.getConnectionID();
			//	Error
			if (conn == null)
				return null;

			if (isStartNewYear)
			{
				if (po != null && dateColumn != null && dateColumn.length() > 0)
				{
					Date docDate = (Date)po.get_Value(dateColumn);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					calendarYear = sdf.format(docDate);
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					calendarYear = sdf.format(new Date());
				}
			}

			//
			pstmt = conn.prepareStatement(selectSQL,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, PREFIX_DOCSEQ + TableName);
			pstmt.setInt(2, AD_Client_ID);
			if (isStartNewYear)
				pstmt.setString(3, calendarYear);

			//
			if (!USE_PROCEDURE && DB.getDatabase().isQueryTimeoutSupported())
				pstmt.setQueryTimeout(QUERY_TIME_OUT);
			rs = pstmt.executeQuery();
		//	s_log.fine("AC=" + conn.getAutoCommit() + " -Iso=" + conn.getTransactionIsolation()
		//		+ " - Type=" + pstmt.getResultSetType() + " - Concur=" + pstmt.getResultSetConcurrency());
			if (rs.next())
			{
				AD_Sequence_ID = rs.getInt(7);
				prefix = rs.getString(4);
				suffix = rs.getString(5);
				decimalPattern = rs.getString(6);
				incrementNo = rs.getInt(3);
				if (USE_PROCEDURE)
				{
					next = isStartNewYear
						? nextIDByYear(conn, AD_Sequence_ID, incrementNo, calendarYear)
						: nextID(conn, AD_Sequence_ID, adempiereSys);
				}
				else
				{
					PreparedStatement updateSQL = null;
					try
					{
						if (adempiereSys) {
							updateSQL = conn
									.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + ? WHERE AD_Sequence_ID = ?");
							next = rs.getInt(2);
						} else {
							String sql = isStartNewYear
								? "UPDATE AD_Sequence_No SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ? AND CalendarYear = ?"
								: "UPDATE AD_Sequence SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ?";
							updateSQL = conn
									.prepareStatement(sql);
							next = rs.getInt(1);
						}
						updateSQL.setInt(1, incrementNo);
						updateSQL.setInt(2, AD_Sequence_ID);
						if (isStartNewYear)
							updateSQL.setString(3, calendarYear);
						updateSQL.executeUpdate();
					}
					finally
					{
						DB.close(updateSQL);
					}
				}
			}
			else
			{
				s_log.warning ("(Table) - no record found - " + TableName);
				MSequence seq = new MSequence (Env.getCtx(), AD_Client_ID, TableName, null);
				next = seq.getNextID();
				seq.save();
			}
			//	Commit
			if (trx == null)
			{
				conn.commit();
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "(Table) [" + trxName + "]", e);
			if (DBException.isTimeout(e))
				throw new AdempiereException("GenerateDocumentNoTimeOut", e);
			else
				throw new AdempiereException("GenerateDocumentNoError", e);
		}
		finally
		{
			//Finish
			DB.close(rs, pstmt);
			try
			{
				if (trx == null && conn != null) {
					conn.close();
					conn = null;
				}
			}
			catch (Exception e)
			{
				s_log.log(Level.SEVERE, "(Table) - finish", e);
			}
		}

		//	Error
		if (next < 0)
			return null;

		//	create DocumentNo
		StringBuffer doc = new StringBuffer();
		if (prefix != null && prefix.length() > 0)
			doc.append(Env.parseVariable(prefix, po, trxName, false));
		if (decimalPattern != null && decimalPattern.length() > 0)
			doc.append(new DecimalFormat(decimalPattern).format(next));
		else
			doc.append(next);
		if (suffix != null && suffix.length() > 0)
			doc.append(Env.parseVariable(suffix, po, trxName, false));
		String documentNo = doc.toString();
		s_log.finer (documentNo + " (" + incrementNo + ")"
				+ " - Table=" + TableName + " [" + trx + "]");
		return documentNo;
	}	//	getDocumentNo

	/**
	 * 	Get Document No based on Document Type
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 *  @deprecated
	 */
	public static String getDocumentNo(int C_DocType_ID, String trxName)
	{
		return getDocumentNo (C_DocType_ID, trxName, false);
	}	//	getDocumentNo

	/**
	 * 	Get Document No based on Document Type
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *  @param definite asking for a definitive or temporary sequence
	 *	@return document no or null
	 */
	public static String getDocumentNo (int C_DocType_ID, String trxName, boolean definite)
	{
		return getDocumentNo(C_DocType_ID, trxName, definite, null);
	}

	/**
	 * 	Get Document No based on Document Type
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *  @param definite asking for a definitive or temporary sequence
	 *  @param po
	 *	@return document no or null
	 */
	public static String getDocumentNo (int C_DocType_ID, String trxName, boolean definite, PO po)
	{
		if (C_DocType_ID == 0)
		{
			s_log.severe ("C_DocType_ID=0");
			return null;
		}

		MDocType dt = MDocType.get (Env.getCtx(), C_DocType_ID);	//	wrong for SERVER, but r/o
		if (dt != null && !dt.isDocNoControlled())
		{
			s_log.finer("DocType_ID=" + C_DocType_ID + " Not DocNo controlled");
			return null;
		}
		if (definite && ! dt.isOverwriteSeqOnComplete()) {
			s_log.finer("DocType_ID=" + C_DocType_ID + " Not Sequence Overwrite on Complete");
			return null;
		}
		if (dt == null || dt.getDocNoSequence_ID() == 0)
		{
			s_log.warning ("No Sequence for DocType - " + dt);
			return null;
		}
		if (definite && dt.getDefiniteSequence_ID() == 0)
		{
			s_log.warning ("No Definite Sequence for DocType - " + dt);
			return null;
		}

		//	Check AdempiereSys
		boolean adempiereSys = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		if (CLogMgt.isLevel(LOGLEVEL))
			s_log.log(LOGLEVEL, "DocType_ID=" + C_DocType_ID + " [" + trxName + "]");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isStartNewYear = false;
		String dateColumn = null;

		if (!adempiereSys)
		{
			// Get the Start New Year & Sequence Type
			String startNewYearSQL = "SELECT StartNewYear, DateColumn FROM AD_Sequence "
					+ "WHERE AD_Sequence_ID = ? AND IsActive = 'Y' AND IsTableID = 'N' AND IsAutoSequence='Y'";
			try {
				pstmt = DB.prepareStatement(startNewYearSQL, trxName);
				pstmt.setInt(1, definite ? dt.getDefiniteSequence_ID() : dt
						.getDocNoSequence_ID());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					isStartNewYear = "Y".equals(rs.getString(1));
					dateColumn = rs.getString(2);
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "(Table) [" + trxName + "]", e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}

		String selectSQL = null;
		if (DB.isOracle() == false)
		{
			if (isStartNewYear)
			{
				selectSQL = "SELECT y.CurrentNext, s.CurrentNextSys, s.IncrementNo, s.Prefix, s.Suffix, s.DecimalPattern, s.AD_Client_ID, s.AD_Sequence_ID "
						+ "FROM AD_Sequence_No y, AD_Sequence s "
						+ "WHERE y.AD_Sequence_ID = s.AD_Sequence_ID "
						+ "AND s.AD_Sequence_ID = ? "
						+ "AND y.CalendarYear = ? "
						+ "AND s.IsActive='Y' AND s.IsTableID='N' AND s.IsAutoSequence='Y' "
						+ "FOR UPDATE OF y";
			}
			else
			{
				selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, DecimalPattern, AD_Client_ID, AD_Sequence_ID "
						+ "FROM AD_Sequence "
						+ "WHERE AD_Sequence_ID = ? "
						+ "AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' "
						+ "FOR UPDATE OF AD_Sequence";
			}
			USE_PROCEDURE=false;
		}
		else
		{
			if (isStartNewYear) {
				selectSQL = "SELECT y.CurrentNext, s.CurrentNextSys, s.IncrementNo, s.Prefix, s.Suffix, s.DecimalPattern, s.AD_Client_ID, s.AD_Sequence_ID "
						+ "FROM AD_Sequence_No y, AD_Sequence s "
						+ "WHERE y.AD_Sequence_ID = s.AD_Sequence_ID "
						+ "AND s.AD_Sequence_ID = ? "
						+ "AND y.CalendarYear = ? "
						+ "AND s.IsActive='Y' AND s.IsTableID='N' AND s.IsAutoSequence='Y' ";
			} else {
				selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, DecimalPattern, AD_Client_ID, AD_Sequence_ID "
						+ "FROM AD_Sequence "
						+ "WHERE AD_Sequence_ID = ? "
						+ "AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' ";
			}
			USE_PROCEDURE = true;
		}
		Connection conn = null;
		Trx trx = trxName == null ? null : Trx.get(trxName, true);
		//
		int AD_Sequence_ID = 0;
		int incrementNo = 0;
		int next = -1;
		String prefix = "";
		String suffix = "";
		String decimalPattern = "";
		String calendarYear = "";
		try
		{
			if (trx != null)
				conn = trx.getConnection();
			else
				conn = DB.getConnectionID();
			//	Error
			if (conn == null)
				return null;

			if (isStartNewYear)
			{
				if (po != null && dateColumn != null && dateColumn.length() > 0)
				{
					Date docDate = (Date)po.get_Value(dateColumn);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					calendarYear = sdf.format(docDate);
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					calendarYear = sdf.format(new Date());
				}
			}

			pstmt = conn.prepareStatement(selectSQL,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			if (definite)
				pstmt.setInt(1, dt.getDefiniteSequence_ID());
			else
				pstmt.setInt(1, dt.getDocNoSequence_ID());
			if (isStartNewYear)
				pstmt.setString(2, calendarYear);

			//
			if (!USE_PROCEDURE && DB.getDatabase().isQueryTimeoutSupported())
				pstmt.setQueryTimeout(QUERY_TIME_OUT);
			rs = pstmt.executeQuery();
		//	s_log.fine("AC=" + conn.getAutoCommit() + " -Iso=" + conn.getTransactionIsolation()
		//		+ " - Type=" + pstmt.getResultSetType() + " - Concur=" + pstmt.getResultSetConcurrency());
			if (rs.next())
			{
				incrementNo = rs.getInt(3);
				prefix = rs.getString(4);
				suffix = rs.getString(5);
				decimalPattern = rs.getString(6);
				int AD_Client_ID = rs.getInt(7);
				if (adempiereSys && AD_Client_ID > 11)
					adempiereSys = false;
				AD_Sequence_ID = rs.getInt(8);

				if (USE_PROCEDURE)
				{
					next = isStartNewYear
						? nextIDByYear(conn, AD_Sequence_ID, incrementNo, calendarYear)
						: nextID(conn, AD_Sequence_ID, adempiereSys);
				}
				else
				{
					PreparedStatement updateSQL = null;
					try
					{
						if (adempiereSys) {
							updateSQL = conn
									.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + ? WHERE AD_Sequence_ID = ?");
							next = rs.getInt(2);
						} else {
							String sql = isStartNewYear
								? "UPDATE AD_Sequence_No SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ? AND CalendarYear = ?"
								: "UPDATE AD_Sequence SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ?";
							updateSQL = conn.prepareStatement(sql);
							next = rs.getInt(1);
						}
						updateSQL.setInt(1, incrementNo);
						updateSQL.setInt(2, AD_Sequence_ID);
						if (isStartNewYear)
							updateSQL.setString(3, calendarYear);
						updateSQL.executeUpdate();
					}
					finally
					{
						DB.close(updateSQL);
					}
				}
			}
			else
			{
				s_log.warning ("(DocType)- no record found - " + dt);
				next = -2;
			}
			//	Commit
			if (trx == null)
			{
				conn.commit();
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "(DocType) [" + trxName + "]", e);
			if (DBException.isTimeout(e))
				throw new AdempiereException("GenerateDocumentNoTimeOut", e);
			else
				throw new AdempiereException("GenerateDocumentNoError", e);
		}
		finally
		{
			//	Finish
			try
			{
				DB.close(rs, pstmt);
				if (trx == null && conn != null) {
					conn.close();
					conn = null;
				}
			}
			catch (Exception e)
			{
				s_log.log(Level.SEVERE, "(DocType) - finish", e);
			}
		}
		//	Error
		if (next < 0)
			return null;

		//	create DocumentNo
		StringBuffer doc = new StringBuffer();
		if (prefix != null && prefix.length() > 0)
			doc.append(Env.parseVariable(prefix, po, trxName, false));
		if (decimalPattern != null && decimalPattern.length() > 0)
			doc.append(new DecimalFormat(decimalPattern).format(next));
		else
			doc.append(next);
		if (suffix != null && suffix.length() > 0)
			doc.append(Env.parseVariable(suffix, po, trxName, false));
		String documentNo = doc.toString();
		s_log.finer (documentNo + " (" + incrementNo + ")"
				+ " - C_DocType_ID=" + C_DocType_ID + " [" + trx + "]");
		return documentNo;
	}	//	getDocumentNo


	/**************************************************************************
	 *	Check/Initialize Client DocumentNo/Value Sequences
	 *	@param ctx context
	 *	@param AD_Client_ID client
	 *	@param trxName transaction
	 *	@return true if no error
	 */
	public static boolean checkClientSequences (Properties ctx, int AD_Client_ID, String trxName)
	{
		String sql = "SELECT TableName "
			+ "FROM AD_Table t "
			+ "WHERE IsActive='Y' AND IsView='N'"
			//	Get all Tables with DocumentNo or Value
			+ " AND AD_Table_ID IN "
				+ "(SELECT AD_Table_ID FROM AD_Column "
				+ "WHERE ColumnName = 'DocumentNo' OR ColumnName = 'Value')"
			//	Ability to run multiple times
			+ " AND 'DocumentNo_' || TableName NOT IN "
				+ "(SELECT Name FROM AD_Sequence s "
				+ "WHERE s.AD_Client_ID=?)";
		int counter = 0;
		boolean success = true;
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String tableName = rs.getString(1);
				s_log.fine("Add: " + tableName);
				MSequence seq = new MSequence (ctx, AD_Client_ID, tableName, trxName);
				if (seq.save())
					counter++;
				else
				{
					s_log.severe ("Not created - AD_Client_ID=" + AD_Client_ID
						+ " - "  + tableName);
					success = false;
				}
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		s_log.info ("AD_Client_ID=" + AD_Client_ID
			+ " - created #" + counter
			+ " - success=" + success);
		return success;
	}	//	checkClientSequences


	/**
	 * 	Create Table ID Sequence
	 * 	@param ctx context
	 * 	@param TableName table name
	 *	@param trxName transaction
	 * 	@return true if created
	 */
	public static boolean createTableSequence (Properties ctx, String TableName, String trxName)
	{
		boolean SYSTEM_NATIVE_SEQUENCE = MSysConfig.getBooleanValue("SYSTEM_NATIVE_SEQUENCE",false);

		if(SYSTEM_NATIVE_SEQUENCE)
		{
			int next_id = MSequence.getNextID(Env.getAD_Client_ID(ctx), TableName, trxName);
			if(next_id == -1)
			{
				MSequence seq = new MSequence (ctx, 0, trxName);
				seq.setClientOrg(0, 0);
				seq.setName(TableName);
				seq.setDescription("Table " + TableName);
				seq.setIsTableID(true);
				seq.saveEx();
				next_id = 1000000;
			}

			if(CConnection.get().getDatabase().createSequence(TableName+"_SEQ", 1, 0 , 99999999,  next_id, trxName))
				return true;

			return false;
		}

		MSequence seq = new MSequence (ctx, 0, trxName);
		seq.setClientOrg(0, 0);
		seq.setName(TableName);
		seq.setDescription("Table " + TableName);
		seq.setIsTableID(true);
		return seq.save();
	}	//	createTableSequence


	/**
	 * 	Get Sequence
	 *	@param ctx context
	 *	@param tableName table name
	 *	@return Sequence
	 */
	public static MSequence get (Properties ctx, String tableName)
	{
		return get(ctx, tableName, null);
	}

	/**
	 * 	Get Sequence
	 *	@param ctx context
	 *	@param tableName table name
	 *  @param trxName optional transaction name
	 *	@return Sequence
	 */
	public static MSequence get (Properties ctx, String tableName, String trxName)
	{
		String sql = "SELECT * FROM AD_Sequence "
			+ "WHERE UPPER(Name)=?"
			+ " AND IsTableID='Y'";
		MSequence retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setString (1, tableName.toUpperCase());
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MSequence (ctx, rs, trxName);
			if (rs.next())
				s_log.log(Level.SEVERE, "More then one sequence for " + tableName);
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "get", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}	//	get


	/**	Sequence for Table Document No's	*/
	private static final String	PREFIX_DOCSEQ = "DocumentNo_";
	/**	Start Number			*/
	public static final int		INIT_NO = 1000000;	//	1 Mio
	/**	Start System Number		*/
	// public static final int		INIT_SYS_NO = 100; // start number for Compiere
	public static final int		INIT_SYS_NO = 50000;   // start number for Adempiere
	/** Static Logger			*/
	private static CLogger 		s_log = CLogger.getCLogger(MSequence.class);


	/**************************************************************************
	 *	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Sequence_ID id
	 *	@param trxName transaction
	 */
	public MSequence (Properties ctx, int AD_Sequence_ID, String trxName)
	{
		super(ctx, AD_Sequence_ID, trxName);
		if (AD_Sequence_ID == 0)
		{
		//	setName (null);
			//
			setIsTableID(false);
			setStartNo (INIT_NO);
			setCurrentNext (INIT_NO);
			setCurrentNextSys (INIT_SYS_NO);
			setIncrementNo (1);
			setIsAutoSequence (true);
			setIsAudited(false);
			setStartNewYear(false);
		}
	}	//	MSequence

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MSequence (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MSequence

	/**
	 * 	New Document Sequence Constructor
	 *	@param ctx context
	 *	@param AD_Client_ID owner
	 *	@param tableName name
	 *	@param trxName transaction
	 */
	public MSequence (Properties ctx, int AD_Client_ID, String tableName, String trxName)
	{
		this (ctx, 0, trxName);
		setClientOrg(AD_Client_ID, 0);			//	Client Ownership
		setName(PREFIX_DOCSEQ + tableName);
		setDescription("DocumentNo/Value for Table " + tableName);
	}	//	MSequence;

	/**
	 * 	New Document Sequence Constructor
	 *	@param ctx context
	 *	@param AD_Client_ID owner
	 *	@param sequenceName name
	 *	@param StartNo start
	 *	@param trxName trx
	 */
	public MSequence (Properties ctx, int AD_Client_ID, String sequenceName, int StartNo, String trxName)
	{
		this (ctx, 0, trxName);
		setClientOrg(AD_Client_ID, 0);			//	Client Ownership
		setName(sequenceName);
		setDescription(sequenceName);
		setStartNo(StartNo);
		setCurrentNext(StartNo);
		setCurrentNextSys(StartNo/10);
	}	//	MSequence;


	/**************************************************************************
	 * 	Get Next No and increase current next
	 *	@return next no to use
	 */
	public int getNextID()
	{
		int retValue = getCurrentNext();
		setCurrentNext(retValue + getIncrementNo());
		return retValue;
	}	//	getNextNo

	/**
	 * 	Validate Table Sequence Values
	 *	@return true if updated
	 */
	public boolean validateTableIDValue()
	{
		if (!isTableID())
			return false;
		String tableName = getName();
		int AD_Column_ID = DB.getSQLValue(null, "SELECT MAX(c.AD_Column_ID) "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "WHERE t.TableName='" + tableName + "'"
			+ " AND c.ColumnName='" + tableName + "_ID'");
		if (AD_Column_ID <= 0)
			return false;
		//
		MSystem system = MSystem.get(getCtx());
		int IDRangeEnd = 0;
		if (system.getIDRangeEnd() != null)
			IDRangeEnd = system.getIDRangeEnd().intValue();
		boolean change = false;
		String info = null;

		//	Current Next
		String sql = "SELECT MAX(" + tableName + "_ID) FROM " + tableName;
		if (IDRangeEnd > 0)
			sql += " WHERE " + tableName + "_ID < " + IDRangeEnd;
		int maxTableID = DB.getSQLValue(null, sql);
		if (maxTableID < INIT_NO)
			maxTableID = INIT_NO - 1;
		maxTableID++;		//	Next
		if (getCurrentNext() < maxTableID)
		{
			setCurrentNext(maxTableID);
			info = "CurrentNext=" + maxTableID;
			change = true;
		}

		//	Get Max System_ID used in Table
		sql = "SELECT MAX(" + tableName + "_ID) FROM " + tableName
			+ " WHERE " + tableName + "_ID < " + INIT_NO;
		int maxTableSysID = DB.getSQLValue(null, sql);
		if (maxTableSysID <= 0)
			maxTableSysID = INIT_SYS_NO - 1;
		maxTableSysID++;	//	Next
		if (getCurrentNextSys() < maxTableSysID)
		{
			setCurrentNextSys(maxTableSysID);
			if (info == null)
				info = "CurrentNextSys=" + maxTableSysID;
			else
				info += " - CurrentNextSys=" + maxTableSysID;
			change = true;
		}
		if (info != null)
			log.fine(getName() + " - " + info);
		return change;
	}	//	validate


	/**************************************************************************
	 *	Test
	 *	@param args ignored
	 */
	static public void main (String[] args)
	{
		// int id = getNextID_HTTP("AD_Column");
		// if (true) return;

		org.compiere.Adempiere.startup(true);
		CLogMgt.setLevel(Level.SEVERE);
		CLogMgt.setLoggerLevel(Level.SEVERE, null);
		s_list = new Vector<Integer>(1000);

		/**	Lock Test **
		String trxName = "test";
		System.out.println(DB.getDocumentNo(115, trxName));
		System.out.println(DB.getDocumentNo(116, trxName));
		System.out.println(DB.getDocumentNo(117, trxName));
		System.out.println(DB.getDocumentNo(118, trxName));
		System.out.println(DB.getDocumentNo(118, trxName));
		System.out.println(DB.getDocumentNo(117, trxName));

		trxName = "test1";
		System.out.println(DB.getDocumentNo(115, trxName));	//	hangs here as supposed
		System.out.println(DB.getDocumentNo(116, trxName));
		System.out.println(DB.getDocumentNo(117, trxName));
		System.out.println(DB.getDocumentNo(118, trxName));





		/** **/

		/** Time Test	*/
		long time = System.currentTimeMillis();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++)
		{
			Runnable r = new GetIDs(i);
			threads[i] = new Thread(r);
			threads[i].start();
		}
		for (int i = 0; i < 10; i++)
		{
			try
			{
				threads[i].join();
			}
			catch (InterruptedException e)
			{
			}
		}
		time = System.currentTimeMillis() - time;

		System.out.println("-------------------------------------------");
		System.out.println("Size=" + s_list.size() + " (should be 1000)");
		Integer[] ia = new Integer[s_list.size()];
		s_list.toArray(ia);
		Arrays.sort(ia);
		Integer last = null;
		int duplicates = 0;
		for (int i = 0; i < ia.length; i++)
		{
			if (last != null)
			{
				if (last.compareTo(ia[i]) == 0)
				{
				//	System.out.println(i + ": " + ia[i]);
					duplicates++;
				}
			}
			last = ia[i];
		}
		System.out.println("-------------------------------------------");
		System.out.println("Size=" + s_list.size() + " (should be 1000)");
		System.out.println("Duplicates=" + duplicates);
		System.out.println("Time (ms)=" + time + " - " + ((float)time/s_list.size()) + " each" );
		System.out.println("-------------------------------------------");



		/** **
		try
		{
			int retValue = -1;
			Connection conn = DB.getConnectionRW ();
		//	DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		//	Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@//dev2:1521/dev2", "adempiere", "adempiere");

			conn.setAutoCommit(false);
			String sql = "SELECT CurrentNext, CurrentNextSys, IncrementNo "
				+ "FROM AD_Sequence "
				+ "WHERE Name='AD_Sequence' ";
			sql += "FOR UPDATE";
			//	creates ORA-00907: missing right parenthesis
		//	sql += "FOR UPDATE OF CurrentNext, CurrentNextSys";


			PreparedStatement pstmt = conn.prepareStatement(sql,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("AC=" + conn.getAutoCommit() + ", RO=" + conn.isReadOnly()
				+ " - Isolation=" + conn.getTransactionIsolation() + "(" + Connection.TRANSACTION_READ_COMMITTED
				+ ") - RSType=" + pstmt.getResultSetType() + "(" + ResultSet.TYPE_SCROLL_SENSITIVE
				+ "), RSConcur=" + pstmt.getResultSetConcurrency() + "(" + ResultSet.CONCUR_UPDATABLE
				+ ")");

			if (rs.next())
			{
				int IncrementNo = rs.getInt(3);
				retValue = rs.getInt(1);
				rs.updateInt(1, retValue + IncrementNo);
				rs.updateRow();
			}
			else
				s_log.severe ("no record found");
			rs.close();
			pstmt.close();
			conn.commit();
			conn.close();
			//
			System.out.println("Next=" + retValue);

		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}

		System.exit(0);

		/** **

		int AD_Client_ID = 0;
		int C_DocType_ID = 115;	//	GL
		String TableName = "C_Invoice";
		String trxName = "x";
		Trx trx = Trx.get(trxName, true);

		System.out.println ("none " + getNextID (0, "Test"));
		System.out.println ("----------------------------------------------");
		System.out.println ("trx1 " + getNextID (0, "Test"));
		System.out.println ("trx2 " + getNextID (0, "Test"));
	//	trx.rollback();
		System.out.println ("trx3 " + getNextID (0, "Test"));
	//	trx.commit();
		System.out.println ("trx4 " + getNextID (0, "Test"));
	//	trx.rollback();
	//	trx.close();
		System.out.println ("----------------------------------------------");
		System.out.println ("none " + getNextID (0, "Test"));
		System.out.println ("==============================================");


		trx = Trx.get(trxName, true);
		System.out.println ("none " + getDocumentNo(AD_Client_ID, TableName, null));
		System.out.println ("----------------------------------------------");
		System.out.println ("trx1 " + getDocumentNo(AD_Client_ID, TableName, trxName));
		System.out.println ("trx2 " + getDocumentNo(AD_Client_ID, TableName, trxName));
		trx.rollback();
		System.out.println ("trx3 " + getDocumentNo(AD_Client_ID, TableName, trxName));
		trx.commit();
		System.out.println ("trx4 " + getDocumentNo(AD_Client_ID, TableName, trxName));
		trx.rollback();
		trx.close();
		System.out.println ("----------------------------------------------");
		System.out.println ("none " + getDocumentNo(AD_Client_ID, TableName, null));
		System.out.println ("==============================================");


		trx = Trx.get(trxName, true);
		System.out.println ("none " + getDocumentNo(C_DocType_ID, null));
		System.out.println ("----------------------------------------------");
		System.out.println ("trx1 " + getDocumentNo(C_DocType_ID, trxName));
		System.out.println ("trx2 " + getDocumentNo(C_DocType_ID, trxName));
		trx.rollback();
		System.out.println ("trx3 " + getDocumentNo(C_DocType_ID, trxName));
		trx.commit();
		System.out.println ("trx4 " + getDocumentNo(C_DocType_ID, trxName));
		trx.rollback();
		trx.close();
		System.out.println ("----------------------------------------------");
		System.out.println ("none " + getDocumentNo(C_DocType_ID, null));
		System.out.println ("==============================================");
		/** **/
	}	//	main

	/** Test		*/
	private static Vector<Integer> s_list = null;

	/**
	 * 	Test Sequence - Get IDs
	 *
	 *  @author Jorg Janke
	 *  @version $Id: MSequence.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
	 */
	public static class GetIDs implements Runnable
	{
		/**
		 * 	Get IDs
		 *	@param i
		 */
		public GetIDs (int i)
		{
			m_i = i;
		}
		private int m_i;

		/**
		 * 	Run
		 */
		public void run()
		{
			for (int i = 0; i < 100; i++)
			{
				try
				{
					int no = DB.getNextID(0, "Test", null);
					s_list.add(new Integer(no));
				//	System.out.println("#" + m_i + ": " + no);
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}
	}	//	GetIDs

	/**
	 *	Get next number for Key column
	 *  @param AD_Client_ID client
	 *  @param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @return next no or (-1=error)
	 */
	public static synchronized int getNextOfficialID_HTTP (String TableName)
	{
		String website = MSysConfig.getValue("DICTIONARY_ID_WEBSITE"); // "http://developer.adempiere.com/cgi-bin/get_ID";
		String prm_USER = MSysConfig.getValue("DICTIONARY_ID_USER");  // "globalqss";
		String prm_PASSWORD = MSysConfig.getValue("DICTIONARY_ID_PASSWORD");  // "password_inseguro";
		String prm_TABLE = TableName;
		String prm_ALTKEY = "";  // TODO: generate alt-key based on key of table
		String prm_COMMENT = MSysConfig.getValue("DICTIONARY_ID_COMMENTS");
		String prm_PROJECT = new String("Adempiere");

		return getNextID_HTTP(TableName, website, prm_USER,
				prm_PASSWORD, prm_TABLE, prm_ALTKEY, prm_COMMENT, prm_PROJECT);
	}

	/**
	 *	Get next number for Key column
	 *  @param AD_Client_ID client
	 *  @param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @return next no or (-1=error)
	 */
	public static synchronized int getNextProjectID_HTTP (String TableName)
	{
		String website = MSysConfig.getValue("PROJECT_ID_WEBSITE"); // "http://developer.adempiere.com/cgi-bin/get_ID";
		String prm_USER = MSysConfig.getValue("PROJECT_ID_USER");  // "globalqss";
		String prm_PASSWORD = MSysConfig.getValue("PROJECT_ID_PASSWORD");  // "password_inseguro";
		String prm_TABLE = TableName;
		String prm_ALTKEY = "";  // TODO: generate alt-key based on key of table
		String prm_COMMENT = MSysConfig.getValue("PROJECT_ID_COMMENTS");
		String prm_PROJECT = MSysConfig.getValue("PROJECT_ID_PROJECT");

		return getNextID_HTTP(TableName, website, prm_USER,
				prm_PASSWORD, prm_TABLE, prm_ALTKEY, prm_COMMENT, prm_PROJECT);
	}

	private static int getNextID_HTTP(String TableName,
			String website, String prm_USER, String prm_PASSWORD,
			String prm_TABLE, String prm_ALTKEY, String prm_COMMENT,
			String prm_PROJECT) {
		StringBuffer read = new StringBuffer();
		int retValue = -1;
		try {
			String completeUrl = website + "?" + "USER="
					+ URLEncoder.encode(prm_USER, "UTF-8") + "&PASSWORD="
					+ URLEncoder.encode(prm_PASSWORD, "UTF-8") + "&PROJECT="
					+ URLEncoder.encode(prm_PROJECT, "UTF-8") + "&TABLE="
					+ URLEncoder.encode(prm_TABLE, "UTF-8") + "&ALTKEY="
					+ URLEncoder.encode(prm_ALTKEY, "UTF-8") + "&COMMENT="
					+ URLEncoder.encode(prm_COMMENT, "UTF-8");

			// Now use the URL class to parse the user-specified URL into
			// its various parts: protocol, host, port, filename.  Check the protocol
			URL url = new URL(completeUrl);
			String protocol = url.getProtocol();
			if (!protocol.equals("http"))
				throw new IllegalArgumentException("URL must use 'http:' protocol");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setAllowUserInteraction(false);
			InputStream is =  conn.getInputStream();

			// Now read the server's response, and write it to the file
			byte[] buffer = new byte[4096];
			int bytes_read;
			while((bytes_read = is.read(buffer)) != -1) {
				for (int i = 0; i < bytes_read; i++) {
					if (buffer[i] != 10)
						read.append((char) buffer[i]);
				}
			}
			conn.disconnect();
			retValue = Integer.parseInt(read.toString());
			if (retValue <= 0)
				retValue = -1;
		}
		catch (Exception e) {    // Report any errors that arise
			System.err.println(e);
			retValue = -1;
		}
		s_log.log(Level.INFO, "getNextID_HTTP - " + TableName + "=" + read + "(" + retValue + ")");

		return retValue;
	}

	private static boolean isExceptionCentralized(String tableName) {
		String [] exceptionTables = new String[] {
				"AD_ACCESSLOG",
				"AD_ALERTPROCESSORLOG",
				"AD_CHANGELOG",
				"AD_ISSUE",
				"AD_LDAPPROCESSORLOG",
				"AD_PACKAGE_IMP",
				"AD_PACKAGE_IMP_BACKUP",
				"AD_PACKAGE_IMP_DETAIL",
				"AD_PACKAGE_IMP_INST",
				"AD_PACKAGE_IMP_PROC",
				"AD_PINSTANCE",
				"AD_PINSTANCE_LOG",
				"AD_PINSTANCE_PARA",
				"AD_REPLICATION_LOG",
				"AD_SCHEDULERLOG",
				"AD_SESSION",
				"AD_WORKFLOWPROCESSORLOG",
				"CM_WEBACCESSLOG",
				"C_ACCTPROCESSORLOG",
				"K_INDEXLOG",
				"R_REQUESTPROCESSORLOG",
				"T_AGING",
				"T_ALTER_COLUMN",
				"T_DISTRIBUTIONRUNDETAIL",
				"T_INVENTORYVALUE",
				"T_INVOICEGL",
				"T_REPLENISH",
				"T_REPORT",
				"T_REPORTSTATEMENT",
				"T_SELECTION",
				"T_SELECTION2",
				"T_SPOOL",
				"T_TRANSACTION",
				"T_TRIALBALANCE"
			};
		for (int i = 0; i < exceptionTables.length; i++) {
			if (tableName.equalsIgnoreCase(exceptionTables[i]))
				return true;
		}

		// don't log selects or insert/update for exception tables (i.e. AD_Issue, AD_ChangeLog)
		return false;
	}

	/**
	 * Get preliminary document no by year
	 * @param tab
	 * @param AD_Sequence_ID
	 * @param dateColumn
	 * @return Preliminary document no
	 */
	public static String getPreliminaryNoByYear(GridTab tab, int AD_Sequence_ID, String dateColumn, String trxName) {
		Date d = (Date)tab.getValue(dateColumn);
		if (d == null)
			d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String calendarYear = sdf.format(d);
		String sql = "select CurrentNext From AD_Sequence_No Where AD_Sequence_ID = ? and CalendarYear = ?";

		return DB.getSQLValueString(trxName, sql, AD_Sequence_ID, calendarYear);
	}

}	//	MSequence