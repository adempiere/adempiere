/**
 *   reportServer for JasperReport.
 *   Copyright (C) 2004  Peter Shen.
 *   Shanghai, China.
 *   Email: zpshen@gmail.com
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *   Contributor: Marco LOMBARDO, Compilo subAdministrator.
 *                lombardo@mayking.com, mar9000@gmail.com
 *                Italy.
 **/
package net.sf.compilo.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.compilo.data.CompiereDataSourceFactory;
import net.sf.compilo.data.compiereDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *  Report Server
 *
 * 	@author 	Peter Shen
 * 	@version 	$Id: ReportProcessor.java,v 1.7 2005/09/03 04:09:51 pshen Exp $
 *	@description:	report server
 */
public class ReportProcessor
{
	public static final String REPORT_PATH__fix = "c:/compiere/compiere-all/reports";
	
	public static File REPORT_HOME = null;
	private static CLogger log = CLogger.getCLogger(ReportProcessor.class);
	
    static
    {
        String reportPath = REPORT_PATH__fix;// "c:/compiere/compiere-all/reports";//System.getProperty("REPORT_HOME");
		if (reportPath == null || reportPath.length() == 0)
			reportPath = System.getProperty("COMPIERE_HOME")+ System.getProperty("file.separator")+ "reports";
		//System.setProperty("jasper.reports.compiler.class", "net.sf.jasperreports.engine.design.JRCompiler");
		String classpath = 	Thread.currentThread().getContextClassLoader().getResource("net/sf/jasperreports/engine").toString();	
		System.setProperty("jasper.reports.compile.temp", reportPath);
		System.setProperty("jasper.reports.compile.class.path", classpath.split("file:/")[1].split("!")[0]);
		//System.setProperty("jasper.reports.compiler.class", "net.sf.jasperreports.engine.design.JRBshCompiler");
				
		REPORT_HOME = new File(reportPath);        
		if(!REPORT_HOME.exists())
		    log.saveError("The Report_Home not exists", REPORT_HOME.getAbsolutePath());
		
		log.info("Set REPORT_HOME to " + REPORT_HOME.getAbsolutePath());
    }
        
	public ReportProcessor (Properties ctx, ProcessInfo pi)
	{
	    System.setProperty("jasper.reports.compile.keep.java.file", "true");
		m_AD_PInstance_ID = pi.getAD_PInstance_ID();
		m_AD_Process_ID	= pi.getAD_Process_ID();
        m_Record_ID = pi.getRecord_ID();        
        //m_isPrint = pi.getIsPrint();
        m_Param = new HashMap();        
        m_ctx = ctx;
        m_pi = pi;
	}   //  ReportServer
	
	public JasperPrint runReport()
	{
	    System.setProperty("jasper.reports.compile.keep.java.file", "true");
		ReportPool rp = new ReportPool();
		ReportInfo reportInfo = rp.getReport(m_AD_Process_ID);
		rp = null;
		
		if (!reportInfo.hasError() && reportInfo.getJasperReport()!= null) 
		{   
			// add parameter
			m_Param.putAll(reportInfo.getSubReport());
            addProcessParameters( m_AD_PInstance_ID, m_Param);
            m_Param.put("RECORD_ID", new Integer( m_Record_ID));
	    // Marco LOMBARDO: REPORT_HOME used to express subreports path.
            m_Param.put("REPORT_HOME", REPORT_PATH__fix ); //System.getProperty("REPORT_HOME"));
        // End Marco LOMBARDO.
            Language currLang = Env.getLanguage(Env.getCtx());
            m_Param.put("CURRENT_LANG", currLang.getAD_Language());

	    java.sql.Connection conn = DB.getConnectionRO();
	    m_Param.put("REPORT_CONNECTION", conn );  //DB_CONN
	    m_Param.put("DB_CONN", conn );
	    System.out.println(  "REPORT_CONNECTION = "+conn.toString() );
	    log.saveError("REPORT_CONNECTION = "+conn.toString(), "");
	    //System.out.println(  "REPORT_HOME = "+System.getProperty("REPORT_HOME") );
        
			// fill report
            try
            {
                compiereDataSource ds = CompiereDataSourceFactory.createDataSource(m_ctx, reportInfo, m_pi, m_Param);
            	//compiereDataSource ds = new compiereDataSource (m_ctx, reportInfo.getJasperReport());
            	m_jasperPrint = JasperFillManager.fillReport( reportInfo.getJasperReport(), m_Param, ds);
            	ds.close();
            	
            	log.finest("ReportProcessor.fillReport");
            }
            catch (JRException e)
            {
            	m_jasperPrint = null;
            	e.printStackTrace();
            }
            catch (Exception e)
            {
            	e.printStackTrace();
            }
        }
		else
			System.out.println("Error:" + reportInfo.getErrorMsg());
		
        return m_jasperPrint;
	}// runReport   
 
    
    private void addProcessParameters( int AD_PInstance_ID, Map params) 
    {
        log.finest("ReportStarter.addProcessParameters");
        MPInstance pinstance = new MPInstance(m_ctx, AD_PInstance_ID, null);
        MPInstancePara[] pinstancePara = pinstance.getParameters();
        
        m_Record_ID = pinstance.getRecord_ID();
        params.put("AD_Client_ID",new Integer(pinstance.getAD_Client_ID()));
        params.put("AD_Org_ID",new Integer(pinstance.getAD_Org_ID()));
        
        for(int i=0; i<pinstancePara.length; i++)
        {
            MPInstancePara para = pinstancePara[i];
            if (para.getInfo() != null) 
            {
                if (para.getInfo_To()!=null) 
                {
                    params.put( para.getParameterName()+"_Info1", para.getInfo());
                    params.put( para.getParameterName()+"_info2", para.getInfo_To());
                } 
                else 
                    params.put( para.getParameterName()+"_Info", para.getInfo());
                
            }
            else
            {
            	params.put( para.getParameterName()+"_Info", "");
            	params.put( para.getParameterName()+"_Info1", "");
                params.put( para.getParameterName()+"_info2", "");
            }
            
            if (para.getP_String() != null) 
            {
                if (para.getP_String_To()!=null) {
                    params.put( para.getParameterName()+"1", para.getP_String());
                    params.put( para.getParameterName()+"2", para.getP_String_To());
                } 
                else 
                    params.put( para.getParameterName(), para.getP_String());
                
            } 
            else if (para.getP_Date() != null) 
            {
                if (para.getP_Date()!=null) 
                {
                    params.put( para.getParameterName()+"1", para.getP_Date());
                    params.put( para.getParameterName()+"2", para.getP_Date_To());
                } 
                else 
                    params.put( para.getParameterName(), para.getP_Date());
                
            } 
            else if (para.getP_Number() != null)
            {
                if (para.getP_Number_To() != null
                        && !para.getP_Number_To().equals(""))
                {
                    try
                    {
                        Integer pIntNum = Integer.valueOf(para.getP_Number()
                                .toString());
                        Integer pIntNumTo = Integer.valueOf(para
                                .getP_Number_To().toString());
                        params.put(para.getParameterName() + "1", pIntNum);
                        params.put(para.getParameterName() + "2", pIntNumTo);
                    }
                    catch (NumberFormatException nfe)
                    {
                        try
                        {
                            Double pDoubleNum = Double.valueOf(para
                                    .getP_Number().toString());
                            Double pDoubleNumTo = Double.valueOf(para
                                    .getP_Number_To().toString());
                            params.put(para.getParameterName() + "1",
                                    pDoubleNum);
                            params.put(para.getParameterName() + "2",
                                    pDoubleNumTo);
                        }
                        catch (NumberFormatException nfe2)
                        {
                            params.put(para.getParameterName() + "1", para
                                    .getP_Number());
                            params.put(para.getParameterName() + "2", para
                                    .getP_Number_To());
                        }
                    }

                }
                else
                {
                    try
                    {
                        Integer pIntNum = Integer.valueOf(para.getP_Number()
                                .toString());
                        params.put(para.getParameterName(), pIntNum);
                    }
                    catch (NumberFormatException nfe)
                    {
                        try
                        {
                            Double pDoubleNum = Double.valueOf(para
                                    .getP_Number().toString());
                            params.put(para.getParameterName(), pDoubleNum);
                        }
                        catch (NumberFormatException nfe2)
                        {
                            params.put(para.getParameterName(), para
                                    .getP_Number());
                        }
                    }
                }
            }            
        }
        
    }
    
    private int m_AD_PInstance_ID = 0;
    private int m_AD_Process_ID = 0;
    private int m_Record_ID = 0;
    private boolean m_isPrint = false;
    private HashMap m_Param = null;
    private Properties m_ctx = null;   
    private JasperPrint m_jasperPrint = null;
    private ProcessInfo m_pi = null;
}
