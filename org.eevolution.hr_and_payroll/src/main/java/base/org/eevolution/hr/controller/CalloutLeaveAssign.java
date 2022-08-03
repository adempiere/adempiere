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
 * Copyright(C) Walking Tree Consultancy Services Pvt Ltd,All Rights Reserved *
 * Contributor(s):                                                            *
 *****************************************************************************/
package org.eevolution.hr.controller;

import org.adempiere.core.api.I_HR_LeaveAssign;
import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.eevolution.hr.model.MHRLeaveType;

import java.util.Properties;

/**
 * @author Arunkumar
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 *         <li> Refactory and apply ADempiere Best Practice
 */
public class CalloutLeaveAssign extends CalloutEngine {
    // Kindly do not delete below line as it is being used for svn version maintenance
    public static final String svnRevision = "$Id: CalloutLeaveAssign.java 1009 2012-02-09 09:16:13Z suman $";

    /**
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     */
    public String leaveAssigned(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        if (isCalloutActive() || value == null)
            return "";
        I_HR_LeaveAssign leaveAssign = GridTabWrapper.create(mTab, I_HR_LeaveAssign.class);
        leaveAssign.setTotalLeaves(leaveAssign.getNoOfLeavesAllocated());
        leaveAssign.setBalance(leaveAssign.getTotalLeaves() - leaveAssign.getUsedLeaves());
        return "";
    }    //	planned

    /**
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     */

    public String addLeave(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        // Set QtyBook from first storage location
        if (isCalloutActive() || value == null)
            return "";
        
        I_HR_LeaveAssign leaveAssign = GridTabWrapper.create(mTab, I_HR_LeaveAssign.class);
        
        MHRLeaveType leaveType = new MHRLeaveType(ctx, leaveAssign.getHR_LeaveType_ID(), null);
        leaveAssign.setTotalLeaves(leaveType.getNoOfLeavesAllocated());
        leaveAssign.setBalance(leaveType.getNoOfLeavesAllocated());
        leaveAssign.setNoOfLeavesAllocated(leaveType.getNoOfLeavesAllocated());
        return "";
    }


}
