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

import org.adempiere.core.domains.models.X_HR_LeaveAssign;
import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created victor.perez@e-evolution.com, by e-Evolution on 04/12/13.
 */
public class MHRLeaveAssign extends X_HR_LeaveAssign {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8779396920883632575L;

	public static MHRLeaveAssign getByLeaveType(MHRLeaveType leaveType)
    {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMNNAME_HR_LeaveType_ID).append("=?");
        return new Query(leaveType.getCtx(), Table_Name, whereClause.toString(), leaveType.get_TrxName())
                .setClient_ID().setParameters(leaveType.getHR_LeaveType_ID())
                .first();
    }

    public MHRLeaveAssign(Properties ctx, int HR_LeaveAssign_ID, String trxName) {
        super(ctx, HR_LeaveAssign_ID, trxName);
    }

    public MHRLeaveAssign(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Add Used Balance
     * @param usedLeave
     */
    public void addUsedLeave(int usedLeave) {
    	setUsedLeaves(getUsedLeaves() + usedLeave);
    }
    
    @Override
    protected boolean beforeSave(boolean newRecord) {
		if(is_ValueChanged(COLUMNNAME_NoOfLeavesAllocated)) {
			setTotalLeaves(getNoOfLeavesAllocated());
		}
		//	Calculate balance
		setBalance(getTotalLeaves() - getUsedLeaves());
    	return true;
    }

	@Override
	public String toString() {
		return "MHRLeaveAssign [getHR_LeaveAssign_ID()=" + getHR_LeaveAssign_ID() + ", getHR_LeaveType()="
				+ MHRLeaveType.getById(getCtx(), getHR_LeaveType_ID(), get_TrxName()) + "]";
	}
    
    
}
