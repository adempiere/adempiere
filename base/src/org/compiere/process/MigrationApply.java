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

import org.compiere.model.MMigration;
import org.compiere.model.MMigrationStep;
import org.compiere.model.MTable;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class MigrationApply extends SvrProcess {

	private MMigration migration;
	private boolean failOnError = false;

	@Override
	protected String doIt() throws Exception {

		if ( migration == null || migration.is_new() )
		{
			addLog("No migration");
			return "@Error@";
		}
		
		boolean apply = true;
		if ( MMigration.APPLY_Rollback.equals(migration.getApply()))
			apply = false;
		
		migration.setFailOnError(failOnError);
		
		if ( apply )
		{
			migration.apply();
			
			if ( migration.getStatusCode().equals(MMigration.STATUSCODE_Applied) )
			{
				addLog("Migration successful");
			}
			else if ( migration.getStatusCode().equals(MMigration.STATUSCODE_PartiallyApplied) )
			{
				addLog("Migration partially applied. Please review migration steps for errors.");
			}
			else if (  migration.getStatusCode().equals(MMigration.STATUSCODE_Failed) )
			{
				addLog("Migration failed. Please review migration steps for errors.");
			}
			
		}
		else 
		{
			migration.rollback();

			if ( migration.getStatusCode().equals(MMigration.STATUSCODE_Unapplied) )
			{
				addLog("Rollback successful.");
			}
			else
			{
				addLog("Rollback failed. Please review migration steps for errors.");
			}
			
		}

		return "@OK@";
	}

	@Override
	protected void prepare() {
		
		migration = new MMigration(getCtx(), getRecord_ID(), get_TrxName());
		
		ProcessInfoParameter[] params = getParameter();
		for ( ProcessInfoParameter p : params)
		{
			String para = p.getParameterName();
			if ( para.equals("FailOnError") )
				failOnError  = "Y".equals((String)p.getParameter());
		}

	}

}
