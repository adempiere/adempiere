/**
 *
 */
package org.eevolution.hr.controller;

import org.adempiere.core.api.I_HR_JobOpening;
import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Msg;

import java.sql.Timestamp;
import java.util.Properties;

/**
 * @author Arunkumar
 *         This Class is responsible For Writing Call Outs Which are Related To Job Openings Window.
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 *         <li> Refactory and apply ADempiere Best Practice
 */

public class CalloutJobOpenings extends CalloutEngine {

    /**
     * To Date Should Not Be Less Than The From Date
     *
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value    org.compiere.model.CalloutJobOpenings.checkDateValidation
     */
    public void checkDateValidation(Properties ctx,
                                    int WindowNo,
                                    GridTab mTab,
                                    GridField mField,
                                    Object value) {
        if (isCalloutActive() || value == null)
            return;

        I_HR_JobOpening jobOpening = GridTabWrapper.create(mTab, I_HR_JobOpening.class);

        Timestamp validFrom = jobOpening.getValidFrom();
        Timestamp validTo = jobOpening.getValidTo();
        if (validFrom != null && validTo != null) {

            if (validFrom.after(validTo)) {
                jobOpening.setValidTo(null);
                mTab.fireDataStatusEEvent(
                        Msg.parseTranslation(ctx, "@ValidFrom@") + " " +
                        Msg.getMsg(ctx, "ShouldNotBeGreaterThan") + " " +
                        Msg.parseTranslation(ctx, "@ValidTo@"), "", true);
            }
        }
    }
}
