/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.model;

import org.adempiere.core.domains.models.X_C_ProjectProcessorLog;
import org.compiere.model.AdempiereProcessorLog;
import org.compiere.model.MProjectProcessorChange;
import org.compiere.model.MProjectProcessorQueued;
import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class MProjectProcessorLog extends X_C_ProjectProcessorLog implements AdempiereProcessorLog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MProjectProcessorLog(Properties ctx, int C_ProjectProcessorLog_ID, String trxName) {
        super(ctx, C_ProjectProcessorLog_ID, trxName);
    }

    public MProjectProcessorLog(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * 	Parent Constructor
     *	@param parent parent
     *	@param summary summary
	 *  @param trxName transaction name
     */
    public MProjectProcessorLog (MProjectProcessor parent, String summary, String trxName)
    {
        this (parent.getCtx(), 0, trxName);
        setClientOrg(parent);
        setC_ProjectProcessor_ID(parent.getC_ProjectProcessor_ID());
        setSummary(summary);
    }
    
    /**
     * Get Queued
     * FR [ 2202 ]
     * @param whereClause
     * @return
     */
    public MProjectProcessorQueued[] getQueued(String whereClause) {
    	if (whereClause !=null
    			&& !whereClause.equals(""))
    		whereClause += " AND ";
    	else 
    		whereClause = "";
    	
    	whereClause += "C_ProjectProcessorLog_ID = ? ";
    	List<MProjectProcessorQueued> queued = new Query(getCtx() , MProjectProcessorQueued.Table_Name , whereClause , get_TrxName())
    			.setParameters(getC_ProjectProcessorLog_ID())
                .list();
    	MProjectProcessorQueued[] retValue = new MProjectProcessorQueued[queued.size()];
    	queued.toArray(retValue);
    	return retValue;
    }
    /**
     * Get Changes
     * FR [ 2202 ]
     * @param whereClause
     * @return
     */
    public MProjectProcessorChange[] getChange(String whereClause) {
    	if (whereClause !=null
    			&& !whereClause.equals(""))
    		whereClause += " AND ";
    	else 
    		whereClause = "";
    	
    	whereClause += "C_ProjectProcessorLog_ID = ? ";
    	List<MProjectProcessorChange> changes = new Query(getCtx() , MProjectProcessorChange.Table_Name , whereClause , get_TrxName())
    			.setParameters(getC_ProjectProcessorLog_ID())
                .list();
    	MProjectProcessorChange[] retValue = new MProjectProcessorChange[changes.size()];
    	changes.toArray(retValue);
    	return retValue;
    }
}
