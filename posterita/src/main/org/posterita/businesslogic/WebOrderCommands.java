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
 * 
 * Created on 27-Sep-2005  by alok
 */


package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.process.DocumentEngine;

import org.posterita.Constants;
import org.posterita.beans.CommandBean;
import org.posterita.doctype.UDIDocSubTypeSOValues;
import org.posterita.exceptions.OperationException;
import org.posterita.order.UDIOrderTypes;


public class WebOrderCommands 
{
    public static CommandBean getWebOrderCommands(Properties ctx, MOrder order) throws OperationException
    {
        CommandBean cmdBean ;        
        
        if (order.isSOTrx())
        {
            cmdBean = WebOrderCommands.getSOWebOrderCommands(ctx, order);
        }
        else
        {
            cmdBean = WebOrderCommands.getPOWebOrderCommands(ctx, order);
        }
        
        return cmdBean;
    }
    public static CommandBean getSOWebOrderCommands(Properties ctx, MOrder order) throws OperationException
    {
        String orderType = order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
       

        if (orderType.equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
            return getTmkOrderCommands(order);
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
    }
    
    public static CommandBean getPOWebOrderCommands(Properties ctx, MOrder order) throws OperationException
    { 
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(new String[]{});
        bean.setComplexCommand(new String[]{});
        return bean;
    }
    
    
    private static CommandBean getSO_DOCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
       order.getOrderType();
        
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        //choose vin number
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_SUBMIT};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
       // vin number has been assigned
        if (docStatus.equals(DocumentEngine.STATUS_Approved))
        {
            
            simpleCommands = new String[]{Constants.ORDER_ACTION_PREPARE};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        //vin number has been assigned and reserved
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
        {
            
            simpleCommands = new String[]{Constants.ORDER_ACTION_ACCEPT};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Invalid))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Completed))
        {
            if (!InvoiceManager.allOrderLinesInvoiced(order.getCtx(), order))
            {
            	simpleCommands = new String[]{Constants.GENERATE_INVOICE_ACTION, Constants.INVOICE_PAID_SHIP};
            }
                
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
        
    }
    
    private static CommandBean getSO_SCO_Commands(MOrder order)
    {
        MDocType docType = new MDocType(order.getCtx(),order.getC_DocType_ID(),null);
        
        if (docType.getDocSubTypeSO().equals(UDIDocSubTypeSOValues.DOCTYPESO_Quotation.getDocTypeSubSO()))
            return getSCO_QuotationCommands(order);
        
        
        return getSCO_StandardCommands(order);
        
    }
    
    private static CommandBean getSCO_QuotationCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
        order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            //simpleCommands = new String[]{Constants.ORDER_ACTION_PREPARE_SCO};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        //vin number has been assigned
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
        {
            
            simpleCommands = new String[]{Constants.ORDER_ACTION_GENERATE_ORDER};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Invalid))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(simpleCommands, new String[]{}, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Completed))
        {
            if (!InvoiceManager.allOrderLinesInvoiced(order.getCtx(), order))
                simpleCommands = new String[]{Constants.GENERATE_INVOICE_ACTION};
            //cmdBean = getCommands(simpleCommands, new String[]{}, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
        
        
    }
    
    private static CommandBean getSCO_StandardCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
         order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_PREPARE_SCO_ORDER};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        //vin number has been assigned
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
        {
            
            simpleCommands = new String[]{Constants.ORDER_ACTION_ACCEPT};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
    }
    
    private static CommandBean getWholeSaleOrderCommands(MOrder order)
    {
        
        order.getDocStatus();
        order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        simpleCommands = new String[]{Constants.ORDER_ACTION_ACCEPT_WO};
        complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
        //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
    }
    
    
    
    private static CommandBean getPO_DOCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
        
        String[] simpleCommands = null;
        String[] complexCommands = null; 
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_CONFIRM};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Completed))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }   
        
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
        
    }
    
   /* private static CommandBean getPO_DTCommands(MOrder order) throws OperationException
    {
        String docStatus = order.getDocStatus();
        
        String[] simpleCommands = null;
        String[] complexCommands = null; 
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_CONFIRM};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Completed))
        {
            
            MOrder counterOrder = HondaOrderManager.getCounterOrder(order);
            
            if (counterOrder!= null && counterOrder.getDocStatus().equals(DocumentEngine.STATUS_Drafted))
                complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
        }   
        
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
        
    }*/
    
    
    private static CommandBean getPO_SCOCommands(MOrder order)
    {
        MDocType docType = new MDocType(order.getCtx(),order.getC_DocType_ID(),null);
        
        if (docType.getDocSubTypeSO().equals(UDIDocSubTypeSOValues.DOCTYPESO_Quotation.getDocTypeSubSO()))
            return getPO_SCO_QuotationCommands(order);
        
        return getPO_SCO_StandardCommands(order);
    }
    
    private static CommandBean getPO_SCO_QuotationCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
        
        String[] simpleCommands = null;
        String[] complexCommands = null; 
        
        
        
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
        {
//            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_PREPARE_SCO};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Completed))
        {
            // complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }   
        
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
    }
    
    private static CommandBean getPO_SCO_StandardCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
        
        String[] simpleCommands = null;
        String[] complexCommands = null; 
        
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_ACCEPT};
            //  complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_CONFIRM};
            // complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Completed))
        {
            // complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }   
        
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(new String[]{}, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
    }
    
    private static CommandBean getSCOCommands(Properties ctx, MOrder order) throws OperationException
    {
        MOrg myOrg = OrganisationManager.getMyOrg(ctx);
        
        
       /*if (myOrg.isWholesaler())
            return getSO_SCO_Commands(order);*/
        
        return getPO_SCOCommands(order);
        
    }
    
    
    private static CommandBean getCustomerOrderCommands(MOrder order) throws OperationException
    {
        String docStatus = order.getDocStatus();
        order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        String description = null;
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
           
                description = "The vehicle has already been released. Please cancel this order.";
                order.setDescription(description);
                order.save();
                complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            
            
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
        
    }
    
    private static CommandBean getUsedCarPurchaseOrderCommands(MOrder order)
    {
        String docStatus = order.getDocStatus();
        order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_CONFIRM};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
            
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
            //cmdBean = getCommands(simpleCommands, complexCommands, roleID);
        }
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
        
        
    }
    
    private static CommandBean getALOrderCommands(MOrder order) throws OperationException
    {
        String docStatus = order.getDocStatus();
        order.getOrderType();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_CONFIRM};
            complexCommands = new String[]{Constants.ORDER_ACTION_CANCEL};
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.ORDER_ACTION_DELETE};
        }
        
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
        {
            simpleCommands = new String[]{Constants.ORDER_ACTION_SAVE, Constants.ORDER_ACTION_COMPLETE};
        }
        
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
        
    }

    
    private static CommandBean getTmkOrderCommands(MOrder order) throws OperationException
    {
        String docStatus = order.getDocStatus();
        
        String[] simpleCommands = null;
        String[] complexCommands = null;
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
           	simpleCommands = new String[]{Constants.INVOICE_PAID};
        
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
        
    }
    
}
