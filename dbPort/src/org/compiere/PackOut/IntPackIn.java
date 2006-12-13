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
 *
 * Copyright (C) 2005 Robert KLEIN.  robeklein@hotmail.com  
 * 
 *****************************************************************************/

package org.compiere.PackOut;

import java.io.File;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Util;
import java.util.logging.*;

/**
 *  IntPackIn Tool. 
 *  @author: Robert KLEIN. robeklein@hotmail.com
 */
public class IntPackIn extends SvrProcess
{

    /**	Logger						*/
    private CLogger log = CLogger.getCLogger("PackIn");
    public static String m_UpdateMode = "false";
    public static String m_Database = "Oracle";
	public static String m_Package_Dir = null;
    public int p_IntPackIn_ID = 0;
    
    protected void prepare()
	{		
    	p_IntPackIn_ID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
		}		
	}	//	prepare

    
    /**
     * 	Uses PackInHandler to update AD.
     *	@param fileName xml file to read
     * 	@return status message
     */
    public String importXML (String fileName) {
	log.info("importXML:" + fileName);
	File in = new File (fileName);
	if (!in.exists()) {
	    String msg = "File does not exist: " + fileName;
	    log.info("importXML:" + msg);
	    return msg;
	}
	try {
		log.info("starting");
		System.setProperty("javax.xml.parsers.SAXParserFactory",
		"org.apache.xerces.jaxp.SAXParserFactoryImpl");
		IntPackInHandler handler = new IntPackInHandler();
		handler.set_TrxName(get_TrxName());
		SAXParserFactory factory = SAXParserFactory.newInstance();		
	    SAXParser parser = factory.newSAXParser();
	    String msg = "Start Parser";	    
	    log.info (msg);	    
	    parser.parse(in, handler);
	    msg = "End Parser";
	    log.info (msg);
	    return "OK.";
	}
	catch (Exception e) {
	    log.log(Level.SEVERE,"importXML:", e);
	    return e.toString();
	}

    }   
    
    /**
     * 	Doit
     *	@return ""
     *
     */
    
    protected String doIt() 
	{
    
		X_AD_Package_Imp_Proc IntPackIn = new X_AD_Package_Imp_Proc(
				getCtx(),p_IntPackIn_ID, null);		

       // Create Target directory if required
       String fileSeperator=null;
       File tempfile = new File("");
       fileSeperator = tempfile.separator;
       File targetDir = new
       File(IntPackIn.getAD_Package_Dir()+fileSeperator+"packages");

       if (!targetDir.exists()){
           boolean success = (new File(IntPackIn.getAD_Package_Dir()+fileSeperator+"packages")).mkdirs();
           if (!success) {
               log.info("Target directory creation failed");
           }
       }	   

       //Unzip package
       File zipFilepath = new File(IntPackIn.getAD_Package_Source());
	   String PackageName = CreateZipFile.getParentDir(zipFilepath);
       CreateZipFile.unpackFile(zipFilepath,targetDir);


    String dict_file = IntPackIn.getAD_Package_Dir()+fileSeperator+"packages"+fileSeperator+PackageName
		+fileSeperator+"dict"+fileSeperator+"PackOut.xml";
	log.info("dict file->"+dict_file);
	IntPackIn impXML = new IntPackIn();
	
	if(IntPackIn.isAD_Override_Dict()== true)
		impXML.m_UpdateMode = "true";
	else
		impXML.m_UpdateMode = "false";
	
	impXML.m_Package_Dir=IntPackIn.getAD_Package_Dir()+fileSeperator+"packages"+fileSeperator+PackageName
		+fileSeperator;
	if (DB.isOracle())
		impXML.m_Database = "Oracle";
/*	else if (DB.isSybase())	
		impXML.m_Database = "Sybase";
*/
	//call XML Handler	
	impXML.importXML(dict_file);
	
	//Generate Model Classes
	// globalqss - don't call Generate Model must be done manual
	// String args[] = {IntPackIn.getAD_Package_Dir()+"/dbPort/src/org/compiere/model/", "org.compiere.model","'U'"}; 
	// org.compiere.util.GenerateModel.main(args) ;
	
	
	return "";
	}	//	doIt
        
}   // IntPackIn

 

