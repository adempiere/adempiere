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
import org.compiere.util.Ini;
import org.compiere.util.Msg;

/**
 * Apply or Rollback a migration.  The application or rollback is determined by
 * the status of the migration.  Only unapplied migrations will be applied.
 * All other migration status codes will result in a rollback.
 *
 */
public class MigrationApply extends MigrationApplyAbstract {

	@Override
	protected String doIt() throws Exception{

		if ( Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT) )
		{
			addLog( Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSetMessage"));
			return "@Error@";
		}
		
			// Use a null transaction to generate a read only query
			MMigration migration = new MMigration(getCtx(), getRecord_ID() , null);		// Doesn't lock table 
			if (migration.is_new())
			{
				addLog( Msg.getMsg(getCtx(), "NoMigrationMessage"));
				//return;
				return "@Error@";
			}
			
			migration.setIsForce(isForce());
			
			try {
				addLog(migration.apply());
			} catch (AdempiereException e) {
				addLog(e.getMessage());
				if (!isForce())    // abort on first error
					throw new AdempiereException(e.getMessage(), e);
			}
		return "@OK@";
	}
}
