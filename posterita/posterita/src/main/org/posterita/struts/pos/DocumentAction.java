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
* Created on Oct 17, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.struts.pos;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MInventory;
import org.compiere.print.ReportEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.DocumentBean;
import org.posterita.beans.InventoryBean;
import org.posterita.businesslogic.stock.InventoryManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TabularReport;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.InventoryForm;
import org.posterita.struts.core.DefaultForm;

import com.itextpdf.text.DocumentException;

public class DocumentAction extends POSDispatchAction
{
	public static final String VIEW_ORDER_DOCUMENT_PDF = "viewOrderDocumentPDF";
	public ActionForward viewOrderDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     DefaultForm df= (DefaultForm) form;
	        
	     DocumentBean bean = (DocumentBean) df.getBean();
	     String reportName = RandomStringGenerator.randomstring() + ".pdf";
	     String reportPath = ReportManager.getReportPath(reportName);
	     
	     ReportEngine reportEngine = ReportEngine.get(ctx, ReportEngine.ORDER, bean.getDocumentId().intValue());
	     reportEngine.createPDF(new File(reportPath));	     
	     
	     String reportURI = ReportManager.getReportURI(reportPath,request);
	     request.setAttribute(Constants.DOCUMENT_PDF, reportURI);
	     
	     return mapping.findForward(VIEW_ORDER_DOCUMENT_PDF);
	}
	
	public static final String VIEW_INVOICE_DOCUMENT_PDF = "viewInvoiceDocumentPDF";
	public ActionForward viewInvoiceDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     DefaultForm df= (DefaultForm) form;
	        
	     DocumentBean bean = (DocumentBean) df.getBean();
	     String reportName = RandomStringGenerator.randomstring() + ".pdf";
	     String reportPath = ReportManager.getReportPath(reportName);
	     
	     ReportEngine reportEngine = ReportEngine.get(ctx, ReportEngine.INVOICE, bean.getDocumentId().intValue());
	     reportEngine.createPDF(new File(reportPath));	     
	     
	     String reportURI = ReportManager.getReportURI(reportPath,request);
	     request.setAttribute(Constants.DOCUMENT_PDF, reportURI);
	     
	     return mapping.findForward(VIEW_INVOICE_DOCUMENT_PDF);
	}
	
	public static final String VIEW_SHIPMENT_DOCUMENT_PDF = "viewShipmentDocumentPDF";
	public ActionForward viewShipmentDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     DefaultForm df= (DefaultForm) form;
	        
	     DocumentBean bean = (DocumentBean) df.getBean();
	     String reportName = RandomStringGenerator.randomstring() + ".pdf";
	     String reportPath = ReportManager.getReportPath(reportName);
	     
	     ReportEngine reportEngine = ReportEngine.get(ctx, ReportEngine.SHIPMENT, bean.getDocumentId().intValue());
	     reportEngine.createPDF(new File(reportPath));	     
	     
	     String reportURI = ReportManager.getReportURI(reportPath,request);
	     request.setAttribute(Constants.DOCUMENT_PDF, reportURI);
	     
	     return mapping.findForward(VIEW_SHIPMENT_DOCUMENT_PDF);
	}
	
	public static String VIEW_PAYMENT_DOCUMENT_PDF = "viewPaymentDocumentPDF";
	public ActionForward viewPaymentDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     DefaultForm df= (DefaultForm) form;
	        
	     DocumentBean bean = (DocumentBean) df.getBean();
	     String reportName = RandomStringGenerator.randomstring() + ".pdf";
	     String reportPath = ReportManager.getReportPath(reportName);
	     
	     ReportEngine reportEngine = ReportEngine.get(ctx, ReportEngine.REMITTANCE, bean.getDocumentId().intValue());
	     reportEngine.createPDF(new File(reportPath));	     
	     
	     String reportURI = ReportManager.getReportURI(reportPath,request);
	     request.setAttribute(Constants.DOCUMENT_PDF, reportURI);
	     
	     return mapping.findForward(VIEW_SHIPMENT_DOCUMENT_PDF);
	}
	
	
	

/*	public static String VIEW_PAYMENT_DOCUMENT_PDF = "viewPaymentDocumentPDF";
	public ActionForward viewPaymentDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     DefaultForm df= (DefaultForm) form;
	        
	     DocumentBean bean = (DocumentBean) df.getBean();
	     
	     String pdfPath = POSReportManager.getPaymentPDFReport(ctx, bean.getDocumentId(), null);
	     pdfPath = ReportManager.getReportURI(pdfPath,request);
	     
	     request.setAttribute(Constants.DOCUMENT_PDF, pdfPath);
	     
	     return mapping.findForward(VIEW_PAYMENT_DOCUMENT_PDF);
	}	
*/	
	
	/*public static String VIEW_INVOICEFROMORDER_DOCUMENT_PDF = "viewInvoiceFromOrderDocumentPDF";
	public ActionForward viewInvoiceFromOrderDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     DefaultForm df= (DefaultForm) form;
	        
	     DocumentBean bean = (DocumentBean) df.getBean();
	     
	     String pdfPath = POSReportManager.getInvoiceFromOrderPDFReport(ctx, bean.getDocumentId(), null);
	     pdfPath = ReportManager.getReportURI(pdfPath,request);
	     
	     request.setAttribute(Constants.DOCUMENT_PDF, pdfPath);
	     
	     return mapping.findForward(VIEW_INVOICEFROMORDER_DOCUMENT_PDF);
	}*/
	
	/*========================================================================*/
	
	public static final String VIEW_INVENTORY_DOCUMENT_PDF = "viewInventoryDocumentPDF";
	
	/**
	 * Generate Inventory Document after Inventory count
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws ApplicationException
	 * @throws OperationException
	 * @throws DocumentException 
	 * @throws IOException 
	 * @return
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	
	public ActionForward viewInventoryDocumentPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, DocumentException, IOException, SQLException, ParseException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     
	     InventoryForm If = (InventoryForm)form;	        
	     InventoryBean bean = (InventoryBean) If.getBean();
	     
	     ArrayList<Object[]> reportData = InventoryManager.getInventoryReportData(ctx, bean.getInventoryId(), null);

	     String title = "";
	     
	     MInventory inv = MInventory.get(ctx, bean.getInventoryId());
	     
	     if(inv.getDescription() == null)
	     {
	         title = "Inventory Adjustment Report";
	     }
	     else
	     {
	         title = inv.getDescription();
	     }
	     
	     String subtitle  = null;
	     
	     String sql = "Select Name from AD_Org where AD_org_ID=?";
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
		
	     pstmt = DB.prepareStatement(sql.toString(), null); 
	     pstmt.setInt(1, Env.getAD_Org_ID(ctx));
		
	     try
	     {
	    	 rs = pstmt.executeQuery();
    		
	    	 if (rs.next())
	    	 {
	    		 subtitle = rs.getString(1);
	    	 }
    		    		
	     }
	     catch(SQLException e)
	     {
	    	 throw new OperationException(e);
	     }
	     finally 
	     {
	    	 DB.close(rs);
	    	 DB.close(pstmt);
    	 }
	     
	     //constructing the table
	     TabularReport tReport = new TabularReport(reportData);    	
	     tReport.setSortable(false);
	     tReport.setStyle("display");
	     tReport.setTitle(title);
	     tReport.setSubtitle(subtitle);
	     tReport.createReport();
	     
	     response.setContentType("x-application/pdf");
	     response.setHeader("Content-Disposition","inline;filename=Inventory"+System.currentTimeMillis()+".pdf");
	     response.getOutputStream().write(tReport.getPDFData());
	     
	     return null;
	}
	
}
