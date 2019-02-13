/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2011 Adaxa Pty Ltd. All Rights Reserved.                *
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

import org.compiere.model.MMigrationStep;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

public class MigrationStepApply extends SvrProcess {

	private MMigrationStep migrationStep;

	@Override
	protected void prepare() {
		
	}

	/**
	 * 
	 * Process to apply a single migration step
	 * 
	 * @author Paul Bowden, Adaxa Pty Ltd
	 *
	 */
	@Override
	protected String doIt() throws Exception {
		
		// Test if the user is logging dictionary or other changes.  If so
		// advise that the flag is set and exit as it is not a good idea to 
		// log scripts and apply them at the same time.
		if ( Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT) )
		{
			addLog( Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSetMessage"));
			return "@Error@" + Msg.getMsg(getCtx(), "LogMigrationScripFlagtIsSet");
		}

		// Find the migrationSetp. Use a null transaction to create a read-only
		// query.
		migrationStep = new MMigrationStep(getCtx(), getRecord_ID(), null);
		
		if ( migrationStep == null || migrationStep.is_new() )
		{
			// TODO Translate
			addLog(Msg.getMsg(getCtx(), "NoMigrationStepFound"));
			return "@Error@" + Msg.getMsg(getCtx(), "NoMigrationStepFound");
		}
		
		// This call will either rollback or apply based on the 
		// status of the step.
		migrationStep.apply();
		return "";			
	}
}
