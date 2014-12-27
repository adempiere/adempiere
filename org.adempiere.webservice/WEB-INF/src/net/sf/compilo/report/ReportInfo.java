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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

//import com._3e.tools.KwotaSlownie.*;

/**
 * 	@author 	Peter Shen
 * 	@version 	$Id: ReportInfo.java,v 1.5 2005/08/04 09:42:41 pshen Exp $
 **/
public class ReportInfo
{	
	private String reportFile;
    private boolean directPrint;
    private int ReportViewID = 0;
    private int PrintFormatID = 0;
    private int m_AD_Process_ID;
    /** The field that points to the JasperReport or
        to the main Report in case of subreports. Marco LOMBARDO
    */
    private JasperReport jasperReport;
    private HashMap subReport = new HashMap();
    private boolean hasSubReport = false;
    private boolean hasError = false;
    private ArrayList errorMsg = new ArrayList();
    private CLogger log = CLogger.getCLogger(ReportInfo.class);
    
    public ReportInfo ()
    {        
    }
    
    public ReportInfo( int AD_Process_ID)
    {    
        this.m_AD_Process_ID = AD_Process_ID;
        getReportDefinition(AD_Process_ID);
        if(hasError)
            return;
            
        processReport(this.reportFile);        
    }    

	
    protected File processReport(String reportFile) 
    {
        log.finest("ReportInfo.processReport - " + reportFile);
        File JasperDesignFile = new File(reportFile);
        String JasperReportFile = reportFile.replaceAll(".jrxml", ".jasper").replaceAll(".xml", ".jasper");
        File jasperFile = new File(JasperReportFile);
        // Marco LOMBARDO: this is the local one, there is the same 
        // field at class level. There was a bug on it about subreports.
        JasperReport jasperReport = null;
        
        if (jasperFile.exists())
        { 	// test time
            if (JasperDesignFile.lastModified() <= jasperFile.lastModified())
            {
                try
                {
                    jasperReport = (JasperReport)JRLoader.loadObject(jasperFile.getAbsolutePath());   // Marco LOMBARDO: should refer to local.
                }
                catch (JRException e)
                {
                    this.jasperReport = null;   // Marco LOMBARDO: on error reset global report.
                    log.saveError("ReportServer.processReport: Can not load report - ", e);
                }
            }
            else
            {
                jasperReport = compileReport( JasperDesignFile, jasperFile);   // Marco LOMBARDO: should refer to local.
            }
        }
        else
        { // create new jasper file
            // Marco LOMBARDO: should refer to local.
            jasperReport = compileReport( JasperDesignFile, jasperFile);
        }        
        if(hasError)
        	return null;
        // Marco LOMBARDO: report is ready.
        // Marco LOMBARDO: if the global field is null, set it.
        // Marco LOMBARDO: this should happen only after compile the main report.
        if (this.jasperReport == null)
           this.jasperReport = jasperReport;
        
        if(jasperReport != null)   // Marco LOMBARDO: should refer to local.
        {
            String[] extension = {".xml", "jrxml"};
            File[] subreports = JasperDesignFile.getParentFile().listFiles( new FileFilter( JasperReportFile.replaceAll(".jasper","")+"Subreport", JasperDesignFile.getParentFile(), extension));
            for( int i=0; i<subreports.length; i++)
            {
            	log.finest("The subreport file @ " + subreports[i].getAbsolutePath());
                File sub = processReport(subreports[i].getAbsolutePath());
                String subName = sub.getName();
                int pos = sub.getName().indexOf('.');
                if (pos!=-1)
                	subName = subName.substring(0, pos);
                subReport.put(subName,sub.getAbsolutePath());  
                this.hasSubReport = true;
            }
        }
        
        if(hasError)
        	return null;
        return jasperFile;
    }
    
    /**
	 *	Compile xml file to jasper File
	 */     
    private JasperReport compileReport( File reportFile, File jasperFile)
    {
        JasperReport res = null;
        try
        {
	    String compiere_home = (String)System.getProperty("COMPIERE_HOME");
//		compiere_home = "C:/compiere/acompiere2/";
  	    //log.info( "compiere_home = "+compiere_home);
   	    System.setProperty("jasper.reports.compile.class.path", compiere_home+"/lib/reporttools.jar;"+compiere_home+"/lib/Compiere.jar");
            JasperCompileManager.compileReportToFile( reportFile.getAbsolutePath(), jasperFile.getAbsolutePath());
            jasperFile.setLastModified( reportFile.lastModified());
            res = (JasperReport)JRLoader.loadObject(jasperFile.getAbsolutePath());
        }
        catch (JRException e)
        {
            log.info("ReportInfo.compileReport- "+ e);
            this.errorMsg.add("ReportInfo.compileReport- " + e.getMessage());
            this.hasError = true;           
        }
        return res;
    }
    
    /**
	 *	Get JasperReport xml file path or ReportView and PrintFormat definition 
	 */     
    private void getReportDefinition(int AD_Process_ID)
    {    	
        String sql = "SELECT pr.JasperReport, pr.AD_ReportView_ID, pr.AD_PrintFormat_ID, " 
                       + "pr.IsDirectPrint from AD_Process pr "
                       + "WHERE pr.AD_Process_Id=? ";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            pstmt.setInt(1, AD_Process_ID);
            rs = pstmt.executeQuery();
            String path = null;
            String tmp = null;
            if (rs.next())
            {
                path = rs.getString(1);
                this.ReportViewID = rs.getInt(2);
                this.PrintFormatID = rs.getInt(3);
                tmp = rs.getString(4);
            }
            else
            {
                String err = "ReportData.getReportFileName data not found -" + sql;
                errorMsg.add(err);
                hasError = true;
                log.saveError(err, "");
                return;
            }
           
           this.directPrint = (tmp.equalsIgnoreCase("y"))?true:false;
           File xmlfile = new File(ReportProcessor.REPORT_HOME, path);
           if(xmlfile != null && xmlfile.exists())               
               this.reportFile = xmlfile.getAbsolutePath();     
           else
           {
               hasError = true;
               log.saveError("Error", "The Report File not exists");
           }
           rs.close();
           pstmt.close();
        }
        catch (SQLException e)
        {
            log.saveError("ReportData.getReportFileName - " + sql, e);
            this.errorMsg.add("ReportData.getReportFileName - " + sql);
            this.hasError = true;           
       		this.directPrint = false;
       		this.reportFile = null;
        }
        catch (Exception ee)
        {
            log.saveError("ReportData.getReportFileName - " + sql, ee);
            this.errorMsg.add("ReportData.getReportFileName - " + sql);
            this.hasError = true;           
       		this.directPrint = false;
       		this.reportFile = null;
        }
        finally
        {
            try
            {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
            }
            catch(Exception ex){}
        }        
        log.info("Get ReportDefinition-" + this.toString());
    }
    /*
    private void getResource()
    {
//      Resources
        File[] resources = 
        	reportInfo.getReportFile().getParentFile().listFiles( 
        			new FileFilter( jasperName, reportInfo.getReportFile().getParentFile(), ".properties"));
        File resFile = null;
        // try baseName + "_" + language
        for( int i=0; i<resources.length; i++)
        {
        	if ( resources[i].getName().equals( jasperName+currLang.getLocale().getLanguage()+".properties"))
        	{
            	resFile=resources[i];
                break;
            }
        }
        if (resFile==null)
        {
        	// try baseName only
            for( int i=0; i<resources.length; i++)
            {
            	if ( resources[i].getName().equals( jasperName+".properties"))
            	{
                	resFile=resources[i];
                    break;
                 }
            }
        }
        if (resFile!=null)
        {
        	try
        	{
            	PropertyResourceBundle res = new PropertyResourceBundle( new FileInputStream(resFile));
                m_Param.put("RESOURCE", res);
            }
            catch (IOException e)
            {}
        }
    }
    */
    
    
    
    protected boolean isDirty()
    {
        if(this.reportFile == null)
            return true;
        
        if(this.jasperReport == null)
            return true;
        
        if(isDirty(this.reportFile))
            return true;
        
        if(hasSubReport)
        {
	         String[] extension = {".xml", "jrxml"};
	         File reportDesighFile = new File(this.reportFile);
	         File[] subreports = reportDesighFile.getParentFile().listFiles( new FileFilter( reportFile.replaceAll(".jasper","")+"Subreport", reportDesighFile.getParentFile(), extension));
	         for( int i=0; i<subreports.length; i++)
	         {
	         	log.finest("The subreport file @ " + subreports[i].getAbsolutePath());
	         	if(isDirty(subreports[i].getAbsolutePath()))
	         	    return true;
	         }
        }
        return false;
    }
    
    private boolean isDirty(String file)
    {
        File JasperDesignFile = new File(file);
        String JasperReportFile = this.reportFile.replaceAll(".jrxml", ".jasper").replaceAll(".xml", ".jasper");
        File jasperFile = new File(JasperReportFile);
        if (jasperFile.exists())
        { 	
            // test time
            if (JasperDesignFile.lastModified() > jasperFile.lastModified())
            {
                return true;
            }
        }
        else
            return true;
        
        return false;
    }
    
    public String toString()
    {
        return "AD_Process_ID:" + m_AD_Process_ID + " XMLFile:" + this.reportFile + " ReportView_ID:" + this.ReportViewID;
    }
    /**
     * @return Returns the reportViewID.
     */
    public int getReportViewID()
    {
        return ReportViewID;
    }
    /**
     * @param reportViewID The reportViewID to set.
     */
    protected void setReportViewID(int reportViewID)
    {
        ReportViewID = reportViewID;
    }
    /**
     * @return Returns the directPrint.
     */
    protected boolean isDirectPrint()
    {
        return directPrint;
    }
    /**
     * @return Returns the errorMsg.
     */
    protected ArrayList getErrorMsg()
    {
        return errorMsg;
    }
    /**
     * @return Returns the hasError.
     */
    protected boolean hasError()
    {
        return hasError;
    }
    /**
     * @return Returns the jasperReport.
     */
    public JasperReport getJasperReport()
    {
        return jasperReport;
    }
    /**
     * @return Returns the subReport.
     */
    protected HashMap getSubReport()
    {
        return subReport;
    }
    
}
