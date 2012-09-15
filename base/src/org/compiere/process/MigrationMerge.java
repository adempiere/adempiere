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

public class MigrationMerge extends SvrProcess {

	private MMigration migrationFrom;
	private MMigration migrationTo;

	/**
	 * 
	 * Process to merge two migrations together
	 * 
	 * @author Paul Bowden, Adaxa Pty Ltd
	 *
	 */
	@Override
	protected String doIt() throws Exception {

		if ( migrationFrom == null || migrationFrom.is_new() 
				|| migrationTo == null || migrationTo.is_new() 
				|| migrationFrom.getAD_Migration_ID() == migrationTo.getAD_Migration_ID() )
		{
			addLog("Two different existing migrations required for merge");
			return "@Error@";
		}
		
		migrationTo.mergeMigration(migrationFrom);
		
		return "@OK@";
	}

	@Override
	protected void prepare() {
		
		int fromId = 0, toId = 0;
		
		ProcessInfoParameter[] params = getParameter();
		for ( ProcessInfoParameter p : params)
		{
			String para = p.getParameterName();
			if ( para.equals("AD_Migration_ID") )
				fromId  = p.getParameterAsInt();
			else if ( para.equals("AD_MigrationTo_ID") )
				toId = p.getParameterAsInt();
		}

		// launched from migration window
		if ( toId == 0 )
			toId = getRecord_ID();
		
		migrationTo = new MMigration(getCtx(), toId, get_TrxName());
		migrationFrom = new MMigration(getCtx(), fromId, get_TrxName());
	}

}
