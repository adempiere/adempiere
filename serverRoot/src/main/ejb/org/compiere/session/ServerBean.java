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
package org.compiere.session;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.ejb.*;
import javax.sql.*;

import org.adempiere.util.ProcessUtil;
import org.compiere.*;
import org.compiere.acct.*;
import org.compiere.model.*;
import org.compiere.process.*;
import org.compiere.util.*;

/**
 * 	Adempiere Server Bean.
 *
 *  @ejb.bean name="adempiere/Server"
 *		display-name="Adempiere Server Session Bean"
 *		type="Stateless"
 *		view-type="both"
 *      transaction-type="Bean"
 *      jndi-name="adempiere/Server"
 *      local-jndi-name="adempiere/ServerLocal"
 *
 *  @ejb.ejb-ref ejb-name="adempiere/Server"
 *  	view-type="both"
 *		ref-name="adempiere/Server"
 *  @ejb.ejb-ref ejb-name="adempiere/Server"
 *  	view-type="local"
 *		ref-name="adempiere/ServerLocal"
 *  
 *  @ejb.permission role-name="adempiereUsers"
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ServerBean.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 *  @author Low Heng Sin
 *  - Added remote transaction management
 *  - Added support to run db process remotely on server
 *  
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL - BF [ 1757523 ]
 */
public class ServerBean implements SessionBean
{
	/**	Context				*/
	private SessionContext 	m_Context;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(ServerBean.class);
	//
	private static int		s_no = 0;
	private int				m_no = 0;
	//
	private int				m_windowCount = 0;
	private int				m_postCount = 0;
	private int				m_processCount = 0;
	private int				m_workflowCount = 0;
	private int				m_paymentCount = 0;
	private int				m_nextSeqCount = 0;
	private int				m_stmt_rowSetCount = 0;
	private int				m_stmt_updateCount = 0;
	private int				m_cacheResetCount = 0;
	private int				m_updateLOBCount = 0;

	/**
	 *  Get and create Window Model Value Object
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param ctx   Environment Properties
	 *  @param WindowNo  number of this window
	 *  @param AD_Window_ID  the internal number of the window, if not 0, AD_Menu_ID is ignored
	 *  @param AD_Menu_ID ine internal menu number, used when AD_Window_ID is 0
	 *  @return initialized Window Model
	 */
	public GridWindowVO getWindowVO (Properties ctx, int WindowNo, int AD_Window_ID, int AD_Menu_ID)
	{
		log.info ("getWindowVO[" + m_no + "] Window=" + AD_Window_ID);
	//	log.fine(ctx);
		GridWindowVO vo = GridWindowVO.create(ctx, WindowNo, AD_Window_ID, AD_Menu_ID);
		m_windowCount++;
		return vo;
	}	//	getWindowVO


	/**
	 *  Post Immediate
	 *  @ejb.interface-method view-type="both"
	 *
	 *	@param	ctx Client Context
	 *  @param  AD_Client_ID    Client ID of Document
	 *  @param  AD_Table_ID     Table ID of Document
	 *  @param  Record_ID       Record ID of this document
	 *  @param  force           force posting
	 *  @param trxName transaction
	 *  @return null, if success or error message
	 */
	public String postImmediate (Properties ctx, 
		int AD_Client_ID, int AD_Table_ID, int Record_ID, boolean force, String trxName)
	{
		log.info ("[" + m_no + "] Table=" + AD_Table_ID + ", Record=" + Record_ID);
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		m_postCount++;
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(ctx, AD_Client_ID);
		return Doc.postImmediate(ass, AD_Table_ID, Record_ID, force, trxName);
	}	//	postImmediate

	/*************************************************************************
	 *  Get Prepared Statement ResultSet
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param info Result info
	 *  @param token Security Token
	 *  @return RowSet
	 *  @throws NotSerializableException
	 */
	public RowSet pstmt_getRowSet (CStatementVO info, SecurityToken token) 
		throws NotSerializableException
	{
		
		validateSecurityToken(token);
		
		if (info.getTrxName() != null) {
			if (Trx.get(info.getTrxName(), false) == null)
				throw new RuntimeException("Transaction lost - " + info.getTrxName());
		}
		
		m_stmt_rowSetCount++;
		CPreparedStatement pstmt = new CPreparedStatement(info);
		RowSet rowset = null;
		try
		{
			rowset =  pstmt.getRowSet();
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch (Exception ex)
			{
				log.log(Level.SEVERE, "Could not close prepared statement", ex);
			}
		}
		return rowset;
	}	//	pstmt_getRowSet

	/**
	 *  Get Statement ResultSet
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param info Result info
	 *  @param token Security Token
	 *  @return RowSet
	 */
	public RowSet stmt_getRowSet (CStatementVO info, SecurityToken token)
	{
		validateSecurityToken(token);
		
		if (info.getTrxName() != null) {
			if (Trx.get(info.getTrxName(), false) == null)
				throw new RuntimeException("Transaction lost - " + info.getTrxName());
		}
		m_stmt_rowSetCount++;
		CPreparedStatement stmt = new CPreparedStatement(info);
		RowSet rowset = null;
		try
		{
			rowset = stmt.getRowSet();
		}
		finally
		{
			DB.close(stmt);
		}
		return rowset;
	}	//	stmt_getRowSet

	/**
	 *  Execute Update
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param info Result info
	 *  @param token Security Token
	 *  @return row count
	 */
	public int stmt_executeUpdate (CStatementVO info, SecurityToken token)
	{
		validateSecurityToken(token);
		
		if (info.getTrxName() != null) {
			if (Trx.get(info.getTrxName(), false) == null)
				throw new RuntimeException("Transaction lost - " + info.getTrxName());
		}
		
		m_stmt_updateCount++;
		CPreparedStatement stmt = null;
		int retVal = -1;				        
		try
		{
			stmt = new CPreparedStatement(info);
			if (info.getParameterCount() > 0)
				stmt.fillParametersFromVO();
			retVal = stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			log.log(Level.SEVERE, info.toString(), e);
			throw new RuntimeException (e);
		}
		finally
		{
			DB.close(stmt);
		}
		return retVal;
	}	//	stmt_executeUpdate
	
	/**
	 * @ejb.interface-method view-type="both"
	 * 
	 * @param info
	 * @param token
	 * @return ExecuteResult
	 */
	public ExecuteResult stmt_execute( org.compiere.util.CStatementVO info,org.compiere.util.SecurityToken token )
	{
		ExecuteResult result = new ExecuteResult();
		if (info.getTrxName() != null) 
		{
			if (Trx.get(info.getTrxName(), false) == null)
				throw new RuntimeException("Transaction lost - " + info.getTrxName());
		}
		
		CPreparedStatement stmt = null;		
		try 
		{
			stmt = new CPreparedStatement(info);
			stmt.fillParametersFromVO();
			boolean b = stmt.execute();
			result.setFirstResult(b);
			while (b) 
			{
				ResultSet rs = stmt.getResultSet();
				result.addResultSet(CCachedRowSet.getRowSet(rs));
				rs.close();
				b = stmt.getMoreResults();
			}
			result.setUpdateCount(stmt.getUpdateCount());
		} 
		catch (SQLException e) 
		{
			log.log(Level.SEVERE, info.toString(), e);
			throw new RuntimeException (e);
		}
		finally
		{
			DB.close(stmt);
		}
		
		return result;
	}
	
	/***
	 * @ejb.interface-method view-type="both"
	 * 
	 * @param info
	 * @param token
	 * @return CallableResult
	 */
	public CallableResult callable_execute( org.compiere.util.CStatementVO info,org.compiere.util.SecurityToken token )
	{
		CallableResult result = new CallableResult();
		
		if (info.getTrxName() != null) {
			if (Trx.get(info.getTrxName(), false) == null)
				throw new RuntimeException("Transaction lost - " + info.getTrxName());
		}
		
		CCallableStatement stmt = null;
		try
		{
			stmt = new CCallableStatement(info);
			stmt.fillParametersFromVO();
			boolean b = stmt.execute();
			result.setFirstResult(b);
			
			//retrieve result set and update count
			while (b) {
				ResultSet rs = stmt.getResultSet();
				result.addResultSet(CCachedRowSet.getRowSet(rs));
				rs.close();
				b = stmt.getMoreResults();
			}
			result.setUpdateCount(stmt.getUpdateCount());
			
			//get ordinal outputparameter value
			ArrayList ordinal = info.getParameters();
			Map ordinalOutput = new HashMap();
			for (int i = 0; i < ordinal.size(); i++) 
			{	
				Object o = ordinal.get(i);
				if (o instanceof OutputParameter)
				{
					OutputParameter output = (OutputParameter)o;
					retrieveOutputParameter(stmt, output, i+1, null);
					ordinalOutput.put((i+1), o);
				}
			}
			result.setOrdinalOutput(ordinalOutput);
			
			//get named output parameter value
			Map named = info.getNamedOutput();
			for (Iterator iter = named.entrySet().iterator(); iter.hasNext(); ) 
			{
				Map.Entry e = (Map.Entry)iter.next();
				String s = (String)e.getKey();
				OutputParameter output = (OutputParameter)e.getValue();
				retrieveOutputParameter(stmt, output, -1, s);				
			}
			result.setNamedOutput(named);
		} catch (SQLException e) {
			log.log(Level.SEVERE, info.toString(), e);
			throw new RuntimeException (e);
		}
		finally
		{
			DB.close(stmt);
		}
		
		return result;
	}
	
	private void retrieveOutputParameter(CallableStatement stmt, OutputParameter o, int i, String s) throws SQLException
	{
		switch (o.getSqlType()) {
		case Types.BIGINT:
			if (i > 0)
				o.setValue(stmt.getLong(i));
			else
				o.setValue(stmt.getLong(s));
			break;
		case Types.BINARY:
		case Types.LONGVARBINARY:
		case Types.VARBINARY:
			if (i > 0)
				o.setValue(stmt.getBytes(i));
			else
				o.setValue(stmt.getBytes(s));
			break;
		case Types.BOOLEAN:
			if (i > 0)
				o.setValue(stmt.getBoolean(i));
			else
				o.setValue(stmt.getBoolean(s));
			break;
		case Types.CHAR:
		case Types.LONGVARCHAR:
		case Types.VARCHAR:
			if (i > 0)
				o.setValue(stmt.getString(i));
			else
				o.setValue(stmt.getString(s));
			break;
		case Types.DATE:
			if (i > 0)
				o.setValue(stmt.getDate(i));
			else
				o.setValue(stmt.getDate(s));
			break;
		case Types.DECIMAL:
		case Types.NUMERIC:
			if (i > 0)
				o.setValue(stmt.getBigDecimal(i));
			else
				o.setValue(stmt.getBigDecimal(s));
			break;
		case Types.DOUBLE:
			if (i > 0)
				o.setValue(stmt.getDouble(i));
			else
				o.setValue(stmt.getDouble(s));
			break;
		case Types.FLOAT:
		case Types.REAL:
			if (i > 0)
				o.setValue(stmt.getFloat(i));
			else
				o.setValue(stmt.getFloat(s));
			break;
		case Types.INTEGER:
		case Types.BIT:
			if (i > 0)
				o.setValue(stmt.getInt(i));
			else
				o.setValue(stmt.getInt(s));
			break;
		case Types.SMALLINT:
		case Types.TINYINT:
			if (i > 0)
				o.setValue(stmt.getShort(i));
			else
				o.setValue(stmt.getShort(s));
			break;
		case Types.TIME:					
			if (i > 0)
				o.setValue(stmt.getTime(i));
			else
				o.setValue(stmt.getTime(s));
			break;
		case Types.TIMESTAMP:
			if (i > 0)
				o.setValue(stmt.getTimestamp(i));
			else
				o.setValue(stmt.getTimestamp(s));
			break;
		default:
			try {
				if (i > 0)
					o.setValue((Serializable)stmt.getObject(i));
				else
					o.setValue((Serializable)stmt.getObject(s));
			} catch (Throwable t) {}
			break;
		}
	}

	/*************************************************************************
	 *	Get next number for Key column = 0 is Error.
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param AD_Client_ID client
	 *  @param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @return next no
	 */
	public int getNextID (int AD_Client_ID, String TableName, String trxName)
	{
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		int retValue = MSequence.getNextID (AD_Client_ID, TableName, trxName);
		log.finer("[" + m_no + "] " + TableName + " = " + retValue);
		m_nextSeqCount++;
		return retValue;
	}	//	getNextID

	/**
	 * 	Get Document No from table
	 *  @ejb.interface-method view-type="both"
	 * 
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 */
	public String getDocumentNo (int AD_Client_ID, String TableName, String trxName)
	{
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		m_nextSeqCount++;
		String dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName);
		if (dn == null)		//	try again
			dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName);
		return dn;
	}	//	GetDocumentNo
	
	/**
	 * 	Get Document No from table
	 *  @ejb.interface-method view-type="both"
	 * 
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @param po
	 *	@return document no or null
	 */
	public String getDocumentNo (int AD_Client_ID, String TableName, String trxName, PO po)
	{
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		m_nextSeqCount++;
		String dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName, po);
		if (dn == null)		//	try again
			dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName, po);
		return dn;
	}	//	GetDocumentNo
	
	/**
	 * 	Get Document No based on Document Type
	 *  @ejb.interface-method view-type="both"
	 * 
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 */
	public String getDocumentNo (int C_DocType_ID, String trxName, boolean definite)
	{
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		m_nextSeqCount++;
		String dn = MSequence.getDocumentNo (C_DocType_ID, trxName, definite);
		if (dn == null)		//	try again
			dn = MSequence.getDocumentNo (C_DocType_ID, trxName, definite);
		return dn;
	}	//	getDocumentNo
	
	/**
	 * 	Get Document No based on Document Type
	 *  @ejb.interface-method view-type="both"
	 * 
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *  @param po
	 *	@return document no or null
	 */
	public String getDocumentNo (int C_DocType_ID, String trxName, boolean definite, PO po)
	{
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		m_nextSeqCount++;
		String dn = MSequence.getDocumentNo (C_DocType_ID, trxName, definite, po);
		if (dn == null)		//	try again
			dn = MSequence.getDocumentNo (C_DocType_ID, trxName, definite, po);
		return dn;
	}	//	getDocumentNo


	/*************************************************************************
	 *  Process Remote
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param ctx Context
	 *  @param pi Process Info
	 *  @return resulting Process Info
	 */
	public ProcessInfo process (Properties ctx, ProcessInfo pi)
	{
		
		m_processCount++;
		
		//	Start Process
		String trxName = pi.getTransactionName();
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		if (trxName == null) trxName = Trx.createTrxName("ServerPrc"); 
		Trx trx = Trx.get(trxName, true);
		ProcessUtil.startJavaProcess(ctx, pi, trx);
		return pi;
	}	//	process


	/*************************************************************************
	 *  Run Workflow (and wait) on Server
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param ctx Context
	 *  @param pi Process Info
	 *  @param AD_Workflow_ID id
	 *  @return process info
	 */
	public ProcessInfo workflow (Properties ctx, ProcessInfo pi, int AD_Workflow_ID)
	{
		log.info ("[" + m_no + "] " + AD_Workflow_ID);
		m_workflowCount++;
		ProcessUtil.startWorkFlow(ctx, pi, AD_Workflow_ID);
		return pi;
	}	//	workflow

	/**
	 *  Online Payment from Server
	 *  @ejb.interface-method view-type="both"
	 *	Called from MPayment processOnline
	 *  @param ctx Context
	 *  @param C_Payment_ID payment
	 *  @param C_PaymentProcessor_ID processor
	 *  @param trxName transaction
	 *  @return true if approved
	 */
	public boolean paymentOnline (Properties ctx, 
		int C_Payment_ID, int C_PaymentProcessor_ID, String trxName)
	{
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		MPayment payment = new MPayment (ctx, C_Payment_ID, trxName);
		MPaymentProcessor mpp = new MPaymentProcessor (ctx, C_PaymentProcessor_ID, null);
		log.info ("[" + m_no + "] " + payment + " - " + mpp);
		m_paymentCount++;
		boolean approved = false;
		try
		{
			PaymentProcessor pp = PaymentProcessor.create(mpp, payment);
			if (pp == null)
				payment.setErrorMessage("No Payment Processor");
			else
			{
				approved = pp.processCC ();
				if (approved)
					payment.setErrorMessage(null);
				else
					payment.setErrorMessage("From " +  payment.getCreditCardName() 
						+ ": " + payment.getR_RespMsg());
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
			payment.setErrorMessage("Payment Processor Error");
		}
		payment.save();	
		return approved;
	}	//	paymentOnline

	/**
	 *  Create EMail from Server (Request User)
	 *  @ejb.interface-method view-type="both"
	 *  @param ctx Context
	 *  @param AD_Client_ID client 
	 *	@param to recipient email address
	 *	@param subject subject
	 *	@param message message
	 *  @return EMail
	 */
	public EMail createEMail (Properties ctx, int AD_Client_ID, 
		String to, String subject, String message)
	{
		MClient client = MClient.get(ctx, AD_Client_ID);
		return client.createEMail(to, subject, message);
	}	//	createEMail

	/**
	 *  Create EMail from Server (Request User)
	 *  @ejb.interface-method view-type="both"
	 *  @param ctx Context
	 *  @param AD_Client_ID client 
	 *  @param AD_User_ID user to send email from
	 *	@param to recipient email address
	 *	@param subject subject
	 *	@param message message
	 *  @return EMail
	 */
	public EMail createEMail (Properties ctx, int AD_Client_ID,
		int AD_User_ID,
		String to, String subject, String message)
	{
		MClient client = MClient.get(ctx, AD_Client_ID);
		MUser from = new MUser (ctx, AD_User_ID, null);
		return client.createEMail(from, to, subject, message);
	}	//	createEMail

	
	/**
	 *  Create EMail from Server (Request User)
	 *  @ejb.interface-method view-type="both"
	 *  @param AD_Task_ID task 
	 *  @return execution trace
	 */
	public String executeTask (int AD_Task_ID)
	{
		MTask task = new MTask (Env.getCtx(), AD_Task_ID, null);	//	Server Context
		return task.execute();
	}	//	executeTask
	
	
	/**
	 *  Cash Reset
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param tableName table name
	 *  @param Record_ID record or 0 for all
	 * 	@return number of records reset
	 */
	public int cacheReset (String tableName, int Record_ID)
	{
		log.config(tableName + " - " + Record_ID);
		m_cacheResetCount++;
		return CacheMgt.get().reset(tableName, Record_ID);
	}	//	cacheReset
	
	/**
	 *  LOB update
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param sql table name
	 *  @param displayType display type (i.e. BLOB/CLOB)
	 * 	@param value the data
	 *  @param trxName
	 *  @param token Security Token
	 * 	@return true if updated
	 */
	public boolean updateLOB (String sql, int displayType, Object value, String trxName, SecurityToken token)
	{
		validateSecurityToken(token);
		
		if (sql == null || value == null)
		{
			log.fine("No sql or data");
			return false;
		}
		log.fine(sql);
		
		Trx trx = null;
		if (trxName != null && trxName.trim().length() > 0) {
			trx = Trx.get(trxName, false); 
			if ( trx == null)
				throw new RuntimeException("Transaction lost - " + trxName);
		}
		
		m_updateLOBCount++;
		boolean success = true;
		Connection con = trx != null ? trx.getConnection() : DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		PreparedStatement pstmt = null;
		try
		{
			pstmt = con.prepareStatement(sql);
			if (displayType == DisplayType.TextLong)
				pstmt.setString(1, (String)value);
			else
				pstmt.setBytes(1, (byte[])value);
			int no = pstmt.executeUpdate();
			//
		}
		catch (Exception e)
		{
			log.log(Level.FINE, sql, e);
			success = false;
		}
		finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		
		//	Success - commit local trx
		if (success && trx == null)
		{
			try
			{
				con.commit();				
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "commit" , e);
				success = false;
			}
			finally
			{
				try {
					con.close();
				} catch (SQLException e) {
				}
				con = null;
			}
		}
		//	Error - roll back
		if (!success)
		{
			log.severe ("rollback");
			if ( trx == null)
			{
				try
				{
					con.rollback();				
				}
				catch (Exception ee)
				{
					log.log(Level.SEVERE, "rollback" , ee);
				}
				finally
				{
					try {
						con.close();
					} catch (SQLException e) {
					}
					con = null;
				}
			}
			else
			{
				trx.rollback();
			}
		}		
		return success;
	}	//	updateLOB

	
	/**************************************************************************
	 * 	Describes the instance and its content for debugging purpose
	 *  @ejb.interface-method view-type="both"
	 *  @ejb.permission unchecked="true"
	 * 	@return Debugging information about the instance and its content
	 */
	public String getStatus()
	{
		StringBuffer sb = new StringBuffer("ServerBean[");
		sb.append(m_no)
			.append("-Window=").append(m_windowCount)
			.append(",Post=").append(m_postCount)
			.append(",Process=").append(m_processCount)
			.append(",NextSeq=").append(m_nextSeqCount)
			.append(",Workflow=").append(m_workflowCount)
			.append(",Payment=").append(m_paymentCount)
			.append(",RowSet=").append(m_stmt_rowSetCount)
			.append(",Update=").append(m_stmt_updateCount)
			.append(",CacheReset=").append(m_cacheResetCount)
			.append(",UpdateLob=").append(m_updateLOBCount)
			.append("]");
		return sb.toString();
	}	//	getStatus


	/**
	 * Set savepoint
	 * @ejb.interface-method view-type="both"
	 * @param trxName
	 * @param savePointName
	 * @return true if success, false otherwise
	 */
	public SavepointVO setSavepoint(String trxName, String savePointName)
	{
		Trx trx = Trx.get(trxName, false);
		if (trx != null) 
		{
			try 
			{
				Savepoint sp = null;
				if (savePointName != null)
					sp = trx.getConnection().setSavepoint(savePointName);
				else
					sp = trx.getConnection().setSavepoint();
				return new SavepointVO(sp);
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e);
			} 
		}
		return null;
	}
	
	/**
	 * Start remote transaction
	 * @ejb.interface-method view-type="both"
	 * @param trxName
	 */
	public void startTransaction(String trxName) {
		Trx trx = Trx.get(trxName, true);
		trx.start();
	}
	
	/**
	 * Close remote transaction
	 * @ejb.interface-method view-type="both"
	 * @param trxName
	 */
	public void closeTransaction(String trxName) {
		Trx trx = Trx.get(trxName, false);
		if (trx != null)
			trx.close();
	}
	
	/**
	 * Commit the named transaction on server
	 * @ejb.interface-method view-type="both"
	 * @param trxName
	 * @return true if success, false otherwise
	 */
	public boolean commit(String trxName)
	{
		boolean success = false;
		Trx trx = Trx.get(trxName, false);
		if (trx != null) 
		{
			try 
			{
				success = trx.commit(true);
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e);
			} 
		}
		return success;
	}
	
	/**
	 * Rollback the named transaction on server
	 * @ejb.interface-method view-type="both"
	 * @param trxName
	 * @return true if success, false otherwise
	 */
	public boolean rollback(String trxName)
	{
		boolean success = false;
		Trx trx = Trx.get(trxName, false);
		if (trx != null)
		{
			try 
			{
				success = trx.rollback(true);
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e);
			}
		}
		return success;
	}
	
	/**
	 * Rollback the named transaction on server
	 * @ejb.interface-method view-type="both"
	 * @param trxName
	 * @return true if success, false otherwise
	 */
	public boolean rollback(String trxName, SavepointVO savePoint)
	{
		boolean success = false;
		Trx trx = Trx.get(trxName, false);
		if (trx != null)
		{
			try 
			{
				trx.getConnection().rollback(savePoint);
				success = true;
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e);
			}
		}
		return success;
	}
	
	/**
	 * Execute db proces on server
	 * @ejb.interface-method view-type="both"
	 * @param processInfo
	 * @param procedureName
	 * @param trxName
	 * @return ProcessInfo
	 */
	public ProcessInfo dbProcess(ProcessInfo processInfo, String procedureName, String trxName, SecurityToken token)
	{
		validateSecurityToken(token);
		
		Trx trx = null;
		if (trxName != null) {
			trx = Trx.get(trxName, false);
			if (trx == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		ProcessUtil.startDatabaseProcedure(processInfo, procedureName, trx);
		return processInfo;
	}
	
	/**
	 * Load fields meta data from database
	 * @ejb.interface-method view-type="both"
	 * @param gridTabVO
	 * @return ArrayList
	 */
	public ArrayList getFields(GridTabVO gridTabVO)
	{
		//this will load all fields meta data from database
		return gridTabVO.getFields();
	}
	
	/**
	 * Get table id from ad_table by table name
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * @param tableName
	 * @return tableName
	 */
	public int getTableID(String tableName)
	{
		return MTable.getTable_ID(tableName);
	}
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		return getStatus();
	}	//	toString

	
	/**************************************************************************
	 * 	Create the Session Bean
	 * 	@throws EJBException
	 * 	@throws CreateException
	 *  @ejb.create-method view-type="both"
	 *  @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws EJBException, CreateException
	{
		m_no = ++s_no;
		try
		{
			if (!Adempiere.startup(false))
				throw new CreateException("Adempiere could not start");
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "ejbCreate", ex);
		//	throw new CreateException ();
		}
		log.info ("#" + getStatus());
	}	//	ejbCreate


	// -------------------------------------------------------------------------
	// Framework Callbacks
	// -------------------------------------------------------------------------

	/**
	 * Method setSessionContext
	 * @param aContext SessionContext
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#setSessionContext(SessionContext)
	 */
	public void setSessionContext (SessionContext aContext) throws EJBException
	{
		m_Context = aContext;
	}	//	setSessionContext

	/**
	 * Method ejbActivate
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException
	{
		if (log == null)
			log = CLogger.getCLogger(getClass());
		log.info ("ejbActivate " + getStatus());
	}	//	ejbActivate

	/**
	 * Method ejbPassivate
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException
	{
		log.info ("ejbPassivate " + getStatus());
	}	//	ejbPassivate

	/**
	 * Method ejbRemove
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException
	{
		log.info ("ejbRemove " + getStatus());
	}	//	ejbRemove


	/**************************************************************************
	 * 	Dump SerialVersionUID of class 
	 *	@param clazz class
	 */
	protected static void dumpSVUID (Class clazz)
	{
		String s = clazz.getName() 
			+ " ==\nstatic final long serialVersionUID = "
			+ java.io.ObjectStreamClass.lookup(clazz).getSerialVersionUID()
			+ "L;\n";
		System.out.println (s);
	}	//	dumpSVUID
	
	/**
	 * Validate security token from client
	 * @param token
	 */
	private void validateSecurityToken(SecurityToken token)
	{
		if (Ini.isServerValidateSecurityToken())
		{
			checkCertificate(token);
			checkCodeBaseHost(token);
		}
	}
	
	/**
	 * Verify client code is sign with the same certificate as server 
	 * @param token
	 */
	private void checkCertificate(SecurityToken token)
	{
		Certificate certs[] =
			this.getClass().getProtectionDomain().getCodeSource().getCertificates();
		if (certs != null && certs.length > 0)
		{
			if (!certs[0].equals(token.getCodeCertificate()))
				throw new RuntimeException("Client not signed or not signed with the same certificate");
		}
	}
	
	/**
	 * Verify client code is loaded from trusted server
	 * @param token
	 */
	private void checkCodeBaseHost(SecurityToken token)
	{
		InetAddress host = null;
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
		}
		String hostName = null;
		String hostAddress = null;
		if (host != null)
		{
			hostName = host.getHostName();
			hostAddress = host.getHostAddress();
		}
		else
		{
			hostName = "localhost";
			hostAddress = "127.0.0.1";
		}
		if (!hostName.equals(token.getCodeBaseHost()) && 
				!hostAddress.equals(token.getCodeBaseHost()))
		{
			throw new RuntimeException("Client code not originated from server.");
		}
	}

	/**
	 * 	Print UID of used classes.
	 * 	R2.5.1h		

org.compiere.process.ProcessInfo ==
static final long serialVersionUID = -1993220053515488725L;

org.compiere.util.CStatementVO ==
static final long serialVersionUID = -3393389471515956399L;

org.compiere.model.MQuery ==
static final long serialVersionUID = 1511402030597166113L;

org.compiere.model.POInfo ==
static final long serialVersionUID = -5976719579744948419L;

org.compiere.model.POInfoColumn ==
static final long serialVersionUID = -3983585608504631958L;

org.compiere.model.MWindowVO ==
static final long serialVersionUID = 3802628212531678981L;

org.compiere.model.MTabVO ==
static final long serialVersionUID = 9160212869277319305L;

org.compiere.model.MFieldVO ==
static final long serialVersionUID = 4385061125114436797L;

org.compiere.model.MLookupInfo ==
static final long serialVersionUID = -7958664359250070233L;

	 *
	 *	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		dumpSVUID(ProcessInfo.class);
		dumpSVUID(CStatementVO.class);
		dumpSVUID(MQuery.class);
		dumpSVUID(POInfo.class);
		dumpSVUID(POInfoColumn.class);
		dumpSVUID(GridWindowVO.class);
		dumpSVUID(GridTabVO.class);
		dumpSVUID(GridFieldVO.class);
		dumpSVUID(MLookupInfo.class);
	}	//	main

}	//	ServerBean
