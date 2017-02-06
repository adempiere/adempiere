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
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
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

    public void calculateAge(Properties ctx, int WindowNo, GridTab mTab,
                             GridField mField, Object value) {

        if (value == null || isCalloutActive()) {
            return;
        }

        I_HR_EmployeeDependent employeeDependent = GridTabWrapper.create(mTab, I_HR_EmployeeDependent.class);

        Timestamp date = (Timestamp) value;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthYear = calendar.get(Calendar.YEAR);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int birthday = 365 - calendar.get(Calendar.DAY_OF_YEAR);
        int totalDays = currentDay + birthday;

        if (currentYear < birthYear) {
            mTab.fireDataStatusEEvent("", Msg.getMsg(ctx, "Invalid") + " " + Msg.parseTranslation(ctx,"@Birthday@"), false);
            return;

        }
        Integer age = currentYear - birthYear;
        //if (totalDays >= 365)
        //   age = age + 1;

        employeeDependent.setAge(age.toString());
        return;
    }

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
