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
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 *********************************************************************/

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.core.domains.models.I_EXP_ProcessorParameter;
import org.adempiere.core.domains.models.X_EXP_Processor;
import org.adempiere.core.domains.models.X_EXP_ProcessorParameter;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 * @author Trifon N. Trifonov
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> Add cast for parameters
 */
public class MEXPProcessor extends X_EXP_Processor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2925330684523283775L;
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MEXPProcessor.class);
	
	private static MEXPProcessor processor = null;
	private List<MEXPProcessorParameter> parameters = null;
	
	/**
	 * Clean instance
	 */
	public void cleanInstance() {
		processor = null;
	}
	
	public static MEXPProcessor get(Properties ctx, int EXP_Processor_ID, String trxName) {
	    if(processor == null) {
	    	processor = new MEXPProcessor(ctx, EXP_Processor_ID, trxName);
	    }
	    return processor;
	}
	
	public MEXPProcessor(Properties ctx, int EXP_Processor_ID, String trxName) {
		super(ctx, EXP_Processor_ID, trxName);
	}
	
	public MEXPProcessor(Properties ctx, ResultSet rs, String trxName) {
		super (ctx, rs, trxName);
	}
	
	/**
	 * Please use getProcessorParameters instead
	 * Old compatibility
	 * @param trxName
	 * @return
	 */
	public X_EXP_ProcessorParameter[] getEXP_ProcessorParameters(String trxName) {
	    getProcessorParameters();
	    //	
		return parameters.toArray(new X_EXP_ProcessorParameter[0]);
	}

	/**
	 * Get Processor parameter using instance
	 * @return
	 */
	public List<MEXPProcessorParameter> getProcessorParameters() {
		if(parameters != null) {
	    	return parameters;
	    }
	    //	Get list
	    parameters = new Query(getCtx(), I_EXP_ProcessorParameter.Table_Name, I_EXP_ProcessorParameter.COLUMNNAME_EXP_Processor_ID + " = ?", get_TrxName())
	    		.setParameters(getEXP_Processor_ID())
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
        Optional<MEXPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
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
    	Optional<MEXPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
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
    	Optional<MEXPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
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
    	Optional<MEXPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
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
    	Optional<MEXPProcessorParameter> maybeParameter = getProcessorParameters().stream().filter(parameter -> parameter.getValue().equals(key)).findFirst();
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
		return "MEXPProcessor [getEXP_Processor_ID()=" + getEXP_Processor_ID() + ", getName()=" + getName()
				+ ", getValue()=" + getValue() + "]";
	}
}
