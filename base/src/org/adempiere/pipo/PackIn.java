/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert KLEIN.  robeklein@hotmail.com  
 * 
 *****************************************************************************/

package org.adempiere.pipo;

import java.io.File;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.db.CConnection;
import org.compiere.model.*;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;

import java.util.Properties;
import java.util.logging.*;

/**
 * IntPackIn Tool.
 * 
 * @author: Robert KLEIN. robeklein@hotmail.com
 */
public class PackIn extends SvrProcess {

	/** Logger */
	private CLogger log = CLogger.getCLogger("PackIn");
	public static String m_UpdateMode = "false";
	public static String m_Database = "Oracle";
	public static String m_Package_Dir = null;
	public int p_PackIn_ID = 0;

	protected void prepare() {
		p_PackIn_ID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
		}
	} // prepare

	/**
	 * Uses PackInHandler to update AD.
	 * 
	 * @param fileName
	 *            xml file to read
	 * @return status message
	 */
	public String importXML(String fileName, Properties ctx, String trxName) {
		log.info("importXML:" + fileName);
		File in = new File(fileName);
		if (!in.exists()) {
			String msg = "File does not exist: " + fileName;
			log.info("importXML:" + msg);
			return msg;
		}
		try {
			log.info("starting");
			System.setProperty("javax.xml.parsers.SAXParserFactory",
					"org.apache.xerces.jaxp.SAXParserFactoryImpl");
			PackInHandler handler = new PackInHandler();
			handler.set_TrxName(trxName);
			handler.setCtx(ctx);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			String msg = "Start Parser";
			log.info(msg);
			parser.parse(in, handler);
			msg = "End Parser";
			log.info(msg);
			return "OK.";
		} catch (Exception e) {
			log.log(Level.SEVERE, "importXML:", e);
			return e.toString();
		}
	}

	/**
	 * Doit
	 * 
	 * @return ""
	 * 
	 */
	protected String doIt() {

		X_AD_Package_Imp_Proc adPackageImp = new X_AD_Package_Imp_Proc(getCtx(),
				p_PackIn_ID, null);

		// Create Target directory if required
		String fileSeparator = null;
		File tempfile = new File("");
		fileSeparator = tempfile.separator;
		File targetDir = new File(adPackageImp.getAD_Package_Dir() + fileSeparator
				+ "packages");

		if (!targetDir.exists()) {
			boolean success = (new File(adPackageImp.getAD_Package_Dir()
					+ fileSeparator + "packages")).mkdirs();
			if (!success) {
				log.info("Target directory creation failed");
			}
		}

		// Unzip package
		File zipFilepath = new File(adPackageImp.getAD_Package_Source());
		log.info("zipFilepath->" + zipFilepath);
		String PackageName = CreateZipFile.getParentDir(zipFilepath);
		CreateZipFile.unpackFile(zipFilepath, targetDir);

		String dict_file = adPackageImp.getAD_Package_Dir() + fileSeparator
				+ "packages" + fileSeparator + PackageName + fileSeparator
				+ "dict" + fileSeparator + "PackOut.xml";
		log.info("dict file->" + dict_file);
		PackIn impXML = new PackIn();

		if (adPackageImp.isAD_Override_Dict() == true)
			impXML.m_UpdateMode = "true";
		else
			impXML.m_UpdateMode = "false";

		impXML.m_Package_Dir = adPackageImp.getAD_Package_Dir() + fileSeparator
				+ "packages" + fileSeparator + PackageName + fileSeparator;
		if (DB.isOracle())
			impXML.m_Database = "Oracle";
		else if (DB.isPostgreSQL())
			impXML.m_Database = "PostgreSQL";

		// call XML Handler
		impXML.importXML(dict_file, getCtx(), get_TrxName());

		// Generate Model Classes
		// globalqss - don't call Generate Model must be done manual
		// String args[] =
		// {IntPackIn.getAD_Package_Dir()+"/dbPort/src/org/compiere/model/",
		// "org.compiere.model","'U'"};
		// org.compiere.util.GenerateModel.main(args) ;

		return "Finish Process";
	} // doIt

	/***************************************************************************
	 * 
	 * @param args
	 *            XMLfile host port db username password
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out
					.println("Please give the file name to read as first parameter.");
			System.exit(1);
		}

		String file = args[0];
		org.compiere.Adempiere.startup(true);

		// globalqss - added argument 8 to generate system sequences
		if (args.length > 8 && args[8].equals(Ini.P_ADEMPIERESYS)) {
			System.out.println("**** WARNING: Working with system sequences "
					+ Ini.P_ADEMPIERESYS + " ****");
			Ini.setProperty(Ini.P_ADEMPIERESYS, true);
		}

		PackIn impXML = new PackIn();
		// org.compiere.Compiere.startupEnvironment(true);
		// Force connection if there are enough parameters. Else we work with
		// Compiere.properties
		if (args.length >= 6) {
			// CConnection cc = CConnection.get("PostgreSQL", args[1],
			// Integer.valueOf(args[2]).intValue(), args[5], args[3], args[4]);
			CConnection cc = CConnection.get();
			// System.out.println("DB Connect String1:"+cc.getDbName());
			impXML.m_Database = cc.getType();
			DB.setDBTarget(cc);
		}

		// Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO,
		// Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL

		Level logLevel = Level.FINER;

		switch (Integer.parseInt(args[6])) {
		case 1:
			logLevel = Level.OFF;
			break;
		case 2:
			logLevel = Level.SEVERE;
			break;
		case 3:
			logLevel = Level.WARNING;
			break;
		case 4:
			logLevel = Level.INFO;
			break;
		case 5:
			logLevel = Level.CONFIG;
			break;
		case 6:
			logLevel = Level.FINE;
			break;
		case 7:
			logLevel = Level.FINER;
			break;
		case 8:
			logLevel = Level.FINEST;
			break;
		case 9:
			logLevel = Level.ALL;
			break;
		}
		CLogMgt.setLevel(logLevel);
		CLogMgt.setLoggerLevel(logLevel, null);

		// impXML.setUpdateMode(args[7]);
		impXML.m_UpdateMode = args[7];
		impXML.importXML(file, Env.getCtx(), null);

		System.exit(0);
	} // main
} // PackIn
