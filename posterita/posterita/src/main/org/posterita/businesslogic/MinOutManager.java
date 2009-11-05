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
 *  Created on 08-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInOutLineConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.ChangeDocumentStatusBean;
import org.posterita.beans.CommandBean;
import org.posterita.beans.InOutHistoryBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebDocumentHeaderBean;
import org.posterita.beans.WebMinOutLineBean;
import org.posterita.core.JulianDate;
import org.posterita.core.UDIMap;
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ShipmentAlreadyExistsException;
import org.posterita.exceptions.TrackingNumberNotfoundException;
import org.posterita.util.PoManager;

public class MinOutManager extends AbstractDocumentManager
{  
    private static final String SHIPMENT_MADE = "Shipment Made";    
    
    //TODO pass warehouseId in list of arguments
    public static MInOut createShipmentFromInvoice(Properties ctx,MInvoice invoice) throws OperationException
    {
        
        
        int SOId = invoice.getC_Order_ID();
        
        MOrder order = new MOrder(ctx,SOId,invoice.get_TrxName());
       
        MInvoiceLine[] lines = invoice.getLines();
        int []orderLineIds = new int[lines.length];
        for(int i=0;i<lines.length;i++)
        {
            orderLineIds[i]=lines[i].getC_OrderLine_ID();
        }
        
        MInOut inOut = createMInOut(ctx,invoice,order.getM_Warehouse_ID());
        inOut.setC_Invoice_ID(invoice.get_ID());
        
        PoManager.save(inOut);
          
        return inOut;
    }    
    
    public static MInOut generateCompletedMinOut(Properties ctx, Properties toCtx, MInvoice invoice) throws OperationException, ShipmentAlreadyExistsException
    {
    	MInOut shipment = createShipmentFromInvoice(ctx, invoice);
    	 
        shipment = completeShipment(ctx, shipment);
        createConfirmation(ctx, shipment);
        
        MInOut counterDoc = getInitialDoc(toCtx, shipment.get_ID(), invoice.get_TrxName());
        
        shipment.setRef_InOut_ID(counterDoc.get_ID());
        shipment.save();
        
        //counter documents
        MInOut receipt = getCounterDoc(ctx, shipment.get_ID(), invoice.get_TrxName());
        MInvoice counterInvoice = InvoiceManager.getCounterInvoice(ctx, invoice.get_ID(), invoice.get_TrxName());
       
        Properties counterCtx = (Properties) ctx.clone();
        counterCtx.setProperty("#AD_Org_ID", "" + receipt.getAD_Org_ID());
        
        receipt = completeMaterialReceiptFromShipment(counterCtx, shipment.get_ID(), invoice.get_TrxName());
        receipt.setC_Invoice_ID(counterInvoice.get_ID());
        receipt.save();
        createConfirmation(ctx, receipt);
  
        return shipment;
    }
    
    public static MInOut generateMinOut(Properties ctx, MInvoice invoice) throws OperationException
    {
        MInOut shipment = createShipmentFromInvoice(ctx, invoice);
 
        shipment = completeShipment(ctx, shipment);
        createConfirmation(ctx, shipment);
        //counter documents
        if (shipment.getRef_InOut_ID() != 0)
        {
        	MInOut receipt = getCounterDoc(ctx, shipment.get_ID(), invoice.get_TrxName());
	        MInvoice counterInvoice = InvoiceManager.getCounterInvoice(ctx, invoice.get_ID(), invoice.get_TrxName());
	       
	        Properties counterCtx = (Properties) ctx.clone();
	        counterCtx.setProperty("#AD_Org_ID", "" + receipt.getAD_Org_ID());
	        
	        receipt = completeMaterialReceiptFromShipment(counterCtx, shipment.get_ID(), invoice.get_TrxName());
	        receipt.setC_Invoice_ID(counterInvoice.get_ID());
	        receipt.save();
	        createConfirmation(ctx, receipt);
        }
        return shipment;
    }
     
   
    
    public static MInOutConfirm completeConfirmation(Properties ctx,MInOutConfirm confirm) throws OperationException
    {
    	PoManager.processIt(confirm, DocumentEngine.ACTION_Complete);
//    	confirm.processIt(DocumentEngine.ACTION_Complete);
        return confirm;        
    }
    
        
    public static MInOut completeShipment(Properties ctx,MInOut inOut) throws OperationException
    {
        PoManager.processIt(inOut, DocumentEngine.ACTION_Complete);
        return inOut;        
    }
    
    public static MInOut createMInOut(Properties ctx,MInvoice invoice,int warehouseId) throws OperationException
    {
       
        JulianDate today = JulianDate.getToday();
        
        Timestamp stamp = new Timestamp(today.getStandardTime());
        int c_doctype_id = 0;
        
        if(invoice.isCreditMemo() && !invoice.isSOTrx())
        {
        	String sql = "SELECT C_DOCTYPE_ID FROM C_DOCTYPE WHERE AD_CLIENT_ID = ? AND NAME = 'MM Vendor Return'"; 
        	c_doctype_id = DB.getSQLValue(invoice.get_TrxName(), sql, Env.getAD_Client_ID(ctx));
        	
        	if(c_doctype_id == -1)
        	{
        		c_doctype_id = 0;
        	}
        }
        
        MInOut inOut = new MInOut(invoice,c_doctype_id,stamp,warehouseId);
        
        c_doctype_id = inOut.getC_DocType_ID();
        MDocType doctype = MDocType.get(ctx, c_doctype_id);
        String docBaseType = doctype.getDocBaseType();        
        boolean IsSOTrx = invoice.isSOTrx();
        
        if (docBaseType.equals("MMS"))					//	Material Shipments
        {
        	if(IsSOTrx)
        	{
        		inOut.setMovementType(MInOut.MOVEMENTTYPE_CustomerShipment);
        	}
        	else
        	{
        		inOut.setMovementType(MInOut.MOVEMENTTYPE_VendorReturns);
        	}
        		
        }
        
        if(docBaseType.equals("MMR"))					//	Material Receipts
        {
        	if(IsSOTrx)
        	{
        		inOut.setMovementType(MInOut.MOVEMENTTYPE_CustomerReturns);
        	}
        	else
        	{
        		inOut.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);
        	}
        }  
       
           
        PoManager.save(inOut);
        
        MInvoiceLine[] invoiceLines = invoice.getLines(false);
        for (int i = 0; i < invoiceLines.length; i++)
        {
            MInvoiceLine invoiceLine = invoiceLines[i];
            MInOutLine sLine = new MInOutLine(inOut);
            sLine.setInvoiceLine(invoiceLine, 0,    //  Locator 
                invoice.isSOTrx() ? invoiceLine.getQtyInvoiced() : Env.ZERO);
            sLine.setQtyEntered(invoiceLine.getQtyEntered());
            sLine.setMovementQty(invoiceLine.getQtyInvoiced());            
            if (!sLine.save())
                throw new OperationException("@SaveError@ @M_InOutLine_ID@");
           
        }
       
        invoice.setShipment(inOut);
        PoManager.save(invoice);
        
        
        return inOut;
    }
    
    
    
    public static MInOut createShipment(Properties ctx,int salesOrderId,int [] salesOrderLineid,String trxName) throws OperationException
    {
        JulianDate today = JulianDate.getToday();
        
        Timestamp stamp = new Timestamp(today.getStandardTime());
        
        MOrder salesOrder = new MOrder(ctx,salesOrderId,trxName);
        
        MInOut inOut = new MInOut(salesOrder, 0, stamp);
        try
        {
           int [] invoiceIds = MInvoice.getAllIDs(MInvoice.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and c_order_id="+salesOrderId,trxName);
            if(invoiceIds.length>0)
            {
                inOut.setC_Invoice_ID(invoiceIds[0]); 
            }
           
            salesOrder.setIsDelivered(true);
            PoManager.save(salesOrder);
            
            if (!salesOrder.isSOTrx())
                throw new OperationException("The Order should be a sales order");
            
            inOut.setIsSOTrx(true);
            inOut.setDescription("Shipment");
            
            //inOut.setC_DocType_ID(MDocType.DOCBASETYPE_MaterialDelivery); // @Trifon
            int [] docTypeShipmentIndirectId = MInvoice.getAllIDs(MDocType.Table_Name, 
                      "AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)
                    + " AND DocBaseType='"+MDocType.DOCBASETYPE_MaterialDelivery+"' "
                    + " AND IsActive='Y' "
                    + " AND IsSOTrx='Y' "
                    + " AND Name = 'MM Shipment Indirect'", trxName);
            if (docTypeShipmentIndirectId.length > 0)
            {
                inOut.setC_DocType_ID( docTypeShipmentIndirectId[0] ); 
            } else {
                inOut.setC_DocType_ID(MDocType.DOCBASETYPE_MaterialDelivery); // @Trifon; old behavior
            }
            
            PoManager.save(inOut);
              
            MInOutLine [] line=new MInOutLine[salesOrderLineid.length];
             
            for(int i=0;i<salesOrderLineid.length;i++)
            {
                
                MOrderLine oLine  = new MOrderLine(ctx,salesOrderLineid[i],trxName);
                
                MWarehouse warehouse = new MWarehouse(ctx,oLine.getM_Warehouse_ID(),null);
                
                
                MLocator locator = warehouse.getDefaultLocator();
               
                MProduct product = new MProduct(ctx, oLine.getM_Product_ID(),trxName);
                
                line[i] =new MInOutLine(inOut);
                line[i].setC_OrderLine_ID(oLine.get_ID());
                line[i].setQtyEntered(oLine.getQtyEntered());
                line[i].setM_Product_ID(oLine.getM_Product_ID());
                line[i].setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());
                line[i].setC_UOM_ID(product.getC_UOM_ID());
                line[i].setM_Locator_ID(locator.getM_Locator_ID());
                line[i].setMovementQty(oLine.getQtyEntered());
                
                PoManager.save(line[i]);
               
            }
            
        }
        catch(OperationException e)
        {
            throw e;
        }
        
        return inOut;
    }
    

    public static MInOutConfirm createConfirmation(Properties ctx,MInOut inOut) throws OperationException
    {
    	return createConfirmation(ctx, inOut.get_ID(), inOut.get_TrxName());
    }
    
    public static MInOutConfirm createConfirmation(Properties ctx,int inOutId, String trxName) throws OperationException
    {
        MInOut inOut = new MInOut(ctx,inOutId,trxName);
        MInOutConfirm inOutConfirm = new MInOutConfirm(inOut,MInOutConfirm.CONFIRMTYPE_ShipReceiptConfirm);
        PoManager.save(inOutConfirm);
       
        
        MInOutLine [] line = inOut.getLines();
         
        MInOutLineConfirm [] inOutLineConfirm = new MInOutLineConfirm[line.length];
        
        for (int i=0;i<line.length;i++)
        {
            inOutLineConfirm[i]= new MInOutLineConfirm(ctx,0,trxName);
            inOutLineConfirm[i].setM_InOutConfirm_ID(inOutConfirm.get_ID());
            inOutLineConfirm[i].setM_InOutLine_ID(line[i].get_ID());
            inOutLineConfirm[i].setTargetQty(line[i].getQtyEntered());
            inOutLineConfirm[i].setConfirmedQty(line[i].getMovementQty());
            
            PoManager.save(inOutLineConfirm[i]);
            
        }
        
        return inOutConfirm;
        
    }
    
    
    public static MInOut completeMaterialReceiptFromShipment(Properties ctx,int inOutId, String trxName) throws OperationException
    {
        
        MInOut inOut = new MInOut(ctx,inOutId,trxName);
        MInvoice invoice = new MInvoice(ctx, inOut.getC_Invoice_ID(), trxName);
        MInOut refInOut = new MInOut(ctx,inOut.getRef_InOut_ID(),trxName);
        
        refInOut.setDescription("Material Receipt");
        refInOut.setC_Invoice_ID(invoice.getRef_Invoice_ID());        
        PoManager.save(refInOut);
        refInOut.processIt(DocumentEngine.ACTION_Complete);
        return refInOut;
    }
    
   	
	public static ArrayList getInOutHistory(Properties ctx, String isSoTrx, String docStatus, Integer partnerId, Integer month, Integer year) throws SQLException
	{
		StringBuffer select = new StringBuffer();
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		
		select.append(" select inOut.M_INOUT_ID," + // 1
		 				" inOut.DOCUMENTNO," + //2
						" inOut.DOCSTATUS," + // 3
						" inOut.C_ORDER_ID," + //4
						" inOut.DATEORDERED," + //5
						" inOut.MOVEMENTDATE," + //6
						" inOut.M_WAREHOUSE_ID," + //7
						" inOut.POREFERENCE," + //8
						" inOut.C_INVOICE_ID," + // 9
						" inOut.REF_INOUT_ID, " + // 10
						" bp.name, " +//11
						" bp.C_BPARTNER_ID"); //12
	
		from.append(" from M_INOUT inOut,C_BPARTNER bp ");
		
		where.append("	where inOut.C_BPARTNER_ID = bp.C_BPARTNER_ID " +
					"	and inOut.AD_CLIENT_ID =  " +Env.getAD_Client_ID(ctx)+
					"	and inOut.AD_ORG_ID =  " + Env.getAD_Org_ID(ctx)+
					"	and inOut.ISSOTRX =  '" + isSoTrx + "'");
		
		if(docStatus!=null)
    	{
    	    where.append(" and inOut.docstatus = '" + docStatus + "'");		    	    
    	}
		
		if (partnerId!= null)
		{
		    where.append(" and bp.c_bpartner_id = " + partnerId);
		}
		
    	if (month!= null) 
    	{
    		where.append(" and to_char(inOut.created, 'mm') = " + month);
    	}	    	

    	if (year!= null) 
    	{
    		where.append(" and to_char(inOut.created, 'yyyy') = " + year);
    	}
		
		String sql = ""+select+from+where;
		System.out.println("Query for InOutHistory :" + sql);
		
		PreparedStatement pstmt = DB.prepareStatement(sql,null);
		InOutHistoryBean bean;
		ArrayList<InOutHistoryBean> list = new ArrayList<InOutHistoryBean>();
		ResultSet rs = null;
		
		try
		{
			rs = pstmt.executeQuery();

			while(rs.next())
			{
				bean = new InOutHistoryBean();
				bean.setInOutId(Integer.valueOf(rs.getInt(1)));
				bean.setDocumentNo(rs.getString(2));
				bean.setDocStatus(UDIMap.docStatusMap.get(rs.getString(3)));
				bean.setDocStatusCode(rs.getString(3));
				bean.setOrderId(Integer.valueOf(rs.getInt(4)));
				bean.setDateOrdered(rs.getTimestamp(5));
				bean.setDateMovement(rs.getTimestamp(6));
				bean.setWarehouseId(Integer.valueOf(rs.getInt(7)));
				bean.setPoReference(rs.getString(8));
				bean.setInvoiceId(Integer.valueOf(rs.getInt(9)));
				bean.setRefInOutId(Integer.valueOf(rs.getInt(10)));
				bean.setPartnerName(rs.getString(11));
				bean.setPartnerId(Integer.valueOf(rs.getString(12)));
				list.add(bean);
			}
			
			rs.close();		
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try 
			{
				pstmt.close();
			}
			catch(Exception e)
			{}
			
			pstmt = null;
		}

		
		return list;
	}
	
	public static ArrayList getShipmentHistory(Properties ctx, String docStatus, Integer partnerId, Integer month, Integer year) throws SQLException
	{
	    return getInOutHistory(ctx,"Y",docStatus,partnerId,month,year);
	}
	
	public static ArrayList getReceiptHistory(Properties ctx, String docStatus, Integer partnerId, Integer month, Integer year) throws SQLException
	{
	    return getInOutHistory(ctx,"N",docStatus,partnerId,month,year);
	}
	
	public static WebDocumentBean getWebMinOutBean(Properties ctx, MInOut minOut) throws OperationException, DataException
	{
	    if (minOut == null)
            throw new OperationException("Invalid operation shipment/receipt is null");
        
        if (minOut.get_ID() == 0)
            throw new OperationException("You have deleted this shipment/receipt. You cannot view this shipment/receipt.");
        
        WebDocumentBean bean = new WebDocumentBean();
        
        bean.setMinOutId(Integer.valueOf(minOut.get_ID()));
         
        MOrg myOrg = OrganisationManager.getMyOrg(ctx);
        MBPartner me = new MBPartner(ctx, myOrg.getLinkedC_BPartner_ID(null), null);
        bean.setMe(me);
        
        //Me Location---------------
		MBPartnerLocation meLocation[] =  MBPartnerLocation.getForBPartner(ctx,me.get_ID());
		if (meLocation.length  ==0)
			throw new OperationException("No location has been set for your organisation. Please ask your administrator to set one for you");
		
		MLocation location = new MLocation(ctx, meLocation[0].getC_Location_ID(), null);
		
		bean.setMeLocation(location);
        
        //End-----------------
        
        MBPartner you = new MBPartner(ctx, minOut.getC_BPartner_ID(), null);
        bean.setYou(you);

        
        //You Location------------
		MBPartnerLocation youBPLocation[] = MBPartnerLocation.getForBPartner(ctx, you.get_ID());
		
		if (youBPLocation.length  ==0)
			throw new OperationException("No location has been set for the dealer organisation. Please ask your administrator to set one for you");
		
		MLocation youLocation = new MLocation(ctx, youBPLocation[0].getC_Location_ID(), null);
        
        bean.setYoubpLocation(youBPLocation[0]);
        bean.setYouLocation(youLocation);
        
        bean.setLines(getWebMinOutLines(ctx, minOut));
        
        WebDocumentHeaderBean headerBean = createWebDocumentHeader(ctx, minOut.getAD_Org_ID(),minOut.getC_BPartner_ID(), minOut.getDocStatus(), minOut.isSOTrx(),null);
        headerBean.setDocumentHeader(getDocumentHeader(minOut));
        
        MDocType doctype = new MDocType(ctx, minOut.getC_DocType_ID(), null);
        headerBean.setDocumentTitle(doctype.getName());
        bean.setHeaderBean(headerBean);
        
        bean.setMinOut(minOut);
        

        MInvoice invoice = new MInvoice(ctx, minOut.getC_Invoice_ID(), null);
        bean.setInvoice(invoice);
        
        CommandBean cmdBean = getShipmenttWebCommands(minOut);
       
        bean.setSimpleCommand(cmdBean.getSimpleCommand());
        bean.setComplexCommand(cmdBean.getComplexCommand());

        return bean;
	}
	
    public static String getDocumentHeader(MInOut minOut)
    {
        if (minOut.isSOTrx())
            return SHIPMENT_MADE;
        
        return SHIPMENT_MADE;
    }	
	
	public static ArrayList<WebMinOutLineBean> getMinOutLines(Properties ctx, MInOut inOut) throws OperationException
	{
	    MInOutLine lines[] = inOut.getLines();
	    
	    MInOutLine line;
	    WebMinOutLineBean inOutLine;
	    MProduct product;
	    ArrayList<WebMinOutLineBean> inOutLines = new ArrayList<WebMinOutLineBean>();
	    for (int i = 0; i < lines.length; i++)
	    {
	        line = lines[i];
	        inOutLine = new WebMinOutLineBean();
	        product = new MProduct(ctx, line.getM_Product_ID(), null);
	        inOutLine.setAttributeValuesPair(AttributeValuesManager.retrieveAttributeValues(ctx, product.getM_AttributeSetInstance_ID()));
	        inOutLine.setSerno(getSerno(ctx,line.getM_AttributeSetInstance_ID()));
            inOutLine.setQtyOrdered(line.getMovementQty());
	        inOutLines.add(inOutLine);
	    }
	    
	    return inOutLines;
	}
	
	public static ArrayList<WebMinOutLineBean> getWebMinOutLines(Properties ctx, MInOut inOut) throws OperationException
	{
	    MInOutLine lines[] = inOut.getLines();
	    
	    MInOutLine line;
	    WebMinOutLineBean inOutLine;
	    MProduct product;
	    ArrayList<WebMinOutLineBean> inOutLines = new ArrayList<WebMinOutLineBean>();
	    for (int i = 0; i < lines.length; i++)
	    {
	        line = lines[i];

			if (line.getM_Product_ID() != 0)
			{
		        inOutLine = new WebMinOutLineBean();
				product = new MProduct(ctx, line.getM_Product_ID(), null);
	        	inOutLine.setProductName(product.getName().replaceAll("~"," "));
	        	inOutLine.setDescription(product.getDescription());
		        inOutLine.setQtyOrdered(line.getQtyEntered());
		        
		        inOutLines.add(inOutLine);
			}
	    }
	    
	    return inOutLines;
	}	
	
	//get receipt from shipment
	public static MInOut getCounterDoc(Properties ctx, int shipmentId, String trxName)
	{
	    MInOut shipment = new MInOut(ctx,shipmentId, trxName);
	    
	    if ((shipment == null)||(shipment.get_ID() == 0))
	        throw new RuntimeException("no counter document found for shipment----" + shipment.get_ID());
	    
	    if (!shipment.getDocStatus().equals(DocumentEngine.STATUS_Completed))
	        throw new RuntimeException("cannot get counter document of shipment which is not completed");
	    
	    
	    MInOut receipt = new MInOut(ctx, shipment.getRef_InOut_ID(), trxName);
	    
	    if ((receipt == null) ||(receipt.get_ID() == 0))
	        throw new RuntimeException("no receipt found for shipment");
	    
	    return receipt;
	}
	
	public static MInOut getInitialDoc(Properties ctx, int shipmentId, String trxName)
	{
		String sql = " AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and AD_Org_ID=" + Env.getAD_Org_ID(ctx) + " and Ref_InOut_ID=" + shipmentId;
		
		int minoutIds[] = MInOut.getAllIDs(MInOut.Table_Name,  sql, trxName);
		
		if(minoutIds.length == 0)
			throw new RuntimeException("Original shipment doc could not be found!");
		MInOut origInOut = new MInOut(ctx, minoutIds[0], trxName);
		
		return origInOut;
	}
	
	public static boolean isShipped(Properties ctx, MInvoice invoice) throws OperationException
	{
		int minOutIds[] = MInOut.getAllIDs(MInOut.Table_Name, " ad_client_id=" + Env.getAD_Client_ID(ctx) + " and c_invoice_id=" + invoice.get_ID(), null);
		
		if (minOutIds.length == 0)
			return false;
		
		if (minOutIds.length > 1)
			throw new OperationException("More than 1 shipment found for invoice " + invoice.get_ID());
		
		MInOut minout = new MInOut(ctx, minOutIds[0], null);
		
		if (!minout.getDocStatus().equals(DocumentEngine.STATUS_Completed))
			return false;
		
		int refInoutId = minout.getRef_InOut_ID();
		
		if (refInoutId == 0)
			return false;
		
		MInOut refInOut = new MInOut(ctx, refInoutId, null);
		
		if (!refInOut.getDocStatus().equals(DocumentEngine.STATUS_Completed))
			return false;
		
		return true;	
	}
    
    private static CommandBean getShipmenttWebCommands(MInOut inOut) throws OperationException, DataException
    {
               
        String[] simpleCommands = null;
        String[] complexCommands = null;  
       
        CommandBean bean = new CommandBean();
        
        if (inOut.getDocStatus().equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.MINOUT_ACTION_COMPLETE};
            complexCommands =new String[]{Constants.MINOUT_ACTION_CANCEL};
        }
        
        if (inOut.getDocStatus().equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.MINOUT_ACTION_DELETE};
        }
        
       
       
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
    }
    
    public static WebDocumentBean changeMinOutStatus(Properties ctx, MInOut minOut, ChangeDocumentStatusBean bean) throws OperationException, DataException
    {
        WebDocumentBean webBean = new WebDocumentBean();
        
        if(bean.getTrackingNo()==null)
            throw new  TrackingNumberNotfoundException("Please specify the FexdEx Tracking Number");
        
        String submit = bean.getSubmit();
        
        if (minOut == null)
        {
            throw new OperationException("Cannot change the status on an Shipment that does not exist anymore.");
        }
       
        if (submit.equals(Constants.MINOUT_ACTION_COMPLETE))
        {
            minOut.setTrackingNo(bean.getTrackingNo());
            PoManager.save(minOut);
            minOut = MinOutManager.completeShipment(ctx,minOut);
        }
        
        if (submit.equals(Constants.MINOUT_ACTION_CANCEL))
        {
            minOut = forceVoid(ctx, minOut);
        }
        
        if (submit.equals(Constants.MINOUT_ACTION_DELETE))
        {
            minOut = deleteInOut(ctx, minOut);
        }
        
        
          webBean= MinOutManager.getWebMinOutBean(ctx,minOut);
        
        return webBean;
    }
    
    
    private static MInOut forceVoid(Properties ctx, MInOut inout) throws OperationException
    {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        String sql  = "UPDATE M_INOUT SET DOCSTATUS=" + "'" 
                        + DocumentEngine.STATUS_Voided + "'"
                        + " WHERE AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
                        + " AND M_INOUT_ID=" + inout.get_ID();
                        
        String sql2  = "UPDATE M_INOUT SET DOCACTION=" + "'" 
                        + DocumentEngine.ACTION_None + "'"
                        + " WHERE AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
                        + " AND M_INOUT_ID=" + inout.get_ID();
                        
        pstmt1 = DB.prepareStatement(sql,null);
        pstmt2 = DB.prepareStatement(sql2,null);
        
        try 
        {
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e.getMessage());
        }
        finally
        {
        	try
        	{
        		pstmt1.close();
        		pstmt2.close();
        	}
        	catch(Exception e)
        	{}
        	
        	pstmt1 = null;
        	pstmt2 = null;
        }
        
        return inout;
        
    }
    
    public static MInOut deleteInOut(Properties ctx, MInOut inOut) throws OperationException
    {
        inOut.setIsActive(false);
        PoManager.save(inOut);
        
        return inOut;
    }
    
    public static void printInOut(Properties ctx,MInOut inOut) throws OperationException
    {
        PrintManager.print(ctx,ReportEngine.SHIPMENT,inOut.get_ID());
    }

    public static MInOut loadMInOut(Properties ctx, int minoutId, String trxName) throws OperationException
    {
    	MInOut minout = new MInOut(ctx, minoutId, trxName);
    	if(minout.get_ID() == 0)
    		throw new OperationException("Could not load invoice with id: " + minout);
    	
    	return minout;
    }
    
    
}
