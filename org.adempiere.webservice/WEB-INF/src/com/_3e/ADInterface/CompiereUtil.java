package com._3e.ADInterface;


import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MClient;
import org.compiere.model.MSystem;
import org.compiere.util.CLogger;


public class CompiereUtil {

	private static CLogger			log = CLogger.getCLogger(CompiereUtil.class);	
	private static boolean          s_initOK    = false;

	
	public static boolean initWeb()
	{
		if (s_initOK)
		{
			//log.info(context.getServletContextName());
			return true;
		}
		
		// TODO: 
		//  Load Environment Variables (serverApps/src/web/WEB-INF/web.xml)


		try
		{
			//CLogMgt.setLevel(Level.OFF);			
			
			/* ADEMPIERE/COMPIERE */ 
			//s_initOK = Compiere.startup(false);
			s_initOK = Adempiere.startup(false);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "startup", ex); 
		}
		if (!s_initOK)
			return false;

		//	Logging now initiated
		//log.info(info.toString());
		
		//
		Properties ctx = new Properties();
		MClient client = MClient.get(ctx, 0);
		MSystem system = MSystem.get(ctx);
		
		// Env.setContext( ctx, "#AD_Language", "pl_PL" );
		//client.sendEMail(client.getRequestEMail(), 
		//	"Server started: " + system.getName() 
		//	,"ServerInfo: " + context.getServerInfo()
		//);

		return s_initOK;
	}
}
