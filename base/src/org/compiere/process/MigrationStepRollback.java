/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution * Copyright (C)
 * 1999-2009 Adempiere, Inc. All Rights Reserved. * This program is free
 * software; you can redistribute it and/or modify it * under the terms version
 * 2 of the GNU General Public License as published * by the Free Software
 * Foundation. This program is distributed in the hope * that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied * warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. * See the GNU General
 * Public License for more details. * You should have received a copy of the GNU
 * General Public License along * with this program; if not, write to the Free
 * Software Foundation, Inc., * 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA. *
 * 
 ******************************************************************************/

package org.compiere.process;

import org.compiere.model.MMigrationStep;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

/**
 * 
 * Process to rollback a single migration step
 * 
 * @author Paul Bowden, Adaxa Pty Ltd
 *
 */
@Deprecated // Not used at all.  See MigrationStepApply which handles both apply and rollback functions. 
public class MigrationStepRollback extends SvrProcess {

	private MMigrationStep migrationStep;

	@Override
	protected void prepare() {
		migrationStep = new MMigrationStep(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {

		if ( Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT) )
		{
			addLog( Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSetMessage"));
			return "@Error@" + Msg.getMsg(getCtx(), "LogMigrationScripFlagtIsSet");
		}

		String retval = migrationStep.toString();
		if ( migrationStep == null || migrationStep.is_new() )
			return "No migration step";
		else
			retval += migrationStep.rollback();

		return retval;
	}
}