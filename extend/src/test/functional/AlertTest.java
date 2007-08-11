/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Trifon Trifonov.                                      *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.d3-soft.com)                                  *
***********************************************************************/
package test.functional;

import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.I_AD_Alert;
import org.compiere.model.I_AD_AlertProcessor;
import org.compiere.model.MAlert;
import org.compiere.model.X_AD_Alert;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Trx;


/**
 *	Example Test which shows usage of new methods.
 *	
 *  @author Trifon Trifonov
 *  @version $Id$
 */
public class AlertTest implements Runnable
{

	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (AlertTest.class);
	
	/**
	 * 	AlertTest
	 */
	public AlertTest () 
	{
		super ();
	}
	
	/**
	 * 	Run
	 */
	public void run()
	{
		Trx trx = Trx.get(Trx.createTrxName("Test"), true);
		trx.start();
		log.info("trx = " + trx.toString());
		
		// Old way:
		//MAlert alert = new MAlert(Env.getCtx(), 1000000, trx.getTrxName());
		
		// New way:
/*		I_AD_Alert alert = new MAlert(Env.getCtx(), 1000000, trx.getTrxName());
		
		boolean resultSave = ((X_AD_Alert) alert).save();
		log.info(alert.toString());
				
		//
		System.out.println("Trifon - alert.getAD_AlertProcessor_ID = " + alert.getAD_AlertProcessor_ID());
		
		I_AD_AlertProcessor alertProcessor = alert.getI_AD_AlertProcessor();
		System.out.println("Trifon - I_AD_AlertProcessor.getAD_AlertProcessor_ID = " + alertProcessor.getAD_AlertProcessor_ID());
*/
		trx.commit();
		trx.close();
	}
	
	/**
	 * 	Test
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		Adempiere.startup(true);
		CLogMgt.setLoggerLevel(Level.INFO, null);
		CLogMgt.setLevel(Level.INFO);
		//
		Ini.setProperty(Ini.P_UID,			"SuperUser");
		Ini.setProperty(Ini.P_PWD,			"System");
		Ini.setProperty(Ini.P_ROLE,			"GardenWorld Admin");
		Ini.setProperty(Ini.P_CLIENT, 		"GardenWorld");
		Ini.setProperty(Ini.P_ORG,			"HQ");
		Ini.setProperty(Ini.P_WAREHOUSE,	"HQ Warehouse");
		Ini.setProperty(Ini.P_LANGUAGE,		"English");
		Login login = new Login(Env.getCtx());
		if (!login.batchLogin(null))
			System.exit(1);
		//
		CLogMgt.setLoggerLevel(Level.WARNING, null);
		CLogMgt.setLevel(Level.WARNING);

		long time = System.currentTimeMillis();
		AlertTest test = new AlertTest();
		test.run();

		//	Wait
		time = System.currentTimeMillis() - time;

		System.out.println("Time (ms)=" + time);
	}
	
}
