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

package org.adempiere.process;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Generate shipment for Vendor RMA.
 * Based on {@link org.compiere.process.InOutGenerate}.
 * @author Ashley Ramdass
 * @author Teo Sarca
 * 			<li>BF [ 2818523 ] Invoice and Shipment are not matched in case of RMA
 * 				https://sourceforge.net/tracker/?func=detail&aid=2818523&group_id=176962&atid=879332
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * 	<li> Remove old implementation
 * 	https://github.com/adempiere/adempiere/pull/3074
 * 
 */
public class InOutGenerateRMA extends InOutGenerateRMAAbstract {

	/**	Counter	*/
	private AtomicInteger created = new AtomicInteger();
	/**	Document Action	*/
	private String documentAction = DocAction.ACTION_Complete;
	/**	Movement Date	*/
	private Timestamp movementDate = null;
	
    protected void prepare() {
        super.prepare();
        if(getParameterAsString("DocAction") != null) {
        	documentAction = getParameterAsString("DocAction");
		}
		//	DocAction check
		if (!DocAction.ACTION_Complete.equals(documentAction)) {
			documentAction = DocAction.ACTION_Prepare;
		}
        movementDate = Env.getContextAsDate(getCtx(), "#Date");
        if (movementDate == null) {
            movementDate = new Timestamp(System.currentTimeMillis());
        }
    }
    
    protected String doIt() throws Exception {
        if (!isSelection()) {
            throw new AdempiereException("@IsSelection@ @M_RMA_ID@ @IsMandatory@");
        }
        //	Generate
        getSelectionKeys().forEach(rmaId -> generateShipment(rmaId));  
        return "@Created@ = " + created;
    }
    
    private int getShipmentDocTypeId(int M_RMA_ID)  {
        String docTypeSQl = "SELECT dt.C_DocTypeShipment_ID FROM C_DocType dt "
            + "INNER JOIN M_RMA rma ON dt.C_DocType_ID=rma.C_DocType_ID "
            + "WHERE rma.M_RMA_ID=?";
        
        int docTypeId = DB.getSQLValue(null, docTypeSQl, M_RMA_ID);
        
        return docTypeId;
    }
    
    private MInOut createShipment(MRMA rma) {
        int docTypeId = getShipmentDocTypeId(rma.get_ID());
        
        if (docTypeId == -1) {
            throw new AdempiereException("@C_DocTypeShipment_ID@ @M_RMA_ID@ @NotFound@");
        }
        
        MInOut originalReceipt = rma.getShipment();
        
        MInOut shipment = new MInOut(getCtx(), 0, get_TrxName());
        shipment.setM_RMA_ID(rma.get_ID());
        shipment.setAD_Org_ID(rma.getAD_Org_ID());
        shipment.setAD_OrgTrx_ID(originalReceipt.getAD_OrgTrx_ID());
        shipment.setDescription(rma.getDescription());
        shipment.setC_BPartner_ID(rma.getC_BPartner_ID());
        shipment.setC_BPartner_Location_ID(originalReceipt.getC_BPartner_Location_ID());
        shipment.setIsSOTrx(rma.isSOTrx());
        shipment.setC_DocType_ID(docTypeId);       
        shipment.setM_Warehouse_ID(originalReceipt.getM_Warehouse_ID());
        shipment.setMovementType(MInOut.MOVEMENTTYPE_VendorReturns);
        shipment.setC_Project_ID(originalReceipt.getC_Project_ID());
        shipment.setC_Campaign_ID(originalReceipt.getC_Campaign_ID());
        shipment.setC_Activity_ID(originalReceipt.getC_Activity_ID());
        shipment.setUser1_ID(originalReceipt.getUser1_ID());
        shipment.setUser2_ID(originalReceipt.getUser2_ID());
        shipment.setUser3_ID(originalReceipt.getUser3_ID());
        shipment.setUser4_ID(originalReceipt.getUser4_ID());
        //	
        shipment.saveEx();
        return shipment;
    }
    
    /**
     * Create lines for shipment
     * @param rma
     * @param shipment
     * @return
     */
    private MInOutLine[] createShipmentLines(MRMA rma, MInOut shipment) {
        ArrayList<MInOutLine> shipLineList = new ArrayList<MInOutLine>();
        
        MRMALine rmaLines[] = rma.getLines(true);
        for (MRMALine rmaLine : rmaLines) {
            if (rmaLine.getM_InOutLine_ID() != 0) {
                MInOutLine shipLine = new MInOutLine(shipment);
                shipLine.setM_RMALine_ID(rmaLine.get_ID());
                shipLine.setLine(rmaLine.getLine());
                shipLine.setDescription(rmaLine.getDescription());
                shipLine.setM_Product_ID(rmaLine.getM_Product_ID());
                shipLine.setM_AttributeSetInstance_ID(rmaLine.getM_AttributeSetInstance_ID());
                shipLine.setC_UOM_ID(rmaLine.getC_UOM_ID());
                shipLine.setQty(rmaLine.getQty());
                shipLine.setM_Locator_ID(rmaLine.getM_Locator_ID());
                shipLine.setC_Project_ID(rmaLine.getC_Project_ID());
                shipLine.setC_Campaign_ID(rmaLine.getC_Campaign_ID());
                shipLine.setC_Activity_ID(rmaLine.getC_Activity_ID());
                shipLine.setC_ProjectPhase_ID(rmaLine.getC_ProjectPhase_ID());
                shipLine.setC_ProjectTask_ID(rmaLine.getC_ProjectTask_ID());
                shipLine.setUser1_ID(rmaLine.getUser1_ID());
                shipLine.setUser2_ID(rmaLine.getUser2_ID());
                shipLine.setUser3_ID(rmaLine.getUser3_ID());
                shipLine.setUser4_ID(rmaLine.getUser4_ID());
                shipLine.saveEx();
                shipLineList.add(shipLine);
                //
                // Link to corresponding Invoice Line (if any) - teo_sarca [ 2818523 ]
                // The MMatchInv records will be automatically generated on MInOut.completeIt()
            	MInvoiceLine invoiceLine = new Query(shipment.getCtx(), I_C_InvoiceLine.Table_Name,
            			I_C_InvoiceLine.COLUMNNAME_M_RMALine_ID+"=?",
            			shipment.get_TrxName())
            	.setParameters(rmaLine.getM_RMALine_ID())
            	.firstOnly();
            	if (invoiceLine != null) {
            		invoiceLine.setM_InOutLine_ID(shipLine.getM_InOutLine_ID());
            		invoiceLine.saveEx();
            	}

            }
        }
        
        MInOutLine shipLines[] = new MInOutLine[shipLineList.size()];
        shipLineList.toArray(shipLines);
        
        return shipLines;
    }
    
    /**
     * Generate Shipment from RMA ID
     * @param rmaId
     */
    private void generateShipment(int rmaId){
        MRMA rma = new MRMA(getCtx(), rmaId, get_TrxName());
        
        MInOut shipment = createShipment(rma);
        MInOutLine shipmentLines[] = createShipmentLines(rma, shipment);
        
        if (shipmentLines.length == 0) {
            log.log(Level.WARNING, "No shipment lines created: M_RMA_ID="
                    + rmaId + ", M_InOut_ID=" + shipment.get_ID());
        }
        
        StringBuffer processMsg = new StringBuffer(shipment.getDocumentNo());
        
        if (!shipment.processIt(documentAction)){
            processMsg.append(" ").append(shipment.getProcessMsg());
            log.warning("Shipment Processing failed: " + shipment + " - " + shipment.getProcessMsg());
        }
        //	Save
        shipment.saveEx();
        
        // Add processing information to process log
        addLog(shipment.getM_InOut_ID(), shipment.getMovementDate(), null, processMsg.toString());
        created.getAndIncrement();
    }
    
}
