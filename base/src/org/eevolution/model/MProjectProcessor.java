/**
 * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
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

import org.adempiere.core.domains.models.X_C_ProjectProcessor;
import org.compiere.model.AdempiereProcessor;
import org.compiere.model.AdempiereProcessorLog;
import org.compiere.model.MClient;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

/**
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class MProjectProcessor extends X_C_ProjectProcessor  implements AdempiereProcessor {
	
	private static final long serialVersionUID = -3149710397208186523L;

    public MProjectProcessor(Properties ctx, int C_ProjectProcessor_ID, String trxName) {
        super(ctx, C_ProjectProcessor_ID, trxName);
    }

    public MProjectProcessor(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    /**
     * 	Get Active Project Processors
     *	@param ctx context
     *	@return array of Project
     */
    public static MProjectProcessor[] getActive (Properties ctx)
    {
    	List<MProjectProcessor> processors = new Query(ctx , Table_Name , "IsActive='Y'", null)
                .list();
    	MProjectProcessor[] retValue = new MProjectProcessor[processors.size()];
    	processors.toArray(retValue);
    	return retValue;
    }	//	getActive

    /**
     * 	Parent Constructor
     *	@param parent parent
     *	@param supervisorId Supervisor
     */
    public MProjectProcessor (MClient parent, int supervisorId) {
            this (parent.getCtx() , 0, parent.get_TrxName());
            setClientOrg(parent);
            setName (parent.getName() + " - "
                    + Msg.translate(getCtx(), "C_ProjectProcessor_ID"));
            setSupervisor_ID (supervisorId);
    }	//	MProjectProcessor

    /**
     * 	Get Logs
     *	@return Array of Logs
     */
    public AdempiereProcessorLog[] getLogs()
    {
        ArrayList<MProjectProcessorLog> list = new ArrayList<MProjectProcessorLog>();
        String sql = "SELECT * "
                + "FROM C_ProjectProcessorLog "
                + "WHERE C_ProjectProcessor_ID=? "
                + "ORDER BY Created DESC";
        PreparedStatement pstmt = null;
        try
        {
            pstmt = DB.prepareStatement (sql, get_TrxName());
            pstmt.setInt (1, getC_ProjectProcessor_ID());
            ResultSet rs = pstmt.executeQuery ();
            while (rs.next ())
                list.add (new MProjectProcessorLog (getCtx(), rs, get_TrxName()));
            rs.close ();
            pstmt.close ();
            pstmt = null;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, sql, e);
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
        MProjectProcessorLog[] retValue = new MProjectProcessorLog[list.size ()];
        list.toArray (retValue);
        return retValue;
    }	//	getLogs

    /**
     * 	Delete old Project Log
     *	@return number of records
     */
    public int deleteLog()
    {
        if (getKeepLogDays() < 1)
            return 0;
        String sql = "DELETE C_ProjectProcessorLog "
                + "WHERE C_ProjectProcessor_ID=" + getC_ProjectProcessor_ID()
                + " AND (Created+" + getKeepLogDays() + ") < SysDate";
        int no = DB.executeUpdate(sql, get_TrxName());
        return no;
    }	//	deleteLog

    /**
     * 	Get the date Next run
     * 	@param requery requery database
     * 	@return date next run
     */
    public Timestamp getDateNextRun (boolean requery)
    {
        if (requery)
            load(get_TrxName());
        return getDateNextRun();
    }	//	getDateNextRun

    /**
     * 	Get Unique ID
     *	@return Unique ID
     */
    public String getServerID()
    {
        return "ProjectProcessor" + get_ID();
    }	//	getServerID
    
    /**
     * get Logs
     * FR [ 2202 ] 
     * @param whereClause
     * @return
     */
	public MProjectProcessorLog[] getProcessorLogs(String whereClause) {
		whereClause += " AND C_ProjectProcessor_ID = ? ";
    	List<MProjectProcessorLog> logs = new Query(getCtx() , MProjectProcessorLog.Table_Name , whereClause
    							, get_TrxName())
    							.setParameters(getC_ProjectProcessor_ID())
    							.list();
    	MProjectProcessorLog[] retValue = new MProjectProcessorLog[logs.size()];
    	logs.toArray(retValue);
		return retValue;
		
	}
}
