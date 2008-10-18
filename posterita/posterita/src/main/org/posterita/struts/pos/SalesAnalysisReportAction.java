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
 * Created on 25-Jul-2005 by alok
 *
 */


package org.posterita.struts.pos;


public class SalesAnalysisReportAction extends POSDispatchAction
{
	/*public static final String GET_CURRENT_DAY_REPORT = "getCurrentDayReport";	
	public ActionForward getCurrentDayReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
       
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Calendar cal = Calendar.getInstance();
        
        Timestamp toDate = new Timestamp(cal.getTimeInMillis());
        
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        
        Timestamp fromDate = new Timestamp(cal.getTimeInMillis());
        
        ArrayList list=POSReportManager.getSalesAnalysisReport(ctx,fromDate,toDate);
        request.getSession().setAttribute(org.posterita.Constants.SALES_ANALYSIS,list);        
        
		return mapping.findForward(GET_CURRENT_DAY_REPORT);
	}*/
	
	/*public static final String GET_CURRENT_MONTH_REPORT = "getCurrentMonthReport";
	public ActionForward getCurrentMonthReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
        
        String toDate = sdf.format(new Date(cal.getTimeInMillis()));
        
        cal.set(Calendar.DATE,1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        
        String fromDate = sdf.format(new Date(cal.getTimeInMillis()));
        
        //String report = SalesAnalysisReportManager.getPDFReport(ctx,fromDate,toDate);
        
        ArrayList list=POSReportManager.getSalesAnalysisReport(ctx,fromDate,toDate);
        request.getSession().setAttribute(org.posterita.Constants.SALES_ANALYSIS,list); 
        
		return mapping.findForward(GET_CURRENT_MONTH_REPORT);
	}
	*/
	/*public static final String GET_CUSTOM_REPORT = "getCustomReport";
	public ActionForward getCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;        
       
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();        
        Timestamp fromDate = ReportDateManager.getFromDate(bean);
        Timestamp toDate = ReportDateManager.getToDate(bean);
        
        ArrayList list=POSReportManager.getSalesAnalysisReport(ctx,fromDate,toDate);
        request.getSession().setAttribute(org.posterita.Constants.SALES_ANALYSIS,list);
        
		return mapping.findForward(GET_CUSTOM_REPORT);
	}*/
	
	/*public static final String GET_JPEG_REPORT = "getJpegReport";
	public ActionForward getJpegReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{        
        try 
        {        	
        	ActionForward fwd= init(mapping,form,request,response);
            if(fwd!=null)
                return fwd;  
            
			
			String reportDir = ReportManager.getReportDirectoryFromServletContext(this.servlet);
			
			String filename = RandomStringGenerator.randomstring();
			
			PieChart pieChart = new PieChart();	
			pieChart.getDataSetFromSQL("" +
					" select t.ATTR_MODEL \"Model\"," +
					" sum(AMTACCTCR) \"Sum\"" +
					" from FACT_ACCT f,M_PRODUCT p,U_TSHIRT_V t" +
					" where f.AD_CLIENT_ID = 1016840" +
					" and f.M_PRODUCT_ID = p.M_PRODUCT_ID" +
					" and f.AMTACCTCR > 0" +
					" and t.M_PRODUCT_ID = p.M_PRODUCT_ID" +
					" having sum(AMTACCTCR) < 1000" +
					" group by t.ATTR_MODEL");
			
			pieChart.saveChartAsJPEG(reportDir+filename+".jpg",700,600);
			
			String imgSrc = request.getContextPath() + "/config/reports/"+filename+".jpg";			
			String imagemaptag = pieChart.getImageMap(filename);
			String imagetag = "<img class='chart' src='"+ imgSrc +"' usemap='#"+ filename +"'/>";

			request.getSession().setAttribute("CHART",imagemaptag+imagetag);
		
		} 
        catch (Exception e) 
		{
			e.printStackTrace();
		}
        
        return mapping.findForward(GET_JPEG_REPORT);
	}*/
	
	
}
