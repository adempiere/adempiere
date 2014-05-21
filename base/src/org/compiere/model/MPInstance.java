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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Process Instance Model
 *
 *  @author Jorg Janke
 *  @version $Id: MPInstance.java,v 1.3 2006/07/30 00:58:36 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 		<li>FR [ 2818478 ] Introduce MPInstance.createParameter helper method
 * 			https://sourceforge.net/tracker/?func=detail&aid=2818478&group_id=176962&atid=879335
 */
public class MPInstance extends X_AD_PInstance
{

    /**
     * 
     */
    private static final long serialVersionUID = -5848424269552679604L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_PInstance_ID instance or 0
	 *	@param ignored no transaction support
	 */
	public MPInstance (Properties ctx, int AD_PInstance_ID, String ignored)
	{
		super (ctx, AD_PInstance_ID, null);
		//	New Process
		if (AD_PInstance_ID == 0)
		{
		//	setAD_Process_ID (0);	//	parent
		//	setRecord_ID (0);
			setIsProcessing (false);
		}
	}	//	MPInstance

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param ignored no transaction support
	 */
	public MPInstance (Properties ctx, ResultSet rs, String ignored)
	{
		super(ctx, rs, null);
	}	//	MPInstance

	/**
	 * 	Create Process Instance from Process and create parameters
	 *	@param process process
	 *	@param Record_ID Record
	 */
	public MPInstance (MProcess process, int Record_ID)
	{
		this (process.getCtx(), 0, null);
		setAD_Process_ID (process.getAD_Process_ID());
		setRecord_ID (Record_ID);
		setAD_User_ID(Env.getAD_User_ID(process.getCtx()));
		if (!save())		//	need to save for parameters
			throw new IllegalArgumentException ("Cannot Save");
		//	Set Parameter Base Info
		MProcessPara[] para = process.getParameters();
		for (int i = 0; i < para.length; i++)
		{
			MPInstancePara pip = new MPInstancePara (this, para[i].getSeqNo());
			pip.setParameterName(para[i].getColumnName());
			pip.setInfo(para[i].getName());
			pip.saveEx();
		}
	}	//	MPInstance

	/**
	 * 	New Constructor
	 *	@param ctx context
	 *	@param AD_Process_ID Process ID
	 *	@param Record_ID record
	 */
	public MPInstance (Properties ctx, int AD_Process_ID, int Record_ID)
	{
		this(ctx, 0, null);
		setAD_Process_ID (AD_Process_ID);
		setRecord_ID (Record_ID);
		setAD_User_ID(Env.getAD_User_ID(ctx));
		setIsProcessing (false);
	}	//	MPInstance
	

	/**	Parameters						*/
	private MPInstancePara[]		m_parameters = null;

	/**
	 * 	Get Parameters
	 *	@return parameter array
	 */
	public MPInstancePara[] getParameters()
	{
		if (m_parameters != null)
			return m_parameters;
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "AD_PInstance_ID=?";
		List <MPInstancePara> list = new Query(getCtx(), I_AD_PInstance_Para.Table_Name, whereClause, null) // @TODO: Review implications of using transaction 
		.setParameters(getAD_PInstance_ID())
		.list();

		//
		m_parameters = new MPInstancePara[list.size()];
		list.toArray(m_parameters);
		return m_parameters;
	}	//	getParameters


	/**	Log Entries					*/
	private ArrayList<MPInstanceLog>	m_log	= new ArrayList<MPInstanceLog>();

	/**
	 *	Get Logs
	 *	@return array of logs
	 */
	public MPInstanceLog[] getLog()
	{
		//	load it from DB
		m_log.clear();
		String sql = "SELECT * FROM AD_PInstance_Log WHERE AD_PInstance_ID=? ORDER BY Log_ID";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_PInstance_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				m_log.add(new MPInstanceLog(rs));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}

		MPInstanceLog[] retValue = new MPInstanceLog[m_log.size()];
		m_log.toArray(retValue);
		return retValue;
	}	//	getLog

	/**
	 *	@param P_Date date
	 *	@param P_ID id
	 *	@param P_Number number
	 *	@param P_Msg msg
	 */
	public void addLog (Timestamp P_Date, int P_ID, BigDecimal P_Number, String P_Msg)
	{
		MPInstanceLog logEntry = new MPInstanceLog (getAD_PInstance_ID(), m_log.size()+1,
			P_Date, P_ID, P_Number, P_Msg);
		m_log.add(logEntry);
		//	save it to DB ?
	//	log.saveEx();
	}	//	addLog

	
	/**
	 * 	Set AD_Process_ID.
	 * 	Check Role if process can be performed
	 *	@param AD_Process_ID process
	 */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		int AD_Role_ID = Env.getAD_Role_ID(getCtx());
		if (AD_Role_ID != 0)
		{
			MRole role = MRole.get(getCtx(), AD_Role_ID);
			Boolean access = role.getProcessAccess(AD_Process_ID);
			if (access == null || !access.booleanValue())
				throw new IllegalAccessError("Cannot access Process " + AD_Process_ID
					+ " with role: " + role.getName());
		}
		super.setAD_Process_ID (AD_Process_ID);
	}	//	setAD_Process_ID

	/**
	 * 	Set Record ID.
	 * 	direct internal record ID
	 * 	@param Record_ID record
	 **/
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
		{
			log.info("Set to 0 from " + Record_ID);
			Record_ID = 0;
		}
		set_ValueNoCheck ("Record_ID", new Integer(Record_ID));
	}	//	setRecord_ID

	/**
	 * 	String Representation
	 *	@see java.lang.Object#toString()
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPInstance[")
			.append (get_ID())
			.append(",OK=").append(isOK());
		String msg = getErrorMsg();
		if (msg != null && msg.length() > 0)
			sb.append(msg);
		sb.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Dump Log
	 */
	public void log()
	{
		log.info(toString());
		MPInstanceLog[] pil = getLog();
		for (int i = 0; i < pil.length; i++)
			log.info(i + "=" + pil[i]);
	}	//	log

	/** Result OK = 1			*/
	public static final int		RESULT_OK = 1;
	/** Result FALSE = 0		*/
	public static final int		RESULT_ERROR = 0;

	/**
	 * 	Is it OK
	 *	@return Result == OK
	 */
	public boolean isOK()
	{
		return getResult() == RESULT_OK;
	}	//	isOK
	
	/**
	 * 	Set Result
	 *	@param ok 
	 */
	public void setResult (boolean ok)
	{
		super.setResult (ok ? RESULT_OK : RESULT_ERROR);
	}	//	setResult
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Update Statistics
		if (!newRecord 
			&& !isProcessing()
			&& is_ValueChanged("IsProcessing"))
		{
			long ms = System.currentTimeMillis() - getCreated().getTime();
			int seconds = (int)(ms / 1000);
			if (seconds < 1)
				seconds = 1;
			MProcess prc = MProcess.get(getCtx(), getAD_Process_ID());
			prc.addStatistics(seconds);
			if (prc.get_ID() != 0 && prc.save())
				log.fine("afterSave - Process Statistics updated Sec=" + seconds);
			else
				log.warning("afterSave - Process Statistics not updated");
		}
		return success;
	}	//	afterSave
	
	/**
	 * Create Process Instance Parameter and save to database
	 * @param seqNo parameter sequence#
	 * @param parameterName parameter name
	 * @param value parameter value
	 * @return
	 */
	public MPInstancePara createParameter(int seqNo, String parameterName, Object value)
	{
		MPInstancePara ip = new MPInstancePara(this, seqNo);
		if (value == null)
		{
			ip.setParameter(parameterName, (String)null);
		}
		else if (value instanceof BigDecimal)
		{
			ip.setParameter(parameterName, (BigDecimal)value);
		}
		else if (value instanceof Integer)
		{
			ip.setParameter(parameterName, (Integer)value);
		}
		else if (value instanceof Timestamp)
		{
			ip.setParameter(parameterName, (Timestamp)value);
		}
		else if (value instanceof Boolean)
		{
			ip.setParameter(parameterName, (Boolean)value);
		}
		else
		{
			ip.setParameter(parameterName, value.toString());
		}
		//
		ip.saveEx();
		return ip;
	}
}	//	MPInstance
