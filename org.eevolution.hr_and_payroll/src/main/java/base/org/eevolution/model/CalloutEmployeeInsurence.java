/**
 * UserName           Bug ID  		ChangeDescription
 *
 * Anitha.K			  980			Create File: Create A Callout For LastPremium Date
 * 												 It Should Not Exceed the 
 * 												  Next Payment Date.
 */
package org.eevolution.model;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.sql.Timestamp;
import java.util.Properties;


/**
 * @author Alok Ranjan
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 *         <li> Refactory and apply ADempiere Best Practice
 */
public class CalloutEmployeeInsurence extends CalloutEngine {

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


}

