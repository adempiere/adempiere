/**
 *
 */
package org.eevolution.model;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author Alok Ranjan
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 			<li> Refactory and apply ADempiere Best Practice
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
}
