/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.eevolution.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eevolution.model.MHRPaySelection;
import org.eevolution.model.MHRPaySelectionCheck;
import org.eevolution.model.MHRPaySelectionLine;
import org.compiere.model.X_C_Order;


/**
 * Create Checks from Payment Selection Line
 * @author ogomezislas
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 */
public class HRPaySelectionCreateCheck extends HRPaySelectionCreateFromAbstract {

    private List<MHRPaySelectionCheck> paySelectionChecks = new ArrayList<>();

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
        getProcessInfo().setTable_ID(MHRPaySelection.Table_ID);
    }    //	prepare

    /**
     * Perform process.
     * @return Message (clear text)
     */
    protected String doIt() {
        log.info("HR_PaySelection_ID=" + getRecord_ID() + ", PaymentRule=" + getPaymentRule());
        MHRPaySelection paySelection = (MHRPaySelection) getInstance(get_TrxName());
        if (paySelection.get_ID() == 0)
            throw new IllegalArgumentException("Not found HR_PaySelection_ID=" + getRecord_ID());
        if (paySelection.isProcessed())
            throw new IllegalArgumentException("@Processed@");

        Arrays.stream(paySelection.getLines(true))
                .filter(paySelectionLine -> paySelectionLine != null && (paySelectionLine.isActive() && !paySelectionLine.isProcessed()))
                .forEach(paySelectionLine -> {
                    createCheck(paySelectionLine);
                });

        paySelection.setProcessed(true);
        paySelection.save();
        return "@C_PaySelectionCheck_ID@ - #" + paySelectionChecks.size();
    }    //	doIt

    /**
     * Create Check from line
     *
     * @param paySelectionLine
     */
    private void createCheck(MHRPaySelectionLine paySelectionLine) {
        paySelectionChecks.stream()
                .filter(paySelectionCheck -> paySelectionCheck.getC_BPartner_ID() == paySelectionLine.getHR_Movement().getC_BPartner_ID())
                .forEach(paySelectionCheck -> {
                    paySelectionCheck.addLine(paySelectionLine);
                    paySelectionCheck.saveEx();
                    paySelectionLine.setHR_PaySelectionCheck_ID(paySelectionCheck.getHR_PaySelectionCheck_ID());
                    paySelectionLine.setProcessed(true);
                    paySelectionCheck.saveEx();
                });
        Optional<String> paymentRuleOptional = Optional.of(paySelectionLine.getPaymentRule());
        MHRPaySelectionCheck paySelectionCheck = new MHRPaySelectionCheck(paySelectionLine, paymentRuleOptional.orElse(MHRPaySelectionCheck.PAYMENTRULE_DirectDeposit));
        paySelectionCheck.setAD_Org_ID(paySelectionLine.getHR_PaySelection().getAD_Org_ID());
        paySelectionCheck.saveEx();
        paySelectionLine.setHR_PaySelectionCheck_ID(paySelectionCheck.getHR_PaySelectionCheck_ID());
        paySelectionLine.setProcessed(true);
        paySelectionLine.saveEx();
        paySelectionChecks.add(paySelectionCheck);
    }    //	createCheck

}    //	HRPaySelectionCreateCheck
