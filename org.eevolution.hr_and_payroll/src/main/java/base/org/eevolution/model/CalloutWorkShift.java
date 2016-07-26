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

package org.eevolution.model;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

//import org.wtc.util.WTCTimeUtil;

/**
 *
 * @Bug    @author         @CahngeID               @Description
 * 1628		Arunkumar      [20111208:1:52PM]        Added Code To Handle The Situation Of From Time And To Time
 * 													If   I logged In To The System At  Night 9:30
 *  												And The Log out Time Next Day Morning 5:30
 *  												To Handle This Situation We Have To Increment
 *  												Day Of The Month OF To Time to 1
 *
 * 						   [20111209:6:00PM]        We have To use Bigdecimal Instead Of integer , Because We Have
 * 													to deal with minits also
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 			<li> Refactory and apply ADempiere Best Practice
 */
public class CalloutWorkShift extends CalloutEngine {

    /**
     * Calculates The Number Of Hours Between Given Time
     *
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return Bug -1628
     */
    public String calculateHours(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

        if (isCalloutActive() || value == null)
            return "";

        I_HR_WorkShift workShift = GridTabWrapper.create(mTab, I_HR_WorkShift.class);
        Timestamp fromTime = workShift.getShiftFromTime();
        Timestamp toTime = workShift.getShiftToTime();

        if (fromTime == null || toTime == null)
            return "";
        else if (fromTime.after(toTime)) {
            GregorianCalendar gre = (GregorianCalendar) Calendar.getInstance();
            gre.setTimeInMillis(toTime.getTime());
            gre.add(Calendar.DAY_OF_MONTH, 1);
            toTime = new Timestamp(gre.getTimeInMillis());
        }
        //[20111209:6:00]
        long difference = toTime.getTime() - fromTime.getTime();
        if (difference > 3600000) {
            long hoursBetween = difference / 3600000;
            workShift.setNoOfHours(new BigDecimal(hoursBetween));
        }
        return "";
    }

}
