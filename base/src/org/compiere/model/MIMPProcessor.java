/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Trifon Trifonov.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Trifon Trifonov (trifonnt@users.sourceforge.net)                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - E-evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_IMP_ProcessorParameter;
import org.adempiere.core.domains.models.X_IMP_Processor;
import org.adempiere.core.domains.models.X_IMP_ProcessorLog;
import org.adempiere.core.domains.models.X_IMP_ProcessorParameter;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 * @author Trifon Trifonov
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Some methods
 */
public class MIMPProcessor extends X_IMP_Processor implements AdempiereProcessor 
{
	private static final long serialVersionUID = 8634765494025824138L;

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MIMPProcessor.class);
	private static MIMPProcessor processor = null;
	private List<MIMPProcessorParameter> parameters = null;
	
	/**
	 * Clean instance
	 */
	public void cleanInstance() {
		processor = null;
	}
	
	/**
	 * Get instance
	 * @param ctx
	 * @param impProcessorId
	 * @param trxName
	 * @return
	 */
	public static MIMPProcessor get(Properties ctx, int impProcessorId, String trxName) {
	    if(processor == null) {
	    	processor = new MIMPProcessor(ctx, impProcessorId, trxName);
	    }
	    return processor;
	}

	public MIMPProcessor(Properties ctx, int importProcessorId, String trxName) {
		super(ctx, importProcessorId, trxName);
		if (importProcessorId == 0)
		{
			//setValue (/*client.getName() + " - " +*/ "Default Import Processor");
			setName (/*client.getName() + " - " +*/ "Default Import Processor");
			setFrequencyType (FREQUENCYTYPE_Hour);
			setFrequency (1);
			setKeepLogDays (7);
		}
	}

	public MIMPProcessor(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}

	public Timestamp getDateNextRun (boolean requery)
	{
		if (requery)
			load(get_TrxName());
		return getDateNextRun();
	}


	/**
	 * 	Get Logs
	 *	@return logs
	 */
	public AdempiereProcessorLog[] getLogs ()
	{
		ArrayList<MIMPProcessorLog> list = new ArrayList<MIMPProcessorLog>();
		String sql = "SELECT * "
			+ "FROM " + X_IMP_ProcessorLog.Table_Name + " "
			+ "WHERE " + X_IMP_Processor.COLUMNNAME_IMP_Processor_ID + "=? " // # 1 
			+ "ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getIMP_Processor_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MIMPProcessorLog(getCtx(), rs, get_TrxName()));
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		MIMPProcessorLog[] retValue = new MIMPProcessorLog[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getLogs

	/**
	 * 	Delete old Request Log
	 *	@return number of records
	 */
	public int deleteLog()
	{
		if (getKeepLogDays() < 1)
			return 0;
		String sql = "DELETE " + X_IMP_ProcessorLog.Table_Name + " "
			+ "WHERE "+X_IMP_ProcessorLog.COLUMNNAME_IMP_Processor_ID+"=" + getIMP_Processor_ID() 
			+ " AND (Created+" + getKeepLogDays() + ") < SysDate";
		int no = DB.executeUpdate(sql, get_TrxName());
		return no;
	}

	public String getServerID() {
		return "ReplicationProcessor" + get_ID();
	}

	public static MIMPProcessor[] getActive(Properties ctx)
	{
		ArrayList<MIMPProcessor> list = new ArrayList<MIMPProcessor>();
		String sql = "SELECT * FROM "+X_IMP_Processor.Table_Name+" WHERE IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MIMPProcessor(ctx, rs, null));
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}

		MIMPProcessor[] retValue = new MIMPProcessor[list.size()];
		list.toArray(retValue);
		return retValue;
	}
	
	/**
	 * Please use getProcessorParameters instead
	 * Old compatibility
	 * @param trxName
	 * @return
	 */
	public X_IMP_ProcessorParameter[] getIMP_ProcessorParameters(String trxName) {
	    getProcessorParameters();
	    //	
		return parameters.toArray(new X_IMP_ProcessorParameter[0]);
	}

	/**
	 * Get Processor parameter using instance
	 * @return
	 */
	public List<MIMPProcessorParameter> getProcessorParameters() {
		if(parameters != null) {
	    	return parameters;
	    }
	    //	Get list
	    parameters = new Query(getCtx(), I_IMP_ProcessorParameter.Table_Name, I_IMP_ProcessorParameter.COLUMNNAME_IMP_Processor_ID + " = ?", get_TrxName())
	    		.setParameters(getIMP_Processor_ID())
	    		.setOnlyActiveRecords(true)
	    		.list();
	    return parameters;
	}
	
	/**
     *  Get parameter as int
     *  @param Value for cast
     *  @return int value or 0
     */
    public int getParameterAsInt(String key) {
        Optional<MIMPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
        if(maybeParameter.isPresent()) {
        	if(!Util.isEmpty(maybeParameter.get().getParameterValue())) {
        		try {
                    return Integer.parseInt(maybeParameter.get().getParameterValue());
                } catch (NumberFormatException ex) {
                	s_log.warning(ex.getLocalizedMessage());
                }
        	}
        }
        return 0;
    }   //  getParameterAsInt

    /**
     * 	Get Column Value
     *	@param key name
     *	@return value
     */
    public String getParameterAsString(String key) {
    	Optional<MIMPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
        if(maybeParameter.isPresent()) {
        	return maybeParameter.get().getParameterValue();
        }
        return null;
    }	//	getParameterAsString

    /**
     * Get value as Boolean
     * @param Value for cast
     * @return boolean value
     */
    public boolean getValueAsBoolean(String key) {
    	Optional<MIMPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
        if(maybeParameter.isPresent()) {
        	return Util.isEmpty(maybeParameter.get().getParameterValue()) 
        			&& (maybeParameter.get().getParameterValue().equals("Y") || maybeParameter.get().getParameterValue().equals("true"));
        }
        return false;
    }

    /**
     * Get value as big decimal
     * @param key
     * @return
     */
    public BigDecimal getValueAsBigDecimal(String key) {
    	Optional<MIMPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
    	BigDecimal bigDecimalValue = null;
    	if(maybeParameter.isPresent()) {
        	if(!Util.isEmpty(maybeParameter.get().getParameterValue())) {
        		try {
        			bigDecimalValue = new BigDecimal(maybeParameter.get().getParameterValue());
        		} catch (Exception e) {
        			s_log.warning(e.getLocalizedMessage());
				}
        	}
        }
        //  Default
        return bigDecimalValue;
    }

    /**
     * Get value as Timestamp
     * @param Value for cast
     * @return boolean value
     */
    public Timestamp getValueAsDate(String key, String pattern) {
    	Optional<MIMPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
    	if(maybeParameter.isPresent()) {
        	if(!Util.isEmpty(maybeParameter.get().getParameterValue())) {
        		try {
        			return Timestamp.valueOf (maybeParameter.get().getParameterValue());
        		} catch (Exception e) {
        			s_log.warning(e.getLocalizedMessage());
				}
        	}
    	}
        return null;
    }
    
    @Override
    protected boolean afterSave(boolean newRecord, boolean success) {
    	cleanInstance();
    	return super.afterSave(newRecord, success);
    }
    
    @Override
    protected boolean afterDelete(boolean success) {
    	cleanInstance();
    	return super.afterDelete(success);
    }

	@Override
	public String toString() {
		return "MIMPProcessor [getIMP_Processor_ID()=" + getIMP_Processor_ID() + ", getName()=" + getName()
				+ ", getValue()=" + getValue() + "]";
	}
}
