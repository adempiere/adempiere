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
 * Created on Aug 23, 2006
 */


package org.posterita.businesslogic;

import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Properties;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import org.compiere.model.MCashLine;
import org.compiere.model.MUser;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.util.Env;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.UDIBean;
import org.posterita.exceptions.OperationException;
import org.posterita.util.IPrintFormatter;
import org.posterita.util.TMKOrderPrintFormatter;
import org.posterita.util.TmkPrinterConstants;


public class PrintManager
{
    final static String SINGLE_LINE  			= "-----------------------------";
    final static String DOUBLE_LINE 			= "=============================";
    final static String EMPTY_STRING 			= "";
    
    
    
    
    
    public static void printAdjustCashbookReport(Properties ctx,CashBookDetailBean bean) throws OperationException
    {
        int ad_user_id          = Env.getAD_User_ID(ctx);
        String adjustmentAmount = bean.getAdjustmentAmount();
        String transferType     = bean.getTransferType();
        String title            = "Adjust Cashbook";
        
        if(transferType.equalsIgnoreCase(MCashLine.CASHTYPE_GeneralExpense))
        {
            transferType = "Expense";
        }
        else if(transferType.equalsIgnoreCase(MCashLine.CASHTYPE_BankAccountTransfer))
        {
            transferType = "Account Transfer";
        }
        else if(transferType.equalsIgnoreCase(MCashLine.CASHTYPE_GeneralReceipts))
        {
            transferType = "Receipt";
        }
        else
        {
            
        }
        
        //writing the report
        StringBuffer reportData = new StringBuffer();
        
        //adding report header
        reportData.append(TmkPrinterConstants.LOGO1);
        
        //adding title
        reportData.append(TmkPrinterConstants.BIG_FONT)
        .append(TmkPrinterConstants.CENTER_ALIGN)
        .append(title)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //setting small font
        reportData.append(TmkPrinterConstants.SMALL_FONT);
        
        //adding customer name
        MUser user = new MUser(ctx,ad_user_id,null);
        String username = user.getName();
        
        username = String.format("%1$-60s",username);
        reportData.append(username).append(TmkPrinterConstants.LINE_FEED);
        
        //adding date
        Calendar c = Calendar.getInstance();       
        String date = String.format("%1$te %1$tb,%1$tY %1$tH:%1$tM:%1$tS",c);
        date = String.format("%1$-60s",date);
        reportData.append(date)
        .append(TmkPrinterConstants.LINE_FEED)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //1.header
        reportData.append(TmkPrinterConstants.H_FULL_LINE_TOP)
        .append(TmkPrinterConstants.LINE_FEED);
        
        String header = String.format("%1$-30s%2$-30s","Transfer Type","Amount");
        
        reportData.append(header)
        .append(TmkPrinterConstants.LINE_FEED);
        
        reportData.append(TmkPrinterConstants.H_FULL_LINE_BOTTOM)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //2.body
        
        String body = String.format("%1$-30s%2$-30s", transferType, adjustmentAmount);
        
        reportData.append(body)
        .append(TmkPrinterConstants.LINE_FEED);
        
        reportData.append(TmkPrinterConstants.H_FULL_LINE_BOTTOM)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //3.footer
        
        String footer = "Sig: ...................................";
        footer = String.format("%1$60s",footer);
        
        reportData.append(TmkPrinterConstants.LINE_FEED)
        .append(TmkPrinterConstants.LINE_FEED)        
        .append(footer)
        .append(TmkPrinterConstants.LINE_FEED)  
        .append(TmkPrinterConstants.PAPER_CUT);
        
        print(ctx,reportData.toString());
        openCashDrawer(ctx);	
        
    }
    
    public static void openCashDrawer(Properties ctx) throws OperationException
    {
        byte [] printData= {10,27,112,48,55,1};
        print(ctx, new String(printData));
    }
        
    public static void print(Properties ctx, int type,int recordId) throws OperationException
    { 
        ReportEngine re = ReportEngine.get(ctx,type, recordId);
        MPrintFormat printFormatter = re.getPrintFormat();
        
        String classname = printFormatter.getClassName();
        
        if (classname != null)
        {
            String args = printFormatter.getArgs();
            try 
            {
                Class clazz = Class.forName(classname);
                IPrintFormatter formatter = null;           
                
                Class[] parameterTypes = new Class[]{ String.class};
                Object[] parameters = new Object[]{ args};
                
                Constructor constructor = clazz.getConstructor(parameterTypes);
                formatter = (IPrintFormatter) constructor.newInstance(parameters);
                
                String str = formatter.format(ctx, recordId);
                int docCopies = re.getPrintInfo().getCopies();
                System.out.println(str);
                
                for (int i=0; i<docCopies; i++)
                {                    
                    print(ctx, str);
                }
            } 
            catch (Exception e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }            
        }
        else
        {
            re.print();
        }
    }
    
    public static void print(Properties ctx, int type,int recordId,UDIBean bean) throws OperationException
    { 
    	printOrder(ctx,recordId,null);
    }
    
    public static void print(Properties ctx, String data)
    {
        PrintService psServies[] = PrintServiceLookup.lookupPrintServices(null, null);
        
        for (int i =0; i < psServies.length; i++)
        {
            
            if (psServies[i].getName().equalsIgnoreCase(POSTerminalManager.getPOSPrinter(ctx)))
            {
                
                DocPrintJob job =   psServies[i].createPrintJob();
                
                SimpleDoc doc = new SimpleDoc(data.getBytes(),DocFlavor.BYTE_ARRAY.AUTOSENSE,null);
                try 
                {
                    job.print(doc, null);
                } 
                catch (PrintException e) 
                {
                    e.printStackTrace();
                }
                
            }
        } 
    }
    
    //----------------------------------------------------------------------------------------------
    /**
     * Print Order Receipt
     * This method is called from order manager
     * @param ctx
     * @param C_Order_ID
     * @param trxName
     * @throws Exception 
     */
    public static void printOrder(Properties ctx, int C_Order_ID, String trxName) throws OperationException
    {
    	String args = "footer=Merci;showFooter=true;";
    	
    	try 
    	{
			IPrintFormatter formatter = new TMKOrderPrintFormatter(args, trxName);
			String printData = formatter.format(ctx, C_Order_ID, new OrderLineBean());
		} 
    	catch (Exception e) 
    	{
			new OperationException(e);
		}
    }
    
    public static String getPrintData(Properties ctx, int C_Order_ID, String trxName) throws OperationException
    {
    	String args = "showFooter=true;LINE_LENGTH=64;priceWithVat=true;showDiscount=true;showLogo=true;showBarcode=true;";
    	String printData  = "null";
    	
    	try 
    	{
			IPrintFormatter formatter = new TMKOrderPrintFormatter(args, trxName);
			printData = formatter.format(ctx, C_Order_ID, new OrderLineBean());
		} 
    	catch (Exception e) 
    	{
			new OperationException(e);
		}
    	
    	return printData;
    }
    public static void printClosedTillData(Properties ctx, String Data) throws OperationException
    {
    	
    }
    public static String getOpenDrawerCmd()
    {
    	byte [] printData= {10,27,112,48,55,1};
        return new String(printData);
    }
}

