/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.eevolution.model;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * @author Alok Ranjan
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 			<li> Refactory and apply ADempiere Best Practice
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/753">
 * 		@see FR [ 753 ] Add Business Partner reference on Employee Dependents</a>
 */
public class CalloutEmployee extends CalloutEngine {

	/**
	 * Calculate insurance balance
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 */
    public void calculateInsuranceBalance(Properties ctx, int WindowNo, GridTab mTab,
                                          GridField mField, Object value) {
        if (value == null || isCalloutActive())
            return;

        I_HR_EmployeeInsurance employeeInsurance = GridTabWrapper.create(mTab, I_HR_EmployeeInsurance.class);
        BigDecimal balanceAmount = employeeInsurance.getCoverageAmount().subtract(employeeInsurance.getClaimedAmount());
        employeeInsurance.setBalanceAmount(balanceAmount);
        return;
    }
    
    /**
     * Create A Callout For Last Paid Date Less than The Next Payment Date.
     *
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String validateLastPaidDate(Properties ctx, int WindowNo,
                                       GridTab mTab, GridField mField, Object value) {


        if (isCalloutActive() || value == null)
            return "";

        I_HR_EmployeeInsurance employeeInsurance = GridTabWrapper.create(mTab, I_HR_EmployeeInsurance.class);
        Timestamp payDate = employeeInsurance.getPayDate();
        Timestamp lastPaidDate = (Timestamp) value;
        if (payDate == null)
            return "";

        if (lastPaidDate.before(payDate))
            return "";
        else {
            String message = Msg.getMsg(Env.getCtx(), "Invalid") + "  " + Msg.parseTranslation(ctx , "@PayDate@");
            employeeInsurance.setDateLastPaid((Timestamp) mField.getOldValue());
            mTab.fireDataStatusEEvent(message, null, false);
        }
        return "";

    }

    /**
     * Create A Callout For LastPremium Date  It Should Not Exceed the Next Payment Date.
     *
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String validateLastPremiumDate(Properties ctx, int WindowNo,
                                          GridTab mTab, GridField mField, Object value) {

        if (isCalloutActive() || value == null) {
            return "";
        }

        I_HR_EmployeeInsurance employeeInsurance = GridTabWrapper.create(mTab, I_HR_EmployeeInsurance.class);
        Timestamp payDate = employeeInsurance.getPayDate();
        Timestamp lastPremiumDate = (Timestamp) value;

        if (payDate == null)
            return "";

        if (lastPremiumDate.after(payDate))
            return "";
        else {
            String message = Msg.getMsg(Env.getCtx(), "Invalid") + " " + Msg.parseTranslation(ctx,"@HR_EmployeeInsurance_ID@");
            employeeInsurance.setDateLastPremium((Timestamp) mField.getOldValue());
            mTab.fireDataStatusEEvent(message, null, false);
        }
        return "";

    }
    
	/**
	 * Get Name from Business Partner and set it
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 */
    public void bpartnerRelation(Properties ctx, int WindowNo, GridTab mTab,
                             GridField mField, Object value) {
    	//	
        if (value == null || isCalloutActive()) {
            return;
        }
        //	Get BP
        Integer bPartnerId = (Integer) value;
        if(bPartnerId.intValue() <= 0)
        	return;
        //	Get Names
        MBPartner bPartner = MBPartner.get(ctx, bPartnerId);
        //	Set Name
        String name = bPartner.getName();
        if(bPartner.getName2() != null
        		&& bPartner.getName2().trim().length() > 0) {
        	name = name + " " + bPartner.getName2();
        }
        
        mTab.setValue(I_HR_EmployeeDependent.COLUMNNAME_Name, name);
        //	
        return;
    }
}
