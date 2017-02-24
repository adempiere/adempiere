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

package org.adempiere.process;

import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 * Processes selected orders
 *
 * @author Susanne Calderon Systemhaus Westfalia, El Salvador
 */
public class OrderProcess extends SvrProcess {

    /** Record ID */
    protected int pRecordId = 0;
    protected List<MOrder> records = null;
    private 		String p_DocAction = "";

    /**
     * Get Parameters
     */
    protected void prepare() {
        pRecordId = getRecord_ID();
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if (para.getParameter() == null)
                ;
            else if (name.equals(MOrder.COLUMNNAME_DocAction))
                p_DocAction = para.getParameterAsString();
            else
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
        }
    }

    /**
     * Process - Generate Export Format
     *
     * @return info
     */
    @SuppressWarnings("unchecked")
    protected String doIt() throws Exception {
    	String result = "";
        List<MOrder> orders = (List<MOrder>) getInstancesForSelection(get_TrxName());
        orders.stream().filter(orderLine -> orderLine != null).forEach( order -> {
            processOrder(order);
        });
        return result;
    }
    
    private void processOrder(MOrder order)
    {
    	Boolean canComplete = (order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted)
    			|| order.getDocStatus().equals(MOrder.DOCSTATUS_InProgress)
    			|| order.getDocStatus().equals(MOrder.DOCSTATUS_Invalid))? true:false;        
        Boolean canProcess = (order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted)
    			|| order.getDocStatus().equals(MOrder.DOCSTATUS_Invalid))? true:false;    
        Boolean canClose = (order.getDocStatus().equals(MOrder.DOCSTATUS_Completed))? true:false;
        Boolean actionValid = (p_DocAction.equals(MOrder.DOCACTION_Complete) && canComplete)
        		|| (p_DocAction.equals(MOrder.DOCACTION_Prepare) && canProcess)
        		|| (p_DocAction.equals(MOrder.DOCACTION_Close) && canClose)?true:false;
    	if (actionValid)
    	{
			log.info(order.toString());
			//
			order.setDocAction(p_DocAction);
			if (order.processIt(p_DocAction)) {
				order.saveEx();
				addLog(0, null, null, order.getDocumentNo() + ": OK");
			}
			else {
				addLog(0, null, null, order.getDocumentNo() + ": Error " + order.getProcessMsg());
				throw new AdempiereException(order.getDocumentNo() + ": Error " + order.getProcessMsg());
			}
    	}    	
    }
}