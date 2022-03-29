/**
 * Copyright (C) 2003-2022, e-Evolution. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.model;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Requisition Tax
 */
public class MRequisitionTax extends X_M_RequisitionTax {
    /**
     * Tax
     */
    private MTax tax = null;
    private MRequisition parent = null;
    /**	Logger							*/
    protected static CLogger log = CLogger.getCLogger (MRequisitionTax.class);

    /**
     * Requisition Tax
     * @param ctx
     * @param M_RequisitionTax_ID
     * @param trxName
     */
    public MRequisitionTax(Properties ctx, int M_RequisitionTax_ID, String trxName) {
        super(ctx, M_RequisitionTax_ID, trxName);
    }

    /**
     * Requisition Tax
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MRequisitionTax(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Get Tax
     * @return
     */
    protected MTax getTax() {
        if (tax == null)
            tax = MTax.get(getCtx(), getC_Tax_ID());
        return tax;
    }    //	getTax

    /**
     * Get Tax
     * @param line
     * @param precision
     * @param oldTax
     * @param trxName
     * @return
     */
    public static MRequisitionTax get (MRequisitionLine line, int precision, boolean oldTax, String trxName) {
        MRequisitionTax requisitionTax = null;
        if (line == null || line.getM_Requisition_ID() == 0)
        {
            log.fine("No Requisition");
            return null;
        }
        int taxId = line.getC_Tax_ID();
        boolean isOldTax = oldTax && line.is_ValueChanged(MRequisitionTax.COLUMNNAME_C_Tax_ID);
        if (isOldTax)
        {
            Object old = line.get_ValueOld(MRequisitionTax.COLUMNNAME_C_Tax_ID);
            if (old == null)
            {
                log.fine("No Old Tax");
                return null;
            }
            taxId = ((Integer)old).intValue();
        }
        if (taxId == 0)
        {
            log.fine("No Tax");
            return null;
        }

        requisitionTax = new Query(line.getCtx() , MRequisitionTax.Table_Name , "M_Requisition_ID = ? AND C_Tax_ID = ?" , trxName)
                .setClient_ID()
                .setParameters(line.getM_Requisition_ID() , taxId)
                .first();

        if (requisitionTax != null) {
            requisitionTax.set_TrxName(trxName);
            log.fine("(old=" + oldTax + ") " + requisitionTax);
            return requisitionTax;
        }
        else {
            if (isOldTax)
                return null;
        }
        //	Create New
        requisitionTax = new MRequisitionTax(line.getCtx(), 0, trxName);
        requisitionTax.set_TrxName(trxName);
        requisitionTax.setClientOrg(line);
        requisitionTax.setM_Requisition_ID(line.getM_Requisition_ID());
        requisitionTax.setC_Tax_ID(line.getC_Tax_ID());
        log.fine("(new) " + requisitionTax);
        return requisitionTax;
    }	//	get

    /**
     * Calculate Tax From Lines
     * @return
     */
    public boolean calculateTaxFromLines() {
        BigDecimal taxBaseAmt = Env.ZERO;
        BigDecimal taxAmt = Env.ZERO;
        boolean documentLevel = getTax().isDocumentLevel();

        for (MRequisitionLine requisitionLine : getParent().getLines()) {
            taxBaseAmt = taxBaseAmt.add(requisitionLine.getLineNetAmt());
            if (!documentLevel)        // calculate line tax
                taxAmt = taxAmt.add(tax.calculateTax(requisitionLine.getLineNetAmt(), isTaxIncluded(), parent.getPrecision()));
        }

        if (taxBaseAmt == null)
            return false;

        //	Calculate Tax
        if (documentLevel)        //	document level
            taxAmt = tax.calculateTax(taxBaseAmt, isTaxIncluded(), parent.getPrecision());
        setTaxAmt(taxAmt);

        //	Set Base
        if (isTaxIncluded())
            setTaxBaseAmt(taxBaseAmt.subtract(taxAmt));
        else
            setTaxBaseAmt(taxBaseAmt);
        log.fine(toString());
        return true;
    }    //	calculateTaxFromLines

    /**
     * Get Parent
     * @return
     */
    public MRequisition getParent() {
        if (parent != null)
            return parent;

        parent = new MRequisition(getCtx(), getM_Requisition_ID(), get_TrxName());
        return parent;
    }
}
