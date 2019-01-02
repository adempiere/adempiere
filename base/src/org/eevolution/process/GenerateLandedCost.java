/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2014 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2014 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.process;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.compiere.model.I_M_InOutLine;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLandedCost;
import org.compiere.model.Query;

/**
 * Fill the Landed Cost based on Material Receipts Smart Browser Filter
 *
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class GenerateLandedCost extends GenerateLandedCostAbstract {

    /** Record ID */
    protected List<MInOutLine> records = null;
    /**
     * Get Parameters
     */
    protected void prepare() {
        super.prepare();
    }

    /**
     * Process - Generate Export Format
     *
     * @return info
     */
    protected String doIt() throws Exception {

        String receipts = "";
        if (isCreateByProduct()) {
            for (MInOutLine inOutLine : getRecords()) {
                createLandedCost(null, inOutLine);
                receipts = receipts.concat(
                        inOutLine.getParent().getDocumentNo() + " "
                                + inOutLine.getM_Product().getValue()).concat(" ");
            }
        } else {
            LinkedHashMap<Integer, MInOut> inOutList = new LinkedHashMap<Integer, MInOut>();
            for (MInOutLine inOutLine : getRecords()) {
                MInOut inOut = inOutLine.getParent();
                if (inOutList.containsKey(inOut.getM_InOut_ID()))
                    continue;

                inOutList.put(inOut.getM_InOut_ID(), inOut);
            }

            for (Entry<Integer, MInOut> entry : inOutList.entrySet()) {
                MInOut inOut = entry.getValue();
                createLandedCost(inOut, null);
                receipts = receipts.concat(inOut.getDocumentNo()).concat(" ");
            }
        }
        return receipts;
    }

    public void createLandedCost(MInOut inOut, MInOutLine inOutLine) {
        MInOut document = inOut;
        if (document == null)
            document = inOutLine.getParent();

        MLandedCost landedCost = new MLandedCost(getCtx(), 0, get_TrxName());
        landedCost.setAD_Org_ID(document.getAD_Org_ID());
        landedCost.setC_InvoiceLine_ID(getRecord_ID());
        landedCost.setM_InOut_ID(document.getM_InOut_ID());
        landedCost.setDescription(document.getPOReference());
        landedCost.setLandedCostDistribution(getLandedCostDistribution());
        landedCost.setM_CostElement_ID(getCostElementId());
        landedCost.setC_LandedCostType_ID(getLandedCostTypeId());
        if (inOut == null) {
            landedCost.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
            landedCost.setM_Product_ID(inOutLine.getM_Product_ID());
        }

        landedCost.saveEx();

    }

    private List<MInOutLine> getRecords() {
        if (records != null)
            return records;

        StringBuilder whereClause = new StringBuilder("EXISTS (SELECT T_Selection_ID FROM T_Selection ");
        whereClause.append("WHERE T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=M_InOutLine.M_InOutLine_ID ");
        if (isCreateByProduct())
            whereClause.append("AND NOT EXISTS (SELECT 1 FROM C_LandedCost lc WHERE lc.C_InvoiceLine_ID=? AND lc.M_InOutLine_ID=M_InOutLine.M_InOutLine_ID))");
        else
            whereClause.append("AND NOT EXISTS (SELECT 1 FROM C_LandedCost lc WHERE lc.C_InvoiceLine_ID=? AND lc.M_InOut_ID=M_InOutLine.M_InOut_ID)) ");

        records = new Query(getCtx(), I_M_InOutLine.Table_Name,
                whereClause.toString(), get_TrxName()).setClient_ID()
                .setParameters(getAD_PInstance_ID(), getRecord_ID())
                .list();
        return records;
    }
}