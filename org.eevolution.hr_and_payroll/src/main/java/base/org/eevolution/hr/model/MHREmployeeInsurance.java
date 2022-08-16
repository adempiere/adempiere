/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;

/**
 * Created by victor.perez@e-evolution.com,e-Evolution on 04/12/13.
 */
public class MHREmployeeInsurance extends X_HR_EmployeeInsurance {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7702841510176469080L;

	public MHREmployeeInsurance(Properties ctx, int HR_EmployeeInsurance_ID, String trxName) {
        super(ctx, HR_EmployeeInsurance_ID, trxName);
    }

    public MHREmployeeInsurance(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /** Static Cache */
	private static CCache<Integer, List<MHREmployeeInsurance>> insuranceCacheIds = new CCache<Integer, List<MHREmployeeInsurance>>(Table_Name, 30);
    
    
	 /**
     * Get insurance from business partner
     * @param ctx
     * @param bPartnerId
     * @param trxName
     * @return
     */
    public static List<MHREmployeeInsurance> getFromBPartner(Properties ctx, int bPartnerId, String trxName) {
        if(bPartnerId <= 0) {
        	return null;
        }
        List<MHREmployeeInsurance> employeeInsuranceList = insuranceCacheIds.get(bPartnerId);
        if(employeeInsuranceList == null) {
        	final String whereClause = COLUMNNAME_C_BPartner_ID + "=?";
        	employeeInsuranceList = new Query(ctx, Table_Name, whereClause, trxName)
                    .setParameters(bPartnerId)
                    .setOnlyActiveRecords(true)
                    .list();
        	//	Set
        	if(employeeInsuranceList != null) {
        		insuranceCacheIds.put(bPartnerId, employeeInsuranceList);
        	}
        }
        //	
        return employeeInsuranceList;
    }
}
