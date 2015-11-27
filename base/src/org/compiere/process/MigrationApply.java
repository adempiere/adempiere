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
 *****************************************************************************/
package org.compiere.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MMigration;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

import java.util.Properties;


public class MigrationApply extends SvrProcess {

	//private MMigration migration;
	private boolean failOnError = false;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] params = getParameter();
		for ( ProcessInfoParameter p : params)
		{
			String para = p.getParameterName();
			if ( para.equals("FailOnError") )
				failOnError  = "Y".equals((String)p.getParameter());
		}
	}

	@Override
	protected String doIt() throws Exception{
			Env.setContext(Env.getCtx() , "LogMigrationScriptBatch", "Y");
			Trx.run(new TrxRunnable() {
			private int migrationId;
			private Properties ctx;
			public TrxRunnable setParameters(Properties ctx , int migrationId)
			{
				this.migrationId= migrationId;
				this.ctx = ctx;
				return this;
			}

			public void run(String trxName) {
				MMigration migration = new MMigration(ctx ,migrationId , trxName);
				try {
					if ( migration == null || migration.is_new() )
					{
						addLog( Msg.getMsg(getCtx(), "NoMigrationMessage"));
						return;
						//return "@Error@" + Msg.getMsg(getCtx(), "NoMigration");
					}

					if ( Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT) )
					{
						addLog( Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSetMessage"));
						return;
						//return "@Error@" + Msg.getMsg(getCtx(), "LogMigrationScripFlagtIsSet");
					}

					boolean apply = true;
					if ( MMigration.APPLY_Rollback.equals(migration.getApply()))
						apply = false;

					migration.setFailOnError(failOnError);

					if ( apply )
					{
						migration.apply();

						if ( migration.getStatusCode().equals(MMigration.STATUSCODE_Applied))
							addLog("Migration successful");
						else if ( migration.getStatusCode().equals(MMigration.STATUSCODE_PartiallyApplied))
							addLog("Migration partially applied. Please review migration steps for errors.");
						else if (  migration.getStatusCode().equals(MMigration.STATUSCODE_Failed) )
							addLog("Migration failed. Please review migration steps for errors.");
					}
					else
					{
						migration.rollback();
						if ( migration.getStatusCode().equals(MMigration.STATUSCODE_Unapplied) )
							addLog("Rollback successful.");
						else
							addLog("Rollback failed. Please review migration steps for errors.");
					}
				} catch (AdempiereException e) {
					addLog(e.getMessage());
					addLog("Execute Rollback");
					migration.rollback();
					if ( migration.getStatusCode().equals(MMigration.STATUSCODE_Unapplied) )
						addLog("Rollback successful.");
					else
						addLog("Rollback failed. Please review migration steps for errors.");
				}
			}
		}.setParameters(getCtx() , getRecord_ID()));
		MMigration migration = new MMigration(getCtx(),getRecord_ID() , get_TrxName());
		migration.updateStatus();
		Env.setContext(Env.getCtx() , "LogMigrationScriptBatch", "N");
		return "@OK@";
	}
}
