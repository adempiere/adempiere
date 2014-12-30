package org.compiere.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.process.MigrationLoader;
import org.apache.commons.io.FileUtils;
import org.compiere.model.MMigration;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MigrationFromXML extends SvrProcess {

	private String fileName = null;
	private boolean apply = false;
	private MigrationLoader loader;

	@Override
	protected String doIt() throws Exception {
		
		if ( Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT) )
		{
			addLog( Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSetMessage"));
			return "@Error@" + Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSet");
		}
		
		// file can be a file or directory
		File file = new File(fileName);		
		loader.loadXML(file, apply);
		
		return "Import complete";

	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] paras = getParameter();
		for ( ProcessInfoParameter para : paras )
		{
			if ( para.getParameterName().equals("FileName"))
				fileName = (String) para.getParameter();
			else if ( para.getParameterName().equals("Apply"))
				apply  = para.getParameterAsBoolean();
		}
		
		// Create the migration loader
		loader = new MigrationLoader();
	}

}