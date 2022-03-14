/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.adempiere.process;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.I_AD_Client;
import org.compiere.model.MClient;
import org.compiere.model.Query;
import org.compiere.process.MigrationFromXML;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SynchronizeTerminology;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Util;
import org.eevolution.service.dsl.ProcessBuilder;

public class MigrationLoader {
	
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (MigrationLoader.class);
	
	public static void main(String[] args) {
		
		boolean isClean = false;
		boolean isForce = false;
		// If called with the argument "clean", the loader will apply any
		// outstanding migrations, mark ALL applied dictionary migrations as processed
		// and delete all the steps and data to save space.
		//	Get Parameters
		List<String> arguments = Arrays.asList(args);
		isClean = arguments.stream().filter(arg -> !Util.isEmpty(arg) && arg.equals("clean")).findFirst().isPresent();
		isForce = arguments.stream().filter(arg -> !Util.isEmpty(arg) && arg.equals("force")).findFirst().isPresent();
		//	Get path
		Optional<String> optionalPath = arguments.stream().filter(arg -> !Util.isEmpty(arg) && !arg.equals("force") && !arg.equals("clean")).findFirst();
		String fileName = null;
		if(optionalPath.isPresent()
				&& new File(optionalPath.get()).exists()) {
			fileName = optionalPath.get();
		}
		Adempiere.startupEnvironment(true);
		CLogMgt.setLevel(Level.INFO);
		
		if (! DB.isConnected()) {
			log.warning("No DB Connection");
			System.exit(1);
		}
		
		// Turn off the Log Migration Script preference.
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, false);

		
		// Parameter values - these need to be final or effectively final
		// Load migrations from the default location
		if(Util.isEmpty(fileName)) {
			fileName = Adempiere.getAdempiereHome() + File.separator + "migration";
		}
		boolean apply = true;
		boolean failOnError = true;

		Properties context = Env.getCtx();

		try {
			ProcessInfo processInfo = ProcessBuilder.create(context)
			.process(org.compiere.process.MigrationFromXML.class)
			.withTitle("Import Migration from XML")
			.withParameter("FailOnError",failOnError)
			.withParameter(MigrationFromXML.FILEPATHORNAME, fileName) // Old parameter name
			.withParameter(MigrationFromXML.FILENAME, fileName)  // New parameter name
			.withParameter(MigrationFromXML.APPLY, apply)
			.withParameter(MigrationFromXML.ISFORCE, isForce)
			.withParameter("Clean", isClean)
			.execute();

			log.log(Level.INFO, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());
			if (failOnError && processInfo.isError())
				throw new AdempiereException(processInfo.getSummary());

			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.SequenceCheck.class)
					.withTitle("Sequence Check")
					.execute();
			log.log(Level.INFO, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.SynchronizeTerminology.class)
					.withTitle("Synchronize Terminology")
					.withParameter(SynchronizeTerminology.ISCREATEELEMENT,false)
					.withParameter(SynchronizeTerminology.ISDELETINGUNUSEDELEMENT, false)
					.execute();
			log.log(Level.INFO, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());
			
			//	Role Access Update
			new Query(context, I_AD_Client.Table_Name, null, null)
			.setOrderBy(I_AD_Client.COLUMNNAME_AD_Client_ID)
			.<MClient>list()
			.forEach(client-> {
				ProcessInfo processInfoRoleAccessUpdate = ProcessBuilder.create(context)
						.process(org.compiere.process.RoleAccessUpdate.class)
						.withTitle("Role Access Update")
						.withParameter("AD_Client_ID", client.getAD_Client_ID())
						.executeUsingSystemRole();
				log.log(Level.INFO, "Process=" + processInfoRoleAccessUpdate.getTitle() + " Client=(" + client.getValue() + " - " + client.getName() + ") Error="+processInfoRoleAccessUpdate.isError() + " Summary=" + processInfoRoleAccessUpdate.getSummary());
			});
			//	
			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.GardenWorldCleanup.class)
					.withTitle("Updating Garden World")
					.executeUsingSystemRole();
			log.log(Level.INFO, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());
		} catch (AdempiereException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
