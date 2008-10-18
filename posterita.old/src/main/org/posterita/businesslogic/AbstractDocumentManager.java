/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
	@author vishee
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MTax;
import org.compiere.util.Env;

import org.posterita.beans.WebDocumentHeaderBean;


public class AbstractDocumentManager 
{
    public static final String REF_PO = "Ref PO No:";
    public static final String COUNTER_REF_SO = "Ref SO No:";
    
    public static final String DEALER_REF = "Dealer Purchase Order No:";
    public static final String DEALER_COUNTER_REF = "Dealer Purchase Order No:";
    
    
    
    public static final String FROM = "From:";
    public static final String TO = "To:";
    
    
    protected static BigDecimal getLineTaxAmt(Properties ctx, BigDecimal lineNetAmt, int taxId, int lineId)
    {
        BigDecimal baseAmt = Env.ZERO;
        
        baseAmt = baseAmt.add(lineNetAmt);
        
        MTax tax = new MTax(ctx, taxId, null);
      
        return (tax.calculateTax(baseAmt, false, 4));
    }
    
    protected static String getSerno(Properties  ctx, int attributeSetInstanceId)
    {
        if (attributeSetInstanceId == 0)
            return "";
        
        MAttributeSetInstance attributeSetInstance = new MAttributeSetInstance(ctx, attributeSetInstanceId, null);
        
        return attributeSetInstance.getSerNo();
    }
    
    protected static WebDocumentHeaderBean createWebDocumentHeader(Properties ctx, int orgId, int bpartnerID, String docStatus, boolean isSoTrx,String paymentType) 
    {
        if (isSoTrx)
            return getSalesOrderHeader(ctx, orgId, bpartnerID, docStatus,paymentType);
        else
            return getPurchaseOrderHeader(ctx, orgId, bpartnerID, docStatus);
    }
    
    protected static WebDocumentHeaderBean getSalesOrderHeader(Properties ctx, int orgId, int bpartnerID, String docStatus,String paymentType) 
    {
        WebDocumentHeaderBean headerBean = new WebDocumentHeaderBean();
        
        headerBean.setDcsRef(COUNTER_REF_SO);
        headerBean.setDcsCounterRef(REF_PO);
        headerBean.setDealerRef(null);
        headerBean.setPaymentType(paymentType);
        
        //set to null because SOReference is not currently available in system
        headerBean.setDealerCounterRef(DEALER_COUNTER_REF);
        //headerBean.setDocumentStatus(order.getZZ_HondaDocStatus());
        headerBean.setDate("Date Ordered:");
        headerBean.setDocumentName("Sales Order");
        
        
        String youName = null;
        MBPartner youPartner = new MBPartner(ctx, bpartnerID, null);
        
        youName = youPartner.getName();
        
        
        headerBean.setYouName(youName);
        
        headerBean.setFrom(TO);
        
        headerBean.setTo(FROM);
        
        return headerBean;
    }
    
    
    
    
    private static WebDocumentHeaderBean getPurchaseOrderHeader(Properties ctx, int orgId, int bpartnerID, String docStatus) 
    {
        WebDocumentHeaderBean headerBean = new WebDocumentHeaderBean();
        
        headerBean.setDcsRef(REF_PO);
        headerBean.setDcsCounterRef(COUNTER_REF_SO);
        headerBean.setDealerRef(DEALER_REF);
        //set to null because SOReference is not currently available in system
        headerBean.setDealerCounterRef(null);
        headerBean.setDocumentStatus(docStatus);
        headerBean.setDate("Date Ordered:");
        headerBean.setDocumentName("Purchase order");
        
        
        MBPartner youBP = new MBPartner(ctx, bpartnerID, null);
        String youName = youBP.getName();
        
        headerBean.setYouName(youName);
        
        headerBean.setFrom(TO);
        
        headerBean.setTo(FROM);
        
        
        return headerBean;
    }
    
}
