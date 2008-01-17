/*
 */
package org.compiere.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.compiere.db.CConnection;
import org.compiere.db.ServerConnection;
import org.compiere.interfaces.MD5;
import org.compiere.interfaces.MD5Home;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MProcess;
import org.compiere.process.ClientProcess;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.compiere.util.Trx;
import org.compiere.utils.DBUtils;
import org.compiere.utils.DigestOfFile;

/**
 * @author rlemeill
 * originaly coming from an application note from compiere.co.uk
 * ---
 * Modifications: Allow Jasper Reports to be able to be run on VPN profile (i.e: no direct connection to DB).
 *                Implemented ClientProcess for it to run on client.
 * @author Ashley Ramdass 
 */
public class ReportStarter implements ProcessCall, ClientProcess {
//logger
	private static CLogger log = CLogger.getCLogger(ReportStarter.class);
	private static File REPORT_HOME = null;
	
	private static JRViewerProvider viewerProvider = new SwingJRViewerProvider();

    static {
        System.setProperty( "javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
        System.setProperty( "org.xml.sax.driver", "org.apache.xerces.parsers.SAXParser");

        String reportPath = System.getProperty("org.compiere.report.path");
        if (reportPath == null) {
        	REPORT_HOME = new File(Ini.getAdempiereHome() + File.separator + "reports");
        } else {
			REPORT_HOME = new File(reportPath);
        }
    }

	private ProcessInfo processInfo;
	private MAttachment attachment;

    
    /**
     * @param requestURL
     * @return true if the report is on the same ip address than Application Server
     */
    private boolean isRequestedonAS(URL requestURL)
    {
    	boolean tBool = false;
    	try{
    		InetAddress[] request_iaddrs = InetAddress.getAllByName(requestURL.getHost());
    		InetAddress as_iaddr = InetAddress.getByName(CConnection.get().getAppsHost());
    		for(int i=0;i<request_iaddrs.length;i++)
    		{
    			log.info("Got "+request_iaddrs[i].toString()+" for "+requestURL+" as address #"+i);
    			if(request_iaddrs[i].equals(as_iaddr))
    			{
    				log.info("Requested report is on application server host");
    				tBool = true;
    				break;
    			}
    		}
    	}
    	catch (UnknownHostException e) {
    		log.severe("Unknown dns lookup error");
    		return false;
    	}
    	return tBool;
    	
    }
    
    /**
     * @return true if the class org.compiere.interfaces.MD5Home is present
     */
    private boolean isMD5HomeInterfaceAvailable()
    {
    	try
		{
    		Class md5HomeClass = Class.forName("org.compiere.interfaces.MD5Home");
    		log.info("EJB client for MD5 remote hashing is present");
    		return true;
		}
    	catch (ClassNotFoundException e)
		{
    		log.warning("EJB Client for MD5 remote hashing absent\nyou need the class org.compiere.interfaces.MD5Home - from webEJB-client.jar - in classpath");
    		return false;	
		}
    }
    
    /**
     * @param requestedURLString
     * @return md5 hash of remote file computed directly on application server
     * 			null if problem or if report doesn't seem to be on AS (different IP or 404)
     */
    private String ejbGetRemoteMD5(String requestedURLString)
    {
		InitialContext context = null;
		String md5Hash = null;
    	try {
    		URL requestURL = new URL(requestedURLString);
    		//String requestURLHost = requestURL.getHost();
    		Hashtable env = new Hashtable();
    		env.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
    		env.put(InitialContext.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
    		env.put(InitialContext.PROVIDER_URL, requestURL.getHost() + ":" + CConnection.get().getAppsPort());
    		context = new InitialContext(env);
    		if (isRequestedonAS(requestURL) && isMD5HomeInterfaceAvailable())
    		{
    			MD5Home home = (MD5Home)context.lookup(MD5Home.JNDI_NAME);
    			MD5 md5 = home.create();
    			md5Hash = md5.getFileMD5(requestedURLString);
    			log.info("MD5 for " + requestedURLString + " is " + md5Hash);
    			md5.remove();
    		}
  
    	}
    	catch (MalformedURLException e) {
    		log.severe("URL is invalid: "+ e.getMessage());
    		return null;
    	}
    	catch (NamingException e){
    		log.warning("Unable to create jndi context did you deployed webApp.ear package?\nRemote hashing is impossible");
    		return null;
    	}
    	catch (RemoteException e){
    		log.warning("Unknown remote error exception");
    		return null;
    	}
    	catch(CreateException e){
    		log.warning("Error in RemoteInterface creation");
			return null;
    	}
    	catch(RemoveException e){
    		log.warning("Error in RemoteInterface removing");
    		return null;
    	}
    	return md5Hash;
    }
    
    /**
     * @author rlemeill
     * @param reportLocation http://applicationserver/webApp/standalone.jrxml for exemple
     * @param localPath Where to put the http downloadede file
     * @return abstract File wich represent the downloaded file
     */
    private File getRemoteFile(String reportLocation, String localPath)
    {
    	try{
    		URL reportURL = new URL(reportLocation); 
			InputStream in = reportURL.openStream();
			
    		File downloadedFile = new File(localPath);

    		if (downloadedFile.exists())
    		{
    			downloadedFile.delete();
    		}
    		
    		FileOutputStream fout = new FileOutputStream(downloadedFile);
			
			byte buf[] = new byte[1024];
			int s = 0;
			long tl = 0;
			
			while((s = in.read(buf, 0, 1024)) > 0)
				fout.write(buf, 0, s);
			
    		in.close();
    		fout.flush();
    		fout.close();
    		return downloadedFile;
    	} catch (FileNotFoundException e) {
			if(reportLocation.indexOf("Subreport") == -1) // Only show the warning if it is not a subreport
				log.warning("404 not found: Report cannot be found on server "+ e.getMessage());
    		return null;
    	} catch (IOException e) {
			log.severe("I/O error when trying to download (sub)report from server "+ e.getMessage());
    		return null;
    	}
    }
	
	/**
	 * Search for additional subreports deployed to a webcontext if
	 * the parent report is located there
	 * @author deathmeat
	 * @param reportName The original reportname
	 * @param reportPath The full path to the parent report
	 * @param fileExtension The file extension of the parent report
	 * @return An Array of File objects referencing to the downloaded subreports
	 */
	private File[] getHttpSubreports(String reportName, String reportPath, String fileExtension)
	{
		ArrayList<File> subreports = new ArrayList<File>();
		String remoteDir = reportPath.substring(0, reportPath.lastIndexOf("/"));
		
		// Currently check hardcoded for max. 10 subreports
		for(int i=1; i<10; i++)
		{
			// Check if subreport number i exists
			File subreport = httpDownloadedReport(remoteDir + "/" + reportName + i + fileExtension);
			if(subreport == null) // Subreport doesn't exist, abort further approaches
				break;
			
			subreports.add(subreport);
		}
		
		File[] subreportsTemp = new File[0];
		subreportsTemp = subreports.toArray(subreportsTemp);
		return subreportsTemp;
	}
    
    /**
     * @author rlemeill
     * @param reportLocation http string url ex: http://adempiereserver.domain.com/webApp/standalone.jrxml
     * @return downloaded File (or already existing one)
     */
    private File httpDownloadedReport(String reportLocation)
    {
    	File reportFile = null;
    	File downloadedFile = null;
    	log.info(" report deployed to " + reportLocation);
    	try {
    		
    		
    		String[] tmps = reportLocation.split("/");
    		String cleanFile = tmps[tmps.length-1];
    		String localFile = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + cleanFile;
    		String downloadedLocalFile = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator")+"TMP" + cleanFile;
    		
    		reportFile = new File(localFile);
    		
    		
    		if (reportFile.exists())
    		{
    			String localMD5hash = DigestOfFile.GetLocalMD5Hash(reportFile);
    			String remoteMD5Hash = ejbGetRemoteMD5(reportLocation);
    			log.info("MD5 for local file is "+localMD5hash );
    			if ( remoteMD5Hash != null)
    			{
    				if (localMD5hash.equals(remoteMD5Hash))
    				{
    					log.info(" no need to download: local report is up-to-date");
    				}
    				else
    				{
    					log.info(" report on server is different that local one, download and replace");
    					downloadedFile = getRemoteFile(reportLocation, downloadedLocalFile);
    					reportFile.delete();
    					downloadedFile.renameTo(reportFile);
    				}
    			}
    			else
    			{
    				log.warning("Remote hashing is not available did you deployed webApp.ear?");
    				downloadedFile = getRemoteFile(reportLocation, downloadedLocalFile);
    				//    				compare hash of existing and downloaded
    				if ( DigestOfFile.md5localHashCompare(reportFile,downloadedFile) )
    				{
    					//nothing file are identic
    					log.info(" no need to replace your existing report");
    				}
    				else
    				{
    					log.info(" report on server is different that local one, replacing");
    					reportFile.delete();
    					downloadedFile.renameTo(reportFile);
    				}
    			}
    		}
    		else
    		{
    			reportFile = getRemoteFile(reportLocation,localFile);
    		}
    		
    	}
    	catch (Exception e) {
    		log.severe("Unknown exception: "+ e.getMessage());
    		return null;
    	}
    	return reportFile;
    }
    
    /**
     * Returns the Server Connection if direct connection is not available
     * (VPN, WAN, Terminal) and thus query has to be run on server only else return
     * a direct connection to DB.
     * 
     * Notes: Need to refactor and integrate in DB if confirmed to be working as
     * expected.
     * 
     * @author Ashley Ramdass
     * @return Connection DB Connection
     */
    protected Connection getConnection()
    {
    	if (DB.isRemoteObjects())
    	{
    		return new ServerConnection();
    	}
    	else 
    	{
    		return DB.getConnectionRW();
    	}
    }
    
    /**
	 *  Start the process.
	 *  Called then pressing the Process button in R_Request.
	 *  It should only return false, if the function could not be performed
	 *  as this causes the process to abort.
	 *  @author rlemeill
	 *  @param ctx  context
	 *  @param pi            Compiere standard process info
	 *  @param trx
	 *  @return true if success
	 */
    public boolean startProcess(Properties ctx, ProcessInfo pi, Trx trx) {
		
    	processInfo = pi;
		String Name=pi.getTitle();
        int AD_PInstance_ID=pi.getAD_PInstance_ID();
        int Record_ID=pi.getRecord_ID();
        log.info( "Name="+Name+"  AD_PInstance_ID="+AD_PInstance_ID+" Record_ID="+Record_ID);
        String trxName = null;
        if (trx != null) {
        	trxName = trx.getTrxName();
        }
        ReportData reportData = getReportData( pi, trxName);
        if (reportData==null) {
            reportResult( AD_PInstance_ID, "Can not find report data", trxName);
            return false;
        }

        String reportPath =  reportData.getReportFilePath();
        if ((reportPath==null)||(reportPath.length()==0)) {
            reportResult( AD_PInstance_ID, "Can not find report", trxName);
            return false;
        }
        if (Record_ID!=-1) {
			JasperData data = null;
			File reportFile = null;
			String fileExtension = "";
			HashMap params = new HashMap( ctx);
			
			addProcessParameters( AD_PInstance_ID, params, trxName);
			
			addProcessInfoParameters(params, pi.getParameter());
			
			reportFile = getReportFile(reportPath, (String)params.get("ReportType"));
			
			if (reportFile == null || reportFile.exists() == false) 
			{
				log.severe("No report file found for given type, falling back to " + reportPath);
				reportFile = getReportFile(reportPath);
			}
			
			if (reportFile == null || reportFile.exists() == false) {
				String tmp = "Can not find report file at path - " + reportPath;
				log.severe(tmp);
				reportResult(AD_PInstance_ID, tmp, trxName);
			}

			if (reportFile != null)
			{
				data = processReport(reportFile);
				fileExtension = reportFile.getName().substring(reportFile.getName().lastIndexOf("."),
						reportFile.getName().length());
			}
			else
			{
				return false;
			}
			
			JasperReport jasperReport = data.getJasperReport();
            String jasperName = data.getJasperName();
            File reportDir = data.getReportDir();

            if (jasperReport != null) {
				File[] subreports;
				
                // Subreports
				if(reportPath.startsWith("http://") || reportPath.startsWith("https://"))
				{
					// Locate and download subreports from remote webcontext
					subreports = getHttpSubreports(jasperName + "Subreport", reportPath, fileExtension);
				}
				else if (reportPath.startsWith("attachment:")) 
				{
					subreports = getAttachmentSubreports(reportPath);
				}
				else if (reportPath.startsWith("resource:")) 
				{
					subreports = getResourceSubreports(jasperName + "Subreport", reportPath, fileExtension);
				}
				// TODO: Implement file:/ lookup for subreports
				else
				{
					// Locate subreports from local/remote filesystem
					subreports = reportDir.listFiles( new FileFilter( jasperName+"Subreport", reportDir, fileExtension));
				}
				
                for( int i=0; i<subreports.length; i++) {
                    JasperData subData = processReport( subreports[i]);
                    if (subData.getJasperReport()!=null) {
                        params.put( subData.getJasperName(), subData.getJasperFile().getAbsolutePath());
                    }
                }

                params.put("RECORD_ID", new Integer( Record_ID));
                
                // contribution from Ricardo (ralexsander)
                // in iReports you can 'SELECT' AD_Client_ID, AD_Org_ID and AD_User_ID using only AD_PINSTANCE_ID
                params.put("AD_PINSTANCE_ID", new Integer( AD_PInstance_ID));
                
                Language currLang = Env.getLanguage(Env.getCtx());
                params.put("CURRENT_LANG", currLang.getAD_Language());
                // Resources
                File resFile = null;
                if (reportPath.startsWith("attachment:") && attachment != null) {
                	resFile = getAttachmentResourceFile(jasperName, currLang);
                } else if (reportPath.startsWith("resource:")) {
                    	resFile = getResourcesForResourceFile(jasperName, currLang);
                // TODO: Implement file:/ for resources
                } else {
	                File[] resources = reportDir.listFiles( new FileFilter( jasperName, reportDir, ".properties"));                
	                // try baseName + "_" + language
	                for( int i=0; i<resources.length; i++) {
	                    if ( resources[i].getName().equals( jasperName+currLang.getLocale().getLanguage()+".properties")) {
	                        resFile=resources[i];
	                        break;
	                    }
	                }
	                if (resFile==null) {
	                    // try baseName only
	                    for( int i=0; i<resources.length; i++) {
	                        if ( resources[i].getName().equals( jasperName+".properties")) {
	                            resFile=resources[i];
	                            break;
	                        }
	                    }
	                }
                }
                if (resFile!=null) {
                    try {
                        PropertyResourceBundle res = new PropertyResourceBundle( new FileInputStream(resFile));
                        params.put("RESOURCE", res);
                    } catch (IOException e) {
                        ;
                    }
                }

                Connection conn = null;
                try {
                    //JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport, params, DB.getConnectionRW());
                	conn = getConnection();
                    JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport, params, conn);
                    if (reportData.isDirectPrint()) {
                        log.info( "ReportStarter.startProcess print report -" + jasperPrint.getName());
                        JasperPrintManager.printReport( jasperPrint, false);
                        
                        // You can use JasperPrint to create PDF
//                        JasperExportManager.exportReportToPdfFile(jasperPrint, "BasicReport.pdf");
                    } else {
                        log.info( "ReportStarter.startProcess run report -"+jasperPrint.getName());
                        JRViewerProvider viewerLauncher = getReportViewerProvider();
                        viewerLauncher.openViewer(jasperPrint, pi.getTitle()+" - " + reportPath);
                    }
                } catch (JRException e) {
                    log.severe("ReportStarter.startProcess: Can not run report - "+ e.getMessage());
                } finally {
                	if (conn != null)
						try {
							conn.close();
						} catch (SQLException e) {
						}
                }
            }

        }

        reportResult( AD_PInstance_ID, null, trxName);
        return true;
    }

	/**
     * Get .property resource file from process attachment
     * @param jasperName
     * @param currLang
     * @return File
     */
    private File getAttachmentResourceFile(String jasperName, Language currLang) {
    	File resFile = null;
    	MAttachmentEntry[] entries = attachment.getEntries();
    	// try baseName + "_" + language
        for( int i=0; i<entries.length; i++) {
            if ( entries[i].getName().equals( jasperName+currLang.getLocale().getLanguage()+".properties")) {
                resFile = getAttachmentEntryFile(entries[i]);
                break;
            }
        }
        if (resFile==null) {
            // try baseName only
            for( int i=0; i<entries.length; i++) {
                if ( entries[i].getName().equals( jasperName+".properties")) {
                	resFile = getAttachmentEntryFile(entries[i]);
                    break;
                }
            }
        }
		return resFile;
	}

	/**
     * Get .property resource file from resources
     * @param jasperName
     * @param currLang
     * @return File
     */
    private File getResourcesForResourceFile(String jasperName, Language currLang) {
    	File resFile = null;
		try {
			resFile = getFileAsResource(jasperName+currLang.getLocale().getLanguage()+".properties");
		} catch (Exception e) {
			// ignore exception - file couldn't exist
		}
		return resFile;
	}

	/**
     * Get subreports from attachment. Assume all other jasper attachment is subreport.
     * @param reportPath
     * @return File[]
     */
    private File[] getAttachmentSubreports(String reportPath) {
		String name = reportPath.substring("attachment:".length()).trim();
		ArrayList<File> subreports = new ArrayList<File>();
		MAttachmentEntry[] entries = attachment.getEntries();
		for(int i = 0; i < entries.length; i++) {
			if (!entries[i].getName().equals(name) &&
				(entries[i].getName().endsWith(".jrxml") || entries[i].getName().endsWith(".jasper"))) {
				File reportFile = getAttachmentEntryFile(entries[i]);
				if (reportFile != null)
					subreports.add(reportFile);
			}
		}
		File[] files = new File[subreports.size()];
		File[] subreportsTemp = new File[0];
		subreportsTemp = subreports.toArray(subreportsTemp);
		return subreportsTemp;
	}

	/**
	 * Search for additional subreports deployed as resources
	 * @param reportName The original reportname
	 * @param reportPath The full path to the parent report
	 * @param fileExtension The file extension of the parent report
	 * @return An Array of File objects referencing to the downloaded subreports
	 */
	private File[] getResourceSubreports(String reportName, String reportPath, String fileExtension)
	{
		ArrayList<File> subreports = new ArrayList<File>();
		String remoteDir = reportPath.substring(0, reportPath.lastIndexOf("/"));
		
		// Currently check hardcoded for max. 10 subreports
		for(int i=1; i<10; i++)
		{
			// Check if subreport number i exists
			File subreport = null;
			try {
				subreport = getFileAsResource(remoteDir + "/" + reportName + i + fileExtension);
			} catch (Exception e) {
				// just ignore it
			}
			if(subreport == null) // Subreport doesn't exist, abort further approaches
				break;
			
			subreports.add(subreport);
		}
		
		File[] subreportsTemp = new File[0];
		subreportsTemp = subreports.toArray(subreportsTemp);
		return subreportsTemp;
	}

	/**
     * @author alinv
     * @param reportPath
     * @param reportType
     * @return the abstract file corresponding to typed report
     */
	protected File getReportFile(String reportPath, String reportType) {
		
		if (reportType != null)
		{
			int cpos = reportPath.lastIndexOf('.');
			reportPath = reportPath.substring(0, cpos) + "_" + reportType + reportPath.substring(cpos, reportPath.length());
		}
		
		return getReportFile(reportPath);
	}
	
	/**
	 * @author alinv
	 * @param reportPath
	 * @return the abstract file corresponding to report
	 */
	protected File getReportFile(String reportPath) {
		File reportFile = null;
		
		// Reports deployement on web server Thanks to Alin Vaida
		if (reportPath.startsWith("http://") || reportPath.startsWith("https://")) {
			reportFile = httpDownloadedReport(reportPath);
		} else if (reportPath.startsWith("attachment:")) {
			//report file from process attachment
			reportFile = downloadAttachment(reportPath);
		} else if(reportPath.startsWith("/")) {
			reportFile = new File(reportPath);
		} else if (reportPath.startsWith("file:/")) {
			try {
				reportFile = new File(new URI(reportPath));
			} catch (URISyntaxException e) {
				log.warning(e.getLocalizedMessage());
				reportFile = null;
			}
		} else if (reportPath.startsWith("resource:")) {
			try {
				reportFile = getFileAsResource(reportPath);
			} catch (Exception e) {
				log.warning(e.getLocalizedMessage());
				reportFile = null;
			}
		} else {
			reportFile = new File(REPORT_HOME, reportPath);
		}
		
		// Set org.compiere.report.path because it is used in reports which refer to subreports
		System.setProperty("org.compiere.report.path", reportFile.getParentFile().getAbsolutePath());
		return reportFile;
	}

	/**
	 * @param reportPath
	 * @return
	 * @throws Exception 
	 */
	private File getFileAsResource(String reportPath) throws Exception {
		File reportFile;
		String name = reportPath.substring("resource:".length()).trim();
		String localName = name.replace('/', '_');
		log.info("reportPath = " + reportPath);
		log.info("getting resource from = " + getClass().getClassLoader().getResource(name));
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
		String localFile = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + localName;
		log.info("localFile = " + localFile);
		reportFile = new File(localFile);

		OutputStream out = null;
		out = new FileOutputStream(reportFile);
		if (out != null){
			byte buf[]=new byte[1024];
			int len;
			while((len=inputStream.read(buf))>0)
				out.write(buf,0,len);
			out.close();
			inputStream.close();
		}

		return reportFile;
	}

	/**
	 * Download db attachment 
	 * @param reportPath must of syntax attachment:filename
	 * @return File
	 */
	private File downloadAttachment(String reportPath) {
		File reportFile = null;
		String name = reportPath.substring("attachment:".length()).trim();
		MProcess process = new MProcess(Env.getCtx(), processInfo.getAD_Process_ID(), processInfo.getTransactionName()); 
		attachment = process.getAttachment();
		if (attachment != null) {
			MAttachmentEntry[] entries = attachment.getEntries();
			MAttachmentEntry entry = null;
			for (int i = 0; i < entries.length; i++) {
				if (entries[i].getName().equals(name)) {
					entry = entries[i];
					break;
				}
			}
			if (entry != null) {
				reportFile = getAttachmentEntryFile(entry);
			}	
		}
		return reportFile;
	}
	
	/**
	 * Download db attachment to local file
	 * @param entry
	 * @return File
	 */
	private File getAttachmentEntryFile(MAttachmentEntry entry) {
		String localFile = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + entry.getName();
		String downloadedLocalFile = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator")+"TMP" + entry.getName();
		File reportFile = new File(localFile);
		if (reportFile.exists()) {
			String localMD5hash = DigestOfFile.GetLocalMD5Hash(reportFile);
			String entryMD5hash = DigestOfFile.getMD5Hash(entry.getData());
			if (localMD5hash.equals(entryMD5hash))
			{
				log.info(" no need to download: local report is up-to-date");
			}
			else
			{
				log.info(" report on server is different that local one, download and replace");
				File downloadedFile = new File(downloadedLocalFile);
				entry.getFile(downloadedFile);
				reportFile.delete();
				downloadedFile.renameTo(reportFile);
			}
		} else {
			entry.getFile(reportFile);
		}
		return reportFile;
	}

	/**
     * @author rlemeill
     * @param AD_PInstance_ID
     * @param errMsg
     */
    protected void reportResult( int AD_PInstance_ID, String errMsg, String trxName) {
        int result = (errMsg==null)?1:0;
        errMsg = (errMsg==null)?"":errMsg;
        String sql = "UPDATE AD_PInstance SET result="+result+", errormsg='"+errMsg+"' "+
                     "WHERE AD_PInstance_ID="+AD_PInstance_ID;
        Statement pstmt = null;
        try {
            pstmt = DB.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, trxName);
            pstmt.executeUpdate(sql);
            pstmt.close();
        } catch (SQLException e) {
            log.severe(sql+e.getMessage());
        } finally {
            DBUtils.close( pstmt);
        }
    }

    /**
     * @author rlemeill
     * @param reportFile
     * @return
     */
    protected JasperData processReport( File reportFile) {
        log.info( "reportFile.getAbsolutePath() = "+reportFile.getAbsolutePath());
        JasperReport jasperReport = null;

        String jasperName = reportFile.getName();
        int pos = jasperName.indexOf('.');
        if (pos!=-1) jasperName = jasperName.substring(0, pos);
        File reportDir = reportFile.getParentFile();
        
        //test if the compiled report exists
        File jasperFile = new File( reportDir.getAbsolutePath(), jasperName+".jasper");
        if (jasperFile.exists()) { // test time
            if (reportFile.lastModified() == jasperFile.lastModified()) {
            	log.info(" no need to compile use "+jasperFile.getAbsolutePath());
                try {
                    jasperReport = (JasperReport)JRLoader.loadObject(jasperFile.getAbsolutePath());
                } catch (JRException e) {
                    jasperReport = null;
                    log.severe("Can not load report - "+ e.getMessage());
                }
            } else {
                jasperReport = compileReport( reportFile, jasperFile);
            }
        } else { // create new jasper file
            jasperReport = compileReport( reportFile, jasperFile);
        }

        return new JasperData( jasperReport, reportDir, jasperName, jasperFile);
    }


    protected void addProcessParameters( int AD_PInstance_ID, Map<String, Object> params, String trxName) {
        log.info("");
        String sql = "SELECT ParameterName, "+
                        "P_String, "+
                        "P_String_To, "+
                        "P_Number, "+
                        "P_Number_To, "+
                        "P_Date, "+
                        "P_Date_To "+
                    "FROM AD_PInstance_Para "+
                    "WHERE AD_PInstance_ID=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, trxName);
            pstmt.setInt(1, AD_PInstance_ID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String pStr = rs.getString(2);
                String pStrTo = rs.getString(3);
                //Double pNum = new Double( rs.getDouble(4));
                //Double pNumTo = new Double( rs.getDouble(5));
                BigDecimal pNum = rs.getBigDecimal(4);
                BigDecimal pNumTo = rs.getBigDecimal(5);
                
                Timestamp pDate = rs.getTimestamp(6);
                Timestamp pDateTo = rs.getTimestamp(7);
                if (pStr != null) {
                    if (pStrTo!=null) {
                        params.put( name+"1", pStr);
                        params.put( name+"2", pStrTo);
                    } else {
                        params.put( name, pStr);
                    }
                } else if (pDate != null) {
                    if (pDateTo!=null) {
                        params.put( name+"1", pDate);
                        params.put( name+"2", pDateTo);
                    } else {
                        params.put( name, pDate);
                    }
                } else if (pNum != null) {
                    if (rs.getBigDecimal(5)!=null) {
                        params.put( name+"1", pNum);
                        params.put( name+"2", pNumTo);
                    } else {
                        params.put( name, pNum);
                    }
                }
            }
        } catch (SQLException e) {
            log.severe("Execption; sql = "+sql+"; e.getMessage() = " +e.getMessage());
        } finally {
            DBUtils.close( rs);
            DBUtils.close( pstmt);
        }
    }

    private void addProcessInfoParameters(Map<String, Object> params, ProcessInfoParameter[] para) {
    	if (para != null) {
			for (int i = 0; i < para.length; i++) {
				if (para[i].getParameter_To() == null) {
					params.put(para[i].getParameterName(), para[i].getParameter());
				} else {
	                params.put( para[i].getParameterName()+"1", para[i].getParameter());
	                params.put( para[i].getParameterName()+"2", para[i].getParameter_To());
				}
			}
    	}
	}

    /**
     * @author rlemeill
     * Correct the class path if loaded from java web start
     */
    private void JWScorrectClassPath()
    {
		URL jasperreportsAbsoluteURL = Thread.currentThread().getContextClassLoader().getResource("net/sf/jasperreports/engine");
		String jasperreportsAbsolutePath = "";
		
		if(jasperreportsAbsoluteURL.toString().startsWith("jar:http:") || jasperreportsAbsoluteURL.toString().startsWith("jar:https:"))
		{
			// Jasper classes are deployed to a webserver (Java Webstart)
			jasperreportsAbsolutePath = jasperreportsAbsoluteURL.toString().split("!")[0].split("jar:")[1];
			
			// Download the required jasper libraries if they are not already existing
			File reqLib = new File(System.getProperty("java.io.tmpdir"), "CompiereJasperReqs.jar");
			if(!reqLib.exists() && !(reqLib.length() > 0))
			{
				try{
					URL reqLibURL = new URL(jasperreportsAbsolutePath);
					InputStream in = reqLibURL.openStream();

					FileOutputStream fout = new FileOutputStream(reqLib);

					byte buf[] = new byte[1024];
					int s = 0;
					long tl = 0;

					while((s = in.read(buf, 0, 1024)) > 0)
						fout.write(buf, 0, s);

					in.close();
					fout.flush();
					fout.close();
				} catch (FileNotFoundException e) {
					log.warning("Required library not found "+ e.getMessage());
					reqLib.delete();
					reqLib = null;
				} catch (IOException e) {
					log.severe("I/O error downloading required library from server "+ e.getMessage());
					reqLib.delete();
					reqLib = null;
				}
			}

			jasperreportsAbsolutePath = reqLib.getAbsolutePath();
		}
		else
		{
			// Jasper classes are locally available (Local client)
			jasperreportsAbsolutePath = jasperreportsAbsoluteURL.toString().split("!")[0].split("file:")[1];
		}
		
		if(jasperreportsAbsolutePath != null && !jasperreportsAbsolutePath.trim().equals(""))
		{
			// Check whether the current CLASSPATH already contains our
			// jasper libraries and dependencies or not.
			if(System.getProperty("java.class.path").indexOf(jasperreportsAbsolutePath) < 0)
			{
			System.setProperty("java.class.path",
					System.getProperty("java.class.path") +
					System.getProperty("path.separator") +
					jasperreportsAbsolutePath);
			log.info("Classpath has been corrected to " + System.getProperty("java.class.path"));
			}
		}
    }
	
    /**
     * @author rlemeill
     * @param reportFile
     * @param jasperFile
     * @return compiled JasperReport
     */
    private JasperReport compileReport( File reportFile, File jasperFile) {
    	JWScorrectClassPath();
        JasperReport compiledJasperReport = null;
        try {
          	JasperCompileManager.compileReportToFile ( reportFile.getAbsolutePath(), jasperFile.getAbsolutePath() );
            jasperFile.setLastModified( reportFile.lastModified()); //Synchronize Dates
            compiledJasperReport =  (JasperReport)JRLoader.loadObject(jasperFile);
        } catch (JRException e) {
            log.severe("JRException; e.getMessage()= "+ e.getMessage());
        }
        return compiledJasperReport;
    }

    /**
     * @author rlemeill
     * @param ProcessInfo
     * @return ReportData
     */
    public ReportData getReportData( ProcessInfo pi, String trxName) {
    	log.info("");
        String sql = "SELECT pr.JasperReport, pr.IsDirectPrint "
        		   + "FROM AD_Process pr, AD_PInstance pi "
                   + "WHERE pr.AD_Process_ID = pi.AD_Process_ID "
                   + " AND pi.AD_PInstance_ID=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, trxName);
            pstmt.setInt(1, pi.getAD_PInstance_ID());
            rs = pstmt.executeQuery();
            String path = null;
            boolean	directPrint = false;
            boolean isPrintPreview = pi.isPrintPreview();
            if (rs.next()) {
                path = rs.getString(1);
				
				if ("Y".equalsIgnoreCase(rs.getString(2)) && !Ini.isPropertyBool(Ini.P_PRINTPREVIEW)
						&& !isPrintPreview )
					directPrint = true;
            } else {
                log.severe("data not found; sql = "+sql);
				return null;
            }
            
            return new ReportData( path, directPrint);
        } catch (SQLException e) {
            log.severe("sql = "+sql+"; e.getMessage() = "+ e.getMessage());
            return null;
        } finally {
            DBUtils.close( rs);
            DBUtils.close( pstmt);
        }
    }
    
    /**
     * Set jasper report viewer provider.
     * @param provider
     */
    public static void setReportViewerProvider(JRViewerProvider provider) {
    	if (provider == null)
    		throw new IllegalArgumentException("Cannot set report viewer provider to null");
    	viewerProvider = provider;
    }
    
    /**
     * Get the current jasper report viewer provider
     * @return JRViewerProvider
     */
    public static JRViewerProvider getReportViewerProvider() {
    	return viewerProvider;
    }

    class ReportData {
        private String reportFilePath;
        private boolean directPrint;

        public ReportData(String reportFilePath, boolean directPrint) {
            this.reportFilePath = reportFilePath;
            this.directPrint = directPrint;
        }

        public String getReportFilePath() {
            return reportFilePath;
        }

        public boolean isDirectPrint() {
            return directPrint;
        }
    }

    class JasperData {
        private JasperReport jasperReport;
        private File reportDir;
        private String jasperName;
        private File jasperFile;

        public JasperData(JasperReport jasperReport, File reportDir, String jasperName, File jasperFile) {
            this.jasperReport = jasperReport;
            this.reportDir = reportDir;
            this.jasperName = jasperName;
            this.jasperFile = jasperFile;
        }

        public JasperReport getJasperReport() {
            return jasperReport;
        }

        public File getReportDir() {
            return reportDir;
        }

        public String getJasperName() {
            return jasperName;
        }

        public File getJasperFile() {
            return jasperFile;
        }
    }

    class FileFilter implements FilenameFilter {
        private String reportStart;
        private File directory;
        private String extension;

        public FileFilter(String reportStart, File directory, String extension) {
            this.reportStart = reportStart;
            this.directory = directory;
            this.extension = extension;
        }

        public boolean accept(File file, String name) {
            if (file.equals( directory)) {
                if (name.startsWith( reportStart)) {
                    int pos = name.lastIndexOf( extension);
                    if ( (pos!=-1) && (pos==(name.length()-extension.length()))) return true;
                }
            }
            return false;
        }
    }

}