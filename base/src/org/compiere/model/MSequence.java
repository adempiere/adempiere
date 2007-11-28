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
package org.compiere.model;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import java.io.*;
import java.net.*;

import org.compiere.db.CConnection;
import org.compiere.interfaces.Server;
import org.compiere.util.*;

/**
 *	Sequence Model.
 *	@see org.compiere.process.SequenceCheck
 *  @author Jorg Janke
 *  @version $Id: MSequence.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MSequence extends X_AD_Sequence
{
	/** Use SQL procedure to get next id			*/
	  //begin vpj-cd e-evolution 02/11/2005  PostgreSQL  
	//private static final boolean USE_PROCEDURE = true;
	private static boolean USE_PROCEDURE = false;
	//end vpj-cd e-evolution 02/11/2005
	/** Log Level for Next ID Call					*/
	private static final Level LOGLEVEL = Level.ALL;
	
	
	/**
	 *	Get next number for Key column = 0 is Error.
	 *  @param AD_Client_ID client
	 *  @param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @return next no or (-1=not found, -2=error)
	 */
	public static synchronized int getNextID (int AD_Client_ID, String TableName, String trxName)
	{
		if (TableName == null || TableName.length() == 0)
			throw new IllegalArgumentException("TableName missing");
		
		//get from server
		if (DB.isRemoteObjects())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					int id = server.getNextID(AD_Client_ID, TableName, trxName);
					s_log.finest("server => " + id);
					if (id < 0)
						throw new DBException("No NextID");
					return id;
				}
				s_log.log(Level.SEVERE, "AppsServer not found - " + TableName); 
			}
			catch (RemoteException ex)
			{
				s_log.log(Level.SEVERE, "AppsServer error", ex);
			}
			//	Try locally
		}
		
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
			+ " AND IsActive='Y' AND IsTableID='Y' AND IsAutoSequence='Y' "
			+ "FOR UPDATE";	// jz derby needs expicitly said it//OF CurrentNext, CurrentNextSys";
			
			USE_PROCEDURE = true;
		}
		
		Trx trx = trxName == null ? null : Trx.get(trxName, true);
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean autocommit = false;
		for (int i = 0; i < 3; i++)
		{
			try
			{
				if (trx != null)
					conn = trx.getConnection();
				else
					conn = DB.getConnectionID();
				//	Error
				if (conn == null)
					return -1;
				//
				//jz auto commit off here
				autocommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(selectSQL,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, TableName);
				//
				ResultSet rs = pstmt.executeQuery();
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
					boolean hasEntityType = false;
					if (table.getColumn("EntityType") != null)
						hasEntityType = true;
					
					int AD_Sequence_ID = rs.getInt(4);
					boolean gotFromHTTP = false;

					// If maintaining official dictionary try to get the ID from http official server
					if (adempiereSys) {

						String isUseCentralizedID = MSysConfig.getValue("DICTIONARY_ID_USE_CENTRALIZED_ID", "Y"); // defaults to Y
						if (! isUseCentralizedID.equals("N")) {
							// get ID from http site
							retValue = getNextOfficialID_HTTP(TableName);
							if (retValue > 0) {
								PreparedStatement updateSQL;
								updateSQL = conn.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = ? + 1 WHERE AD_Sequence_ID = ?");
								updateSQL.setInt(1, retValue);
								updateSQL.setInt(2, AD_Sequence_ID);
								updateSQL.executeUpdate();
								updateSQL.close();
							}
							gotFromHTTP = true;
						}

					}

					// If not official dictionary try to get the ID from http custom server - if configured
					if (hasEntityType && ! adempiereSys) {

						String isUseProjectCentralizedID = MSysConfig.getValue("PROJECT_ID_USE_CENTRALIZED_ID", "N"); // defaults to N
						if (isUseProjectCentralizedID.equals("Y")) {
							// get ID from http site
							retValue = getNextProjectID_HTTP(TableName);
							if (retValue > 0) {
								PreparedStatement updateSQL;
								updateSQL = conn.prepareStatement("UPDATE AD_Sequence SET CurrentNext = GREATEST(CurrentNext, ? + 1) WHERE AD_Sequence_ID = ?");
								updateSQL.setInt(1, retValue);
								updateSQL.setInt(2, AD_Sequence_ID);
								updateSQL.executeUpdate();
								updateSQL.close();
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
							updateSQL.setInt(1, incrementNo);
							updateSQL.setInt(2, AD_Sequence_ID);
							updateSQL.executeUpdate();
							updateSQL.close();
						} 
					}
					
					if (trx == null)
						conn.commit();
				}
				else
					s_log.severe ("No record found - " + TableName);
				rs.close();
				pstmt.close();
				pstmt = null;
				conn.setAutoCommit(autocommit); //jz set back
				//
				if (trx == null && conn != null)
					conn.close();
				conn = null;
				//
				break;		//	EXIT
			}
			catch (Exception e)
			{
				s_log.log(Level.SEVERE, TableName + " - " + e.getMessage(), e);
				try 
				{
					conn.rollback();
					conn.setAutoCommit(autocommit); //jz set back
					if (pstmt != null)
						pstmt.close();
				}
				catch (SQLException e1) 
				{
				}
			}
			Thread.yield();		//	give it time
		}
		//	Finish
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		//	if (conn != null)
		//		conn.close();
			conn = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "Finish", e);
			pstmt = null;
		}
		s_log.finest (retValue + " - Table=" + TableName + " [" + trx + "]");
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
			cstmt.execute();
			retValue = cstmt.getInt(3);
			cstmt.close();
			cstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, e.toString());
		}
		//	Finish
		try
		{
			if (cstmt != null)
				cstmt.close();
		}
		catch (Exception e)
		{
		}
		return retValue;
	}	//	nextID
	
	/**************************************************************************
	 * 	Get Document No from table
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 */
	public static synchronized String getDocumentNo (int AD_Client_ID, String TableName, String trxName)
	{
		if (TableName == null || TableName.length() == 0)
			throw new IllegalArgumentException("TableName missing");

		//get from server
		if (DB.isRemoteObjects())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					String dn = server.getDocumentNo (AD_Client_ID, TableName, trxName);
					s_log.finest("Server => " + dn);
					if (dn != null)
						return dn;
				}
				s_log.log(Level.SEVERE, "AppsServer not found - " + TableName); 
			}
			catch (RemoteException ex)
			{
				s_log.log(Level.SEVERE, "AppsServer error", ex);
			}
		}
		
		//local
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
			selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, AD_Sequence_ID "	
				+ "FROM AD_Sequence "
				+ "WHERE Name=?"
				//jz fix duplicated nextID  + " AND AD_Client_ID IN (0,?)"
				+ " AND AD_Client_ID = ?"
				+ " AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' "
				+ "ORDER BY AD_Client_ID DESC "
				+ " FOR UPDATE OF AD_Sequence ";						
			USE_PROCEDURE=false;
		}	
		else
		{
			//String selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, AD_Sequence_ID "
			selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, AD_Sequence_ID "	
				//end vpj-cd e-evolution 09/02/2005	PostgreSQL
				+ "FROM AD_Sequence "
				+ "WHERE Name=?"
				//jz fix duplicated nextID  + " AND AD_Client_ID IN (0,?)"
				+ " AND AD_Client_ID = ?"
				+ " AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' "
				+ " ORDER BY AD_Client_ID DESC ";
			USE_PROCEDURE = true;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		Trx trx = trxName == null ? null : Trx.get(trxName, true);
		//
		int AD_Sequence_ID = 0;
		int incrementNo = 0;
		int next = -1;
		String prefix = "";
		String suffix = "";
		try
		{
			if (trx != null)
				conn = trx.getConnection();
			else
				conn = DB.getConnectionID();
			//	Error
			if (conn == null)
				return null;
			//
			pstmt = conn.prepareStatement(selectSQL,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, PREFIX_DOCSEQ + TableName);
			pstmt.setInt(2, AD_Client_ID);
			//
			ResultSet rs = pstmt.executeQuery();
		//	s_log.fine("AC=" + conn.getAutoCommit() + " -Iso=" + conn.getTransactionIsolation() 
		//		+ " - Type=" + pstmt.getResultSetType() + " - Concur=" + pstmt.getResultSetConcurrency());
			if (rs.next())
			{
				AD_Sequence_ID = rs.getInt(6);
				prefix = rs.getString(4);
				suffix = rs.getString(5);
				incrementNo = rs.getInt(3);
				if (USE_PROCEDURE)
				{
					next = nextID(conn, AD_Sequence_ID, adempiereSys);
				}
				else
				{
					PreparedStatement updateSQL;
					if (adempiereSys) {
						updateSQL = conn
								.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + ? WHERE AD_Sequence_ID = ?");
						next = rs.getInt(2);
					} else {
						updateSQL = conn
								.prepareStatement("UPDATE AD_Sequence SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ?");
						next = rs.getInt(1);
					}
					updateSQL.setInt(1, incrementNo);
					updateSQL.setInt(2, AD_Sequence_ID);
					updateSQL.executeUpdate();
					updateSQL.close();
				} 
			}
			else
			{
				s_log.warning ("(Table) - no record found - " + TableName);
				MSequence seq = new MSequence (Env.getCtx(), AD_Client_ID, TableName, null);
				next = seq.getNextID();
				seq.save();
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			//	Commit
			if (trx == null)
			{
				conn.commit();
				conn.close();
			}
			conn = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "(Table) [" + trxName + "]", e);
			next = -2;
		}
		//	Finish
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
			if (trx == null && conn != null)
				conn.close();
			conn = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "(Table) - finish", e);
			pstmt = null;
		}
		//	Error
		if (next < 0)
			return null;
		
		//	create DocumentNo
		StringBuffer doc = new StringBuffer();
		if (prefix != null && prefix.length() > 0)
			doc.append(prefix);
		doc.append(next);
		if (suffix != null && suffix.length() > 0)
			doc.append(suffix);
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
	 */
	public static synchronized String getDocumentNo (int C_DocType_ID, String trxName)
	{
		if (C_DocType_ID == 0)
		{
			s_log.severe ("C_DocType_ID=0");
			return null;
		}
		
		//get from server
		if (DB.isRemoteObjects())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					String dn = server.getDocumentNo (C_DocType_ID, trxName);
					s_log.finest("Server => " + dn);
					if (dn != null)
						return dn;
				}
				s_log.log(Level.SEVERE, "AppsServer not found - " + C_DocType_ID); 
			}
			catch (RemoteException ex)
			{
				s_log.log(Level.SEVERE, "AppsServer error", ex);
			}
		}
		
		//local
		MDocType dt = MDocType.get (Env.getCtx(), C_DocType_ID);	//	wrong for SERVER, but r/o
		if (dt != null && !dt.isDocNoControlled())
		{
			s_log.finer("DocType_ID=" + C_DocType_ID + " Not DocNo controlled");
			return null;
		}
		if (dt == null || dt.getDocNoSequence_ID() == 0)
		{
			s_log.warning ("No Sequence for DocType - " + dt);
			return null;
		}
			
		//	Check AdempiereSys
		boolean adempiereSys = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		if (CLogMgt.isLevel(LOGLEVEL))
			s_log.log(LOGLEVEL, "DocType_ID=" + C_DocType_ID + " [" + trxName + "]");
		   //begin vpj-cd e-evolution 09/02/2005 PostgreSQL
		String selectSQL = null;
		if (DB.isOracle() == false)
		{	
			selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, AD_Client_ID, AD_Sequence_ID "
				+ "FROM AD_Sequence "
				+ "WHERE AD_Sequence_ID=?"
				+ " AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' "
				+ " FOR UPDATE OF AD_Sequence ";						
			USE_PROCEDURE=false;
		}	
		else		
		{
			selectSQL = "SELECT CurrentNext, CurrentNextSys, IncrementNo, Prefix, Suffix, AD_Client_ID, AD_Sequence_ID "	
                //end vpj-cd e-evolution 09/02/2005	PostgreSQL	
			+ "FROM AD_Sequence "
			+ "WHERE AD_Sequence_ID=?"
			+ " AND IsActive='Y' AND IsTableID='N' AND IsAutoSequence='Y' ";
			USE_PROCEDURE = true;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		Trx trx = trxName == null ? null : Trx.get(trxName, true);
		//
		int AD_Sequence_ID = 0;
		int incrementNo = 0;
		int next = -1;
		String prefix = "";
		String suffix = "";
		try
		{
			if (trx != null)
				conn = trx.getConnection();
			else
				conn = DB.getConnectionID();
			//	Error
			if (conn == null)
				return null;
			//
			pstmt = conn.prepareStatement(selectSQL,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, dt.getDocNoSequence_ID());
			//
			ResultSet rs = pstmt.executeQuery();
		//	s_log.fine("AC=" + conn.getAutoCommit() + " -Iso=" + conn.getTransactionIsolation() 
		//		+ " - Type=" + pstmt.getResultSetType() + " - Concur=" + pstmt.getResultSetConcurrency());
			if (rs.next())
			{
				incrementNo = rs.getInt(3);
				prefix = rs.getString(4);
				suffix = rs.getString(5);
				int AD_Client_ID = rs.getInt(6);
				if (adempiereSys && AD_Client_ID > 11)
					adempiereSys = false;
				AD_Sequence_ID = rs.getInt(7);
				
				if (USE_PROCEDURE)
				{
					next = nextID(conn, AD_Sequence_ID, adempiereSys);
				}
				else
				{
					PreparedStatement updateSQL;
					if (adempiereSys) {
						updateSQL = conn
								.prepareStatement("UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + ? WHERE AD_Sequence_ID = ?");
						next = rs.getInt(2);
					} else {
						updateSQL = conn
								.prepareStatement("UPDATE AD_Sequence SET CurrentNext = CurrentNext + ? WHERE AD_Sequence_ID = ?");
						next = rs.getInt(1);
					}
					updateSQL.setInt(1, incrementNo);
					updateSQL.setInt(2, AD_Sequence_ID);
					updateSQL.executeUpdate();
					updateSQL.close();
				}
			}
			else
			{
				s_log.warning ("(DocType)- no record found - " + dt);
				next = -2;
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			//	Commit
			if (trx == null)
			{
				conn.commit();
				conn.close();
			}
			conn = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "(DocType) [" + trxName + "]", e);
			next = -2;
		}
		//	Finish
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
			if (trx == null && conn != null)
				conn.close();
			conn = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "(DocType) - finish", e);
			pstmt = null;
		}
		//	Error
		if (next < 0)
			return null;

		//	create DocumentNo
		StringBuffer doc = new StringBuffer();
		if (prefix != null && prefix.length() > 0)
			doc.append(prefix);
		doc.append(next);
		if (suffix != null && suffix.length() > 0)
			doc.append(suffix);
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
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
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
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
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
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setString (1, tableName.toUpperCase());
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MSequence (ctx, rs, trxName);
			if (rs.next())
				s_log.log(Level.SEVERE, "More then one sequence for " + tableName);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "get", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
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
	 * 	Get next DocumentNo
	 *	@return document no
	 */
	public String getDocumentNo()
	{
		//	create DocumentNo
		StringBuffer doc = new StringBuffer();
		String prefix = getPrefix();
		if (prefix != null && prefix.length() > 0)
			doc.append(prefix);
		doc.append(getNextID());
		String suffix = getSuffix();
		if (suffix != null && suffix.length() > 0)
			doc.append(suffix);
		return doc.toString();
	}	//	getDocumentNo
	
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
		StringBuffer read = new StringBuffer("");
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
			String host = url.getHost();
			int port = url.getPort();
			if (port == -1) port = 80;  // if no port, use the default HTTP port
			String filename = url.getFile();
			// Open a network socket connection to the specified host and port
			Socket socket = new Socket(host, port);
			// Get input and output streams for the socket
			InputStream from_server = socket.getInputStream();
			PrintWriter to_server = 
				new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Send the HTTP GET command to the Web server, specifying the file.
			// This uses an old and very simple version of the HTTP protocol
			to_server.println("GET " + filename);
			to_server.flush();  // Send it right now!

			// Now read the server's response, and write it to the file
			byte[] buffer = new byte[4096];
			int bytes_read;
			while((bytes_read = from_server.read(buffer)) != -1) {
				for (int i = 0; i < bytes_read; i++) {
					if (buffer[i] != 10)
						read.append((char) buffer[i]);
				}
			}

			// When the server closes the connection, we close our stuff
			socket.close();
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
	
}	//	MSequence